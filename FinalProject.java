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
		int numOfVertices = 0;
		
		Map<Integer, Integer> mapStopsIndices = new HashMap<Integer, Integer>();
		
		TST <String> busStopTST = new TST<String>();
		TST<String> arrivalTimeTST = new TST<String>();
		
	    try(BufferedReader br1 = new BufferedReader(new FileReader("/Users/aliconnolly/Algorithms2 Final project/Algorithms2-Final-project/stops.txt")))
	    {
	    	br1.readLine();
	    	String line = null;
	    	int stopIndex = 0;
			
	    	String stop_name = null;
			
	    	while((line = br1.readLine()) != null)
	    	{
	    		String[] parts = line.split(",");			
				
	    		int stop_id = Integer.parseInt(parts[0].trim());
	    		stop_name = parts[2].trim();
                String[] checkPrefix = stop_name.split(" ");
                String[] tempArray = new String[checkPrefix.length-1];
                if(checkPrefix[0] == "WB" || checkPrefix[0] == "NB" || checkPrefix[0] == "SB" || checkPrefix[0] == "EB" || checkPrefix[0] == "flagstop")
                {
                	String savePrefix = checkPrefix[0];
                	for(int i = 0, k=0; i < checkPrefix.length; i++)
                	{
                		if(i == 0)
                		{
                			continue;
                		}
                		tempArray[k++] = checkPrefix[i];
                	}
                	tempArray[tempArray.length-1] = savePrefix;
                	stop_name = tempArray.toString();
                	
                }
                
	    		
	    		
				
	    		busStopTST.put(stop_name, line);
				
	    		mapStopsIndices.put(stop_id, stopIndex);
	    		stopIndex++;	
	    	}
			
	    	List<String> stops = Files.readAllLines(new File("/Users/aliconnolly/Algorithms2 Final project/Algorithms2-Final-project/stops.txt").toPath(), Charset.defaultCharset());
	    	for (int i = 1; i < stops.size(); i++)
	    	{
	    		String[] parts = stops.get(i).split(",");
	    		int currentStop = Integer.parseInt(parts[0]);
	    		if (currentStop > numOfVertices)
	    		{
	    			numOfVertices = currentStop;
	    		}
	    	}
	    }
	    catch(IOException e)
	    {
	    	System.out.println("Sorry no such file found.");
	    }
			
			//br1.close();
			EdgeWeightedDigraph graph = new EdgeWeightedDigraph(numOfVertices);
			
		try(BufferedReader br2 = new BufferedReader(new FileReader("/Users/aliconnolly/Algorithms2 Final project/Algorithms2-Final-project/transfers.txt")))
		{
			br2.readLine();
			String line2 = null;
			while((line2 = br2.readLine()) != null)
			{
				
				String[] parts = line2.split(",");
				int from_stop_id = Integer.parseInt(parts[0].trim());
				int to_stop_id = Integer.parseInt(parts[1].trim());	
				
				double transferCost = (Integer.parseInt(parts[2].trim()) == 0) ? 2.0 : Double.parseDouble(parts[3])/100;
								
				int fromStopIndex = mapStopsIndices.get(from_stop_id);
				int toStopIndex = mapStopsIndices.get(to_stop_id);

				graph.addEdge(new DirectedEdge(fromStopIndex, toStopIndex, transferCost));
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
				
			String[] parts = line3.split(",");
				
			 arrival_time = parts[1].trim();
			 stop_id3 = parts[3].trim();
	
			arrivalTimeTST.put(arrival_time, line3);
			int fromStopIndex = mapStopsIndices.get(prev_stop_id);
			int toStopIndex = mapStopsIndices.get(Integer.parseInt(stop_id3));
								
			graph.addEdge(new DirectedEdge(fromStopIndex, toStopIndex, transferCost));
				
			prev_stop_id = Integer.parseInt(stop_id3);
	     } 
		//br3.close();	
	}
	catch(IOException e)
	{
		System.out.println("No such file found.");
	}
			
			
	boolean quit = false;
	Scanner scanner = new Scanner(System.in);
	System.out.println("Welcome to the Vancouver public transport system!");
	System.out.println("This system allows you to: \n1. Find shortest path between 2 bus stops. \n2. Retrieve all info related to a particular bus stop. \n3. Get details of all trips arriving at specified time. \n");
	System.out.println("Which option would you like to choose? \nPlease choose by typing 1, 2 or 3. Or type 'quit' if you would like to close the application.\n");
	
	//do {	
				
		 Scanner input = new Scanner(System.in);
						
	     if(input.hasNextInt())
		 {
		    Integer userChoice = input.nextInt();
		    
		    if(userChoice == 1)
		    {
				System.out.println("Please enter the bus stop 1\n");
				Scanner firstStop = new Scanner(System.in);
				if(firstStop.hasNextLine())
				{
					Integer source = firstStop.nextInt();
								
					System.out.println("Please enter the bus stop 2\n");
					Scanner secondStop = new Scanner(System.in);
					int destination = secondStop.nextInt();
					
					DijkstraSP dijkstraObj = new DijkstraSP(graph, source);
					System.out.print("The shortest path distance between the 2 bus stops is: " +  dijkstraObj.distTo(destination) + "\n");
					
					System.out.print("The stops on the shortest route are: " + dijkstraObj.pathTo(destination)+ "\n");
					firstStop.close();
					secondStop.close();
				}
				else
				{
					System.out.println("Bus stop does not exist. Please enter a valid bus stop.");
				}
				
				
		    }
		    else if(userChoice == 2)
		    {
		    	System.out.print("Please enter bus stops' full name or the first few characters in it's name to retrieve all information relating to stop.\n");
		    	Scanner userStopInput = new Scanner(System.in);
		    	//if(userStopInput.hasNextLine())
		    	//{
		    		String name = userStopInput.nextLine();
		    		//if(busStopTST.keysWithPrefix(name) != null)
		    		//{
		    		System.out.print(busStopTST.keysWithPrefix(name));
		    		//}
		    		//else
		    		//{
		    			//System.out.println("Sorry - No bus stops match your search.");
		    		//}			
		    		userStopInput.close();
		    	//}
		    	//else
		    	//{
		    		//System.out.println("Error - please enter letters only");
		    	//}
		    }
		    else if(userChoice == 3)
		    {
		    	System.out.print("Please enter bus' arrival time to retrieve all information relating to trip.\n");		   
		    	Scanner userTimeInput = new Scanner(System.in);
		    	if(userTimeInput.hasNextLine())
		    	{
		    		String time = userTimeInput.nextLine();
		    		if(busStopTST.keysWithPrefix(time) == null)
		    		{
		    			System.out.println("Sorry - No bus stops match your search.");
		    		}
		    		else
		    		{
		    			System.out.print(arrivalTimeTST.keysWithPrefix(time));
		    		}			
		    		
		    	}
		    	else
		    	{
		    		System.out.println("Error - Please enter a valid arrival time;");
		    	}
		    	userTimeInput.close();
		    }
		    
	 }
	 else if(input.hasNext("quit"))
	 {
		  quit = true;
     }
	 else
	 {
		 System.out.println("Please enter a valid choice number (1-3)\n");
		 System.out.println("Which option would you like to choose? \nPlease choose by typing 1, 2 or 3. Or type 'quit' if you would like to close the application.\n");
	 }
	
	//} while(!quit);
	scanner.close();
	}
}
	
	
		


