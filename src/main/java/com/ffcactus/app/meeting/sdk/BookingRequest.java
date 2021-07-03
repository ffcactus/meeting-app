package com.ffcactus.app.meeting.sdk;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;

/**
 * The DTO of book meeting request.
 */
public class BookingRequest {
    private LocalDate submitDate;
    private LocalTime submitTime;
    private String employeeId;
    private LocalDate startDate;
    private LocalTime startTime;
    private Integer duration;

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }

    public LocalTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalTime submitTime) {
        this.submitTime = submitTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
