package com.ffcactus.app.meeting.sdk;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Timer;

/**
 * Represents a booking of a meeting.
 */
public class Booking {
    private LocalDate submitDate;
    private LocalTime submitTime;
    private String employeeId;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * The comparator for the submit date and time.
     */
    public static Comparator<Booking> submitComparator;

    /**
     * The comparator for the meeting start time.
     */
    public static Comparator<Booking> startTimeComparator;

    static {
        submitComparator = Comparator.comparing(Booking::getSubmitDate).thenComparing(Booking::getSubmitTime);
        startTimeComparator = Comparator.comparing(Booking::getStartTime);
    }

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

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Validate the request.
     * @param configuration The configuration under which the validation is taken.
     * @throws InvalidBookingException When the validation failed.
     */
    public void validate (MeetingConfiguration configuration) throws InvalidBookingException {
        if (endTime.isBefore(startTime)) {
            throw new InvalidBookingException("Should not across midnight");
        }

        if (startTime.isBefore(configuration.getStartTime()) || endTime.isAfter((configuration.getEndTime()))) {
            throw new InvalidBookingException("Not in the time range.");
        }
    }

    @Override
    public String toString() {
        return "Booking{" +
                "submitDate=" + submitDate +
                ", submitTime=" + submitTime +
                ", employeeId='" + employeeId + '\'' +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
