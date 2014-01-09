package nl.games.xrebirth.neo4j.importer.events;

import nl.games.xrebirth.neo4j.cache.CacheEntry;
import org.apache.commons.lang.NotImplementedException;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: bram
 * Date: 8-1-14
 * Time: 2:27
 */
public class DeferedRelationshipEvent implements Relationship {


    private RelationshipType type;
    private CacheEntry from;
    private CacheEntry to;
    private Map<String, Object> properties = new ConcurrentHashMap<>();

    public DeferedRelationshipEvent(RelationshipType type, CacheEntry from, CacheEntry to) {
        this.type = type;
        this.from = from;
        this.to = to;
    }

    public CacheEntry getFrom() {
        return from;
    }

    public CacheEntry getTo() {
        return to;
    }

    @Override
    public GraphDatabaseService getGraphDatabase() {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasProperty(String key) {
        return properties.containsKey(key);
    }

    @Override
    public Object getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public Object getProperty(String key, Object defaultValue) {
        Object value = properties.get(key);
        if (value != null)  {
            return value;
        }
        return defaultValue;
    }

    @Override
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    @Override
    public Object removeProperty(String key) {
        return properties.remove(key);
    }

    @Override
    public Iterable<String> getPropertyKeys() {
        return properties.keySet();
    }

    @Override
    public long getId() {
        throw new NotImplementedException();
    }

    @Override
    public void delete() {
        throw new NotImplementedException();
    }

    @Override
    public Node getStartNode() {
        throw new NotImplementedException();
    }

    @Override
    public Node getEndNode() {
        throw new NotImplementedException();
    }

    @Override
    public Node getOtherNode(Node node) {
        throw new NotImplementedException();
    }

    @Override
    public Node[] getNodes() {
        throw new NotImplementedException();
    }

    @Override
    public RelationshipType getType() {
        return type;
    }

    @Override
    public boolean isType(RelationshipType type) {
        return false;
    }
}
