package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demo showing a dataset created using the {@link XYSeriesCollection}
 * class.
 *
 */
public class graph extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A demonstration application showing an XY series containing a null value.
	 *
	 * @param title
	 *            the frame title.
	 */
	public graph(final String title) {

		super(title);
		final XYSeries series = new XYSeries("Random Data");
		series.add(1.0, 500.2);
		series.add(5.0, 694.1);
		series.add(4.0, 100.0);
		series.add(12.5, 734.4);
		series.add(17.3, 453.2);
		series.add(21.2, 500.2);
		series.add(21.9, null);
		series.add(25.6, 734.4);
		series.add(30.0, 453.2);
		final XYSeriesCollection data = new XYSeriesCollection(series);
		final JFreeChart chart = ChartFactory.createXYLineChart("XY Series Demo", "X", "Y", data, PlotOrientation.VERTICAL, true, true, false);

		final ChartPanel chartPanel = new ChartPanel(chart);
		setContentPane(chartPanel);

	}

	/**
	 * Starting point for the demonstration application.
	 *
	 * @param args
	 *            ignored.
	 */
	public static void main(final String[] args) {

		final graph demo = new graph("XY Series Demo");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}
