package nl.games.xrebirth.neo4j.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jodd.bean.BeanUtil;
import nl.games.xrebirth.generated.AbstractElement;
import org.jvnet.jaxb2_commons.lang.EnumValue;
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


    public void addObject(AbstractElement element, Node node) {
        cache.put(generateCacheKey(element), generateCacheValue(element, node));
    }

    public Long getNodeId(AbstractElement element) {
        CacheValue val = cache.getIfPresent(generateCacheKey(element));
        if (val != null) {
            return val.getNodeId();
        } else {
            return null;
        }
    }

    public Long getNodeId(Class<? extends AbstractElement> clazz, String id) {
        try {
            AbstractElement obj = clazz.newInstance();
            BeanUtil.setProperty(obj, "id", id);
            CacheValue val = cache.getIfPresent(generateCacheKey(obj));
            if (val != null) {
                return val.getNodeId();
            } else {
                return null;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Long getNodeId(Class<? extends AbstractElement> clazz, EnumValue anEnum) {
        return getNodeId(clazz, String.valueOf(anEnum.enumValue()));
    }

    private CacheKey generateCacheKey(AbstractElement element) {
        if (element.getIdString() == null) {
            throw new NullPointerException("no id found for:" + element.getClass().getName());
        } else {
            CacheKey cacheKey = new CacheKey(element.getClass(), "id", element.getIdString());
            return cacheKey;
        }
    }

    private CacheValue generateCacheValue(AbstractElement element, Node node) {
        CacheValue cacheValue = new CacheValue();
        cacheValue.setNodeId(node.getId());
        return cacheValue;
    }

}
