package nl.games.xrebirth.neo4j.cache;

import java.io.Serializable;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 3:35
 */
class CacheKey implements Serializable {

    private Class clazz;
    private String name;
    private String value;


    CacheKey(Class clazz, String name, String value) {
        this.clazz = clazz;
        this.name = name;
        this.value = value;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheKey cacheKey = (CacheKey) o;

        if (clazz != null ? !clazz.equals(cacheKey.clazz) : cacheKey.clazz != null) return false;
        if (name != null ? !name.equals(cacheKey.name) : cacheKey.name != null) return false;
        if (value != null ? !value.equals(cacheKey.value) : cacheKey.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clazz != null ? clazz.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
