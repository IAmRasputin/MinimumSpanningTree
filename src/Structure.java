import java.util.ArrayList;

// File:	Structure.java
// Description:	An abstract superclass for Adjacency lists and matrices
// Author:	Ryan Gannon

public abstract class Structure {
	
	private int numNodes;
	
	
	// Return the string representation of the struct
	public abstract void print();
	
	// Connects two given nodes with the given weight
	public abstract void connect(int node1, int node2, int weight);
	
	// Perform an insertion sort on the struct and print it
	public abstract void insertionSort();
	
	// Perform a count sort and print the results
	public abstract void countSort();
	
	// Perform a quicksort and print the results
	public abstract void quickSort();
	
	public int getNumNodes(){
		return this.numNodes;
	}
	
	// Finds the MST with kruskal's algorithm and prints the results
	public static void kruskalMST(ArrayList<Edge> edges, int size){
		Partition part = new Partition(size);
		int root1, root2 = 0;
		int w = 0; // Weight
		Partition copy = part;
		ArrayList<Edge> MST = new ArrayList<Edge>();
		
		
		for(Edge e : edges){
			root1 = part.find(e.getLeftNode());
			root2 = part.find(e.getRightNode());
			
			if(root1 != root2){
				MST.add(e);
				part.union(root1, root2);
			}
		}

		int len = MST.size();

		for(Edge e : MST){
			if(len <= 10){
				System.out.println(e.getLeftNode() + " " +
					e.getRightNode() + " weight = " +
					e.getWeight());
			}
			w += e.getWeight();
		}

		System.out.println();
		System.out.println("Total weight of MST using Kruskal: "+ w);
		
	}
	
	// Is the structure representing a connected graph?
	public abstract boolean isConnected();
	
	//Clears a data structure in case it's not connected
	public abstract void clear();
	
}
