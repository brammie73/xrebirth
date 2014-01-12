package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.AbstractElement;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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

    private ConcurrentHashMap<String, Value> cache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> multiMatch = new ConcurrentHashMap<>();


    public Value add(String name, String value) {
        if (name.endsWith("*")) {
            name = name.substring(0, name.length() - 1);
            multiMatch.putIfAbsent(name.toLowerCase(), value);
        }
        Value v = new Value(value);
        Value cached = cache.putIfAbsent(name.toLowerCase(), v);
        if (cached != null) {
            assert cached.getFile().equals(value);
        }
        return v;
    }

    public Value get(String name) {
        name = name.toLowerCase();
        Value v = cache.get(name);
        if (v == null) {
            for (String start : multiMatch.keySet()) {
                if (name.startsWith(start)) {
                    String fileName = multiMatch.get(start);
                    v = add(name, fileName);
                }
            }
        }
        return v;
    }

    public Set<String> getNames() {
        return cache.keySet();
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

    }
}
