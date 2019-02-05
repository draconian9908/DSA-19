package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxi;

    public MyStack() {
        ll = new LinkedList<>();
        maxi = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        if (maxi.isEmpty()) {
            maxi.addFirst(e);
        }
        else {
            if (e > maxi.getFirst()) {
                maxi.addFirst(e);
            }
            else {
                maxi.addFirst(maxi.getFirst());
            }
        }
        ll.addFirst(e);
    }

    @Override
    public Integer pop() {
        maxi.removeFirst();
        Integer pop = ll.removeFirst();
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return maxi.getFirst();
    }
}
