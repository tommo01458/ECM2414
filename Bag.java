import java.util.*;
import java.io.*;
/**
 * Bag class
 */
public class Bag {
    private String bagName;
    public int intBagLetter = 0;
    public List<Pebble> pebbleWeights = Collections.synchronizedList(new ArrayList<Pebble>());
    /**
     * Bag constructor for white bags
     * @param name name of the bag for white bags (either A, B or C)
     */
    public Bag(String name){
        this.bagName = name;
    }
    /**
     * Bag constructor for black bags 
     * @param name name of the bag (either X, Y or Z)
     * @param fileChoiceString location of the bag
     * @param noOfPlayersInt number of players in the game
     * @throws IOException
     */
    public Bag(String name, String fileChoiceString, int noOfPlayersInt)throws IOException{
        if(fileChoiceString .contains("E") && fileChoiceString.length()==1){ //Checking for valid input or end game request
            System.out.println("You exited the game.");
            System.exit(0);
        }
            List <String> firstWeights = new ArrayList<>();
            this.bagName = name;
            try(BufferedReader br = new BufferedReader(new FileReader(fileChoiceString))){ //Reading in the file path
                String line;
                
                int lines = 0;
                while ((line = br.readLine()) != null) { //Check the line
                    line = line.replaceAll("\\s+",""); //Replace the black space with nothing left
                    firstWeights.addAll(Arrays.asList(line.split(","))); //Split at the commas
                    lines++;
                }
                if (lines > 1){ //Handling exceptions
                    throw new IllegalArgumentException("The file must have only one line of comma separated weights. Please use another file.");
                }
                if (firstWeights.size() < 11*noOfPlayersInt){
                    throw new IOException("You need to have enough pebbles so there are at least 11 time as many pebbles as players");
                }
                
                for(String i: firstWeights){
                    int newNum = Integer.parseInt(i);
                    if (newNum > 0){
                        if (name.contains("X")){
                            Pebble pebble = new Pebble(newNum, "X", "A"); //Constructing the pebbles with the correct black and white bags
                            pebbleWeights.add(pebble);
                        }else if(name.contains("Y")){
                            Pebble pebble = new Pebble(newNum, "Y", "B");
                            pebbleWeights.add(pebble);
                        }else if(name.contains("Z")){
                            Pebble pebble = new Pebble(newNum, "Z", "C");
                            pebbleWeights.add(pebble);
                        } 
                    } else { //Handling exceptions
                        throw new IllegalArgumentException("All Pebbles must be strictly positive. Please restart the program and use another bag.");
                    }
                    
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: file not found, please try again.");
            } catch (NumberFormatException n){
                System.out.println("Please make sure there are only positive integers in the file");
            }
    }
    /**
     * Method to get the name of the bag
     * @return the string name
     */
    public String getBagName(){
        return this.bagName;
    }
    /**
     * Method to get the contents of the bag
     * @return contents of the bag
     */
    public List<Pebble> getPebbleWeights(){
        return this.pebbleWeights;
    }
    /**
     * Method to print out the size of the bag
     * @return string of the bag name and size
     */
    public String toString(){
        return "The bag name is " + this.getBagName() + " and has size: " + this.getPebbleWeights().size();
    }
}
    
    