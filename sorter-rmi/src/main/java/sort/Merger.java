package sort;
import org.osoa.sca.annotations.Service;
@Service
public interface Merger <T extends Comparable<? super T>>extends Runnable {
	public  void merge();
	public T[] getA();
	public void setA(T[]a);
	public T[] getB();
	public void setB(T[]a);
	public T[] getArray();
	public void setArray(T[]a);


}
