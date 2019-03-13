package it.sunnyvale.jsenewfeature.datetimeformatting.test;

import static org.junit.Assert.assertTrue;
import it.sunnyvale.jsenewfeature.datetimeformatting.DateTimeFormatting;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.Test;

public class DateTimeFormattingTest {

    @Test
    public void testFormatLocalDate() {
        LocalDate localDate = LocalDate.of(1981, 04, 23);
        String formattedString = DateTimeFormatting.format(localDate);
        assertTrue(formattedString.matches("23::Apr::1981"));
    }

    @Test
    public void testFormatLocalDateTime() {
        LocalDate localDate = LocalDate.of(1981, 04, 23);
        LocalTime localTime = LocalTime.of(20, 0);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        String formattedString = DateTimeFormatting.format(localDateTime);
        assertTrue(formattedString.matches("23::Apr::1981 20::00::00"));
    }

    @Test
    public void testFormatInstant() {
        LocalDate localDate = LocalDate.of(1981, 04, 23);
        LocalTime localTime = LocalTime.of(20, 0);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        Instant instant = localDateTime.toInstant(ZoneOffset.of("+2"));
        assertTrue(instant.toString().matches("1981-04-23T18:00:00Z"));
               
    }
    
    @Test
    public void testFormatZonedDateTime() {
        LocalDate localDate = LocalDate.of(1981, 04, 23);
        LocalTime localTime = LocalTime.of(20, 0);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Rome"));
        String formattedString = DateTimeFormatting.format(zonedDateTime);
        assertTrue(formattedString.matches("23::Apr::1981 20::00::00 CEST"));
    }

}
