package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.generated.wares.*;
import nl.games.xrebirth.neo4j.cache.NodeCache;
import nl.games.xrebirth.neo4j.importer.writer.AbstractNeo4jWriter;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.ImportException;
import nl.games.xrebirth.neo4j.producers.LabelProducer;
import nl.games.xrebirth.neo4j.producers.RelationshipTypeProducer;
import nl.games.xrebirth.neo4j.utils.Utils;
import org.neo4j.graphdb.*;

import javax.inject.Inject;
import javax.inject.Singleton;
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

@Singleton
public class WaresWriter extends AbstractNeo4jWriter<WaresType> {


    @Inject
    LabelProducer labelProducer;

    @Inject
    RelationshipTypeProducer relationshipTypeProducer;

    @Inject
    NodeCache nodeCache;


    private RelationshipType rawMaterialRelationType = DynamicRelationshipType.withName("rawmaterial");
    private RelationshipType secondaryMaterialRelationType = DynamicRelationshipType.withName("secondarymaterial");
    private RelationshipType produces = DynamicRelationshipType.withName("produces");


    @Override
    public WaresType doWrite(ImportContext importContext, WaresType wares) {
        GraphDatabaseService service = importContext.getDatabaseService();
        DefaultsType defaultWare = wares.getDefaults();
        try (Transaction tx = service.beginTx()) {
            //import all wares
            for (WareType ware : wares.getWare()) {
                updateWithDefault(ware, defaultWare);
                Node node = service.createNode(labelProducer.getLabels(ware));
                addAttributeFields(importContext, ware, node);
                nodeCache.addObject(ware, node);
            }

            //link all poduction types
            for (WareType ware : wares.getWare()) {
                Node node = service.getNodeById(nodeCache.getNodeId(ware));
                for (ProductionType production : ware.getProduction()) {
                    Node productionNode = service.createNode(labelProducer.getLabels(production));
                    addAttributeFields(importContext, production, productionNode, node);
                    productionNode.createRelationshipTo(node, relationshipTypeProducer.produce(production));

                    if (production.getPrimary() != null) {
                        for (ProductionWareType primaryWare : production.getPrimary().getWare()) {
                            Long primWareId = nodeCache.getNodeId(WareType.class, primaryWare.getWare());
                            if (primWareId == null) {
                                System.err.println("id not found for:" + primaryWare.getWare());
                            }
                            Node primaryWareNode = service.getNodeById(primWareId);
                            RelationshipType rt = relationshipTypeProducer.produce(production.getPrimary());
                            Relationship relationship = productionNode.createRelationshipTo(primaryWareNode, rt);
                            relationship.setProperty("amount", primaryWare.getAmount());
                        }
                    }
                    if (production.getSecondary() != null) {
                        for (ProductionWareType secondaryWare : production.getSecondary().getWare()) {
                            Long primWareId = nodeCache.getNodeId(WareType.class, secondaryWare.getWare());
                            if (primWareId == null) {
                                System.err.println("id not found for:" + secondaryWare.getWare());
                            }
                            Node secondairyWareNode = service.getNodeById(primWareId);
                            RelationshipType rt = relationshipTypeProducer.produce(production.getSecondary());
                            Relationship relationship = productionNode.createRelationshipTo(secondairyWareNode, rt);
                            relationship.setProperty("amount", secondaryWare.getAmount());
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

    protected void addAttributeFields(ImportContext context, WareType ware, Node node) throws IllegalAccessException {
        super.addAttributeFields(context, ware, node);
        node.setProperty("name", ware.getId());
        node.setProperty("price-min", ware.getPrice().getMin().longValue());
        node.setProperty("price-avarage", ware.getPrice().getAverage().longValue());
        node.setProperty("price-max", ware.getPrice().getMax().longValue());
    }

    protected void addAttributeFields(ImportContext context, ProductionType production, Node productionNode, Node wareNode) throws IllegalAccessException {
        super.addAttributeFields(context, production, productionNode);
        productionNode.setProperty("id", String.format("%s - %s", wareNode.getProperty("id"), production.getMethod()));
        productionNode.setProperty("product", wareNode.getId());
        productionNode.setProperty("name", wareNode.getId());
        if (production.getEffects() != null) {
            for (EffectType effect : production.getEffects().getEffect()) {
                productionNode.setProperty(effect.getType(), effect.getProduct().floatValue());
            }
        }
    }



    private void updateWithDefault(WareType ware, DefaultsType defaults) {
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
