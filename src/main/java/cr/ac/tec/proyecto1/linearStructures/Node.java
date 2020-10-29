package cr.ac.tec.proyecto1.linearStructures;

public class Node<T> {

    public T value;
    public Node next;

    public Node(){
        this.next = null;
    }

    public Node(T value){
        this();
        this.value = value;
    }

    public Node(T value, Node next){
        this(value);
        this.next = next;
    }

    public T getValue(){
        return value;
    }

    public void setValue(T value){
        this.value = value;
    }

    public Node<T> getNext(){
        return next;
    }

    public void setNext(Node<T> next){
        this.next = next;
    }

}
