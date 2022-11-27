package Client.Controllers;

import AppDataReader.EmailConfigImp;
import Client.Models.DbContext;
import Email.SendEmail;


public class ClientRegControllerImp implements ClientRegController {

    protected final DbContext dbContext;

    public ClientRegControllerImp(DbContext dbContext){
        this.dbContext = dbContext;
    }

    @Override
    public void register(String name, String email, String password){
        String id = new DataGenerator().generateID(name);
        this.dbContext.getClientModel().insert(id, name, email,  password);
        this.sendSuccessfulRegistrationEmail(email, id, password);
    }

    @Override
    public void sendSuccessfulRegistrationEmail(String email, String id, String password){

        String messageSubject = "Successful Registration";
        String messageText = "Your ID is " + id + "\n"
                + "Your Password is " + password + " .";

        SendEmail.setDefaultConfig(new EmailConfigImp()).send(email, messageSubject, messageText);
    }

}