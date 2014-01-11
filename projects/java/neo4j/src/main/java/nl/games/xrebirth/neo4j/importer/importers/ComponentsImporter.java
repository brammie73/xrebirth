package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.generated.components.ComponentType;
import nl.games.xrebirth.generated.components.ComponentsType;
import nl.games.xrebirth.generated.macros.MacrosType;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.events.FileEvent;
import nl.games.xrebirth.neo4j.importer.events.Index;
import nl.games.xrebirth.neo4j.importer.events.Reference;
import nl.games.xrebirth.neo4j.importer.events.ReferenceHolderEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class ComponentsImporter extends AbstractImporter {

    @Inject
    ImportContext context;

    @Inject
    @Singleton
    @Index
    XRIndex xrIndex;

    ConcurrentHashMap<String, Boolean> importedFiles = new ConcurrentHashMap<>();


    @Override
    public void doImport() {
        for (XRIndex.Key key : xrIndex.keySet()) {
            if (key.getClazz() == ComponentsType.class) {
                String file = xrIndex.get(key.getClazz(), key.getName());
                if (!importedFiles.containsKey(file)) {
                    FileEvent fileEvent = new FileEvent(ComponentsType.class, file);
                    getFileEventBus().fire(fileEvent);
                    importedFiles.putIfAbsent(file, Boolean.TRUE);
                }
            }
        }
    }

    public void componentsListener(@Observes ComponentsType components) {
        for (ComponentType componentType : components.getComponent()) {
            xrIndex.setElement(componentType);
        }
    }


    public void referenceFoundListener(@Observes @Reference ReferenceHolderEvent referenceHolderEvent) {
        if (!referenceHolderEvent.getReferenceType().getClass().getSimpleName().startsWith("Component")) {
            return;
        }
        String ref = referenceHolderEvent.getReferenceType().getRef();
        if (ref.endsWith("macro"))  {
            System.err.println(ref);
        }
        String file = xrIndex.get(ComponentsType.class, ref);
        AbstractElement element = xrIndex.getElement(ComponentsType.class, ref);
        if (element != null) {
            referenceHolderEvent.setElement(element);
            return;
        }
        if (file != null) {
            if (!file.contains("\\")) {
                return;
            }
            FileEvent event = new FileEvent(ComponentsType.class, file);
            event.setObject(referenceHolderEvent);
            fileEventBus.fire(event);
            referenceHolderEvent.setElement((ComponentsType) event.getObject());
        } else {
            System.err.println("file not found in index:" + ref);
        }
    }
}
