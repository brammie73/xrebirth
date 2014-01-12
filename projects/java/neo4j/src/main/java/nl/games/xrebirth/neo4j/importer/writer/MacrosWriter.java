package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.components.ComponentType;
import nl.games.xrebirth.generated.macros.ConnectionType;
import nl.games.xrebirth.generated.macros.MacroType;
import nl.games.xrebirth.generated.macros.MacrosType;
import nl.games.xrebirth.generated.macros.PropertiesType;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.annotation.Load;
import nl.games.xrebirth.neo4j.importer.annotation.LoadComplete;
import nl.games.xrebirth.neo4j.importer.annotation.Reference;
import nl.games.xrebirth.neo4j.importer.db.PropertyBuilder;
import nl.games.xrebirth.neo4j.importer.events.MacroEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 23:27
 */
@Singleton
public class MacrosWriter extends AbstractNeo4jWriter<MacrosType> {

    private static Logger log = LogManager.getLogger();


    @Inject
    @Reference
    Event<MacroType> refMacroEvent;


    @Inject
    ImportContext importContext;

    @Inject
    @Load
    Event<MacroEvent> macroEventBus;



    public void macroTypeListener(@Observes @LoadComplete MacroEvent macroEvent) {
        log.info("loadcomplete:{},  {}", macroEvent.getMacroType().getName(), macroEvent.getComponent().getName());
        MacroType macro = macroEvent.getMacroType();
        PropertyBuilder propertyBuilder = PropertyBuilder.create(macro)
                .add("id", macro.getName());
        updateProperties(macro, propertyBuilder);
        ComponentType componentType = macroEvent.getComponent();
        if (macro.getClazz() == null)
            macro.setClazz(componentType.getClazz());

        importContext.getService().createNode(
                macro,
                propertyBuilder
        );
        if (macro.getConnections() != null)  {
            List<ConnectionType> ct =  macro.getConnections().getConnection();
            for (ConnectionType connectionType : ct) {
                MacroType child = connectionType.getMacro();
                if (child == null) {
                    log.info("fixme: {}", connectionType.getRef());
                } else {
                    if (child.getRef() != null) {
                        log.debug("ref {}", child.getRef());
                        PropertyBuilder connectionBuilder = PropertyBuilder.create(connectionType)
                                .add("id", connectionType.getRef());
                        child.setName(child.getRef());
                        updateProperties(connectionType, connectionBuilder);
                        importContext.getService().createRelationship(macro, child, connectionType, connectionBuilder);
                        this.refMacroEvent.fire(child);
                    } else if (child.getPath() != null) {
                        log.info("todo: path {}", child.getPath());
                    } else {
                        log.info("name {}", child.getName());
                        PropertyBuilder connectionBuilder = PropertyBuilder.create(connectionType)
                                .add("id", connectionType.getRef());
                        updateProperties(connectionType, connectionBuilder);
                        importContext.getService().createRelationship(macro, child, connectionType, connectionBuilder);
                        MacroEvent childEvent = new MacroEvent();
                        childEvent.setMacroType(child);
                        macroEventBus.fire(childEvent);
                    }
                }
            }
        }
    }


    private void updateProperties(MacroType macro, PropertyBuilder propertyBuilder) {
        PropertiesType propertiesType = macro.getProperties();
        if (propertiesType == null) {
            return;
        }
        propertyBuilder.update(propertiesType, true, true);

    }

    private void updateProperties(ConnectionType connectionType, PropertyBuilder propertyBuilder) {
        propertyBuilder.update(connectionType.getOffset(), true, true);
        propertyBuilder.add("position", connectionType.getPosition());
        propertyBuilder.update(connectionType.getBuild(), true, true);

    }
}
