package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
	 * @return Kuerzel des Users
	 */
	public String sendKuerzel(String kuerzel) throws RemoteException;
	
	/**
	 * Setzt neuen User auf die Anwesenheitsliste des Servers.
	 * @param user
	 * @throws RemoteException
	 */
	public void setUser(Arbeiter user) throws RemoteException;
}