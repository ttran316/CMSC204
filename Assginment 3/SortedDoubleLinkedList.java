import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	Comparator<T> comparator;
	
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	public SortedDoubleLinkedList<T> add (T data){
		Node temp = new Node(data);
		Node element = head;
		
		if(size == 0) {
			head = temp;
			tail = head;
			size++;
			return this;
		} else if(comparator.compare(head.data, data) > 0) {
			temp.next = head;
			head.back = temp;
			head = temp;
			size++;
			return this;
		}else {
			while(comparator.compare(element.data,data) < 0){
				if(element.next == null) {
					element.next = temp;
					temp.back = element;
					tail = temp;
					size++;
					return this;
				}else {
					element = element.next;
				}
			}
			element.back.next = temp;
			temp.back = element.back;
			element.back = temp;
			temp.next = element;
			size++;
			return this;
		}	
	}
	
	public BasicDoubleLinkedList<T> addToEnd(T data){
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	public BasicDoubleLinkedList<T> addToFront(T data){
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	public ListIterator<T> iterator(){
		return super.iterator();
	}
	
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator){
		BasicDoubleLinkedList<T> result = super.remove(data, comparator);
		return (SortedDoubleLinkedList<T>)result;
	}
}
