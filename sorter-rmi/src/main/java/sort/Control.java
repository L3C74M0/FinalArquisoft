package sort;


import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Service;
@Service(Runnable.class)

public class Control<T extends Comparable<? super T>> implements Runnable{
	
	//private Sorter [] sorters;
	//private int nodes;
	
        @Reference (required=true)
	protected Sorter s1;

        @Reference (required=true)
	protected Sorter s2;
    
        @Reference (required=true)
	protected Sorter s3;
   
        @Reference (required=true)
	protected Sorter s4;
   
        @Reference (required=true)
	protected Sorter s5;
   
        @Reference (required=true)
	protected Sorter s6;
   
        @Reference (required=true)
	protected Sorter s7;
   
        @Reference (required=true)
	protected Sorter s8;
	
        @Reference (required=true)
	protected Merger m1;
	
        @Reference (required=true)
	protected Merger m2;
	
        @Reference (required=true)
	protected Merger m3;
	
        @Reference (required=true)
	protected Merger m4;
	
        @Reference (required=true)
	protected Merger m5;
	
        @Reference (required=true)
	protected Merger m6;
	
        @Reference (required=true)
	protected Merger m7;

	
	
    


	public Control(){
		System.out.println("control started");
	}
	
	// }
	public  void run(){
	
		
		int size =100;
		Comparable [] prueba= new Comparable[size];
		for (int i=0; i<size; i ++){
			prueba[i]= 1 + (int)(Math.random() * ((1000 - 1) + 1));
			
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(8);
	


	     //Etapa 1: sorting
	    int gap= size/8;
	    
	    s1.setArray(Arrays.copyOfRange(prueba, 0 , gap));
	    executor.execute(s1);
	    
	    
	    s2.setArray(Arrays.copyOfRange(prueba, gap , gap*2));
	    executor.execute(s2);
	    
	    s3.setArray(Arrays.copyOfRange(prueba, gap*2, gap*3));
	    executor.execute(s3);
	    
	    s4.setArray(Arrays.copyOfRange(prueba, gap*3 , gap*4));
	    executor.execute(s4);
	    
	    s5.setArray(Arrays.copyOfRange(prueba, gap*4 , gap*5));
	    executor.execute(s5);
	    
	    s6.setArray(Arrays.copyOfRange(prueba, gap*5 , gap*6));
	    executor.execute(s6);
	    
	    s7.setArray(Arrays.copyOfRange(prueba, gap*6 , gap*7));
	    executor.execute(s7);
	    
	    s8.setArray(Arrays.copyOfRange(prueba, gap*7 , size));
	    executor.execute(s8);
	    // This will make the executor accept no new threads
	    // and finish all existing threads in the queue
	    executor.shutdown();
	    // Wait until all threads are finish
	    while (!executor.isTerminated()) {

	    }

	   
	    executor = Executors.newFixedThreadPool(4);
	    //Etapa 2: merging 1 nivel

	    m1.setA(s1.getArray());
	    m1.setB(s2.getArray());
	    executor.execute(m1);	    

	    m2.setA(s3.getArray());
	    m2.setB(s4.getArray());
	    executor.execute(m2);
	    
	    m3.setA(s5.getArray());
	    m3.setB(s6.getArray());
	    executor.execute(m3);
	    
	    m4.setA(s7.getArray());
	    m4.setB(s8.getArray());
	    executor.execute(m4);
	    
	    //cierre y sincronizacion
	    executor.shutdown();
	    while (!executor.isTerminated()) {

	    }
	    //Etapa 3: merging 2 nivel
	    
	    executor = Executors.newFixedThreadPool(2);
	   
	   
	    m5.setA(m1.getArray());
	    m5.setB(m2.getArray());
	    executor.execute(m5);

	
	    m6.setA(m3.getArray());
	    m6.setB(m4.getArray());
	    executor.execute(m6);
	    //cierre y sincronizacion
	    executor.shutdown();
	    while (!executor.isTerminated()) {

	    }
	    
	    

	    
	    //Etapa 4: merging 3 nivel
	    
	    
	    m7.setA(m5.getArray());
	    m7.setB(m6.getArray());
	    m7.merge();
	    
		System.out.println("ordenado");
		print(m7.getArray());
		
	
	}
	public void print(Object[] a){
		for(int i=0; i <a.length;i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	public void startSorting(){
		
	}
	

}