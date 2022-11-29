package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class ServerSoc{

    private ServerSocket serverSocket;
    private ExecutorService executorService;

    public ServerSoc(int port, int backlog, String host){
        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            String hostName = inetAddress.getHostName();
            this.serverSocket = new ServerSocket(port, backlog, inetAddress);
            this.executorService = Executors.newCachedThreadPool();
        } catch (IOException e) {
            this.close();
        }
    }

    public void start(){
        try {
            System.out.println("<Server> started.");
            while(!this.serverSocket.isClosed()){
                Socket socket = this.serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);

                this.executorService.execute(clientHandler);
            }
        } catch (IOException ex){
            this.close();
        } catch (NullPointerException ex){
            System.out.println("<Server> is already started in another process with the same address and port.");
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
