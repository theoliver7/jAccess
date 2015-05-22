package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import jdbc.Arbeiter;

/**
 * Interface für Server. Dient als Schnittstelle zum Client-Programm
 * 
 * @author Nico Fehr, ICT Berufsbildungscenter AG, nico.fehr@bbcag.ch
 * @version 1.0
 *
 */
public interface UserIntf extends Remote {

	/**
	 * Sendet kürzel des User-Client als String zum Server um die entsprechende
	 * Uid herauszulesen.
	 * 
	 * @param kuerzel
	 * @throws RemoteException
	 * @return Arbeiter Objekt des Users
	 */
	public Arbeiter getYourArbeiter(String kuerzel) throws RemoteException;
	
	/**
	 * Gibt alle Arbeiter der Datenbank in einer Liste zurück.
	 * Nur Für Admin anwenden.
	 * @return
	 * @throws RemoteException
	 */
	public List<Arbeiter> getAllArbeiter() throws RemoteException;
	
	public void insertArbeiter(Arbeiter a) throws RemoteException;
	
	public void updateArbeiter(Arbeiter a, String kuerzel) throws RemoteException;
	
	public void deleteArbeiter(String uid) throws RemoteException;
	
	public List<String> getAbteilungen() throws RemoteException;

	/**
	 * Gibt dem UserClient eine Liste seiner Mitarbeiter mit für das GUI.
	 * 
	 * @param kuerzel
	 * @return Liste der Mitarbeiter des gleichen Teams
	 * @throws RemoteException
	 */
	public List<Arbeiter> getYourTeam(String teamname, String kuerzel) throws RemoteException;

	/**
	 * Gibt die Messages den Clients weiter
	 * 
	 * @return Messages an die Clients
	 * @throws RemoteException
	 */
	public List<Message> returnMessages() throws RemoteException;

	/**
	 * Entfernt die Bereits gesendeten Nachrichten wieder aus dem Array um
	 * Nachrichten nicht mehrmals anzeigen zu lassen.
	 * 
	 * @throws RemoteException
	 */
	public void rmvPrintedMsgs() throws RemoteException;

	/**
	 * Schickt die Nachricht des Clients an den Server, der diese dann
	 * verarbeitet.
	 * 
	 * @param msg
	 * @throws RemoteException
	 */
	public void send(Message msg) throws RemoteException;

	/**
	 * Gibt Liste bereit für den Client. Liste beinhaltet Arbeitet die gerade
	 * Online
	 * 
	 * @return Arbeiter-Liste von Leuten, die Online sind.
	 * @throws RemoteException
	 */
	public List<Arbeiter> getWhoishere() throws RemoteException;

	/**
	 * Setzt auf die Arbeiter-Liste einen neuen Client der gerade Online ist.
	 * 
	 * @param whoishere
	 *            Liste der Arbeiter die hier sind
	 * @throws RemoteException
	 */
	public void setWhoishere(List<Arbeiter> whoishere) throws RemoteException;

	/**
	 * Entfernt einen User aus der Liste der Leute die Online sind, wenn sie
	 * sich abmelden.
	 * 
	 * @param kuerzel
	 * @return Hat Server User entfernt? true or false
	 * @throws RemoteException
	 */
	public boolean removeUser(String kuerzel) throws RemoteException;

	/**
	 * 
	 * @param a
	 * @return
	 * @throws RemoteException
	 */
	public boolean addUser(Arbeiter a) throws RemoteException;

	public List<Timestamp> getWorktimes(String arbeiterid) throws RemoteException;
	
	/**
	 * Methode um das Profilbild des Users zu ändern.
	 * @param kuerzel
	 * @param pic
	 * @throws RemoteException
	 */
	public void changePic(String kuerzel, String pic) throws RemoteException;
}