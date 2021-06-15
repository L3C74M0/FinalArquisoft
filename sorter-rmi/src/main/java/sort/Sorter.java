package sort;


import org.osoa.sca.annotations.Service;
@Service
public interface Sorter <T extends Comparable<? super T>> extends Runnable{
   	public void setState(int s);
	public int getState();
	public T[] getArray();
	public void setArray(T[]a);


}