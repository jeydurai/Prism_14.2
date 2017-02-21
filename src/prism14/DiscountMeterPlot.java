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
public class DiscountMeterPlot extends ApplicationFrame{
    public DiscountMeterPlot (String s){
        super(s);
    }
        public JFreeChart createChart(ValueDataset valuedataset, double minValue, double rEnd,
        		double oStart, double oEnd, double gStart, double maxValue){
            MeterPlot meterPlot = new MeterPlot(valuedataset);
    		GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(255, 255, 255), 
    				100, 200, new Color(170, 170, 220), false);
            meterPlot.setRange(new Range(0D, 90D));
            meterPlot.addInterval(new MeterInterval("", 
    	    		new Range(0D, 59D),	new Color(0,255,50), new BasicStroke(7.0F),null));
            meterPlot.addInterval(new MeterInterval("", 
    	    		new Range(60D, 69D), Color.YELLOW, new BasicStroke(7.0F),null));
            meterPlot.addInterval(new MeterInterval("", 
    	    		new Range(70D, 79D),Color.ORANGE, new BasicStroke(7.0F),null));
            meterPlot.addInterval(new MeterInterval("", 
    	    		new Range(80D, 90D),Color.RED, new BasicStroke(7.0F),null));
            meterPlot.setNeedlePaint(Color.BLACK);
            meterPlot.setDialBackgroundPaint(gradientPaint);
            meterPlot.setDialOutlinePaint(null);
            meterPlot.setDialShape(DialShape.CIRCLE);
            meterPlot.setMeterAngle(180);
            meterPlot.setTickLabelsVisible(true);
            meterPlot.setTickLabelFont(new Font("Arial",1,10));
            meterPlot.setTickLabelPaint(Color.DARK_GRAY);
            meterPlot.setTickSize(5D);
            meterPlot.setTickPaint(Color.LIGHT_GRAY);
            meterPlot.setValuePaint(Color.DARK_GRAY);
//            meterPlot.setValueFont(new Font("Arial Black",1,20));
            meterPlot.setValueFont(new Font("Verdana",1,24));
            meterPlot.setUnits("%");
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
