import java.util.*;
import java.io.*;
import java.util.Scanner;
public class Bags {
    public int intBagLetter = 0;
    public String blackBagLetter;
    public String whiteBagLetter;
    public class BlackBag{
        private List<Pebble> pebbleWeights = new ArrayList<>();
        private boolean taken = false;
        public BlackBag(String fileChoiceString, int noOfPlayersInt)throws IOException{
            List <String> firstWeights = new ArrayList<>();
            try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/tommo/Desktop/Softare Development/"+ fileChoiceString))){
                String line;
                while ((line = br.readLine()) != null) {
                    firstWeights.addAll(Arrays.asList(line.split(",")));
                }
                if (firstWeights.size() < 11*noOfPlayersInt){
                    throw new IOException("You need to have enough pebbles so there are at least 11 time as many pebbles as players");
                }
                intBagLetter++;
                for(String i: firstWeights){
                    int newNum = Integer.parseInt(i);
                    if (intBagLetter == 0){
                        blackBagLetter = "X";
                        whiteBagLetter = "A";
                    }else if(intBagLetter == 1){
                        blackBagLetter = "Y";
                        whiteBagLetter = "B";

                    }else if(intBagLetter == 2){
                        blackBagLetter = "Z";
                        whiteBagLetter = "C";
                    }
                    Pebble pebble = new Pebble(newNum, blackBagLetter, whiteBagLetter);
                    //this.addPebbleWeights(newNum);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: file not found, please try again.");
            }
        }
        public List<Pebble> getPebbleWeights(){
            return this.pebbleWeights;
        }
        public void addPebbleWeights(int newNum){
            this.getPebbleWeights().add(newNum);
        }
        public int removePebbleWeight(int index){
            int removedValue = this.getPebbleWeights().get(index);
            removedValue-=1;
            this.getPebbleWeights().remove(getPebbleWeights().indexOf(index));
            return removedValue;
        }
    }
    public class WhiteBag{
        private List <Integer> usedPebblesWeights = new ArrayList <>();
        public WhiteBag(){
        }
        public void addToWhiteBag(int newPebble){
            this.getWhiteBagPebbles().add(newPebble);
        }
        public List<Integer> getWhiteBagPebbles(){
            return this.usedPebblesWeights;
        }
    }   
}