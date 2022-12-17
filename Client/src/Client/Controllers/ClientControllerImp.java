package Client.Controllers;

import AppDataReader.SocketConfig;
import AppDataReader.SocketConfigImp;
import Client.Views.ViewControllers.ChatRoomViewController;

/**
 * The class Client controller imp.
 */
public class ClientControllerImp implements ClientController {

    private final ClientSoc clientSoc;

    /**
     * Instantiates a new Client controller imp.
     *
     * @param clientID   the client id
     * @param clientName the client name
     * @param controller the controller
     */
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
