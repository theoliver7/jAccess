package jAccess.testarea;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CardIntf extends Remote {
	public String receiveUid(String uid) throws RemoteException;
}
