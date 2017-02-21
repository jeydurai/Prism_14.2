package prism14;
import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

public class ThermoMeter extends ApplicationFrame{

    public ThermoMeter(final String title){
        super(title);
    }
    public JFreeChart createChart(ValueDataset valuedataset) {
        ThermometerPlot tPlot = new ThermometerPlot(valuedataset);
        tPlot.setRange(0.0, 100.0);
        tPlot.setGap(5);
        tPlot.setUnits(ThermometerPlot.UNITS_NONE);
        tPlot.setBulbRadius(30);
        tPlot.setColumnRadius(10);
        tPlot.setValuePaint(Color.white);
        tPlot.setThermometerStroke(new BasicStroke(1.0f));
        tPlot.setThermometerPaint(Color.darkGray);
        tPlot.setBackgroundPaint(null);
        tPlot.setOutlineVisible(false);
        tPlot.setAxisLocation(2);
        tPlot.setSubrangePaint(0, Color.RED);
        tPlot.setSubrange(0, 0, 80);
        tPlot.setSubrangePaint(1, Color.ORANGE);
        tPlot.setSubrange(1, 80, 95);
        tPlot.setSubrangePaint(2, Color.GREEN.darker());
        tPlot.setSubrange(2, 95, 100);
        JFreeChart jFreeChart = new JFreeChart(tPlot);
        jFreeChart.setBackgroundPaint(null);
        jFreeChart.setBorderVisible(false);
        return jFreeChart;
    }
	
}
