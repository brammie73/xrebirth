package nl.games.xrebirth.neo4j.importer.reader.races;

import nl.games.xrebirth.generated.races.Races;
import nl.games.xrebirth.neo4j.importer.reader.AbstractImporter;
import nl.games.xrebirth.neo4j.importer.reader.AbstractJaxbXmlReader;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:58
 * To change this template use File | Settings | File Templates.
 */
public class RacesImporter extends AbstractImporter<Races> {

    private static String racesFileLocation = "/libraries/races.xml";

    public RacesImporter() {
        super(new AbstractJaxbXmlReader<Races>(racesFileLocation) { }, new RacesWriter());
    }


}