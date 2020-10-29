package cr.ac.tec.proyecto1.linearStructures;

public class Deck<T> {

    private SingleList<T> stack = new SingleList<>();

    /**
     * add a new element to the deck
     * @param element
     */
    public void push(T element){
        stack.addToStart(element);
    }

    /**
     * delete the first element
     */
    public void pop(){
        stack.deleteByIndex(0);
    }

    /**
     * gets the first element of the deck
     * @return
     */
    public T peek(){
        return stack.getElement(0);
    }

    /**
     * print the deck or the elements that are inside the stack
     */
    public void showDeck(){
        stack.showList();
    }

}
