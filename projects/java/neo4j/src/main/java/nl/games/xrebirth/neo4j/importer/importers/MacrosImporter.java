package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.macros.MacroType;
import nl.games.xrebirth.generated.macros.MacrosType;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.events.FileEvent;
import nl.games.xrebirth.neo4j.importer.events.Index;
import nl.games.xrebirth.neo4j.importer.events.Reference;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 23:26
 */

@Singleton
public class MacrosImporter extends AbstractImporter {

    String galaxy = "/maps/XU_ep1_universe/galaxy.xml";
    String clusters = "/maps/XU_ep1_universe/clusters.xml";
    String sectors = "/maps/XU_ep1_universe/sectors.xml";
    String zones = "/maps/XU_ep1_universe/zones.xml";
    String zonehighways = "/maps/XU_ep1_universe/zonehighways.xml";


    @Inject
    @Singleton
    @Index
    XRIndex xrIndex;

    @Inject
    Event<FileEvent> fileEventBus;

    public void doImport() {
        fileEventBus.fire(new FileEvent(MacrosType.class, galaxy));
        fileEventBus.fire(new FileEvent(MacrosType.class, clusters));
        fileEventBus.fire(new FileEvent(MacrosType.class, sectors));
        fileEventBus.fire(new FileEvent(MacrosType.class, zones));
        fileEventBus.fire(new FileEvent(MacrosType.class, zonehighways));
    }

    public void refererFoundListener(@Observes @Reference MacroType macroType) {
        String ref = macroType.getRef();
        String file = xrIndex.get(MacrosType.class, ref);
        if (file != null) {
            if (!file.contains("\\") ) {
                return;
            }
            FileEvent event = new FileEvent(MacrosType.class, file);
            fileEventBus.fire(event);
        } else {
            System.err.println("file not found in index:" + ref);
        }
    }
}
