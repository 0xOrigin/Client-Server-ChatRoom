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

    @FXML
    void onDisconnectButtonAction(ActionEvent event) {
        sendMessage("/quit");
    }

    @FXML
    void onMessageFieldKeyPress(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER))
            onSendButtonAction(null);
    }

    @FXML
    void onSendButtonAction(ActionEvent event) {
        String message = this.messageField.getText();
        sendMessage(message);
        appendMessageToMessageArea(prepareMyMessage(message));
        this.messageField.clear();
    }

    void sendMessage(String message){
        String generatedMessage = "<" + clientName.getText() + "(" + clientID.getText() + ")>: " + message;
        this.clientController.sendMessage(generatedMessage);
    }

    String prepareMyMessage(String message){
        return "<You>: " + message;
    }

    public void appendMessageToMessageArea(String message){
        this.messagesArea.appendText(message + "\n");
    }

    public void setClientName(String name){
        this.clientName.setText(name);
    }

    public void setClientID(String id){
        this.clientID.setText(id);
    }

    public void setClientController(ClientController controller){
        this.clientController = controller;
    }
}
