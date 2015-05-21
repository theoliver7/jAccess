package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import view.View;

public class ExitListener implements ActionListener, WindowListener {

	private View view;
	
	public ExitListener(View view) {
		this.setView(view);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.getView().getUcl().getServer().removeUser(this.getView().getUcl().getKuerzel());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		System.exit(0);
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			this.getView().getUcl().getServer().removeUser(this.getView().getUcl().getKuerzel());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
