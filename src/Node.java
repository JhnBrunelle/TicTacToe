/**
 * This Class creates an Object that points to another Node Object.
 */
public class Node {

    private Record record;       // Info (TTTRecord) to store in the Node
    private Node nextNode;          // the next connected node

    public Node(Record record){
        this.record = record;
        this.nextNode = null;
    }

    /**
     * Gets the next connected Node
     * @return next connected Node
     */
    public Node getNext() {
        return nextNode;
    }

    /**
     * Sets the next connected Node
     */
    public void setNext(Node next){
        this.nextNode = next;
    }

    /**
     * Returns the record in the Node
     * @return TTTRecord
     */
    public Record getRecord() {
        return record;
    }

    /**
     * Sets the record in the Node
     */
    public void setRecord(Record newRecord) {
        this.record = newRecord;
    }
}
