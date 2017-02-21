package prism14;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultValueDataset;

public class DashboardGeneralSupport {

	private PicturedPanel currentYearPanel = null;
    private DashboardGeneralValues valueEntry;
    private List<TopNames> valueTop = null;
	
    private JList jltNode, jltName;
    private String optionTrigger, finYear, finQuarter, finMonth;
	private List<DashboardGeneralValues> valueSet;
	private boolean isAbs, isAttempted;
    
	private ChartPanel  techSplitChartPanel, archSplitChartPanel, 
	quarterSplitChartPanel, month_weekSplitChartPanel, halfYearSplitChartPanel;
	private GradientPanel2 techSplitHolder, archSplitHolder, quarterSplitHolder, 
	halfYearSplitHolder, month_weekSplitHolder;
    private double[][] techSplitValues, archSplitValues, quarterSplitValues, halfYearSplitValues, 
    weekSplitValues, monthSplitValues;
    private BufferedImage panelImage;
    private DashboardGeneralFieldHelper field;
	
	public DashboardGeneralSupport(String optionTrigger, List<DashboardGeneralValues> valueSet, 
			String finYear, String finQuarter, String finMonth, boolean isAbs) {
		this.jltNode = jltNode;
		this.jltName = jltName;
		this.optionTrigger = optionTrigger;
		this.valueSet = valueSet;
		this.finYear = finYear;
		this.finQuarter = finQuarter;
		this.finMonth = finMonth;
		this.isAbs = isAbs;
		try {
	        panelImage =  ImageIO.read(this.getClass().getResource(GeneralConstants.SKY_VIEW_IMAGE));
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
		currentYearPanel = new PicturedPanel(panelImage);
	}	
	public DashboardGeneralFieldHelper getCompareYearPanel() {
	    	JLabel title = new JLabel("Comparing with FY" + finYear);
	    	title.setFont(new Font("Arial Black",Font.BOLD,15));
	    	title.setForeground(Color.WHITE);
	    	title.setBackground(null);
	    	title.setOpaque(false);
	    	title.setBounds(350, 10, 200, 20);
			valueEntry = valueSet.get(0);
			valueTop = (List<TopNames>) valueEntry.getTopData();
			Collections.sort(valueTop, new TopNames());
			setCenterPlot(valueEntry.getBookingAll(), valueEntry.getYldPerCus());
			setDiscountPlots(valueEntry.getDiscountAll()*100D, valueEntry.getDiscountCollab()*100D, 
					valueEntry.getDiscountENTNW()*100D, valueEntry.getDiscountSecurity()*100D, valueEntry.getDiscountDCV()*100D, 
					valueEntry.getCusPen(), CalcHelper.round(
					valueEntry.getTechPen(), 2, BigDecimal.ROUND_HALF_UP), 
					valueEntry.getATAttach(), 
					valueEntry.getNonATAttach());		
			setOtherSplitPlots(valueEntry.getBookingENTNW(), valueEntry.getBookingSecurity(), 
					valueEntry.getBookingCollab(), valueEntry.getBookingDCV(),	valueEntry.getM1(), 
					valueEntry.getM2(), valueEntry.getM3(), valueEntry.getM4(), valueEntry.getM5(), 
					valueEntry.getM6(), valueEntry.getM7(), valueEntry.getM8(), valueEntry.getM9(), 
					valueEntry.getM10(), valueEntry.getM11(), valueEntry.getM12(), valueEntry.getQ1(), 
					valueEntry.getQ2(), valueEntry.getQ3(),	valueEntry.getQ4(), valueEntry.getANS(), 
					valueEntry.getDCSwitching(), valueEntry.getOthers(), valueEntry.getLANSwitching(), 
					valueEntry.getRouting(), valueEntry.getSecurity(), valueEntry.getStorage(), 
					valueEntry.getUC(), valueEntry.getUCS(), valueEntry.getVideo(), 
					valueEntry.getWirelessLAN(), valueEntry.getH1(), valueEntry.getH2(),
					valueEntry.getM1W1(), valueEntry.getM1W2(), valueEntry.getM1W3(), valueEntry.getM1W4(), 
					valueEntry.getM2W1(), valueEntry.getM2W2(), valueEntry.getM2W3(), valueEntry.getM2W4(),
					valueEntry.getM3W1(), valueEntry.getM3W2(), valueEntry.getM3W3(), valueEntry.getM3W4(), valueEntry.getM3W5(),
					valueEntry.getM4W1(), valueEntry.getM4W2(), valueEntry.getM4W3(), valueEntry.getM4W4(), 
					valueEntry.getM5W1(), valueEntry.getM5W2(), valueEntry.getM5W3(), valueEntry.getM5W4(),
					valueEntry.getM6W1(), valueEntry.getM6W2(), valueEntry.getM6W3(), valueEntry.getM6W4(), valueEntry.getM6W5(),
					valueEntry.getM7W1(), valueEntry.getM7W2(), valueEntry.getM7W3(), valueEntry.getM7W4(), 
					valueEntry.getM8W1(), valueEntry.getM8W2(), valueEntry.getM8W3(), valueEntry.getM8W4(),
					valueEntry.getM9W1(), valueEntry.getM9W2(), valueEntry.getM9W3(), valueEntry.getM9W4(), valueEntry.getM9W5(),
					valueEntry.getM10W1(), valueEntry.getM10W2(), valueEntry.getM10W3(), valueEntry.getM10W4(), 
					valueEntry.getM11W1(), valueEntry.getM11W2(), valueEntry.getM11W3(), valueEntry.getM11W4(),
					valueEntry.getM12W1(), valueEntry.getM12W2(), valueEntry.getM12W3(), valueEntry.getM12W4(), valueEntry.getM12W5());
			currentYearPanel.add(title);
			isAttempted = true;
			field = new DashboardGeneralFieldHelper(currentYearPanel, isAttempted);
			return field;
		}

	
	private void setCenterPlot(double d1, double d2) {
	    DefaultValueDataset dataset1, dataset2;
	    ChartPanel centerChartPanel, disAllChartPanel, disCollChartPanel, 
	    disDCVChartPanel, disENTNWChartPanel, 
	    disSecChartPanel, penCusChartPanel, techPenChartPanel, atAttachChartPanel;
		dataset1 = new DefaultValueDataset(d1);
		dataset2 = new DefaultValueDataset(d2);
		centerChartPanel = ComponentHelper.getTotalBookingPlot(d1, d2);
		centerChartPanel.setBounds(370, 60, 200, 200);
		currentYearPanel.add(centerChartPanel);
	}

	private void setDiscountPlots(double disAll, double disColl, double disENTNW, double disSec,
			double disDCV, double cPen, double techPen, double atAttach, double nonATAttach) {
		JPanel techPenHolder, atAttachHolder;
		ChartPanel centerChartPanel, disAllChartPanel, disCollChartPanel, disDCVChartPanel, disENTNWChartPanel, 
	    disSecChartPanel, penCusChartPanel, techPenChartPanel, atAttachChartPanel;
		techPenHolder = new JPanel();
		atAttachHolder = new JPanel();
		
		// Overall Discount MeterPlot
	    disAllChartPanel = ComponentHelper.getDiscountPlot(disAll, "Overall Dis.");
	    disAllChartPanel.setBounds(260, 180, 130, 85);
//	    disAllChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    currentYearPanel.add(disAllChartPanel);

	    
		// ENT Net Working Discount MeterPlot
	    disENTNWChartPanel = ComponentHelper.getDiscountPlot(disENTNW, "ENT NW Dis.");
	    disENTNWChartPanel.setBounds(320, 250, 130, 85);
//	    disENTNWChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    currentYearPanel.add(disENTNWChartPanel);

		// Security Discount MeterPlot
	    disSecChartPanel = ComponentHelper.getDiscountPlot(disSec, "Security Dis.");
	    disSecChartPanel.setBounds(410, 270, 130, 85);
//	    disSecChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    currentYearPanel.add(disSecChartPanel);

	    // Collaboration Discount MeterPlot
	    disCollChartPanel = ComponentHelper.getDiscountPlot(disColl, "Collab Dis.");
	    disCollChartPanel.setBounds(500, 250, 130, 85);
//	    disCollChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    currentYearPanel.add(disCollChartPanel);
	    
		// DataCentre Virtualization Discount MeterPlot
	    disDCVChartPanel = ComponentHelper.getDiscountPlot(disDCV, "DCV Dis.");
	    disDCVChartPanel.setBounds(550, 180, 130, 85);
//	    disDCVChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    currentYearPanel.add(disDCVChartPanel);

    
	    // Customer Penetration MeterPlot
	    penCusChartPanel = ComponentHelper.getCustomerPenetrationPlot(cPen, "Customer Pen.");
	    penCusChartPanel.setBounds(550, 85, 130, 85);
//	    penCusChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    currentYearPanel.add(penCusChartPanel);
	    
	    // Technology Penetration MeterPlot
	    techPenChartPanel = ComponentHelper.getTechPenetrationPlot(techPen);
	    techPenChartPanel.setBounds(0, 0, 180, 110);
//	    techPenChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    techPenHolder.setBackground(new Color(170, 170, 220));
	    techPenHolder.setOpaque(false);
	    techPenHolder.setBounds(660, 200, 60, 110);
	    techPenHolder.setLayout(null);
	    techPenHolder.add(techPenChartPanel);
	    currentYearPanel.add(techPenHolder);

	    // AT Attach PieChart
	    atAttachChartPanel = ComponentHelper.getATRatePlot(atAttach, nonATAttach);
	    atAttachChartPanel.setBounds(0, 0, 180, 130);
//	    atAttachChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    atAttachHolder.setBackground(new Color(170, 170, 220));
	    atAttachHolder.setOpaque(false);
	    atAttachHolder.setBounds(210, 65, 180, 150);
	    atAttachHolder.setLayout(null);
	    atAttachHolder.add(atAttachChartPanel);
	    currentYearPanel.add(atAttachHolder);
}

	private void setOtherSplitPlots(double eBook, double sBook, double cBook, double dBook,	double m1, 
			double m2, double m3, double m4, double m5, double m6, double m7, double m8, double m9, 
			double m10, double m11, double m12, double q1, double q2, double q3, 
			double q4, double techANS, double techDCS, double techOth, double techLAN, double techRou, double techSec, 
			double techSto, double techUC, double techUCS, double techVid, double techWLA,
			double h1, double h2, double m1w1, double m1w2, double m1w3, double m1w4,
			double m2w1, double m2w2, double m2w3, double m2w4, double m3w1, 
			double m3w2, double m3w3, double m3w4, double m3w5,
			double m4w1, double m4w2, double m4w3, double m4w4,
			double m5w1, double m5w2, double m5w3, double m5w4, 
			double m6w1, double m6w2, double m6w3, double m6w4, double m6w5,
			double m7w1, double m7w2, double m7w3, double m7w4,
			double m8w1, double m8w2, double m8w3, double m8w4, double m9w1, 
			double m9w2, double m9w3, double m9w4, double m9w5, double m10w1, 
			double m10w2, double m10w3, double m10w4, double m11w1, double m11w2, 
			double m11w3, double m11w4, double m12w1, double m12w2, double m12w3, 
			double m12w4, double m12w5) {
		techSplitHolder = new GradientPanel2();
		archSplitHolder = new GradientPanel2();
		quarterSplitHolder = new GradientPanel2();
		halfYearSplitHolder = new GradientPanel2();
		month_weekSplitHolder = new GradientPanel2();
		String[] techLabelArray = {"ANS","DC Switching","Others","LAN Switching",
				"Routing","Security","Storage","UC","UCS","Video","Wireless LAN"};
		String[] archLabelArray = {"ENT. NW","Security","Collab","DCV"};
		String[] halfYearLabelArray = {"H1","H2"};
		CategoryDataset techDataset, archDataset, quarterDataset = null, halfYearDataset = null, 
		month_weekDataset = null;
		String[] quarterLabelArray = null;
		String[] monthLabelArray = null;

		techSplitValues = new double[1][11];
		archSplitValues = new double[1][4];
		quarterSplitValues = new double[1][4];
		halfYearSplitValues = new double[1][2];
		if ((finQuarter.equals("Q1") || finQuarter.equals("Q2") || finQuarter.equals("Q3") || 
				finQuarter.equals("Q4")) && finMonth.equals("ALL")) {
			monthSplitValues = new double[1][3];
			weekSplitValues = new double[1][13];
			
			switch (finQuarter) {
			case "Q1" :
			    weekSplitValues[0][0] = m1w1;
			    weekSplitValues[0][1] = m1w2;
			    weekSplitValues[0][2] = m1w3;
			    weekSplitValues[0][3] = m1w4;
			    weekSplitValues[0][4] = m2w1;
			    weekSplitValues[0][5] = m2w2;
			    weekSplitValues[0][6] = m2w3;
			    weekSplitValues[0][7] = m2w4;
			    weekSplitValues[0][8] = m3w1;
			    weekSplitValues[0][9] = m3w2;
			    weekSplitValues[0][10] = m3w3;
			    weekSplitValues[0][11] = m3w4;
			    weekSplitValues[0][12] = m3w5;
			    month_weekDataset = DatasetUtilities.createCategoryDataset("", "", weekSplitValues);
			    monthSplitValues[0][0] = m1;
			    monthSplitValues[0][1] = m2;
			    monthSplitValues[0][2] = m3;
			    quarterDataset = DatasetUtilities.createCategoryDataset("", "", monthSplitValues);
			    quarterLabelArray = new String[3];
				quarterLabelArray[0] = "August"; 
				quarterLabelArray[1] = "September"; 
				quarterLabelArray[2] = "October";
				monthLabelArray = new String[13];
				monthLabelArray[0] = "M1_W1";
				monthLabelArray[1] = "M1_W2";
				monthLabelArray[2] = "M1_W3";
				monthLabelArray[3] = "M1_W4";
				monthLabelArray[4] = "M2_W1";
				monthLabelArray[5] = "M2_W2";
				monthLabelArray[6] = "M2_W3";
				monthLabelArray[7] = "M2_W4";
				monthLabelArray[8] = "M3_W1";
				monthLabelArray[9] = "M3_W2";
				monthLabelArray[10] = "M3_W3";
				monthLabelArray[11] = "M3_W4";
				monthLabelArray[12] = "M3_W5";
			    break;
			case "Q2" :
			    weekSplitValues[0][0] = m4w1;
			    weekSplitValues[0][1] = m4w2;
			    weekSplitValues[0][2] = m4w3;
			    weekSplitValues[0][3] = m4w4;
			    weekSplitValues[0][4] = m5w1;
			    weekSplitValues[0][5] = m5w2;
			    weekSplitValues[0][6] = m5w3;
			    weekSplitValues[0][7] = m5w4;
			    weekSplitValues[0][8] = m6w1;
			    weekSplitValues[0][9] = m6w2;
			    weekSplitValues[0][10] = m6w3;
			    weekSplitValues[0][11] = m6w4;
			    weekSplitValues[0][12] = m6w5;
			    month_weekDataset = DatasetUtilities.createCategoryDataset("", "", weekSplitValues);
			    monthSplitValues[0][0] = m4;
			    monthSplitValues[0][1] = m5;
			    monthSplitValues[0][2] = m6;
			    quarterDataset = DatasetUtilities.createCategoryDataset("", "", monthSplitValues);
			    quarterLabelArray = new String[3];
				quarterLabelArray[0] = "November"; 
				quarterLabelArray[1] = "December"; 
				quarterLabelArray[2] = "January";
				monthLabelArray = new String[13];
				monthLabelArray[0] = "M4_W1";
				monthLabelArray[1] = "M4_W2";
				monthLabelArray[2] = "M4_W3";
				monthLabelArray[3] = "M4_W4";
				monthLabelArray[4] = "M5_W1";
				monthLabelArray[5] = "M5_W2";
				monthLabelArray[6] = "M5_W3";
				monthLabelArray[7] = "M5_W4";
				monthLabelArray[8] = "M6_W1";
				monthLabelArray[9] = "M6_W2";
				monthLabelArray[10] = "M6_W3";
				monthLabelArray[11] = "M6_W4";
				monthLabelArray[12] = "M6_W5";
			    break;
			case "Q3" :
			    weekSplitValues[0][0] = m7w1;
			    weekSplitValues[0][1] = m7w2;
			    weekSplitValues[0][2] = m7w3;
			    weekSplitValues[0][3] = m7w4;
			    weekSplitValues[0][4] = m8w1;
			    weekSplitValues[0][5] = m8w2;
			    weekSplitValues[0][6] = m8w3;
			    weekSplitValues[0][7] = m8w4;
			    weekSplitValues[0][8] = m9w1;
			    weekSplitValues[0][9] = m9w2;
			    weekSplitValues[0][10] = m9w3;
			    weekSplitValues[0][11] = m9w4;
			    weekSplitValues[0][12] = m9w5;
			    month_weekDataset = DatasetUtilities.createCategoryDataset("", "", weekSplitValues);
			    monthSplitValues[0][0] = m7;
			    monthSplitValues[0][1] = m8;
			    monthSplitValues[0][2] = m9;
			    quarterDataset = DatasetUtilities.createCategoryDataset("", "", monthSplitValues);
			    quarterLabelArray = new String[3];
				quarterLabelArray[0] = "February"; 
				quarterLabelArray[1] = "March"; 
				quarterLabelArray[2] = "April";
				monthLabelArray = new String[13];
				monthLabelArray[0] = "M7_W1";
				monthLabelArray[1] = "M7_W2";
				monthLabelArray[2] = "M7_W3";
				monthLabelArray[3] = "M7_W4";
				monthLabelArray[4] = "M8_W1";
				monthLabelArray[5] = "M8_W2";
				monthLabelArray[6] = "M8_W3";
				monthLabelArray[7] = "M8_W4";
				monthLabelArray[8] = "M9_W1";
				monthLabelArray[9] = "M9_W2";
				monthLabelArray[10] = "M9_W3";
				monthLabelArray[11] = "M9_W4";
				monthLabelArray[12] = "M9_W5";
				break;
			case "Q4" :
			    weekSplitValues[0][0] = m10w1;
			    weekSplitValues[0][1] = m10w2;
			    weekSplitValues[0][2] = m10w3;
			    weekSplitValues[0][3] = m10w4;
			    weekSplitValues[0][4] = m11w1;
			    weekSplitValues[0][5] = m11w2;
			    weekSplitValues[0][6] = m11w3;
			    weekSplitValues[0][7] = m11w4;
			    weekSplitValues[0][8] = m12w1;
			    weekSplitValues[0][9] = m12w2;
			    weekSplitValues[0][10] = m12w3;
			    weekSplitValues[0][11] = m12w4;
			    weekSplitValues[0][12] = m12w5;
			    month_weekDataset = DatasetUtilities.createCategoryDataset("", "", weekSplitValues);
			    monthSplitValues[0][0] = m10;
			    monthSplitValues[0][1] = m11;
			    monthSplitValues[0][2] = m12;
			    quarterDataset = DatasetUtilities.createCategoryDataset("", "", monthSplitValues);
			    quarterLabelArray = new String[3];
				quarterLabelArray[0] = "May"; 
				quarterLabelArray[1] = "June"; 
				quarterLabelArray[2] = "July";
				monthLabelArray = new String[13];
				monthLabelArray[0] = "M10_W1";
				monthLabelArray[1] = "M10_W2";
				monthLabelArray[2] = "M10_W3";
				monthLabelArray[3] = "M10_W4";
				monthLabelArray[4] = "M11_W1";
				monthLabelArray[5] = "M11_W2";
				monthLabelArray[6] = "M11_W3";
				monthLabelArray[7] = "M11_W4";
				monthLabelArray[8] = "M12_W1";
				monthLabelArray[9] = "M12_W2";
				monthLabelArray[10] = "M12_W3";
				monthLabelArray[11] = "M12_W4";
				monthLabelArray[12] = "M12_W5";
			    break;
			}
			
		} else {
			monthSplitValues = new double[1][12];
		    // Month or week Split Horizontal Bar Graph
		    monthSplitValues[0][0] = m1;
		    monthSplitValues[0][1] = m2;
		    monthSplitValues[0][2] = m3;
		    monthSplitValues[0][3] = m4;
		    monthSplitValues[0][4] = m5;
		    monthSplitValues[0][5] = m6;
		    monthSplitValues[0][6] = m7;
		    monthSplitValues[0][7] = m8;
		    monthSplitValues[0][8] = m9;
		    monthSplitValues[0][9] = m10;
		    monthSplitValues[0][10] = m11;
		    monthSplitValues[0][11] = m12;
		    month_weekDataset = DatasetUtilities.createCategoryDataset("", "", monthSplitValues);
		    // Quarter Split Horizontal Bar Graph
		    quarterSplitValues[0][0] = q1;
		    quarterSplitValues[0][1] = q2;
		    quarterSplitValues[0][2] = q3;
		    quarterSplitValues[0][3] = q4;
		    quarterDataset = DatasetUtilities.createCategoryDataset("", "", quarterSplitValues);
		    quarterLabelArray = new String[4];
			quarterLabelArray[0] = "Quarter-1"; 
			quarterLabelArray[1] = "Quarter-2"; 
			quarterLabelArray[2] = "Quarter-3";
			quarterLabelArray[3] = "Quarter-4";
			monthLabelArray = new String[12];
			monthLabelArray[0] = "August";
			monthLabelArray[1] = "September";
			monthLabelArray[2] = "October";
			monthLabelArray[3] = "November";
			monthLabelArray[4] = "December";
			monthLabelArray[5] = "January";
			monthLabelArray[6] = "February";
			monthLabelArray[7] = "March";
			monthLabelArray[8] = "April";
			monthLabelArray[9] = "May";
			monthLabelArray[10] = "June";
			monthLabelArray[11] = "July";
		    // Half Year Split Horizontal Bar Graph
		    halfYearSplitValues[0][0] = h1;
		    halfYearSplitValues[0][1] = h2;
		    halfYearDataset = DatasetUtilities.createCategoryDataset("", "", halfYearSplitValues);
       }
		
	    // Technology Split Horizontal Bar Graph
	    techSplitValues[0][0] = techANS;
	    techSplitValues[0][1] = techDCS;
	    techSplitValues[0][2] = techOth;
	    techSplitValues[0][3] = techLAN;
	    techSplitValues[0][4] = techRou;
	    techSplitValues[0][5] = techSec;
	    techSplitValues[0][6] = techSto;
	    techSplitValues[0][7] = techUC;
	    techSplitValues[0][8] = techUCS;
	    techSplitValues[0][9] = techVid;
	    techSplitValues[0][10] = techWLA;
	    techDataset = DatasetUtilities.createCategoryDataset("", "", techSplitValues);


	    // Architecture Split Horizontal Bar Graph
	    archSplitValues[0][0] = CalcHelper.getValueInThousandUSD(eBook*1000000);
	    archSplitValues[0][1] = CalcHelper.getValueInThousandUSD(sBook*1000000);
	    archSplitValues[0][2] = CalcHelper.getValueInThousandUSD(cBook*1000000);
	    archSplitValues[0][3] = CalcHelper.getValueInThousandUSD(dBook*1000000);
	    archDataset = DatasetUtilities.createCategoryDataset("", "", archSplitValues);

	    //Technology Split Graph

	    techSplitChartPanel = ComponentHelper.getTechnologySplitGraph(techDataset);
	    techSplitChartPanel.setBounds(65, 5, 80, 300);
//	    techSplitChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    techSplitHolder.setBackground(new Color(170, 170, 220));
	    techSplitHolder.setOpaque(false);
	    techSplitHolder.setBounds(5, 5, 210, 310);
	    techSplitHolder.setLayout(null);
	    techSplitHolder.add(techSplitChartPanel);
	    ComponentHelper.placeTechGraphRangeLabels(techSplitHolder, techLabelArray, 
	    		"Technology Split", techSplitValues, isAbs);
	    currentYearPanel.add(techSplitHolder);

	    //Architecture Split Graph
	    archSplitChartPanel = ComponentHelper.getArchitectureSplitGraph(archDataset);
	    archSplitChartPanel.setBounds(65, 20, 80, 100);
//	    archSplitChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    archSplitHolder.setBackground(new Color(170, 170, 220));
	    archSplitHolder.setOpaque(false);
	    archSplitHolder.setBounds(5, 320, 210, 125);
	    archSplitHolder.setLayout(null);
	    archSplitHolder.add(archSplitChartPanel);
	    ComponentHelper.placeArchGraphRangeLabels(archSplitHolder, archLabelArray, 
	    		"Architecture Split", archSplitValues, isAbs);
	    currentYearPanel.add(archSplitHolder);


		if ((finQuarter.equals("ALL") && finMonth.equals("ALL")) || 
				(finQuarter.equals("Q1") && finMonth.equals("Q2")) || 
				(finQuarter.equals("Q3") && finMonth.equals("Q4"))) {
			
		    //Month_Week Split Graph
		    month_weekSplitChartPanel = ComponentHelper.getMonthWeekSplitGraph(month_weekDataset);
		    month_weekSplitChartPanel.setBounds(65, 5, 80, 300);
//		    month_weekSplitChartPanel.addChartMouseListener(new Q1ChartMouseListener());
		    month_weekSplitHolder.setBackground(new Color(170, 170, 220));
		    month_weekSplitHolder.setOpaque(false);
		    month_weekSplitHolder.setBounds(740, 5, 210, 290);
		    month_weekSplitHolder.setLayout(null);
		    month_weekSplitHolder.add(month_weekSplitChartPanel);
		    ComponentHelper.placeMonthWeekGraphRangeLabels(month_weekSplitHolder, monthLabelArray, 
		    		"Month wise Split", monthSplitValues, isAbs);
		    currentYearPanel.add(month_weekSplitHolder);

		    //Quarter Split Graph
		    quarterSplitChartPanel = ComponentHelper.getQuarterSplitGraph(quarterDataset);
		    quarterSplitChartPanel.setBounds(65, 20, 80, 100);
//		    quarterSplitChartPanel.addChartMouseListener(new Q1ChartMouseListener());
		    quarterSplitHolder.setBackground(new Color(170, 170, 220));
		    quarterSplitHolder.setOpaque(false);
		    quarterSplitHolder.setBounds(740, 300, 210, 125);
		    quarterSplitHolder.setLayout(null);
		    quarterSplitHolder.add(quarterSplitChartPanel);
		    ComponentHelper.placeQuarterGraphRangeLabels(quarterSplitHolder, quarterLabelArray, 
		    		"Quartner wise Split", quarterSplitValues, isAbs);
		    currentYearPanel.add(quarterSplitHolder);

		    //Half Year Split Graph
		    halfYearSplitChartPanel = ComponentHelper.getHalfYearSplitGraph(halfYearDataset);
		    halfYearSplitChartPanel.setBounds(65, 20, 80, 50);
//	    	halfYearSplitChartPanel.addChartMouseListener(new Q1ChartMouseListener());
		    halfYearSplitHolder.setBackground(new Color(170, 170, 220));
		    halfYearSplitHolder.setOpaque(false);
		    halfYearSplitHolder.setBounds(740, 430, 210, 85);
		    halfYearSplitHolder.setLayout(null);
		    halfYearSplitHolder.add(halfYearSplitChartPanel);
		    ComponentHelper.placeHalfYearGraphRangeLabels(halfYearSplitHolder, halfYearLabelArray, 
		    		"Half Year wise Split", halfYearSplitValues, isAbs);
		    currentYearPanel.add(halfYearSplitHolder);
		} else {
		    //Month_Week Split Graph
		    month_weekSplitChartPanel = ComponentHelper.getMonthWeekSplitGraph(month_weekDataset);
		    month_weekSplitChartPanel.setBounds(65, 5, 80, 300);
//		    month_weekSplitChartPanel.addChartMouseListener(new Q1ChartMouseListener());
		    month_weekSplitHolder.setBackground(new Color(170, 170, 220));
		    month_weekSplitHolder.setOpaque(false);
		    month_weekSplitHolder.setBounds(740, 5, 210, 310);
		    month_weekSplitHolder.setLayout(null);
		    month_weekSplitHolder.add(month_weekSplitChartPanel);
		    ComponentHelper.placeMonthWeekGraphRangeLabels(month_weekSplitHolder, monthLabelArray, 
		    		"Week wise Split", weekSplitValues, isAbs);
		    currentYearPanel.add(month_weekSplitHolder);

		    //Quarter Split Graph
		    quarterSplitChartPanel = ComponentHelper.getQuarterSplitGraph(quarterDataset);
		    quarterSplitChartPanel.setBounds(65, 20, 80, 80);
//		    quarterSplitChartPanel.addChartMouseListener(new Q1ChartMouseListener());
		    quarterSplitHolder.setBackground(new Color(170, 170, 220));
		    quarterSplitHolder.setOpaque(false);
		    quarterSplitHolder.setBounds(740, 320, 210, 115);
		    quarterSplitHolder.setLayout(null);
		    quarterSplitHolder.add(quarterSplitChartPanel);
		    ComponentHelper.placeQuarterGraphRangeLabels(quarterSplitHolder, quarterLabelArray, 
		    		"Month wise Split", monthSplitValues, isAbs);
		    currentYearPanel.add(quarterSplitHolder);
		}
	
	}
	
	
}
