package course.java.sdm.engine.dto;

import course.java.sdm.engine.Utils;
import course.java.sdm.engine.engine.accounts.Transaction;

public class TransactionDto {

    private final String type;
    private final String dateStr;
    private final float amount;
    private final float balanceBefore;
    private final float balanceAfter;

    public TransactionDto(Transaction transaction) {
        this.type = transaction.getType();
        this.dateStr = Utils.convertDateToString(transaction.getDate());
        this.amount = Utils.roundNumberWithTwoDigitsAfterPoint(transaction.getAmount());
        this.balanceBefore = Utils.roundNumberWithTwoDigitsAfterPoint(transaction.getBalanceBefore());
        this.balanceAfter = Utils.roundNumberWithTwoDigitsAfterPoint(transaction.getBalanceAfter());
    }

    public String getType() {
        return type;
    }

    public String getDateStr() {
        return dateStr;
    }

    public float getAmount() {
        return amount;
    }

    public float getBalanceBefore() {
        return balanceBefore;
    }

    public float getBalanceAfter() {
        return balanceAfter;
    }
}
