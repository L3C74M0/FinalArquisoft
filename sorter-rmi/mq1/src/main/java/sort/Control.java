package sort;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Service;
import org.osoa.sca.annotations.Property;



@Service(Runnable.class)
public class Control<T extends Comparable<? super T>> implements Runnable{
	
	//Sorters
	@Reference(name="workers")
	private WorkersService workerService;

	@Property
	private int blockSize;


	public Control(){
		
	}
	
	@Override
	public void run() {
		Scanner scanner=new Scanner(System.in);
		int size=scanner.nextInt();
		while (size>1) {
			Comparable [] prueba= new Comparable[size];
			for (int i=0; i<size; i ++){
				prueba[i]= 1+(int)(Math.random()*1000);
			}
			sort(prueba);
			size=scanner.nextInt();
		}
	}
	
	public  void sort(Comparable[] prueba){
		int size=prueba.length;
		System.out.println("waiting for sorter ...");
		while (!workerService.isSorter());
		System.out.println("... sorter connected");
		
	    // Etapa 1: sorting
		long start = System.currentTimeMillis();
		int init=0;
	    int end= blockSize;
		ArrayList<WrapRunnable> runners=new ArrayList();
		int threads=(int)Math.ceil(size/(blockSize*1.0));
		System.out.println("threads: "+threads);
		ExecutorService executor = Executors.newFixedThreadPool(threads);
		while(threads-->0){
			if(end>size){
				end=size;
			}
			WrapRunnable runner=new WrapRunnable(workerService.getSorter(),Arrays.copyOfRange(prueba, init , end));
			try {
				Thread.sleep(1000);
				
			} catch (Exception e) {
				//TODO: handle exception
			}
			runners.add(runner);
	    	executor.execute(runner);
			init=end;
			end+=blockSize;
		}
		executor.shutdown();
		while (!executor.isTerminated()); 

		
		// //Etapa 2: merging 

		System.out.println("waiting for merger ...");
		while (!workerService.isMerger());
		System.out.println("... merger connected");
		while (runners.size()>1) {

			threads=runners.size()/2;
			executor = Executors.newFixedThreadPool(threads);
			int index=0;
			ArrayList<WrapRunnable> runnersMer=new ArrayList();
			while (threads-->0) {
				WrapRunnable slice1=runners.get(index);
				WrapRunnable slice2=runners.get(index+1);
				WrapRunnable runner=new WrapRunnable(workerService.getMerger(),slice1.getResult(),slice2.getResult());
				runnersMer.add(runner);
				executor.execute(runner);
				index+=2;
			}	
			executor.shutdown();
			while (!executor.isTerminated());
			if(runners.size()%2==1){
				runnersMer.add(runners.get(runners.size()-1));
			}
			runners=runnersMer;			
		}		
		print(runners.get(0).getResult());
		long find = (System.currentTimeMillis()-start)/1000;
		System.out.println("Tiempo total: "+(find/60)+" mn con "+(find%60)+" segundos");
	
	}
	public void print(Object[] a){
		try {
			
			File file=new File("out.txt");
			System.out.println(file.getAbsolutePath());
			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			System.out.println("size: "+a.length);
			for(int i=0; i <a.length;i++){
				bw.write(a[i] + " ");
			}
			bw.close();
			System.out.println();
		} catch (Exception e) {
			//TODO: handle exception
		}
	}	

}