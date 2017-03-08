package car;


import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.net.ssl.SSLException;

public class Main {
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		
	ExecutorService threadPool = Executors.newFixedThreadPool(3);
	//suzdavame pool s broi ni6ki
	
	CompletionService<String> pool = new ExecutorCompletionService<>(threadPool);
	CompletionService<String> pool1 = new ExecutorCompletionService<>(threadPool);
	CompletionService<String> pool2 = new ExecutorCompletionService<>(threadPool);
	ArrayList<Future<String>> futures = new ArrayList<Future<String>>(10);
	
	Car car = new Car();
	while(car.dvigatel ==0 || car.skele == 0 || car.gumi<4 || car.sedalki <5){
		 pool.submit(car);
		 pool1.submit(car);
		 pool2.submit(car);
		String result = pool.take().get();
		String result2 = pool1.take().get();
		String result3 = pool2.take().get();
		System.out.println(result);
		System.out.println(result2);
		System.out.println(result3);
		 //Compute the result
		}
		threadPool.shutdown();
		System.out.println(car.sekundi);
		System.out.println("gumi " + car.gumi + " sedalki " + car.sedalki + " Dvigatel " + car.dvigatel + " skele " + car.skele );
}
	private static class Car implements Callable<String> {
		
		int gumi =0;
		int sedalki =0;
		int dvigatel =0;
		int skele =0;
		int sekundi = 0;
		
		public String call() throws InterruptedException {
			Object obj = new Object();
			if(gumi < 4){
				gumi++;
			 	//Thread.currentThread().sleep(2000);
				synchronized(obj){
					this.sekundi +=2;
				}
			}else{
				if(sedalki < 5){
					sedalki++;
					//Thread.currentThread().sleep(3000);
					synchronized(obj){
						this.sekundi +=3;
					}
				}else{
					if(dvigatel == 0){
						dvigatel++;
						//Thread.currentThread().sleep(7000);
						synchronized(obj){
							this.sekundi +=7;
						}
					}else{
						if(skele == 0){
							skele++;
							//Thread.currentThread().sleep(5000);
							synchronized(obj){
								this.sekundi +=5;
							}
						}
					}
				}
			}
		return "gumi " + gumi + " sedalki " + sedalki + " Dvigatel " + dvigatel + " skele " + skele;
		}
	}
}
