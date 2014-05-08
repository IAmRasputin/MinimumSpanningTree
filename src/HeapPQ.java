// File:	HeapPQ.java
// Description:	A class representing a heap priority queue
// Author:	Ryan Gannon

public class HeapPQ
{
	private static class Vertex{
		public int index;
		public int priority;
		LinkedList<Edge> edges;
		LinkedList<Edge> solution;

		public Vertex(int i, int p, LinkedList<Edge> edges)
		{
			this.index = i;
			this.priority = p;
			this.edges = edges;
			this.solution = new LinkedList<Edge>();
		}
	}
	
	int size;
	Vertex[] heap;

	public HeapPQ(int n, LinkedList<Vertex> v)
	{
		this.size = n;
		this.heap = new Vertex[size + 1];
	}

	private static void insert(Vertex v)
	{
		size++;
		Vertex[] newHeap = new Vertex[size + 1];
		System.arraycopy(heap, 0, newHeap, 0, heap.length);
		newHeap[size] = v;
		heap = newHeap;
		swim(size);
	}

	private static Vertex deleteMax()
	{
		Vertex max = heap[1];
		exch(1, size--);
		sink(1);
		heap[size + 1] = null;
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
		if(j < 0) return true;
		else if(i < 0) return false;
		else if(i < j) return true;
		else return false;
	}

	private static void exch(int i, int j)
	{
		Vertex temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	public LinkedList<Edge> prim()
	{
	}
}
