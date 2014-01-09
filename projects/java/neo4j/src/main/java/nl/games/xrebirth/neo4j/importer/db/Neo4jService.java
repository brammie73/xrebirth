package nl.games.xrebirth.neo4j.importer.db;

import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.neo4j.cache.CacheEntry;
import nl.games.xrebirth.neo4j.cache.CacheKey;
import nl.games.xrebirth.neo4j.cache.NodeCache;
import nl.games.xrebirth.neo4j.importer.TextFormatter;
import nl.games.xrebirth.neo4j.importer.events.DeferedRelationshipEvent;
import nl.games.xrebirth.neo4j.importer.events.NodeEvent;
import org.neo4j.graphdb.*;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: bram
 * Date: 9-1-14
 * Time: 2:15
 */

@Singleton
public class Neo4jService {

    private GraphDatabaseService databaseService;

    private ExecutorService executorServices = Executors.newFixedThreadPool(10);

    @Inject
    NodeCache nodeCache;

    @Inject
    LabelProducer labelProducer;

    @Inject
    RelationshipTypeProducer relationshipTypeProducer;

    @Inject
    TextFormatter textFormatter;


    @Inject
    private Event<NodeEvent> nodeCreate;

    @Inject
    private Event<DeferedRelationshipEvent> relationshipEvent;


    GraphDatabaseService getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(GraphDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void createNode(final AbstractElement element, final PropertyBuilder builder) {
        try (Transaction tx = beginTransaction()) {
            Label[] labels = labelProducer.getLabels(element);
            Node node = getDatabaseService().createNode(labels);
            builder.build(textFormatter, node);
            CacheEntry cacheEntry = nodeCache.addObject(element, node);
            tx.success();
            nodeCreate.fire(new NodeEvent(cacheEntry));
        }
    }

    public void createRelationship(final AbstractElement from, final AbstractElement to, final PropertyBuilder builder) {
        CacheEntry fromEntry = nodeCache.getCacheEntry(from);
        CacheEntry toEntry = nodeCache.getCacheEntry(to);
        createRelationship(fromEntry, toEntry, relationshipTypeProducer.produce(from, to), builder);
    }

    public void createRelationship(final AbstractElement from, final AbstractElement to, final AbstractElement relationType, final PropertyBuilder builder) {
        CacheEntry fromEntry = nodeCache.getCacheEntry(from);
        CacheEntry toEntry = nodeCache.getCacheEntry(to);
        createRelationship(fromEntry, toEntry, relationshipTypeProducer.produce(from, relationType), builder);
    }


    /**
     * useed by defered writer
     * @param fromKey
     * @param toKey
     * @param relationshipType
     * @param builder
     */
    public void createRelationship(final CacheKey fromKey, final CacheKey toKey, final RelationshipType relationshipType, final PropertyBuilder builder) {
        CacheEntry fromEntry = nodeCache.getCacheEntry(fromKey);
        CacheEntry toEntry = nodeCache.getCacheEntry(toKey);
        createRelationship(fromEntry, toEntry, relationshipType, builder);
    }


    private void createRelationship(final CacheEntry fromEntry, final CacheEntry toEntry, final RelationshipType relationshipType, final PropertyBuilder builder) {
        if (fromEntry.getCacheValue() == null || toEntry.getCacheValue() == null) {
            DeferedRelationshipEvent relationship = new DeferedRelationshipEvent(relationshipType, fromEntry, toEntry);
            builder.build(textFormatter, relationship);
            relationshipEvent.fire(relationship);
        } else {
            try (Transaction tx = beginTransaction()) {
                Node fromNode = getDatabaseService().getNodeById(fromEntry.getCacheValue().getNodeId());
                Node toNode = getDatabaseService().getNodeById(toEntry.getCacheValue().getNodeId());
                Relationship relationship = fromNode.createRelationshipTo(toNode, relationshipType);
                builder.build(textFormatter, relationship);
                tx.success();
            }
        }
    }

    private Transaction beginTransaction() {
        return getDatabaseService().beginTx();
    }
}
