/*
Jordan Jancic
November 23, 2019
 */
package undoAndRedo;
/**
 * @author Jordan Jancic
 */
public class OperationAndValue {
    
    private final char operator;                                                //Stores the operator value.
    private final double value;                                                 //Stores the numerical value.
    private final boolean hasOperatorOnly;                                      //Stores the boolean value of whether or not the object is just a operator, or both operator and value.
    private final String stringValue;                                           //Stores the string value of any user input that is not a valid command.
    private final boolean isError;                                              //Is true if the value is anything but a valid command or input.
    
    /*
    This constructor only gets called if
    a valid operator is passed.
    @param operation, the valid operator.
    */
    public OperationAndValue(char operation) {
        
        this.operator = operation;                                              //Sets operator value to passed param.
        this.value = 0;                                                         //Value is set to zero since only char is coming through as input.
        this.hasOperatorOnly = true;                                            //Is true because only operator is being passed.
        this.stringValue = "";                                                  //String value is empty because only char value is passed.
        this.isError = false;
    }
    
    /*
    This constructor only gets called if
    a valid operator and value is passed.
    @param operation, the valid operator.
    @param value, the valid double value
    */
    public OperationAndValue(char operation, double value) {
        
        this.operator = operation;                                              //Sets operator value to passed param.
        this.value = value;                                                     //Value is set to value of what ever is passed through as a param.
        this.hasOperatorOnly = false;                                           //Sets to false as there is more than just an operator if this constructor is triggered.
        this.stringValue = "";                                                  //Set to empty string as not needed.
        this.isError = false;                                                   //Set to false as this should be a successful value entry.
    }
    
    /*
    This constructor only gets called if
    a single string value is passed.
    This will only be called if the 
    input is not valid.
    @param s, the string value.
    */
    public OperationAndValue(String s) {
        this.stringValue = s;                                                   //String value is set to s and s represents whatever invalid input the user has entered.
        this.operator = '#';                                                    //Sets operator to a unusable placeholder value.
        this.value = 0;                                                         //Sets value to zero as this is an errored object.
        this.hasOperatorOnly = false;                                           //Sets to false as there is no operator present when this constructor is called.
        this.isError = true;                                                    //Set to true because if this constructor is called, it is an error.
    }
    
    /*
    Returns the object's operator value.
    */
    public char getOperator() {
        return operator;
    }
    
    /*
    Returns the object's numerical value.
    */
    public double getValue() {
        return value;
    }
    
    /*
    Returns true or false based on 
    whether or not the object has
    an operator.
    */
    public boolean hasOperatorOnly() {
        return hasOperatorOnly;
    }
    
    /*
    Returns whether or not the object
    is in an error state (i.e., constructed
    with only a string).
    */
    public boolean getError() {
        return isError;
    }
    
    /*
    Returns the value of the object
    based on whether or not the object
    has an operator, has both operator and
    value, or is a string/error.
    */
    @Override
    public String toString() {
        if(hasOperatorOnly) {
            return operator + "";                                               //Returns only the valid operator.
        }
        else if(!hasOperatorOnly && !isError) { 
            return operator + " " + value;                                      //Returns valid operator and value.
        }
        else {
            return stringValue + "";                                            //Returns user input as string if input is error.
        }
        
    }
    
}