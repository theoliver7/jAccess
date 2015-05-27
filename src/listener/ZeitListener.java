package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.Zeit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import view.View;
import client.UserClient;

public class ZeitListener implements ActionListener {

	private View view;
	private int count;
	
	public ZeitListener(View view, int count) {
		this.setView(view);
		this.setCount(count);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		this.getView().chart_panel.removeAll();
		
		int totalzeahler = 5;
		int tagzeahler = 0;
		String total = "";
		Double emptydouble = 0.0;
		Double novalues = 0.0;
		this.getView().series.clear();
		this.getView().time_table_1.setModel(new DefaultTableModel());
		Zeit z1 = new Zeit();
		this.getView().cal.set(Calendar.MONTH, +1);
		this.getView().month = this.getView().month + this.getCount();
		
		if(this.getView().month < 1) {
			this.getView().month = 12;
			this.getView().year--;
		}
		else if(this.getView().month > 12) {
			this.getView().month = 1;
			this.getView().year++;
		}
		
		try {
			if (!(UserClient.getServer().getWorktimesMonth(this.getView().getUcl().getYou().getIdarbeiter(), this.getView().month, 
					this.getView().year).isEmpty())) {
				this.getView().getUcl().setArbeitszeit(z1.totalberechnen(z1.zeitenorganisieren(UserClient.getServer().getWorktimesMonth(
						this.getView().getUcl().getYou().getIdarbeiter(), this.getView().month, this.getView().year))));
			} else {
				this.getView().getUcl().setArbeitszeit(new ArrayList<ArrayList<String>>());
				ArrayList<ArrayList<String>> notimes = new ArrayList<ArrayList<String>>();
				ArrayList<String> row = new ArrayList<String>();
				row.add("Noch keine Arbeitszeiten!");
				notimes.add(row);
				this.getView().getUcl().setArbeitszeit(notimes);
			}

		} catch (RemoteException | SQLException | ParseException | IndexOutOfBoundsException e) {
			ArrayList<ArrayList<String>> notimes = new ArrayList<ArrayList<String>>();
			ArrayList<String> row = new ArrayList<String>();
			row.add("Noch keine Arbeitszeiten!");
			notimes.add(row);
			this.getView().getUcl().setArbeitszeit(notimes);
		}

		String[][] datenletzer = new String[this.getView().getUcl().getArbeitszeit().size()][];
		ArrayList<String> row = new ArrayList<String>();
		for (int i = 0; i < this.getView().getUcl().getArbeitszeit().size(); i++) {
			row = this.getView().getUcl().getArbeitszeit().get(i);
			datenletzer[i] = row.toArray(new String[row.size()]);
		}
		this.getView().time_table_1 = new JTable();
		this.getView().time_table_1.setModel(new DefaultTableModel(datenletzer, new String[] { "Datum", "Morgen", "Mittag", "Nachmittag", "Abend", "Total" }));
		this.getView().time_table_1.getColumnModel().getColumn(1).setPreferredWidth(120);
		this.getView().scrollPane_1.setViewportView(this.getView().time_table_1);

		for (int i = 0; i < this.getView().getUcl().getArbeitszeit().size(); i++) {
			try {
				total = this.getView().getUcl().getArbeitszeit().get(tagzeahler).get(totalzeahler);
				total = total.replace(':', '.');
				Double toal_float = Double.valueOf(total);
				this.getView().series.add(emptydouble, toal_float);
				emptydouble++;
				tagzeahler = tagzeahler + 1;

			} catch (IndexOutOfBoundsException e) {
				this.getView().series.add(emptydouble, novalues);
				emptydouble++;
				tagzeahler = tagzeahler + 1;
			}
		}
		this.getView().chartPanel.removeAll();
		XYSeriesCollection data1 = new XYSeriesCollection(this.getView().series);
		JFreeChart chart = ChartFactory.createXYLineChart(null, "Days", "Hours", data1, PlotOrientation.VERTICAL, true, true, true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.getPreferredSize();
		chartPanel.revalidate();
		chartPanel.repaint();
		
		this.getView().datumLabel.setText(this.getView().month + "." + this.getView().year);
		this.getView().datumLabel.revalidate();
		this.getView().datumLabel.repaint();

		this.getView().chart_panel.add(chartPanel);
		this.getView().chart_panel.revalidate();
		this.getView().chart_panel.repaint();

		this.getView().time_panel.add(this.getView().chart_panel);
		this.getView().time_panel.repaint();
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
