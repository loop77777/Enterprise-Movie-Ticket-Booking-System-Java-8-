package com.movieticket.model;

import com.movieticket.enums.SeatStatus;
import com.movieticket.enums.SeatType;
import java.time.Duration;
import java.time.LocalDateTime;

// # Class: ShowSeat
// # Purpose: Handles showseat logic in this module.
public class ShowSeat {
    private final String seatId;
    private final SeatType seatType;
    private final double price;
    private SeatStatus status;
    private String lockedByUserId;
    private LocalDateTime lockTimestamp;

// # Method: ShowSeat
// # Purpose: Executes the ShowSeat workflow for this class.
    public ShowSeat(String seatId, SeatType seatType, double price) {
        this.seatId = seatId;
        this.seatType = seatType;
        this.price = price;
        this.status = SeatStatus.AVAILABLE;
    }

// # Method: getSeatId
// # Purpose: Executes the getSeatId workflow for this class.
    public String getSeatId() { return seatId; }
// # Method: getPrice
// # Purpose: Executes the getPrice workflow for this class.
    public double getPrice() { return price; }
// # Method: getStatus
// # Purpose: Executes the getStatus workflow for this class.
    public SeatStatus getStatus() { return status; }

// # Method: isLockExpired
// # Purpose: Executes the isLockExpired workflow for this class.
    public boolean isLockExpired(int lockTimeoutMinutes) {
        if (status != SeatStatus.LOCKED || lockTimestamp == null) return false;
        return Duration.between(lockTimestamp, LocalDateTime.now()).toMinutes() >= lockTimeoutMinutes;
    }

// # Method: lockSeat
// # Purpose: Executes the lockSeat workflow for this class.
    public void lockSeat(String userId) {
        this.status = SeatStatus.LOCKED;
        this.lockedByUserId = userId;
        this.lockTimestamp = LocalDateTime.now();
    }

// # Method: releaseLock
// # Purpose: Executes the releaseLock workflow for this class.
    public void releaseLock() {
        this.status = SeatStatus.AVAILABLE;
        this.lockedByUserId = null;
        this.lockTimestamp = null;
    }

// # Method: setStatus
// # Purpose: Executes the setStatus workflow for this class.
    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}