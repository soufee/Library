package library.utils;

/**
 * Created by Shoma on 11.04.2017.
 */

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.lang.reflect.*;
import library.models.Book;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class LoadToXML {
    /*
    <?xml version="1.0" encoding="UTF-8" standalone="no"?>
    * <Books>
    *     <Book>
    *         <Fields></Fields>
    *         <Methods></Methods>
    *     </Book>
    *  </Books>
    * */
//Book book = new Book("AUTOR","TITLE",1950,"ISBN");
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    Document doc;
    TransformerFactory transformerFactory;
    Transformer transformer;
    DOMSource source;
    StreamResult result;
    StreamResult consoleResult;

//    public LoadToXML(Book book) {
//        this.book = book;
//
//    }

    public void createXMLFile()
{
    try {
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
       doc = dBuilder.newDocument();

        loadXML();

     transformerFactory = TransformerFactory.newInstance();
      transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

   source = new DOMSource(doc);


 result = new StreamResult(new File("books.xml"));
        transformer.transform(source, result);
        // Output to console for testing
  //    consoleResult = new StreamResult(System.out);
//        transformer.transform(source, consoleResult);
   //
    } catch (Exception e)
    {
        System.out.println("Возникла какая то ошибка");
        e.printStackTrace();
    }
}

    public void loadXML()
{

    try {
        Element rootElement = doc.createElement("BOOKS");
        doc.appendChild(rootElement);

            for (Book book : Main.library.getCatalog()) {

            Element bookInst = doc.createElement("book");
            rootElement.appendChild(bookInst);

                Element fieldsElement = doc.createElement("fields");
                bookInst.appendChild(fieldsElement);

            Element bookTitle = doc.createElement("Title");
            Attr attrATitle = doc.createAttribute("Title");
            attrATitle.setValue(book.getTitle());
            bookTitle.setAttributeNode(attrATitle);
                fieldsElement.appendChild(bookTitle);



            Element bookAuthor = doc.createElement("Author");
            Attr attrAuthor = doc.createAttribute("Author");
            attrAuthor.setValue(book.getAuthor());
            bookAuthor.setAttributeNode(attrAuthor);
                fieldsElement.appendChild(bookAuthor);

            Element bookYear = doc.createElement("Year");
            Attr attrYear = doc.createAttribute("Year");
            attrYear.setValue(String.valueOf(book.getYear()));
            bookYear.setAttributeNode(attrYear);
                fieldsElement.appendChild(bookYear);


            Element bookISBN = doc.createElement("ISBN");
            Attr attrIsbn = doc.createAttribute("ISBN");
            attrIsbn.setValue(book.getIsbn());
            bookISBN.setAttributeNode(attrIsbn);
                fieldsElement.appendChild(bookISBN);



            // write the content into xml file
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}

