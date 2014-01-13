package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.generated.wares.DefaultsType;
import nl.games.xrebirth.generated.wares.EffectType;
import nl.games.xrebirth.generated.wares.ProductionType;
import nl.games.xrebirth.generated.wares.ProductionWareType;
import nl.games.xrebirth.generated.wares.WareType;
import nl.games.xrebirth.generated.wares.WaresType;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.ImportException;
import nl.games.xrebirth.neo4j.importer.annotation.Reference;
import nl.games.xrebirth.neo4j.importer.db.PropertyBuilder;
import nl.games.xrebirth.neo4j.utils.Utils;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
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
    ImportContext importContext;

    @Inject
    @Reference
    Event<AbstractElement> refEvent;


    public void doWrite(@Observes WaresType wares) {
        DefaultsType defaultWare = wares.getDefaults();
        for (WareType ware : wares.getWare()) {
            updateWithDefault(ware, defaultWare);
            PropertyBuilder wareProperties = PropertyBuilder.create(defaultWare)
                    .update(ware)
                    .add("name", ware.getId())
                    .add("price-min", ware.getPrice().getMin().longValue())
                    .add("price-avarage", ware.getPrice().getAverage().longValue())
                    .add("price-max", ware.getPrice().getMax().longValue());
            importContext.getService().createNode(ware, wareProperties);
            if (ware.getComponent() != null) {
                AbstractElement ref = TypeLookup.find(ware.getComponent());
                refEvent.fire(ref);
                importContext.getService().createRelationship(ware, ref, PropertyBuilder.create().add("amount",  ware.getComponent().getAmount()));
            }
            //todo: component ref's
            for (ProductionType productionType : ware.getProduction()) {
                if (productionType != null) {
                    productionType.setParentElement(ware);
                    productionType.setId(String.format("%s - %s", ware.getId(), productionType.getName()));
                    doWrite(productionType);
                }
            }
        }
    }


    public void doWrite(@Observes ProductionType productionType) {
        WareType parent = (WareType) productionType.getParentElement();
        PropertyBuilder productionProperties = PropertyBuilder.create(productionType)
                .add("product", parent.getId())
                .add("name", productionType.getMethod());

        if (productionType.getEffects() != null) {
            for (EffectType effect : productionType.getEffects()) {
                productionProperties.add(effect.getType(), effect.getProduct().floatValue());
            }
        }
        importContext.getService().createNode(productionType, productionProperties);
        importContext.getService().createRelationship(
                productionType,
                parent,
                PropertyBuilder.create()
        );

/*
        if (productionType.getPrimary() != null) {
            productionType.getPrimary().setParentElement(productionType);
            doWrite(productionType.getPrimary());
        }
        if (productionType.getSecondary() != null) {
            productionType.getSecondary().setParentElement(productionType);
            doWrite(productionType.getSecondary());
        }
*/
    }

    public void doWrite(@Observes final ProductionType.Primary primary) {
        for (final ProductionWareType ware : primary.getWare()) {
            WareType wareElement = createElement(WareType.class);
            wareElement.setId(ware.getWare());
            importContext.getService().createRelationship(
                    wareElement,
                    primary.getParentElement(),
                    primary,
                    PropertyBuilder.create().add("amount", ware.getAmount())
            );
        }
    }

    public void doWrite(@Observes final ProductionType.Secondary secondary) {
        for (final ProductionWareType ware : secondary.getWare()) {
            WareType wareElement = createElement(WareType.class);
            wareElement.setId(ware.getWare());
            importContext.getService().createRelationship(
                    wareElement,
                    secondary.getParentElement(),
                    secondary,
                    PropertyBuilder.create().add("amount", ware.getAmount())
            );
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
