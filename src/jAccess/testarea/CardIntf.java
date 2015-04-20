package jAccess.testarea;

import java.rmi.RemoteException;

public interface CardIntf {
	public String sendUid(String uid) throws RemoteException;
}
