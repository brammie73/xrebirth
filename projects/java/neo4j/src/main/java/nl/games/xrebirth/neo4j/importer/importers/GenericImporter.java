package nl.games.xrebirth.neo4j.importer.importers;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimap;
import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.generated.factions.Factions;
import nl.games.xrebirth.generated.races.Races;
import nl.games.xrebirth.generated.wares.WaresType;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.events.FileEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:58
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class GenericImporter extends  AbstractImporter {

    static final Multimap<Class<? extends AbstractElement>, String> map = ImmutableListMultimap.<Class<? extends AbstractElement>, String>builder()
            .put(Races.class, "/libraries/races.xml")
            .put(Factions.class, "/libraries/factions.xml")
            .put(WaresType.class, "/libraries/wares.xml")
            .build();


    @Inject
    ImportContext importContext;

    @Inject
    Event<FileEvent> fileEventBus;


    @Override
    public void doImport() {
        for (Class<? extends AbstractElement> clazz : map.keySet()) {
            for (String file : map.get(clazz)) {
                fileEventBus.fire(new FileEvent(clazz, file));
            }
        }
    }

}
