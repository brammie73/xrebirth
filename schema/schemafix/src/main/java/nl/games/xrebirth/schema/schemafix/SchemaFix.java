package nl.games.xrebirth.schema.schemafix;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 4-1-14
 * Time: 1:45
 * To change this template use File | Settings | File Templates.
 */
public interface SchemaFix {

    boolean execute(File in, File out);

}
