package library.utils;

import library.models.Book;

/**
 * Created by Shoma on 11.04.2017.
 */
public interface MyInterface  {
    Book book = new Book("","",1900,"");
    Book deserializeOneBook ();
  //  Book deserializeOneBook (Object obj);

}
