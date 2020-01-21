/*
@author Jordan Jancic
November 23, 2019
 */
package undoAndRedo;

import java.util.Scanner;

public class UndoAndRedo {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("COMP10152 - Lab#5 - Calculator using Queues and Stacks ... by Jordan Jancic - 000269500\n" +
        "Enter tokens. Legal tokens are integers, +, -, *, /, U[ndo], R[edo], E[valuate] and [e]X[it]");
        
        MQueue operations = new MQueue();                                       //Creates the queue that will hold the operation and value objects.
        
        MStack undo = new MStack();                                             //Stores the undo commands.
        
        MStack redo = new MStack();                                             //Stores the redo commands.
        
        double total = 0;                                                       //Tracks the total value of calculations.
        
        String userInput = "";                                                  //Initializes a string variable to hold user input.
        
        while(!userInput.equals("x") && !userInput.equals("X")) {               //While loop runs as long as user input is not X.
            
            Scanner input = new Scanner(System.in);                             //Scanner object created for user input.
            userInput = input.nextLine();                                       //Stores input into String variable.
            
            if(!userInput.equals("x") && !userInput.equals("X")) {              //Checks if user input is not equal to X.
                
                if(userInput.equals("u") || userInput.equals("U")) {            //Checks if user enters Undo command.
                    OperationAndValue o = new OperationAndValue('U');           //Creates Undo command as OperationAndValue object.
                    operations.enqueue(o);                                      //Enqueues the command in the operations queue.
                }
                
                else if(userInput.equals("r") || userInput.equals("R")) {       //Checks if user enters the Redo command.
                    OperationAndValue o = new OperationAndValue('R');           //Creates Redo command as OperationAndValue object.
                    operations.enqueue(o);                                      //Enqueues the command in the operations queue.
                }
                
                else if(userInput.equals("e") || userInput.equals("E")) {       //Checks if user enters the Evaluate command.
                    OperationAndValue o = new OperationAndValue('E');           //Creates Evaluate command as OperationAndValue object.
                    operations.enqueue(o);                                      //Enqueues the command in the operations queue.
                }
                
                else {
                    
                    try {
                        String[]userInputs = userInput.split(" ");              //Splits the user input string by space delimiter.

                        char charInput = userInputs[0].charAt(0);               //Saves the operator as char variable.
                        double doubleInput = Double.parseDouble(userInputs[1]);                 //Saves the value as double variable.
                        OperationAndValue o = new OperationAndValue(charInput, doubleInput);    //Creates new OperationAndValue object and passes user input values as params.   
                        operations.enqueue(o);                                                  //Enqueues the new OperationAndValue object to the operations queue.
                        
                    }
                    catch(Exception e) {                                            //Catches the error if the user inputs anything but the correct commands.
                        OperationAndValue o = new OperationAndValue(userInput);     //Takes the user input (of whatever it is) and stores it as an errored object.
                        operations.enqueue(o);                                      //Enqueues the object into the queue.
                    }  
                }
            }
        }
            
        boolean redoEmpty = false;                                              //Tracks whether or not the redo stack is empty.
        boolean undoEmpty = false;                                              //Tracks whether or not the undo stack is empty.
        boolean error = false;                                                  //Tracks whether or not an error is present.
        
