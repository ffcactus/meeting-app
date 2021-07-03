package com.ffcactus.app.meeting;

import com.ffcactus.app.meeting.sdk.Booking;
import com.ffcactus.app.meeting.sdk.BookedMeeting;
import com.ffcactus.app.meeting.sdk.InvalidBookingException;
import com.ffcactus.app.meeting.sdk.MeetingConfiguration;

import java.util.Date;
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
     * List the current meetings that are booked on a specific day.
     * @param day The day to query.
     * @return The list of the meetings that are booked.
     */
    List<BookedMeeting> getBookedMeeting(Date day);
}
