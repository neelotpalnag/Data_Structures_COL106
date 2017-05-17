//THIS FILE NEEDS TO BE EDITED BY THE STUDENT

//This class extends the abstract class binaryOperations and should include an
//implementation of the multiplication algorithm. The algorithm that should be
//implemented is the simple O(n^2) Long Multiplication algorithm discussed
//in class.
import java.io.*;
import java.lang.*;
import java.util.*;

public class Mult1 extends binaryOperations{

    public myBinaryNumber multiplication(myBinaryNumber N1, myBinaryNumber N2){
        //To be implemented by the student
        int var1,var2,n;
        var1=N1.getSize();
        var2=N2.getSize();

        if(var1>var2){
          n=var1;
          /*for(int k=0;k<(var1-var2);k++){
            try{N2.setBit(var2+k,0);}
            catch(Exception e){return null;}
          }*/
        }
        else{
          n=var2;
          /*for(int q=0;q<(var2-var1);q++){
            try{N1.setBit(var1+q,0);}
            catch(Exception e){return null;}
          }*/
        }

    myBinaryNumber C = new myBinaryNumber(2*n);

    //This loop browses through the second multiplicand array B and calls the "ShiftAdd" func.
    //for every '1' bit in B.
    for(int i =0;i<2*n;i++){
        try{
          C.setBit(i,0);
        }catch(Exception e){
          return null;
        }}
        for(int j=0;j<var2;j++){
          if(N2.getBit(j)==1){ShiftAdd(C,N1,var1,j);}
        }
        return zeroRemoved(C);
    }
////////////////////////////////////////////////////////////////////////////////
    //ShiftAdd Function Definition here ->
    //This function performs a shifted addition of array A to result array C, whenever the loop pointer on array B
    //encounters a 1.
    public void ShiftAdd(myBinaryNumber Nn1, myBinaryNumber Nn2,int n,int s){
      int carry=0;
      int sum_local;
      int a=0;
      for(a=0;a<n;a++){
        sum_local=(Nn1.getBit(a+s))+(Nn2.getBit(a))+carry;
        try{Nn1.setBit(a+s,sum_local%2);}
        catch(Exception e){return;}
        carry=sum_local/2;
      }
      a=n;
      while(carry!=0){
        sum_local=Nn1.getBit(a+s)+carry;
        try{Nn1.setBit(a+s,sum_local%2);}catch(Exception e){return;}
        carry=sum_local/2;
        a=a+1;
      }
    }
    ////////////////////////////////////////////////////////////////////////////////
    //This method Removes the leading zeroes from the binary number passed to it.
    public myBinaryNumber zeroRemoved(myBinaryNumber N){
    int ctr =0;
    while(N.getBit(N.getSize()-ctr-1)!=1&&ctr!=N.getSize()-1){
    ctr++;
    }
    myBinaryNumber newB = new myBinaryNumber(N.getSize()-ctr);
    for(int t=0;t<newB.getSize();t++){
    try {
      newB.setBit(t,N.getBit(t));
    }catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error in zeroRemoved");
        }
      }
      return newB;
    }

}
