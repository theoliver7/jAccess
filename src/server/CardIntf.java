package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Einfaches Interface für den Server. Dient als Schnittstelle zum Client
 * 
 * @author Nico Fehr, ICT Berufsbildungscenter AG, nico.fehr@bbcag.ch
 * @version 1.0
 */
public interface CardIntf extends Remote {
	/**
	 * Empfängt die UID vom Client Reader
	 * @param send
	 * @throws RemoteException
	 * @throws SQLException 
	 */
	public void receiveUid(String uid) throws RemoteException, SQLException;
	
	/**
	 * Schickt Antwort des Servers zurück an den Client.
	 * @return Erfolg oder Fehlgeschlagen
	 * @throws RemoteException
	 */
	public String getResponse() throws RemoteException;
}