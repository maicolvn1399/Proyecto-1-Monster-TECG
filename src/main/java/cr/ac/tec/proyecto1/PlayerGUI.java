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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import java.io.File;


import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Random;


public class PlayerGUI extends Application implements EventHandler<ActionEvent> , Subject{

    private int playerID;
    private int otherPlayer;
    private ClientSideConnection csc;
    private int[] values;//*
    private int maxTurns;
    private int turnsMade;
    private int myPoints;
    private int enemyPoints;
    private boolean buttonsEnabled;
    private int health;
    private int mana;
    private int enemyHealth;
    private int enemyMana;
    private Label lbl_mana_status;
    private Label lbl_health_status;
    private Label lbl_mana;
    private Label lbl_health;
    private Label lbl_enemy_health_status;
    private Label lbl_enemy_health;
    public static TextArea historial;
    private Button btnCard1;
    private Button btnCard2;
    private Button btnCard3;
    private Button btnCard4;
    private SingleList<Card> cardList = getCardsList();
    private JSONHandler jsonHandler;
    private int[] randomDeckIndexes;
    private Deck<Card> cardDeck = getCardDeck();
    private CircularDoublyLinkedList<Card> circularCardList = getCircularCardList();
    private Card[] cards = jsonHandler.getCardsArray();
    private List<Listener> liss = new ArrayList<>();
    private String[] backgrounds = {"-fx-background-image: url('bg2.jpg');","-fx-background-image: url('bg3.jpg');","-fx-background-image: url('bg4.jpg');"
    ,"-fx-background-image: url('bg5.jpg');","-fx-background-image: url('bg6.jpg');","-fx-background-image: url('bg7.jpg');"};

    private GridPane root;



    public static void main(String[] args) {
        launch(args);

    }

    /**
     * sets up the GUI for the players
     * @param stage
     * @throws FileNotFoundException
     */
    @Override
    public void start(Stage stage) throws FileNotFoundException {

        values = new int[4];
        turnsMade = 0;
        myPoints = 0;
        enemyPoints = 0;

        this.health = 1000;
        this.mana = 200;
        this.enemyHealth = 1000;
        this.enemyMana = 200;



        this.lbl_mana = new Label("Mana: ");
        this.lbl_mana.setFont(new Font("Impact", 30));
        this.lbl_mana.setStyle("-fx-background-color: #bdcf95");

        this.lbl_health = new Label("Health: ");
        this.lbl_health.setFont(new Font("Impact", 30));
        this.lbl_health.setStyle("-fx-background-color: #bdcf95");

        this.lbl_mana_status = new Label(String.valueOf(this.mana));
        this.lbl_mana_status.setFont(new Font("Impact", 30));
        this.lbl_mana_status.setStyle("-fx-background-color: #bdcf95");

        this.lbl_health_status = new Label(String.valueOf(this.health));
        this.lbl_health_status.setFont(new Font("Impact", 30));
        this.lbl_health_status.setStyle("-fx-background-color: #bdcf95");



        this.btnCard1 = new Button();
        this.btnCard2 = new Button();
        this.btnCard3 = new Button();
        this.btnCard4 = new Button();

        this.btnCard1.setId("1");
        this.btnCard2.setId("2");
        this.btnCard3.setId("3");
        this.btnCard4.setId("4");

        this.historial = new TextArea();
        historial.setMaxSize(200,900);


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

        root = new GridPane();

        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(15, 15, 15, 15));

        root.add(lbl_mana, 0, 0);
        root.add(lbl_health,0,1);
        root.add(lbl_health_status,1,1);
        root.add(lbl_mana_status,1,0);



        root.add(btnCard1,0,40);
        root.add(btnCard2,1,40);
        root.add(btnCard3,2,40);
        root.add(btnCard4,3,40);
        root.add(btnDeck,1,20);

        root.add(historial,3, 20);
        historial.setText("Historial de Jugadas");

        Listener l1 = new Listener();
        this.listen(l1);

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


