import java.util.ArrayList;

public class TransactionHolder {
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
