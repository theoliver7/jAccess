package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import view.Login;
import view.View;

public class LoginListener implements ActionListener {
	
	private View view;
	
	public LoginListener(View view) {
		this.setView(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Login dialog = new Login(this.getView());
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
}
