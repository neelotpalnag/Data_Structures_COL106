import java.util.HashMap;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

class AdjList{

  HashMap<Integer, List<Integer>> AdjacencyList ;
  int numberOfVertices;
  public AdjList(int number_of_vertices){          //Constructor
       AdjacencyList = new HashMap<Integer, LinkedList<Integer>>();
       numberOfVertices = number_of_vertices;
       //for (int i = 1 ; i <= number_of_vertices ; i++){
       //     Adjacency_List.put(i, new LinkedList<Integer>());
       //}
   }
   public void addVertices(int src, LinkedList<Integer> dst){                      // SRC ----> DST
       AdjacencyList.put(src,dst);
      }

  //---------------------------------------------------------------------------
  // Graph Operations begin Here !
  // The algorithm first produces a condensed graph by finding the Strongly
  // connected components of the parent Graph. This graph will be a DAG.
  // Now
}
