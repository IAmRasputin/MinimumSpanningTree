import java.util.ArrayList;
import java.util.Stack;

// File:		AdjacencyMatrix.java
// Description:	Represents an adjacency matrix
// Author:		Ryan Gannon
public class AdjacencyMatrix extends Structure {
	
	private int numNodes;
	private Integer[][] adjMatrix;
	private Integer[] preTrace; // For determining predecessors
	
	public AdjacencyMatrix(int nodes){
		this.numNodes = nodes;
		this.preTrace = new Integer[numNodes];
		this.clear();
	}
	
	public void connect(int node1, int node2, int weight) {
		this.adjMatrix[node1][node2] = weight;
		this.adjMatrix[node2][node1] = weight;
	}
	
	public void print() {
		System.out.println("The graph as an adjacency matrix:");
		System.out.println();
		for(int i = 0; i < this.numNodes; i++){
			for(int j = 0; j < this.numNodes; j++){
				System.out.print(this.adjMatrix[i][j]);
				if(this.adjMatrix[i][i] >= 10){
					System.out.print("  ");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println();
			System.out.println();
		}
	}


	public void insertionSort() {
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING INSERTION SORT");

		// Populate the edges of the structure into an arraylist
		ArrayList<ArrayList<Integer>> edges =
				new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < numNodes; i++){
			for(int j = 0; j < i; j++){
				if( adjMatrix[i][j] != 0 ){
					ArrayList<Integer> edge = new ArrayList<Integer>();
					edge.add(i);
					edge.add(j);
					edge.add(adjMatrix[i][j]);
					edges.add(edge);
				}
			}
		}
		
		// Begin insertion sort implementation
		long startTime = -System.currentTimeMillis();
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
		System.out.println("SORTED EDGES WITH MATRIX USING COUNT SORT");

		// Populate the edges of the structure into an arraylist
		ArrayList<ArrayList<Integer>> edges =
				new ArrayList<ArrayList<Integer>>();
		int radix = 0;
		for(int i = 0; i < numNodes; i++){
			for(int j = 0; j < i; j++){
				if( adjMatrix[i][j] != 0 ){
					ArrayList<Integer> edge = new ArrayList<Integer>();
					edge.add(i);
					edge.add(j);
					edge.add(adjMatrix[i][j]);
					edges.add(edge);
					if(adjMatrix[i][j] > radix){
						radix = adjMatrix[i][j];
					}
				}
			}
		}
		
		radix++;
		
		long startTime = -System.currentTimeMillis();
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
		System.out.println("SORTED EDGES WITH MATRIX USING QUICKSORT");

		// Populate the edges of the structure into an arraylist
		ArrayList<ArrayList<Integer>> edges =
				new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < numNodes; i++){
			for(int j = 0; j < i; j++){
				if( adjMatrix[i][j] != 0 ){
					ArrayList<Integer> edge = new ArrayList<Integer>();
					edge.add(i);
					edge.add(j);
					edge.add(adjMatrix[i][j]);
					edges.add(edge);
				}
			}
		}
		
		long startTime = -System.currentTimeMillis();
		int len = edges.size();
		
		// Begin quicksort implementation
		
		
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

	

	public boolean isConnected() {
		Stack<Integer[]> stack = new Stack<Integer[]>();
		Stack<Integer> preStack = new Stack<Integer>();
		ArrayList<Integer[]> visited = new ArrayList<Integer[]>();
		Integer[] current;
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
				if(current[i] != 0){
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
	
	public void DFSTrace(){
		Stack<Integer[]> stack = new Stack<Integer[]>();
		Stack<Integer> preStack = new Stack<Integer>();
		ArrayList<Integer[]> visited = new ArrayList<Integer[]>();
		Integer[] current;
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
				if(current[i] != 0){
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
	
	public void clear(){
		this.adjMatrix = new Integer[numNodes][numNodes];
		for(int i = 0; i < numNodes; i++){
			this.adjMatrix[i][i] = 0;
		}
	}




}
