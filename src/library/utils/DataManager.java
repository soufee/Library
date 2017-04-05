package library.utils;

import library.Library;
import library.models.*;
import library.models.Reader;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shoma on 05.04.2017.
 */
public class DataManager {
    public static void serializeToFile(Set<Book> books) {
        try(FileOutputStream fos = new FileOutputStream("books.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for(Book book : books)
                oos.writeObject(book);
oos.writeObject(null);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Set<Book> deserialize() {
        Set<Book> books = new HashSet<>();
        try(FileInputStream fis = new FileInputStream("books.txt");
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            Book book = null;
            while((book = (Book) ois.readObject()) != null) {
                books.add(book);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return books;

        }

    }
    public static <T extends Externalizable> void externalObject (Object set, String fileName)
    {
        try (FileOutputStream fis = new FileOutputStream(fileName);
             ObjectOutputStream ois = new ObjectOutputStream(fis)){
            if(set instanceof Set) {
                for (T obj : (Set<T>) set)
                    obj.writeExternal(ois);
            } else
                ((T)set).writeExternal(ois);
          // ois.writeUTF("@soufee");
            ois.flush();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static<T extends Externalizable> Set<T> deexternal(String fileName, Class<T> clazz) {
        Set<T> set = new HashSet<T>();
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
          while (ois.available()>9)
          {
              T obj = (T)clazz.newInstance();
              obj.readExternal(ois);
              set.add(obj);
          }
//        System.out.println(ois.readUTF());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return set;
    }
    }
