package nl.games.xrebirth.neo4j.importer.reader.races;

import nl.games.xrebirth.generated.factions.Faction;
import nl.games.xrebirth.generated.factions.Factions;
import nl.games.xrebirth.generated.factions.Relation;
import nl.games.xrebirth.neo4j.importer.reader.AbstractNeo4jWriter;
import nl.games.xrebirth.neo4j.importer.reader.ImportContext;
import nl.games.xrebirth.neo4j.importer.reader.ImportException;
import org.neo4j.graphdb.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:58
 * To change this template use File | Settings | File Templates.
 */
public class FactionsWriter extends AbstractNeo4jWriter<Factions> {

    private Map<Key, Value> cache = new HashMap<Key, Value>();

    public String getLabel() {
        return "faction";
    }

    @Override
    public Factions doWrite(ImportContext importContext, Factions factions) {
        GraphDatabaseService service = importContext.getDatabaseService();

        try (Transaction tx = service.beginTx()) {
            for (Faction faction : factions.getFaction()) {
                Label label = DynamicLabel.label(getLabel());
                Node node = service.createNode(label);
                addAttributeFields(importContext, faction, node);
                cache.put(new Key(faction.getId()), new Value(node.getId()));
            }

            /**
             *     protected Account account;
             protected Icon icon;
             protected Licences licences;
             protected Relations relations;

             */
            for (Faction faction: factions.getFaction()) {
                if (faction.getRelations() == null) {
                    continue;
                }
                for (Relation relation : faction.getRelations().getRelation()) {
                    Node from  = service.getNodeById(cache.get(new Key(faction.getId())).getNodeId());
                    Node to = service.getNodeById(cache.get(new Key(relation.getFaction())).getNodeId());
                    Relationship rs = from.createRelationshipTo(to, DynamicRelationshipType.withName("relation"));
                    rs.setProperty("value", relation.getRelation());
                }
            }
            tx.success();
        } catch (Exception e) {
            throw new ImportException(e);
        }
        return factions;
    }

    private class Key {
        private final String label;
        private final String id;

        private Key(String id) {
            this.label = getLabel();
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public String getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (id != null ? !id.equals(key.id) : key.id != null) return false;
            if (label != null ? !label.equals(key.label) : key.label != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = label != null ? label.hashCode() : 0;
            result = 31 * result + (id != null ? id.hashCode() : 0);
            return result;
        }
    }

    private class Value {
        private long nodeId;

        private Value(long nodeId) {
            this.nodeId = nodeId;
        }

        public long getNodeId() {
            return nodeId;
        }

        public void setNodeId(long nodeId) {
            this.nodeId = nodeId;
        }

    }
}
