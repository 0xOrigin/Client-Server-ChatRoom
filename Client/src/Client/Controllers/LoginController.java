package Client.Controllers;

public interface LoginController {

    boolean isValidAccount(String id, String password);

    String getClientName(String id);

}
