import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

// File:		AdjacencyMatrix.java
// Description:	Represents an adjacency matrix
// Author:		Ryan Gannon
public class AdjacencyMatrix extends Structure {
	
	private int numNodes;
	private Edge[][] adjMatrix;
	private Integer[] preTrace; // For determining predecessors
	private ArrayList<Integer> visited;

	// Public constructor
	public AdjacencyMatrix(int nodes){
		this.numNodes = nodes;
		this.preTrace = new Integer[numNodes];
		this.visited = new ArrayList<Integer>();
		this.clear();
	}
	
	// Connects two nodes with the given indices with the given weight
	public void connect(int node1, int node2, int weight) {
		this.adjMatrix[node1][node2] = new Edge(weight, node1, node2);
		this.adjMatrix[node2][node1] = new Edge(weight, node2, node1);
	}
	
	// Prints out a string representation of the matrix
	public void print() {
		System.out.println("The graph as an adjacency matrix:");
		System.out.println();
		for(int i = 0; i < this.numNodes; i++){
			for(int j = 0; j < this.numNodes; j++){
				System.out.print(this.adjMatrix[i][j].getWeight());
				if(this.adjMatrix[i][i].getWeight() >= 10){
					System.out.print("  ");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println();
			System.out.println();
		}
	}


	// Performs an insertion sort on the matrix and prints the results
	public void insertionSort() {
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH MATRIX USING INSERTION SORT");
	
		// Populate the edges of the structure into an arraylist
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i = 0; i < numNodes; i++){
			for(int j = 0; j < i; j++){
				if( adjMatrix[i][j].getWeight() != 0 ){
					edges.add(new Edge(adjMatrix[i][j].getWeight(), i, j));
				}
			}
		}
		
		// Begin insertion sort implementation
		long startTime = -System.currentTimeMillis();
		int len = edges.size();
		Edge temp;
		
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


	// Performs a count sort on the matrix and prints the results
	public void countSort() {
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH MATRIX USING COUNT SORT");

		
		
		// Populate the edges of the structure into an arraylist
		ArrayList<Edge> edges = new ArrayList<Edge>();
		int radix = 0;
		for(int i = 0; i < numNodes; i++){
			for(int j = 0; j < i; j++){
				if( adjMatrix[i][j].getWeight() != 0 ){
					Edge edge = new Edge(adjMatrix[i][j].getWeight(), i, j);
					edges.add(edge);
					if(adjMatrix[i][j].getWeight() > radix){
						radix = adjMatrix[i][j].getWeight();
					}
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
		

		// Print the MST
		kruskalMST(edges, len);
		
		long totalTime = startTime + System.currentTimeMillis();
		System.out.println("Runtime: " + totalTime + " milliseconds");
		System.out.println();
		
	}

	// Performs a stable quicksort on the matrix and prints the results
	public void quickSort() {
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH MATRIX USING QUICKSORT");

		
		
		// Populate the edges of the structure into an arraylist
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i = 0; i < numNodes; i++){
			for(int j = 0; j < i; j++){
				if( adjMatrix[i][j].getWeight() != 0 ){
					Edge edge = new Edge(adjMatrix[i][j].getWeight(), i, j);
					edges.add(edge);
				}
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
	
	// Partitions the arrayList for the quicksort
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
	
	// Performs the actual work for the quicksort
	private static void QS_sort(ArrayList<Edge> edges, int lo, int hi){
		if(hi <= lo){
			return;
		} 
		int j = QS_partition(edges, lo, hi);
		QS_sort(edges, lo, j-1);
		QS_sort(edges, j+1, hi);
	}
	
	

	
	// Determines whether the graph is connected
	public boolean isConnected() {
		Stack<Edge[]> stack = new Stack<Edge[]>();
		Stack<Integer> preStack = new Stack<Integer>();
		ArrayList<Edge[]> visited = new ArrayList<Edge[]>();
		Edge[] current;
		Integer currentIndex;
		// Start at node 0
		stack.push(adjMatrix[0]);
		preStack.push(0);
		this.preTrace[0] = -1;
		
		while(!stack.empty()){
			current = stack.pop();
			currentIndex = preStack.pop();
			//visited.add(current);
			for(int i = 0; i < this.numNodes; i++){
				if(current[i].getWeight() != 0){
					if(!visited.contains(adjMatrix[i])){
						stack.push(adjMatrix[i]);
						preStack.push(i);
						visited.add(adjMatrix[i]);
						if(i != 0){
							this.preTrace[i] = currentIndex;
						}
					}
				}
			}
		}
		return (visited.size() == this.numNodes);
	}

	// Determines and prints out a trace of a depth-first search
	
	
	public void depthFirstSearch(){
		this.depthFirstSearch(0);
		
		System.out.println("Depth-First Search:\nVertices:");
		System.out.print(" ");
		for(int i = 0; i < this.numNodes; i++){
			System.out.print(i+" ");
		}
		System.out.println("\nPredecessors:");
		for(int i = 0; i < this.numNodes; i++){
			System.out.print(preTrace[i]+" ");
		}
		System.out.println();

	}

	public void depthFirstSearch(int index){
		boolean children = false;
		this.visited.add(index);

		for(int i = 0; i < this.numNodes; i++){
			if(this.adjMatrix[index][i].getWeight() != 0 
			&& !this.visited.contains(i)){
				children = true;
				this.preTrace[i] = index;
				this.depthFirstSearch(i);
			}
		}

		if(!children) return;
	}
			
	// Performs and prints the output of Prim's algorithm
	public void prim(){
		// Populate the edges of the structure into an arraylist
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for(int i = 0; i < numNodes; i++){
			for(int j = 0; j < i; j++){
				if( adjMatrix[i][j].getWeight() != 0 ){
					edges.add(new Edge(adjMatrix[i][j].getWeight(), i, j));
				}
			}
		}
		HeapPQ heap = new HeapPQ(numNodes, edges);


		long startTime = -System.currentTimeMillis();
		LinkedList<Edge> solution = heap.prim();


		System.out.println("===================================");
		System.out.println("PRIM WITH ADJACENCY MATRIX");

		int totalWeight = 0;

		for(Edge e : solution){
			if(solution.size() <= 10){
				System.out.println(e.getLeftNode() + " " + 
						   e.getRightNode() + " weight = " +
					   	   e.getWeight());
			}
			totalWeight += e.getWeight();
		}

		System.out.println();

		System.out.println("Total weight of MST using Prim: " + totalWeight);
		long totalTime = startTime + System.currentTimeMillis();
		System.out.println("Runtime: " + totalTime + " milliseconds");
		
		System.out.println();
	}
	
	// Re-sets the matrix to only contain zeros
	public void clear(){
		this.adjMatrix = new Edge[numNodes][numNodes];
		for(int i = 0; i < numNodes; i++){
			for(int j = 0; j < numNodes; j++){
				this.adjMatrix[i][j] = new Edge(0, 0, 0);
			}
		}
	}




}
