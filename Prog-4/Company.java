import java.util.*;
import java.io.*;
import java.lang.*;

class Company{
  public String companyID;
  public String companyInformation;
  public int capacity;
  public int vacant;
  public boolean full;
  public HashMap<String, GN> gradMap;//String = Entry Numbers
  //public GAVL gradPrefAVL;
  public HashMap<Integer,String> gradprefMap;
  public HashMap<Integer,String> employedGrads;
  public int ctrOfMap;


  public Company(String cid, String cinfo, int cap){
    companyID = cid;
    companyInformation =cinfo;
    capacity = cap;
    vacant = cap;
    full = false;
    gradMap = new HashMap<String,GN>();
    gradprefMap = new HashMap<Integer,String>();
    employedGrads = new HashMap<Integer,String>();
    ctrOfMap=0;
    //gradPrefAVL = new GAVL();
  }
  public void addGraduatetoAVL(String gi,int i){
    ctrOfMap++;
    GN node = new GN(gi,i);
    //GN newNode;
    //newNode= gradPrefAVL.insert(gradPrefAVL.ROOT,i,gi);*/
    gradprefMap.put(i,gi);
    gradMap.put(gi,node);
  }

  public void printPreference(){
      //Performs InOrder Traversal of the AVL Tree and prints the Company IDs
      //gradPrefAVL.InOrderPrint(gradPrefAVL.ROOT);
      for(int w=1;w<=ctrOfMap;w++){
        if(gradprefMap.containsKey(w)){
          System.out.print(gradprefMap.get(w)+", ");
        }
      }
      System.out.println("");
  }

  public void updateCapacity(int k){
    capacity = k;
  }
  public void DeleteGraduate(String d){
  //  gradPrefAVL.deleteGN(gradPrefAVL.ROOT,gradMap.get(d).rankG);
    gradprefMap.remove(gradMap.get(d).rankG);
    gradMap.remove(d);
  }
  public void createEmployedGradMap(){
    employedGrads = new HashMap<Integer,String>();
  }

}
