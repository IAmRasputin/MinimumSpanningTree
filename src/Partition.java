// File:	Partition.java
// Description:	Represents a linked list of vertices
// AUthor:	Ryan Gannon

public class Partition
{
	private int[] parent, rank;

	// public constructor
	public Partition(int size)
	{
		parent = new int[size];
		rank = new int[size];

		for(int i = 0; i < size; i++){
			rank[i] = 0;
			parent[i] = i;
		}
	}
	
	// Finds the root of a vertex (with path compression)
	public int find(int vertex)
	{
		if(vertex != parent[vertex]){
			parent[vertex] = find(parent[vertex]);
		}

		return parent[vertex];
	}
	
	// Joins two vertices (union-by-rank)
	public void union(int vertex1, int vertex2)
	{
		if(this.rank[vertex1] > this.rank[vertex2]){
			parent[vertex2] = vertex1;
		} else {
			parent[vertex1] = vertex2;
			if(rank[vertex1] == rank[vertex2]){
				rank[vertex2]++;
			}
		}
	}
}
