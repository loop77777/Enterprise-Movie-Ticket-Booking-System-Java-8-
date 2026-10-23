Enterprise Movie Ticket Booking System (Java 8)
Detailed Real-Time Case Study Problem Statement
1. Executive Summary
   A leading entertainment company wants to build an enterprise-grade Online Movie Ticket Booking System similar to BookMyShow, PVR Cinemas, INOX, Cinepolis, and Ticketmaster.
   The platform should allow customers to browse movies, search theaters, view show timings, select seats, add optional concessions/amenities (Food & Beverage, 3D glasses, insurance), make payments via multiple gateways, and receive electronic tickets. The application must support thousands of concurrent users while maintaining high performance, security, reliability, and scalability.
   The system should be designed using modern Java 8 development practices and should demonstrate Core Java, Object-Oriented Programming (OOP) principles, SOLID principles, Enterprise Design Patterns (Strategy, Factory, Builder, Decorator, Facade, Repository, Observer, Explicit Locking), Collections Framework, Java 8 Functional Programming (Streams, Lambdas, Optional), Concurrency Control, Exception Handling, Data Structures, Algorithms, and Layered Architecture.
2. Business Background
   The organization currently manages movie bookings manually through theater counters and disconnected software systems. Customers experience:
   Long waiting queues at physical counters
   Seat availability conflicts and double bookings
   Payment failures without automated reconciliation
   Lack of online booking facilities, add-on selections, and instant ticket retrieval
   No centralized reporting mechanism for administrative decision-making
   To overcome these challenges, the company wants to build a centralized online movie ticketing platform.
3. Business Problem & Concurrency Challenge
   Movies are released every week across multiple theaters and multiple cities. Customers want a simple and efficient way to search currently running movies, check theater availability, view show timings, select seats, customize bookings with add-ons, complete online payments, and obtain electronic tickets instantly.
   The primary operational challenge arises when thousands of customers attempt to book the exact same seats simultaneously.
   Example Scenario
   A popular movie such as "KGF-3" releases on Friday for a 7:00 PM show:
   At 5:59 PM: Seat A1 is AVAILABLE.
   At exactly 6:00 PM: 100 users attempt to reserve Seat A1 simultaneously:
   Plaintext
   User 1   ───> Book Seat A1
   User 2   ───> Book Seat A1
   User 3   ───> Book Seat A1
   ...
   User 100 ───> Book Seat A1
   Without proper concurrency management, multiple customers receive the exact same seat, creating revenue loss, severe customer dissatisfaction, legal issues, and system instability.
   Core Guarantee
   Plaintext
   One Seat ───> One Customer ───> One Successful Booking
   All remaining 99 requests must receive a "Seat Already Booked / Locked" status and be added to a thread-safe waiting list queue.
4. Scope of the System
   A. Customer Services
   Account Management: Register Account, Login, Logout, Update Profile, Change Password.
   Movie Discovery: Search Movies by Name, View Movie Details, View Ratings, View Genre, View Language.
   Theater Discovery: Search Nearby Theaters by City, Location, or Movie; View Screens & Available Shows.
   Booking Management: Select Movie, Theater, Show, and Seats; Reserve Seats with temporary 5-minute locks; Customize with Add-ons (Popcorn Combos, 3D Glasses, Insurance); Confirm Booking.
   Payment Processing: Support for UPI, Credit Card, Debit Card, Net Banking, and Wallets.
   Ticket Management: Generate Ticket, Download Ticket, Email Ticket, Print Ticket, Cancel Ticket.
   Refund Management: Cancel Reservation, Process Refund, View Refund Status.
   B. Administrator Services
   Movie Operations: Add Movie (via Builder), Update Movie, Delete/Archive Movie.
   Theater Operations: Add Theater, Update Theater, Delete Theater.
   Screen Operations: Add Screen, Update Screen, Manage Capacity & Seating Grid.
   Show Operations: Create Show, Update Show, Cancel Show.
   Seat Operations: Block Seats, Open Seats, Manage Seat Pricing Tiers (Regular, Premium, VIP).
   Reporting Operations: Revenue Reports, Occupancy Reports, Booking Reports, Refund Reports.
