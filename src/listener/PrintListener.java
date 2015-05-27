package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.print.PrintQuality;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser c = new JFileChooser();
		File file = new File(System.getProperty("user.home") + "/jAccess/");
		if (!file.exists()) {
			file = new File(System.getProperty("user.home"));
		}
		c.setSelectedFile(file);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
		c.setAcceptAllFileFilterUsed(false);
		c.setFileFilter(filter);
		c.setCurrentDirectory(file);
		int rVal = c.showOpenDialog(null);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			file = c.getSelectedFile();

			FileInputStream textStream = null;
			try {
				textStream = new FileInputStream(file);
			} catch (FileNotFoundException e2) {
				JOptionPane.showMessageDialog(null, "Datei nicht gefunden!", "Fehler", JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}

			final HashPrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
			attrs.add(OrientationRequested.LANDSCAPE);
			
			final DocAttributeSet docattrs = new HashDocAttributeSet();
			docattrs.add(OrientationRequested.LANDSCAPE);

			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			Doc mydoc = new SimpleDoc(textStream, flavor, docattrs);

			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
			PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, attrs);

			if (services.length == 0) {
				if (defaultService == null) {
					// no printer found

				} else {
					// print using default
					DocPrintJob job = defaultService.createPrintJob();
					try {
						job.print(mydoc, attrs);
					} catch (PrintException e1) {
						e1.printStackTrace();
					}

				}

			} else {
				PrintService service = ServiceUI.printDialog(null, 200, 200, services, defaultService, flavor, attrs);

				if (service != null) {
					DocPrintJob job = service.createPrintJob();
					try {
						job.print(mydoc, attrs);
					} catch (PrintException e1) {
						e1.printStackTrace();
					}
				}

			}
		}
	}
}
