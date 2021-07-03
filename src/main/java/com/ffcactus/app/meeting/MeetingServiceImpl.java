package com.ffcactus.app.meeting;

import com.ffcactus.app.meeting.sdk.BookingRequest;
import com.ffcactus.app.meeting.sdk.BookedMeeting;
import com.ffcactus.app.meeting.sdk.MeetingConfiguration;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * This is the implementation for {@link MeetingService}. It assume that a meeting can not across the midnight
 * of the day. It also assume that the data is saved in a SQL database, the data type will take this into account.
 */
public class MeetingServiceImpl implements MeetingService {

    private MeetingConfiguration configuration;

    public MeetingConfiguration getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(MeetingConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Boolean book(BookingRequest request) {
        validateBookingRequest(request);

        return null;
    }

    @Override
    public List<BookedMeeting> getBookedMeeting(Date day) {
        return null;
    }

    private void validateBookingRequest (BookingRequest request) {
        if (configuration == null) {
            throw new IllegalStateException("Configuration not set");
        }
        LocalTime startTime = request.getStartTime();
        LocalTime endTime = startTime.plusHours(request.getDuration());

        if (endTime.isBefore(startTime)) {
            throw new RuntimeException("Should not across midnight");
        }

        if (startTime.isAfter(configuration.getStartTime()) && endTime.isBefore((configuration.getEndTime()))) {
            throw new RuntimeException("Not in the time range.");
        }
    }
}

