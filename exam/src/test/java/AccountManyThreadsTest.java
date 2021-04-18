import junit.framework.TestCase;

public class AccountManyThreadsTest extends TestCase {
    BankAccount testBankAccount;
    DepositAccount testDepositAccount;
    CardAccount testCardAccount;
    AccountHolder accountHolder;

    @Override
    protected void setUp() throws Exception {
        accountHolder = new AccountHolder();
        testBankAccount =
                new BankAccount(accountHolder, 100000., AccountType.CURRENT, "JavaLearnersLtd");
        testDepositAccount =
                new DepositAccount(accountHolder, 50000., AccountType.TIME_DEPOSIT, "Вася");
        testCardAccount =
                new CardAccount(accountHolder, 90000., AccountType.SAVINGS, "Гена");
    }

    public void testTransactionCaseOne() {
        Thread threadOne = new Thread(() -> {
            testBankAccount.send(testDepositAccount, 1000.);
            testBankAccount.send(testCardAccount, 1000.);
            testBankAccount.send(testDepositAccount, 2000.);
            testBankAccount.send(testDepositAccount, 3000.);
            testBankAccount.send(testCardAccount, 4000.);

        });

        Thread threadTwo = new Thread(() -> {
            testBankAccount.send(testCardAccount, 2000.);
            testBankAccount.send(testCardAccount, 1000.);
            testBankAccount.send(testDepositAccount, 5000.);
            testBankAccount.send(testDepositAccount, 4000.);
            testCardAccount.send(testDepositAccount, 1000.);
        });

        Thread threadThree = new Thread(() -> {
            testCardAccount.send(testBankAccount, 3000.);
            testCardAccount.send(testBankAccount, 1000.);
            testBankAccount.send(testDepositAccount, 1000.);
            testBankAccount.send(testCardAccount, 2000.);
            testCardAccount.send(testDepositAccount, 2000.);
        });

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double bankActual = testBankAccount.getAmountOfMoney();
        double bankExpected = 78000.;
        assertEquals(bankExpected, bankActual);

        double cardActual = testCardAccount.getAmountOfMoney();
        double cardExpected = 92930.;
        assertEquals(cardExpected, cardActual);

        double depositActual = testDepositAccount.getAmountOfMoney();
        double depositExpected = 69000.;
        assertEquals(depositExpected, depositActual);
    }

    @Override
    protected void tearDown() throws Exception {
        testBankAccount = null;
        testDepositAccount = null;
        testCardAccount = null;
        accountHolder = null;
    }
}
