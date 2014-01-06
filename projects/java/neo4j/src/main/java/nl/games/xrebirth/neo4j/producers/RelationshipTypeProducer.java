package nl.games.xrebirth.neo4j.producers;

import nl.games.xrebirth.generated.AbstractElement;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.RelationshipType;

import javax.inject.Singleton;

/**
 * Author: bram
 * Date: 6-1-14
 * Time: 13:36
 */
@Singleton
public class RelationshipTypeProducer {

    public RelationshipType produce(AbstractElement element) {
        return produce(element.getClass());
    }

    public RelationshipType produce(Class<? extends AbstractElement> clazz) {
        return DynamicRelationshipType.withName(clazz.getSimpleName().toLowerCase());
    }

}
