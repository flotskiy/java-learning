import junit.framework.TestCase;

import java.util.Calendar;

public class AccountSingleThreadTest extends TestCase {
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
                new CardAccount(accountHolder, 90000., AccountType.SAVINGS, "Петя");
    }

    public void testTransactionFromBankAccountToDepositAccount() {
        testBankAccount.send(testDepositAccount, 10000.);
        double actual = testDepositAccount.getAmountOfMoney();
        double expected = 60000.;
        assertEquals(expected, actual);
    }

    public void testTransactionFromCardAccountToBankAccount() {
        testCardAccount.send(testBankAccount, 10000.);
        double actual = testCardAccount.getAmountOfMoney();
        double expected = 79900.;
        assertEquals(expected, actual);
    }

    public void testFailTransactionFromDepositAccountToBankAccount() {
        testDepositAccount.send(testBankAccount, 1000.);
        double actual = testDepositAccount.getAmountOfMoney();
        double expected = 50000.;
        assertEquals(expected, actual);
    }

    public void testTransactionFromDepositAccountToBankAccount() {
        Calendar tempDate = testDepositAccount.getLastIncome();
        tempDate.add(Calendar.MONTH, 2);
        testDepositAccount.send(testBankAccount, 1000.);
        double actual = testDepositAccount.getAmountOfMoney();
        double expected = 49000.;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {
        testBankAccount = null;
        testDepositAccount = null;
        testCardAccount = null;
        accountHolder = null;
    }
}
