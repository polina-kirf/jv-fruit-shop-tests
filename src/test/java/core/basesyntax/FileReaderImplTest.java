package core.basesyntax;

import core.basesyntax.basesyntax.FileReader;
import core.basesyntax.basesyntax.FileReaderImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileReaderImplTest {
    private final FileReader reader = new FileReaderImpl();

    @Test
    void readFile_validFile_ok() throws IOException {
        Path tempFile = Files.createTempFile("test", ".csv");

        Files.write(tempFile, List.of(
                "type,fruit,quantity",
                "b,apple,10"
        ));

        List<String> result = reader.readFile(tempFile.toString());

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("b,apple,10", result.get(1));
    }

    @Test
    void readFile_fileNotFound_notOK() {
        Assertions.assertThrows(RuntimeException.class,
                () -> reader.readFile("non-existing-file.csv"));
    }

    @Test
    void readFile_emptyFile_ok() throws IOException {
        Path tempFile = Files.createTempFile("empty", ".csv");

        List<String> result = reader.readFile(tempFile.toString());

        Assertions.assertTrue(result.isEmpty());
    }
}
