package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.macros.MacroType;
import nl.games.xrebirth.generated.macros.MacrosType;
import nl.games.xrebirth.neo4j.importer.annotation.Index;
import nl.games.xrebirth.neo4j.importer.annotation.Load;
import nl.games.xrebirth.neo4j.importer.annotation.LoadComplete;
import nl.games.xrebirth.neo4j.importer.annotation.Reference;
import nl.games.xrebirth.neo4j.importer.events.FileEvent;
import nl.games.xrebirth.neo4j.importer.events.MacroEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 23:26
 */

@Singleton
public class MacrosImporter extends AbstractImporter {

    static Logger log = LogManager.getLogger();


    String galaxy = "/maps/XU_ep1_universe/galaxy.xml";
    String clusters = "/maps/XU_ep1_universe/clusters.xml";
    String sectors = "/maps/XU_ep1_universe/sectors.xml";
    String zones = "/maps/XU_ep1_universe/zones.xml";
    String zonehighways = "/maps/XU_ep1_universe/zonehighways.xml";


    @Inject
    @Index(MacrosType.class)
    XRIndex xrIndex;


    @Inject
    Event<FileEvent> fileEventBus;

    @Inject
    @Load
    Event<MacroEvent> loadMacroEventBus;

    @Inject
    @LoadComplete
    Event<MacroEvent> loadCompleteMacroEventBus;


    public void doImport() {
        fileEventBus.fire(new FileEvent(MacrosType.class, galaxy));
        //fileEventBus.fire(new FileEvent(MacrosType.class, clusters));
        //fileEventBus.fire(new FileEvent(MacrosType.class, sectors));
        //fileEventBus.fire(new FileEvent(MacrosType.class, zones));
        //fileEventBus.fire(new FileEvent(MacrosType.class, zonehighways));
    }

    public void doWrite(@Observes MacrosType macros) {
        log.info("read  file: {}", macros.getXmlFile());
        for (MacroType macro : macros.getMacro()) {
            String name = macro.getName();
            XRIndex.Value v = xrIndex.get(name);
            if (v  == null) {
                log.error("should not happen");
                throw new IllegalArgumentException("name");
            }
            v.setElement(macro);
            MacroEvent macroEvent = new MacroEvent();
            macroEvent.setMacroType(macro);
            loadMacroEventBus.fire(macroEvent);
        }
    }

    public MacroType getMacro(String name) {
        XRIndex.Value v = xrIndex.get(name);
        if (v  == null) {
            log.error("should not happen");
            throw new IllegalArgumentException(name);
        } else {
            MacroType macroType = (MacroType)v.getElement();
            if (macroType == null) {
                String file = v.getFile();
                FileEvent event = new FileEvent(MacrosType.class, file);
                fileEventBus.fire(event);
                return null;
            } else {
                return macroType;
            }
        }
    }

    public void refFoundListener(@Observes @Reference MacroType macroType) {
        String ref = macroType.getRef();
        MacroType loadedMacro = getMacro(ref);
        if (loadedMacro == null) {
            log.info("to load: {}", ref);
        } else {
            MacroEvent macroEvent = new MacroEvent();
            macroEvent.setMacroType(loadedMacro);
            loadMacroEventBus.fire(macroEvent);
        }
    }
}
