import java.util.HashMap;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class alow{


  public static void main(String[] args) {
    try {
      BufferedReader BR= new BufferedReader(new FileReader("input.txt"));
      String n = BR.readLine();
      int numberOfVertices = Integer.parseInt(n);
      AdjList Graph = new AdjList(numberOfVertices);      //The entire Adjacency List holding all the vertices & respective LinkedLists
      int result = -1;


      String s = BR.readLine();
      for(int k=1;s!=null;k++){
        String[] vertices = s.split(",");
        LinkedList<Integer> L = new LinkedList<Integer>();
        for(int t=0;t<sizeOf(vertices);t++){
          L.add(vertices[i]);
        }
        Graph.addVertices(k,L);
        s = BR.readLine();
      }

      result = checkALOWC(Graph);
      if(result==-1)System.out.println("100");
      else if(result==0){
        System.out.println("0");
      }
      else System.out.println("1");
      // End ofProgram Execution
    }catch (Exception e) {
      e.printStackTrace();
    }
  }//End of Main()
}//Class Scope Ends Here
