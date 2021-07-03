package com.ffcactus.app.meeting.sdk;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This object represents a booked meeting.
 */
public class BookedMeeting {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String employeeId;

    public BookedMeeting(LocalDate startDate, LocalTime startTime, LocalTime endTime, String employeeId) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employeeId = employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
