package library.utils;

import library.Library;
import library.models.Book;
import library.models.BookInstance;
import library.models.Reader;
import library.utils.DataManager;
import library.utils.LoadToXML;


import java.io.*;
import java.util.HashSet;
import java.util.Set;


public class Main {
  public static  Library library = new Library();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Book book = new Book("Tween Mark", "Tom Soyer", 1861,"123-321-564-98N");
//        book.print();

//        Reader john = new Reader("John", "Connor", "Androidovich",250000);
//        Reader sarah = new Reader( "Sara", "Connor", "Humanovich",100000);
//library.getReaders().add(john);
//library.getReaders().add(sarah);
   library.buyBook("Intro to Java", "Schildt", "123123123q", 3, 1952);
   library.buyBook("How to hack Pentagon", "Snowden", "1225653123ewr", 2, 2017);
       library.buyBook("Thinking in Java","Gilbert","123123123qwe",1,2017);
         for(Book book1 : DataManager.deserialize("books.txt"))
             library.buyBook(book1, 1);
library.takeBook("Michel", "Jackson","",333333,"Intro to Java");
//library.returnBook("Michel", "Jackson","",333333,"Intro to Java");
DataManager.serializeToFile(library.getCatalog(), "books.txt");

for(Reader r : DataManager.<Reader>deexternal("readers.txt", Reader.class))
library.getReaders().add(r);
DataManager.externalObject(library.getReaders(),"readers.txt");



        for(BookInstance r : DataManager.<BookInstance>deexternal("bookinstance.txt", BookInstance.class))
            library.getStore().add(r);

            DataManager.externalObject(library.getStore(), "bookinstance.txt");

        library.showAllData();

    LoadToXML loadToXML = new LoadToXML();
    loadToXML.createXMLFile();
    }
    }
