package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.View;

public class FileListener implements ActionListener {

	private View view;

	public FileListener(View view) {
		this.setView(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// the file path
			File dir = new File(System.getProperty("user.home") + "/jAccess");
			if (!dir.exists()) {
				boolean result = false;

				try {
					dir.mkdir();
					result = true;
				} catch (SecurityException se) {

				}
			}
			
			String filename = null;
			String curdir = null;

			JFileChooser c = new JFileChooser();
			File file = new File(System.getProperty("user.home") + "/jAccess/Zeitnachweis.txt");
			c.setSelectedFile(file);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
			c.setAcceptAllFileFilterUsed(false);
			c.setFileFilter(filter);
			c.setCurrentDirectory(file);
			int rVal = c.showSaveDialog(this.getView());
			if (rVal == JFileChooser.APPROVE_OPTION) {
				filename = c.getSelectedFile().getName();
				curdir = c.getCurrentDirectory().toString();
				file = new File(curdir + "/" + filename);
				
				FileWriter filewriter = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bufferdwriter = new BufferedWriter(filewriter);
				bufferdwriter.write(System.getProperty("line.separator"));
				bufferdwriter.write("Arbeitszeiten von " + this.getView().getUcl().getYou().getName() + " " +
				this.getView().getUcl().getYou().getNachname() + "\t\t\t\t\t JAccess");
				bufferdwriter.write(System.getProperty("line.separator"));
				bufferdwriter.write(this.getView().getUcl().getYou().getAbteilung());
				bufferdwriter.write(System.getProperty("line.separator"));
				bufferdwriter.write(this.getView().getUcl().getYou().getWohnort());
				bufferdwriter.write(System.getProperty("line.separator"));
				bufferdwriter.write(System.getProperty("line.separator"));
				bufferdwriter.write(System.getProperty("line.separator"));

				bufferdwriter.write("________________________________________________________________________________________________________");
				bufferdwriter.write(System.getProperty("line.separator"));
				bufferdwriter.write(System.getProperty("line.separator"));

				// loop for jtable rows
				for (int i = 0; i < this.getView().time_table_1.getRowCount(); i++) {
					// loop for jtable column
					for (int j = 0; j < this.getView().time_table_1.getColumnCount(); j++) {
						bufferdwriter.write("   ");
						if (this.getView().time_table_1.getModel().getValueAt(i, j) != null) {
							bufferdwriter.write(this.getView().time_table_1.getModel().getValueAt(i, j) + " ");
							bufferdwriter.write("   ");
						}

					}
					// break line at the begin
					// break line at the end
					bufferdwriter.write(System.getProperty("line.separator"));

				}
				// close BufferedWriter
				bufferdwriter.write("________________________________________________________________________________________________________");
				bufferdwriter.close();
				// close FileWriter
				filewriter.close();
				JOptionPane.showMessageDialog(null, "Zeitnachweisblatt wurde in den Desktop exportiert");
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				
			}
			
			System.out.println(file.toString());
			// if the file not exist create one
			if (!file.exists()) {
				file.createNewFile();
			}


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
}
