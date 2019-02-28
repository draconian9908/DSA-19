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


    public static int findOneDPeak(int[] nums) {
        int start = nums.length/2;
        int status = peakOneD(start, nums);
        int rec = 0;
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

    public static int[] findTwoDPeak(int[][] nums) {
        int startY = nums.length/2;
        // find max x index based on the starting point for y (halfway)
        int maxX = maxXIndex(startY,0,nums[0].length,nums);
        int statusX = peakX(maxX,startY,nums);
        // if peak is to the left
        if (statusX == -1) {
            int startX = maxX/2;
            int maxY = maxYIndex(startX,0,nums.length,nums);
            int statusY = peakY(startX,maxY,nums);
            // if peak is to the left and up
            if (statusY == -1) {
                int[][] temp = new int[maxY][maxX];
                for (int i = 0; i < (maxY); i++) {
                    temp[i] = Arrays.copyOfRange(nums[i],0,maxX);
                }
                findTwoDPeak(temp);
                return new int[]{startY,startX};
            }
            // if peak is to the left and down
            else if (statusY == 1) {
                int[][] temp = new int[nums.length-maxY][maxX];
                for (int i = 0; i < (nums.length-maxY); i++) {
                    temp[i] = Arrays.copyOfRange(nums[i],0,maxX);
                }
                int[] rec = findTwoDPeak(temp);
                return new int[]{(rec[0]+1)+startY,(rec[1]+1)+startX};
            }
            // if peak is to the left, no input on up or down
            else {
                int[][] temp = new int[nums.length][maxX];
                for (int i = 0; i < (nums.length); i++) {
                    temp[i] = Arrays.copyOfRange(nums[i],0,maxX);
                }
                findTwoDPeak(temp);
                return new int[]{startY,startX};
            }
        }
        // if peak is to the right
        else if (statusX == 1) {
            int startX = maxX+((nums.length-maxX)/2);
            int maxY = maxYIndex(startX,0,nums.length,nums);
            int statusY = peakY(startX,maxY,nums);
            int[] rec;
            // if peak is to the right and up
            if (statusY == -1) {
                int[][] temp = new int[maxY][maxX];
                for (int i = 0; i < (maxY); i++) {
                    temp[i] = Arrays.copyOfRange(nums[i],0,maxX);
                }
                rec = findTwoDPeak(temp);
                return new int[]{(rec[0]+1)+startY,(rec[1]+1)+startX};
            }
            // if peak is to the right and down
            else if (statusY == 1) {
                int[][] temp = new int[nums.length-maxY][maxX];
                for (int i = 0; i < (nums.length-maxY); i++) {
                    temp[i] = Arrays.copyOfRange(nums[i],0,maxX);
                }
                rec = findTwoDPeak(temp);
                return new int[]{(rec[0]+1)+startY,(rec[1]+1)+startX};
            }
            // if peak is to the right, no input on up or down
            else {
                int[][] temp = new int[nums.length][maxX];
                for (int i = 0; i < (nums.length); i++) {
                    temp[i] = Arrays.copyOfRange(nums[i],0,maxX);
                }
                rec = findTwoDPeak(temp);
                return new int[]{(rec[0]+1)+startY,(rec[1]+1)+startX};
            }
        }
        // if no input on right or left
        else {
            int maxY = maxYIndex(maxX,0,nums.length,nums);
            int statusY = peakY(maxX,maxY,nums);
            // if peak up, no input on right or left
            if (statusY == -1) {
                int[][] temp = new int[maxY][nums[0].length];
                for (int i = 0; i < (maxY); i++) {
                    temp[i] = Arrays.copyOf(nums[i],nums[0].length);
                }
                findTwoDPeak(temp);
                return new int[]{startY,maxX};
            }
            // if peak down, no input on right or left
            else if (statusY == 1) {
                int[][] temp = new int[nums.length-maxY][nums[0].length];
                for (int i = 0; i < (nums.length-maxY); i++) {
                    temp[i] = Arrays.copyOf(nums[i],nums[0].length);
                }
                int[] rec = findTwoDPeak(temp);
                return new int[]{(rec[0]+1)+startY,(rec[1]+1)+maxX};
            }
            // if peak!
            else {
                return new int[]{startY,maxX};
            }
        }
    }
}
