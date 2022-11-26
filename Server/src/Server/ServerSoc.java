package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class ServerSoc{

    private ServerSocket serverSocket;
    private ExecutorService executorService;

    public ServerSoc(int port){
        try {
            this.serverSocket = new ServerSocket(port);
            this.executorService = Executors.newCachedThreadPool();
        } catch (IOException e) {
            this.close();
        }
    }

    public void start(){
        try {
            System.out.println("[!] Server started.");
            while(!this.serverSocket.isClosed()){
                Socket socket = this.serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);

                this.executorService.execute(clientHandler);
            }
        } catch (IOException ex){
            this.close();
        }
    }

    public void close(){
        try {
            if(this.serverSocket != null)
                this.serverSocket.close();
            if(this.executorService != null)
                this.executorService.shutdownNow();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
