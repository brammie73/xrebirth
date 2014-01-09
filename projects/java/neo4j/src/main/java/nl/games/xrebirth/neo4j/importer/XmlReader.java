package nl.games.xrebirth.neo4j.importer;

import nl.games.xrebirth.generated.AbstractElement;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 28-12-13
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public interface XmlReader {

    public AbstractElement doRead(ImportContext importContext, String file);

}
