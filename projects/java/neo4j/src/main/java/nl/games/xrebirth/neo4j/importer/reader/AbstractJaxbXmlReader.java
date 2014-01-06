package nl.games.xrebirth.neo4j.importer.reader;

import nl.games.xrebirth.generated.JAXBHelper;
import nl.games.xrebirth.neo4j.importer.ImportContext;
import nl.games.xrebirth.neo4j.importer.ImportException;
import nl.games.xrebirth.neo4j.importer.XmlReader;
import nl.games.xrebirth.neo4j.utils.Utils;
import org.apache.commons.vfs2.FileObject;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 28-12-13
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractJaxbXmlReader<T> implements XmlReader<T> {


    Class<T> getDeclaredClass() {
        return Utils.getDeclaredClass(this);
    }

    @Override
    public T doRead(ImportContext importContext, String file) {
        return doImportXml(importContext, file);
    }

    protected T doImportXml(ImportContext importContext, String file) {
        try {
            FileObject fo = importContext.getRoot().resolveFile(file);
            InputStream is = fo.getContent().getInputStream();
            return JAXBHelper.get().unMarshall(is, getDeclaredClass());
        } catch (Exception e) {
            throw new ImportException(e);
        }
    }
}
