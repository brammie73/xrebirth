package nl.games.xrebirth.neo4j.importer.db;

import jodd.JoddBean;
import jodd.bean.BeanUtil;
import jodd.introspector.ClassDescriptor;
import jodd.introspector.PropertyDescriptor;
import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.neo4j.importer.TextFormatter;
import nl.games.xrebirth.neo4j.importer.events.DeferedRelationshipEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jvnet.jaxb2_commons.lang.EnumValue;
import org.neo4j.graphdb.PropertyContainer;

import javax.xml.bind.annotation.XmlAttribute;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: bram
 * Date: 9-1-14
 * Time: 2:37
 */
public class PropertyBuilder {

    static Logger log = LogManager.getLogger();


    private Map<String, Object> map = new HashMap<>();

    private PropertyBuilder() {
    }

    public static PropertyBuilder create() {
        return new PropertyBuilder();
    }

    public static PropertyBuilder create(AbstractElement element) {
        return new PropertyBuilder().update(element);
    }

    public static PropertyBuilder create(DeferedRelationshipEvent container) {
        PropertyBuilder builder = new PropertyBuilder();
        for (String name : container.getPropertyKeys()) {
            builder.map.put(name, container.getProperty(name));
        }
        return builder;
    }

    public PropertyBuilder update(AbstractElement element) {
        return update(element, false);
    }

    public PropertyBuilder update(AbstractElement element, boolean deep) {
        if (element == null) {
            return this;
        }
        ClassDescriptor classsDescriptor = JoddBean.introspector.lookup(element.getClass());
        PropertyDescriptor[] array = classsDescriptor.getAllPropertyDescriptors();
        for (PropertyDescriptor descriptor : array) {
            String name = descriptor.getName();
            Object value = BeanUtil.getProperty(element, name);
            if (value == null) {
                continue;
            }
            if (value instanceof AbstractElement && deep) {
                update((AbstractElement) value, deep);
            } else if (descriptor.getFieldDescriptor() != null && descriptor.getFieldDescriptor().getField() != null) {
                Field field = descriptor.getFieldDescriptor().getField();
                if (field.isAnnotationPresent(XmlAttribute.class)) {
                    XmlAttribute annotation = field.getAnnotation(XmlAttribute.class);
                    this.map.put(annotation.name(), value);
                }
            } else if (value instanceof String) {
                this.map.put(name, value);
            } else if (value instanceof String[]) {
                this.map.put(name, value);
            } else if (value.getClass().isPrimitive()) {
                this.map.put(name, value);
            }
        }
        return this;
    }


    public PropertyBuilder add(final String name, final Object value) {
        this.map.put(name, value);
        return this;
    }

    void build(TextFormatter textFormatter, final PropertyContainer node) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value == null) {
                continue;
            } else if (value instanceof EnumValue) {
                value = ((EnumValue) value).enumValue();
            } else if (value instanceof BigInteger) {
                value = ((BigInteger) value).longValue();
            }
            node.setProperty(entry.getKey(), textFormatter.format(value));
        }
    }


}
