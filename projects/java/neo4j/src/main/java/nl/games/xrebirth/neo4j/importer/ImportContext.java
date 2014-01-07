package nl.games.xrebirth.neo4j.importer;

import nl.games.xrebirth.neo4j.Config;
import nl.games.xrebirth.neo4j.importer.reader.IndexReader;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.neo4j.graphdb.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */

@Singleton
public class ImportContext {

    private GraphDatabaseService databaseService;

    private FileObject root;

    private Configuration configuration = Config.getConfiguration();

    @Inject
    private LanguageTextFormatter textFormatter;

    public ImportContext() {
    }

    @PostConstruct
    void postConstruct() {
        setUpFilesystem();
        textFormatter.initialize(this);

    }

    void setUpFilesystem() {
        try {
            String uri = new StringBuilder("xr:").append(this.configuration.getString("egosoft.xrebirth.dir")).toString();
            FileSystemManager manager = VFS.getManager();
            this.root = manager.resolveFile(uri);
        } catch (FileSystemException e) {
            throw new ImportException(e);
        }
    }

    public FileObject getRoot() {
        return root;
    }

    public GraphDatabaseService getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(GraphDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public static final Label ROOT_LABEL = DynamicLabel.label("ROOT");
    public static final String NAME_OBJECT_TYPE = "object-type";


    //no transaction ...
    public Node findRootNode(String type) {
        ResourceIterable<Node> iterable = databaseService.findNodesByLabelAndProperty(ROOT_LABEL, NAME_OBJECT_TYPE, type);
        ResourceIterator<Node> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            Node item = iterator.next();
            return item;
        } else {
            Node node = databaseService.createNode();
            node.addLabel(ROOT_LABEL);
            node.setProperty(NAME_OBJECT_TYPE, type);
            return node;
        }
    }

    public String format(String in) {
        return textFormatter.format(in);
    }

    public String[] format(String[] in) {
        return textFormatter.format(in);
    }
}
