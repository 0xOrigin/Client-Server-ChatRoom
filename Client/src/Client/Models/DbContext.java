package Client.Models;

import Client.Models.Database.Client;

/**
 * The interface Db context.
 *
 * @author 0xOrigin
 */
public interface DbContext {

    /**
     * Gets client model.
     *
     * @return the client model
     */
    Client getClientModel();
    
}
