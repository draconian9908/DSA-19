import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZumaTest {

    @Test
    public void testEmptyBoard() {
        char[] board = new char[]{};
        char[] hand = new char[]{'g','w','r','b','y'};
        assertEquals(0, ZumaGame.zumaGame(board, hand));
    }

    @Test
    public void testEmptyHand() {
        char[] board = new char[]{'y','y'};
        char[] hand = new char[]{};
        assertEquals(-1, ZumaGame.zumaGame(board, hand));
    }

    @Test
    public void testCantBeDone() {
        char[] board = new char[]{'w','r','r','b','b','w'};
        char[] hand = new char[]{'r','b'};
        assertEquals(-1, ZumaGame.zumaGame(board, hand));
    }

     @Test
     public void testAllSame() {
        char[] board = new char[]{'g'};
        char[] hand = new char[]{'g','g','g','g','g'};
        assertEquals(2, ZumaGame.zumaGame(board, hand));
     }

     @Test
     public void testNormal() {
        char[] board = new char[]{'w','w','r','r','b','b','w','w'};
        char[] hand = new char[]{'w','r','b','r','w'};
        assertEquals(2, ZumaGame.zumaGame(board, hand));
     }

     @Test
     public void testAccordion() {
        char[] board = new char[]{'r','b','y','y','b','b','r','r','b'};
        char[] hand = new char[]{'y','r','b','g','b'};
        assertEquals(3, ZumaGame.zumaGame(board, hand));
     }
}
