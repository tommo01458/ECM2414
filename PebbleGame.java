import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.*;
public class PebbleGame{
    private ArrayList<Player> allPlayers = new ArrayList<>();
    private Bag[] blackBags = new Bag[3];
    private Bag[] whiteBags = new Bag[3];
    private int noOfPlayers;
    public PebbleGame(String[] bagLocations, int numberOfPlayers){
        try{
            String[][] names = {{"X", "A"},{"Y", "B"},{"Z", "C"}};
            this.noOfPlayers = numberOfPlayers;
            for (int i =0; i<3;i++){
                blackBags[i] = new Bag(names[i][0],bagLocations[i], numberOfPlayers);
                whiteBags[i] = new Bag(names[i][1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0); 
        }
    }
    }
    public void addPlayer(Player player){
        this.allPlayers.add(player);
    }
    public int getNoOfPlayers(){
        return this.allPlayers.size();
    }
    public ArrayList<Player> getPlayers(){
        return this.allPlayers;
    }
    public void addBag(Bags bag){
        this.allBags.add(bag);
    }
    public ArrayList<Bags> getBags(){
        return this.allBags;
    }
    public void setBagX(Bags.BlackBag x){
        this.blackBagX = x;
    }
    public void setBagY(Bags.BlackBag y){
        this.blackBagY = y;
    }
    public void setBagZ(Bags.BlackBag z){
        this.blackBagZ = z;
    }
    public void setBagA(Bags.WhiteBag a){
        this.whiteBagA = a;
    }
    public void setBagB(Bags.WhiteBag b){
        this.whiteBagB= b;
    }
    public void setBagC(Bags.WhiteBag c){
        this.whiteBagC = c;
    }
    public Bags.BlackBag getBagX(){
        return this.blackBagX;
    }
    public Bags.BlackBag getBagY(){
        return this.blackBagY;
    }
    public Bags.BlackBag getBagZ(){
        return this.blackBagZ;
    }
    public Bags.WhiteBag getBagA(){
        return this.whiteBagA;
    }
    public Bags.WhiteBag getBagB(){
        return this.whiteBagB;
    }
    public Bags.WhiteBag getBagC(){
        return this.whiteBagC;
    }
    public class Player implements Runnable{
        //Attributes
        private int playerId;
        public List<Pebble> playerPebbles = new ArrayList<>();
        private int playerScore;
        private String playerBlackBag;
        private String playerWhiteBag;
        //Methods
        public void run(){
            int counter =0;
            Random random = new Random();
            List<Pebble> bagX = PebbleGame.this.getBagX().getPebbleWeights();
            List<Pebble> bagY = PebbleGame.this.getBagY().getPebbleWeights();
            List<Pebble> bagZ = PebbleGame.this.getBagZ().getPebbleWeights();
            for (int i = 0; i <10; i++){
                int bagChoice = random.nextInt(3);
                if (bagChoice==0 && bagX.size() > 0){
                    synchronized(bagX){
                        firstTen(bagX);
                            Thread.yield();
                    }
                }
            }
                try{
                    this.playerToFile(("Player-" + this.getId()), "The Player "+ this.getId() + " has started the game!");
                } catch (IOException e){
                    System.out.println(e);
                }
                try {
                    while(this.getScore() != 100){
                
                        List<Pebble> bagA = PebbleGame.this.getBagA().getPebbleWeights();
                        List<Pebble> bagB = PebbleGame.this.getBagB().getPebbleWeights();
                        List<Pebble> bagC = PebbleGame.this.getBagC().getPebbleWeights();
                        while (bagX.size() + bagY.size() + bagZ.size() > 0){
                            int bagChoice = random.nextInt(3);
                            if (bagChoice==0 && bagX.size() > 0){
                                synchronized(bagX){
                                    synchronized(bagA){
                                        drawAndDiscard(bagX, bagA);
                                        Thread.yield();
                                    }
                                    
                                }
                            } else if (bagChoice == 1 && bagY.size() > 0){
                                synchronized(bagY){
                                    synchronized(bagB){
                                        drawAndDiscard(bagY, bagB);
                                        Thread.yield();
                                    }
                                }
                            } else if (bagChoice == 2 && bagZ.size() > 0){
                                synchronized(bagZ){
                                    synchronized(bagC){
                                        drawAndDiscard(bagZ, bagC);
                                        Thread.yield();
                                    }
                                }
                            }
                            //System.out.println(this);
                            counter ++;
                            //System.out.println("Player " + this.getId() + " with score " + this.getScore());
                        }
                        if (bagX.size() + bagY.size() + bagZ.size() == 0 ){
                            bagX.addAll(bagA);
                            bagY.addAll(bagB);
                            bagZ.addAll(bagC);
                            bagA.clear();
                            bagB.clear();
                            bagC.clear();
                        }
                        
                    }
                
                if (this.getScore() == 100){
                    System.out.println("The player " + this.getId() + " has won the game!!"+ counter);
                }
                System.exit(0);  
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0); 
                }
                
            }          
    
        //constructor
        
        public Player(int id){
            this.playerId = id;
        }
        //Setters/getters
        public void setPlayerId(int id){
            this.playerId = id+1;
        }
        public void setPlayerPebbles(List<Pebble> pebbles){
            this.playerPebbles = pebbles;
        }
        public void addPebble(Pebble pebble){
            this.playerPebbles.add(pebble);
        }
        public void setPlayerScore(int playerScore){
            this.playerScore = playerScore;
        }
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
        public void playerToFile(String filename, String string) throws IOException{
            FileWriter writer = new FileWriter(filename + "-output.txt");
            writer.write(string);
            writer.close();
        }
        public int getId(){
            return this.playerId;
        }
        public void setBlackBag(String playerBlackBag){
            this.playerBlackBag = playerBlackBag;
        }
        public void setWhiteBag(String playerWhiteBag){
            this.playerWhiteBag = playerWhiteBag;
        }
        public String getPlayerBlackBag(){
            return this.playerBlackBag;
        }
        public String getPlayerWhiteBag(){
            return this.playerWhiteBag;
        }
        public List<Pebble> getPlayerPebbles(){
            return this.playerPebbles;
        }
        public String toString(){
            return "Player ID: " +this.getId() + " , Score: " + this.getScore() + " , Pebbles: " + this.getPlayerPebbles();
        }
        public synchronized void firstTen(List<Pebble> randomBlackBag){
            try {
                    int randomIndex = ThreadLocalRandom.current().nextInt(0,randomBlackBag.size());
                    Pebble pebbleChoice = randomBlackBag.get(randomIndex); 
                    this.addPebble(pebbleChoice);
                    randomBlackBag.remove(randomIndex);
            } catch (Exception e){
                e.printStackTrace();
                System.exit(0);
            }
            
        }
    
        public synchronized void drawAndDiscard(List<Pebble> randomBlackBag, List<Pebble> randomWhiteBag){
            Random rand = new Random();
            List<Pebble> bagX = PebbleGame.this.getBagX().getPebbleWeights();
            List<Pebble> bagY = PebbleGame.this.getBagY().getPebbleWeights();
            List<Pebble> bagZ = PebbleGame.this.getBagZ().getPebbleWeights();
            List<Pebble> bagA = PebbleGame.this.getBagA().getPebbleWeights();
            List<Pebble> bagB = PebbleGame.this.getBagB().getPebbleWeights();
            List<Pebble> bagC = PebbleGame.this.getBagC().getPebbleWeights();
            try {
                //drawing from a bag to the hand
                int randomBlackIndex = rand.nextInt(randomBlackBag.size());
                Pebble drawChoice = randomBlackBag.get(randomBlackIndex);
                this.addPebble(drawChoice);
                randomBlackBag.remove(randomBlackIndex);
                //adding random player pebble to white bag
                int randomPlayerPebbleIndex = rand.nextInt(this.getPlayerPebbles().size());
                Pebble playerDiscard = this.getPlayerPebbles().get(randomPlayerPebbleIndex);
                
                if(playerDiscard.getBlackBag() == null){
                    System.out.println(playerDiscard);
                }
                if(playerDiscard.getBlackBag() == "X"){
                    bagA.add(playerDiscard);
                }else if(playerDiscard.getBlackBag() == "Y"){
                    bagB.add(playerDiscard);
                }else if(playerDiscard.getBlackBag() == "Z"){
                    bagC.add(playerDiscard);
                }
                this.getPlayerPebbles().remove(randomPlayerPebbleIndex);
            } catch (Exception e){
                e.printStackTrace();
                System.exit(0);
            }
            
            
        }
    
    }
    
