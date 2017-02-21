package prism14;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public final class BusinessRulesXxmScoreCard {

    public static int fontSize = 11;
    public static String fontFamily = "Arial";
    public static String fontSpecialFamily = "Verdana";

	public static DashboardScoreCard2CompoGroup getMetricPanel(String title, int pXPos, 
			int pYPos, int pWidth, int pHeight, ImageIcon redMark, 
			ImageIcon yellowMark, ImageIcon greenMark, String metric,
			String[] metricTexts, double[] values, double[] thresholds, 
			RatioBoundaries[] rBoundary, String[] formatMethod, GeneralConstants.CompareMethod[] calcMethod, RatioBoundaries rBound) {
		GeneralConstants.ScorecardWaits waits = null;
		double finalPoint = 0D;
		DashboardScoreCard2CompoGroup cGroup = null;
		Headers headerCompo; 
		GradientPanel3 mainPanel = new GradientPanel3();
		mainPanel.setBackground(new Color(255,255,255));
		mainPanel.setLayout(null);
		mainPanel.setBounds(pXPos, pYPos, pWidth, pHeight);
		JLabel label = new JLabel(title);
		label.setBounds(pXPos+15, pYPos-30, 150, 25);
		label.setFont(new Font(fontFamily,Font.BOLD,fontSize));
		label.setForeground(new Color(178,34,34).darker());
		headerCompo = getHeader(mainPanel, pWidth-20, metric);
		finalPoint = setBodyFilledWithPointsReturn(mainPanel, headerCompo, metricTexts, values, thresholds, redMark, yellowMark, 
				greenMark, rBoundary, formatMethod, calcMethod);
		
		JLabel jlMark = null;
		JLabel jlScore = null;
		
		if (finalPoint < 0.7D) {
			jlMark = new JLabel(redMark);
		} else if (finalPoint == 1.0D) {
			jlMark = new JLabel(greenMark);
		} else {
			jlMark = new JLabel(yellowMark);
		}
		
		jlMark.setBounds(label.getX()+label.getWidth()+5, label.getY()+5, 15, 25);
		jlMark.setForeground(new Color(178,34,34).darker());
		jlMark.setOpaque(false);
		jlMark.setAlignmentX(SwingConstants.RIGHT);
		mainPanel.add(jlMark);

		jlScore = new JLabel();
		jlScore.setBounds(jlMark.getX()+jlMark.getWidth()+5, jlMark.getY()+5, 80, 15);
		jlScore.setOpaque(false);
		jlScore.setAlignmentX(SwingConstants.LEFT);
		mainPanel.add(jlScore);

		
		JPanel legendPanel = ComponentHelper.getLegendColorRange(redMark, yellowMark, 
				greenMark, new Font(fontFamily,Font.BOLD,fontSize-1), rBound);
		legendPanel.setOpaque(false);
		legendPanel.setBounds(pXPos-5, pYPos+pHeight, pWidth, 25);

		
		
        cGroup = new DashboardScoreCard2CompoGroup(mainPanel, label, jlMark, jlScore, legendPanel, finalPoint);
		return cGroup;
	}
	
	
	
	private static Headers getHeader(JPanel mainPanel, int groupWidth, String metric) {
		Headers head;
		JSeparator topSep = new JSeparator();
		topSep.setBounds(10, 25, groupWidth, 2);
		topSep.setForeground(Color.LIGHT_GRAY);
        mainPanel.add(topSep);
		JLabel metricTitle = new JLabel(metric);
		metricTitle.setBounds(topSep.getX()+5, topSep.getY()+topSep.getHeight()+5, 50, 10);;
		metricTitle.setFont(new Font(fontFamily,Font.BOLD,fontSize-2));
		metricTitle.setForeground(Color.BLACK);
        mainPanel.add(metricTitle);
		JLabel valueTitle = new JLabel("Actual");
		valueTitle.setBounds(metricTitle.getX()+metricTitle.getWidth()+35, metricTitle.getY(), 
				metricTitle.getWidth(), metricTitle.getHeight());
		valueTitle.setFont(new Font(fontFamily,Font.BOLD,fontSize-2));
		valueTitle.setAlignmentX(SwingConstants.RIGHT);
		valueTitle.setForeground(Color.BLACK);
        mainPanel.add(valueTitle);
		JLabel ThresholdTitle = new JLabel("BaseLine");
		ThresholdTitle.setBounds(valueTitle.getX()+valueTitle.getWidth()+10, valueTitle.getY(), 
				valueTitle.getWidth(), valueTitle.getHeight());
		ThresholdTitle.setFont(new Font(fontFamily,Font.BOLD,fontSize-2));
		ThresholdTitle.setForeground(Color.BLACK);
		ThresholdTitle.setAlignmentX(SwingConstants.RIGHT);
        mainPanel.add(ThresholdTitle);
		JLabel pointTitle = new JLabel("Score");
		pointTitle.setBounds(ThresholdTitle.getX()+ThresholdTitle.getWidth()+10, ThresholdTitle.getY(), 
				ThresholdTitle.getWidth(), ThresholdTitle.getHeight());
		pointTitle.setFont(new Font(fontFamily,Font.BOLD,fontSize-2));
		pointTitle.setForeground(Color.BLACK);
		pointTitle.setAlignmentX(SwingConstants.RIGHT);
        mainPanel.add(pointTitle);

		JSeparator bottomSep = new JSeparator();
		bottomSep.setBounds(topSep.getX(), 
				pointTitle.getY()+pointTitle.getHeight()+5, topSep.getWidth(), 2);
		bottomSep.setForeground(Color.LIGHT_GRAY);
        mainPanel.add(bottomSep);
        head = new Headers(topSep, bottomSep, metricTitle, valueTitle, 
        		ThresholdTitle, pointTitle);

        return head;
	}
	
	private static double setBodyFilledWithPointsReturn(JPanel panel, Headers headerCompo,
			String[] metricTexts, double[] values, double[] thresholds,
			ImageIcon red, ImageIcon yellow, ImageIcon green, RatioBoundaries[] rBoundary, 
			String[] formatMethod, GeneralConstants.CompareMethod[] calcMethod) {
		double[] points = new double[metricTexts.length];
		double tempPoint = 0D;
		for (int i=0; i< metricTexts.length; i++ ) {
			JLabel metricText = new JLabel(metricTexts[i]);
			metricText.setBounds(headerCompo.getBottomSeparator().getX(), 
					headerCompo.getBottomSeparator().
					getY()+(headerCompo.getBottomSeparator().getHeight()*9*i)+5, 80, 10);
			metricText.setFont(new Font(fontFamily,Font.BOLD,fontSize-2));
			metricText.setOpaque(true);
			metricText.setForeground(Color.BLUE);
			panel.add(metricText);

			JLabel valueText;
			if (formatMethod[i].equals("%")) {
				valueText = new JLabel(new DecimalFormat("###.#%").format(values[i]));
			} else if (formatMethod[i].equals("#")) {
				valueText = new JLabel(new DecimalFormat("####").format(values[i]));
			} else if (formatMethod[i].equals("$")) {
				valueText = new JLabel(new DecimalFormat("$ #,###.####").format(values[i]));
			} else {
				valueText = new JLabel(new DecimalFormat("##.##").format(values[i]));
			}
			valueText.setBounds(metricText.getX()+metricText.getWidth()+10, 
					headerCompo.getBottomSeparator().
					getY()+(headerCompo.getBottomSeparator().getHeight()*9*i)+5, 50, 10);
			valueText.setFont(new Font(fontFamily,Font.BOLD,fontSize-2));
			valueText.setForeground(Color.BLUE);
			valueText.setOpaque(true);
			valueText.setAlignmentX(SwingConstants.RIGHT);
			panel.add(valueText);

			JLabel thresholdText;
			if (formatMethod[i].equals("%")) {
				thresholdText = new JLabel(new DecimalFormat("###.#%").format(thresholds[i]));
			} else if (formatMethod[i].equals("#")) {
				thresholdText = new JLabel(new DecimalFormat("####").format(thresholds[i]));
			} else if (formatMethod[i].equals("$")) {
				thresholdText = new JLabel(new DecimalFormat("$ #,###.####").format(thresholds[i]));
			} else {
				thresholdText = new JLabel(new DecimalFormat("##.##").format(thresholds[i]));
			}
			thresholdText.setBounds(valueText.getX()+valueText.getWidth()+10, 
					headerCompo.getBottomSeparator().
					getY()+(headerCompo.getBottomSeparator().getHeight()*9*i)+5, 50, 10);
			thresholdText.setFont(new Font(fontFamily,Font.BOLD,fontSize-2));
			thresholdText.setForeground(Color.BLUE);
			thresholdText.setOpaque(true);
			thresholdText.setAlignmentX(SwingConstants.RIGHT);
			panel.add(thresholdText);

			
			if (metricTexts[i].equals("De-Booking")) {
//				System.out.print("Before: ");
//				System.out.print(values[i]*1000000);
//				System.out.print(" / ");
//				System.out.println(thresholds[i]*1000000);
				thresholds[i] = thresholds[i]*0.05D;
//				System.out.print(values[i]*1000000);
//				System.out.print(" / ");
//				System.out.println(thresholds[i]*1000000);
			}
			
			
			if (calcMethod[i].equals(GeneralConstants.CompareMethod.ACHIEVEMENT)) {
				tempPoint = CalcHelper.getAchievementSpecial(values[i], thresholds[i], rBoundary[i].getHigherBoundary())/100;
			} else if (calcMethod[i].equals(GeneralConstants.CompareMethod.GROWTH)) {
				tempPoint = CalcHelper.getGrowth(values[i], thresholds[i])/100;
			} else if (calcMethod[i].equals(GeneralConstants.CompareMethod.RANGE)) {
				tempPoint = values[i] - thresholds[i];
			} else if (calcMethod[i].equals(GeneralConstants.CompareMethod.NEGATIVE_ACHIEVEMENT)) {
				tempPoint = CalcHelper.getAchievement(((-1d)*values[i]), thresholds[i])/100;
//				System.out.println(tempPoint);
			}
			
			
			JLabel jlMark = null;
			if (rBoundary[i].getCompareMethod().
					equals(GeneralConstants.ComparePoint.RED_WHEN_LESS)) {
				if (tempPoint < rBoundary[i].getLowerBoundary()) {
					points[i] = GeneralConstants.RED_POINT;
					jlMark = new JLabel(red);
				} else if (tempPoint >= rBoundary[i].getHigherBoundary()) {
					jlMark = new JLabel(green);
					points[i] = GeneralConstants.GREEN_POINT;
				} else {
					jlMark = new JLabel(yellow);
					points[i] = GeneralConstants.YELLOW_POINT;
				}
			} else if (rBoundary[i].getCompareMethod().
					equals(GeneralConstants.ComparePoint.GREEN_WHEN_LESS)) {
				if (tempPoint < rBoundary[i].getLowerBoundary()) {
					points[i] = GeneralConstants.GREEN_POINT;
					jlMark = new JLabel(green);
				} else if (tempPoint >= rBoundary[i].getHigherBoundary()) {
					jlMark = new JLabel(red);
					points[i] = GeneralConstants.RED_POINT;
				} else {
					jlMark = new JLabel(yellow);
					points[i] = GeneralConstants.YELLOW_POINT;
				}
			} else if (rBoundary[i].getCompareMethod().
					equals(GeneralConstants.ComparePoint.RED_WHEN_LESS_THAN_EQUAL)) {
				if (tempPoint <= rBoundary[i].getLowerBoundary()) {
					points[i] = GeneralConstants.RED_POINT;
					jlMark = new JLabel(red);
				} else if (tempPoint > rBoundary[i].getHigherBoundary()) {
					jlMark = new JLabel(green);
					points[i] = GeneralConstants.GREEN_POINT;
				} else {
					jlMark = new JLabel(yellow);
					points[i] = GeneralConstants.YELLOW_POINT;
				}
			} else if (rBoundary[i].getCompareMethod().
					equals(GeneralConstants.ComparePoint.GREEN_WHEN_LESS_THAN_EQUAL)) {
				if (tempPoint <= rBoundary[i].getLowerBoundary()) {
					points[i] = GeneralConstants.GREEN_POINT;
					jlMark = new JLabel(green);
				} else if (tempPoint > rBoundary[i].getHigherBoundary()) {
					jlMark = new JLabel(red);
					points[i] = GeneralConstants.RED_POINT;
				} else {
					jlMark = new JLabel(yellow);
					points[i] = GeneralConstants.YELLOW_POINT;
				}
			} else if (rBoundary[i].getCompareMethod().
					equals(GeneralConstants.ComparePoint.RED_WHEN_LESS_THAN_EQUAL_AND_GREATER_THAN_EQUAL_TO)) {
				if (tempPoint <= rBoundary[i].getLowerBoundary()) {
					points[i] = GeneralConstants.RED_POINT;
					jlMark = new JLabel(red);
				} else if (tempPoint >= rBoundary[i].getHigherBoundary()) {
					jlMark = new JLabel(green);
					points[i] = GeneralConstants.GREEN_POINT;
				} else {
					jlMark = new JLabel(yellow);
					points[i] = GeneralConstants.YELLOW_POINT;
				}
			} 
			
			JLabel pointText = new JLabel(new DecimalFormat("#.##").format(points[i]));
			pointText.setBounds(thresholdText.getX()+thresholdText.getWidth()+10, 
					headerCompo.getBottomSeparator().
					getY()+(headerCompo.getBottomSeparator().getHeight()*9*i)+5, 50, 10);
			pointText.setFont(new Font(fontFamily,Font.BOLD,fontSize-2));
			pointText.setForeground(Color.BLUE);
			pointText.setOpaque(true);
			pointText.setAlignmentX(SwingConstants.RIGHT);
			panel.add(pointText);
			
			
			jlMark.setBounds(pointText.getX()+pointText.getWidth()+5, 
					headerCompo.getBottomSeparator().
					getY()+(headerCompo.getBottomSeparator().getHeight()*9*i), 15, 15);
			jlMark.setOpaque(false);
			jlMark.setAlignmentX(SwingConstants.RIGHT);
			panel.add(jlMark);
		}
		
		double finalPoint = 0D;
		for (int j=0; j<points.length; j++) {
			finalPoint += points[j];
		}
		return finalPoint/points.length;
	}

	
	public static ScorecardXxmValues getXxmScorecardMetrics(JList jltNode, JList jltSalesAgent, 
			String agentType, String finQuarter, String eu, 
			String region, String node, String salesAgent) {
		ScorecardGoalActualValues goalActualValues;
		ScorecardGrowthValues growthValues;
		ScorecardDiscountValues discountValues;
		ScorecardATPenetrationValues atPenValues;
		ScorecardLeadsValues leadsValues;
		ScorecardPartnerPenetrationValues partnerPenValues;
		ScorecardLinearityValues linearityValues;
		ScorecardPipelineValues pipelineValues;
		ScorecardDebookingValues debookingValues;
		ScorecardSCPValues scpTableValues;
		
		int tempCount=0, tempCount2=0, tempCount3=0, tempCount4=0, tempCount5=0, tempCount6=0, 
				tempCount7=0, tempCount8=0, tempCount9=0, tempCount10=0;
		double totCustomers=0D, q1cusPen=0D, q1techCount=0D, 
		q1oppCount=0D, q1revAll=0D, q1bListAll=0D, q1revColl=0D, 
		q1bListColl=0D, q1revDCV=0D, q1bListDCV=0D, q1revBN=0D,
		q1bListBN=0D, q1revSec=0D, q1bListSec=0D, cwp=0D,
		q2cusPen=0D, q2techCount=0D, 
		q2oppCount=0D, q2revAll=0D, q2bListAll=0D, q2revColl=0D, 
		q2bListColl=0D, q2revDCV=0D, q2bListDCV=0D, q2revBN=0D,
		q2bListBN=0D, q2revSec=0D, q2bListSec=0D,
		h1cusPen=0D, h1techCount=0D, 
		h1oppCount=0D, h1revAll=0D, h1bListAll=0D, h1revColl=0D, 
		h1bListColl=0D, h1revDCV=0D, h1bListDCV=0D, h1revBN=0D,
		h1bListBN=0D, h1revSec=0D, h1bListSec=0D,
		q3cusPen=0D, q3techCount=0D, 
		q3oppCount=0D, q3revAll=0D, q3bListAll=0D, q3revColl=0D, 
		q3bListColl=0D, q3revDCV=0D, q3bListDCV=0D, q3revBN=0D,
		q3bListBN=0D, q3revSec=0D, q3bListSec=0D,
		q4cusPen=0D, q4techCount=0D, 
		q4oppCount=0D, q4revAll=0D, q4bListAll=0D, q4revColl=0D, 
		q4bListColl=0D, q4revDCV=0D, q4bListDCV=0D, q4revBN=0D,
		q4bListBN=0D, q4revSec=0D, q4bListSec=0D,
		h2cusPen=0D, h2techCount=0D, 
		h2oppCount=0D, h2revAll=0D, h2bListAll=0D, h2revColl=0D, 
		h2bListColl=0D, h2revDCV=0D, h2bListDCV=0D, h2revBN=0D,
		h2bListBN=0D, h2revSec=0D, h2bListSec=0D,
		ytdtotCustomers=0D, ytdcusPen=0D, ytdtechCount=0D, 
		ytdoppCount=0D, ytdrevAll=0D, ytdbListAll=0D, ytdrevColl=0D, 
		ytdbListColl=0D, ytdrevDCV=0D, ytdbListDCV=0D, ytdrevBN=0D,
		ytdbListBN=0D, ytdrevSec=0D, ytdbListSec=0D;
		
		double goal=0D, actual=0D, currentYearValue=0D, previousYearValue=0D, 
				bookActualENTNW=0D, listActualENTNW=0D, bookActualSecurity=0D, listActualSecurity=0D,
				bookActualCollab=0D, listActualCollab=0D, bookActualDCV=0D, listActualDCV=0D,
				bookThresholdENTNW=0D, listThresholdENTNW=0D, bookThresholdSecurity=0D, listThresholdSecurity=0D,
				bookThresholdCollab=0D, listThresholdCollab=0D, bookThresholdDCV=0D, listThresholdDCV=0D,
				salesATActual=0D, salesNonATActual=0D,salesATThreshold=0D, salesNonATThreshold=0D, 
				waitingActual=0D, acceptedActual=0D, convertedActual=0D, overallLeadsActual=0D,
				waitingThreshold=0D, acceptedThreshold=0D, convertedThreshold=0D, overallLeadsThreshold=0D,
				assignedPartnerActual=0D, assignedPartnerThreshold=0D, 
				linearityActualM1=0D, linearityActualM2=0D, linearityActualM3=0D, 
				linearityThresholdM1=0D, linearityThresholdM2=0D, linearityThresholdM3=0D,
				pipelineActualQ1=0D, pipelineActualQ2=0D, pipelineActualQ3=0D, pipelineActualQ4=0D, pipelineActualOverall=0D,
				pipelineThresholdQ1=0D, pipelineThresholdQ2=0D, pipelineThresholdQ3=0D, pipelineThresholdQ4=0D, pipelineThresholdOverall=0D,
				debookingActual=0D, debookingThreshold=0D, accountPenetration=0D,	rev=0D, techs=0D, bnet=0D, blist=0D, totalAccounts=0D;

				
				;

		ScorecardXxmValues valueSet = null;
		
		ScorecardXxmDataset resultData;

		List<ScorecardGoalActualTable> resultSubData1 = null;
		List<ScorecardGrowthTable> resultSubData2 = null;
		List<ScorecardDiscountTable> resultSubData3 = null;
		List<ScorecardATPenetrationTable> resultSubData4 = null;
		List<ScorecardSCPTable> resultSubData5 = null;
		List<ScorecardLeadsTable> resultSubData6 = null;
		List<ScorecardPartnerPenetrationTable> resultSubData7 = null;
		List<ScorecardLinearityTable> resultSubData8 = null;
		List<ScorecardPipelineTable> resultSubData9 = null;
		List<ScorecardDebookingTable> resultSubData10 = null;

		
		DataBaseXxmScorecardQueries query;

		ScorecardGoalActualTable currentEntry = null;
		ScorecardGrowthTable currentEntry2 = null;
		ScorecardDiscountTable currentEntry3 = null;
		ScorecardATPenetrationTable currentEntry4 = null;
		ScorecardSCPTable currentEntry5 = null;
		ScorecardLeadsTable currentEntry6 = null;
		ScorecardPartnerPenetrationTable currentEntry7 = null;
		ScorecardLinearityTable currentEntry8 = null;
		ScorecardPipelineTable currentEntry9 = null;
		ScorecardDebookingTable currentEntry10 = null;

		
		query = new DataBaseXxmScorecardQueries();
		String[] xxmArray, nodeArray;
		int xxmArrayLength = ComponentHelper.getListInArray(jltSalesAgent).length;
		int nodeArrayLength = ComponentHelper.getListInArray(jltNode).length;
		xxmArray = new String[xxmArrayLength];
		nodeArray = new String[nodeArrayLength];
		xxmArray = ComponentHelper.getListInArray(jltSalesAgent);
		nodeArray = ComponentHelper.getListInArray(jltNode);
		try {
			resultData = query.getAll(agentType, finQuarter, eu, region, node, salesAgent);
	
			
			 // this is the data for SCP Table					
				resultSubData5 = resultData.getSCPTableData();
			    tempCount = resultSubData5.size();
			    for (int j = 0; j < tempCount; j++) {
			    	currentEntry5 = resultSubData5.get(j);
			    	double val1 = currentEntry5.getAccountPenetration();
			    	double val2 = currentEntry5.getRevenue();
			    	double val3 = currentEntry5.getTechnologyCount();
			    	double val4 = currentEntry5.getBookingNet();
			    	double val5 = currentEntry5.getBookingList();
			    	double val6 = currentEntry5.getTotalAccounts();
			    	String sAgent = currentEntry5.getSalesAgent();
			    	String pNode = currentEntry5.getNode();
			    	String dType = currentEntry5.getDataType();
			    	for (int i=0; i<xxmArrayLength; i++) {
				    	for (int l=0; l<nodeArrayLength; l++) {
				    		if (xxmArray[i].equals(sAgent) && 
				    				nodeArray[l].equals(pNode)) {
				    			accountPenetration += val1;
				    			rev += val2;
				    			techs += val3;
				    			bnet += val4;
				    			blist += val5;
				    			totalAccounts += val6;
				    		}
				    	}
			    	}
				}
			
				 // this is the data for Goal Actual Table					
					resultSubData1 = resultData.getGoalActualData();
				    tempCount = resultSubData1.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultSubData1.get(j);
				    	double val = currentEntry.getValue();
				    	String sAgent = currentEntry.getSalesAgent();
				    	String pNode = currentEntry.getNode();
				    	String dType = currentEntry.getDataType();
				    	for (int i=0; i<xxmArrayLength; i++) {
					    	for (int l=0; l<nodeArrayLength; l++) {
					    		if (xxmArray[i].equals(sAgent) && 
					    				nodeArray[l].equals(pNode) &&
					    				dType.equals("Actual")) {
					    			actual += val;
					    		} else if ((xxmArray[i].equals(sAgent) && 
					    				nodeArray[l].equals(pNode) &&
					    				dType.equals("Threshold"))) {
					    			goal += val;
					    		}
					    	}
				    	}
					}
				
					 // this is the data for Growth Table					
						resultSubData2 = resultData.getGrowthTableData();
					    tempCount = resultSubData2.size();
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry2 = resultSubData2.get(j);
					    	double val = currentEntry2.getValue();
					    	String sAgent = currentEntry2.getSalesAgent();
					    	String pNode = currentEntry2.getNode();
					    	String dType = currentEntry2.getDataType();
					    	for (int i=0; i<xxmArrayLength; i++) {
						    	for (int l=0; l<nodeArrayLength; l++) {
						    		if (xxmArray[i].equals(sAgent) && 
						    				nodeArray[l].equals(pNode) &&
						    				dType.equals("Actual")) {
						    			currentYearValue += val;
						    		} else if ((xxmArray[i].equals(sAgent) && 
						    				nodeArray[l].equals(pNode) &&
						    				dType.equals("Threshold"))) {
						    			previousYearValue += val;
						    		}
						    	}
					    	}
						}


						 // this is the data for Discount Table					
							resultSubData3 = resultData.getDiscountData();
						    tempCount = resultSubData3.size();
						    for (int j = 0; j < tempCount; j++) {
						    	currentEntry3 = resultSubData3.get(j);
						    	double bookENTNW = currentEntry3.getBookENTNW();
						    	double bookSecurity = currentEntry3.getBookSecurity();
						    	double bookCollab = currentEntry3.getBookCollab();
						    	double bookDCV = currentEntry3.getBookDCV();
						    	double listENTNW = currentEntry3.getListENTNW();
						    	double listSecurity = currentEntry3.getListSecurity();
						    	double listCollab = currentEntry3.getListCollab();
						    	double listDCV = currentEntry3.getListDCV();
						    	String sAgent = currentEntry3.getSalesAgent();
						    	String pNode = currentEntry3.getNode();
						    	String dType = currentEntry3.getDataType();
						    	for (int i=0; i<xxmArrayLength; i++) {
							    	for (int l=0; l<nodeArrayLength; l++) {
							    		if (xxmArray[i].equals(sAgent) && 
							    				nodeArray[l].equals(pNode) &&
							    				dType.equals("Actual")) {
							    			bookActualENTNW += bookENTNW;
							    			bookActualSecurity += bookSecurity;
							    			bookActualCollab += bookCollab;
							    			bookActualDCV += bookDCV;
							    			listActualENTNW += listENTNW;
							    			listActualSecurity += listSecurity;
							    			listActualCollab += listCollab;
							    			listActualDCV += listDCV;
							    		} else if ((xxmArray[i].equals(sAgent) && 
							    				nodeArray[l].equals(pNode) &&
							    				dType.equals("Threshold"))) {
							    			bookThresholdENTNW += bookENTNW;
							    			bookThresholdSecurity += bookSecurity;
							    			bookThresholdCollab += bookCollab;
							    			bookThresholdDCV += bookDCV;
							    			listThresholdENTNW += listENTNW;
							    			listThresholdSecurity += listSecurity;
							    			listThresholdCollab += listCollab;
							    			listThresholdDCV += listDCV;
							    		}
							    	}
						    	}
							}

							 // this is the data for AT Penetration Table					
								resultSubData4 = resultData.getATPenData();
							    tempCount = resultSubData4.size();
							    for (int j = 0; j < tempCount; j++) {
							    	currentEntry4 = resultSubData4.get(j);
							    	double salesAT = currentEntry4.getSalesAT();
							    	double salesNonAT = currentEntry4.getSalesNonAT();
							    	String sAgent = currentEntry4.getSalesAgent();
							    	String pNode = currentEntry4.getNode();
							    	String dType = currentEntry4.getDataType();
							    	for (int i=0; i<xxmArrayLength; i++) {
								    	for (int l=0; l<nodeArrayLength; l++) {
								    		if (xxmArray[i].equals(sAgent) && 
								    				nodeArray[l].equals(pNode) &&
								    				dType.equals("Actual")) {
								    			salesATActual += salesAT;
								    			salesNonATActual += salesNonAT;
								    		} else if ((xxmArray[i].equals(sAgent) && 
								    				nodeArray[l].equals(pNode) &&
								    				dType.equals("Threshold"))) {
								    			salesATThreshold += salesAT;
								    			salesNonATThreshold += salesNonAT;
								    		}
								    	}
							    	}
								}
						    
								 // this is the data for Leads Table					
									resultSubData6 = resultData.getLeadsData();
								    tempCount = resultSubData6.size();
								    for (int j = 0; j < tempCount; j++) {
								    	currentEntry6 = resultSubData6.get(j);
								    	double waiting = currentEntry6.getLeadsWaiting();
								    	double accepted = currentEntry6.getLeadsAccepted();
								    	double converted = currentEntry6.getLeadsConverted();
								    	double overall = currentEntry6.getLeadsOverall();
								    	String sAgent = currentEntry6.getSalesAgent();
								    	String pNode = currentEntry6.getNode();
								    	String dType = currentEntry6.getDataType();
								    	for (int i=0; i<xxmArrayLength; i++) {
									    	for (int l=0; l<nodeArrayLength; l++) {
									    		if (xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Actual")) {
											    	waitingActual += waiting;
											    	acceptedActual += accepted;
											    	convertedActual += converted;
											    	overallLeadsActual += overall;
									    		} else if ((xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Threshold"))) {
											    	waitingThreshold += waiting;
											    	acceptedThreshold += accepted;
											    	convertedThreshold += converted;
											    	overallLeadsThreshold += overall;
									    		}
									    	}
								    	}
									}


									 // this is the data for Partner Penetration Table					
									resultSubData7 = resultData.getPartnerPenetrationData();
								    tempCount = resultSubData7.size();
								    for (int j = 0; j < tempCount; j++) {
								    	currentEntry7 = resultSubData7.get(j);
								    	double parPen = currentEntry7.getValue();
								    	String sAgent = currentEntry7.getSalesAgent();
								    	String pNode = currentEntry7.getNode();
								    	String dType = currentEntry7.getDataType();
								    	for (int i=0; i<xxmArrayLength; i++) {
									    	for (int l=0; l<nodeArrayLength; l++) {
									    		if (xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Actual")) {
									    			assignedPartnerActual += parPen;
									    		} else if ((xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Threshold"))) {
									    			assignedPartnerThreshold += parPen;
									    		}
									    	}
								    	}
									}

									 // this is the data for Linearity Table					
									resultSubData8 = resultData.getLinearityData();
								    tempCount = resultSubData8.size();
								    for (int j = 0; j < tempCount; j++) {
								    	currentEntry8 = resultSubData8.get(j);
								    	double m1 = currentEntry8.getLinearityM1();
								    	double m2 = currentEntry8.getLinearityM2();
								    	double m3 = currentEntry8.getLinearityM3();
								    	String sAgent = currentEntry8.getSalesAgent();
								    	String pNode = currentEntry8.getNode();
								    	String dType = currentEntry8.getDataType();
								    	for (int i=0; i<xxmArrayLength; i++) {
									    	for (int l=0; l<nodeArrayLength; l++) {
									    		if (xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Actual")) {
									    			linearityActualM1 += m1;
									    			linearityActualM2 += m2;
									    			linearityActualM3 += m3;
									    		} else if ((xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Threshold"))) {
									    			linearityThresholdM1 += m1;
									    			linearityThresholdM2 += m2;
									    			linearityThresholdM3 += m3;
									    		}
									    	}
								    	}
									}

									 // this is the data for Pipeline Table					
									resultSubData9 = resultData.getPipelineData();
								    tempCount = resultSubData9.size();
								    for (int j = 0; j < tempCount; j++) {
								    	currentEntry9 = resultSubData9.get(j);
								    	double q1 = currentEntry9.getQ1();
								    	double q2 = currentEntry9.getQ2();
								    	double q3 = currentEntry9.getQ3();
								    	double q4 = currentEntry9.getQ4();
								    	double overall = currentEntry9.getOverall();
								    	String sAgent = currentEntry9.getSalesAgent();
								    	String pNode = currentEntry9.getNode();
								    	String dType = currentEntry9.getDataType();
								    	for (int i=0; i<xxmArrayLength; i++) {
									    	for (int l=0; l<nodeArrayLength; l++) {
									    		if (xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Actual")) {
									    			pipelineActualQ1 += q1;
									    			pipelineActualQ2 += q2;
									    			pipelineActualQ3 += q3;
									    			pipelineActualQ4 += q4;
									    			pipelineActualOverall += overall;
									    		} else if ((xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Threshold"))) {
									    			pipelineThresholdQ1 += q1;
									    			pipelineThresholdQ2 += q2;
									    			pipelineThresholdQ3 += q3;
									    			pipelineThresholdQ4 += q4;
									    			pipelineThresholdOverall += overall;
									    		}
									    	}
								    	}
									}
								    

									 // this is the data for De Booking Table					
									resultSubData10 = resultData.getDebookingData();
								    tempCount = resultSubData10.size();
								    for (int j = 0; j < tempCount; j++) {
								    	currentEntry10 = resultSubData10.get(j);
								    	double deBook = currentEntry10.getValue();
								    	String sAgent = currentEntry10.getSalesAgent();
								    	String pNode = currentEntry10.getNode();
								    	String dType = currentEntry10.getDataType();
								    	for (int i=0; i<xxmArrayLength; i++) {
									    	for (int l=0; l<nodeArrayLength; l++) {
									    		if (xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Actual")) {
									    			debookingActual += deBook;
									    		} else if ((xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Threshold"))) {
									    			debookingThreshold += deBook;
									    		}
									    	}
								    	}
									}
								    
								    
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		scpTableValues = new ScorecardSCPValues(accountPenetration,
				    			rev,
				    			techs,
				    			bnet,
				    			blist,
				    			totalAccounts);
		
		goalActualValues = new ScorecardGoalActualValues(actual, goal);
		growthValues = new ScorecardGrowthValues(currentYearValue, previousYearValue);
		discountValues = new ScorecardDiscountValues(bookActualENTNW, bookActualSecurity, bookActualCollab, 
				bookActualDCV, listActualENTNW, listActualSecurity, listActualCollab, listActualDCV,
				bookThresholdENTNW, bookThresholdSecurity, bookThresholdCollab, bookThresholdDCV, 
				listThresholdENTNW, listThresholdSecurity, listThresholdCollab, listThresholdDCV);
		
		atPenValues = new ScorecardATPenetrationValues(salesATActual, salesNonATActual,
				salesATThreshold, salesNonATThreshold);
		leadsValues = new ScorecardLeadsValues(waitingActual, acceptedActual, convertedActual, 
				overallLeadsActual, waitingThreshold, acceptedThreshold, convertedThreshold, 
				overallLeadsThreshold);
		partnerPenValues = new ScorecardPartnerPenetrationValues(assignedPartnerActual, assignedPartnerThreshold);
		linearityValues = new ScorecardLinearityValues(linearityActualM1, linearityActualM2, linearityActualM3, 
				linearityThresholdM1, linearityThresholdM2, linearityThresholdM3);
		pipelineValues = new ScorecardPipelineValues(pipelineActualQ1, pipelineActualQ2, pipelineActualQ3, pipelineActualQ4, 
				pipelineActualOverall, pipelineThresholdQ1, pipelineThresholdQ2, pipelineThresholdQ3, 
				pipelineThresholdQ4, pipelineThresholdOverall);
		debookingValues = new ScorecardDebookingValues(debookingActual, debookingThreshold);

		
		valueSet = new ScorecardXxmValues(goalActualValues, growthValues, discountValues, atPenValues, 
				scpTableValues, leadsValues, partnerPenValues, linearityValues, pipelineValues, debookingValues);

		
		return valueSet;
	}
	

	
	
	public static ScorecardXxmValues getXxmScorecardMetricsRanking(String agentType, String finQuarter, String eu, 
			String region, String node, String salesAgent) {
		ScorecardGoalActualValues goalActualValues;
		ScorecardGrowthValues growthValues;
		ScorecardDiscountValues discountValues;
		ScorecardATPenetrationValues atPenValues;
		ScorecardLeadsValues leadsValues;
		ScorecardPartnerPenetrationValues partnerPenValues;
		ScorecardLinearityValues linearityValues;
		ScorecardPipelineValues pipelineValues;
		ScorecardDebookingValues debookingValues;
/*		DashboardXxmOtherTableValues otherTableValues;*/
		ScorecardSCPValues scpTableValues;
		
		int tempCount=0, tempCount2=0, tempCount3=0, tempCount4=0, tempCount5=0, tempCount6=0, 
				tempCount7=0, tempCount8=0, tempCount9=0, tempCount10=0;
		double totCustomers=0D, q1cusPen=0D, q1techCount=0D, 
		q1oppCount=0D, q1revAll=0D, q1bListAll=0D, q1revColl=0D, 
		q1bListColl=0D, q1revDCV=0D, q1bListDCV=0D, q1revBN=0D,
		q1bListBN=0D, q1revSec=0D, q1bListSec=0D, cwp=0D,
		q2cusPen=0D, q2techCount=0D, 
		q2oppCount=0D, q2revAll=0D, q2bListAll=0D, q2revColl=0D, 
		q2bListColl=0D, q2revDCV=0D, q2bListDCV=0D, q2revBN=0D,
		q2bListBN=0D, q2revSec=0D, q2bListSec=0D,
		h1cusPen=0D, h1techCount=0D, 
		h1oppCount=0D, h1revAll=0D, h1bListAll=0D, h1revColl=0D, 
		h1bListColl=0D, h1revDCV=0D, h1bListDCV=0D, h1revBN=0D,
		h1bListBN=0D, h1revSec=0D, h1bListSec=0D,
		q3cusPen=0D, q3techCount=0D, 
		q3oppCount=0D, q3revAll=0D, q3bListAll=0D, q3revColl=0D, 
		q3bListColl=0D, q3revDCV=0D, q3bListDCV=0D, q3revBN=0D,
		q3bListBN=0D, q3revSec=0D, q3bListSec=0D,
		q4cusPen=0D, q4techCount=0D, 
		q4oppCount=0D, q4revAll=0D, q4bListAll=0D, q4revColl=0D, 
		q4bListColl=0D, q4revDCV=0D, q4bListDCV=0D, q4revBN=0D,
		q4bListBN=0D, q4revSec=0D, q4bListSec=0D,
		h2cusPen=0D, h2techCount=0D, 
		h2oppCount=0D, h2revAll=0D, h2bListAll=0D, h2revColl=0D, 
		h2bListColl=0D, h2revDCV=0D, h2bListDCV=0D, h2revBN=0D,
		h2bListBN=0D, h2revSec=0D, h2bListSec=0D,
		ytdtotCustomers=0D, ytdcusPen=0D, ytdtechCount=0D, 
		ytdoppCount=0D, ytdrevAll=0D, ytdbListAll=0D, ytdrevColl=0D, 
		ytdbListColl=0D, ytdrevDCV=0D, ytdbListDCV=0D, ytdrevBN=0D,
		ytdbListBN=0D, ytdrevSec=0D, ytdbListSec=0D;
		
		double goal=0D, actual=0D, currentYearValue=0D, previousYearValue=0D, 
				bookActualENTNW=0D, listActualENTNW=0D, bookActualSecurity=0D, listActualSecurity=0D,
				bookActualCollab=0D, listActualCollab=0D, bookActualDCV=0D, listActualDCV=0D,
				bookThresholdENTNW=0D, listThresholdENTNW=0D, bookThresholdSecurity=0D, listThresholdSecurity=0D,
				bookThresholdCollab=0D, listThresholdCollab=0D, bookThresholdDCV=0D, listThresholdDCV=0D,
				salesATActual=0D, salesNonATActual=0D,salesATThreshold=0D, salesNonATThreshold=0D, 
				waitingActual=0D, acceptedActual=0D, convertedActual=0D, overallLeadsActual=0D,
				waitingThreshold=0D, acceptedThreshold=0D, convertedThreshold=0D, overallLeadsThreshold=0D,
				assignedPartnerActual=0D, assignedPartnerThreshold=0D, 
				linearityActualM1=0D, linearityActualM2=0D, linearityActualM3=0D, 
				linearityThresholdM1=0D, linearityThresholdM2=0D, linearityThresholdM3=0D,
				pipelineActualQ1=0D, pipelineActualQ2=0D, pipelineActualQ3=0D, pipelineActualQ4=0D, pipelineActualOverall=0D,
				pipelineThresholdQ1=0D, pipelineThresholdQ2=0D, pipelineThresholdQ3=0D, pipelineThresholdQ4=0D, pipelineThresholdOverall=0D,
				debookingActual=0D, debookingThreshold=0D, accountPenetration=0D,	rev=0D, techs=0D, bnet=0D, blist=0D, totalAccounts=0D;

		ScorecardXxmValues valueSet = null;
		
		ScorecardXxmDataset resultData;

		List<ScorecardGoalActualTable> resultSubData1 = null;
		List<ScorecardGrowthTable> resultSubData2 = null;
		List<ScorecardDiscountTable> resultSubData3 = null;
		List<ScorecardATPenetrationTable> resultSubData4 = null;
		List<ScorecardSCPTable> resultSubData5 = null;
		List<ScorecardLeadsTable> resultSubData6 = null;
		List<ScorecardPartnerPenetrationTable> resultSubData7 = null;
		List<ScorecardLinearityTable> resultSubData8 = null;
		List<ScorecardPipelineTable> resultSubData9 = null;
		List<ScorecardDebookingTable> resultSubData10 = null;

		
		DataBaseXxmScorecardQueries query;

		ScorecardGoalActualTable currentEntry = null;
		ScorecardGrowthTable currentEntry2 = null;
		ScorecardDiscountTable currentEntry3 = null;
		ScorecardATPenetrationTable currentEntry4 = null;
		ScorecardSCPTable currentEntry5 = null;
		ScorecardLeadsTable currentEntry6 = null;
		ScorecardPartnerPenetrationTable currentEntry7 = null;
		ScorecardLinearityTable currentEntry8 = null;
		ScorecardPipelineTable currentEntry9 = null;
		ScorecardDebookingTable currentEntry10 = null;

		
		query = new DataBaseXxmScorecardQueries();
		String[] xxmArray, nodeArray;
		int xxmArrayLength = 1;
		int nodeArrayLength = 1;
		xxmArray = new String[xxmArrayLength];
		nodeArray = new String[nodeArrayLength];
		xxmArray[0] = salesAgent;
		nodeArray[0] = node;
		try {
			resultData = query.getAll(agentType, finQuarter, eu, region, node, salesAgent);
			
			 // this is the data for SCP Table					
				resultSubData5 = resultData.getSCPTableData();
			    tempCount = resultSubData5.size();
			    for (int j = 0; j < tempCount; j++) {
			    	currentEntry5 = resultSubData5.get(j);
			    	double val1 = currentEntry5.getAccountPenetration();
			    	double val2 = currentEntry5.getRevenue();
			    	double val3 = currentEntry5.getTechnologyCount();
			    	double val4 = currentEntry5.getBookingNet();
			    	double val5 = currentEntry5.getBookingList();
			    	double val6 = currentEntry5.getTotalAccounts();
			    	String sAgent = currentEntry5.getSalesAgent();
			    	String pNode = currentEntry5.getNode();
			    	String dType = currentEntry5.getDataType();
			    	for (int i=0; i<xxmArrayLength; i++) {
				    	for (int l=0; l<nodeArrayLength; l++) {
				    		if (xxmArray[i].equals(sAgent) && 
				    				nodeArray[l].equals(pNode)) {
				    			accountPenetration += val1;
				    			rev += val2;
				    			techs += val3;
				    			bnet += val4;
				    			blist += val5;
				    			totalAccounts += val6;
				    		}
				    	}
			    	}
				}

			
				 // this is the data for Goal Actual Table					
					resultSubData1 = resultData.getGoalActualData();
				    tempCount = resultSubData1.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultSubData1.get(j);
				    	double val = currentEntry.getValue();
				    	String sAgent = currentEntry.getSalesAgent();
				    	String pNode = currentEntry.getNode();
				    	String dType = currentEntry.getDataType();
				    	for (int i=0; i<xxmArrayLength; i++) {
					    	for (int l=0; l<nodeArrayLength; l++) {
					    		if (xxmArray[i].equals(sAgent) && 
					    				nodeArray[l].equals(pNode) &&
					    				dType.equals("Actual")) {
					    			actual += val;
					    		} else if ((xxmArray[i].equals(sAgent) && 
					    				nodeArray[l].equals(pNode) &&
					    				dType.equals("Threshold"))) {
					    			goal += val;
					    		}
					    	}
				    	}
					}
				
					 // this is the data for Growth Table					
						resultSubData2 = resultData.getGrowthTableData();
					    tempCount = resultSubData2.size();
					    for (int j = 0; j < tempCount; j++) {
					    	currentEntry2 = resultSubData2.get(j);
					    	double val = currentEntry2.getValue();
					    	String sAgent = currentEntry2.getSalesAgent();
					    	String pNode = currentEntry2.getNode();
					    	String dType = currentEntry2.getDataType();
					    	for (int i=0; i<xxmArrayLength; i++) {
						    	for (int l=0; l<nodeArrayLength; l++) {
						    		if (xxmArray[i].equals(sAgent) && 
						    				nodeArray[l].equals(pNode) &&
						    				dType.equals("Actual")) {
						    			currentYearValue += val;
						    		} else if ((xxmArray[i].equals(sAgent) && 
						    				nodeArray[l].equals(pNode) &&
						    				dType.equals("Threshold"))) {
						    			previousYearValue += val;
						    		}
						    	}
					    	}
						}


						 // this is the data for Discount Table					
							resultSubData3 = resultData.getDiscountData();
						    tempCount = resultSubData3.size();
						    for (int j = 0; j < tempCount; j++) {
						    	currentEntry3 = resultSubData3.get(j);
						    	double bookENTNW = currentEntry3.getBookENTNW();
						    	double bookSecurity = currentEntry3.getBookSecurity();
						    	double bookCollab = currentEntry3.getBookCollab();
						    	double bookDCV = currentEntry3.getBookDCV();
						    	double listENTNW = currentEntry3.getListENTNW();
						    	double listSecurity = currentEntry3.getListSecurity();
						    	double listCollab = currentEntry3.getListCollab();
						    	double listDCV = currentEntry3.getListDCV();
						    	String sAgent = currentEntry3.getSalesAgent();
						    	String pNode = currentEntry3.getNode();
						    	String dType = currentEntry3.getDataType();
						    	for (int i=0; i<xxmArrayLength; i++) {
							    	for (int l=0; l<nodeArrayLength; l++) {
							    		if (xxmArray[i].equals(sAgent) && 
							    				nodeArray[l].equals(pNode) &&
							    				dType.equals("Actual")) {
							    			bookActualENTNW += bookENTNW;
							    			bookActualSecurity += bookSecurity;
							    			bookActualCollab += bookCollab;
							    			bookActualDCV += bookDCV;
							    			listActualENTNW += listENTNW;
							    			listActualSecurity += listSecurity;
							    			listActualCollab += listCollab;
							    			listActualDCV += listDCV;
							    		} else if ((xxmArray[i].equals(sAgent) && 
							    				nodeArray[l].equals(pNode) &&
							    				dType.equals("Threshold"))) {
							    			bookThresholdENTNW += bookENTNW;
							    			bookThresholdSecurity += bookSecurity;
							    			bookThresholdCollab += bookCollab;
							    			bookThresholdDCV += bookDCV;
							    			listThresholdENTNW += listENTNW;
							    			listThresholdSecurity += listSecurity;
							    			listThresholdCollab += listCollab;
							    			listThresholdDCV += listDCV;
							    		}
							    	}
						    	}
							}

							 // this is the data for AT Penetration Table					
								resultSubData4 = resultData.getATPenData();
							    tempCount = resultSubData4.size();
							    for (int j = 0; j < tempCount; j++) {
							    	currentEntry4 = resultSubData4.get(j);
							    	double salesAT = currentEntry4.getSalesAT();
							    	double salesNonAT = currentEntry4.getSalesNonAT();
							    	String sAgent = currentEntry4.getSalesAgent();
							    	String pNode = currentEntry4.getNode();
							    	String dType = currentEntry4.getDataType();
							    	for (int i=0; i<xxmArrayLength; i++) {
								    	for (int l=0; l<nodeArrayLength; l++) {
								    		if (xxmArray[i].equals(sAgent) && 
								    				nodeArray[l].equals(pNode) &&
								    				dType.equals("Actual")) {
								    			salesATActual += salesAT;
								    			salesNonATActual += salesNonAT;
								    		} else if ((xxmArray[i].equals(sAgent) && 
								    				nodeArray[l].equals(pNode) &&
								    				dType.equals("Threshold"))) {
								    			salesATThreshold += salesAT;
								    			salesNonATThreshold += salesNonAT;
								    		}
								    	}
							    	}
								}
						    
								 // this is the data for Leads Table					
									resultSubData6 = resultData.getLeadsData();
								    tempCount = resultSubData6.size();
								    for (int j = 0; j < tempCount; j++) {
								    	currentEntry6 = resultSubData6.get(j);
								    	double waiting = currentEntry6.getLeadsWaiting();
								    	double accepted = currentEntry6.getLeadsAccepted();
								    	double converted = currentEntry6.getLeadsConverted();
								    	double overall = currentEntry6.getLeadsOverall();
								    	String sAgent = currentEntry6.getSalesAgent();
								    	String pNode = currentEntry6.getNode();
								    	String dType = currentEntry6.getDataType();
								    	for (int i=0; i<xxmArrayLength; i++) {
									    	for (int l=0; l<nodeArrayLength; l++) {
									    		if (xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Actual")) {
											    	waitingActual += waiting;
											    	acceptedActual += accepted;
											    	convertedActual += converted;
											    	overallLeadsActual += overall;
									    		} else if ((xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Threshold"))) {
											    	waitingThreshold += waiting;
											    	acceptedThreshold += accepted;
											    	convertedThreshold += converted;
											    	overallLeadsThreshold += overall;
									    		}
									    	}
								    	}
									}


									 // this is the data for Partner Penetration Table					
									resultSubData7 = resultData.getPartnerPenetrationData();
								    tempCount = resultSubData7.size();
								    for (int j = 0; j < tempCount; j++) {
								    	currentEntry7 = resultSubData7.get(j);
								    	double parPen = currentEntry7.getValue();
								    	String sAgent = currentEntry7.getSalesAgent();
								    	String pNode = currentEntry7.getNode();
								    	String dType = currentEntry7.getDataType();
								    	for (int i=0; i<xxmArrayLength; i++) {
									    	for (int l=0; l<nodeArrayLength; l++) {
									    		if (xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Actual")) {
									    			assignedPartnerActual += parPen;
									    		} else if ((xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Threshold"))) {
									    			assignedPartnerThreshold += parPen;
									    		}
									    	}
								    	}
									}

									 // this is the data for Linearity Table					
									resultSubData8 = resultData.getLinearityData();
								    tempCount = resultSubData8.size();
								    for (int j = 0; j < tempCount; j++) {
								    	currentEntry8 = resultSubData8.get(j);
								    	double m1 = currentEntry8.getLinearityM1();
								    	double m2 = currentEntry8.getLinearityM2();
								    	double m3 = currentEntry8.getLinearityM3();
								    	String sAgent = currentEntry8.getSalesAgent();
								    	String pNode = currentEntry8.getNode();
								    	String dType = currentEntry8.getDataType();
								    	for (int i=0; i<xxmArrayLength; i++) {
									    	for (int l=0; l<nodeArrayLength; l++) {
									    		if (xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Actual")) {
									    			linearityActualM1 += m1;
									    			linearityActualM2 += m2;
									    			linearityActualM3 += m3;
									    		} else if ((xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Threshold"))) {
									    			linearityThresholdM1 += m1;
									    			linearityThresholdM2 += m2;
									    			linearityThresholdM3 += m3;
									    		}
									    	}
								    	}
									}

									 // this is the data for Pipeline Table					
									resultSubData9 = resultData.getPipelineData();
								    tempCount = resultSubData9.size();
								    for (int j = 0; j < tempCount; j++) {
								    	currentEntry9 = resultSubData9.get(j);
								    	double q1 = currentEntry9.getQ1();
								    	double q2 = currentEntry9.getQ2();
								    	double q3 = currentEntry9.getQ3();
								    	double q4 = currentEntry9.getQ4();
								    	double overall = currentEntry9.getOverall();
								    	String sAgent = currentEntry9.getSalesAgent();
								    	String pNode = currentEntry9.getNode();
								    	String dType = currentEntry9.getDataType();
								    	for (int i=0; i<xxmArrayLength; i++) {
									    	for (int l=0; l<nodeArrayLength; l++) {
									    		if (xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Actual")) {
									    			pipelineActualQ1 += q1;
									    			pipelineActualQ2 += q2;
									    			pipelineActualQ3 += q3;
									    			pipelineActualQ4 += q4;
									    			pipelineActualOverall += overall;
									    		} else if ((xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Threshold"))) {
									    			pipelineThresholdQ1 += q1;
									    			pipelineThresholdQ2 += q2;
									    			pipelineThresholdQ3 += q3;
									    			pipelineThresholdQ4 += q4;
									    			pipelineThresholdOverall += overall;
									    		}
									    	}
								    	}
									}
								    

									 // this is the data for De Booking Table					
									resultSubData10 = resultData.getDebookingData();
								    tempCount = resultSubData10.size();
								    for (int j = 0; j < tempCount; j++) {
								    	currentEntry10 = resultSubData10.get(j);
								    	double deBook = currentEntry10.getValue();
								    	String sAgent = currentEntry10.getSalesAgent();
								    	String pNode = currentEntry10.getNode();
								    	String dType = currentEntry10.getDataType();
								    	for (int i=0; i<xxmArrayLength; i++) {
									    	for (int l=0; l<nodeArrayLength; l++) {
									    		if (xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Actual")) {
									    			debookingActual += deBook;
									    		} else if ((xxmArray[i].equals(sAgent) && 
									    				nodeArray[l].equals(pNode) &&
									    				dType.equals("Threshold"))) {
									    			debookingThreshold += deBook;
									    		}
									    	}
								    	}
									}
								    
								    
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		scpTableValues = new ScorecardSCPValues(accountPenetration,
    			rev,
    			techs,
    			bnet,
    			blist,
    			totalAccounts);

		
		goalActualValues = new ScorecardGoalActualValues(actual, goal);
		growthValues = new ScorecardGrowthValues(currentYearValue, previousYearValue);
		discountValues = new ScorecardDiscountValues(bookActualENTNW, bookActualSecurity, bookActualCollab, 
				bookActualDCV, listActualENTNW, listActualSecurity, listActualCollab, listActualDCV,
				bookThresholdENTNW, bookThresholdSecurity, bookThresholdCollab, bookThresholdDCV, 
				listThresholdENTNW, listThresholdSecurity, listThresholdCollab, listThresholdDCV);
		
		atPenValues = new ScorecardATPenetrationValues(salesATActual, salesNonATActual,
				salesATThreshold, salesNonATThreshold);
		leadsValues = new ScorecardLeadsValues(waitingActual, acceptedActual, convertedActual, 
				overallLeadsActual, waitingThreshold, acceptedThreshold, convertedThreshold, 
				overallLeadsThreshold);
		partnerPenValues = new ScorecardPartnerPenetrationValues(assignedPartnerActual, assignedPartnerThreshold);
		linearityValues = new ScorecardLinearityValues(linearityActualM1, linearityActualM2, linearityActualM3, 
				linearityThresholdM1, linearityThresholdM2, linearityThresholdM3);
		pipelineValues = new ScorecardPipelineValues(pipelineActualQ1, pipelineActualQ2, pipelineActualQ3, pipelineActualQ4, 
				pipelineActualOverall, pipelineThresholdQ1, pipelineThresholdQ2, pipelineThresholdQ3, 
				pipelineThresholdQ4, pipelineThresholdOverall);
		debookingValues = new ScorecardDebookingValues(debookingActual, debookingThreshold);

		
		valueSet = new ScorecardXxmValues(goalActualValues, growthValues, discountValues, atPenValues, 
				scpTableValues, leadsValues, partnerPenValues, linearityValues, pipelineValues, debookingValues);
		
		return valueSet;
	}

	
	
	
}

class Headers {
	private JLabel metric, value, threshold, point;
	private JSeparator topSep, bottomSep;
	
	public Headers(JSeparator tSep, JSeparator bSep, JLabel metric, JLabel value, 
			JLabel threshold, JLabel point) {
		this.topSep = tSep;
		this.bottomSep = bSep;
		this.metric = metric;
		this.value = value;
		this.threshold = threshold;
		this.point = point;
	}
	
	public JSeparator getTopSeparator() {
		return this.topSep;
	}
	public JSeparator getBottomSeparator() {
		return this.bottomSep;
	}
	
	public JLabel getMetricHeader() {
		return this.metric;
	}
	
	public JLabel getValuHeader() {
		return this.value;
	}
	
	public JLabel getThresholdHeader() {
		return this.threshold;
	}
	
	public JLabel getPointHeader() {
		return this.point;
	}
	
	
}

