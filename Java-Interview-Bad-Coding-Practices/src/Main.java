import java.util.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount("John Doe", 1000);
        bank.processTransaction(1011827261, "John Doe", "deposit", 500, LocalDateTime.now(), "Deposit from John Doe");
        bank.processTransaction(1011827262, "Dan Kolov", "withdraw", 200, LocalDateTime.now(), "Withdrawal by Dan Kolov");
    }
}

public class Transaction {
    private int tid;
    private String ttype;
    private double tamount;
    private LocalDateTime tdate;
    private String tdiscr;

    public Transaction() {}

    public Transaction(int id, String type, double amount, LocalDateTime date, String description) {
        this.tid = id;
        this.ttype = type;
        this.tamount = amount;
        this.tdate = date;
        this.tdiscr = description;
    }

    // Getters and setters omitted for brevity...

    public Transaction getById(long transactionId) {
        // Database operations omitted...
        return null;
    }

    public void updateAllTransactions(List<Transaction> transactions) {
        // Database operations omitted...
    }

    public void updateSingleTransaction(Transaction transaction) {
        // Database operations omitted...
    }

    public void markAsVoidedTransaction(Transaction transaction) {
        // Database operations omitted...
    }

    public void logTransactionUpdateInformationMessage() {
        // Logging operations omitted...
    }

    public void logTransactionErrorMessage() {
        // Logging operations omitted...
    }
}

public class Account {
    private String aName;
    private double aBalance;
    private List<Transaction> aTransactions;

    public Account(String name, double balance) {
        this.aName = name;
        this.aBalance = balance;
        this.aTransactions = new ArrayList<>();
        // Database request in constructor omitted...
    }

    public void addTransaction(int id, String type, double amount, LocalDateTime date, String description) {
        Transaction newTransaction = new Transaction(id, type, amount, date, description);
        this.aTransactions.add(newTransaction);
        // Balance update logic omitted...
    }

    public void updateFeeTransactions() {
        // Balance update logic omitted...
    }

    public String exportAccountInfo(String format) {
        if (format.equals("csv")) {
            StringBuilder header = new StringBuilder("Account,transactionid,amount,type\n");
            for (Transaction t : this.aTransactions) {
                header.append(this.aName).append(",");
                header.append(t.getTid()).append(",");
                header.append(t.getTamount()).append(",");
                header.append(t.getTtype()).append("\n");
            }
            return header.toString();
        } else if (format.equals("json")) {
            // JSON serialization omitted...
            return null;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    // Getters and setters omitted for brevity...
}

public class Bank {
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(String name, double balance) {
        Account newAccount = new Account(name, balance);
        this.accounts.add(newAccount);
    }

    public void processTransaction(int transactionid, String accountName, String transactionType, double transactionAmount, LocalDateTime transactionDate, String transactionDescription) {
        Account account = null;
        for (Account a : this.accounts) {
            if (a.getName().equals(accountName)) {
                account = a;
                break;
            }
        }
        if (account != null) {
            account.addTransaction(transactionid, transactionType, transactionAmount, transactionDate, transactionDescription);
        }
    }

    public void updateAllTransactions() {
        for (Account account : this.accounts) {
            account.updateFeeTransactions();
        }
    }

    // Getters and setters omitted for brevity...
}