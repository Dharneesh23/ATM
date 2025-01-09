class User extends Account{//class name
    private double balance;// Store user balance amount

    public User(String userId, String userPin) { // Constructor
        super(userId,userPin);
    }

    public User() {} // Default Constructor

    // getters and setters

    public double getBalance() { // user balance getter
        return balance;
    }

    public void setBalance(double balance) { // user balance setter
        this.balance = balance;
    }


}