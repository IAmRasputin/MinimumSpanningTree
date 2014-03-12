// File:	Graph.java
// Description:	The driver file for graph generation 
// Author:	Ryan Gannon

import java.util.Random;


// Represents an undirected, weighted graph
public class Graph{
	private int numNodes;
	private long seed;			// The pseudo-random number generator seed
	private double p;			// The probability of a connection being made
	long time;					// Used to determine generation time
	long startTime;				// See time
	Random edge;				// PRNG for edge connections
	Random weight;				// PRNG for edge weights

	AdjacencyMatrix adjMatrix;
	AdjacencyList adjList;
	
	private Integer[][] matrix;
	private Integer[] preTrace;

	// Public constructor
	public Graph(int nodes, long sd, double prob){
		this.numNodes = nodes;
		this.seed = sd;
		this.p = prob;
		this.edge = new Random((long)this.seed);
		this.weight = new Random((long)(this.seed * 2));
		this.preTrace = new Integer[nodes];
		this.adjMatrix = new AdjacencyMatrix(nodes);
		this.adjList = new AdjacencyList(nodes);
		
		// Make a note of the current time
		startTime = System.currentTimeMillis();
		
		// Generate a graph.  If it is unconnected, try again.
		do{
			adjMatrix.clear();
			adjList.clear();
			for(int i = 0; i < numNodes; i++)
				for(int j = 0; j < i; j++)
					generateConnection(i, j);
			
		} while( !adjMatrix.isConnected() );
		
		// See how much time it took to generate the graph
		time = System.currentTimeMillis() - startTime;
	}

	// Generate the graph and return the Adjacency Matrix as an Integer[][]
	private void generateConnection(int node1, int node2){
		int weight =  this.weight.nextInt(this.numNodes) + 1;
		double connected = this.edge.nextDouble();
		System.out.println("Connecting nodes " + node1 + " and " + node2 + " with weight " + weight);
		if( node1 != node2 ){
			if(connected < this.p){
				System.out.println("connected");
				adjMatrix.connect(node1, node2, weight);
				adjList.connect(node1, node2, weight);
			} else {
				System.out.println("not connected");
				adjMatrix.connect(node1, node2, 0);
			}
		}
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
	
}
