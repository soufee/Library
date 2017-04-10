package test;

import library.Library;
import library.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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


}
