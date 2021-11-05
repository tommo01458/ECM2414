import java.util.*;
import java.io.*;
public class Pebble {
    public int value;
    public String blackBag;
    public String whiteBag;
    public Pebble(int value, String blackBag, String whiteBag){
        this.value = value;
        this.blackBag = blackBag;
        this.whiteBag = whiteBag;
    }
    public void setValue(){
        this.value = value;
    }
    public void setBlackBag(){
        this.blackBag = blackBag;
    }
    public void setWhiteBag(){
        this.whiteBag = whiteBag;
    }
    public int getValue(){
        return value;
    }
    public String getBlackBag(){
        return blackBag;
    }
    public String getWhiteBag(){
        return whiteBag;
    }
}
