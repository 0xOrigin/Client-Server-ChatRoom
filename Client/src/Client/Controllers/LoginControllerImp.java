package Client.Controllers;

import Client.Models.DbContext;
import Client.Models.Enum.Column;

import java.util.Arrays;

/**
 * The class Login controller imp.
 */
public class LoginControllerImp implements LoginController{

    /**
     * The Db context.
     */
    protected final DbContext dbContext;

    /**
     * Instantiates a new Login controller imp.
     *
     * @param dbContext the db context
     */
    public LoginControllerImp(DbContext dbContext){
        this.dbContext = dbContext;
    }

    @Override
    public boolean isValidAccount(String id, String password){
        return this.dbContext.getClientModel().isValidAccount(id, password);
    }

    @Override
    public String getClientName(String id){
        return String.valueOf(this.dbContext.getClientModel().getInfo(Arrays.asList(Column.Name), id).get(Column.Name));
    }
}
