package validator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class ValidatorImpl extends UnicastRemoteObject implements Validator {
	
	private Map<String, String> keys = null;
	
	public ValidatorImpl() throws RemoteException {
		super();
		keys = new HashMap<String, String>();
		keys.put("Admin", "1234");
	}

	private static final long serialVersionUID = 4120045380485216559L;

	@Override
	public boolean validate(String id, String pw) throws RemoteException {
		if(keys.containsKey(id) && keys.get(id).equals(pw)) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Kein Zugriff!", "Fehler", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

}
