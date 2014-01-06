package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.races.Race;
import nl.games.xrebirth.generated.races.Races;
import nl.games.xrebirth.generated.races.Relation;
import nl.games.xrebirth.neo4j.cache.NodeCache;
import nl.games.xrebirth.neo4j.importer.writer.AbstractNeo4jWriter;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.ImportException;
import nl.games.xrebirth.neo4j.producers.LabelProducer;
import nl.games.xrebirth.neo4j.producers.RelationshipTypeProducer;
import org.neo4j.graphdb.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.bind.annotation.XmlAttribute;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:58
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class RacesWriter extends AbstractNeo4jWriter<Races> {

    @Inject
    LabelProducer labelProducer;

    @Inject
    RelationshipTypeProducer relationshipTypeProducer;

    @Inject
    NodeCache nodeCache;

    @Override
    public Races doWrite(ImportContext importContext, Races races) {
        GraphDatabaseService service = importContext.getDatabaseService();
        try (Transaction tx = service.beginTx()) {
            for (Race race : races.getRace()) {
                Label[] label = labelProducer.getLabels(race);
                Node node = service.createNode(label);
                addAttributeFields(importContext, race, node);
                nodeCache.addObject(race, node);
            }
            for (Race race : races.getRace()) {
                if (race.getRelations() == null) {
                    continue;
                }
                for (Relation relation : race.getRelations().getRelation()) {

                    Node from  = service.getNodeById(nodeCache.getNodeId(race));
                    Node to = service.getNodeById(nodeCache.getNodeId(Race.class, relation.getRace()));
                    Relationship rs = from.createRelationshipTo(to, relationshipTypeProducer.produce(relation));
                    rs.setProperty("value", relation.getRelation());
                }
            }
            tx.success();
        } catch (Exception e) {
            throw new ImportException(e);
        }
        return races;
    }
}
