package nl.games.xrebirth.neo4j.importer.reader;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 28-12-13
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public interface Neo4jWriter<T> {

    public T doWrite(ImportContext importContext, T t);

}