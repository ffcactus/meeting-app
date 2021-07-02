package com.ffcactus.app.meeting;

import com.ffcactus.app.meeting.sdk.BookMeetingRequest;
import com.ffcactus.app.meeting.sdk.BookedMeeting;

import java.util.Date;
import java.util.List;

/**
 * This interface includes all the public API this application.
 */
public interface MeetingService {
    /**
     * Try to book a meeting.
     * @param request The request detail.
     * @return If this booking is succeed.
     */
    Boolean book(BookMeetingRequest request);

    /**
     * List the current meetings that are booked on a specific day.
     * @param day The day to query.
     * @return The list of the meetings that are booked.
     */
    List<BookedMeeting> getBookedMeeting(Date day);
}
