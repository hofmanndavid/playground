package demo.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by hdavid on 6/1/16.
 */
public class ResourceLocator {

    public static <T> T locate(Class<T> clazz) {
        try {
            InitialContext ic = new InitialContext();
            Object resource = ic.lookup("java:module/" + clazz.getSimpleName());
            ic.close();
            return clazz.cast(resource);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
