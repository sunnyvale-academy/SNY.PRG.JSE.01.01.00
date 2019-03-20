package it.sunnyvale.jsenewfeature.librarycalculations;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DateParseFormatExercise
 * 
 *  @author denismaggiorotto
 */
public class LibraryCalculations {

    // Returns the unique surnames in uppercase of the book authors that are 50 years old or over.
    public static List<String> getUniqueSurnamesUppercase(List<Book> library) {
        return library.stream()
            .map(book -> book.getAuthor())
            .filter(author -> author.getAge() >= 50)
            .map(Author::getSurname)
            .distinct()
            .map(String::toUpperCase)
            //.peek(System.out::println)
            .collect(Collectors.toList());
    }

    // Returns the sum of ages of all female authors younger than 40
    public static Integer getSumAgesAllFemalesAuthorsYoungerThan25(List<Book> library) {
        return library.stream()
            .map(Book::getAuthor)
            .filter(a -> a.getGender() == Gender.FEMALE)
            .map(Author::getAge)
            .filter(age -> age <= 40)
            //.peek(System.out::println)
            .reduce(0, Integer::sum);
    }

}