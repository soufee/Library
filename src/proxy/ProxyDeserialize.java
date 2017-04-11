package proxy;

import library.models.Book;
import library.utils.DataManager;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Shoma on 11.04.2017.
 */
public class ProxyDeserialize implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DataManager manager = (DataManager) proxy;
        if (method.getName().equals(manager.getClass().getMethod("deserializeOneBook"))) {

            return  manager;
        }

return null;
    }
}