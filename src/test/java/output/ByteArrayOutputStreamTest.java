package output;


import com.podzirey.iostreams.output.ByteArrayOutputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteArrayOutputStreamTest {

    @DisplayName("test ByteArrayInputStream Write Byte Correct")
    @Test
    public void testByteArrayOutputStreamWriteByteCorrect() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byteArrayOutputStream.write(49);

        assertEquals("1", new String(byteArrayOutputStream.toByteArray()));
    }

    @DisplayName("test ByteArrayInputStream Size Work Correct")
    @Test
    public void testByteArrayOutputStreamSizeWorkCorrect() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        assertEquals(0, byteArrayOutputStream.size());

        byteArrayOutputStream.write(49);
        assertEquals(1, byteArrayOutputStream.size());
    }

    @DisplayName("test ByteArrayInputStream Write Byte Correct")
    @Test
    public void testByteArrayOutputStreamWriteByte() throws IOException {
        byte[] array = "Hello".getBytes();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byteArrayOutputStream.write(array);

        assertEquals(new String(array), byteArrayOutputStream.toString());
    }

    @DisplayName("test ByteArrayInputStream Increase Buffer And Write Correct")
    @Test
    public void testByteArrayInputStreamIncreaseBufferAndWriteCorrect() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] array = "123456789 123456789 123456789 123".getBytes();
        byteArrayOutputStream.write(array);

        assertEquals(64, byteArrayOutputStream.getCapacity());
        assertEquals(new String(array), byteArrayOutputStream.toString());
    }

    @DisplayName("test ByteArrayInputStream Methods Work And Don't Throw Exceptions After Close")
    @Test
    public void testByteArrayInputStreamMethodsWorkAndDontThrowExceptionsAfterClose() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] array = "123456789 123456789 123456789 123".getBytes();
        byteArrayOutputStream.write(array);
        byteArrayOutputStream.close();

        assertEquals(64, byteArrayOutputStream.getCapacity());
        assertEquals(new String(array), byteArrayOutputStream.toString());
    }

    @DisplayName("test ByteArrayInputStream Methods Work And Don't Throw Exceptions After")
    @Test
    public void testByteArrayOutputStream() throws IOException {
        java.io.ByteArrayOutputStream byteArrayOutputStream = new java.io.ByteArrayOutputStream();

        byte[] array = "123456789 123456789 123456789 123".getBytes();
        byteArrayOutputStream.write(array);
        byteArrayOutputStream.close();

        assertEquals(new String(array), byteArrayOutputStream.toString());
    }
}
