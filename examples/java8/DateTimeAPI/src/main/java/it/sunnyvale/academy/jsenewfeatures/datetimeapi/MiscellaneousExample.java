package it.sunnyvale.academy.jsenewfeatures.datetimeapi;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.zone.ZoneOffsetTransition;

import static java.time.Month.*;
import static java.time.temporal.ChronoUnit.*;

public class MiscellaneousExample {

    public static void main(String[] args) throws InterruptedException {
        ZoneId nyTZ = ZoneId.of("America/New_York");
        ZoneId EST = ZoneId.of("US/Eastern");
        ZoneId Romeo = ZoneId.of("Europe/London");

        ZoneOffset USEast = ZoneOffset.of("-5");
        ZoneOffset Nepal = ZoneOffset.ofHoursMinutes(5, 45);
        ZoneId EST1 = ZoneId.ofOffset("UTC", USEast);

        ZoneId USEast1 = ZoneId.of("America/New_York");
        LocalDate date = LocalDate.of(2014, MARCH, 23);
        LocalTime time = LocalTime.of(9, 30);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        ZonedDateTime courseStart = ZonedDateTime.of(date, time, USEast);
        ZonedDateTime hereNow = ZonedDateTime.now(USEast).truncatedTo(MINUTES);
        System.out.println("Here now:         " + hereNow);
        System.out.println("Course start:     " + courseStart);
        ZonedDateTime newCourseStart = courseStart.plusDays(2).minusMinutes(30);
        System.out.println("New Course Start: " + newCourseStart);

        // DST Begins March 9th, 2014
        LocalDate meetDate = LocalDate.of(2014, MARCH, 8);
        LocalTime meetTime = LocalTime.of(16, 00);
        ZonedDateTime meeting = ZonedDateTime.of(meetDate, meetTime, USEast);
        System.out.println("meeting time:     " + meeting);
        ZonedDateTime newMeeting = meeting.plusDays(1);
        System.out.println("new meeting time: " + newMeeting);

        // Ask the rules if there was a gap or overlap
        ZoneId USEast2 = ZoneId.of("America/New_York");
        LocalDateTime lateNight = LocalDateTime.of(2014, MARCH, 9, 2, 30);
        ZoneOffsetTransition zot = USEast.getRules().getTransition(lateNight);
        if (zot != null) {
            if (zot.isGap())
                System.out.println("gap");
            if (zot.isOverlap())
                System.out.println("overlap");
        }

        LocalDateTime meeting1 = LocalDateTime.of(2014, JUNE, 13, 12, 30);
        ZoneId SanFran = ZoneId.of("America/Los_Angeles");
        ZonedDateTime staffCall = ZonedDateTime.of(meeting1, SanFran);
        OffsetDateTime off = staffCall.toOffsetDateTime();

        ZoneId London = ZoneId.of("Europe/London");
        OffsetDateTime staffCallOffset = staffCall.toOffsetDateTime();
        ZonedDateTime staffCallUK = staffCallOffset.atZoneSameInstant(London);
        System.out.println("Staff call (Pacific) is at: " + staffCall);
        System.out.println("Staff call (UK) is at:      " + staffCallUK);

        // Instant
        Instant now = Instant.now();
        Thread.sleep(0, 1); // long milliseconds, int nanoseconds
        Instant later = Instant.now();
        System.out.println("now is before later? " + now.isBefore(later));
        System.out.println("Now:   " + now);
        System.out.println("Later: " + later);

        // Period
        Period oneDay = Period.ofDays(1);
        System.out.println("Period of one day: " + oneDay);
        LocalDateTime beforeDST = LocalDateTime.of(2014, MARCH, 8, 12, 00);
        ZonedDateTime newYorkTime = ZonedDateTime.of(beforeDST, ZoneId.of("America/New_York"));
        System.out.println("Before: " + newYorkTime);
        System.out.println("After:  " + newYorkTime.plus(oneDay));

        // Duration
        Duration one24hourDay = Duration.ofDays(1);
        System.out.println("Duration of one day: " + one24hourDay);
        beforeDST = LocalDateTime.of(2014, MARCH, 8, 12, 00);
        newYorkTime = ZonedDateTime.of(beforeDST, ZoneId.of("America/New_York"));
        System.out.println("Before: " + newYorkTime);
        System.out.println("After:  " + newYorkTime.plus(one24hourDay));

        // TemporalUnit
        LocalDate christmas = LocalDate.of(2014, DECEMBER, 25);
        LocalDate today = LocalDate.now();
        long days = DAYS.between(today, christmas);
        System.out.println("There are " + days + " shopping days til Christmas");

        Period tilXMas = Period.between(today, christmas);
        System.out.println(
                "There are " + tilXMas.getMonths() + " months and " + tilXMas.getDays() + " days til Christmas");

        // DateTimeFormatter
        ZonedDateTime now1 = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        System.out.println(now1.format(formatter));
        formatter = DateTimeFormatter.ISO_ORDINAL_DATE;
        System.out.println(now1.format(formatter));
        formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy G, hh:mm a VV");
        System.out.println(now1.format(formatter));
        formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println(now1.format(formatter));

        // Not very readable - is this June 11 or November 6th?
        LocalDate myBday = LocalDate.of(1970, 6, 11);

        // A fluent approach
        myBday = Year.of(1970).atMonth(JUNE).atDay(11);

        // Schedule a meeting fluently
        LocalDateTime meeting2 = LocalDate.of(2014, MARCH, 25).atTime(12, 30);

        // Schedule that meeting using the London timezone
        ZonedDateTime meetingUK = meeting2.atZone(ZoneId.of("Europe/London"));

        // What time is it in San Francisco for that meeting?
        ZonedDateTime earlyMeeting = meetingUK.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));

    }
}