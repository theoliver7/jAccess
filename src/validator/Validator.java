package validator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Validator extends Remote {
	/**
	 * Validiert das Login fuer den Admin
	 * @param id
	 * @param pw
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean validate(String id, String pw) throws RemoteException;
}
