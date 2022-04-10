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
		int numOfStops = 0;
		
		Map<Integer, Integer> mapStopsIndices = new HashMap<Integer, Integer>();
		
		TST <String> busStopTST = new TST<String>();
		TST<String> arrivalTimeTST = new TST<String>();
		
	    try(BufferedReader br1 = new BufferedReader(new FileReader("/Users/aliconnolly/Algorithms2 Final project/Algorithms2-Final-project/stops.txt")))
	    {
	    	br1.readLine();
	    	
	    	String lineOfFile = null;
	    	int stopIndex = 0;
		   	String stop_name = null;
			
	    	while((lineOfFile = br1.readLine()) != null)
	    	{
	    		String[] parts = lineOfFile.split(",");			
				
	    		int stop_id = Integer.parseInt(parts[0].trim());
	    		stop_name = parts[2].trim();
	    		
                String[] checkNamePrefix = stop_name.split(" ");
                String[] tempArray = new String[checkNamePrefix.length-1];
                if(checkNamePrefix[0] == "WB" || checkNamePrefix[0] == "NB" || checkNamePrefix[0] == "SB" || checkNamePrefix[0] == "EB" || checkNamePrefix[0] == "flagstop")
                {
                	String saveNamePrefix = checkNamePrefix[0];
                	for(int i = 0, k = 0; i < checkNamePrefix.length; i++)
                	{
                		if(i == 0)
                		{
                			continue;
                		}
                		tempArray[k++] = checkNamePrefix[i];
                	}
                	tempArray[tempArray.length-1] = saveNamePrefix;
                	stop_name = tempArray.toString();
                	
                }
	    		busStopTST.put(stop_name, lineOfFile);
				
	    		mapStopsIndices.put(stop_id, stopIndex);
	    		stopIndex++;	
	    	}
	    	
			
	    	List<String> stopsFile = Files.readAllLines(new File("/Users/aliconnolly/Algorithms2 Final project/Algorithms2-Final-project/stops.txt").toPath(), Charset.defaultCharset());
	    	for (int i = 1; i < stopsFile.size(); i++)
	    	{
	    		String[] parts = stopsFile.get(i).split(",");
	    		int currentStop = Integer.parseInt(parts[0]);
	    		if (currentStop > numOfStops)
	    		{
	    			numOfStops = currentStop;
	    		}
	    	}
	    }
	    catch(IOException e)
	    {
	    	System.out.println("Error. File not found.");
	    }

	    
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(numOfStops);
			
		try(BufferedReader br2 = new BufferedReader(new FileReader("/Users/aliconnolly/Algorithms2 Final project/Algorithms2-Final-project/transfers.txt")))
		{
			br2.readLine();
			String lineOfFile = null;
			while((lineOfFile = br2.readLine()) != null)
			{
				
				String[] parts = lineOfFile.split(",");
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
			System.out.println("Error. File not found");	
        } 

			
	try(BufferedReader br3 = new BufferedReader(new FileReader("/Users/aliconnolly/Algorithms2 Final project/Algorithms2-Final-project/stop_times.txt")))
	{	
	   br3.readLine();
	   String lineOfFile = null;
	   int prev_stop_id = 646;
	   double transferCost = 1.0;
			
	   String arrival_time = null;
	   String stop_id = null;
			
			
	   while((lineOfFile = br3.readLine()) != null)
		{
				
			String[] parts = lineOfFile.split(",");
				
			 arrival_time = parts[1].trim();
			 stop_id = parts[3].trim();
	
			arrivalTimeTST.put(arrival_time, lineOfFile);
			int fromStopIndex = mapStopsIndices.get(prev_stop_id);
			int toStopIndex = mapStopsIndices.get(Integer.parseInt(stop_id));
								
			graph.addEdge(new DirectedEdge(fromStopIndex, toStopIndex, transferCost));
				
			prev_stop_id = Integer.parseInt(stop_id);
	     } 
	}
	catch(IOException e)
	{
		System.out.println("Error. File not found.");
	}
	
	
boolean quit = false;
Scanner scanner = new Scanner(System.in);
System.out.println("Welcome to the Vancouver public transport system!");
System.out.println("This system allows you to: \n1. Find shortest path between 2 bus stops. \n2. Retrieve all info related to a particular bus stop. \n3. Get details of all trips arriving at specified time. \n");

while(!quit) 
{				
	System.out.println("Which option would you like to choose? \nPlease choose by typing 1, 2 or 3. Or type 'quit' if you would like to close the application.\n");
	if(scanner.hasNextInt()) 
	{
		Integer userChoice = scanner.nextInt();
		scanner.skip("\\R?");

		if(userChoice == 1)
		{
			System.out.println("Please enter the first bus stop.\n");
			if(scanner.hasNextLine())
			{
				Integer source = scanner.nextInt();
				scanner.skip("\\R?");
					
				System.out.println("Please enter the second bus stop.\n");
				int destination = scanner.nextInt();
				scanner.skip("\\R?");

				DijkstraSP dijkstraObj = new DijkstraSP(graph, source);
				System.out.print("The shortest path distance between the 2 bus stops is: " +  dijkstraObj.distTo(destination) + "\n");

				System.out.print("The stops along the shortest route are: " + dijkstraObj.pathTo(destination)+ "\n");
			}
			else
			{
				System.out.println("Bus stop does not exist. Please enter a valid bus stop.");
			}
		}
		else if(userChoice == 2)
		{
			System.out.print("Please enter bus stops' full name or the first few characters to retrieve all information relating to stop.\n");
		    String name = scanner.next();
		
		    if(!busStopTST.contains(name))
			{
				System.out.println("Sorry - No arrival times match your search.");
			}
			Iterable<String> namesWithPrefix = busStopTST.keysWithPrefix(name);
			System.out.print(namesWithPrefix);
			String namesWithPrefixString = namesWithPrefix.toString();
			String[] namesWithPrefixArr = namesWithPrefixString.split(" ");
			if(namesWithPrefixArr.length > 1)
			{
				for(int i = 0; i < namesWithPrefixArr.length; i++)
				{
					String info = busStopTST.get(namesWithPrefixArr[i]);
					System.out.println(info);
				}
		
			}
			else 
			{
				String info = busStopTST.get(namesWithPrefixString);
				System.out.println(info);
			}
		}
		else if(userChoice == 3)
		{
			System.out.print("Please enter bus' arrival time to retrieve all information relating to trip.\n");		   
			
				String time = scanner.nextLine();
				if(busStopTST.keysWithPrefix(time) == null)
				{
					System.out.println("Sorry. No bus stops match your search.");
				}
				else
				{
					System.out.print(arrivalTimeTST.keysWithPrefix(time));
				}			

			if(!busStopTST.contains(time))
			{
				System.out.println("Error. Please enter a valid arrival time.");
			}
		}
		else
		{
			System.out.println("Error. Please enter a valid choice number (1-3)\n");
		}
	}
	else {
		String newLine = scanner.nextLine();
		if(newLine.equals("quit")) {
			quit = true;
		}
		else {
			System.out.println("Error. Please enter a valid choice number (1-3)\n");
		}
	}

}
scanner.close();
}
}
	
