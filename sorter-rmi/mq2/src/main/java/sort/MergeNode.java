package sort;

import org.osoa.sca.annotations.Scope;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import org.osoa.sca.annotations.*;


@Scope("CONVERSATION")
@Service(Runnable.class)
public class MergeNode <T extends Comparable<? super T>> extends UnicastRemoteObject implements Merger <T> ,Runnable {
	
	@Property
	public  T[] array;
	@Property
	public  T[] A;
	@Property
	public  T[] B;
	@Property
	private String myServiceMerger;



	@Reference(name="subject")
	private Subject subject;
	
	
	public MergeNode()  throws RemoteException{
		System.out.println("Starting mergenode1");
	}
	public  void run() {
		System.out.println("conecting merger...");
		subject.attachMerger(myServiceMerger);
		System.out.println("...mergerConected");
		// int maxA=A.length;
		// int maxB=B.length; 
		// int size= maxA+maxB; 
		// //ArrayList<T> nuevo = new ArrayList<T>();
		// array= (T[]) new Comparable[size];
		// int a,b,c;
		// for(a=0, b=0,c=0; c<size; c++ ){
		// 	if (a>=maxA){
		// 		array[c]=B[b];
		// 		b++;
		// 	}
		// 	else if(b>=maxB){
		// 		array[c]=A[a];
		// 		a++;
		// 	}else{
		// 		int val= A[a].compareTo(B[b]);
		// 		if(val<=0){
		// 			array[c]=A[a];
		// 			a++;
		// 		}else{
		// 			array[c]=B[b];
		// 			b++;
		// 		}
		// 	}
			
		// }

	}


	@Override
	public T[] getArray() throws RemoteException {
		return array;
	}

	@Override
	public void setArray(T[] a)throws RemoteException {
		array=a;
		
	}

	@Override
	public T[] getA()throws RemoteException {
		return A;
	}

	@Override
	public void setA(T[] a)throws RemoteException {
		A=a;
	}

	@Override
	public T[] getB()throws RemoteException {
		return B;
	}

	@Override
	public void setB(T[] b)throws RemoteException {
		B=b;
	}
	@Override
	public T[] merge(T[] A,T[] B) throws RemoteException {
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
		return array;
		
	}

}
