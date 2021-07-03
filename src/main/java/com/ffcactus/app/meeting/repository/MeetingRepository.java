package com.ffcactus.app.meeting.repository;

import com.ffcactus.app.meeting.sdk.Booking;
import com.ffcactus.app.meeting.sdk.MeetingConfiguration;

import java.time.LocalDate;
import java.util.List;

// The persistent system interface.
public interface MeetingRepository {
    /**
     * Save the configuration in DB.
     * @param cfg The configuration.
     */
    void saveConfiguration(MeetingConfiguration cfg);

    /**
     * Get the configuration from DB.
     * @return The configuration.
     */
    MeetingConfiguration getConfiguration();

    /**
     * Get the meetings of the specified date. The meeting will be sored by the start time.
     * @param date The specific date for the meetings.
     * @return The meetings sorted by the start time. If no meetings for the date, an empty list will be returned.
     */
    List<Booking> getAndSortMeetingOfDate(LocalDate date);

    /**
     * Save a booking.
     * @param booking The booking to save.
     */
    void saveBooking(Booking booking);

    /**
     * Return all the dates that has a bookings. The dates will be sorted.
     * @return The sorted booking dates.
     */
    List<LocalDate> getSortedBookingDates();
}
