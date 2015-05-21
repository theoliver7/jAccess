package client;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import jdbc.Arbeiter;
import jdbc.Zeit;
import listener.ExitListener;
import server.Message;
import server.UserIntf;
import view.View;

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
	private ArrayList<ArrayList<String>> arbeitszeit;
	// Für den Chat
	private Message msg;

	/**
	 * Privater Konstruktor für Singleton
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
			String server = "//localhost/Server";
			try (FileReader reader = new FileReader("config.properties")) {
				Properties properties = new Properties();
				properties.load(reader);
				server = properties.getProperty("server");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			serverobj = (UserIntf) Naming.lookup(server);
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

			Zeit z = new Zeit();
			try {
				if (getServer().getWorktimes(ucl.getYou().getIdarbeiter()) != null) {
					ucl.setArbeitszeit(z.totalberechnen(z.zeitenorganisieren(getServer().getWorktimes(ucl.getYou().getIdarbeiter()))));
				} else {
					System.out.println("hello");
					ucl.setArbeitszeit(new ArrayList<ArrayList<String>>());
				}
			} catch (SQLException | ParseException e1) {
				e1.printStackTrace();
			}

			for (Arbeiter a : getServer().getYourTeam(ucl.getYou().getAbteilung(), ucl.getKuerzel())) {
				if (a.getKuerzel().equals(ucl.getKuerzel())) {
					UserClient.getServer().addUser(a);
				}
			}

			System.out.println(getServer().getWhoishere());
			System.out.println(ucl.getKuerzel());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Keine Verbindung zur Datenbank! \nBitte wenden " + "Sie sich an den IT-Support.", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		View frame = new View(ucl);
		frame.setVisible(true);

		frame.append("Erfolgreich mit dem Chat Verbunden!\n");
		frame.append("-------------------------------------------------------" + "--------------------------------------------------\n");
		frame.message.requestFocusInWindow();

		Message msg = new Message("[" + ucl.getKuerzel() + "]", " got connected.\n");
		try {
			UserClient.getServer().send(msg);
		} catch (RemoteException e1) {
			JOptionPane.showMessageDialog(null, "Die Nachricht konnte nicht gesendet werden! \nBitte wenden " + "Sie sich an den IT-Support.", "Fehler", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		frame.repaint();

		frame.addWindowListener(new ExitListener(frame));
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

	public ArrayList<ArrayList<String>> getArbeitszeit() {
		return arbeitszeit;
	}

	public void setArbeitszeit(ArrayList<ArrayList<String>> arrayList) {
		this.arbeitszeit = arrayList;
	}

}