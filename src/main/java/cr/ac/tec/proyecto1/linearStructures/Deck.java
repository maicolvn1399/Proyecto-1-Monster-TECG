package cr.ac.tec.proyecto1.linearStructures;

public class Deck<T> {

    private Node<T> root;

    public boolean isEmpty(){
        return this.root == null;
    }

    public void push(T element){
        Node<T> newNode = new Node<>(element);

        if(this.root == null){
            this.root = newNode;
        }else {
            Node<T> temp = this.root;
            this.root = newNode;
            newNode.setNext(temp);
        }
    }

    public T pop(){
        if (this.isEmpty()){
            System.out.println("Stack Underflow");
        }
        T poppedElement = this.root.value;
        this.root = this.root.next;
        return poppedElement;
    }

    public T peek() {
        if (this.isEmpty()) {
            System.out.println("Stack Underflow");
        }
        return this.root.value;
    }

    @Override
    public String toString(){
        String deck = "--------\n";
        Node<T> temp = this.root;
        while (temp!=null){
            deck += "[ " + temp.getValue() + " ] \n";
            temp = temp.next;
        }
        deck = "--------\n";
        return deck;
    }


}
