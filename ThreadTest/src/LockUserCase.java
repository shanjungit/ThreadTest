import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUserCase {
	
	public static void main(String[] args) {
		Lock locker = new ReentrantLock();
		locker.lock();
		try{
			
		}finally {
			locker.unlock();
		}
	}
	
}
