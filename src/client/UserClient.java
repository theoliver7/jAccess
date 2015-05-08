package client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JOptionPane;

import jdbc.Arbeiter;
import server.Message;
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
	private Arbeiter you;
	private List<Arbeiter> team;
	// Für den Chat
	private Message msg;

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
			serverobj = (UserIntf) Naming.lookup("//localhost/Server");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			JOptionPane.showMessageDialog(null, "Der Server hat zurzeit Probleme! \nBitte wenden " + "Sie sich an den IT-Support.", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return serverobj;
	}

	public static void main(String[] args) {
		UserClient ucl = UserClient.getInstance();
		ucl.setKuerzel(System.getProperty("user.name"));

		try {
			ucl.setYou(getServer().getYourArbeiter(ucl.getKuerzel()));
			ucl.setTeam(getServer().getYourTeam(ucl.getYou().getAbteilung(), ucl.getKuerzel()));
			getServer();

			for (Arbeiter a : getServer().getYourTeam(ucl.getYou().getAbteilung(), ucl.getKuerzel())) {
				if (a.getKuerzel().equals(ucl.getKuerzel())) {
					UserClient.getServer().addUser(a);
				}
			}

			System.out.println(getServer().getWhoishere());
			System.out.println(ucl.getKuerzel());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		View2 frame = new View2(ucl);
		frame.setVisible(true);

		frame.append("Erfolgreich mit dem Chat Verbunden!\n");
		frame.append("-------------------------------------------------------" + "--------------------------------------------------\n");
		frame.message.requestFocusInWindow();

		Message msg = new Message("[" + ucl.getKuerzel() + "]", " got connected.\n");
		try {
			UserClient.getServer().send(msg);
		} catch (RemoteException e1) {
			// TODO eigene exception wenn nachricht nicht senden kann
			e1.printStackTrace();
		}
		frame.repaint();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					getServer().removeUser(ucl.getKuerzel());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * Gibt einen Booleschen Wert zurück ob UserClient im Chat online ist.
	 * 
	 * @return boolean (ist UserClient online?)
	 */
	@SuppressWarnings("unused")
	private boolean isOnline() {
		return false;
	}

	// Methoden für die Chat-Funktion
	public void send(Message msg) throws RemoteException {
		try {
			UserClient.getServer().send(msg);
		} catch (RemoteException e) {
			// TODO eigene exception wenn nachricht nicht senden kann
		}
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

	public Arbeiter getYou() {
		return you;
	}

	public void setYou(Arbeiter you) {
		this.you = you;
	}

	public List<Arbeiter> getTeam() {
		return team;
	}

	public void setTeam(List<Arbeiter> team) {
		this.team = team;
	}

}