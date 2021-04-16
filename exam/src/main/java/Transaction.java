import java.util.concurrent.atomic.AtomicLong;

public class Transaction {
    private static volatile AtomicLong idCounter = new AtomicLong(0);

    private final long id;
    private final Account from;
    private final Account to;
    private final double amount;
    private boolean isSuccessfully = false;

    public Transaction(Account from, Account to, double amount) {
        id = getIdCounter();
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public static void incrementId() {
        idCounter.incrementAndGet();
    }

    public static long getIdCounter() {
        incrementId();
        return idCounter.get();
    }

    public void fixSuccess() {
        isSuccessfully = true;
    }
}
