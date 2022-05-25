import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {
	private Town town1,town2,town3;
	
	@Before
	public void setUp() throws Exception{
		town1 = new Town("Town_1");
		town2 = new Town("Town_2");
		town3 = new Town("Town_3");
	}
	
	@After
	public void tearDown() throws Exception{
		town1=town2=town3=null;
	}
	
	@Test
	public void testGetName() {
		assertEquals("Town_1", town1.getName());
		assertEquals("Town_2", town2.getName());
		assertEquals("Town_3", town3.getName());
	}
	
	@Test
	public void testSetName() {
		assertEquals("Town_1", town1.getName());
		town1.setName("Town_4");
		assertEquals("Town_4", town1.getName());
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, town1.compareTo(town1));
		assertEquals(-1, town1.compareTo(town2));
		assertEquals(-2, town1.compareTo(town3));
	}
	
	@Test
	public void testEquals() {
		assertTrue(town1.equals(town1));
		assertTrue(town2.equals(town2));
		assertTrue(town3.equals(town3));
	}
	
	@Test
	public void testHashCode() {
		assertEquals(town1.hashCode(), new Town("Town_1").hashCode());
		assertEquals(town2.hashCode(), new Town("Town_2").hashCode());
		assertEquals(town3.hashCode(), new Town("Town_3").hashCode());
	}
	
	@Test
	public void testToString() {
		assertEquals("Town name is Town_1", town1.toString());
		assertEquals("Town name is Town_2", town2.toString());
	}
}
