package core.basesyntax;

import core.basesyntax.basesyntax.ReportGenerator;
import core.basesyntax.basesyntax.ReportGeneratorImpl;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    private final ReportGenerator generator = new ReportGeneratorImpl();

    @Test
    void generate_validData_ok() {
        Map<String, Integer> data = Map.of("apple", 10);

        List<String> result = generator.generate(data);

        Assertions.assertEquals("fruit,quantity", result.get(0));
        Assertions.assertTrue(result.contains("apple,10"));
    }

    @Test
    void generate_multipleFruits_ok() {
        Map<String, Integer> data = Map.of(
                "banana", 20,
                "orange", 10
        );

        List<String> result = generator.generate(data);

        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.contains("banana,20"));
        Assertions.assertTrue(result.contains("orange,10"));
    }

    @Test
    void generate_emptyData_onlyHeader() {
        Map<String, Integer> data = Map.of();

        List<String> result = generator.generate(data);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("fruit,quantity", result.get(0));
    }
}
