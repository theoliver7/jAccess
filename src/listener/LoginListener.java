package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Login;
import view.View;

public class LoginListener implements ActionListener {
	
	public LoginListener() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Login.main(null);
	}
}
