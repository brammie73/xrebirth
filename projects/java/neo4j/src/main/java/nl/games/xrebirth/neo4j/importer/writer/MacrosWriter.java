package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.macros.*;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.db.PropertyBuilder;
import nl.games.xrebirth.neo4j.importer.events.Reference;
import nl.games.xrebirth.neo4j.importer.events.ReferenceHolderEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.graphdb.Node;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

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
    Event<MacroType> macroEvent;

    @Inject
    @Reference
    Event<ReferenceHolderEvent> referenceEvent;


    @Inject
    ImportContext importContext;

    public void doWrite(@Observes MacrosType macros) {
        for (MacroType macro : macros.getMacro()) {
            importElement(macro);
        }
    }


    private void importElement(MacroType macro) {
        ComponentType componentType = macro.getComponent();
        ConnectionsType connectionsType = macro.getConnections();
        PropertyBuilder propertyBuilder = PropertyBuilder.create(macro)
                .add("id", macro.getName());
        updateProperties(macro, propertyBuilder);
        importContext.getService().createNode(
                macro,
                propertyBuilder
        );
        if (componentType != null) {
            ReferenceHolderEvent event = new ReferenceHolderEvent(componentType);
            referenceEvent.fire(event);

        }
        if (connectionsType != null) {
            for (ConnectionType connectionType : connectionsType.getConnection()) {
                if (connectionType.getMacro() != null) {
                    //ref to macro
                    MacroType child = connectionType.getMacro();
                    if (child.getRef() == null) {
                        //inline
                        PropertyBuilder relation = PropertyBuilder.create();
                        relation.add("position", connectionType.getPosition());
                        relation.update(connectionType.getOffset(), true);
                        importContext.getService().createRelationship(
                                child,
                                macro,
                                relation
                        );
                        importElement(child);
                    } else {
                        //fire event
                        //child.setName(child.getRef());
                        PropertyBuilder relation = PropertyBuilder.create();
                        relation.add("position", connectionType.getPosition());
                        relation.update(connectionType.getOffset(), true);
                        importContext.getService().createRelationship(
                                child,
                                macro,
                                relation
                        );
                        this.macroEvent.fire(child);
                    }
                } else {
                    //inline

                }
            }
        }
    }

    private void updateProperties(MacroType macro, PropertyBuilder propertyBuilder) {
        PropertiesType propertiesType = macro.getProperties();
        if (propertiesType == null) {
            return;
        }
        propertyBuilder.update(propertiesType, true);

    }


    private Node lookupComponent(ComponentType componentType) {
        return null;
    }

    public void unMarshallObserver(@Observes MacrosType macrosType) {

    }

}
