package Client.Models;

import Client.Models.Database.Client;
import Client.Models.Enum.Column;
import Client.Models.Enum.Table;
import ORM.*;


/**
 *
 * @author xorigin
 */
public class Client_DbContext implements DbContext{

    private final Client clientModel;
    
    
    public Client_DbContext(){

        this.clientModel = new Client(new SQLiteAdapter(Table.Client, Column.ID));
    }
    

    @Override
    public Client getClientModel(){
    
        return this.clientModel;
    }
    
}
