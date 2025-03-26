public class Transaction {
    private final String user;
    private String type;
    private double amount;

    public Transaction(String type, double amount,String user) { // Constructor
        this.type = type;
        this.amount = amount;
        this.user=user;
    }

    @Override
    public String toString() { // Override method
        return "Transaction of user="+user+" type='" + type + " " + " Amount=" + amount;
    }
}