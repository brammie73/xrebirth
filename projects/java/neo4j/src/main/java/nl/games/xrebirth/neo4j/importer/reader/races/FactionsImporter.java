package nl.games.xrebirth.neo4j.importer.reader.races;

import nl.games.xrebirth.generated.factions.Factions;
import nl.games.xrebirth.neo4j.importer.reader.AbstractImporter;
import nl.games.xrebirth.neo4j.importer.reader.AbstractJaxbXmlReader;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:58
 * To change this template use File | Settings | File Templates.
 */
public class FactionsImporter extends AbstractImporter<Factions> {

    private final static String fileLocation = "/libraries/factions.xml";


    public FactionsImporter() {
        super(new AbstractJaxbXmlReader<Factions>(fileLocation) {}, new FactionsWriter());
    }


}
