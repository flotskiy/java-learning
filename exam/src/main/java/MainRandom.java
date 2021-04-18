public class MainRandom {
    public static void main(String[] args) {
        AccountHolder accountHolder = new AccountHolder();
        RandomHelper.createAndSaveAccounts(accountHolder, 15);
        RandomHelper.createSeveralThreads(accountHolder, 10);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Account account : accountHolder.getAccounts()) {
            long amount = (long) account.getAmountOfMoney();
            System.out.println("Bank account of '" + account.getAccountOwner() + "', remains - " + amount);
            account.getTransactionHolder().showTransactions();
            System.out.println();
        }
    }
}
