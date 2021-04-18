public class RandomHelper {

    public static void createAndSaveAccounts(AccountHolder accountHolder, int numberOfAccountsToCreate) {
        for (int i = 0; i < numberOfAccountsToCreate; i++) {
            Account account = createAccount(accountHolder);
            accountHolder.addAccount(account);
        }
    }

    public static Account createAccount(AccountHolder accountHolder) {
        int accountToChoose = (int) (Math.random() * 3);
        switch (accountToChoose) {
            case 1:
                return new CardAccount(accountHolder);
            case 2:
                return new DepositAccount(accountHolder);
            default:
                return new BankAccount(accountHolder);
        }
    }

    public static void createSeveralThreads(AccountHolder accountHolder, int numberOfThreadsToCreate) {
        for (int i = 0; i < numberOfThreadsToCreate; i++) {
            createAndStartThread(accountHolder);
        }
    }

    public static void createAndStartThread(AccountHolder accountHolder) {
        int sendOperations = (int) (Math.random() * 10) + 1;
        Thread thread = new Thread(() -> {
           for (int i = 0; i < sendOperations; i++) {
               Account from = accountHolder.chooseAccount();
               Account to = accountHolder.chooseAccount();
               if (from != to) {
                   from.send(to);
               }
           }
        });
        thread.start();
    }
}
