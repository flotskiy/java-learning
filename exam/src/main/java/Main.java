public class Main {
    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount(
                50000., AccountType.CURRENT, "Большой начальник"
        );

        DepositAccount depositAccount = new DepositAccount(
                40000., AccountType.TIME_DEPOSIT, "Паша"
        );

        CardAccount cardAccount = new CardAccount(
                30000., AccountType.SAVINGS, "Олег"
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

        double bank = bankAccount.getAmountOfMoney();
        System.out.println("Bank account remains - " + bank);

        double card = cardAccount.getAmountOfMoney();
        System.out.println("Card account remains - " + card);

        double deposit = depositAccount.getAmountOfMoney();
        System.out.println("Deposit account remains - " + deposit);
    }
}
