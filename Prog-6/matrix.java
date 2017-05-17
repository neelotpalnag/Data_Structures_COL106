/**
 * Created by Neelotpal Nag on 4/19/2017.
 */
import java.io.*;
import java.util.*;
import java.lang.*;

public class matrix {

    static int[][] MAT;
    static int[] rowSumData;
    static ArrayList<ColumnData> colSumData;
    static int n;
    public static functions F;

    public void matrix(int n){
        MAT = new int[n][n];
        rowSumData = new int[n];
        colSumData = new ArrayList<ColumnData>();
    }
    //EXECUTE FUNCTION ---->  For every row, fill 1's in the matrix at k largest column numbers
    // Indexing of columns and rows starts from 0 --> n-1

    public static void main(String[] args) {
        //long ST= System.currentTimeMillis();
        try{
            BufferedReader BR = new BufferedReader(new FileReader("input.txt"));
            //READ THE FIRST LINE
            String S0 = BR.readLine();
            n = Integer.parseInt(S0);
            MAT = new int[n][n];
            rowSumData = new int[n];
            colSumData = new ArrayList<ColumnData>();
            //READ THE SECOND LINE
            String SReader = BR.readLine();
            String S1 = SReader.replace(","," ");
            String[] SA = S1.split(" ");
            for(int k=0;k<n;k++){
                    rowSumData[k]=Integer.parseInt(SA[k]);
                }
             //READ THIRD LINE
            SReader = BR.readLine();
            String S2= SReader.replace(","," ");
            String[] SB = S2.split(" ");
            for(int l=0;l<n;l++){

                colSumData.add(new ColumnData(l,Integer.parseInt(SB[l])));
            }


            // EXECUTE THE ALGORITHM HERE :
            int res = EXECUTE(n);
            F=new functions();
            if(res==-1){
                System.out.println("NP");
                F.PrintToFile();
            }else{
                //PRINT THE Matrix MAT in desired format
                F.PrintToFile(MAT);
            }
            //long ET= System.currentTimeMillis();
            //System.out.println(ET-ST);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //EXECUTE FUNCTION - Performs the Greedy Algorithm Implementation.
    static int EXECUTE(int n){

        for(int i=0;i<n;i++){
            int coins = rowSumData[i];
            //SORT THE  sortedColData Array on grounds of key.
            Collections.sort(colSumData);
            if(colSumData.get(0).colCap<=0){
                return -1;
            }
            else{
                for(int p=0;p<coins;p++){
                    ColumnData CS = colSumData.get(p);
                    if(CS.colCap<=0){
                        return -1;
                    }
                    else {
                        MAT[i][CS.colIndex] = 1;
                        colSumData.get(p).colCap--;
                    }
             }
            }
        }
        Collections.sort(colSumData);
        if(colSumData.get(0).colCap>0){
            return -1;
        }
        else{return 0;}
    }

}
