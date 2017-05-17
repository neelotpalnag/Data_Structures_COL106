import java.util.*;
import java.io.*;
import java.lang.*;

class Simulate {
//Some stuff..
//HashMap for all students:
static HashMap<String,Graduate> GradData = new HashMap<String,Graduate>();
static HashMap<String,Company> CompanyData = new HashMap<String,Company>();
static int noOfGraduates = GradData.size();
static int noOfCompanies = CompanyData.size();

public static boolean CheckValidity(){
//  Iterator T = CompanyData.entrySet().iterator();
  int brk = 0;
  for(Map.Entry<String,Company> entry:CompanyData.entrySet()){
    //Map.Entry pair1 = (Map.Entry) T.next();
    Company CV = entry.getValue();
    //Iterator t = CV.gradMap.entrySet().iterator();
    String st = entry.getKey();
    for(Map.Entry<String,GN> entry2:CV.gradMap.entrySet()){
    //  Map.Entry pair2 = (Map.Entry) t.next();
      String cs = entry2.getKey();
      if(GradData.get(cs).companyMap.containsKey(st)==false){
        System.out.println("Exception: Incorrect Matching Found for Company :"+st+" which contains graduate:"+cs+" who doesn't have the company in it's preference list.");
        System.out.println("Note: This is the first-caught exception. More suh combiations are possible.  ABORTING MATCH");
        brk =1;
        break;
      }
    }
    if(brk==1){
      System.out.println("CheckValididty FAILED! MATCH will not be processed.");
      return false;
    }
  }
  return true;
}

public static boolean tryPlacement(Graduate g1){
//  g1.makePrefCompanyMap();
  for(int i=1;i<=g1.ctrOfMap;i++){
    if(g1.companyPrefMap.containsKey(i)==true&&g1.companyMap.containsKey(g1.companyPrefMap.get(i))){
      CN czz = g1.companyMap.get(g1.companyPrefMap.get(i));
      Company cz = CompanyData.get(czz.companyID);
      if(cz.full!=true){            //When the company has a vacancy
        cz.employedGrads.put(cz.gradMap.get(g1.graduateID).rankG,g1.graduateID);
        cz.vacant--;
        if(cz.vacant==0)cz.full=true;
        CompanyData.put(cz.companyID,cz);
        g1.employed=true;
        g1.employedBy=cz.companyID;
        GradData.put(g1.graduateID,g1);
        return true;
      }
      else if(cz.full==true){
        int maxRank=0;
        int reqRank = cz.gradMap.get(g1.graduateID).rankG;
        for(Map.Entry<Integer,String> foo:cz.employedGrads.entrySet()){
          if(foo.getKey()>maxRank) maxRank=foo.getKey();
          }
        if(maxRank>reqRank){
          cz.employedGrads.put(reqRank,g1.graduateID);
          cz.employedGrads.remove(maxRank);
          //Graduate gz= GradData.get(cz.gradprefMap.get(maxRank));
          cz.vacant--;
          if(cz.vacant==0)cz.full=true;
          CompanyData.put(cz.companyID,cz);
          g1.employed=true;
          g1.employedBy=cz.companyID;
          GradData.put(g1.graduateID,g1);
          return true;
          }
        }

      }
    }return false;
}
/////////////////////////////////////////////////////////////////////
public static void printResults(){
  for(Map.Entry<String,Graduate> TY:GradData.entrySet()){
    System.out.println(TY.getKey()+", "+TY.getValue().employedBy);
  }
}
/////////////////////////////////////////////////////////////////////
public static void MATCH(){
  int numberEmployed = 0;
  int noOfFullCompanies = 0;
  int totalGraduates = GradData.size();
  int noOfLoops=0;
  // Match using all the data given in the above HashMaps!
 while((numberEmployed!=totalGraduates)&&(noOfFullCompanies!=noOfCompanies)&&(noOfLoops!=noOfCompanies)){
  for(Map.Entry<String,Graduate> GP: GradData.entrySet()){
    Graduate g1 = GP.getValue();
    boolean placed = tryPlacement(g1);
    if(placed == true) numberEmployed++;
  }
  for(Map.Entry<String,Company> CP:CompanyData.entrySet()){
    if(CP.getValue().full==true) noOfFullCompanies++;
  }
  noOfLoops++;
  }//end While
  printResults();
}
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
public static String removeCommas(String s){
    return (s.replace(",",""));
}
    public static void main(String[] args) {
      long startTime = System.currentTimeMillis();

      try{
      BufferedReader BR = new BufferedReader(new FileReader("medium1.txt"));
      String Str = BR.readLine();
      String Str2 = removeCommas(Str);

      for(int i=0;Str!=null;i++){
        String[] StrA = Str2.split(" ");
        String[] StrB = Str.split(",");
        //Four Cases:
        /*
        ADD
          -graduate
          -company
        RANK
          -graduates
        DISPLAY
          -company
          -graduates
        DISPLAY
          -ranking of a company
        UPDATE
          -capacity of a company
          -cgpa
          -graduate preference
        DELETE
          -company
          -graduate
        MATCH
        */
        if(StrA[0].equals("ADD")){
          if(StrA[1].equals("COMPANY")){
            Company C = new Company(StrA[2],StrB[1],Integer.parseInt(StrA[4])); //Now add this company to HashMap CompanyData
            CompanyData.put(StrA[2],C);
            noOfCompanies++;
          }
          else if(StrA[1].equals("GRADUATE")){
            Graduate G = new Graduate(StrA[2],StrB[1],Double.parseDouble(StrA[4]));
            for(int iq=1;iq<(StrA.length-4);iq++){
            //  System.out.println("Adding "+StrA[4+iq] +" at rank "+iq);
              G.addCompanytoAVL(StrA[4+iq],iq);
            }
            GradData.put(StrA[2],G);
            noOfGraduates++;
          }
          else{
            System.out.println("Invalid Query line. Expected : ADD GRADUATE ... ... ...");
          }
        }
        else if(StrA[0].equals("RANK")){
          if(StrA[1].equals("GRADUATES")){
            int wrong = 0;
            Company cc = CompanyData.get(StrA[2]);
            for(int j=1;j<(StrA.length-2);j++){
              String kk = StrA[j+2];
              /*for(Map.Entry<String,GN> entry2:cc.gradMap.entrySet()){
                String cs = entry2.getKey();
                if(GradData.get(kk).companyMap.containsKey(StrA[2])==false){
                  wrong =1;
                  break;
                }
              }*/
              if(wrong==0) cc.addGraduatetoAVL(StrA[j+2],j);
              else break;
            }
              if(wrong==0)CompanyData.put(StrA[2],cc);
              else System.out.println("Exception");
          }
          else{
            System.out.println("Invalid Query line. Expected : RANK GRADUATES ... ... ...");
          }
        }
        else if(StrA[0].equals("DISPLAY")){
          if(StrA[1].equals("COMPANY")){
            Company   CD = CompanyData.get(StrA[2]);
            System.out.println(CD.companyInformation+", "+CD.capacity);
          }
          else if(StrA[1].equals("GRADUATE")){
            Graduate GD = GradData.get(StrA[2]);
            System.out.print(GD.graduateName+", "+GD.CGPA);
            GD.printPreference();
            System.out.println("");
          }
          else if(StrA[1].equals("RANKING")){
            //Display Ranking list of CompanyID StrA[2]
            Company dd = CompanyData.get(StrA[2]);
            dd.printPreference();
          }
          else{
              System.out.println("Invalid Query line. Expected : DISPLAY RANKING");
          }
        }
        else if(StrA[0].equals("UPDATE")){
          if(StrA[1].equals("CAPACITY")){
            //Update Capacity of StrA[2] to StrA[3]
            Company Q = CompanyData.get(StrA[2]);
            Q.updateCapacity(Integer.parseInt(StrA[3]));
            CompanyData.put(StrA[2],Q);
          }
          else if(StrA[1].equals("CGPA")){
            Graduate gg = GradData.get(StrA[2]);
            gg.updateCGPA(Double.parseDouble(StrA[3]));
            GradData.put(StrA[2],gg);
          }
          else if(StrA[1].equals("GRADUATE")&&StrA[2].equals("PREFERENCE")){
            Graduate GG = GradData.get(StrA[3]);
            GG.updatePreferences();
            for(int i1=1;i1<(StrA.length-3);i1++){
              GG.addCompanytoAVL(StrA[i1+3],i1);
            }
            GradData.put(StrA[3],GG);
          }
          else{
              System.out.println("Invalid Query line. Expected : UPDATE ... ... ...");
          }
        }
        else if(StrA[0].equals("DELETE")){
          if(StrA[1].equals("COMPANY")){
            //Delete the Company from everywhere!
            String DelStr = StrA[2];
          //  Iterator ij = GradData.entrySet().iterator();
            for(Map.Entry<String,Graduate> ij: GradData.entrySet()){
              Graduate GD = ij.getValue();
              if(GD.companyMap.containsKey(DelStr) == true) GD.DeleteCompany(DelStr);
            }
            CompanyData.remove(StrA[2]);
            noOfCompanies--;
          }
          else if(StrA[1].equals("GRADUATE")){
            //Delete Graduate from everywhere
            String DelStr = StrA[2];
          //  Iterator I = CompanyData.entrySet().iterator();
            for(Map.Entry<String,Company> I:CompanyData.entrySet()){
              Company CD = I.getValue();
              if(CD.gradMap.containsKey(DelStr)==true) CD.DeleteGraduate(DelStr);
            }
            GradData.remove(StrA[2]);
            noOfGraduates--;
          }
          else   System.out.println("Invalid Query line. Expected : DELETE GRADUATES / COMPANY");
        }
        else if(StrA[0].equals("MATCH")){
          //Call the matching function
      if(CheckValidity()==true) {
        MATCH();}
          else{
            System.out.println("Exception");
            System.exit(-1);
          }
          //MATCH();
        }
        else   System.out.println("Invalid Query line. No Relevant Request found");

        Str = BR.readLine();
        if(Str!=null){
        Str2 = removeCommas(Str);}
        }
      }catch (FileNotFoundException e) {
        e.printStackTrace();
      }catch (IOException e) {
        e.printStackTrace();
      }
      long endTime   = System.currentTimeMillis();
long totalTime = endTime - startTime;
System.out.println(totalTime);
      }

}
