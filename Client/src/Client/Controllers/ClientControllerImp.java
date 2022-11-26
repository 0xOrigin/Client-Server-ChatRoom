package Client.Controllers;

import AppDataReader.SocketConfig;
import AppDataReader.SocketConfigImp;

public class ClientControllerImp implements ClientController {

    private final ClientSoc clientSoc;

    public ClientControllerImp(String clientName){
        SocketConfig socketConfig = new SocketConfigImp();
        this.clientSoc = new ClientSoc(socketConfig.getHost(), socketConfig.getPort(), clientName);
    }

    @Override
    public void listenToBroadcast(){
        this.clientSoc.listenToBroadcast();
    }

    @Override
    public void sendRegistrationMessage(){
        this.clientSoc.sendRegistrationMessage();
    }

    @Override
    public void sendMessage(String message){
        this.clientSoc.sendMessage(message);
    }

    @Override
    public void sendToBroadcast(){
        this.clientSoc.sendToBroadcast();
    }

}
