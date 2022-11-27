package Client.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainViewController extends Utilities {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;


    @FXML
    void onLoginButtonAction(ActionEvent event) {
        super.changeScene(event, "LoginView.fxml", "Login");
    }

    @FXML
    void onRegisterButtonAction(ActionEvent event) {
        super.changeScene(event, "RegisterView.fxml", "Client Registration");
    }
}