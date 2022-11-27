package Client.Views;

import Client.Controllers.ClientController;
import Client.Controllers.ClientControllerImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Utilities {

    protected void changeScene(ActionEvent event, String fxmlFile, String stageTitle){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/" + fxmlFile));
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(stageTitle);
            centerOnScreen(stage);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void centerOnScreen(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    protected void openChatRoom(ActionEvent event, String clientID, String clientName){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ChatRoomView.fxml"));
            Parent root = (Parent) loader.load();

            ClientController clientController = new ClientControllerImp(clientID, clientName, loader.getController());
            setControllerData(loader.getController(), clientController, clientID, clientName);

            registerAndStart(clientController);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Chat Room");
            centerOnScreen(stage);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setControllerData(ChatRoomViewController controller, ClientController clientController, String id, String name){
        controller.setClientID(id);
        controller.setClientName(name);
        controller.setClientController(clientController);
    }

    private void registerAndStart(ClientController clientController){
        clientController.sendRegistrationMessage();
        clientController.listenToBroadcast();
    }

    void handleAlert(Alert alert, String title, String message, Alert.AlertType alertType){
        alert.setAlertType(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
