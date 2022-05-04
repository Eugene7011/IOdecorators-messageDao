package com.podzirey.iostreams.input;

import java.io.IOException;
import java.io.InputStream;

public class ByteArrayInputStream extends InputStream {
    private static final int DEFAULT_OFFSET = 0;

    private final int length;
    private final int offset;
    private int nextByte;
    private byte[] array;

    public ByteArrayInputStream(byte[] b) {
        this(b, DEFAULT_OFFSET, b.length);
    }

    public ByteArrayInputStream(byte[] b, int offset, int length) {
        this.array = b;
        this.offset = offset;
        this.length = length;
        nextByte = offset;
    }

    @Override
    public int read() {
        if (nextByte - offset == length) {
            return -1;
        }
        int result = array[nextByte];
        nextByte++;
        return result;
    }

    @Override
    public int read(byte[] buffer) {
        return read(buffer, 0, buffer.length);
    }

    @Override
    public int read(byte[] buffer, int off, int len) {
        if (buffer == null) {
            throw new NullPointerException("Param array can't be null");
        }
        if (off < 0 || len < 0 || len > buffer.length - off) {
            throw new IndexOutOfBoundsException("Params off or len are incorrect: off < 0 || len < 0 || len > array.length - off");
        }

        if (read() == -1) {
            return -1;
        }

        int bytesCount = length;
        while (read() != -1) {
            if (nextByte - offset > len) {
                bytesCount = len;
                System.arraycopy(array, offset, buffer, off, bytesCount);
                return bytesCount;
            }
        }
        System.arraycopy(array, offset, buffer, off, bytesCount);

        return bytesCount;
    }

    @Override
    public void close() throws IOException {
        super.close();
        //Closing a ByteArrayInputStream has no effect.
    }
}
