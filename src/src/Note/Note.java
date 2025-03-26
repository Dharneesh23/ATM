package Note;

public class Note <T extends Notes>{
    T[] note= (T[])new Notes[4];
    int length =0;
    public void add(T getnotes)
    {
        if(length< note.length)
        {
            note[length]=getnotes;
            length++;
        }
    }
    public T[]getNote()
    {
        return note;
    }


}
