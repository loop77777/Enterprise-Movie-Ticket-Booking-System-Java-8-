# Enterprise Movie Ticket Booking System

A Java 8-based simulation of a movie ticket booking platform with concurrency-focused seat reservation logic, booking services, and reporting features.

## Overview

This project demonstrates how an enterprise-style movie ticket booking system can manage:

- Movie catalog creation
- Theatre and screen configuration
- Seat reservation with concurrency control
- Waiting list processing
- Payment and add-on flows
- Revenue and occupancy reporting
- Booking cancellation handling

## Main Features

- Concurrent booking simulation for multiple users
- Seat lock reservation logic for the same seat
- Waiting queue for unavailable seats
- Facade pattern for ticket booking with extra add-ons
- Decorator-style booking workflow integration
- Java 8 compatible implementation

## Project Structure

```text
src/
├── com/
│   └── movieticket/
│       ├── Main.java
│       ├── enums/
│       ├── facade/
│       ├── model/
│       ├── repository/
│       └── service/
```

## Prerequisites

- Java 8 or newer
- Command line / terminal

## Run the Project

### Windows PowerShell

```powershell
cd "C:\Users\Asus\IdeaProjects\java 2026 batch\Enterprise Movie Ticket Booking System"
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out com.movieticket.Main
```

## Example Behavior

The application:

1. Creates a sample movie, theatre, screen, and show
2. Simulates 100 concurrent users trying to book the same seat
3. Checks which reservation succeeds under concurrency
4. Processes a winning booking with add-ons and ticket generation
5. Produces a business report and cancellation workflow summary

## Notes

This is a demonstration project focused on system design and concurrency behavior rather than a full production-ready web application.
