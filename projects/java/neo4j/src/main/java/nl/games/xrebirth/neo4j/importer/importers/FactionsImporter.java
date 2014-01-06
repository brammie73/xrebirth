package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.factions.Factions;
import nl.games.xrebirth.neo4j.importer.AbstractImporter;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.reader.AbstractJaxbXmlReader;
import nl.games.xrebirth.neo4j.importer.writer.FactionsWriter;

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
public class FactionsImporter extends AbstractImporter<Factions> {

    private final static String fileLocation = "/libraries/factions.xml";

    @Inject
    RacesImporter racesImporter;

    @Inject
    public FactionsImporter(FactionsWriter writer) {
        super(new AbstractJaxbXmlReader<Factions>() {}, writer);
    }

    public List<String> doGetFileLocations() {
        return Arrays.asList(fileLocation);
    }

    @Override
    public boolean doImport(ImportContext importContext) {
        racesImporter.doImport(importContext);
        return super.doImport(importContext);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
