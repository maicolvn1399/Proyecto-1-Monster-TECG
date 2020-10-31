package cr.ac.tec.proyecto1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSException;

import java.io.*;
import java.net.Socket;


public class PlayerGUI extends Application {

    private int playerID;
    private int otherPlayer;
    private ClientSideConnection csc;

    public static void main(String[] args) {
        launch(args);

    }


    @Override
    public void start(Stage stage) throws FileNotFoundException {

        stage.setTitle("Game GUI");
        Label lbl_mana = new Label("Mana: ");
        Label lbl_health = new Label("Health: ");
        Button btnCard1 = new Button("card1");
        Button btnCard2 = new Button("card2");
        Button btnCard3 = new Button("card3");
        Button btnCard4 = new Button("card4");


        Image img = new Image("cardback1.jpg");
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setFitHeight(150);
        imgView.setPreserveRatio(true);
        Button btnDeck = new Button("",imgView);

        btnDeck.setPrefSize(100,150);
        btnCard4.setPrefSize(100,150);
        btnCard3.setPrefSize(100,150);
        btnCard2.setPrefSize(100,150);
        btnCard1.setPrefSize(100,150);

        GridPane root = new GridPane();

        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(15, 15, 15, 15));

        root.add(lbl_mana, 0, 0);
        root.add(lbl_health,0,1);
        root.add(btnCard1,0,40);
        root.add(btnCard2,1,40);
        root.add(btnCard3,2,40);
        root.add(btnCard4,3,40);
        root.add(btnDeck,1,20);

        connectToServer();

        if(this.playerID == 1){
            System.out.println("You're player #1, you go first");
            this.otherPlayer = 2;
        }else {
            System.out.println("You're player #2, wait for your turn");
            this.otherPlayer = 1;
        }


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public void connectToServer(){
        this.csc = new ClientSideConnection();
    }


    private class ClientSideConnection{
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;

        public ClientSideConnection() {
            System.out.println("---Client---");
            try{
                this.socket = new Socket("127.0.0.1",51734);
                this.dataIn = new DataInputStream(this.socket.getInputStream());
                this.dataOut = new DataOutputStream(this.socket.getOutputStream());
                playerID = dataIn.readInt();
                System.out.println("Connected to the server as player #"+playerID+".");


            }catch (IOException e){
                System.out.println("IOException from CSC constructor");
            }
        }
    }


}
