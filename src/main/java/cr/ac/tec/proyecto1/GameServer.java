package cr.ac.tec.proyecto1;


import java.io.*;
import java.net.*;


public class GameServer {


    private ServerSocket ss;
    private int numPlayers;
    private ServerSideConnection player1;
    private  ServerSideConnection player2;

    /**
     * Class that will work as the server on the game
     */

    public GameServer() {
        System.out.println("Game server");
        this.numPlayers = 0;
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
                dataOut.flush();

                while (true){

                }
            }catch (IOException e){
                System.out.println("IOException from run() in SSC");
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
