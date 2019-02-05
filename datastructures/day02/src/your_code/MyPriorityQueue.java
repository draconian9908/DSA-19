package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private LinkedList<Integer> ll;

    public MyPriorityQueue() {
        ll = new LinkedList<>();
    }

    public void enqueue(int item) {
        if (ll.size() == 0) {
            ll.add(item);
        }
        else if (item >= ll.getLast()) {
            ll.add(item);
        }
        else {
            for (int i = ll.size()-1; i >= 0; i--) {
                if (item >= ll.get(i)) {
                    ll.add(i, item);
                    break;
                }
                else if (item < ll.get(i) && i == 0) {
                    ll.add(0, item);
                    break;
                }
            }
        }
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        int maxi = ll.removeLast();
        return maxi;
    }

}