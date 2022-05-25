import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
	private Road road1, road2, road3;
	private Town town1, town2, town3;
	
	@Before
	public void setUp() throws Exception{
		town1 = new Town("Town_1");
		town2 = new Town("Town_2");
		town3 = new Town("Town_3");
		road1 = new Road(town1, town2, "Road_1");
		road2 = new Road(town2, town3, "Road_2");
		road3 = new Road(town3, town1, "Road_3");
	}
	
	@After
	public void tearDown() throws Exception{
		road1 =road2=road3=null;
		town1=town2=town3=null;
	}
	
	@Test
	public void testCompareTo(){
		assertEquals(0,road1.compareTo(road1));
		assertEquals(-1, road1.compareTo(road2));
		assertEquals(-2, road1.compareTo(road3));
	}
	
	@Test
	public void testEquals(){
		assertTrue(road1.equals(road1));
		assertTrue(road2.equals(road2));
		assertTrue(road3.equals(road3));
	}
	
	@Test
	public void testContains(){
		assertTrue(road1.contains(town1));
		assertFalse(road2.contains(town1));
		assertTrue(road3.contains(town1));
	}
	
	@Test
	public void testGetPointA(){
		assertEquals(town1,road1.getPointA());
		assertEquals(town2,road2.getPointA());
	}
	
	@Test
	public void testGetPointB(){
		assertEquals(town3,road2.getPointB());
		assertEquals(town2,road1.getPointB());
	}
	
	@Test
	public void testGetDist(){
		assertEquals(1,road1.getDist());
		assertEquals(10, new Road(town1, town2, 10, "Road_4").getDist());
	}
	
	@Test
	public void testGetName(){
		assertEquals("Road_1", road1.getName());
		assertEquals("Road_3", road3.getName());
	}
	
	@Test
	public void testToString(){
		assertEquals("Town_1 via Road_1 to Town_2 1 mi", road1.toString());
		assertEquals("Town_2 via Road_2 to Town_3 1 mi", road2.toString());
	}
}
