package Server;

import AppDataReader.SocketConfig;
import AppDataReader.SocketConfigImp;

/**
 * The class Server runner.
 */
public class ServerRunner {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        SocketConfig socketConfig = new SocketConfigImp();
        ServerSoc serverSoc = new ServerSoc(socketConfig.getPort(), socketConfig.getBacklog(), socketConfig.getHost());
        serverSoc.start();

    }

}
