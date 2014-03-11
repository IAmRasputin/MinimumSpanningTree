// File:	Graph.java
// Description:	The driver file for graph generation 
// Author:	Ryan Gannon

import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;

// Represents an undirected, weighted graph
public class Graph{
	private int numNodes;		// the number of graph nodes
	private long seed;			// The pseudo-random number generator seed
	private double p;			// The probability of a connection being made
	private boolean connected;	// Private use bool, used by the constructor
	long time;					// Used to determine generation time
	long startTime;				// See time
	Random edge;				// PRNG for edge connections
	Random weight;				// PRNG for edge weights

	private Integer[][] adjMatrix;
	private Integer[] preTrace;

	// Public constructor
	public Graph(int nodes, long sd, double prob){
		this.numNodes = nodes;
		this.seed = sd;
		this.p = prob;
		this.edge = new Random((long)this.seed);
		this.weight = new Random((long)(this.seed * 2));
		this.preTrace = new Integer[nodes];
		
		// Make a note of the current time
		startTime = System.currentTimeMillis();
		
		// Generate a graph.  If it is unconnected, try again.
		do{
//			System.out.println("Generating graph...");
			
			this.adjMatrix = this.generateGraph();
//			System.out.println("Verifying connectivity...");
			this.connected = this.verifyConnectivity(this.adjMatrix);
//			if(this.connected){
//				System.out.println("Successful.");
//			} else {
//				System.out.println("Failed, retrying...");
//			}
		} while( !this.connected );
		//System.out.println("Graph generation complete.");
		
		// See how much time it took to generate the graph
		time = System.currentTimeMillis() - startTime;
	}

	// Generate the graph and return the Adjacency Matrix as an Integer[][]
	private Integer[][] generateGraph(){
		
		Integer[][] adjMatrix = new Integer[this.numNodes][this.numNodes];

		// Traverse the diagonal, to ensure no self-connections
		for(int i = 0; i < this.numNodes; i++){
			adjMatrix[i][i] = 0;
		}

		// Generate half of the matrix, and reflect it across the diagonal
		for(int i = 1; i < this.numNodes; i++){
			for(int j = 0; j < i; j++){
				if( this.edge.nextDouble() < this.p ){
					
					adjMatrix[i][j] =  this.weight.nextInt(this.numNodes) + 1;

					adjMatrix[j][i] = 
					adjMatrix[i][j];
				}
					
			}
		}

		// In case we accidentally get any nulls, replace them with 0s.
		// Note: I'm not sure this is actually necessary, as it was used as a 
		// workaround for a bug that I believe has been fixed.  Tests needed.
		for(int i = 0; i < this.numNodes; i++){
			for(int j = 0; j < this.numNodes; j++){
				if(adjMatrix[i][j] == null){
					adjMatrix[i][j] = 0;
				}
			}
		}
		return adjMatrix;
	}

	// A Depth-First search.  Returns the result of the connectivity test
	private boolean verifyConnectivity(Integer[][] matrix){
		Stack<Integer[]> stack = new Stack<Integer[]>();
		Stack<Integer> preStack = new Stack<Integer>();
		ArrayList<Integer[]> visited = new ArrayList<Integer[]>();
		Integer[] current;
		Integer currentIndex;
		// Start at node 0
		stack.push(matrix[0]);
		preStack.push(0);
		this.preTrace[0] = -1;
		
		while(!stack.empty()){
			current = stack.pop();
			currentIndex = preStack.pop();
			//visited.add(current);
			for(int i = 0; i < this.numNodes; i++){
				if(current[i] != 0){
					if(!visited.contains(matrix[i])){
						stack.push(matrix[i]);
						preStack.push(i);
						visited.add(matrix[i]);
						if(i != 0){
							this.preTrace[i] = currentIndex;
						}
					}
				}
			}
		}
		return (visited.size() == this.numNodes);
	}

	// Prints a graph summary
	public void printSummary(){
		System.out.println();
		System.out.print("TEST: n="+this.numNodes+", seed="+this.seed);
		System.out.print(", p="+this.p);
		System.out.println();
		System.out.println("Time to generate graph: "+this.time+" milliseconds");
		System.out.println();
	}
	
	// Prints an adjacency matrix for the graph
	public void printAdjacencyMatrix(){
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
	
	// Prints an adjacency list for the graph
	public void printAdjacencyList(){
		System.out.println("The graph as an adjacency list:");
		for(int i = 0; i < this.numNodes; i++){
			System.out.print(i+"-> ");
			for(int j = 0; j < this.numNodes; j++){
				if(this.adjMatrix[i][j] != 0){
					System.out.print(j+"("+this.adjMatrix[i][j]+") ");
				}
			}
			System.out.println();
			System.out.println();
		}
	}
	
	// Print the DFS trace for the graph
	public void printDFSTrace(){
		System.out.println("Depth-First Search:\nVertices:");
		System.out.print(" ");
		for(int i = 0; i < this.numNodes; i++){
			System.out.print(i+" ");
		}
		System.out.println("\nPredecessors:");
		for(int i = 0; i < this.numNodes; i++){
			System.out.print(preTrace[i]+" ");
		}
	}
}