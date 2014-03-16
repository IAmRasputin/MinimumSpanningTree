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
		
		long startTime = -System.currentTimeMillis();
		
		// Populate the edges of the structure into an arraylist
		ArrayList<ArrayList<Integer>> edges =
				new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < adjList.size(); i++){
			for(int j = 0; j < adjList.get(i).size(); j++){
				ArrayList<Integer> edge = new ArrayList<Integer>();
				ArrayList<Integer> duplicate = new ArrayList<Integer>();
				edge.add(i);
				edge.add(adjList.get(i).get(j).get(0));
				edge.add(adjList.get(i).get(j).get(1));
				duplicate.add(adjList.get(i).get(j).get(0));
				duplicate.add(i);
				duplicate.add(adjList.get(i).get(j).get(1));
				
				if(!edges.contains(edge)){
					edges.add(duplicate);
				}
			}
		}
		
		// Begin insertion sort implementation
		
		int len = edges.size();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for(int i = 0; i < len; i++){
			for(int j = i; j > 0; j--){
				if(edges.get(j).get(2) < edges.get(j-1).get(2)){
					temp = edges.get(j-1);
					edges.set(j-1, edges.get(j));
					edges.set(j, temp);
				} else {
					break;
				}
			}
		}

		long totalTime = startTime + System.currentTimeMillis();
		int totWeight = 0;
		// Print out the result len <= 10
		
		for( ArrayList<Integer> e : edges){
			if(len <= 10){
				System.out.println(e.get(0)+" "+e.get(1)+" weight = "+e.get(2));
			}
			totWeight += e.get(2);
		}
		
		System.out.println();
		System.out.println("Total weight: " + totWeight);
		System.out.println("Runtime: " + totalTime + " milliseconds");
		System.out.println();
	}


	public void countSort() {
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH LIST USING COUNT SORT");

		long startTime = -System.currentTimeMillis();
		
		// Populate the edges of the structure into an arraylist
		ArrayList<ArrayList<Integer>> edges =
				new ArrayList<ArrayList<Integer>>();
		int radix = 0;
		for(int i = 0; i < adjList.size(); i++){
			for(int j = 0; j < adjList.get(i).size(); j++){
				ArrayList<Integer> edge = new ArrayList<Integer>();
				ArrayList<Integer> duplicate = new ArrayList<Integer>();
				edge.add(i);
				edge.add(adjList.get(i).get(j).get(0));
				edge.add(adjList.get(i).get(j).get(1));
				duplicate.add(adjList.get(i).get(j).get(0));
				duplicate.add(i);
				duplicate.add(adjList.get(i).get(j).get(1));
				
				if(!edges.contains(edge)){
					edges.add(duplicate);
				}
				if(edge.get(2) > radix){
					radix = edge.get(2);
				}
				
			}
		}
		
		radix++;
		

		int len = edges.size();
		
		// Begin count sort implementation
		ArrayList<ArrayList<Integer>> aux = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> blank = new ArrayList<Integer>();
		for(int i = 0; i < 3; i++) blank.add(0);
		for(int i = 0; i < len; i++) aux.add(blank);
		
		Integer[] count = new Integer[radix+1];
		for(int i = 0; i < radix+1; i++){
			count[i] = 0;
		}
		int index;
		for(int i = 0; i < len; i++){
			index = edges.get(i).get(2) + 1;
			count[index]++;
		}
		
		for(int r = 0; r < radix; r++){
			count[r+1] += count[r];
		}
		
		for(int i = 0; i < len; i++){
			index = count[edges.get(i).get(2)];
			aux.set(index, edges.get(i));
			count[edges.get(i).get(2)]++;
		}
		
		edges = aux;
		
		long totalTime = startTime + System.currentTimeMillis();
		int totWeight = 0;
		// Print out the result len <= 10
		
		for( ArrayList<Integer> e : edges){
			if(len <= 10){
				System.out.println(e.get(0)+" "+e.get(1)+" weight = "+e.get(2));
			}
			totWeight += e.get(2);
		}
		
		System.out.println();
		System.out.println("Total weight: " + totWeight);
		System.out.println("Runtime: " + totalTime + " milliseconds");
		System.out.println();
	}


	public void quickSort() {
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH LIST USING QUICKSORT");

		long startTime = -System.currentTimeMillis();
		
		// Populate the edges of the structure into an arraylist
		ArrayList<ArrayList<Integer>> edges =
				new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < adjList.size(); i++){
			for(int j = 0; j < adjList.get(i).size(); j++){
				ArrayList<Integer> edge = new ArrayList<Integer>();
				ArrayList<Integer> duplicate = new ArrayList<Integer>();
				edge.add(i);
				edge.add(adjList.get(i).get(j).get(0));
				edge.add(adjList.get(i).get(j).get(1));
				duplicate.add(adjList.get(i).get(j).get(0));
				duplicate.add(i);
				duplicate.add(adjList.get(i).get(j).get(1));
				
				if(!edges.contains(edge)){
					edges.add(duplicate);
				}
			}
		}
		
		int len = edges.size();
		
		// Begin quicksort implementation
		QS_sort(edges, 0, len-1);
		
		long totalTime = startTime + System.currentTimeMillis();
		int totWeight = 0;
		// Print out the result len <= 10
		
		for( ArrayList<Integer> e : edges){
			if(len <= 10){
				System.out.println(e.get(0)+" "+e.get(1)+" weight = "+e.get(2));
			}
			totWeight += e.get(2);
		}
		
		System.out.println();
		System.out.println("Total weight: " + totWeight);
		System.out.println("Runtime: " + totalTime + " milliseconds");
		System.out.println();
		
	}
	
	private static int QS_partition(ArrayList<ArrayList<Integer>> edges, int lo, int hi){
		int i = lo, j = hi+1;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		while(true){
			while(edgeLessThan(edges.get(++i), edges.get(lo))){
				if(i == hi) break;
			}
			while(edgeLessThan(edges.get(lo), edges.get(--j))){
				if(j == lo) break;
			}
			
			if(i >= j) break;
			temp = edges.get(i);
			edges.set(i, edges.get(j));
			edges.set(j, temp);	
		}
		
		temp = edges.get(lo);
		edges.set(lo, edges.get(j));
		edges.set(j, temp);
		
		return j;
	}
	
	private static void QS_sort(ArrayList<ArrayList<Integer>> edges, int lo, int hi){
		if(hi <= lo){
			return;
		} 
		int j = QS_partition(edges, lo, hi);
		QS_sort(edges, lo, j-1);
		QS_sort(edges, j+1, hi);
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
