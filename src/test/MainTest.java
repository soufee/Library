package test;

import library.Library;
import library.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Shoma on 10.04.2017.
 */
class MainTest {
    private static Library library;

    @Test
    public void buyBookTestCatalog() {
        library.buyBook("Intro to Java", "Schildt", "123123123q", 3, 1952);
        Book book = new Book("Schildt", "Intro to Java", 1952, "123123123q");
        assertEquals(1, library.getCatalog().size());
        assertTrue(library.getCatalog().contains(book));
        Book bookFromCatalog = library.getCatalog().iterator().next();
        assertTrue(book.getTitle().equals(bookFromCatalog.getTitle()));
        assertTrue(book.getAuthor().equals(bookFromCatalog.getAuthor()));
        assertTrue(book.getIsbn().equals(bookFromCatalog.getIsbn()));
        assertTrue(book.getYear() == (bookFromCatalog.getYear()));

    }

    @BeforeAll
    public static void init() {
        library = new Library();

    }

    @AfterEach
    public void clearAll() {
        library = new Library();
    }

    @Test
    public void buyBookTestStore() {

        library.buyBook("Intro to Java", "Schildt", "123123123q", 3, 1952);
        int quant = library.getStore().size();
        assertEquals(3, quant);

        Book book = new Book("Schildt", "Intro to Java", 1952, "123123123q");
        for (BookInstance b:library.getStore()) {
            Book bookFromCatalog = b.getBook();
            assertTrue(book.getTitle().equals(bookFromCatalog.getTitle()));
            assertTrue(book.getAuthor().equals(bookFromCatalog.getAuthor()));
            assertTrue(book.getIsbn().equals(bookFromCatalog.getIsbn()));
            assertTrue(book.getYear() == (bookFromCatalog.getYear()));
        }
    }
    @Test
    public void takeBookTest()
    {
        Book book = new Book("Schildt", "Intro to Java", 1952, "123123123q");

        buyBookTestStore();

        int quant = library.getStore().size();
        assertEquals(3, quant);
        library.takeBook("Michel", "Jackson","",333333,"Intro to Java");
        assertEquals(1, library.getReaders().size());
        Reader reader = new Reader("Michel", "Jackson", "",333333);
        assertTrue(reader.equals(library.getReaders().iterator().next()));

        assertEquals(1,library.getBookings().size());

        UUID uuid = UUID.randomUUID();
       BookInstance bookInstance = new BookInstance(book, uuid);
      assertEquals(bookInstance.getBook(), library.getBookings().iterator().next().getBookInstance().getBook());

        quant = library.getStore().size();
        assertEquals(2, quant);
    }

    public void returnBookTest()
    {

        buyBookTestStore();
        takeBookTest();
//        int quant = library.getStore().size();
//        assertEquals(3, quant);
//        library.takeBook("Michel", "Jackson","",333333,"Intro to Java");
//        assertEquals(1,library.getBookings().size());
     //   Booking booking = library.getBookings().iterator().next();
     library.returnBook("Michel", "Jackson","",333333,"Intro to Java");
       assertEquals(0,library.getBookings().size());



    }





}
