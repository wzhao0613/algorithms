import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
	private AtomicReference<Thread> owner = new AtomicReference<>();
	private int count = 0;
	
	public void lock() {
		Thread current = Thread.currentThread();
		while (!owner.compareAndSet(null, current)) {
			//void
		}
		
	}
	public void unlock() {
		Thread current = Thread.currentThread();
		owner.compareAndSet(current, null);
		//System.out.println("unlock done!");
	}
	
	public void reentrantLock() {
		Thread current = Thread.currentThread();
		if(current == owner.get())
		{
			count ++;
			return;
		}
		while( !owner.compareAndSet(null, current)) {
			//void
		}
	}
	public void reentrantUnLock() {
		Thread current = Thread.currentThread();
		if(current == owner.get()){
			if(count != 0) {
				count --;
			}
			else {
				owner.compareAndSet(current, null);
			}
		}
	}
}
