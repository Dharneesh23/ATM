import java.util.ArrayList;

public abstract class Account {
    protected String accountId;
    protected String pin;
    protected ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(String accountId, String pin) { // Constructor to store id and pin

        this.accountId = accountId;
        this.pin = pin;
    }


    public Account() { // Constructor to store transaction
        this.transactions = new ArrayList<>();
    }

    // Getters and Setters
    public abstract String getId();



    public abstract String getPin() ;


    public abstract void setPin(String pin);


    public abstract  ArrayList<Transaction> getTransactions() ;

    public abstract void addTransaction(Transaction transaction) ;


    public abstract void setTransactions(Transaction transactions) ;

}

