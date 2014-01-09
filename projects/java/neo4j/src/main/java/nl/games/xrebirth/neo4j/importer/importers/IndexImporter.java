package nl.games.xrebirth.neo4j.importer.importers;

import com.google.common.collect.ImmutableMap;
import nl.games.xrebirth.generated.index.Entry;
import nl.games.xrebirth.generated.index.Index;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.events.FileEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
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

    static final Map<String, String> map = ImmutableMap.<String, String>builder()
            .put("index/components.xml", "component")
            .put("index/macros.xml", "macro")
            .build();

    Logger log = LogManager.getLogger();

    @Inject
    Event<FileEvent> fileEventBus;

    @Inject
    ImportContext importContext;

    private XRIndex index = new XRIndex();


    protected void doImport() {
        for (String file : map.keySet()) {
            FileEvent event = new FileEvent(Index.class, file);
            this.fileEventBus.fire(event);
        }
    }

    @Produces
    @Singleton
    @Any
    public XRIndex getXRIndex() {
        doImport();
        return this.index;
    }

    private void indexObserver(@Observes Index index) {
        String type = map.get(index.getXmlFile());
        if (type != null) {
            for (Entry entry : index.getEntry()) {
                this.index.add(type, entry.getName(), entry.getValue());
            }
        } else {
            log.error("unkown index type found {}", type);
        }
    }


}
