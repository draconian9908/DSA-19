import java.util.ArrayList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        List<char[]> result = new ArrayList<>();
        char[] arrangement = new char[hoursInDay];
        dough(pennies, nickels, dimes, hoursInDay, arrangement, result);
        return result;
    }

    private static void dough(int p, int n, int d, int h, char[] arr, List<char[]> results) {
        if (p == 0 && n == 0 && d == 0) {
            results.add(arr);
        }

        for (int i = 0; i < h; i++) {

        }
    }
}
