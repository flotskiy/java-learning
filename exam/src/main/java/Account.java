import java.util.concurrent.locks.Lock;

public interface Account {
    void put(double amountToPut);

    boolean take(double amountToTake);

    boolean send(Account receiver);

    boolean send(Account receiver, double amount);

    TransactionHolder getTransactionHolder();

    Lock getLock();

    double getAmountOfMoney();

    String getAccountOwner();
}
