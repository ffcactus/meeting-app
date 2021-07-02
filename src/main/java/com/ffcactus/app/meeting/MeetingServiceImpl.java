package com.ffcactus.app.meeting;

import com.ffcactus.app.meeting.sdk.BookMeetingRequest;
import com.ffcactus.app.meeting.sdk.BookedMeeting;

import java.util.Date;
import java.util.List;

/**
 * This is the implementation for {@link MeetingService}. It assume that a meeting can not across the midnight
 * of the day. It also assume that the data is saved in a SQL database, the data type will take this into account.
 */
public class MeetingServiceImpl implements MeetingService {
    @Override
    public Boolean book(BookMeetingRequest request) {
        return null;
    }

    @Override
    public List<BookedMeeting> getBookedMeeting(Date day) {
        return null;
    }
}

