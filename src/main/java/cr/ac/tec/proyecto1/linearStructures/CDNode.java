package cr.ac.tec.proyecto1.linearStructures;

public class CDNode<T> {

    /**
     * Node class for the Circular Doubly Linked List
     */

    private T data;
    private CDNode<T> next;
    private CDNode<T> prev;

    /**
     * Constructor
     */
    public CDNode() {
        this.next = null;
        this.prev = null;
        this.data = null;
    }

    /**
     * Constructor
     * @param data
     */
    public CDNode(T data) {
        this(data,null,null);
    }

    /**
     * Constructor
     * @param data
     * @param next
     * @param prev
     */
    public CDNode(T data, CDNode<T> next, CDNode<T> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CDNode<T> getNext() {
        return next;
    }

    public void setNext(CDNode<T> next) {
        this.next = next;
    }

    public CDNode<T> getPrev() {
        return prev;
    }

    public void setPrev(CDNode<T> prev) {
        this.prev = prev;
    }
}
