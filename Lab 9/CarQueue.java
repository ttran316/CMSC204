import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue {
	Random rand;
	Queue<Integer> direction;
	
	public CarQueue() {
		direction = new LinkedList<>();
		rand = new Random();
		direction.add(0);
		direction.add(1);
		direction.add(2);
		direction.add(3);
		direction.add(0);
	}
	
	public void addToQueue() {
		class Randomss implements Runnable{
			public void run() {
				try {
					for(int i = 0; i < 1000; i++) {
						int x = rand.nextInt(4);
						direction.add(x);
						Thread.sleep(50);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		Runnable x = new Randomss();
		Thread thrd = new Thread(x);
		thrd.start();
	}
	
	public int deleteQueue() {
		return direction.remove();
	}
}
