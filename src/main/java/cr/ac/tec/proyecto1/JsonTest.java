package cr.ac.tec.proyecto1;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.FileReader;

public class JsonTest {

    public static void main(String[] args) {

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
                card.enlistCard();
            }

        }catch (Exception e){
            e.printStackTrace();
        }








    }

}
