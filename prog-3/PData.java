public class PData{
  public String participantID;
  public String participantName;
  public String universityName;

  /*public PData(String pi, String pn, String un){
    participantID = pi;
    participantName = pn;
    universityName = un;}*/
    public PData(String a, String b, String c){
      //System.out.println("Construtor PData Called !");
      participantID = a;
      participantName = b;
      universityName = c;
    }
    public PData(){
      //System.out.println("Construtor PData Called !");
      participantID = "null";
      participantName = "null";
      universityName = "null";
    }
}
