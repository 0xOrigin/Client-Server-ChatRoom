package Client.Controllers;

public interface ClientRegController {

    void register(String name, String email, String password);

    void sendSuccessfulRegistrationEmail(String email, String id, String password);

    boolean isValidName(String name);

    boolean isValidEmail(String email);

    boolean isValidPassword(String password);

}
