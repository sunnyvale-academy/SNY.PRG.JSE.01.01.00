package it.sunnyvale.jsenewfeature.librarycalculations.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.hamcrest.collection.IsIterableContainingInOrder;

import it.sunnyvale.jsenewfeature.librarycalculations.Author;
import it.sunnyvale.jsenewfeature.librarycalculations.Book;
import it.sunnyvale.jsenewfeature.librarycalculations.Gender;
import it.sunnyvale.jsenewfeature.librarycalculations.LibraryCalculations;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LibraryCalculationsTest {
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
    public void testUniqueSurnamesUppercase(){
        List<String> list = LibraryCalculations.getUniqueSurnamesUppercase(LibraryCalculationsTest.library);
        assertThat(list, IsIterableContainingInOrder.<String>contains("CAMILLERI", "FALETTI", "MOCCIA"));
    }

    @Test
    public void testSumAgesAllFemalesAuthorsYoungerThan25(){
        int sum = LibraryCalculations.getSumAgesAllFemalesAuthorsYoungerThan25(LibraryCalculationsTest.library);
        assertTrue(sum == 30);
    }

}
