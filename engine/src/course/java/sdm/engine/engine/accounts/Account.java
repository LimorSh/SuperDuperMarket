package course.java.sdm.engine.engine.accounts;

import course.java.sdm.engine.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String username;
    private final List<Transaction> transactions;

    public Account(String username) {
        this.username = username;
        this.transactions = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addCredit(LocalDate date, int credit) {
        float balanceBefore = getBalanceBefore();
        Transaction transaction = new Transaction(Constants.TRANSACTION_TYPE_CHARGE_STR,
                date, (float) credit, balanceBefore);
        transactions.add(transaction);
    }

    private float getBalanceBefore() {
        float balanceBefore = 0f;
        int len = transactions.size();
        if (len > 0) {
            // the balance after will become the balance before for the new transaction
            balanceBefore = transactions.get(len - 1).getBalanceAfter();
        }
        return balanceBefore;
    }
}
