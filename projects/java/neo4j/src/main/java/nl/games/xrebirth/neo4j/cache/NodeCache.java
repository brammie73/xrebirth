package nl.games.xrebirth.neo4j.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jodd.bean.BeanUtil;
import jodd.typeconverter.TypeConverterManager;
import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.neo4j.Neo4jException;
import org.neo4j.graphdb.Node;

import javax.inject.Singleton;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 8:22
 */

@Singleton
public class NodeCache {


    private Cache<CacheKey, CacheValue> cache = CacheBuilder.newBuilder().build();


    public CacheEntry addObject(AbstractElement element, Node node) {
        if (node == null || node.getId() < 0) {
            throw new IllegalArgumentException("node is null");
        }
        CacheKey cacheKey = generateCacheKey(element);
        CacheValue cacheValue = generateCacheValue(element, node);
        cache.put(cacheKey, cacheValue);
        return new CacheEntry(cacheKey, cacheValue);
    }

    public CacheValue get(CacheKey cacheKey) {
        return cache.getIfPresent(cacheKey);
    }


    public CacheEntry getCacheEntry(CacheKey cacheKey) {
        return new CacheEntry(cacheKey, get(cacheKey));
    }

    public CacheEntry getCacheEntry(AbstractElement element) {
        CacheKey cacheKey = generateCacheKey(element);
        return getCacheEntry(cacheKey);
    }

    public CacheEntry getCacheEntry(Class<? extends AbstractElement> clazz, String id) {
        try {
            AbstractElement obj = clazz.newInstance();
            BeanUtil.setProperty(obj, "id", id);
            return getCacheEntry(obj);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private CacheKey generateCacheKey(AbstractElement element) {
        CacheKey cacheKey = new CacheKey(element.getClass(), "id", getIdString(element));
        return cacheKey;
    }

    private CacheValue generateCacheValue(AbstractElement element, Node node) {
        CacheValue cacheValue = new CacheValue();
        cacheValue.setNodeId(node.getId());
        return cacheValue;
    }

    private static String getIdString(AbstractElement element) {
        Object object = element.getId();
        if (object != null) {
            return TypeConverterManager.convertType(object, String.class);
        } else if (BeanUtil.hasDeclaredProperty(element, "name")) {
            return TypeConverterManager.convertType(BeanUtil.getProperty(element, "name"), String.class);
        }
        throw new Neo4jException(String.format("id not generated for:%s, %s", element.getClass().getSimpleName(), element.getXmlFile()));
    }

}
