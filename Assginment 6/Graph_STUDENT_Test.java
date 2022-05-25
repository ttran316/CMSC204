import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {
	private Town[] town;
	private GraphInterface<Town,Road> graph;
	
	@Before
	public void setUp() throws Exception{
		graph = new Graph();
		town = new Town[10];
		for (int i = 1; i < 9; i++) {
			town[i] = new Town("Town_" + i);
			graph.addVertex(town[i]);
		}
		
		graph.addEdge(town[1], town[7], 3, "Road_1");
	    graph.addEdge(town[1], town[2], 2, "Road_2");
	    graph.addEdge(town[2], town[4], 6, "Road_3");
	    graph.addEdge(town[8], town[4], 4, "Road_4");
	    graph.addEdge(town[4], town[2], 2, "Road_5");
	    graph.addEdge(town[6], town[1], 2, "Road_6");
	    graph.addEdge(town[5], town[7], 3, "Road_7");
	    graph.addEdge(town[7], town[5], 5, "Road_8");
	    graph.addEdge(town[8], town[6], 4, "Road_9");
	    
	}
	@After
	public void tearDown() throws Exception{
		graph = null;
	}
	
	@Test
	public void testGetEdge() {
		assertEquals(new Road(town[1], town[2],1, "Road_1"), graph.getEdge(town[1], town[2]));
		assertEquals(new Road(town[2], town[1],2, "Road_2"), graph.getEdge(town[2], town[1]));
	}
	
	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[2], town[5]));
		graph.addEdge(town[2], town[5], 1, "Road_11");
		assertEquals(true, graph.containsEdge(town[2], town[5]));
	}
	
	@Test
	public void testAddVertex() {
		Town newTown = new Town("Town_11");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}
	
	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(town[8], town[4]));
		assertEquals(false, graph.containsEdge(town[2], town[5]));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("Town_6")));
		assertEquals(false, graph.containsVertex(new Town("Town_13")));
	}
	
	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_1", roadArrayList.get(0));
		assertEquals("Road_2", roadArrayList.get(1));
		assertEquals("Road_3", roadArrayList.get(2));
		assertEquals("Road_4", roadArrayList.get(3));
		assertEquals("Road_5", roadArrayList.get(4));
		assertEquals("Road_9", roadArrayList.get(8));
	}
	
	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(town[2]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_3", roadArrayList.get(1));
		assertEquals("Road_5", roadArrayList.get(2));
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(town[8], town[4]));
		graph.removeEdge(town[8], town[4], 6, "Road_13");
		assertEquals(false, graph.containsEdge(town[8], town[4]));
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(town[7]));
		graph.removeVertex(town[7]);
		assertEquals(false, graph.containsVertex(town[7]));
	}
	
	@Test
	public void testVertexSet() {
		Set<Town> roads = graph.vertexSet();
		assertEquals(true,roads.contains(town[2]));
		assertEquals(true, roads.contains(town[4]));
		assertEquals(true, roads.contains(town[1]));
		assertEquals(true, roads.contains(town[7]));
		assertEquals(true, roads.contains(town[3]));
	}
	
	@Test
	public void testTown() {
		String beginTown = "Town_1", endTown = "Town_8";
		Town beginIndex=null, endIndex=null;
		Set<Town> towns = graph.vertexSet();
		Iterator<Town> iterator = towns.iterator();
		while(iterator.hasNext())
		{    	
			Town town = iterator.next();
			if(town.getName().equals(beginTown))
				beginIndex = town;
			if(town.getName().equals(endTown))
				endIndex = town;		
		}
		
		if(beginIndex != null && endIndex != null)
		{

			ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			assertNotNull(path);
			assertTrue(path.size() > 0);
			assertEquals("Town_1 via Road_6 to Town_6 2 mi",path.get(0).trim());
			assertEquals("Town_6 via Road_9 to Town_8 4 mi",path.get(1).trim());
		}
		else
			fail("Town names are not valid");
	}
	
}
