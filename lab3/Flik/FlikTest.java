import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testFlik() {
        assertEquals(false, Flik.isSameNumber(1, 2));
        assertEquals(true, Flik.isSameNumber(1, 1));
    }
}
