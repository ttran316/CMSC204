import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph implements GraphInterface<Town,Road>{
	private ArrayList<String> path = new ArrayList<>();
	private Set<Road> roads = new HashSet<>();
	private Set<Town> towns = new HashSet<>();
	private Town destination;
	
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		/*
		if(sourceVertex == null || destinationVertex == null) {
			return null;
		}
		*/
		return roads.stream().filter(x -> x.contains(sourceVertex) && x.contains(destinationVertex)).findAny().orElse(null);
	}
	
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws IllegalArgumentException, NullPointerException{
		if(sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
			throw new IllegalArgumentException();
		}
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		roads.add(road);
		return road;
	}
	
	public boolean addVertex(Town v) throws NullPointerException{
		if(v == null) {
			throw new NullPointerException();
		}
		if(!towns.contains(v)) {
			towns.add(v);
			return true;
		}
		return false;
	}
	
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for(Road road: roads) {
			if(road.contains(sourceVertex) && road.contains(destinationVertex)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsVertex(Town v) {
		return towns.contains(v);
	}
	
	public Set<Road> edgeSet() {
		return roads;
	}
	
	public Set<Road> edgesOf(Town vertex) {
		if(vertex == null) {
			throw new NullPointerException();
		}
		Set<Road> road = new HashSet<>();
		for(Road r : roads) {
			if(r.contains(vertex)) {
				road.add(r);
			}
		}
		if(road.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return road;
	}
	
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road temp= null;
		for(Road road: roads) {
			if(road.contains(destinationVertex) && road.contains(sourceVertex) && (weight > -1) && description != null) {
				temp = road;
			}
		}
		if(roads.remove(temp)) {
			return temp;
		}
		return null;
	}
	
	public boolean removeVertex(Town v) {
		if(v==null) {
			return false;
		}
		return towns.remove(v);
	}
	
	public Set<Town> vertexSet() {
		return towns;
	}
	
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		destination = destinationVertex;
		ArrayList<Road> pathing = new ArrayList<>();
		dijkstraShortestPath(sourceVertex);
		
		if(!(roads.stream().anyMatch(x -> x.contains(sourceVertex))) || !(roads.stream().anyMatch(x -> x.contains(destinationVertex)))) {
			return new ArrayList<>();
		}
		for(int i = 0; i < path.size()-1; i++) {
			Town pointA = new Town(path.get(i));
			Town pointB = new Town(path.get(i+1));
			Road road = getEdge(pointA, pointB);
			pathing.add(new Road(pointA, pointB, road.getDist(), road.getName()));
		}
		path.clear();
		return pathing.stream().map(Road::toString).collect(Collectors.toCollection(ArrayList::new));
		//return null;
	}
	
	public void dijkstraShortestPath(Town sourceVertex) {
		List<Town> list = new ArrayList<>(towns);
		int[][] array = new int[towns.size()][towns.size()];
		int towns = 0, start = 0, end = 0;
		
		for(int i = 0; i < array.length; i++) {
			for(int x = 0; x < array[i].length; x++) {
				if(i==x || !containsEdge(list.get(i), list.get(x))) {
					array[i][x] = 0;
				}else {
					int difference = getEdge(list.get(i), list.get(x)).getDist();
					array[i][x] = array[x][i] = difference; 
				}
			}
		}
		
		towns = array[0].length;
		boolean[] done = new boolean[towns];
		int[] dist = new int[towns];
		
		for(int i = 0; i < towns; i++) {
			dist[i] = Integer.MAX_VALUE;
			done[i] = false;
		}
		
		//check
		for(Town x: list) {
			if(!x.equals(sourceVertex)) {
				start++;
			}else {
				break;
			}
		}
		for(Town x: list) {
			if(!x.equals(destination)) {
				end++;
			}else {
				break;
			}
		}
		
		dist[start] = 0;
		int[] min = new int[towns];
		min[start]=-1;
		
		for(int i = 1; i< towns; i++) {
			int minn = Integer.MAX_VALUE;
			int close = -1;
			for(int x = 0; x <towns; x++) {
				if(!done[x] && dist[x] < minn) {
					close = x;
					minn = dist[x];
				}
			}
			done[close] = true;
			for(int x = 0; x < towns; x++) {
				int distance = array[close][x];
				if(distance > 0) {
					if(dist[x] > (minn+distance)) {
						min[x] = close;
						dist[x] = minn+distance;
					}
				}
			}
		}
		/*
		if(end == -1) {
			
		}else {
			
		}
		*/
		re(end, min);
	}
	private void re(int i, int[] j) {
		if(i == -1) {
			return;
		}
		int temp = 0;
		re(j[i], j);
		for(Town town : towns) {
			if(temp == i) {
				path.add(town.getName());
			}
			temp++;
		}
	}
}
