
public class QueueOverflowException extends Exception{
	/**
	 * In provided docx, they said Queueoverflow should be called when dequeue method is called on empty queue
	 * That doesn't make sense. When you try to minus it doesn't overflow, it underflows.
	 */
	private static final long serialVersionUID = 1L;

	public QueueOverflowException(String message) {
		super(message);
	}
}
