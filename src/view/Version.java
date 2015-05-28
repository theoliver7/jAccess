package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;

public class Version extends JDialog {

	private static final long serialVersionUID = -7309734233122492107L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Version() {
		setTitle("Version");
		setBounds(100, 100, 253, 216);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 237, 152);
			panel.setLayout(null);
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.add(panel);
			{
				JLabel label = new JLabel("Version");
				label.setFont(new Font("SansSerif", Font.BOLD, 15));
				label.setBounds(10, 11, 60, 26);
				panel.add(label);
			}
			{
				JSeparator separator = new JSeparator();
				separator.setBounds(10, 35, 217, 2);
				panel.add(separator);
			}
			{
				JLabel label = new JLabel("jAccess");
				label.setBounds(69, 17, 55, 16);
				panel.add(label);
			}
			
			JLabel lblVersion = new JLabel("Version");
			lblVersion.setFont(new Font("SansSerif", Font.ITALIC, 12));
			lblVersion.setBounds(10, 50, 46, 14);
			panel.add(lblVersion);
			
			JLabel lblJaccessVersion = new JLabel("jAccess Version 1.0");
			lblJaccessVersion.setBounds(20, 75, 207, 16);
			panel.add(lblJaccessVersion);
			
			JLabel lblJavaVersion = new JLabel("Java Version " + System.getProperty("java.version"));
			lblJavaVersion.setBounds(20, 102, 207, 16);
			panel.add(lblJavaVersion);
		}
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
