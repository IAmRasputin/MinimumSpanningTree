// File:	MST.java
// Description: Processes input and output for a weighted graph generator
// Author:	Ryan Gannon

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.lang.NumberFormatException;
import java.io.IOException;


// The main class, takes input from a file, and prints output to stdout 
public class MST{
	
	// Reads in a supplied input file
	public static ArrayList<String> parseFile(BufferedReader b) 
		throws IOException {

		ArrayList<String> out = new ArrayList<String>();

		// Read the lines of the file as strings
		String n = b.readLine();
		String seed = b.readLine();
		String p = b.readLine();
		
		// Check for correct number formatting, exit gracefully if an error is
		// found, e.g. p is not a double, n is not an integer
		try{
			Integer.parseInt(n);
			Integer.parseInt(seed);
		} catch (NumberFormatException e){
			System.out.println("n and seed must be integers");
			System.exit(1);
		}	
			
		try{
			Double.parseDouble(p);
		} catch (NumberFormatException e){
			System.out.println("p must be a real number");
			System.exit(1);
		}
		
		if(Integer.parseInt(n) < 2){
			System.out.println("n must be greater than 1");
			System.exit(1);
		}

		if(Double.parseDouble(p) < 0 || Double.parseDouble(p) > 1){
			System.out.println("p must be between 0 and 1");
			System.exit(1);
		}

		// If everything looks good, append it to an ArrayList
		out.add(n);
		out.add(seed);
		out.add(p);

		return out;
	}
	
	public static void main(String[] args){
		
		// Make sure that there is a file name supplied
		if( args.length < 1 ){
			System.out.println("Input file not found");
			System.exit(1);
		}
		BufferedReader b = null;
		FileReader fr = null;
		
		// Set up the file IO, exiting gracefully if the file does not exist
		try{
			fr = new FileReader(args[0]);
			b = new BufferedReader(fr);
		} catch (FileNotFoundException e){
			System.out.println("Input file not found");
			System.exit(1);
		}
		
		// Parse the input file into an ArrayList
		ArrayList<String> fileIn = new ArrayList<String>();
		
		// This try-catch will gracefully exit upon any error reading the file
		try{
			fileIn = MST.parseFile(b);

		} catch(Exception e){
			System.out.println("Error reading file");
			System.exit(1);
		}
		
		// Generate the graph
		Graph graph = new Graph(
				Integer.parseInt(fileIn.get(0)), 
				Integer.parseInt(fileIn.get(1)), 
				Double.parseDouble(fileIn.get(2)));

		// Display the output
		graph.printSummary();
		if(Integer.parseInt(fileIn.get(0)) <= 10){
			graph.adjMatrix.print();
			graph.adjList.print();
			graph.adjMatrix.DFSTrace();
			
		}
		graph.adjMatrix.insertionSort();
		graph.adjMatrix.countSort();
		graph.adjMatrix.quickSort();
		graph.adjList.insertionSort();
		graph.adjList.countSort();
		graph.adjList.quickSort();
		
	}
}
