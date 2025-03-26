import java.util.ArrayList;

class Admin extends Account {

    public Admin(String adminId, String adminPin) {  // Constructor to initialize admin ID and PIN
        super(adminId,adminPin);  // Pass admin Id and Pin to super class
    }
    @Override
    public String getId() {
        return super.accountId;
    }

    @Override
    public String getPin() {
        return super.pin;
    }

    @Override
    public void setPin(String pin) {
        super.pin = pin;
    }

    @Override
    public ArrayList<Transaction> getTransactions() {
        return super.transactions;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        super.transactions.add(transaction);
    }
    @Override
    public void setTransactions(Transaction transactions){

    }

}