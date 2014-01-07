package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.factions.Faction;
import nl.games.xrebirth.generated.factions.Factions;
import nl.games.xrebirth.generated.factions.Relation;
import nl.games.xrebirth.generated.races.Race;
import nl.games.xrebirth.neo4j.cache.NodeCache;
import nl.games.xrebirth.neo4j.importer.writer.AbstractNeo4jWriter;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.ImportException;
import nl.games.xrebirth.neo4j.producers.LabelProducer;
import nl.games.xrebirth.neo4j.producers.RelationshipTypeProducer;
import org.neo4j.graphdb.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:58
 *
 */

@Singleton
public class FactionsWriter extends AbstractNeo4jWriter<Factions> {

    @Inject
    LabelProducer labelProducer;

    @Inject
    NodeCache nodeCache;

    @Inject
    RelationshipTypeProducer relationshipTypeProducer;

    @Override
    public Factions doWrite(ImportContext importContext, Factions factions) {
        GraphDatabaseService service = importContext.getDatabaseService();
        try (Transaction tx = service.beginTx()) {
            for (Faction faction : factions.getFaction()) {
                Label[] labels = labelProducer.getLabels(faction);
                Node node = service.createNode(labels);
                addAttributeFields(importContext, faction, node);
                nodeCache.addObject(faction, node);
                if (faction.getPrimaryrace() != null) {
                    long to = nodeCache.getNodeId(Race.class, faction.getPrimaryrace());
                    Node fromNode = service.getNodeById(to);
                    fromNode.createRelationshipTo(node, relationshipTypeProducer.produce(Race.class));
                }
            }

            /**
             *
             protected Account account;
             protected Icon icon;
             protected Licences licences;
             protected Relations relations;

             */
            for (Faction faction: factions.getFaction()) {
                if (faction.getRelations() == null) {
                    continue;
                }
                for (Relation relation : faction.getRelations().getRelation()) {
                    long fromNodeId = nodeCache.getNodeId(faction);
                    Node from  = service.getNodeById(fromNodeId);
                    Faction toFaction = new Faction();
                    toFaction.setId(relation.getFaction());
                    long toNodeId = nodeCache.getNodeId(toFaction);
                    Node to = service.getNodeById(toNodeId);
                    Relationship rs = from.createRelationshipTo(to, relationshipTypeProducer.produce(relation));
                    rs.setProperty("value", relation.getRelation());
                }
            }
            tx.success();
        } catch (Exception e) {
            throw new ImportException(e);
        }
        return factions;
    }
}
