import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;
import java.io.IOException;

import org.junit.Test;
public class testPebbleGame {
    
    @Test
    public void testPebbleGameConstructor(){
        int numberOfPlayers = 5;
        PebbleGame pebbleGame = new PebbleGame(numberOfPlayers);
        assertEquals(numberOfPlayers, pebbleGame.getNoOfPlayers());
    }

    @Test
    public void testGameWon(){ 
        int numberOfPlayers = 5;
        PebbleGame pebbleGame = new PebbleGame(numberOfPlayers);
        int playerID = 1;
        PebbleGame.Player player = pebbleGame.new Player(playerID);
        assertEquals(false, pebbleGame.gameWon(player, 1, 10));
    }

    @Test
    public void testAddPlayer(){
        int numberOfPlayers = 5;
        PebbleGame pebbleGame = new PebbleGame(numberOfPlayers);
        int playerID = 1;
        PebbleGame.Player player = pebbleGame.new Player(playerID);
        pebbleGame.addPlayer(player);
        assertEquals(1, pebbleGame.getPlayers().size());
    }
    @Test
    public void testRun(){
        int numberOfPlayers = 5;
        int playerID = 1;
        PebbleGame pebbleGame = new PebbleGame(numberOfPlayers);
        PebbleGame.Player player1 = pebbleGame.new Player(playerID);
        PebbleGame.Player player2 = pebbleGame.new Player(playerID+1);
        pebbleGame.addPlayer(player1);
        pebbleGame.addPlayer(player2);
        for (PebbleGame.Player players : pebbleGame.getPlayers()){
            Thread thread = new Thread(players);
            thread.start();
            assertEquals(true, thread.isAlive());
            thread.interrupt();
        }
    }

    @Test
    public void testPlayerConstructor(){
        int numberOfPlayers = 5;
        int playerID = 1;
        PebbleGame pebbleGame = new PebbleGame(numberOfPlayers);
        PebbleGame.Player player = pebbleGame.new Player(playerID);
        assertEquals(playerID, player.getId());
    }

    @Test
    public void testAddPebble(){
        int numberOfPlayers = 5;
        int playerID = 1;
        PebbleGame pebbleGame = new PebbleGame(numberOfPlayers);
        PebbleGame.Player player = pebbleGame.new Player(playerID);
        Pebble pebble = new Pebble(5, "X", "A");
        player.addPebble(pebble);
        assertEquals(1, player.getPlayerPebbles().size());
    }

    @Test
    public void testGetScore(){
        int numberOfPlayers = 5;
        int playerID = 1;
        PebbleGame pebbleGame = new PebbleGame(numberOfPlayers);
        PebbleGame.Player player = pebbleGame.new Player(playerID);
        assertEquals(0, player.getPlayerPebbles().size());
        assertEquals(0, player.getScore());
        int pebbleValue = 34;
        String blackBag = "X";
        String whiteBag = "A";
        Pebble pebble = new Pebble(pebbleValue, blackBag, whiteBag);
        player.addPebble(pebble);
        assertEquals(1, player.getPlayerPebbles().size());
        assertEquals(pebbleValue, player.getScore());

    }
    
    @Test
    public void testPebbleChoice() throws IOException{
        int numberOfPlayers = 5;
        int playerID = 1;
        PebbleGame pebbleGame = new PebbleGame(numberOfPlayers);
        PebbleGame.Player player = pebbleGame.new Player(playerID);
        Bag blackBagX = new Bag("X", "C:/Users/tommo/Desktop/Softare Development/example_file_2.csv", numberOfPlayers);
        Bag blackBagY = new Bag("Y", "C:/Users/tommo/Desktop/Softare Development/example_file_2.csv", numberOfPlayers);
        Bag blackBagZ = new Bag("Z", "C:/Users/tommo/Desktop/Softare Development/example_file_2.csv", numberOfPlayers);
        Bag whiteBagA = new Bag("A");
        Bag whiteBagB = new Bag("B");
        Bag whiteBagC = new Bag("C");
        pebbleGame.blackBags[0] = blackBagX; 
        pebbleGame.blackBags[1] = blackBagY;
        pebbleGame.blackBags[2] = blackBagZ;
        pebbleGame.whiteBags[0] = whiteBagA;
        pebbleGame.whiteBags[1] = whiteBagB;
        pebbleGame.whiteBags[2] = whiteBagC;
        for (int i = 0; i < 10; i++){
            player.pebbleChoice();
        }
        assertEquals(10, player.getPlayerPebbles().size());
        ArrayList<Integer> firstHand = new ArrayList<Integer>();
        for (Pebble ints : player.getPlayerPebbles()){
            firstHand.add(ints.getValue());
        }
        player.pebbleChoice();
        int counter = 0;
        for (int i = 0; i < player.getPlayerPebbles().size(); i++){
            if (firstHand.get(i)!=player.getPlayerPebbles().get(i).getValue()){
                counter++;
            }
        }
        assertEquals(1, counter);
    }

}