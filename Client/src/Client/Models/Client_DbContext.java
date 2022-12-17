package Client.Models;

import Client.Models.Database.Client;
import Client.Models.Enum.Column;
import Client.Models.Enum.Table;
import ORM.*;


/**
 * The class Client db context.
 *
 * @author 0xOrigin
 */
public class Client_DbContext implements DbContext{

    private final Client clientModel;


    /**
     * Instantiates a new Client db context.
     */
    public Client_DbContext(){

        this.clientModel = new Client(new SQLiteAdapter(Table.Client, Column.ID));
    }
    

    @Override
    public Client getClientModel(){
    
        return this.clientModel;
    }
    
}
