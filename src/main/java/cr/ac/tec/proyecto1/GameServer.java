package cr.ac.tec.proyecto1;

import java.io.*;
import java.net.*;

public class GameServer {


    private ServerSocket ss;
    private int numPlayers;
    private ServerSideConnection player1;
    private  ServerSideConnection player2;
    private int turnsMade;
    private int maxTurns;
    private int[] values;
    private int playerOneButtonNum;
    private int playerTwoButtonNum;



    /**
     * Class that will work as the server on the game
     */

    public GameServer() {
        System.out.println("Game server");
        this.numPlayers = 0;
        turnsMade = 0;
        maxTurns = 4;
        values = new int[4]; //array de 4 valores porque hay 4 botones en la interfaz

        for (int i = 0; i < values.length; i++){
            values[i] = (int) Math.ceil(Math.random() * 100);
            System.out.println("Value #"+(i + 1)+" is "+values[i]);
        }


        try{
            ss = new ServerSocket(51734);
            
        }catch (IOException e){
            System.out.println("IOException from GameServer Constructor");
        }
    }

    /**
     * Accepts the connections
     */
    public void acceptConnections(){
        try{
            System.out.println("Waiting for connections");
            while (this.numPlayers < 2){
                Socket s = this.ss.accept();
                this.numPlayers++;
                System.out.println("Player # "+numPlayers+" has connected");
                ServerSideConnection ssc = new ServerSideConnection(s,numPlayers);

                if(numPlayers == 1){
                    player1 = ssc;
                }else {
                    player2 = ssc;
                }
                Thread t = new Thread(ssc);
                t.start();

            }
            System.out.println("We have 2 players now, no longer accepting connections");
        }catch (IOException e){
            System.out.println("IOException from acceptConnections");
        }
    }

    /**
     * Inner class to connect the server and the clients
     */
    private class ServerSideConnection implements Runnable{
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        private int playerID;


        public ServerSideConnection(Socket s, int id) {
            this.socket = s;
            this.playerID = id;

            try{
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());

            }catch (IOException e){
                System.out.println("IOException from SSC constructor");
            }
        }

        public void run(){
            try{
                dataOut.writeInt(playerID);
                dataOut.writeInt(maxTurns);
                dataOut.writeInt(values[0]);
                dataOut.writeInt(values[1]);
                dataOut.writeInt(values[2]);
                dataOut.writeInt(values[3]);
                dataOut.flush();

                while (true){
                    if (this.playerID == 1){
                        playerOneButtonNum = dataIn.readInt();
                        System.out.println("Player 1 clicked button #"+playerOneButtonNum);
                        player2.sendButtonNum(playerOneButtonNum);
                    }else {
                        playerTwoButtonNum = dataIn.readInt();
                        System.out.println("Player 2 clicked button #" + playerTwoButtonNum);
                        player1.sendButtonNum(playerTwoButtonNum);
                    }
                    turnsMade++;
                    if (turnsMade == maxTurns){
                        System.out.println("Max turns has been reached");
                        break;
                    }
                }
                player1.closeConnection();
                player2.closeConnection();
            }catch (IOException e){
                System.out.println("IOException from run() in SSC");
            }
        }

        public void sendButtonNum(int n){
            try{
                dataOut.writeInt(n);
                dataOut.flush();
            }catch (IOException ex){
                System.out.println("IOException from sendButtonNum() SSC");
            }
        }

        public void closeConnection(){
            try{
                socket.close();
                System.out.println("Connection closed");
            }catch (IOException ex){
                System.out.println("IOException on closeConnection() SSC");

            }

        }

    }

    /**
     * Main method to accept connections
     * @param args
     */
    public static void main(String[] args){
        GameServer gS = new GameServer();
        gS.acceptConnections();
    }
    
}
