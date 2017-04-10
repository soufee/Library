package test;

import library.models.Book;
import library.models.BookInstance;
import library.utils.DataManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Shoma on 10.04.2017.
 */
public class DataManagerTest {
    public static DataManager dataManager;

    @Test
    public void testDeSerializationBook() {
        File file = mock(File.class);
        FileReader fileReader = mock(FileReader.class);
        BufferedReader bufferedReader = mock(BufferedReader.class);
        StringBuilder line = new StringBuilder();
try(BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
  line.append (br.readLine().toString());
}
catch (IOException e)
{

}
        try {
            when(bufferedReader.readLine()).thenReturn(line.toString());

            Set<Book> collection = dataManager.deserialize(bufferedReader.readLine());
        //    Book book = collection.iterator().next();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    public static void init() {
        dataManager = new DataManager();
    }
}
