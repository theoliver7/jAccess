package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class About extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public About() {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setResizable(false);
		setTitle("About - \u00DCber");
		setBounds(100, 100, 344, 266);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAbout = new JLabel("About");
		lblAbout.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblAbout.setBounds(10, 11, 105, 26);
		contentPanel.add(lblAbout);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 35, 307, 2);
		contentPanel.add(separator);
		
		JLabel lblJaccess = new JLabel("jAccess");
		lblJaccess.setBounds(60, 17, 55, 16);
		contentPanel.add(lblJaccess);
		
		JLabel lblEntwickler = new JLabel("Entwickler");
		lblEntwickler.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lblEntwickler.setBounds(10, 48, 105, 16);
		contentPanel.add(lblEntwickler);
		
		JLabel lblNewLabel = new JLabel("Oliver Aschwanden, oliver.aschwanden@bbcag.ch");
		lblNewLabel.setBounds(20, 76, 307, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nico Fehr, nico.fehr@bbcag.ch");
		lblNewLabel_1.setBounds(22, 103, 295, 16);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblJaccessWurdeEntwickelt = new JLabel("jAccess wurde Entwickelt mit:");
		lblJaccessWurdeEntwickelt.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lblJaccessWurdeEntwickelt.setBounds(10, 142, 295, 16);
		contentPanel.add(lblJaccessWurdeEntwickelt);
		
		JLabel lblJava = new JLabel("Java, SQL");
		lblJava.setBounds(24, 169, 179, 16);
		contentPanel.add(lblJava);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);						
					}
					
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
