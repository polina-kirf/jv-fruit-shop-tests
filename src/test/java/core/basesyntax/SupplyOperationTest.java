package core.basesyntax;

import core.basesyntax.basesyntax.OperationHandler;
import core.basesyntax.basesyntax.SupplyOperation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SupplyOperationTest {
    private final OperationHandler operation = new SupplyOperation();

    @Test
    void apply_newFruit_ok() {
        Map<String, Integer> storage = new HashMap<>();

        operation.apply("orange", 15, storage);
        operation.apply("apple", 20, storage);
        operation.apply("banana", 25, storage);

        Assertions.assertEquals(15, storage.get("orange"));
        Assertions.assertEquals(20, storage.get("apple"));
        Assertions.assertEquals(25, storage.get("banana"));
    }

    @Test
    void apply_increaseExistingFruit_ok() {
        Map<String, Integer> storage = new HashMap<>();
        storage.put("tangerine", 15);
        storage.put("grapes", 10);

        operation.apply("tangerine", 20, storage);
        operation.apply("grapes", 15, storage);

        Assertions.assertEquals(35, storage.get("tangerine"));
        Assertions.assertEquals(25, storage.get("grapes"));
    }

    @Test
    void apply_zeroQuantity_ok() {
        Map<String, Integer> storage = new HashMap<>();
        storage.put("mango", 10);

        operation.apply("mango", 0, storage);

        Assertions.assertEquals(10, storage.get("mango"));
    }
}
