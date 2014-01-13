package nl.games.xrebirth.neo4j.cdi.async;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: bram
 * Date: 12-1-14
 * Time: 14:50
 */

@InterceptorBinding
@Inherited
@Target( { ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Asynchronous {
}
