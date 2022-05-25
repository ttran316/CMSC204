import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T>{
	Node head, tail;
	int size;
	
	public BasicDoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public class Node{
		T data;
		Node back, next;
		
		public Node(T data) {
			this.back = null;
			this.next = null;
			this.data = data;
		}
	}
	
	public BasicDoubleLinkedList<T> addToEnd(T data){
		Node temp = new Node(data);
		if(size == 0) {
			head = temp;
			tail = head;
		}else {
			tail.next = temp;
			temp.back = tail;
			tail = temp;
		}
		size++;
		return this;
	}
	
	public BasicDoubleLinkedList<T> addToFront(T data){
		Node temp = new Node(data);
		if(size == 0) {
			head = temp;
			tail = head;
		}else {
			head.back = temp;
			temp.next = head;
			head = temp;
		}
		size++;
		return this;
	}
	
	public T getFirst() {
		if(size == 0) {
			return null;
		}
		return head.data;
	}
	
	public T getLast() {
		if(size == 0) {
			return null;
		}
		return tail.data;
	}
	
	public int getSize() {
		return size;
	}
	
	public ListIterator<T> iterator() {
		return new iteratorInner();
	}
	
	public class iteratorInner implements ListIterator<T>{
		Node element = head;
		Node old;
		
		@Override
		public T next() throws NoSuchElementException{
			if(element != null) {
				old = element;
				element = element.next;
				return old.data;
			}else {
				throw new NoSuchElementException("NoSuchElementException");
			}
		}
		
		@Override
		public T previous() {
			if(old != null) {
				element = old;
				old = old.back;
				return element.data;
			}else {
				throw new NoSuchElementException("NoSuchElementException");
			}
		}

		@Override
		public boolean hasNext() {
			if(element != null) {
				return true;
			}
			return false;
		}


		@Override
		public boolean hasPrevious() {
			if(old != null) {
				return true;
			}
			return false;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}


		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}


		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}


		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();
		}


		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
		}
	}
	
	
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator){
		Node temp = head;
		while(temp != null) {
			if(comparator.compare(targetData, temp.data) == 0) {
				if(temp == head) {
					head = head.next;
				}else if(temp == tail) {
					tail = tail.back;
				}else {
					temp.back.next = temp.next;
				}
				size--;
				return this;
			}
			temp = temp.next;
		}
		return this;
	}
	
	public T retrieveFirstElement() {
		T result = head.data;
		if(size == 0) {
			return null;
		}else if(size == 1) {
			head = null;
			tail = null;
		}else {
			head = head.next;
			head.back = null;
		}
		size--;
		return result;
	}
	
	public T retrieveLastElement() {
		T result = tail.data;
		if(size == 0) {
			return null;
		}else if(size == 1) {
			head = null;
			tail = null;
		}else {
			tail = tail.back;
			tail.next = null;
		}
		size--;
		return result;
	}
	
	public ArrayList<T> toArrayList(){
		ArrayList<T> list = new ArrayList<>();
		Node temp = head;
		
		while(temp != null) {
			list.add(temp.data);
			temp = temp.next;
		}
		return list;
	}
}
