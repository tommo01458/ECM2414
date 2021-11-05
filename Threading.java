import java.util.Random;
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Threading implements Runnable{

    public int a;
    public List<Integer> pebbleWeights = new ArrayList<>();
    public int counter = 0;
    public int noOfGoes = 0;

    public Threading(int a) {
        this.a = a;
    }

    public void run(){
        int score = 0;
        try {
            while(score != 100){
            if (a == 100){
                score = a;
                System.out.print("\nYou win with a score of " + a);
                System.out.println("\nNo of goes =  " + noOfGoes);
            }else{
                System.out.print("\nYou have lost with a score of " + a);
                noOfGoes++;
                randomBag();
            }
        }
        } catch (Exception e){
            System.out.println("THERES A CUNTING ERROR");
        }
        
    }

    public void randomBag(){ //Random value from bag
        Random rand = new Random();
        int n = rand.nextInt(this.pebbleWeights.size()) + 1;
        int bagChoice = rand.nextInt((3 - 1) + 1) + 1;
        System.out.println(bagChoice);
        synchronized (this) {
            if( counter < 3){
                a += n;
                counter++;
            }else{
                a = 0;
                counter = 0;
            }
            
            
        }
    }

    public void loadBags()throws IOException{
        List <String> firstWeights = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/tommo/Desktop/Softare Development/example_file_1.csv"))){
            String line;
            while ((line = br.readLine()) != null) {
                
                firstWeights.addAll(Arrays.asList(line.split(",")));
            }
            for(String i: firstWeights){
                int newNum = Integer.parseInt(i);
                this.pebbleWeights.add(newNum);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found, please try again.");
        }
    }

    // public int getNoOfPebbles(List<Integer> pebbles){
    //     return pebbleWeights.size();
    // }

    public static void main(String[] args) throws IOException{

        Threading thread = new Threading(0);
        thread.loadBags();
        // use random to choose index from pebbleWeights
        // can then take and discard pebbles
        
        
        int base = 0;

        Threading sum2 = new Threading(base);

        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        Thread t3 = new Thread(thread);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();

        }catch (InterruptedException e) {
            e.printStackTrace();
        }
       
        
        
        
    }
}