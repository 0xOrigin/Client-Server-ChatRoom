package Client.Controllers;

public interface ClientController {

    void listenToBroadcast();

    void sendRegistrationMessage();

    void sendMessage(String message);

}
