

public class stack
{
	stack_elem top=null;

	void push(vertex v)
	{
		stack_elem s = new stack_elem();
		s.v=v;
		s.prev=top;
		top=s;
	}

	vertex pop()
	{
		if(top==null)
		{
			return null;
		}
		else
		{
			vertex v = top.v;
			top=top.prev;
			return v;
		}
	}
}

class vertex
{
	int num;
	int index;
	int lowlink;
	boolean onStack;

	vertex(int n)
	{
		num=n;
		onStack=false;
		index=-1;
		lowlink=-1;
	}
}

class stack_elem
{
	vertex v;
	stack_elem prev;
}
