package core.basesyntax;

import core.basesyntax.basesyntax.FruitTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FruitTransactionTest {

    @Test
    void constructor_setFieldsCorrectly_ok() {
        FruitTransaction tx = new FruitTransaction(
                FruitTransaction.Operation.BALANCE, "apple", 10);

        Assertions.assertEquals(FruitTransaction.Operation.BALANCE, tx.getOperation());
        Assertions.assertEquals("apple", tx.getFruit());
        Assertions.assertEquals(10, tx.getQuantity());
    }

    @Test
    void fromCode_validCode_ok() {
        FruitTransaction.Operation op = FruitTransaction.Operation.fromCode("s");

        Assertions.assertEquals(FruitTransaction.Operation.SUPPLY, op);
    }

    @Test
    void fromCode_invalidCode_notOk() {
        Assertions.assertThrows(RuntimeException.class,
                () -> FruitTransaction.Operation.fromCode("x"));
    }
}
