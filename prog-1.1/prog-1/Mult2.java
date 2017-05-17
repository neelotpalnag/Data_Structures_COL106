//THIS FILE NEEDS TO BE EDITED BY THE STUDENT

//This class extends the abstract class binaryOperations and should include an
//implementation of the multiplication algorithm. The algorithm that should be
//implemented should be the simple O(n^2) Divide and Conquer algorithm discussed
//in class.

public class Mult2 extends binaryOperations{

    public myBinaryNumber multiplication(myBinaryNumber N1, myBinaryNumber N2){
        //To be implemented by the student
        int var1,var2,n;
        var1=N1.getSize();
        var2=N2.getSize();
        if(var1>var2){
          n=var1;        }
        else{
          n=var2;        }
        myBinaryNumber A1 = new myBinaryNumber(n);
        myBinaryNumber A2 = new myBinaryNumber(n);
        for(int i=0;i<n;i++){
          try{
          A1.setBit(i,N1.getBit(i));
          A2.setBit(i,N2.getBit(i));}
          catch(Exception z){return null;}
        }

        myBinaryNumber NM = new myBinaryNumber(2*n);
        if(A1.getSize()==1&&A2.getSize()==1){
          try{
            NM.setBit(0,((A1.getBit(0))*(A2.getBit(0))));
          }catch(Exception E){
            return null;
          }
          return (NM);
        }

//////////////////////////////////////////////

int size = A1.getSize();
int hSize = A1.getSize() - size/2;
myBinaryNumber A1R = newSubArray(A1,0,hSize);
myBinaryNumber A1L = newSubArray(A1,hSize,size);
myBinaryNumber A2R = newSubArray(A2,0,hSize);
myBinaryNumber A2L = newSubArray(A2,hSize,size);

myBinaryNumber D1 = multiplication(A1L,A2L);
myBinaryNumber D2 = multiplication(A1L,A2R);
myBinaryNumber D3 = multiplication(A1R,A2L);
myBinaryNumber D4 = multiplication(A1R,A2R);

myBinaryNumber sec1,sec2;
sec1 = shiftToLeft(D1,2*hSize);
sec2 = shiftToLeft(zeroRemoved(addition(D2,D3)),hSize);

//return combinedArray(D1,D2,D3,D4);
return zeroRemoved(addition(addition(sec1,sec2),D4));
////////////////////////////////////////////////////////////////////////////////
//Method Territory ends.
    }

//Definition of instance "newSubArray" starts here.
//This instance accepts a myBinaryNumber and returns a new sub-array containing elements from
//position startpos-to-endpos.
public myBinaryNumber newSubArray(myBinaryNumber P, int startpos, int endpos){
    myBinaryNumber subArray = new myBinaryNumber(endpos-startpos);
    for(int t=startpos;t<endpos;t++){
      try {
        subArray.setBit(t-startpos,P.getBit(t));
      }catch (Exception e) {
        System.out.println("Error in newSubArray");
        return null;
      }
    }
    return subArray;
}// Method newSubArray ends here.
////////////////////////////////////////////////////////////////////////////////
//Method shiftToLeft begins here
//This method shifts the myBinaryNumber Q passed to it,
//by adding "amt" number of zeroes to the right of binary number Q.
//This is same as multiplying 2^amt to the binary in Q.
public myBinaryNumber shiftToLeft(myBinaryNumber Q, int amt){
  myBinaryNumber newBinary = new myBinaryNumber(Q.getSize()+amt);
  for (int t=0; t<Q.getSize(); t++){
				try {
				  newBinary.setBit(t+amt,Q.getBit(t));
				}catch (Exception e) {
				  System.out.println("Error in shiftToLeft ! ");
				}
			}
      return newBinary;
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
