package nl.games.xrebirth.neo4j.cache;

/**
 * Author: bram
 * Date: 8-1-14
 * Time: 21:35
 */
public class CacheEntry {

    private final CacheKey cacheKey;
    private final CacheValue cacheValue;

    CacheEntry(CacheKey cacheKey, CacheValue cacheValue) {
        this.cacheKey = cacheKey;
        this.cacheValue = cacheValue;
    }

    public CacheKey getCacheKey() {
        return cacheKey;
    }

    public CacheValue getCacheValue() {
        return cacheValue;
    }

}
