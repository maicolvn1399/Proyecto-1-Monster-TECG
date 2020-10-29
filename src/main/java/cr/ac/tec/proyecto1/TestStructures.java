package cr.ac.tec.proyecto1;

import cr.ac.tec.proyecto1.linearStructures.Deck;
import cr.ac.tec.proyecto1.linearStructures.Deck1;

public class TestStructures {

    public static void main(String[] args) {
        Deck1<Integer> deck = new Deck1<>();
        deck.pop();
        deck.push(1);
        deck.push(2);
        deck.push(3);
        deck.push(4);
        deck.push(5);
        deck.push(6);
        deck.showDeck();
        System.out.println("Deck complete");

        System.out.println("<<<<<<<<<<<<<<<");
        System.out.println("Deck pop");
        deck.pop();
        System.out.println("deck after being popped ");
        deck.showDeck();
        System.out.println("second pop");
        deck.pop();
        System.out.println("after pop");
        deck.showDeck();
        System.out.println("third pop ");
        deck.pop();
        System.out.println("after 3rd pop");
        deck.showDeck();
        System.out.println("new push of 20");
        deck.push(20);
        System.out.println("after push of 20");
        deck.showDeck();


    }


}
