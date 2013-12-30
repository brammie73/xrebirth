package nl.games.xrebirth.neo4j.importer.reader;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 30-12-13
 * Time: 22:20
 * To change this template use File | Settings | File Templates.
 */
public class DefaultTextFormatter implements TextFormatter {

    @Override
    public String format(String in) {
        return in;
    }

    @Override
    public String[] format(String[] in) {
        return in;
    }
}
