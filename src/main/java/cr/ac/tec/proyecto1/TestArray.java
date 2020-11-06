package cr.ac.tec.proyecto1;

import cr.ac.tec.proyecto1.jsonFileHandling.JSONHandler;
import cr.ac.tec.proyecto1.linearStructures.SingleList;

public class TestArray {


    public static void main(String[] args) {


        JSONHandler jsonHandler = new JSONHandler();
        Card[] cards = jsonHandler.getCardsArray();

        SingleList<Card> cardSingleList = jsonHandler.getCardList();
        Card singleCard = cardSingleList.getElement(32);


        for (int i = 0; i < cards.length; i++) {
            System.out.println(cards[i].getCardInfo());
        }

        for (int i = 0; i < cards.length; i++) {
            if (cards[i].getId() == singleCard.getId()) {
                System.out.println("Position");
                System.out.println(i);
                break;
            }
        }

    }
}
