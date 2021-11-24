import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testGameWon(){ //TODO explain this in report
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
    //TODO Test run cannot be done due to threads

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
    public void testPlayerToFile(){

    }
    @Test
    public void testPebbleChoice(){
        int numberOfPlayers = 5;
        int playerID = 1;
        PebbleGame pebbleGame = new PebbleGame(numberOfPlayers);
        PebbleGame.Player player = pebbleGame.new Player(playerID);
    }

}