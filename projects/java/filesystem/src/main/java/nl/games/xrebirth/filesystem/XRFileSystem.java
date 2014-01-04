package nl.games.xrebirth.filesystem;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.provider.AbstractFileName;
import org.apache.commons.vfs2.provider.AbstractFileSystem;
import org.apache.commons.vfs2.provider.UriParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 3:21
 * To change this template use File | Settings | File Templates.
 */
public class XRFileSystem extends AbstractFileSystem implements FileSystem {

    private long diretoryLastModilfied = 0L;

    protected XRFileSystem(FileName rootName, FileObject parentLayer, FileSystemOptions fileSystemOptions) {
        super(rootName, parentLayer, fileSystemOptions);

    }

    @Override
    public void init() throws FileSystemException {
        super.init();

        //todo:sort by name?
        for (final FileObject catalogFile : getParentLayer().getChildren()) {
            if ("cat".equals(catalogFile.getName().getExtension())) {
                String dataFileName = catalogFile.getName().getBaseName().replace(".cat", ".dat");
                parseCatalog(catalogFile, getParentLayer().getChild(dataFileName));
            }
        }
    }

    private void parseCatalog(FileObject catalogFileObject, FileObject dataFileObject) throws FileSystemException {
        diretoryLastModilfied = catalogFileObject.getContent().getLastModifiedTime();
        InputStream inputStream = catalogFileObject.getContent().getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<XRFileObject> strongRef = new ArrayList<>(100);
        long offset = 0;
        try {
            while ((line = reader.readLine()) != null) {
                XRFileInfo fileInfo = new XRFileInfo(catalogFileObject, dataFileObject, line, offset);
                AbstractFileName name = (AbstractFileName) getFileSystemManager().resolveName(getRootName(), UriParser.encode(fileInfo.getPath()));
                XRFileObject fileObj = new XRFileObject(name, this, fileInfo);
                putFileToCache(fileObj);
                strongRef.add(fileObj);
                fileObj.holdObject(strongRef);
                offset = offset + fileObj.getContent().getSize();

                // Make sure all ancestors exist
                XRFileObject parent;
                for (AbstractFileName parentName = (AbstractFileName) name.getParent();
                     parentName != null;
                     fileObj = parent, parentName = (AbstractFileName) parentName.getParent()
                        ) {
                    // Locate the parent
                    parent = (XRFileObject) getFileFromCache(parentName);
                    if (parent == null) {
                        parent = new XRFileObject(parentName, this, null) {
                            @Override
                            protected long doGetLastModifiedTime() throws Exception {
                                return diretoryLastModilfied;
                            }
                        };

                        putFileToCache(parent);
                        strongRef.add(parent);
                        parent.holdObject(strongRef);
                    }
                    // Attach child to parent
                    parent.attachChild(fileObj.getName());
                }
            }
        } catch (IOException e) {
            throw new FileSystemException(e);
        }
    }

    @Override
    protected FileObject createFile(AbstractFileName name) throws Exception {
        // This is only called for files which do not exist in the File System, eg direcories file
        return new XRFileObject(name, null, null) {
            @Override
            protected long doGetLastModifiedTime() throws Exception {
                return diretoryLastModilfied;
            }
        };
    }


    @Override
    protected void addCapabilities(Collection<Capability> caps) {
        caps.addAll(XRFileProvider.capabilities);
    }


}
