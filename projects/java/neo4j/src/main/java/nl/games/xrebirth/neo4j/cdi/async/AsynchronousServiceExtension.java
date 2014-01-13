package nl.games.xrebirth.neo4j.cdi.async;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import java.lang.reflect.Method;
import java.util.concurrent.Future;

/**
 * User: bram
 * Date: 12-1-14
 * Time: 14:55
 */
public class AsynchronousServiceExtension implements Extension {

    <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> event) {
        Class<T> clazz = event.getAnnotatedType().getJavaClass();
        for (Method method : clazz.getMethods()) {
            processMethod(method);
        }
    }

    private void processMethod(Method method) {
        if (method.isAnnotationPresent(Asynchronous.class)) {
            processAsynchronousMethod(method);
        }
    }

    private void processAsynchronousMethod(Method method){
        if (!void.class.isAssignableFrom(method.getReturnType()) &&
                !Future.class.isAssignableFrom(method.getReturnType())) {
            throw new RuntimeException("@Asynchronous annotated method return type must be either void or Future<?>");
        }
    }


}
