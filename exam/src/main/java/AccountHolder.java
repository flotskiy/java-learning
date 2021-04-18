import java.util.ArrayList;
import java.util.List;

public class AccountHolder {
    private final List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account chooseAccount() {
        return accounts.get((int) (Math.random() * accounts.size()));
    }
}
