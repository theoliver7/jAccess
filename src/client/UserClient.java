package client;

import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import server.UserIntf;
import view.View2;

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
	private String kuerzel;
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
	public synchronized static UserClient getInstance() {
		if (client == null) {
			client = new UserClient();
		}
		return client;
	}

	/**
	 * Gibt Server Objekt über RMI zurück
	 * 
	 * @return Server-Verbindung
	 */
	public static UserIntf getServer() {
		UserIntf serverobj = null;
		try {
			serverobj = (UserIntf) Naming.lookup("//localhost/CardServer");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			JOptionPane.showMessageDialog(null, "Der Server läuft zurzeit nicht! Bitte wenden " + "Sie sich an den IT-Support.", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return serverobj;
	}

	public static void main(String[] args) {
		UserClient ucl = UserClient.getInstance();
		ucl.setKuerzel(System.getProperty("user.name"));
		
		View2 frame = new View2(ucl);
		frame.setVisible(true);
	}

	/**
	 * Gibt einen Bool'schen Wert zurück ob UserClient im Chat online ist.
	 * 
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

	public void setKuerzel(String kuerzel) {
		this.kuerzel = kuerzel;
	}

	public static void setClient(UserClient client) {
		client = client;
	}

}