import java.util.Arrays;

public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        int l = leftChild(i);
        int r = rightChild(i);
        boolean lpos = false;
        boolean rpos = false;
        if (l>=size && r>= size) {
            return;
        }
        if (!(l>=size) && heap[i]<heap[l]) {
            lpos = true;
        }
        if (!(r>=size) && heap[i]<heap[r]) {
            rpos = true;
        }
        if (!rpos && !lpos) {
            return;
        }
        if (rpos && lpos) {
            if (heap[l] <= heap[r]) {
                swap(heap, i, r);
                i = r;
            }
            else {
                swap(heap,i,l);
                i = l;
            }
        }
        else if (rpos) {
            swap(heap, i, r);
            i = r;
        }
        else if (lpos) {
            swap(heap,i,l);
            i = l;
        }
        sink(i);
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        if (heap.length == 0 || heap.length == 1) {
            return heap;
        }
        else if (heap.length == 2) {
            if (heap[0] <= heap[1]) {
                return heap;
            }
            else {
                swap(heap,0,1);
                return heap;
            }
        }
        else {
            for (int i = size - 1; i > 0; i--) {
                swap(heap,i,0);
                size--;
                sink(0);
            }
            return heap;
        }
    }
}
