import java.util.concurrent.TimeUnit;

public class Shutdown {
	public static void main(String[] args) throws InterruptedException {
		Runner one = new Runner();
		Runner two = new Runner();
		Thread countThread = new Thread(one,"Count Thread1");
			
		countThread.start();
		TimeUnit.SECONDS.sleep(1);
		countThread.interrupt();
		
		countThread = new Thread(two,"Count Thread2");
		countThread.start();
		TimeUnit.SECONDS.sleep(1);
		
		two.cancel();
		
	}
	
	private static class Runner implements Runnable {
		private long i;
		private volatile boolean on = true;
		
		@Override
		public void run() {
			while(on && !Thread.currentThread().isInterrupted()) {
				i++;
			}
			System.out.println("Count i ="+i);
		}
		public void cancel() {
			on = false;
		}
		
	}
}
