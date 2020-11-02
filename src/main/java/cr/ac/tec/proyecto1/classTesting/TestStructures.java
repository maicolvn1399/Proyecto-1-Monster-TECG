package cr.ac.tec.proyecto1.classTesting;

import cr.ac.tec.proyecto1.linearStructures.CircularDoublyLinkedList;
import cr.ac.tec.proyecto1.linearStructures.Deck;

public class TestStructures {

    public static void main(String[] args) {
        Deck<Integer> deck = new Deck<>();
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

        System.out.println("TEST FOR CIRCULAR DOUBLY LINKED LIST");
        CircularDoublyLinkedList<Integer> cdll = new CircularDoublyLinkedList<>();
        cdll.insertAtBeginning(32);
        cdll.insertAtBeginning(35);
        cdll.insertAtBeginning(45);
        cdll.insertAtEnd(12);
        cdll.insertAtEnd(10);
        cdll.insertAtEnd(9);
        cdll.traverse();
        System.out.println();
        System.out.println("Size is "+cdll.getSize());
        cdll.insertAtPosition(11,5);
        System.out.println();
        cdll.traverse();
        System.out.println("\nSize is "+cdll.getSize());
        System.out.println();
        System.out.println("Deleted "+cdll.remove(5));
        //Index for deletion also starts from zero

        cdll.removeAtBeginning();
        cdll.traverse();
        cdll.removeAtEnd();
        System.out.println();
        cdll.traverse();
        System.out.println();
        System.out.println("Size is "+cdll.getSize());
    }


}
