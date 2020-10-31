package cr.ac.tec.proyecto1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class GUIMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Game GUI");

        Label lbl_mana = new Label("Mana: ");
        Label lbl_health = new Label("Health: ");
        Button bntCard1 = new Button("Card1");
        Button btnCard2 = new Button("Card2");
        Button btnCard3 = new Button("Card3");
        Button btnCard4 = new Button("Card4");
        Button btnDeck = new Button("Deck");
        btnDeck.setPrefSize(100,150);
        btnCard4.setPrefSize(100,150);
        btnCard3.setPrefSize(100,150);
        btnCard2.setPrefSize(100,150);
        bntCard1.setPrefSize(100,150);


        GridPane root = new GridPane();

        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(15, 15, 15, 15));

        root.add(lbl_mana, 0, 0);
        root.add(lbl_health,0,1);
        root.add(bntCard1,0,40);
        root.add(btnCard2,1,40);
        root.add(btnCard3,2,40);
        root.add(btnCard4,3,40);
        root.add(btnDeck,1,20);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
}