        System.out.println("----- TESTING CIRCULAR LIST ---- ");
        //getCircularCardList().traverse();
        System.out.println("Testing getElement() of circularCardList");
        this.circularCardList.getElement(0).enlistCard();
        System.out.println("Testing of getElement() done ");
        System.out.println("----- TESTING CIRCULAR LIST DONE ---- ");

        System.out.println("Size of circular list " + this.circularCardList.getSize());

        root.setStyle("-fx-background-image: url('bg2.jpg');"
                + "-fx-background-position:center;"
                + "-fx-background-repeat:no-repeat;"
                + "-fx-background-size:auto;"
                + "-fx-background-color:#000000");


        Button buttons[] = {this.btnCard1,this.btnCard2,this.btnCard3,this.btnCard4};
        for (int i = 0; i < buttons.length; i++){
            Image image = new Image(this.circularCardList.getElement(i).getImage());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            buttons[i].setGraphic(imageView);
        }

        stage.setTitle("Monster - TECG - Player #"+this.playerID);



        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    /**
     * Allows to disable the buttons when the other player has a turn
     */
    public void toggleButtons(){
        btnCard1.setDisable(buttonsEnabled);
        btnCard2.setDisable(buttonsEnabled);
        btnCard3.setDisable(buttonsEnabled);
        btnCard4.setDisable(buttonsEnabled);
    }

    /**
     * gets a single linked list of cards
     * @return cardList
     */
    public SingleList<Card> getCardsList(){
        this.jsonHandler = new JSONHandler();
        this.cardList = jsonHandler.getCardList();
        return this.cardList;
    }

    /**
     * makes a list of indexes that allows to shuffle the deck of cards
     * @return randomDeckIndexes
     */
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


    /**
     * get a deck of cards
     * @return cardDeck
     */
    public Deck<Card> getCardDeck(){
        this.cardDeck = new Deck<>();
        for(int i = 0; i < 16; i++){
            cardDeck.push(getCardsList().getElement(randomizeDeckIndexes()[i]));
        }
        return this.cardDeck;
    }

    /**
     * gets a circular list of cards, this list is the first hand of cards that the game uses
     * @return circularCardList
     */
    public CircularDoublyLinkedList<Card> getCircularCardList(){
        this.circularCardList = new CircularDoublyLinkedList<>();
        for(int i = 0; i < 4; i++){
            this.circularCardList.insertAtEnd(this.cardDeck.peek());
            this.cardDeck.pop();
            System.out.println(this.cardDeck.getSize());
        }
        return this.circularCardList;
    }

    @Override
    public void listen(Listener lis) {
        liss.add(lis);
    }

    @Override
    public void notifyListener(String m) {
        for (Listener lis : liss)  {
            lis.update(m);
        }
    }

    /**
     * updates the turns for the players
     */
    public void updateTurn(){
        int n = csc.receiveButtonNum();
        //int n = csc.receiveDamage();
        System.out.println("Your enemy used card #"+n + ". Your turn");
        notifyListener("Player" + otherPlayer+" used card #"+ cardList.getElement(n-1).getId());
        //enemyPoints += values[n-1];
        //Card auxCard = this.circularCardList.getElement(n-1);
        //int position = this.getCardsIndex(auxCard);

        this.health -= cardList.getElement(n-1).damage;
/*
        if (playerID == 1){
            this.health -= cardList.getElement(n-1).damage;
        }else if(playerID == 2){
            this.enemyHealth -= cardList.getElement(n-1).damage;
        }
*/

        System.out.println("Damage points received :"+ cardList.getElement(n-1).damage);

        //this.enemyHealth -= this.circularCardList.getElement(n-1).getDamage();//gets the damage of the card and decreases the health of enemy
        //this.lbl_enemy_health_status.setText(String.valueOf(this.enemyHealth)); //shows the damage of enemy in a label
        //System.out.println("Your health is " + this.enemyHealth + " health");
        this.lbl_health_status.setText(String.valueOf(this.health));//shows the value of health in a label
        if (playerID == 1){
            System.out.println("Your health is: " +this.health);
        }else {
            System.out.println("Your health is: "+ this.enemyHealth);
        }
        buttonsEnabled = false;
        if(playerID == 1 && turnsMade == maxTurns ){
            checkWinner();
        }else {
            buttonsEnabled = false;
        }
        toggleButtons();
    }

