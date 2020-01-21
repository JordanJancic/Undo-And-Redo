package undoAndRedo;

import java.util.LinkedList;
/**
 * @author Mark Yendt
 */
public class MQueue {
    
    private LinkedList<OperationAndValue> q = new LinkedList<>();
    
    public void enqueue(OperationAndValue e) {
        q.addLast(e);
    }
    
    public OperationAndValue dequeue() {
        return q.removeFirst();
    }
    
    public OperationAndValue peek() {
        return q.getFirst();
    }
    
    public int size() {
        return q.size();
    }
    
    public boolean isEmpty() {
        return q.isEmpty();
    }
    
}