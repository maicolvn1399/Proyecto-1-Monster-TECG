package cr.ac.tec.proyecto1.linearStructures;

public class Deck1<T> {

    private SingleList<T> stack = new SingleList<>();

    public void push(T element){
        stack.addToStart(element);
    }

    public void pop(){
        stack.deleteByIndex(0);
    }

    public T peek(){
        return stack.getElement(0);
    }

    public void showDeck(){
        stack.showList();
    }

}
