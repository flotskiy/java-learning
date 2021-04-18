public class Main {
    public static void main(String[] args) {
        AccountHolder accountHolderTest = new AccountHolder();

        BankAccount bankAccount = new BankAccount(
                accountHolderTest, 50000., AccountType.CURRENT, "Большой начальник"
        );

        DepositAccount depositAccount = new DepositAccount(
                accountHolderTest, 40000., AccountType.TIME_DEPOSIT, "Паша"
        );

        CardAccount cardAccount = new CardAccount(
                accountHolderTest, 30000., AccountType.SAVINGS, "Олег"
        );

        Thread threadOne = new Thread(() -> {
            bankAccount.send(depositAccount, 100.);
            bankAccount.send(cardAccount, 100.);
            bankAccount.send(depositAccount, 200.);
            bankAccount.send(depositAccount, 300.);
            bankAccount.send(cardAccount, 400.);

        });

        Thread threadTwo = new Thread(() -> {
            bankAccount.send(cardAccount, 200.);
            bankAccount.send(cardAccount, 100.);
            bankAccount.send(depositAccount, 500.);
            bankAccount.send(depositAccount, 400.);
            cardAccount.send(depositAccount, 100.);
        });

        Thread threadThree = new Thread(() -> {
            cardAccount.send(bankAccount, 300.);
            cardAccount.send(bankAccount, 100.);
            bankAccount.send(depositAccount, 100.);
            bankAccount.send(cardAccount, 200.);
            cardAccount.send(depositAccount, 200.);
        });

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------------");

        double bank = bankAccount.getAmountOfMoney();
        System.out.println("Bank account of '" + bankAccount.getAccountOwner() + "', remains - " + bank);
        bankAccount.getTransactionHolder().showTransactions();

        double card = cardAccount.getAmountOfMoney();
        System.out.println("Card account of '" + cardAccount.getAccountOwner() + "', remains - " + card);
        cardAccount.getTransactionHolder().showTransactions();

        double deposit = depositAccount.getAmountOfMoney();
        System.out.println("Deposit account of '" +depositAccount.getAccountOwner() + "', remains - "+ deposit);
        depositAccount.getTransactionHolder().showTransactions();
    }
}
