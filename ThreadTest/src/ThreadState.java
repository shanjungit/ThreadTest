
public class ThreadState {
	public static void main(String[] args) {
		new Thread(new TimeWaiting(),"TimeWating Thread").start();
		new Thread(new Waiting(),"Waiting Thread").start();
		new Thread(new Block(),"Blocked-1").start();
		new Thread(new Block(),"Blocked-2").start();
	}
	
	static class TimeWaiting implements Runnable {
		@Override
		public void run() {
			while(true) {
				SleepUtils.second(1000);
			}			
		}		
	}
	
	static class Waiting implements Runnable {
		@Override
		public void run() {
			while(true) {
				synchronized(Waiting.class) {
					try {
						Waiting.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}		
	}	
	
	static class Block implements Runnable {
		@Override
		public void run() {			
			synchronized(Block.class) {
				SleepUtils.second(100);
			}
		}
		
	}

	
}
