// File:	Partition.java
// Description:	Represents a linked list of vertices
// AUthor:	Ryan Gannon

import java.util.ArrayList;
import java.util.LinkedList;

public class Partition
{
	// Internal class to represent a vertex.  Tryting out a 
	// Lisp-y bracketing style to save space in the parent class.
	private static class Vertex{
		private int index;
		private Vertex parent;
		private LinkedList<Vertex> children;
		
		// Public constructor, sets all to null
		public Vertex(){
			index = null;
			parent = null;
			children = new LinkedList<Vertex>();}

		// Sets the index
		public setIndex(int i){
			this.index = i;}

		// Sets the parent
		public setParent(Vertex p){
		 	this.parent = p;}

		// Adds a child to the LinkedList of children
		public addChild(Vertex c){
			this.children.add(c);}

		// Returns the index of the vertex
		public int getIndex(){
			return this.index;}

		// Returns the parent vertex
		public Vertex getParent(){
			return this.parent;}

		// Returns the LinkedList of Children
		public LinkedList<Vertex> getChildren(){
			return this.children;}
	} // private static class Vertex

	// Represents a connected subgraph of vertices
	private static class SubGraph{
		private LinkedList<Vertex> graph; // graph.at(0) := root

		// Public constructor
		public SubGraph(Vertex root){
			graph = new LinkedList<Vertex>();
			graph.add(root);}

		

		// Adds a vertex to the subgraph, with no links
		public void addVertex(Vertex v)
}		
