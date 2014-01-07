package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.macros.*;
import nl.games.xrebirth.neo4j.cache.NodeCache;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.events.Reference;
import nl.games.xrebirth.neo4j.producers.LabelProducer;
import nl.games.xrebirth.neo4j.producers.RelationshipTypeProducer;
import org.neo4j.graphdb.Node;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 23:27
 */
@Singleton
public class MacrosWriter  extends AbstractNeo4jWriter<MacrosType>{

    @Inject
    @Reference
    Event<MacroType> macroEvent;

    @Inject
    NodeCache nodeCache;

    @Inject
    LabelProducer labelProducer;

    @Inject
    RelationshipTypeProducer relationshipTypeProducer;


    @Override
    public MacrosType doWrite(ImportContext importContext, MacrosType macros) {
        for (MacroType macro : macros.getMacro()) {
            importMacro(macro);
        }
        return null;
    }


    private void importMacro(MacroType macro) {
        ComponentType componentType = macro.getComponent();
        PropertiesType propertiesType = macro.getProperties();
        ConnectionsType connectionsType = macro.getConnections();
        if (connectionsType != null) {
            for (ConnectionType connectionType : connectionsType.getConnection()) {
                if (connectionType.getMacro() != null) {
                    //ref to macro
                    MacroType child = connectionType.getMacro();
                    if (child.getRef() == null) {
                        //inline
                        importMacro(child);
                    } else {
                        //fire event
                        macroEvent.fire(child);
                    }
                } else {
                    //inline

                }
            }
        }
    }


    private Node lookupComponent(ComponentType componentType) {
        return null;
    }
}
