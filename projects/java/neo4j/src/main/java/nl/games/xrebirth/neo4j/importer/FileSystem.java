package nl.games.xrebirth.neo4j.importer;

import nl.games.xrebirth.neo4j.Config;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.InputStream;

/**
 * Author: bram
 * Date: 9-1-14
 * Time: 2:56
 */
@Singleton
public class FileSystem {

    private FileObject root;

    private Configuration configuration = Config.getConfiguration();


    @PostConstruct
    public void postConstruct() {
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

    public InputStream resolve(String filePath) {
        try {
            FileObject fo = getRoot().resolveFile(filePath);
            if (fo.exists() && fo.getContent().getSize() > 1L) {
                return fo.getContent().getInputStream();
            }
            return null;
        } catch (FileSystemException e) {
            throw new ImportException(e);
        }
    }
}
