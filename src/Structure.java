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
	
	// Compares two edges (arraylists of integers) to force quicksort to be stable
	protected static boolean edgeLessThan(ArrayList<Integer> edge1, ArrayList<Integer> edge2){
		if(edge1.get(2) < edge2.get(2)){
			return true;
		} else if (edge1.get(2) > edge2.get(2)){
			return false;
		} else {
			if(edge1.get(0) < edge2.get(0)){
				return true;
			} else if(edge1.get(0) > edge2.get(0)){
				return false;
			} else {
				if(edge1.get(1) < edge2.get(1)){
					return true;
				} else if(edge1.get(1) > edge2.get(1)){
					return false;
				}
			}
		}
		return true;
	}
	
	public abstract boolean isConnected();
	
	//Clears a data structure in case it's not connected
	public abstract void clear();
	
}
