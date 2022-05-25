import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import BasicDoubleLinkedList_STUDENT_Test.Grade;



public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<Grade> sortedLinkedGrade;
	GradeComparator comparatorGrade;
	
	public Grade a=new Grade("Class1", "Student1", 25);
	public Grade b=new Grade("Class2", "Student2", 50);
	public Grade c=new Grade("Class3", "Student3", 75);
	public Grade d=new Grade("Class4", "Student4", 100);
	public Grade e=new Grade("Class5", "Student5", 0);
	public Grade f=new Grade("Class6", "Student6", 65);
	
	@Before
	public void setUp() throws Exception {
		comparatorGrade = new GradeComparator();
		sortedLinkedGrade = new SortedDoubleLinkedList<Grade>(comparatorGrade);
		
	}

	@After
	public void tearDown() throws Exception {
		comparatorGrade = null;
		sortedLinkedGrade = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedGrade.addToEnd(f);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedGrade.addToFront(a);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		sortedLinkedGrade.add(a);
		sortedLinkedGrade.add(b);
		sortedLinkedGrade.add(c);
		sortedLinkedGrade.add(d);
		ListIterator<Grade> iterator = sortedLinkedGrade.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(a, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulGradePrevious() {
		sortedLinkedGrade.add(e);
		sortedLinkedGrade.add(c);
		sortedLinkedGrade.add(b);
		sortedLinkedGrade.add(d);
		//ArrayList<Car> carList = sortedLinkedCar.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Grade> iterator = sortedLinkedGrade.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(e, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(e, iterator.previous());
		assertEquals(d, iterator.previous());
		assertEquals(c, iterator.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedGrade.add(e);
		sortedLinkedGrade.add(c);
		sortedLinkedGrade.add(b);
		sortedLinkedGrade.add(d);
		//ArrayList<Car> carList = sortedLinkedCar.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Grade> iterator = sortedLinkedGrade.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		try{
			//no more elements in list
			iterator.next();
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
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedGrade.add(e);
		sortedLinkedGrade.add(c);
		sortedLinkedGrade.add(b);
		sortedLinkedGrade.add(d);
		//ArrayList<Car> carList = sortedLinkedCar.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Grade> iterator = sortedLinkedGrade.iterator();
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
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
	public void testAddGrade() {
		//alphabetic order: e f a c b d
		sortedLinkedGrade.add(a);
		sortedLinkedGrade.add(b);
		sortedLinkedGrade.add(c);
		assertEquals(a, sortedLinkedGrade.getFirst());
		assertEquals(c, sortedLinkedGrade.getLast());
		sortedLinkedGrade.add(d);
		sortedLinkedGrade.add(e);
		assertEquals(a, sortedLinkedGrade.getFirst());
		assertEquals(e, sortedLinkedGrade.getLast());
		//deletes Elephant from linked list
		assertEquals(e,sortedLinkedGrade.retrieveLastElement());
		assertEquals(d, sortedLinkedGrade.getLast());
	}

	@Test
	public void testRemoveFirstGrade() {
		//alphabetic order: e f a c b d
		sortedLinkedGrade.add(b);
		sortedLinkedGrade.add(c);
		assertEquals(b, sortedLinkedGrade.getFirst());
		assertEquals(c, sortedLinkedGrade.getLast());
		sortedLinkedGrade.add(a);
		assertEquals(a, sortedLinkedGrade.getFirst());
		// remove the first
		sortedLinkedGrade.remove(a, comparatorGrade);
		assertEquals(b, sortedLinkedGrade.getFirst());
	}
	
	@Test
	public void testRemoveEndGrade() {
		//alphabetic order: e f a c b d
		sortedLinkedGrade.add(b);
		sortedLinkedGrade.add(f);
		assertEquals(b, sortedLinkedGrade.getFirst());
		assertEquals(f, sortedLinkedGrade.getLast());
		sortedLinkedGrade.add(d);
		assertEquals(f, sortedLinkedGrade.getLast());
		//remove from the end of the list
		sortedLinkedGrade.remove(d, comparatorGrade);
		assertEquals(f, sortedLinkedGrade.getLast());
	}

	@Test
	public void testRemoveMiddleGrade() {
		//alphabetic order: e f a c b d
		sortedLinkedGrade.add(a);
		sortedLinkedGrade.add(b);
		assertEquals(a, sortedLinkedGrade.getFirst());
		assertEquals(b, sortedLinkedGrade.getLast());
		sortedLinkedGrade.add(f);
		assertEquals(a, sortedLinkedGrade.getFirst());
		assertEquals(f, sortedLinkedGrade.getLast());
		assertEquals(3,sortedLinkedGrade.getSize());
		//remove from middle of list
		sortedLinkedGrade.remove(a, comparatorGrade);
		assertEquals(b, sortedLinkedGrade.getFirst());
		assertEquals(f, sortedLinkedGrade.getLast());
		assertEquals(2,sortedLinkedGrade.getSize());
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
			return arg0.getClassName().compareTo(arg1.getClassName());
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