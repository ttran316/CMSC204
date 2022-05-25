import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T> {
	private Object[] queue;
	private int length, filled;
	
	public NotationStack() {
		length = 20;
		queue = new Object[length];
	}
	
	public NotationStack(int length) {
		this.length = length;
		this.filled = 0;
		queue = new Object[length];
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

	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException("Queue is empty.");
		}
		T first = (T) queue[filled-1];
		filled--;
		return first;
	}

	public T top() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException("Queue is empty.");
		}
		T first = (T) queue[filled-1];
		return first;
	}

	public int size() {
		return filled;
	}

	public boolean push(T e) throws StackOverflowException {
		if(isFull()) {
			throw new StackOverflowException("Queue is full.");
		}
		queue[size()] = e;
		filled++;
		return true;
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

	public void fill(ArrayList<T> list) {
		ArrayList<T> array = new ArrayList<T>(list);
		array.forEach(T -> 
		{
			try {
				push(T);
			}catch(StackOverflowException e) {
				e.getMessage();
			}
			
		});
		
	}
	
}
