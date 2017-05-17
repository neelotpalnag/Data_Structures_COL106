import java.lang.reflect.Array;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class graph
{

	//public static final String FILENAME="output.txt";

	/*boolean edges[][]=new boolean[10000][10000];
	boolean path[][] = new boolean[10000][10000];
	*/boolean started=false;
	int n;
	int count=0;
	vertex v[];
	newGraph g;
	/*//int paths_left_to_check;

	boolean check_path(int a, int b,boolean trav[])
	{
		//System.out.println("checking path b/w: "+(a+1)+" and "+(b+1));
		//System.out.println("a is "+a);
		trav[a]=true;
		if(!path[a][b])
		{
			for(int k=0;k<n;k++)
			{
				if(edges[a][k] && !trav[k])
				{
					//System.out.println("checking path b/w: "+(k+1)+" and "+(b+1));
					if(check_path(k,b,trav))
					{
						//path[b][a]=true;
						path[a][b]=true;
						return true;
					}
				}
			}
			return false;
		}
		else return true;
	}

	void init()
	{
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(i==j)
				{
					edges[i][j]=true;
					path[i][j]=true;
				}
				else
				{
					edges[i][j]=false;
					edges[j][i]=false;
					path[i][j]=false;
					path[j][i]=false;
				}
			}
		}
	}

	void performAction(String s)
	{
		//System.out.println(s);
		String parts[] = s.split(",");

		if(!started)
		{
			started=true;
			n=Integer.valueOf(s);
			//paths_left_to_check = n*(n-1)/2;
			init();
		}
		else
		{
			if(parts[0].charAt(0)!='-')
			{
				//System.out.println("parts[0]: "+parts[0]);
				for(int i=0;i<Array.getLength(parts);i++)
				{
					int x=Integer.valueOf(parts[i])-1;
					edges[count][x]=true;
					//edges[count][count]=true;
					path[count][x]=true;
					//path[x][count]=true;
					//System.out.println("path b/w: "+(count+1)+" and "+(x+1));
				}
			}
			count++;
		}

		if(count==n)
		{
			for(int i=0;i<n;i++)
			{
				for(int j=i;j<n;j++)
				{
					boolean trav1[]=new boolean[n];
					boolean trav[]=new boolean[n];
					for(int k=0;k<n;k++){
						trav1[i]=false;
						trav[i]=false;
					}
					if(!check_path(i, j,trav) && !check_path(j,i,trav1))
					{
						System.out.println("no path between: "+(i+1)+" and " +(j+1));
						System.out.println("0");
						return;
					}
					//else System.out.println("1");
				}
			}
			System.out.println("1");
		}
	}*/

	void performAction(String s)
	{

		String parts[] = s.split(",");

		if(!started)
		{
			started=true;
			n=Integer.valueOf(s);
			//paths_left_to_check = n*(n-1)/2;
			//init();
			v=new vertex[n];
			for(int i=0;i<n;i++)v[i]=new vertex(i);
			g= new newGraph(n);
		}
		else
		{
			if(parts[0].charAt(0)!='-')
			{
				//System.out.println("parts[0]: "+parts[0]);
				for(int i=0;i<Array.getLength(parts);i++)
				{
					int x=Integer.valueOf(parts[i])-1;
					//vertex v= new vertex(x);
					g.addVertex(v[x], count);
					//edges[count][x]=true;
					//edges[count][count]=true;
					//path[count][x]=true;
					//path[x][count]=true;
					//System.out.println("path b/w: "+(count+1)+" and "+(x+1));
				}
			}
			count++;
		}

		if(count==n)
		{
			//g.printGraph(g);
			tarjan t= new tarjan();
			Vector<Set<vertex>> set=t.start(g,v, n);

			/*Iterator<Set<vertex>> it = set.iterator();
			System.out.println("num connected components: "+set.size());
			while(it.hasNext())
			{
				Set<vertex> v = it.next();
				Iterator<vertex> it2 = v.iterator();
				while(it2.hasNext())
				{
					System.out.print(it2.next().num+" ");
				}
				System.out.println();
			}*/
			int si=set.size();
			Vector<vertex> newlist[] = new Vector[si];
			for(int i=0;i<si;i++)newlist[i]=new Vector<>();
			for(int i=0;i<n;i++)
			{
				graph_elem el = g.g[i].start;
				int pos=-1;
				for(int j=0;j<si;j++)
				{
					if(set.get(j).contains(v[i])){pos=j;break;}
				}
				while(el!=null)
				{
					for(int k=0;k<si;k++)
					{
						if(set.get(k).contains(el.v))
						{
							if(k!=pos)
							{
								vertex d= new vertex(k);
								newlist[pos].addElement(d);
							}
						}
					}
					el=el.next;
				}
			}


		/*	Iterator<Set<vertex>> it = set.iterator();
			//System.out.println("num connected components: "+set.size());
			System.out.println("updated print");
			while(it.hasNext())
			{
				Set<vertex> v = it.next();
				Iterator<vertex> it2 = v.iterator();
				while(it2.hasNext())
				{
					System.out.print(it2.next().num+" ");
				}
				System.out.println();
			}

			for(int i=0;i<si;i++)
			{
				System.out.println(i+": ");
				for(int j=0;j<newlist[i].size();j++)
				{
					System.out.print(" "+newlist[i].get(j).num+" ");
				}
				System.out.println();
			}*/

				if(calc(newlist,si)){

					System.out.println("1");}
				else {System.out.println("0");
				}


		}

		/*stack st= new stack();
		vertex v = new vertex(1);
		vertex v2 = new vertex(2);
		vertex v3 = new vertex(3);
		st.push(v);
		st.push(v3);
		st.push(v2);

		System.out.println("pop is printed: "+st.pop().num);
		System.out.println("pop is printed: "+st.pop().num);*/
	}

	boolean calc(Vector<vertex> l[],int n)
	{
		int indeg[]=new int[n];
		for(int i=0;i<n;i++)indeg[i]=0;
		for(int i=0;i<n;i++)
		{
			Iterator<vertex> it = l[i].iterator();
			while(it.hasNext())
			{
				vertex d = it.next();
				indeg[d.num]++;
			}
		}
		int source=-1;
		for(int i=0;i<n;i++)
		{
			if(indeg[i]==0)
			{
				if(source!=-1)return false;
				source=i;
			}
		}

		int co=n;
		while(co>1)
		{
			int next=-1;
			Iterator<vertex> it = l[source].iterator();
			while(it.hasNext())
			{
				vertex z =it.next();
				indeg[z.num]--;
				if(indeg[z.num]==0)
				{
					if(next!=-1)return false;
					next=z.num;
				}
			}
			source=next;
			co--;
		}
		return true;
	}
}
