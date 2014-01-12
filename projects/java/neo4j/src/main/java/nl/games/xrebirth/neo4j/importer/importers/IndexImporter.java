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

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;
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

    protected void doImport(Class clazz) {
        for (Map.Entry<String, Class<? extends AbstractElement>> entry : map.entrySet()) {
            if (entry.getValue() == clazz) {
                FileEvent event = new FileEvent(Index.class, entry.getKey());
                event.setObject(index);
                this.fileEventBus.fire(event);
            }
        }
    }

    XRIndex index;


    @Produces
    @Dependent
    @nl.games.xrebirth.neo4j.importer.annotation.Index
    public XRIndex indexProducer(InjectionPoint ip) {
        for (Annotation n : ip.getQualifiers()) {
            if (n instanceof nl.games.xrebirth.neo4j.importer.annotation.Index) {
                Class val = ((nl.games.xrebirth.neo4j.importer.annotation.Index) n).value();
                index = XRIndex.build();
                doImport(val);
                return index;
            }
        }
        throw new IllegalArgumentException("illegal class");
    }

    private void indexObserver(@Observes Index index) {
        Class type = map.get(index.getXmlFile());
        if (type != null) {
            for (Entry entry : index.getEntry()) {
                this.index.add(entry.getName(), entry.getValue());
            }
        } else {
            log.error("unkown index type found {}", type);
        }
    }


}
