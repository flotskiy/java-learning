import java.util.concurrent.atomic.AtomicLong;

public class BankAccount implements Account {
    private static volatile AtomicLong accountCounter = new AtomicLong(Long.MAX_VALUE);
    private TransactionHolder transactionHolder = new TransactionHolder();

    private final long accountNumber;
    private double amountOfMoney;
    private final AccountType accountType;
    private final String accountOwner;

    public BankAccount(double amountOfMoney, AccountType accountType, String accountOwner) {
        accountNumber = getAccountCounter();
        this.amountOfMoney = amountOfMoney;
        this.accountType = accountType;
        this.accountOwner = accountOwner;
    }

    public BankAccount(AccountType accountType, String accountOwner) {
        accountNumber = getAccountCounter();
        amountOfMoney = Math.random() * Integer.MAX_VALUE;
        this.accountType = accountType;
        this.accountOwner = accountOwner;
    }

    public static void getNextUniqueAccountNumber() {
        accountCounter.decrementAndGet();
    }

    public static long getAccountCounter() {
        getNextUniqueAccountNumber();
        return accountCounter.get();
    }

    public void put(double amountToPut) {
        if (amountToPut > 0) {
            amountOfMoney += amountToPut;
        }
    }

    public boolean take(double amountToTake) {
        if (amountToTake <= amountOfMoney && amountToTake > 0) {
            amountOfMoney -= amountToTake;
            return true;
        }
        return false;
    }

    // TODO - make synchronized
    public boolean send(BankAccount receiver, double amount) {
        Transaction transaction = new Transaction(this, receiver, amount);
        transactionHolder.addTransaction(transaction);
        receiver.getTransactionHolder().addTransaction(transaction);
        if (take(amount)) {
            receiver.put(amount);
            transaction.fixSuccess();
            return true;
        }
        return false;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public TransactionHolder getTransactionHolder() {
        return transactionHolder;
    }
}
