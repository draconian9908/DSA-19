import java.util.HashMap;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        if (points.length < 3) {
            return 0;
        }
        int num = 0;
        HashMap<Double, Integer> mapping;
        for (int i = 0; i < points.length; i++) {
            mapping = new HashMap<Double, Integer>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int[] p1 = points[i];
                int[] p2 = points[j];
                double dist = Math.hypot(p1[0]-p2[0],p1[1]-p2[1]);
                if (mapping.containsKey(dist)) {
                    mapping.put(dist,mapping.get(dist)+1);
                }
                else {
                    mapping.put(dist,1);
                }
            }
            for (HashMap.Entry<Double, Integer> el : mapping.entrySet()) {
                num = num + (el.getValue()*(el.getValue()-1));
            }
        }
        return num;
    }

}

