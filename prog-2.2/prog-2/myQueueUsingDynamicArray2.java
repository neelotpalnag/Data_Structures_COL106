class myQueueUsingDynamicArray2{

    private dynamicArray A;
    private int front;
    private int rear;
    private int localSize;

    //Other variables to be defined by student

    public myQueueUsingDynamicArray2(){
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
    //This should implement the enqueue operation of Queue
    public void enqueue(int value){
        //To be written by student
        if(rear>front){
          if(rear==A.getSize()-1){
            if(localSize==A.getSize()){
              rear++;
              localSize++;A.doubleSize();
              try{
                A.modifyElement(value,rear);
              }catch(Exception e){System.out.println("Error Position 1- Enqueue");}
            }
            else{rear=0;
              localSize++;
            try{A.modifyElement(value,rear);
            }catch(Exception e){System.out.println("Error Position 2- Enqueue");}
          }
          }
          else{
            rear++; localSize++;
            try{A.modifyElement(value,rear);
            }catch(Exception e){System.out.println("Error Position 3- Enqueue");}
          }
        }
        else if(rear==front){
          if(rear==-1&&front==-1){
            rear=front=0;localSize++;
            try{A.modifyElement(value,rear);
            }catch(Exception e){System.out.println("Error Position 4- Enqueue");}
          }
          else{localSize++;
            if(localSize==A.getSize()){A.doubleSize();}
            try{
              int val=A.getElement(front);
              rear=1;front=0;
              A.modifyElement(value,rear);
              A.modifyElement(val,0);
            }catch(Exception e){System.out.println("Error Position 5- Enqueue");}
          }
        }
        else if(rear<front){
          if(rear==front-1){
            //int sz=A.getSize();
            A.doubleSize();
            for(int x=A.getSize()-1;x>(A.getSize()-(localSize-front+1));x--){
              try{
                A.modifyElement(A.getElement(localSize-(x-A.getSize()+1)-1),x);
              }catch(Exception e){System.out.println("Error Position 1- Enqueue");}
            }
            front=A.getSize()-(localSize-front);
            rear++;localSize++;
            A.doubleSize();
            try{A.modifyElement(value,rear);
            }catch(Exception e){System.out.println("Error Position 4- Enqueue");}
          }
          else{rear++;localSize++;
            try{A.modifyElement(value,rear);
            }catch(Exception e){System.out.println("Error Position 4- Enqueue");}}
        }
        else{
          System.out.println("Enqueue operation failed. No relevant case administered.");
          System.exit(0);
        }
    }
////////////////////////////////////////////////////////////////////////////////

    //This should implement the dequeue operation of Queue
    //This method should throw an exception in case the queue is empty.
    public int dequeue() throws Exception{
        if(isQueueEmpty()==true){
          System.out.println("Queue is UNPOPULATED. Deque() failed.");
          throw new Exception();
        }
        else if(front<rear){
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

        else if(front>rear){
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
          }
        }
          else if(front==rear&&localSize==1){
            int fr=0;
            try{fr = A.getElement(front);}catch(Exception w){w.printStackTrace();}
            front=rear=-1;
            localSize=0;
            return(fr);
          }
          else{return (99999);}
          return(999999);
      }

///////////////////////////////////////////////////////////////////////////////
          //New methods start here:
          public void printQueue(){
            if(front<=rear&&front!=-1){System.out.print("Front<-");
              for(int l=front;l<=rear;l++){
                try{
                System.out.print(A.getElement(l)+"<-");}catch(Exception m){m.printStackTrace();}
              }System.out.println("EOQ");
            }
            if(front>rear){System.out.print("Front");
              for(int s=front;s<A.getSize();front++){
                try{
                System.out.print(A.getElement(s)+"<-");}catch(Exception m){m.printStackTrace();}
              }
              for(int q=0;q<=rear;q++){try{
                System.out.print(A.getElement(q)+"<-");}catch(Exception m){m.printStackTrace();}
              }
              System.out.println("EOQ");
            }
            if(front==-1){
              System.out.println("Queue is EMPTY!");
            }
          }
            //3. Returns TRUE if queue is empty
            public boolean isQueueEmpty(){
              if(localSize==0)return true;
              else return false;
            }

              //4. Updates the dynamic array depending on conditions


    }
////////////////////////////////////////////////////////////////////////////////
