package nl.games.xrebirth.neo4j.importer.events;

import nl.games.xrebirth.neo4j.cache.CacheEntry;
import nl.games.xrebirth.neo4j.cache.CacheKey;

/**
 * Author: bram
 * Date: 8-1-14
 * Time: 1:47
 */
public class NodeEvent {

    private final CacheEntry cacheEntry;

    public NodeEvent(CacheEntry cacheEntry) {
        this.cacheEntry = cacheEntry;
    }

    public long getId() {
        return cacheEntry.getCacheValue().getNodeId();
    }

    public CacheKey getCacheKey() {
        return this.cacheEntry.getCacheKey();
    }
}
