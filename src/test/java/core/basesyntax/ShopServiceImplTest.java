package core.basesyntax;

import core.basesyntax.basesyntax.BalanceOperation;
import core.basesyntax.basesyntax.FruitTransaction;
import core.basesyntax.basesyntax.OperationHandler;
import core.basesyntax.basesyntax.OperationHandlerProvider;
import core.basesyntax.basesyntax.OperationHandlerProviderImpl;
import core.basesyntax.basesyntax.PurchaseOperation;
import core.basesyntax.basesyntax.ReturnOperation;
import core.basesyntax.basesyntax.ShopService;
import core.basesyntax.basesyntax.ShopServiceImpl;
import core.basesyntax.basesyntax.SupplyOperation;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShopServiceImplTest {
    private ShopService createService() {
        Map<FruitTransaction.Operation, OperationHandler> handlers = Map.of(
                FruitTransaction.Operation.BALANCE, new BalanceOperation(),
                FruitTransaction.Operation.SUPPLY, new SupplyOperation(),
                FruitTransaction.Operation.PURCHASE, new PurchaseOperation(),
                FruitTransaction.Operation.RETURN, new ReturnOperation()
        );

        OperationHandlerProvider provider = new OperationHandlerProviderImpl(handlers);

        return new ShopServiceImpl(provider);
    }

    @Test
    void process_validScenario_ok() {
        ShopService service = createService();

        List<FruitTransaction> transactions = List.of(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 5),
                new FruitTransaction(FruitTransaction.Operation.SUPPLY, "apple", 15),
                new FruitTransaction(FruitTransaction.Operation.PURCHASE, "apple", 4),
                new FruitTransaction(FruitTransaction.Operation.RETURN, "apple", 2)
        );

        Map<String, Integer> result = service.process(transactions);

        Assertions.assertEquals(18, result.get("apple"));
    }

    @Test
    void process_multipleFruits_ok() {
        ShopService service = createService();

        List<FruitTransaction> transactions = List.of(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "orange", 10),
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 20),
                new FruitTransaction(FruitTransaction.Operation.PURCHASE, "banana", 10)
        );

        Map<String, Integer> result = service.process(transactions);

        Assertions.assertEquals(10, result.get("orange"));
        Assertions.assertEquals(10, result.get("banana"));
    }

    @Test
    void process_notEnoughStock_notOk() {
        ShopService service = createService();

        List<FruitTransaction> transactions = List.of(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, "grapes", 3),
                new FruitTransaction(FruitTransaction.Operation.PURCHASE, "grapes", 5)
        );

        Assertions.assertThrows(RuntimeException.class, () -> service.process(transactions));
    }

    @Test
    void process_nullTransactions_notOK() {
        ShopService service = createService();

        Assertions.assertThrows(RuntimeException.class, () -> service.process(null));
    }
}
