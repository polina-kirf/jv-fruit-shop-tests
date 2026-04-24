package core.basesyntax;

import core.basesyntax.basesyntax.OperationHandler;
import core.basesyntax.basesyntax.ReturnOperation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReturnOperationTest {
    private final OperationHandler operation = new ReturnOperation();

    @Test
    void apply_returnFruit_ok() {
        Map<String, Integer> storage = new HashMap<>();
        storage.put("orange", 15);
        storage.put("banana", 25);
        storage.put("apple", 20);
        storage.put("grapes", 7);

        operation.apply("orange", 5, storage);
        operation.apply("grapes", 3, storage);

        Assertions.assertEquals(20, storage.get("orange"));
        Assertions.assertEquals(25, storage.get("banana"));
        Assertions.assertEquals(20, storage.get("apple"));
        Assertions.assertEquals(10, storage.get("grapes"));
    }
}
