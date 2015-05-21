package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import jdbc.Arbeiter;
import listener.ExitListener;
import listener.LoginListener;
import listener.PicListener;
import listener.ProfileListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import server.Message;
import client.UserClient;

public class View extends JFrame {

	private static final long serialVersionUID = 4701804783439921041L;
	private JPanel content;
	private JTable time_tabel;
	private JTextPane chattext;
	public JTextField message;
	private Timer timer;
	private UserClient ucl;
	private JPanel youteam_panel;
	private JPanel teamPanel;
	private JScrollPane teamScroll;

	private PicListener picl;

	public JLabel profilePic = new JLabel();
	public JPanel profileInfo = new JPanel();
	public JLabel vornameLabel = new JLabel();
	public JLabel nachnameLabel = new JLabel();
	public JLabel funktionLabel = new JLabel();
	public JLabel abteilungLabel = new JLabel();
	public JLabel wohnortLabel = new JLabel();
	public JLabel statusLabel = new JLabel();

	public JButton picButton;
	public JLabel profileInfoPanel = new JLabel("Profil links auswählen");

	// Icons fürs GUI
	private static final Icon online = loadIcon("bullet_green.png");
	private static final Icon offline = loadIcon("bullet_red.png");

	/**
	 * Create the frame.
	 */
	public View(UserClient ucl) {
		setTitle("jAccess");
		this.setUcl(ucl);
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 992, 651);

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmPrint = new JMenuItem("Print");
		mnFile.add(mntmPrint);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ExitListener(this));
		mnFile.add(mntmExit);

		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);

		JMenuItem mntmAccount = new JMenuItem("Account");
		mnSettings.add(mntmAccount);

		JMenuItem mntmAdminLogin = new JMenuItem("Admin Login");
		mntmAdminLogin.addActionListener(new LoginListener());
		mnSettings.add(mntmAdminLogin);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmVersion = new JMenuItem("About");
		mnHelp.add(mntmVersion);

		JMenuItem mntmVersion_1 = new JMenuItem("Version");
		mnHelp.add(mntmVersion_1);
		content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		content.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		content.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		JLabel lblJaccess = new JLabel("jAccess");
		lblJaccess.setFont(new Font("Arial", Font.ITALIC, 30));
		panel.add(lblJaccess);

		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP);
		content.add(tabbedPanel, BorderLayout.CENTER);

		JPanel overview = new JPanel();
		tabbedPanel.addTab("Übersicht", null, overview, null);
		overview.setLayout(null);

		JLabel yourteam_label = new JLabel("Your Team");
		yourteam_label.setBounds(44, 17, 85, 16);
		overview.add(yourteam_label);

		JLabel profile_label = new JLabel("Profile");
		profile_label.setBounds(270, 17, 85, 16);
		overview.add(profile_label);

		JLabel chat_label = new JLabel("Chat");
		chat_label.setBounds(551, 17, 85, 16);
		overview.add(chat_label);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(106, 26, 116, 7);
		overview.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(220, 27, 18, 457);
		overview.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(7, 26, 33, 7);
		overview.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(6, 27, 18, 457);
		overview.add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(7, 482, 215, 21);
		overview.add(separator_5);

		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(234, 26, 18, 456);
		overview.add(separator_6);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(235, 26, 32, 7);
		overview.add(separator_7);

		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(307, 26, 194, 7);
		overview.add(separator_8);

		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setBounds(499, 27, 18, 456);
		overview.add(separator_9);

		JSeparator separator_10 = new JSeparator();
		separator_10.setBounds(235, 482, 266, 21);
		overview.add(separator_10);

		JSeparator separator_11 = new JSeparator();
		separator_11.setOrientation(SwingConstants.VERTICAL);
		separator_11.setBounds(513, 27, 18, 456);
		overview.add(separator_11);

		JSeparator separator_12 = new JSeparator();
		separator_12.setBounds(514, 26, 33, 7);
		overview.add(separator_12);

		JSeparator separator_13 = new JSeparator();
		separator_13.setBounds(580, 26, 390, 21);
		overview.add(separator_13);

		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setBounds(968, 27, 2, 456);
		overview.add(separator_14);

		JSeparator separator_15 = new JSeparator();
		separator_15.setBounds(514, 482, 456, 21);
		overview.add(separator_15);

		youteam_panel = new JPanel();
		youteam_panel.setBounds(7, 26, 215, 458);
		overview.add(youteam_panel);
		youteam_panel.setLayout(null);

		teamScroll = new JScrollPane();
		teamScroll.setBounds(6, 6, 203, 446);
		youteam_panel.add(teamScroll);

		teamPanel = new JPanel();
		teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
		teamScroll.setViewportView(teamPanel);

		JPanel profile_panel = new JPanel();
		profile_panel.setBounds(234, 26, 267, 458);
		overview.add(profile_panel);
		profile_panel.setLayout(null);

		profilePic.setBackground(new Color(204, 204, 204));
		profilePic.setBounds(20, 30, 96, 96);
		profile_panel.add(profilePic);

		profileInfo.setBackground(UIManager.getColor("DesktopPane.background"));
		profileInfo.setBounds(20, 138, 149, 292);
		profile_panel.add(profileInfo);
		profileInfo.setLayout(new GridLayout(0, 2, 0, 0));

		vornameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		profileInfo.add(vornameLabel);

		profileInfo.add(nachnameLabel);

		profileInfo.add(funktionLabel);

		JLabel lblNewLabel_3 = new JLabel("");
		profileInfo.add(lblNewLabel_3);

		profileInfo.add(abteilungLabel);

		JLabel lblNewLabel_5 = new JLabel("");
		profileInfo.add(lblNewLabel_5);

		profileInfo.add(wohnortLabel);

		JLabel lblNewLabel_7 = new JLabel("");
		profileInfo.add(lblNewLabel_7);

		profileInfo.add(statusLabel);

		JLabel lblNewLabel_10 = new JLabel("");
		profileInfo.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("");
		profileInfo.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("");
		profileInfo.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("");
		profileInfo.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("");
		profileInfo.add(lblNewLabel_14);

		JLabel label_7 = new JLabel("");
		profileInfo.add(label_7);

		JLabel lblNewLabel_15 = new JLabel("");
		profileInfo.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("");
		profileInfo.add(lblNewLabel_16);

		JLabel lblNewLabel_17 = new JLabel("");
		profileInfo.add(lblNewLabel_17);

		JLabel lblNewLabel_18 = new JLabel("");
		profileInfo.add(lblNewLabel_18);

		JLabel lblNewLabel = new JLabel("");
		profileInfo.add(lblNewLabel);

		JLabel label_4 = new JLabel("");
		profileInfo.add(label_4);

		JLabel label_6 = new JLabel("");
		profileInfo.add(label_6);

		JLabel label_5 = new JLabel("");
		profileInfo.add(label_5);

		JLabel label_2 = new JLabel("");
		profileInfo.add(label_2);

		JLabel label_3 = new JLabel("");
		profileInfo.add(label_3);

		JLabel lblNewLabel_19 = new JLabel("");
		profileInfo.add(lblNewLabel_19);

		JLabel lblNewLabel_20 = new JLabel("");
		profileInfo.add(lblNewLabel_20);

		picButton = new JButton();
		picButton.setText("Profilbild");
		picButton.setToolTipText("Profilbild ändern");
		picButton.setVisible(false);
		picButton.setBounds(185, 6, 76, 28);
		picButton.addActionListener(PicListener.getInstance(this, this.getUcl().getYou()));
		profile_panel.add(picButton);

		profileInfoPanel.setBounds(74, 425, 129, 27);
		profile_panel.add(profileInfoPanel);

		JPanel chat_panel = new JPanel();
		chat_panel.setBounds(509, 26, 457, 458);
		overview.add(chat_panel);
		chat_panel.setLayout(null);

		message = new JTextField();
		message.setBounds(10, 419, 362, 28);
		chat_panel.add(message);
		message.setColumns(10);
		message.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					try {
						if (!message.getText().equals("")) {
							getUcl().send(new Message("[" + getUcl().getKuerzel() + "] ", message.getText() + "\n"));
							message.setText("");
						}
					} catch (RemoteException e1) {
						// TODO eigene Nachrichten exception
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		JButton message_send = new JButton("Send");
		message_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!message.getText().equals("")) {
						getUcl().send(new Message("[" + getUcl().getKuerzel() + "] ", message.getText() + "\n"));
						message.setText("");
					}
				} catch (RemoteException e1) {
					// TODO Nachricht nicht senden exception
				}
			}
		});
		message_send.setBounds(374, 420, 77, 26);
		chat_panel.add(message_send);

		chattext = new JTextPane();
		chattext.setEditable(false);
		chattext.setBackground(new Color(214, 217, 223));

		JScrollPane scrollPane = new JScrollPane(chattext);
		scrollPane.setBounds(10, 6, 441, 407);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});
		chat_panel.add(scrollPane);

		JPanel time_panel = new JPanel();
		tabbedPanel.addTab("Arbeitszeiten", null, time_panel, null);
		time_panel.setLayout(null);

		JLabel tabel_label = new JLabel("Tabel");
		tabel_label.setBounds(44, 6, 55, 16);
		time_panel.add(tabel_label);

		JLabel chart_label = new JLabel("Chart");
		chart_label.setBounds(44, 245, 55, 16);
		time_panel.add(chart_label);

		JSeparator separator_17 = new JSeparator();
		separator_17.setBounds(7, 17, 33, 7);
		time_panel.add(separator_17);

		JSeparator separator_16 = new JSeparator();
		separator_16.setBounds(79, 17, 891, 16);
		time_panel.add(separator_16);

		JSeparator separator_18 = new JSeparator();
		separator_18.setOrientation(SwingConstants.VERTICAL);
		separator_18.setBounds(5, 17, 18, 191);
		time_panel.add(separator_18);

		JSeparator separator_19 = new JSeparator();
		separator_19.setBounds(7, 206, 963, 16);
		time_panel.add(separator_19);

		JSeparator separator_20 = new JSeparator();
		separator_20.setOrientation(SwingConstants.VERTICAL);
		separator_20.setBounds(968, 17, 2, 191);
		time_panel.add(separator_20);

		JSeparator separator_21 = new JSeparator();
		separator_21.setBounds(7, 482, 963, 16);
		time_panel.add(separator_21);

		JSeparator separator_22 = new JSeparator();
		separator_22.setOrientation(SwingConstants.VERTICAL);
		separator_22.setBounds(6, 255, 18, 228);
		time_panel.add(separator_22);

		JSeparator separator_23 = new JSeparator();
		separator_23.setOrientation(SwingConstants.VERTICAL);
		separator_23.setBounds(968, 255, 18, 228);
		time_panel.add(separator_23);

		JSeparator separator_24 = new JSeparator();
		separator_24.setBounds(79, 254, 891, 16);
		time_panel.add(separator_24);

		JSeparator separator_25 = new JSeparator();
		separator_25.setBounds(7, 254, 33, 7);
		time_panel.add(separator_25);

		JPanel table_panel = new JPanel();
		table_panel.setBounds(6, 26, 964, 181);
		time_panel.add(table_panel);
		table_panel.setLayout(null);

		Object rowData[][] = new String[ucl.getArbeitszeit().size()][];
		for (int i = 0; i < ucl.getArbeitszeit().size(); i++) {
			ArrayList<String> row = ucl.getArbeitszeit().get(i);
			rowData[i] = row.toArray(new String[row.size()]);
		}
		Object columnNames[] = { "Date", "Morning", "Lunch", "Evening", "Total" };

		JTable time_tabel = new JTable(rowData, columnNames);
		time_tabel.setEnabled(false);

		String[][] daten = new String[ucl.getArbeitszeit().size()][];
		for (int i = 0; i < ucl.getArbeitszeit().size(); i++) {
			ArrayList<String> row = ucl.getArbeitszeit().get(i);
			daten[i] = row.toArray(new String[row.size()]);
		}
		time_tabel.setModel(new DefaultTableModel(daten, new String[] { "Date", "Morning", "Lunch", "Noon", "Evening", "Total" }));
		time_tabel.setFillsViewportHeight(true);
		time_tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		time_tabel.setShowGrid(false);
		time_tabel.setShowVerticalLines(true);
		time_tabel.setShowHorizontalLines(true);
		time_tabel.setBackground(Color.WHITE);
		time_tabel.setBounds(6, 6, 952, 169);

		JTableHeader header = time_tabel.getTableHeader();
		table_panel.setLayout(new BorderLayout());
		table_panel.add(header, BorderLayout.NORTH);
		table_panel.add(time_tabel, BorderLayout.CENTER);
		table_panel.add(time_tabel);

		JPanel chart_panel = new JPanel();
		chart_panel.setBounds(7, 255, 964, 228);
		chart_panel.setLayout(new BorderLayout());

		XYSeries series = new XYSeries("Random Data");
		series.add(10, 0);
		series.add(4, 1);
		series.add(4.6, 2);
		series.add(9,3);

		XYSeriesCollection data = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart(null, "X", "Y" , data, PlotOrientation.VERTICAL, true, true, true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.getPreferredSize();

		chart_panel.add(chartPanel, BorderLayout.NORTH);

		time_panel.add(chart_panel);

		JPanel online_panel = new JPanel();
		content.add(online_panel, BorderLayout.SOUTH);
		online_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JSeparator separator = new JSeparator();
		online_panel.add(separator);

		JLabel lblYouAreOnline = new JLabel("You [" + getUcl().getYou().getName() + "] are online");
		online_panel.add(lblYouAreOnline);

		class Prozess extends TimerTask {

			@Override
			public void run() {
				msgReceiver();
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
			teamPanel.removeAll();
			List<Arbeiter> workers = UserClient.getServer().getWhoishere();
			List<Arbeiter> team = UserClient.getServer().getYourTeam(ucl.getYou().getAbteilung(), ucl.getKuerzel());

			for (Arbeiter a : team) {
				for (Arbeiter mitglied : workers) {
					if (a.getKuerzel().equals(mitglied.getKuerzel())) {
						if (a.getKuerzel().equals(ucl.getYou().getKuerzel())) {
							you = "(You)";
						}
						JLabel label = new JLabel(a.getName() + " " + a.getNachname() + " " + you, online, JLabel.LEFT);
						label.addMouseListener(new ProfileListener(this, a));
						teamPanel.add(label);
					} else {
						JLabel label = new JLabel(a.getName() + " " + a.getNachname(), offline, JLabel.LEFT);
						label.addMouseListener(new ProfileListener(this, a));
						teamPanel.add(label);
					}
				}
			}
			teamPanel.repaint();
			teamPanel.revalidate();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void msgReceiver() {
		chattext.removeAll();
		List<Message> messages = new ArrayList<Message>();
		try {
			messages = this.getUcl().getServer().returnMessages();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Message i : messages) {
			append(i.getName() + i.getMsg());
			try {
				this.getUcl().getServer().rmvPrintedMsgs();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chattext.repaint();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void append(String s) {
		try {
			Document doc = chattext.getDocument();
			doc.insertString(doc.getLength(), s, null);
		} catch (BadLocationException exc) {
			exc.printStackTrace();
		}
	}

	public static Icon loadIcon(String iconName) {
		final URL resource = View.class.getResource("/images/" + iconName);

		if (resource == null) {
			System.err.println("Error in " + View.class.getName() + ": Icon /images/" + iconName + " could not be loaded.");
			return new ImageIcon();
		}
		return new ImageIcon(resource);
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public UserClient getUcl() {
		return ucl;
	}

	public void setUcl(UserClient ucl) {
		this.ucl = ucl;
	}

}
