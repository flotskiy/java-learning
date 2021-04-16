public interface Account {
    public void put(double amountToPut);

    public boolean take(double amountToTake);

    public boolean send(BankAccount receiver, double amount);
}
