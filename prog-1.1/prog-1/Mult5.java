//THIS FILE NEEDS TO BE EDITED BY THE STUDENT

//This class extends the abstract class binaryOperations and should include an
//implementation of the multiplication algorithm. The algorithm that should be
//implemented should be the simple O(n^2) Divide and Conquer algorithm discussed
//in class.

public class Mult5 extends binaryOperations{

    public myBinaryNumber multiplication(myBinaryNumber N1, myBinaryNumber N2){
        //To be implemented by the student
        int var1,var2,n;
        var1=N1.getSize();
        var2=N2.getSize();
        if(var1>var2){
          n=var1;        }
        else{
          n=var2;        }
        myBinaryNumber NM = new myBinaryNumber(2*n);
        if(N1.getSize()==1&&N2.getSize()==1){
          try{
            NM.setBit(0,((N1.getBit(0))*(N2.getBit(0))));
          }catch(Exception E){
            return null;
          }
          return NM;
        }

        myBinaryNumber A1 = new myBinaryNumber(n);
        myBinaryNumber A2 = new myBinaryNumber(n);
        for(int i=0;i<n;i++){
          try{
          A1.setBit(i,N1.getBit(i));
          A2.setBit(i,N2.getBit(i));}
          catch(Exception z){return null;}
        }
///////////////////////////////////////////////////
if(A1.getSize()==1&&A2.getSize()==1){
  myBinaryNumber A = new myBinaryNumber(1);
  int m= A1.getBit(0)*A2.getBit(0);
  try{
  A.setBit(0,m);
  }catch (Exception e) {
    return null;
  }
    return A;
}

int size = A1.getSize();
int hSize = A1.getSize() - size/2;
myBinaryNumber A1R = newSubArray(A1,0,size/2);
myBinaryNumber A1L = newSubArray(A1,size/2,size);
myBinaryNumber A2R = newSubArray(A2,0,size/2);
myBinaryNumber A2L = newSubArray(A2,size/2,size);

myBinaryNumber D1 = multiplication(A1L,A2L);
myBinaryNumber D2 = multiplication(A1L,A2R);
myBinaryNumber D3 = multiplication(A1R,A2L);
myBinaryNumber D4 = multiplication(A1R,A2R);

myBinaryNumber sec1,sec2;
sec1 = shiftToLeft(D1,2*hSize);
sec2 = shiftToLeft(addition(D2,D3),hSize);

//return combinedArray(D1,D2,D3,D4);
return addition(addition(sec1,sec2),D4);


///////////////////////////////////////////////////
        //multiplication function ends here.
//Method Territory ends.
    }
//Class Territory



//Definition of instance "multiplication" starts here.
/*public myBinaryNumber multiplication(myBinaryNumber A1, myBinaryNumber A2){
        if(A1.getSize()==1&&A2.getSize()==1){
          myBinaryNumber A = new myBinaryNumber(1);
          int m= A1.getBit(0)*A2.getBit(0);
          try{
          A.setBit(0,m);
          }catch (Exception e) {
            return null;
          }
            return A;
        }

        int size = A1.getSize();
        int hSize = A1.getSize() - size/2;
        myBinaryNumber A1R = newSubArray(A1,0,size/2);
        myBinaryNumber A1L = newSubArray(A1,size/2,size);
        myBinaryNumber A2R = newSubArray(A2,0,size/2);
        myBinaryNumber A2L = newSubArray(A2,size/2,size);

        myBinaryNumber D1 = multiplication(A1L,A2L);
        myBinaryNumber D2 = multiplication(A1L,A2R);
        myBinaryNumber D3 = multiplication(A1R,A2L);
        myBinaryNumber D4 = multiplication(A1R,A2R);

        myBinaryNumber sec1,sec2;
        sec1 = shiftToLeft(D1,2*hSize);
        sec2 = shiftToLeft(addition(D2,D3),hSize);

        //return combinedArray(D1,D2,D3,D4);
        return addition(addition(sec1,sec2),D4);
}//Method multiplication ends here.
*/


//Definition of instance "newSubArray" starts here.
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

//Definition of instance "combinedArray" starts here.
/*public myBinaryNumber combinedArray(myBinaryNumber T1,myBinaryNumber T2,myBinaryNumber T3,myBinaryNumber T4){
  int sz = T1.getSize()+T2.getSize()+T3.getSize()+T4.getSize();
  myBinaryNumber BitArrayCombined = new myBinaryNumber(sz);
  myBinaryNumber add1 = addition(T2,T3);
  myBinaryNumber add2 = new myBinaryNumber(add1.getSize()+(sz/2));
  myBinaryNumber add3 = new myBinaryNumber(sz);
  try {
    for(int q=0;q<sz/2;q++){add3.setBit(q,0);}
    for(int w=sz/2;w<sz;w++){add3.setBit(w,T1.getBit(w-(sz/2)));}

    for(int r=0;r<(add2.getSize()/2);r++){add2.setBit(r,0);}
    for(int x=(add2.getSize()/2);x<add2.getSize();x++){add2.setBit(x,add1.getBit(x-(add2.getSize()/2)));}

    BitArrayCombined = addition(addition(T4,add2),add3);
  }catch (Exception e) {
    return null;
  }
  return BitArrayCombined;
}*/
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

}
