package library.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Shoma on 05.04.2017.
 */
public class BookInstance implements Externalizable{

    private Book            book;
    private UUID            number;
    private List<Booking>   bookingHistory;

    public BookInstance(Book book, UUID number) {
        this.book = book;
        this.number = number;
        bookingHistory = new ArrayList<>(32);
    }

    @Override
    public int hashCode() {
        return number.hashCode()*32;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null)return false;
        if (!(obj instanceof Book)) return false;
        if (!(this.number.equals(((BookInstance)obj).number))) return false;
        return true;
    }

    @Override
    public String toString() {
        return book.toString()+"@"+number;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
      out.writeObject(book);
      out.writeObject(number);
      out.writeObject(bookingHistory);
    //  out.writeUTF("@soufee");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
book = (Book)in.readObject();
number = (UUID) in.readObject();
bookingHistory = (List<Booking>) in.readObject();


    }
}
