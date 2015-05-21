package server;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jdbc.Arbeiter;
import jdbc.ArbeiterDAO;
import jdbc.ArbeiterJDBCDAO;
import jdbc.ZeitDAO;
import jdbc.ZeitJDBCDAO;

/**
 * Einfacher RMI-Server für die Verbindung zur DB und allgemeinen Verwaltung des
 * Programmes. Sendet bestimmte Objekte an ihre Clients. Empfängt UIDs des
 * CardReaders.
 * 
 * @author Nico Fehr, ICT Berufsbildungscenter AG, nico.fehr@bbcag.ch
 * @version 1.0
 *
 */
public class Server extends UnicastRemoteObject implements CardIntf, UserIntf {

	// Seriennummer
	private static final long serialVersionUID = 8078764826510784045L;

	// Datenbankanbindungen
	ArbeiterDAO arbeiterDb = new ArbeiterJDBCDAO();
	ZeitDAO zeitDb = new ZeitJDBCDAO();

	// Variabeln deklarieren
	// Karten Teil
	private String uid;
	private final String response = "OK";
	// Ende Karten Teil

	// User Teil
	public List<Arbeiter> whoishere = new ArrayList<Arbeiter>();
	// Ende User Teil

	// Chat Teil
	private static List<Message> msgs = new ArrayList<Message>();

	// Ende Variabeln deklarieren

	// Konstruktor
	public Server() throws RemoteException {
		super(0);
	}

	// Server starten
	public static void main(String[] args) throws RemoteException, MalformedURLException, Exception {
		System.out.println("CardServer is Starting");

		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("Registry gestartet");
		} catch (RemoteException re) {
			System.err.println("Registry existiert schon");
			try {
			LocateRegistry.createRegistry(1100);
			System.out.println("1099 ist besetzt. Neues Registry wird erstellt.");
			} catch (RemoteException re1) {
				System.out.println("Registry existiert ebenfalls schon");
				re1.printStackTrace();
			}
		}
		Server server = new Server();
		try {
			String servername = "//localhost/Server";
			try (FileReader reader = new FileReader("config.properties")) {
				Properties properties = new Properties();
				properties.load(reader);
				servername = properties.getProperty("server");
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(servername + " || " + server.toString());
			Naming.rebind(servername, server);
			System.out.println("Server ist bereit");
		} catch (MalformedURLException e) {
			System.err.println("Fehler mit Server");
			e.printStackTrace();
		}
	}

	// Methoden für Reader
	@Override
	public void receiveUid(String uid) throws SQLException {
		this.setUid(uid);
		this.getZeitDb().zeiteintragen(uid);
	}

	// Methoden für Client
	@Override
	public Arbeiter getYourArbeiter(String kuerzel) throws RemoteException {
		Arbeiter you = new Arbeiter();
		try {
			you = this.getArbeiterDb().findPersonBykuerzel(kuerzel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return you;
	}

	@Override
	public List<Arbeiter> getYourTeam(String teamname, String kuerzel) throws RemoteException {
		List<Arbeiter> team = null;
		try {
			team = this.getArbeiterDb().findTeam(teamname, kuerzel);
		} catch (SQLException e) {
			// TODO Meldung wenn DB nicht funktioniert.
			e.printStackTrace();
		}
		return team;
	}

	@Override
	public List<Timestamp> getWorktimes(String arbeiterid) throws RemoteException {
		List<Timestamp> zeiten = null;
		try {
			zeiten = this.getZeitDb().arbeitszeitauslesen(arbeiterid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zeiten;
	}

	@Override
	public boolean removeUser(String kuerzel) throws RemoteException {
		for (Arbeiter a : this.getWhoishere()) {
			if (a.getKuerzel().equals(kuerzel)) {
				this.getWhoishere().remove(a);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addUser(Arbeiter a) throws RemoteException {
		this.getWhoishere().add(a);
		return true;
	}

	@Override
	public void changePic(String kuerzel, String pic) throws RemoteException {
		try {
			this.getArbeiterDb().updatePic(kuerzel, pic);
		} catch (SQLException e) {
			System.err.println();
			e.printStackTrace();
		}
	}

	// Methoden für Chat
	@Override
	public List<Message> returnMessages() throws RemoteException {
		return this.getMsgs();
	}

	@Override
	public void rmvPrintedMsgs() throws RemoteException {
		List<Message> msgarray = this.returnMessages();
		try {

			// Mit sleep(int) kann ein Cooldown eingestellt werden. Für
			// besonders
			// Nervige Chatter :)
			Thread.sleep(20);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		msgarray.clear();
		this.setMsgs(msgarray);
	}

	@Override
	public void send(Message msg) throws RemoteException {
		getMsgs().add(msg);
	}

	// Getters and Setters
	public String getResponse() {
		if (uid != null) {
			return response;
		} else {
			return "Fehler bei der Übermittlung!";
		}
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public ArbeiterDAO getArbeiterDb() {
		return arbeiterDb;
	}

	public void setArbeiterDb(ArbeiterDAO arbeiterDb) {
		this.arbeiterDb = arbeiterDb;
	}

	public ZeitDAO getZeitDb() {
		return zeitDb;
	}

	public void setZeitDb(ZeitDAO zeitDb) {
		this.zeitDb = zeitDb;
	}

	public List<Arbeiter> getWhoishere() throws RemoteException {
		return whoishere;
	}

	public void setWhoishere(List<Arbeiter> whoishere) throws RemoteException {
		this.whoishere = whoishere;
	}

	public static List<Message> getMsgs() {
		return msgs;
	}

	public static void setMsgs(List<Message> msgs) {
		Server.msgs = msgs;
	}
}