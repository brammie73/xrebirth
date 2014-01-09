package nl.games.xrebirth.neo4j.importer.importers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Author: bram
 * Date: 7-1-14
 * Time: 20:13
 */
public class XRIndex {

    private Cache<Key, String> cache = CacheBuilder.newBuilder().build();

    public void add(String clazz, String key, String value) {
        cache.put(new Key(key, clazz), value);
    }

    public void get(String clazz, String key) {
        cache.getIfPresent(new Key(key, clazz));
    }




    private class Key {
        private final String key;
        private final String clazz;

        private Key(String key, String type) {
            this.key = key;
            this.clazz = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key1 = (Key) o;

            if (key != null ? !key.equals(key1.key) : key1.key != null) return false;
            if (clazz != key1.clazz) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
            return result;
        }
    }

}
