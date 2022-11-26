package Client.Models.Database;

import Client.Models.Enum.Column;
import Client.Models.Enum.Table;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.sql.ResultSet;
import ORM.*;
import ORM.Utilities.*;

public class Client extends ModelUtility {

    private final Adapter clientModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;

    public Client(Adapter adapter){

        super(adapter);
        this.clientModel = adapter;
    }

    public void insert(String id, String name, String email, String password){

        List<Enum> fields = Arrays.asList(Column.ID, Column.Name, Column.Email, Column.Password);

        List<Object> values = Arrays.asList(id, name, email, password);

        this.clientModel.insert(fields, values);
    }

    public void update(List<Enum> fields, List<Object> values, String id){
        this.clientModel.update(fields, values, this.clientModel.Where(Column.ID, "=", id));
    }

    public void delete(String id){
        this.clientModel.delete(this.clientModel.Where(Column.ID, "=", id));
    }

    public boolean isClientExists(String id){

        boolean isExists = false;

        this.selectQuery = new SelectBuilder(Arrays.asList(this.clientModel.Aggregate("count", "", Column.ID)),
                Table.Client)
                .where(Column.ID, "=", id).build();

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try {

            if(!this.resource.isResultSetEmpty())
                isExists = (this.resultSet.getInt(1) == 1);

        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return isExists;
    }

    public boolean isValidAccount(String id, String password){

        if(!this.isClientExists(id))
            return false;

        return super.isPasswordMatch(id, password);
    }
}
