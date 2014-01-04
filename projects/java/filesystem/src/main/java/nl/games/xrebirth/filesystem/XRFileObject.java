package nl.games.xrebirth.filesystem;

import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.provider.AbstractFileName;
import org.apache.commons.vfs2.provider.AbstractFileObject;

import java.io.InputStream;
import java.util.HashSet;

/**
 * User: bram
 * Date: 27-12-13
 * Time: 3:28
 */
public class XRFileObject extends AbstractFileObject implements FileObject {

    private final HashSet<String> children = new HashSet<>();
    private final XRFileInfo fileInfo;


    protected XRFileObject(AbstractFileName name, XRFileSystem fs, XRFileInfo fileInfo) {
        super(name, fs);
        this.fileInfo = fileInfo;
    }

    /**
     * Attaches a child.
     *
     * @param childName The name of the child.
     */
    public void attachChild(FileName childName) {
        children.add(childName.getBaseName());
    }


    @Override
    protected FileType doGetType() throws Exception {
        if (fileInfo == null) {
            return FileType.FOLDER;
        } else {
            return FileType.FILE;
        }
    }

    /**
     * Lists the children of the file.
     */
    @Override
    protected String[] doListChildren() {
        try {
            if (!getType().hasChildren()) {
                return null;
            }
        } catch (FileSystemException e) {
            // should not happen as the type has already been cached.
            throw new XRFileSystemException(e);
        }

        return children.toArray(new String[children.size()]);
    }

    @Override
    protected long doGetContentSize() throws Exception {
        if (fileInfo == null) {
            return -1L;
        } else {
            return fileInfo.getLength();
        }
    }

    @Override
    protected InputStream doGetInputStream() throws Exception {
        return new XRDataInputStream(fileInfo);
    }

    @Override
    protected long doGetLastModifiedTime() throws Exception {
        return fileInfo.getEpoch();
    }

    @Override
    protected boolean doIsWriteable() throws Exception {
        return false;
    }

    @Override
    public FileObject getChild(String name) throws FileSystemException {
        return super.getChild(name.toLowerCase());
    }
}
