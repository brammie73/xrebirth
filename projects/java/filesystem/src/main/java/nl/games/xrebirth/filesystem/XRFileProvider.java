package nl.games.xrebirth.filesystem;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.provider.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 3:09
 * To change this template use File | Settings | File Templates.
 */
public class XRFileProvider extends AbstractLayeredFileProvider implements FileProvider {

    protected static final Collection<Capability> capabilities =
            Collections.unmodifiableCollection(Arrays.asList(Capability.READ_CONTENT, Capability.LIST_CHILDREN));

    public XRFileProvider() {
        super();
    }

    @Override
    protected FileSystem doCreateFileSystem(String scheme, FileObject file, FileSystemOptions fileSystemOptions) throws FileSystemException {
        final AbstractFileName rootName = new LayeredFileName(scheme, file.getName(), FileName.ROOT_PATH, FileType.FOLDER);
        return new XRFileSystem(rootName, file, fileSystemOptions);
    }

    @Override
    public Collection<Capability> getCapabilities() {
        return capabilities;
    }
}
