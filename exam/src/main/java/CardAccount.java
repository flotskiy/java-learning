public class CardAccount extends BankAccount {
    private static final double ONE_PER_CENT_COMMISSION = 1.01;

    public CardAccount(double amountOfMoney, AccountType accountType, String accountOwner) {
        super(amountOfMoney, accountType, accountOwner);
    }

    public CardAccount(AccountType accountType, String accountOwner) {
        super(accountType, accountOwner);
    }

    @Override
    public boolean take(double amountToTake) {
        return super.take(amountToTake * ONE_PER_CENT_COMMISSION);
    }
}
