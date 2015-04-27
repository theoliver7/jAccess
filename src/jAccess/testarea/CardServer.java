package jAccess.testarea;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class CardServer extends UnicastRemoteObject implements CardIntf {
	
	protected CardServer() throws RemoteException {
		super(0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6668943615186356316L;

	public static void main(String[] args) throws RemoteException, Exception {
		
		System.out.println("Card Server is starting ...");
		
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("Card RMI registry started ...");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.err.print("Java Card RMI Registry already exists!");
		}
		
		CardServer cserver = new CardServer();
		
		Naming.rebind("//localhost/CardServer", cserver);
		System.out.println("PeerServer bound in registry!");
	}

	@Override
	public String receiveUid(String uid) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("UID: " + uid);
		return uid;
	}
	
}