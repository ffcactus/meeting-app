package com.ffcactus.app.meeting;

import com.ffcactus.app.meeting.sdk.Booking;
import com.ffcactus.app.meeting.sdk.InvalidBookingException;
import com.ffcactus.app.meeting.sdk.MeetingConfiguration;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingServiceImplTests {

    private static final String exampleInput =
            "0900 1730\n" +
            "2011-03-17 10:17:06 EMP001\n" + "2011-03-21 09:00 2\n" +
            "2011-03-16 12:34:56 EMP002\n" + "2011-03-21 09:00 2\n" +
            "2011-03-16 09:28:23 EMP003\n" + "2011-03-22 14:00 2\n" +
            "2011-03-17 11:23:45 EMP004\n" + "2011-03-22 16:00 1\n" +
            "2011-03-15 17:29:12 EMP005\n" + "2011-03-21 16:00 3\n";

    @Test
    public void testCaseFromExample() {
        MeetingRepository repository = new MeetingRepositoryImpl();
        MeetingService s = new MeetingServiceImpl(repository);

        String[] lines = exampleInput.split("\n");
        // set configuration.
        MeetingConfiguration configuration = parseConfiguration(lines[0]);
        s.saveConfiguration(configuration);
        System.out.println(configuration);

        // retrieve all the bookings.
        List<Booking> bookings = new ArrayList<Booking>();

        for (int i = 1; i < lines.length; i += 2) {
            bookings.add(parseBooking(lines[i], lines[i + 1]));
        }
        // sort the booking by their submit date and time.
        bookings.sort(Booking.submitComparator);
        for (Booking booking : bookings) {
            try {
                boolean booked = s.book(booking);
                System.out.println(booking + " " + booked);
            } catch (InvalidBookingException e) {
                System.out.println(booking + " " + e);
            }
        }
    }

    private MeetingConfiguration parseConfiguration(String line) {
        String[] ss = line.split(" ");
        int startHour = Integer.parseInt(ss[0].substring(0, 2));
        int startMin = Integer.parseInt(ss[0].substring(2));
        int endHour = Integer.parseInt(ss[1].substring(0, 2));
        int endMin = Integer.parseInt(ss[1].substring(2));
        return new MeetingConfiguration(
                LocalTime.of(startHour, startMin),
                LocalTime.of(endHour, endMin)
        );
    }

    private Booking parseBooking(String s1, String s2) {
        Booking booking = new Booking();
        String[] ss1 = s1.split(" ");
        String[] ss2 = s2.split(" ");

        booking.setSubmitDate(parseDate(ss1[0]));
        booking.setSubmitTime(parseTime(ss1[1]));
        booking.setEmployeeId(ss1[2]);
        booking.setStartDate(parseDate(ss2[0]));
        booking.setStartTime(parseTime(ss2[1]));
        booking.setEndTime(booking.getStartTime().plusHours(Integer.parseInt(ss2[2])));
        return booking;
    }

    private LocalDate parseDate(String s) {
        String[] ss = s.split("-");
        int year = Integer.parseInt(ss[0]);
        int month = Integer.parseInt(ss[1]);
        int day = Integer.parseInt(ss[2]);
        return LocalDate.of(year,month, day);
    }

    private LocalTime parseTime(String s) {
        String[] ss = s.split(":");
        int hour = Integer.parseInt(ss[0]);
        int min = Integer.parseInt(ss[1]);
        if (ss.length == 2) {
            return LocalTime.of(hour, min);
        } else if (ss.length == 3) {
            int second = Integer.parseInt(ss[2]);
            return LocalTime.of(hour, min, second);
        }
        return null;
    }
}
