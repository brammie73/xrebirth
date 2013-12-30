package nl.games.xrebirth.neo4j.importer.reader.wares;

import nl.games.xrebirth.generated.wares.*;
import nl.games.xrebirth.neo4j.importer.reader.AbstractNeo4jWriter;
import nl.games.xrebirth.neo4j.importer.reader.ImportContext;
import nl.games.xrebirth.neo4j.importer.reader.ImportException;
import nl.games.xrebirth.neo4j.utils.Utils;
import org.neo4j.graphdb.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
public class WaresWriter extends AbstractNeo4jWriter<Wares> {

    private Map<String, Long> nodeIdMap;

    private RelationshipType rawMaterialRelationType = DynamicRelationshipType.withName("rawmaterial");
    private RelationshipType secondaryMaterialRelationType = DynamicRelationshipType.withName("secondarymaterial");
    private RelationshipType produces = DynamicRelationshipType.withName("produces");

    private static Label productionLabel = DynamicLabel.label(Production.class.getSimpleName().toLowerCase());


    @Override
    public Wares doWrite(ImportContext importContext, Wares wares) {
        GraphDatabaseService service = importContext.getDatabaseService();
        Defaults defaultWare = wares.getDefaults();
        try (Transaction tx = service.beginTx()) {
            nodeIdMap = new HashMap<String, Long>(wares.getWare().size());
            for (Ware ware : wares.getWare()) {
                updateWithDefault(ware, defaultWare);
                Node node = service.createNode(DynamicLabel.label(getLabel()));
                this.addAttributeFields(importContext, ware, node);
                node.setProperty("name", ware.getId());
                node.setProperty("price-min", ware.getPrice().getMin().longValue());
                node.setProperty("price-avarage", ware.getPrice().getAverage().longValue());
                node.setProperty("price-max", ware.getPrice().getMax().longValue());
                nodeIdMap.put(ware.getId(), node.getId());
            }
            for (Ware ware : wares.getWare()) {
                Node node = service.getNodeById(nodeIdMap.get(ware.getId()));
                for (Production production : ware.getProduction()) {
                    Node productionNode = service.createNode(productionLabel);
                    addAttributeFields(importContext, production, productionNode);
                    productionNode.createRelationshipTo(node, produces);
                    if (production.getEffects() != null) {
                        for (Effect effect : production.getEffects().getEffect()) {
                            productionNode.setProperty(effect.getType(), effect.getProduct().floatValue());
                        }
                    }
                    if (production.getPrimary() != null) {
                        for (Ware primaryWare : production.getPrimary().getWare()) {
                            if (nodeIdMap.get(primaryWare.getWare()) == null) {
                                System.err.println("id not found for:" + primaryWare.getWare());
                            }
                            Node primaryWareNode = service.getNodeById(nodeIdMap.get(primaryWare.getWare()));
                            Relationship relationship = productionNode.createRelationshipTo(primaryWareNode, rawMaterialRelationType);
                            relationship.setProperty("amount", primaryWare.getAmount().longValue());
                        }
                    }
                    if (production.getSecondary() != null) {
                        for (Ware secondaryWare : production.getSecondary().getWare()) {
                            Node primaryWareNode = service.getNodeById(nodeIdMap.get(secondaryWare.getWare()));
                            Relationship relationship = productionNode.createRelationshipTo(primaryWareNode, secondaryMaterialRelationType);
                            relationship.setProperty("amount", secondaryWare.getAmount().longValue());
                        }
                    }
                }
            }
            tx.success();
        } catch (Exception e) {
            throw new ImportException(e);
        }
        return wares;
    }

    private void updateWithDefault(Ware ware, Defaults defaults) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field[] fields = defaults.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                String name = finadXmlName(field);
                map.put(name, field.get(defaults));
            }
            fields = ware.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                if (field.get(ware) == null) { //lookup in defaults
                    String name = finadXmlName(field);
                    Object value = map.get(name);
                    if (value != null) {
                        field.set(ware, value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new ImportException(e);
        }
    }

    private String finadXmlName(final Field field) {
        String name = field.getName();
        if (field.isAnnotationPresent(XmlAttribute.class)) {
            XmlAttribute annotation = field.getAnnotation(XmlAttribute.class);
            if (!Utils.getDefaultValue(XmlAttribute.class, "name").equals(name)) { //name="##default"
                name = annotation.name();
            }
        } else if (field.isAnnotationPresent(XmlElement.class)) {
            XmlElement annotation = field.getAnnotation(XmlElement.class);
            if (!Utils.getDefaultValue(XmlElement.class, "name").equals(name)) { //name="##default"
                name = annotation.name();
            }
        }
        if (name == null) {
            throw new ImportException("XML name not found");
        }
        return name;
    }
}
