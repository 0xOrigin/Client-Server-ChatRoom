package Client.Controllers;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSoc {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Scanner scanner;
    private String name;

    public ClientSoc(String host, int port, String name){
        try{
            this.socket = new Socket(host, port);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.scanner = new Scanner(System.in);
            this.name = name;
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
        this.sendMessage(this.name);
    }

    void sendToBroadcast(){
        try {
            while(this.socket.isConnected()){
                String message = this.generateClientMessage(scanner.nextLine());
                this.sendMessage(message);
            }
        } catch (NullPointerException | IllegalStateException exception){
            close();
        }
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
                        printMessage(message);
                    }
                } catch (IOException | NullPointerException ex){
                    close();
                } finally {
                    close();
                }
            }
        }).start();
    }
    private String generateClientMessage(String message){
        return "[!] " + this.name + " : " + message;
    }

    private void printMessage(String message){
        if(message != null)
            System.out.println(message);
    }

    synchronized void close() {
        try {
            if(this.scanner != null)
                this.scanner.close();
            if(this.bufferedReader != null)
                this.bufferedReader.close();
            if(this.bufferedWriter != null)
                this.bufferedWriter.close();
            if(this.socket == null)
                System.out.println("[!] Server is closed");
            else
                this.socket.close();

        } catch (IOException ex){
            ex.printStackTrace();
        }
        System.exit(0);
    }
}
