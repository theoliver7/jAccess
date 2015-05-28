package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;

import client.UserClient;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import jdbc.Arbeiter;
import listener.ProfileListener;
import java.awt.Window.Type;
import java.awt.Toolkit;

public class AdminView extends JFrame {

	private static final long serialVersionUID = -8103788374432545888L;
	private JPanel contentPane;
	private JPanel mitarbeiter;
	private JTextField uid;
	private JTextField vorname;
	private JTextField nachname;
	private JTextField kuerzel;
	private JTextField wohnort;
	private JTextField funktion;
	private JComboBox abteilungBox;

	private Timer timer;
	private View view;

	/**
	 * Create the frame.
	 */
	public AdminView(View view) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminView.class.getResource("/images/user.png")));
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Mitarbeiterverwaltung");
		this.setView(view);
		setBounds(100, 100, 540, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(SystemColor.controlShadow));
		panel_3.setBackground(SystemColor.controlHighlight);
		panel_3.setBounds(180, 11, 341, 392);
		panel.add(panel_3);
		panel_3.setLayout(null);

		uid = new JTextField();
		uid.setBounds(127, 6, 191, 28);
		panel_3.add(uid);
		uid.setColumns(10);

		JLabel lblNewLabel = new JLabel("UID");
		lblNewLabel.setBounds(10, 13, 74, 14);
		panel_3.add(lblNewLabel);

		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setBounds(10, 50, 74, 14);
		panel_3.add(lblVorname);

		JLabel lblNewLabel_1 = new JLabel("Nachname");
		lblNewLabel_1.setBounds(10, 86, 74, 14);
		panel_3.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Kuerzel");
		lblNewLabel_2.setBounds(10, 125, 74, 14);
		panel_3.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Wohnort");
		lblNewLabel_3.setBounds(10, 182, 74, 14);
		panel_3.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Funktion");
		lblNewLabel_4.setBounds(10, 216, 74, 14);
		panel_3.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Abteilung");
		lblNewLabel_5.setBounds(10, 250, 74, 14);
		panel_3.add(lblNewLabel_5);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 289, 308, 14);
		panel_3.add(separator);

		vorname = new JTextField();
		vorname.setColumns(10);
		vorname.setBounds(127, 43, 191, 28);
		panel_3.add(vorname);

		nachname = new JTextField();
		nachname.setColumns(10);
		nachname.setBounds(127, 79, 191, 28);
		panel_3.add(nachname);

		kuerzel = new JTextField();
		kuerzel.setColumns(10);
		kuerzel.setBounds(127, 118, 191, 28);
		panel_3.add(kuerzel);

		wohnort = new JTextField();
		wohnort.setColumns(10);
		wohnort.setBounds(127, 175, 191, 28);
		panel_3.add(wohnort);

		funktion = new JTextField();
		funktion.setColumns(10);
		funktion.setBounds(127, 209, 191, 28);
		panel_3.add(funktion);
		
		Vector<String> comboBoxItems = new Vector<String>();
		try {
			Collection<String> abteilungen = UserClient.getServer().getAbteilungen();
			comboBoxItems.addAll(abteilungen);
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(comboBoxItems);
		abteilungBox = new JComboBox(model);
		abteilungBox.setBounds(127, 244, 191, 26);
		panel_3.add(abteilungBox);

		JButton createbtn = new JButton("Erstellen");
		createbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!(uid.getText().equals("")) || !(vorname.getText().equals("")) || !(nachname.getText().equals("")) || !(kuerzel.getText().equals(""))
							|| !(wohnort.getText().equals("")) || !(funktion.getText().equals("")) || !(abteilungBox.getSelectedItem().equals(""))) {
						Arbeiter a = new Arbeiter();
						a.setIdarbeiter(uid.getText());
						a.setName(vorname.getText());
						a.setNachname(nachname.getText());
						a.setKuerzel(kuerzel.getText());
						a.setWohnort(wohnort.getText());
						a.setFunktion(funktion.getText());
						a.setAbteilung(abteilungBox.getSelectedItem().toString());
						UserClient.getServer().insertArbeiter(a);
					} else {
						JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen!", "Fehler", JOptionPane.ERROR_MESSAGE);
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		createbtn.setBounds(214, 302, 90, 28);
		panel_3.add(createbtn);

		JButton savebtn = new JButton("Speichern");
		savebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!(uid.getText().equals("")) || !(vorname.getText().equals("")) || !(nachname.getText().equals("")) || !(kuerzel.getText().equals(""))
							|| !(wohnort.getText().equals("")) || !(funktion.getText().equals("")) || !(abteilungBox.getSelectedItem().equals(""))) {
						Arbeiter a = new Arbeiter();
						a.setIdarbeiter(uid.getText());
						a.setName(vorname.getText());
						a.setNachname(nachname.getText());
						a.setKuerzel(kuerzel.getText());
						a.setWohnort(wohnort.getText());
						a.setFunktion(funktion.getText());
						a.setAbteilung(abteilungBox.getSelectedItem().toString());
						UserClient.getServer().updateArbeiter(a, a.getKuerzel());
					} else {
						JOptionPane.showMessageDialog(null, "Bitte alle Felder ausfüllen!", "Fehler", JOptionPane.ERROR_MESSAGE);
					}
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, "Kann nicht gespeichert werden; User existiert nicht!\n"
							+ "Bitte bestehenden User auswählen.", "Fehler", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			
		});
		savebtn.setBounds(112, 302, 90, 28);
		panel_3.add(savebtn);

		JButton delbtn = new JButton("Löschen");
		delbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!uid.getText().equals("")) {
						UserClient.getServer().deleteArbeiter(uid.getText());
					}
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, "Konnte nicht gelöscht werden!\n"
							+ "UID des Users ist nicht bekannt!", "Fehler", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			
		});
		delbtn.setBounds(10, 302, 90, 28);
		panel_3.add(delbtn);

		JLabel lblAlsAdminEingeloggt = new JLabel("Als Admin eingeloggt");
		lblAlsAdminEingeloggt.setBounds(10, 411, 140, 14);
		panel.add(lblAlsAdminEingeloggt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 168, 392);
		panel.add(scrollPane);

		mitarbeiter = new JPanel();
		mitarbeiter.setBackground(SystemColor.controlHighlight);
		scrollPane.setViewportView(mitarbeiter);
		mitarbeiter.setLayout(new BoxLayout(mitarbeiter, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 189, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 17, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblMitarbeiterverwaltung = new JLabel("jAccess - Mitarbeiterverwaltung");
		lblMitarbeiterverwaltung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblMitarbeiterverwaltung = new GridBagConstraints();
		gbc_lblMitarbeiterverwaltung.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMitarbeiterverwaltung.gridwidth = 3;
		gbc_lblMitarbeiterverwaltung.insets = new Insets(0, 0, 0, 5);
		gbc_lblMitarbeiterverwaltung.gridx = 0;
		gbc_lblMitarbeiterverwaltung.gridy = 0;
		panel_1.add(lblMitarbeiterverwaltung, gbc_lblMitarbeiterverwaltung);

		JButton btnNeuerBenutzer = new JButton("Neuer Benutzer");
		btnNeuerBenutzer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uid.setText("");
				vorname.setText("");
				nachname.setText("");
				kuerzel.setText("");
				wohnort.setText("");
				funktion.setText("");
				abteilungBox.setSelectedIndex(0);
			}
			
		});
		GridBagConstraints gbc_btnNeuerBenutzer = new GridBagConstraints();
		gbc_btnNeuerBenutzer.insets = new Insets(0, 0, 0, 5);
		gbc_btnNeuerBenutzer.gridx = 9;
		gbc_btnNeuerBenutzer.gridy = 0;
		panel_1.add(btnNeuerBenutzer, gbc_btnNeuerBenutzer);

		class Prozess extends TimerTask {

			@Override
			public void run() {
				usrReceiver();
			}
		}

		// Thread starten für den Chat
		this.setTimer(new Timer());
		this.getTimer().scheduleAtFixedRate(new Prozess(), 0, 500);

	}

	private void usrReceiver() {
		try {
			String you = null;
			this.mitarbeiter.removeAll();
			List<Arbeiter> team = UserClient.getServer().getAllArbeiter();

			for (Arbeiter a : team) {
				JLabel label = new JLabel(a.getName() + " " + a.getNachname(), JLabel.LEFT);
				label.addMouseListener(new MouseListener() {

					@Override
					public void mousePressed(MouseEvent e) {
						uid.setText(a.getIdarbeiter());
						vorname.setText(a.getName());
						nachname.setText(a.getNachname());
						kuerzel.setText(a.getKuerzel());
						wohnort.setText(a.getWohnort());
						funktion.setText(a.getFunktion());
						abteilungBox.setSelectedItem(a.getAbteilung());
					}

					@Override
					public void mouseExited(MouseEvent e) {
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

				});
				this.mitarbeiter.add(label);
			}
			this.mitarbeiter.repaint();
			this.mitarbeiter.revalidate();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
}
