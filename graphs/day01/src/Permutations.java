import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        LinkedList<Integer> unused = new LinkedList<>(A);
        List<List<Integer>> permutations = new LinkedList<>();
        perm(new LinkedList<Integer>(), unused, permutations);
        return permutations;
    }

    private static void perm(LinkedList<Integer> curr, LinkedList<Integer> unused, List<List<Integer>> perms) {
        if (unused.isEmpty()) {
            perms.add(curr);
            return;
        }

        for (int i = 0; i < unused.size(); i++) {
            LinkedList<Integer> curr2 = (LinkedList)curr.clone();
            LinkedList<Integer> temp = (LinkedList)unused.clone();
            int currentInt = temp.remove(i);
            curr2.add(currentInt);
            perm(curr2, temp, perms);
        }
    }

}