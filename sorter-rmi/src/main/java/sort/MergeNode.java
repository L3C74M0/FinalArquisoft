package sort;

import org.osoa.sca.annotations.Scope;
import org.osoa.sca.annotations.Property;
@Scope("CONVERSATION")
public class MergeNode <T extends Comparable<? super T>> implements Merger <T> ,Runnable {
	
	@Property
	public  T[] array;
	@Property
	public  T[] A;
	@Property
	public  T[] B;
	
	
	
	public MergeNode(){
		System.out.println("Starting mergenode");
	}
	public  void run() {
		int maxA=A.length;
		int maxB=B.length; 
		int size= maxA+maxB; 
		//ArrayList<T> nuevo = new ArrayList<T>();
		array= (T[]) new Comparable[size];
		int a,b,c;
		for(a=0, b=0,c=0; c<size; c++ ){
			if (a>=maxA){
				array[c]=B[b];
				b++;
			}
			else if(b>=maxB){
				array[c]=A[a];
				a++;
			}else{
				int val= A[a].compareTo(B[b]);
				if(val<=0){
					array[c]=A[a];
					a++;
				}else{
					array[c]=B[b];
					b++;
				}
			}
			
		}

	}


	@Override
	public T[] getArray() {
		return array;
	}

	@Override
	public void setArray(T[] a) {
		array=a;
		
	}

	@Override
	public T[] getA() {
		return A;
	}

	@Override
	public void setA(T[] a) {
		A=a;
	}

	@Override
	public T[] getB() {
		return B;
	}

	@Override
	public void setB(T[] b) {
		B=b;
	}
	@Override
	public void merge() {
		int maxA=A.length;
		int maxB=B.length; 
		int size= maxA+maxB; 
		//ArrayList<T> nuevo = new ArrayList<T>();
		array= (T[]) new Comparable[size];
		int a,b,c;
		for(a=0, b=0,c=0; c<size; c++ ){
			if (a>=maxA){
				array[c]=B[b];
				b++;
			}
			else if(b>=maxB){
				array[c]=A[a];
				a++;
			}else{
				int val= A[a].compareTo(B[b]);
				if(val<=0){
					array[c]=A[a];
					a++;
				}else{
					array[c]=B[b];
					b++;
				}
			}
			
		}
		
	}

}
