import java.io.*;  
import java.util.*;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class PebbleGame{
    
    public class Players{

    }
        public static void main(String[] args) throws Exception  {
            Bags bag = new Bags();
            
            
            System.out.println("Welcome to pebble game!");
            System.out.println("You will be asked to enter the number of players");
            System.out.println("And then for the location of 3 files in turn containing comma seperated integer values for the pebble weights.");
            System.out.println("The integer values must be strictly positive.");
            System.out.println("The game will then be simulated, and output written to files in this directory.");
            Scanner noOfPlayers = new Scanner(System.in);
            System.out.println("Number of players?");
            String noOfPlayersString = noOfPlayers.nextLine();
            int noOfPlayersInt = Integer.parseInt(noOfPlayersString);
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
            
            
            
            Bags.WhiteBag whiteBags = bag.new WhiteBag();
                

                
    }
}

            
    
    
  