    public static void main(String[] args) throws Exception {
        Bags bag = new Bags();
        PebbleGame pebbleGame = new PebbleGame();
        System.out.println("Welcome to pebble game!");
        System.out.println("You will be asked to enter the number of players");
        System.out.println("And then for the location of 3 files in turn containing comma seperated integer values for the pebble weights.");
        System.out.println("The integer values must be strictly positive.");
        System.out.println("The game will then be simulated, and output written to files in this directory.");
        Scanner noOfPlayers = new Scanner(System.in);
        System.out.println("Number of players?");
        String noOfPlayersString = noOfPlayers.nextLine();
        int noOfPlayersInt = Integer.parseInt(noOfPlayersString);
        List<Player> numberOfPlayers = new ArrayList<Player>();
        for (int i = 0; i <noOfPlayersInt; i++){
            PebbleGame.Player player = pebbleGame.new Player(i);
            pebbleGame.addPlayer(player);
        }
        Scanner fileChoice = new Scanner(System.in);
        System.out.println("Location of bag X");
        String fileChoiceStringX = fileChoice.nextLine();
        Bag.BlackBag blackBagX = bag.new BlackBag(fileChoiceStringX,noOfPlayersInt);
        System.out.println("Location of bag Y");
        String fileChoiceStringY = fileChoice.nextLine();
        Bag.BlackBag blackBagY = bag.new BlackBag(fileChoiceStringY,noOfPlayersInt);
        System.out.println("Location of bag Z");
        String fileChoiceStringZ = fileChoice.nextLine();
        Bag.BlackBag blackBagZ = bag.new BlackBag(fileChoiceStringZ,noOfPlayersInt);
        Bag.WhiteBag whiteBagA = bag.new WhiteBag();
        Bag.WhiteBag whiteBagB = bag.new WhiteBag();
        Bag.WhiteBag whiteBagC = bag.new WhiteBag();
        pebbleGame.setBagX(blackBagX);
        pebbleGame.setBagY(blackBagY);
        pebbleGame.setBagZ(blackBagZ);
        pebbleGame.setBagA(whiteBagA);
        pebbleGame.setBagB(whiteBagB);
        pebbleGame.setBagC(whiteBagC);
        fileChoice.close();
        for (PebbleGame.Player player: pebbleGame.getPlayers()){
            Thread thread = new Thread(player);
            thread.start();
        }
        noOfPlayers.close();
  
    }
}