5. Functional Requirements (FR)
   FR-1 Customer Registration: Register users with CustomerId, Name, Email, Phone Number, and encrypted Password.
   FR-2 Customer Login: Authenticate users with valid username and password credentials.
   FR-3 Search Movies: Search movies by Name, Language, Genre, Release Date, and Rating using Java 8 Streams.
   FR-4 Search Theaters: Search venues by City, Location, or specific Movie availability.
   FR-5 View Show Timings: View categorized showtimes (Morning, Matinee, Evening, Night).
   FR-6 Seat Selection: View available seats, booked seats, premium seats, and VIP seats in real time.
   FR-7 Seat Booking & Add-ons: Reserve seats, lock seats temporarily for 5 minutes, decorate bookings with optional add-ons, and confirm reservation upon payment.
   FR-8 Payment Processing: Integrate via Strategy Pattern with UPI, Credit/Debit Cards, Net Banking, and Wallets.
   FR-9 Unified Facade Endpoint: Provide a single entry point (BookingFacade) orchestrating locking, pricing decoration, payment processing, and ticketing.
   FR-10 Ticket Generation: Generate Booking Id, Ticket Number, Movie Name, Theater Name, Seat Numbers, Booking Date, and Show Time.
   FR-11 Notification System: Send asynchronous Email Notifications and SMS Alerts.
   FR-12 Waiting List: If seats are unavailable, add the customer to a thread-safe FIFO queue (ConcurrentLinkedQueue). Notify them when a seat is released.
   FR-13 Cancellation and Refund: Cancel tickets prior to show time, process automated refunds, and update booking status.
6. Non-Functional Requirements (NFR)
   Scalability: Support 10,000+ concurrent users.
   Performance Response Times:
   Movie Search <1" Second"
   Seat Booking <2" Seconds"
   Payment Processing <5" Seconds"
   Security: Authentication, Role-based Authorization, Encrypted Passwords, Secure Payments.
   Availability: 99.99% Uptime.
   Reliability: Guarantee no double bookings, consistent transactions, and accurate refunds.
   Maintainability: Modular architecture allowing addition of new payment methods, notification channels, report types, or add-ons without modifying existing modules.
7. Business Rules
   Rule 1: Customer must be logged in before booking.
   Rule 2: A booked or locked seat cannot be booked by another user.
   Rule 3: Payment must succeed before ticket generation.
   Rule 4: Cancelled tickets should trigger an automated refund.
   Rule 5: Temporary seat locks automatically expire after 5 minutes if unpaid.
   Rule 6: Only administrators can manage shows, screens, and seat pricing.
   Rule 7: Booking ID and Ticket ID must be globally unique.
   Rule 8: Customer cannot cancel tickets for shows that have already started or expired.

   
========================================================================================
                                   1. USER HIERARCHY
========================================================================================

                             ┌────────────────────────┐
                             │       <<abstract>>     │
                             │          User          │
                             ├────────────────────────┤
                             │ # id: String           │
                             │ # name: String         │
                             │ # email: String        │
                             │ # phone: String        │
                             │ # passwordHash: String │
                             │ # role: UserRole       │
                             ├────────────────────────┤
                             │ + getId(): String      │
                             │ + getName(): String    │
                             │ + getEmail(): String   │
                             │ + getPhone(): String   │
                             │ + getRole(): UserRole  │
                             │ + validatePassword()   │
                             └───────────▲────────────┘
                                         │
                    ┌────────────────────┴────────────────────┐
                    │                                         │
        ┌───────────┴───────────┐                 ┌───────────┴───────────┐
        │       Customer        │                 │         Admin         │
        ├───────────────────────┤                 ├───────────────────────┤
        │ - bookingHistory: List│                 │ - permissions: List   │
        ├───────────────────────┤                 ├───────────────────────┤
        │ + getBookings(): List │                 │ + hasPermission(): Bln│
        │ + addBooking(): void  │                 └───────────────────────┘
        └───────────────────────┘

========================================================================================
2. THEATER & SHOW LAYOUT
========================================================================================

┌───────────────┐ 1           * ┌───────────────┐ 1           * ┌───────────────┐
│    Theatre    │──(contains)───│    Screen     │──(composes)───│     Seat      │
├───────────────┤               ├───────────────┤               ├───────────────┤
│ - id: String  │               │ - id: String  │               │ - seatId: Str │
│ - name: Str   │               │ - name: Str   │               │ - row/col: Int│
│ - city: Str   │               │ - seats: Map  │               │ - type: Enum  │
│ - location:Str│               ├───────────────┤               │ - price: Dbl  │
├───────────────┤               │ + getId(): Str│               ├───────────────┤
│ + getScreens()│               │ + getSeats()  │               │ + getPrice()  │
└───────────────┘               └───────────────┘               └───────────────┘

