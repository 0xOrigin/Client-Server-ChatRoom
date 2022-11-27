package Client.Controllers;

import Client.Views.ChatRoomViewController;
import java.io.*;
import java.net.Socket;

public class ClientSoc {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ChatRoomViewController controller;
    private String name;
    private String id;

    public ClientSoc(String host, int port, String id, String name, ChatRoomViewController controller){
        try{
            this.socket = new Socket(host, port);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.controller = controller;
            this.name = name;
            this.id = id;
        } catch (IOException ex){
            this.close();
        }
    }

    void sendMessage(String message){
        try {
            if(this.socket.isConnected()){
                this.bufferedWriter.write(message);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
            }
            if(message.contains("/quit"))
                this.close();
        } catch (IOException | NullPointerException exception){
            this.close();
        }
    }

    void sendRegistrationMessage(){
        this.sendMessage(this.id);
        this.sendMessage(this.name);
    }

    void listenToBroadcast(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                try {
                    while(socket.isConnected()){
                        message = bufferedReader.readLine();
                        if(message == null)
                            break;
                        controller.appendMessageToMessageArea(message);
                    }
                } catch (IOException | NullPointerException ex){
                    close();
                } finally {
                    close();
                }
            }
        }).start();
    }

    synchronized void close() {
        try {
            if(this.bufferedReader != null)
                this.bufferedReader.close();
            if(this.bufferedWriter != null)
                this.bufferedWriter.close();
            if(this.socket == null)
                System.out.println("<Server> is closed");
            else
                this.socket.close();

        } catch (IOException ex){
            ex.printStackTrace();
        }
        System.exit(0);
    }
}
