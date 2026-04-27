package core.basesyntax.basesyntax.service.impl;

import core.basesyntax.basesyntax.service.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterImpl implements FileWriter {
    @Override
    public void writeFile(List<String> lines, String path) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(path))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing file", e);
        }
    }
}
