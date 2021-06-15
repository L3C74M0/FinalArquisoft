package sort;


import java.util.ArrayList;
import java.util.Arrays;

import org.osoa.sca.annotations.Scope;
import org.osoa.sca.annotations.Property;
@Scope("CONVERSATION")

public class SortNode<T extends Comparable<? super T>> implements Sorter <T> ,Runnable
{
	public final static int EMPTY=1;
	public final static int SORTING=2;
	public final static int FREE=3;
	public final static int BIG=100;
	
	private int state;


	@Property
	public  T[] array;
	
	// @Reference (required=true)	
	//public Sorter next;
	
	
	public SortNode(){
		state=EMPTY;
		System.out.println("sorter started");
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
		
		//Arrays.sort(array);
	}

	@Override
	public T[] sort(T[] array) {
		for (T t : array) {
			System.out.print(t.toString()+" ");
		}
		Arrays.sort(array);
		return array;
	}

}