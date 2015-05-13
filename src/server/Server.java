package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		}
		Server server = new Server();
		try {
			Naming.rebind("//localhost/Server", server);
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
		Arbeiter you = null;
		try {
			you = this.getArbeiterDb().findPersonBykuerzel(kuerzel);
		} catch (SQLException e) {
			// TODO: Meldung wenn DB nicht funktioniert.
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
	public List<String> getWorktimes(String arbeiterid) throws RemoteException {
		List<String> zeiten = null;
		try {
			zeiten = this.getZeitDb().arbeitszeitauslesen(arbeiterid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zeiten;
	}

	@Override
	public void setUser(Arbeiter user) throws RemoteException {
		// TODO: User auf die 'whoishere' liste setzen
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