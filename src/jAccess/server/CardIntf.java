package jAccess.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CardIntf extends Remote {
	public void receiveUid(String send) throws RemoteException;
}
