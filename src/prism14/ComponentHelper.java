
package prism14;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.StandardGradientPaintTransformer;


public final class ComponentHelper {
	
    public static int fontSize = 11;
    public static String fontFamily = "Arial";
    public static String fontSpecialFamily = "Verdana";

	public static JPanel getLegendColorRange(ImageIcon redMark, 
			ImageIcon yellowMark, ImageIcon greenMark, Font font, 
			RatioBoundaries rBoundary) {
		JLabel jlRedMark, jlYellowMark, jlGreenMark;
		JLabel jlRedText = null, jlYellowText = null, jlGreenText = null;
		JPanel panel = new JPanel();

		jlRedMark = new JLabel(redMark);
		jlRedMark.setPreferredSize(new Dimension(15,15));
		jlRedMark.setOpaque(false);
		panel.add(jlRedMark);

		if (rBoundary.getCompareMethod().
				equals(GeneralConstants.ComparePoint.RED_WHEN_LESS)) {
			jlRedText = new JLabel("<" + new DecimalFormat("###%").format(rBoundary.getLowerBoundary()));
			jlYellowText = new JLabel(">=" + new DecimalFormat("###%").format(rBoundary.getLowerBoundary()) +
					" ~ <" + new DecimalFormat("###%").format(rBoundary.getHigherBoundary()));
			jlGreenText = new JLabel(">=" + new DecimalFormat("###%").format(rBoundary.getHigherBoundary()));
		} else if (rBoundary.getCompareMethod().
				equals(GeneralConstants.ComparePoint.GREEN_WHEN_LESS)) {
			jlRedText = new JLabel(">=" + new DecimalFormat("###%").format(rBoundary.getHigherBoundary()));
			jlYellowText = new JLabel(">=" + new DecimalFormat("###%").format(rBoundary.getLowerBoundary()) +
					" ~ <" + new DecimalFormat("###%").format(rBoundary.getHigherBoundary()));
			jlGreenText = new JLabel("<" + new DecimalFormat("###%").format(rBoundary.getLowerBoundary()));
		} else if (rBoundary.getCompareMethod().
				equals(GeneralConstants.ComparePoint.RED_WHEN_LESS_THAN_EQUAL)) {
			jlRedText = new JLabel("<=" + new DecimalFormat("###%").format(rBoundary.getLowerBoundary()));
			jlYellowText = new JLabel(">" + new DecimalFormat("###%").format(rBoundary.getLowerBoundary()) +
					" ~ =<" + new DecimalFormat("###%").format(rBoundary.getHigherBoundary()));
			jlGreenText = new JLabel(">" + new DecimalFormat("###%").format(rBoundary.getHigherBoundary()));
		} else if (rBoundary.getCompareMethod().
				equals(GeneralConstants.ComparePoint.GREEN_WHEN_LESS_THAN_EQUAL)) {
			jlRedText = new JLabel(">" + new DecimalFormat("###%").format(rBoundary.getHigherBoundary()));
			jlYellowText = new JLabel(">" + new DecimalFormat("###%").format(rBoundary.getLowerBoundary()) +
					" ~ =<" + new DecimalFormat("###%").format(rBoundary.getHigherBoundary()));
			jlGreenText = new JLabel("=<" + new DecimalFormat("###%").format(rBoundary.getLowerBoundary()));
		} else if (rBoundary.getCompareMethod().
				equals(GeneralConstants.ComparePoint.RED_WHEN_LESS_THAN_EQUAL_AND_GREATER_THAN_EQUAL_TO)) {
			jlRedText = new JLabel("<=" + new DecimalFormat("###%").format(rBoundary.getLowerBoundary()));
			jlYellowText = new JLabel(">" + new DecimalFormat("###%").format(rBoundary.getLowerBoundary()) +
					" ~ <" + new DecimalFormat("###%").format(rBoundary.getHigherBoundary()));
			jlGreenText = new JLabel(">=" + new DecimalFormat("###%").format(rBoundary.getHigherBoundary()));
		}

		
		jlRedText.setPreferredSize(new Dimension(40,20));
		jlRedText.setForeground(Color.DARK_GRAY);
		jlRedText.setFont(font);
		jlRedText.setOpaque(false);
		panel.add(jlRedText);

		jlYellowMark = new JLabel(yellowMark);
		jlYellowMark.setPreferredSize(new Dimension(15,15));
		jlYellowMark.setOpaque(false);
		panel.add(jlYellowMark);

		jlYellowText.setPreferredSize(new Dimension(90,20));
		jlYellowText.setForeground(Color.DARK_GRAY);
		jlYellowText.setFont(font);
		jlYellowText.setOpaque(false);
		panel.add(jlYellowText);
	    
		jlGreenMark = new JLabel(greenMark);
		jlGreenMark.setPreferredSize(new Dimension(15,15));
		jlGreenMark.setOpaque(false);
		panel.add(jlGreenMark);
	    
		jlGreenText.setPreferredSize(new Dimension(40,20));
		jlGreenText.setForeground(Color.DARK_GRAY);
		jlGreenText.setFont(font);
		jlGreenText.setOpaque(false);
		panel.add(jlGreenText);
		
		return panel;
	}


	public static JPanel getLegendColorRangeNonPercentage(ImageIcon redMark, 
			ImageIcon yellowMark, ImageIcon greenMark, Font font, 
			RatioBoundaries rBoundary) {
		JLabel jlRedMark, jlYellowMark, jlGreenMark;
		JLabel jlRedText = null, jlYellowText = null, jlGreenText = null;
		JPanel panel = new JPanel();

		jlRedMark = new JLabel(redMark);
		jlRedMark.setPreferredSize(new Dimension(15,15));
		jlRedMark.setOpaque(false);
		panel.add(jlRedMark);

		if (rBoundary.getCompareMethod().
				equals(GeneralConstants.ComparePoint.RED_WHEN_LESS)) {
			jlRedText = new JLabel("<" + new DecimalFormat("###.##").format(rBoundary.getLowerBoundary()));
			jlYellowText = new JLabel(">=" + new DecimalFormat("###.##").format(rBoundary.getLowerBoundary()) +
					" ~ <" + new DecimalFormat("###.##").format(rBoundary.getHigherBoundary()));
			jlGreenText = new JLabel(">=" + new DecimalFormat("###.##").format(rBoundary.getHigherBoundary()));
		} else if (rBoundary.getCompareMethod().
				equals(GeneralConstants.ComparePoint.GREEN_WHEN_LESS)) {
			jlRedText = new JLabel(">=" + new DecimalFormat("###.##").format(rBoundary.getHigherBoundary()));
			jlYellowText = new JLabel(">=" + new DecimalFormat("###.##").format(rBoundary.getLowerBoundary()) +
					" ~ <" + new DecimalFormat("###.##").format(rBoundary.getHigherBoundary()));
			jlGreenText = new JLabel("<" + new DecimalFormat("###.##").format(rBoundary.getLowerBoundary()));
		} else if (rBoundary.getCompareMethod().
				equals(GeneralConstants.ComparePoint.RED_WHEN_LESS_THAN_EQUAL)) {
			jlRedText = new JLabel("<=" + new DecimalFormat("###.##").format(rBoundary.getLowerBoundary()));
			jlYellowText = new JLabel(">" + new DecimalFormat("###.##").format(rBoundary.getLowerBoundary()) +
					" ~ =<" + new DecimalFormat("###.##").format(rBoundary.getHigherBoundary()));
			jlGreenText = new JLabel(">" + new DecimalFormat("###.##").format(rBoundary.getHigherBoundary()));
		} else if (rBoundary.getCompareMethod().
				equals(GeneralConstants.ComparePoint.GREEN_WHEN_LESS_THAN_EQUAL)) {
			jlRedText = new JLabel(">" + new DecimalFormat("###.##").format(rBoundary.getHigherBoundary()));
			jlYellowText = new JLabel(">" + new DecimalFormat("###.##").format(rBoundary.getLowerBoundary()) +
					" ~ =<" + new DecimalFormat("###.##").format(rBoundary.getHigherBoundary()));
			jlGreenText = new JLabel("=<" + new DecimalFormat("###.##").format(rBoundary.getLowerBoundary()));
		} else if (rBoundary.getCompareMethod().
				equals(GeneralConstants.ComparePoint.RED_WHEN_LESS_THAN_EQUAL_AND_GREATER_THAN_EQUAL_TO)) {
			jlRedText = new JLabel("<=" + new DecimalFormat("###.##").format(rBoundary.getLowerBoundary()));
			jlYellowText = new JLabel(">" + new DecimalFormat("###.##").format(rBoundary.getLowerBoundary()) +
					" ~ <" + new DecimalFormat("###.##").format(rBoundary.getHigherBoundary()));
			jlGreenText = new JLabel(">=" + new DecimalFormat("###.##").format(rBoundary.getHigherBoundary()));
		}

		
		jlRedText.setPreferredSize(new Dimension(40,20));
		jlRedText.setForeground(Color.DARK_GRAY);
		jlRedText.setFont(font);
		jlRedText.setOpaque(false);
		panel.add(jlRedText);

		jlYellowMark = new JLabel(yellowMark);
		jlYellowMark.setPreferredSize(new Dimension(15,15));
		jlYellowMark.setOpaque(false);
		panel.add(jlYellowMark);

		jlYellowText.setPreferredSize(new Dimension(90,20));
		jlYellowText.setForeground(Color.DARK_GRAY);
		jlYellowText.setFont(font);
		jlYellowText.setOpaque(false);
		panel.add(jlYellowText);
	    
		jlGreenMark = new JLabel(greenMark);
		jlGreenMark.setPreferredSize(new Dimension(15,15));
		jlGreenMark.setOpaque(false);
		panel.add(jlGreenMark);
	    
		jlGreenText.setPreferredSize(new Dimension(40,20));
		jlGreenText.setForeground(Color.DARK_GRAY);
		jlGreenText.setFont(font);
		jlGreenText.setOpaque(false);
		panel.add(jlGreenText);
		
		return panel;
	}

	
	public static JRadioButton getRadioButton (String Text, Font font, 
			int width, int height) {
		JRadioButton jrb;
		jrb = new JRadioButton(Text);
		jrb.setFont(font);
		jrb.setPreferredSize(new Dimension(width, height));
		jrb.setBackground(null);
		jrb.setOpaque(false);
		jrb.setFocusable(false);
		jrb.setEnabled(true);
		return jrb;
	}
	
	public static String getMyToolTip(double actual, double threshold) {
		String dText = "No Data";
		if (actual != 0D || threshold != 0D) {
			dText = new DecimalFormat("$ ##,###,###").format(actual) + " / " 
				+ new DecimalFormat("$ ##,###,###").format(threshold);
		} 
		return dText;
	}
	
	public static String getCWPShareHeader(double actual, double threshold, double share) {
		String dText = "No Data";
		if (actual != 0D || threshold != 0D) {
			dText = "(" + new DecimalFormat("Thou $ ##,###,###").format(CalcHelper.getValueInThousandUSD(actual)) + " / " 
				+ new DecimalFormat("Thou $ ##,###,###").format(CalcHelper.getValueInThousandUSD(threshold)) + " / "
				+ new DecimalFormat("###.#%").format(share) + ")";
		} 
		return dText;
	}
	
	
	public static String getMyThermoToolTip(double rs, double sec, double ucs, double uc, 
			double dcs, double video, double wlan, double pTechPoints, double coverage) {
		String dText="";
		String rsText, secText, ucsText, ucText, dcsText, videoText, wlanText;
		rsText = secText = ucsText = ucText = dcsText = videoText = wlanText = "ND";

		rsText = !(rs == 0D) ? new DecimalFormat("#.##").format(rs) : "ND";
		secText = !(sec == 0D) ? new DecimalFormat("#.##").format(sec) : "ND";
		ucsText = !(ucs == 0D) ? new DecimalFormat("#.##").format(ucs) : "ND";
		ucText = !(uc == 0D) ? new DecimalFormat("#.##").format(uc) : "ND";
		dcsText = !(dcs == 0D) ? new DecimalFormat("#.##").format(dcs) : "ND";
		videoText = !(video == 0D) ? new DecimalFormat("#.##").format(video) : "ND";
		wlanText = !(wlan == 0D) ? new DecimalFormat("#.##").format(wlan) : "ND";
		
		
/*		dText = "Technology Performance = \n" + "(" + rsText + 
		"+" + secText + "+"	+ ucsText + "+"	+ ucText + "+"
		+ dcsText + "+"	+ videoText + "+" + wlanText + ")/" 
		+ new DecimalFormat("#").format(pTechPoints) + " =" 
		+ new DecimalFormat("##.##%").format(coverage);*/		
		dText = "Technology Performance = \n" + "(" + rsText + 
		"+" + secText + "+"	+ ucsText + "+"	+ ucText + "+"
		+ dcsText + "+"	+ videoText + "+" + wlanText + ")/" 
		+ new DecimalFormat("#").format(7) + " =" 
		+ new DecimalFormat("##.##%").format(coverage);		

		return dText;
	}

	public static JPanel getTrafficSignal(JPanel gPanel, JPanel yPanel, JPanel rPanel, 
			JLabel label, double point) {
		JPanel panel = new JPanel();
		if (point == GeneralConstants.GREEN_POINT) {
	    	gPanel.add(label);
	    	panel = gPanel;
	    } else if (point == GeneralConstants.AMBER_POINT) {
	    	yPanel.add(label);
	    	panel = yPanel;
	    } else {
	    	rPanel.add(label);
	    	panel = rPanel;
	    }
		return panel;
	}

	public static void addMyToolTip(JLabel label, String labelText, int labelSize, 
			int labelXPos, int labelYPos, int labelWidth, int labelHeight, JPanel panel) {
		label.setText(labelText);
		label.setBounds(labelXPos, labelYPos, labelWidth, labelHeight);
		label.setFont(new Font("Verdana", Font.ITALIC,labelSize));
		panel.add(label);
		panel.repaint();
		panel.revalidate();
	}

	public static void removeMyToolTip(JLabel label, JPanel panel) {
		label.setText("");
		panel.remove(label);
		label.removeAll();
		panel.repaint();
		panel.revalidate();
	}

	public static void addMyLongerToolTip(JTextArea label, String labelText, int labelSize, 
			int labelXPos, int labelYPos, int labelWidth, int labelHeight, JPanel panel) {
		label.setText(labelText);
		label.setBounds(labelXPos, labelYPos, labelWidth, labelHeight);
		label.setFont(new Font("Verdana", Font.ITALIC,labelSize));
		panel.add(label);
		panel.repaint();
		panel.revalidate();
	}
	
	public static void removeMyLongerToolTip(JTextArea label, JPanel panel) {
		label.setText("");
		panel.remove(label);
		label.removeAll();
		panel.repaint();
		panel.revalidate();
	}
	
	public static String getSelectedListItem(JList list) {
    	int counter = 0;
    	String item = null;
		ListModel model = list.getModel();
    	for (int s = 0; s < model.getSize(); s++) {
    		if (list.isSelectedIndex(s)) {
    			item = (String)model.getElementAt(s);
    		}
    	}
    	return item;
	}
	
	public static boolean doesListContainAll(JList list) {
    	int counter = 0;
    	boolean tempBoolean;
    	for (int s = 0; s < list.getModel().getSize(); s++) {
    		if (list.isSelectedIndex(s)) {
    			counter++;
    		}
    	}
    	tempBoolean = (counter == list.getModel().getSize()) ? true : false;
    	return tempBoolean;
	}
	
	
	public static boolean doesListContainAtleastOne(JList list) {
    	int counter = 0;
    	boolean tempBoolean;
    	for (int s = 0; s < list.getModel().getSize(); s++) {
    		if (list.isSelectedIndex(s)) {
    			counter++;
    		}
    	}
    	tempBoolean = (counter == list.getModel().getSize() && 
    			counter == 1) ? true : false;
    	return tempBoolean;
	}

	public static boolean doesListContainJustOne(JList list) {
    	int counter = 0;
    	boolean tempBoolean;
    	for (int s = 0; s < list.getModel().getSize(); s++) {
    		if (list.isSelectedIndex(s)) {
    			counter++;
    		}
    	}
    	tempBoolean = (counter == 1) ? true : false;
    	return tempBoolean;
	}

	public static boolean doesListContainMultiple(JList list) {
    	int counter = 0;
    	boolean tempBoolean;
    	for (int s = 0; s < list.getModel().getSize(); s++) {
    		if (list.isSelectedIndex(s)) {
    			counter++;
    		}
    	}
    	tempBoolean = (counter > 1 && 
    			counter < list.getModel().getSize()) ? true : false;
    	return tempBoolean;
	}

	public static String[] getListInArray(JList list) {
    	int counter = 0;
    	String[] array;
    	boolean tempBoolean;
		ListModel model = list.getModel();
	        for (int s = 0; s < list.getModel().getSize(); s++) {
	    		if (list.isSelectedIndex(s)) {
	    			counter++;
	    		}
	    	}
	    	array = new String[counter];
	    	counter=0;
	    	for (int s = 0; s < list.getModel().getSize(); s++) {
	    		if (list.isSelectedIndex(s)) {
	    	        array[counter] = (String)model.getElementAt(s);
	    	        counter++;
	    		}
	    	}
    	return array;
	}

	public static boolean doesComboContainAll(JComboBox combo) {
    	boolean tempBoolean;
    	String tString = (String)combo.getSelectedItem();
    	tempBoolean = (tString.equals("ALL")) ? true : false;
    	return tempBoolean;
	}

	public static DefaultListModel getListModelFromArray(String[] array) {
		DefaultListModel lModel = new DefaultListModel();
		int arrayLength = array.length;
	    	for (int i=0; i<arrayLength; i++) {
	    			lModel.addElement(array[i]);
	    	}
	    return lModel;
	}

	public static ChartPanel getDiscountPlot(double chartValue, String title) {
	
		DiscountMeterPlot meterChart = new DiscountMeterPlot("");
		DefaultValueDataset dataset = new DefaultValueDataset((int)Math.round(chartValue));
	    JFreeChart chart = meterChart.createChart(dataset, 0D, GeneralConstants.RED_POINT_BOUNDARY,
	    		GeneralConstants.AMBER_POINT_START,GeneralConstants.AMBER_POINT_END,
	    		GeneralConstants.GREEN_POINT_BOUNDARY,100D);
	    chart.removeLegend();
	    chart.setTitle(new TextTitle(title, new Font("Arial", Font.BOLD, 18)));
	    ChartPanel cPanel = new ChartPanel(chart);
	    cPanel.setBorder(null);
	    cPanel.setBackground(null);
	    cPanel.setOpaque(false);
	    cPanel.updateUI();
	    return cPanel;
	}