    /**
     * connects to the server
     */
    public void connectToServer(){
        this.csc = new ClientSideConnection();
    }

    /**
     * button handler
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();// gets the button that was pressed
        System.out.println("Pressed button: " + b);
        int bNum = Integer.parseInt(b.getId());
        notifyListener("Player" + playerID+" used card #"+ this.circularCardList.getElement(bNum-1).id);
        System.out.println("You clicked button #"+bNum+" Now wait for player #"+otherPlayer);
        turnsMade ++;
        System.out.println("Turns made " + turnsMade);
        buttonsEnabled = true;
        toggleButtons(); //enables and disables the interface when other player has to select a card
        //myPoints += values[bNum - 1];
        System.out.println(this.circularCardList.getElement(bNum-1).getCardInfo());
        csc.sendButtonNum(this.circularCardList.getElement(bNum-1).id);
        this.enemyHealth -= this.circularCardList.getElement(bNum-1).damage;

        System.out.println("Damage: " + this.circularCardList.getElement(bNum-1).getDamage());
        System.out.println();

        int damage = this.circularCardList.getElement(bNum-1).getDamage();
        Card auxCard = this.circularCardList.getElement(bNum-1);
        executeCard(auxCard);



        /*
        Card auxCard = this.circularCardList.getElement(bNum-1);
        int position = this.getCardsIndex(auxCard);
        this.health -= this.getSelectedCard(position).getDamage();
         */


        //this.health -= this.circularCardList.getElement(bNum-1).getDamage(); //gets the damage of the card and decreases the health of the player
        this.lbl_health_status.setText(String.valueOf(this.health));//shows the value of health in a label
        this.circularCardList.remove(bNum-1); //removes the card that was already played from the circular list
        this.circularCardList.insertAtPosition(this.cardDeck.peek(),bNum-1); //inserts a new card from the deck to the circular list
        this.cardDeck.pop(); // delete the card that is now on the circular list

        updateButtonImages();//update images of cards

        System.out.println(this.cardDeck.getSize());
        System.out.println(this.circularCardList.getSize());
        //System.out.println("My health: "+this.health);
        //csc.sendButtonNum(bNum);

        //csc.sendDamage(damage);

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

