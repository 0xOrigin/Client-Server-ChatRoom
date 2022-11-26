package ORM;

/**
 *
 * @author xorigin
 */
abstract class DML {
    
    protected DML(){
    
    }
    
    public String Where(Enum field, String operator, Object value){
    
        String statement = " ( " + field.name() + " " + operator + " ";

        if(value instanceof String && !String.valueOf(value).toLowerCase().contains("select"))
            statement += "\'" + value + "\'";
        else if(String.valueOf(value).toLowerCase().contains("select")) // To support nested Select queries.
            statement += "( " + value + " )";
        else
            statement += value;
        
        return statement + " )";
    }
    
    public String Aggregate(String functionName, String optionalAttribute, Enum field){
    
        return functionName.toUpperCase() + "(" + optionalAttribute.toUpperCase() + " " + field.name() + ")";
    } 
    
    public String ControlPrecedence(String character){
    
        return character;
    }
    
    public String Operator(String operator){
    
        return " " + operator.toUpperCase() + " ";
    }
    
}
