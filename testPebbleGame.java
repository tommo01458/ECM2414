import org.junit.Test;
import static org.junit.Assert.*;
public class testPebbleGame {
    public testPebbleGame(){

    }


    @Test
    public void testPebbleGameMain(){
        assertEquals(9, PebbleGame.PebbleGame(9));
    }
}