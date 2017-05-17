import java.util.*;
import java.lang.*;

class myStackUsingLinkedList<E> extends SinglyLinkedList{

  private SinglyLinkedList<E> L;
  //Other variables to be defined by student
  private int sizeLocal;
  private Node<E> top;

  public myStackUsingLinkedList(){
      L = new SinglyLinkedList<>();
      //Other initializations to be done by student
      sizeLocal=0;
      top=null;
  }

  //This method should return the size of the stack
  public int getSize(){
      //To be written by student
      return sizeLocal;
  }

    //This should implement the push operation of stack
    public void push(E value){
        //To be written by student
        top = new Node<>(value,top);
        sizeLocal++;
    }

    //This should implement the pop operation of stack.
    //This method should throw an exception in case the stack is empty.
    public E pop()  throws Exception{
        //To be written by student
        if(sizeLocal==0){
          System.out.println("Stack Empty!");
          throw new Exception();
        }
        else{
          E popData = top.getData();
          top = top.getNext();
          sizeLocal--;
          if(sizeLocal==0){
            top=null;
          }
          return popData;

        }
    }

    public void printStack(){
      if(sizeLocal==0){
        System.out.println("Empty Stack.");
      }
      else{
        System.out.println("Top");
        Node<E> printer = new Node<>(top.getData(),top.getNext());
        while(printer!=null){
          System.out.println(printer.getData());
          printer=printer.getNext();
        }
          System.out.println("Bottom");
      }
    }
}
