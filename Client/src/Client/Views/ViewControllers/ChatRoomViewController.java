package Client.Views.ViewControllers;

import Client.Controllers.ClientController;
import Client.Views.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * The class Chat room view controller.
 */
public class ChatRoomViewController extends Utilities {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label clientID;
    @FXML
    private Label clientName;
    @FXML
    private Label idLabel;
    @FXML
    private Button disconnectButton;
    @FXML
    private TextField messageField;
    @FXML
    private TextArea messagesArea;
    @FXML
    private Label nameLabel;
    @FXML
    private Button sendButton;

    private ClientController clientController;

    /**
     * On disconnect button action.
     *
     * @param event the event
     */
    @FXML
    void onDisconnectButtonAction(ActionEvent event) {
        sendMessage("/quit");
    }

    /**
     * On message field key press.
     *
     * @param event the event
     */
    @FXML
    void onMessageFieldKeyPress(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER))
            onSendButtonAction(null);
    }

    /**
     * On send button action.
     *
     * @param event the event
     */
    @FXML
    void onSendButtonAction(ActionEvent event) {
        String message = this.messageField.getText();
        sendMessage(message);
        appendMessageToMessageArea(prepareMyMessage(message));
        this.messageField.clear();
    }

    /**
     * Send message.
     *
     * @param message the message
     */
    void sendMessage(String message){
        String generatedMessage = "<" + clientName.getText() + "(" + clientID.getText() + ")>: " + message;
        this.clientController.sendMessage(generatedMessage);
    }

    /**
     * Prepare my message.
     *
     * @param message the message
     * @return the string
     */
    String prepareMyMessage(String message){
        return "<You>: " + message;
    }

    /**
     * Append message to message area.
     *
     * @param message the message
     */
    public void appendMessageToMessageArea(String message){
        this.messagesArea.appendText(message + "\n");
    }

    /**
     * Set client name.
     *
     * @param name the name
     */
    public void setClientName(String name){
        this.clientName.setText(name);
    }

    /**
     * Set client id.
     *
     * @param id the id
     */
    public void setClientID(String id){
        this.clientID.setText(id);
    }

    /**
     * Set client controller.
     *
     * @param controller the controller
     */
    public void setClientController(ClientController controller){
        this.clientController = controller;
    }
}
