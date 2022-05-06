package input;

import com.podzirey.iostreams.input.BufferedInputStream;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BufferedInputStreamTest {
    private File filePath;
    private final String text = "After the Second World War.";
    byte[] contentArray = text.getBytes();

    @BeforeEach
    void init() throws Exception {
        filePath = new File("Test.txt");
        filePath.createNewFile();
        OutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(contentArray);
        outputStream.close();
    }

    @AfterEach
    void delete() {
        filePath = new File("Test.txt");
        filePath.delete();
    }

    @DisplayName("test BufferedInputStream Read Correct")
    @Test
    public void testBufferedInputStreamReadCorrect() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedInputStream bufferedInputStream =
                     new BufferedInputStream(new FileInputStream(filePath))) {
            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                stringBuilder.append((char) data);
            }
        }

        assertEquals(text, new String(stringBuilder));
    }

    @DisplayName("test BufferedInputStream Read Bytes Correct")
    @Test
    public void testBufferedInputStreamReadBytesCorrect() throws IOException {
        byte[] b = new byte[text.length()];
        try (BufferedInputStream bufferedInputStream =
                     new BufferedInputStream(new FileInputStream(filePath))) {
            bufferedInputStream.read(b);
        }

        assertEquals(text, new String(b));
    }

    @DisplayName("test BufferedInputStream Read Bytes Correct Using Offset And Length")
    @Test
    public void testBufferedInputStreamReadBytesCorrectUsingOffsetAndLength() throws IOException {
        byte[] b = new byte[text.length()];
        try (BufferedInputStream bufferedInputStream =
                     new BufferedInputStream(new FileInputStream(filePath))) {
            bufferedInputStream.read(b, 0, 5);
        }

        assertEquals("After", new String(b, 0, 5));
    }

    @DisplayName("test BufferedInputStream Read Return -1 At The End Of Stream")
    @Test
    public void testBufferedInputStreamReadReturnMinusOneAtTheEndOfStream() throws IOException {
        int i;
        try (BufferedInputStream bufferedInputStream =
                     new BufferedInputStream(new FileInputStream(filePath))) {
            int data;
            for (i = 0; true; i++) {
                data = bufferedInputStream.read();
                if (data == -1) {
                    break;
                }
            }
        }
        assertEquals(27, i);
    }

    @DisplayName("test BufferedInputStream Close Make InputStream Null")
    @Test
    public void testBufferedInputStreamCloseMakeInputStreamNull() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
        bufferedInputStream.close();

        Assertions.assertThrows(IOException.class, () -> {
            bufferedInputStream.read();
        });
    }

    @DisplayName("test BufferedInputStream Read Bytes With Params Throws IndexOutOfBoundsException On Negative Off")
    @Test
    public void testBufferedInputStreamReadBytesWithParamsThrowsIndexOutOfBoundsExceptionOnNegativeOff() throws IOException {
        byte[] b = new byte[text.length()];
        BufferedInputStream bufferedInputStream =
                new BufferedInputStream(new FileInputStream(filePath));

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            bufferedInputStream.read(b, -1, 5);
        });
    }

    @DisplayName("test BufferedInputStream Read Bytes With Params Throws IndexOutOfBoundsException On Negative Len")
    @Test
    public void testBufferedInputStreamReadBytesWithParamsThrowsIndexOutOfBoundsExceptionOnNegativeLen() throws IOException {
        byte[] b = new byte[text.length()];
        BufferedInputStream bufferedInputStream =
                new BufferedInputStream(new FileInputStream(filePath));

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            bufferedInputStream.read(b, 0, -1);
        });
    }

    @DisplayName("test BufferedInputStream Read With Params Bytes Throws IndexOutOfBoundsException When Len Greater Than Array Length Minus Off")
    @Test
    public void testBufferedInputStreamReadBytesWithParamsThrowsIndexOutOfBoundsExceptionWhenLenGreaterThanArrayLengthMinusOff() throws IOException {
        byte[] b = new byte[1];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            bufferedInputStream.read(b, 0, 2);
        });
    }

    @DisplayName("test BufferedInputStream Read Bytes With Params Throws NullPointerException On Null Array")
    @Test
    public void testBufferedInputStreamReadBytesWithParamsThrowsNullPointerExceptionOnNullArray() throws IOException {
        byte[] b = null;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));

        Assertions.assertThrows(NullPointerException.class, () -> {
            bufferedInputStream.read(b, 0, 2);
        });
    }

    @DisplayName("test BufferedInputStream Read Bytes Throws NullPointerException On Null Array")
    @Test
    public void testBufferedInputStreamReadBytesThrowsNullPointerExceptionOnNullArray() throws IOException {

        byte[] b = null;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));

        Assertions.assertThrows(NullPointerException.class, () -> {
            bufferedInputStream.read(b);
        });
    }

    @DisplayName("test BufferedInputStream Not Null Before Close")
    @Test
    public void testBufferedInputStreamNotNullBeforeClose() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
        assertNotNull(bufferedInputStream.getTarget());
    }

    @DisplayName("test BufferedInputStream Is Null After Close")
    @Test
    public void testBufferedInputStreamIsNullAfterClose() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
        bufferedInputStream.close();
        assertNull(bufferedInputStream.getTarget());
    }

    @DisplayName("test BufferedInputStream Read Bytes Return Minus One On Empty Stream")
    @Test
    public void testBufferedInputStreamReadBytesReturnMinusOneOnEmptyStream() throws IOException {
        byte[] b = new byte[30];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream("".getBytes()));

        assertEquals(-1, bufferedInputStream.read(b, 0, 30));
    }

    @DisplayName("test BufferedInputStream Read Bytes Correct Using Offset And Length")
    @Test
    public void testBufferedInputStreamReadBytesCorrectUsingOffsetAnd() throws IOException {
        byte[] b = new byte[3];
        java.io.BufferedInputStream bufferedInputStream = new java.io.BufferedInputStream(new ByteArrayInputStream("ABC".getBytes()));
        bufferedInputStream.read(b, 0, 3);

        assertEquals("ABC",new String (b));
    }
}
