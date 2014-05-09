// File:	HeapPQ.java
// Description:	A class representing a heap priority queue
// Author:	Ryan Gannon

public class HeapPQ
{
	private static class Vertex{
		public int index;
		public int priority;

		public Vertex(int i, int p)
		{
			this.index = i;
			this.priority = p;
		}

		public Vertex()
		{
			this.index = null;
			this.priority = null;
		}
	}
	
	int size;
	LinkedList<Vertex> heap;
	LinkedList<Edge> edges;
	LinkedList<Edge> solution;

	public HeapPQ(int n, LinkedList<Edge> edges)
	{
		this.size = n + 1;
		this.heap = new LinkedList<Vertex>();
		this.heap.add(new Vertex());
		for(int i = 1; i <= n; i++){
			this.heap.add(new Vertex(i, -1));
		}
	}

	private static void insert(Vertex v)
	{
		size++;
		this.heap.add(v);
		swim(size);
	}

	private static Vertex deleteMin()
	{
		Vertex max = heap.get(1);
		exch(1, size);
		sink(1);
		heap.removeLast();
		size--;
		return max;
	}

		

	private static void swim(int k)
	{
		while(k > 1 && less(Math.floor(k/2), k)){
			exch(k, Math.floor(k/2));
			k = Math.floor(k/2);
		}
	}

	private static void sink(int k)
	{
		int j;
		while(2 * k <= this.size){
			j = 2*k;
			if(j < this.size && less(j, j+1)) j++;
			if(!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	private static boolean less(int i, int j)
	{
		// The return statements here are reversed to create a min heap
		if(heap.get(j).priority < 0) return false;
		else if(heap.get(i).priority < 0) return true;
		else if(heap.get(i).priority < heap.get(j).priority) return false;
		else return true;
	}

	private static void exch(int i, int j)
	{
		Vertex temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	public LinkedList<Edge> prim()
	{
		Vertex u = deleteMin();
		for(Edge e : edges){
			if(e.getLeftNode() == u.index){
				if(u.priority == -1)
	}
}
