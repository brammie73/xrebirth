package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.macros.*;
import nl.games.xrebirth.neo4j.importer.events.Reference;
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
public class MacrosWriter  {

    private static Logger log = LogManager.getLogger();


    @Inject
    @Reference
    Event<MacroType> macroEvent;



    public void doWrite(MacrosType macros) {
        for (MacroType macro : macros.getMacro()) {
            importElement(macro);
        }
    }


    private void importElement(MacroType macro) {
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
                        importElement(child);
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

    public void unMarshallObserver(@Observes MacrosType macrosType) {

    }

}
