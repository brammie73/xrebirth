package nl.games.xrebirth.neo4j.importer.reader.races;

import nl.games.xrebirth.generated.races.Race;
import nl.games.xrebirth.generated.races.Races;
import nl.games.xrebirth.generated.races.Relation;
import nl.games.xrebirth.neo4j.importer.reader.AbstractNeo4jWriter;
import nl.games.xrebirth.neo4j.importer.reader.ImportContext;
import nl.games.xrebirth.neo4j.importer.reader.ImportException;
import org.neo4j.graphdb.*;

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
public class RacesWriter extends AbstractNeo4jWriter<Races> {

    private Map<Key, Value> cache = new HashMap<Key, Value>();

    public String getLabel() {
        return "Race";
    }

    @Override
    public Races doWrite(ImportContext importContext, Races races) {
        GraphDatabaseService service = importContext.getDatabaseService();
        try (Transaction tx = service.beginTx()) {
            for (Race race : races.getRace()) {
                Label label = DynamicLabel.label(getLabel());
                Node node = service.createNode(label);
                Field[] fields = Race.class.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(XmlAttribute.class)) {
                        XmlAttribute annotation = field.getAnnotation(XmlAttribute.class);
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        node.setProperty(annotation.name(), field.get(race));
                    }
                }
                cache.put(new Key(race), new Value(node.getId()));

            }
            for (Race race : races.getRace()) {
                if (race.getRelations() == null) {
                    continue;
                }
                for (Relation relation : race.getRelations().getRelation()) {
                    Node from  = service.getNodeById(cache.get(new Key(race.getId())).getNodeId());
                    Node to = service.getNodeById(cache.get(new Key(relation.getRace().value())).getNodeId());
                    Relationship rs = from.createRelationshipTo(to, DynamicRelationshipType.withName("relation"));
                    rs.setProperty("value", relation.getRelation());
                }
            }
            tx.success();
        } catch (Exception e) {
            throw new ImportException(e);
        }
        return races;
    }

    private class Key {
        private final String label;
        private final String id;

        private Key(String id) {
            this.label = getLabel();
            this.id = id;
        }

        private Key(Race race) {
            this.label = getLabel();
            this.id = race.getId();
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
