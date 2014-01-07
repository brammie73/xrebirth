package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.components.ComponentType;
import nl.games.xrebirth.generated.components.Components;
import nl.games.xrebirth.generated.components.ConnectionType;
import nl.games.xrebirth.neo4j.cache.NodeCache;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.producers.LabelProducer;
import nl.games.xrebirth.neo4j.producers.RelationshipTypeProducer;
import org.neo4j.graphdb.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 18:56
 */
@Singleton
public class ComponentWriter extends AbstractNeo4jWriter<Components> {

    @Inject
    LabelProducer labelProducer;

    @Inject
    RelationshipTypeProducer relationshipTypeProducer;

    @Inject
    NodeCache nodeCache;

    @Override
    public Components doWrite(ImportContext importContext, Components components) {

        GraphDatabaseService service = importContext.getDatabaseService();
        try (Transaction tx = service.beginTx()) {
            for (ComponentType componentType : components.getComponent()) {
                Node node = service.createNode(labelProducer.getLabels(componentType));
                addAttributeFields(importContext, componentType, node);
                node.setProperty("id", componentType.getName());
                nodeCache.addObject(componentType, node);
                for (ConnectionType connectionType : componentType.getConnections().getConnection()) {
                    String temp = connectionType.getTags();
                    if (temp == null) {
                        temp = "*";
                    }
                    String[] tags = temp.split(" ");
                    Node connection = importContext.getDatabaseService().createNode(labelProducer.getLabels(connectionType));
                    connection.setProperty("tags", tags);
                    connection.setProperty("id", temp);
                    Relationship relationship = node.createRelationshipTo(connection, relationshipTypeProducer.produce(connectionType));
                    relationship.setProperty("name", connectionType.getName());

                }
            }
            tx.success();
        }
        return null;
    }
}
