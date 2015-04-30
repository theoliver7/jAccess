package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
import java.awt.Canvas;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.JToggleButton;

public class View2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View2 frame = new View2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View2() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 992, 651);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		
		JLabel lblJaccess = new JLabel("jAccess");
		lblJaccess.setFont(new Font("Arial", Font.ITALIC, 30));
		panel.add(lblJaccess);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Übersicht", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblHi = new JLabel("Hi");
		lblHi.setBounds(274, 139, 55, 16);
		panel_2.add(lblHi);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Arbeitszeiten", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblPeter = new JLabel("Peter");
		lblPeter.setBounds(221, 157, 55, 16);
		panel_3.add(lblPeter);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblYouAreOnline = new JLabel("You are online");
		panel_1.add(lblYouAreOnline);
		
		JSeparator separator = new JSeparator();
		panel_1.add(separator);
	}
}
