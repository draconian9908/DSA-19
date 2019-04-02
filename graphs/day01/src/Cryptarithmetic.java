import java.util.*;

public class Cryptarithmetic {

    // Do not modify this function (though feel free to use it)
    public static boolean validSolution(String S1, String S2, String S3, Map<Character, Integer> assignments) {
        return (stringToInt(S1, assignments) + stringToInt(S2, assignments) == stringToInt(S3, assignments))
                && assignments.get(S1.charAt(0)) != 0
                && assignments.get(S2.charAt(0)) != 0
                && assignments.get(S3.charAt(0)) != 0;
    }


    private static Iterable<Integer> randomOrder() {
        List<Integer> l = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(l);
        return l;
    }

    private static int stringToInt(String s, Map<Character, Integer> assignments) {
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            i *= 10;
            i += assignments.get(s.charAt(j));
        }
        return i;
    }

    public static Map<Character, Integer> solvePuzzle(String S1, String S2, String S3) {
        Map<Character, Integer> assignments = new HashMap<>();
        LinkedList<Character> needed = new LinkedList<>();
        makeList(S1, S2, S3, needed);
        owo(S1, S2, S3, needed, assignments);
        return assignments;
    }

    private static boolean owo(String s1, String s2, String s3, LinkedList<Character> needed, Map<Character, Integer> assignments) {
        if (needed.isEmpty()) return validSolution(s1, s2, s3, assignments);
        else {
            for (char el : needed) {
                for (int i : randomOrder()) {
//                    System.out.println(needed + "   ");
                    if (!assignments.containsValue(i)) {
                        LinkedList<Character> need2 = new LinkedList<Character>(needed);//(LinkedList) needed.clone();
                        need2.remove((Character) el);
//                        System.out.println(need2);
                        assignments.put(el, i);
                        if (owo(s1, s2, s3, need2, assignments)) {
                            return true;
                        }
                        assignments.remove(el);
                    }
                }
            }
//            for (int i : randomOrder()) {
//                if (!assignments.containsValue(i)) {
//                    for (char el : needed) {
//                        System.out.println(needed);
//                        LinkedList<Character> need2 = (LinkedList)needed.clone();
//                        need2.remove((Character)el);
//                        assignments.put(el, i);
//                        owo(s1, s2, s3, need2, assignments);
//                        assignments.remove(el);
//                    }
//                }
//            }
        }
        return false;
    }

//    private static boolean mapContains(String s, Map<Character, Integer> assi) {
//        for (int i = 0; i < s.length(); i++)
//    }

    private static void makeList(String s1, String s2, String s3, List<Character> needed) {
        for (int i = 0; i < s1.length(); i++) {
            char temp = s1.charAt(i);
            if (!needed.contains(temp)) {
                needed.add(temp);
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            char temp = s2.charAt(i);
            if (!needed.contains(temp)) {
                needed.add(temp);
            }
        }
        for (int i = 0; i < s3.length(); i++) {
            char temp = s3.charAt(i);
            if (!needed.contains(temp)) {
                needed.add(temp);
            }
        }
    }
}
