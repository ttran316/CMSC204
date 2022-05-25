import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradebookTester {
	private GradeBook test, test2;
	
	@Before
	public void setUp() throws Exception{
		test = new GradeBook(5);
		test2 = new GradeBook(5);
		test.addScore(50);
		test.addScore(70);
		test.addScore(90);
		test2.addScore(60);
		test2.addScore(80);
		test2.addScore(20);
	}
	
	@After
	public void tearDown() throws Exception{
		test = null;
		test2 = null;
	}
	
	@Test
	public void testAddScore() {
		assertTrue(test.toString().equals("50.0 70.0 90.0"));
		assertEquals(3, test.getScoreSize());
		assertTrue(test2.toString().equals("60.0 80.0 20.0"));
		assertEquals(3, test.getScoreSize());
	}
	
	@Test
	public void testSum() {
		assertEquals(210, test.sum(), 0.01);
		assertEquals(160, test2.sum(), 0.01);
	}
	
	@Test
	public void testMinimum() {
		assertEquals(50, test.minimum(), 0.01);
		assertEquals(20, test2.minimum(), 0.01);
	}
	
	@Test
	public void testFinalScore() {
		assertEquals(160, test.finalScore(), 0.01);
		assertEquals(140, test2.finalScore(), 0.01);
	}
}
