package core.basesyntax;

import core.basesyntax.basesyntax.BalanceOperation;
import core.basesyntax.basesyntax.OperationHandler;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BalanceOperationTest {
    private final OperationHandler operation = new BalanceOperation();

    @Test
    void apply_setInitialBalance_Ok() {
        Map<String, Integer> storage = new HashMap<>();

        operation.apply("apple", 20, storage);
        operation.apply("banana", 15, storage);

        Assertions.assertEquals(20, storage.get("apple"));
        Assertions.assertEquals(15, storage.get("banana"));
    }

    @Test
    void apply_overWritesExistingValue_ok() {
        Map<String, Integer> storage = new HashMap<>();
        storage.put("apple", 5);

        operation.apply("apple", 20, storage);

        Assertions.assertEquals(20, storage.get("apple"));
    }
}
