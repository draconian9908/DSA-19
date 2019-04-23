public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        long j = n/(long)2;
        while (isBadVersion.isFailingVersion(j)) {
            j = j/(long)2;
        }
        while(!isBadVersion.isFailingVersion(j)) {
            j++;
        }
        return j;
    }
}
