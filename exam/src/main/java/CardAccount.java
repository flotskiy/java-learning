public class CardAccount extends BankAccount {
    private static final double ONE_PER_CENT_COMMISSION = 1.01;

    public CardAccount(double amountOfMoney, String accountName, String accountOwner) {
        super(amountOfMoney, accountName, accountOwner);
    }

    public CardAccount(String accountName, String accountOwner) {
        super(accountName, accountOwner);
    }

    @Override
    public boolean take(double amountToTake) {
        return super.take(amountToTake * ONE_PER_CENT_COMMISSION);
    }
}
