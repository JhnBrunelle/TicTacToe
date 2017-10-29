
public class Dictionary implements DictionaryADT {

    private Node[] table;
    private int tableSize;
    private int elements;

    public Dictionary(int tableSize) {
        this.tableSize = tableSize;
        table = new Node[tableSize];
    }

    /**
     * Generates a hash key
     * @param config config to generate hashKeyFor
     * @return hash key
     */
    private int hashFunction(String config) {

        int hashKey = 0;

        for (int i = 0; i < config.length(); i++) {
            hashKey = (hashKey * 43 ) % tableSize;
            hashKey += (int) config.charAt(i);
        }

        return hashKey % tableSize;
    }

    /**
     * Inserts a given record in to the dictonary
     * @param record TTTRecord to insert
     * @return 1 if produces a collision, else 0
     * @throws DuplicatedKeyException Thrown when config already in the dictionary
     */
    public int put(Record record) throws DuplicatedKeyException {

        int hashKey = hashFunction(record.getConfiguration());

        // Check if config is already in the table
        if(get(record.getConfiguration()) != null) {
            throw new DuplicatedKeyException("config is already in the table");
        }

        // Key is empty, create a new node
        if (table[hashKey] == null){
            table[hashKey] = new Node(record);
            return 0;
        } else {

            // Get the Front of the Linked List
            Node currentRecord = table[hashKey];
            while (currentRecord.getNext() != null) {

                // Iterate until end
                currentRecord = currentRecord.getNext();
            }

            currentRecord.setNext(new Node(record));
            this.elements = numElements() + 1;
        }

        return 1;
    }

    /**
     * Removes the record with the given key config from the dictionary
     * @param config Config to remove
     * @throws InexistentKeyException If key is not found in the table
     */
    public void remove(String config) throws InexistentKeyException {

        int hashKey = hashFunction(config);

        Node currentNode = table[hashKey];

        if (currentNode == null) {
            throw new InexistentKeyException("config not found in the table");
        }
        while (currentNode != null) {
            if (currentNode.getRecord().getConfiguration().equals(config)) {
                table[hashKey] = currentNode.getNext();
                break;
            } else {
                currentNode = currentNode.getNext();
            }
        }
    }

    /**
     * Returns a TTTRecord stored in the dictionary for the given config
     * @param config Config to get from table
     * @return TTTRecord at key, or otherwise null
     */
    public Record get(String config) {

        int hashKey = hashFunction(config);

        Node currentNode = table[hashKey];

        while (currentNode != null) {
            if (currentNode.getRecord().getConfiguration().equals(config)) {
                return currentNode.getRecord();

            } else {
                currentNode = currentNode.getNext();
            }
        }

        return null;
    }

    /**
     * Returns the number of TTTRecords in the table
     * @return Number of records
     */
    public int numElements() {
        return elements;
    }


}
