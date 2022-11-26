package Client.Models.Database;

import Client.Models.Enum.Column;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ORM.*;
import ORM.Utilities.*;

/**
 *
 * @author xorigin
 */
abstract class ModelUtility {
    
    private final Adapter modelInstance;
    private final Enum primaryKey;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    
    protected ModelUtility(Adapter adapter){
    
        this.modelInstance = adapter;
        this.primaryKey = adapter.getPrimaryKeyColumnName();
    }
    
    
    public Map<Enum, Object> getInfo(List<Enum> fields, String identifier){
        
        Map<Enum, Object> info = new HashMap<>();
        
        this.selectQuery = new SelectBuilder(Arrays.asList(fields),
                                        this.modelInstance.getTableName())
                                        .where(this.primaryKey, "=", identifier).build();
        
        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try { 
        
            if(!this.resource.isResultSetEmpty())
                for(Enum field : fields)
                    info.put(field, this.resultSet.getObject(field.name()));
            
        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }
        
        return info;
    }

    
    public boolean isPasswordMatch(String identifier, String password){
    
        boolean isMatch = false;
        
        this.selectQuery = new SelectBuilder(Arrays.asList(Column.Password),
                                        this.modelInstance.getTableName())
                                        .where(this.primaryKey, "=", identifier)
                                        .build();

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try { 
        
            if(!this.resource.isResultSetEmpty())
                isMatch = this.resultSet.getString(Column.Password.name()).equals(password);
            
        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return isMatch;
    }
    
}
