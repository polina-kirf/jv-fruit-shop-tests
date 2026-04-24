package core.basesyntax;

import core.basesyntax.basesyntax.OperationHandler;
import core.basesyntax.basesyntax.PurchaseOperation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PurchaseOperationTest {
    private final OperationHandler operation = new PurchaseOperation();

    @Test
    void apply_validPurchase_ok() {
        Map<String, Integer> storage = new HashMap<>();
        storage.put("apple", 10);
        storage.put("banana", 20);
        storage.put("orange", 15);

        operation.apply("apple", 6, storage);
        operation.apply("orange", 5, storage);

        Assertions.assertEquals(4, storage.get("apple"));
        Assertions.assertEquals(20, storage.get("banana"));
        Assertions.assertEquals(10, storage.get("orange"));
    }

    @Test
    void apply_exactStock_ok() {
        Map<String, Integer> storage = new HashMap<>();
        storage.put("banana", 5);

        operation.apply("banana", 5, storage);

        Assertions.assertEquals(0, storage.get("banana"));
    }

    @Test
    void apply_notEnoughStock_notOK() {
        Map<String, Integer> storage = new HashMap<>();
        storage.put("grapes", 3);

        Assertions.assertThrows(RuntimeException.class,
                () -> operation.apply("grapes", 5, storage));
    }

    @Test
    void apply_notFruit_notOk() {
        Map<String, Integer> storage = new HashMap<>();
        storage.put("apple", 20);
        storage.put("banana", 30);

        Assertions.assertThrows(RuntimeException.class,
                () -> operation.apply("tomato", 5, storage));
    }
}
