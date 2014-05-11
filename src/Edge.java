// File:	Edge.java
// Description:	A class representing an edge of the graph
// Author:	Ryan Gannon

public class Edge
{
	private int weight;
	private int lNode;
	private int rNode;

	// Public constructor
	public Edge(int w, int l, int r)
	{
		weight = w;
		lNode = l;
		rNode = r;
	}
	
	// Copy Constructor
	public Edge(Edge other){
		this.weight = other.getWeight();
		this.lNode = other.getLeftNode();
		this.rNode = other.getRightNode();
	}

	// Returns the weight of the edge
	public int getWeight()
	{
		return weight;
	}

	// Returns the left node of the edge
	public int getLeftNode()
	{
		return lNode;
	}

	// Returns the right node of the edge
	public int getRightNode()
	{
		return rNode;
	}

	// Performs an equality check on this edge and another
	public boolean equals(Edge e)
	{
		return  ((this.lNode == e.lNode && this.rNode == e.rNode) ||
			(this.lNode == e.rNode && this.rNode == e.lNode)) &&
			(this.weight == e.weight);
	}

	// Determines ordering for two edges, based on weight and nodes
	public boolean lessThan(Edge e)
	{
		if(this.getWeight() < e.getWeight()){
			return true;
		} else if (this.getWeight() > e.getWeight()){
			return false;
		} else {
			if(this.getLeftNode() < e.getLeftNode()){
				return true;
			} else if(this.getLeftNode() > e.getLeftNode()){
				return false;
			} else {
				if(this.getRightNode() < e.getRightNode()){
					return true;
				} else if(this.getRightNode() > e.getRightNode()){
					return false;
				}
			}
		}
		return true;
	}

	// Returns a string representation of the edge
	public String toString()
	{
		return "Edge of weight " +this.getWeight()+ " with left node "+
		       this.getLeftNode() + " and right node " +
		       this.getRightNode();
	}
}
