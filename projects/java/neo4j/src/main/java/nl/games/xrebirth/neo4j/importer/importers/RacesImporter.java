package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.races.Races;
import nl.games.xrebirth.neo4j.importer.reader.AbstractJaxbXmlReader;
import nl.games.xrebirth.neo4j.importer.writer.RacesWriter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:58
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class RacesImporter extends AbstractImporter<Races> {

    private static String racesFileLocation = "/libraries/races.xml";

    @Inject
    public RacesImporter(RacesWriter writer) {
        super(new AbstractJaxbXmlReader<Races>() { }, writer);
    }

    @Override
    public List<String> doGetFileLocations() {
        return Arrays.asList(racesFileLocation);
    }
}
