package undoAndRedo;

import java.util.LinkedList;
/**
 * @author Mark Yendt
 */
public class MStack {
    
    private LinkedList<OperationAndValue> s = new LinkedList<>();
    
    public void push(OperationAndValue e) {  
        s.addLast(e); 
    }
    
    public OperationAndValue pop() {  
        return s.removeLast();
    }
        
    public OperationAndValue peek() {
        return s.getLast();
    }
        
    public int size() {
        return s.size(); 
    }
        
    public boolean isEmpty() {
        return s.isEmpty();
    }
}