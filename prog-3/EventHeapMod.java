import java.util.*;
import java.io.*;
import java.lang.*;


class EventHeapMod extends PData{

    public EData[] Events;
    public int SIZE;

////////////////////////////////////////////////////////////////////////////////
  public EventHeapMod(){
    Events = new EData[1001];
    SIZE=0;
    System.out.println("Constructor Called EventHeapMod!");
  }
////////////////////////////////////////////////////////////////////////////////
private int ParentE(int pos){
        return (pos-1)/2;}

private int LeftEChild(int pos){
        return (2*pos) +1;}

private int RightEChild(int pos){
        return (2*pos)+2;}
////////////////////////////////////////////////////////////////////////////////
  public boolean isLeaf(int pos){
    if (pos>=(SIZE/ 2) && pos<=SIZE){
       return true;
       }
       return false;
  }
  public boolean hasLeft(int pos){
    return LeftEChild(pos)<SIZE;
  }
  public boolean hasRight(int pos){
    return RightEChild(pos)<SIZE;
  }

////////////////////////////////////////////////////////////////////////////////
  public void swapNode(int a, int b){
    EData temp;
    temp=Events[a];
    Events[a] = Events[b];
    Events[b] = temp;
  }
////////////////////////////////////////////////////////////////////////////////
  public void upHeap(int pos){
    while(pos>0){
      int p = ParentE(pos);
      if(Events[pos].maxScoreKEY<=Events[p].maxScoreKEY)break;
      else{swapNode(pos,p);
      pos=p;}
    }
  }
////////////////////////////////////////////////////////////////////////////////
  public void downHeap(int pos){
    while(hasLeft(pos)){
      int leftIndex = LeftEChild(pos);
      int bigChildIndex= leftIndex;
      if(hasRight(pos)){
        int rightIndex = RightEChild(pos);
        if(Events[leftIndex].maxScoreKEY<Events[rightIndex].maxScoreKEY){
          bigChildIndex = rightIndex;
        }
      }
      if(Events[bigChildIndex].maxScoreKEY<Events[pos].maxScoreKEY)break;
      swapNode(pos,bigChildIndex);
      pos=bigChildIndex;
    }
  }
////////////////////////////////////////////////////////////////////////////////
  public void addNewEvent(EData ed){//add a new Eventin the Heap
    SIZE++;
    Events[SIZE-1] = ed;
    upHeap(SIZE-1);
    System.out.println("New Event has been added, with id : "+ed.eventID);
  }

////////////////////////////////////////////////////////////////////////////////
  public EData removeMax(){
    if(SIZE==0)return null;
    EData rEv = Events[0];
    swapNode(0,SIZE-1);
    Events[SIZE-1]=null;
    SIZE--;
    downHeap(0);
    return rEv;
  }
////////////////////////////////////////////////////////////////////////////////
  public void deleteEvent(String id) throws Exception{//delete an entire event
      //Find the Event by simple traversal
      //Change its maxScoreKEY to Very high.
      //Upheap to top.
      //Remove the top.
      int removePos=-1;
      for(int w=0;w<SIZE;w++){
        if(Events[w].eventID.equals(id)){
          removePos=w;
        }}
        if(removePos==-1){
          System.out.println("No Such Event Exists with ID:"+id+" .Please check!");
          throw new Exception();
        }
        else{
          Events[removePos].maxScoreKEY=Integer.MAX_VALUE;
          System.out.println("Event with ID:"+id+" has been deleted !");

        upHeap(removePos);
        EData rm = removeMax();
        SIZE--;
        }
        //Output the deleted event
  }
////////////////////////////////////////////////////////////////////////////////
  public void addParticipantInEvent(String eID, PData newP) throws Exception{//Adds a participant in an event
    int addPos=-1;
    for(int w=0;w<SIZE;w++){
      if(Events[w].eventID.equals(eID)){
        addPos=w;
      }}
      if(addPos==-1){
        System.out.println("No Such Event Exists with ID:"+eID+" .Please check!");
        throw new Exception();
      }
      else{
        Events[addPos].P.InsertNew(newP);
      }

  }
////////////////////////////////////////////////////////////////////////////////
  public void deleteParticipantInEvent(String eID, String pID) throws Exception{//Remove participant from an event
    int delPos=-1;
    for(int w=0;w<SIZE;w++){
      if(Events[w].eventID.equals(eID)){
        delPos=w;
      }}
      if(delPos==-1){
        System.out.println("No Such Event Exists with ID:"+eID+" .Please check!");
        throw new Exception();
      }
      else{
        try {
          Events[delPos].P.DeleteParticipant(pID);
          Events[delPos].UpdateEventKEY();
        }catch (Exception e) {
          e.printStackTrace();
        }
      }
  }
  public void deleteParticipantInAllEvents(String eID, String pID) {//Remove participant from an event
    int delPos=-1;
    for(int w=0;w<SIZE;w++){
      if(Events[w].eventID.equals(eID)){
        delPos=w;
      }}
      if(delPos==-1){
        return;
      }
      else{
        try {
          Events[delPos].P.DeleteParticipant(pID);
          Events[delPos].UpdateEventKEY();
        }catch (Exception e) {
          e.printStackTrace();
        }
      }

  }
////////////////////////////////////////////////////////////////////////////////
public void OverallTop3(){
    //Outputs Overall TOP3 in Rendezvous
    int d10,d11,d12,d20,d21,d22,d00,d01,d02;
    d11=d12=d10=d20=d21=d22=d00=d01=d02=0;
    EData E0 = Events[0];
    EData E1 = Events[1];
    EData E2 = Events[2];
    d00=E0.maxScoreKEY;
    d10=E1.maxScoreKEY;
    d20=E2.maxScoreKEY;
    d01=E0.P.ROOT.LeftPChild.ScoreKEY;
    d11=E1.P.ROOT.LeftPChild.ScoreKEY;
    d21=E2.P.ROOT.LeftPChild.ScoreKEY;
    d02=E0.P.ROOT.RightPChild.ScoreKEY;
    d12=E1.P.ROOT.RightPChild.ScoreKEY;
    d22=E2.P.ROOT.RightPChild.ScoreKEY;
    if(d00==0)return;
    else{System.out.println(E0.P.ROOT.PDATA.participantID+", "+E0.P.ROOT.PDATA.participantName+", "+
      E0.P.ROOT.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.ScoreKEY);
      if(d01>d02&&d01>d10&&d01>d20){
        System.out.println(E0.P.ROOT.LeftPChild.PDATA.participantID+", "+E0.P.ROOT.LeftPChild.PDATA.participantName+", "+
          E0.P.ROOT.LeftPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.LeftPChild.ScoreKEY);
                          int d011=E0.P.ROOT.LeftPChild.LeftPChild.ScoreKEY;
                          int d012=E0.P.ROOT.LeftPChild.RightPChild.ScoreKEY;
                          if(d011>d02&&d011>d012&&d011>d10&&d011>d20){
                            System.out.println(E0.P.ROOT.LeftPChild.LeftPChild.PDATA.participantID+", "+E0.P.ROOT.LeftPChild.LeftPChild.PDATA.participantName+", "+
                              E0.P.ROOT.LeftPChild.LeftPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.LeftPChild.LeftPChild.ScoreKEY);
                          }
                          else if(d012>d011&&d012>d02&&d012>d10&&d012>d20){
                            System.out.println(E0.P.ROOT.LeftPChild.RightPChild.PDATA.participantID+", "+E0.P.ROOT.LeftPChild.RightPChild.PDATA.participantName+", "+
                              E0.P.ROOT.LeftPChild.RightPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.LeftPChild.RightPChild.ScoreKEY);
                          }
                          else if(d02>d011&&d02>d012&&d02>d10&&d02>d20){
                            System.out.println(E0.P.ROOT.RightPChild.PDATA.participantID+", "+E0.P.ROOT.RightPChild.PDATA.participantName+", "+
                              E0.P.ROOT.RightPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.RightPChild.ScoreKEY);
                          }
                          else if(d10>d011&&d10>d012&&d10>d02&&d10>d20){
                            System.out.println(E1.P.ROOT.PDATA.participantID+", "+E1.P.ROOT.PDATA.participantName+", "+
                              E1.P.ROOT.PDATA.universityName+", "+E1.eventID+", "+E1.eventName+", "+E1.P.ROOT.ScoreKEY);
                          }
                          else if(d20>d011&&d20>d012&&d20>d02&&d20>d10){
                            System.out.println(E2.P.ROOT.PDATA.participantID+", "+E2.P.ROOT.PDATA.participantName+", "+
                              E2.P.ROOT.PDATA.universityName+", "+E2.eventID+", "+E2.eventName+", "+E2.P.ROOT.ScoreKEY);
                          }
                          else return;

      }//////////////////
      else if(d02>d01&&d02>d10&&d02>d20){
        System.out.println(E0.P.ROOT.RightPChild.PDATA.participantID+", "+E0.P.ROOT.RightPChild.PDATA.participantName+", "+
          E0.P.ROOT.RightPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.RightPChild.ScoreKEY);
          int d021=E0.P.ROOT.RightPChild.LeftPChild.ScoreKEY;
          int d022=E0.P.ROOT.RightPChild.RightPChild.ScoreKEY;
          ////////////******//////////////////////
          if(d021>d01&&d021>d022&&d021>d10&&d021>d20){
            System.out.println(E0.P.ROOT.RightPChild.LeftPChild.PDATA.participantID+", "+E0.P.ROOT.RightPChild.LeftPChild.PDATA.participantName+", "+
              E0.P.ROOT.RightPChild.LeftPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.RightPChild.LeftPChild.ScoreKEY);
          }
          else if(d022>d021&&d022>d01&&d022>d10&&d022>d20){
            System.out.println(E0.P.ROOT.RightPChild.RightPChild.PDATA.participantID+", "+E0.P.ROOT.RightPChild.RightPChild.PDATA.participantName+", "+
              E0.P.ROOT.RightPChild.RightPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.RightPChild.RightPChild.ScoreKEY);
          }
          else if(d01>d021&&d01>d022&&d01>d10&&d01>d20){
            System.out.println(E0.P.ROOT.LeftPChild.PDATA.participantID+", "+E0.P.ROOT.LeftPChild.PDATA.participantName+", "+
              E0.P.ROOT.LeftPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.LeftPChild.ScoreKEY);
          }
          else if(d10>d021&&d10>d022&&d10>d01&&d10>d20){
            System.out.println(E1.P.ROOT.PDATA.participantID+", "+E1.P.ROOT.PDATA.participantName+", "+
              E1.P.ROOT.PDATA.universityName+", "+E1.eventID+", "+E1.eventName+", "+E1.P.ROOT.ScoreKEY);
          }
          else if(d20>d021&&d20>d022&&d20>d01&&d20>d10){
            System.out.println(E2.P.ROOT.PDATA.participantID+", "+E2.P.ROOT.PDATA.participantName+", "+
              E2.P.ROOT.PDATA.universityName+", "+E2.eventID+", "+E2.eventName+", "+E2.P.ROOT.ScoreKEY);
          }
          else return;
          /////////////////////////////////

      }
      else if(d10>d01&&d10>d01&&d10>d20){
        System.out.println(E1.P.ROOT.PDATA.participantID+", "+E1.P.ROOT.PDATA.participantName+", "+
          E1.P.ROOT.PDATA.universityName+", "+E1.eventID+", "+E1.eventName+", "+E1.P.ROOT.ScoreKEY);
                      if(d11>d01&&d11>d02&&d11>d12&&d11>d20){
                        System.out.println(E1.P.ROOT.LeftPChild.PDATA.participantID+", "+E1.P.ROOT.LeftPChild.PDATA.participantName+", "+
                          E1.P.ROOT.LeftPChild.PDATA.universityName+", "+E1.eventID+", "+E1.eventName+", "+E1.P.ROOT.LeftPChild.ScoreKEY);
                      }
                      else if(d12>d01&&d12>d02&&d12>d11&&d12>d20){
                        System.out.println(E1.P.ROOT.RightPChild.PDATA.participantID+", "+E1.P.ROOT.RightPChild.PDATA.participantName+", "+
                          E1.P.ROOT.RightPChild.PDATA.universityName+", "+E1.eventID+", "+E1.eventName+", "+E1.P.ROOT.RightPChild.ScoreKEY);
                      }
                      else if(d01>d02&&d01>d11&&d01>d12&&d01>d20){
                        System.out.println(E0.P.ROOT.LeftPChild.PDATA.participantID+", "+E0.P.ROOT.LeftPChild.PDATA.participantName+", "+
                          E0.P.ROOT.LeftPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.LeftPChild.ScoreKEY);
                      }
                      else if(d02>d01&&d02>d11&&d02>d12&&d02>d20){
                        System.out.println(E0.P.ROOT.RightPChild.PDATA.participantID+", "+E0.P.ROOT.RightPChild.PDATA.participantName+", "+
                          E0.P.ROOT.RightPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.RightPChild.ScoreKEY);
                      }
                      else if(d20>d01&&d20>d02&&d20>d11&&d20>d12){
                        System.out.println(E2.P.ROOT.PDATA.participantID+", "+E2.P.ROOT.PDATA.participantName+", "+
                          E2.P.ROOT.PDATA.universityName+", "+E2.eventID+", "+E2.eventName+", "+E2.P.ROOT.ScoreKEY);
                      }
                      else return;

                    }
      else if(d20>d10&&d20>d01&&d20>d01){
        System.out.println(E2.P.ROOT.PDATA.participantID+", "+E2.P.ROOT.PDATA.participantName+", "+
          E2.P.ROOT.PDATA.universityName+", "+E2.eventID+", "+E2.eventName+", "+E2.P.ROOT.ScoreKEY);
                  if(d21>d01&&d21>d02&&d21>d10&&d21>d22){
                    System.out.println(E2.P.ROOT.LeftPChild.PDATA.participantID+", "+E2.P.ROOT.LeftPChild.PDATA.participantName+", "+
                      E2.P.ROOT.LeftPChild.PDATA.universityName+", "+E2.eventID+", "+E2.eventName+", "+E2.P.ROOT.LeftPChild.ScoreKEY);
                  }
                  else if(d22>d01&&d22>d02&&d22>d10&&d22>d21){
                    System.out.println(E2.P.ROOT.RightPChild.PDATA.participantID+", "+E2.P.ROOT.RightPChild.PDATA.participantName+", "+
                      E2.P.ROOT.RightPChild.PDATA.universityName+", "+E2.eventID+", "+E2.eventName+", "+E2.P.ROOT.RightPChild.ScoreKEY);
                  }
                  else if(d01>d02&&d01>d10&&d01>d21&&d01>d22){
                    System.out.println(E0.P.ROOT.LeftPChild.PDATA.participantID+", "+E0.P.ROOT.LeftPChild.PDATA.participantName+", "+
                      E0.P.ROOT.LeftPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.LeftPChild.ScoreKEY);
                  }
                  else if(d02>d01&&d02>d10&&d02>d21&&d02>d22){
                    System.out.println(E0.P.ROOT.RightPChild.PDATA.participantID+", "+E0.P.ROOT.RightPChild.PDATA.participantName+", "+
                      E0.P.ROOT.RightPChild.PDATA.universityName+", "+E0.eventID+", "+E0.eventName+", "+E0.P.ROOT.RightPChild.ScoreKEY);
                  }
                  else if(d10>d01&&d10>d02&&d10>d21&&d10>d22){
                    System.out.println(E1.P.ROOT.PDATA.participantID+", "+E1.P.ROOT.PDATA.participantName+", "+
                      E1.P.ROOT.PDATA.universityName+", "+E1.eventID+", "+E1.eventName+", "+E1.P.ROOT.ScoreKEY);
                  }
                  else return;
      }
      else return;



    }
    }



























////////////////////////////////////////////////////////////////////////////////

}
