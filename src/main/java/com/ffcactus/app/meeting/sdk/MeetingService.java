package com.ffcactus.app.meeting.sdk;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface includes all the public API this application.
 */
public interface MeetingService {

    /**
     * Getter of {link MeetingConfiguration}.
     * @return the current configuration.
     */
    MeetingConfiguration getConfiguration();

    /**
     * Setter of {link MeetingConfiguration}.
     * @param configuration The configuration.
     */
    void saveConfiguration(MeetingConfiguration configuration);

    /**
     * Try to book a meeting.
     * @param request The request detail.
     * @return If this booking is succeed.
     */
    Boolean book(Booking request) throws InvalidBookingException;

    /**
     * Get all the dates that have valid bookings, and sort the dates.
     * @return The sorted booking dates.
     */
    List<LocalDate> getSortedBookingDates();

    /**
     * List the current meetings that are booked on a specific day.
     * @param date The day to query.
     * @return The list of the meetings that are booked.
     */
    List<Booking> getAndSortBookingsOfDate(LocalDate date);
}
