package core.basesyntax;

import core.basesyntax.basesyntax.BalanceOperation;
import core.basesyntax.basesyntax.FruitTransaction;
import core.basesyntax.basesyntax.OperationHandler;
import core.basesyntax.basesyntax.OperationHandlerProvider;
import core.basesyntax.basesyntax.OperationHandlerProviderImpl;
import core.basesyntax.basesyntax.PurchaseOperation;
import core.basesyntax.basesyntax.ReturnOperation;
import core.basesyntax.basesyntax.SupplyOperation;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperationHandlerProviderImplTest {

    Map<FruitTransaction.Operation, OperationHandler> createHandlers() {
        return Map.of(
                FruitTransaction.Operation.BALANCE, new BalanceOperation(),
                FruitTransaction.Operation.SUPPLY, new SupplyOperation(),
                FruitTransaction.Operation.PURCHASE, new PurchaseOperation(),
                FruitTransaction.Operation.RETURN, new ReturnOperation()
        );
    }

    @Test
    void get_validOperation_ok() {
        OperationHandlerProvider provider =
                new OperationHandlerProviderImpl(createHandlers());

        OperationHandler handler = provider.get(FruitTransaction.Operation.SUPPLY);

        Assertions.assertNotNull(handler);
        Assertions.assertInstanceOf(SupplyOperation.class, handler);
    }

    @Test
    void get_purchaseOperation_ok() {
        OperationHandlerProvider provider =
                new OperationHandlerProviderImpl(createHandlers());

        OperationHandler handler = provider.get(FruitTransaction.Operation.PURCHASE);

        Assertions.assertInstanceOf(PurchaseOperation.class, handler);
    }

    @Test
    void get_nullOperation_notOk() {
        OperationHandlerProvider provider =
                new OperationHandlerProviderImpl(createHandlers());

        Assertions.assertThrows(RuntimeException.class, () -> provider.get(null));
    }
}
