// File:		AdjacencyList.java
// Description:	An adjacency list (as an alternative to the Matrix)
// Author:		Ryan Gannon

import java.util.ArrayList;
import java.util.LinkedList;

public class AdjacencyList extends Structure {

	int numNodes;
	ArrayList<LinkedList<Edge>> adjList;
//	private Integer[] preTrace; 	// For determining predecessors

	// Public constructor
	public AdjacencyList(int nodes) {
		this.numNodes = nodes;
		this.clear();
	}

	// Print out a string representation of the list
	public void print() {
		System.out.println("The graph as an adjacency list:");
		for(int i = 0; i < this.numNodes; i++){
			System.out.print(i+"-> ");
			for(Edge e : adjList.get(i)){				
				System.out.print(e.getRightNode() + "(" + e.getWeight() + ") ");				
			}
			System.out.println();
		}
		System.out.println();
	}


	// Performs an insertion sort on the list and prints the results
	public void insertionSort() {
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH LIST USING INSERTION SORT");
		
		
		// Populate the edges of the structure into an arraylist
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i = 0; i < adjList.size(); i++){
			for(int j = 0; j < adjList.get(i).size(); j++){
				Edge edge = new Edge(adjList.get(i).get(j).getWeight(), 
						     adjList.get(i).get(j).getRightNode(), 
						     i);

				boolean add = true;
				for(Edge e : edges){
					if (e.equals(edge)){
						add = false;
						break;
					}
				}

				if(add)
					edges.add(edge);
					
			}
		}
		
		// Begin insertion sort implementation
		
		long startTime = -System.currentTimeMillis();
		int len = edges.size();
		Edge temp = new Edge(0, 0, 0);
		
		for(int i = 0; i < len; i++){
			for(int j = i; j > 0; j--){
				if(edges.get(j).getWeight() < edges.get(j-1).getWeight()){
					temp = edges.get(j-1);
					edges.set(j-1, edges.get(j));
					edges.set(j, temp);
				} else {
					break;
				}
			}
		}

		
		// Print out the MST	
		kruskalMST(edges, len);

		long totalTime = startTime + System.currentTimeMillis();
		System.out.println("Runtime: " + totalTime + " milliseconds");
		System.out.println();
	}

	// Performs a count sort on the list and prints the results
	public void countSort() {
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH LIST USING COUNT SORT");

		
		// Populate the edges of the structure into an arraylist
		int radix = 0;
		
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i = 0; i < adjList.size(); i++){
			for(int j = 0; j < adjList.get(i).size(); j++){
				Edge edge = new Edge(adjList.get(i).get(j).getWeight(), 
						     adjList.get(i).get(j).getRightNode(), 
						     i);

				boolean add = true;
				for(Edge e : edges){
					if (e.equals(edge)){
						add = false;
						break;
					}
				}

				if(add)
					edges.add(edge);
				
				if(edge.getWeight() > radix){
					radix = edge.getWeight();
				}

			}
		}
		
		radix++;
		

		int len = edges.size();
		
		// Begin count sort implementation
		long startTime = -System.currentTimeMillis();
		ArrayList<Edge> aux = new ArrayList<Edge>();
		Edge blank = new Edge(0, 0, 0);

		for(int i = 0; i < len; i++) aux.add(blank);
		
		Integer[] count = new Integer[radix+1];
		for(int i = 0; i < radix+1; i++){
			count[i] = 0;
		}
		int index;
		for(int i = 0; i < len; i++){
			index = edges.get(i).getWeight() + 1;
			count[index]++;
		}
		
		for(int r = 0; r < radix; r++){
			count[r+1] += count[r];
		}
		
		for(int i = 0; i < len; i++){
			index = count[edges.get(i).getWeight()];
			aux.set(index, edges.get(i));
			count[edges.get(i).getWeight()]++;
		}
		
		edges = aux;
		
		// Print out the result len <= 10
		kruskalMST(edges, len);	


		long totalTime = startTime + System.currentTimeMillis();
		System.out.println("Runtime: " + totalTime + " milliseconds");
		System.out.println();
	}

	// Performs a (stable) quicksort on the list and prints the results
	public void quickSort() {
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH LIST USING QUICKSORT");

		
		// Populate the edges of the structure into an arraylist
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i = 0; i < adjList.size(); i++){
			for(int j = 0; j < adjList.get(i).size(); j++){
				Edge edge = new Edge(adjList.get(i).get(j).getWeight(), 
						     adjList.get(i).get(j).getRightNode(), 
						     i); 

				boolean add = true;
				for(Edge e : edges){
					if (e.equals(edge)){
						add = false;
						break;
					}
				}

				if(add)
					edges.add(edge);
					
			}
		}
		
		int len = edges.size();
		
		// Begin quicksort implementation
		long startTime = -System.currentTimeMillis();
		QS_sort(edges, 0, len-1);
		
		// Print out the result len <= 10
		kruskalMST(edges, len);

		long totalTime = startTime + System.currentTimeMillis();
		System.out.println("Runtime: " + totalTime + " milliseconds");
		System.out.println();
		
	}
	
	// Partitions the arraylist for the quicksort
	private static int QS_partition(ArrayList<Edge> edges, int lo, int hi){
		int i = lo, j = hi+1;
		Edge temp = new Edge(0, 0, 0);
		
		while(true){
			while(edges.get(++i).lessThan(edges.get(lo))){
				if(i == hi) break;
			}
			while(edges.get(lo).lessThan(edges.get(--j))){
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
	
	// performs the quicksort
	private static void QS_sort(ArrayList<Edge> edges, int lo, int hi){
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

	// Connects two nodes at the givern vertices with the given weight
	public void connect(int node1, int node2, int weight) {
		if(weight != 0){
			Edge toAddFirst = new Edge(weight, node1, node2);
			Edge toAddSecond = new Edge(weight, node2, node1);
			adjList.get(node1).add(toAddFirst);
			adjList.get(node2).add(toAddSecond);
			
		}
	}

	// Performs and prints the output of Prim's algorithm
	public void prim(){
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for(int i = 0; i < adjList.size(); i++){
			for(int j = 0; j < adjList.get(i).size(); j++){
				Edge edge = new Edge(adjList.get(i).get(j).getWeight(), 
						     adjList.get(i).get(j).getRightNode(), 
						     i);

				boolean add = true;
				for(Edge e : edges){
					if (e.equals(edge)){
						add = false;
						break;
					}
				}

				if(add)
					edges.add(edge);
					
			}
		}

		System.out.println("===================================");
		System.out.println("PRIM WITH ADJACENCY LIST");

		HeapPQ heap = new HeapPQ(numNodes, edges);
		LinkedList<Edge> solution = heap.prim();

		for(Edge e : solution){
			System.out.println(e.getLeftNode() + " " + 
					   e.getRightNode() + " weight = " +
					   e.getWeight());
		}
		
		System.out.println();
	}
	
	// Clears the list to all 0s
	public void clear(){
		adjList = new ArrayList<LinkedList<Edge>>();	
		for(int i = 0; i < numNodes; i++)
			adjList.add(new LinkedList<Edge>());
	}

}
