class myQueueUsingDynamicArray{

    private dynamicArray A;
    private int front;
    private int rear;
    private int localSize;

    //Other variables to be defined by student

    public myQueueUsingDynamicArray(){
        A = new dynamicArray();
        front = -1;
        rear =  -1;
        localSize= 0;
        //Other initializations to be done by student
    }
    //This method should return the number of elements in the queue
    /*public int getSize(){
        //To be written by student
        return A.getSize();
    }*/
    public int getSize(){
        //To be written by student
        return localSize;
    }
////////////////////////////////////////////////////////////////////////////////
    //This should implement the enqueue operation of
    //The following method implements a Circular Queue using a Dynamic Array
    //The method uses For-Loops for some cases only, when the circular queue-array needs to be doubled
    //The amortized running time for the enqueue operation is O(1).
    public void enqueue(int value){
        //To be written by student
        if(front==-1&&rear==-1){                                          ///////////
          front=rear=0;
          try{A.modifyElement(value,0);}catch(Exception w){w.printStackTrace();}
          localSize++;
        }
        else if((rear-front)==(A.getSize()-1)&&rear!=0){  //Condition 1 for full circular array
          A.doubleSize();
        rear++;
        try{
          A.modifyElement(value,rear);
          localSize++;
        }catch(Exception e){
          e.printStackTrace();
        }
      }
        else if(rear==front-1){   //Condition 2 for full circular array
          A.doubleSize();
          for(int i=front;i<localSize;i++){
                try{
                  A.modifyElement(value,A.getSize()-localSize+i);
                }catch(Exception e){
                  e.printStackTrace();
                }
          }
          front=A.getSize()-localSize;
          rear++;
          //enque
          try{A.modifyElement(value,rear);
            localSize++;
          }catch(Exception e){
            e.printStackTrace();
          }
        }
        else if(rear==A.getSize()-1&&front>0){  //Condition 3 for circular array
          rear=0;
          try{A.modifyElement(value,rear);
            localSize++;
          }catch(Exception e){
            e.printStackTrace();
          }
        }
        else if(rear==A.getSize()-1&&front==0){   //Condition 4 for circular array
          A.doubleSize();
          rear++;
          try{A.modifyElement(value,rear);
          }catch(Exception e){
            e.printStackTrace();
          }      localSize++;
        }
        else{
          rear++;
          try{A.modifyElement(value,rear);
              localSize++;
          }catch(Exception e){
            e.printStackTrace();
          }
        }
    }
////////////////////////////////////////////////////////////////////////////////

    //This should implement the dequeue operation of Queue
    //This method should throw an exception in case the queue is empty.
    //The following method implements a Circular Queue using a Dynamic Array
    //The method uses For-Loops for some cases only, when the circular queue-array needs to be halved.
    //The amortized running time for the enqueue operation is O(1).

    public int dequeue() throws Exception{
        if(isQueueEmpty()==true){                                       ////////
          System.out.println("Queue is UNPOPULATED. Deque() failed.");
          throw new Exception();
        }
        else if(front<rear){                                           /////////
          int fr=A.getElement(front);
            front++;
            localSize--;
            if(localSize==A.getSize()/2){
            for(int l=front;l<=rear;l++){
              try{A.modifyElement(A.getElement(l),l-front); }
              catch(Exception w){w.printStackTrace();}
            }
            A.halveSize();
            front=0;
            rear=front+localSize-1;
            }
          return fr;}

        else if(front>rear){                                          //////////
          int fr=0;
          if(front==A.getSize()-1){
            fr=A.getElement(front);
            front=0;
            localSize--;
            if(localSize==A.getSize()/2)A.halveSize();
            return fr;
          }
          else{
            fr=A.getElement(front);
            front++;
            localSize--;
            if(localSize==A.getSize()/2){
              for(int d=1;d<=A.getSize()-front;d++){
                try{
                  A.modifyElement(A.getElement(front+d-1),rear+d);
                }catch(Exception e){e.printStackTrace();}}}
                return fr;
          }
        }
          else if(front==rear&&localSize==1){
            int fr=0;
            try{fr = A.getElement(front);}catch(Exception w){w.printStackTrace();}
            front=rear=-1;
            localSize=0;
            return(fr);
          }
          else{
            return (99999); }

      }

///////////////////////////////////////////////////////////////////////////////
          //New methods start here:
          public void printQueue(){
            if(front<=rear&&front!=-1){System.out.print("Front<-");                               //////////////////C1
              for(int l=front;l<=rear;l++){
                try{
                System.out.print(A.getElement(l)+"<-");}catch(Exception m){m.printStackTrace();}
              }System.out.println("EOQ");
            }
            if(front>rear){System.out.print("Front <-");                                             ///////////////////C2
              for(int s=front;s<A.getSize();s++){
                try{
                System.out.print(A.getElement(s)+"<-");}catch(Exception m){m.printStackTrace();}
              }
              for(int q=0;q<=rear;q++){try{
                System.out.print(A.getElement(q)+"<-");}catch(Exception m){m.printStackTrace();}
              }
              System.out.println("EOQ");
            }
            if(front==-1){                                                                         //////////////////C3
              System.out.println("Queue is EMPTY!");
            }
          }
            //3. Returns TRUE if queue is empty
            public boolean isQueueEmpty(){
              if(localSize==0)return true;
              else return false;
            }

            /*public int returnMax() throws Exception{
              if(localSize==0){
                System.out.println("Queue is Empty!");
                throw new Exception();
              } //
              else if(front<=rear&&front!=-1){
                int max=-1;
                try{max= A.getElement(front);}catch(Exception e){e.printStackTrace();}
                for(int i=front;i<=rear;i++){
                  try{
                    if(A.getElement(i)>front)max=A.getElement(i);
                  }catch(Exception e){e.printStackTrace();}
                }
                for(int i=front;i<=rear;i++){
                  try{
                    if(A.getElement(i)>front)max=A.getElement(i);
                  }catch(Exception e){e.printStackTrace();}
                }
              }
            }*/

    }
////////////////////////////////////////////////////////////////////////////////
