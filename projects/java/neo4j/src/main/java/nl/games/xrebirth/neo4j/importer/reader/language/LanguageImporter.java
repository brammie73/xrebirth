package nl.games.xrebirth.neo4j.importer.reader.language;

import com.google.common.base.Strings;
import nl.games.xrebirth.generated.text.Language;
import nl.games.xrebirth.generated.wares.Wares;
import nl.games.xrebirth.neo4j.Config;
import nl.games.xrebirth.neo4j.importer.reader.AbstractImporter;
import nl.games.xrebirth.neo4j.importer.reader.AbstractJaxbXmlReader;
import nl.games.xrebirth.neo4j.importer.reader.AbstractNeo4jWriter;
import nl.games.xrebirth.neo4j.importer.reader.ImportContext;
import nl.games.xrebirth.neo4j.importer.reader.wares.WaresWriter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 30-12-13
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class LanguageImporter extends AbstractImporter<Language> {

    private static String fileLocation;

    static {
        fileLocation = String.format("/t/0001-L%03d.xml",  Config.getConfiguration().getInt("language", 44)).toLowerCase();
    }

    public LanguageImporter() {
        super(
                new AbstractJaxbXmlReader<Language>() {},
                new AbstractNeo4jWriter<Language>() {
                    public Language doWrite(ImportContext importContext, Language language) {
                        return language;
                    }
                }
        );
    }

    @Override
    public List<String> doGetFileLocations() {
        return Arrays.asList(fileLocation);
    }

    public boolean doImport(ImportContext importContext) {
        return super.doImport(importContext, fileLocation);
    }
}