    /**
     * update the images of the cards in the buttons
     */
    public void updateButtonImages(){
        Button[] buttons = {this.btnCard1,this.btnCard2,this.btnCard3,this.btnCard4};
        for (int i = 0; i < buttons.length; i++){
            Image image = new Image(this.circularCardList.getElement(i).getImage());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);
            buttons[i].setGraphic(imageView);
        }
    }

    public void executeCard(Card card){
        if (card.getCategory().equalsIgnoreCase("esbirros")){
            showDialogEsbirrosCard(card.getDamage());
        }else if(card.getCategory().equalsIgnoreCase("hechizos")){
            executeHechizosCard(card);
        }else if(card.getCategory().equalsIgnoreCase("secrets")){
            executeSecretsCard(card);
        }
    }


    public void executeSecretsCard(Card card){
        switch (card.getAction()){
            case "change_background":
                changeGameBackground();
                break;
            case "play_sound":
        }
    }

    public void play_sound(){
        AudioClip note = new AudioClip("resources/gameSound.mp3");
        note.play();
    }


    public void changeGameBackground(){
        int rnd = new Random().nextInt(this.backgrounds.length);
        String bg = this.backgrounds[rnd];

        this.root.setStyle(bg
                + "-fx-background-position:center;"
                + "-fx-background-repeat:no-repeat;"
                + "-fx-background-size:auto;"
                + "-fx-background-color:#000000");
    }


    public void executeHechizosCard(Card card){
        switch (card.getAction()){
            case "freeze":
                toggleButtons();
                turnsMade++;
                toggleButtons();
                break;
            case "supreme_power":
                System.out.println("Supreme power");
                break;
            case "heal":
                heal_player();
                break;
            case "decrease_health_by_100":
                decrease_health();
                break;
            case "steal_card":
                steal_card();

        }
    }

    public void heal_player(){
        if (playerID == 1){
            this.health += 100;
            JOptionPane.showMessageDialog(null,"You have played an Hechizos card that healed\n" +
                    "your health by 100, your new health is " + this.health);
        }else {
            this.enemyHealth += 100;
            JOptionPane.showMessageDialog(null,"You have played an Hechizos card that healed\n" +
                    "your health by 100, your new health is " + this.enemyHealth);
        }
    }

    public void decrease_health(){
        if (playerID == 1){
            this.health -= 100;
            JOptionPane.showMessageDialog(null,"You have played an Hechizos card and you hurt your\n" +
                    "own health, your health will be decreased by -100");
        }else {
            this.enemyHealth -= 100;
            JOptionPane.showMessageDialog(null,"You have played an Hechizos card and you hurt your\n" +
                    "own health, your health will be decreased by -100");
        }
    }

    public void steal_card(){
        Random random = new Random();
        Card stolenCard = this.cardList.getElement(random.nextInt(cardList.getSize()));
        this.cardDeck.push(stolenCard);

        ImageIcon imageIcon = new ImageIcon(stolenCard.getImage());
        JOptionPane.showMessageDialog(null,"You have stolen a card from your opponent\n" +
                "This is the new card added to your deck: \n" +
                stolenCard.getCardInfo(), "Stolen card",JOptionPane.INFORMATION_MESSAGE,imageIcon);
    }


    /**
     * shows a window to let the player know the kind of card that is playing
     * @param damage
     */
    public void showDialogEsbirrosCard(int damage){
        JOptionPane.showMessageDialog(null,"You have selected an Esbirros card this will lower the \n" +
                "health of your opponent by " + damage + " points of damage");
    }

    public int getCardsIndex(Card card){
        int found = -1;
        for (int i = 0; i < cards.length; i++){
            if (cards[i].getId() == card.getId()){
                found = i;
                break;
            }
        }
        return found;
    }

    public Card getSelectedCard(int position){
        Card auxCard = new Card();
        for (int i = 0; i < cards.length; i++){
            if (i == position){
               auxCard = cards[i];
            }
        }
        return auxCard;
    }



    /**
     *checks winner
     */
    private void checkWinner(){
        buttonsEnabled = true;

        if(this.health > this.enemyHealth){
            System.out.println("You won!\n" + "YOU: " + health +"\n" + "ENEMY: " + enemyHealth );
        }else if(this.health < this.enemyHealth){
            System.out.println("You lost!\n" + "YOU: " + health +"\n" + "ENEMY: " + enemyHealth );
        }else
            System.out.println("It's a tie, you both got "+ this.health + " points");


        csc.closeConnection();
    }




    /**
     * inner class that allows to make a connection with both players
     */
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

        /**
         * sends data to socket
         * @param n
         */
        public void sendButtonNum(int n){
            try {
                dataOut.writeInt(n);
                dataOut.flush();
            }catch (IOException ex){
                System.out.println("IOException from sendButtonNum() from CSC");
            }
        }

        public void sendDamage(int n){
            try {
                dataOut.writeInt(n);
                dataOut.flush();
            }catch (IOException e){
                System.out.println("IOException from sendDamage() from CSC");
            }
        }


        /**
         * receives data from socket
         * @return number selected
         */
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


        public int receiveDamage(){
            int n = -1;
            try{
                n = dataIn.readInt();
                System.out.println("Player #"+otherPlayer+" has sent "+ n + " damage points");
            }catch (IOException e){
                System.out.println("IOException from receiveDamage() from CSC");
            }
            return n;
        }





        /**
         * closes the connection between sockets
         */
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
