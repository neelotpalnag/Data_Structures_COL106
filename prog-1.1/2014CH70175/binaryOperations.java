//THIS CLASS SHOULD BE USED AS IT IS AND SHOULD NOT BE MODIFIED IN ANY MANNER

abstract class binaryOperations{

    //Given an n-bit number and an m-bit number, the method below outputs a binary number
    //of size max{n, m}+1 which is the sum of the given numbers
    public myBinaryNumber addition(myBinaryNumber N1, myBinaryNumber N2){
        //Find the size of the numbers
        int size1 = N1.getSize();
        int size2 = N2.getSize();
        int size = size1;
        if(size2 > size1)size = size2;

        //Create an all 0's binary number of size size+1
        myBinaryNumber N = new myBinaryNumber(size+1);

        int position;
        int sum, carry = 0;
        for(position = 0; position < size;++position){
            sum = N1.getBit(position) + N2.getBit(position) + carry;
            try{
              N.setBit(position, sum%2);
            }catch(Exception e){e.printStackTrace();System.exit(0);}
            carry = sum/2;
        }
        try{
            N.setBit(position, carry);
        }catch(Exception e){e.printStackTrace();System.exit(0);}
        return(N);
    }

  public boolean equal(myBinaryNumber N1,myBinaryNumber N2){
    int s1 = N1.getSize();
    int s2 = N2.getSize();
    int n = (s1>s2)?s1:s2;
    for(int p=0;p<n;p++){
      if(N1.getBit(p)!=N2.getBit(p)){
        return false;
      }
    }
      return true;
    }



    //Given an n-bit number and an m-bit number, the method below should return an
    //(n+m)-bit number which is the product of the given numbers.
    abstract myBinaryNumber multiplication(myBinaryNumber N1, myBinaryNumber N2);
}
