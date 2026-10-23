package com.movieticket;

import com.movieticket.enums.Genre;
import com.movieticket.enums.PaymentMethod;
import com.movieticket.enums.SeatType;
import com.movieticket.facade.BookingFacade;
import com.movieticket.model.Booking;
import com.movieticket.model.Customer;
import com.movieticket.model.Movie;
import com.movieticket.model.Screen;
import com.movieticket.model.Seat;
import com.movieticket.model.Show;
import com.movieticket.model.Theatre;
import com.movieticket.model.Ticket;
import com.movieticket.repository.DataRepository;
import com.movieticket.service.BookingService;
import com.movieticket.service.CatalogService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // # System banner and startup message
        System.out.println("=========================================================");
        System.out.println("   ENTERPRISE MOVIE TICKET BOOKING SYSTEM (JAVA 8)       ");
        System.out.println("=========================================================\n");

        // # Initialize application services and repositories
        DataRepository repository = new DataRepository();
        BookingService bookingService = new BookingService(repository);
        CatalogService catalogService = new CatalogService(repository);
        BookingFacade bookingFacade = new BookingFacade(bookingService);

        // # Create a sample movie for the demo
        Movie movie = new Movie.MovieBuilder("M-101", "KGF-3", "Kannada", Genre.ACTION)
                .duration(180)
                .rating(4.9)
                .director("Prashanth Neel")
                .build();
        repository.movies.put(movie.getId(), movie);

        // # Add seats to the screen
        List<Seat> screenSeats = Arrays.asList(
                new Seat("A1", 1, 1, SeatType.REGULAR, 200.0),
                new Seat("A2", 1, 2, SeatType.REGULAR, 200.0),
                new Seat("A3", 1, 3, SeatType.PREMIUM, 350.0)
        );

        // # Create theatre and screen setup
        Screen screen = new Screen("SCR-1", "IMAX Screen 1", screenSeats);
        Theatre theatre = new Theatre(
                "TH-1",
                "PVR Cinemas",
                "Bengaluru",
                "Koramangala",
                Collections.singletonList(screen)
        );
        repository.theatres.put(theatre.getId(), theatre);

        // # Configure a sample show in the near future
        Show show = new Show(
                "SH-501",
                movie,
                theatre,
                screen,
                LocalDateTime.now().plusHours(2),
                LocalDateTime.now().plusHours(5)
        );
        repository.shows.put(show.getId(), show);

        // # Seed the repository with 100 simulated customers
        int concurrentUserCount = 100;
        for (int i = 1; i <= concurrentUserCount; i++) {
            String userId = "USR-" + i;
            Customer customer = new Customer(
                    userId,
                    "Customer_" + i,
                    "user" + i + "@example.com",
                    "98765432" + i,
                    "password"
            );
            repository.users.put(userId, customer);
        }

        // # Display the target booking scenario
        System.out.println("Target Show: " + show.getMovie().getTitle()
                + " at " + theatre.getName() + " (" + theatre.getCity() + ")");
        System.out.println("# Simulating " + concurrentUserCount
                + " concurrent users attempting to lock Seat 'A1' simultaneously...\n");

        // # Concurrency test setup
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        CountDownLatch latch = new CountDownLatch(1);
        List<Future<Optional<Booking>>> futures = new ArrayList<>();

        for (int i = 1; i <= concurrentUserCount; i++) {
            final String userId = "USR-" + i;
            Callable<Optional<Booking>> task = () -> {
                latch.await();
                return bookingService.reserveSeats(userId, "SH-501", Collections.singletonList("A1"));
            };
            futures.add(executorService.submit(task));
        }

        // # Release all threads at once to trigger concurrent seat booking
        latch.countDown();

        // # Gather successful bookings from all concurrent attempts
        List<Booking> successfulLocks = new ArrayList<>();
        for (Future<Optional<Booking>> future : futures) {
            try {
                Optional<Booking> result = future.get();
                result.ifPresent(successfulLocks::add);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        // # Display concurrency summary
        System.out.println("\n---------------- CONCURRENCY TEST SUMMARY ----------------");
        System.out.println("Total Concurrent Requests: " + concurrentUserCount);
        System.out.println("Successful Locks Granted: " + successfulLocks.size());
        System.out.println("Waiting List Queue Size: " + show.getWaitingListQueue().size());
        System.out.println("----------------------------------------------------------\n");

        // # Validate the winning booking with facade and add-on workflow
        if (!successfulLocks.isEmpty()) {
            Booking winningBooking = successfulLocks.get(0);
            String winningUserId = winningBooking.getCustomer().getId();

            System.out.println("# Testing Facade + Decorator Workflow for Winning User [" + winningUserId + "]...");

            Optional<Ticket> ticketOpt = bookingFacade.bookMovieTicketWithAddons(
                    winningUserId,
                    "SH-501",
                    Collections.singletonList("A1"),
                    PaymentMethod.UPI,
                    true,   // # Add Popcorn
                    true,   // # Add 3D Glasses
                    false   // # Add Insurance
            );

            ticketOpt.ifPresent(ticket -> System.out.println("\nFINAL ISSUED E-TICKET:\n" + ticket));
        }

        // # Generate reporting output
        catalogService.generateRevenueAndOccupancyReport();

        // # Test cancellation and priority alert pipeline
        if (!successfulLocks.isEmpty()) {
            System.out.println("# Testing Cancellation & Priority Alert Pipeline...");
            String winningBookingId = successfulLocks.get(0).getBookingId();
            bookingService.cancelBooking(winningBookingId);
        }
    }
}