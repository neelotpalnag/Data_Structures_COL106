import java.util.*;
import java.io.*;
import java.lang.*;


class EventHeap extends ParticipantNode, ParticipantMaxHeap, PData{

    public EData[] Events;
    public int SIZE;

////////////////////////////////////////////////////////////////////////////////
  public EventHeap(){
    Events = new EData[1001 ];
    SIZE=0;
    Events[0] = Integer.MAX_VALUE;

  }
////////////////////////////////////////////////////////////////////////////////
private int ParentE(int pos){
        return pos/2;}

private int LeftEChild(int pos){
        return (2*pos);}

private int RightEChild(int pos){
        return (2*pos)+1;}
////////////////////////////////////////////////////////////////////////////////
  public boolean isLeaf(int pos){
    if (pos>=(SIZE/ 2) && pos<=SIZE){
       return true;
       }
       return false;
  }
////////////////////////////////////////////////////////////////////////////////
  public void swapNode(int a, int b){
    int temp;
    temp=Events[a];
    Events[a] = Events[b];
    Events[b] = temp;
  }
////////////////////////////////////////////////////////////////////////////////
  public void Heapify(int n){
    if(!isLeaf(n)){
      if(Events[n].ScoreKEY<Events[LeftEChild(n)].ScoreKEY || Events[n].ScoreKEY<Events[RightEChild(n)].ScoreKEY){
        if(Events[LeftEChild(n)].ScoreKEY>Events[RightEChild(n)].ScoreKEY){
          swap(n,LeftEChild(n));
          Heapify(LeftEChild(n));
        }
        else{
          swap(n,RightEChild(n));
          Heapify(RightEChild(n));
        }
      }
    }
  }
////////////////////////////////////////////////////////////////////////////////
  public void MaxHeapify(){
    for(int i=(SIZE/2);i>=1;i--){
      Heapify(i);
    }
  }
////////////////////////////////////////////////////////////////////////////////
  public void addNewEvent(EData ed){//add a new Eventin the Heap
    Elements[++SIZE] = ed;
    int SC = SIZE;
      while(Events[current].maxScoreKEY>Events[parentP(current)].maxScoreKEY){
          swapNode(current,ParentE(current));
          current = ParentE(current);
      }
      MaxHeapify();
  }
////////////////////////////////////////////////////////////////////////////////
  //public void deleteEvent(String id){//delete an entire event

  //}
////////////////////////////////////////////////////////////////////////////////
  //public void addParticipantInEvent(String eID, PData newP)//Adds a participant in an event
////////////////////////////////////////////////////////////////////////////////
  //public PData deleteParticipantInEvent(String eID, String pID)//Remove participant from an event
////////////////////////////////////////////////////////////////////////////////
/*  public void OverallTop3(){
    //Outputs Overall TOP3 in Rendezvous
  }*/
////////////////////////////////////////////////////////////////////////////////

}
