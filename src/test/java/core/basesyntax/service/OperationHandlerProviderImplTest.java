package core.basesyntax.service;

import core.basesyntax.basesyntax.model.FruitTransaction;
import core.basesyntax.basesyntax.service.OperationHandlerProvider;
import core.basesyntax.basesyntax.service.impl.OperationHandlerProviderImpl;
import core.basesyntax.basesyntax.strategy.BalanceOperation;
import core.basesyntax.basesyntax.strategy.OperationHandler;
import core.basesyntax.basesyntax.strategy.PurchaseOperation;
import core.basesyntax.basesyntax.strategy.ReturnOperation;
import core.basesyntax.basesyntax.strategy.SupplyOperation;
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
