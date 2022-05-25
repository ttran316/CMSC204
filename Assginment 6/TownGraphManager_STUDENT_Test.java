import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
	    graph = new TownGraphManager();
	    town = new String[8];

	    for (int i = 1; i < 8; i++) {
	      town[i] = "Town_" + i;
	      graph.addTown(town[i]);
	    }

	    
	    graph.addRoad(town[2], town[7], 7, "Road_1");
	    graph.addRoad(town[1], town[5], 6, "Road_2");
	    graph.addRoad(town[2], town[4], 5, "Road_3");
	    graph.addRoad(town[3], town[1], 4, "Road_4");
	    graph.addRoad(town[4], town[6], 3, "Road_5");
	    graph.addRoad(town[3], town[5], 2, "Road_6");
	    graph.addRoad(town[4], town[5], 1, "Road_7");

	  }

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_4", roads.get(3));
		graph.addRoad(town[4], town[1], 1,"Road_10");
		roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_2", roads.get(2));
		assertEquals("Road_3", roads.get(3));
		assertEquals("Road_4", roads.get(4));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("Road_6", graph.getRoad(town[3], town[5]));
		assertEquals("Road_2", graph.getRoad(town[1], town[5]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_15"));
		graph.addTown("Town_15");
		assertEquals(true, graph.containsTown("Town_15"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Town_12"));
		graph.addTown("Town_12");
		ArrayList<String> path = graph.getPath(town[1],"Town_12");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		assertEquals(false, graph.containsTown("Town_12"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[4]));
		assertEquals(false, graph.containsRoadConnection(town[2], town[5]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_6", roads.get(5));
		assertEquals("Road_7", roads.get(6));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[4], town[5]));
		graph.deleteRoadConnection(town[4], town[5], "Road_16");
		assertEquals(false, graph.containsRoadConnection(town[4], town[5]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		graph.deleteTown(town[2]);
		assertEquals(false, graph.containsTown("Town_2"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Town_1", roads.get(0));
		assertEquals("Town_2", roads.get(1));
		assertEquals("Town_3", roads.get(2));
		assertEquals("Town_4", roads.get(3));
		assertEquals("Town_6", roads.get(5));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[4],town[7]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_4 via Road_3 to Town_2 5 mi",path.get(0).trim());
		  assertEquals("Town_2 via Road_1 to Town_7 7 mi",path.get(1).trim());

	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(town[1],town[7]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_2 to Town_5 6 mi",path.get(0).trim());
		  assertEquals("Town_5 via Road_7 to Town_4 1 mi",path.get(1).trim());
		  assertEquals("Town_4 via Road_3 to Town_2 5 mi",path.get(2).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(town[2],town[5]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_2 via Road_3 to Town_4 5 mi",path.get(0).trim());
		  assertEquals("Town_4 via Road_7 to Town_5 1 mi",path.get(1).trim());

	}
	
}