package prism14;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.Range;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class YokePlot extends ApplicationFrame{
    public YokePlot (String s){
        super(s);
    }
        public JFreeChart createChart(ValueDataset valuedataset, double minValue, double rEnd,
        		double oStart, double oEnd, double gStart, double maxValue){
            MeterPlot meterPlot = new MeterPlot(valuedataset);
            meterPlot.setRange(new Range(0D, 4D));
            meterPlot.addInterval(new MeterInterval("", 
    	    		new Range(0D, 4D),	new Color(50,50,50), new BasicStroke(7.0F),null));
            meterPlot.setNeedlePaint(Color.RED);
            meterPlot.setDialBackgroundPaint(null);
            meterPlot.setDialOutlinePaint(null);
            meterPlot.setDialShape(DialShape.CIRCLE);
            meterPlot.setMeterAngle(110);
            meterPlot.setTickLabelsVisible(false);
            meterPlot.setTickSize(1D);
            meterPlot.setTickPaint(new Color(50,50,50));
            meterPlot.setUnits("");
            meterPlot.setOutlineStroke(new BasicStroke(20.0F));
            meterPlot.setOutlinePaint(Color.BLACK);
            meterPlot.setBackgroundAlpha(1.0F);
    		JFreeChart jFreeChart = new JFreeChart(meterPlot);
                    jFreeChart.setBackgroundPaint(null);
                    jFreeChart.setTextAntiAlias(true);
                    jFreeChart.setTitle(new TextTitle("", new Font("Arial", Font.BOLD, 16)));
                    
            return jFreeChart;
        }
}
