package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.ListSelectionModel;

import client.UserClient;

public class View2 extends JFrame {

	private static final long serialVersionUID = 4701804783439921041L;
	private JPanel content;
	private JTextField message;
	private JTable time_tabel;

	/**
	 * Create the frame.
	 */
	public View2(UserClient ucl) {
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

		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);

		JMenuItem mntmAccount = new JMenuItem("Account");
		mnSettings.add(mntmAccount);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmVersion = new JMenuItem("About");
		mnHelp.add(mntmVersion);
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

		JPanel youteam_panel = new JPanel();
		youteam_panel.setBounds(7, 26, 215, 458);
		overview.add(youteam_panel);
		youteam_panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(16, 18, 55, 16);
		youteam_panel.add(lblNewLabel);

		JLabel label = new JLabel("New label");
		label.setBounds(16, 46, 55, 16);
		youteam_panel.add(label);

		JLabel label_1 = new JLabel("New label");
		label_1.setBounds(16, 74, 55, 16);
		youteam_panel.add(label_1);

		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(16, 102, 55, 16);
		youteam_panel.add(label_2);

		JLabel label_3 = new JLabel("New label");
		label_3.setBounds(16, 130, 55, 16);
		youteam_panel.add(label_3);

		JPanel profile_panel = new JPanel();
		profile_panel.setBounds(234, 26, 267, 458);
		overview.add(profile_panel);
		profile_panel.setLayout(null);

		JLabel name = new JLabel("New label");
		name.setBounds(16, 18, 55, 16);
		profile_panel.add(name);

		JLabel vorname = new JLabel("New label");
		vorname.setBounds(16, 46, 55, 16);
		profile_panel.add(vorname);

		JLabel kuerzel = new JLabel("New label");
		kuerzel.setBounds(16, 102, 55, 16);
		profile_panel.add(kuerzel);

		JLabel wohnort = new JLabel("New label");
		wohnort.setBounds(16, 74, 55, 16);
		profile_panel.add(wohnort);

		JLabel funktion = new JLabel("New label");
		funktion.setBounds(16, 130, 55, 16);
		profile_panel.add(funktion);

		JPanel chat_panel = new JPanel();
		chat_panel.setBounds(513, 26, 457, 458);
		overview.add(chat_panel);
		chat_panel.setLayout(null);

		message = new JTextField();
		message.setBounds(6, 424, 377, 28);
		chat_panel.add(message);
		message.setColumns(10);

		JButton message_send = new JButton("Send");
		message_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		message_send.setBounds(385, 424, 66, 28);
		chat_panel.add(message_send);

		ScrollPane textPane = new ScrollPane();
		textPane.setBackground(SystemColor.inactiveCaption);
		textPane.setBounds(10, 22, 441, 390);
		chat_panel.add(textPane);

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

		Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3","Row1-Column1", "Row1-Column2", "Row1-Column3"  },
				{ "Row1-Column1", "Row1-Column2", "Row1-Column3" ,"Row2-Column1", "Row2-Column2", "Row2-Column3" } };
		Object columnNames[] = { "Date", "Morning", "Lunch","Evening", "Total" };

		JTable time_tabel = new JTable(rowData, columnNames);
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
		time_panel.add(chart_panel);

		JPanel online_panel = new JPanel();
		content.add(online_panel, BorderLayout.SOUTH);
		online_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JSeparator separator = new JSeparator();
		online_panel.add(separator);

		JLabel lblYouAreOnline = new JLabel("You [" + ucl.getKuerzel() + "] are online");
		online_panel.add(lblYouAreOnline);

	}
}
