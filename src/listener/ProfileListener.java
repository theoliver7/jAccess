package listener;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import jdbc.Arbeiter;
import view.View;

public class ProfileListener implements MouseListener {

	View view = null;
	Arbeiter arbeiter = null;

	public ProfileListener(View view, Arbeiter arbeiter) {
		this.setView(view);
		this.setArbeiter(arbeiter);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.getArbeiter().getKuerzel().equals(this.getView().getUcl().getKuerzel())) {
			this.getView().picButton.setVisible(true);
		} else {
			this.getView().picButton.setVisible(false);
		}
		this.getView().profileInfoPanel.setText("");
		this.getView().vornameLabel.setText(this.getArbeiter().getName());
		this.getView().vornameLabel.repaint();
		this.getView().nachnameLabel.setText(this.getArbeiter().getNachname());
		this.getView().nachnameLabel.repaint();
		this.getView().abteilungLabel.setText(this.getArbeiter().getAbteilung());
		this.getView().abteilungLabel.repaint();
		this.getView().funktionLabel.setText(this.getArbeiter().getFunktion());
		this.getView().funktionLabel.repaint();
		this.getView().wohnortLabel.setText(this.getArbeiter().getWohnort());
		this.getView().wohnortLabel.repaint();
		this.getView().statusLabel.setText("STATUS");
		this.getView().statusLabel.repaint();
		ImageIcon icon = (ImageIcon) View.loadIcon(this.getArbeiter().getPic());
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		this.getView().profilePic.setIcon(icon);
		this.getView().profilePic.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Arbeiter getArbeiter() {
		return arbeiter;
	}

	public void setArbeiter(Arbeiter arbeiter) {
		this.arbeiter = arbeiter;
	}

}