        while(!operations.isEmpty()) {                                          //Iterates over the operations queue so long as it is not empty.
            
            OperationAndValue o = operations.dequeue();                         //Takes the next value out of the queue.
            
            if(o.hasOperatorOnly()) {                                           //Checks if OperationAndValue object contains only an operator.
                
                if(o.getOperator() == 'U') {                                    //Checks if the operator is the Undo command.

                    try {
                        
                        OperationAndValue u = undo.pop();                       //If it is an Undo command, we pop off the stores undo command in the stack.
                    
                        if(u.getOperator() == '+') {                            //Checks if the undo is of type add.
                            total -= u.getValue();                              //Gets the value of the undo and performs the opposite function on the total.
                            redo.push(u);                                       //Stores the undo value in the redo stack.
                        }
                        else if(u.getOperator() == '-') {                       //Checks if the undo is of type subtract
                            total += u.getValue();                              //Gets the value of the undo and performs the opposite function on the total.
                            redo.push(u);                                       //Stores the undo value in the redo stack.
                        }
                        else if(u.getOperator() == '*') {                       //Checks if the undo is of type multiply.
                            total = total / u.getValue();                       //Gets the value of the undo and performs the opposite function on the total.
                            redo.push(u);                                       //Stores the undo value in the redo stack.
                        }
                        else if(u.getOperator() == '/') {                       //Checks if the undo is of type divide.
                            total = total * u.getValue();                       //Gets the value of the undo and performs the opposite function on the total.
                            redo.push(u);                                       //Stores the undo value in the redo stack.
                        }
                    }
                    catch(Exception e) {                                        //If no undo value is present, then we enable the undoEmpty boolean as true.
                        
                        undoEmpty = true;                                       //Sets Undo empty as true.
                        redoEmpty = false;                                      //Sets Redo empty as false.
                        error = false;                                          //Sets error as false.
                        
                    } 
                }
                else if(o.getOperator() == 'R') {                               //Checks if the operator is the Redo command.

                    try {
                        OperationAndValue r = redo.pop();                       //If it is an Redo command, we pop off the stores Redo command in the stack.
                    
                        if(r.getOperator() == '+') {                            //Checks if the Redo is of type add.
                            total += r.getValue();                              //Gets the value of the Redo and performs the opposite function on the total.
                            undo.push(r);                                       //Stores the Redo value in the redo stack.
                        }
                        else if(r.getOperator() == '-') {                       //Checks if the Redo is of type subtract
                            total -= r.getValue();                              //Gets the value of the Redo and performs the opposite function on the total.
                            undo.push(r);                                       //Stores the Redo value in the redo stack.
                        }
                        else if(r.getOperator() == '*') {                       //Checks if the Redo is of type multiply.
                            total = total * r.getValue();                       //Gets the value of the Redo and performs the opposite function on the total.
                            undo.push(r);                                       //Stores the Redo value in the redo stack.
                        }
                        else if(r.getOperator() == '/') {                       //Checks if the Redo is of type divide.
                            total = total / r.getValue();                       //Gets the value of the Redo and performs the opposite function on the total.
                            undo.push(r);                                       //Stores the Redo value in the redo stack.
                        }
                        
                    }
                    catch(Exception e) {                                        //If no Redo value is present, then we enable the redoEmpty boolean as true.
                        
                        undoEmpty = false;                                      //Sets Undo empty as false.
                        redoEmpty = true;                                       //Sets Redo empty as true.
                        error = false;                                          //Sets Undo empty as false.
                        
                    }               
                }
            }
            else if(o.getError()) {                                             //Checks if OperationAndValue object is error type.
                undoEmpty = false;                                              //Sets Undo empty as false.
                redoEmpty = false;                                              //Sets Redo empty as false.
                error = true;                                                   //Sets Undo empty as true.
            }
            
            //Else, we assume the value will be valid.
            else {  
                if(o.getOperator() == '+') {                                    //Checks if the OperatorAndValue object is of type add.
                    total += o.getValue();                                      //Gets the value of the OperatorAndValue object and performs the function on the total.
                    undo.push(o);                                               //Pushes the value onto the Undo stack.
                }
                else if(o.getOperator() == '-') {                               //Checks if the OperatorAndValue object is of type subtract.
                    total = total - o.getValue();                               //Gets the value of the OperatorAndValue object and performs the function on the total.
                    undo.push(o);                                               //Pushes the value onto the Undo stack.
                }
                else if(o.getOperator() == '*') {                               //Checks if the OperatorAndValue object is of type multiply.
                    total = total * o.getValue();                               //Gets the value of the OperatorAndValue object and performs the function on the total.
                    undo.push(o);                                               //Pushes the value onto the Undo stack.
                }
                else if(o.getOperator() == '/') {                               //Checks if the OperatorAndValue object is of type divide.
                    total = total / o.getValue();                               //Gets the value of the OperatorAndValue object and performs the function on the total.
                    undo.push(o);                                               //Pushes the value onto the Undo stack.
                }
            }
            
            System.out.println(o.toString());                                   //Prints the value of the OperatorAndValue object.
            
            if(undoEmpty) {                                                     //Checks if the undoEmpty boolean flag has been set to true.
                System.out.println("ERROR --> Undo is empty - Can't Undo");     //Outputs error message for no undos available.
            }
            
            else if(redoEmpty) {                                                //Checks if the redoEmpty boolean flag has been set to true.
                System.out.println("ERROR --> Redo is empty - Can't Redo");     //Outputs error message for no redos available.
            }
            
            else if(error) {                                                    //Checks if the error boolean flag has been set to true.
                System.out.println("ERROR --> Invalid Token - line ignored.");  //Outputs error message for invalid input.
            }
            
            if(!error) {
                System.out.println("Total = " + total);                         //If no error present, output total normally.
            }
            
            redoEmpty = false;                                                  //Resets boolean for next iterations.
            undoEmpty = false;                                                  //Resets boolean for next iterations.
            error = false;                                                      //Resets boolean for next iterations.
        }
    }
}