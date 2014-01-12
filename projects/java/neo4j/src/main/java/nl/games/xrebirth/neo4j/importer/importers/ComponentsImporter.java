package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.components.ComponentType;
import nl.games.xrebirth.generated.components.ComponentsType;
import nl.games.xrebirth.neo4j.importer.annotation.Index;
import nl.games.xrebirth.neo4j.importer.annotation.Load;
import nl.games.xrebirth.neo4j.importer.annotation.LoadComplete;
import nl.games.xrebirth.neo4j.importer.events.FileEvent;
import nl.games.xrebirth.neo4j.importer.events.MacroEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class ComponentsImporter extends AbstractImporter {

    Logger log = LogManager.getLogger();

    @Inject
    @Index(ComponentsType.class)
    XRIndex xrIndex;


    @Inject
    Event<FileEvent> fileEventBus;


    @Inject
    @LoadComplete
    Event<MacroEvent> loadCompleteEventBus;

    @PostConstruct
    void initialize() {
        this.xrIndex.add("playercargobay", "libraries/component"); //not in indexx ...
    }



    @Override
    public void doImport() {
    }

    private ConcurrentLinkedQueue<MacroEvent> macroEventQueue = new ConcurrentLinkedQueue<>();

    public void macroEventListener(@Observes @Load MacroEvent macroEvent) {
        String ref = macroEvent.getMacroType().getComponent().getRef();
        macroEventQueue.add(macroEvent);
        ComponentType component = getComponentType(ref);
        if (component != null) {
            macroEvent.setComponent(component);
            macroEventQueue.remove(macroEvent);
            loadCompleteEventBus.fire(macroEvent);
        }
    }

    public ComponentType getComponentType(String name) {
        XRIndex.Value v = xrIndex.get(name);
        if (v != null) {
            ComponentType component =  (ComponentType)v.getElement();
            if (component ==  null) {
                FileEvent fileEvent = new FileEvent(ComponentsType.class, v.getFile());
                fileEventBus.fire(fileEvent);
                return  null;
            } else {
                return component;
            }
        }
        log.error("getComponentType: should not happen");
        throw new IllegalArgumentException(name);
    }

    public void componentsListener(@Observes ComponentsType components) {
        for (ComponentType componentType : components.getComponent()) {
            XRIndex.Value v = xrIndex.get(componentType.getName());
            if (v == null) {
                log.error("componentsListener: should not happen");
                throw new IllegalArgumentException(componentType.getName());
            } else {
                v.setElement(componentType);
            }
            for (MacroEvent macroEvent : macroEventQueue) {
                if (componentType.getName().equals(macroEvent.getMacroType().getComponent().getRef())) {
                    macroEvent.setComponent(componentType);
                    macroEventQueue.remove(macroEvent);
                    loadCompleteEventBus.fire(macroEvent);
                }
            }
        }
    }
}
