
class ParticipantMaxHeap extends PData{

  //public ParticipantNode[] PHeap;
  public ParticipantNode ROOT;
  public int SIZE;
  public int MAX_SIZE = 1000;
  public ParticipantNode LASTNODE;

  public ParticipantMaxHeap(){
    //PHeap = new ParticipantNode[MAX_SIZE];
    ROOT=new ParticipantNode();
    LASTNODE=new ParticipantNode();
    SIZE = 0;
    //System.out.println("Constructor Called ParticipantMaxHEap");
  }
////////////////////////////////////////////////////////////////////////////////
//Function to return the maximum element in the max-heap
  public int Max(){
  return ROOT.ScoreKEY;
  }
////////////////////////////////////////////////////////////////////////////////
//This function updates the last ParticipantNode where new insertions are supposed to take place
  public void IncrementLASTNODE(){
  if(LASTNODE==null){
    LASTNODE = ROOT;
    return ;}

    if(LASTNODE.RightPChild==null)return;
    ParticipantNode n = LASTNODE;
    while(n.ParentP!=null && n.ParentP.RightPChild==n){n=n.ParentP;}
    if(n.ParentP!=null){n=n.ParentP.RightPChild;}
    while(n.LeftPChild!=null){n=n.LeftPChild;}
    LASTNODE = n;
  }
////////////////////////////////////////////////////////////////////////////////
//This function updates the last ParticipantNode before Removal of Max. ParticipantNode
  public void DecrementLASTNODE(){

     if(LASTNODE.LeftPChild != null || LASTNODE.RightPChild != null)return;

     ParticipantNode p = LASTNODE;
     while(p.ParentP!=null && p.ParentP.LeftPChild == p)p = p.ParentP;
     if(p.ParentP != null)p = p.ParentP.LeftPChild;
     while(p.RightPChild != null)p = p.RightPChild;
     LASTNODE = p.ParentP;
   }
////////////////////////////////////////////////////////////////////////////////
//Outputs whether the Max Heap is empty or not
  public boolean isEmpty(){
    return (SIZE==0);
  }
////////////////////////////////////////////////////////////////////////////////
  //This function swaps two Nodes
  public void swapNodes(ParticipantNode a1, ParticipantNode a2) throws Exception{

     PData temp = a1.PDATA;
     int temp2 = a1.ScoreKEY;
     a1.ScoreKEY = a2.ScoreKEY;
     a1.PDATA = a2.PDATA;
     a2.ScoreKEY=temp2;
     a2.PDATA = temp;
  }
////////////////////////////////////////////////////////////////////////////////
  //This function implements the UP HEAP BUBBLING of a ParticipantNode
  public void upHeapBubble(ParticipantNode v){
    if(SIZE==1)return;
    else{
      while(v!=ROOT){
      if(v.ScoreKEY>v.ParentP.ScoreKEY){
        try {
          swapNodes(v.ParentP,v);
        }catch (Exception e) {
          System.out.println("Exception raised in ParticipantMaxHeap.java/method upHeapBubble");
          e.printStackTrace();
        }
        v=v.ParentP;
              }
      else{return;}
      }
    }
  }
////////////////////////////////////////////////////////////////////////////////
  //This function is used to INSERT A NEW ParticipantNode(or Participant) in the ParticipantMaxHeap
  public void InsertNew(PData data){
    ParticipantNode newP = new ParticipantNode(data);

    if(SIZE==0){
      ROOT=newP;
      LASTNODE = newP;
      SIZE++;
      return;
    }
    else{
      if(LASTNODE.LeftPChild==null){LASTNODE.LeftPChild=newP;}
      else{LASTNODE.RightPChild=newP;}
      newP.ParentP=LASTNODE;
      SIZE++;
      upHeapBubble(newP);
      IncrementLASTNODE();
    }

  }
////////////////////////////////////////////////////////////////////////////////

  //This function performs a DOWN HEAP BUBBLING of a ParticipantNode
  public void downHeapBubble(ParticipantNode v){
      if(SIZE==1){return;}
      else{
        while(v.LeftPChild!=null&&v.RightPChild!=null){
          if(v.ScoreKEY<v.LeftPChild.ScoreKEY){
            try {
              swapNodes(v,v.LeftPChild); v=v.LeftPChild;
            }catch (Exception e) {
              System.out.println("Exception raised in ParticipantMaxHeap.java/method downHeapBubble 1");
              e.printStackTrace();
            }
          }
          else if(v.RightPChild!=null&&v.ScoreKEY<v.RightPChild.ScoreKEY){
            try {
              swapNodes(v,v.RightPChild); v=v.RightPChild;
            }catch (Exception e) {
              System.out.println("Exception raised in ParticipantMaxHeap.java/method downHeapBubble 2");
              e.printStackTrace();
            }
          }
          else if(v.RightPChild!=null&&v.ScoreKEY>v.RightPChild.ScoreKEY){return;}
          else{return;}
        }
      }
  }
////////////////////////////////////////////////////////////////////////////////

