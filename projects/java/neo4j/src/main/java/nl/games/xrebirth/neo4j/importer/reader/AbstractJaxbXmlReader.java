package nl.games.xrebirth.neo4j.importer.reader;

import nl.games.xrebirth.neo4j.utils.Utils;
import org.apache.commons.vfs2.FileObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 28-12-13
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractJaxbXmlReader<T> implements XmlReader<T> {

    private final String fileLocation;

    public AbstractJaxbXmlReader(final String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @Override
    public String getFileLocation() {
        return this.fileLocation;
    }

    String getPackage() {
        return Utils.getDeclaredClass(this).getPackage().getName();
    }

    @Override
    public T doRead(ImportContext importContext) {
        return doImportXml(importContext);
    }

    protected T doImportXml(ImportContext importContext) {
        try {
            FileObject fo = importContext.getRoot().resolveFile(getFileLocation());
            InputStream is = fo.getContent().getInputStream();
            JAXBContext jc = JAXBContext.newInstance(getPackage());
            Unmarshaller u = jc.createUnmarshaller();
            Object obj = u.unmarshal( is );
            if (obj instanceof JAXBElement) {
                JAXBElement element = (JAXBElement)obj;
                return (T)element.getValue();
            } else {
                return (T)obj;
            }
        } catch (Exception e) {
            throw new ImportException(e);
        }
    }
}
