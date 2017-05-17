//THIS FILE NEEDS TO BE EDITED BY THE STUDENT

//This class extends the abstract class binaryOperations and should include an
//implementation of the multiplication algorithm. The algorithm that should be
//implemented is the O(n^{log 3}) Karatsuba algorithm discussed in class.
//However, there is a small change compared to the one implemented in Mult3.java
//The change is that before making recursive call to multiply smaller numbers, if you
//find that the numbers are of size <= 100, then call the longMultiplication method
//instead of making the recursive call. So, effectively the base case for the recursion
//is any number of size <=100 instead of when the number is of size 1 (or 2).

public class Mult7 extends binaryOperations{
//Implementation of LONG MULTIPLICATION methodwitnessed in Mult1.java

////////////////////////////////////////////////////////////////////////////////////////
//Karatsuba multiplication function starts here.
////////////////////////////////////////////////////////////////////////////////////////
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
      if(A1.getSize()<=100){
        try{
          NM.setBit(0,((A1.getBit(0))*(A2.getBit(0))));
        }catch(Exception E){
          return null;
        }
        return NM;
      }
///////////////////////////////////////////////////////////////////////
int size = A1.getSize();
int hSize = A1.getSize() - size/2;
myBinaryNumber A1R = newSubArray(A1,0,hSize);
myBinaryNumber A1L = newSubArray(A1,hSize,size);
myBinaryNumber A2R = newSubArray(A2,0,hSize);
myBinaryNumber A2L = newSubArray(A2,hSize,size);
//Note: zeroRemoved(myBinaryNumber B) removes the zeroes ahead of the greatest significant bit in the number.
myBinaryNumber D1 = zeroRemoved(multiplication(A1L,A2L));
myBinaryNumber D2 = zeroRemoved(multiplication(zeroRemoved(addition(A1R,A1L)),zeroRemoved(addition(A2R,A2L))));
myBinaryNumber D3 = zeroRemoved(multiplication(A1R,A2R));

myBinaryNumber T1 = zeroRemoved(subtract(D2,D1));
myBinaryNumber T2 = zeroRemoved(subtract(T1,D3));


myBinaryNumber sec1,sec2;
sec1 = shiftToLeft(D1,2*hSize);
sec2 = shiftToLeft(T2,hSize);

return zeroRemoved(addition(addition(sec1,sec2),D3));

///////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////
//This method returns the subtraction result of two arrays, one being greater than the other.
public myBinaryNumber subtract(myBinaryNumber lrgBit,myBinaryNumber smlBit){
int szlrg = lrgBit.getSize();
int borrow =0;
int borrowCtr=0;
myBinaryNumber subBit = new myBinaryNumber(szlrg);
for(int y=0;y<szlrg;y++){
  if(lrgBit.getBit(y)-smlBit.getBit(y)<0){
    borrowCtr=y+1;
    borrow=lrgBit.getBit(borrowCtr);
    while(borrow!=1){
      try {
        lrgBit.setBit(borrowCtr,1);
      }catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error in Subtract while-loop");
      }
      borrow=lrgBit.getBit(borrowCtr);
      borrowCtr++;
    }
    try {
      lrgBit.setBit(borrowCtr,0);
    }catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error in setting bits beyond borrowCtr to ZERO");
    }
    try {
      subBit.setBit(y,1);
    }catch(Exception w){
      System.out.println("Error in Setting final bits to 1 ");
    }}
    else{
      try {
        subBit.setBit(y,lrgBit.getBit(y)-smlBit.getBit(y));
      }catch(Exception e){
        System.out.println("Error in setting difference of binaries");
      }
    }
  }
  return subBit;
}
///////////////////////////////////////////////////////////////////////
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
  for (int q=0; q<amt; q++){
        try {
          newBinary.setBit(q,0);
        }catch (Exception e) {
          System.out.println("Error in shiftToLeft ! ");
        }
      }
  return newBinary;
}
////////////////////////////////////////////////////////////////////////
public myBinaryNumber zeroRemoved(myBinaryNumber N){
int ctr =0;
if(N.getSize()==1){return N;}
else{
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
return newB;}
}
    ////////////////////////////////////////////////////////////////////////
}
