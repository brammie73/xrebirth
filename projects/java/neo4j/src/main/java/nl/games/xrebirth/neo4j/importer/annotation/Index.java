package nl.games.xrebirth.neo4j.importer.annotation;

import nl.games.xrebirth.generated.AbstractElement;
import sun.rmi.transport.ObjectTable;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 9-1-14
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */

@Qualifier
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Index {
    @Nonbinding
    public Class<? extends AbstractElement> value() default AbstractElement.class;
}
