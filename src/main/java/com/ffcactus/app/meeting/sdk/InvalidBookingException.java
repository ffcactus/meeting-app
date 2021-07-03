package com.ffcactus.app.meeting.sdk;

/**
 * Represents a exception in booking validation.
 */
public class InvalidBookingException extends Exception {
    public InvalidBookingException(String msg) {
        super(msg);
    }
}
