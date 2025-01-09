import java.util.ArrayList;

public class Account {
    private String accountId;
    private String pin;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(String accountId, String pin) { // Constructor to store id and pin
        this.accountId = accountId;
        this.pin = pin;
    }


    public Account() { // Constructor to store transaction
        this.transactions = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return accountId;
    }


    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void viewTransactions() { // Method to view transaction
        if (transactions.isEmpty()) { // Check if the transaction is empty or not
            System.out.println("No transactions available for this account.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}