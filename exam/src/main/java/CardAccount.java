public class CardAccount extends BankAccount {
    private static final double ONE_PER_CENT_COMMISSION = 1.01;

    public CardAccount(AccountHolder accountHolder, double amountOfMoney, AccountType accountType, String accountOwner) {
        super(accountHolder, amountOfMoney, accountType, accountOwner);
    }

    public CardAccount(AccountHolder accountHolder) {
        super(accountHolder);
    }

    @Override
    public boolean take(double amountToTake) {
        return super.take(amountToTake * ONE_PER_CENT_COMMISSION);
    }
}
