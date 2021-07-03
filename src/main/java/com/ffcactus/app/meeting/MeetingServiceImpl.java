package com.ffcactus.app.meeting;

import com.ffcactus.app.meeting.repository.MeetingRepository;
import com.ffcactus.app.meeting.sdk.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * This is the implementation for {@link MeetingService}.
 * It assume that a meeting can not across the midnight of the day.
 * It also assume that the data is saved in a SQL database, the data type will take this into account.
 */
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository repository;

    public MeetingServiceImpl(MeetingRepository repository) {
        this.repository = repository;
    }

    /**
     * Get the configuration from repository.
     * @return the saved configuration.
     */
    public MeetingConfiguration getConfiguration() {
        return repository.getConfiguration();
    }

    /**
     * Save the configuration to repository.
     * @param configuration The configuration.
     */
    public void saveConfiguration(MeetingConfiguration configuration) {
        repository.saveConfiguration(configuration);
    }

    /**
     * Book a meeting. It will validated the request against the configuration, so make sure the configuration is set in advance.
     * It will check if the there is a time zone available for the date.
     *
     * @param booking The booking detail.
     * @return Return true if there is a time zone available or False otherwise.
     * @throws InvalidBookingException When the booking is invalided according to the configuration.
     */
    @Override
    public Boolean book(Booking booking) throws InvalidBookingException {
        // reload configuration before each of the booking, make sure it is synced.
        MeetingConfiguration configuration = repository.getConfiguration();
        if (configuration == null) {
            throw new RuntimeException("Configuration not set.");
        }
        booking.validate(configuration);

        boolean available;
        // The following block should be put in a transaction in a serialization level.
        {
            available = hasTimeZoneAvailable(booking, repository.getAndSortMeetingOfDate(booking.getStartDate()));
            if (available) {
                repository.saveBooking(booking);
            }
        }
        return available;
    }

    /**
     * Check if there is a time zone available in the current bookings for the new booking.
     * @param booking New booking.
     * @param currentBookings Current bookings sorted by it's start time.
     * @return If there is a time zone available.
     */
    private boolean hasTimeZoneAvailable(Booking booking, List<Booking> currentBookings) {
        // Can be taken as the first booking of the day.
        if (currentBookings.isEmpty()) {
            return true;
        }

        // Now we have at least one booked meeting. Now we check if the new meeting can be set as the first one or the last one.
        LocalTime bookingStart = booking.getStartTime();
        LocalTime bookingEnd = booking.getEndTime();
        LocalTime firstStart = currentBookings.get(0).getStartTime();
        LocalTime lastEnd = currentBookings.get(currentBookings.size() - 1).getEndTime();
        if (bookingEnd.isBefore(firstStart) || bookingEnd.equals(firstStart)) {
            return true;
        }
        if (bookingStart.isAfter(lastEnd) || bookingStart.equals(lastEnd)) {
            return true;
        }

        // If the new meeting can't be set as the first one or the last one, and there is only one booked meeting,
        // It means the new meeting can't be inserted.
        if (currentBookings.size() == 1) {
            return false;
        }

        // Now we have at least 2 booked meetings. The new meeting can only be inserted into the gaps.
        for (int i = 0; i < currentBookings.size() - 2; i++) {
            LocalTime gapStart = currentBookings.get(i).getEndTime();
            LocalTime gapEnd = currentBookings.get(i + 1).getStartTime();

            boolean startSatisfied = bookingStart.equals(gapStart) || bookingStart.isAfter(gapStart);
            boolean endSatisfied = bookingEnd.equals(gapEnd) || bookingEnd.isBefore(gapEnd);
            if (startSatisfied && endSatisfied) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get sorted booking dates from repository.
     * @return The sorted booking dates.
     */
    @Override
    public List<LocalDate> getSortedBookingDates() {
        return repository.getSortedBookingDates();
    }

    /**
     * Get sorted booking of a specific date from repository.
     * @param date The date to query.
     * @return The sorted booking.
     */
    @Override
    public List<Booking> getAndSortBookingsOfDate(LocalDate date) {
        return repository.getAndSortMeetingOfDate(date);
    }
}

