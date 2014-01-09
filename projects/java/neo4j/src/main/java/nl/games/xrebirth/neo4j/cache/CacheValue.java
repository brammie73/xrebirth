package nl.games.xrebirth.neo4j.cache;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 3:38
 */
public class CacheValue {

    private long nodeId;

    CacheValue() {
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheValue that = (CacheValue) o;

        if (nodeId != that.nodeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (nodeId ^ (nodeId >>> 32));
    }
}
