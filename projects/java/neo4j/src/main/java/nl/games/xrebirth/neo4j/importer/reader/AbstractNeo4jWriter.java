package nl.games.xrebirth.neo4j.importer.reader;

import nl.games.xrebirth.neo4j.utils.Utils;
import org.neo4j.graphdb.Node;

import javax.xml.bind.annotation.XmlAttribute;
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

    public String getLabel() {
        return Utils.getDeclaredClass(this).getSimpleName().toLowerCase();
    }




    protected void addAttributeFields(TextFormatter textFormatter, Object object, Node node) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(XmlAttribute.class)) {
                XmlAttribute annotation = field.getAnnotation(XmlAttribute.class);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Object value = field.get(object);
                if (value != null && value.getClass().getPackage().getName().equals("java.lang")) {
                    if (value instanceof String) {
                        String stringValue = ((String) value).trim();
                        if ("tags".equalsIgnoreCase(annotation.name())) {
                            value = textFormatter.format(stringValue.split(" "));
                        } else if (stringValue.startsWith("{") && stringValue.endsWith("}") ) {
                            value = textFormatter.format(stringValue);
                        }
                    }
                    node.setProperty(annotation.name(), value);
                }
            }
        }
    }
}