┌───────────────────────────────┐
│             Movie             │
├───────────────────────────────┤
│ - id: String                  │
│ - title: String               │
│ - language: String            │
│ - genre: Genre                │
│ - durationInMinutes: int      │
│ - rating: double              │
│ - director: String            │
├───────────────────────────────┤
│ + getId(): String             │
│ + getTitle(): String          │
└───────────────▲───────────────┘
│ (constructed by)
┌───────────────┴───────────────┐
│     Movie.MovieBuilder        │  <--- BUILDER PATTERN
├───────────────────────────────┤
│ + duration(int): Builder      │
│ + rating(double): Builder     │
│ + director(String): Builder   │
│ + build(): Movie              │
└───────────────▲───────────────┘
│
│ 1 (references)
│
┌───────────────┴───────────────┐ 1           * ┌───────────────┐               ┌───────────────┐
│             Show              │──(tracks)────>│   ShowSeat    │──(status)────>│  SeatStatus   │
├───────────────────────────────┤               ├───────────────┤               ├───────────────┤
│ - id: String                  │               │ - seatId: Str │               │ AVAILABLE     │
│ - startTime: LocalDateTime    │               │ - status: Enum│               │ LOCKED        │
│ - endTime: LocalDateTime      │               │ - lockedBy    │               │ BOOKED        │
│ - showLock: ReentrantLock     │               │ - lockTime    │               └───────────────┘
│ - waitingQueue: Queue         │               ├───────────────┤
├───────────────────────────────┤               │ + lockSeat()  │
│ + getShowSeats(): Map         │               │ + releaseLock()│
│ + getShowLock(): ReentrantLock│               │ + isExpired() │
└───────────────▲───────────────┘               └───────────────┘
│
│ 1 (belongs to)
│
========================================================================================
3. BOOKING & TRANSACTION
========================================================================================

┌───────┴───────┐ 1           * ┌───────────────┐ 1           1 ┌───────────────┐
│    Booking    │──(reserves)──>│   ShowSeat    │   ┌───────────│    Ticket     │
├───────────────┤               └───────────────┘   │           ├───────────────┤
│ - bookingId   │                                   │           │ - ticketId    │
│ - totalAmount │───────────────────────────────────┤           │ - qrCode: Str │
│ - status: Enum│ 1                                 │           │ - showTime    │
├───────────────┤                                   │ 1         └───────────────┘
│ + setTicket() │                                   │
│ + setPayment()│                                   │ 1
└───────▲───────┘                                   │
│                                           │
│ * (placed by)                             ▼ 1
┌───────┴───────┐                       ┌───────────────┐
│   Customer    │                       │    Payment    │
└───────────────┘                       ├───────────────┤
│ - paymentId   │
│ - amount: Dbl │
│ - method: Enum│
│ - status: Enum│
└───────────────┘

========================================================================================
4. SERVICES & REPOSITORIES
========================================================================================

┌───────────────────────────────┐               ┌───────────────────────────────┐
│        BookingService         │               │        CatalogService         │
├───────────────────────────────┤               ├───────────────────────────────┤
│ - repository: DataRepository  │               │ - repository: DataRepository  │
│ - emailNotifier: Notification │               ├───────────────────────────────┤
│ - smsNotifier: Notification   │               │ + searchMovies(): List        │
├───────────────────────────────┤               │ + searchTheatresByCity(): List│
│ + reserveSeats(): Opt<Booking>│               │ + generateReports(): void     │
│ + confirmBooking():Opt<Ticket>│               └───────────────┬───────────────┘
│ + cancelBooking(): boolean    │                               │
└───────────────┬───────────────┘                               │
│ (uses)                                        │ (uses)
└───────────────────────┬───────────────────────┘
▼
┌───────────────────────────────┐
│        DataRepository         │  <--- REPOSITORY PATTERN
├───────────────────────────────┤
│ + users: Map<String, User>    │
│ + movies: Map<String, Movie>  │
│ + theatres: Map<Str, Theatre> │
│ + shows: Map<String, Show>    │
│ + bookings: Map<Str, Booking> │
└───────────────────────────────┘

