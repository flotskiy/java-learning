import java.util.ArrayList;
import java.util.List;

public class TransactionHolder {
    private volatile List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void showTransactions() {
        System.out.println("List of transactions:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
