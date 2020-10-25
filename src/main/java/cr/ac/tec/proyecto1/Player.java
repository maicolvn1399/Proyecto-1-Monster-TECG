package cr.ac.tec.proyecto1;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Player extends JFrame {

    private int width;
    private int height;
    private Container contentPane;
    private JTextArea messageArea;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private ClientSideConnection csc;
    private int playerID;
    private int otherPlayer;


    public Player(int width,int height){
        this.width = width;
        this.height = height;
        contentPane = this.getContentPane();
        this.messageArea = new JTextArea();
        this.btn1 = new JButton("1");
        this.btn2 = new JButton("2");
        this.btn3 = new JButton("3");
        this.btn4 = new JButton("4");

    }

    /**
     * shows up the GUI
     */

    public void setUpGUI(){
        this.setSize(this.width,this.height);
        this.setTitle("Monster TECG -  Player# "+ this.playerID);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.contentPane.setLayout(new GridLayout(1,5));
        this.contentPane.add(messageArea);
        this.messageArea.setText("Creating a simple turn based game in Java");
        this.messageArea.setWrapStyleWord(true);
        this.messageArea.setLineWrap(true);
        this.messageArea.setEditable(false);
        this.contentPane.add(btn1);
        this.contentPane.add(btn2);
        this.contentPane.add(btn3);
        this.contentPane.add(btn4);

        if(this.playerID == 1){
            this.messageArea.setText("You're player #1, you go first");
            this.otherPlayer = 2;
        }else {
            this.messageArea.setText("You're player #2, wait for your turn");
            this.otherPlayer = 1;
        }

        this.setVisible(true);
    }

    public void connectToServer(){
        this.csc = new ClientSideConnection();

    }

    /***
     * Client connection inner class
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


            }catch (IOException e){
                System.out.println("IOException from CSC constructor");
            }
        }
    }

    /**
     * Main method to run the GUI
     * @param args
     */

    public static void main(String[] args) {
        Player p = new Player(500,100);
        p.connectToServer();
        p.setUpGUI();

    }


}
