import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZumaTest {
    @Test
    public void testOne() {
        assertEquals(0,ZumaGame.zumaGame(0));
    }

    //
    // @Test
    // public void testTwo() {
    //     assertEquals(2,ZumaGame.zumaGame(2));
    // }
    //
    // @Test
    // public void testThree() {
    //     // All the sequences that sum to 4:
    //     // 4
    //     // 3, 1
    //     // 2, 2
    //     // 2, 1, 1
    //     // 1, 3
    //     // 1, 2, 1
    //     // 1, 1, 2
    //     // 1, 1, 1, 1
    //     assertEquals(8,ZumaGame.zumaGame(4));
    // }
    //
    // @Test
    // public void testFour() {
    //     assertEquals(16,ZumaGame.zumaGame(5));
    // }
    //
    // @Test
    // public void testFive() {
    //     assertEquals(125,ZumaGame.zumaGame(8));
    // }
    //
    //
    // @Test
    // public void testSix() {
    //     assertEquals(492,ZumaGame.zumaGame(10));
    // }

}
