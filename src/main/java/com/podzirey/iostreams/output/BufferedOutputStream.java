package com.podzirey.iostreams.output;

import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends OutputStream {
    private static final int DEFAULT_BUFFER_CAPACITY = 1024 * 8;

    private final int bufferSize;
    private int position = 0;
    private final byte[] buffer;
    private OutputStream target;

    public BufferedOutputStream(OutputStream target) {
        this(target, DEFAULT_BUFFER_CAPACITY);
    }

    public BufferedOutputStream(OutputStream target, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Buffer size <= 0");
        }
        this.target = target;
        this.bufferSize = size;
        buffer = new byte[bufferSize];
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        isOpen();
        if (len > b.length - off) {
            throw new ArrayIndexOutOfBoundsException("Error! Len can't be greater than b.length - off");
        }

        int freeSpace = buffer.length - position;
        if (freeSpace < len) {
            flush();
            if (len > buffer.length) {
                target.write(b, off, len);
            }
        } else {
            System.arraycopy(b, off, buffer, position, len);
            position += len;
        }
    }

    private void isOpen() {
        if (target == null) {
            throw new IllegalArgumentException("Stream is closed.");
        }
    }

    @Override
    public void flush() throws IOException {
        target.write(buffer, 0, position);
        position = 0;
    }

    @Override
    public void close() throws IOException {
        flush();
        target.close();
        target = null;
        position = 0;
    }

    @Override
    public void write(int b) throws IOException {
        buffer[position] = (byte) b;
        position++;
        if (position == bufferSize) {
            flush();
        }
    }
}
