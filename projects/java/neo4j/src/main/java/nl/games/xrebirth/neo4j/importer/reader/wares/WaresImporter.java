package nl.games.xrebirth.neo4j.importer.reader.wares;

import nl.games.xrebirth.generated.wares.Wares;
import nl.games.xrebirth.neo4j.importer.reader.AbstractImporter;
import nl.games.xrebirth.neo4j.importer.reader.AbstractJaxbXmlReader;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:58
 * To change this template use File | Settings | File Templates.
 */
public class WaresImporter extends AbstractImporter<Wares> {

    private final static String fileLocation = "/libraries/wares.xml";

    public WaresImporter() {
        super(new AbstractJaxbXmlReader<Wares>(fileLocation) {
        }, new WaresWriter());

    }
}