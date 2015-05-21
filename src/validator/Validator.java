package validator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Validator extends Remote {
	public boolean validate(String id, String pw) throws RemoteException;
}
