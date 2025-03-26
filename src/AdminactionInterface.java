import Note.Note;
import Note.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public interface AdminactionInterface extends Action{

    void addUser(ArrayList<Account> allAccounts, Scanner sc);
    void deleteUser(ArrayList<Account> accounts, Scanner sc);
    void viewUsers(ArrayList<Account> accounts);
    void depositecashtoatm(Note<Notes> cashInventory, Account loginedAdmin, Scanner sc);
    void addToCashInventory( Note<Notes> cashInventory, Notes newNote);
    void viewTransaction(ArrayList<Account>accounts);
    double ATMBalance(Note<Notes> cashInventory);
}
