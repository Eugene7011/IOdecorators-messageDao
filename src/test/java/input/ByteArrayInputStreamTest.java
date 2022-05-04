package input;

import com.podzirey.iostreams.input.ByteArrayInputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteArrayInputStreamTest {

    @DisplayName("test ByteArrayInputStream Read Without Params Correct")
    @Test
    public void testByteArrayInputStreamReadWithoutParamsCorrect() throws IOException {
        String content = "IO test";
        ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(content.getBytes());
        assertEquals('I', (char) byteArrayInputStream.read());
        assertEquals('O', (char) byteArrayInputStream.read());
        assertEquals(' ', (char) byteArrayInputStream.read());
        assertEquals('t', (char) byteArrayInputStream.read());
        assertEquals('e', (char) byteArrayInputStream.read());
        assertEquals('s', (char) byteArrayInputStream.read());
        assertEquals('t', (char) byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());
    }

    @DisplayName("test ByteArrayInputStream With Params Read Correct")
    @Test
    public void testByteArrayInputStreamWithParamsReadCorrect() throws IOException {
        String content = "IO test";
        ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(content.getBytes(), 3, 4);

        assertEquals('t', (char) byteArrayInputStream.read());
        assertEquals('e', (char) byteArrayInputStream.read());
        assertEquals('s', (char) byteArrayInputStream.read());
        assertEquals('t', (char) byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());
    }

    @DisplayName("test ByteArrayInputStream Read With Params Correct")
    @Test
    public void testByteArrayInputStreamReadWithParams() throws IOException {
        String content = "IO test";

        byte[] buffer = new byte[7];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
        byteArrayInputStream.read(buffer);

        assertEquals(content, new String(buffer));
    }

    @DisplayName("test ByteArrayInputStream Read With Params Return Minus One When InputStream Is Empty")
    @Test
    public void testByteArrayInputStreamReadReturnMinusOneWhenInputStreamIsEmpty() throws IOException {
        String content = "";

        byte[] buffer = new byte[5];
        java.io.ByteArrayInputStream byteArrayInputStream = new java.io.ByteArrayInputStream(content.getBytes());

        assertEquals(-1, byteArrayInputStream.read(buffer));
    }

    @DisplayName("test ByteArrayInputStream Read With Params Return Len If Dest Buffer Space Is Lower Than Source")
    @Test
    public void testByteArrayInputStreamReadReturnLenIfDestBufferSpaceIsLowerThanSource() throws IOException {
        String content = "IO test";

        byte[] buffer = new byte[5];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());

        assertEquals(5, byteArrayInputStream.read(buffer, 0, 5));
    }

    @DisplayName("test ByteArrayInputStream Read With Params Return Source Length If Dest Buffer Space Is Greater Than Source")
    @Test
    public void testByteArrayInputStreamReadReturnSourceLengthIfDestBufferSpaceIsGreaterThanSource() throws IOException {
        String content = "IO test";

        byte[] buffer = new byte[10];
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());

        assertEquals(7, byteArrayInputStream.read(buffer, 0, 10));
    }

    @DisplayName("test ByteArrayInputStream Read With Params Throws NullPointerException on Null Array")
    @Test
    public void testByteArrayInputStreamReadWithParamsThrowsNullPointerExceptionOnNullArray() throws IOException {

        String content = "IO test";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());

        Assertions.assertThrows(NullPointerException.class, () -> {
            byteArrayInputStream.read(null, 0, 10);
        });
    }

    @DisplayName("test ByteArrayInputStream Read With Params Throws IndexOutOfBoundsException On Negative Off")
    @Test
    public void testByteArrayInputStreamReadWithParamsThrowsIndexOutOfBoundsExceptionOnNegativeOff() throws IOException {
        String content = "IO test";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());

        byte[] buffer = new byte[10];

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            byteArrayInputStream.read(buffer, -1, 10);
        });
    }

    @DisplayName("test ByteArrayInputStream Read With Params Throws IndexOutOfBoundsException On Negative Len")
    @Test
    public void testByteArrayInputStreamReadWithParamsThrowsIndexOutOfBoundsExceptionOnNegativeLen() throws IOException {
        String content = "IO test";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());

        byte[] buffer = new byte[10];

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            byteArrayInputStream.read(buffer, 0, -1);
        });
    }

    @DisplayName("test ByteArrayInputStream Read With Params Throws IndexOutOfBoundsException When Len Greater Than Array Length Minus Off")
    @Test
    public void testByteArrayInputStreamReadWithParamsThrowsIndexOutOfBoundsExceptionWhenLenGreaterThanArrayLengthMinusOff() throws IOException {
        String content = "IO test";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());

        byte[] buffer = new byte[10];

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            byteArrayInputStream.read(buffer, 0, 11);
        });
    }


    @DisplayName("test ByteArrayInputStream Read With Params Throws IndexOutOfBoundsException On Negative Off")
    @Test
    public void testByteArrayInputStreamReadWithParamsThrowsIndexOutOfBoundsExceptionOnNegative() throws IOException {
        String content = "IO test";
        java.io.ByteArrayInputStream byteArrayInputStream = new java.io.ByteArrayInputStream(content.getBytes());

        byte[] buffer = new byte[10];

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            byteArrayInputStream.read(buffer, -1, 10);
        });
    }
}