	public static ChartPanel getCustomerPenetrationPlot(double chartValue, String title) {
		
		GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(255, 255, 255), 
				100, 200, new Color(150, 220, 150), false);
		DiscountMeterPlot meterChart = new DiscountMeterPlot("");
		DefaultValueDataset dataset = new DefaultValueDataset((int)Math.round(chartValue));
	    JFreeChart chart = meterChart.createChart(dataset, 0D, GeneralConstants.RED_POINT_BOUNDARY,
	    		GeneralConstants.AMBER_POINT_START,GeneralConstants.AMBER_POINT_END,
	    		GeneralConstants.GREEN_POINT_BOUNDARY,100D);
	    chart.setBackgroundPaint(null);
	    chart.removeLegend();
	    chart.setTitle(new TextTitle(title, new Font("Arial", Font.BOLD, 18)));
	    chart.getTitle().setPaint(Color.WHITE);
	    MeterPlot plot = (MeterPlot)chart.getPlot();
	    plot.setRange(new Range(0D, 6000D));
	    plot.addInterval(new MeterInterval("", 
	    		new Range(0D, 1999D),	Color.RED, new BasicStroke(7.0F),null));
	    plot.addInterval(new MeterInterval("", 
	    		new Range(2000D, 3499D), Color.ORANGE, new BasicStroke(7.0F),null));
	    plot.addInterval(new MeterInterval("", 
	    		new Range(3500D, 4079D),Color.YELLOW, new BasicStroke(7.0F),null));
	    plot.addInterval(new MeterInterval("", 
	    		new Range(4080D, 6000D),new Color(0,255,50), new BasicStroke(7.0F),null));
	    plot.setNeedlePaint(Color.BLACK);
	    plot.setDialBackgroundPaint(gradientPaint);
	    plot.setDialOutlinePaint(null);
	    plot.setDialShape(DialShape.CIRCLE);
	    plot.setMeterAngle(180);
	    plot.setTickLabelsVisible(true);
	    plot.setTickLabelFont(new Font("Arial",1,10));
	    plot.setTickLabelPaint(Color.DARK_GRAY);
	    plot.setTickSize(500D);
	    plot.setTickPaint(Color.LIGHT_GRAY);
	    plot.setValuePaint(Color.DARK_GRAY);
//	    plot.setValueFont(new Font("Arial Black",1,20));
	    plot.setValueFont(new Font("Verdana",1,24));
	    plot.setUnits("");
	    plot.setOutlineStroke(new BasicStroke(20.0F));
	    plot.setOutlinePaint(Color.BLACK);
	    plot.setBackgroundAlpha(1.0F);
	    ChartPanel cPanel = new ChartPanel(chart);
	    cPanel.setBorder(null);
	    cPanel.setBackground(null);
	    cPanel.setOpaque(false);
	    cPanel.updateUI();
	    return cPanel;
	}
	
	public static ChartPanel getTotalBookingPlot(double dataValue1, 
			double dataValue2) {
		DefaultValueDataset dataset1 = new DefaultValueDataset(dataValue1);
		DefaultValueDataset dataset2 = new DefaultValueDataset(dataValue2);
		DialPlot dialplot = new DialPlot();
		dialplot.setView(0.0D, 0.0D, 1.0D, 1.0D);
		dialplot.setDataset(0, dataset1);
		dialplot.setDataset(1, dataset2);
		StandardDialFrame standarddialframe = new StandardDialFrame();
		standarddialframe.setBackgroundPaint(Color.lightGray);
		standarddialframe.setForegroundPaint(Color.darkGray);
		dialplot.setDialFrame(standarddialframe);
		GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
		DialBackground dialbackground = new DialBackground(gradientpaint);
		dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
		dialplot.setBackground(dialbackground);
		DialTextAnnotation dialtextannotation = new DialTextAnnotation("US$ Mn");
		dialtextannotation.setFont(new Font("Dialog", 1, 14));
		dialtextannotation.setRadius(0.69999999999999996D);
		dialplot.addLayer(dialtextannotation);
		DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
		dialvalueindicator.setFont(new Font("Dialog", 0, 10));
		dialvalueindicator.setOutlinePaint(Color.darkGray);
		dialvalueindicator.setRadius(0.59999999999999998D);
		dialvalueindicator.setAngle(-103D);
		dialplot.addLayer(dialvalueindicator);
		DialValueIndicator dialvalueindicator1 = new DialValueIndicator(1);
		dialvalueindicator1.setFont(new Font("Dialog", 0, 10));
		dialvalueindicator1.setOutlinePaint(Color.red);
		dialvalueindicator1.setRadius(0.59999999999999998D);
		dialvalueindicator1.setAngle(-77D);
		dialplot.addLayer(dialvalueindicator1);
//		StandardDialScale standarddialscale = new StandardDialScale(-40D, 60D, -120D, -300D, 10D, 4);
		StandardDialScale standarddialscale = new StandardDialScale(-1D, 50D, -120D, -300D, 5D, 1);
		standarddialscale.setTickRadius(0.88D);
		standarddialscale.setTickLabelOffset(0.14999999999999999D);
		standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
		dialplot.addScale(0, standarddialscale);
//		StandardDialScale standarddialscale1 = new StandardDialScale(0.0D, 100D, -120D, -300D, 10D, 4);
		StandardDialScale standarddialscale1 = new StandardDialScale(-0.1D, 25D, -120D, -300D, 2.5D, 1);
		standarddialscale1.setTickRadius(0.5D);
		standarddialscale1.setTickLabelOffset(0.14999999999999999D);
		standarddialscale1.setTickLabelFont(new Font("Dialog", 0, 10));
		standarddialscale1.setMajorTickPaint(Color.red);
		standarddialscale1.setMinorTickPaint(Color.red);
		dialplot.addScale(1, standarddialscale1);
		dialplot.mapDatasetToScale(1, 1);
		StandardDialRange standarddialrange = new StandardDialRange(90D, 100D, Color.blue);
		standarddialrange.setScaleIndex(1);
		standarddialrange.setInnerRadius(0.58999999999999997D);
		standarddialrange.setOuterRadius(0.58999999999999997D);
		dialplot.addLayer(standarddialrange);
		org.jfree.chart.plot.dial.DialPointer.Pin pin = new org.jfree.chart.plot.dial.DialPointer.Pin(1);
		pin.setRadius(0.55000000000000004D);
		dialplot.addPointer(pin);
		org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer(0);
		dialplot.addPointer(pointer);
		DialCap dialcap = new DialCap();
		dialcap.setRadius(0.10000000000000001D);
		dialplot.setCap(dialcap);
		JFreeChart jFreeChart = new JFreeChart(dialplot);
		jFreeChart.setBackgroundPaint(null);
        jFreeChart.setTitle(new TextTitle("Total Booking", new Font("Arial", Font.BOLD, 14)));
        jFreeChart.getTitle().setPaint(Color.WHITE);
		ChartPanel cPanel = new ChartPanel(jFreeChart);
		cPanel.setBorder(null);
		cPanel.setBackground(null);
		cPanel.setOpaque(false);
		cPanel.updateUI();
		return cPanel;
	}

	public static ChartPanel getTechPenetrationPlot(double dataValue) {
		GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(50, 0, 0).brighter(), 
				0, 10, new Color(255, 10, 10).darker(), false);
	    ThermoMeter thermo = new ThermoMeter("");
	    DefaultValueDataset therDataSet = new DefaultValueDataset(dataValue);
	    JFreeChart therChart = thermo.createChart(therDataSet);
	    therChart.removeLegend();
	    therChart.setPadding(new RectangleInsets(0D,5D,1D,200D));
	    therChart.setTitle(new TextTitle("Tech.Pen.", new Font("Arial", Font.BOLD, 15)));
	    therChart.getTitle().setPaint(Color.WHITE);
	    therChart.setTextAntiAlias(true);
	    ThermometerPlot tPlot = (ThermometerPlot)therChart.getPlot();
        tPlot.setRange(0D, 11D);
        tPlot.setGap(2);
        tPlot.setUnits(ThermometerPlot.UNITS_NONE);
        tPlot.setBulbRadius(25);
        tPlot.setColumnRadius(10);
        tPlot.setValuePaint(Color.white);
        tPlot.setThermometerStroke(new BasicStroke(1.0f));
        tPlot.setThermometerPaint(Color.darkGray);
        tPlot.setBackgroundPaint(null);
        tPlot.setOutlineVisible(false);
        tPlot.setAxisLocation(2);
        tPlot.setSubrangePaint(0, gradientPaint);
        tPlot.setSubrange(0, 0, 11);
	    ChartPanel therPanel = new ChartPanel(therChart);
	    therPanel.setLayout(null);
	    therPanel.setBackground(null);
	    therPanel.setOpaque(false);
	    therPanel.setDomainZoomable(true);
	    return therPanel;
	}

	@SuppressWarnings("deprecation")
	public static ChartPanel getATRatePlot(double dataValue1, double dataValue2) {
		GradientPaint gradientPaint1 = new GradientPaint(0, 0, new Color(255, 255, 255), 
				100, 200, new Color(170, 170, 220), false);
		GradientPaint gradientPaint2 = new GradientPaint(0, 0, new Color(255, 255, 255), 
				100, 200, new Color(170, 220, 170), false);
	    DefaultPieDataset pieDataset = new DefaultPieDataset();
	    PieSectionLabelGenerator labels = new StandardPieSectionLabelGenerator("{0}:{2}", new DecimalFormat(""), new DecimalFormat("0%"));
	    pieDataset.setValue("AT", dataValue1);
	    pieDataset.setValue("Non-AT", dataValue2);
	    JFreeChart chart = ChartFactory.createPieChart("AT Attach", pieDataset, true, true, false);
	    chart.removeLegend();
	    chart.setTitle(new TextTitle("AT Attach", new Font("Arial", Font.BOLD, 14)));
	    chart.getTitle().setPaint(Color.WHITE);
	    chart.setTextAntiAlias(true);
	    chart.setBackgroundPaint(null);
	    chart.setBorderVisible(false);
	    PiePlot plot = (PiePlot)chart.getPlot();
	    plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);	 
        plot.setBackgroundPaint(null);
        plot.setOutlinePaint(null);
        plot.setSectionPaint(0, gradientPaint2);
        plot.setSectionPaint(1, gradientPaint1);
        plot.setLabelLinksVisible(true);
        plot.setExplodePercent(0, 0.3D);
        plot.setLabelGenerator(labels);
        plot.setMaximumLabelWidth(0.15D);
        ChartPanel cPanel = new ChartPanel(chart);
        cPanel.setLayout(null);
        cPanel.setBackground(null);
        cPanel.setOpaque(false);
        cPanel.setDomainZoomable(true);
	    return cPanel;
	}
	
	public static ChartPanel getTechnologySplitGraph(CategoryDataset dataset) {
	    GradientPaint gp = new GradientPaint(0.0f,0.0f,new Color(250,120,120),0.0f,0.0f,new Color(125,0,0));
		HorizontalBarChartMetric graph = new HorizontalBarChartMetric("");
		graph.setBackground(null);
	    JFreeChart chart = graph.drawChart(dataset);
	    chart.setBackgroundPaint(null);
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
		renderer.setDrawBarOutline(true);
		renderer.setShadowVisible(false);
		renderer.setSeriesPaint(0, gp);
	    ValueAxis valueAxis = chart.getCategoryPlot().getRangeAxis();
	    valueAxis.setUpperMargin(0);
	    ChartPanel cPanel = new ChartPanel(chart);
	    cPanel.setLayout(null);
	    cPanel.setBackground(null);
	    cPanel.setOpaque(false);
	    cPanel.setDomainZoomable(true);
	    return cPanel;
	}

	public static ChartPanel getArchitectureSplitGraph(CategoryDataset dataset) {
//	    GradientPaint gp = new GradientPaint(0.0f,0.0f,new Color(120,120,250),0.0f,0.0f,new Color(0,0,125));
	    GradientPaint gp = new GradientPaint(0.0f,0.0f,new Color(250,120,120),0.0f,0.0f,new Color(125,0,0));
		HorizontalBarChartMetric graph = new HorizontalBarChartMetric("");
		graph.setBackground(null);
	    JFreeChart chart = graph.drawChart(dataset);
	    chart.setBackgroundPaint(null);
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
		renderer.setDrawBarOutline(false);
		renderer.setShadowVisible(false);
		renderer.setSeriesPaint(0, gp);
	    ValueAxis valueAxis = chart.getCategoryPlot().getRangeAxis();
	    valueAxis.setUpperMargin(0);
	    ChartPanel cPanel = new ChartPanel(chart);
	    cPanel.setLayout(null);
	    cPanel.setBackground(null);
	    cPanel.setOpaque(false);
	    cPanel.setDomainZoomable(true);
	    return cPanel;
	}

	
	public static ChartPanel getQuarterSplitGraph(CategoryDataset dataset) {
//	    GradientPaint gp = new GradientPaint(0.0f,0.0f,new Color(250,250,120),0.0f,0.0f,new Color(125,0,0));
	    GradientPaint gp = new GradientPaint(0.0f,0.0f,new Color(120,250,120),0.0f,0.0f,new Color(0,125,0));
		HorizontalBarChartMetric graph = new HorizontalBarChartMetric("");
		graph.setBackground(null);
	    JFreeChart chart = graph.drawChart(dataset);
	    chart.setBackgroundPaint(null);
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
		renderer.setDrawBarOutline(false);
		renderer.setShadowVisible(false);
		renderer.setSeriesPaint(0, gp);
	    ValueAxis valueAxis = chart.getCategoryPlot().getRangeAxis();
	    valueAxis.setUpperMargin(0);
	    ChartPanel cPanel = new ChartPanel(chart);
	    cPanel.setLayout(null);
	    cPanel.setBackground(null);
	    cPanel.setOpaque(false);
	    cPanel.setDomainZoomable(true);
	    return cPanel;
	}

	public static ChartPanel getHalfYearSplitGraph(CategoryDataset dataset) {
	    GradientPaint gp = new GradientPaint(0.0f,0.0f,new Color(120,250,120),0.0f,0.0f,new Color(0,125,0));
		HorizontalBarChartMetric graph = new HorizontalBarChartMetric("");
		graph.setBackground(null);
	    JFreeChart chart = graph.drawChart(dataset);
	    chart.setBackgroundPaint(null);
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
		renderer.setDrawBarOutline(false);
		renderer.setShadowVisible(false);
		renderer.setSeriesPaint(0, gp);
	    ValueAxis valueAxis = chart.getCategoryPlot().getRangeAxis();
	    valueAxis.setUpperMargin(0);
	    ChartPanel cPanel = new ChartPanel(chart);
	    cPanel.setLayout(null);
	    cPanel.setBackground(null);
	    cPanel.setOpaque(false);
	    cPanel.setDomainZoomable(true);
	    return cPanel;
	}

	
	public static ChartPanel getMonthWeekSplitGraph(CategoryDataset dataset) {
	    GradientPaint gp = new GradientPaint(0.0f,0.0f,new Color(120,250,120),0.0f,0.0f,new Color(0,125,0));
		HorizontalBarChartMetric graph = new HorizontalBarChartMetric("");
		graph.setBackground(null);
	    JFreeChart chart = graph.drawChart(dataset);
	    chart.setBackgroundPaint(null);
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
		renderer.setDrawBarOutline(false);
		renderer.setShadowVisible(false);
		renderer.setSeriesPaint(0, gp);
	    ValueAxis valueAxis = chart.getCategoryPlot().getRangeAxis();
	    valueAxis.setUpperMargin(0);
	    ChartPanel cPanel = new ChartPanel(chart);
	    cPanel.setLayout(null);
	    cPanel.setBackground(null);
	    cPanel.setOpaque(false);
	    cPanel.setDomainZoomable(true);
	    return cPanel;
	}
	
	public static ChartPanel getTopGraph(CategoryDataset dataset) {
		HorizontalBarChartMetric graph = new HorizontalBarChartMetric("");
		graph.setBackground(null);
	    JFreeChart chart = graph.drawChart(dataset);
	    chart.setBackgroundPaint(null);
//	    chart.setTitle(new TextTitle("Technology Split & Share", new Font("Arial", Font.BOLD, 20)));
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
//		renderer.setDrawBarOutline(false);
		renderer.setShadowVisible(false);
	    ValueAxis valueAxis = chart.getCategoryPlot().getRangeAxis();
	    valueAxis.setUpperMargin(0);
	    ChartPanel cPanel = new ChartPanel(chart);
	    cPanel.setLayout(null);
	    cPanel.setBackground(null);
	    cPanel.setOpaque(false);
	    cPanel.setDomainZoomable(true);
	    return cPanel;
	}


	public static JPanel getColorLegend(ImageIcon redMark, 
			ImageIcon yellowMark, ImageIcon greenMark, RatioBoundaries rBound, int xPos, 
			int yPos, int width, int height) {
		JPanel legendPanel = ComponentHelper.getLegendColorRangeNonPercentage(redMark, yellowMark, 
				greenMark, new Font(fontFamily,Font.BOLD,fontSize-1), rBound);
		legendPanel.setOpaque(false);
		legendPanel.setBounds(xPos, yPos, width, height);
		return legendPanel;
	}
	
	public static ChartPanel getStackRankingGraph(CategoryDataset dataset) {
		HorizontalBarChartMetric graph = new HorizontalBarChartMetric("");
		graph.setBackground(null);
	    JFreeChart chart = graph.drawChart(dataset);
	    chart.setBackgroundPaint(null);
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
		renderer.setShadowVisible(false);
//		renderer.setSeriesPaint(0, Color.GREEN);
		chart.getCategoryPlot().setRenderer(new MultipleColorRenderer());
	    ValueAxis valueAxis = chart.getCategoryPlot().getRangeAxis();
	    valueAxis.setUpperMargin(0);
	    ChartPanel cPanel = new ChartPanel(chart);
	    cPanel.setLayout(null);
	    cPanel.setBackground(null);
	    cPanel.setOpaque(false);
	    cPanel.setDomainZoomable(true);
	    return cPanel;
	}

	
	public static ChartPanel getYokePlot(double chartValue) {
		
		YokePlot meterChart = new YokePlot("");
		DefaultValueDataset dataset = new DefaultValueDataset((int)Math.round(chartValue));
	    JFreeChart chart = meterChart.createChart(dataset, 0D, GeneralConstants.RED_POINT_BOUNDARY,
	    		GeneralConstants.AMBER_POINT_START,GeneralConstants.AMBER_POINT_END,
	    		GeneralConstants.GREEN_POINT_BOUNDARY,100D);
	    chart.removeLegend();
	    ChartPanel cPanel = new ChartPanel(chart);
	    cPanel.setBorder(null);
	    cPanel.setBackground(null);
	    cPanel.setOpaque(false);
	    cPanel.updateUI();
	    return cPanel;
	}
	
	public static JLabel getPeriodLabel(String text, int xPos, int yPos, int width, int height) {
		JLabel label = new JLabel(text);
		label.setBackground(null);
		label.setForeground(Color.BLACK.darker());
		label.setOpaque(false);
		label.setFont(new Font("Arial", Font.BOLD,9));;
		label.setVerticalTextPosition(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setBounds(xPos, yPos, width, height);
		return label;
	}
	
	public static RoundButton getPeriodChangeButton(Image img, int xPos, int yPos, int width, int height) {
		RoundButton button = new RoundButton("");
		button = new RoundButton("");
		button.setBounds(xPos, yPos, width, height);
		button.setFocusable(false);
		button.setBackground(Color.BLACK);
		button.setBorder(null);
		button.setIcon(new ImageIcon(img));
		return button;
	}
	
	public static RoundButton getSwitchButton(Image img, int xPos, int yPos, int width, int height) {
		RoundButton button = new RoundButton("");
		button = new RoundButton("");
		button.setBounds(xPos, yPos, width, height);
		button.setFocusable(false);
		button.setBackground(Color.BLACK);
		button.setBorder(null);
		button.setIcon(new ImageIcon(img));
		return button;
	}

	
	public static void placeTechGraphRangeLabels(JPanel panel, String[] text, String title, double[][] values,
			boolean isAbs) {
		JLabel[] label = new JLabel[text.length];
		JLabel[] label2 = new JLabel[values[0].length];
		double totalValue = 0;
		int techSplitYPos = 34, techSplitYPosIncre = 24, xPos=10, width=70, 
				height=10, xPosOffset= 140; 
		placeTitle(panel, title, 50, 3, 150, 15);
		for (int i = 0; i < values[0].length; i++) {
			totalValue += values[0][i];
		}
		for (int i = 0; i < text.length; i++) {
			label[i] = new JLabel();
			label[i].setText(text[i]);
			label[i].setBackground(null);
			label[i].setForeground(Color.RED.darker());
			label[i].setOpaque(false);
			label[i].setFont(new Font("Arial", Font.BOLD, 8));
			label[i].setBounds(xPos, techSplitYPos+(i*techSplitYPosIncre), width, height);
			label2[i] = new JLabel();
			if (isAbs) {
				label2[i].setText(new DecimalFormat("###.#%").format(values[0][i]/totalValue));
			} else {
				label2[i].setText(new DecimalFormat("$###,###.##").format(values[0][i]));
			}
			label2[i].setBackground(null);
			label2[i].setForeground(Color.RED.darker());
			label2[i].setOpaque(false);
			label2[i].setFont(new Font("Arial", Font.BOLD, 9));
			label2[i].setBounds(xPos+xPosOffset, techSplitYPos+(i*techSplitYPosIncre), width, height);
			panel.add(label[i]);
			panel.add(label2[i]);
		}
	}
	
	public static void placeArchGraphRangeLabels(JPanel panel, String[] text, String title, double[][] values,
			boolean isAbs) {
		JLabel[] label = new JLabel[text.length];
		JLabel[] label2 = new JLabel[text.length];
		double totalValue = 0;
		int techSplitYPos = 34, techSplitYPosIncre = 22, xPos=10, width=70, 
				height=10, xPosOffset= 140;
		placeTitle(panel, title, 50, 3, 150, 15);
		for (int i = 0; i < values[0].length; i++) {
			totalValue += values[0][i];
		}
		for (int i = 0; i < text.length; i++) {
			label[i] = new JLabel();
			label[i].setText(text[i]);
			label[i].setBackground(null);
			label[i].setForeground(Color.RED.darker());
			label[i].setOpaque(false);
			label[i].setFont(new Font("Arial", Font.BOLD, 8));
			label[i].setBounds(xPos, techSplitYPos+(i*techSplitYPosIncre), width, height);
			label2[i] = new JLabel();
			if (isAbs) {
				label2[i].setText(new DecimalFormat("###.#%").format(values[0][i]/totalValue));
			} else {
				label2[i].setText(new DecimalFormat("$###,###.##").format(values[0][i]));
			}
			label2[i].setBackground(null);
			label2[i].setForeground(Color.RED.darker());
			label2[i].setOpaque(false);
			label2[i].setFont(new Font("Arial", Font.BOLD, 9));
			label2[i].setBounds(xPos+xPosOffset, techSplitYPos+(i*techSplitYPosIncre), width, height);
			panel.add(label[i]);
			panel.add(label2[i]);
		}
	}
	
	public static void placeMonthWeekGraphRangeLabels(JPanel panel, String[] text, String title, double[][] values,
			boolean isAbs) {
		JLabel[] label = new JLabel[text.length];
		JLabel[] label2 = new JLabel[values[0].length];
		double totalValue = 0D;
		int techSplitYPos = 34, techSplitYPosIncre = 20, xPos=10, width=70, 
				height=10, xPosOffset= 140; 
		placeTitle(panel, title, 50, 3, 150, 15);
		for (int i = 0; i < values[0].length; i++) {
			totalValue += values[0][i];
		}
		for (int i = 0; i < text.length; i++) {
			label[i] = new JLabel();
			label[i].setText(text[i]);
			label[i].setBackground(null);
			label[i].setForeground(Color.GREEN.darker());
			label[i].setOpaque(false);
			label[i].setFont(new Font("Arial", Font.BOLD, 8));
			label[i].setBounds(xPos, techSplitYPos+(i*techSplitYPosIncre), width, height);
			label2[i] = new JLabel();
			if (isAbs) {
				label2[i].setText(new DecimalFormat("###.#%").format(values[0][i]/totalValue));
			} else {
				label2[i].setText(new DecimalFormat("$###,###.##").format(values[0][i]));
			}
			label2[i].setBackground(null);
			label2[i].setForeground(Color.GREEN.darker());
			label2[i].setOpaque(false);
			label2[i].setFont(new Font("Arial", Font.BOLD, 9));
			label2[i].setBounds(xPos+xPosOffset, techSplitYPos+(i*techSplitYPosIncre), width, height);
			panel.add(label[i]);
			panel.add(label2[i]);
		}
	}

	public static void placeQuarterGraphRangeLabels(JPanel panel, String[] text, String title, double[][] values,
			boolean isAbs) {
		JLabel[] label = new JLabel[text.length];
		JLabel[] label2 = new JLabel[text.length];
		double totalValue = 0D;
		int techSplitYPos = 34, techSplitYPosIncre = 22, xPos=10, width=70, 
				height=10, xPosOffset= 140; 
		placeTitle(panel, title, 50, 3, 150, 15);
		for (int i = 0; i < values[0].length; i++) {
			totalValue += values[0][i];
		}
		for (int i = 0; i < text.length; i++) {
			label[i] = new JLabel();
			label[i].setText(text[i]);
			label[i].setBackground(null);
			label[i].setForeground(Color.GREEN.darker());
			label[i].setOpaque(false);
			label[i].setFont(new Font("Arial", Font.BOLD, 8));
			label[i].setBounds(xPos, techSplitYPos+(i*techSplitYPosIncre), width, height);
			label2[i] = new JLabel();
			if (isAbs) {
				label2[i].setText(new DecimalFormat("###.#%").format(values[0][i]/totalValue));
			} else {
				label2[i].setText(new DecimalFormat("$###,###.##").format(values[0][i]));
			}
			label2[i].setBackground(null);
			label2[i].setForeground(Color.GREEN.darker());
			label2[i].setOpaque(false);
			label2[i].setFont(new Font("Arial", Font.BOLD, 9));
			label2[i].setBounds(xPos+xPosOffset, techSplitYPos+(i*techSplitYPosIncre), width, height);
			panel.add(label[i]);
			panel.add(label2[i]);
		}
	}

	public static void placeHalfYearGraphRangeLabels(JPanel panel, String[] text, String title, double[][] values,
			boolean isAbs) {
		JLabel[] label = new JLabel[text.length];
		JLabel[] label2 = new JLabel[text.length];
		double totalValue = 0D;
		int techSplitYPos = 32, techSplitYPosIncre = 24, xPos=10, width=70, 
				height=10, xPosOffset= 140; 
		placeTitle(panel, title, 50, 3, 150, 15);
		for (int i = 0; i < values[0].length; i++) {
			totalValue += values[0][i];
		}
		for (int i = 0; i < text.length; i++) {
			label[i] = new JLabel();
			label[i].setText(text[i]);
			label[i].setBackground(null);
			label[i].setForeground(Color.GREEN.darker());
			label[i].setOpaque(false);
			label[i].setFont(new Font("Arial", Font.BOLD, 8));
			label[i].setBounds(xPos, techSplitYPos+(i*techSplitYPosIncre), width, height);
			label2[i] = new JLabel();
			if (isAbs) {
				label2[i].setText(new DecimalFormat("###.#%").format(values[0][i]/totalValue));
			} else {
				label2[i].setText(new DecimalFormat("$###,###.##").format(values[0][i]));
			}
			label2[i].setBackground(null);
			label2[i].setForeground(Color.GREEN.darker());
			label2[i].setOpaque(false);
			label2[i].setFont(new Font("Arial", Font.BOLD, 9));
			label2[i].setBounds(xPos+xPosOffset, techSplitYPos+(i*techSplitYPosIncre), width, height);
			panel.add(label[i]);
			panel.add(label2[i]);
		}
	}
	
	
	public static void placeTopGraphRangeLabels(JPanel panel, String[] text, String title, double[][] values) {
		JToggleButton[] toggle = new JToggleButton[text.length];
		JLabel[] label = new JLabel[text.length];
		int techSplitYPos = 34, techSplitYPosIncre = 23, xPos=10, width=170, 
				height=10, xPosOffset= 260; 
		placeTitle(panel, title, 125, 3, 150, 15);
		for (int i = 0; i < text.length; i++) {
			toggle[i] = new JToggleButton();
			toggle[i].setText(text[i]);
			toggle[i].setHorizontalAlignment(SwingConstants.LEFT);;
			toggle[i].setFocusable(false);;
			toggle[i].setBackground(null);
			toggle[i].setForeground(Color.BLUE.darker());
			toggle[i].setOpaque(false);
			toggle[i].setBorder(null);
			toggle[i].setFont(new Font("Arial", Font.BOLD, 8));
			toggle[i].setBounds(xPos, techSplitYPos+(i*techSplitYPosIncre), width, height);
			label[i] = new JLabel();
			label[i].setText(new DecimalFormat("$###,###.##").format(values[0][i]));
			label[i].setBackground(null);
			label[i].setForeground(Color.BLUE.darker());
			label[i].setOpaque(false);
			label[i].setFont(new Font("Arial", Font.BOLD, 9));
			label[i].setBounds(xPos+xPosOffset, techSplitYPos+(i*techSplitYPosIncre), width, height);
			panel.add(toggle[i]);
			panel.add(label[i]);
		}
	}

		public static TopNamesComponent getTopGraphToggle(String text, int xPos, int yPos, 
				int width, int height, int xPosOffset, double values) {
			JButton toggle = new JButton();
			JLabel label = new JLabel();
			TopNamesComponent obj;
			toggle.setText(text);
			toggle.setHorizontalAlignment(SwingConstants.LEFT);;
			toggle.setFocusable(false);;
			toggle.setBackground(null);
			toggle.setForeground(Color.BLUE.darker());
			toggle.setOpaque(false);
			toggle.setBorder(null);
			toggle.setFont(new Font("Arial", Font.BOLD, 8));
			toggle.setBounds(xPos, yPos, width, height);
			label = new JLabel();
			label.setText(new DecimalFormat("$###,###.##").format(values));
			label.setBackground(null);
			label.setForeground(Color.BLUE.darker());
			label.setOpaque(false);
			label.setFont(new Font("Arial", Font.BOLD, 9));
			label.setBounds(xPos+xPosOffset, yPos, width, height);
			obj = new TopNamesComponent(toggle, label);
			return obj;
		}


		public static StackRankersComponent getTopGraphLabels(String text, int xPos, int yPos, 
				int width, int height, int xPosOffset, double values) {
			JLabel name = new JLabel();
			JLabel label = new JLabel();
			StackRankersComponent obj;
			name.setText(text);
			name.setHorizontalAlignment(SwingConstants.LEFT);;
			name.setFocusable(false);;
			name.setBackground(null);
			name.setForeground(Color.BLUE.darker());
			name.setOpaque(false);
			name.setBorder(null);
			name.setFont(new Font("Arial", Font.BOLD, 9));
			name.setBounds(xPos, yPos, width, height);
			label = new JLabel();
			label.setText(new DecimalFormat("###.##").format(values));
			label.setBackground(null);
			label.setForeground(Color.BLUE.darker());
			label.setOpaque(false);
			label.setFont(new Font("Arial", Font.BOLD, 9));
			label.setBounds(xPos+xPosOffset, yPos, width, height);
			obj = new StackRankersComponent(name, label);
			return obj;
		}

		
		
	public static void placeTitle(JPanel panel, String title, int xPos, int yPos, int width, int height) {
	    JLabel jlTitle = new JLabel(title);
	    jlTitle.setBackground(null);
	    jlTitle.setOpaque(false);
	    jlTitle.setFont(new Font("Arial Black", Font.PLAIN, 12));
	    jlTitle.setBounds(xPos, yPos, width, height);
	    panel.add(jlTitle);
	}

	public static JSlider placeWhatIfJSlider(int sliderMin, 
			int sliderMax, int sliderInterval, int minTick, 
			int maxTick, int xPos, int yPos, int width, 
			int height, Color color, Font font) {
		JSlider slider = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, sliderInterval);
		slider.setMajorTickSpacing(maxTick);
		slider.setMinorTickSpacing(minTick);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBounds(xPos, yPos, width, height);
		slider.setBackground(null);
		slider.setOpaque(false);;
		slider.setForeground(color);
		slider.setFont(font);
		slider.setFocusable(false);
		slider.setEnabled(false);
		return slider;
	}

	public static JLabel placeWhatIfLabel(int xPos, int yPos, 
			int width,	int height, String title, Color color,
			Font font) {
		JLabel label = new JLabel("" + title + "?", JLabel.CENTER);
		label.setBackground(null);
		label.setForeground(color);
		label.setOpaque(false);
		label.setFont(font);
		label.setBounds(xPos, yPos-10, width, height-40);
		return label;
	}

	
	public static JButton getControlButton(String title, int xPos, int yPos, int width, int height) {
	    JButton toggle = new JButton(title);
	    toggle.setFont(new Font("Tahoma", Font.BOLD, 8));
	    toggle.setFocusable(false);
	    toggle.setBounds(xPos, yPos, width, height);
	    toggle.setHorizontalTextPosition(SwingConstants.CENTER);
	    toggle.setVerticalTextPosition(SwingConstants.CENTER);
	    toggle.setBorder(null);
	    toggle.setBackground(Color.BLACK);
	    toggle.setForeground(Color.WHITE);
	    return toggle;
	}

	public static JToggleButton getControlTButton(String title, int xPos, int yPos, int width, int height) {
	    JToggleButton toggle = new JToggleButton(title);
	    toggle.setFont(new Font("Tahoma", Font.BOLD, 8));
	    toggle.setFocusable(false);
	    toggle.setBounds(xPos, yPos, width, height);
	    toggle.setHorizontalTextPosition(SwingConstants.CENTER);
	    toggle.setVerticalTextPosition(SwingConstants.CENTER);
	    toggle.setBorder(null);
	    toggle.setBackground(Color.BLACK);
	    toggle.setForeground(Color.WHITE);
	    return toggle;
	}

	public static void changeToSwitchOnState(JComponent comp, Color bColor, Color fColor) {
		comp.setBackground(bColor);
		comp.setForeground(fColor);
	}
	
	public static void changeToSwitchOffState(JComponent comp, Color bColor, Color fColor) {
		comp.setBackground(bColor);
		comp.setForeground(fColor);
	}

	public static void refreshSwitchPanel(JPanel panel1, JPanel panel2, 
			JComponent comp1, JComponent comp2, JComponent comp3, JComponent comp4, 
			JComponent comp5, JComponent comp6, JComponent comp7, JComponent comp8,
			JComponent comp9, JComponent comp10, JComponent comp11, JComponent comp12, 
			JComponent comp13, JComponent comp14, JComponent comp15, JComponent comp16, 
			JComponent comp17, JComponent comp18, JComponent comp19, JComponent comp20,
			JComponent comp21, JComponent comp22, JComponent comp23, JComponent comp24,
			JComponent comp25) {

		panel2.removeAll();
		panel2.add(comp1);
		panel2.add(comp2);
		panel2.add(comp3);
		panel2.add(comp4);
		panel2.add(comp5);
		panel2.add(comp6);
		panel2.add(comp7);
		panel2.add(comp8);
		panel2.add(comp9);
		panel2.add(comp10);
		panel2.add(comp11);
		panel2.add(comp12);
		panel2.add(comp13);
		panel2.add(comp14);
		panel2.add(comp15);
		panel2.add(comp16);
		panel2.add(comp17);
		panel2.add(comp18);
		panel2.add(comp19);
		panel2.add(comp20);
		panel2.add(comp21);
		panel2.add(comp22);
		panel2.add(comp23);
		panel2.add(comp24);
		panel2.add(comp25);
		panel1.repaint();
		panel2.repaint();
	}

	public static void refreshWhenAssumptionOn(JPanel panel, JButton jb1, JButton jb2, JButton jb3, JButton jb4, 
			JButton jb5, JButton jb6, JButton jb7) {
		panel.remove(jb1);
		panel.remove(jb2);
		panel.remove(jb3);
		panel.remove(jb4);
		panel.remove(jb5);
		panel.remove(jb6);
		panel.remove(jb7);
		jb1.setEnabled(false);
		jb2.setEnabled(false);
		jb3.setEnabled(false);
		jb4.setEnabled(false);
		jb5.setEnabled(false);
		jb6.setEnabled(false);
		jb7.setEnabled(false);
		panel.add(jb1);
		panel.add(jb2);
		panel.add(jb3);
		panel.add(jb4);
		panel.add(jb5);
		panel.add(jb6);
		panel.add(jb7);
	}

	public static void refreshWhenAssumptionOff(JPanel panel, JButton jb1, JButton jb2, JButton jb3, JButton jb4, 
			JButton jb5, JButton jb6, JButton jb7) {
		panel.remove(jb1);
		panel.remove(jb2);
		panel.remove(jb3);
		panel.remove(jb4);
		panel.remove(jb5);
		panel.remove(jb6);
		panel.remove(jb7);
		jb1.setEnabled(true);
		jb2.setEnabled(true);
		jb3.setEnabled(true);
		jb4.setEnabled(true);
		jb5.setEnabled(true);
		jb6.setEnabled(true);
		jb7.setEnabled(true);
		panel.add(jb1);
		panel.add(jb2);
		panel.add(jb3);
		panel.add(jb4);
		panel.add(jb5);
		panel.add(jb6);
		panel.add(jb7);
	}

	
	public static JLabel getControlLight(ImageIcon img, int xPos, int yPos, int width, int height) {
		JLabel label = new JLabel(img);
		label.setBounds(xPos, yPos, width, height);
		label.setOpaque(false);
		return label;
	}

	public static JLabel getControlLightLabel(String title, int xPos, int yPos, int width, int height) {
		JLabel label = new JLabel(title);
		label.setBounds(xPos, yPos, width, height);
		label.setForeground(Color.WHITE);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.ITALIC, 8));
		label.setOpaque(false);
		return label;
	}
	
	
	public static BufferedImage makeCompatible(BufferedImage image) {
		 int w = image.getWidth();
		  int h = image.getHeight();

		  BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		  Graphics2D g = result.createGraphics();
		  g.drawRenderedImage(image, new AffineTransform()); //or some other drawImage function
		  g.dispose();

		  return result;
	    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JComboBox fillGDYearComboBox() {
		ArrayList<String> arrayList = new ArrayList<String>();
		int tempCount=0;
		DefaultComboBoxModel cModel = null;
		JComboBox jcb = null;
		List<DataBaseUniqueData> resultData;
		DataBaseUniqueDataQueries query;
		DataBaseUniqueData currentEntry;
		try {
			 query = new DataBaseUniqueDataQueries();
			
			try {
				resultData = query.getFY();
			    tempCount = resultData.size();

			    for (int i = 0; i < tempCount; i++) {
			    	currentEntry = resultData.get(i);
			    	String temp = currentEntry.getFiscalYear();
			    	temp = temp.substring(temp.indexOf("20")+2);
			    	temp="FY" + temp;
			    			if (!arrayList.contains(temp)) {
			    				arrayList.add(temp);
			    			}
				}
			    List<String> list = arrayList;
			    Collections.sort(list);
			    arrayList = (ArrayList<String>) list;
			    cModel = new DefaultComboBoxModel (arrayList.toArray());
				jcb = new JComboBox(cModel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return jcb;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JComboBox fillCompareYearComboBox(String year) {
		ArrayList<String> arrayList = new ArrayList<String>();
		int tempCount=0;
		DefaultComboBoxModel cModel = null;
		JComboBox jcb = null;
		List<DataBaseUniqueData> resultData;
		DataBaseUniqueDataQueries query;
		DataBaseUniqueData currentEntry;
		try {
			 query = new DataBaseUniqueDataQueries();
			
			try {
				resultData = query.getFY();
			    tempCount = resultData.size();

			    for (int i = 0; i < tempCount; i++) {
			    	currentEntry = resultData.get(i);
			    	String temp = currentEntry.getFiscalYear();
			    	temp = temp.substring(temp.indexOf("20")+2);
			    	temp="FY" + temp;
			    			if (!arrayList.contains(temp) && !temp.equals(year)) {
			    				arrayList.add(temp);
			    			}
				}
			    List<String> list = arrayList;
			    Collections.sort(list);
			    arrayList = (ArrayList<String>) list;
			    cModel = new DefaultComboBoxModel (arrayList.toArray());
				jcb = new JComboBox(cModel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return jcb;
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DefaultComboBoxModel fillGDEUComboBox(String u) {
		ArrayList<String> arrayList = new ArrayList<String>();
		int tempCount=0;
		DefaultComboBoxModel cModel = null;
		List<DataBaseAdminEU> resultData;
		DataBaseAdminEUQueries query;
		DataBaseAdminEU currentEntry;
		try {
			 query = new DataBaseAdminEUQueries();
			
			try {
				resultData = query.getEU(u);
			    tempCount = resultData.size();

			    for (int i = 0; i < tempCount; i++) {
			    	currentEntry = resultData.get(i);
			    	String temp = currentEntry.getExecutionUnit();
			    			if (!arrayList.contains(temp)) {
			    				arrayList.add(temp);
			    			}
				}
			    List<String> list = arrayList;
			    Collections.sort(list);
			    arrayList = (ArrayList<String>) list;
			    cModel = new DefaultComboBoxModel (arrayList.toArray());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return cModel;
	}


	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static DefaultListModel fillGDRegionListBox(String u, String eu) {
		int tempCount=0;
		DefaultListModel lModel = new DefaultListModel();
		List<DataBaseAdminRegion> resultData;
		DataBaseAdminRegionQueries query;
		DataBaseAdminRegion currentEntry;
		query = new DataBaseAdminRegionQueries();
		
		try {
			resultData = query.getRegion(u, eu);
		    tempCount = resultData.size();
		    for (int i = 0; i < tempCount; i++) {
		    	currentEntry = resultData.get(i);
		    	String temp = currentEntry.getRegion();
				lModel.addElement(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lModel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static DefaultListModel fillGDNodeListBox(JList list, String u, String subSCMS, String region, String finYear, String eu) {
		int tempCount=0;
		DefaultListModel lModel = new DefaultListModel();
		List<DataBaseAdminNode> resultData;
		DataBaseAdminNodeQueries query;
		DataBaseAdminNode currentEntry;
		query = new DataBaseAdminNodeQueries();
		String[] regionArray;
		int arrayLength = ComponentHelper.getListInArray(list).length;
		regionArray = new String[arrayLength];
		if (region.equals("Multiple")) {
			regionArray = ComponentHelper.getListInArray(list);
			try {
				resultData = query.getSalesLevel6(u, subSCMS, "ALL", finYear, eu);
			    tempCount = resultData.size();
			    for (int j = 0; j < tempCount; j++) {
			    	currentEntry = resultData.get(j);
			    	String node = currentEntry.getSalesLevel6();
			    	String reg = currentEntry.getRegion();
			    	for (int i=0; i<arrayLength; i++) {
			    		if (regionArray[i].equals(reg)) {
			    			lModel.addElement(node);
			    		}
			    	}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				resultData = query.getSalesLevel6(u, subSCMS, region, finYear, eu);
			    tempCount = resultData.size();
			    for (int i = 0; i < tempCount; i++) {
			    	currentEntry = resultData.get(i);
			    	String temp = currentEntry.getSalesLevel6();
					lModel.addElement(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return lModel;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static DefaultListModel fillSalesAgentListBox(JList jlNode, JList jlRegion, 
			String node, String subSCMS, String region, String finYear, String eu) {
		int tempCount=0;
		DefaultListModel lModel = new DefaultListModel();
		List<DataBaseAdminSalesAgent> resultData;
		DataBaseAdminSalesAgentQueries query;
		DataBaseAdminSalesAgent currentEntry;
		query = new DataBaseAdminSalesAgentQueries();
		String[] regionArray, nodeArray;
		int regionArrayLength = ComponentHelper.getListInArray(jlRegion).length;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		regionArray = new String[regionArrayLength];
		nodeArray = new String[nodeArrayLength];
		if (region.equals("Multiple")) {
			if (node.equals("Multiple")) {
				regionArray = ComponentHelper.getListInArray(jlRegion);
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getSalesLevel6("ALL", subSCMS, "ALL", finYear, eu);
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	String name = currentEntry.getSalesAgent();
				    	String reg = currentEntry.getRegion();
				    	String nod = currentEntry.getSalesLevel6();
				    	for (int i=0; i<regionArrayLength; i++) {
					    	for (int l=0; l<nodeArrayLength; l++) {
					    		if (regionArray[i].equals(reg) && 
					    				nodeArray[l].equals(nod)&& 
					    				!lModel.contains(name)) {
					    			lModel.addElement(name);
					    		}
					    	}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				regionArray = ComponentHelper.getListInArray(jlRegion);
				try {
					resultData = query.getSalesLevel6(node, subSCMS, "ALL", finYear, eu);
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	String name = currentEntry.getSalesAgent();
				    	String reg = currentEntry.getRegion();
				    	for (int i=0; i<regionArrayLength; i++) {
				    		if (regionArray[i].equals(reg) && 
				    				!lModel.contains(name)) {
				    			lModel.addElement(name);
				    		}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (node.equals("Multiple")) {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getSalesLevel6("ALL", subSCMS, region, finYear, eu);
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	String name = currentEntry.getSalesAgent();
				    	String nod = currentEntry.getSalesLevel6();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod) && 
				    				!lModel.contains(name)) {
				    			lModel.addElement(name);
				    		}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					resultData = query.getSalesLevel6(node, subSCMS, region, finYear, eu);
				    tempCount = resultData.size();
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String temp = currentEntry.getSalesAgent();
				    	if (!lModel.contains(temp)) {
				    		lModel.addElement(temp);
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return lModel;
	}


	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static DefaultListModel fillResolutionListBox() {
		int tempCount=0;
		DefaultListModel lModel = new DefaultListModel();
		for (int i=0; i<GeneralConstants.RESOLUTION_OPTION.length; i++) {
			lModel.addElement(GeneralConstants.RESOLUTION_OPTION[i]);
		}
		
		return lModel;
	}

	
	
	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static DefaultListModel fillPartnerListBox(JList jlSA, JList jlNode, 
			String node, String subSCMS, String sa, String finYear) {
		int tempCount=0;
		DefaultListModel lModel = new DefaultListModel();
		List<DataBaseAdminPartner> resultData;
		DataBaseAdminPartnerQueries query;
		DataBaseAdminPartner currentEntry;
		query = new DataBaseAdminPartnerQueries();
		String[] saArray, nodeArray;
		int saArrayLength = ComponentHelper.getListInArray(jlSA).length;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		saArray = new String[saArrayLength];
		nodeArray = new String[nodeArrayLength];
		if (node.equals("Multiple")) {
			if (sa.equals("Multiple")) {
				saArray = ComponentHelper.getListInArray(jlSA);
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getPartner("ALL", subSCMS, finYear, "ALL");
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	String name = currentEntry.getPartner();
				    	String salesAgent = currentEntry.getSalesAgent();
				    	String nod = currentEntry.getSalesLevel6();
				    	for (int i=0; i<nodeArrayLength; i++) {
					    	for (int l=0; l<saArrayLength; l++) {
					    		if (saArray[l].equals(salesAgent) && 
					    				nodeArray[i].equals(nod)&& 
					    				!lModel.contains(name)) {
					    			lModel.addElement(name);
					    		}
					    	}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getPartner("ALL", subSCMS, finYear, sa);
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	String name = currentEntry.getPartner();
				    	String nod = currentEntry.getSalesLevel6();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod) && 
				    				!lModel.contains(name)) {
				    			lModel.addElement(name);
				    		}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (sa.equals("Multiple")) {
				saArray = ComponentHelper.getListInArray(jlSA);
				try {
					resultData = query.getPartner(node, subSCMS, finYear, "ALL");
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	String name = currentEntry.getPartner();
				    	String salesAgent = currentEntry.getSalesAgent();
				    	for (int i=0; i<saArrayLength; i++) {
				    		if (saArray[i].equals(salesAgent) && 
				    				!lModel.contains(name)) {
				    			lModel.addElement(name);
				    		}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					resultData = query.getPartner(node, subSCMS, finYear, sa);
				    tempCount = resultData.size();
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String temp = currentEntry.getPartner();
				    	if (!lModel.contains(temp)) {
				    		lModel.addElement(temp);
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return lModel;
	}


	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static DefaultListModel fillCustomerListBox(JList jlSA, JList jlNode, 
			String node, String subSCMS, String sa, String finYear) {
		int tempCount=0;
		DefaultListModel lModel = new DefaultListModel();
		List<DataBaseAdminCustomer> resultData;
		DataBaseAdminCustomerQueries query;
		DataBaseAdminCustomer currentEntry;
		query = new DataBaseAdminCustomerQueries();
		String[] saArray, nodeArray;
		int saArrayLength = ComponentHelper.getListInArray(jlSA).length;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		saArray = new String[saArrayLength];
		nodeArray = new String[nodeArrayLength];
		if (node.equals("Multiple")) {
			if (sa.equals("Multiple")) {
				saArray = ComponentHelper.getListInArray(jlSA);
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getCustomer("ALL", subSCMS, finYear, "ALL");
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	String name = currentEntry.getCustomer();
				    	String salesAgent = currentEntry.getSalesAgent();
				    	String nod = currentEntry.getSalesLevel6();
				    	for (int i=0; i<nodeArrayLength; i++) {
					    	for (int l=0; l<saArrayLength; l++) {
					    		if (saArray[l].equals(salesAgent) && 
					    				nodeArray[i].equals(nod)&& 
					    				!lModel.contains(name)) {
					    			lModel.addElement(name);
					    		}
					    	}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getCustomer("ALL", subSCMS, finYear, sa);
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	String name = currentEntry.getCustomer();
				    	String nod = currentEntry.getSalesLevel6();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod) && 
				    				!lModel.contains(name)) {
				    			lModel.addElement(name);
				    		}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (sa.equals("Multiple")) {
				saArray = ComponentHelper.getListInArray(jlSA);
				try {
					resultData = query.getCustomer(node, subSCMS, finYear, "ALL");
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	String name = currentEntry.getCustomer();
				    	String salesAgent = currentEntry.getSalesAgent();
				    	for (int i=0; i<saArrayLength; i++) {
				    		if (saArray[i].equals(salesAgent) && 
				    				!lModel.contains(name)) {
				    			lModel.addElement(name);
				    		}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					resultData = query.getCustomer(node, subSCMS, finYear, sa);
				    tempCount = resultData.size();
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String temp = currentEntry.getCustomer();
				    	if (!lModel.contains(temp)) {
				    		lModel.addElement(temp);
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return lModel;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static List<DashboardGeneralValues> getTBMMetricsYTD(JList jlSA, JList jlNode, 
			String node, String sa, String finYear, String finQuarter, String finMonth, boolean isPreviousYear) {
		int tempCount=0;
		double booking = 0D, list = 0D, entNWBook = 0D, entNWList = 0D,
		secBook = 0D, secList = 0D, collBook = 0D, collList = 0D, dcvBook = 0D, 
		dcvList = 0D, atAttach = 0D, nonATAttach = 0D, techs = 0D, 
		m1 = 0D, m2 = 0D, m3 = 0D, m4 = 0D, m5 = 0D, m6 = 0D, m7 = 0D, m8 = 0D, 
		m9 = 0D, m10 = 0D, m11 = 0D, m12 = 0D, q1 = 0D, q2 = 0D, q3 = 0D, q4 = 0D, 
		h1 = 0D, h2 = 0D, 
		m1w1=0D, m1w2=0D, m1w3=0D, m1w4=0D,
		m2w1=0D, m2w2=0D, m2w3=0D, m2w4=0D,
		m3w1=0D, m3w2=0D, m3w3=0D, m3w4=0D, m3w5=0D,
		m4w1=0D, m4w2=0D, m4w3=0D, m4w4=0D,
		m5w1=0D, m5w2=0D, m5w3=0D, m5w4=0D,
		m6w1=0D, m6w2=0D, m6w3=0D, m6w4=0D, m6w5=0D,
		m7w1=0D, m7w2=0D, m7w3=0D, m7w4=0D,
		m8w1=0D, m8w2=0D, m8w3=0D, m8w4=0D,
		m9w1=0D, m9w2=0D, m9w3=0D, m9w4=0D, m9w5=0D,
		m10w1=0D, m10w2=0D, m10w3=0D, m10w4=0D,
		m11w1=0D, m11w2=0D, m11w3=0D, m11w4=0D,
		m12w1=0D, m12w2=0D, m12w3=0D, m12w4=0D, m12w5=0D,
		techANS = 0D, techDCS = 0D, techOth = 0D, techLAN = 0D, techRou = 0D, 
		techSec = 0D, techSto = 0D, techUC = 0D, techUCS = 0D, techVid = 0D, techWLA = 0D;
		List<DashboardGeneralValues> valueSet = null;
		DashboardGeneralValues valueEntry;
		List<DataBaseUniqueTBM> resultData;
		DataBaseUniqueTBMQueries query;
		DataBaseUniqueTBM currentEntry;
		query = new DataBaseUniqueTBMQueries(isPreviousYear);
		String[] saArray, nodeArray;
		int saArrayLength = ComponentHelper.getListInArray(jlSA).length;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		DefaultListModel lModel = new DefaultListModel();
		DefaultListModel lModel2 = new DefaultListModel();
		List<TopNames> top = new ArrayList<TopNames>();
		List<String> tempTop = new ArrayList<String>();
		saArray = new String[saArrayLength];
		nodeArray = new String[nodeArrayLength];
		if (node.equals("Multiple")) {
			if (sa.equals("Multiple")) {
				saArray = ComponentHelper.getListInArray(jlSA);
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", "ALL", finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String salesAgent = currentEntry.getSalesAgent();
				    	String nod = currentEntry.getSalesLevel6();
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	for (int i=0; i<nodeArrayLength; i++) {
					    	for (int l=0; l<saArrayLength; l++) {
					    		if (saArray[l].equals(salesAgent) && 
					    				nodeArray[i].equals(nod)) {
					    			if(!tempTop.contains(salesAgent)) {
					    				tempTop.add(salesAgent);
					    			}
					    			booking += value;
					    			list += value2;
					    			entNWBook += value3;
					    			entNWList += value4;
					    			secBook += value11;
					    			secList += value12;
					    			collBook += value5;
					    			collList += value6;
					    			dcvBook += value7;
					    			dcvList += value8;
					    			atAttach += value9;
					    			nonATAttach += value10;
							    	m1 += value13;
							    	m2 += value14;
							    	m3 += value15;
							    	m4 += value16;
							    	m5 += value17;
							    	m6 += value18;
							    	m7 += value19;
							    	m8 += value20;
							    	m9 += value21;
							    	m10 += value22;
							    	m11 += value23;
							    	m12 += value24;
							    	q1 += value25;
							    	q2 += value26;
							    	q3 += value27;
							    	q4 += value28;
							    	m1w1 +=value42;
							    	m1w2 +=value43;
							    	m1w3 +=value44;
							    	m1w4 +=value45;
							    	m2w1 +=value46;
							    	m2w2 +=value47;
							    	m2w3 +=value48;
							    	m2w4 +=value49;
							    	m3w1 +=value50;
							    	m3w2 +=value51;
							    	m3w3 +=value52;
							    	m3w4 +=value53;
							    	m3w4 +=value54;
							    	m4w1 +=value55;
							    	m4w2 +=value56;
							    	m4w3 +=value57;
							    	m4w4 +=value58;
							    	m5w1 +=value59;
							    	m5w2 +=value60;
							    	m5w3 +=value61;
							    	m5w4 +=value62;
							    	m6w1 +=value63;
							    	m6w2 +=value64;
							    	m6w3 +=value65;
							    	m6w4 +=value66;
							    	m6w5 +=value67;
							    	m7w1 +=value68;
							    	m7w2 +=value69;
							    	m7w3 +=value70;
							    	m7w4 +=value71;
							    	m8w1 +=value72;
							    	m8w2 +=value73;
							    	m8w3 +=value74;
							    	m8w4 +=value75;
							    	m9w1 +=value76;
							    	m9w2 +=value77;
							    	m9w3 +=value78;
							    	m9w4 +=value79;
							    	m9w5 +=value80;
							    	m10w1 +=value81;
							    	m10w2 +=value82;
							    	m10w3 +=value83;
							    	m10w4 +=value84;
							    	m11w1 +=value85;
							    	m11w2 +=value86;
							    	m11w3 +=value87;
							    	m11w4 +=value88;
							    	m12w1 +=value89;
							    	m12w2 +=value90;
							    	m12w3 +=value91;
							    	m12w4 +=value92;
							    	m12w5 +=value93;
							    	techANS += value29;
							    	techDCS += value30;
							    	techOth += value31;
							    	techLAN += value32;
							    	techRou += value33;
							    	techSec += value34;
							    	techSto += value35;
							    	techUC += value36;
							    	techUCS += value37;
							    	techVid += value38;
							    	techWLA += value39;
				    				if (!lModel.contains(cus)) {
				    					lModel.addElement(cus);
				    				}
			    					lModel2.addElement(technology);
					    		}
					    	}
				    	}
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String nodes = currentEntry.getSalesLevel6();
					    	String salesAgent = currentEntry.getSalesAgent();
					    	if (tempTop.get(p).equals(salesAgent)) {
						    	for (int i=0; i<nodeArrayLength; i++) {
						    		rev += (nodeArray[i].equals(nodes)) ? value : 0D;
						    	}
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", sa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String nod = currentEntry.getSalesLevel6();
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod)) {
				    			if(!tempTop.contains(sa)) {
				    				tempTop.add(sa);
				    			}
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(cus)) {
			    					lModel.addElement(cus);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String nodes = currentEntry.getSalesLevel6();
					    	if (tempTop.get(p).equals(sa)) {
						    	for (int i=0; i<nodeArrayLength; i++) {
						    		rev += (nodeArray[i].equals(nodes)) ? value : 0D;
						    	}
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (sa.equals("Multiple")) {
				saArray = ComponentHelper.getListInArray(jlSA);
				try {
					resultData = query.getAll(node, "ALL", finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Multiple");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String salesAgent = currentEntry.getSalesAgent();
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	for (int i=0; i<saArrayLength; i++) {
				    		if (saArray[i].equals(salesAgent)) {
				    			if(!tempTop.contains(salesAgent)) {
				    				tempTop.add(salesAgent);
				    			}
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(cus)) {
			    					lModel.addElement(cus);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String salesAgent = currentEntry.getSalesAgent();
					    	String nodes = currentEntry.getSalesLevel6();
					    	if (tempTop.get(p).equals(salesAgent)) {
						    		rev += value;
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					resultData = query.getAll(node, sa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Single");
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
		    			if(!tempTop.contains(sa)) {
		    				tempTop.add(sa);
		    			}
		    			booking += value;
		    			list += value2;
		    			entNWBook += value3;
		    			entNWList += value4;
		    			secBook += value11;
		    			secList += value12;
		    			collBook += value5;
		    			collList += value6;
		    			dcvBook += value7;
		    			dcvList += value8;
		    			atAttach += value9;
		    			nonATAttach += value10;
				    	m1 += value13;
				    	m2 += value14;
				    	m3 += value15;
				    	m4 += value16;
				    	m5 += value17;
				    	m6 += value18;
				    	m7 += value19;
				    	m8 += value20;
				    	m9 += value21;
				    	m10 += value22;
				    	m11 += value23;
				    	m12 += value24;
				    	q1 += value25;
				    	q2 += value26;
				    	q3 += value27;
				    	q4 += value28;
				    	m1w1 +=value42;
				    	m1w2 +=value43;
				    	m1w3 +=value44;
				    	m1w4 +=value45;
				    	m2w1 +=value46;
				    	m2w2 +=value47;
				    	m2w3 +=value48;
				    	m2w4 +=value49;
				    	m3w1 +=value50;
				    	m3w2 +=value51;
				    	m3w3 +=value52;
				    	m3w4 +=value53;
				    	m3w4 +=value54;
				    	m4w1 +=value55;
				    	m4w2 +=value56;
				    	m4w3 +=value57;
				    	m4w4 +=value58;
				    	m5w1 +=value59;
				    	m5w2 +=value60;
				    	m5w3 +=value61;
				    	m5w4 +=value62;
				    	m6w1 +=value63;
				    	m6w2 +=value64;
				    	m6w3 +=value65;
				    	m6w4 +=value66;
				    	m6w5 +=value67;
				    	m7w1 +=value68;
				    	m7w2 +=value69;
				    	m7w3 +=value70;
				    	m7w4 +=value71;
				    	m8w1 +=value72;
				    	m8w2 +=value73;
				    	m8w3 +=value74;
				    	m8w4 +=value75;
				    	m9w1 +=value76;
				    	m9w2 +=value77;
				    	m9w3 +=value78;
				    	m9w4 +=value79;
				    	m9w5 +=value80;
				    	m10w1 +=value81;
				    	m10w2 +=value82;
				    	m10w3 +=value83;
				    	m10w4 +=value84;
				    	m11w1 +=value85;
				    	m11w2 +=value86;
				    	m11w3 +=value87;
				    	m11w4 +=value88;
				    	m12w1 +=value89;
				    	m12w2 +=value90;
				    	m12w3 +=value91;
				    	m12w4 +=value92;
				    	m12w5 +=value93;
				    	techANS += value29;
				    	techDCS += value30;
				    	techOth += value31;
				    	techLAN += value32;
				    	techRou += value33;
				    	techSec += value34;
				    	techSto += value35;
				    	techUC += value36;
				    	techUCS += value37;
				    	techVid += value38;
				    	techWLA += value39;
	    				if (!lModel.contains(cus)) {
	    					lModel.addElement(cus);
	    				}
    					lModel2.addElement(technology);
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String nodes = currentEntry.getSalesLevel6();
					    	if (tempTop.get(p).equals(sa)) {
						    		rev += value;
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		valueSet = new ArrayList<DashboardGeneralValues>();
		valueSet.add(new 
				DashboardGeneralValues(
						CalcHelper.getValueInMillionUSD(booking),
						CalcHelper.getValueInMillionUSD(list),
						(double)lModel.getSize(),
						CalcHelper.getValueInMillionUSD(entNWBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(secBook),
						CalcHelper.getValueInMillionUSD(secList),
						CalcHelper.getValueInMillionUSD(collBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(dcvBook),
						CalcHelper.getValueInMillionUSD(dcvList),
						(double)lModel2.getSize(),
						CalcHelper.getValueInMillionUSD(atAttach),
						CalcHelper.getValueInMillionUSD(nonATAttach),
						CalcHelper.getValueInThousandUSD(m1),
						CalcHelper.getValueInThousandUSD(m2),
						CalcHelper.getValueInThousandUSD(m3),
						CalcHelper.getValueInThousandUSD(m4),
						CalcHelper.getValueInThousandUSD(m5),
						CalcHelper.getValueInThousandUSD(m6),
						CalcHelper.getValueInThousandUSD(m7),
						CalcHelper.getValueInThousandUSD(m8),
						CalcHelper.getValueInThousandUSD(m9),
						CalcHelper.getValueInThousandUSD(m10),
						CalcHelper.getValueInThousandUSD(m11),
						CalcHelper.getValueInThousandUSD(m12),
						CalcHelper.getValueInThousandUSD(q1),
						CalcHelper.getValueInThousandUSD(q2),
						CalcHelper.getValueInThousandUSD(q3),
						CalcHelper.getValueInThousandUSD(q4),
						CalcHelper.getValueInThousandUSD(techANS),
						CalcHelper.getValueInThousandUSD(techDCS),
						CalcHelper.getValueInThousandUSD(techOth),
						CalcHelper.getValueInThousandUSD(techLAN),
						CalcHelper.getValueInThousandUSD(techRou),
						CalcHelper.getValueInThousandUSD(techSec),
						CalcHelper.getValueInThousandUSD(techSto),
						CalcHelper.getValueInThousandUSD(techUC),
						CalcHelper.getValueInThousandUSD(techUCS),
						CalcHelper.getValueInThousandUSD(techVid),
						CalcHelper.getValueInThousandUSD(techWLA),
						top,
						CalcHelper.getValueInThousandUSD(m1w1),
						CalcHelper.getValueInThousandUSD(m1w2),
						CalcHelper.getValueInThousandUSD(m1w3),
						CalcHelper.getValueInThousandUSD(m1w4),
						CalcHelper.getValueInThousandUSD(m2w1),
						CalcHelper.getValueInThousandUSD(m2w2),
						CalcHelper.getValueInThousandUSD(m2w3),
						CalcHelper.getValueInThousandUSD(m2w4),
						CalcHelper.getValueInThousandUSD(m3w1),
						CalcHelper.getValueInThousandUSD(m3w2),
						CalcHelper.getValueInThousandUSD(m3w3),
						CalcHelper.getValueInThousandUSD(m3w4),
						CalcHelper.getValueInThousandUSD(m3w5),
						CalcHelper.getValueInThousandUSD(m4w1),
						CalcHelper.getValueInThousandUSD(m4w2),
						CalcHelper.getValueInThousandUSD(m4w3),
						CalcHelper.getValueInThousandUSD(m4w4),
						CalcHelper.getValueInThousandUSD(m5w1),
						CalcHelper.getValueInThousandUSD(m5w2),
						CalcHelper.getValueInThousandUSD(m5w3),
						CalcHelper.getValueInThousandUSD(m5w4),
						CalcHelper.getValueInThousandUSD(m6w1),
						CalcHelper.getValueInThousandUSD(m6w2),
						CalcHelper.getValueInThousandUSD(m6w3),
						CalcHelper.getValueInThousandUSD(m6w4),
						CalcHelper.getValueInThousandUSD(m6w5),
						CalcHelper.getValueInThousandUSD(m7w1),
						CalcHelper.getValueInThousandUSD(m7w2),
						CalcHelper.getValueInThousandUSD(m7w3),
						CalcHelper.getValueInThousandUSD(m7w4),
						CalcHelper.getValueInThousandUSD(m8w1),
						CalcHelper.getValueInThousandUSD(m8w2),
						CalcHelper.getValueInThousandUSD(m8w3),
						CalcHelper.getValueInThousandUSD(m8w4),
						CalcHelper.getValueInThousandUSD(m9w1),
						CalcHelper.getValueInThousandUSD(m9w2),
						CalcHelper.getValueInThousandUSD(m9w3),
						CalcHelper.getValueInThousandUSD(m9w4),
						CalcHelper.getValueInThousandUSD(m9w5),
						CalcHelper.getValueInThousandUSD(m10w1),
						CalcHelper.getValueInThousandUSD(m10w2),
						CalcHelper.getValueInThousandUSD(m10w3),
						CalcHelper.getValueInThousandUSD(m10w4),
						CalcHelper.getValueInThousandUSD(m11w1),
						CalcHelper.getValueInThousandUSD(m11w2),
						CalcHelper.getValueInThousandUSD(m11w3),
						CalcHelper.getValueInThousandUSD(m11w4),
						CalcHelper.getValueInThousandUSD(m12w1),
						CalcHelper.getValueInThousandUSD(m12w2),
						CalcHelper.getValueInThousandUSD(m12w3),
						CalcHelper.getValueInThousandUSD(m12w4),
						CalcHelper.getValueInThousandUSD(m12w5)));
		return valueSet;
	}


	
	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static List<DashboardGeneralValues> getTBMPartnerMetricsYTD(JList jlNode, 
			String node, String sa, String finYear, String finQuarter, String finMonth
			, boolean isPreviousYear) {
		int tempCount=0;
		double booking = 0D, list = 0D, entNWBook = 0D, entNWList = 0D,
		secBook = 0D, secList = 0D, collBook = 0D, collList = 0D, dcvBook = 0D, 
		dcvList = 0D, atAttach = 0D, nonATAttach = 0D, techs = 0D, 
		m1 = 0D, m2 = 0D, m3 = 0D, m4 = 0D, m5 = 0D, m6 = 0D, m7 = 0D, m8 = 0D, 
		m9 = 0D, m10 = 0D, m11 = 0D, m12 = 0D, q1 = 0D, q2 = 0D, q3 = 0D, q4 = 0D, 
		h1 = 0D, h2 = 0D, 
		m1w1=0D, m1w2=0D, m1w3=0D, m1w4=0D,
		m2w1=0D, m2w2=0D, m2w3=0D, m2w4=0D,
		m3w1=0D, m3w2=0D, m3w3=0D, m3w4=0D, m3w5=0D,
		m4w1=0D, m4w2=0D, m4w3=0D, m4w4=0D,
		m5w1=0D, m5w2=0D, m5w3=0D, m5w4=0D,
		m6w1=0D, m6w2=0D, m6w3=0D, m6w4=0D, m6w5=0D,
		m7w1=0D, m7w2=0D, m7w3=0D, m7w4=0D,
		m8w1=0D, m8w2=0D, m8w3=0D, m8w4=0D,
		m9w1=0D, m9w2=0D, m9w3=0D, m9w4=0D, m9w5=0D,
		m10w1=0D, m10w2=0D, m10w3=0D, m10w4=0D,
		m11w1=0D, m11w2=0D, m11w3=0D, m11w4=0D,
		m12w1=0D, m12w2=0D, m12w3=0D, m12w4=0D, m12w5=0D,
		techANS = 0D, techDCS = 0D, techOth = 0D, techLAN = 0D, techRou = 0D, 
		techSec = 0D, techSto = 0D, techUC = 0D, techUCS = 0D, techVid = 0D, techWLA = 0D;
		List<DashboardGeneralValues> valueSet = null;
		DashboardGeneralValues valueEntry;
		List<DataBaseUniqueTBM> resultData;
		DataBaseUniqueTBMQueries query;
		DataBaseUniqueTBM currentEntry;
		query = new DataBaseUniqueTBMQueries(isPreviousYear);
		String[] nodeArray;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		DefaultListModel lModel = new DefaultListModel();
		DefaultListModel lModel2 = new DefaultListModel();
		List<TopNames> top = new ArrayList<TopNames>();
		List<String> tempTop = new ArrayList<String>();
		List<String> tempTop2 = new ArrayList<String>();
		nodeArray = new String[nodeArrayLength];
		if (node.equals("Multiple")) {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", sa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String nod = currentEntry.getSalesLevel6();
				    	String cus = currentEntry.getCustomer();
				    	String par = currentEntry.getPartner();
				    	String technology = currentEntry.getTechnology();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod)) {
			    				tempTop.add(sa);
				    			if(tempTop.contains(sa) && (!tempTop2.contains(par))) {
				    				tempTop2.add(par);
				    			}
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(cus)) {
			    					lModel.addElement(cus);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
			    	for (int p=0; p<tempTop2.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String salesAgent = currentEntry.getSalesAgent();
					    	String nodes = currentEntry.getSalesLevel6();
					    	String par = currentEntry.getPartner();
					    	if (sa.equals(salesAgent) &&
					    			tempTop2.get(p).equals(par)) {
						    	for (int i=0; i<nodeArrayLength; i++) {
						    		rev += (nodeArray[i].equals(nodes)) ? value : 0D;
						    	}
					    	}
					    }
			    		top.add(new TopNames(tempTop2.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} else {
				try {
					resultData = query.getAll(node, sa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Single");
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	String par = currentEntry.getPartner();
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
	    				tempTop.add(sa);
		    			if(tempTop.contains(sa) && (!tempTop2.contains(par))) {
		    				tempTop2.add(par);
		    			}
		    			booking += value;
		    			list += value2;
		    			entNWBook += value3;
		    			entNWList += value4;
		    			secBook += value11;
		    			secList += value12;
		    			collBook += value5;
		    			collList += value6;
		    			dcvBook += value7;
		    			dcvList += value8;
		    			atAttach += value9;
		    			nonATAttach += value10;
				    	m1 += value13;
				    	m2 += value14;
				    	m3 += value15;
				    	m4 += value16;
				    	m5 += value17;
				    	m6 += value18;
				    	m7 += value19;
				    	m8 += value20;
				    	m9 += value21;
				    	m10 += value22;
				    	m11 += value23;
				    	m12 += value24;
				    	q1 += value25;
				    	q2 += value26;
				    	q3 += value27;
				    	q4 += value28;
				    	m1w1 +=value42;
				    	m1w2 +=value43;
				    	m1w3 +=value44;
				    	m1w4 +=value45;
				    	m2w1 +=value46;
				    	m2w2 +=value47;
				    	m2w3 +=value48;
				    	m2w4 +=value49;
				    	m3w1 +=value50;
				    	m3w2 +=value51;
				    	m3w3 +=value52;
				    	m3w4 +=value53;
				    	m3w4 +=value54;
				    	m4w1 +=value55;
				    	m4w2 +=value56;
				    	m4w3 +=value57;
				    	m4w4 +=value58;
				    	m5w1 +=value59;
				    	m5w2 +=value60;
				    	m5w3 +=value61;
				    	m5w4 +=value62;
				    	m6w1 +=value63;
				    	m6w2 +=value64;
				    	m6w3 +=value65;
				    	m6w4 +=value66;
				    	m6w5 +=value67;
				    	m7w1 +=value68;
				    	m7w2 +=value69;
				    	m7w3 +=value70;
				    	m7w4 +=value71;
				    	m8w1 +=value72;
				    	m8w2 +=value73;
				    	m8w3 +=value74;
				    	m8w4 +=value75;
				    	m9w1 +=value76;
				    	m9w2 +=value77;
				    	m9w3 +=value78;
				    	m9w4 +=value79;
				    	m9w5 +=value80;
				    	m10w1 +=value81;
				    	m10w2 +=value82;
				    	m10w3 +=value83;
				    	m10w4 +=value84;
				    	m11w1 +=value85;
				    	m11w2 +=value86;
				    	m11w3 +=value87;
				    	m11w4 +=value88;
				    	m12w1 +=value89;
				    	m12w2 +=value90;
				    	m12w3 +=value91;
				    	m12w4 +=value92;
				    	m12w5 +=value93;
				    	techANS += value29;
				    	techDCS += value30;
				    	techOth += value31;
				    	techLAN += value32;
				    	techRou += value33;
				    	techSec += value34;
				    	techSto += value35;
				    	techUC += value36;
				    	techUCS += value37;
				    	techVid += value38;
				    	techWLA += value39;
	    				if (!lModel.contains(cus)) {
	    					lModel.addElement(cus);
	    				}
    					lModel2.addElement(technology);
					}
			    	for (int p=0; p<tempTop2.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String salesAgent = currentEntry.getSalesAgent();
					    	String par = currentEntry.getPartner();
					    	if (sa.equals(salesAgent) &&
					    			tempTop2.get(p).equals(par)) {
						    		rev += value;
					    	}
					    }
			    		top.add(new TopNames(tempTop2.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		valueSet = new ArrayList<DashboardGeneralValues>();
		valueSet.add(new 
				DashboardGeneralValues(
						CalcHelper.getValueInMillionUSD(booking),
						CalcHelper.getValueInMillionUSD(list),
						(double)lModel.getSize(),
						CalcHelper.getValueInMillionUSD(entNWBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(secBook),
						CalcHelper.getValueInMillionUSD(secList),
						CalcHelper.getValueInMillionUSD(collBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(dcvBook),
						CalcHelper.getValueInMillionUSD(dcvList),
						(double)lModel2.getSize(),
						CalcHelper.getValueInMillionUSD(atAttach),
						CalcHelper.getValueInMillionUSD(nonATAttach),
						CalcHelper.getValueInThousandUSD(m1),
						CalcHelper.getValueInThousandUSD(m2),
						CalcHelper.getValueInThousandUSD(m3),
						CalcHelper.getValueInThousandUSD(m4),
						CalcHelper.getValueInThousandUSD(m5),
						CalcHelper.getValueInThousandUSD(m6),
						CalcHelper.getValueInThousandUSD(m7),
						CalcHelper.getValueInThousandUSD(m8),
						CalcHelper.getValueInThousandUSD(m9),
						CalcHelper.getValueInThousandUSD(m10),
						CalcHelper.getValueInThousandUSD(m11),
						CalcHelper.getValueInThousandUSD(m12),
						CalcHelper.getValueInThousandUSD(q1),
						CalcHelper.getValueInThousandUSD(q2),
						CalcHelper.getValueInThousandUSD(q3),
						CalcHelper.getValueInThousandUSD(q4),
						CalcHelper.getValueInThousandUSD(techANS),
						CalcHelper.getValueInThousandUSD(techDCS),
						CalcHelper.getValueInThousandUSD(techOth),
						CalcHelper.getValueInThousandUSD(techLAN),
						CalcHelper.getValueInThousandUSD(techRou),
						CalcHelper.getValueInThousandUSD(techSec),
						CalcHelper.getValueInThousandUSD(techSto),
						CalcHelper.getValueInThousandUSD(techUC),
						CalcHelper.getValueInThousandUSD(techUCS),
						CalcHelper.getValueInThousandUSD(techVid),
						CalcHelper.getValueInThousandUSD(techWLA),
						top,
						CalcHelper.getValueInThousandUSD(m1w1),
						CalcHelper.getValueInThousandUSD(m1w2),
						CalcHelper.getValueInThousandUSD(m1w3),
						CalcHelper.getValueInThousandUSD(m1w4),
						CalcHelper.getValueInThousandUSD(m2w1),
						CalcHelper.getValueInThousandUSD(m2w2),
						CalcHelper.getValueInThousandUSD(m2w3),
						CalcHelper.getValueInThousandUSD(m2w4),
						CalcHelper.getValueInThousandUSD(m3w1),
						CalcHelper.getValueInThousandUSD(m3w2),
						CalcHelper.getValueInThousandUSD(m3w3),
						CalcHelper.getValueInThousandUSD(m3w4),
						CalcHelper.getValueInThousandUSD(m3w5),
						CalcHelper.getValueInThousandUSD(m4w1),
						CalcHelper.getValueInThousandUSD(m4w2),
						CalcHelper.getValueInThousandUSD(m4w3),
						CalcHelper.getValueInThousandUSD(m4w4),
						CalcHelper.getValueInThousandUSD(m5w1),
						CalcHelper.getValueInThousandUSD(m5w2),
						CalcHelper.getValueInThousandUSD(m5w3),
						CalcHelper.getValueInThousandUSD(m5w4),
						CalcHelper.getValueInThousandUSD(m6w1),
						CalcHelper.getValueInThousandUSD(m6w2),
						CalcHelper.getValueInThousandUSD(m6w3),
						CalcHelper.getValueInThousandUSD(m6w4),
						CalcHelper.getValueInThousandUSD(m6w5),
						CalcHelper.getValueInThousandUSD(m7w1),
						CalcHelper.getValueInThousandUSD(m7w2),
						CalcHelper.getValueInThousandUSD(m7w3),
						CalcHelper.getValueInThousandUSD(m7w4),
						CalcHelper.getValueInThousandUSD(m8w1),
						CalcHelper.getValueInThousandUSD(m8w2),
						CalcHelper.getValueInThousandUSD(m8w3),
						CalcHelper.getValueInThousandUSD(m8w4),
						CalcHelper.getValueInThousandUSD(m9w1),
						CalcHelper.getValueInThousandUSD(m9w2),
						CalcHelper.getValueInThousandUSD(m9w3),
						CalcHelper.getValueInThousandUSD(m9w4),
						CalcHelper.getValueInThousandUSD(m9w5),
						CalcHelper.getValueInThousandUSD(m10w1),
						CalcHelper.getValueInThousandUSD(m10w2),
						CalcHelper.getValueInThousandUSD(m10w3),
						CalcHelper.getValueInThousandUSD(m10w4),
						CalcHelper.getValueInThousandUSD(m11w1),
						CalcHelper.getValueInThousandUSD(m11w2),
						CalcHelper.getValueInThousandUSD(m11w3),
						CalcHelper.getValueInThousandUSD(m11w4),
						CalcHelper.getValueInThousandUSD(m12w1),
						CalcHelper.getValueInThousandUSD(m12w2),
						CalcHelper.getValueInThousandUSD(m12w3),
						CalcHelper.getValueInThousandUSD(m12w4),
						CalcHelper.getValueInThousandUSD(m12w5)));
		return valueSet;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static List<DashboardGeneralValues> getTBMPartnerCustomerMetricsYTD(JList jlNode, 
			String node, String sa, String pa, String finYear, String finQuarter, String finMonth, 
			boolean isPreviousYear) {
		int tempCount=0;
		double booking = 0D, list = 0D, entNWBook = 0D, entNWList = 0D,
		secBook = 0D, secList = 0D, collBook = 0D, collList = 0D, dcvBook = 0D, 
		dcvList = 0D, atAttach = 0D, nonATAttach = 0D, techs = 0D, 
		m1 = 0D, m2 = 0D, m3 = 0D, m4 = 0D, m5 = 0D, m6 = 0D, m7 = 0D, m8 = 0D, 
		m9 = 0D, m10 = 0D, m11 = 0D, m12 = 0D, q1 = 0D, q2 = 0D, q3 = 0D, q4 = 0D, 
		h1 = 0D, h2 = 0D, 
		m1w1=0D, m1w2=0D, m1w3=0D, m1w4=0D,
		m2w1=0D, m2w2=0D, m2w3=0D, m2w4=0D,
		m3w1=0D, m3w2=0D, m3w3=0D, m3w4=0D, m3w5=0D,
		m4w1=0D, m4w2=0D, m4w3=0D, m4w4=0D,
		m5w1=0D, m5w2=0D, m5w3=0D, m5w4=0D,
		m6w1=0D, m6w2=0D, m6w3=0D, m6w4=0D, m6w5=0D,
		m7w1=0D, m7w2=0D, m7w3=0D, m7w4=0D,
		m8w1=0D, m8w2=0D, m8w3=0D, m8w4=0D,
		m9w1=0D, m9w2=0D, m9w3=0D, m9w4=0D, m9w5=0D,
		m10w1=0D, m10w2=0D, m10w3=0D, m10w4=0D,
		m11w1=0D, m11w2=0D, m11w3=0D, m11w4=0D,
		m12w1=0D, m12w2=0D, m12w3=0D, m12w4=0D, m12w5=0D,
		techANS = 0D, techDCS = 0D, techOth = 0D, techLAN = 0D, techRou = 0D, 
		techSec = 0D, techSto = 0D, techUC = 0D, techUCS = 0D, techVid = 0D, techWLA = 0D;
		List<DashboardGeneralValues> valueSet = null;
		DashboardGeneralValues valueEntry;
		List<DataBaseUniqueTBM> resultData;
		DataBaseUniqueTBMQueries query;
		DataBaseUniqueTBM currentEntry;
		query = new DataBaseUniqueTBMQueries(isPreviousYear);
		String[] nodeArray;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		DefaultListModel lModel = new DefaultListModel();
		DefaultListModel lModel2 = new DefaultListModel();
		List<TopNames> top = new ArrayList<TopNames>();
		List<String> tempTop = new ArrayList<String>();
		List<String> tempTop2 = new ArrayList<String>();
		List<String> tempTop3 = new ArrayList<String>();
		nodeArray = new String[nodeArrayLength];
		if (node.equals("Multiple")) {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", sa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Multiple; Name Single");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String nod = currentEntry.getSalesLevel6();
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	String salesAgent = currentEntry.getSalesAgent();
				    	String par = currentEntry.getPartner();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod) && 
				    				sa.equals(salesAgent) &&
				    				pa.equals(par)) {
			    				tempTop.add(sa);
			    				tempTop2.add(pa);
				    			if(tempTop.contains(sa) && tempTop2.contains(pa) 
				    					&& (!tempTop3.contains(cus))) {
				    				tempTop3.add(cus);
				    			}
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(cus)) {
			    					lModel.addElement(cus);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
			    	for (int p=0; p<tempTop3.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String salesAgent = currentEntry.getSalesAgent();
					    	String par = currentEntry.getPartner();
					    	String cust = currentEntry.getCustomer();
					    	String nodes = currentEntry.getSalesLevel6();
					    	if (sa.equals(salesAgent) &&
					    			pa.equals(par) && 
					    			tempTop3.get(p).equals(cust)) {
						    	for (int i=0; i<nodeArrayLength; i++) {
						    		rev += (nodeArray[i].equals(nodes)) ? value : 0D;
						    	}
					    	}
					    }
			    		top.add(new TopNames(tempTop3.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} else {
				try {
					resultData = query.getAll(node, sa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Single");
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	String salesAgent = currentEntry.getSalesAgent();
				    	String par = currentEntry.getPartner();
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
			    		if (sa.equals(salesAgent) && pa.equals(par)) {
	    				tempTop.add(sa);
	    				tempTop2.add(pa);
		    			if(tempTop.contains(sa) && tempTop2.contains(pa) 
		    					&& (!tempTop3.contains(cus))) {
		    				tempTop3.add(cus);
		    			}
		    			booking += value;
		    			list += value2;
		    			entNWBook += value3;
		    			entNWList += value4;
		    			secBook += value11;
		    			secList += value12;
		    			collBook += value5;
		    			collList += value6;
		    			dcvBook += value7;
		    			dcvList += value8;
		    			atAttach += value9;
		    			nonATAttach += value10;
				    	m1 += value13;
				    	m2 += value14;
				    	m3 += value15;
				    	m4 += value16;
				    	m5 += value17;
				    	m6 += value18;
				    	m7 += value19;
				    	m8 += value20;
				    	m9 += value21;
				    	m10 += value22;
				    	m11 += value23;
				    	m12 += value24;
				    	q1 += value25;
				    	q2 += value26;
				    	q3 += value27;
				    	q4 += value28;
				    	m1w1 +=value42;
				    	m1w2 +=value43;
				    	m1w3 +=value44;
				    	m1w4 +=value45;
				    	m2w1 +=value46;
				    	m2w2 +=value47;
				    	m2w3 +=value48;
				    	m2w4 +=value49;
				    	m3w1 +=value50;
				    	m3w2 +=value51;
				    	m3w3 +=value52;
				    	m3w4 +=value53;
				    	m3w4 +=value54;
				    	m4w1 +=value55;
				    	m4w2 +=value56;
				    	m4w3 +=value57;
				    	m4w4 +=value58;
				    	m5w1 +=value59;
				    	m5w2 +=value60;
				    	m5w3 +=value61;
				    	m5w4 +=value62;
				    	m6w1 +=value63;
				    	m6w2 +=value64;
				    	m6w3 +=value65;
				    	m6w4 +=value66;
				    	m6w5 +=value67;
				    	m7w1 +=value68;
				    	m7w2 +=value69;
				    	m7w3 +=value70;
				    	m7w4 +=value71;
				    	m8w1 +=value72;
				    	m8w2 +=value73;
				    	m8w3 +=value74;
				    	m8w4 +=value75;
				    	m9w1 +=value76;
				    	m9w2 +=value77;
				    	m9w3 +=value78;
				    	m9w4 +=value79;
				    	m9w5 +=value80;
				    	m10w1 +=value81;
				    	m10w2 +=value82;
				    	m10w3 +=value83;
				    	m10w4 +=value84;
				    	m11w1 +=value85;
				    	m11w2 +=value86;
				    	m11w3 +=value87;
				    	m11w4 +=value88;
				    	m12w1 +=value89;
				    	m12w2 +=value90;
				    	m12w3 +=value91;
				    	m12w4 +=value92;
				    	m12w5 +=value93;
				    	techANS += value29;
				    	techDCS += value30;
				    	techOth += value31;
				    	techLAN += value32;
				    	techRou += value33;
				    	techSec += value34;
				    	techSto += value35;
				    	techUC += value36;
				    	techUCS += value37;
				    	techVid += value38;
				    	techWLA += value39;
	    				if (!lModel.contains(cus)) {
	    					lModel.addElement(cus);
	    				}
    					lModel2.addElement(technology);
				    }
					}
			    	for (int p=0; p<tempTop3.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String salesAgent = currentEntry.getSalesAgent();
					    	String par = currentEntry.getPartner();
					    	String cust = currentEntry.getCustomer();
					    	if (sa.equals(salesAgent) &&
					    			pa.equals(par) && 
					    			tempTop3.get(p).equals(cust)) {
					    		rev += value;
					    	}
					    }
			    		top.add(new TopNames(tempTop3.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		valueSet = new ArrayList<DashboardGeneralValues>();
		valueSet.add(new 
				DashboardGeneralValues(
						CalcHelper.getValueInMillionUSD(booking),
						CalcHelper.getValueInMillionUSD(list),
						(double)lModel.getSize(),
						CalcHelper.getValueInMillionUSD(entNWBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(secBook),
						CalcHelper.getValueInMillionUSD(secList),
						CalcHelper.getValueInMillionUSD(collBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(dcvBook),
						CalcHelper.getValueInMillionUSD(dcvList),
						(double)lModel2.getSize(),
						CalcHelper.getValueInMillionUSD(atAttach),
						CalcHelper.getValueInMillionUSD(nonATAttach),
						CalcHelper.getValueInThousandUSD(m1),
						CalcHelper.getValueInThousandUSD(m2),
						CalcHelper.getValueInThousandUSD(m3),
						CalcHelper.getValueInThousandUSD(m4),
						CalcHelper.getValueInThousandUSD(m5),
						CalcHelper.getValueInThousandUSD(m6),
						CalcHelper.getValueInThousandUSD(m7),
						CalcHelper.getValueInThousandUSD(m8),
						CalcHelper.getValueInThousandUSD(m9),
						CalcHelper.getValueInThousandUSD(m10),
						CalcHelper.getValueInThousandUSD(m11),
						CalcHelper.getValueInThousandUSD(m12),
						CalcHelper.getValueInThousandUSD(q1),
						CalcHelper.getValueInThousandUSD(q2),
						CalcHelper.getValueInThousandUSD(q3),
						CalcHelper.getValueInThousandUSD(q4),
						CalcHelper.getValueInThousandUSD(techANS),
						CalcHelper.getValueInThousandUSD(techDCS),
						CalcHelper.getValueInThousandUSD(techOth),
						CalcHelper.getValueInThousandUSD(techLAN),
						CalcHelper.getValueInThousandUSD(techRou),
						CalcHelper.getValueInThousandUSD(techSec),
						CalcHelper.getValueInThousandUSD(techSto),
						CalcHelper.getValueInThousandUSD(techUC),
						CalcHelper.getValueInThousandUSD(techUCS),
						CalcHelper.getValueInThousandUSD(techVid),
						CalcHelper.getValueInThousandUSD(techWLA),
						top,
						CalcHelper.getValueInThousandUSD(m1w1),
						CalcHelper.getValueInThousandUSD(m1w2),
						CalcHelper.getValueInThousandUSD(m1w3),
						CalcHelper.getValueInThousandUSD(m1w4),
						CalcHelper.getValueInThousandUSD(m2w1),
						CalcHelper.getValueInThousandUSD(m2w2),
						CalcHelper.getValueInThousandUSD(m2w3),
						CalcHelper.getValueInThousandUSD(m2w4),
						CalcHelper.getValueInThousandUSD(m3w1),
						CalcHelper.getValueInThousandUSD(m3w2),
						CalcHelper.getValueInThousandUSD(m3w3),
						CalcHelper.getValueInThousandUSD(m3w4),
						CalcHelper.getValueInThousandUSD(m3w5),
						CalcHelper.getValueInThousandUSD(m4w1),
						CalcHelper.getValueInThousandUSD(m4w2),
						CalcHelper.getValueInThousandUSD(m4w3),
						CalcHelper.getValueInThousandUSD(m4w4),
						CalcHelper.getValueInThousandUSD(m5w1),
						CalcHelper.getValueInThousandUSD(m5w2),
						CalcHelper.getValueInThousandUSD(m5w3),
						CalcHelper.getValueInThousandUSD(m5w4),
						CalcHelper.getValueInThousandUSD(m6w1),
						CalcHelper.getValueInThousandUSD(m6w2),
						CalcHelper.getValueInThousandUSD(m6w3),
						CalcHelper.getValueInThousandUSD(m6w4),
						CalcHelper.getValueInThousandUSD(m6w5),
						CalcHelper.getValueInThousandUSD(m7w1),
						CalcHelper.getValueInThousandUSD(m7w2),
						CalcHelper.getValueInThousandUSD(m7w3),
						CalcHelper.getValueInThousandUSD(m7w4),
						CalcHelper.getValueInThousandUSD(m8w1),
						CalcHelper.getValueInThousandUSD(m8w2),
						CalcHelper.getValueInThousandUSD(m8w3),
						CalcHelper.getValueInThousandUSD(m8w4),
						CalcHelper.getValueInThousandUSD(m9w1),
						CalcHelper.getValueInThousandUSD(m9w2),
						CalcHelper.getValueInThousandUSD(m9w3),
						CalcHelper.getValueInThousandUSD(m9w4),
						CalcHelper.getValueInThousandUSD(m9w5),
						CalcHelper.getValueInThousandUSD(m10w1),
						CalcHelper.getValueInThousandUSD(m10w2),
						CalcHelper.getValueInThousandUSD(m10w3),
						CalcHelper.getValueInThousandUSD(m10w4),
						CalcHelper.getValueInThousandUSD(m11w1),
						CalcHelper.getValueInThousandUSD(m11w2),
						CalcHelper.getValueInThousandUSD(m11w3),
						CalcHelper.getValueInThousandUSD(m11w4),
						CalcHelper.getValueInThousandUSD(m12w1),
						CalcHelper.getValueInThousandUSD(m12w2),
						CalcHelper.getValueInThousandUSD(m12w3),
						CalcHelper.getValueInThousandUSD(m12w4),
						CalcHelper.getValueInThousandUSD(m12w5)));
		return valueSet;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static List<DashboardGeneralValues> getTBMCustomerMetricsYTD(JList jlNode, 
			String node, String sa, String pa, String cu, String finYear, String finQuarter, String finMonth, 
			boolean isPreviousYear) {
		int tempCount=0;
		double booking = 0D, list = 0D, entNWBook = 0D, entNWList = 0D,
		secBook = 0D, secList = 0D, collBook = 0D, collList = 0D, dcvBook = 0D, 
		dcvList = 0D, atAttach = 0D, nonATAttach = 0D, techs = 0D, 
		m1 = 0D, m2 = 0D, m3 = 0D, m4 = 0D, m5 = 0D, m6 = 0D, m7 = 0D, m8 = 0D, 
		m9 = 0D, m10 = 0D, m11 = 0D, m12 = 0D, q1 = 0D, q2 = 0D, q3 = 0D, q4 = 0D, 
		h1 = 0D, h2 = 0D, 
		m1w1=0D, m1w2=0D, m1w3=0D, m1w4=0D,
		m2w1=0D, m2w2=0D, m2w3=0D, m2w4=0D,
		m3w1=0D, m3w2=0D, m3w3=0D, m3w4=0D, m3w5=0D,
		m4w1=0D, m4w2=0D, m4w3=0D, m4w4=0D,
		m5w1=0D, m5w2=0D, m5w3=0D, m5w4=0D,
		m6w1=0D, m6w2=0D, m6w3=0D, m6w4=0D, m6w5=0D,
		m7w1=0D, m7w2=0D, m7w3=0D, m7w4=0D,
		m8w1=0D, m8w2=0D, m8w3=0D, m8w4=0D,
		m9w1=0D, m9w2=0D, m9w3=0D, m9w4=0D, m9w5=0D,
		m10w1=0D, m10w2=0D, m10w3=0D, m10w4=0D,
		m11w1=0D, m11w2=0D, m11w3=0D, m11w4=0D,
		m12w1=0D, m12w2=0D, m12w3=0D, m12w4=0D, m12w5=0D,
		techANS = 0D, techDCS = 0D, techOth = 0D, techLAN = 0D, techRou = 0D, 
		techSec = 0D, techSto = 0D, techUC = 0D, techUCS = 0D, techVid = 0D, techWLA = 0D;
		List<DashboardGeneralValues> valueSet = null;
		DashboardGeneralValues valueEntry;
		List<DataBaseUniqueTBM> resultData;
		DataBaseUniqueTBMQueries query;
		DataBaseUniqueTBM currentEntry;
		query = new DataBaseUniqueTBMQueries(isPreviousYear);
		String[] nodeArray;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		DefaultListModel lModel = new DefaultListModel();
		DefaultListModel lModel2 = new DefaultListModel();
		List<TopNames> top = new ArrayList<TopNames>();
		List<String> tempTop = new ArrayList<String>();
		List<String> tempTop2 = new ArrayList<String>();
		List<String> tempTop3 = new ArrayList<String>();
		nodeArray = new String[nodeArrayLength];
		if (node.equals("Multiple")) {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", sa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Multiple; Name Single");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String nod = currentEntry.getSalesLevel6();
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	String salesAgent = currentEntry.getSalesAgent();
				    	String par = currentEntry.getPartner();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod) && 
				    				sa.equals(salesAgent) &&
				    				pa.equals(par) &&
				    				cu.equals(cus)) {
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(cus)) {
			    					lModel.addElement(cus);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} else {
				try {
					resultData = query.getAll(node, sa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Single");
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	String salesAgent = currentEntry.getSalesAgent();
				    	String par = currentEntry.getPartner();
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
			    		if (sa.equals(salesAgent) && pa.equals(par) &&
			    				cu.equals(cus)) {
			    			booking += value;
			    			list += value2;
			    			entNWBook += value3;
			    			entNWList += value4;
			    			secBook += value11;
			    			secList += value12;
			    			collBook += value5;
			    			collList += value6;
			    			dcvBook += value7;
			    			dcvList += value8;
			    			atAttach += value9;
			    			nonATAttach += value10;
					    	m1 += value13;
					    	m2 += value14;
					    	m3 += value15;
					    	m4 += value16;
					    	m5 += value17;
					    	m6 += value18;
					    	m7 += value19;
					    	m8 += value20;
					    	m9 += value21;
					    	m10 += value22;
					    	m11 += value23;
					    	m12 += value24;
					    	q1 += value25;
					    	q2 += value26;
					    	q3 += value27;
					    	q4 += value28;
					    	m1w1 +=value42;
					    	m1w2 +=value43;
					    	m1w3 +=value44;
					    	m1w4 +=value45;
					    	m2w1 +=value46;
					    	m2w2 +=value47;
					    	m2w3 +=value48;
					    	m2w4 +=value49;
					    	m3w1 +=value50;
					    	m3w2 +=value51;
					    	m3w3 +=value52;
					    	m3w4 +=value53;
					    	m3w4 +=value54;
					    	m4w1 +=value55;
					    	m4w2 +=value56;
					    	m4w3 +=value57;
					    	m4w4 +=value58;
					    	m5w1 +=value59;
					    	m5w2 +=value60;
					    	m5w3 +=value61;
					    	m5w4 +=value62;
					    	m6w1 +=value63;
					    	m6w2 +=value64;
					    	m6w3 +=value65;
					    	m6w4 +=value66;
					    	m6w5 +=value67;
					    	m7w1 +=value68;
					    	m7w2 +=value69;
					    	m7w3 +=value70;
					    	m7w4 +=value71;
					    	m8w1 +=value72;
					    	m8w2 +=value73;
					    	m8w3 +=value74;
					    	m8w4 +=value75;
					    	m9w1 +=value76;
					    	m9w2 +=value77;
					    	m9w3 +=value78;
					    	m9w4 +=value79;
					    	m9w5 +=value80;
					    	m10w1 +=value81;
					    	m10w2 +=value82;
					    	m10w3 +=value83;
					    	m10w4 +=value84;
					    	m11w1 +=value85;
					    	m11w2 +=value86;
					    	m11w3 +=value87;
					    	m11w4 +=value88;
					    	m12w1 +=value89;
					    	m12w2 +=value90;
					    	m12w3 +=value91;
					    	m12w4 +=value92;
					    	m12w5 +=value93;
					    	techANS += value29;
					    	techDCS += value30;
					    	techOth += value31;
					    	techLAN += value32;
					    	techRou += value33;
					    	techSec += value34;
					    	techSto += value35;
					    	techUC += value36;
					    	techUCS += value37;
					    	techVid += value38;
					    	techWLA += value39;
	    				if (!lModel.contains(cus)) {
	    					lModel.addElement(cus);
	    				}
    					lModel2.addElement(technology);
				    }
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		valueSet = new ArrayList<DashboardGeneralValues>();
		valueSet.add(new 
				DashboardGeneralValues(
						CalcHelper.getValueInMillionUSD(booking),
						CalcHelper.getValueInMillionUSD(list),
						(double)lModel.getSize(),
						CalcHelper.getValueInMillionUSD(entNWBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(secBook),
						CalcHelper.getValueInMillionUSD(secList),
						CalcHelper.getValueInMillionUSD(collBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(dcvBook),
						CalcHelper.getValueInMillionUSD(dcvList),
						(double)lModel2.getSize(),
						CalcHelper.getValueInMillionUSD(atAttach),
						CalcHelper.getValueInMillionUSD(nonATAttach),
						CalcHelper.getValueInThousandUSD(m1),
						CalcHelper.getValueInThousandUSD(m2),
						CalcHelper.getValueInThousandUSD(m3),
						CalcHelper.getValueInThousandUSD(m4),
						CalcHelper.getValueInThousandUSD(m5),
						CalcHelper.getValueInThousandUSD(m6),
						CalcHelper.getValueInThousandUSD(m7),
						CalcHelper.getValueInThousandUSD(m8),
						CalcHelper.getValueInThousandUSD(m9),
						CalcHelper.getValueInThousandUSD(m10),
						CalcHelper.getValueInThousandUSD(m11),
						CalcHelper.getValueInThousandUSD(m12),
						CalcHelper.getValueInThousandUSD(q1),
						CalcHelper.getValueInThousandUSD(q2),
						CalcHelper.getValueInThousandUSD(q3),
						CalcHelper.getValueInThousandUSD(q4),
						CalcHelper.getValueInThousandUSD(techANS),
						CalcHelper.getValueInThousandUSD(techDCS),
						CalcHelper.getValueInThousandUSD(techOth),
						CalcHelper.getValueInThousandUSD(techLAN),
						CalcHelper.getValueInThousandUSD(techRou),
						CalcHelper.getValueInThousandUSD(techSec),
						CalcHelper.getValueInThousandUSD(techSto),
						CalcHelper.getValueInThousandUSD(techUC),
						CalcHelper.getValueInThousandUSD(techUCS),
						CalcHelper.getValueInThousandUSD(techVid),
						CalcHelper.getValueInThousandUSD(techWLA),
						top,
						CalcHelper.getValueInThousandUSD(m1w1),
						CalcHelper.getValueInThousandUSD(m1w2),
						CalcHelper.getValueInThousandUSD(m1w3),
						CalcHelper.getValueInThousandUSD(m1w4),
						CalcHelper.getValueInThousandUSD(m2w1),
						CalcHelper.getValueInThousandUSD(m2w2),
						CalcHelper.getValueInThousandUSD(m2w3),
						CalcHelper.getValueInThousandUSD(m2w4),
						CalcHelper.getValueInThousandUSD(m3w1),
						CalcHelper.getValueInThousandUSD(m3w2),
						CalcHelper.getValueInThousandUSD(m3w3),
						CalcHelper.getValueInThousandUSD(m3w4),
						CalcHelper.getValueInThousandUSD(m3w5),
						CalcHelper.getValueInThousandUSD(m4w1),
						CalcHelper.getValueInThousandUSD(m4w2),
						CalcHelper.getValueInThousandUSD(m4w3),
						CalcHelper.getValueInThousandUSD(m4w4),
						CalcHelper.getValueInThousandUSD(m5w1),
						CalcHelper.getValueInThousandUSD(m5w2),
						CalcHelper.getValueInThousandUSD(m5w3),
						CalcHelper.getValueInThousandUSD(m5w4),
						CalcHelper.getValueInThousandUSD(m6w1),
						CalcHelper.getValueInThousandUSD(m6w2),
						CalcHelper.getValueInThousandUSD(m6w3),
						CalcHelper.getValueInThousandUSD(m6w4),
						CalcHelper.getValueInThousandUSD(m6w5),
						CalcHelper.getValueInThousandUSD(m7w1),
						CalcHelper.getValueInThousandUSD(m7w2),
						CalcHelper.getValueInThousandUSD(m7w3),
						CalcHelper.getValueInThousandUSD(m7w4),
						CalcHelper.getValueInThousandUSD(m8w1),
						CalcHelper.getValueInThousandUSD(m8w2),
						CalcHelper.getValueInThousandUSD(m8w3),
						CalcHelper.getValueInThousandUSD(m8w4),
						CalcHelper.getValueInThousandUSD(m9w1),
						CalcHelper.getValueInThousandUSD(m9w2),
						CalcHelper.getValueInThousandUSD(m9w3),
						CalcHelper.getValueInThousandUSD(m9w4),
						CalcHelper.getValueInThousandUSD(m9w5),
						CalcHelper.getValueInThousandUSD(m10w1),
						CalcHelper.getValueInThousandUSD(m10w2),
						CalcHelper.getValueInThousandUSD(m10w3),
						CalcHelper.getValueInThousandUSD(m10w4),
						CalcHelper.getValueInThousandUSD(m11w1),
						CalcHelper.getValueInThousandUSD(m11w2),
						CalcHelper.getValueInThousandUSD(m11w3),
						CalcHelper.getValueInThousandUSD(m11w4),
						CalcHelper.getValueInThousandUSD(m12w1),
						CalcHelper.getValueInThousandUSD(m12w2),
						CalcHelper.getValueInThousandUSD(m12w3),
						CalcHelper.getValueInThousandUSD(m12w4),
						CalcHelper.getValueInThousandUSD(m12w5)));
		return valueSet;
	}

	
	public static String getSelectedTopNames(JButton jb1, JButton jb2, 
			JButton jb3, JButton jb4, JButton jb5) {
		String selectedText = null;
		
		if (jb1.getForeground().equals(Color.RED.darker())) {
			selectedText = jb1.getText();
		} else if (jb2.getForeground().equals(Color.RED.darker())) {
			selectedText = jb2.getText();
		} else if (jb3.getForeground().equals(Color.RED.darker())) {
			selectedText = jb3.getText();
		} else if (jb4.getForeground().equals(Color.RED.darker())) {
			selectedText = jb4.getText();
		} else selectedText = jb2.getText();
		
		return selectedText;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static List<DashboardGeneralValues> getPartnerMetricsYTD(JList jlPa, JList jlNode, 
			String node, String pa, String finYear, String finQuarter, String finMonth, 
			boolean isPreviousYear) {
		int tempCount=0;
		double booking = 0D, list = 0D, entNWBook = 0D, entNWList = 0D,
		secBook = 0D, secList = 0D, collBook = 0D, collList = 0D, dcvBook = 0D, 
		dcvList = 0D, atAttach = 0D, nonATAttach = 0D, techs = 0D, 
		m1 = 0D, m2 = 0D, m3 = 0D, m4 = 0D, m5 = 0D, m6 = 0D, m7 = 0D, m8 = 0D, 
		m9 = 0D, m10 = 0D, m11 = 0D, m12 = 0D, q1 = 0D, q2 = 0D, q3 = 0D, q4 = 0D, 
		h1 = 0D, h2 = 0D, 
		m1w1=0D, m1w2=0D, m1w3=0D, m1w4=0D,
		m2w1=0D, m2w2=0D, m2w3=0D, m2w4=0D,
		m3w1=0D, m3w2=0D, m3w3=0D, m3w4=0D, m3w5=0D,
		m4w1=0D, m4w2=0D, m4w3=0D, m4w4=0D,
		m5w1=0D, m5w2=0D, m5w3=0D, m5w4=0D,
		m6w1=0D, m6w2=0D, m6w3=0D, m6w4=0D, m6w5=0D,
		m7w1=0D, m7w2=0D, m7w3=0D, m7w4=0D,
		m8w1=0D, m8w2=0D, m8w3=0D, m8w4=0D,
		m9w1=0D, m9w2=0D, m9w3=0D, m9w4=0D, m9w5=0D,
		m10w1=0D, m10w2=0D, m10w3=0D, m10w4=0D,
		m11w1=0D, m11w2=0D, m11w3=0D, m11w4=0D,
		m12w1=0D, m12w2=0D, m12w3=0D, m12w4=0D, m12w5=0D,
		techANS = 0D, techDCS = 0D, techOth = 0D, techLAN = 0D, techRou = 0D, 
		techSec = 0D, techSto = 0D, techUC = 0D, techUCS = 0D, techVid = 0D, techWLA = 0D;
		List<DashboardGeneralValues> valueSet = null;
		DashboardGeneralValues valueEntry;
		List<DataBaseUniquePartner> resultData;
		DataBaseUniquePartnerQueries query;
		DataBaseUniquePartner currentEntry;
		query = new DataBaseUniquePartnerQueries(isPreviousYear);
		String[] paArray, nodeArray;
		int paArrayLength = ComponentHelper.getListInArray(jlPa).length;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		DefaultListModel lModel = new DefaultListModel();
		DefaultListModel lModel2 = new DefaultListModel();
		List<TopNames> top = new ArrayList<TopNames>();
		List<String> tempTop = new ArrayList<String>();
		paArray = new String[paArrayLength];
		nodeArray = new String[nodeArrayLength];
		if (node.equals("Multiple")) {
			if (pa.equals("Multiple")) {
				paArray = ComponentHelper.getListInArray(jlPa);
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", "ALL", finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Multiple; Name Muliple");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String partner = currentEntry.getPartner();
				    	String nod = currentEntry.getSalesLevel6();
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	for (int i=0; i<nodeArrayLength; i++) {
					    	for (int l=0; l<paArrayLength; l++) {
					    		if (paArray[l].equals(partner) && 
					    				nodeArray[i].equals(nod)) {
					    			if(!tempTop.contains(partner)) {
					    				tempTop.add(partner);
					    			}
					    			booking += value;
					    			list += value2;
					    			entNWBook += value3;
					    			entNWList += value4;
					    			secBook += value11;
					    			secList += value12;
					    			collBook += value5;
					    			collList += value6;
					    			dcvBook += value7;
					    			dcvList += value8;
					    			atAttach += value9;
					    			nonATAttach += value10;
							    	m1 += value13;
							    	m2 += value14;
							    	m3 += value15;
							    	m4 += value16;
							    	m5 += value17;
							    	m6 += value18;
							    	m7 += value19;
							    	m8 += value20;
							    	m9 += value21;
							    	m10 += value22;
							    	m11 += value23;
							    	m12 += value24;
							    	q1 += value25;
							    	q2 += value26;
							    	q3 += value27;
							    	q4 += value28;
							    	m1w1 +=value42;
							    	m1w2 +=value43;
							    	m1w3 +=value44;
							    	m1w4 +=value45;
							    	m2w1 +=value46;
							    	m2w2 +=value47;
							    	m2w3 +=value48;
							    	m2w4 +=value49;
							    	m3w1 +=value50;
							    	m3w2 +=value51;
							    	m3w3 +=value52;
							    	m3w4 +=value53;
							    	m3w4 +=value54;
							    	m4w1 +=value55;
							    	m4w2 +=value56;
							    	m4w3 +=value57;
							    	m4w4 +=value58;
							    	m5w1 +=value59;
							    	m5w2 +=value60;
							    	m5w3 +=value61;
							    	m5w4 +=value62;
							    	m6w1 +=value63;
							    	m6w2 +=value64;
							    	m6w3 +=value65;
							    	m6w4 +=value66;
							    	m6w5 +=value67;
							    	m7w1 +=value68;
							    	m7w2 +=value69;
							    	m7w3 +=value70;
							    	m7w4 +=value71;
							    	m8w1 +=value72;
							    	m8w2 +=value73;
							    	m8w3 +=value74;
							    	m8w4 +=value75;
							    	m9w1 +=value76;
							    	m9w2 +=value77;
							    	m9w3 +=value78;
							    	m9w4 +=value79;
							    	m9w5 +=value80;
							    	m10w1 +=value81;
							    	m10w2 +=value82;
							    	m10w3 +=value83;
							    	m10w4 +=value84;
							    	m11w1 +=value85;
							    	m11w2 +=value86;
							    	m11w3 +=value87;
							    	m11w4 +=value88;
							    	m12w1 +=value89;
							    	m12w2 +=value90;
							    	m12w3 +=value91;
							    	m12w4 +=value92;
							    	m12w5 +=value93;
							    	techANS += value29;
							    	techDCS += value30;
							    	techOth += value31;
							    	techLAN += value32;
							    	techRou += value33;
							    	techSec += value34;
							    	techSto += value35;
							    	techUC += value36;
							    	techUCS += value37;
							    	techVid += value38;
							    	techWLA += value39;
				    				if (!lModel.contains(cus)) {
				    					lModel.addElement(cus);
				    				}
			    					lModel2.addElement(technology);
					    		}
					    	}
				    	}
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String partner = currentEntry.getPartner();
					    	String nodes = currentEntry.getSalesLevel6();
					    	if (tempTop.get(p).equals(partner)) {
						    	for (int i=0; i<nodeArrayLength; i++) {
						    		rev += (nodeArray[i].equals(nodes)) ? value : 0D;
						    	}
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", pa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Multiple; Name Single");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String nod = currentEntry.getSalesLevel6();
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod)) {
				    			if(!tempTop.contains(pa)) {
				    				tempTop.add(pa);
				    			}
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(cus)) {
			    					lModel.addElement(cus);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String partner = currentEntry.getPartner();
					    	String nodes = currentEntry.getSalesLevel6();
					    	if (tempTop.get(p).equals(partner)) {
						    	for (int i=0; i<nodeArrayLength; i++) {
						    		rev += (nodeArray[i].equals(nodes)) ? value : 0D;
						    	}
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (pa.equals("Multiple")) {
				paArray = ComponentHelper.getListInArray(jlPa);
				try {
					resultData = query.getAll(node, "ALL", finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Multiple");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String partner = currentEntry.getPartner();
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	for (int i=0; i<paArrayLength; i++) {
				    		if (paArray[i].equals(partner)) {
				    			if(!tempTop.contains(partner)) {
				    				tempTop.add(partner);
				    			}
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(cus)) {
			    					lModel.addElement(cus);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String partner = currentEntry.getPartner();
					    	if (tempTop.get(p).equals(partner)) {
					    		rev += value;
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					resultData = query.getAll(node, pa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Single");
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
		    			if(!tempTop.contains(pa)) {
		    				tempTop.add(pa);
		    			}

		    			booking += value;
		    			list += value2;
		    			entNWBook += value3;
		    			entNWList += value4;
		    			secBook += value11;
		    			secList += value12;
		    			collBook += value5;
		    			collList += value6;
		    			dcvBook += value7;
		    			dcvList += value8;
		    			atAttach += value9;
		    			nonATAttach += value10;
				    	m1 += value13;
				    	m2 += value14;
				    	m3 += value15;
				    	m4 += value16;
				    	m5 += value17;
				    	m6 += value18;
				    	m7 += value19;
				    	m8 += value20;
				    	m9 += value21;
				    	m10 += value22;
				    	m11 += value23;
				    	m12 += value24;
				    	q1 += value25;
				    	q2 += value26;
				    	q3 += value27;
				    	q4 += value28;
				    	m1w1 +=value42;
				    	m1w2 +=value43;
				    	m1w3 +=value44;
				    	m1w4 +=value45;
				    	m2w1 +=value46;
				    	m2w2 +=value47;
				    	m2w3 +=value48;
				    	m2w4 +=value49;
				    	m3w1 +=value50;
				    	m3w2 +=value51;
				    	m3w3 +=value52;
				    	m3w4 +=value53;
				    	m3w4 +=value54;
				    	m4w1 +=value55;
				    	m4w2 +=value56;
				    	m4w3 +=value57;
				    	m4w4 +=value58;
				    	m5w1 +=value59;
				    	m5w2 +=value60;
				    	m5w3 +=value61;
				    	m5w4 +=value62;
				    	m6w1 +=value63;
				    	m6w2 +=value64;
				    	m6w3 +=value65;
				    	m6w4 +=value66;
				    	m6w5 +=value67;
				    	m7w1 +=value68;
				    	m7w2 +=value69;
				    	m7w3 +=value70;
				    	m7w4 +=value71;
				    	m8w1 +=value72;
				    	m8w2 +=value73;
				    	m8w3 +=value74;
				    	m8w4 +=value75;
				    	m9w1 +=value76;
				    	m9w2 +=value77;
				    	m9w3 +=value78;
				    	m9w4 +=value79;
				    	m9w5 +=value80;
				    	m10w1 +=value81;
				    	m10w2 +=value82;
				    	m10w3 +=value83;
				    	m10w4 +=value84;
				    	m11w1 +=value85;
				    	m11w2 +=value86;
				    	m11w3 +=value87;
				    	m11w4 +=value88;
				    	m12w1 +=value89;
				    	m12w2 +=value90;
				    	m12w3 +=value91;
				    	m12w4 +=value92;
				    	m12w5 +=value93;
				    	techANS += value29;
				    	techDCS += value30;
				    	techOth += value31;
				    	techLAN += value32;
				    	techRou += value33;
				    	techSec += value34;
				    	techSto += value35;
				    	techUC += value36;
				    	techUCS += value37;
				    	techVid += value38;
				    	techWLA += value39;
	    				if (!lModel.contains(cus)) {
	    					lModel.addElement(cus);
	    				}
    					lModel2.addElement(technology);
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String partner = currentEntry.getPartner();
					    	if (tempTop.get(p).equals(partner)) {
					    		rev += value;
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		valueSet = new ArrayList<DashboardGeneralValues>();
		valueSet.add(new 
				DashboardGeneralValues(
						CalcHelper.getValueInMillionUSD(booking),
						CalcHelper.getValueInMillionUSD(list),
						(double)lModel.getSize(),
						CalcHelper.getValueInMillionUSD(entNWBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(secBook),
						CalcHelper.getValueInMillionUSD(secList),
						CalcHelper.getValueInMillionUSD(collBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(dcvBook),
						CalcHelper.getValueInMillionUSD(dcvList),
						(double)lModel2.getSize(),
						CalcHelper.getValueInMillionUSD(atAttach),
						CalcHelper.getValueInMillionUSD(nonATAttach),
						CalcHelper.getValueInThousandUSD(m1),
						CalcHelper.getValueInThousandUSD(m2),
						CalcHelper.getValueInThousandUSD(m3),
						CalcHelper.getValueInThousandUSD(m4),
						CalcHelper.getValueInThousandUSD(m5),
						CalcHelper.getValueInThousandUSD(m6),
						CalcHelper.getValueInThousandUSD(m7),
						CalcHelper.getValueInThousandUSD(m8),
						CalcHelper.getValueInThousandUSD(m9),
						CalcHelper.getValueInThousandUSD(m10),
						CalcHelper.getValueInThousandUSD(m11),
						CalcHelper.getValueInThousandUSD(m12),
						CalcHelper.getValueInThousandUSD(q1),
						CalcHelper.getValueInThousandUSD(q2),
						CalcHelper.getValueInThousandUSD(q3),
						CalcHelper.getValueInThousandUSD(q4),
						CalcHelper.getValueInThousandUSD(techANS),
						CalcHelper.getValueInThousandUSD(techDCS),
						CalcHelper.getValueInThousandUSD(techOth),
						CalcHelper.getValueInThousandUSD(techLAN),
						CalcHelper.getValueInThousandUSD(techRou),
						CalcHelper.getValueInThousandUSD(techSec),
						CalcHelper.getValueInThousandUSD(techSto),
						CalcHelper.getValueInThousandUSD(techUC),
						CalcHelper.getValueInThousandUSD(techUCS),
						CalcHelper.getValueInThousandUSD(techVid),
						CalcHelper.getValueInThousandUSD(techWLA),
						top,
						CalcHelper.getValueInThousandUSD(m1w1),
						CalcHelper.getValueInThousandUSD(m1w2),
						CalcHelper.getValueInThousandUSD(m1w3),
						CalcHelper.getValueInThousandUSD(m1w4),
						CalcHelper.getValueInThousandUSD(m2w1),
						CalcHelper.getValueInThousandUSD(m2w2),
						CalcHelper.getValueInThousandUSD(m2w3),
						CalcHelper.getValueInThousandUSD(m2w4),
						CalcHelper.getValueInThousandUSD(m3w1),
						CalcHelper.getValueInThousandUSD(m3w2),
						CalcHelper.getValueInThousandUSD(m3w3),
						CalcHelper.getValueInThousandUSD(m3w4),
						CalcHelper.getValueInThousandUSD(m3w5),
						CalcHelper.getValueInThousandUSD(m4w1),
						CalcHelper.getValueInThousandUSD(m4w2),
						CalcHelper.getValueInThousandUSD(m4w3),
						CalcHelper.getValueInThousandUSD(m4w4),
						CalcHelper.getValueInThousandUSD(m5w1),
						CalcHelper.getValueInThousandUSD(m5w2),
						CalcHelper.getValueInThousandUSD(m5w3),
						CalcHelper.getValueInThousandUSD(m5w4),
						CalcHelper.getValueInThousandUSD(m6w1),
						CalcHelper.getValueInThousandUSD(m6w2),
						CalcHelper.getValueInThousandUSD(m6w3),
						CalcHelper.getValueInThousandUSD(m6w4),
						CalcHelper.getValueInThousandUSD(m6w5),
						CalcHelper.getValueInThousandUSD(m7w1),
						CalcHelper.getValueInThousandUSD(m7w2),
						CalcHelper.getValueInThousandUSD(m7w3),
						CalcHelper.getValueInThousandUSD(m7w4),
						CalcHelper.getValueInThousandUSD(m8w1),
						CalcHelper.getValueInThousandUSD(m8w2),
						CalcHelper.getValueInThousandUSD(m8w3),
						CalcHelper.getValueInThousandUSD(m8w4),
						CalcHelper.getValueInThousandUSD(m9w1),
						CalcHelper.getValueInThousandUSD(m9w2),
						CalcHelper.getValueInThousandUSD(m9w3),
						CalcHelper.getValueInThousandUSD(m9w4),
						CalcHelper.getValueInThousandUSD(m9w5),
						CalcHelper.getValueInThousandUSD(m10w1),
						CalcHelper.getValueInThousandUSD(m10w2),
						CalcHelper.getValueInThousandUSD(m10w3),
						CalcHelper.getValueInThousandUSD(m10w4),
						CalcHelper.getValueInThousandUSD(m11w1),
						CalcHelper.getValueInThousandUSD(m11w2),
						CalcHelper.getValueInThousandUSD(m11w3),
						CalcHelper.getValueInThousandUSD(m11w4),
						CalcHelper.getValueInThousandUSD(m12w1),
						CalcHelper.getValueInThousandUSD(m12w2),
						CalcHelper.getValueInThousandUSD(m12w3),
						CalcHelper.getValueInThousandUSD(m12w4),
						CalcHelper.getValueInThousandUSD(m12w5)));
		return valueSet;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static List<DashboardGeneralValues> getPartnerCustomerMetricsYTD(JList jlNode, 
			String node, String pa, String finYear, String finQuarter, String finMonth, 
			boolean isPreviousYear) {
		int tempCount=0;
		double booking = 0D, list = 0D, entNWBook = 0D, entNWList = 0D,
		secBook = 0D, secList = 0D, collBook = 0D, collList = 0D, dcvBook = 0D, 
		dcvList = 0D, atAttach = 0D, nonATAttach = 0D, techs = 0D, 
		m1 = 0D, m2 = 0D, m3 = 0D, m4 = 0D, m5 = 0D, m6 = 0D, m7 = 0D, m8 = 0D, 
		m9 = 0D, m10 = 0D, m11 = 0D, m12 = 0D, q1 = 0D, q2 = 0D, q3 = 0D, q4 = 0D, 
		h1 = 0D, h2 = 0D, 
		m1w1=0D, m1w2=0D, m1w3=0D, m1w4=0D,
		m2w1=0D, m2w2=0D, m2w3=0D, m2w4=0D,
		m3w1=0D, m3w2=0D, m3w3=0D, m3w4=0D, m3w5=0D,
		m4w1=0D, m4w2=0D, m4w3=0D, m4w4=0D,
		m5w1=0D, m5w2=0D, m5w3=0D, m5w4=0D,
		m6w1=0D, m6w2=0D, m6w3=0D, m6w4=0D, m6w5=0D,
		m7w1=0D, m7w2=0D, m7w3=0D, m7w4=0D,
		m8w1=0D, m8w2=0D, m8w3=0D, m8w4=0D,
		m9w1=0D, m9w2=0D, m9w3=0D, m9w4=0D, m9w5=0D,
		m10w1=0D, m10w2=0D, m10w3=0D, m10w4=0D,
		m11w1=0D, m11w2=0D, m11w3=0D, m11w4=0D,
		m12w1=0D, m12w2=0D, m12w3=0D, m12w4=0D, m12w5=0D,
		techANS = 0D, techDCS = 0D, techOth = 0D, techLAN = 0D, techRou = 0D, 
		techSec = 0D, techSto = 0D, techUC = 0D, techUCS = 0D, techVid = 0D, techWLA = 0D;
		List<DashboardGeneralValues> valueSet = null;
		DashboardGeneralValues valueEntry;
		List<DataBaseUniquePartner> resultData;
		DataBaseUniquePartnerQueries query;
		DataBaseUniquePartner currentEntry;
		query = new DataBaseUniquePartnerQueries(isPreviousYear);
		String[] nodeArray;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		DefaultListModel lModel = new DefaultListModel();
		DefaultListModel lModel2 = new DefaultListModel();
		List<TopNames> top = new ArrayList<TopNames>();
		List<String> tempTop = new ArrayList<String>();
		List<String> tempTop2 = new ArrayList<String>();
		nodeArray = new String[nodeArrayLength];
		if (node.equals("Multiple")) {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", pa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Multiple; Name Single");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String nod = currentEntry.getSalesLevel6();
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	String partner = currentEntry.getPartner();
				    	String par = currentEntry.getPartner();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod) && 
				    				pa.equals(partner)) {
			    				tempTop.add(pa);
				    			if(tempTop.contains(pa) 
				    					&& (!tempTop2.contains(cus))) {
				    				tempTop2.add(cus);
				    			}
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(cus)) {
			    					lModel.addElement(cus);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
			    	for (int p=0; p<tempTop2.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String partner = currentEntry.getPartner();
					    	String cust = currentEntry.getCustomer();
					    	String nodes = currentEntry.getSalesLevel6();
					    	if (pa.equals(partner) && 
					    			tempTop2.get(p).equals(cust)) {
						    	for (int i=0; i<nodeArrayLength; i++) {
						    		rev += (nodeArray[i].equals(nodes)) ? value : 0D;
						    	}
					    	}
					    }
			    		top.add(new TopNames(tempTop2.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} else {
				try {
					resultData = query.getAll(node, pa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Single");
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	String partner = currentEntry.getPartner();
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
			    		if (pa.equals(partner)) {
	    				tempTop.add(pa);
		    			if(tempTop.contains(pa)	&& (!tempTop2.contains(cus))) {
		    				tempTop2.add(cus);
		    			}
		    			booking += value;
		    			list += value2;
		    			entNWBook += value3;
		    			entNWList += value4;
		    			secBook += value11;
		    			secList += value12;
		    			collBook += value5;
		    			collList += value6;
		    			dcvBook += value7;
		    			dcvList += value8;
		    			atAttach += value9;
		    			nonATAttach += value10;
				    	m1 += value13;
				    	m2 += value14;
				    	m3 += value15;
				    	m4 += value16;
				    	m5 += value17;
				    	m6 += value18;
				    	m7 += value19;
				    	m8 += value20;
				    	m9 += value21;
				    	m10 += value22;
				    	m11 += value23;
				    	m12 += value24;
				    	q1 += value25;
				    	q2 += value26;
				    	q3 += value27;
				    	q4 += value28;
				    	m1w1 +=value42;
				    	m1w2 +=value43;
				    	m1w3 +=value44;
				    	m1w4 +=value45;
				    	m2w1 +=value46;
				    	m2w2 +=value47;
				    	m2w3 +=value48;
				    	m2w4 +=value49;
				    	m3w1 +=value50;
				    	m3w2 +=value51;
				    	m3w3 +=value52;
				    	m3w4 +=value53;
				    	m3w4 +=value54;
				    	m4w1 +=value55;
				    	m4w2 +=value56;
				    	m4w3 +=value57;
				    	m4w4 +=value58;
				    	m5w1 +=value59;
				    	m5w2 +=value60;
				    	m5w3 +=value61;
				    	m5w4 +=value62;
				    	m6w1 +=value63;
				    	m6w2 +=value64;
				    	m6w3 +=value65;
				    	m6w4 +=value66;
				    	m6w5 +=value67;
				    	m7w1 +=value68;
				    	m7w2 +=value69;
				    	m7w3 +=value70;
				    	m7w4 +=value71;
				    	m8w1 +=value72;
				    	m8w2 +=value73;
				    	m8w3 +=value74;
				    	m8w4 +=value75;
				    	m9w1 +=value76;
				    	m9w2 +=value77;
				    	m9w3 +=value78;
				    	m9w4 +=value79;
				    	m9w5 +=value80;
				    	m10w1 +=value81;
				    	m10w2 +=value82;
				    	m10w3 +=value83;
				    	m10w4 +=value84;
				    	m11w1 +=value85;
				    	m11w2 +=value86;
				    	m11w3 +=value87;
				    	m11w4 +=value88;
				    	m12w1 +=value89;
				    	m12w2 +=value90;
				    	m12w3 +=value91;
				    	m12w4 +=value92;
				    	m12w5 +=value93;
				    	techANS += value29;
				    	techDCS += value30;
				    	techOth += value31;
				    	techLAN += value32;
				    	techRou += value33;
				    	techSec += value34;
				    	techSto += value35;
				    	techUC += value36;
				    	techUCS += value37;
				    	techVid += value38;
				    	techWLA += value39;
	    				if (!lModel.contains(cus)) {
	    					lModel.addElement(cus);
	    				}
    					lModel2.addElement(technology);
				    }
					}
			    	for (int p=0; p<tempTop2.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String partner = currentEntry.getPartner();
					    	String cust = currentEntry.getCustomer();
					    	if (pa.equals(partner) && tempTop2.get(p).equals(cust)) {
					    		rev += value;
					    	}
					    }
			    		top.add(new TopNames(tempTop2.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		valueSet = new ArrayList<DashboardGeneralValues>();
		valueSet.add(new 
				DashboardGeneralValues(
						CalcHelper.getValueInMillionUSD(booking),
						CalcHelper.getValueInMillionUSD(list),
						(double)lModel.getSize(),
						CalcHelper.getValueInMillionUSD(entNWBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(secBook),
						CalcHelper.getValueInMillionUSD(secList),
						CalcHelper.getValueInMillionUSD(collBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(dcvBook),
						CalcHelper.getValueInMillionUSD(dcvList),
						(double)lModel2.getSize(),
						CalcHelper.getValueInMillionUSD(atAttach),
						CalcHelper.getValueInMillionUSD(nonATAttach),
						CalcHelper.getValueInThousandUSD(m1),
						CalcHelper.getValueInThousandUSD(m2),
						CalcHelper.getValueInThousandUSD(m3),
						CalcHelper.getValueInThousandUSD(m4),
						CalcHelper.getValueInThousandUSD(m5),
						CalcHelper.getValueInThousandUSD(m6),
						CalcHelper.getValueInThousandUSD(m7),
						CalcHelper.getValueInThousandUSD(m8),
						CalcHelper.getValueInThousandUSD(m9),
						CalcHelper.getValueInThousandUSD(m10),
						CalcHelper.getValueInThousandUSD(m11),
						CalcHelper.getValueInThousandUSD(m12),
						CalcHelper.getValueInThousandUSD(q1),
						CalcHelper.getValueInThousandUSD(q2),
						CalcHelper.getValueInThousandUSD(q3),
						CalcHelper.getValueInThousandUSD(q4),
						CalcHelper.getValueInThousandUSD(techANS),
						CalcHelper.getValueInThousandUSD(techDCS),
						CalcHelper.getValueInThousandUSD(techOth),
						CalcHelper.getValueInThousandUSD(techLAN),
						CalcHelper.getValueInThousandUSD(techRou),
						CalcHelper.getValueInThousandUSD(techSec),
						CalcHelper.getValueInThousandUSD(techSto),
						CalcHelper.getValueInThousandUSD(techUC),
						CalcHelper.getValueInThousandUSD(techUCS),
						CalcHelper.getValueInThousandUSD(techVid),
						CalcHelper.getValueInThousandUSD(techWLA),
						top,
						CalcHelper.getValueInThousandUSD(m1w1),
						CalcHelper.getValueInThousandUSD(m1w2),
						CalcHelper.getValueInThousandUSD(m1w3),
						CalcHelper.getValueInThousandUSD(m1w4),
						CalcHelper.getValueInThousandUSD(m2w1),
						CalcHelper.getValueInThousandUSD(m2w2),
						CalcHelper.getValueInThousandUSD(m2w3),
						CalcHelper.getValueInThousandUSD(m2w4),
						CalcHelper.getValueInThousandUSD(m3w1),
						CalcHelper.getValueInThousandUSD(m3w2),
						CalcHelper.getValueInThousandUSD(m3w3),
						CalcHelper.getValueInThousandUSD(m3w4),
						CalcHelper.getValueInThousandUSD(m3w5),
						CalcHelper.getValueInThousandUSD(m4w1),
						CalcHelper.getValueInThousandUSD(m4w2),
						CalcHelper.getValueInThousandUSD(m4w3),
						CalcHelper.getValueInThousandUSD(m4w4),
						CalcHelper.getValueInThousandUSD(m5w1),
						CalcHelper.getValueInThousandUSD(m5w2),
						CalcHelper.getValueInThousandUSD(m5w3),
						CalcHelper.getValueInThousandUSD(m5w4),
						CalcHelper.getValueInThousandUSD(m6w1),
						CalcHelper.getValueInThousandUSD(m6w2),
						CalcHelper.getValueInThousandUSD(m6w3),
						CalcHelper.getValueInThousandUSD(m6w4),
						CalcHelper.getValueInThousandUSD(m6w5),
						CalcHelper.getValueInThousandUSD(m7w1),
						CalcHelper.getValueInThousandUSD(m7w2),
						CalcHelper.getValueInThousandUSD(m7w3),
						CalcHelper.getValueInThousandUSD(m7w4),
						CalcHelper.getValueInThousandUSD(m8w1),
						CalcHelper.getValueInThousandUSD(m8w2),
						CalcHelper.getValueInThousandUSD(m8w3),
						CalcHelper.getValueInThousandUSD(m8w4),
						CalcHelper.getValueInThousandUSD(m9w1),
						CalcHelper.getValueInThousandUSD(m9w2),
						CalcHelper.getValueInThousandUSD(m9w3),
						CalcHelper.getValueInThousandUSD(m9w4),
						CalcHelper.getValueInThousandUSD(m9w5),
						CalcHelper.getValueInThousandUSD(m10w1),
						CalcHelper.getValueInThousandUSD(m10w2),
						CalcHelper.getValueInThousandUSD(m10w3),
						CalcHelper.getValueInThousandUSD(m10w4),
						CalcHelper.getValueInThousandUSD(m11w1),
						CalcHelper.getValueInThousandUSD(m11w2),
						CalcHelper.getValueInThousandUSD(m11w3),
						CalcHelper.getValueInThousandUSD(m11w4),
						CalcHelper.getValueInThousandUSD(m12w1),
						CalcHelper.getValueInThousandUSD(m12w2),
						CalcHelper.getValueInThousandUSD(m12w3),
						CalcHelper.getValueInThousandUSD(m12w4),
						CalcHelper.getValueInThousandUSD(m12w5)));
		return valueSet;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static List<DashboardGeneralValues> getTopPartnerCustomerMetricsYTD(JList jlNode, 
			String node, String pa, String cu, String finYear, String finQuarter, String finMonth, 
			boolean isPreviousYear) {
		int tempCount=0;
		double booking = 0D, list = 0D, entNWBook = 0D, entNWList = 0D,
		secBook = 0D, secList = 0D, collBook = 0D, collList = 0D, dcvBook = 0D, 
		dcvList = 0D, atAttach = 0D, nonATAttach = 0D, techs = 0D, 
		m1 = 0D, m2 = 0D, m3 = 0D, m4 = 0D, m5 = 0D, m6 = 0D, m7 = 0D, m8 = 0D, 
		m9 = 0D, m10 = 0D, m11 = 0D, m12 = 0D, q1 = 0D, q2 = 0D, q3 = 0D, q4 = 0D, 
		h1 = 0D, h2 = 0D, 
		m1w1=0D, m1w2=0D, m1w3=0D, m1w4=0D,
		m2w1=0D, m2w2=0D, m2w3=0D, m2w4=0D,
		m3w1=0D, m3w2=0D, m3w3=0D, m3w4=0D, m3w5=0D,
		m4w1=0D, m4w2=0D, m4w3=0D, m4w4=0D,
		m5w1=0D, m5w2=0D, m5w3=0D, m5w4=0D,
		m6w1=0D, m6w2=0D, m6w3=0D, m6w4=0D, m6w5=0D,
		m7w1=0D, m7w2=0D, m7w3=0D, m7w4=0D,
		m8w1=0D, m8w2=0D, m8w3=0D, m8w4=0D,
		m9w1=0D, m9w2=0D, m9w3=0D, m9w4=0D, m9w5=0D,
		m10w1=0D, m10w2=0D, m10w3=0D, m10w4=0D,
		m11w1=0D, m11w2=0D, m11w3=0D, m11w4=0D,
		m12w1=0D, m12w2=0D, m12w3=0D, m12w4=0D, m12w5=0D,
		techANS = 0D, techDCS = 0D, techOth = 0D, techLAN = 0D, techRou = 0D, 
		techSec = 0D, techSto = 0D, techUC = 0D, techUCS = 0D, techVid = 0D, techWLA = 0D;
		List<DashboardGeneralValues> valueSet = null;
		DashboardGeneralValues valueEntry;
		List<DataBaseUniquePartner> resultData;
		DataBaseUniquePartnerQueries query;
		DataBaseUniquePartner currentEntry;
		query = new DataBaseUniquePartnerQueries(isPreviousYear);
		String[] nodeArray;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		DefaultListModel lModel = new DefaultListModel();
		DefaultListModel lModel2 = new DefaultListModel();
		List<TopNames> top = new ArrayList<TopNames>();
		List<String> tempTop = new ArrayList<String>();
		List<String> tempTop2 = new ArrayList<String>();
		nodeArray = new String[nodeArrayLength];
		if (node.equals("Multiple")) {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", pa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Multiple; Name Single");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String nod = currentEntry.getSalesLevel6();
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	String partner = currentEntry.getPartner();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod) && 
				    				pa.equals(partner) &&
				    				cu.equals(cus)) {
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(cus)) {
			    					lModel.addElement(cus);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} else {
				try {
					resultData = query.getAll(node, pa, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Single");
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	String partner = currentEntry.getPartner();
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
			    		if (pa.equals(partner) &&
			    				cu.equals(cus)) {
			    			booking += value;
			    			list += value2;
			    			entNWBook += value3;
			    			entNWList += value4;
			    			secBook += value11;
			    			secList += value12;
			    			collBook += value5;
			    			collList += value6;
			    			dcvBook += value7;
			    			dcvList += value8;
			    			atAttach += value9;
			    			nonATAttach += value10;
					    	m1 += value13;
					    	m2 += value14;
					    	m3 += value15;
					    	m4 += value16;
					    	m5 += value17;
					    	m6 += value18;
					    	m7 += value19;
					    	m8 += value20;
					    	m9 += value21;
					    	m10 += value22;
					    	m11 += value23;
					    	m12 += value24;
					    	q1 += value25;
					    	q2 += value26;
					    	q3 += value27;
					    	q4 += value28;
					    	m1w1 +=value42;
					    	m1w2 +=value43;
					    	m1w3 +=value44;
					    	m1w4 +=value45;
					    	m2w1 +=value46;
					    	m2w2 +=value47;
					    	m2w3 +=value48;
					    	m2w4 +=value49;
					    	m3w1 +=value50;
					    	m3w2 +=value51;
					    	m3w3 +=value52;
					    	m3w4 +=value53;
					    	m3w4 +=value54;
					    	m4w1 +=value55;
					    	m4w2 +=value56;
					    	m4w3 +=value57;
					    	m4w4 +=value58;
					    	m5w1 +=value59;
					    	m5w2 +=value60;
					    	m5w3 +=value61;
					    	m5w4 +=value62;
					    	m6w1 +=value63;
					    	m6w2 +=value64;
					    	m6w3 +=value65;
					    	m6w4 +=value66;
					    	m6w5 +=value67;
					    	m7w1 +=value68;
					    	m7w2 +=value69;
					    	m7w3 +=value70;
					    	m7w4 +=value71;
					    	m8w1 +=value72;
					    	m8w2 +=value73;
					    	m8w3 +=value74;
					    	m8w4 +=value75;
					    	m9w1 +=value76;
					    	m9w2 +=value77;
					    	m9w3 +=value78;
					    	m9w4 +=value79;
					    	m9w5 +=value80;
					    	m10w1 +=value81;
					    	m10w2 +=value82;
					    	m10w3 +=value83;
					    	m10w4 +=value84;
					    	m11w1 +=value85;
					    	m11w2 +=value86;
					    	m11w3 +=value87;
					    	m11w4 +=value88;
					    	m12w1 +=value89;
					    	m12w2 +=value90;
					    	m12w3 +=value91;
					    	m12w4 +=value92;
					    	m12w5 +=value93;
					    	techANS += value29;
					    	techDCS += value30;
					    	techOth += value31;
					    	techLAN += value32;
					    	techRou += value33;
					    	techSec += value34;
					    	techSto += value35;
					    	techUC += value36;
					    	techUCS += value37;
					    	techVid += value38;
					    	techWLA += value39;
	    				if (!lModel.contains(cus)) {
	    					lModel.addElement(cus);
	    				}
    					lModel2.addElement(technology);
				    }
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		valueSet = new ArrayList<DashboardGeneralValues>();
		valueSet.add(new 
				DashboardGeneralValues(
						CalcHelper.getValueInMillionUSD(booking),
						CalcHelper.getValueInMillionUSD(list),
						(double)lModel.getSize(),
						CalcHelper.getValueInMillionUSD(entNWBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(secBook),
						CalcHelper.getValueInMillionUSD(secList),
						CalcHelper.getValueInMillionUSD(collBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(dcvBook),
						CalcHelper.getValueInMillionUSD(dcvList),
						(double)lModel2.getSize(),
						CalcHelper.getValueInMillionUSD(atAttach),
						CalcHelper.getValueInMillionUSD(nonATAttach),
						CalcHelper.getValueInThousandUSD(m1),
						CalcHelper.getValueInThousandUSD(m2),
						CalcHelper.getValueInThousandUSD(m3),
						CalcHelper.getValueInThousandUSD(m4),
						CalcHelper.getValueInThousandUSD(m5),
						CalcHelper.getValueInThousandUSD(m6),
						CalcHelper.getValueInThousandUSD(m7),
						CalcHelper.getValueInThousandUSD(m8),
						CalcHelper.getValueInThousandUSD(m9),
						CalcHelper.getValueInThousandUSD(m10),
						CalcHelper.getValueInThousandUSD(m11),
						CalcHelper.getValueInThousandUSD(m12),
						CalcHelper.getValueInThousandUSD(q1),
						CalcHelper.getValueInThousandUSD(q2),
						CalcHelper.getValueInThousandUSD(q3),
						CalcHelper.getValueInThousandUSD(q4),
						CalcHelper.getValueInThousandUSD(techANS),
						CalcHelper.getValueInThousandUSD(techDCS),
						CalcHelper.getValueInThousandUSD(techOth),
						CalcHelper.getValueInThousandUSD(techLAN),
						CalcHelper.getValueInThousandUSD(techRou),
						CalcHelper.getValueInThousandUSD(techSec),
						CalcHelper.getValueInThousandUSD(techSto),
						CalcHelper.getValueInThousandUSD(techUC),
						CalcHelper.getValueInThousandUSD(techUCS),
						CalcHelper.getValueInThousandUSD(techVid),
						CalcHelper.getValueInThousandUSD(techWLA),
						top,
						CalcHelper.getValueInThousandUSD(m1w1),
						CalcHelper.getValueInThousandUSD(m1w2),
						CalcHelper.getValueInThousandUSD(m1w3),
						CalcHelper.getValueInThousandUSD(m1w4),
						CalcHelper.getValueInThousandUSD(m2w1),
						CalcHelper.getValueInThousandUSD(m2w2),
						CalcHelper.getValueInThousandUSD(m2w3),
						CalcHelper.getValueInThousandUSD(m2w4),
						CalcHelper.getValueInThousandUSD(m3w1),
						CalcHelper.getValueInThousandUSD(m3w2),
						CalcHelper.getValueInThousandUSD(m3w3),
						CalcHelper.getValueInThousandUSD(m3w4),
						CalcHelper.getValueInThousandUSD(m3w5),
						CalcHelper.getValueInThousandUSD(m4w1),
						CalcHelper.getValueInThousandUSD(m4w2),
						CalcHelper.getValueInThousandUSD(m4w3),
						CalcHelper.getValueInThousandUSD(m4w4),
						CalcHelper.getValueInThousandUSD(m5w1),
						CalcHelper.getValueInThousandUSD(m5w2),
						CalcHelper.getValueInThousandUSD(m5w3),
						CalcHelper.getValueInThousandUSD(m5w4),
						CalcHelper.getValueInThousandUSD(m6w1),
						CalcHelper.getValueInThousandUSD(m6w2),
						CalcHelper.getValueInThousandUSD(m6w3),
						CalcHelper.getValueInThousandUSD(m6w4),
						CalcHelper.getValueInThousandUSD(m6w5),
						CalcHelper.getValueInThousandUSD(m7w1),
						CalcHelper.getValueInThousandUSD(m7w2),
						CalcHelper.getValueInThousandUSD(m7w3),
						CalcHelper.getValueInThousandUSD(m7w4),
						CalcHelper.getValueInThousandUSD(m8w1),
						CalcHelper.getValueInThousandUSD(m8w2),
						CalcHelper.getValueInThousandUSD(m8w3),
						CalcHelper.getValueInThousandUSD(m8w4),
						CalcHelper.getValueInThousandUSD(m9w1),
						CalcHelper.getValueInThousandUSD(m9w2),
						CalcHelper.getValueInThousandUSD(m9w3),
						CalcHelper.getValueInThousandUSD(m9w4),
						CalcHelper.getValueInThousandUSD(m9w5),
						CalcHelper.getValueInThousandUSD(m10w1),
						CalcHelper.getValueInThousandUSD(m10w2),
						CalcHelper.getValueInThousandUSD(m10w3),
						CalcHelper.getValueInThousandUSD(m10w4),
						CalcHelper.getValueInThousandUSD(m11w1),
						CalcHelper.getValueInThousandUSD(m11w2),
						CalcHelper.getValueInThousandUSD(m11w3),
						CalcHelper.getValueInThousandUSD(m11w4),
						CalcHelper.getValueInThousandUSD(m12w1),
						CalcHelper.getValueInThousandUSD(m12w2),
						CalcHelper.getValueInThousandUSD(m12w3),
						CalcHelper.getValueInThousandUSD(m12w4),
						CalcHelper.getValueInThousandUSD(m12w5)));
		return valueSet;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static List<DashboardGeneralValues> getCustomerMetricsYTD(JList jlCus, JList jlNode, 
			String node, String cus, String finYear, String finQuarter, String finMonth, 
			boolean isPreviousYear) {
		int tempCount=0;
		double booking = 0D, list = 0D, entNWBook = 0D, entNWList = 0D,
		secBook = 0D, secList = 0D, collBook = 0D, collList = 0D, dcvBook = 0D, 
		dcvList = 0D, atAttach = 0D, nonATAttach = 0D, techs = 0D, 
		m1 = 0D, m2 = 0D, m3 = 0D, m4 = 0D, m5 = 0D, m6 = 0D, m7 = 0D, m8 = 0D, 
		m9 = 0D, m10 = 0D, m11 = 0D, m12 = 0D, q1 = 0D, q2 = 0D, q3 = 0D, q4 = 0D, 
		h1 = 0D, h2 = 0D, 
		m1w1=0D, m1w2=0D, m1w3=0D, m1w4=0D,
		m2w1=0D, m2w2=0D, m2w3=0D, m2w4=0D,
		m3w1=0D, m3w2=0D, m3w3=0D, m3w4=0D, m3w5=0D,
		m4w1=0D, m4w2=0D, m4w3=0D, m4w4=0D,
		m5w1=0D, m5w2=0D, m5w3=0D, m5w4=0D,
		m6w1=0D, m6w2=0D, m6w3=0D, m6w4=0D, m6w5=0D,
		m7w1=0D, m7w2=0D, m7w3=0D, m7w4=0D,
		m8w1=0D, m8w2=0D, m8w3=0D, m8w4=0D,
		m9w1=0D, m9w2=0D, m9w3=0D, m9w4=0D, m9w5=0D,
		m10w1=0D, m10w2=0D, m10w3=0D, m10w4=0D,
		m11w1=0D, m11w2=0D, m11w3=0D, m11w4=0D,
		m12w1=0D, m12w2=0D, m12w3=0D, m12w4=0D, m12w5=0D,
		techANS = 0D, techDCS = 0D, techOth = 0D, techLAN = 0D, techRou = 0D, 
		techSec = 0D, techSto = 0D, techUC = 0D, techUCS = 0D, techVid = 0D, techWLA = 0D;
		List<DashboardGeneralValues> valueSet = null;
		DashboardGeneralValues valueEntry;
		List<DataBaseUniqueCustomer> resultData;
		DataBaseUniqueCustomerQueries query;
		DataBaseUniqueCustomer currentEntry;
		query = new DataBaseUniqueCustomerQueries(isPreviousYear);
		String[] cusArray, nodeArray;
		int cusArrayLength = ComponentHelper.getListInArray(jlCus).length;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		DefaultListModel lModel = new DefaultListModel();
		DefaultListModel lModel2 = new DefaultListModel();
		List<TopNames> top = new ArrayList<TopNames>();
		List<String> tempTop = new ArrayList<String>();
		cusArray = new String[cusArrayLength];
		nodeArray = new String[nodeArrayLength];
		if (node.equals("Multiple")) {
			if (cus.equals("Multiple")) {
				cusArray = ComponentHelper.getListInArray(jlCus);
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", "ALL", finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Multiple; Name Muliple");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String nod = currentEntry.getSalesLevel6();
				    	String customer = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	for (int i=0; i<nodeArrayLength; i++) {
					    	for (int l=0; l<cusArrayLength; l++) {
					    		if (cusArray[l].equals(customer) && 
					    				nodeArray[i].equals(nod)) {
					    			if(!tempTop.contains(customer)) {
					    				tempTop.add(customer);
					    			}
					    			booking += value;
					    			list += value2;
					    			entNWBook += value3;
					    			entNWList += value4;
					    			secBook += value11;
					    			secList += value12;
					    			collBook += value5;
					    			collList += value6;
					    			dcvBook += value7;
					    			dcvList += value8;
					    			atAttach += value9;
					    			nonATAttach += value10;
							    	m1 += value13;
							    	m2 += value14;
							    	m3 += value15;
							    	m4 += value16;
							    	m5 += value17;
							    	m6 += value18;
							    	m7 += value19;
							    	m8 += value20;
							    	m9 += value21;
							    	m10 += value22;
							    	m11 += value23;
							    	m12 += value24;
							    	q1 += value25;
							    	q2 += value26;
							    	q3 += value27;
							    	q4 += value28;
							    	m1w1 +=value42;
							    	m1w2 +=value43;
							    	m1w3 +=value44;
							    	m1w4 +=value45;
							    	m2w1 +=value46;
							    	m2w2 +=value47;
							    	m2w3 +=value48;
							    	m2w4 +=value49;
							    	m3w1 +=value50;
							    	m3w2 +=value51;
							    	m3w3 +=value52;
							    	m3w4 +=value53;
							    	m3w4 +=value54;
							    	m4w1 +=value55;
							    	m4w2 +=value56;
							    	m4w3 +=value57;
							    	m4w4 +=value58;
							    	m5w1 +=value59;
							    	m5w2 +=value60;
							    	m5w3 +=value61;
							    	m5w4 +=value62;
							    	m6w1 +=value63;
							    	m6w2 +=value64;
							    	m6w3 +=value65;
							    	m6w4 +=value66;
							    	m6w5 +=value67;
							    	m7w1 +=value68;
							    	m7w2 +=value69;
							    	m7w3 +=value70;
							    	m7w4 +=value71;
							    	m8w1 +=value72;
							    	m8w2 +=value73;
							    	m8w3 +=value74;
							    	m8w4 +=value75;
							    	m9w1 +=value76;
							    	m9w2 +=value77;
							    	m9w3 +=value78;
							    	m9w4 +=value79;
							    	m9w5 +=value80;
							    	m10w1 +=value81;
							    	m10w2 +=value82;
							    	m10w3 +=value83;
							    	m10w4 +=value84;
							    	m11w1 +=value85;
							    	m11w2 +=value86;
							    	m11w3 +=value87;
							    	m11w4 +=value88;
							    	m12w1 +=value89;
							    	m12w2 +=value90;
							    	m12w3 +=value91;
							    	m12w4 +=value92;
							    	m12w5 +=value93;
							    	techANS += value29;
							    	techDCS += value30;
							    	techOth += value31;
							    	techLAN += value32;
							    	techRou += value33;
							    	techSec += value34;
							    	techSto += value35;
							    	techUC += value36;
							    	techUCS += value37;
							    	techVid += value38;
							    	techWLA += value39;
				    				if (!lModel.contains(customer)) {
				    					lModel.addElement(customer);
				    				}
			    					lModel2.addElement(technology);
					    		}
					    	}
				    	}
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String customer = currentEntry.getCustomer();
					    	String nodes = currentEntry.getSalesLevel6();
					    	if (tempTop.get(p).equals(customer)) {
						    	for (int i=0; i<nodeArrayLength; i++) {
						    		rev += (nodeArray[i].equals(nodes)) ? value : 0D;
						    	}
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", cus, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Multiple; Name Single");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String nod = currentEntry.getSalesLevel6();
				    	String technology = currentEntry.getTechnology();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod)) {
				    			if(!tempTop.contains(cus)) {
				    				tempTop.add(cus);
				    			}
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(cus)) {
			    					lModel.addElement(cus);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String customer = currentEntry.getCustomer();
					    	String nodes = currentEntry.getSalesLevel6();
					    	if (tempTop.get(p).equals(customer)) {
						    	for (int i=0; i<nodeArrayLength; i++) {
						    		rev += (nodeArray[i].equals(nodes)) ? value : 0D;
						    	}
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (cus.equals("Multiple")) {
				cusArray = ComponentHelper.getListInArray(jlCus);
				try {
					resultData = query.getAll(node, "ALL", finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Multiple");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String customer = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	for (int i=0; i<cusArrayLength; i++) {
				    		if (cusArray[i].equals(customer)) {
				    			if(!tempTop.contains(customer)) {
				    				tempTop.add(customer);
				    			}
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(customer)) {
			    					lModel.addElement(customer);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String customer = currentEntry.getCustomer();
					    	if (tempTop.get(p).equals(customer)) {
					    		rev += value;
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					resultData = query.getAll(node, cus, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Single");
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String customer = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
		    			if(!tempTop.contains(cus)) {
		    				tempTop.add(cus);
		    			}
		    			booking += value;
		    			list += value2;
		    			entNWBook += value3;
		    			entNWList += value4;
		    			secBook += value11;
		    			secList += value12;
		    			collBook += value5;
		    			collList += value6;
		    			dcvBook += value7;
		    			dcvList += value8;
		    			atAttach += value9;
		    			nonATAttach += value10;
				    	m1 += value13;
				    	m2 += value14;
				    	m3 += value15;
				    	m4 += value16;
				    	m5 += value17;
				    	m6 += value18;
				    	m7 += value19;
				    	m8 += value20;
				    	m9 += value21;
				    	m10 += value22;
				    	m11 += value23;
				    	m12 += value24;
				    	q1 += value25;
				    	q2 += value26;
				    	q3 += value27;
				    	q4 += value28;
				    	m1w1 +=value42;
				    	m1w2 +=value43;
				    	m1w3 +=value44;
				    	m1w4 +=value45;
				    	m2w1 +=value46;
				    	m2w2 +=value47;
				    	m2w3 +=value48;
				    	m2w4 +=value49;
				    	m3w1 +=value50;
				    	m3w2 +=value51;
				    	m3w3 +=value52;
				    	m3w4 +=value53;
				    	m3w4 +=value54;
				    	m4w1 +=value55;
				    	m4w2 +=value56;
				    	m4w3 +=value57;
				    	m4w4 +=value58;
				    	m5w1 +=value59;
				    	m5w2 +=value60;
				    	m5w3 +=value61;
				    	m5w4 +=value62;
				    	m6w1 +=value63;
				    	m6w2 +=value64;
				    	m6w3 +=value65;
				    	m6w4 +=value66;
				    	m6w5 +=value67;
				    	m7w1 +=value68;
				    	m7w2 +=value69;
				    	m7w3 +=value70;
				    	m7w4 +=value71;
				    	m8w1 +=value72;
				    	m8w2 +=value73;
				    	m8w3 +=value74;
				    	m8w4 +=value75;
				    	m9w1 +=value76;
				    	m9w2 +=value77;
				    	m9w3 +=value78;
				    	m9w4 +=value79;
				    	m9w5 +=value80;
				    	m10w1 +=value81;
				    	m10w2 +=value82;
				    	m10w3 +=value83;
				    	m10w4 +=value84;
				    	m11w1 +=value85;
				    	m11w2 +=value86;
				    	m11w3 +=value87;
				    	m11w4 +=value88;
				    	m12w1 +=value89;
				    	m12w2 +=value90;
				    	m12w3 +=value91;
				    	m12w4 +=value92;
				    	m12w5 +=value93;
				    	techANS += value29;
				    	techDCS += value30;
				    	techOth += value31;
				    	techLAN += value32;
				    	techRou += value33;
				    	techSec += value34;
				    	techSto += value35;
				    	techUC += value36;
				    	techUCS += value37;
				    	techVid += value38;
				    	techWLA += value39;
	    				if (!lModel.contains(cus)) {
	    					lModel.addElement(cus);
	    				}
    					lModel2.addElement(technology);
					}
			    	for (int p=0; p<tempTop.size(); p++) {
			    		double rev = 0D;
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry = resultData.get(j);
					    	double value = currentEntry.getBookingAll();
					    	String customer = currentEntry.getCustomer();
					    	if (tempTop.get(p).equals(customer)) {
					    		rev += value;
					    	}
					    }
			    		top.add(new TopNames(tempTop.get(p), rev));
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		valueSet = new ArrayList<DashboardGeneralValues>();
		valueSet.add(new 
				DashboardGeneralValues(
						CalcHelper.getValueInMillionUSD(booking),
						CalcHelper.getValueInMillionUSD(list),
						(double)lModel.getSize(),
						CalcHelper.getValueInMillionUSD(entNWBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(secBook),
						CalcHelper.getValueInMillionUSD(secList),
						CalcHelper.getValueInMillionUSD(collBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(dcvBook),
						CalcHelper.getValueInMillionUSD(dcvList),
						(double)lModel2.getSize(),
						CalcHelper.getValueInMillionUSD(atAttach),
						CalcHelper.getValueInMillionUSD(nonATAttach),
						CalcHelper.getValueInThousandUSD(m1),
						CalcHelper.getValueInThousandUSD(m2),
						CalcHelper.getValueInThousandUSD(m3),
						CalcHelper.getValueInThousandUSD(m4),
						CalcHelper.getValueInThousandUSD(m5),
						CalcHelper.getValueInThousandUSD(m6),
						CalcHelper.getValueInThousandUSD(m7),
						CalcHelper.getValueInThousandUSD(m8),
						CalcHelper.getValueInThousandUSD(m9),
						CalcHelper.getValueInThousandUSD(m10),
						CalcHelper.getValueInThousandUSD(m11),
						CalcHelper.getValueInThousandUSD(m12),
						CalcHelper.getValueInThousandUSD(q1),
						CalcHelper.getValueInThousandUSD(q2),
						CalcHelper.getValueInThousandUSD(q3),
						CalcHelper.getValueInThousandUSD(q4),
						CalcHelper.getValueInThousandUSD(techANS),
						CalcHelper.getValueInThousandUSD(techDCS),
						CalcHelper.getValueInThousandUSD(techOth),
						CalcHelper.getValueInThousandUSD(techLAN),
						CalcHelper.getValueInThousandUSD(techRou),
						CalcHelper.getValueInThousandUSD(techSec),
						CalcHelper.getValueInThousandUSD(techSto),
						CalcHelper.getValueInThousandUSD(techUC),
						CalcHelper.getValueInThousandUSD(techUCS),
						CalcHelper.getValueInThousandUSD(techVid),
						CalcHelper.getValueInThousandUSD(techWLA),
						top,
						CalcHelper.getValueInThousandUSD(m1w1),
						CalcHelper.getValueInThousandUSD(m1w2),
						CalcHelper.getValueInThousandUSD(m1w3),
						CalcHelper.getValueInThousandUSD(m1w4),
						CalcHelper.getValueInThousandUSD(m2w1),
						CalcHelper.getValueInThousandUSD(m2w2),
						CalcHelper.getValueInThousandUSD(m2w3),
						CalcHelper.getValueInThousandUSD(m2w4),
						CalcHelper.getValueInThousandUSD(m3w1),
						CalcHelper.getValueInThousandUSD(m3w2),
						CalcHelper.getValueInThousandUSD(m3w3),
						CalcHelper.getValueInThousandUSD(m3w4),
						CalcHelper.getValueInThousandUSD(m3w5),
						CalcHelper.getValueInThousandUSD(m4w1),
						CalcHelper.getValueInThousandUSD(m4w2),
						CalcHelper.getValueInThousandUSD(m4w3),
						CalcHelper.getValueInThousandUSD(m4w4),
						CalcHelper.getValueInThousandUSD(m5w1),
						CalcHelper.getValueInThousandUSD(m5w2),
						CalcHelper.getValueInThousandUSD(m5w3),
						CalcHelper.getValueInThousandUSD(m5w4),
						CalcHelper.getValueInThousandUSD(m6w1),
						CalcHelper.getValueInThousandUSD(m6w2),
						CalcHelper.getValueInThousandUSD(m6w3),
						CalcHelper.getValueInThousandUSD(m6w4),
						CalcHelper.getValueInThousandUSD(m6w5),
						CalcHelper.getValueInThousandUSD(m7w1),
						CalcHelper.getValueInThousandUSD(m7w2),
						CalcHelper.getValueInThousandUSD(m7w3),
						CalcHelper.getValueInThousandUSD(m7w4),
						CalcHelper.getValueInThousandUSD(m8w1),
						CalcHelper.getValueInThousandUSD(m8w2),
						CalcHelper.getValueInThousandUSD(m8w3),
						CalcHelper.getValueInThousandUSD(m8w4),
						CalcHelper.getValueInThousandUSD(m9w1),
						CalcHelper.getValueInThousandUSD(m9w2),
						CalcHelper.getValueInThousandUSD(m9w3),
						CalcHelper.getValueInThousandUSD(m9w4),
						CalcHelper.getValueInThousandUSD(m9w5),
						CalcHelper.getValueInThousandUSD(m10w1),
						CalcHelper.getValueInThousandUSD(m10w2),
						CalcHelper.getValueInThousandUSD(m10w3),
						CalcHelper.getValueInThousandUSD(m10w4),
						CalcHelper.getValueInThousandUSD(m11w1),
						CalcHelper.getValueInThousandUSD(m11w2),
						CalcHelper.getValueInThousandUSD(m11w3),
						CalcHelper.getValueInThousandUSD(m11w4),
						CalcHelper.getValueInThousandUSD(m12w1),
						CalcHelper.getValueInThousandUSD(m12w2),
						CalcHelper.getValueInThousandUSD(m12w3),
						CalcHelper.getValueInThousandUSD(m12w4),
						CalcHelper.getValueInThousandUSD(m12w5)));
		return valueSet;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static List<DashboardGeneralValues> getTopCustomerMetricsYTD(JList jlNode, 
			String node, String cu, String finYear, String finQuarter, String finMonth, 
			boolean isPreviousYear) {
		int tempCount=0;
		double booking = 0D, list = 0D, entNWBook = 0D, entNWList = 0D,
		secBook = 0D, secList = 0D, collBook = 0D, collList = 0D, dcvBook = 0D, 
		dcvList = 0D, atAttach = 0D, nonATAttach = 0D, techs = 0D, 
		m1 = 0D, m2 = 0D, m3 = 0D, m4 = 0D, m5 = 0D, m6 = 0D, m7 = 0D, m8 = 0D, 
		m9 = 0D, m10 = 0D, m11 = 0D, m12 = 0D, q1 = 0D, q2 = 0D, q3 = 0D, q4 = 0D, 
		h1 = 0D, h2 = 0D, 
		m1w1=0D, m1w2=0D, m1w3=0D, m1w4=0D,
		m2w1=0D, m2w2=0D, m2w3=0D, m2w4=0D,
		m3w1=0D, m3w2=0D, m3w3=0D, m3w4=0D, m3w5=0D,
		m4w1=0D, m4w2=0D, m4w3=0D, m4w4=0D,
		m5w1=0D, m5w2=0D, m5w3=0D, m5w4=0D,
		m6w1=0D, m6w2=0D, m6w3=0D, m6w4=0D, m6w5=0D,
		m7w1=0D, m7w2=0D, m7w3=0D, m7w4=0D,
		m8w1=0D, m8w2=0D, m8w3=0D, m8w4=0D,
		m9w1=0D, m9w2=0D, m9w3=0D, m9w4=0D, m9w5=0D,
		m10w1=0D, m10w2=0D, m10w3=0D, m10w4=0D,
		m11w1=0D, m11w2=0D, m11w3=0D, m11w4=0D,
		m12w1=0D, m12w2=0D, m12w3=0D, m12w4=0D, m12w5=0D,
		techANS = 0D, techDCS = 0D, techOth = 0D, techLAN = 0D, techRou = 0D, 
		techSec = 0D, techSto = 0D, techUC = 0D, techUCS = 0D, techVid = 0D, techWLA = 0D;
		List<DashboardGeneralValues> valueSet = null;
		DashboardGeneralValues valueEntry;
		List<DataBaseUniqueCustomer> resultData;
		DataBaseUniqueCustomerQueries query;
		DataBaseUniqueCustomer currentEntry;
		query = new DataBaseUniqueCustomerQueries(isPreviousYear);
		String[] nodeArray;
		int nodeArrayLength = ComponentHelper.getListInArray(jlNode).length;
		DefaultListModel lModel = new DefaultListModel();
		DefaultListModel lModel2 = new DefaultListModel();
		List<TopNames> top = new ArrayList<TopNames>();
		List<String> tempTop = new ArrayList<String>();
		nodeArray = new String[nodeArrayLength];
		if (node.equals("Multiple")) {
				nodeArray = ComponentHelper.getListInArray(jlNode);
				try {
					resultData = query.getAll("ALL", cu, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Multiple; Name Single");
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultData.get(j);
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
				    	String nod = currentEntry.getSalesLevel6();
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	for (int i=0; i<nodeArrayLength; i++) {
				    		if (nodeArray[i].equals(nod) && 
				    				cu.equals(cus)) {
				    			booking += value;
				    			list += value2;
				    			entNWBook += value3;
				    			entNWList += value4;
				    			secBook += value11;
				    			secList += value12;
				    			collBook += value5;
				    			collList += value6;
				    			dcvBook += value7;
				    			dcvList += value8;
				    			atAttach += value9;
				    			nonATAttach += value10;
						    	m1 += value13;
						    	m2 += value14;
						    	m3 += value15;
						    	m4 += value16;
						    	m5 += value17;
						    	m6 += value18;
						    	m7 += value19;
						    	m8 += value20;
						    	m9 += value21;
						    	m10 += value22;
						    	m11 += value23;
						    	m12 += value24;
						    	q1 += value25;
						    	q2 += value26;
						    	q3 += value27;
						    	q4 += value28;
						    	m1w1 +=value42;
						    	m1w2 +=value43;
						    	m1w3 +=value44;
						    	m1w4 +=value45;
						    	m2w1 +=value46;
						    	m2w2 +=value47;
						    	m2w3 +=value48;
						    	m2w4 +=value49;
						    	m3w1 +=value50;
						    	m3w2 +=value51;
						    	m3w3 +=value52;
						    	m3w4 +=value53;
						    	m3w4 +=value54;
						    	m4w1 +=value55;
						    	m4w2 +=value56;
						    	m4w3 +=value57;
						    	m4w4 +=value58;
						    	m5w1 +=value59;
						    	m5w2 +=value60;
						    	m5w3 +=value61;
						    	m5w4 +=value62;
						    	m6w1 +=value63;
						    	m6w2 +=value64;
						    	m6w3 +=value65;
						    	m6w4 +=value66;
						    	m6w5 +=value67;
						    	m7w1 +=value68;
						    	m7w2 +=value69;
						    	m7w3 +=value70;
						    	m7w4 +=value71;
						    	m8w1 +=value72;
						    	m8w2 +=value73;
						    	m8w3 +=value74;
						    	m8w4 +=value75;
						    	m9w1 +=value76;
						    	m9w2 +=value77;
						    	m9w3 +=value78;
						    	m9w4 +=value79;
						    	m9w5 +=value80;
						    	m10w1 +=value81;
						    	m10w2 +=value82;
						    	m10w3 +=value83;
						    	m10w4 +=value84;
						    	m11w1 +=value85;
						    	m11w2 +=value86;
						    	m11w3 +=value87;
						    	m11w4 +=value88;
						    	m12w1 +=value89;
						    	m12w2 +=value90;
						    	m12w3 +=value91;
						    	m12w4 +=value92;
						    	m12w5 +=value93;
						    	techANS += value29;
						    	techDCS += value30;
						    	techOth += value31;
						    	techLAN += value32;
						    	techRou += value33;
						    	techSec += value34;
						    	techSto += value35;
						    	techUC += value36;
						    	techUCS += value37;
						    	techVid += value38;
						    	techWLA += value39;
			    				if (!lModel.contains(cus)) {
			    					lModel.addElement(cus);
			    				}
		    					lModel2.addElement(technology);
				    		}
				    	}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} else {
				try {
					resultData = query.getAll(node, cu, finYear, finQuarter, finMonth);
				    tempCount = resultData.size();
//			    	JOptionPane.showMessageDialog(null, "Node Single; Name Single");
				    for (int i = 0; i < tempCount; i++) {
				    	currentEntry = resultData.get(i);
				    	String cus = currentEntry.getCustomer();
				    	String technology = currentEntry.getTechnology();
				    	double value = currentEntry.getBookingAll();
				    	double value2 = currentEntry.getListAll();
				    	double value3 = currentEntry.getArchBookingENTNetWorking();
				    	double value4 = currentEntry.getArchListENTNetWorking();
				    	double value5 = currentEntry.getArchBookingCollab();
				    	double value6 = currentEntry.getArchListCollab();
				    	double value7 = currentEntry.getArchBookingDCV();
				    	double value8 = currentEntry.getArchListDCV();
				    	double value9 = currentEntry.getATAttach();
				    	double value10 = currentEntry.getNonATAttach();
				    	double value11 = currentEntry.getArchBookingSecurity();
				    	double value12 = currentEntry.getArchListSecurity();
				    	double value13 = currentEntry.getBookingM1();
				    	double value14 = currentEntry.getBookingM2();
				    	double value15 = currentEntry.getBookingM3();
				    	double value16 = currentEntry.getBookingM4();
				    	double value17 = currentEntry.getBookingM5();
				    	double value18 = currentEntry.getBookingM6();
				    	double value19 = currentEntry.getBookingM7();
				    	double value20 = currentEntry.getBookingM8();
				    	double value21 = currentEntry.getBookingM9();
				    	double value22 = currentEntry.getBookingM10();
				    	double value23 = currentEntry.getBookingM11();
				    	double value24 = currentEntry.getBookingM12();
				    	double value25 = currentEntry.getBookingQ1();
				    	double value26 = currentEntry.getBookingQ2();
				    	double value27 = currentEntry.getBookingQ3();
				    	double value28 = currentEntry.getBookingQ4();
				    	double value29 = currentEntry.getANS();
				    	double value30 = currentEntry.getDCSwitching();
				    	double value31 = currentEntry.getOthersTechnology();
				    	double value32 = currentEntry.getLANSwitching();
				    	double value33 = currentEntry.getRouting();
				    	double value34 = currentEntry.getSecurity();
				    	double value35 = currentEntry.getStorage();
				    	double value36 = currentEntry.getUC();
				    	double value37 = currentEntry.getUCS();
				    	double value38 = currentEntry.getVideo();
				    	double value39 = currentEntry.getWirelessLAN();
				    	double value42 = currentEntry.getBookingM1W1();
				    	double value43 = currentEntry.getBookingM1W2();
				    	double value44 = currentEntry.getBookingM1W3();
				    	double value45 = currentEntry.getBookingM1W4();
				    	double value46 = currentEntry.getBookingM2W1();
				    	double value47 = currentEntry.getBookingM2W2();
				    	double value48 = currentEntry.getBookingM2W3();
				    	double value49 = currentEntry.getBookingM2W4();
				    	double value50 = currentEntry.getBookingM3W1();
				    	double value51 = currentEntry.getBookingM3W2();
				    	double value52 = currentEntry.getBookingM3W3();
				    	double value53 = currentEntry.getBookingM3W4();
				    	double value54 = currentEntry.getBookingM3W5();
				    	double value55 = currentEntry.getBookingM4W1();
				    	double value56 = currentEntry.getBookingM4W2();
				    	double value57 = currentEntry.getBookingM4W3();
				    	double value58 = currentEntry.getBookingM4W4();
				    	double value59 = currentEntry.getBookingM5W1();
				    	double value60 = currentEntry.getBookingM5W2();
				    	double value61 = currentEntry.getBookingM5W3();
				    	double value62 = currentEntry.getBookingM5W4();
				    	double value63 = currentEntry.getBookingM6W1();
				    	double value64 = currentEntry.getBookingM6W2();
				    	double value65 = currentEntry.getBookingM6W3();
				    	double value66 = currentEntry.getBookingM6W4();
				    	double value67 = currentEntry.getBookingM6W5();
				    	double value68 = currentEntry.getBookingM7W1();
				    	double value69 = currentEntry.getBookingM7W2();
				    	double value70 = currentEntry.getBookingM7W3();
				    	double value71 = currentEntry.getBookingM7W4();
				    	double value72 = currentEntry.getBookingM8W1();
				    	double value73 = currentEntry.getBookingM8W2();
				    	double value74 = currentEntry.getBookingM8W3();
				    	double value75 = currentEntry.getBookingM8W4();
				    	double value76 = currentEntry.getBookingM9W1();
				    	double value77 = currentEntry.getBookingM9W2();
				    	double value78 = currentEntry.getBookingM9W3();
				    	double value79 = currentEntry.getBookingM9W4();
				    	double value80 = currentEntry.getBookingM9W5();
				    	double value81 = currentEntry.getBookingM10W1();
				    	double value82 = currentEntry.getBookingM10W2();
				    	double value83 = currentEntry.getBookingM10W3();
				    	double value84 = currentEntry.getBookingM10W4();
				    	double value85 = currentEntry.getBookingM11W1();
				    	double value86 = currentEntry.getBookingM11W2();
				    	double value87 = currentEntry.getBookingM11W3();
				    	double value88 = currentEntry.getBookingM11W4();
				    	double value89 = currentEntry.getBookingM12W1();
				    	double value90 = currentEntry.getBookingM12W2();
				    	double value91 = currentEntry.getBookingM12W3();
				    	double value92 = currentEntry.getBookingM12W4();
				    	double value93 = currentEntry.getBookingM12W5();
			    		if (cu.equals(cus)) {
			    			booking += value;
			    			list += value2;
			    			entNWBook += value3;
			    			entNWList += value4;
			    			secBook += value11;
			    			secList += value12;
			    			collBook += value5;
			    			collList += value6;
			    			dcvBook += value7;
			    			dcvList += value8;
			    			atAttach += value9;
			    			nonATAttach += value10;
					    	m1 += value13;
					    	m2 += value14;
					    	m3 += value15;
					    	m4 += value16;
					    	m5 += value17;
					    	m6 += value18;
					    	m7 += value19;
					    	m8 += value20;
					    	m9 += value21;
					    	m10 += value22;
					    	m11 += value23;
					    	m12 += value24;
					    	q1 += value25;
					    	q2 += value26;
					    	q3 += value27;
					    	q4 += value28;
					    	m1w1 +=value42;
					    	m1w2 +=value43;
					    	m1w3 +=value44;
					    	m1w4 +=value45;
					    	m2w1 +=value46;
					    	m2w2 +=value47;
					    	m2w3 +=value48;
					    	m2w4 +=value49;
					    	m3w1 +=value50;
					    	m3w2 +=value51;
					    	m3w3 +=value52;
					    	m3w4 +=value53;
					    	m3w4 +=value54;
					    	m4w1 +=value55;
					    	m4w2 +=value56;
					    	m4w3 +=value57;
					    	m4w4 +=value58;
					    	m5w1 +=value59;
					    	m5w2 +=value60;
					    	m5w3 +=value61;
					    	m5w4 +=value62;
					    	m6w1 +=value63;
					    	m6w2 +=value64;
					    	m6w3 +=value65;
					    	m6w4 +=value66;
					    	m6w5 +=value67;
					    	m7w1 +=value68;
					    	m7w2 +=value69;
					    	m7w3 +=value70;
					    	m7w4 +=value71;
					    	m8w1 +=value72;
					    	m8w2 +=value73;
					    	m8w3 +=value74;
					    	m8w4 +=value75;
					    	m9w1 +=value76;
					    	m9w2 +=value77;
					    	m9w3 +=value78;
					    	m9w4 +=value79;
					    	m9w5 +=value80;
					    	m10w1 +=value81;
					    	m10w2 +=value82;
					    	m10w3 +=value83;
					    	m10w4 +=value84;
					    	m11w1 +=value85;
					    	m11w2 +=value86;
					    	m11w3 +=value87;
					    	m11w4 +=value88;
					    	m12w1 +=value89;
					    	m12w2 +=value90;
					    	m12w3 +=value91;
					    	m12w4 +=value92;
					    	m12w5 +=value93;
					    	techANS += value29;
					    	techDCS += value30;
					    	techOth += value31;
					    	techLAN += value32;
					    	techRou += value33;
					    	techSec += value34;
					    	techSto += value35;
					    	techUC += value36;
					    	techUCS += value37;
					    	techVid += value38;
					    	techWLA += value39;
	    				if (!lModel.contains(cus)) {
	    					lModel.addElement(cus);
	    				}
    					lModel2.addElement(technology);
				    }
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		valueSet = new ArrayList<DashboardGeneralValues>();
		valueSet.add(new 
				DashboardGeneralValues(
						CalcHelper.getValueInMillionUSD(booking),
						CalcHelper.getValueInMillionUSD(list),
						(double)lModel.getSize(),
						CalcHelper.getValueInMillionUSD(entNWBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(secBook),
						CalcHelper.getValueInMillionUSD(secList),
						CalcHelper.getValueInMillionUSD(collBook),
						CalcHelper.getValueInMillionUSD(entNWList),
						CalcHelper.getValueInMillionUSD(dcvBook),
						CalcHelper.getValueInMillionUSD(dcvList),
						(double)lModel2.getSize(),
						CalcHelper.getValueInMillionUSD(atAttach),
						CalcHelper.getValueInMillionUSD(nonATAttach),
						CalcHelper.getValueInThousandUSD(m1),
						CalcHelper.getValueInThousandUSD(m2),
						CalcHelper.getValueInThousandUSD(m3),
						CalcHelper.getValueInThousandUSD(m4),
						CalcHelper.getValueInThousandUSD(m5),
						CalcHelper.getValueInThousandUSD(m6),
						CalcHelper.getValueInThousandUSD(m7),
						CalcHelper.getValueInThousandUSD(m8),
						CalcHelper.getValueInThousandUSD(m9),
						CalcHelper.getValueInThousandUSD(m10),
						CalcHelper.getValueInThousandUSD(m11),
						CalcHelper.getValueInThousandUSD(m12),
						CalcHelper.getValueInThousandUSD(q1),
						CalcHelper.getValueInThousandUSD(q2),
						CalcHelper.getValueInThousandUSD(q3),
						CalcHelper.getValueInThousandUSD(q4),
						CalcHelper.getValueInThousandUSD(techANS),
						CalcHelper.getValueInThousandUSD(techDCS),
						CalcHelper.getValueInThousandUSD(techOth),
						CalcHelper.getValueInThousandUSD(techLAN),
						CalcHelper.getValueInThousandUSD(techRou),
						CalcHelper.getValueInThousandUSD(techSec),
						CalcHelper.getValueInThousandUSD(techSto),
						CalcHelper.getValueInThousandUSD(techUC),
						CalcHelper.getValueInThousandUSD(techUCS),
						CalcHelper.getValueInThousandUSD(techVid),
						CalcHelper.getValueInThousandUSD(techWLA),
						top,
						CalcHelper.getValueInThousandUSD(m1w1),
						CalcHelper.getValueInThousandUSD(m1w2),
						CalcHelper.getValueInThousandUSD(m1w3),
						CalcHelper.getValueInThousandUSD(m1w4),
						CalcHelper.getValueInThousandUSD(m2w1),
						CalcHelper.getValueInThousandUSD(m2w2),
						CalcHelper.getValueInThousandUSD(m2w3),
						CalcHelper.getValueInThousandUSD(m2w4),
						CalcHelper.getValueInThousandUSD(m3w1),
						CalcHelper.getValueInThousandUSD(m3w2),
						CalcHelper.getValueInThousandUSD(m3w3),
						CalcHelper.getValueInThousandUSD(m3w4),
						CalcHelper.getValueInThousandUSD(m3w5),
						CalcHelper.getValueInThousandUSD(m4w1),
						CalcHelper.getValueInThousandUSD(m4w2),
						CalcHelper.getValueInThousandUSD(m4w3),
						CalcHelper.getValueInThousandUSD(m4w4),
						CalcHelper.getValueInThousandUSD(m5w1),
						CalcHelper.getValueInThousandUSD(m5w2),
						CalcHelper.getValueInThousandUSD(m5w3),
						CalcHelper.getValueInThousandUSD(m5w4),
						CalcHelper.getValueInThousandUSD(m6w1),
						CalcHelper.getValueInThousandUSD(m6w2),
						CalcHelper.getValueInThousandUSD(m6w3),
						CalcHelper.getValueInThousandUSD(m6w4),
						CalcHelper.getValueInThousandUSD(m6w5),
						CalcHelper.getValueInThousandUSD(m7w1),
						CalcHelper.getValueInThousandUSD(m7w2),
						CalcHelper.getValueInThousandUSD(m7w3),
						CalcHelper.getValueInThousandUSD(m7w4),
						CalcHelper.getValueInThousandUSD(m8w1),
						CalcHelper.getValueInThousandUSD(m8w2),
						CalcHelper.getValueInThousandUSD(m8w3),
						CalcHelper.getValueInThousandUSD(m8w4),
						CalcHelper.getValueInThousandUSD(m9w1),
						CalcHelper.getValueInThousandUSD(m9w2),
						CalcHelper.getValueInThousandUSD(m9w3),
						CalcHelper.getValueInThousandUSD(m9w4),
						CalcHelper.getValueInThousandUSD(m9w5),
						CalcHelper.getValueInThousandUSD(m10w1),
						CalcHelper.getValueInThousandUSD(m10w2),
						CalcHelper.getValueInThousandUSD(m10w3),
						CalcHelper.getValueInThousandUSD(m10w4),
						CalcHelper.getValueInThousandUSD(m11w1),
						CalcHelper.getValueInThousandUSD(m11w2),
						CalcHelper.getValueInThousandUSD(m11w3),
						CalcHelper.getValueInThousandUSD(m11w4),
						CalcHelper.getValueInThousandUSD(m12w1),
						CalcHelper.getValueInThousandUSD(m12w2),
						CalcHelper.getValueInThousandUSD(m12w3),
						CalcHelper.getValueInThousandUSD(m12w4),
						CalcHelper.getValueInThousandUSD(m12w5)));
		return valueSet;
	}

}
