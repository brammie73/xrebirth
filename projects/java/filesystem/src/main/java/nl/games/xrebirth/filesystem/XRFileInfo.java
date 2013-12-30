package nl.games.xrebirth.filesystem;

import org.apache.commons.vfs2.FileObject;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 27-12-13
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
public class XRFileInfo {

    private String path;
    private long epoch;
    private long length;
    private String  hash;
    private FileObject catalogFileObject;
    private FileObject dataFileObject;
    private long dataOffset;

    public XRFileInfo(final FileObject catalogFileObject, final FileObject dataFileObject, final String line, long dataOffset) {
        this.catalogFileObject = catalogFileObject;
        this.dataFileObject = dataFileObject;
        this.dataOffset = dataOffset;
        parseLine(line);

    }

    //name, bytes, epoch, hash
    private void parseLine(String line) {
        int last = line.length();
        int first = line.lastIndexOf(' ', last);
        this.hash = line.substring(first +1, last);

        last = first;
        first = line.lastIndexOf(' ', last -1);
        String sEpoch = line.substring(first + 1, last);
        this.epoch = Long.parseLong(sEpoch);

        last = first;
        first = line.lastIndexOf(' ', last - 1);
        this.length = Long.parseLong(line.substring(first + 1, last));

        last = first;
        first = 0;
        this.path = line.substring(first, last).toLowerCase(); //make case insensitive

    }

    public String getPath() {
        return path;
    }

    public long getEpoch() {
        return epoch;
    }

    public long getLength() {
        return length;
    }

    public String getHash() {
        return hash;
    }

    public FileObject getCatalogFileObject() {
        return catalogFileObject;
    }

    public long getDataOffset() {
        return dataOffset;
    }
    public FileObject getDataFileObject() {
        return this.dataFileObject;
    }

    @Override
    public String toString() {
        return "XRFileInfo{" +
                "path='" + path + '\'' +
                ", epoch=" + epoch +
                ", length=" + length +
                ", hash='" + hash + '\'' +
                '}';
    }

}
