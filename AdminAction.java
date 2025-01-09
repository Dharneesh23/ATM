import Note.Notes;
import Notes.Note100;
import Notes.Note200;
import Notes.Note2000;
import Notes.Note500;

import java.util.ArrayList;
import java.util.Scanner;

class AdminAction {
    public static Account adminLogin(ArrayList<Account> accounts, Scanner sc) { // Method to Admin Login
        System.out.print("Enter Admin ID: ");
        String adminId = sc.next();
        int incre = 1;
        for (Account account : accounts) {
            if (account instanceof Admin) { // check account is instance of admin
                if (account.getId().equals(adminId)) { // Check admin id is valid
                    while (incre <= 3) {//allow only for 3 attempts
                        System.out.print("Enter Admin pin: ");
                        String adminPin = sc.next();
                        if (account.getPin().equals(adminPin)) { // Check admin pin is valid
                            System.out.println("Admin login successful.");
                            return account; // return admin ....login is successful
                        }else {
                            incre++; // add incrementer
                            int remainingAttempts = 3 - incre; // Decreasing remaining attempt
                            if (remainingAttempts == 0) { // Check the remaining attempt
                                System.out.println("User account locked due to too many failed attempts.");
                                return null;
                            } else {
                                System.out.println("Incorrect PIN. You have " + remainingAttempts + " attempts left....");
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void addUser(ArrayList<Account> allAccounts, Scanner sc) { // Method to add a user
        System.out.print("Enter new User ID: ");
        String userId = sc.next();
        for (Account acc : allAccounts) { // Iterates the user information from list
            if (acc.getId().equals(userId)) { // Check if User ID already exists
                System.out.println("User ID already exists. Try a different ID.");
                return;
            }
        }
        System.out.print("Enter new User PIN: ");
        String userPin = sc.next();
        User newUser = new User(userId, userPin); //  user details in User variable to be stored
        allAccounts.add(newUser); // adding the new user in allAccount array list of object
        System.out.println("User added successfully.");
    }

    public void deleteUser(ArrayList<Account> accounts, Scanner sc) { // Method to delete a user
        System.out.print("Enter User ID to delete: ");
        String userId = sc.next();

        boolean userFound = false;
        for (Account account : accounts) { //  the user information from list will store to account
            if (account.getId().equals(userId)) { // Check th given id is equal to entered id
                accounts.remove(account); // Delete the user from user list
                System.out.println("User " + userId + " deleted successfully.");
                userFound = true;
                break;
            }
        }

        if (!userFound) { // if user not found
            System.out.println("User with ID " + userId + " not found.");
        }
    }

    public void viewUsers(ArrayList<Account> accounts) { // Method to view all user
        System.out.println("List of Users:");
        for (Account account : accounts) { // Iterates the user information from list
            if (account instanceof User) { // check if account is instance of user is there
                System.out.println("User ID: " + account.getId()+"\t\tBalance: " + ((User) account).getBalance()+"\n");
            }
        }
    }


    public void depositecashtoatm(ArrayList<Notes> cashInventory, Account account, Scanner sc) {
        for (Notes notes : cashInventory) {
            System.out.println(notes.getNoteValue() + " " + notes.getNoteCount()); //to view how many notes and note count
        }
        System.out.println("Enter the amount of money to add to the ATM:");
        double total = sc.nextDouble();
        System.out.print("Enter number of  denomination notes 2000: ");
        int num2000= sc.nextInt();
        System.out.print("Enter number of  denomination notes 500: ");
        int num500= sc.nextInt();
        System.out.print("Enter number of denomination notes 200: ");
        int num200 = sc.nextInt();
        System.out.print("Enter number of  denomination notes 100: ");
        int num100= sc.nextInt();

        double totalAmount = (num100 * 100) + (num200 * 200) + (num500 * 500) + (num2000 * 2000);

        if(totalAmount == total){ //to check the entered amount and denomation amount is equal
            System.out.println("Total amount to be added to ATM: " + totalAmount);

            addToCashInventory(cashInventory, new Note100(num100)); // Add money to cash Inventory with count
            addToCashInventory(cashInventory, new Note200(num200));
            addToCashInventory(cashInventory, new Note500(num500));
            addToCashInventory(cashInventory, new Note2000(num2000));

            Transaction transaction = new Transaction("Deposit", totalAmount,account.getId()); //add to transation
            account.addTransaction(transaction);
            ATM.setatmbalance(ATM.getatmbalance()+totalAmount);// Adding transaction in account
            System.out.println("Money added successfully to ATM.");
            for (Notes notes : cashInventory) {
                System.out.println(notes.getNoteValue() + " " + notes.getNoteCount()); //to view how many notes and note count
            }
        }
        else {
            System.out.println("Deposit amount does not match the denomination counts.");
        }

    }

    private void addToCashInventory(ArrayList<Notes> cashInventory, Notes newNote) { // Method for  add the cash for inventory
        boolean found = false;
        for (Notes note : cashInventory) { // Iterates the notes from cashInventory
            if (note.getNoteValue() == newNote.getNoteValue()) { // check the get note value to new note value
                note.setNoteCount(note.getNoteCount() + newNote.getNoteCount()); // set the old not value + new note vale
                found = true;
                break;
            }
        }
        if (!found) { // if not found
            cashInventory.add(newNote); // add new note
        }
    }
    public static double ATMBalance(ArrayList<Notes> cashInventory) { // method to check ATM balance
        double totalBalance = 0;
        for (Notes note : cashInventory) { // Iterates the notes from cashInventory
            totalBalance += note.getNoteCount() * note.getNoteValue();
        }
        return totalBalance;
    }

    public void viewTransaction(Scanner sc,ArrayList<Account>accounts){ // Method to view transaction
        while (true){
            System.out.println("Enter your choice:\n1. View Atm Transactions\n2. View User Transactions\n3. View All Transactions\n4. Exit");
            int choice = sc.nextInt();
            if (choice == 1){
                viewAtmTransactions(accounts);
            }
            else if (choice == 2){
                viewUserTransaction(sc,accounts);
            } else if (choice ==3) {
                viewAllTransactions(accounts);
            } else if (choice == 4) {
                System.out.println("Exiting from view transaction....");
                return;
            } else {
                System.out.println("Enter a valid choice");
            }
        }

    }
    public void viewAllTransactions(ArrayList<Account> allAccounts) { // Method to view all transaction
        System.out.println("All Transactions:");
        String typeOfUser ;
        for (Account acc : allAccounts) {
            if(acc instanceof Admin){ // check the acc in instanceof that class
                typeOfUser = "ATM";
            }
            else {
                typeOfUser = "User";
            }
            System.out.println("Transactions for " + typeOfUser + " " + acc.getId());
            viewTransactions(acc);
        }
    }


    public void viewAtmTransactions(ArrayList<Account> allAccounts) { // Method to view all ATM transaction
        System.out.println("ATM Transactions:");
        boolean foundAdmin = false;

        for (Account account : allAccounts) {
            if (account instanceof Admin) { // Find the Admin account and display its transactions
                System.out.println("Transactions for ATM:"+ATM.getatmbalance());

                viewTransactions(account); // call the  view Transaction function
                foundAdmin = true;
                break;
            }
        }

        if (!foundAdmin) { // if admin transaction not found
            System.out.println("No ATM transactions found.");
        }
    }


    private void viewUserTransaction(Scanner sc,ArrayList<Account> accounts) { // Method to view User transaction
        while (true) {
            System.out.println("Enter the choice \n1.viewspecfic user 2.All user \n 3.Exit");
            int choice = sc.nextInt();
            if (choice == 1) {
               viewSpecificUserTransactions(sc,accounts);
            } else if(choice==2)
            {
                viewAllUserTransaction(accounts);
            }
            else if (choice == 3) {
                System.out.println("Exiting ...");
                return;
            }
            else {
                System.out.println("Enter a valid choice");
            }
        }
    }
    public void viewSpecificUserTransactions(Scanner sc, ArrayList<Account> accounts) { // Method to view specific User transaction
        System.out.println("Enter User ID to view transactions:");
        String userId = sc.next();
        for (Account account : accounts) { // Iterate through all accounts and display their transactions
            if (account.getId().equals(userId)) { // if user id id=s equal to entered id
                System.out.println("Transactions for User: " + userId);
                for (Transaction transaction : account.getTransactions()) { // Iterate through all accounts and display their transactions
                    System.out.println(transaction); // Print transaction
                }
                return;
            }
        }
        System.out.println("User not found!");
    }

    public void viewAllUserTransaction(ArrayList<Account> allAccounts) { // Method to view all User transaction
        System.out.println("All User Transactions:");
        for (Account acc : allAccounts) { // all accounts to store in acc to display the transation
            if (acc instanceof User) {
                System.out.println("Transactions for User: " + acc.getId());
                acc.viewTransactions();
            }
        }
    }

    public void viewTransactions(Account account) {//viewtransation method
        if(account.getTransactions().isEmpty())//to check whether the account will empty or not
        {
            System.out.println("No transaction in this account ");
        }else {
            System.out.println("User Transactions:");
            for (Transaction transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        }
    }

}