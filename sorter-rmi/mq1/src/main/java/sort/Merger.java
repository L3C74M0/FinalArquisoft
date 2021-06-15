package sort;
import java.rmi.Remote;
import java.rmi.RemoteException;

import org.osoa.sca.annotations.Service;
@Service
public interface Merger <T extends Comparable<? super T>>extends Remote {
	public T[] merge(T[] a, T[] b) throws RemoteException;
	public T[] getA() throws RemoteException;
	public void setA(T[]a) throws RemoteException;
	public T[] getB() throws RemoteException;
	public void setB(T[]a) throws RemoteException;
	public T[] getArray() throws RemoteException;
	public void setArray(T[]a) throws RemoteException;


}
