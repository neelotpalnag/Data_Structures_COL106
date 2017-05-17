class EData extends PData{

  public int maxScoreKEY;
  public ParticipantMaxHeap P;
  public int NumberOfEntries;
  public String eventID;
  public String eventName;
  public String eventDescriptiom;
  /*public EData ParentE;
  public EData LeftEChild;
  public EData RightEChild;*/

  public EData(String ei, String en, String ed){
    eventID = ei;
    eventName = en;
    eventDescriptiom = ed;
    NumberOfEntries = 0;
    //System.out.println("Constructor Called EData!");
    P = new ParticipantMaxHeap();
    maxScoreKEY=0;
  }

  public void UpdateEventKEY(){
    if(P.ROOT!=null)maxScoreKEY = P.ROOT.ScoreKEY;
    else maxScoreKEY=0;
  }

}
