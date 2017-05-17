import java.util.*;
import java.io.*;

class ParticipantNode extends PData{

  public int ScoreKEY;
  public PData PDATA;
  public ParticipantNode ParentP;
  public ParticipantNode LeftPChild;
  public ParticipantNode RightPChild;


  public ParticipantNode(PData data){
    PDATA = data;
    ScoreKEY = 0;
    LeftPChild = RightPChild = ParentP = null;
    //System.out.println("Constructor Called ParticipantNode!");
  }
  public ParticipantNode(){
    PDATA = new PData();
    ScoreKEY = 0;
    LeftPChild = RightPChild = ParentP = null;
    //System.out.println("Constructor Called ParticipantNode!");
  }
  /*public void modifyParticipantID(String newPID){
    PDATA.articipantID = newPID;
  }

  public void modifyParticipantName(String newPName){
    PDATA.participantName = newPName;
  }

  public void modifyUniversityName(String newUName){
    PDATA.universityName = newUName;
  }
*/
  public void modifyScoreKEY(int newKEY){
    ScoreKEY = newKEY;
  }

}
