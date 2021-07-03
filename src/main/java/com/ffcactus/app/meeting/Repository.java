package com.ffcactus.app.meeting;

import com.ffcactus.app.meeting.sdk.BookingRequest;
import com.ffcactus.app.meeting.sdk.BookedMeeting;

import java.time.LocalDate;
import java.util.List;

// The persistent system interface.
public interface Repository {
    List<BookedMeeting> GetMeetingOfDate(LocalDate date);
    void AddBooking(BookingRequest booking);
}
