import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<Grade> linkedGrade;
	GradeComparator comparatorGrade;
	
	public Grade a=new Grade("Class1", "Student1", 25);
	public Grade b=new Grade("Class2", "Student2", 50);
	public Grade c=new Grade("Class3", "Student3", 75);
	public Grade d=new Grade("Class4", "Student4", 100);
	public Grade e=new Grade("Class5", "Student5", 0);
	

	@Before
	public void setUp() throws Exception {
		linkedGrade = new BasicDoubleLinkedList<Grade>();
		linkedGrade.addToEnd(b);
		linkedGrade.addToEnd(c);
		linkedGrade.addToEnd(d);
		comparatorGrade = new GradeComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedGrade = null;
		comparatorGrade = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(3,linkedGrade.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals(d, linkedGrade.getLast());
		linkedGrade.addToEnd(e);
		assertEquals(e, linkedGrade.getLast());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals(b,linkedGrade.getFirst());
		linkedGrade.addToFront(a);
		assertEquals(a,linkedGrade.getFirst());
	}
	
	@Test
	public void testGetFirst() {
		assertEquals(b,linkedGrade.getFirst());
		linkedGrade.addToFront(a);
		assertEquals(a,linkedGrade.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals(d,linkedGrade.getLast());
		linkedGrade.addToEnd(e);
		assertEquals(e,linkedGrade.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<Grade> list;
		linkedGrade.addToFront(a);
		linkedGrade.addToEnd(d);
		list = linkedGrade.toArrayList();
		assertEquals(a,list.get(0));
		assertEquals(b,list.get(1));
		assertEquals(c,list.get(2));
		assertEquals(d,list.get(3));
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedGrade.addToFront(a);
		linkedGrade.addToEnd(d);
		ListIterator<Grade> iteratorCar = linkedGrade.iterator();
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(a, iteratorCar.next());
		assertEquals(b, iteratorCar.next());
		assertEquals(c, iteratorCar.next());
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(d, iteratorCar.next());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedGrade.addToFront(a);
		linkedGrade.addToEnd(d);
		ListIterator<Grade> iteratorCar = linkedGrade.iterator();
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(a, iteratorCar.next());
		assertEquals(b, iteratorCar.next());
		assertEquals(c, iteratorCar.next());
		assertEquals(d, iteratorCar.next());
		assertEquals(true, iteratorCar.hasPrevious());
		assertEquals(d, iteratorCar.previous());
		assertEquals(c, iteratorCar.previous());
		assertEquals(b, iteratorCar.previous());
		assertEquals(a, iteratorCar.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedGrade.addToFront(a);
		linkedGrade.addToEnd(e);
		ListIterator<Grade> iteratorGrade = linkedGrade.iterator();		
		assertEquals(true, iteratorGrade.hasNext());
		assertEquals(a, iteratorGrade.next());
		assertEquals(b, iteratorGrade.next());
		assertEquals(c, iteratorGrade.next());
		assertEquals(d, iteratorGrade.next());
		assertEquals(true, iteratorGrade.hasNext());
		assertEquals(e, iteratorGrade.next());
		
		try{
			//no more elements in list
			iteratorGrade.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedGrade.addToFront(a);
		linkedGrade.addToEnd(d);
		ListIterator<Grade> iteratorCar = linkedGrade.iterator();		
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(a, iteratorCar.next());
		assertEquals(b, iteratorCar.next());
		assertEquals(c, iteratorCar.next());
		assertEquals(d, iteratorCar.next());
		assertEquals(true, iteratorCar.hasPrevious());
		assertEquals(d, iteratorCar.previous());
		assertEquals(c, iteratorCar.previous());
		assertEquals(b, iteratorCar.previous());
		assertEquals(a, iteratorCar.previous());
		
		try{
			//no more elements in list
			iteratorCar.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedGrade.addToFront(a);
		linkedGrade.addToEnd(d);
		ListIterator<Grade> iteratorCar = linkedGrade.iterator();		
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(a, iteratorCar.next());
		assertEquals(b, iteratorCar.next());
		assertEquals(c, iteratorCar.next());
		assertEquals(true, iteratorCar.hasNext());
		assertEquals(d, iteratorCar.next());
		
		try{
			//remove is not supported for the iterator
			iteratorCar.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}
	
	@Test
	public void testRemove() {
		// remove the first
		assertEquals(b, linkedGrade.getFirst());
		assertEquals(d, linkedGrade.getLast());
		linkedGrade.addToFront(a);
		assertEquals(a, linkedGrade.getFirst());
		linkedGrade.remove(a, comparatorGrade);
		assertEquals(b, linkedGrade.getFirst());
		//remove from the end of the list
		linkedGrade.addToEnd(d);
		assertEquals(d, linkedGrade.getLast());
		linkedGrade.remove(d, comparatorGrade);
		assertEquals(d, linkedGrade.getLast());
		//remove from middle of list
		linkedGrade.addToFront(a);
		assertEquals(a, linkedGrade.getFirst());
		assertEquals(d, linkedGrade.getLast());
		linkedGrade.remove(b, comparatorGrade);
		assertEquals(a, linkedGrade.getFirst());
		assertEquals(d, linkedGrade.getLast());
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(b, linkedGrade.getFirst());
		linkedGrade.addToFront(a);
		assertEquals(a, linkedGrade.getFirst());
		assertEquals(a, linkedGrade.retrieveFirstElement());
		assertEquals(b,linkedGrade.getFirst());
		assertEquals(b, linkedGrade.retrieveFirstElement());
		assertEquals(c,linkedGrade.getFirst());
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(d, linkedGrade.getLast());
		linkedGrade.addToEnd(d);
		assertEquals(d, linkedGrade.getLast());
		assertEquals(d, linkedGrade.retrieveLastElement());
		assertEquals(d,linkedGrade.getLast());
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class GradeComparator implements Comparator<Grade>
	{

		@Override
		public int compare(Grade arg0, Grade arg1) {
			// Just put cars in alphabetic order by make
			return arg0.toString().compareTo(arg1.toString());
		}
		
	}
	
	private class Grade{
		String className;
		String student;
		int grade;
		
		public Grade(String className, String student, int grade){
			this.className = className;
			this.student = student;
			this.grade = grade;
		}
		
		public String getClassName(){
			return className;
		}
		public String getStudent(){
			return student;
		}
		public int getGrade(){
			return grade;
		}
		
		public String toString() {
			return (getClassName()+" "+getStudent()+" "+getGrade());
		}
	}
}