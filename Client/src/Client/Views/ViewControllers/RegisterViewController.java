package Client.Views.ViewControllers;

import Client.Controllers.ClientRegController;
import Client.Controllers.ClientRegControllerImp;
import Client.Models.Client_DbContext;
import Client.Views.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 * The class Register view controller.
 */
public class RegisterViewController extends Utilities {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button backButton;
    @FXML
    private TextField emailField;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField nameField;
    @FXML
    private Label nameLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button registerButton;


    /**
     * On back button action.
     *
     * @param event the event
     */
    @FXML
    void onBackButtonAction(ActionEvent event) {
        super.changeScene(event, "MainView.fxml", "Client-Server Chat Room");
    }

    /**
     * Redirect to log in.
     *
     * @param event the event
     */
    void redirectToLogin(ActionEvent event){
        super.changeScene(event, "LoginView.fxml", "Login");
    }

    /**
     * Validate name.
     *
     * @param controller the controller
     * @param nameField  the name field
     * @return the boolean
     */
    boolean validateName(ClientRegController controller, TextField nameField){
        return controller.isValidName(nameField.getText().trim());
    }

    /**
     * Validate email.
     *
     * @param controller the controller
     * @param emailField the email field
     * @return the boolean
     */
    boolean validateEmail(ClientRegController controller, TextField emailField){
        return controller.isValidEmail(emailField.getText().trim());
    }

    /**
     * Validate password.
     *
     * @param controller    the controller
     * @param passwordField the password field
     * @return the boolean
     */
    boolean validatePassword(ClientRegController controller, TextField passwordField){
        return controller.isValidPassword(passwordField.getText().trim());
    }

    /**
     * Do registration.
     *
     * @param controller    the controller
     * @param nameField     the name field
     * @param emailField    the email field
     * @param passwordField the password field
     */
    void doRegistration(ClientRegController controller, TextField nameField, TextField emailField, TextField passwordField){
        controller.register(nameField.getText().trim(), emailField.getText().trim(), passwordField.getText().trim());
    }

    /**
     * On register button action.
     *
     * @param event the event
     */
    @FXML
    void onRegisterButtonAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        super.setAlertOwner(event, alert);

        ClientRegController clientRegController = new ClientRegControllerImp(new Client_DbContext());

        if(nameField.getText().trim().isBlank())
            handleAlert(alert, "Name field", "Name field is required and cannot be blank!", Alert.AlertType.ERROR);
        else if(emailField.getText().trim().isBlank())
            handleAlert(alert, "Email field", "Email field is required and cannot be blank!", Alert.AlertType.ERROR);
        else if(passwordField.getText().trim().isBlank())
            handleAlert(alert, "Password field", "Password field is required and cannot be blank!", Alert.AlertType.ERROR);
        else {
            boolean nameResult = validateName(clientRegController, nameField);
            boolean emailResult = validateEmail(clientRegController, emailField);
            boolean passwordResult = validatePassword(clientRegController, passwordField);

            if(nameResult && emailResult && passwordResult) {
                handleAlert(alert, "Successful Registration", "You will receive an email with your ID and Password.", Alert.AlertType.INFORMATION);
                doRegistration(clientRegController, nameField, emailField, passwordField);
                redirectToLogin(event);
            }
            else
                handleAlert(alert, "Unsuccessful Registration", "Please Enter a valid Name, Email, and Password!", Alert.AlertType.ERROR);
        }
    }
}
