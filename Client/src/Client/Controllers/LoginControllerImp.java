package Client.Controllers;

import Client.Models.DbContext;
import Client.Models.Enum.Column;

import java.util.Arrays;

public class LoginControllerImp implements LoginController{

    protected final DbContext dbContext;

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
