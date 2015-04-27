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
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, ClassCastException {
		CardIntf obj = (CardIntf)Naming.lookup("//localhost/CardServer");
		while(true) {
		obj.receiveUid(getUid());
		}
	}

	public static String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
}