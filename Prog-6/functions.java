/**
 * Created by Neelotpal Nag on 4/21/2017.
 */
import java.io.*;
import java.util.*;

public class functions {


    String FILENAME = "output.txt";

    public functions(){
        FILENAME="output.txt";
    }

    public void PrintToFile(){

        try{
            /*BufferedWriter BWR = new BufferedWriter(new FileWriter(FILENAME));
            BWR.write("0");*/
            FileWriter FWR = new FileWriter(FILENAME);
            FWR.write("0");
            FWR.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void PrintToFile(int[][] MAT){
        int n = MAT.length;
        try{
            FileWriter FWR = new FileWriter(FILENAME);
            FWR.write("1\n");
            for (int p=0;p<n;p++){
                for (int q=0;q<n-1;q++){
                    FWR.write(MAT[p][q]+",");
                }
                FWR.write(MAT[p][n-1]+"\n");
            }
            //FWR.write(MAT[n-1][n-1]);
            FWR.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
