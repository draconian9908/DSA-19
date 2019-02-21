import java.util.*;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        System.out.println("Slow: " + Arrays.toString(out));
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     * want to put less than med in maxPQ, since max will be largest of the smallest, so middle; vice versa
     */
    public static double[] runningMedian(int[] inputStream) {
        PriorityQueue<Integer> minpq = minPQ();
        PriorityQueue<Integer> maxpq = maxPQ();

        double[] runningMedian = new double[inputStream.length];
        double med = 0.0;// current median, to organize min and max PQs around
        int idx = 0;// current index in runningMedian, for placing the elements

        for (int el : inputStream) {
            if (minpq.isEmpty()) {
                minpq.offer(el);
                med = el;
                runningMedian[idx] = med;
                idx++;
                continue;
            }
            if (el > med) {
                minpq.offer(el);
            }
            else {
                maxpq.offer(el);
            }
            if (minpq.size() > maxpq.size()+1) {
                maxpq.offer(minpq.poll());
            }
            else if (maxpq.size() >= minpq.size()+1) {
                minpq.offer(maxpq.poll());
            }
            if (maxpq.size() == minpq.size()) {
                med = (maxpq.peek() + minpq.peek())/2.0;
            }
            else {
                med = minpq.peek();
            }
            runningMedian[idx] = med;
            idx++;
        }
        System.out.println(Arrays.toString(runningMedian));
        return runningMedian;
    }
}