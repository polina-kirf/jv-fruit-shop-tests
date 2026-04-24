package core.basesyntax.basesyntax;

public interface OperationHandlerProvider {
    OperationHandler get(FruitTransaction.Operation operation);
}
