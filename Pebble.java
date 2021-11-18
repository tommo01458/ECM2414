import java.util.*;
import java.io.*;
/**
 * Pebble class for each pebble
 */
public class Pebble {
    public int value;
    public String blackBag;
    public String whiteBag;
    /**
     * Constructor for the bag
     * @param value int value of the pebble
     * @param blackBag the black bag it is linked to
     * @param whiteBag the white bag it is linked to
     */
    public Pebble(int value, String blackBag, String whiteBag){
        this.value = value;
        this.blackBag = blackBag;
        this.whiteBag = whiteBag;
    }
    /**
     * Method to get the value of the pebble
     * @return int value
     */
    public int getValue(){
        return value;
    }
    /**
     * Method to see which black bag the Pebble is from
     * @return string name of the black bag
     */
    public String getBlackBag(){
        return blackBag;
    }
    /**
     * Method to see which white bag the Pebble is from
     * @return string name of the white bag
     */
    public String getWhiteBag(){
        return whiteBag;
    }
    /**
     * Method to print out the information of the Pebble
     * @return string information of the pebble
     */
    public String toString(){
        return "Value: "+ this.getValue() + " Bag: " + this.getBlackBag() + this.getWhiteBag();
    }
}
