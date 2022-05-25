import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TownGraphManager implements TownGraphManagerInterface{
	Graph graph = new Graph();

	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		if(graph.addEdge(new Town(town1), new Town(town2), weight, roadName) != null) {
			return true;
		}
		return false;
	}

	
	public String getRoad(String town1, String town2) {
		return graph.getEdge(new Town(town1), new Town(town2)).getName();
	}

	
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	
	public Town getTown(String name) {
		return graph.vertexSet().stream().filter(x -> x.getName().equals(name)).findAny().orElse(null);
	}

	
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	
	public ArrayList<String> allRoads() {
		return graph.edgeSet().stream().map(Road::getName).sorted().collect(Collectors.toCollection(ArrayList::new));
	}

	
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		return graph.removeEdge(new Town(town1), new Town(town2), 0, road) != null;
	}

	
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	
	public ArrayList<String> allTowns() {
		
		return graph.vertexSet().stream().map(Town::getName).sorted().collect(Collectors.toCollection(ArrayList::new));
	}

	
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}


	public void populateTownGraph(File selectedFile) throws IOException {
		InputStream input = new FileInputStream(selectedFile);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
		buffer.lines().map(x -> x.split(";|//,")).forEach(y -> {addTown(y[2]); addTown(y[3]); addRoad(y[2], y[3], Integer.parseInt(y[1]), y[0]);});
		buffer.close();
	}
	
	
}
