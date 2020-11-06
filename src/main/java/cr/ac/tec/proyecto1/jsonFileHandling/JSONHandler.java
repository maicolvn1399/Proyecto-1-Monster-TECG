package cr.ac.tec.proyecto1.jsonFileHandling;

import com.fasterxml.jackson.databind.JsonNode;
import cr.ac.tec.proyecto1.Card;
import cr.ac.tec.proyecto1.jsonFileHandling.Json;
import cr.ac.tec.proyecto1.linearStructures.SingleList;

import java.io.BufferedReader;
import java.io.FileReader;

public class JSONHandler {

    /**
     * Class that works as a handler to get data from the cards.json file
     */
    private SingleList<Card> cardList;
    private Card[] cards;

    public JSONHandler() {
        this.cardList = new SingleList<>();
        this.cards = new Card[40];
    }

    public SingleList<Card> getCardList() {
        try{
            String jsonString;
            //read the file
            BufferedReader br = new BufferedReader(new FileReader("cards.json"));
            try{
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line!=null){
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                jsonString = sb.toString();
            }finally {
                br.close();
            }
            JsonNode node = Json.parse(jsonString);
            for (int i = 0; i < 40;i++){
                //System.out.println(node.get("gameCards").get(i));
                Card card = Json.fromJson(node.get("gameCards").get(i),Card.class);
                //card.enlistCard();
                cardList.add(card);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cardList;
    }


    public Card[] getCardsArray(){
        try{
            String jsonString;
            //read the file
            BufferedReader br = new BufferedReader(new FileReader("cards.json"));
            try{
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line!=null){
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                jsonString = sb.toString();
            }finally {
                br.close();
            }
            JsonNode node = Json.parse(jsonString);
            for (int i = 0; i < 40;i++){
                //System.out.println(node.get("gameCards").get(i));
                Card card = Json.fromJson(node.get("gameCards").get(i),Card.class);
                //card.enlistCard();
                this.cards[i] = card;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.cards;



    }


}
