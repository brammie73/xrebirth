package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.neo4j.cache.CacheKey;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.db.PropertyBuilder;
import nl.games.xrebirth.neo4j.importer.events.DeferedRelationshipEvent;
import nl.games.xrebirth.neo4j.importer.events.NodeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PreDestroy;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Author: bram
 * Date: 8-1-14
 * Time: 2:02
 */
@Singleton
public class DeferedRelationWriter {

    Logger log = LogManager.getLogger(DeferedRelationWriter.class);

    @Inject
    ImportContext importContext;

    ConcurrentHashMap<CacheKey, Queue<DeferedRelationshipEvent>> map = new ConcurrentHashMap<>();

    public void nodeCreateObserver(@Observes NodeEvent event) {
        Queue<DeferedRelationshipEvent> list = map.remove(event.getCacheKey());
        if (list != null && !list.isEmpty()) {
            for (DeferedRelationshipEvent relationship : list) {
                if (relationship.getFrom().getCacheKey() != null && relationship.getTo().getCacheKey() != null ) {
                    importContext.getService().createRelationship(
                            relationship.getFrom().getCacheKey(),
                            relationship.getTo().getCacheKey(),
                            relationship.getType(),
                            PropertyBuilder.create(relationship)
                    );
                } else if (relationship.getFrom().getCacheKey() == null) {
                    importContext.getService().createRelationship(
                            event.getCacheKey(),
                            relationship.getTo().getCacheKey(),
                            relationship.getType(),
                            PropertyBuilder.create(relationship)
                    );
                } else if (relationship.getTo().getCacheKey() == null) {
                    importContext.getService().createRelationship(
                            relationship.getFrom().getCacheKey(),
                            event.getCacheKey(),
                            relationship.getType(),
                            PropertyBuilder.create(relationship)
                    );
                } else {
                    throw new IllegalStateException("dunno what to do fom and to are null");
                }
            }
        }
    }

    public void relationshipObserver(@Observes DeferedRelationshipEvent event) {
        Queue<DeferedRelationshipEvent> list = new ConcurrentLinkedQueue<>();
        if (event.getFrom().getCacheValue() == null) {
            Queue<DeferedRelationshipEvent> q = map.putIfAbsent(event.getFrom().getCacheKey(), list);
            if (q == null) {
                list.add(event);
            } else {
                q.add(event);
            }

        } else if (event.getTo().getCacheValue() == null) {
            Queue<DeferedRelationshipEvent> q = map.putIfAbsent(event.getTo().getCacheKey(), list);
            if (q == null) {
                list.add(event);
            } else {
                q.add(event);
            }
        }
    }

    @PreDestroy
    public void preDestroy() {
        if (!map.isEmpty()) {
            log.error("still have items left {}", map.size());
        }
    }


}
