package jAccess.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
	 */
	public void receiveUid(String send) throws RemoteException;
	
	/**
	 * Schickt Antwort des Servers zurück an den Client.
	 * @return Erfolg oder Fehlgeschlagen
	 * @throws RemoteException
	 */
	public String getResponse() throws RemoteException;
}