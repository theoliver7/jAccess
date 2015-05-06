package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import server.CardIntf;

public class UserClient {
	
	// Variabeln deklarieren
	private String kuerzel;
	
	public static CardIntf getServer() {
		CardIntf serverobj = null;
		try {
			serverobj = (CardIntf) Naming.lookup("//localhost/CardServer");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Der Server läuft zurzeit nicht", "Fehler" , JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return serverobj;
	}
	
	public static void main(String[] args) {
		
		getServer();
	}

	private boolean isOnline() {
		return false;
	}
	
	// Getter und Setter Methoden
	public String getKuerzel() {
		return kuerzel;
	}

	public void setKuerzel(String kuerzel) {
		this.kuerzel = kuerzel;
	}

}