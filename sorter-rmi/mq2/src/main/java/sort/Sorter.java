package sort;


import java.rmi.*;

import org.osoa.sca.annotations.Service;
@Service
public interface Sorter <T extends Comparable<? super T>> extends Remote{
   	public void setState(int s)throws RemoteException;
	public int getState()throws RemoteException;
	public T[] getArray()throws RemoteException;
	public void setArray(T[]a)throws RemoteException;
	public T[] sort(T[] a)throws RemoteException;

}