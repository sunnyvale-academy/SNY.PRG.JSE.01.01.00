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

## Lambda Expression

### Lambda Array Processor
Suppose that a function interface ArrayProcessor is defined as
```
public interface ArrayProcessor {
    double apply( double[] array );
}
```
Write a class named **LambdaArrayProcessor** with two static methods that return different implementations of ArrayProcessor (written as Lambda Expressions), so as to  process an array in the following ways: find the maximum value in the array, find the minimum value in an array.

Methods will have the following signatures:

```
public static ArrayProcessor getMaxer()
public static ArrayProcessor getMiner)

```

Class will be tested with

```
private double[] array = { 17.0, 3.14, 17.0, -3.4, 17.0, 42.0, 29.2, 3.14 };

@Test
public void testLambdaArrayProcessorMax() {
    ArrayProcessor maxer = LambdaArrayProcessor.getMaxer();
    double max = maxer.apply(array);
    assertTrue(42.0 == max);
    
}

@Test
public void testLambdaArrayProcessorMin() {
    ArrayProcessor miner = LambdaArrayProcessor.getMiner();
    double min = miner.apply(array);
    assertTrue(-3.4 == min);
}
```

## Stream API

### Library Calculations

Given this list of books/authors

```
[Book{title=null, author=Author{gender=MALE, name=Andrea, surname=Camilleri, age=75}}, Book{title=null, author=Author{gender=MALE, name=Roberto, surname=Saviano, age=35}}, Book{title=null, author=Author{gender=FEMALE, name=Loredana, surname=Faletti, age=58}}, Book{title=null, author=Author{gender=MALE, name=Giorgio, surname=Faletti, age=60}}, Book{title=null, author=Author{gender=MALE, name=Gabriele, surname=Del Grande, age=24}}, Book{title=null, author=Author{gender=FEMALE, name=Emanuela, surname=Canepa, age=30}}, Book{title=null, author=Author{gender=MALE, name=Federico, surname=Moccia, age=60}}]
```


Write a class named **LibraryCalculations** with a method that returns unique surnames in uppercase of the book
authors that are 50 years old or over, as showed here after:

```
public static List<String> getUniqueSurnamesUppercase(List<Book> library)
```

Then, in the same class, write a method that returns the sum of ages of all female authors younger than 40, as showed here after:

```
public static Integer getSumAgesAllFemalesAuthorsYoungerThan40(List<Book> library) 
```

Both methods have to contain the internal business logic implemented using Java 8 Stream API

Methods will be tested with


```
static List<Book>  library = new ArrayList<>();

@BeforeClass
public static void setup(){
    
    Book book1 = new Book();
    Author author1 = new Author();
    author1.setAge(75);
    author1.setName("Andrea");
    author1.setSurname("Camilleri");
    author1.setGender(Gender.MALE);
    book1.setAuthor(author1);

    Book book2 = new Book();
    Author author2 = new Author();
    author2.setAge(35);
    author2.setName("Roberto");
    author2.setSurname("Saviano");
    author2.setGender(Gender.MALE);
    book2.setAuthor(author2);

    Book book3 = new Book();
    Author author3 = new Author();
    author3.setAge(58);
    author3.setName("Loredana");
    author3.setSurname("Faletti");
    author3.setGender(Gender.FEMALE);
    book3.setAuthor(author3);

    Book book4 = new Book();
    Author author4 = new Author();
    author4.setAge(60);
    author4.setName("Giorgio");
    author4.setSurname("Faletti");
    author4.setGender(Gender.MALE);
    book4.setAuthor(author4);

    Book book5 = new Book();
    Author author5 = new Author();
    author5.setAge(24);
    author5.setName("Gabriele");
    author5.setSurname("Del Grande");
    author5.setGender(Gender.MALE);
    book5.setAuthor(author5);

    Book book6 = new Book();
    Author author6 = new Author();
    author6.setAge(30);
    author6.setName("Emanuela");
    author6.setSurname("Canepa");
    author6.setGender(Gender.FEMALE);
    book6.setAuthor(author6);

    Book book7 = new Book();
    Author author7 = new Author();
    author7.setAge(60);
    author7.setName("Federico");
    author7.setSurname("Moccia");
    author7.setGender(Gender.MALE);
    book7.setAuthor(author7);

    library.add(book1);
    library.add(book2);
    library.add(book3);
    library.add(book4);
    library.add(book5);
    library.add(book6);
    library.add(book7);
    
    System.out.println(library);

}


@Test
public void testSumAgesAllFemalesAuthorsYoungerThan40(){
    int sum = LibraryCalculations.getSumAgesAllFemalesAuthorsYoungerThan40(LibraryCalculationsTest.library);
        assertTrue(sum == 30);
}   

@Test
public void testUniqueSurnamesUppercase(){
    List<String> list = LibraryCalculations.getUniqueSurnamesUppercase(LibraryCalculationsTest.library);
    assertThat(list, IsIterableContainingInOrder.<String>contains("CAMILLERI", "FALETTI", "MOCCIA"));
}
```