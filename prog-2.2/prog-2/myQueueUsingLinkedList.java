class myQueueUsingLinkedList<E> extends SinglyLinkedList{

    private SinglyLinkedList<E> L;
    //Other variables to be defined by student
    private int sizeLocal;
    private Node<E> first;
    private Node<E> last;

    public myQueueUsingLinkedList(){
        L = new SinglyLinkedList<>();
        //Other initializations to be done by student
        sizeLocal=0;
        first=null;
        last=null;
    }

    //This method should return the size of the stack
    public int getSize(){
        //To be written by student
        return sizeLocal;
    }

    //This should implement the enqueue operation of stack
    public void enqueue(E value){
        //To be written by student
        Node<E> Fresh =new Node<>(value,null);
        if(sizeLocal==0){
          first=last=Fresh;
          first.setNext(last.getNext());
          sizeLocal++;
        }
        else{
          last.setNext(Fresh);
          last=Fresh;
          sizeLocal++;
        }
    }

    //This should implement the dequeue operation of stack.
    //This method should throw an exception in case the queue is empty.
    public E dequeue() throws Exception{
        //To be written by student
        if(sizeLocal==0){
          System.out.println("Queue Empty!");
          throw new Exception();
        }
        else{
          E dequedData = first.getData();
          first = first.getNext();
          sizeLocal--;
          if(sizeLocal==0){
            first=null;
          }
          return dequedData;
        }
    }

    public void printQueue(){
      if(sizeLocal==0)System.out.println("Queue Empty!");
      else{
        System.out.print("Front <-");
        Node<E> printer = new Node<>(first.getData(),first.getNext());
        while(printer!=null){
          System.out.print(printer.getData()+" <- ");
          printer=printer.getNext();
        }
        System.out.print("EOQ");
      }
    }

}
