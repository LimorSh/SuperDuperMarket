package course.java.sdm.engine.engine.accounts;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public synchronized void addCreditForUser(String username, LocalDate date, int credit) {
        Account account = accounts.get(username);
        account.addCredit(date, credit);
    }
}
