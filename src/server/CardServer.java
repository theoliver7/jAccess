package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ArbeiterDAO;
import jdbc.ArbeiterJDBCDAO;
import jdbc.ZeitDAO;
import jdbc.ZeitJDBCDAO;

/**
 * Einfacher RMI-Server für die Verbindung zur DB und allgemeinen Verwaltung des Programmes.
 * Sendet bestimmte Objekte an ihre Clients. Empfängt UIDs des CardReaders.
 * @author Nico Fehr, ICT Berufsbildungscenter AG, nico.fehr@bbcag.ch
 * @version 1.0
 *
 */
public class CardServer extends UnicastRemoteObject implements CardIntf, UserIntf {

	// Seriennummer
	private static final long serialVersionUID = 8078764826510784045L;
	
	// Datenbankanbindungen
	ArbeiterDAO arbeiterDb = new ArbeiterJDBCDAO();
	ZeitDAO zeitDb = new ZeitJDBCDAO();

	// Variabeln deklarieren
	private String uid = null;
	private String response = "OK";
	private List<User> whoishere = new ArrayList<User>();

	// Konstruktor
	public CardServer() throws RemoteException {
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
		CardServer server = new CardServer();
		try {
			Naming.rebind("//localhost/CardServer", server);
			System.out.println("Server ist bereit");
		} catch (MalformedURLException e) {
			System.err.println("Fehler mit Server");
			e.printStackTrace();
		}

	}

	@Override
	public void receiveUid(String uid) throws SQLException {
		this.setUid(uid);
		this.zeitDb.zeiteintragen(uid);
	}
	
	// Getters and Setters
	public String getResponse() {
		if(uid != null) {
			return response;
		} else {
			return "Something went wrong!"; 
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
}