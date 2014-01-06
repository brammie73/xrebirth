package nl.games.xrebirth.neo4j.importer;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 30-12-13
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */
public interface TextFormatter {

    public String format(String in);

    public String[] format(String[] in);

}
