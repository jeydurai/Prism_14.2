package prism14;

import java.awt.Color;
import java.awt.GradientPaint;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.Font;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.AbstractCategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.renderer.category.AbstractCategoryItemRenderer;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.TextAnchor;

//import com.lowagie.text.Font;
//import com.lowagie.text.FontFactory;



@SuppressWarnings("serial")
public class HorizontalBarChartMetric extends HorizontalBarChart{
	public HorizontalBarChartMetric(String title) {
		super(title);
	}
	public JFreeChart drawChart(CategoryDataset dataset){
	    JFreeChart chart = createChart(dataset);
	    chart.setBackgroundPaint(null);
	    chart.getCategoryPlot().setBackgroundPaint(null);
	    chart.getCategoryPlot().setOutlinePaint(null);
	    chart.getCategoryPlot().setDomainGridlinePaint(Color.white);
	    chart.setBorderVisible(false);
	    chart.removeLegend();
	    CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
	    ValueAxis rangeAxis = chart.getCategoryPlot().getRangeAxis();
	    domainAxis.setAxisLineVisible(false);
	    domainAxis.setTickLabelsVisible(false);
	    domainAxis.setTickMarksVisible(false);
		rangeAxis.setAxisLineVisible(false);
		rangeAxis.setMinorTickMarksVisible(false);
		rangeAxis.setTickLabelsVisible(false);
		rangeAxis.setTickMarksVisible(false);
		rangeAxis.setUpperMargin(0.2);
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
		renderer.setDrawBarOutline(false);
		renderer.setShadowVisible(true);
		GradientPaint gp0 = new GradientPaint(0.0f,0.0f,Color.blue,0.0f,0.0f,new Color(0,0,64));
		renderer.setSeriesPaint(0, gp0);
		chart.getCategoryPlot().setRenderer(renderer);

		CategoryItemRenderer ren = chart.getCategoryPlot().getRenderer();
		DecimalFormat dFormat = new DecimalFormat("##.##");
		CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator("{2}",dFormat);
		ren.setBaseItemLabelGenerator(generator);
		ren.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3,TextAnchor.TOP_CENTER));
//		ren.setBaseItemLabelsVisible(true);

		return chart;
	}
}
