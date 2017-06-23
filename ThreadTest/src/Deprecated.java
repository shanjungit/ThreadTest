import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Deprecated {
	public static void main(String[] args) throws InterruptedException {
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		Thread printThread = new Thread(new Runner(),"PrintThread");
		printThread.setDaemon(true);
		printThread.start();
		TimeUnit.SECONDS.sleep(3);
		printThread.suspend();
		System.out.println("Main suspend PrintThread at "+format.format(new Date()));
		TimeUnit.SECONDS.sleep(3);
		printThread.resume();
		System.out.println("Main resume PrintThread at "+format.format(new Date()));
		TimeUnit.SECONDS.sleep(3);
		printThread.stop();
		System.out.println("Main stopped PrintThread at "+format.format(new Date()));
		TimeUnit.SECONDS.sleep(3);
	}
	
	static class Runner implements Runnable {

		@Override
		public void run() {
			DateFormat format = new SimpleDateFormat("HH:MM:SS");
			while(true) {
				System.out.println(Thread.currentThread().getName()+" Run at "+format.format(new Date()));
				SleepUtils.second(1);
			}

		}		
	}
}
