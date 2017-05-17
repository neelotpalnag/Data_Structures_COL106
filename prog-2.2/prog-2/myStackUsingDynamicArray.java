import java.lang.*;
import java.util.*;

class myStackUsingDynamicArray{

    private dynamicArray A;
    private int top;
    private int bottom;
    private int localSize;
    //Other variables to be defined by student

    public myStackUsingDynamicArray(){
        A = new dynamicArray();
        top=-1;
        bottom=-1;
        localSize=0;
        //Othe initializations to be done by student
    }

    //This method should return the size of the stack
    public int getSize(){
        //To be written by student
        return localSize;
    }

    //This should implement the push operation of stack
    public void push(int value){
        //To be written by student
        localSize++;
        top++;
        if(localSize>A.getSize()){
          A.doubleSize();
          try{
            A.modifyElement(value,top);
          }catch(Exception c){c.printStackTrace();}
        }
        else{try{
          A.modifyElement(value,top);
        }catch(Exception a){a.printStackTrace();} }
    }

    //This should implement the pop operation of stack.
    //This method should throw an exception in case the stack is empty.
    public int pop() throws EmptyStackException{
        //To be written by student
        if(top==-1){
          System.out.println("Stack is Empty!");
          throw new EmptyStackException();
        }
        else if(localSize==A.getSize()/2){
        	int tp=0;
        	try{
        		 tp=A.getElement(top);
        	}catch(Exception e){e.printStackTrace();}
          top--;localSize--;
          A.halveSize();
          return tp;
        }
        else{ int tp=0;
        	try{
       		 tp=A.getElement(top);
       	}catch(Exception e){e.printStackTrace();}
          top--;localSize--;
          
          return tp;
         }
    }

    public void printStack(){
      if(top!=-1){
        System.out.println("Top");
        for(int h=top;h>=0;h--){
        	try{
        		System.out.println(A.getElement(h));
       	}catch(Exception e){e.printStackTrace();}
          
        }System.out.println("Bottom");
      }
      else{System.out.println("Empty Stack!");}
    }

	public void Multipop(int k) throws Exception{
	if(localSize<k){System.out.println("Error in popping! Insufficient stack height!");
	throw new Exception();}
	
	else{for(int i=0;i<k;i++){
		int l = pop();
	}}
	
}


}
















