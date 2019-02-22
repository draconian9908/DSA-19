import java.util.Arrays;

public class TripleSum {

    static int tripleSum(int[] arr, int sum) {
        if (arr.length < 3) {
            return 0;
        }
        boolean hasNeg = false;
        int count = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length/2;i++) {
            if (arr[i] < 0) {
                hasNeg = true;
            }
            int needed = sum - arr[i];
            for (int j = arr.length-1; j > i; j--) {
                if (hasNeg) {
                    int need = sum - (arr[i] + arr[j]);
                    for (int k = j-1; k > i; k--) {
                        if (arr[k] == need) {
                            count++;
                        }
                    }
                }
                else if (arr[j] <= needed) {
                    int need = sum - (arr[i] + arr[j]);
                    for (int k = j-1; k > i; k--) {
                        if (arr[k] == need) {
                            count++;
                        }
                    }
                }
            }
            hasNeg = false;
        }
        System.out.println();
        System.out.println();
        return count;
    }
}

//if (arr.length < 3) {
//        return 0;
//        }
//        int count = 0;
//        LinkedList<LinkedList<Integer>> doubles = new LinkedList<>();
//        for (int i = 0; i < arr.length-1; i++) {
//        for (int j = 0; j < arr.length; j++) {
//        if (j > i) {
//        LinkedList<Integer> set = new LinkedList<>();
//        set.add(arr[i]);
//        set.add(arr[j]);
//        doubles.add(set);
//        }
//        }
//        }
//        for (int el : arr) {
//        for (int i = 0; i < doubles.size(); i ++) {
//        LinkedList<Integer> d = doubles.get(i);
//        if (el == d.getFirst() || el == d.getLast()) {
//        continue;
//        }
//        int n = d.getFirst();
//        int m = d.getLast();
//        if (el + n + m == sum) {
//        count++;
//        }
//        }
//        }
//        return count;




//        HashMap<Integer, LinkedList<LinkedList<Integer>>> doubles = new HashMap<>();
//        for (int i = 1; i < arr.length-1; i++) {
//            for (int j = 2; j < arr.length; j++) {
//                if (j > i) {
//                    LinkedList<Integer> set = new LinkedList<>();
//                    set.add(arr[i]);
//                    set.add(arr[j]);
//                    if (!doubles.containsKey(arr[i])) {
//                        LinkedList<LinkedList<Integer>> sets = new LinkedList<>();
//                        sets.add(set);
//                        doubles.put(arr[i],sets);
//                    }
//                    else {
//                        LinkedList<LinkedList<Integer>> sets = doubles.get(arr[i]);
//                        sets.add(set);
//                        doubles.put(arr[i],sets);
//                    }
//                }
//            }
//        }
//        for (int el : arr) {
//            System.out.println("el: " + el);
//            LinkedList<LinkedList<Integer>> sets = doubles.getOrDefault(el,null);
//            if (sets == null) {
//                continue;
//            }
//            System.out.println("doubles: " + sets.toString());
//            for (LinkedList<Integer> set : sets) {
//                int n = set.getFirst();
//                int m = set.getLast();
//                if ((el + n + m) == sum) {
//                    count++;
//                }
//            }
//        }