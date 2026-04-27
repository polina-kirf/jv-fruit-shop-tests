package core.basesyntax.service;

import core.basesyntax.basesyntax.service.FileReader;
import core.basesyntax.basesyntax.service.impl.FileReaderImpl;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileReaderImplTest {
    private static final int EXPECTED_SIZE = 2;

    private static FileReader reader;

    @BeforeAll
    static void setUp() {
        reader = new FileReaderImpl();
    }

    @Test
    void readFile_validFile_ok() {
        String data = "type,fruit,quantity\nb,apple,10";
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());

        List<String> result = reader.readFile(inputStream);

        Assertions.assertEquals(EXPECTED_SIZE, result.size());
        Assertions.assertEquals("type,fruit,quantity", result.get(0));
        Assertions.assertEquals("b,apple,10", result.get(1));
    }

    @Test
    void readFile_nullInput_throwsException() {
        Assertions.assertThrows(RuntimeException.class,
                () -> reader.readFile(null));
    }

    @Test
    void readFile_emptyFile_ok() {
        InputStream inputStream = new ByteArrayInputStream("".getBytes());

        List<String> result = reader.readFile(inputStream);

        Assertions.assertTrue(result.isEmpty());
    }
}
