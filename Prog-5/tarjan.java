
import java.util.*;

public class tarjan
{
	int index=0;
    Vector<Set<vertex>> set = new Vector<>();
    int n;
	stack s= new stack();

	Vector<Set<vertex>> start(newGraph g,vertex v[],int n)
	{
		this.n=n;
		for(int i=0;i<n;i++)
		{
			if(v[i].index==-1)
			{
				set.addAll(strongconnect(v[i],g));
				//System.out.println("yes");
			}
		}
		return set;
	}

	Vector<Set<vertex>> strongconnect(vertex v,newGraph g)
	{
		Set<vertex> set = new HashSet<>();
		Vector<Set<vertex>> vec = new Vector<>();
		v.index=index;
		v.lowlink=index;
		index++;
		s.push(v);
		v.onStack=true;
		//System.out.println(v.index+" is present");
		graph_elem w=g.g[v.num].start;
		while(w!=null)
		{
			//System.out.print("w: "+w.v.num+" in "+v.num+" ,");
			if(w.v.index==-1)
			{
				//System.out.print("strongconnect("+w.v.num+",g)");
				vec.addAll(strongconnect(w.v, g));
				v.lowlink = Math.min(v.lowlink, w.v.lowlink);
			}
			else if(w.v.onStack)
			{
				v.lowlink=Math.min(v.lowlink, w.v.lowlink);
			}
			w=w.next;
		}
		//System.out.println();

		if(v.lowlink==v.index)
		{
			//System.out.println(v.num+" is a node");
			vertex v2;
			do
			{
				 v2=s.pop();
				 v2.onStack=false;
				 set.add(v2);
			}
			while(v2!=v);
		}
		if(set.size()>0)vec.add(set);
		/*Iterator<Set<vertex>> it = vec.iterator();
		//System.out.println("num connected components in tarjan: "+vec.size());
		while(it.hasNext())
		{
			Set<vertex> i2 = it.next();
			Iterator<vertex> it2 = i2.iterator();
			while(it2.hasNext())
			{
				System.out.print(it2.next().num+" ");
			}
			System.out.println();
		}
		System.out.println("vec length: "+vec.size());*/
		return vec;
	}
}
