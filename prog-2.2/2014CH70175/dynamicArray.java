import java.lang.*;
import java.util.*;

class dynamicArray{
    private int[] A;//Reference to the array that is maintained.
    private int arraySize;//This stores the size of the current array A

    public dynamicArray(){
        //Start with an array of size 1
        A = new int[1];
        arraySize = 1;
    }

    //This method should return the current size of the dynamic array
    public int getSize(){
        return(arraySize);
    }

    //This method should double the size of the array A and copy all
    //the previous elements in the first half of the array (of double size)
    public void doubleSize(){
        //To be written by the student.
        int[] B = new int[arraySize*2];
        for(int t=0;t<arraySize;t++){
          B[t]=A[t];
        }
        arraySize=2*arraySize;
        A = B;
    }

    //This method should halve the size of the array by copying the first-half
    //in an array of half the size and ignoring the second-half
    public void halveSize(){
        //To be written by the student.
        int[] C=new int[arraySize/2];
        for(int r=0;r<arraySize/2;r++){
          C[r]=A[r];
        }
        arraySize=arraySize/2;
        C=A;
    }

    //This method should return the integer at array index "index"
    //If index exceeds the size of the array, then this
    //method should throw an exception
    public int getElement(int index) throws Exception{
        //To be written by the student.
      if(index<0||index>=arraySize){
          System.out.println(index + " - index not present in Array !");
        throw new Exception();      }
        else return A[index];
    }

    //This method should write integer "value" in array location "index"
    //In case, "index" exceeds the size of the array, then this method should
    //throw an exception.
    public void modifyElement(int value, int index) throws Exception{
        //To be written by the student.
        if(index<0||index>=arraySize){
          System.out.println(index + " - index not present in Array !");
          throw new Exception();
    }    A[index]=value;
}
}
