// File:		AdjacencyList.java
// Description:	An adjacency list (as an alternative to the Matrix)
// Author:		Ryan Gannon

import java.util.ArrayList;

public class AdjacencyList extends Structure {

	int numNodes;
	ArrayList<ArrayList<ArrayList<Integer>>> adjList;	// Absolutely disgusting
//	private Integer[] preTrace; 			// For determining predecessors

	public AdjacencyList(int nodes) {
		this.numNodes = nodes;
		this.clear();
	}


	public void print() {
		System.out.println("The graph as an adjacency list:");
		for(int i = 0; i < this.numNodes; i++){
			System.out.print(i+"-> ");
			for(ArrayList<Integer> s : adjList.get(i)){				
				System.out.print(s.get(0) + "(" + s.get(1) + ") ");				
			}
			System.out.println();
		}
		System.out.println();
	}


	public void insertionSort() {
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH LIST USING INSERTION SORT");
		

	}


	public void countSort() {
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH LIST USING COUNT SORT");

	}


	public void quickSort() {
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH LIST USING QUICKSORT");

	}


	public boolean isConnected() {
		// This method is implemented in the AdjacencyMatrix class.
		// However, I might still implement it later if I find it necessary.
		return false;
	}


	public void connect(int node1, int node2, int weight) {
		if(weight != 0){
			ArrayList<Integer> toAddFirst = new ArrayList<Integer>();
			toAddFirst.add(node2);
			toAddFirst.add(weight);
			ArrayList<Integer> toAddSecond = new ArrayList<Integer>();
			toAddSecond.add(node1);
			toAddSecond.add(weight);
			adjList.get(node1).add(toAddFirst);
			adjList.get(node2).add(toAddSecond);
			
		}
	}
	
	public void clear(){
		adjList = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		for(int i = 0; i < numNodes; i++)
			adjList.add(new ArrayList<ArrayList<Integer>>());
	}

}
