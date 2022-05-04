package output;

import com.podzirey.iostreams.output.BufferedOutputStream;
import com.podzirey.iostreams.output.ByteArrayOutputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.*;

public class BufferedOutputStreamTest {
    private byte[] array = new byte[2];
    private final String text = "After the Second World War.";
    byte[] contentArray = text.getBytes();

    @DisplayName("test BufferedOutputStream Write Int Correct")
    @Test
    public void testBufferedOutputStreamWriteIntCorrect() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream outputStream = new BufferedOutputStream(byteArrayOutputStream);
        outputStream.write(1);
        outputStream.write(2);
        outputStream.write(3);
        outputStream.close();

        assertEquals("[1, 2, 3]", Arrays.toString(byteArrayOutputStream.toByteArray()));

    }

    @DisplayName("test BufferedOutputStream Write Byte Array Correct")
    @Test
    public void testBufferedOutputStreamWriteByteArrayCorrect() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream outputStream = new BufferedOutputStream(byteArrayOutputStream);
        byte[] b = {1, 2, 3};
        outputStream.write(b);
        outputStream.close();

        assertEquals("[1, 2, 3]", Arrays.toString(byteArrayOutputStream.toByteArray()));
    }

    @DisplayName("test BufferedOutputStream Write Byte Array With Offset And Length Correct")
    @Test
    public void testBufferedOutputStreamWriteByteArrayWithOffsetAndLengthCorrect() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream outputStream = new BufferedOutputStream(byteArrayOutputStream);
        byte[] b = {1, 2, 3, 4, 5};
        outputStream.write(b, 2, 3);
        outputStream.close();

        assertEquals("[3, 4, 5]", Arrays.toString(byteArrayOutputStream.toByteArray()));
    }

    //тест работает неправильно. В документации метод write (byte[] b, int off, int len) возвращает только IOException.!!!
    @DisplayName("test BufferedOutputStream Throws IllegalArgumentException When Parameter Len Is Too Big")
    @Test
    public void testBufferedOutputStreamThrowsIllegalArgumentExceptionWhenParameterLenIsTooBig() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream outputStream = new BufferedOutputStream(byteArrayOutputStream);
        byte[] b = {1, 2, 3, 4, 5};

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            outputStream.write(b, 2, 4);
        });
    }

}
