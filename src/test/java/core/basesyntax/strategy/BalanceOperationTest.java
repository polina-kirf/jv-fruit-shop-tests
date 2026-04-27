package core.basesyntax.strategy;

import core.basesyntax.basesyntax.db.StorageImpl;
import core.basesyntax.basesyntax.strategy.BalanceOperation;
import core.basesyntax.basesyntax.strategy.OperationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BalanceOperationTest {
    private static final String FRUIT_APPLE = "apple";
    private static final String FRUIT_BANANA = "banana";
    private static final int QUANTITY_5 = 5;
    private static final int QUANTITY_15 = 15;
    private static final int QUANTITY_20 = 20;

    private static OperationHandler operation;

    @BeforeAll
    static void setUp() {
        operation = new BalanceOperation();
    }

    @Test
    void apply_setInitialBalance_Ok() {
        StorageImpl storage = new StorageImpl();

        operation.apply(FRUIT_APPLE, QUANTITY_20, storage);
        operation.apply(FRUIT_BANANA, QUANTITY_15, storage);

        Assertions.assertEquals(QUANTITY_20, storage.getQuantity("apple"));
        Assertions.assertEquals(QUANTITY_15, storage.getQuantity("banana"));
    }

    @Test
    void apply_overwritesExistingValue_ok() {
        StorageImpl storage = new StorageImpl();
        storage.setQuantity(FRUIT_APPLE, QUANTITY_5);

        operation.apply(FRUIT_APPLE, QUANTITY_20, storage);

        Assertions.assertEquals(QUANTITY_20, storage.getQuantity("apple"));
    }
}
