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
	
	public abstract boolean isConnected();
	
	//Clears a data structure in case it's not connected
	public abstract void clear();
	
}
