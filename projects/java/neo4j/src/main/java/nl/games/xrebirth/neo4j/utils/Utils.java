package nl.games.xrebirth.neo4j.utils;

import nl.games.xrebirth.neo4j.Neo4jException;

import java.lang.reflect.ParameterizedType;

/**
 * User: bram
 * Date: 29-12-13
 * Time: 21:47
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
            throw new Neo4jException(e);
        }
    }
}
