package sort;


import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.rmi.*;
import org.osoa.sca.annotations.*;

@Scope("CONVERSATION")
@Service(Runnable.class)
public class SortNode<T extends Comparable<? super T>> extends UnicastRemoteObject implements Sorter <T> ,Runnable
{
	public final static int EMPTY=1;
	public final static int SORTING=2;
	public final static int FREE=3;
	public final static int BIG=100;
	
	private int state;

	private Subject subject;

	@Property
	private String myServiceSorter;
	@Property
	private String myServiceMerger;

	/**
	 * @param subject the subject to set
	 */
	@Reference(name="subject")
	public void setSubject(Subject sub) {
		this.subject = sub;
	}


	@Property
	public  T[] array;
	
	// @Reference (required=true)	
	//public Sorter next;
	
	
	public SortNode()throws RemoteException{
		state=EMPTY;
		System.out.println("sorter 2 started");
	}


	@Override
	public void setState(int s) {
		// TODO Auto-generated method stub
		state=s;
	}

	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return state;
	}

	public T[] getArray(){
		return array;
	}
	
	public void setArray(T[] a) {
		// TODO Auto-generated method stub
		array=a;
	}

	@Override
	public void run() {
		System.out.println("Conecting sorter");
		subject.attachSorter(myServiceSorter);
		System.out.println("Conected sorter");
	}
	@Override
	public T[] sort(T[] array) throws RemoteException {
		print(array);
		Arrays.sort(array);
		return array;
	}

	public synchronized void print(T[] array){
		System.out.println("sorting ..."+array.length);
		for (T t : array) {
			System.out.print(t.toString()+" ");
		}
		System.out.println("end Array");

	}


}