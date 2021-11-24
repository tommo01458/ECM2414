/**
 * Todo handle txt file input
 * Todo handle incorrect inputs
 * TODO start unit testing
 * TODO  
 * 
 */
//Importing all the utility for the functionality of the program.
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.*;
/**
 * The class for the PebbleGame
 * Defining the array list of the players
 * Defining the black and white bags
 * Defining the integer variable for the number of players
 * Defining the boolean condition for winning the game
 */
public class PebbleGame{
    private ArrayList<Player> allPlayers = new ArrayList<>();
    public Bag[] blackBags = new Bag[3];
    public Bag[] whiteBags = new Bag[3];
    private int noOfPlayers;
    private boolean hasWon = false;
    
    /**
     * Constructor for the PebbleGame class
     * @param numberOfPlayers number of players in the game
     */
    public PebbleGame(int numberOfPlayers){
        try{
            this.noOfPlayers = numberOfPlayers; //If the number of players doesnt match an error has occured
        } catch (Exception e) {
            e.printStackTrace(); //Printing the error
        }
    }
    /**
     * Method to calculate if the game has been won by a player
     * @param player player thread
     * @param playerID player ID
     * @param counter counter to see how many times the player has run the run method
     * @return the boolean to declare that the game has been won
     */
    public synchronized boolean gameWon(Player player, int playerID, int counter){
        if (player.getScore()==100){ //Condition for winning the game
            this.hasWon = true; //Setting the condition to true
            System.out.println("Player " + player.getId() + " has won with the hand: " + player.getPlayerPebbles()); //Printing the winner
            System.out.println(counter); //The number of tries to win the game
            for (Bag bag : blackBags){ //Printing the contents of all black bags
                System.out.println(bag);
            }
            for (Bag bag : whiteBags){ //Printing the contents of all black bags
                System.out.println(bag);
            }
            System.exit(0); //Ending the game
            return true;

        }
        return false; //Win condition not met
    }
    /**
     * Method to add a player to list of PebbleGame players
     * @param player Player
     */
    public void addPlayer(Player player){
        this.allPlayers.add(player);
    }
    /**
     * Method to return the number of players in the game
     * @return the number of players in the game
     */
    public int getNoOfPlayers(){
        return this.noOfPlayers;
    }
    /**
     * Method to get the players
     * @return the list of players
     */
    public ArrayList<Player> getPlayers(){
        return this.allPlayers; 
    }
    /**
     * Player class which implements Runnable so each player is a concurrent thread
     * Defining the id of each player
     * Defining the list to hold the players hand
     * Defining the integer variable to hold the players score
     * Defining the counter to track the number of tries
     */
    public class Player implements Runnable{
        //Attributes
        private int playerId;
        public List<Pebble> playerPebbles = new ArrayList<>();
        private int playerScore;
        private int counter;
        //Methods
        /**
         * Run method for Player class
         * @Override
         */
        public void run(){
            try { //Try catch for writing to the file
                this.playerToFile("Player " + this.getId(), "Player " +this.getId() + " has started the game.");
                //first ten pebbles for each player
                for (int i = 0; i <10; i++){ //Creating the inital players hand
                    this.pebbleChoice();
                }
                this.playerToFile("Player " + this.getId(), " has drawn all ten pebbles.");
                while(!gameWon(this, this.getId(), counter)){ //Running the game while the win condition isnt met
                    this.pebbleChoice();
                    counter++; //Incrementing the counter
                    
                }
            } catch (Exception e){
                e.printStackTrace(); //Printing the error
                System.exit(0);
            }
            
        }       
        //constructor
        /**
         * Player constructor 
         * @param id player ID
         */
        public Player(int id){
            this.playerId = id;
        }
        //Setters/getters
        /**
         * Method to add a pebble to a players hand
         * @param pebble Pebble to be added
         */
        public void addPebble(Pebble pebble){
            this.playerPebbles.add(pebble);
        }
        
        /**
         * Method to get the player's score
         * @return player's score
         */
        public int getScore(){
            int score = 0;
            for (Pebble pebble:this.getPlayerPebbles()){
                if (pebble != null){
                    score+=pebble.getValue();
                }
            }
            this.playerScore = score;
            return this.playerScore;
        }
        
