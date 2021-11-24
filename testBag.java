import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.*;
public class testBag{
    
    @Test
    public void testWhiteBag(){
        String name = "name";
        Bag whiteBag = new Bag(name);
        assertEquals(name, whiteBag.getBagName());
        assertEquals(0, whiteBag.getPebbleWeights().size());
    }

    @Test
    public void testBlackBag() throws IOException{
        String name = "X";
        int numberOfPlayers = 5;
        Bag blackBag = new Bag(name, "C:/Users/tommo/Desktop/Softare Development/example_file_2.csv", numberOfPlayers);
        assertEquals(100, blackBag.getPebbleWeights().size());
        assertEquals(name, blackBag.getBagName());
    }
    @Test
    public void testToString()throws IOException{
        Bag blackBag = new Bag("X", "C:/Users/tommo/Desktop/Softare Development/example_file_2.csv", 5);
        assertEquals("The bag name is X and has size: 100", blackBag.toString());
    }

    
    
}

