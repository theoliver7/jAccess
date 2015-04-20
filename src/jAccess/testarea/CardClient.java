package jAccess.testarea;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CardClient {
	
	public CardIntf cardclient = null;
	private static String uid = null;

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -547343587330787268L;

	private CardClient() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, ClassCastException {
		CardIntf obj = (CardIntf) Naming.lookup("//localhost/CardServer");
		obj.sendUid(getUid());
	}
	
	public CardClient getInstance() throws RemoteException {
		if (cardclient == null) {
			cardclient = (CardIntf) new CardClient();
			return (CardClient) cardclient;
		}
		return (CardClient) cardclient;
	}

	public static String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
}
