package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.macros.MacroType;
import nl.games.xrebirth.generated.macros.MacrosType;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.events.FileEvent;
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
    ImportContext importContext;

    @Inject
    Event<FileEvent> fileEventBus;

    @Override
    public void doImport() {

    }

    public Collection<String> doGetFileLocations() {
        return null;
    }

    public boolean doImport(ImportContext importContext) {
        FileEvent event = new FileEvent(MacrosType.class, galaxy);
        fileEventBus.fire(event);
        return true;
    }


    protected boolean doImport(ImportContext importContext, String file) {
        return true;
    }

    public void refererFoundListener(@Observes @Reference MacroType macroType) {
        String ref = macroType.getRef();
        String file = null; //xRIndex.getMacro(ref);
        if (file != null) {
            FileEvent event = new FileEvent(MacrosType.class, file);
            fileEventBus.fire(event);
        } else {
            System.err.println("file not found in index:" + ref);
        }
    }
}
