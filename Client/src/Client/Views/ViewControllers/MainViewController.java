package Client.Views.ViewControllers;

import Client.Views.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * The class Main view controller.
 */
public class MainViewController extends Utilities {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;


    /**
     * On login button action.
     *
     * @param event the event
     */
    @FXML
    void onLoginButtonAction(ActionEvent event) {
        super.changeScene(event, "LoginView.fxml", "Login");
    }

    /**
     * On register button action.
     *
     * @param event the event
     */
    @FXML
    void onRegisterButtonAction(ActionEvent event) {
        super.changeScene(event, "RegisterView.fxml", "Client Registration");
    }
}