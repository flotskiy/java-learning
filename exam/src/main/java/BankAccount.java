import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount implements Account {
    private static volatile AtomicLong accountCounter = new AtomicLong(Long.MAX_VALUE);
    private static final Random RANDOM = new Random();

    private final TransactionHolder transactionHolder;
    private final AccountHolder accountHolder;
    private final long accountNumber;
    private volatile double amountOfMoney;
    private final AccountType accountType;
    private final String accountOwner;

    Lock lock = new ReentrantLock();

    public BankAccount(AccountHolder accountHolder, double amountOfMoney, AccountType accountType, String accountOwner) {
        this.transactionHolder = new TransactionHolder();
        this.accountHolder = accountHolder;
        accountNumber = getAccountCounter();
        this.amountOfMoney = amountOfMoney;
        this.accountType = accountType;
        this.accountOwner = accountOwner;
    }

    public BankAccount(AccountHolder accountHolder) {
        this.transactionHolder = new TransactionHolder();
        this.accountHolder = accountHolder;
        accountNumber = getAccountCounter();
        amountOfMoney = Math.random() * Integer.MAX_VALUE;
        accountType = AccountType.values()[RANDOM.nextInt(AccountType.values().length)];
        accountOwner = ClientsHolder.getNAMES()[RANDOM.nextInt(ClientsHolder.getNAMES().length)];
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

    public boolean send(Account receiver) {
        int amountToSend = RANDOM.nextInt(200_000);
        return send(receiver, amountToSend);
    }

    public boolean send(Account receiver, double amount) {
        try {
            if (acquireLocks(this.lock, receiver.getLock())) {
                Transaction transaction = new Transaction(this, receiver, amount);
                transactionHolder.addTransaction(transaction);
                receiver.getTransactionHolder().addTransaction(transaction);
                if (take(amount)) {
                    receiver.put(amount);
                    transaction.fixSuccess();
                    releaseLocks(this.lock, receiver.getLock());
                    return true;
                }
                releaseLocks(this.lock, receiver.getLock());
                return false;
            }
        } catch (Exception e) {
            releaseLocks(this.lock, receiver.getLock());
            e.printStackTrace();
        }
        return false;
    }

    public boolean acquireLocks(Lock first, Lock second) {
        while (true) {
            if (first.tryLock()) {
                if (second.tryLock()) {
                    return true;
                }
                first.unlock();
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void releaseLocks(Lock first, Lock second) {
        first.unlock();
        second.unlock();
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

    public Lock getLock() {
        return lock;
    }
}
