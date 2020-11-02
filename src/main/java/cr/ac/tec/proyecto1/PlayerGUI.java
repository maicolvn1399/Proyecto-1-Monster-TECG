package cr.ac.tec.proyecto1;


import cr.ac.tec.proyecto1.jsonFileHandling.JSONHandler;
import cr.ac.tec.proyecto1.linearStructures.CircularDoublyLinkedList;
import cr.ac.tec.proyecto1.linearStructures.Deck;
import cr.ac.tec.proyecto1.linearStructures.SingleList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;
import java.util.Random;


public class PlayerGUI extends Application implements EventHandler<ActionEvent> {

    private int playerID;
    private int otherPlayer;
    private ClientSideConnection csc;
    private int[] values;
    private int maxTurns;
    private int turnsMade;
    private int myPoints;
    private int enemyPoints;
    private boolean buttonsEnabled;
    private Label lbl_mana;
    private Label lbl_health;
    private Button btnCard1;
    private Button btnCard2;
    private Button btnCard3;
    private Button btnCard4;
    private SingleList<Card> cardList;
    private JSONHandler jsonHandler;
    private int[] randomDeckIndexes;
    private Deck<Card> cardDeck;
    private CircularDoublyLinkedList<Card> circularCardList;



    public static void main(String[] args) {
        launch(args);

    }


