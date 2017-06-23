import java.util.concurrent.TimeUnit;

public class Interrupted {
	
	public static void main(String[] args) throws InterruptedException {
		Thread sleepThread = new Thread(new SleepRunner(),"SleepThread");
		Thread busyThread = new Thread(new BusyRunner(),"BusyThread");		
		sleepThread.setDaemon(true);
		busyThread.setDaemon(true);
		
		sleepThread.start();
		busyThread.start();
		
		TimeUnit.SECONDS.sleep(3);
		
		sleepThread.interrupt();
		busyThread.interrupt();
		
		System.out.println("SleepThread interrupt is: "+sleepThread.isInterrupted()+","+sleepThread.getState());
		System.out.println("Busy Thread interrupt is: "+busyThread.isInterrupted()+","+busyThread.getState());
		
		SleepUtils.second(2);
	}		
	static class SleepRunner implements Runnable {
		@Override
		public void run() {
			while(true) {
				SleepUtils.second(10);
			}			
		}
		
	}
	
	static class BusyRunner implements Runnable {
		@Override
		public void run() {
			while(true) {
				
			}
		}
		
	}
	
}
