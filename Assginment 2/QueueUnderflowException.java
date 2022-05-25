
public class QueueUnderflowException extends Exception{
	/**
	 * In provided docx, they said Queueunderflow should be called when enqueue method is called on filled queue
	 * That doesn't make sense. When you try to add it doesn't underflow, it overflows.
	 */
	private static final long serialVersionUID = 1L;

	public QueueUnderflowException(String message) {
		super(message);
	}
}
