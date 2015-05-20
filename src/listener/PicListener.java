package listener;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.UserClient;
import view.View;
import jdbc.Arbeiter;

public class PicListener implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private View view;
	private Arbeiter arbeiter;
	private static PicListener pl = null;
	private JDialog dialog = null;

	private String[] pics = { "user_c3po.png", "user_darth_vader.png", "user_jawa.png", "user_r2d2.png", "user_trooper_captain.png", "user_trooper.png", "user_yoda.png", "user.png" };

	private PicListener(View view, Arbeiter a) {
		this.setView(view);
		this.setArbeiter(a);
	}

	public static synchronized PicListener getInstance(View view, Arbeiter a) {
		if (pl == null) {
			pl = new PicListener(view, a);
		}
		return pl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.dialog == null) {
			dialog = new JDialog();
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
				Image newimg = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
				ImageIcon newicon = new ImageIcon(newimg);
				button.setIcon(newicon);
				button.addActionListener(new PicChangeListener(this.getView(), this.getArbeiter(), pics[i]));
				contentPanel.add(button);
			}
			dialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					contentPanel.removeAll();
					contentPanel.revalidate();
					contentPanel.repaint();
					dialog = null;
				}
			});
		}
	}
	
	

	class PicChangeListener implements ActionListener {

		private View view;
		private Arbeiter a;
		private String pic;

		public PicChangeListener(View view, Arbeiter a, String pic) {
			this.setView(view);
			this.setA(a);
			this.setPic(pic);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				System.out.println(this.getPic());
				UserClient.getServer().changePic(this.getA().getKuerzel(), this.getPic());
			} catch (RemoteException e1) {
				JOptionPane.showMessageDialog(null, "Ihr Profilbild konnte nicht geändert werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			contentPanel.removeAll();
			contentPanel.revalidate();
			contentPanel.repaint();
			dialog.setVisible(false);
			dialog = null;
			ImageIcon icon = (ImageIcon) View.loadIcon(this.getPic());
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			this.getView().profilePic.removeAll();
			this.getView().profilePic.setIcon(icon);
			this.getView().profilePic.repaint();
		}

		public View getView() {
			return view;
		}

		public void setView(View view) {
			this.view = view;
		}

		public Arbeiter getA() {
			return a;
		}

		public void setA(Arbeiter a) {
			this.a = a;
		}

		public String getPic() {
			return pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

	}

	public View getView() {
		return view;
	}

	public void setView(View view2) {
		this.view = view2;
	}

	public Arbeiter getArbeiter() {
		return arbeiter;
	}

	public void setArbeiter(Arbeiter arbeiter) {
		this.arbeiter = arbeiter;
	}
}
