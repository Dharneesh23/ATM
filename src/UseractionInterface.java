import Note.Note;
import Note.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public interface UseractionInterface extends Action {

    void changePin(Account account, Scanner sc, User user);
    void withdraw(User user,Scanner scanner,Account account) throws CloneNotSupportedException;
    void deposit(Account account, User user, Note<Notes> cashInventory, Scanner sc);
    void addToCashInventory(Note<Notes> cashInventory, Notes newNote);
    void viewTransactions(Account account);

}
