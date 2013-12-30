package nl.games.xrebirth.filesystem;

import org.apache.commons.vfs2.FileSystemException;

import java.io.FilterInputStream;
import java.io.IOException;

/**
* Created with IntelliJ IDEA.
* User: bram
* Date: 30-12-13
* Time: 4:33
* To change this template use File | Settings | File Templates.
*/
public class XRDataInputStream extends FilterInputStream {

    private byte[] oneBuf;
    private long entryStart;
    private long entryLength;
    private long currentOffset;

    XRDataInputStream(XRFileInfo fileInfo) throws FileSystemException {
        super(fileInfo.getDataFileObject().getContent().getInputStream());
        this.entryStart = fileInfo.getDataOffset();
        this.entryLength= fileInfo.getLength();
        this.oneBuf = new byte[1];
        this.currentOffset = 0;
        try {
            in.skip(entryStart);
        } catch (IOException e) {
            throw new FileSystemException(e);
        }
    }

    /**
     * Reads a byte from the current tar archive entry. This method simply calls
     * read( byte[], int, int ).
     *
     * @return The byte read, or -1 at EOF.
     * @throws java.io.IOException when an IO error causes operation to fail
     */
    @Override
    public int read() throws IOException {
        final int num = read(oneBuf, 0, 1);
        if (num == -1) {
            return num;
        } else {
            return oneBuf[0];
        }
    }

    @Override
    public int read(final byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }


    @Override
    public int available() throws IOException {
        return super.available();
    }

    @Override
    public int read(byte[] b, int offset, int count) throws IOException {
        int numToRead = count;
        int totalRead = 0;
        //we have read the whole file
        if (currentOffset >= entryLength) {
            return -1;
        }

        //we want totalRead read past the filesize
        if ((numToRead + currentOffset) > entryLength) {
            numToRead = (int) (entryLength - currentOffset);
        }

        totalRead = super.read(b, offset, numToRead);
        currentOffset += totalRead;
        return totalRead;

    }



    public boolean markSupported() {
        return false;
    }

}
