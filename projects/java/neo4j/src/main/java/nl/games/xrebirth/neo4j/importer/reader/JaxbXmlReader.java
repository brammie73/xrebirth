package nl.games.xrebirth.neo4j.importer.reader;

import nl.games.xrebirth.generated.AbstractElement;
import nl.games.xrebirth.generated.JAXBHelper;
import nl.games.xrebirth.neo4j.importer.FileSystem;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.XmlReader;
import nl.games.xrebirth.neo4j.importer.events.FileEvent;
import org.apache.commons.lang.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.InputStream;

/**
 * User: bram
 * Date: 28-12-13
 * Time: 17:32
 * <p/>
 * cant make a local importcontext, generates a loop...
 */
@Singleton
public class JaxbXmlReader implements XmlReader {

    private static Logger log = LogManager.getLogger(JaxbXmlReader.class);

    @Inject
    Event<AbstractElement> unmarshallEventBus;

    @Inject
    FileSystem fileSystem;

    public AbstractElement doRead(ImportContext importContext, String file) {
        throw new NotImplementedException();
    }

    public void fileEventObserver(@Observes FileEvent event) {
        try {
            if (event != null && event.getFile() != null) {
                String file = event.getFile();
                InputStream inputStream = fileSystem.resolve(file);
                if (inputStream == null)  {
                    log.debug("skipping:{}", file);
                    return;
                }
                Object result = JAXBHelper.get().unMarshall(inputStream, event.getClazz());
                AbstractElement element = (AbstractElement) result;
                element.setXmlFile(event.getFile());
                unmarshallEventBus.fire(element);
            } else {
                log.error("file not found for:{}", event.getFile());
            }
        } catch (Exception e) {
            throw new XmlReaderException(e);
        }
    }
}
