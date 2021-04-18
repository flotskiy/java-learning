import java.util.Calendar;

public class DepositAccount extends BankAccount {
    private Calendar lastIncome;

    public DepositAccount(AccountHolder accountHolder, double amountOfMoney, AccountType accountType, String accountOwner) {
        super(accountHolder, amountOfMoney, accountType, accountOwner);
        lastIncome = Calendar.getInstance();
    }

    public DepositAccount(AccountHolder accountHolder) {
        super(accountHolder);
        lastIncome = Calendar.getInstance();
    }

    @Override
    public void put(double amountToPut) {
        if (amountToPut > 0) {
            lastIncome = Calendar.getInstance();
            setAmountOfMoney(getAmountOfMoney() + amountToPut);
        }
    }

    @Override
    public boolean take(double amountToTake) {
        if (checkLastIncome(lastIncome)) {
            return super.take(amountToTake);
        }
        return false;
    }

    private boolean checkLastIncome(Calendar incomeDate) {
        Calendar incomeDatePlusMonth = Calendar.getInstance();
        incomeDatePlusMonth.setTimeInMillis(incomeDate.getTimeInMillis());
        incomeDatePlusMonth.add(Calendar.MONTH, 1);
        Calendar currentMoment = Calendar.getInstance();
        return !currentMoment.after(incomeDate) && currentMoment.before(incomeDatePlusMonth);
    }

    public Calendar getLastIncome() {
        return lastIncome;
    }

    public void setLastIncome(Calendar lastIncome) {
        this.lastIncome = lastIncome;
    }
}
