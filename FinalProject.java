 import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Map;
import java.util.Scanner;
import edu.princeton.cs.algs4.DijkstraSP;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.TST;


public class FinalProject
{
	public static void main(String[] args) throws IOException 
	{
 	try(BufferedReader br1 = new BufferedReader(new FileReader("/Users/aliconnolly/Algorithms2 Final project/Algorithms2-Final-project/stops.txt")))
	    {
	    	br1.readLine();
	    	String line = null;
	    	int stopIndex = 0;
			
	    	String stop_name = null;
			
	    	while((line = br1.readLine()) != null)
	    	{
	    		
                	
            }
                
	    		  		
				
	    			
	    }
		
			
		try(BufferedReader br2 = new BufferedReader(new FileReader("/Users/aliconnolly/Algorithms2 Final project/Algorithms2-Final-project/transfers.txt")))
		{
			br2.readLine();
			String line2 = null;
			while((line2 = br2.readLine()) != null)
			{
				
			}
		}
	    catch(IOException e)
		{
			System.out.println("File not found");	
        } 
			//br2.close();
			
	try(BufferedReader br3 = new BufferedReader(new FileReader("/Users/aliconnolly/Algorithms2 Final project/Algorithms2-Final-project/stop_times.txt")))
	{	
	   br3.readLine();
	   String line3 = null;
	   int prev_stop_id = 646;
	   double transferCost = 1.0;
			
	   String arrival_time = null;
	   String stop_id3 = null;
			
			
	   while((line3 = br3.readLine()) != null)
		{
				
			
	     } 
		//br3.close();	
	}
	catch(IOException e)
	{
		System.out.println("No such file found.");
	}
		

		
		
// GET USER INPUT		
		// get user to input 2 bus stops
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter 2 bus stops with a space between them (E.g. 34 96).");
		String inputStops = scanner.nextLine();
		
		// converting input to integer array
		String[] splitInputArray = inputStops.split(" ");
		int[] inputStopsArr = new int[splitInputArray.length];
		for(int i = 0; i < splitInputArray.length; i++)
		{
			inputStopsArr[i] = Integer.parseInt(splitInputArray[i]);
		}
		
		
		// find shortest path between he two bus stops
		// return shortest path, list of stops on the route and the cost(distance)
		
		
		// make graph
		//EdgeWeightedGraph graph = new EdgeWeightedGraph(numOfvertices, numOfEdges);
		
		// create dijkstra class object
		//DijkstraSP dijkstraClassObj = new DijkstraSP(graph, source);
		
		

	

	}

}
