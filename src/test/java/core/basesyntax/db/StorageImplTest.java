package core.basesyntax.db;

import core.basesyntax.basesyntax.db.StorageImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StorageImplTest {
    private static final String FRUIT_APPLE = "apple";
    private static final String FRUIT_BANANA = "banana";
    private static final int QUANTITY_0 = 0;
    private static final int QUANTITY_10 = 10;
    private static final int QUANTITY_15 = 15;
    private static final int QUANTITY_20 = 20;

    @Test
    void getQuantity_defaultZero_ok() {
        StorageImpl storage = new StorageImpl();
        Assertions.assertEquals(QUANTITY_0, storage.getQuantity(FRUIT_APPLE));
    }

    @Test
    void setAndGetQuantity_ok() {
        StorageImpl storage = new StorageImpl();
        storage.setQuantity(FRUIT_APPLE, QUANTITY_15);
        Assertions.assertEquals(QUANTITY_15, storage.getQuantity(FRUIT_APPLE));
    }

    @Test
    void setQuantity_overwriteQuantity_ok() {
        StorageImpl storage = new StorageImpl();
        storage.setQuantity(FRUIT_BANANA, QUANTITY_10);
        storage.setQuantity(FRUIT_BANANA, QUANTITY_20);

        Assertions.assertEquals(QUANTITY_20, storage.getQuantity(FRUIT_BANANA));
    }
}
