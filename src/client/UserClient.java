package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import server.CardIntf;

/**
 * Client für den Server um dem User die Entsprechenden Zeiten und die Anderen
 * Mitarbeiter anzuzeigen (Singleton)
 * 
 * @author Nico Fehr, ICT Berufsbildungscenter AG, nico.fehr@bbcag.ch
 * @author Oliver Aschwanden, ICT Berufsbildungscenter AG,
 *         oliver.aschwanden@bbcag.ch
 *
 */
public class UserClient {

	// Variabeln deklarieren
	private static String kuerzel;
	private static UserClient client;

	/**
	 * Private Konstruktor für Singleton
	 */
	private UserClient() {
	}

	/**
	 * Methode um UserClient Objekt zu erhalten (Singleton)
	 * 
	 * @return UserClient-Instanz
	 */
	public UserClient getInstance() {
		if (UserClient.client == null) {
			UserClient.setClient(new UserClient());
			return UserClient.client;
		} else {
			return UserClient.client;
		}
	}

	/**
	 * Gibt Server Objekt über RMI zurück
	 * 
	 * @return Server-Verbindung
	 */
	public static CardIntf getServer() {
		CardIntf serverobj = null;
		try {
			serverobj = (CardIntf) Naming.lookup("//localhost/CardServer");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Der Server läuft zurzeit nicht", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return serverobj;
	}

	public static void main(String[] args) {
		

	}

	/**
	 * Gibt einen Bool'schen Wert zurück ob UserClient im Chat online ist.
	 * @return boolean (ist UserClient online?)
	 */
	@SuppressWarnings("unused")
	private boolean isOnline() {
		return false;
	}

	// Getter und Setter Methoden
	public String getKuerzel() {
		return kuerzel;
	}

	public static void setKuerzel(String kuerzel) {
		kuerzel = kuerzel;
	}

	public static void setClient(UserClient client) {
		client = client;
	}

}