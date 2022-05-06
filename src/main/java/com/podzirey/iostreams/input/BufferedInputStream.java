package com.podzirey.iostreams.input;

import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStream extends InputStream {
    private static final int DEFAULT_BUFFER_CAPACITY = 1024 * 8;

    private int bufferSize;
    private int bufferNextByte = 0;
    private byte[] buffer;
    private InputStream target;

    public BufferedInputStream(InputStream target) throws IOException {
        this(target, DEFAULT_BUFFER_CAPACITY);
    }

    public BufferedInputStream(InputStream target, int size) throws IOException {
        if (size <= 0) {
            throw new IllegalArgumentException("Buffer size <= 0");
        }
        this.target = target;
        this.bufferSize = size;
        fillBuffer();
    }

    @Override
    public int read() throws IOException {
        isOpen();
        if (bufferNextByte == DEFAULT_BUFFER_CAPACITY) {
            fillBuffer();
        }

        if (buffer[bufferNextByte] != -1) {
            return buffer[bufferNextByte++];
        } else {
            return -1;
        }
    }

    private void isOpen() throws IOException {
        if (target == null) {
            throw new IOException("Stream is closed.");
        }
    }

    private void fillBuffer() throws IOException {
        buffer = new byte[bufferSize];
        int i = 0;
        while (i < bufferSize) {
            buffer[i] = (byte) target.read();
            i++;
        }
        bufferNextByte = 0;
    }

    @Override
    public int read(byte[] b) throws IOException {
        if (b == null) {
            throw new NullPointerException("byte[] array is null");
        }
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        isOpen();
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException("Params off or len are incorrect: off < 0 || len < 0 || len > array.length - off");
        }
        if (b == null) {
            throw new NullPointerException("byte[] array is null");
        }

        if (buffer[bufferNextByte] == -1) {
            return -1;
        } else {
            int restData = buffer.length - bufferNextByte;
            int readBytes;
            if (restData < len) {
                if (len > buffer.length) {
                    readBytes = target.read(b, off, len);
                    return bufferNextByte += readBytes;
                }
            } else {
                System.arraycopy(buffer, bufferNextByte, b, off, len);
                return bufferNextByte += len;
            }
        }
        return bufferNextByte += len;
    }

    @Override
    public void close() throws IOException {
        target.close();
        target = null;
        bufferNextByte = 0;
    }

    public InputStream getTarget() {
        return target;
    }
}
