import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FinalProject
{

	public static void main(String[] args) 
	{

	
	
// READING IN INPUT FILES		
		// read in stops.txt (list of stops)
		String stopsFileLine = "";
		try {
			File stopsFile = new File("stops.txt");
			Scanner myReaderStops = new Scanner(stopsFile);
			while(myReaderStops.hasNextLine())
			{
				stopsFileLine = myReaderStops.nextLine();				
			}
			myReaderStops.close();
			String[] splitStopsArray = stopsFileLine.split(",");
			int[] stopsFileArray = new int[splitStopsArray.length];
			for(int j = 0; j < splitStopsArray.length; j++)
			{
				// array of bus stops
				// stop_id, stop_code, stop_name, stop_desc, stop_lat, stop_lon, zone_id, stop_url, location_type, parent_station
				
				stopsFileArray[j] = Integer.parseInt(splitStopsArray[j]);
			}
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("An error occured - file name may be incorrect");
			e.printStackTrace();
		}
		
		// read in stop_times.txt (edge only exists if they have sam ID (directed and undirected)?)
		String stopTimesFileLine = "";
		try {
			File stopTimesFile = new File("stops_times.txt");
			Scanner myReaderStopTimes = new Scanner(stopTimesFile);
			while(myReaderStopTimes.hasNextLine())
			{
				// stop times array
				// trip_id, arrival_time, departure_time, stop_id, stop_sequence, stop_headsign, pickup_type,drop_off_type, shape_dist_traveled
				stopTimesFileLine = myReaderStopTimes.nextLine();				
			}
			myReaderStopTimes.close();
			String[] splitStopTimesArray = stopTimesFileLine.split(",");
			int[] stopTimesFileArray = new int[splitStopTimesArray.length];
			for(int j = 0; j < splitStopTimesArray.length; j++)
			{
				// array of
				stopTimesFileArray[j] = Integer.parseInt(splitStopTimesArray[j]);
			}
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("An error occured - file name may be incorrect");
			e.printStackTrace();
		}
		
		
		// read in transfers.txt (directed edges)
		String transfersFileLine = "";
		try {
			File transfersFile = new File("transfers.txt");
			Scanner myReaderTransfers = new Scanner(transfersFile);
			while(myReaderTransfers.hasNextLine())
			{
				// from_stop_id, to_stop_id, transfer_type, min_transfer_time
				transfersFileLine = myReaderTransfers.nextLine();				
			}
			myReaderTransfers.close();
			String[] splitTransfersArray = transfersFileLine.split(",");
			int[] transfersFileArray = new int[splitTransfersArray.length];
			for(int j = 0; j < splitTransfersArray.length; j++)
			{
				transfersFileArray[j] = Integer.parseInt(splitTransfersArray[j]);
			}
					
		}
		catch(FileNotFoundException e)
		{
			System.out.println("An error occured - file name may be incorrect");
			e.printStackTrace();
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
