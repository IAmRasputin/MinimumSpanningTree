// File:	HeapPQ.java
// Description:	A class representing a heap priority queue
// Author:	Ryan Gannon

import java.util.LinkedList;

public class HeapPQ
{
	private class Vertex{
		public int index;
		public int priority;

		public Vertex(int index, int priority){
			this.index = index;
			this.priority = priority;}
	}

	private LinkedList<Vertex> heap;
	private LinkedList<Edge> edges;
	private LinkedList<Edge> solution;

	public HeapPQ(int n, LinkedList<Edges> e){
		this.heap = new LinkedList<Vertex>();
		this.edges = e;
		this.size = n+1;

		for(int i = 0; i < size; i++){
			heap.add(new Vertex(i, -1));
		}
	}

	private static void heapify(){
		for(int i = 1; i < size; i++){
			if(size > 2 * i + 1){
				if(less(i, 2 * i) || less(i, 2 * i + 1)){
					if(less(2 * i, 2 * i + 1)){
						exch(i, 2 * i + 1);
					} else {
						exch(i, 2 * i);
					}
				}
			} else if (size > 2 * i) {
				if(less(i, 2 * i)){
					exch(i, 2 * i);
				}
			} else {
				break;
			}
		}

	}

	private static void insert(Vertex v){
		heap.add(v);
		size++;
		swim(size);
	}

	private static Vertex deleteMin(){
		Vertex min = heap.get(1);
		exch(1, size);
		heap.removeLast();
		size--;
		return min;
	}

	private static void swim(int i)
	{
		while(i > 1 && less(i/2, i)){
			exch(k, k/2);
			k = k/2;
		}
	}

	private static void sink(int i)
	{
		int j;
		while(2 * i <= size){
			j = 2*i;
			if(j < size && less(j, j+1)) j++;
			if(!less(i, j)) break;
			exch(i, j);
			i = j;
		}
	}

	private static boolean less(int u, int v)
	{
		int up = heap.get(u).priority;
		int vp = heap.get(v).priority;

		if(up == vp) return false;
		else if(up == -1) return true;
		else if(vp == -1) return false;
		else if(up < vp) return true;
		else return false;
	}

	private static void exch(int i, int j)
	{
		Vertex temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	public void prim(){
		Vertex u;
		while(size != 1){
			u = deleteMin();
			for(Edge e : edges){
				// Found a match!
				if(u.index == e.getLeftNode()){
					heap.get(e.getRightNode()).priority = e.getWeight();


				}
			heapify();
		}
	}
}