    @Override
    public void start(Stage stage) throws FileNotFoundException {

        values = new int[4];
        turnsMade = 0;
        myPoints = 0;
        enemyPoints = 0;



        this.lbl_mana = new Label("Mana: ");
        this.lbl_health = new Label("Health: ");
        this.btnCard1 = new Button("1");
        this.btnCard2 = new Button("2");
        this.btnCard3 = new Button("3");
        this.btnCard4 = new Button("4");


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
            this.buttonsEnabled = false;
        }else {
            System.out.println("You're player #2, wait for your turn");
            this.otherPlayer = 1;
            this.buttonsEnabled = true;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    updateTurn();
                }
            });
            t.start();
        }


        toggleButtons();

        btnCard1.setOnAction(this);
        btnCard2.setOnAction(this);
        btnCard3.setOnAction(this);
        btnCard4.setOnAction(this);

        /*
        System.out.println("----- TESTING SINGLE CARD LIST ------- ");
        //getCardsList().showList();

        for(int i = 0; i < getCardsList().getSize()-1; i++){
            getCardsList().getElement(i).enlistCard();
        }

        System.out.println("----- TESTING SINGLE CARD LIST DONE ------- ");


        System.out.println("TESTING RANDOM INDEXES FOR DECK");
        for(int i = 0; i < randomizeDeckIndexes().length; i ++){
            System.out.println(randomizeDeckIndexes()[i]);
        }

        System.out.println("----- TESTING CARD DECK ----- ");
        getCardDeck().peek().enlistCard();
        System.out.println("----- TESTING CARD DECK DONE ----- ");

         */
        System.out.println("----- TESTING CIRCULAR LIST ---- ");
        getCircularCardList().traverse();
        System.out.println("----- TESTING CIRCULAR LIST DONE ---- ");


        Image im = new Image(getCardDeck().peek().getImage());
        ImageView imv = new ImageView(im);
        imv.setFitWidth(100);
        imv.setFitHeight(150);
        imv.setPreserveRatio(true);
        btnCard1.setGraphic(imv);



        stage.setTitle("Monster - TECG - Player #"+this.playerID);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public void toggleButtons(){
        btnCard1.setDisable(buttonsEnabled);
        btnCard2.setDisable(buttonsEnabled);
        btnCard3.setDisable(buttonsEnabled);
        btnCard4.setDisable(buttonsEnabled);
    }

    public SingleList<Card> getCardsList(){
        this.jsonHandler = new JSONHandler();
        this.cardList = jsonHandler.getCardList();
        return this.cardList;
    }

    public int[] randomizeDeckIndexes(){
        this.randomDeckIndexes = new int[40];
        for(int i = 0; i < this.randomDeckIndexes.length; i++){
            this.randomDeckIndexes[i] = i;
        }

        Random randomGenerator = new Random();
        int randomIndex;
        int randomValue;

        for(int i = 0; i < randomDeckIndexes.length; i++){
            randomIndex = randomGenerator.nextInt(randomDeckIndexes.length);

            randomValue = randomDeckIndexes[randomIndex];
            randomDeckIndexes[randomIndex] = randomDeckIndexes[i];
            randomDeckIndexes[i] = randomValue;
        }
        return randomDeckIndexes;
    }

    public Deck<Card> getCardDeck(){
        this.cardDeck = new Deck<>();
        for(int i = 0; i < 16; i++){
            cardDeck.push(getCardsList().getElement(randomizeDeckIndexes()[i]));
        }
        return this.cardDeck;
    }

    public CircularDoublyLinkedList<Card> getCircularCardList(){
        this.circularCardList = new CircularDoublyLinkedList<>();
        for(int i = 0; i < 4; i++){
            this.circularCardList.insertAtEnd(getCardDeck().peek());
            getCardDeck().pop();


        }
        getCardDeck().showDeck();
        return this.circularCardList;
    }


    public void updateTurn(){
        int n = csc.receiveButtonNum();
        System.out.println("Your enemy clicked button #"+n + ". Your turn");
        enemyPoints += values[n-1];
        System.out.println("Your enemy has " + enemyPoints + " points");
        buttonsEnabled = false;
        if(playerID == 1 && turnsMade == maxTurns ){
            checkWinner();
        }else {
            buttonsEnabled = false;
        }
        toggleButtons();
    }

    public void connectToServer(){
        this.csc = new ClientSideConnection();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();
        int bNum = Integer.parseInt(b.getText());
        System.out.println("You clicked button #"+bNum+" Now wait for player #"+otherPlayer);
        turnsMade ++;
        System.out.println("Turns made " + turnsMade);
        buttonsEnabled = true;
        toggleButtons();
        myPoints += values[bNum - 1];
        System.out.println("My points: "+myPoints);
        csc.sendButtonNum(bNum);

        if (playerID == 2 && turnsMade == maxTurns){
            checkWinner();
        }else {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    updateTurn();
                }
            });
            t.start();
        }
    }

    private void checkWinner(){
        buttonsEnabled = true;
        if(myPoints > enemyPoints){
            System.out.println("You won!\n" + "YOU: " + myPoints +"\n" + "ENEMY: " + enemyPoints );
        }else if(myPoints < enemyPoints){
            System.out.println("You lost!\n" + "YOU: " + myPoints +"\n" + "ENEMY: " + enemyPoints );
        }else
            System.out.println("It's a tie, you both got "+ myPoints + " points");

        csc.closeConnection();
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
                maxTurns = dataIn.readInt()/2;
                values[0] = dataIn.readInt();
                values[1] = dataIn.readInt();
                values[2] = dataIn.readInt();
                values[3] = dataIn.readInt();
                System.out.println("max turns "+ maxTurns);
                System.out.println("value #1 "+ values[0]);
                System.out.println("value #2 "+ values[1]);
                System.out.println("value #3 "+ values[2]);
                System.out.println("value #4 "+ values[3]);

            }catch (IOException e){
                System.out.println("IOException from CSC constructor");
            }
        }

        public void sendButtonNum(int n){
            try {
                dataOut.writeInt(n);
                dataOut.flush();
            }catch (IOException ex){
                System.out.println("IOException from sendButtonNum() from CSC");
            }
        }

        public int receiveButtonNum(){
            int n = -1;
            try {
                n = dataIn.readInt();
                System.out.println("Player #" + otherPlayer + " clicked button #"+n);
            }catch (IOException ex){
                System.out.println("IOException from receiveButtonNum() CSC ");
            }
            return n;
        }

        public void closeConnection(){
            try {
                socket.close();
                System.out.println("---CONNECTION CLOSED----");
            }catch (IOException ex){
                System.out.println("IOException on closeConnection() CSC");
            }
        }

    }


}