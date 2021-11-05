import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.*;
public class PebbleGame{
    private ArrayList<Player> allPlayers = new ArrayList<>();
    private ArrayList<Thread> allThreads = new ArrayList<>();
    private ArrayList<Bags> allBags = new ArrayList<>();
    private Bags.BlackBag blackBagX;
    private Bags.BlackBag blackBagY;
    private Bags.BlackBag blackBagZ;
    private Bags.WhiteBag whiteBagA;
    private Bags.WhiteBag whiteBagB;
    private Bags.WhiteBag whiteBagC;
    public PebbleGame(){
    
    }
    
    public Bags.BlackBag randomBag(){
        Random rand = new Random();
        int bagChoice = rand.nextInt(3);
        if(bagChoice== 0){
            return this.getBagX();
        } else if(bagChoice == 1){
            return this.getBagY();
        } else if(bagChoice==2){
            return this.getBagZ();
        }
        return null;
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
    public int getNoOfThreads(){
        return this.allThreads.size();
    }
    public void addThread(Thread thread){
        this.allThreads.add(thread);
    }
    public ArrayList<Thread> getThread(){
        return this.allThreads;
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
        private List<Pebble> playerPebbles = new ArrayList<>();
        private int playerScore;
        private String playerBlackBag;
        private String playerWhiteBag;
        //Methods
        public void run(){
            int counter = 0;
            synchronized(this){
                    while(true){
                    if(this.getPlayerPebbles().size() < 10){
                        draw(this);
                    }else{
                        while(this.getScore() != 100){
                            System.out.println(this);
                            int bag = ThreadLocalRandom.current().nextInt(0, 3 + 1);
                            synchronized(this){
                                draw(this);
                                System.out.println("draw");
                                discard(this, bag);
                                System.out.println("discard"); 
                            }                         
                        }
                           
                    }           
                
                }
                
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
                score+=pebble.getValue();
            }
            this.playerScore = score;
            return this.playerScore;
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
        public List<Pebble> randomPebble(List<Pebble> pebbleWeights, Player player){
            Random rand = new Random();
            int randomIndex = rand.nextInt(pebbleWeights.size());
            Pebble pebbleChoice = pebbleWeights.get(randomIndex);
            pebbleWeights.remove(randomIndex);
            if (player.getPlayerPebbles().contains(pebbleChoice)){
            } else {
                player.addPebble(pebbleChoice);
            }
            return pebbleWeights;
        }
        
        public String draw(Player player){
            List<Pebble> bagX = PebbleGame.this.getBagX().getPebbleWeights();
            List<Pebble> bagY = PebbleGame.this.getBagY().getPebbleWeights();
            List<Pebble> bagZ = PebbleGame.this.getBagZ().getPebbleWeights();
            int bagChoice = ThreadLocalRandom.current().nextInt(0,3 + 1);
            int blackBagSize = bagX.size() + bagY.size() + bagZ.size();
            if (blackBagSize == 0){
                
            }else{
                if(bagChoice == 1){
                    player.setBlackBag("X");
                    player.setWhiteBag("A");
                    int randomIndex = ThreadLocalRandom.current().nextInt(0,bagX.size() +1);
                    Pebble pebbleChoice = bagX.get(randomIndex); 
                    player.addPebble(pebbleChoice); 
                    bagX.remove(randomIndex);
                    return "X";
                }else if(bagChoice == 2){
                    player.setBlackBag("Y");
                    player.setWhiteBag("B");
                    int randomIndex = ThreadLocalRandom.current().nextInt(0,bagY.size() +1);
                    Pebble pebbleChoice = bagY.get(randomIndex); 
                    player.addPebble(pebbleChoice); 
                    bagY.remove(randomIndex);
                    return "Y";
                
                }else if(bagChoice == 3){
                    player.setBlackBag("Z");
                    player.setWhiteBag("C");
                    int randomIndex = ThreadLocalRandom.current().nextInt(0,bagZ.size()+1);
                    Pebble pebbleChoice = bagZ.get(randomIndex); 
                    player.addPebble(pebbleChoice); 
                    bagZ.remove(randomIndex);
                    return "Z";
                }
                
            }
            return null;
            
        }
        public void discard(Player player, int bag){
            List<Pebble> bagA = PebbleGame.this.getBagA().getWhiteBagPebbles();
            List<Pebble> bagB = PebbleGame.this.getBagB().getWhiteBagPebbles();
            List<Pebble> bagC = PebbleGame.this.getBagC().getWhiteBagPebbles();
            int randomPebble = ThreadLocalRandom.current().nextInt(0, 10 + 1);
            Pebble discardChoice = player.getPlayerPebbles().get(randomPebble);
            System.out.println("size of bag: " + player.getPlayerPebbles().size());
            System.out.println("discarded: " + discardChoice);
            if(bag == 0){
                bagA.add(discardChoice);
                player.getPlayerPebbles().remove(discardChoice);
            }else if(bag == 1){
                bagB.add(discardChoice);
                player.getPlayerPebbles().remove(discardChoice);
            }else if(bag == 2){
                bagC.add(discardChoice);
                player.getPlayerPebbles().remove(discardChoice);
            }else{
                System.out.println("Cunt");
            }
        }



        // public void drawAndDiscard(Player player){ //finish this off with fixed if statements
        //     try {
        //         List<Integer> bagX = PebbleGame.this.getBagX().getPebbleWeights();
        //         List<Integer> bagY = PebbleGame.this.getBagY().getPebbleWeights();
        //         List<Integer> bagZ = PebbleGame.this.getBagZ().getPebbleWeights();
        //         int combinedSize = bagX.size() + bagY.size() + bagZ.size();
        //         while (combinedSize>0){
        //             //if combined size ==0 
        //             //empty all bags
        //             //
        //             /**
        //             while (player.getScore()!=100){ we could do this loop from inside the run!!
        //                 if combined size > 0:
        //                     choose from random bag
        //                 else:
        //                     empty bags back into it
        //             just another thought - i saw online the AtomicInteger variable - is there a way we can make a Pebble object and make it 
        //             atomic and save us some time??
        //             or do you think thats more effort?
        //             does it have to be an onject ?
        //             can we not just define it as atomic when we read them in
        //             true they are all integers or floats
        //             and we can use AtomicInteger and AtomicFloat?
        //             idk
        //             i think its a hassle
        //             the definiton of atomic is just that either the entire process works or none of it does 
        //             so we just need error checking
        //             i reckon we split it up into different methods
        //             player.draw() and player.discard()
        //             100% make it simpler that way
        //             agrred
        //             lets leave these notes here and do the lecture notes now
        //             kkk
        //             read them in as integers and convert to atomic integers
        //             when you draw return the name of the bag
        //              */
        //             Random rand = new Random();
        //             int bagChoice = rand.nextInt(3);
        //             if(bagChoice == 0 && bagX.size()>0){
        //                 List<Integer> bagA = PebbleGame.this.getBagA().getWhiteBagPebbles();
        //                 int randomIndex = rand.nextInt(bagX.size()); 
        //                 int pebbleChoice = bagX.get(randomIndex); 
        //                 player.getPlayerPebbles().add(pebbleChoice); 
        //                 bagX.remove(randomIndex);
        //                 int randomPlayerIndex = rand.nextInt(player.getPlayerPebbles().size()); 
        //                 int discardChoice = player.getPlayerPebbles().get(randomPlayerIndex); 
        //                 bagA.add(discardChoice);
        //                 player.getPlayerPebbles().remove(randomPlayerIndex);
        //             } else if(bagChoice == 1 && bagY.size() > 0){
        //                 List<Integer> bagB = PebbleGame.this.getBagB().getWhiteBagPebbles();
        //                 int randomIndex = rand.nextInt(bagY.size());
        //                 int pebbleChoice = bagY.get(randomIndex);
        //                 player.getPlayerPebbles().add(pebbleChoice);
        //                 bagY.remove(randomIndex);
        //                 int randomPlayerIndex = rand.nextInt(player.getPlayerPebbles().size());
        //                 int discardChoice = player.getPlayerPebbles().get(randomPlayerIndex);
        //                 bagB.add(discardChoice);
        //                 player.getPlayerPebbles().remove(randomPlayerIndex);
        //             } else if(bagChoice == 2 && bagZ.size() > 0){
        //                 List<Integer> bagC = PebbleGame.this.getBagC().getWhiteBagPebbles();
        //                 int randomIndex = rand.nextInt(bagZ.size());
        //                 int pebbleChoice = bagZ.get(randomIndex);
        //                 player.getPlayerPebbles().add(pebbleChoice);
        //                 bagZ.remove(randomIndex);
        //                 int randomPlayerIndex = rand.nextInt(player.getPlayerPebbles().size());
        //                 int discardChoice = player.getPlayerPebbles().get(randomPlayerIndex);
        //                 bagC.add(discardChoice);
        //                 player.getPlayerPebbles().remove(randomPlayerIndex);
        //             }
        //         }
        //     } catch (Exception e){
        //         System.out.println(e);
        //     }
        // }
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
        Bags.BlackBag blackBagX = bag.new BlackBag(fileChoiceStringX,noOfPlayersInt);
        System.out.println("Location of bag Y");
        String fileChoiceStringY = fileChoice.nextLine();
        Bags.BlackBag blackBagY = bag.new BlackBag(fileChoiceStringY,noOfPlayersInt);
        System.out.println("Location of bag Z");
        String fileChoiceStringZ = fileChoice.nextLine();
        Bags.BlackBag blackBagZ = bag.new BlackBag(fileChoiceStringZ,noOfPlayersInt);
        Bags.WhiteBag whiteBagA = bag.new WhiteBag();
        Bags.WhiteBag whiteBagB = bag.new WhiteBag();
        Bags.WhiteBag whiteBagC = bag.new WhiteBag();
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
//using threads choose random numbers from the array list(first choice is 10 pebbles)
//log details to output file
//if statement to decide which bad to take and add too 
//function to add and remove in bag (ATOMIC ACTION)
//threading player