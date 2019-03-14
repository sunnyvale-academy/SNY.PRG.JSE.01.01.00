# Java SE 8 labs
## DateTime API
### First and last day of month

Write a Java program to get the name of the first and last day of a month.

Create a class named **FirstLastDayOfMonth** with the following method:

```
public static String[] firstLastDayOfMonth(YearMonth yearMonth);
```
where

```
String firstDaOfMonth = arr[0];
String lastDaOfMonth = arr[1];
```


### DateTime formatting
Write a Java program to print the date 23 April 1981, h 20, m 00 in the specified formats:

- As a LocalDate: `23::Apr::1981`
- As a LocalDateTime: `23::Apr::1981 20::00::00`
- As an Instant: `1981-04-23T18:00:00Z`
- As a ZonedDateTime: `1981-04-23T18:00:00 CEST`

Create a class named **DateTimeFormatting** with the following methods:

```
public static String format(LocalDate localDate);
public static String format(LocalDateTime localDateTime);
public static String format(Instant instant);
public static String format(ZonedDateTime zonedDateTime)
```

The class will be tested using the following unit tests

```
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
```