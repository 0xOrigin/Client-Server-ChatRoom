package Client.Controllers;

/**
 * The interface Client controller.
 */
public interface ClientController {

    /**
     * Listen to broadcast.
     */
    void listenToBroadcast();

    /**
     * Send registration message.
     */
    void sendRegistrationMessage();

    /**
     * Send message.
     *
     * @param message the message
     */
    void sendMessage(String message);

}
