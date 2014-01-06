package nl.games.xrebirth.neo4j.utils;

import nl.games.xrebirth.neo4j.importer.ImportException;

import java.lang.reflect.ParameterizedType;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static Class getDeclaredClass(Object o) {
        return getDeclaredClass(o.getClass());
    }

    public static  Class getDeclaredClass(Class clazz) {
        return (Class)((ParameterizedType)clazz.getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public static Object getDefaultValue(Class clazz, String method) {
        try {
            return clazz.getMethod(method).getDefaultValue();
        } catch (NoSuchMethodException e) {
            throw new ImportException(e);
        }
    }

}
