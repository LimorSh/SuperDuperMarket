package course.java.sdm.engine.engine.accounts;

import course.java.sdm.engine.Constants;
import java.time.LocalDate;

public class Transaction {

    public enum TransactionType {
        CHARGE(Constants.TRANSACTION_TYPE_CHARGE_STR),
        RECEIVE(Constants.TRANSACTION_TYPE_RECEIVE_STR),
        TRANSFER(Constants.TRANSACTION_TYPE_TRANSFER_STR),
        DEFAULT(Constants.TRANSACTION_TYPE_DEFAULT_STR),
        ;

        private final String transactionTypeStr;

        TransactionType(String transactionType) {
            this.transactionTypeStr = transactionType;
        }

        public String getTransactionTypeStr() {
            return transactionTypeStr;
        }

        public static TransactionType getTransactionType(String type) {
            for (TransactionType transactionType : TransactionType.values()) {
                if (transactionType.transactionTypeStr.equals(type))
                    return transactionType;
            }
            return DEFAULT;
        }
    }

    private final TransactionType type;
    private final LocalDate date;
    private final float amount;
    private final float balanceBefore;
    private float balanceAfter;

    public Transaction(String type, LocalDate date, float amount, float balanceBefore) {
        TransactionType transactionType = TransactionType.getTransactionType(type);
        this.type = transactionType;
        this.date = date;
        this.amount = amount;
        this.balanceBefore = balanceBefore;

        switch (transactionType) {
            case CHARGE:
            case RECEIVE:
                this.balanceAfter = balanceBefore + amount;
                break;
            case TRANSFER:
                this.balanceAfter = balanceBefore - amount;
                break;
        }
    }

    public static Transaction.TransactionType convertStringToTransactionType(String transactionType) {
        if (transactionType.toLowerCase().contains(Constants.TRANSACTION_TYPE_CHARGE_STR)) {
            return Transaction.TransactionType.CHARGE;
        }
        else if (transactionType.toLowerCase().contains(Constants.TRANSACTION_TYPE_RECEIVE_STR)) {
            return Transaction.TransactionType.RECEIVE;
        }
        return Transaction.TransactionType.TRANSFER;
    }

    public String getType() {
        return type.getTransactionTypeStr();
    }

    public LocalDate getDate() {
        return date;
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
