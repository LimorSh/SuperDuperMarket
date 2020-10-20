package course.java.sdm.engine.engine.accounts;

import course.java.sdm.engine.dto.TransactionDto;

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

    public synchronized void chargeCreditForUser(String username, Date date, int credit) {
        Account account = getUserAccount(username);
        account.chargeCredit(date, credit);
    }

    public synchronized void receiveCreditForUser(String username, Date date, float credit) {
        Account account = getUserAccount(username);
        account.receiveCredit(date, credit);
    }

    public synchronized void transferCreditForUser(String username, Date date, float credit) {
        Account account = getUserAccount(username);
        account.transferCredit(date, credit);
    }

    public synchronized List<TransactionDto> getUserTransactionsDto(String username) {
        Account account = getUserAccount(username);
        List<Transaction> transactions = account.getTransactions();
        List<TransactionDto> transactionsDto = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = new TransactionDto(transaction);
            transactionsDto.add(transactionDto);
        }
        return transactionsDto;
    }

    private Account getUserAccount(String username) {
        return accounts.get(username);
    }

    public void transferCredit(Date date, String receiver, String sender, float credit) {
        receiveCreditForUser(receiver, date, credit);
        transferCreditForUser(sender, date, credit);
    }
}
