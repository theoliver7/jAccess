package listener;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import jdbc.Arbeiter;
import view.View2;

public class ProfileListener implements MouseListener {

	View2 view2 = null;
	Arbeiter arbeiter = null;

	public ProfileListener(View2 view2, Arbeiter arbeiter) {
		this.setView2(view2);
		this.setArbeiter(arbeiter);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.getArbeiter().getKuerzel().equals(this.getView2().getUcl().getKuerzel())) {
			this.getView2().picButton.setVisible(true);
		} else {
			this.getView2().picButton.setVisible(false);
		}
		this.getView2().profileInfoPanel.setText("");
		this.getView2().vornameLabel.setText(this.getArbeiter().getName());
		this.getView2().vornameLabel.repaint();
		this.getView2().nachnameLabel.setText(this.getArbeiter().getNachname());
		this.getView2().nachnameLabel.repaint();
		this.getView2().abteilungLabel.setText(this.getArbeiter().getAbteilung());
		this.getView2().abteilungLabel.repaint();
		this.getView2().funktionLabel.setText(this.getArbeiter().getFunktion());
		this.getView2().funktionLabel.repaint();
		this.getView2().wohnortLabel.setText(this.getArbeiter().getWohnort());
		this.getView2().wohnortLabel.repaint();
		this.getView2().statusLabel.setText("STATUS");
		this.getView2().statusLabel.repaint();
		ImageIcon icon = (ImageIcon) View2.loadIcon(this.getArbeiter().getPic());
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		this.getView2().profilePic.setIcon(icon);
		this.getView2().profilePic.repaint();
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

	public View2 getView2() {
		return view2;
	}

	public void setView2(View2 view2) {
		this.view2 = view2;
	}

	public Arbeiter getArbeiter() {
		return arbeiter;
	}

	public void setArbeiter(Arbeiter arbeiter) {
		this.arbeiter = arbeiter;
	}

}
