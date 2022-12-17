package Client.Controllers;

/**
 * The interface Login controller.
 */
public interface LoginController {

    /**
     * Is valid account.
     *
     * @param id       the id
     * @param password the password
     * @return the boolean
     */
    boolean isValidAccount(String id, String password);

    /**
     * Gets client name.
     *
     * @param id the id
     * @return the client name
     */
    String getClientName(String id);

}
