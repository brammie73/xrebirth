package nl.games.xrebirth.neo4j.importer.service;

import nl.games.xrebirth.neo4j.importer.reader.ImportContext;
import nl.games.xrebirth.neo4j.importer.reader.wares.WaresImporter;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.Settings;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.shell.ShellLobby;
import org.neo4j.shell.ShellServer;
import org.neo4j.shell.ShellSettings;
import org.neo4j.shell.kernel.GraphDatabaseShellServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 16:12
 * To change this template use File | Settings | File Templates.
 */
public class EmbeddedService {

    private static final String DB_PATH = "neo4j-store";
    private static final String USERNAME_KEY = "username";
    private static GraphDatabaseService graphDb;


    public static void main(final String[] args) throws Exception {
        registerShutdownHookForNeo();
        boolean trueForLocal = waitForUserInput(
                "Would you like to start a "
                        + "local shell instance or enable neo4j to accept remote "
                        + "connections [l/r]? ").equalsIgnoreCase("l");
        if (trueForLocal) {
            startLocalShell();
        } else {
            startRemoteShellAndWait();
        }
        shutdown();
    }


    private static void startLocalShell() throws Exception {
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);

        ImportContext importContext = new ImportContext();
        importContext.setDatabaseService(graphDb);


/*

        RacesImporter racesImporter = new RacesImporter();
        racesImporter.doImport(importContext);

        FactionsImporter factionsImporter = new FactionsImporter();
        factionsImporter.doImport(importContext);
*/

        WaresImporter waresImporter = new WaresImporter();
        waresImporter.doImport(importContext);


        ShellServer shellServer = new GraphDatabaseShellServer((GraphDatabaseAPI)graphDb);
        ShellLobby.newClient(shellServer).grabPrompt();
        shellServer.shutdown();
    }

    private static void startRemoteShellAndWait() throws Exception {
        graphDb = (GraphDatabaseAPI) new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(DB_PATH).
                setConfig(ShellSettings.remote_shell_enabled, Settings.TRUE).
                newGraphDatabase();

        waitForUserInput("Remote shell enabled, connect to it by executing\n"
                + "the shell-client script in a separate terminal."
                + "The script is located in the bin directory.\n"
                + "\nWhen you're done playing around, just press [Enter] "
                + "in this terminal ");
    }

    private static String waitForUserInput(final String textToSystemOut)
            throws Exception {
        System.out.print(textToSystemOut);
        return new BufferedReader(new InputStreamReader(System.in, "UTF-8"))
                .readLine();
    }

    private static void shutdownGraphDb() {
        System.out.println("Shutting down database ...");
        graphDb.shutdown();
        graphDb = null;
    }

    private static void shutdown() {
        if (graphDb != null) {
            shutdownGraphDb();
        }
    }

    private static void registerShutdownHookForNeo() {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running example before it's completed)
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutdown();
            }
        });
    }

}
