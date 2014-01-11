package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.neo4j.importer.Importer;
import nl.games.xrebirth.neo4j.importer.events.FileEvent;
import nl.games.xrebirth.neo4j.importer.events.Reference;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:59
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractImporter implements Importer {

    boolean imported = false;

    @Inject
    Event<FileEvent> fileEventBus;

    @Inject
    @Reference
    Event<FileEvent> referenceEventBus;


    public Event<FileEvent> getFileEventBus() {
        return fileEventBus;
    }


    public boolean isImported() {
        return imported;
    }

}
