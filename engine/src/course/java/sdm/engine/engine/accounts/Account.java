package course.java.sdm.engine.engine.accounts;

import course.java.sdm.engine.Constants;

import java.util.ArrayList;
import java.util.Date;
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

    public void chargeCredit(Date date, int credit) {
        addTransaction(date, (float) credit, Constants.TRANSACTION_TYPE_CHARGE_STR);
    }

    public void receiveCredit(Date date, float credit) {
        addTransaction(date, credit, Constants.TRANSACTION_TYPE_RECEIVE_STR);
    }

    public void transferCredit(Date date, float credit) {
        addTransaction(date, credit, Constants.TRANSACTION_TYPE_TRANSFER_STR);
    }

    public void addTransaction(Date date, float credit, String transactionType) {
        float balanceBefore = getBalanceBefore();
        Transaction transaction = new Transaction(transactionType,
                date, credit, balanceBefore);
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
