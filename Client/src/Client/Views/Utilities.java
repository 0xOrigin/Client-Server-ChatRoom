package Client.Views;

import Client.Controllers.ClientController;
import Client.Controllers.ClientControllerImp;
import Client.Views.ViewControllers.ChatRoomViewController;
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

/**
 * The class Utilities.
 */
public class Utilities {

    /**
     * Change scene.
     *
     * @param event      the event
     * @param fxmlFile   the fxml file
     * @param stageTitle the stage title
     */
    protected void changeScene(ActionEvent event, String fxmlFile, String stageTitle){
        FXMLLoader loader = getLoader(fxmlFile);
        Parent root = getParent(loader);
        setStagePreferences(root, event, stageTitle);
    }

    /**
     * Open chat room.
     *
     * @param event      the event
     * @param clientID   the client id
     * @param clientName the client name
     */
    protected void openChatRoom(ActionEvent event, String clientID, String clientName){
        FXMLLoader loader = getLoader("ChatRoomView.fxml");
        Parent root = getParent(loader);

        ClientController clientController = new ClientControllerImp(clientID, clientName, loader.getController());
        setControllerData(loader.getController(), clientController, clientID, clientName);
        registerAndStart(clientController);

        setStagePreferences(root, event, "Chat Room");
    }

    /**
     * Center on screen.
     *
     * @param stage the stage
     */
    public static void centerOnScreen(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    private FXMLLoader getLoader(String fxmlFile){
        return new FXMLLoader(Utilities.class.getResource("FXML/" + fxmlFile));
    }

    private Parent getParent(FXMLLoader loader){
        try {
            return (Parent) loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setStagePreferences(Parent root, ActionEvent event, String stageTitle){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(stageTitle);
        centerOnScreen(stage);
        stage.show();
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

    /**
     * Set alert owner.
     *
     * @param event the event
     * @param alert the alert
     */
    protected void setAlertOwner(ActionEvent event, Alert alert){
        alert.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    /**
     * Handle alert.
     *
     * @param alert     the alert
     * @param title     the title
     * @param message   the message
     * @param alertType the alert type
     */
    protected void handleAlert(Alert alert, String title, String message, Alert.AlertType alertType){
        alert.setAlertType(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

}
