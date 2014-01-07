package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.wares.WaresType;
import nl.games.xrebirth.neo4j.importer.reader.AbstractJaxbXmlReader;
import nl.games.xrebirth.neo4j.importer.writer.WaresWriter;

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
public class WaresImporter  extends AbstractImporter<WaresType>{

    private final static String fileLocation = "/libraries/wares.xml";

    @Inject
    public WaresImporter(WaresWriter waresWriter) {
        super(new AbstractJaxbXmlReader<WaresType>() { }, waresWriter);
    }

    public List<String> doGetFileLocations() {
        return Arrays.asList(fileLocation);
    }
}
