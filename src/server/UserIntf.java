package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
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
	 * Gibt dem UserClient eine Liste seiner Mitarbeiter mit für das GUI.
	 * @param kuerzel
	 * @return Liste der Mitarbeiter des gleichen Teams
	 * @throws RemoteException
	 */
	public List<Arbeiter> getYourTeam(String kuerzel) throws RemoteException;
	
	/**
	 * Setzt neuen User auf die Anwesenheitsliste des Servers.
	 * @param user
	 * @throws RemoteException
	 */
	public void setUser(Arbeiter user) throws RemoteException;

	/**
	 * Gibt die Messages den Clients weiter
	 * @return Messages an die Clients
	 * @throws RemoteException
	 */
	public List<Message> returnMessages() throws RemoteException;

	/**
	 * Entfernt die Bereits gesendeten Nachrichten wieder aus dem Array um Nachrichten
	 * nicht mehrmals anzeigen zu lassen.
	 * @throws RemoteException
	 */
	public void rmvPrintedMsgs() throws RemoteException;

	/**
	 * Schickt die Nachricht des Clients an den Server, 
	 * der diese dann verarbeitet.
	 * @param msg
	 * @throws RemoteException
	 */
	public void send(Message msg) throws RemoteException;
	
	public List<Arbeiter> getWhoishere() throws RemoteException;
	
	public void setWhoishere(List<Arbeiter> whoishere) throws RemoteException;
}