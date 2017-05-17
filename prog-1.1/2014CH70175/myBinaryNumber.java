  //THIS CLASS SHOULD BE USED AS IT IS AND SHOULD NOT BE MODIFIED IN ANY MANNER

final class myBinaryNumber{
    private int[] N;
    private int size;

    //This conctructs an all 0's binary string of size n
    public myBinaryNumber(int n){
        size = n;
        N = new int[size];
    }

    //The input to this constructor is the number as a string
    public myBinaryNumber(String S){
        size = S.length();
        N = new int[size];
        //Store the number in an integer array
        for(int i=0;i<size;++i){
            N[i] = Character.getNumericValue(S.charAt(size-1-i));
        }
    }

    //This returns the size of the binary number
    public int getSize(){
        return(size);
    }

    //This method returns the i^th bit of the number from the LSB side
    //The 0th bit is N[0], 1st bit is N[1] and so on
    public int getBit(int position){
        //If the position asked for exceeds the length of the number then
        //0 is returned since any binary number remains the same when padded
        //with 0's in the MSB side.
        if(position >= size)return(0);
        else return(N[position]);
    }


    //This method sets the bit at a given position
    //If the position exeeds the size of the number or if the bit is not 0 or 1
    //then this method throws an Exception. This needs to be handled appropriately
    //by the programmer that uses this method.
    public void setBit(int position, int bit) throws Exception{
        if(position >= size || (bit != 0 && bit != 1) ){
          System.out.println("Error in setting the bit. Please check the inputs: (" + position + ", " + bit + ")");
        throw new Exception();
        }
        else
         N[position] = bit;
    }

    //This method prints the binary number
    public void printNumber(){
        for(int i = size-1;i>=0;--i){
            System.out.print(N[i]);
        }
        System.out.println();
    }
}
