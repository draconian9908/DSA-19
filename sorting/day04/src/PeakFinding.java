import java.util.Arrays;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }

    /**
     * 1D Peak Finding
     * Runtime: O(logN)
     */
    public static int findOneDPeak(int[] nums) {
        int start = nums.length/2;
        int status = peakOneD(start, nums);
        int rec;
        if (status == -1) {
            findOneDPeak(Arrays.copyOfRange(nums,0,start));
            return start;
        }
        else if (status == 1) {
            rec = findOneDPeak(Arrays.copyOfRange(nums,start+1,nums.length));
            return start + (rec+1);
        }
        else {
            return start;
        }
    }

    /**
     * 2D Peak Finding
     * Runtime: O()
     */
    public static int[] findTwoDPeak(int[][] nums) {
        int startY = nums.length/2;
        // find max x index based on the starting point for y (halfway)
        int maxX = maxXIndex(startY,0,nums[0].length,nums);
        int statusX = peakY(maxX,startY,nums);
        // if peak is above
        if (statusX == -1) {
            int startX = nums[0].length/2;
            int maxY = maxYIndex(startX,0,startY,nums);
            int statusY = peakX(startX,maxY,nums);
            // if peak is above and left
            if (statusY == -1) {
                int[][] temp = new int[startY][startX];
                for (int i = 0; i < (startY); i++) {
                    temp[i] = Arrays.copyOfRange(nums[i],0,startX);
                }
                return findTwoDPeak(temp);
            }
            // if peak is above and right
            else if (statusY == 1) {
                int[][] temp;
                if (nums[0].length % 2 == 0) {
                    temp = new int[startY][startX-1];
                }
                else {
                    temp = new int[startY][startX];
                }
                for (int i = 0; i < (startY); i++) {
                    temp[i] = Arrays.copyOfRange(nums[i],startX+1,nums[0].length);
                }
                int[] rec = findTwoDPeak(temp);
                return new int[]{rec[0],(rec[1]+1)+startX};
            }
            // if peak is above, no input on left or right
            else {
                int[][] temp = new int[startY][nums[0].length];
                for (int i = 0; i < (startY); i++) {
                    temp[i] = Arrays.copyOfRange(nums[i],0,nums[0].length);
                }
                return findTwoDPeak(temp);
            }
        }
        // if peak is below
        else if (statusX == 1) {
            int startX = nums[0].length/2;
            int maxY = maxYIndex(startX,startY+1,nums.length,nums);
            int statusY = peakX(startX,maxY,nums);
            int[] rec;
            // if peak below and left
            if (statusY == -1) {
                int[][] temp;
                if (nums.length % 2 == 0) {
                    temp = new int[startY-1][startX];
                }
                else {
                    temp = new int[startY][startX];
                }
                int j = 0;// index in temp
                for (int i = startY+1; i < (nums.length); i++) {
                    temp[j] = Arrays.copyOfRange(nums[i],0,startX);
                    j++;
                }
                rec = findTwoDPeak(temp);
                return new int[]{(rec[0]+1)+startY,rec[1]};
            }
            // if peak is below and right
            else if (statusY == 1) {
                int[][] temp;
                if (nums.length % 2 == 0 && nums[0].length % 2 == 0) {
                    temp = new int[startY-1][startX-1];
                }
                else if (nums.length % 2 != 0 && nums[0].length % 2 == 0) {
                    temp = new int[startY][startX-1];
                }
                else if (nums.length % 2 == 0 && nums[0].length % 2 != 0) {
                    temp = new int[startY-1][startX];
                }
                else {
                    temp = new int[startY][startX];
                }
                int j = 0;// index in temp
                for (int i = startY+1; i < nums.length; i++) {
                    temp[j] = Arrays.copyOfRange(nums[i],startX+1,nums[0].length);
                    j++;
                }
                rec = findTwoDPeak(temp);
                return new int[]{(rec[0]+1)+startY,(rec[1]+1)+startX};
            }
            // if peak is below, no input on left or right
            else {
                int[][]temp;
                if (nums.length % 2 == 0) {
                    temp = new int[startY-1][startX];
                }
                else {
                    temp = new int[startY][startX];
                }
                int j = 0;// index in temp
                for (int i = startY+1; i < (nums.length); i++) {
                    temp[j] = Arrays.copyOfRange(nums[i],0,nums[0].length);
                    j++;
                }
                rec = findTwoDPeak(temp);
                return new int[]{(rec[0]+1)+startY,rec[1]};
            }
        }
        // if no input on above or below
        else {
            int maxY = maxYIndex(maxX,0,nums.length,nums);
            int statusY = peakX(maxX,maxY,nums);
            // if peak left, no input on above or below
            if (statusY == -1) {
                int[][] temp = new int[nums.length][maxX];
                for (int i = 0; i < (nums.length); i++) {
                    temp[i] = Arrays.copyOfRange(nums[i],0,maxX);
                }
                return findTwoDPeak(temp);
            }
            // if peak right, no input on above or below
            else if (statusY == 1) {
                int[][] temp = new int[nums.length][nums[0].length-maxX-1];
                for (int i = 0; i < (nums.length); i++) {
                    temp[i] = Arrays.copyOfRange(nums[i],maxX+1,nums[0].length);
                }
                int[] rec = findTwoDPeak(temp);
                return new int[]{rec[0],(rec[1]+1)+maxX};
            }
            // if peak!
            else {
                return new int[]{startY,maxX};
            }
        }
    }
}