        /**
         * Method to write information to player output file
         * @param filename filename to write to
         * @param string string to write to file
         * @throws IOException any input/output exception that may occur
         */
        public synchronized void playerToFile(String filename, String string) throws IOException{
            try{
                FileWriter writer = new FileWriter(filename + "-output.txt", true);
                writer.write(string);
                writer.write("\n");
                writer.close();
            } catch (IOException e){

            }
        }
        /**
         * Method to get the players ID
         * @return int id
         */
        public int getId(){
            return this.playerId;
        }
        /**
         * Method to get the players hand
         * @return the players hand
         */
        public List<Pebble> getPlayerPebbles(){
            return this.playerPebbles;
        }
        /**
         * Method to choose and discard pebbles from the players hand and bags
        First it checks whether the player has a full hand to start. If it doesn't then the loop is executed ten times until
        the hand is full.
        Once the hand is full it enters the draw and discard functionality.
         */
        public synchronized void pebbleChoice(){
            try {
                boolean firstTenDone; //Boolean for creating the players hand
                boolean picked = false;
                if (this.getPlayerPebbles().size()<10){ //Drawing the players hand
                    firstTenDone = false;
                }else {
                    firstTenDone= true;
                }
                Random rand = new Random();
                int randomNum = rand.nextInt(3);
                Bag randomBag = blackBags[randomNum]; //Random black bag
                Bag randomWhiteBag = whiteBags[randomNum]; //Random white bag
                synchronized(randomBag){
                    while (picked==false){
                        if (randomBag.getPebbleWeights().size()>0){ //Making sure there are pebbles in the bag
                            int randomIndex = rand.nextInt(randomBag.getPebbleWeights().size()); //Random index of a pebble in the bag
                            Pebble pebbleChoice = randomBag.getPebbleWeights().get(randomIndex); //Getting the pebble at the index
                            this.addPebble(pebbleChoice); //Adding the pebble to the hand
                            this.playerToFile("Player " + this.getId(), "Player " + this.getId() + " has drawn a " + pebbleChoice.getValue()+"\nplayer" + this.getId()+" hand is " +this.getPlayerPebbles()); //Writing to the file
                            randomBag.getPebbleWeights().remove(randomIndex); //Removing from the black bag
                            int randomPlayerIndex =rand.nextInt(this.getPlayerPebbles().size()); //Random index in the hand
                            Pebble playerDiscardChoice = this.getPlayerPebbles().get(randomPlayerIndex); //Getting the pebble at the index
                            picked = true;
                            if(firstTenDone == true){ //After the first ten have been drawn
                                if(pebbleChoice.getBlackBag() == "X"){ //Linking the black and white bags based on the attribute of the pebble object
                                    whiteBags[0].getPebbleWeights().add(playerDiscardChoice);
                                }else if(pebbleChoice.getBlackBag()== "Y"){
                                    whiteBags[1].getPebbleWeights().add(playerDiscardChoice);
                                }else if(pebbleChoice.getBlackBag()== "Z"){
                                    whiteBags[2].getPebbleWeights().add(playerDiscardChoice);
                                }
                                this.getPlayerPebbles().remove(randomPlayerIndex); //Removing from the players hand
                                this.playerToFile("Player " + this.getId(), "Player " + this.getId() + " has discarded a " + playerDiscardChoice + "\nplayer" + this.getId() + " hand is " + this.getPlayerPebbles()); //Writing to the file
                            }
                        } else {
                            randomBag.getPebbleWeights().addAll(randomWhiteBag.getPebbleWeights()); //Swaping the pebbles in the black and white bag
                            randomWhiteBag.getPebbleWeights().clear(); //Clearing the white bag
                            picked = true;       
                            break;          
                        }
                    }
                } 
            } catch (Exception e){
                e.printStackTrace(); //Catching error and printing them
                System.exit(0);
            }     
        }
}
    
    /**
     * Main method to run the code
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        while(true){
            try{
                System.out.println("Welcome to pebble game!");
                System.out.println("You will be asked to enter the number of players");
                System.out.println("And then for the location of 3 files in turn containing comma seperated integer values for the pebble weights.");
                System.out.println("The integer values must be strictly positive.");
                System.out.println("The game will then be simulated, and output written to files in this directory.");
                Scanner noOfPlayers = new Scanner(System.in);
                System.out.println("Number of players?");
                String noOfPlayersString = noOfPlayers.nextLine();
                if(noOfPlayersString.contains("E") && noOfPlayersString.length()==1){
                    System.out.println("You exited the game.");
                    System.exit(0);
                }
                int noOfPlayersInt = Integer.parseInt(noOfPlayersString);
                if(noOfPlayersInt < 1){
                    throw new IllegalArgumentException("There must be a positive number of players.");
                }
                Scanner pathChoice = new Scanner(System.in);
                System.out.println("Enter file path for the folder containing the bags in the format C:/***/***/***.../.. Please leave the final forward slash in.");
                String pathChoiceString = pathChoice.nextLine();
                Scanner fileChoice = new Scanner(System.in);
                System.out.println("Location of bag X");
                String fileChoiceStringX = fileChoice.nextLine(); //location of bag X
                Bag blackBagX = new Bag("X", pathChoiceString+fileChoiceStringX,noOfPlayersInt);
                System.out.println("Location of bag Y");
                String fileChoiceStringY = fileChoice.nextLine(); //location of bag Y
                Bag blackBagY = new Bag("Y",pathChoiceString+fileChoiceStringY,noOfPlayersInt);
                System.out.println("Location of bag Z");
                String fileChoiceStringZ = fileChoice.nextLine(); //location of bag Z
                Bag blackBagZ = new Bag("Z",pathChoiceString+fileChoiceStringZ,noOfPlayersInt);
                //creation of white bags
                Bag whiteBagA = new Bag("A"); //Defining the white bags
                Bag whiteBagB = new Bag("B");
                Bag whiteBagC = new Bag("C");
                PebbleGame pebbleGame = new PebbleGame(noOfPlayersInt);
                pebbleGame.blackBags[0] = blackBagX; //Defining the bags in the list
                pebbleGame.blackBags[1] = blackBagY;
                pebbleGame.blackBags[2] = blackBagZ;
                pebbleGame.whiteBags[0] = whiteBagA;
                pebbleGame.whiteBags[1] = whiteBagB;
                pebbleGame.whiteBags[2] = whiteBagC;
                for (int i = 0; i <noOfPlayersInt; i++){
                    PebbleGame.Player player = pebbleGame.new Player(i);//creating players with their ids
                    pebbleGame.addPlayer(player); //adding the players to the list of players in the game
                }
                for (PebbleGame.Player player: pebbleGame.getPlayers()){
                    Thread thread = new Thread(player); //creating the player threads
                    thread.start(); //starting the threads
                }
                fileChoice.close(); //Closing the file scanner
                noOfPlayers.close();
                pathChoice.close();
                break;
            }catch (Exception e){
                e.printStackTrace();
            }
        }  
    }
}