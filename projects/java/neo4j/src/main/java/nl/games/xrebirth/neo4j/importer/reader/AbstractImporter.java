package nl.games.xrebirth.neo4j.importer.reader;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:59
 * To change this template use File | Settings | File Templates.
 */
public class AbstractImporter<T> implements Importer<T> {


    private Class declaredClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    private XmlReader<T> reader;
    private Neo4jWriter<T> writer;
    private Cache<CacheKey, CacheValue> cache = CacheBuilder.newBuilder().build();

    private T t;

    public AbstractImporter(XmlReader<T> reader, Neo4jWriter<T> writer) {
        this.reader = reader;

        this.writer = writer;

    }

    @Override
    public boolean doImport(ImportContext importContext) {
        T t = reader.doRead(importContext);
        writer.doWrite(importContext, t);
        this.t = t;
        return false;
    }

    public T getValues() {
        if (t == null) {
            throw new ImportException("objects not imported yet");
        }
        return t;
    }

    public void put(String id, T value, long nodeId) {
        CacheKey cacheKey = new CacheKey(id);
        CacheValue<T> cacheValue = new CacheValue<T>();
        cacheValue.setValue(value);
        this.cache.put(cacheKey, cacheValue);
    }

    public CacheValue<T> get(String id) {
        CacheValue<T> val = this.cache.getIfPresent(new CacheKey(id));
        return val;
    }


    class CacheKey implements Serializable {
        private final String id;
        private final Class type;

        CacheKey(String id) {
            this.id = id;
            this.type = declaredClass;
        }

        public String getId() {
            return id;
        }

        public Class getType() {
            return type;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CacheKey cacheKey = (CacheKey) o;

            if (id != null ? !id.equals(cacheKey.id) : cacheKey.id != null) return false;
            if (type != null ? !type.equals(cacheKey.type) : cacheKey.type != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (type != null ? type.hashCode() : 0);
            return result;
        }


    }

    class CacheValue<T> {

        private T value;
        private long nodeId;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public long getNodeId() {
            return nodeId;
        }

        public void setNodeId(long nodeId) {
            this.nodeId = nodeId;
        }
    }
}
