public interface DictionaryADT {
    public int put (Record record) throws DuplicatedKeyException;

    public void remove (String config) throws InexistentKeyException;

    public Record get (String config);

    public int numElements();
}