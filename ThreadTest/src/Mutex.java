import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock {
	private static class Sync extends AbstractQueuedSynchronizer {
		protected boolean isHeldExclusively() {
			return getState()==1;
		}
		
		public boolean tryAcquire(int acquires) {
			if(compareAndSetState(0,1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
		
		protected boolean tryRelease(int releases) {
			if(0 == getState()) throw new IllegalMonitorStateException();
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
		
		Condition newCondition() {
			return new ConditionObject();
		}
	}

	private final Sync sync = new Sync();

	
	@Override
	public void lock() {
		sync.acquire(1);
	}
	public boolean isLocked() {
		return sync.isHeldExclusively();
	}

	public boolean hasQueuedThreads() {
		return sync.hasQueuedThreads();
	}
	
	public void lockInterruptible() throws InterruptedException {
		sync.acquire(1);
	}
	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);		
	}

	@Override
	public Condition newCondition() {	
		return sync.newCondition();
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(timeout));
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		sync.release(1);
	}
}
