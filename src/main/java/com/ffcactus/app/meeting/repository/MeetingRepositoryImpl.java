package com.ffcactus.app.meeting.repository;

import com.ffcactus.app.meeting.repository.MeetingRepository;
import com.ffcactus.app.meeting.sdk.Booking;
import com.ffcactus.app.meeting.sdk.MeetingConfiguration;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A SQL style DB implementation. It's just a mock.
 */
public class MeetingRepositoryImpl implements MeetingRepository {
    private MeetingConfiguration configuration;

    /**
     * The map saving all the bookings for each of date. Using tree map here. In SQL we can sort by start date.
     */
    private final Map<LocalDate, List<Booking>> allBookings = new TreeMap<>();

    @Override
    public void saveConfiguration(MeetingConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public MeetingConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Get all the booking of the dates and sort the bookings by start time.
     * @param date The specific date for the meetings.
     * @return The sorted bookings of the date.
     */
    @Override
    public List<Booking> getAndSortMeetingOfDate(LocalDate date) {
        List<Booking> bookings = allBookings.get(date);
        if (bookings == null) {
            return new ArrayList<>();
        }

        bookings.sort(Booking.startTimeComparator);
        return bookings;
    }

    /**
     * Save the booking. The booking will be saved to the list that contains all the bookings of that date.
     * @param booking The booking to save.
     */
    @Override
    public void saveBooking(Booking booking) {
        LocalDate startDate = booking.getStartDate();
        List<Booking> bookings = allBookings.get(startDate);
        if (bookings == null) {
            List<Booking> newDateBookings = new ArrayList<>();
            newDateBookings.add(booking);
            allBookings.put(startDate, newDateBookings);
        } else {
            bookings.add(booking);
        }
    }

    /**
     * Get dates that have bookings and sort the dates.
     * @return The dates that is sorted.
     */
    @Override
    public List<LocalDate> getSortedBookingDates() {
        Set<LocalDate> dates = allBookings.keySet();
        return dates.stream().sorted().collect(Collectors.toList());
    }
}
