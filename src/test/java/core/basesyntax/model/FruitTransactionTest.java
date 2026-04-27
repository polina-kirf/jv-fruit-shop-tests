package core.basesyntax.model;

import core.basesyntax.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FruitTransactionTest {
    private static final String FRUIT_APPLE = "apple";
    private static final int QUANTITY = 10;
    private static final String CODE_S = "s";
    private static final String WRONG_CODE_X = "x";

    @Test
    void constructor_validInput_ok() {
        FruitTransaction tx = new FruitTransaction(
                FruitTransaction.Operation.BALANCE, FRUIT_APPLE, QUANTITY);

        Assertions.assertEquals(FruitTransaction.Operation.BALANCE, tx.getOperation());
        Assertions.assertEquals(FRUIT_APPLE, tx.getFruit());
        Assertions.assertEquals(QUANTITY, tx.getQuantity());
    }

    @Test
    void fromCode_validCode_returnsOperation() {
        FruitTransaction.Operation op = FruitTransaction.Operation.fromCode(CODE_S);
        Assertions.assertEquals(FruitTransaction.Operation.SUPPLY, op);
    }

    @Test
    void fromCode_invalidCode_throwsException() {
        Assertions.assertThrows(RuntimeException.class,
                () -> FruitTransaction.Operation.fromCode(WRONG_CODE_X));
    }
}
