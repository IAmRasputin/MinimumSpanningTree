// File:	HeapPQ.java
// Description:	A class representing a heap priority queue
// Author:	Ryan Gannon

import java.util.LinkedList;

public class HeapPQ
{
	private class Vertex{
		public int index;
		public int priority;
		public int parent;

		public Vertex(int index, int priority, int parent){
			this.index = index;
			this.priority = priority;}
	}

	private LinkedList<Vertex> heap;
	private LinkedList<Edge> edges;
	private int size;

	public HeapPQ(int n, LinkedList<Edge> e){
		this.heap = new LinkedList<Vertex>();
		this.edges = new LinkedList<Edge>();
		for(Edge edge : e){
			edges.add(new Edge(edge));
		}

		this.size = n;
		
		for(int i = -1; i < size; i++){
			heap.add(new Vertex(i, Integer.MAX_VALUE, -1));
		}
	}

	private void heapify(){
		Vertex parent;
		int parentInd;
		
		for(int i = this.size; i > 1; i--){
			parentInd = (int)Math.floor(((double)i)/2);
			parent = heap.get(parentInd);
			if(heap.get(i).priority < parent.priority){
				exch(i, parentInd);
			}
		}
	}

	private void insert(Vertex v){
		this.heap.add(v);
		this.size++;
		swim(this.size);
	}

	private Vertex deleteMin(){
		Vertex min = this.heap.get(1);
		exch(1, this.size);
		this.heap.removeLast();
		this.size--;
		return min;
	}

	private void swim(int i)
	{
		while(i > 1 && less(i/2, i)){
			exch(i, i/2);
			i = i/2;
		}
	}

	private void sink(int i)
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

	private boolean less(int u, int v)
	{
		int up = heap.get(u).priority;
		int vp = heap.get(v).priority;

		if(up < vp) return true;
		else return false;
	}

	private void exch(int i, int j)
	{
		Vertex temp = this.heap.get(i);
		this.heap.set(i, this.heap.get(j));
		this.heap.set(j, temp);
	}

	private int indexOf(int index)
	{
		Vertex v;
		for(int i = 1; i <= size; i++){
			v = heap.get(i);
			if(v.index == index){
				return i;
			}
		}
		return 0;
	}

	public LinkedList<Edge> prim(){
		Vertex u;
		Vertex v;
		LinkedList<Edge> solution = new LinkedList<Edge>();
		
		while(heap.size() != 1){
			u = deleteMin();
			if(u.index != u.parent){
				solution.add(new Edge(u.priority, u.parent, u.index));
			}

			// find edges adjacent to it
			for(Edge e : edges){
				if(e.getLeftNode() == u.index){
					v = heap.get(indexOf(e.getRightNode()));
					if(e.getWeight() < v.priority){
						v.parent = u.index;
						v.priority = e.getWeight();
					}
				}
				else if(e.getRightNode() == u.index){
					v = heap.get(indexOf(e.getLeftNode()));
					if(e.getWeight() < v.priority){
						v.parent = u.index;
						v.priority = e.getWeight();
					}
				}
				
			}
			heapify();
		}
		return solution;
	}
}
