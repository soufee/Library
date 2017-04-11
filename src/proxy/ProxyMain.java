package proxy;

import library.models.Book;
import library.utils.DataManager;
import library.utils.MyInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Set;

/**
 * Created by Shoma on 11.04.2017.
 */
public class ProxyMain {
    public static void main(String[] args) {
//        ProxyCollection proxy = new ProxyCollection();
//
//        Set<Book> catalog = (Set<Book>) Proxy.newProxyInstance(MySet.class.getClassLoader(),
//        MySet.class.getInterfaces(), new ProxyCollection());
//        System.out.println(catalog.contains(1));
        DataManager manager = new DataManager();
//        Book book = new Book("Александр Пушкин", "Капитанская дочка", 1831, "654-987");
//        manager.serializeOneBook(book);

    //    Book book1 = manager.deserializeOneBook();

        MyInterface proxyManager = (MyInterface) Proxy.newProxyInstance(DataManager.class.getClassLoader(),DataManager.class.getInterfaces(),
                new ProxyDeserialize());
        //manager = (DataManager) proxyManager;

Book book1 = proxyManager.deserializeOneBook();
        //= manager.deserializeOneBook();
      System.out.println(book1.getAuthor() + " "+book1.getTitle() + " "+book1.getYear() + " "+book1.getIsbn());
        /*
        * Object proxy = Proxy.newProxyInstance(SomeClass.class.getClassLoader(),
        * new Class[]{MyClass1.class, MyClass1.class}, new InvocationHandler()*/
    }
}
