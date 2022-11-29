package Client.Views;

import Client.Controllers.LoginController;
import Client.Controllers.LoginControllerImp;
import Client.Models.Client_DbContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class LoginViewController extends Utilities {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField idField;
    @FXML
    private Label idLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button backButton;


    @FXML
    void onBackButtonAction(ActionEvent event) {
        super.changeScene(event, "MainView.fxml", "Client-Server Chat Room");
    }

    @FXML
    void onLoginButtonAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        LoginController loginController = new LoginControllerImp(new Client_DbContext());
        String id = idField.getText().trim();
        String password = passwordField.getText();

        if(id.isBlank())
            handleAlert(alert, "ID field", "ID field is required and cannot be blank!", Alert.AlertType.ERROR);
        else if(password.isBlank())
            handleAlert(alert, "Password field", "Password field is required and cannot be blank!", Alert.AlertType.ERROR);
        else {
            boolean result = isValidAccount(loginController, idField, passwordField);
            if(result) {
                handleAlert(alert, "Successful Login", "Welcome to the Chat Room!", Alert.AlertType.INFORMATION);
                super.openChatRoom(event, id, getClientName(loginController, id));
            }
            else
                handleAlert(alert, "Unsuccessful Login", "Please Enter a valid ID and Password!", Alert.AlertType.ERROR);
        }
    }

    boolean isValidAccount(LoginController controller, TextField idField, TextField passwordField){
        return controller.isValidAccount(idField.getText().trim(), passwordField.getText().trim());
    }

    String getClientName(LoginController controller, String clientID){
        return controller.getClientName(clientID);
    }
}