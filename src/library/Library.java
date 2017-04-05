package library;

import library.models.*;
import sun.util.calendar.Gregorian;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;


/**
 * Created by Shoma on 05.04.2017.
 */
public class Library {
    private Set<Book> catalog;
    private Set<BookInstance> store;
    private Set<Reader> readers;
    private Set<Booking> bookings;

    public Library() {
        catalog = new HashSet<>(1024);
        store = new HashSet<>(4096);
        readers = new HashSet<>(512);
        bookings = new HashSet<>(2048);
    }

    public Set<Reader> getReaders() {
        return readers;
    }

    public void setReaders(Set<Reader> readers) {
        this.readers = readers;
    }

    public void buyBook(Book book, int quantity) {
        catalog.add(book);
        for (int i = 0; i < quantity; i++) {
            BookInstance bookInstance = new BookInstance(book, UUID.randomUUID());
            store.add(bookInstance);
        }
    }

    public void buyBook(String title, String author, String isbn, int quantity, int year) {
        Book newBook = new Book(author, title, year, isbn);
        catalog.add(newBook);

        for (int i = 0; i < quantity; i++) {
            BookInstance bookInstance = new BookInstance(newBook, UUID.randomUUID());
            store.add(bookInstance);
        }
    }

    public void takeBook(String firstName, String secondName, String lastName, long passportNumber,
                         String title) {
        Object[] reader = readers.stream().filter((r)->r.getPassportNumber() == passportNumber).toArray();

        Reader tempReader = null;
        if (reader.length != 0) {
            tempReader = (Reader) reader[0];
        } else{
            tempReader = new Reader(firstName, secondName, lastName, passportNumber);
            readers.add(tempReader);
        }

        Object[] bookInstance = store.stream().filter((s)->s.getBook().getTitle().equals(title)).toArray();
        if (bookInstance.length == 0) {
            System.out.println("No such book");
            return;
        }

        Booking booking = new Booking((BookInstance) bookInstance[0], tempReader, new Date(), new Date());

        bookings.add(booking);

        store.remove(bookInstance[0]);

    }

    public void returnBook(String firstName, String secondName, String lastName, long passportNumber,
                           String title) {
        Reader reader = new Reader(firstName, secondName, lastName, passportNumber);
        Booking booking = (Booking) bookings.stream().filter((b)->b.getBookInstance().getBook().getTitle().equals(title) &&
                b.getReader().equals(reader)).toArray()[0];
        if (booking == null) {
            System.out.println("No such booking");
            return;
        }

        store.add(booking.getBookInstance());
        bookings.remove(booking);
    }

    public Set<BookInstance> getStore() {
        return store;
    }

    public void setStore(Set<BookInstance> store) {
        this.store = store;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public void setCatalog(Set<Book> catalog) {
        this.catalog = catalog;
    }

    public Set<Book> getCatalog() {
        return catalog;
    }

    public void showAllData() {
        System.out.println("Список имеющихся книг");
        for (Book book:
                catalog
                ) {
            System.out.println(book);
        }
        System.out.println();
        System.out.println("Список книг по экземплярно");
        for (BookInstance bookInstance:
                store
                ) {
            System.out.println(bookInstance);
        }
        System.out.println();
        System.out.println("Список читателей");
        for (Reader reader:
                readers
                ) {
            System.out.println(reader);
        }
        System.out.println();
        System.out.println("Список заказов");
        for (Booking booking:
                bookings
                ) {
            System.out.println(booking);
        }
    }
   }