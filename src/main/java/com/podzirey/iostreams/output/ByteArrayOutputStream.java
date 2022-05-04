package com.podzirey.iostreams.output;

import com.google.common.annotations.VisibleForTesting;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class ByteArrayOutputStream extends OutputStream {

    private static final int DEFAULT_CAPACITY = 32;
    private static final int INCREASE_FACTOR = 2;

    private byte[] buffer;
    private int capacity;
    private int position = 0;

    public ByteArrayOutputStream() {
        this(DEFAULT_CAPACITY);
    }

    public ByteArrayOutputStream(int size) {
        this.capacity = size;
        buffer = new byte[size];
    }

    @Override
    public void write(int b) {
        if (position == capacity) {
            increaseCapacity();
        }
        buffer[position] = (byte) b;
        position++;
    }

    private void increaseCapacity() {
        byte[] newBuffer = new byte[capacity * INCREASE_FACTOR];
        System.arraycopy(buffer, 0, newBuffer, 0, capacity);
        capacity *= INCREASE_FACTOR;
        buffer = newBuffer;
        newBuffer = null;
    }

    @Override
    public void write(byte[] b) {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) {
        if (b == null) {
            throw new NullPointerException("Param array can't be null");
        }
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException("Params off or len are incorrect: off < 0 || len < 0 || len > array.length - off");
        }

        if (len > capacity - size()) {
            increaseCapacity();
        }

        System.arraycopy(b, off, buffer, position, len);
        position += len;
    }

    @Override
    public void close() throws IOException {
        super.close();
        //Closing a ByteArrayOutputStream has no effect.
    }

    @Override
    public String toString() {
        return new String(buffer, 0, position);

    }

    public int size() {
        return position;
    }

    @VisibleForTesting
    public int getCapacity() {
        return capacity;
    }

    public byte[] toByteArray() {
        return Arrays.copyOf(buffer, position);
    }
}
