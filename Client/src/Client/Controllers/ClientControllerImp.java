package Client.Controllers;

import AppDataReader.SocketConfig;
import AppDataReader.SocketConfigImp;
import Client.Views.ViewControllers.ChatRoomViewController;

public class ClientControllerImp implements ClientController {

    private final ClientSoc clientSoc;

    public ClientControllerImp(String clientID, String clientName, ChatRoomViewController controller){
        SocketConfig socketConfig = new SocketConfigImp();
        this.clientSoc = new ClientSoc(socketConfig.getHost(), socketConfig.getPort(), clientID, clientName, controller);
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

}
