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
	}


	public void countSort() {
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING COUNT SORT");

	}


	public void quickSort() {
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING QUICKSORT");

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
