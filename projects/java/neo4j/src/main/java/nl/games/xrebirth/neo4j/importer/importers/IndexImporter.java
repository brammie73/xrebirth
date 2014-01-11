package nl.games.xrebirth.neo4j.importer.importers;

import com.google.common.collect.ImmutableMap;
import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.generated.components.ComponentsType;
import nl.games.xrebirth.generated.index.Entry;
import nl.games.xrebirth.generated.index.Index;
import nl.games.xrebirth.generated.macros.MacrosType;
import nl.games.xrebirth.neo4j.importer.events.FileEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

/**
 * Author: bram
 * Date: 7-1-14
 * Time: 20:09
 */
@Singleton
public class IndexImporter {

    static final Map<String, Class<? extends AbstractElement>> map = ImmutableMap.<String, Class<? extends AbstractElement>>builder()
            .put("index/components.xml", ComponentsType.class)
            .put("index/macros.xml", MacrosType.class)
            .build();

    Logger log = LogManager.getLogger();

    @Inject
    Event<FileEvent> fileEventBus;

    protected void doImport() {
        for (String file : map.keySet()) {
            FileEvent event = new FileEvent(Index.class, file);
            this.fileEventBus.fire(event);
        }
    }
    XRIndex index = XRIndex.build();


    @Produces
    @Singleton
    @nl.games.xrebirth.neo4j.importer.events.Index
    public XRIndex getXRIndexProducer() {
        doImport();
        return index;
    }

    private void indexObserver(@Observes Index index) {
        Class type = map.get(index.getXmlFile());
        if (type != null) {
            for (Entry entry : index.getEntry()) {
                this.index.add(type, entry.getName(), entry.getValue());
            }
        } else {
            log.error("unkown index type found {}", type);
        }
    }


}
