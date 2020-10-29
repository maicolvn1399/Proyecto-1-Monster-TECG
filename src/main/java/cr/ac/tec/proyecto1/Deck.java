package cr.ac.tec.proyecto1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 *

public class Deck {
    private Card[] cards;
    private int cardsInDeck;

    public Deck(){
        cards = new Card[30];
    }

    public void reset(){
        cardsInDeck = 0;
        Card.Value[] values = Card.Value.values();
        for (int i = 0; i < values.length; i++){
            Card.Value value = values[i];
            cards[cardsInDeck] = new Card(value);
        }
    }

    public void replaceDeckWith(ArrayList<Card> cards) {
        this.cards = cards.toArray(new Card[cards.size()]);
        this.cardsInDeck = this.cards.length;
    }

    public boolean isEmpty() {
        return cardsInDeck == 0;
    }

    public void shuffle(){
        int n = cards.length;
        Random random = new Random();

        for (int i = 0; i < cards.length; i++){
            int randomValue = i + random.nextInt(n - i);
            Card randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
        }
    }
   public Card drawCard() throws IllegalArgumentException{
        if (isEmpty()){
            throw new IllegalArgumentException("No hay cartas para comer");
        }
        return cards[--cardsInDeck];
   }
}
 */