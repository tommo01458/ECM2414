import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import java.io.IOException;
public class testPebble{

    @Test
    public void testPebbleConstructor(){
        Pebble pebble = new Pebble(35, "X", "A");
        assertEquals(35, pebble.getValue());
        assertEquals("X", pebble.getBlackBag());
        assertEquals("A", pebble.getWhiteBag());
    }
    
    @Test
    public void testToString()throws IOException{
        Pebble pebble = new Pebble(69, "X", "A");
        assertEquals("Value: 69 Bag: XA", pebble.toString());
    }
}