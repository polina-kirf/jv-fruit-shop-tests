package core.basesyntax.service;

import core.basesyntax.basesyntax.service.FileWriter;
import core.basesyntax.basesyntax.service.impl.FileWriterImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileWriterImplTest {
    private static FileWriter writer;

    @BeforeAll
    static void setUp() {
        writer = new FileWriterImpl();
    }

    @Test
    void writeFile_validData_ok() throws IOException {
        Path tempFile = Files.createTempFile("output", ".csv");

        List<String> data = List.of(
                "fruit,quantity",
                "banana,20"
        );
        writer.writeFile(data, tempFile.toString());

        List<String> result = Files.readAllLines(tempFile);

        Assertions.assertEquals(data, result);
    }

    @Test
    void writeFile_emptyData_ok() throws IOException {
        Path tempFile = Files.createTempFile("output_empty", ".csv");
        writer.writeFile(List.of(), tempFile.toString());
        List<String> result = Files.readAllLines(tempFile);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void writeFile_invalidPath_notOK() {
        List<String> data = List.of("test");

        Assertions.assertThrows(RuntimeException.class,
                () -> writer.writeFile(data, "/invalid_path/output.csv"));
    }
}
