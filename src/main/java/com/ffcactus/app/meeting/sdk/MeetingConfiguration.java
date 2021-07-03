package com.ffcactus.app.meeting.sdk;

import java.time.LocalTime;

/**
 * Represents the meeting configuration.
 */
public class MeetingConfiguration {
    private LocalTime startTime;
    private LocalTime endTime;

    public MeetingConfiguration(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "MeetingConfiguration{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
