package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.neo4j.cache.NodeCache;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.ImportException;
import nl.games.xrebirth.neo4j.importer.Neo4jWriter;
import nl.games.xrebirth.neo4j.importer.TextFormatter;
import nl.games.xrebirth.neo4j.producers.LabelProducer;
import nl.games.xrebirth.neo4j.producers.RelationshipTypeProducer;
import nl.games.xrebirth.neo4j.utils.Utils;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlAttribute;
import java.lang.annotation.Inherited;
import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 2:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractNeo4jWriter<T> implements Neo4jWriter<T> {

    protected AbstractNeo4jWriter() {

    }

    protected void addAttributeFields(ImportContext context, AbstractElement element, PropertyContainer node) throws ImportException {
        try {
            Field[] fields = element.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(XmlAttribute.class)) {
                    XmlAttribute annotation = field.getAnnotation(XmlAttribute.class);
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    Object value = field.get(element);
                    if (value != null && value.getClass().getPackage().getName().equals("java.lang")) {
                        if (value instanceof String) {
                            String stringValue = ((String) value).trim();
                            if ("tags".equalsIgnoreCase(annotation.name())) {
                                value = context.format(stringValue.split(" "));
                            } else if (stringValue.startsWith("{") && stringValue.endsWith("}") ) {
                                value = context.format(stringValue);
                            }
                        }
                        node.setProperty(annotation.name(), value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw  new ImportException(e);
        }
    }
}
