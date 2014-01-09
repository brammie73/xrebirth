package nl.games.xrebirth.neo4j.importer.writer;

import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.neo4j.importer.Neo4jWriter;

import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 2:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractNeo4jWriter<T extends AbstractElement> implements Neo4jWriter<T> {

    @Inject
    Event<AbstractElement> elementEvent;

    public void fire(AbstractElement element) {
        elementEvent.fire(element);
    }

    public <T extends AbstractElement> T createElement(Class<T> clazz, Object id) {
        T element = createElement(clazz);
        element.setId(id);
        return element;
    }

    public <T extends AbstractElement> T createElement(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new Neo4jWriterException(e);
        }
    }

}
