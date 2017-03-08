package car;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoClass {
	public static void main(String[] args) {
		ExecutorService exe = Executors.newFixedThreadPool(3);	
		Thread t1 = new Thread(){
				public void run() {
				for(int i =1; i <100; i++){
					System.out.println(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("hi there");
					}
				}
			}
		};
		t1.setDaemon(true);
		t1.start();
		
		exe.submit(new Engine());
		exe.submit(new Frame());
		exe.submit(new Tire());
		exe.submit(new Tire());
		for(int i = 0; i <5; i++){
			exe.submit(new Seat());
		}
		exe.submit(new Tire());
		exe.submit(new Tire());
		
		exe.shutdown();
	}
	
	
	static class Tire implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			Thread.sleep(2000);
			return 2;
		}
	}
	
	static class Seat implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			Thread.sleep(3000);
			return 3;
		}
	}
	static class Engine implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			Thread.sleep(7000);
			return 7;
		}
	}
	static class Frame implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			Thread.sleep(5000);
			return 5;
		}
	}
}
