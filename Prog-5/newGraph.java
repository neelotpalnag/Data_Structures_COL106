import java.util.*;

public class newGraph
{
	int n;
	line g[];
	newGraph(int n)
	{
		this.n=n;
		g=new line[n];
		for(int i=0;i<n;i++)
		{
			g[i]=new line();
		}
	}
	//for(int i=0;i<n;i++)g[i]=new line();

	void addVertex(vertex v, int x)
	{
		g[x].add(v);
		//System.out.println("yes");
	}

	void printGraph(newGraph g)
	{
		System.out.println("graph is printed");
		for(int i=0;i<g.g.length;i++)
		{
			graph_elem e = g.g[i].start;
			System.out.println(i);
			while(e!=null)
			{
				System.out.print(" "+e.v.num+" ");
				e=e.next;
			}
			System.out.println();
		}
	}
}

class line
{
	graph_elem start;
	graph_elem end;
	line()
	{
		//graph_elem g = new graph_elem();
		//g.v=v;
		start=null;
		end=null;
		//start.next=null;
		//end.next=null;
	}

	void add(vertex v)
	{
		graph_elem g = new graph_elem();
		g.v=v;
		g.next=null;
		if(start==null)
		{
			start=g;
			end=g;
		}
		else
		{
			end.next=g;
			end=g;
		}
	//	System.out.println();
	}
}

class graph_elem
{
	vertex v;
	graph_elem next;
}
