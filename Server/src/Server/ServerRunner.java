package Server;

import AppDataReader.SocketConfig;
import AppDataReader.SocketConfigImp;

public class ServerRunner {

    public static void main(String[] args) {

        SocketConfig socketConfig = new SocketConfigImp();
        ServerSoc serverSoc = new ServerSoc(socketConfig.getPort());
        serverSoc.start();

    }

}
