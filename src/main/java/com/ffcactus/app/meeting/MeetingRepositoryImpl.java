package com.ffcactus.app.meeting;

import com.ffcactus.app.meeting.sdk.Booking;
import com.ffcactus.app.meeting.sdk.MeetingConfiguration;

import java.time.LocalDate;
import java.util.*;

/**
 * A SQL style DB implementation. It's just a mock.
 */
public class MeetingRepositoryImpl implements MeetingRepository {
    private MeetingConfiguration configuration;

    // Using tree map here. In SQL we can sort by start date.
    private Map<LocalDate, List<Booking>> allBookings = new TreeMap<>();

    @Override
    public void saveConfiguration(MeetingConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public MeetingConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public List<Booking> getAndSortMeetingOfDate(LocalDate date) {
        List<Booking> bookings = allBookings.get(date);
        if (bookings == null) {
            return new ArrayList<>();
        }

        Collections.sort(bookings, Booking.startTimeComparator);
        return bookings;
    }

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
}
