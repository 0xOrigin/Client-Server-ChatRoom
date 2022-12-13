package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    private String id;

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.id = this.readClientID();
            this.name = this.readClientName();

            this.addClientHandler();
        } catch (IOException ex){
            this.close();
        }
    }

    private synchronized String readClientName() throws IOException{
        return this.bufferedReader.readLine();
    }

    private synchronized String readClientID() throws IOException{
        return this.bufferedReader.readLine();
    }

    private synchronized String readLineFromStream(){
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            close();
        }
        return line;
    }

    public synchronized void addClientHandler(){
        clientHandlers.add(this);
        String message = "<Server>: <" + this.name + "(" + this.id + ")" + "> has joined the chat";
        System.out.println(message);
        System.out.println("<Server> number of connected clients: " + clientHandlers.size());
        broadcastMessage(message);
    }

    public synchronized void removeClientHandler(){
        String message = "<Server>: <" + this.name + "(" + this.id + ")" + "> has left the chat";
        System.out.println(message);
        this.broadcastMessage(message);
        clientHandlers.remove(this);
        System.out.println("<Server> number of connected clients: " + clientHandlers.size());
    }

    private synchronized void sendMessage(ClientHandler clientHandler, String message){
        try {
            clientHandler.bufferedWriter.write(message);
            clientHandler.bufferedWriter.newLine();
            clientHandler.bufferedWriter.flush();
        } catch (IOException exception){
            this.close();
        }
    }

    public synchronized void broadcastMessage(String message){
        for(ClientHandler clientHandler: clientHandlers) {
            if(clientHandler != this)
                this.sendMessage(clientHandler, message);
        }
    }

    public synchronized void close(){
        this.removeClientHandler();

        try {
            if(this.bufferedReader != null)
                this.bufferedReader.close();
            if(this.bufferedWriter != null)
                this.bufferedWriter.close();
            if(this.socket != null)
                this.socket.close();

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void run(){
        /* Message receiver */
        String clientMessage;
        while(socket.isConnected()){
            clientMessage = readLineFromStream();
            if(clientMessage == null || clientMessage.contains("/quit"))
                break;
            broadcastMessage(clientMessage);
        }
        close();
    }
}
