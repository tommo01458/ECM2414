import java.util.*;
import java.io.*;
import java.util.Scanner;
public class Bags {
    public class BlackBag{
        public BlackBag(String fileChoiceString, int noOfPlayersInt)throws IOException{
            List <String> pebblesWeights = new ArrayList <>();
            try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/tommo/Desktop/Softare Development/"+ fileChoiceString))){
                String line;
                while ((line = br.readLine()) != null) {
                    pebblesWeights.addAll(Arrays.asList(line.split(",")));
                }
            } catch (FileNotFoundException e) {

            }

            if(pebblesWeights.size() > noOfPlayersInt*11){
                System.out.println("COOL");
            }else{
                System.out.println("Bad");
            }
        }
        
    }
    public class WhiteBag{
        public WhiteBag(){
            List <String> usedPebblesWeights = new ArrayList <>();
        }

    }
    
}