    public void DeleteROOTNode(){
    PData r = ROOT.PDATA;
    if(SIZE==1){ROOT=LASTNODE=null; SIZE=0;}

    DecrementLASTNODE();
    ParticipantNode N;
    if(LASTNODE.RightPChild!=null){N=LASTNODE.RightPChild;LASTNODE.RightPChild=null;}
    else{N=LASTNODE.LeftPChild; LASTNODE.LeftPChild=null;}

    ROOT.ScoreKEY=N.ScoreKEY;
    ROOT.PDATA=N.PDATA;
    SIZE--;
    downHeapBubble(ROOT);
  }
////////////////////////////////////////////////////////////////////////////////
  /*public void FindParticipant(ParticipantNode n, String s){
    if(n!=null){
    FindParticipant(n.LeftPChild,id);
    if(n.PDATA.participantID==s){return   ;}
    FindParticipant(n.RightPChild,id);
    }
  }*/
  ////////////////////////////////////////////////////////////////////////////////
  public boolean FindAndDeleteParticipant(ParticipantNode n, String s){
    boolean found=false;
    if(n.PDATA.participantID.equals(s)){
      n.modifyScoreKEY(9999);
      upHeapBubble(n);
      DeleteROOTNode();
      System.out.println("Participant with ID: "+s+" has been deleted");
      return true;}
    else{
    if(n.LeftPChild!=null)found=FindAndDeleteParticipant(n.LeftPChild,s);
    if(n.RightPChild!=null)found=FindAndDeleteParticipant(n.RightPChild,s);
}

    /*if(n!=null){
    FindAndDeleteParticipant(n.LeftPChild,s);
    if(n.PDATA.participantID.equals(s)){
      n.modifyScoreKEY(9999);
      upHeapBubble(n);
      DeleteROOTNode();
      return n.PDATA;}
    else{FindAndDeleteParticipant(n.RightPChild,s);}
  }*/
    return found;}
////////////////////////////////                  //////////////////////////////
    public Boolean FindAndModifyParticipantScore(ParticipantNode n, String s, int ms){
      boolean found=false;
      if(n.PDATA.participantID.equals(s)){
        n.modifyScoreKEY(ms);
          System.out.println(n.ScoreKEY);
        upHeapBubble(n);
        return true;}
      else{
       if(n.LeftPChild!=null)found=FindAndModifyParticipantScore(n.LeftPChild,s,ms);
       if(found==false){
        if(n.RightPChild!=null)found=FindAndModifyParticipantScore(n.RightPChild,s,ms);
    }


}
    /*  if(n!=null){
      FindAndModifyParticipantScore(n.LeftPChild,s,ms);
      if(n.PDATA.participantID.equals(s)){
        n.modifyScoreKEY(ms);
        upHeapBubble(n);
        return true;}
      else{FindAndModifyParticipantScore(n.RightPChild,s,ms);}
    }*/

    return found;
    }
////////////////////////////////////////////////////////////////////////////////
  public void ModifyScoreParticipant(String id, int sc) throws Exception{
    Boolean done =false;
    done = FindAndModifyParticipantScore(ROOT,id,sc);
    if(done==false){
      System.out.println("Error in Modifying Score. No Participant Found! with ID : "+id);
      throw new Exception();
    }
    else System.out.println("Score Modified !");
  }
////////////////////////////////////////////////////////////////////////////////
  public void DeleteParticipant(String id) throws Exception{
    //Find the participant with the reqd. ID using an inOrder Traversal
    //Then, change it's Key to 9999(large number).
    //Then,upHeapBubble() this participant.
    //Then Simply Call DeleteROOTNode()
    boolean del=false;
    del = FindAndDeleteParticipant(ROOT, id);
    if(del==false){System.out.println("No such Participant Exists in the Database. Please check the Entered ID again.");
        throw new Exception();
      }
    //Output the removed student's DELDATA here
  }

////////////////////////////////////////////////////////////////////////////////
  /*public void UpdateScoreKEY (String id, int score) throws Exception{
    ParticipantNode up=null;
    up=FindAndModifyParticipantScore(ROOT,id);
    if(up==null){System.out.println("No such Participant Exists in the Database. Please check the Entered ID again.");
        throw new Exception();}
    else{up.modifyScoreKEY(score);}
    upHeapBubble(up);
    downHeapBubble(up);
  }*/
  /*public void Top3inEvent() {
    if(ROOT.ScoreKEY<=0){return;}
    else{System.out.println(ROOT.PDATA.participantID+", "
    +ROOT.PDATA.participantName+", "
    +ROOT.PDATA.universityName+", "+ROOT.ScoreKEY);
    if(ROOT.LeftPChild!=null){
      int d01 = ROOT.LeftPChild.ScoreKEY;
      if(ROOT.RightPChild!=null){
        int d02 = ROOT.RightPChild.ScoreKEY;
         if(d01>d02){
           //Top 2nd --->
           ParticipantNode T = ROOT.LeftPChild;
           System.out.println(T.PDATA.participantID+", " +T.PDATA.participantName+", "
             +T.PDATA.universityName+", "+T.ScoreKEY);
//
//
         }
         else if {
           ParticipantNode T = ROOT.LeftPChild;
           System.out.println(T.PDATA.participantID+", " +T.PDATA.participantName+", "
             +T.PDATA.universityName+", "+T.ScoreKEY);
//
//
         }
         else return;
      }
      // TOP 2nd ---->
      ParticipantNode T = ROOT.LeftPChild;
      System.out.println(T.PDATA.participantID+", " +T.PDATA.participantName+", "
        +T.PDATA.universityName+", "+T.ScoreKEY);
        if(T.LeftPChild!=null){
          int d03 = T.LeftPChild.ScoreKEY;
          if(T.RightPChild!=null){
            int d04 = T.RightPChild.ScoreKEY;
            if(d03>d04){
              ParticipantNode R = T.LeftPChild;
              System.out.println(R.PDATA.participantID+", " +R.PDATA.participantName+", "
                +R.PDATA.universityName+", "+R.ScoreKEY);
                return:
            }
            else{
              ParticipantNode R = T.RightPChild;
              System.out.println(R.PDATA.participantID+", " +R.PDATA.participantName+", "
                +R.PDATA.universityName+", "+R.ScoreKEY); return;
            }
          }
        }


    }
    else return;
    int d2 = ROOT.RightPChild.ScoreKEY;
    int d11 = ROOT.LeftPChild.LeftPChild.LeftPChild.ScoreKEY;
  }
}*/

public void Top3inEventMod(){
  int d0=0,d1=0,d2=0,d3=0,d4=0,d5=0,d6=0;
  if(ROOT!=null)   d0 = ROOT.ScoreKEY;
  if(ROOT.LeftPChild!=null) d1 = ROOT.LeftPChild.ScoreKEY;
  if(ROOT.RightPChild!=null) d2 = ROOT.RightPChild.ScoreKEY;
  if(ROOT.LeftPChild.LeftPChild!=null) d3 = ROOT.LeftPChild.LeftPChild.ScoreKEY;
  if(ROOT.LeftPChild.RightPChild!=null) d4 = ROOT.LeftPChild.RightPChild.ScoreKEY;
  if(ROOT.RightPChild.LeftPChild!=null) d5 = ROOT.RightPChild.LeftPChild.ScoreKEY;
  if(ROOT.RightPChild.RightPChild!=null) d6 = ROOT.RightPChild.RightPChild.ScoreKEY;
  if(d0==0) return;
  else System.out.println(ROOT.PDATA.participantID+", "
  +ROOT.PDATA.participantName+", "
  +ROOT.PDATA.universityName+", "+ROOT.ScoreKEY);

  if(d1>d2){
       ParticipantNode T = ROOT.LeftPChild;
     System.out.println(T.PDATA.participantID+", " +T.PDATA.participantName+", "
       +T.PDATA.universityName+", "+T.ScoreKEY);
       if(d2>d3&&d2>d4){ParticipantNode X = ROOT.RightPChild;
     System.out.println(X.PDATA.participantID+", " +X.PDATA.participantName+", "
       +X.PDATA.universityName+", "+X.ScoreKEY);
        }
    else if(d3>d2&&d3>d4){ParticipantNode A = ROOT.LeftPChild.LeftPChild;
     System.out.println(A.PDATA.participantID+", " +A.PDATA.participantName+", "
       +A.PDATA.universityName+", "+A.ScoreKEY);
     }
       else if(d4>d2&&d4>d3){
    ParticipantNode Q = ROOT.LeftPChild.RightPChild;
       System.out.println(Q.PDATA.participantID+", " +Q.PDATA.participantName+", "
         +Q.PDATA.universityName+", "+Q.ScoreKEY);      }
  else return;
  }

  else if(d2>d1){ ParticipantNode D = ROOT.RightPChild;
     System.out.println(D.PDATA.participantID+", " +D.PDATA.participantName+", "
       +D.PDATA.universityName+", "+D.ScoreKEY);
       if(d1>d5&&d1>d6){ParticipantNode V = ROOT.LeftPChild;
          System.out.println(V.PDATA.participantID+", " +V.PDATA.participantName+", "
            +V.PDATA.universityName+", "+V.ScoreKEY);
       }
       else if(d5>d1&&d5>d6){ParticipantNode B = ROOT.RightPChild.LeftPChild;
          System.out.println(B.PDATA.participantID+", " +B.PDATA.participantName+", "
            +B.PDATA.universityName+", "+B.ScoreKEY);}
       else if(d6>d5&&d6>d1){ParticipantNode N = ROOT.RightPChild.RightPChild;
          System.out.println(N.PDATA.participantID+", " +N.PDATA.participantName+", "
            +N.PDATA.universityName+", "+N.ScoreKEY);    }
        else return;
          }
  else return;
}
////////////////////////////////////////////////////////////////////////////////////
}
