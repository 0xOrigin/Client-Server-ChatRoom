package Client.Controllers;

/**
 * The interface Client reg controller.
 */
public interface ClientRegController {

    /**
     * Register.
     *
     * @param name     the name
     * @param email    the email
     * @param password the password
     */
    void register(String name, String email, String password);

    /**
     * Send successful registration email.
     *
     * @param email    the email
     * @param id       the id
     * @param password the password
     */
    void sendSuccessfulRegistrationEmail(String email, String id, String password);

    /**
     * Is valid name.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isValidName(String name);

    /**
     * Is valid email.
     *
     * @param email the email
     * @return the boolean
     */
    boolean isValidEmail(String email);

    /**
     * Is valid password.
     *
     * @param password the password
     * @return the boolean
     */
    boolean isValidPassword(String password);

}
