package cr.ac.tec.proyecto1.linearStructures;

public class CircularDoublyLinkedList<T> {

    /**
     * Circular doubly linked list
     */
    private CDNode<T> head;

    /**
     * Returns the number of nodes in circular doubly linked list
     */
    public int getSize(){
        int count = 0;
        if(this.head == null){
            return count;
        }else {
            CDNode<T> temp = head;
            do{
                temp = temp.getNext();
                count++;

            }while (temp != head);
        }
        return count;
    }

    /**
     * Traversal of circular doubly linked list
     */
    public void traverse(){
        if(head == null){
            System.out.println("Circular doubly linked list is empty");
        }else {
            CDNode<T> temp = head;
            do{
                System.out.print(temp.getData() + " ");
                //System.out.println(temp.getData().toString());
                temp = temp.getNext();
            }while (temp!=head);

        }
    }

    /*
     Methods related to insertion of items in the circular list */
    public void insertAtBeginning(T data){
        CDNode<T> newNode = new CDNode<>(data);
        if (this.head == null){
            newNode.setNext(newNode);
            newNode.setPrev(newNode);
            this.head = newNode;
        }else {
            CDNode<T> temp = this.head.getPrev();
            temp.setNext(newNode);
            newNode.setPrev(temp);
            newNode.setNext(this.head);
            this.head.setPrev(newNode);
            this.head = newNode;
        }
    }

    public void insertAtEnd(T data){
        CDNode<T> newNode = new CDNode<>(data);
        if (this.head == null){
            newNode.setNext(newNode);
            newNode.setPrev(newNode);
            this.head = newNode;
        }else {
            CDNode<T> temp = this.head.getPrev();
            temp.setNext(newNode);
            newNode.setNext(this.head);
            this.head.setPrev(newNode);
            newNode.setPrev(temp);
        }
    }

    public void insertAtPosition(T data,int position) {
        if (position < 0 || position == 0) {
            insertAtBeginning(data);
        } else if (position > getSize() || position == getSize()) {
            insertAtEnd(data);

        } else {
            CDNode<T> temp = this.head;
            CDNode<T> newNode = new CDNode<>(data);
            for (int i = 0; i < position; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.getNext().setPrev(newNode);
            temp.setNext(newNode);
            newNode.setPrev(temp);
        }
    }

    /* methods related to insertion in circular doubly linked list ends

     */

    /*
    methods related to deletion in circular doubly linked list starts
     */
    //internal use only
    @SuppressWarnings("unused")
    private void remove(CDNode<T> node){
        if (node.getPrev() == node || node.getNext() == node){
            this.head = null;
        }else {
            CDNode<T> temp = node.getPrev();
            temp.setNext(node.getNext());
            node.getNext().setPrev(temp);
        }
        node = null;
    }

    public void removeAtBeginning(){
        if (this.head == null){
            System.out.println("List is already empty");
        }else {
            CDNode<T> temp = this.head.getNext();
            this.head.getPrev().setNext(temp);
            temp.setPrev(this.head.getPrev());
            this.head = temp;
        }
    }

    public void removeAtEnd(){
        if(this.head == null){
            System.out.println("List is already empty");{
            }
        }else {
            CDNode<T> temp = this.head.getPrev();
            temp.getPrev().setNext(this.head);
            this.head.setPrev(temp.getPrev());
            temp = null;
        }
    }

    /**
     * Removal based on a given position
     */
    public T remove(int position){
        T data = null;
        if (position == 0){
            data = this.head.getData();
            removeAtBeginning();
        }else if(position == getSize()-1){
            data = this.head.getPrev().getData();
            removeAtEnd();
        }else {
            CDNode<T> temp = this.head;
            for (int i = 0; i < position; i++){
                temp = temp.getNext();
            }
            data = temp.getData();
            CDNode<T> node = temp.getNext();
            node.setPrev(temp.getPrev());
            temp.getPrev().setNext(node);
            temp = null;
        }
        return data;
    }
    /*methods related to deletion in circular doubly linked list ends
     */

}
