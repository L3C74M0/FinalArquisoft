package sort;

import java.util.LinkedList;
import java.rmi.*;

public class SubjectImpl implements Subject, WorkersService{

    
	private static LinkedList<String> workersMergerUris=new LinkedList();
	private static LinkedList<String> workersSorterUris=new LinkedList();

	private static LinkedList<Merger> workersMerger=new LinkedList();
	private static LinkedList<Sorter> workersSorter=new LinkedList();


	public synchronized void attachMerger(String uri){
        try {
            Merger merger=(Merger)Naming.lookup(uri);
            System.out.println("merger: "+uri);
            workersMergerUris.add(uri);
            workersMerger.add(merger);
            
        } catch (Exception e) {
            System.out.println("error al hacer binding: "+uri);
            e.printStackTrace();
        }
	
	}

    public synchronized void attachSorter(String uri){
        try {
            Sorter sorter=(Sorter)Naming.lookup(uri);
            System.out.println("sorter :"+uri);
            workersSorter.add(sorter);
            workersSorterUris.add(uri);
            
        } catch (Exception e) {
            System.out.println("error al hacer binding: "+uri);
            e.printStackTrace();
        }


	}
    public synchronized void detachSorter(String uri){
		int index=workersSorterUris.indexOf(uri);
		String uriRemoved=workersSorterUris.remove(index);
		workersSorter.remove(index);
		assert(uri.equals(uriRemoved));	
	}
    
    public synchronized void detachMerger(String uri){
		int index=workersMergerUris.indexOf(uri);
    	String uriRemoved=workersMergerUris.remove(index);
		workersMerger.remove(index);
		assert(uri.equals(uriRemoved));
		
	}

    public Sorter getSorter(){
        int size=workersSorter.size();
        if(size>0){
            Sorter ret=workersSorter.poll();
            workersSorterUris.add(workersSorterUris.poll());
            workersSorter.add(ret);
            return ret;
        }
        return null;
    }
    public Merger getMerger(){
        int size=workersMerger.size();
        if(size>0){
            Merger ret=workersMerger.poll();
            workersMergerUris.add(workersMergerUris.poll());
            workersMerger.add(ret);
            return ret;
        }
        return null;
    }

    public boolean isSorter(){
        return workersSorter.size()>0;
    }
    public boolean isMerger(){
        return workersMerger.size()>0;
    }
    public int getSorters(){
        return workersSorter.size();
    }
    public int getMergers(){
        return workersMerger.size();
    }
}
