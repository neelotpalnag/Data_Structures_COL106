import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Simulate {

  public static EventHeapMod RDV;
  public static int ctr;
  public static PData[] MasterP;


  public static String removeCommas(String s){
      return (s.replace(",",""));
  }


public static void main(String[] args) {
  //////////////////MAIN METHOD ////////////////////////////////////////////////
  MasterP = new PData[10];
  System.out.println("WakeUp: -1 !");
  RDV = new EventHeapMod();
  ctr=0;

  try {
    BufferedReader BR = new BufferedReader(new FileReader("query.txt"));
    String Str = BR.readLine();
    String Str2 = removeCommas(Str);
    String Str3 =Str;

    for(int i=0;Str!=null; i++){
      String[] StrA = Str2.split(" ");
      String[] StrB = Str.split(",");
      //FOUR primary Conditions are possible
      //1.if ADD is found
          //1.1 EVENT is found
          //1.2 PARTICIPANT is found
          //Else(when Paricipant id is given for adding only participant)
      //2.if UPDATE is found
          //2.1 if SCORE is found
      //3.if DELETE is found
          //3.1 if EVENT is found
              //3.1.1 if PARTICIPANT is found
              //3.1.2 Else(when event is to be deleted)

          //3.2 if PARTICIPANT is found
      //4 if TOP3 is found
              //4.1 if IN is found (top3 in event)
              //4.2 Else
      if(StrA[0].equals("ADD")){
          if(StrA[1].equals("EVENT")){
            String eventCode = StrA[2];
            String eventName = StrA[3];
            String eventDesc= StrA[4];

            EData E = new EData(eventCode,eventName,eventDesc);
            RDV.addNewEvent(E);
          }
          else if(StrA[1].equals("PARTICIPANT")){
            //System.out.println("WakeUp: 0 !"+ StrA[3]);
            //MasterP[ctr].participantID = StrA[2];
            //MasterP[ctr].participantName  = StrA[3];
            //MasterP[ctr].universityName  = StrA[4];
            MasterP[ctr]= new PData(StrA[2],StrB[1],StrB[2]);
            //System.out.println("WakeUp: 1 !");
            ctr++;
          }
          else{
            String pid = StrA[1];
            String eid = StrA[2];
            int found =0;
            for(int y=0;y<ctr;y++){
              if(pid.equals(MasterP[y].participantID)){
                 try {
                   RDV.addParticipantInEvent(eid, MasterP[y]);
                   System.out.println("Added Participant: "+pid+" in Event: "+eid);
                   found=1;
                    break;
                 }catch (Exception e) {
                   e.printStackTrace();
                 }
              }
            }
              if(found==0) System.out.println("Participant " + pid+" not found in MAsterP");
          }
      }
      //-------------------------------------------------------------
      if(StrA[0].equals("UPDATE")){
        if(StrA[1].equals("SCORE")){
            String pid = StrA[2];
            String eid = StrA[3];
            int Score = Integer.parseInt(StrA[4]);
              for(int z=0;z<RDV.SIZE;z++){
                if(RDV.Events[z].eventID.equals(eid)){
                //Modify Score Here
                System.out.println("Event found with ID: "+RDV.Events[z].eventID);
                try {
                  RDV.Events[z].P.ModifyScoreParticipant(pid,Score);
                  break;
                }catch (Exception e) {
                  e.printStackTrace();
                }
                }
              }
        }
        else System.out.println("Error in query file - UPDATE XXXX, where XXXX is not SCORE");
      }
      //-------------------------------------------------------------
      if(StrA[0].equals("DELETE")){
        if(StrA[1].equals("EVENT")){
          if(StrA[2].equals("PARTICIPANT")){
            String pid = StrA[3];
            String eid = StrA[4];
            try {
              RDV.deleteParticipantInEvent(eid,pid);
            }catch (Exception e) {
              e.printStackTrace();
            }
            }
          else{
            String eid= StrA[2];
            try {
              RDV.deleteEvent(eid);
            }catch (Exception e) {
              e.printStackTrace();
             }
            }
        }
        else if(StrA[1].equals("PARTICIPANT")){
          String pid = StrA[2];
          for(int r=0;r<RDV.SIZE;r++){
            RDV.deleteParticipantInAllEvents(RDV.Events[r].eventID,pid);
          }
        }
        else System.out.println("Error in query DELETE ");
      }
      //-------------------------------------------------------------
     if(StrA[0].equals("TOP3")){

      if(Str3.length()>14){
        String[] StrA2= Str3.split(" ");
         String ec = StrA2[3];
         for(int ii=0;ii<RDV.SIZE;ii++){
           if(RDV.Events[ii].eventID.equals(ec)){
             System.out.println("Reached and found Event");
             RDV.Events[ii].P.Top3inEventMod();
             break;
           }
         }
       }
       else{
         RDV.OverallTop3();
    }
    }
      Str = BR.readLine();
      if(Str!=null){
      Str3=Str;
      Str2 = removeCommas(Str);}
    }


  }catch (FileNotFoundException e) {
    e.printStackTrace();
  }catch (IOException e) {
    e.printStackTrace();
  }

  //////////////////MAIN METHOD ENDS //////////////////////////////////////////
}

}
