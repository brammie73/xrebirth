package nl.games.xrebirth.neo4j.importer;

import nl.games.xrebirth.neo4j.cache.NodeCache;
import nl.games.xrebirth.neo4j.importer.db.Neo4jService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */

@Singleton
public class ImportContext {

    static Logger log = LogManager.getLogger(ImportContext.class);

    @Inject
    private TextFormatter textFormatter;

    @Inject
    NodeCache nodeCache;

    @Inject
    Neo4jService neo4jService;


    public void init() {
        textFormatter.init();
    }

    public Neo4jService getService() {
        return neo4jService;
    }
}
