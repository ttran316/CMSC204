import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {
	int newStart = 0;
	private Object[] queue;
	private int length, filled;
	
	public NotationQueue() {
		length = 20;
		queue = new Object[length];
	}
	
	public NotationQueue(int length) {
		this.length = length;
		this.filled = 0;
		queue = new Object[length];
	}
	
	public int size() {
		return filled;
	}
	
	public boolean isEmpty() {
		if(filled == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isFull() {
		if(length == filled) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean enqueue(T t) throws QueueOverflowException{
		if(isFull()) {
			throw new QueueOverflowException("Queue is full.");
		}
		queue[size()] = t;
		filled++;
		return true;
	}
	
	public T dequeue() throws QueueUnderflowException{
		if(isEmpty()) {
			throw new QueueUnderflowException("Queue is empty.");
		}
		T first = (T) queue[newStart];
		queue[newStart] = null;
		filled--;
		newStart++;
		return first;
	}
	
	public void fill(ArrayList<T> list) {
		ArrayList<T> array = new ArrayList<T>(list);
		array.forEach(T -> 
		{
			try {
				enqueue(T);
			}catch(QueueOverflowException e) {
				e.getMessage();
			}
			
		});
		
	}

	public String toString() {
		String result = "";
		for(int i = 0; i < filled; i++) {
			result += queue[i];
		}
		return result;
	}
	
	public String toString(String delimiter) {
		String result = "";
		for(int i = 0; i < filled; i++) {
			if(i == filled-1) {
				result += queue[i];
			}else {
				result += queue[i] + delimiter;
			}
		}
		return result;
	}

}
