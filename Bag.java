import java.util.*;
import java.io.*;
public class Bag {
    private String bagName;
    public int intBagLetter;
    public List<Pebble> pebbleWeights = Collections.synchronizedList(new ArrayList<Pebble>());
    public Bag(String name){
        this.bagName = name;
    }
    public Bag(String name, String fileChoiceString, int noOfPlayersInt)throws IOException{
            List <String> firstWeights = new ArrayList<>();
            try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/tommo/Desktop/Softare Development/"+ fileChoiceString))){
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.replaceAll("\\s+","");
                    firstWeights.addAll(Arrays.asList(line.split(",")));
                }
                if (firstWeights.size() < 11*noOfPlayersInt){
                    throw new IOException("You need to have enough pebbles so there are at least 11 time as many pebbles as players");
                }
                
                for(String i: firstWeights){
                    int newNum = Integer.parseInt(i);
                    if (intBagLetter == 0){
                        Pebble pebble = new Pebble(newNum, "X", "A");
                        pebbleWeights.add(pebble);
                    }else if(intBagLetter == 1){
                        Pebble pebble = new Pebble(newNum, "Y", "B");
                        pebbleWeights.add(pebble);
                    }else if(intBagLetter == 2){
                        Pebble pebble = new Pebble(newNum, "Z", "C");
                        pebbleWeights.add(pebble);
                    } 
                }
                intBagLetter++;
            } catch (FileNotFoundException e) {
                System.out.println("Error: file not found, please try again.");
            }
    }
    public String getBagName(){
        return this.bagName;
    }
    public List<Pebble> getPebbleWeights(){
        return this.pebbleWeights;
    }
}
    
    