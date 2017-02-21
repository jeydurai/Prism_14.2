package prism14;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class HorizontalBarChart extends ApplicationFrame{
	public HorizontalBarChart (String title) {
		super(title);
	}
	public JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart("", "", "", dataset,
				PlotOrientation.HORIZONTAL, true, false, false);
		return chart;
	}

}
