package ORM;

import java.util.List;
/**
 *
 * @author xorigin
 */

public class SelectQuery {
    
    private final String table;
    private final List<Object> fields;
    private final String where, orderBy, limit;
    
    public SelectQuery(SelectBuilder builder){
        
        this.table = builder.getTableName();
        this.fields = builder.getFields();
        this.where = builder.getWhereCondition();
        this.orderBy = builder.getOrderBy();
        this.limit = builder.getLimit();
    }

    
    @Override
    public String toString(){
    
        return "SELECT " + this.fields.toString().replaceAll("[\\[\\]]", "") + " FROM " + this.table +
                (!this.where.isBlank() ? " WHERE ( " + this.where + " ) " : "") +
                (!this.orderBy.isBlank() ? " ORDER BY " + this.orderBy : "") +
                (!this.limit.isBlank() ? " LIMIT " + this.limit : "");   
    }
    
}


