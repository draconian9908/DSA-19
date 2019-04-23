import java.util.*;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        PriorityQueue<Integer> occ = new PriorityQueue<>((a,b) -> a-b);
        for (int el : occupied) occ.add(el);
        List<List<Integer>> covered = new ArrayList<>(occupied.length);
        List<Integer> consec = new ArrayList<>();
        consec.add(occ.poll());
        while(!occ.isEmpty()) {
            int now = occ.poll();
            int then;
            if (!consec.isEmpty()) {
                then = consec.get(consec.size() - 1);
            }
            else then = 1000;
            if (now == then + 1 || then == 1000) {
                consec.add(now);
            }
            else {
                covered.add(copy(consec));
                consec.clear();
                consec.add(now);
            }
            // Add the last set of occupied stalls
            if (occ.isEmpty() && !consec.isEmpty()) {
                covered.add(copy(consec));
            }
        }

        if (covered.size() <= M) {
            return count(covered);
        }

        ArrayList<Integer> distances = new ArrayList<>();
        for (int i = 0; i < covered.size()-1; i++) {
            List<Integer> set1 = covered.get(i);
            List<Integer> set2 = covered.get(i+1);
            int endPoint = set1.get(set1.size() - 1);
            int startPoint = set2.get(0);
            distances.add(startPoint - endPoint);
        }
        int minIndex;
        while (covered.size() > M) {
            minIndex = distances.indexOf(Collections.min(distances));
            List<Integer> set1 = covered.get(minIndex);
            List<Integer> set2 = covered.get(minIndex+1);
            for (int i = set1.get(set1.size()-1)+1; i < set2.get(0); i++) {
                set1.add(i);
            }
            set1.addAll(set2);
            covered.remove(set2);
            distances.remove(minIndex);
        }
        return count(covered);
    }

    private static int count(List<List<Integer>> covered) {
        int count = 0;
        for (List<Integer> L : covered) {
            count += L.size();
        }
        return count;
    }

    private static List<Integer> copy(List<Integer> cloned) {
        List<Integer> temp = new ArrayList<Integer>(cloned.size());
        for (int el : cloned) temp.add(el);
        return temp;
    }
}