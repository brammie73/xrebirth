package nl.games.xrebirth.neo4j.importer.importers;

import nl.games.xrebirth.generated.text.Language;
import nl.games.xrebirth.neo4j.Config;
import nl.games.xrebirth.neo4j.importer.AbstractImporter;
import nl.games.xrebirth.neo4j.importer.TextFormatter;
import nl.games.xrebirth.neo4j.importer.reader.AbstractJaxbXmlReader;
import nl.games.xrebirth.neo4j.importer.writer.AbstractNeo4jWriter;
import nl.games.xrebirth.neo4j.importer.ImportContext;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 30-12-13
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */

@Singleton
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

    @PostConstruct
    public void postConstruct()  {

    }

    public void clear() {

    }

    @Override
    public List<String> doGetFileLocations() {
        return Arrays.asList(fileLocation);
    }

}
