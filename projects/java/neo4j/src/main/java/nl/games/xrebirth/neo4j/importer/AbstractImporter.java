package nl.games.xrebirth.neo4j.importer;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:59
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractImporter<T> implements Importer {

    private XmlReader<T> reader;

    private Neo4jWriter<T> writer;

    private T values;

    private boolean imported = false;

    AbstractImporter() {
    }

    protected AbstractImporter(XmlReader<T> reader, Neo4jWriter<T> writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public abstract List<String> doGetFileLocations();

    public XmlReader<T> getReader() {
        return reader;
    }

    public Neo4jWriter<T> getWriter() {
        return writer;
    }

    @Override
    public boolean doImport(ImportContext importContext) {
        for (String s : doGetFileLocations()) {
            boolean result = doImport(importContext, s);
            if (!result) {
                System.err.println("error on:" + s);
                return false;
            }
        }
        imported = true;
        return true;
    }

    private boolean doImport(ImportContext importContext, String file) {
        T t = getReader().doRead(importContext, file);
        getWriter().doWrite(importContext, t);
        this.values = t;
        return true;
    }


    public T getValues() {
        if (values == null) {
            throw new ImportException("objects not imported yet");
        }
        T  local = values;
        values = null;
        return local;
    }

    public boolean isImported() {
        return imported;
    }

    public Class getDeclaredClass() {
        return  (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
