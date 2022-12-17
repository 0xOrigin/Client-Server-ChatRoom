package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * The class Server soc.
 */
public class ServerSoc{

    private ServerSocket serverSocket;
    private ExecutorService executorService;

    /**
     * Instantiates a new Server soc.
     *
     * @param port    the port
     * @param backlog the backlog
     * @param host    the host
     */
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

    /**
     * Start.
     */
    public void start(){
        try {
            if(this.serverSocket != null) {
                System.out.println("<Server> started on " + this.serverSocket.getInetAddress() +
                        ":" + this.serverSocket.getLocalPort() + "\n");
//                System.out.println(this.serverSocket);
            }
            while(!this.serverSocket.isClosed()){
                Socket socket = this.serverSocket.accept();
//                System.out.println(socket + " From server");

                ClientHandler clientHandler = new ClientHandler(socket);

                this.executorService.execute(clientHandler);
            }
        } catch (IOException ex){
            this.close();
        } catch (NullPointerException ex){
            System.out.println("<Server> cannot be started, the address and port are already taken.");
            this.close();
        }
    }

    /**
     * Close.
     */
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
