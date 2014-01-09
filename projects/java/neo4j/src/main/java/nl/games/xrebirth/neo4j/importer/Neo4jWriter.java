package nl.games.xrebirth.neo4j.importer;

import nl.games.xrebirth.generated.AbstractElement;

import javax.enterprise.event.Observes;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 28-12-13
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public interface Neo4jWriter<T extends AbstractElement> {

    public void doWrite(@Observes T element);

}
