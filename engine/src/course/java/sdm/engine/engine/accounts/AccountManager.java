package course.java.sdm.engine.engine.accounts;

import java.util.*;

// Adding and retrieving accounts is synchronized and in that manner - these actions are thread safe
public class AccountManager {

    private final Map<String, Account> accounts;

    public AccountManager() {
        accounts = new HashMap<>();
    }

    public synchronized void addAccount(String username) {
        Account account = new Account(username);
        accounts.put(username, account);
    }

    public synchronized void removeAccount(String username) {
        accounts.remove(username);
    }

    public synchronized void addCreditForUser(String username, Date date, int credit) {
        Account account = getUserAccount(username);
        account.addCredit(date, credit);
    }

    public synchronized List<Transaction> getUserTransactions(String username) {
        Account account = getUserAccount(username);
        return account.getTransactions();
    }

    private Account getUserAccount(String username) {
        return accounts.get(username);
    }
}