========================================================================================
5. FACADE & DECORATOR PATTERNS (ADD-ONS)
========================================================================================

                        ┌───────────────────────────────┐
                        │         BookingFacade         │  <--- FACADE PATTERN
                        ├───────────────────────────────┤
                        │ - bookingService: Service     │
                        │ - repository: DataRepository  │
                        ├───────────────────────────────┤
                        │ + bookMovieTicket(): Opt<Tck> │
                        └───────────────┬───────────────┘
                                        │ (orchestrates)
                        ┌───────────────┴───────────────┐
                        │        TicketComponent        │  <--- DECORATOR PATTERN
                        ├───────────────────────────────┤
                        │ + getCost(): double           │
                        │ + getDescription(): String    │
                        └───────────────▲───────────────┘
                                        │
          ┌─────────────────────────────┴─────────────────────────────┐
          │                                                           │
┌─────────┴───────────────┐                       ┌───────────────────┴───────────┐
│       BaseTicket        │                       │        TicketDecorator        │
├─────────────────────────┤                       ├───────────────────────────────┤
│ - price: double         │                       │ # wrappedTicket: Component    │
│ - seatId: String        │                       └───────────────▲───────────────┘
└─────────────────────────┘                                       │
┌─────────────────────────────┼─────────────────────────────┐
│                             │                             │
┌───────────┴───────────┐     ┌───────────┴───────────┐     ┌───────────┴───────────┐
│ PopcornComboDecorator │     │  Glasses3DDecorator   │     │ InsuranceDecorator    │
└───────────────────────┘     └───────────────────────┘     └───────────────────────┘

========================================================================================
6. STRATEGY, FACTORY & NOTIFICATION
========================================================================================

                        ┌───────────────────────────────┐
                        │          <<interface>>        │
                        │        PaymentStrategy        │  <--- STRATEGY PATTERN
                        ├───────────────────────────────┤
                        │ + processPayment(): boolean   │
                        │ + processRefund(): boolean    │
                        └───────────────▲───────────────┘
                                        │
          ┌─────────────────────────────┼─────────────────────────────┐
          │                             │                             │
┌─────────┴───────────────┐ ┌───────────┴───────────────┐ ┌───────────┴───────────────┐
│   UPIPaymentStrategy    │ │   CardPaymentStrategy   │ │NetBankingPaymentStrategy│
└─────────────────────────┘ └─────────────────────────┘ └─────────────────────────┘
▲
│ (creates)
┌───────────────┴───────────────┐
│    PaymentStrategyFactory     │  <--- FACTORY PATTERN
└───────────────────────────────┘

                        ┌───────────────────────────────┐
                        │          <<interface>>        │
                        │      NotificationService      │  <--- OBSERVER PATTERN
                        ├───────────────────────────────┤
                        │ + sendNotification(): void    │
                        └───────────────▲───────────────┘
                                        │
                        ┌───────────────┴───────────────┐
                        │                               │
            ┌───────────┴───────────┐       ┌───────────┴───────────┐
            │EmailNotificationServ. │       │ SMSNotificationServ.  │
            └───────────────────────┘       └───────────────────────┘

com.movieticket
├── Main.java
├── enums/
│    ├── UserRole.java
│    ├── Genre.java
│    ├── SeatType.java
│    ├── SeatStatus.java
│    ├── BookingStatus.java
│    ├── PaymentStatus.java
│    └── PaymentMethod.java
├── model/
│    ├── User.java
│    ├── Customer.java
│    ├── Admin.java
│    ├── Movie.java
│    ├── Seat.java
│    ├── Screen.java
│    ├── Theatre.java
│    ├── ShowSeat.java
│    ├── Show.java
│    ├── Ticket.java
│    ├── Payment.java
│    └── Booking.java
├── decorator/
│    ├── TicketComponent.java
│    ├── BaseTicket.java
│    ├── TicketDecorator.java
│    ├── PopcornComboDecorator.java
│    ├── Glasses3DDecorator.java
│    └── CancellationInsuranceDecorator.java
├── repository/
│    └── DataRepository.java
├── strategy/
│    ├── PaymentStrategy.java
│    ├── UPIPaymentStrategy.java
│    ├── CardPaymentStrategy.java
│    ├── NetBankingPaymentStrategy.java
│    └── PaymentStrategyFactory.java
├── notification/
│    ├── NotificationService.java
│    ├── EmailNotificationService.java
│    └── SMSNotificationService.java
├── service/
│    ├── BookingService.java
│    └── CatalogService.java
└── facade/
└── BookingFacade.java

