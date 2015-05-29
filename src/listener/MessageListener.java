package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import server.Message;
import view.View;
import client.UserClient;

public class MessageListener implements KeyListener, ActionListener {

	private View view;

	public MessageListener(View view) {
		this.setView(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		this.checkMessage();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			e.consume();
			this.checkMessage();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	private void checkMessage() {

		try {
			if (this.getView().message.getText().getBytes().length > 250) {
				JOptionPane.showMessageDialog(null, "Zeichenlimite überschritten (max. 250)");
				this.getView().message.setText("");
			}
			else if (!this.getView().message.getText().equals("")) {
				UserClient.getServer().send(
						new Message("[" + this.getView().getUcl().getKuerzel() + " - " + this.getView().getUcl().getYou().getAbteilung() + "] ", this.getView().message.getText() + "\n"));
				this.getView().message.setText("");
			}
		} catch (RemoteException e1) {
		}
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

}
