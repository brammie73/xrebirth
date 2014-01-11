package nl.games.xrebirth.neo4j.importer.importers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.generated.components.ComponentType;
import nl.games.xrebirth.generated.components.ComponentsType;
import nl.games.xrebirth.generated.macros.MacroType;
import nl.games.xrebirth.generated.macros.MacrosType;

import java.util.Set;

/**
 * Author: bram
 * Date: 7-1-14
 * Time: 20:13
 */
public class XRIndex {

    private XRIndex() {
    }

    static XRIndex build() {
        return new XRIndex();
    }

    private Cache<Key, Value> cache = CacheBuilder.newBuilder().build();

    public void add(Class clazz, String name, String value) {
        cache.put(new Key(clazz, name), new Value(value));
    }

    public void setElement(AbstractElement element) {
        if (element instanceof ComponentType) {
            Value v = cache.getIfPresent(new Key(ComponentsType.class, ((ComponentType) element).getName()));
            if (v != null) {
                v.setElement(element);
            } else {
                Key key = new Key(ComponentsType.class, ((ComponentType) element).getName());
                v = new Value(element.getXmlFile());
                v.setElement(element);
                cache.put(key, v);
            }
        }
        if (element instanceof MacroType) {
            Value v = cache.getIfPresent(new Key(MacrosType.class, ((MacroType) element).getName()));
            if (v != null) {
                v.setElement(element);
            }
        }
    }


    public String get(Class clazz, String name) {
        Value value = cache.getIfPresent(new Key(clazz, name));
        if (value == null) {
            return null;
        } else {
            return value.getFile();
        }
    }

    public AbstractElement getElement(Class clazz, String name) {
        Value value = cache.getIfPresent(new Key(clazz, name));
        if (value == null) {
            return null;
        } else {
            return value.getElement();
        }
    }


    public Set<Key> keySet() {
        return cache.asMap().keySet();
    }


    public class Value {
        private AbstractElement element;
        private String file;

        public Value(String file) {
            this.file = file;
        }

        public AbstractElement getElement() {
            return element;
        }

        public void setElement(AbstractElement element) {
            this.element = element;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }
    }


    public class Key {

        private final Class clazz;

        private final String name;

        private Key(Class clazz, String name) {
            this.clazz = clazz;
            this.name = name;
        }

        public Class getClazz() {
            return clazz;
        }

        public String getName() {
            return name;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (clazz != null ? !clazz.equals(key.clazz) : key.clazz != null) return false;
            if (name != null ? !name.equals(key.name) : key.name != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = clazz != null ? clazz.hashCode() : 0;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

}
