package jAccess.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class CardServer extends UnicastRemoteObject implements CardIntf, UserIntf {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8078764826510784045L;

	private String uid;
	private String time;
	private List<User> whoishere = new ArrayList<User>();

	public CardServer() throws RemoteException {
		super(0);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException, Exception {
		System.out.println("CardServer is Starting");
		
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("Registry gestartet");
		} catch (RemoteException re) {
			System.err.println("Registry existiert schon");
		}
		
		CardServer server = new CardServer();
		try {
			Naming.rebind("//localhost/CardServer", server);
			System.out.println("Server ist bereit");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.err.println("Fehler mit Server");
			e.printStackTrace();
		}
		 
	}

	@Override
	public void receiveUid(String send) {
		// TODO Auto-generated method stub
		System.out.println(send);
	}
}
