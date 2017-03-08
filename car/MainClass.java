package car;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainClass{
	static int count = -1;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of threads: ");
		int input = sc.nextInt();
		sc.close();
		File voina = new File("Peter.txt");
		someMagic(input, voina);
		
		System.out.println("Start!");
		
		ExecutorService exe = Executors.newFixedThreadPool(input);
		while(count<= input-2){
			count++;
			File f = new File("File" + count + ".txt");
			Runnable run = new Runnable() {
				@Override
				public void run() {
					int b = 0;
					if(f.exists()){
						try (FileReader fileRead2 = new FileReader(f)){
							System.out.println(f.getName());
							while(b != -1){
								b = fileRead2.read();
							}		
						} catch (FileNotFoundException e) {
							System.out.println("no file");
						} catch (IOException e) {
							System.out.println(e);
						}
					}
				}
			};
			exe.execute(run);
		}
		exe.shutdown();
		System.out.println("End!");
	}
	
	
	static void someMagic(int input, File peter){
		System.out.println(peter.length());
		FileWriter filewrite = null;
		try (FileReader fileread = new FileReader(peter)){
			for(int i = 0; i<input; i++){
				System.out.print("This is " +(i+1) +" substring. " );
				File f = new File("File" + i + ".txt");
				f.createNewFile();
				filewrite = new FileWriter(f);
				char b = 0;
				for(int j =0; j <= peter.length()/input+1; j++){
					try {
						b = (char)fileread.read();
						filewrite.append(b);
					} catch (IOException e) {
						System.out.println("exeption");
					}
				}
				System.out.println("size: " + f.length() + " characters");
			}
		} catch (FileNotFoundException e1) {
			System.out.println("file not fount");
		} catch (IOException e1) {
			System.out.println(e1);
		}finally {
			try {
				filewrite.close();
			} catch (IOException e) {
				System.out.println("close what?");
			}
		}
	}
}
