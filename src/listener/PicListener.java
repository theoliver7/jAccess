package listener;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.View2;
import jdbc.Arbeiter;

public class PicListener implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private View2 view;
	private Arbeiter arbeiter;

	private String[] pics = { "user_c3po.png", "user_darth_vader.png", "user_jawa.png", "user_r2d2.png", "user_trooper_captain.png", "user_trooper.png", "user_yoda.png", "user.png" };

	public PicListener(View2 view2, Arbeiter a) {
		this.setView(view2);
		this.setArbeiter(a);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		dialog.setBounds(100, 100, 450, 300);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 0, 0, 0));

		for (int i = 0; i < pics.length; i++) {
			JButton button = new JButton();
			ImageIcon icon = (ImageIcon) this.getView().loadIcon(pics[i]);
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newicon = new ImageIcon(newimg);
			button.setIcon(newicon);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

				}

			});
			contentPanel.add(button);
		}
	}

	public View2 getView() {
		return view;
	}

	public void setView(View2 view2) {
		this.view = view2;
	}

	public Arbeiter getArbeiter() {
		return arbeiter;
	}

	public void setArbeiter(Arbeiter arbeiter) {
		this.arbeiter = arbeiter;
	}
}
