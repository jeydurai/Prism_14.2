package prism14;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;


public class DashboardScoreCard extends JInternalFrame{

	
    private List<DashboardXxmDataset> valueset = null;
    private List<DataBaseXxmDashOtherTableModified> valueSubset = null;
    private List<DataBaseXxmDashSalesTable> valueSubset2 = null;
    private List<DataBaseXxmDashTechTable> valueSubset3 = null;
    private DashboardXxmDataset valueEntry;
    private DataBaseXxmDashOtherTableModified valueSubEntry1;
    private DataBaseXxmDashSalesTable valueSubEntry2;
    private DataBaseXxmDashTechTable valueSubEntry3;
    
    private DashboardXxmValues valueSet;
    private DashboardXxmOtherTableValues otherTableValueset;
    private DashboardXxmSalesTableValues salesTableValueset;
    private DashboardXxmTechTableValues techTableValueset;

	private boolean isParPre, isParDev, isBB, isCLPL, isShark, isSharkCheckBoxVisible;

    
    private boolean isToggleOn = true;
	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
	private double panel2ActualQ1, panel2ThresholdQ1, panel2ActualQ2, panel2ThresholdQ2, 
	panel2ActualH1, panel2ThresholdH1, panel2ActualQ3, panel2ThresholdQ3, 
	panel2ActualQ4, panel2ThresholdQ4, panel2ActualH2, panel2ThresholdH2,
	panel2ActualYTD, panel2ThresholdYTD, actualRS, thresholdRS, actualSec, thresholdSec,
	actualUCS, thresholdUCS, actualUC, thresholdUC, actualDCS, thresholdDCS,  actualVideo, thresholdVideo, 
	actualWLAN, thresholdWLAN, pointRS, pointSec, pointUCS, pointUC, pointDCS, 
	pointVideo, pointWLAN, techCoverage, posibleTechPoints;
    private ImageIcon dScreenImage1, dScreenImage2, redMark, yellowMark, greenMark, screenTitle, imageIcon;
    private JLabel jlDScreenImage1, jlDScreenImage2, jlWhatIsItTitle, jlHowItWorksTitle, jlTechnologyTitle,
    jlSalesAchievementTitle, jlOtherMetricsTitle, jlScreenTitle, jlCWPShare;
    private GradientPanel infoPanel;
    private ChartPanel q1Panel, q2Panel, q3Panel, q4Panel, q5Panel, q6Panel, q7Panel;
    private JPanel jpRedMark, jpGreenMark, jpBlueMark;
    private JLabel jlRedMark, jlGreenMark, jlYellowMark, jlRedText, jlGreenText, jlYellowText;
	private JTextArea jtaWhatIsIt, jtaHowItWorks, jtaInfo;
	private JButton jbWhatIsItReadMore;
	private JFrame jfWhatIsItReadMore;
    private RedTrafficLight rsRedBox, secRedBox, ucsRedBox, ucRedBox, dcsRedBox, videoRedBox, wlanRedBox;
    private YellowTrafficLight rsYellowBox, secYellowBox, ucsYellowBox, ucYellowBox, dcsYellowBox, videoYellowBox, wlanYellowBox;
    private GreenTrafficLight rsGreenBox, secGreenBox, ucsGreenBox, ucGreenBox, dcsGreenBox, videoGreenBox, wlanGreenBox;
    private GradientPanel2 panel1, panel2, panel11, panel3Parent;
    private JCheckBox jchbxPanel3PartnerPreferred, jchbxPanel3PartnerDevelop, jchbxPanel3BigBet,
    jchbxPanel3CLToPL, jchbxPanel3Sharkpool;
//    private JToggleButton viewToggle;
    private JButton viewToggle;
    private JSeparator sep1, sep11,sep2, sep3;
    private JSeparator metricSepQ1, metricSepQ2, metricSepH1, metricSepQ3, metricSepQ4, metricSepH2, metricSepYTD,
    					panel3Sep;
    private JLabel jlXxmOption, jlEU, jlRegion, jlNode, jlXxm, jlPartner, rsLabel, secLabel, 
    ucsLabel, ucLabel, dcsLabel, videoLabel, wlanLabel;
    private GradientPanel plTotalAccounts, plAccountPenetration, plTechPenetration, plBNDiscount, plSecDiscount, plCollDiscount,
    plDCVDiscount, plOverallDiscount, plAchievement, plAvgYldPerAccounts, plAvgYldPerOpp, plCWP, plAOC, panel3;
    private JLabel jlTotalAccounts, jlAccountPenetration, jlTechPenetration, jlBNDiscount, jlSecDiscount, jlCollDiscount,
    jlDCVDiscount, jlOverallDiscount, jlAchievement ,jlAvgYldPerAccounts, jlAvgYldPerOpp, jlCWP, jlAOC, jlMetricTitleQ1, jlMetricTitleQ2,
    jlMetricTitleH1, jlMetricTitleQ3, jlMetricTitleQ4, jlMetricTitleH2, jlMetricTitleYTD, jlTotalAccountsValueQ1,
    jlAccountPenetrationValueQ1, jlTechPenetrationValueQ1, jlBNDiscountValueQ1, jlSecDiscountValueQ1, jlCollDiscountValueQ1,
    jlDCVDiscountValueQ1, jlOverallDiscountValueQ1, jlAchievementValueQ1, jlAvgYldPerAccountsValueQ1, jlAvgYldPerOppValueQ1, jlCWPValueQ1, jlAOCValueQ1,
    jlTotalAccountsValueQ2, jlAccountPenetrationValueQ2, jlTechPenetrationValueQ2, jlBNDiscountValueQ2, jlSecDiscountValueQ2, jlCollDiscountValueQ2,
    jlDCVDiscountValueQ2, jlOverallDiscountValueQ2, jlAchievementValueQ2, jlAvgYldPerAccountsValueQ2, jlAvgYldPerOppValueQ2,  jlCWPValueQ2, jlAOCValueQ2, jlTotalAccountsValueH1,
    jlAccountPenetrationValueH1, jlTechPenetrationValueH1, jlBNDiscountValueH1, jlSecDiscountValueH1, jlCollDiscountValueH1, jlDCVDiscountValueH1,
    jlOverallDiscountValueH1, jlAchievementValueH1, jlAvgYldPerAccountsValueH1, jlAvgYldPerOppValueH1,  jlCWPValueH1, jlAOCValueH1, jlTotalAccountsValueQ3, jlAccountPenetrationValueQ3,
    jlTechPenetrationValueQ3, jlBNDiscountValueQ3, jlSecDiscountValueQ3, jlCollDiscountValueQ3, jlDCVDiscountValueQ3, jlOverallDiscountValueQ3,
    jlAchievementValueQ3, jlAvgYldPerAccountsValueQ3, jlAvgYldPerOppValueQ3, jlCWPValueQ3, jlAOCValueQ3, jlTotalAccountsValueH2, jlAccountPenetrationValueH2, jlTechPenetrationValueH2,
    jlBNDiscountValueH2, jlSecDiscountValueH2, jlCollDiscountValueH2, jlDCVDiscountValueH2, jlOverallDiscountValueH2, jlAvgYldPerAccountsValueH2, jlAchievementValueH2,
    jlAvgYldPerOppValueH2,  jlCWPValueH2, jlAOCValueH2, jlTotalAccountsValueQ4, jlAccountPenetrationValueQ4, jlTechPenetrationValueQ4, jlBNDiscountValueQ4,
    jlSecDiscountValueQ4, jlCollDiscountValueQ4, jlDCVDiscountValueQ4, jlOverallDiscountValueQ4, jlAvgYldPerAccountsValueQ4, jlAchievementValueQ4, jlAvgYldPerOppValueQ4, jlCWPValueQ4, jlAOCValueQ4,
    jlTotalAccountsValueYTD, jlAccountPenetrationValueYTD, jlTechPenetrationValueYTD, jlBNDiscountValueYTD, jlSecDiscountValueYTD, jlCollDiscountValueYTD,
    jlDCVDiscountValueYTD, jlOverallDiscountValueYTD, jlAchievementValueYTD, jlAvgYldPerAccountsValueYTD, jlAvgYldPerOppValueYTD, jlCWPValueYTD, jlAOCValueYTD; 
    @SuppressWarnings("rawtypes")
	private JComboBox jcbxXxmOption, jcbxEU;
    @SuppressWarnings("rawtypes")
	private JList jltRegion, jltNode, jltXxm, jltPartner;
    private JScrollPane regionScrollPane, nodeScrollPane, xxmScrollPane, partnerScrollPane;
//    private BufferedImage image;
    @SuppressWarnings("rawtypes")
	private DefaultComboBoxModel eUNames, salesAgentType;
    @SuppressWarnings("rawtypes")
	private DefaultListModel node, xxM, partner;
    
    @SuppressWarnings("unused")
	private DataBaseEUMasterQueries eUMasterQueries;
    @SuppressWarnings("unused")
	private DataBaseEUMaster currentEUMasterEntry;
    @SuppressWarnings("unused")
	private List<DataBaseEUMaster> resultsEUMaster;

    private DataBaseSalesListQueries salesListQueries;
    private DataBaseSalesList currentSalesListEntry;
    private List<DataBaseSalesList> resultsSalesList;
    
    private ArrayList<String> tempArrayList;
    private static DefaultValueDataset q1DataSet, q2DataSet, q3DataSet, q4DataSet, q5DataSet, q6DataSet, q7DataSet, therDataSet;
    private static CategoryDataset dataset;


    @SuppressWarnings("rawtypes")
	private DefaultListModel regionFillModel, userFillModel, specialNodeFillModel,
    dummyModel;
    

    /*Panels' components Variables declaration*/
	private double rsActual, secActual, ucsActual, ucActual, dcsActual, 
	videoActual, wlanActual;
	private double rsThreshold, secThreshold, ucsThreshold, ucThreshold, 
		dcsThreshold, videoThreshold, wlanThreshold;
	private double rsPoint, secPoint, ucsPoint, ucPoint, 
	dcsPoint, videoPoint, wlanPoint;
	private double q1Actual, q2Actual, h1Actual, q3Actual, q4Actual, 
	h2Actual, ytdActual;
	private double q1Threshold, q2Threshold, h1Threshold, q3Threshold, 
		q4Threshold, h2Threshold, ytdThreshold;
	private double q1Point, q2Point, h1Point, q3Point, 
	q4Point, h2Point, ytdPoint;
	private double q1TempCusPen, q2TempCusPen, h1TempCusPen, q3TempCusPen, q4TempCusPen, h2TempCusPen, ytdTempCusPen;
	private double q1TechCount, q1OppCount, q1RevAll, q1BListAll, q1RevColl, q1BListColl, q1RevDCV, q1BListDCV, q1RevBN, q1BListBN, q1RevSec, q1BListSec,
	q2TechCount, q2OppCount, q2RevAll, q2BListAll, q2RevColl, q2BListColl, q2RevDCV, q2BListDCV, q2RevBN, q2BListBN, q2RevSec, q2BListSec,  
	h1TechCount, h1OppCount, h1RevAll, h1BListAll, h1RevColl, h1BListColl, h1RevDCV, h1BListDCV, h1RevBN, h1BListBN, h1RevSec, h1BListSec,  
	q3TechCount, q3OppCount, q3RevAll, q3BListAll, q3RevColl, q3BListColl, q3RevDCV, q3BListDCV, q3RevBN, q3BListBN, q3RevSec, q3BListSec,  
	q4TechCount, q4OppCount, q4RevAll, q4BListAll, q4RevColl, q4BListColl, q4RevDCV, q4BListDCV, q4RevBN, q4BListBN, q4RevSec, q4BListSec,  
	h2TechCount, h2OppCount, h2RevAll, h2BListAll, h2RevColl, h2BListColl, h2RevDCV, h2BListDCV, h2RevBN, h2BListBN, h2RevSec, h2BListSec,  
	ytdTechCount, ytdOppCount, ytdRevAll, ytdBListAll, ytdRevColl, ytdBListColl, ytdRevDCV, ytdBListDCV, ytdRevBN, ytdBListBN, ytdRevSec, ytdBListSec;  
	private double[][] q1TotCus, q1Pen, q1CusPen, q1TechPen, q1Discount, q1DiscountAll, q1DiscountColl, q1DiscountDCV, 
	q1DiscountBN, q1DiscountSec, q1Yld, q1CWPAchievement, q1CWP, q1Achievement, q1AOC, q1YldPerCus, q1YldPerOpp,
	q2TotCus, q2Pen, q2CusPen, q2TechPen, q2Discount, q2DiscountAll, q2DiscountColl, q2DiscountDCV, 
	q2DiscountBN, q2DiscountSec, q2Yld, q2CWPAchievement, q2CWP, q2Achievement, q2AOC, q2YldPerCus, q2YldPerOpp,
	h1TotCus, h1Pen, h1CusPen, h1TechPen, h1Discount, h1DiscountAll, h1DiscountColl, h1DiscountDCV, 
	h1DiscountBN, h1DiscountSec, h1Yld, h1CWPAchievement, h1CWP, h1Achievement, h1AOC, h1YldPerCus, h1YldPerOpp,
	q3TotCus, q3Pen, q3CusPen, q3TechPen, q3Discount, q3DiscountAll, q3DiscountColl, q3DiscountDCV, 
	q3DiscountBN, q3DiscountSec, q3Yld, q3CWPAchievement, q3CWP, q3Achievement, q3AOC, q3YldPerCus, q3YldPerOpp,
	q4TotCus, q4Pen, q4CusPen, q4TechPen, q4Discount, q4DiscountAll, q4DiscountColl, q4DiscountDCV, 
	q4DiscountBN, q4DiscountSec, q4Yld, q4CWPAchievement, q4CWP, q4Achievement, q4AOC, q4YldPerCus, q4YldPerOpp,
	h2TotCus, h2Pen, h2CusPen, h2TechPen, h2Discount, h2DiscountAll, h2DiscountColl, h2DiscountDCV, 
	h2DiscountBN, h2DiscountSec, h2Yld, h2CWPAchievement, h2CWP, h2Achievement, h2AOC, h2YldPerCus, h2YldPerOpp,
	ytdTotCus, ytdPen, ytdCusPen, ytdTechPen, ytdDiscount, ytdDiscountAll, ytdDiscountColl, ytdDiscountDCV, 
	ytdDiscountBN, ytdDiscountSec, ytdYld, ytdCWPAchievement, ytdCWP, ytdAchievement, ytdAOC, ytdYldPerCus, ytdYldPerOpp;
	private CategoryDataset q1TotCusDs, q1PenDs, q1DiscountDs, q1YldDs, q1CWPAchievementDs, q1AOCDs,
	q2TotCusDs, q2PenDs, q2DiscountDs, q2YldDs, q2CWPAchievementDs, q2AOCDs,
	h1TotCusDs, h1PenDs, h1DiscountDs, h1YldDs, h1CWPAchievementDs, h1AOCDs,
	q3TotCusDs, q3PenDs, q3DiscountDs, q3YldDs, q3CWPAchievementDs, q3AOCDs,
	q4TotCusDs, q4PenDs, q4DiscountDs, q4YldDs, q4CWPAchievementDs, q4AOCDs,		
	h2TotCusDs, h2PenDs, h2DiscountDs, h2YldDs, h2CWPAchievementDs, h2AOCDs,
	ytdTotCusDs, ytdPenDs, ytdDiscountDs, ytdYldDs, ytdCWPAchievementDs,  ytdAOCDs;
	private double coverage;
    protected GradientPanel2 bannerPanel;
    protected GradientPanel mainPanel, compoPanel;
    protected JLabel JlPrimaryScreenTitle;
    private ImageIcon imgLogo, pScreenImage1, pScreenImage2, trueNorthImage, primaryScreenTitle;
    private DateFormat dateFormat;
    private Date currentDate;
    private JLabel jlLogo, jlPrismText, jlDateTime, jlPScreenImage1, 
    jlPScreenImage2, jlTrueNorthImage;
    protected JScrollPane mainScrollPane, rootScrollPane;
    private JButton jbSignOut,jbEditAccount,jbWelcomeTo;
    protected int fontSize, tempCount;
    protected String fontFamily, fontSpecialFamily;
	public static String role, eUnit, region, reportingTo, firstName, lastName, emailID;
	private String dataType = "";
    private DataBaseUserDataQueries userDataQueries;
    private DataBaseUserData currentUserDataEntry;
    private List<DataBaseUserData> resultsUserData;
	
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
	public DashboardScoreCard() {
		super("TBM-VBM Dashboard #" + (++openFrameCount),
				true, true, true, true);
		trueNorthImage = new ImageIcon(this.getClass().getResource(GeneralConstants.TRUE_NORTH_IMAGE2));
    	imageIcon = new ImageIcon(this.getClass().getResource(GeneralConstants.INTERNAL_FRAME_LOGO_IMAGE));
        fontSize = 11;
        fontFamily = "Arial";
        fontSpecialFamily = "Verdana";
		setSize(700,500);
		setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
		
		dScreenImage1 = new ImageIcon(this.getClass().getResource(GeneralConstants.DASHBOARD_SCREEN_IMAGE1));
		dScreenImage2 = new ImageIcon(this.getClass().getResource(GeneralConstants.DASHBOARD_SCREEN_IMAGE2));
		redMark = new ImageIcon(this.getClass().getResource(GeneralConstants.RED_BALL));
		yellowMark = new ImageIcon(this.getClass().getResource(GeneralConstants.YELLOW_BALL));
		greenMark = new ImageIcon(this.getClass().getResource(GeneralConstants.GREEN_BALL));
		screenTitle = new ImageIcon(this.getClass().getResource(GeneralConstants.TBMVBM_TITLE3));

		jlScreenTitle = new JLabel(screenTitle);
		jlScreenTitle.setBounds(400, 15, 600, 50);
		jlScreenTitle.setOpaque(false);

        try {
	    	userDataQueries = new DataBaseUserDataQueries();
	        eUMasterQueries = new DataBaseEUMasterQueries();
	        salesListQueries = new DataBaseSalesListQueries();
	        }
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException iException){
			iException.printStackTrace();
		}

        
    	

/*//Get Role		    
	    try {
			resultsUserData =  userDataQueries.getRolebyName(DesktopPrimaryScreen.userID);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
	    int tempCount = resultsUserData.size();
		    for (int i = 0; i < tempCount; i++) {
		    	currentUserDataEntry = resultsUserData.get(i);
		    	role = currentUserDataEntry.getRole();
		    }
*/
        
//Get Reporting to		    
	    try {
			resultsUserData =  userDataQueries.getReportingTobyName(DesktopPrimaryScreen.userID);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
	     tempCount = resultsUserData.size();
		    for (int i = 0; i < tempCount; i++) {
		    	currentUserDataEntry = resultsUserData.get(i);
		    	reportingTo = currentUserDataEntry.getReportingTo();
		    }
		    
//Get First Name		    
	    try {
			resultsUserData =  userDataQueries.getFirstNamebyName(DesktopPrimaryScreen.userID);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
	     tempCount = resultsUserData.size();
		    for (int i = 0; i < tempCount; i++) {
		    	currentUserDataEntry = resultsUserData.get(i);
		    	firstName = currentUserDataEntry.getFirstName();
		    }

//Get Last Name		    
	    try {
			resultsUserData =  userDataQueries.getLastNamebyName(DesktopPrimaryScreen.userID);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
	     tempCount = resultsUserData.size();
		    for (int i = 0; i < tempCount; i++) {
		    	currentUserDataEntry = resultsUserData.get(i);
		    	lastName = currentUserDataEntry.getLastName();
		    }
//Get Email ID		    
	    try {
			resultsUserData =  userDataQueries.getEmailIDbyName(DesktopPrimaryScreen.userID);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
	     tempCount = resultsUserData.size();
		    for (int i = 0; i < tempCount; i++) {
		    	currentUserDataEntry = resultsUserData.get(i);
		    	emailID = currentUserDataEntry.getEmailID();
		    }
				    
// Get Execution Unit		    
	    try {
			resultsUserData =  userDataQueries.getEUbyName(DesktopPrimaryScreen.userID);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
	     tempCount = resultsUserData.size();
		    for (int i = 0; i < tempCount; i++) {
		    	currentUserDataEntry = resultsUserData.get(i);
		    	eUnit = currentUserDataEntry.getEU();
		    }

// Get Region		    
	    try {
			resultsUserData =  userDataQueries.getRegionbyName(DesktopPrimaryScreen.userID);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
	     tempCount = resultsUserData.size();
		    for (int i = 0; i < tempCount; i++) {
		    	currentUserDataEntry = resultsUserData.get(i);
		    	region = currentUserDataEntry.getRegion();
		    }

        
        
        
        try {
			setPanelReady();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setComponentsReady();
        setHeadersReady();
        bannerPanel.add(jlScreenTitle);
	    try {
	    	
		    setFrameComponentsReady();
	    }
	    catch (SQLException sqlException) {
	    	sqlException.printStackTrace();
	    }
	}
	
	
	protected void setPanelReady() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		Dimension di = new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
        rootScrollPane = new JScrollPane();
        rootScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        rootScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rootScrollPane.setPreferredSize(di);

		mainPanel = new GradientPanel();
        mainPanel.setBackground(Color.BLUE);
        mainPanel.setLayout(null);
        rootScrollPane.setViewportView(mainPanel);
        setContentPane(rootScrollPane);
        
        sep1 = new JSeparator();
        sep1.setBounds(0, 95,1360,2);
        mainPanel.add(sep1);
        
        jlPScreenImage1 = new JLabel(pScreenImage1);
        jlPScreenImage1.setBounds(1, 97, 700, 700);
        jlPScreenImage1.setOpaque(false);
        mainPanel.add(jlPScreenImage1);

        jlPScreenImage2 = new JLabel(pScreenImage2);
        jlPScreenImage2.setBounds(700, 97, 700, 650);
        jlPScreenImage2.setOpaque(false);
        mainPanel.add(jlPScreenImage2);
    }

    protected void setHeadersReady() {
    	this.setFrameIcon(imageIcon);
        
        imgLogo = new ImageIcon(this.getClass().getResource(GeneralConstants.CISCO_LOGO));

        jlLogo = new JLabel(imgLogo);
        jlLogo.setBounds(0, 0, 100, 60);

        bannerPanel = new GradientPanel2();
//		bannerPanel.setBackground(new Color(245,245,245));
		bannerPanel.setBackground(new Color(255,255,255));
		bannerPanel.setLayout(null);
        bannerPanel.setSize(1355,95);
        bannerPanel.setLocation(0, 0);
//        bannerPanel.setBorder(BorderFactory.createLineBorder(new Color(220,220,220), 1));        
		bannerPanel.add(jlLogo);
    	
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        currentDate = new Date();
        jlDateTime = new JLabel();
        jlDateTime.setText(dateFormat.format(currentDate));
        jlDateTime.setFont(new Font(fontSpecialFamily,Font.ITALIC+Font.BOLD,fontSize+3));
        jlDateTime.setBounds(1160, 0, 200, 20);
		bannerPanel.add(jlDateTime);
        
        jlTrueNorthImage = new JLabel(trueNorthImage);
        jlTrueNorthImage.setBounds(1200, 25, 125, 80);
        jlTrueNorthImage.setOpaque(false);
		bannerPanel.add(jlTrueNorthImage);

		JlPrimaryScreenTitle = new JLabel(primaryScreenTitle);
		JlPrimaryScreenTitle.setBounds(350, 15, 700, 50);
		JlPrimaryScreenTitle.setOpaque(false);
        bannerPanel.add(JlPrimaryScreenTitle);

		mainPanel.add(bannerPanel);

    }

    protected void setComponentsReady() {
		try {
	    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		int PAN_X_POSITION = 10;
		int PAN_WIDTH = 400;
		int PAN_Y_POSITION = 0;
		int PAN_HEIGHT = 360;
		int PAN_INCREMENT = 50;
		compoPanel = new GradientPanel();
        compoPanel.setBackground(Color.BLUE);
        compoPanel.setPreferredSize(new Dimension(1200,840+200));
        compoPanel.setFont(new Font(fontFamily,Font.BOLD,fontSize));

        mainScrollPane = new JScrollPane();
        mainScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setBounds(0, 165, 1355, 485);
        mainScrollPane.setViewportView(compoPanel);
        
        
        panel1 = new GradientPanel2();
	    panel1.setBackground(new Color(250,250,250,150));
	    panel1.setBounds(PAN_X_POSITION, PAN_Y_POSITION+2, PAN_WIDTH, PAN_HEIGHT-2);
	    panel1.setLayout(null);

	    panel11 = new GradientPanel2();
	    panel11.setBackground(null);
	    panel11.setOpaque(false);
	    panel11.setBounds(5, 50, 100, 280);
	    panel11.setLayout(null);

	    panel2 = new GradientPanel2();
	    panel2.setBackground(new Color(250,250,250,0));
	    panel2.setBounds(PAN_X_POSITION+(8*PAN_INCREMENT)+5, PAN_Y_POSITION+2, PAN_WIDTH+(11*PAN_INCREMENT)-30, PAN_HEIGHT-2);
	    panel2.setLayout(null);

	    
//		viewToggle = new JToggleButton("Overall Accounts View - OFF");
		viewToggle = new JButton("Overall Accounts View - OFF");
		viewToggle.setBounds(10, 365, 190, 20);
		viewToggle.setFocusPainted(false);
    	viewToggle.setForeground(Color.RED);
    	viewToggle.setFont(new Font(fontSpecialFamily,Font.PLAIN,fontSize-1));
//		viewToggle.addItemListener(new viewToggleListener());
		viewToggle.addActionListener(new viewToggleListener());

		jchbxPanel3PartnerPreferred = new JCheckBox("Partner Preferred");
		jchbxPanel3PartnerPreferred.setBounds(250, 365, 150, 20);
		jchbxPanel3PartnerPreferred.setFont(new Font(fontSpecialFamily,Font.PLAIN,fontSize-1));
		jchbxPanel3PartnerPreferred.setMnemonic('P');
		jchbxPanel3PartnerPreferred.setBackground(null);
		jchbxPanel3PartnerPreferred.setOpaque(false);
		jchbxPanel3PartnerPreferred.setSelected(true);
		jchbxPanel3PartnerPreferred.setEnabled(true);
		jchbxPanel3PartnerPreferred.setFocusPainted(true);
		jchbxPanel3PartnerPreferred.addItemListener(new toggleCheckBoxListener());

		jchbxPanel3PartnerDevelop = new JCheckBox("Partner Develop");
		jchbxPanel3PartnerDevelop.setBounds(450, 365, 150, 20);
		jchbxPanel3PartnerDevelop.setFont(new Font(fontSpecialFamily,Font.PLAIN,fontSize-1));
		jchbxPanel3PartnerDevelop.setMnemonic('D');
		jchbxPanel3PartnerDevelop.setBackground(null);
		jchbxPanel3PartnerDevelop.setOpaque(false);
		jchbxPanel3PartnerDevelop.setSelected(true);
		jchbxPanel3PartnerDevelop.setEnabled(true);
		jchbxPanel3PartnerDevelop.setFocusPainted(true);
		jchbxPanel3PartnerDevelop.addItemListener(new toggleCheckBoxListener());

		jchbxPanel3BigBet = new JCheckBox("BigBet");
		jchbxPanel3BigBet.setBounds(650, 365, 150, 20);
		jchbxPanel3BigBet.setFont(new Font(fontSpecialFamily,Font.PLAIN,fontSize-1));
		jchbxPanel3BigBet.setMnemonic('B');
		jchbxPanel3BigBet.setBackground(null);
		jchbxPanel3BigBet.setOpaque(false);
		jchbxPanel3BigBet.setSelected(true);
		jchbxPanel3BigBet.setEnabled(true);
		jchbxPanel3BigBet.setFocusPainted(true);
		jchbxPanel3BigBet.addItemListener(new toggleCheckBoxListener());

		jchbxPanel3CLToPL = new JCheckBox("CL to PL");
		jchbxPanel3CLToPL.setBounds(850, 365, 150, 20);
	    jchbxPanel3CLToPL.setFont(new Font(fontSpecialFamily,Font.PLAIN,fontSize-1));
	    jchbxPanel3CLToPL.setMnemonic('t');
	    jchbxPanel3CLToPL.setBackground(null);
	    jchbxPanel3CLToPL.setOpaque(false);
		jchbxPanel3CLToPL.setSelected(true);
		jchbxPanel3CLToPL.setEnabled(true);
		jchbxPanel3CLToPL.setFocusPainted(true);
		jchbxPanel3CLToPL.addItemListener(new toggleCheckBoxListener());
	    
		jchbxPanel3Sharkpool = new JCheckBox("Sharkpool");
		jchbxPanel3Sharkpool.setBounds(1050, 365, 150, 20);
		jchbxPanel3Sharkpool.setFont(new Font(fontSpecialFamily,Font.PLAIN,fontSize-1));
		jchbxPanel3Sharkpool.setMnemonic('P');
		jchbxPanel3Sharkpool.setBackground(null);
		jchbxPanel3Sharkpool.setOpaque(false);
		jchbxPanel3Sharkpool.setSelected(true);
		jchbxPanel3Sharkpool.setEnabled(true);
		jchbxPanel3Sharkpool.setFocusPainted(true);
		jchbxPanel3Sharkpool.addItemListener(new toggleCheckBoxListener());
	    
	    panel3Parent = new GradientPanel2();
	    panel3Parent.setBackground(new Color(250,250,250,0));
	    panel3Parent.setLayout(null);
	    panel3Parent.setBounds(PAN_X_POSITION, PAN_Y_POSITION+(7*PAN_INCREMENT)+47, PAN_WIDTH+(18*PAN_INCREMENT)+25, PAN_HEIGHT+70+125);

		panel3 = new GradientPanel();
	    panel3.setBackground(new Color(250,250,250,0));
	    panel3.setBounds(10, 10, 1300, 530);
	    panel3Parent.add(panel3);
	    
	    sep1 = new JSeparator();
	    sep1.setBounds(0, 95,1360,2);
	    mainPanel.add(sep1);

	    sep11 = new JSeparator();
	    sep11.setBounds(1, 165,1360,2);
	    mainPanel.add(sep11);
	    
	    sep2 = new JSeparator();
	    sep2.setBounds(0, 360,1360,2);

	    sep3 = new JSeparator();
	    sep3.setBounds(0, 392,1360,2);

	    mainPanel.add(mainScrollPane);
        jlDScreenImage1 = new JLabel(dScreenImage1);
        jlDScreenImage1.setBounds(0, 0, 300, 300);
        jlDScreenImage1.setOpaque(false);
        compoPanel.setBackground(Color.WHITE);
        compoPanel.add(jlDScreenImage1);
        
		jlWhatIsItTitle = new JLabel("About TBM/VBM Scorecard");
		jlWhatIsItTitle.setBackground(null);
		jlWhatIsItTitle.setForeground(Color.DARK_GRAY);
		jlWhatIsItTitle.setFont(new Font("Arial",Font.BOLD,16));
		jlWhatIsItTitle.setBounds(330,40,500,50);
		compoPanel.add(jlWhatIsItTitle);

		jtaWhatIsIt = new JTextArea();
		jtaWhatIsIt.setBackground(null);
		jtaWhatIsIt.setForeground(Color.DARK_GRAY);
		jtaWhatIsIt.setFont(new Font("Arial",Font.PLAIN,15));
		jtaWhatIsIt.setBounds(330, 80, 800, 55);
		jtaWhatIsIt.setText("TBM/VBM Dashboard is a Performance Measuring Dashboard that "
				+ "has a set of Metrics showing PL unique partners' technology coverage, sales achievement, "
				+ "customer penentration, technology penetration, discounts offered in architecture level "
				+ "and yields...");
		jtaWhatIsIt.setLineWrap(true);
		jtaWhatIsIt.setWrapStyleWord(true);
		jtaWhatIsIt.setEditable(false);
		jtaWhatIsIt.setOpaque(false);
        compoPanel.add(jtaWhatIsIt);
	    
		jbWhatIsItReadMore = new JButton("Learn More");
		jbWhatIsItReadMore.setForeground(new Color(80,150,255));
		jbWhatIsItReadMore.setBackground(null);
		jbWhatIsItReadMore.setFont(new Font("Arial",Font.PLAIN,13));
		jbWhatIsItReadMore.setBorder(null);
		jbWhatIsItReadMore.setHorizontalAlignment(SwingConstants.LEFT);
		jbWhatIsItReadMore.setBounds(330, 135, 80, 20);
		jbWhatIsItReadMore.setFocusPainted(false);
		jbWhatIsItReadMore.addActionListener(new WhatIsItListener());
		compoPanel.add(jbWhatIsItReadMore);

		
		
        jlDScreenImage2 = new JLabel(dScreenImage2);
        jlDScreenImage2.setBounds(1100, 230, 200, 300);
        jlDScreenImage2.setOpaque(false);
        compoPanel.setBackground(Color.WHITE);
        compoPanel.add(jlDScreenImage2);
        
		jlHowItWorksTitle = new JLabel("How TBM/VBM Scorecard Works?");
		jlHowItWorksTitle.setBackground(null);
		jlHowItWorksTitle.setForeground(Color.DARK_GRAY);
		jlHowItWorksTitle.setFont(new Font("Arial",Font.BOLD,16));
		jlHowItWorksTitle.setBounds(330,260,500,50);
		compoPanel.add(jlHowItWorksTitle);

		jtaHowItWorks = new JTextArea();
		jtaHowItWorks.setBackground(null);
		jtaHowItWorks.setForeground(Color.DARK_GRAY);
		jtaHowItWorks.setFont(new Font("Arial",Font.PLAIN,15));
		jtaHowItWorks.setBounds(330, 300, 800, 200);
		jtaHowItWorks.setText("Depending on the login, the selection components (Combo & List boxes) seen above "
				+ "will list as to which node/partner \nyou have access to... Upon the partner selection the dashboard "
				+ "will be turned on. In the other metrics panel\n you have two options to see the metric changes.\n\n"
				+ "1. Details for only the assigned accounts.\n\n"
				+ "   - you can subset assigned accounts in to Partner Preferred, Partner Develop,\n"
				+ "     Big Bet and CL to PL.\n\n"
				+ "2. Details for All Accounts.");
		jtaHowItWorks.setLineWrap(true);
		jtaHowItWorks.setWrapStyleWord(true);
		jtaHowItWorks.setEditable(false);
		jtaHowItWorks.setOpaque(false);
        compoPanel.add(jtaHowItWorks);

		infoPanel = new GradientPanel();
		infoPanel.setBackground(Color.LIGHT_GRAY);
		infoPanel.setBounds(10, 300, 200, 200);
		infoPanel.setLayout(null);
        compoPanel.add(infoPanel);

        
        jtaInfo = new JTextArea();
        jtaInfo.setBackground(null);
        jtaInfo.setForeground(Color.RED);
        jtaInfo.setFont(new Font("Verdana",Font.ITALIC,14));
        jtaInfo.setBounds(25, 25, 160, 175);
        jtaInfo.setText(GeneralConstants.TBMVBM_INFO);
        jtaInfo.setLineWrap(true);
        jtaInfo.setWrapStyleWord(true);
        jtaInfo.setEditable(false);
        jtaInfo.setOpaque(false);
        infoPanel.add(jtaInfo);
        
        
	    repaint();
	    revalidate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
	    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
//	private class viewToggleListener implements ItemListener {
	private class viewToggleListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
    	   try {
	   		    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		       if (e.getSource() == viewToggle) {
		    	   if (isToggleOn) {
			   		    jchbxPanel3Sharkpool.setSelected(true);
			   		    compoPanel.add(jchbxPanel3Sharkpool);
			    		dataType = "All";
						isParPre = jchbxPanel3PartnerPreferred.isSelected();
						isParDev = jchbxPanel3PartnerDevelop.isSelected();
						isBB = jchbxPanel3BigBet.isSelected();
						isCLPL = jchbxPanel3CLToPL.isSelected();
						isShark = jchbxPanel3Sharkpool.isSelected();
			        	setPanelComponentsAssignment(true);
			        	viewToggle.setText("Overall Accounts View - ON");
			        	viewToggle.setForeground(Color.BLACK);
			        	isToggleOn = false;
			        	isSharkCheckBoxVisible = true;
		    	   } else {
			   		    compoPanel.remove(jchbxPanel3Sharkpool);
			    		dataType = "Net";
						isParPre = jchbxPanel3PartnerPreferred.isSelected();
						isParDev = jchbxPanel3PartnerDevelop.isSelected();
						isBB = jchbxPanel3BigBet.isSelected();
						isCLPL = jchbxPanel3CLToPL.isSelected();
						isShark = false;
			        	setPanelComponentsAssignment(false);
			        	viewToggle.setText("Overall Accounts View - OFF");
			        	viewToggle.setForeground(Color.RED);
			        	isToggleOn = true;
			        	isSharkCheckBoxVisible = false;
		    	   }
		        }
        	} catch (Exception ex) {
        		ex.printStackTrace();
        	} finally {
    		    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        	}
	    }
	}

		private void setPanel3SideHeaders() {
			final int MT_X_POSITION_Q1 = 350;
			final int MT_WIDTH_Q1 = 50;
			final int MT_Y_POSITION_Q1 = 3+30;
			final int MT_HEIGHT_Q1 = 50;
			
			final int P_X_POSITION = 10;
			final int P_WIDTH = 300;
			final int P_Y_POSITION = 45+30;
			final int P_HEIGHT = 30;
			
			final int INCREMENT_PTOP = 35;
			
			final int JL_X_POSITION = 5;
			final int JL_WIDTH = 280;
			final int JL_Y_POSITION = 3;
			final int JL_HEIGHT = 25;
			
			final int M_SEP_X_POSITION = 325;
			final int M_SEP_WIDTH = 2;
			final int M_SEP_Y_POSITION = 50+30;
			final int M_SEP_HEIGHT = 305;

			final int INCREMENT_M_SEP_QTOQ = 135;

			jlCWPShare = new JLabel();
			jlCWPShare.setForeground(Color.RED);
			
			ComponentHelper.addMyToolTip(jlCWPShare, 
					"Share on CWP: " + ComponentHelper.getCWPShareHeader(ytdAchievement[0][0], ytdCWP[0][0], ytdAOC[0][0]/100), 14,  
					5, 5, 500, 50, panel3);
			

			jlOtherMetricsTitle = new JLabel("Other Metrics");
			jlOtherMetricsTitle.setBackground(null);
			jlOtherMetricsTitle.setForeground(Color.DARK_GRAY);
			jlOtherMetricsTitle.setFont(new Font("Arial",Font.BOLD,20));
			jlOtherMetricsTitle.setBounds(600,5,400,25);
			jlOtherMetricsTitle.setOpaque(false);
			panel3.add(jlOtherMetricsTitle);

			panel3Sep = new JSeparator(JSeparator.HORIZONTAL);
			panel3Sep.setBounds(5, 40,1320,2);
			panel3.add(panel3Sep);
			
		    metricSepQ1 = new JSeparator(JSeparator.VERTICAL);
		    metricSepQ1.setBounds(M_SEP_X_POSITION,M_SEP_Y_POSITION,M_SEP_WIDTH,M_SEP_HEIGHT+150);
		    panel3.add(metricSepQ1);

		    metricSepQ2 = new JSeparator(JSeparator.VERTICAL);
		    metricSepQ2.setBounds(M_SEP_X_POSITION+INCREMENT_M_SEP_QTOQ,M_SEP_Y_POSITION,M_SEP_WIDTH,M_SEP_HEIGHT+150);
		    panel3.add(metricSepQ2);
		   
		    metricSepH1 = new JSeparator(JSeparator.VERTICAL);
		    metricSepH1.setBounds(M_SEP_X_POSITION+(2*INCREMENT_M_SEP_QTOQ),M_SEP_Y_POSITION,M_SEP_WIDTH,M_SEP_HEIGHT+150);
		    panel3.add(metricSepH1);

		    metricSepQ3 = new JSeparator(JSeparator.VERTICAL);
		    metricSepQ3.setBounds(M_SEP_X_POSITION+(3*INCREMENT_M_SEP_QTOQ),M_SEP_Y_POSITION,M_SEP_WIDTH,M_SEP_HEIGHT+150);
		    panel3.add(metricSepQ3);

		    metricSepQ4 = new JSeparator(JSeparator.VERTICAL);
		    metricSepQ4.setBounds(M_SEP_X_POSITION+(4*INCREMENT_M_SEP_QTOQ),M_SEP_Y_POSITION,M_SEP_WIDTH,M_SEP_HEIGHT+150);
		    panel3.add(metricSepQ4);

		    metricSepH2 = new JSeparator(JSeparator.VERTICAL);
		    metricSepH2.setBounds(M_SEP_X_POSITION+(5*INCREMENT_M_SEP_QTOQ),M_SEP_Y_POSITION,M_SEP_WIDTH,M_SEP_HEIGHT+150);
		    panel3.add(metricSepH2);

		    metricSepYTD = new JSeparator(JSeparator.VERTICAL);
		    metricSepYTD.setBounds(M_SEP_X_POSITION+(6*INCREMENT_M_SEP_QTOQ),M_SEP_Y_POSITION,M_SEP_WIDTH,M_SEP_HEIGHT+150);
		    panel3.add(metricSepYTD);

		    
			jlMetricTitleQ1 = new JLabel("Q1");
			jlMetricTitleQ1.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+10));
			jlMetricTitleQ1.setBackground(Color.WHITE);
			jlMetricTitleQ1.setBounds(MT_X_POSITION_Q1, MT_Y_POSITION_Q1, MT_WIDTH_Q1, MT_HEIGHT_Q1);
			panel3.add(jlMetricTitleQ1);

			jlMetricTitleQ2 = new JLabel("Q2");
			jlMetricTitleQ2.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+10));
			jlMetricTitleQ2.setBackground(Color.WHITE);
			jlMetricTitleQ2.setBounds(MT_X_POSITION_Q1+INCREMENT_M_SEP_QTOQ, MT_Y_POSITION_Q1, MT_WIDTH_Q1, MT_HEIGHT_Q1);
			panel3.add(jlMetricTitleQ2);

			jlMetricTitleH1 = new JLabel("H1");
			jlMetricTitleH1.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+10));
			jlMetricTitleH1.setBackground(Color.WHITE);
			jlMetricTitleH1.setBounds(MT_X_POSITION_Q1+(2*INCREMENT_M_SEP_QTOQ), MT_Y_POSITION_Q1, MT_WIDTH_Q1, MT_HEIGHT_Q1);
			panel3.add(jlMetricTitleH1);

			jlMetricTitleQ3 = new JLabel("Q3");
			jlMetricTitleQ3.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+10));
			jlMetricTitleQ3.setBackground(Color.WHITE);
			jlMetricTitleQ3.setBounds(MT_X_POSITION_Q1+(3*INCREMENT_M_SEP_QTOQ), MT_Y_POSITION_Q1, MT_WIDTH_Q1, MT_HEIGHT_Q1);
			panel3.add(jlMetricTitleQ3);

			jlMetricTitleQ4 = new JLabel("Q4");
			jlMetricTitleQ4.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+10));
			jlMetricTitleQ4.setBackground(Color.WHITE);
			jlMetricTitleQ4.setBounds(MT_X_POSITION_Q1+(4*INCREMENT_M_SEP_QTOQ), MT_Y_POSITION_Q1, MT_WIDTH_Q1, MT_HEIGHT_Q1);
			panel3.add(jlMetricTitleQ4);

			jlMetricTitleH2 = new JLabel("H2");
			jlMetricTitleH2.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+10));
			jlMetricTitleH2.setBackground(Color.WHITE);
			jlMetricTitleH2.setBounds(MT_X_POSITION_Q1+(5*INCREMENT_M_SEP_QTOQ), MT_Y_POSITION_Q1, MT_WIDTH_Q1, MT_HEIGHT_Q1);
			panel3.add(jlMetricTitleH2);

			jlMetricTitleYTD = new JLabel("YTD");
			jlMetricTitleYTD.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+10));
			jlMetricTitleYTD.setBackground(Color.WHITE);
			jlMetricTitleYTD.setBounds(MT_X_POSITION_Q1+(6*INCREMENT_M_SEP_QTOQ), MT_Y_POSITION_Q1, MT_WIDTH_Q1, MT_HEIGHT_Q1);
			panel3.add(jlMetricTitleYTD);
			
			
			plTotalAccounts = new GradientPanel();
			plTotalAccounts.setBackground(Color.BLUE);
			plTotalAccounts.setBounds(P_X_POSITION, P_Y_POSITION, P_WIDTH, P_HEIGHT);
			panel3.add(plTotalAccounts);
			
			jlTotalAccounts = new JLabel("Total Accounts");
			jlTotalAccounts.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlTotalAccounts.setBackground(Color.white);
			jlTotalAccounts.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plTotalAccounts.add(jlTotalAccounts);

			plAccountPenetration = new GradientPanel();
			plAccountPenetration.setBackground(Color.BLUE);
			plAccountPenetration.setBounds(P_X_POSITION, P_Y_POSITION+INCREMENT_PTOP, P_WIDTH, P_HEIGHT);
			panel3.add(plAccountPenetration);
			
			jlAccountPenetration = new JLabel("Account Penetration");
			jlAccountPenetration.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlAccountPenetration.setBackground(Color.white);
			jlAccountPenetration.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plAccountPenetration.add(jlAccountPenetration);

			plTechPenetration = new GradientPanel();
			plTechPenetration.setBackground(Color.BLUE);
			plTechPenetration.setBounds(P_X_POSITION, P_Y_POSITION+(2*INCREMENT_PTOP), P_WIDTH, P_HEIGHT);
			panel3.add(plTechPenetration);
			
			jlTechPenetration = new JLabel("Tech. Penetration");
			jlTechPenetration.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlTechPenetration.setBackground(Color.white);
			jlTechPenetration.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plTechPenetration.add(jlTechPenetration);
			
			plBNDiscount = new GradientPanel();
			plBNDiscount.setBackground(Color.BLUE);
			plBNDiscount.setBounds(P_X_POSITION, P_Y_POSITION+(3*INCREMENT_PTOP), P_WIDTH, P_HEIGHT);
			panel3.add(plBNDiscount);
			
			jlBNDiscount = new JLabel("Discount - ENT. NetWorks");
			jlBNDiscount.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlBNDiscount.setBackground(Color.white);
			jlBNDiscount.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plBNDiscount.add(jlBNDiscount);
			

			plSecDiscount = new GradientPanel();
			plSecDiscount.setBackground(Color.BLUE);
			plSecDiscount.setBounds(P_X_POSITION, P_Y_POSITION+(4*INCREMENT_PTOP), P_WIDTH, P_HEIGHT);
			panel3.add(plSecDiscount);
			
			jlSecDiscount = new JLabel("Discount - Security");
			jlSecDiscount.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlSecDiscount.setBackground(Color.white);
			jlSecDiscount.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plSecDiscount.add(jlSecDiscount);
			
			
			plCollDiscount = new GradientPanel();
			plCollDiscount.setBackground(Color.BLUE);
			plCollDiscount.setBounds(P_X_POSITION, P_Y_POSITION+(5*INCREMENT_PTOP), P_WIDTH, P_HEIGHT);
			panel3.add(plCollDiscount);
			
			jlCollDiscount = new JLabel("Discount - Collaboration");
			jlCollDiscount.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlCollDiscount.setBackground(Color.white);
			jlCollDiscount.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plCollDiscount.add(jlCollDiscount);

			plDCVDiscount = new GradientPanel();
			plDCVDiscount.setBackground(Color.BLUE);
			plDCVDiscount.setBounds(P_X_POSITION, P_Y_POSITION+(6*INCREMENT_PTOP), P_WIDTH, P_HEIGHT);
			panel3.add(plDCVDiscount);
			
			jlDCVDiscount = new JLabel("Discount - DCV");
			jlDCVDiscount.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlDCVDiscount.setBackground(Color.white);
			jlDCVDiscount.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plDCVDiscount.add(jlDCVDiscount);
			
			plOverallDiscount = new GradientPanel();
			plOverallDiscount.setBackground(Color.BLUE);
			plOverallDiscount.setBounds(P_X_POSITION, P_Y_POSITION+(7*INCREMENT_PTOP), P_WIDTH, P_HEIGHT);
			panel3.add(plOverallDiscount);
			
			jlOverallDiscount = new JLabel("Discount - Overall");
			jlOverallDiscount.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlOverallDiscount.setBackground(Color.white);
			jlOverallDiscount.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plOverallDiscount.add(jlOverallDiscount);

			plAvgYldPerAccounts = new GradientPanel();
			plAvgYldPerAccounts.setBackground(Color.BLUE);
			plAvgYldPerAccounts.setBounds(P_X_POSITION, P_Y_POSITION+(8*INCREMENT_PTOP), P_WIDTH, P_HEIGHT);
			panel3.add(plAvgYldPerAccounts);
			
			jlAvgYldPerAccounts = new JLabel("Avg. Yield/Accounts");
			jlAvgYldPerAccounts.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlAvgYldPerAccounts.setBackground(Color.white);
			jlAvgYldPerAccounts.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plAvgYldPerAccounts.add(jlAvgYldPerAccounts);
			
			plAvgYldPerOpp = new GradientPanel();
			plAvgYldPerOpp.setBackground(Color.BLUE);
			plAvgYldPerOpp.setBounds(P_X_POSITION, P_Y_POSITION+(9*INCREMENT_PTOP), P_WIDTH, P_HEIGHT);
			panel3.add(plAvgYldPerOpp);
			
			jlAvgYldPerOpp = new JLabel("Avg. Yield/Opportunities");
			jlAvgYldPerOpp.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlAvgYldPerOpp.setBackground(Color.white);
			jlAvgYldPerOpp.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plAvgYldPerOpp.add(jlAvgYldPerOpp);

			plAchievement = new GradientPanel();
			plAchievement.setBackground(Color.BLUE);
			plAchievement.setBounds(P_X_POSITION, P_Y_POSITION+(10*INCREMENT_PTOP), P_WIDTH, P_HEIGHT);
			panel3.add(plAchievement);
			
			jlAchievement = new JLabel("Achievement (in Thou. USD)");
			jlAchievement.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlAchievement.setBackground(Color.white);
			jlAchievement.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plAchievement.add(jlAchievement);

			plCWP = new GradientPanel();
			plCWP.setBackground(Color.BLUE);
			plCWP.setBounds(P_X_POSITION, P_Y_POSITION+(11*INCREMENT_PTOP), P_WIDTH, P_HEIGHT);
			panel3.add(plCWP);
			
			jlCWP = new JLabel("CWP (in Thou. USD)");
			jlCWP.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlCWP.setBackground(Color.white);
			jlCWP.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plCWP.add(jlCWP);

			plAOC = new GradientPanel();
			plAOC.setBackground(Color.BLUE);
			plAOC.setBounds(P_X_POSITION, P_Y_POSITION+(12*INCREMENT_PTOP), P_WIDTH, P_HEIGHT);
			panel3.add(plAOC);
			
			jlAOC = new JLabel("Achievement over CWP");
			jlAOC.setFont(new Font(fontSpecialFamily,Font.BOLD,fontSize+5));
			jlAOC.setBackground(Color.white);
			jlAOC.setBounds(JL_X_POSITION, JL_Y_POSITION, JL_WIDTH, JL_HEIGHT);
			plAOC.add(jlAOC);

		}

		private class toggleCheckBoxListener implements ItemListener {
		    @SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
		    public void itemStateChanged(ItemEvent event)
		    {
			    try {
				    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				    checkDisplayAlgorithmForOptionsNet(true);
					isParPre = jchbxPanel3PartnerPreferred.isSelected();
					isParDev = jchbxPanel3PartnerDevelop.isSelected();
					isBB = jchbxPanel3BigBet.isSelected();
					isCLPL = jchbxPanel3CLToPL.isSelected();
					if (isSharkCheckBoxVisible) {
						isShark = jchbxPanel3Sharkpool.isSelected();
					} else {
						isShark = false;
					}
			    	setPanelComponentsAssignment(true);
			    } catch (Exception ex) {
			    	ex.printStackTrace();
			    } finally {
				    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			    }
			    
		    }
		    
		}
    	
		

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void setFrameComponentsReady() throws SQLException {
			try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			int FR_COMP_X_POSITION = 10;
			int FR_COMP_WIDTH = 80;
			int FR_COMP_Y_POSITION = 100;
			int FR_COMP_HEIGHT = 25;
			int FR_COMP_INCREMENT = 51;
			int tempCount;
		    jlXxmOption = new JLabel();
		    jlXxmOption.setText("XXM Option: ");
		    jlXxmOption.setBounds(FR_COMP_X_POSITION,FR_COMP_Y_POSITION,FR_COMP_WIDTH,FR_COMP_HEIGHT);
		    jlXxmOption.setFont(new Font(fontFamily,Font.BOLD,fontSize));
		    mainPanel.add(jlXxmOption);

		    if (DesktopPrimaryScreen.role.equals("HEAD") || DesktopPrimaryScreen.role.equals("ADMIN")) {
			    resultsSalesList =  salesListQueries.getSAType();
			    tempCount = resultsSalesList.size();
			    
			    tempArrayList = new ArrayList<String>();
			    if (tempCount > 1) tempArrayList.add("ALL");
			    
				    for (int i = 0; i < tempCount; i++) {
				    	currentSalesListEntry = resultsSalesList.get(i);
				    	tempArrayList.add(currentSalesListEntry.getSalesAgentType());
				    }
		    }  else {
		    		tempArrayList = new ArrayList<String>();
			    	userFillModel = new DefaultListModel();
				    try {
						resultsUserData =  userDataQueries.getUserNamebyReportingTo(DesktopPrimaryScreen.userID);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			    	
				     tempCount = resultsUserData.size();
					    for (int j = 0; j < tempCount; j++) {
					    	currentUserDataEntry = resultsUserData.get(j);
					    		if (!currentUserDataEntry.getUserName().equals("ALL")) {
					    			userFillModel.addElement(currentUserDataEntry.getUserName());
					    		}
					    }
			    	for (int l = 0; l < userFillModel.size(); l++) {
				    try {
						resultsSalesList =  salesListQueries.getSATypebyName((String)userFillModel.getElementAt(l));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				    
				     tempCount = resultsSalesList.size();
					    for (int k = 0; k < tempCount; k++) {
					    	currentSalesListEntry = resultsSalesList.get(k);
					    			if (!tempArrayList.contains(currentSalesListEntry.getSalesAgentType())) {
					    				tempArrayList.add(currentSalesListEntry.getSalesAgentType());
					    			}
					    }
			    	}
		    	} 
			    
		    	if (tempArrayList.size() == 0) {
				    try {
						resultsSalesList =  salesListQueries.getSATypebyName(DesktopPrimaryScreen.userID);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				     tempCount = resultsSalesList.size();
					    for (int j = 0; j < tempCount; j++) {
					    	currentSalesListEntry = resultsSalesList.get(j);
		    			    tempCount = resultsSalesList.size();
		    			    	if (tempCount > 1) tempArrayList.add("ALL");
			    			tempArrayList.add(currentSalesListEntry.getSalesAgentType());
					    }
		    	} else {
			    	tempCount = tempArrayList.size();
			    	if (!(tempCount == 1 || tempCount ==0 || 
			    			DesktopPrimaryScreen.role.equals("HEAD") || 
			    			DesktopPrimaryScreen.role.equals("ADMIN"))) {
			    		tempArrayList.add("ALL");
			    	} else if (!(tempCount == 1 || tempCount ==0 || 
			    			DesktopPrimaryScreen.role.equals("VM"))) {
			    		tempArrayList.add("VBM");
			    	
			    	}
		    	}

		    List<String> list = tempArrayList;
		    Collections.sort(list);
		    tempArrayList = (ArrayList<String>) list;
		    salesAgentType = new DefaultComboBoxModel (tempArrayList.toArray());
		    jcbxXxmOption = new JComboBox();
		    jcbxXxmOption.setModel(salesAgentType);
		    jcbxXxmOption.setMaximumRowCount(tempCount+1);
		    jcbxXxmOption.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
		    jcbxXxmOption.setBounds(FR_COMP_X_POSITION+FR_COMP_INCREMENT+22, FR_COMP_Y_POSITION, FR_COMP_WIDTH-20, FR_COMP_HEIGHT);
		    jcbxXxmOption.addItemListener(new xxmOptionListener());
		    jcbxXxmOption.setSelectedIndex(-1);
		    mainPanel.add(jcbxXxmOption);

		    jlEU = new JLabel();
		    jlEU.setText("Execution Unit: ");
		    jlEU.setFont(new Font(fontFamily,Font.BOLD,fontSize));
		    jlEU.setBounds(FR_COMP_X_POSITION+(2*FR_COMP_INCREMENT)+40, FR_COMP_Y_POSITION, FR_COMP_WIDTH+20, FR_COMP_HEIGHT);
		    mainPanel.add(jlEU);
			jcbxEU = new JComboBox();
		    jcbxEU.setMaximumRowCount(4);
		    jcbxEU.setSelectedIndex(-1);
		    jcbxEU.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
		    jcbxEU.setBounds(FR_COMP_X_POSITION+(4*FR_COMP_INCREMENT)+28, FR_COMP_Y_POSITION, FR_COMP_WIDTH-20, FR_COMP_HEIGHT);
		    mainPanel.add(jcbxEU);
		    jcbxEU.addItemListener(new euComboListener());

		    jlRegion = new JLabel();
		    jlRegion.setText("Region: ");
		    jlRegion.setBounds(FR_COMP_X_POSITION+(5*FR_COMP_INCREMENT)+48, FR_COMP_Y_POSITION, FR_COMP_WIDTH+FR_COMP_INCREMENT+20, FR_COMP_HEIGHT);
		    jlRegion.setFont(new Font(fontFamily,Font.BOLD,fontSize));
		    mainPanel.add(jlRegion);
		    jltRegion = new JList();
		    jltRegion.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    jltRegion.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
		    regionScrollPane = new JScrollPane(jltRegion, 
		     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    regionScrollPane.setBounds(FR_COMP_X_POSITION+(6*FR_COMP_INCREMENT)+45, FR_COMP_Y_POSITION, FR_COMP_WIDTH-20, FR_COMP_HEIGHT+35);
		    mainPanel.add(regionScrollPane);
		    jltRegion.addListSelectionListener(new regionListListener());

		    jlNode = new JLabel();
		    jlNode.setText("Node: ");
		    jlNode.setFont(new Font(fontFamily,Font.BOLD,fontSize));
		    jlNode.setBounds(FR_COMP_X_POSITION+(8*FR_COMP_INCREMENT)+15, FR_COMP_Y_POSITION, FR_COMP_WIDTH+FR_COMP_INCREMENT+20, FR_COMP_HEIGHT);
		    mainPanel.add(jlNode);

		    jltNode = new JList();
		    jltNode.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    jltNode.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
		    nodeScrollPane = new JScrollPane(jltNode,
		            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    nodeScrollPane.setBounds(FR_COMP_X_POSITION+(8*FR_COMP_INCREMENT)+55, FR_COMP_Y_POSITION, FR_COMP_WIDTH+(2*FR_COMP_INCREMENT)+20, FR_COMP_HEIGHT+35);
		    mainPanel.add(nodeScrollPane);
		    jltNode.addListSelectionListener(new nodeListListener());

		    
		    jlXxm = new JLabel();
		    jlXxm.setText("xxM: ");
		    jlXxm.setFont(new Font(fontFamily,Font.BOLD,fontSize));
		    jlXxm.setBounds(FR_COMP_X_POSITION+(12*FR_COMP_INCREMENT)+65, FR_COMP_Y_POSITION, FR_COMP_WIDTH+20, FR_COMP_HEIGHT);
		    mainPanel.add(jlXxm);

		    jltXxm = new JList();
		    jltXxm.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    jltXxm.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
		    xxmScrollPane = new JScrollPane(jltXxm,
		            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    xxmScrollPane.setBounds(FR_COMP_X_POSITION+(14*FR_COMP_INCREMENT), FR_COMP_Y_POSITION, FR_COMP_WIDTH+(2*FR_COMP_INCREMENT)+20, FR_COMP_HEIGHT+35);
		    mainPanel.add(xxmScrollPane);
		    jltXxm.addListSelectionListener(new xxmListListener());
		    
		    
		    jlPartner = new JLabel();
		    jlPartner.setText("Unique_Partners: ");
		    jlPartner.setFont(new Font(fontFamily,Font.BOLD,fontSize));
		    jlPartner.setBounds(FR_COMP_X_POSITION+(18*FR_COMP_INCREMENT)+10, FR_COMP_Y_POSITION, FR_COMP_WIDTH+FR_COMP_INCREMENT+20, FR_COMP_HEIGHT);
		    mainPanel.add(jlPartner);
		    
		    jltPartner = new JList();
		    jltPartner.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    jltPartner.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
		    partnerScrollPane = new JScrollPane(jltPartner,
		            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    partnerScrollPane.setBounds(FR_COMP_X_POSITION+(20*FR_COMP_INCREMENT)+20, FR_COMP_Y_POSITION, FR_COMP_WIDTH+(4*FR_COMP_INCREMENT)+20, FR_COMP_HEIGHT+35);
		    mainPanel.add(partnerScrollPane);
		    jltPartner.addListSelectionListener(new partnerListListener());
			}
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}


		private class BoxMouseListener  extends MouseAdapter{
			JLabel jlToolTip = new JLabel();
			int x=130, y=320, width=210, height=50;
			public void mouseEntered(MouseEvent e) {
				try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				if (e.getSource() == rsRedBox || e.getSource() == rsYellowBox || e.getSource() == rsGreenBox) {
				ComponentHelper.addMyToolTip(jlToolTip, 
						ComponentHelper.getMyToolTip(actualRS, thresholdRS) + 
						"; Point: " + new DecimalFormat("#.##").format(pointRS), 12,  
						x, y, width, height, panel1);
				}
				else if  (e.getSource() == secRedBox || e.getSource() == secYellowBox || e.getSource() == secGreenBox) {
					ComponentHelper.addMyToolTip(jlToolTip, 
							ComponentHelper.getMyToolTip(actualSec, thresholdSec) + 
							"; Point: " + new DecimalFormat("#.##").format(pointSec), 12,  
							x, y, width, height, panel1);
				}
				else if  (e.getSource() == ucsRedBox || e.getSource() == ucsYellowBox || e.getSource() == ucsGreenBox) {
					ComponentHelper.addMyToolTip(jlToolTip, 
							ComponentHelper.getMyToolTip(actualUCS, thresholdUCS) + 
							"; Point: " + new DecimalFormat("#.##").format(pointUCS), 12,  
							x, y, width, height, panel1);
				}
				else if  (e.getSource() == ucRedBox || e.getSource() == ucYellowBox || e.getSource() == ucGreenBox) {
					ComponentHelper.addMyToolTip(jlToolTip, 
							ComponentHelper.getMyToolTip(actualUC, thresholdUC) + 
							"; Point: " + new DecimalFormat("#.##").format(pointUC), 12, 
							x, y, width, height, panel1);
				}
				else if  (e.getSource() == dcsRedBox || e.getSource() == dcsYellowBox || e.getSource() == dcsGreenBox) {
					ComponentHelper.addMyToolTip(jlToolTip, 
							ComponentHelper.getMyToolTip(actualDCS, thresholdDCS) + 
							"; Point: " + new DecimalFormat("#.##").format(pointDCS), 12,  
							x, y, width, height, panel1);
				}
				else if  (e.getSource() == videoRedBox || e.getSource() == videoYellowBox || e.getSource() == videoGreenBox) {
					ComponentHelper.addMyToolTip(jlToolTip, 
							ComponentHelper.getMyToolTip(actualVideo, thresholdVideo) + 
							"; Point: " + new DecimalFormat("#.##").format(pointVideo), 12,  
							x, y, width, height, panel1);
				}
				else if  (e.getSource() == wlanRedBox || e.getSource() == wlanYellowBox || e.getSource() == wlanGreenBox) {
					ComponentHelper.addMyToolTip(jlToolTip, 
							ComponentHelper.getMyToolTip(actualWLAN, thresholdWLAN) + 
							"; Point: " + new DecimalFormat("#.##").format(pointWLAN), 12, 
							x, y, width, height, panel1);
				}
				} catch(Exception ex) {
					
				}finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		
			public void mouseExited(MouseEvent e) {
				try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				ComponentHelper.removeMyToolTip(jlToolTip, panel1);
				} catch (Exception ex) {
					
				}finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}

		private class ThermoChartPanelMouseListener implements ChartMouseListener {
			private JTextArea jtaToolTip = new JTextArea();
			public void chartMouseClicked(ChartMouseEvent event) {
				// No Actions
			}
			public void chartMouseMoved(ChartMouseEvent event) {
				try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					int x = event.getTrigger().getX();
					int y = event.getTrigger().getY();
					ChartEntity entity = event.getEntity();
					if (entity != null) {
					    if ((x >= 10 && x < 80) && (y >= 20 && y < 300)) {
							ComponentHelper.addMyLongerToolTip(jtaToolTip, 
									ComponentHelper.getMyThermoToolTip(pointRS, pointSec, 
											pointUCS, pointUC, pointDCS, pointVideo, pointWLAN,
											posibleTechPoints, techCoverage), 11, 
									130, 325, 250, 70, panel1); 
					    } else {
							ComponentHelper.removeMyLongerToolTip(jtaToolTip, panel1);
					    }
				}
				} catch(Exception ex) {
				} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}

		
		protected void setPanel1ComponentsReady(double cov, double rs, double sec, double ucs, 
				double uc, double dcs, double video, double wlan) {
			try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    ThermoMeter thermo = new ThermoMeter("");
		    therDataSet = new DefaultValueDataset(CalcHelper.round(cov * 100D, 1, BigDecimal.ROUND_HALF_UP));
		    JFreeChart therChart = thermo.createChart(therDataSet);
		    therChart.removeLegend();
		    therChart.setTitle("");
		    therChart.setPadding(new RectangleInsets(0D,5D,1D,200D));
		    ChartPanel therPanel = new ChartPanel(therChart);
		    therPanel.setLayout(null);
		    therPanel.setBackground(null);
		    therPanel.setOpaque(false);
		    therPanel.setBounds(0, 0, 280, 280);
		    therPanel.setDomainZoomable(true);
		    therPanel.addChartMouseListener(new ThermoChartPanelMouseListener());
		    panel11.add(therPanel);
		    
			jlTechnologyTitle = new JLabel("Technology Performance in %");
			jlTechnologyTitle.setBackground(null);
			jlTechnologyTitle.setForeground(Color.DARK_GRAY);
			jlTechnologyTitle.setFont(new Font("Arial",Font.BOLD,20));
			jlTechnologyTitle.setBounds(80,5,400,25);
			jlTechnologyTitle.setOpaque(false);
			panel1.add(jlTechnologyTitle);

			rsLabel = new JLabel();
		    rsLabel.setBackground(Color.BLACK);
		    rsLabel.setForeground(Color.WHITE);
		    rsLabel.setText("RS");
		    rsLabel.setBounds(10, 0, 20, 20);
		    rsLabel.setFont(new Font(fontFamily,Font.BOLD,fontSize+1));
		    
		    rsRedBox = new RedTrafficLight();
		    rsRedBox.setBackground(Color.BLACK);
		    rsRedBox.setBounds(130,90, 250, 22);
		    rsRedBox.addMouseListener(new BoxMouseListener());
		    rsRedBox.setLayout(null);

		    rsYellowBox = new YellowTrafficLight();
		    rsYellowBox.setBackground(Color.BLACK);
		    rsYellowBox.setBounds(130,90, 250, 22);
		    rsYellowBox.addMouseListener(new BoxMouseListener());
		    rsYellowBox.setLayout(null);

		    rsGreenBox = new GreenTrafficLight();
		    rsGreenBox.setBackground(Color.BLACK);
		    rsGreenBox.setBounds(130,90, 250, 22);
		    rsGreenBox.addMouseListener(new BoxMouseListener());
		    rsGreenBox.setLayout(null);

		    secLabel = new JLabel();
		    secLabel.setBackground(Color.BLACK);
		    secLabel.setForeground(Color.WHITE);
		    secLabel.setText("Sec");
		    secLabel.setBounds(10, 0, 25, 20);
		    secLabel.setFont(new Font(fontFamily,Font.BOLD,fontSize+1));
		    
		    secRedBox = new RedTrafficLight();
		    secRedBox.setBackground(Color.BLACK);
		    secRedBox.setBounds(130, 125, 250, 22);
		    secRedBox.addMouseListener(new BoxMouseListener());
		    secRedBox.setLayout(null);
		    
		    secYellowBox = new YellowTrafficLight();
		    secYellowBox.setBackground(Color.BLACK);
		    secYellowBox.setBounds(130, 125, 250, 22);
		    secYellowBox.addMouseListener(new BoxMouseListener());
		    secYellowBox.setLayout(null);
		    
		    secGreenBox = new GreenTrafficLight();
		    secGreenBox.setBackground(Color.BLACK);
		    secGreenBox.addMouseListener(new BoxMouseListener());
		    secGreenBox.setBounds(130, 125, 250, 22);
		    secGreenBox.setLayout(null);
		    
		    ucsLabel = new JLabel();
		    ucsLabel.setBackground(Color.BLACK);
		    ucsLabel.setForeground(Color.WHITE);
		    ucsLabel.setText("UCS");
		    ucsLabel.setBounds(10, 0, 25, 20);
		    ucsLabel.setFont(new Font(fontFamily,Font.BOLD,fontSize+1));
		    
		    ucsRedBox = new RedTrafficLight();
		    ucsRedBox.setBackground(Color.BLACK);
		    ucsRedBox.setBounds(130, 160, 250, 22);
		    ucsRedBox.addMouseListener(new BoxMouseListener());
		    ucsRedBox.setLayout(null);
		    
		    ucsYellowBox = new YellowTrafficLight();
		    ucsYellowBox.setBackground(Color.BLACK);
		    ucsYellowBox.setBounds(130, 160, 250, 22);
		    ucsYellowBox.addMouseListener(new BoxMouseListener());
		    ucsYellowBox.setLayout(null);
		    
		    ucsGreenBox = new GreenTrafficLight();
		    ucsGreenBox.setBackground(Color.BLACK);
		    ucsGreenBox.setBounds(130, 160, 250, 22);
		    ucsGreenBox.addMouseListener(new BoxMouseListener());
		    ucsGreenBox.setLayout(null);
		    
		    ucLabel = new JLabel();
		    ucLabel.setBackground(Color.BLACK);
		    ucLabel.setForeground(Color.WHITE);
		    ucLabel.setText("UC");
		    ucLabel.setBounds(10, 0, 25, 20);
		    ucLabel.setFont(new Font(fontFamily,Font.BOLD,fontSize+1));
		    
		    ucRedBox = new RedTrafficLight();
		    ucRedBox.setBackground(Color.BLACK);
		    ucRedBox.setBounds(130, 195, 250, 22);
		    ucRedBox.addMouseListener(new BoxMouseListener());
		    ucRedBox.setLayout(null);
		    
		    ucYellowBox = new YellowTrafficLight();
		    ucYellowBox.setBackground(Color.BLACK);
		    ucYellowBox.setBounds(130, 195, 250, 22);
		    ucYellowBox.addMouseListener(new BoxMouseListener());
		    ucYellowBox.setLayout(null);
		    
		    ucGreenBox = new GreenTrafficLight();
		    ucGreenBox.setBackground(Color.BLACK);
		    ucGreenBox.setBounds(130, 195, 250, 22);
		    ucGreenBox.addMouseListener(new BoxMouseListener());
		    ucGreenBox.setLayout(null);
		    
		    dcsLabel = new JLabel();
		    dcsLabel.setBackground(Color.BLACK);
		    dcsLabel.setForeground(Color.WHITE);
		    dcsLabel.setText("DCS");
		    dcsLabel.setBounds(10, 0, 25, 20);
		    dcsLabel.setFont(new Font(fontFamily,Font.BOLD,fontSize+1));
		    
		    dcsRedBox = new RedTrafficLight();
		    dcsRedBox.setBackground(Color.BLACK);
		    dcsRedBox.setBounds(130, 230, 250, 22);
		    dcsRedBox.addMouseListener(new BoxMouseListener());
		    dcsRedBox.setLayout(null);

		    dcsYellowBox = new YellowTrafficLight();
		    dcsYellowBox.setBackground(Color.BLACK);
		    dcsYellowBox.setBounds(130, 230, 250, 22);
		    dcsYellowBox.addMouseListener(new BoxMouseListener());
		    dcsYellowBox.setLayout(null);

		    dcsGreenBox = new GreenTrafficLight();
		    dcsGreenBox.setBackground(Color.BLACK);
		    dcsGreenBox.setBounds(130, 230, 250, 22);
		    dcsGreenBox.addMouseListener(new BoxMouseListener());
		    dcsGreenBox.setLayout(null);

		    videoLabel = new JLabel();
		    videoLabel.setBackground(Color.BLACK);
		    videoLabel.setForeground(Color.WHITE);
		    videoLabel.setText("Video");
		    videoLabel.setBounds(10, 0, 40, 20);
		    videoLabel.setFont(new Font(fontFamily,Font.BOLD,fontSize+1));
		    
		    videoRedBox = new RedTrafficLight();
		    videoRedBox.setBackground(Color.BLACK);
		    videoRedBox.setBounds(130, 265, 250, 22);
		    videoRedBox.addMouseListener(new BoxMouseListener());
		    videoRedBox.setLayout(null);
		    
		    videoYellowBox = new YellowTrafficLight();
		    videoYellowBox.setBackground(Color.BLACK);
		    videoYellowBox.setBounds(130, 265, 250, 22);
		    videoYellowBox.addMouseListener(new BoxMouseListener());
		    videoYellowBox.setLayout(null);
		    
		    videoGreenBox = new GreenTrafficLight();
		    videoGreenBox.setBackground(Color.BLACK);
		    videoGreenBox.setBounds(130, 265, 250, 22);
		    videoGreenBox.addMouseListener(new BoxMouseListener());
		    videoGreenBox.setLayout(null);
		    
		    wlanLabel = new JLabel();
		    wlanLabel.setBackground(Color.BLACK);
		    wlanLabel.setForeground(Color.WHITE);
		    wlanLabel.setText("WLAN");
		    wlanLabel.setBounds(10, 0, 60, 20);
		    wlanLabel.setFont(new Font(fontFamily,Font.BOLD,fontSize+1));
		    
		    wlanRedBox = new RedTrafficLight();
		    wlanRedBox.setBackground(Color.BLACK);
		    wlanRedBox.setBounds(130, 300, 250, 22);
		    wlanRedBox.addMouseListener(new BoxMouseListener());
		    wlanRedBox.setLayout(null);

		    wlanYellowBox = new YellowTrafficLight();
		    wlanYellowBox.setBackground(Color.BLACK);
		    wlanYellowBox.setBounds(130, 300, 250, 22);
		    wlanYellowBox.addMouseListener(new BoxMouseListener());
		    wlanYellowBox.setLayout(null);

		    wlanGreenBox = new GreenTrafficLight();
		    wlanGreenBox.setBackground(Color.BLACK);
		    wlanGreenBox.setBounds(130, 300, 250, 22);
		    wlanGreenBox.addMouseListener(new BoxMouseListener());
		    wlanGreenBox.setLayout(null);

		    
		    panel1.add(ComponentHelper.getTrafficSignal(rsGreenBox, rsYellowBox, rsRedBox, rsLabel, rs));
		    panel1.add(ComponentHelper.getTrafficSignal(secGreenBox, secYellowBox, secRedBox, secLabel, sec));
		    panel1.add(ComponentHelper.getTrafficSignal(ucsGreenBox, ucsYellowBox, ucsRedBox, ucsLabel, ucs));
		    panel1.add(ComponentHelper.getTrafficSignal(ucGreenBox, ucYellowBox, ucRedBox, ucLabel, uc));
		    panel1.add(ComponentHelper.getTrafficSignal(dcsGreenBox, dcsYellowBox, dcsRedBox, dcsLabel, dcs));
		    panel1.add(ComponentHelper.getTrafficSignal(videoGreenBox, videoYellowBox, videoRedBox, videoLabel, video));
		    panel1.add(ComponentHelper.getTrafficSignal(wlanGreenBox, wlanYellowBox, wlanRedBox, wlanLabel, wlan));
		    
			jlRedMark = new JLabel(redMark);
			jlRedMark.setBackground(null);
			jlRedMark.setBounds(130, 40, 20, 20);
			jlRedMark.setOpaque(false);
			panel1.add(jlRedMark);

			jlRedText = new JLabel(GeneralConstants.RED_TEXT);
			jlRedText.setBackground(null);
			jlRedText.setBounds(152, 40, 40, 20);
			jlRedText.setForeground(Color.DARK_GRAY);
			jlRedText.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
			jlRedText.setOpaque(false);
			panel1.add(jlRedText);

			jlYellowMark = new JLabel(yellowMark);
			jlYellowMark.setBackground(null);
			jlYellowMark.setBounds(220, 40, 20, 20);
			jlYellowMark.setOpaque(false);
			panel1.add(jlYellowMark);

			jlYellowText = new JLabel(GeneralConstants.YELLOW_TEXT);
			jlYellowText.setBackground(null);
			jlYellowText.setBounds(242, 40, 70, 20);
			jlYellowText.setForeground(Color.DARK_GRAY);
			jlYellowText.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
			jlYellowText.setOpaque(false);
			panel1.add(jlYellowText);
		    
			jlGreenMark = new JLabel(greenMark);
			jlGreenMark.setBackground(null);
			jlGreenMark.setBounds(310, 40, 20, 20);
			jlGreenMark.setOpaque(false);
			panel1.add(jlGreenMark);
		    
			jlGreenText = new JLabel(GeneralConstants.GREEN_TEXT);
			jlGreenText.setBackground(null);
			jlGreenText.setBounds(332, 40, 40, 20);
			jlGreenText.setForeground(Color.DARK_GRAY);
			jlGreenText.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
			jlGreenText.setOpaque(false);
			panel1.add(jlGreenText);
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		private class WhatIsItListener implements ActionListener {

			private JLabel jlCoverage, jlSalesAchievement, jlOtherMetrics;
			private JTextArea jtaCoverage, jtaSalesAchievement, jtaOtherMetrics;		
			private BufferedImage logoImage;

			 public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jbWhatIsItReadMore) {
					try {
				        logoImage =  ImageIO.read(this.getClass().getResource(GeneralConstants.LOCAL_LOGO_IMAGE));
				    } catch (IOException ex) {
				        ex.printStackTrace();
				    }

				    jfWhatIsItReadMore = new JFrame();
					jfWhatIsItReadMore.setSize(700, 200);
					jfWhatIsItReadMore.setLocation(500, 250);
					jfWhatIsItReadMore.getContentPane().setBackground(Color.WHITE);;
					jfWhatIsItReadMore.setLayout(new GridLayout(3,1));
					jfWhatIsItReadMore.setUndecorated(false);
					jfWhatIsItReadMore.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					jfWhatIsItReadMore.setIconImage(logoImage);
					jfWhatIsItReadMore.setVisible(true);

					jlCoverage = new JLabel();
					jlCoverage.setBackground(null);
					jlCoverage.setForeground(Color.DARK_GRAY);
					jlCoverage.setFont(new Font("Arial",Font.BOLD,16));
					jlCoverage.setText("1. Performance\n");
					jlCoverage.setOpaque(false);
					jfWhatIsItReadMore.add(jlCoverage);

					jtaCoverage = new JTextArea();
					jtaCoverage.setBackground(null);
					jtaCoverage.setForeground(Color.DARK_GRAY);
					jtaCoverage.setFont(new Font("Arial",Font.PLAIN,15));
					jtaCoverage.setText("Shows you technology wise stand point with repect to the goal\n");
					jtaCoverage.setLineWrap(true);
					jtaCoverage.setWrapStyleWord(true);
					jtaCoverage.setEditable(false);
					jtaCoverage.setOpaque(false);
					jfWhatIsItReadMore.add(jtaCoverage);

					jlSalesAchievement = new JLabel();
					jlSalesAchievement.setBackground(null);
					jlSalesAchievement.setForeground(Color.DARK_GRAY);
					jlSalesAchievement.setFont(new Font("Arial",Font.BOLD,16));
					jlSalesAchievement.setText("2. Sales Achievement\n");
					jlSalesAchievement.setOpaque(false);
					jfWhatIsItReadMore.add(jlSalesAchievement);

					jtaSalesAchievement = new JTextArea();
					jtaSalesAchievement.setBackground(null);
					jtaSalesAchievement.setForeground(Color.DARK_GRAY);
					jtaSalesAchievement.setFont(new Font("Arial",Font.PLAIN,15));
					jtaSalesAchievement.setText("Shows you Quarter wise Sales Achievement with respect to Goal\n");
					jtaSalesAchievement.setLineWrap(true);
					jtaSalesAchievement.setWrapStyleWord(true);
					jtaSalesAchievement.setEditable(false);
					jtaSalesAchievement.setOpaque(false);
					jfWhatIsItReadMore.add(jtaSalesAchievement);


					jlOtherMetrics = new JLabel();
					jlOtherMetrics.setBackground(null);
					jlOtherMetrics.setForeground(Color.DARK_GRAY);
					jlOtherMetrics.setFont(new Font("Arial",Font.BOLD,16));
					jlOtherMetrics.setText("3. Other Metrics\n");
					jlOtherMetrics.setOpaque(false);
					jfWhatIsItReadMore.add(jlOtherMetrics);

					jtaOtherMetrics = new JTextArea();
					jtaOtherMetrics.setBackground(null);
					jtaOtherMetrics.setForeground(Color.DARK_GRAY);
					jtaOtherMetrics.setFont(new Font("Arial",Font.PLAIN,15));
					jtaOtherMetrics.setText("Shows you Quarter wise Account penetration, "
							+ "Technology penetration, Architecture wise Discount offered, "
							+ "Yield/Account and Yield/Opportunity\n");
					jtaOtherMetrics.setLineWrap(true);
					jtaOtherMetrics.setWrapStyleWord(true);
					jtaOtherMetrics.setEditable(false);
					jtaOtherMetrics.setOpaque(false);
					jfWhatIsItReadMore.add(jtaOtherMetrics);
					
				}
			}
		}

		
		protected void setCustomFontSize(int customFontSize) {
			fontSize = customFontSize;
		}

		protected void setCustomFontFamily(String customFontFamily) {
			fontFamily = customFontFamily;
		}

		protected void setCustomFontSpecialFamily(String customFontSpecialFamily) {
			fontSpecialFamily = customFontSpecialFamily;
		}


		private class euComboListener implements ItemListener {
			private String tString;
			private int tempCount;
		    @SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
		    public void itemStateChanged(ItemEvent event)  {
		    	try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    	   dummyModel = new DefaultListModel();
		    	   
		    	   dummyModel.removeAllElements();
		    	   jltNode.setModel(dummyModel);
		    	   jltXxm.setModel(dummyModel);
		    	   jltPartner.setModel(dummyModel);

		    	if (event.getStateChange() == ItemEvent.SELECTED) {
				    regionFillModel = new DefaultListModel ();
			    	   tString = (String)jcbxEU.getSelectedItem();
				    if (DesktopPrimaryScreen.region.equals("ALL") && tString.equals("ALL")) {
					    try {
							resultsUserData =  userDataQueries.getAllRegion();
						} catch (SQLException e) {
							e.printStackTrace();
						}
				    } else if (DesktopPrimaryScreen.region.equals("ALL") && (tString.equals("EU3") || 
				    		tString.equals("EU2") || tString.equals("EU1"))) {
					    try {
							resultsUserData =  userDataQueries.getRegionbyEU(tString);
						} catch (SQLException e) {
							e.printStackTrace();
						}
				    } else {
					    try {
							resultsUserData =  userDataQueries.getRegionbyName(DesktopPrimaryScreen.userID);
						} catch (SQLException e) {
							e.printStackTrace();
						}
				    }
				     int tempCount = resultsUserData.size();
					    for (int i = 0; i < tempCount; i++) {
					    	currentUserDataEntry = resultsUserData.get(i);
					    		if (currentUserDataEntry.getRegion().equals("ALL") || currentUserDataEntry.getRegion().equals("")) {
					    			
					    		} else {
					    			regionFillModel.addElement(currentUserDataEntry.getRegion());
					    		}
					    }
				    jltRegion.setModel(regionFillModel);
		        }
		    	} catch(Exception ex) {
		    		ex.printStackTrace();
		    	} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    	}
		    }
		}

		private class xxmOptionListener implements ItemListener {
			private String tString;
			private int tempCount;
		    @SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
		    public void itemStateChanged(ItemEvent event) {
		    	try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		       if (event.getStateChange() == ItemEvent.SELECTED) {
		    	   dummyModel = new DefaultListModel();
		    	   jcbxEU.removeAllItems();
		    	   
		    	   dummyModel.removeAllElements();
		    	   jltRegion.setModel(dummyModel);
		    	   jltNode.setModel(dummyModel);
		    	   jltXxm.setModel(dummyModel);
		    	   jltPartner.setModel(dummyModel);
		    	   
		   	    if (DesktopPrimaryScreen.role.equals("HEAD") || DesktopPrimaryScreen.role.equals("ADMIN")
		   	    	 || DesktopPrimaryScreen.role.equals("VM")) {
				    try {
						resultsUserData =  userDataQueries.getAllEU();
					    tempArrayList = new ArrayList<String>();
					    tempArrayList.add("ALL");
					} catch (SQLException e) {
						e.printStackTrace();
					}
			    } else {
				    try {
						resultsUserData =  userDataQueries.getEUbyName(DesktopPrimaryScreen.userID);
					    tempArrayList = new ArrayList<String>();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			    }
			      tempCount = resultsUserData.size();
				    for (int i = 0; i < tempCount; i++) {
				    	currentUserDataEntry = resultsUserData.get(i);
				    	if (!currentUserDataEntry.getEU().equals("ALL")) {
				    		tempArrayList.add(currentUserDataEntry.getEU());
				    	} 
				    		if (DesktopPrimaryScreen.role.equals("RM")) {
				    			
				    		}
				    }
			    eUNames = new DefaultComboBoxModel (tempArrayList.toArray());
				jcbxEU.setModel(eUNames);
				jcbxEU.setSelectedIndex(-1);
		        }
		    	} catch(Exception ex) {
		    		ex.printStackTrace();
		    	} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    	}
		    }
		}
		
		private class regionListListener implements ListSelectionListener {
		    private int tempCount;
		    private String tString1;
		    private String tString2;
		    private String tString3;
		    @SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
		    public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting() && !jltRegion.isSelectionEmpty()) {
			    	try {
				    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			    	   dummyModel = new DefaultListModel();
			    	   dummyModel.removeAllElements();
			    	   jltXxm.setModel(dummyModel);
			    	   jltPartner.setModel(dummyModel);
			    	@SuppressWarnings("rawtypes")
					ListModel model = jltRegion.getModel();
			        node = new DefaultListModel();
			        for (int i = 0; i < model.getSize(); i++){
			            if (jltRegion.isSelectedIndex(i)) {
			            	tString1 = (String)jcbxXxmOption.getSelectedItem();
			            	tString2 = (String)jcbxEU.getSelectedItem();
			            	tString3 = (String)model.getElementAt(i);
			            	if (DesktopPrimaryScreen.role.equals("HEAD") || DesktopPrimaryScreen.role.equals("ADMIN") 
			            			 || DesktopPrimaryScreen.role.equals("VM")) {
				            	try {
					            		resultsSalesList =  
						                    	salesListQueries.getSL6byRegion(tString3);
				    				} catch (SQLException ex) {
				    					ex.printStackTrace();
				    				}
				                	    for (int j = 0; j < resultsSalesList.size(); j++) {
				                	    	currentSalesListEntry = resultsSalesList.get(j);
				                	    	node.addElement(currentSalesListEntry.getSalesLevel6());
				                	    }
			            	} else if (DesktopPrimaryScreen.role.equals("RM") || DesktopPrimaryScreen.role.equals("TM")) {
			    		    	userFillModel = new DefaultListModel();
			    			    try {
			    					resultsUserData =  userDataQueries.getUserNamebyReportingTo(DesktopPrimaryScreen.userID);
			    				} catch (SQLException e1) {
			    					e1.printStackTrace();
			    				}
			    		    	
			    				    for (int j = 0; j < resultsUserData.size(); j++) {
			    				    	currentUserDataEntry = resultsUserData.get(j);
			    				    		if (!currentUserDataEntry.getUserName().equals("ALL")) {
			    				    			userFillModel.addElement(currentUserDataEntry.getUserName());
			    				    		}
			    				    }
			    		    	for (int l = 0; l < userFillModel.size(); l++) {
			    			    try {
			    			    	resultsSalesList = (tString1.equals("ALL")) ? 
			    							salesListQueries.getSL6byUserName((String)userFillModel.getElementAt(l)) :
			    								salesListQueries.getSL6byXxmOptionandUserName(tString1, 
			    										(String)userFillModel.getElementAt(l));
			    				} catch (SQLException e1) {
			    					e1.printStackTrace();
			    				}
			    			    
			    				    for (int k = 0; k < resultsSalesList.size(); k++) {
			    				    	currentSalesListEntry = resultsSalesList.get(k);
			    				    			if (!node.contains(currentSalesListEntry.getSalesLevel6())) {
			    				    				node.addElement(currentSalesListEntry.getSalesLevel6());
			    				    			}
			    				    }
			    		    	}
			    		    } else {
			    			    try {
			    					resultsSalesList =  salesListQueries.getSL6byUserName(DesktopPrimaryScreen.userID);
			    				} catch (SQLException e1) {
			    					e1.printStackTrace();
			    				}
			    				    for (int j = 0; j < resultsSalesList.size(); j++) {
			    				    	currentSalesListEntry = resultsSalesList.get(j);
			    				    			if (!node.contains(currentSalesListEntry.getSalesLevel6())) {
			    				    			node.addElement(currentSalesListEntry.getSalesLevel6());
			    				    			}
			    				    }
			    		    }
			            	
			            }
			        }
			    	jltNode.setModel(node);
			        getContentPane().revalidate();
			        getContentPane().repaint();
			    	} catch (Exception ex) {
			    		ex.printStackTrace();
			    	} finally {
				    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			    	}
				}
		    }
		}

		private class nodeListListener implements ListSelectionListener {
		    private int tempCount;
		    private String tString1;
		    private String tString2;
		    private String tString3;
		    @SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
		    public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting() && !jltNode.isSelectionEmpty()) {
			    	try {
				    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			    	   dummyModel = new DefaultListModel();
			    	   dummyModel.removeAllElements();
			    	   jltPartner.setModel(dummyModel);
			        @SuppressWarnings("rawtypes")
					ListModel model = jltNode.getModel();
			        xxM = new DefaultListModel();
			    	userFillModel = new DefaultListModel();
			        for (int i = 0; i < model.getSize(); i++){
			            if (jltNode.isSelectedIndex(i)) {
			            	tString1 = (String)jcbxXxmOption.getSelectedItem();
			            	tString2 = (String)jcbxEU.getSelectedItem();
			            	tString3 = (String)model.getElementAt(i);
			            	if (DesktopPrimaryScreen.role.equals("HEAD") || DesktopPrimaryScreen.role.equals("ADMIN") 
			            			 || DesktopPrimaryScreen.role.equals("VM") ) {
				            	try {
				            		if (tString1.equals("ALL")) {
					            		resultsSalesList =  
						                    	salesListQueries.getXxmbyNode(tString3);
				            		} else {
					            		resultsSalesList =  
						                    	salesListQueries.getXxmbySATypeandNode(tString1, tString3);
				            		}
				    				} catch (SQLException ex) {
				    					ex.printStackTrace();
				    				}
				                	    for (int j = 0; j < resultsSalesList.size(); j++) {
				                	    	currentSalesListEntry = resultsSalesList.get(j);
				                	    	xxM.addElement(currentSalesListEntry.getSalesAgentName());
				                	    }
			            	} else if (DesktopPrimaryScreen.role.equals("RM") || DesktopPrimaryScreen.role.equals("TM")) {
			    		    	try {
			    					resultsUserData =  userDataQueries.getUserNamebyReportingTo(DesktopPrimaryScreen.userID);
			    				} catch (SQLException e1) {
			    					e1.printStackTrace();
			    				}
			    		    	
			    			     tempCount = resultsUserData.size();
			    				    for (int j = 0; j < tempCount; j++) {
			    				    	currentUserDataEntry = resultsUserData.get(j);
			    				    			userFillModel.addElement(currentUserDataEntry.getUserName());
			    				    }
			    		    	for (int l = 0; l < userFillModel.size(); l++) {
			    			    try {
			    			    	if (tString1.equals("ALL")) {
			    			    		resultsSalesList =  
			    			    				salesListQueries.getXxmbyNodeandName(tString3, 
			    			    						(String)userFillModel.getElementAt(l));	
			    			    	} else {
			    			    		resultsSalesList =  
			    			    				salesListQueries.getXxmbySATypeNodeandName(tString1, tString3, 
			    			    						(String)userFillModel.getElementAt(l));	
			    			    	}
			    				} catch (SQLException e1) {
			    					e1.printStackTrace();
			    				}
			    				    for (int k = 0; k < resultsSalesList.size(); k++) {
			    				    	currentSalesListEntry = resultsSalesList.get(k);
			    				    			if (!xxM.contains(currentSalesListEntry.getSalesAgentName())) {
		 					    				xxM.addElement(currentSalesListEntry.getSalesAgentName());
			    				    			}
			    				    }
			    		    	}
			    		    } else {
			    		    	if (userFillModel.size() == 0) {
				    			    try {
				    					resultsSalesList =  salesListQueries.getXxmbyNodeandName(tString3, DesktopPrimaryScreen.userID);
				    				} catch (SQLException e1) {
				    					e1.printStackTrace();
				    				}
				    			     tempCount = resultsSalesList.size();
				    				    for (int j = 0; j < tempCount; j++) {
				    				    	currentSalesListEntry = resultsSalesList.get(j);
				    				    			if (!xxM.contains(currentSalesListEntry.getSalesAgentName())) {
				    				    			xxM.addElement(currentSalesListEntry.getSalesAgentName());
				    				    			}
				    				    }
			    		    	} 
			    		    }
			            }
			        }
			        jltXxm.setModel(xxM);
			        getContentPane().revalidate();
			        getContentPane().repaint();
			    	} catch(Exception ex) {
			    		ex.printStackTrace();
			    	} finally {
				    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			    	}
				}
		    }
		}

		private class xxmListListener implements ListSelectionListener {
		    private int tempCount;
		    private String tString1;
		    private String tString2;
		    @SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
		    public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting() && !jltXxm.isSelectionEmpty()) {
			    	try {
				    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			        @SuppressWarnings("rawtypes")
					ListModel model2 = jltXxm.getModel();
					ListModel model1 = jltNode.getModel();
			        partner = new DefaultListModel();
					        for (int p = 0; p < model1.getSize(); p++){
					        	if (jltNode.isSelectedIndex(p)) {
				            	tString1 = (String)model1.getElementAt(p);
				            	for (int i = 0; i < model2.getSize(); i++){
						            if (jltXxm.isSelectedIndex(i)) {
						            	tString2 = (String)model2.getElementAt(i);
						                    try {
						                    	resultsSalesList =  
						                    	salesListQueries.getPartnerbyXxmandNode(tString1, tString2);
						    				} catch (SQLException ex) {
						    					ex.printStackTrace();
						    				}
						                    tempCount = resultsSalesList.size();
						                	    for (int j = 0; j < tempCount; j++) {
						                	    	currentSalesListEntry = resultsSalesList.get(j);
						                	    	if (!partner.contains(currentSalesListEntry.getUniquePartnerName())) {
							                	    	partner.addElement(currentSalesListEntry.getUniquePartnerName());
						                	    	}
						                	    }
						                        jltPartner.setModel(partner);
						            }
						        }
					        }
					        getContentPane().revalidate();
					        getContentPane().repaint();
					    } 
			    	} catch(Exception ex) {
				    	ex.printStackTrace();
				    } finally {
				    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				    }
				}
		    }
		    
		}
		
		private class partnerListListener implements ListSelectionListener {
		    @SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
		    public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting() && !jltPartner.isSelectionEmpty()) {
		    	
			    	try {
				    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				    
					isParPre = true; 
					isParDev = true;
					isBB = true;
					isCLPL = true;
					isShark = false;
				    
		        	viewToggle.setText("Overall Accounts View - OFF");
		        	viewToggle.setForeground(Color.RED);
		        	isToggleOn = true;
//			    	checkDisplayAlgorithm("Net", false);
		        	setPanelComponentsAssignment(false);
			    	} catch(Exception ex) {
			    		ex.printStackTrace();
			    	} finally {
				    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			    	}
				}
		    }
		}
		
		private void variableInitialization() {
		    /*Panel1 & Panel2 variable initialization*/
	    	rsActual = secActual = ucsActual = ucActual = dcsActual = videoActual = wlanActual=
		    rsThreshold = secThreshold = ucsThreshold = ucThreshold = dcsThreshold = 
		    videoThreshold = wlanThreshold = rsPoint = secPoint = ucsPoint = ucPoint = 
		    dcsPoint = videoPoint = wlanPoint = coverage = q1Actual = q2Actual = h1Actual = 
		    q3Actual = q4Actual = h2Actual = ytdActual = q1Threshold = q2Threshold = h1Threshold = 
		    q3Threshold = q4Threshold = h2Threshold = ytdThreshold = q1Point = q2Point = h1Point = 
		    q3Point = q4Point = h2Point = ytdPoint=0D;

		    /*Panel3 variable initialization*/
		    q1TotCus = new double[1][1];
		    q1Pen = new double[1][2];
		    q1CusPen = new double[1][1];
		    q1TechPen = new double[1][1];
		    q1Discount = new double[1][5];
		    q1DiscountAll = new double[1][1];
		    q1DiscountColl = new double[1][1];
		    q1DiscountDCV = new double[1][1];
		    q1DiscountBN = new double[1][1];
		    q1DiscountSec = new double[1][1];
		    q1Yld = new double[1][2];
		    q1YldPerCus = new double[1][1];
		    q1YldPerOpp = new double[1][1];
		    q1CWPAchievement = new double[1][2];
		    q1CWP = new double[1][1];
		    q1Achievement = new double[1][1];
		    q1AOC = new double[1][1];
		    
		    q1TotCus[0][0] = q1Pen[0][0] = q1Pen[0][1] = q1CusPen[0][0] = q1TechPen[0][0] = q1Discount[0][0] = 
		    q1Discount[0][1] = q1Discount[0][2] = q1Discount[0][3] = q1DiscountAll[0][0] = q1DiscountColl[0][0] = 
		    q1DiscountDCV[0][0] = q1DiscountBN[0][0] = q1DiscountSec[0][0] = q1Yld[0][0] = q1Yld[0][1] = q1YldPerCus[0][0] = 
		    q1YldPerOpp[0][0] = q1TechCount = q1OppCount = q1RevAll = q1BListAll = q1RevColl = q1BListColl = 
		    q1RevDCV = q1BListDCV = q1RevBN = q1BListBN = q1CWPAchievement[0][0] = q1CWPAchievement[0][1] = 
		    q1CWP[0][0] = q1Achievement[0][0] = q1AOC[0][0] = q1RevSec = q1BListSec = 0D;
		    

		    q2TotCus = new double[1][1];
		    q2Pen = new double[1][2];
		    q2CusPen = new double[1][1];
		    q2TechPen = new double[1][1];
		    q2Discount = new double[1][5];
		    q2DiscountAll = new double[1][1];
		    q2DiscountColl = new double[1][1];
		    q2DiscountDCV = new double[1][1];
		    q2DiscountBN = new double[1][1];
		    q2DiscountSec = new double[1][1];
		    q2Yld = new double[1][2];
		    q2YldPerCus = new double[1][1];
		    q2YldPerOpp = new double[1][1];
		    q2CWPAchievement = new double[1][2];
		    q2CWP = new double[1][1];
		    q2Achievement = new double[1][1];
		    q2AOC = new double[1][1];
		    
		    q2TotCus[0][0] = q2Pen[0][0] = q2Pen[0][1] = q2CusPen[0][0] = q2TechPen[0][0] = q2Discount[0][0] = 
		    q2Discount[0][1] = q2Discount[0][2] = q2Discount[0][3] = q2DiscountAll[0][0] = q2DiscountColl[0][0] = 
		    q2DiscountDCV[0][0] = q2DiscountBN[0][0] = q2DiscountSec[0][0] = q2Yld[0][0] = q2Yld[0][1] = q2YldPerCus[0][0] = 
		    q2YldPerOpp[0][0] = q2TechCount = q2OppCount = q2RevAll = q2BListAll = q2RevColl = q2BListColl = 
		    q2RevDCV = q2BListDCV = q2RevBN = q2BListBN = q2CWPAchievement[0][0] = q2CWPAchievement[0][1] = 
		    q2CWP[0][0] = q2Achievement[0][0] = q2AOC[0][0] = q2RevSec = q2BListSec = 0D;

		    h1TotCus = new double[1][1];
		    h1Pen = new double[1][2];
		    h1CusPen = new double[1][1];
		    h1TechPen = new double[1][1];
		    h1Discount = new double[1][5];
		    h1DiscountAll = new double[1][1];
		    h1DiscountColl = new double[1][1];
		    h1DiscountDCV = new double[1][1];
		    h1DiscountBN = new double[1][1];
		    h1DiscountSec = new double[1][1];
		    h1Yld = new double[1][2];
		    h1YldPerCus = new double[1][1];
		    h1YldPerOpp = new double[1][1];
		    h1CWPAchievement = new double[1][2];
		    h1CWP = new double[1][1];
		    h1Achievement = new double[1][1];
		    h1AOC = new double[1][1];
		    
		    h1TotCus[0][0] = h1Pen[0][0] = h1Pen[0][1] = h1CusPen[0][0] = h1TechPen[0][0] = h1Discount[0][0] = 
		    h1Discount[0][1] = h1Discount[0][2] = h1Discount[0][3] = h1DiscountAll[0][0] = h1DiscountColl[0][0] = 
		    h1DiscountDCV[0][0] = h1DiscountBN[0][0] = h1DiscountSec[0][0] = h1Yld[0][0] = h1Yld[0][1] = h1YldPerCus[0][0] = 
		    h1YldPerOpp[0][0] = h1TechCount = h1OppCount = h1RevAll = h1BListAll = h1RevColl = h1BListColl = 
		    h1RevDCV = h1BListDCV = h1RevBN = h1BListBN = h1CWPAchievement[0][0] = h1CWPAchievement[0][1] = 
		    h1CWP[0][0] = h1Achievement[0][0] = h1AOC[0][0] = h1RevSec = h1BListSec = 0D;

		    q3TotCus = new double[1][1];
		    q3Pen = new double[1][2];
		    q3CusPen = new double[1][1];
		    q3TechPen = new double[1][1];
		    q3Discount = new double[1][5];
		    q3DiscountAll = new double[1][1];
		    q3DiscountColl = new double[1][1];
		    q3DiscountDCV = new double[1][1];
		    q3DiscountBN = new double[1][1];
		    q3DiscountSec = new double[1][1];
		    q3Yld = new double[1][2];
		    q3YldPerCus = new double[1][1];
		    q3YldPerOpp = new double[1][1];
		    q3CWPAchievement = new double[1][2];
		    q3CWP = new double[1][1];
		    q3Achievement = new double[1][1];
		    q3AOC = new double[1][1];
		    
		    q3TotCus[0][0] = q3Pen[0][0] = q3Pen[0][1] = q3CusPen[0][0] = q3TechPen[0][0] = q3Discount[0][0] = 
		    q3Discount[0][1] = q3Discount[0][2] = q3Discount[0][3] = q3DiscountAll[0][0] = q3DiscountColl[0][0] = 
		    q3DiscountDCV[0][0] = q3DiscountBN[0][0] = q3DiscountSec[0][0] = q3Yld[0][0] = q3Yld[0][1] = q3YldPerCus[0][0] = 
		    q3YldPerOpp[0][0] = q3TechCount = q3OppCount = q3RevAll = q3BListAll = q3RevColl = q3BListColl = 
		    q3RevDCV = q3BListDCV = q3RevBN = q3BListBN = q3CWPAchievement[0][0] = q3CWPAchievement[0][1] = 
		    q3CWP[0][0] = q3Achievement[0][0] = q3AOC[0][0] = q3RevSec = q3BListSec = 0D;

		    q4TotCus = new double[1][1];
		    q4Pen = new double[1][2];
		    q4CusPen = new double[1][1];
		    q4TechPen = new double[1][1];
		    q4Discount = new double[1][5];
		    q4DiscountAll = new double[1][1];
		    q4DiscountColl = new double[1][1];
		    q4DiscountDCV = new double[1][1];
		    q4DiscountBN = new double[1][1];
		    q4DiscountSec = new double[1][1];
		    q4Yld = new double[1][2];
		    q4YldPerCus = new double[1][1];
		    q4YldPerOpp = new double[1][1];
		    q4CWPAchievement = new double[1][2];
		    q4CWP = new double[1][1];
		    q4Achievement = new double[1][1];
		    q4AOC = new double[1][1];
		    
		    q4TotCus[0][0] = q4Pen[0][0] = q4Pen[0][1] = q4CusPen[0][0] = q4TechPen[0][0] = q4Discount[0][0] = 
		    q4Discount[0][1] = q4Discount[0][2] = q4Discount[0][3] = q4DiscountAll[0][0] = q4DiscountColl[0][0] = 
		    q4DiscountDCV[0][0] = q4DiscountBN[0][0] = q4DiscountSec[0][0] = q4Yld[0][0] = q4Yld[0][1] = q4YldPerCus[0][0] = 
		    q4YldPerOpp[0][0] = q4TechCount = q4OppCount = q4RevAll = q4BListAll = q4RevColl = q4BListColl = 
		    q4RevDCV = q4BListDCV = q4RevBN = q4BListBN = q4CWPAchievement[0][0] = q4CWPAchievement[0][1] = 
		    q4CWP[0][0] = q4Achievement[0][0] = q4AOC[0][0] = q4RevSec = q4BListSec = 0D;

		    h2TotCus = new double[1][1];
		    h2Pen = new double[1][2];
		    h2CusPen = new double[1][1];
		    h2TechPen = new double[1][1];
		    h2Discount = new double[1][5];
		    h2DiscountAll = new double[1][1];
		    h2DiscountColl = new double[1][1];
		    h2DiscountDCV = new double[1][1];
		    h2DiscountBN = new double[1][1];
		    h2DiscountSec = new double[1][1];
		    h2Yld = new double[1][2];
		    h2YldPerCus = new double[1][1];
		    h2YldPerOpp = new double[1][1];
		    h2CWPAchievement = new double[1][2];
		    h2CWP = new double[1][1];
		    h2Achievement = new double[1][1];
		    h2AOC = new double[1][1];
		    
		    h2TotCus[0][0] = h2Pen[0][0] = h2Pen[0][1] = h2CusPen[0][0] = h2TechPen[0][0] = h2Discount[0][0] = 
		    h2Discount[0][1] = h2Discount[0][2] = h2Discount[0][3] = h2DiscountAll[0][0] = h2DiscountColl[0][0] = 
		    h2DiscountDCV[0][0] = h2DiscountBN[0][0] = h2DiscountSec[0][0] = h2Yld[0][0] = h2Yld[0][1] = h2YldPerCus[0][0] = 
		    h2YldPerOpp[0][0] = h2TechCount = h2OppCount = h2RevAll = h2BListAll = h2RevColl = h2BListColl = 
		    h2RevDCV = h2BListDCV = h2RevBN = h2BListBN = h2CWPAchievement[0][0] = h2CWPAchievement[0][1] = 
		    h2CWP[0][0] = h2Achievement[0][0] = h2AOC[0][0] = h2RevSec = h2BListSec = 0D;

		    ytdTotCus = new double[1][1];
		    ytdPen = new double[1][2];
		    ytdCusPen = new double[1][1];
		    ytdTechPen = new double[1][1];
		    ytdDiscount = new double[1][5];
		    ytdDiscountAll = new double[1][1];
		    ytdDiscountColl = new double[1][1];
		    ytdDiscountDCV = new double[1][1];
		    ytdDiscountBN = new double[1][1];
		    ytdDiscountSec = new double[1][1];
		    ytdYld = new double[1][2];
		    ytdYldPerCus = new double[1][1];
		    ytdYldPerOpp = new double[1][1];
		    ytdCWPAchievement = new double[1][2];
		    ytdCWP = new double[1][1];
		    ytdAchievement = new double[1][1];
		    ytdAOC = new double[1][1];
		    
		    ytdTotCus[0][0] = ytdPen[0][0] = ytdPen[0][1] = ytdCusPen[0][0] = ytdTechPen[0][0] = ytdDiscount[0][0] = 
		    ytdDiscount[0][1] = ytdDiscount[0][2] = ytdDiscount[0][3] = ytdDiscountAll[0][0] = ytdDiscountColl[0][0] = 
		    ytdDiscountDCV[0][0] = ytdDiscountBN[0][0] = ytdDiscountSec[0][0] = ytdYld[0][0] = ytdYld[0][1] = ytdYldPerCus[0][0] = 
		    ytdYldPerOpp[0][0] = ytdTechCount = ytdOppCount = ytdRevAll = ytdBListAll = ytdRevColl = ytdBListColl = 
		    ytdRevDCV = ytdBListDCV = ytdRevBN = ytdBListBN = ytdCWPAchievement[0][0] = ytdCWPAchievement[0][1] = 
		    ytdCWP[0][0] = ytdAchievement[0][0] = ytdAOC[0][0] = ytdRevSec = ytdBListSec = 0D;

		}
		
		private void setPanelComponentsAssignment(boolean isJustPanel3Change) {

			variableInitialization();
			
    	   String optionString = (String)jcbxXxmOption.getSelectedItem();
    	   String parPre, parDev, bb, clpl, shark;

			parPre = (isParPre)? "1":"0";
			parDev = (isParDev)? "1":"0";
			bb = (isBB)? "1":"0";
			clpl = (isCLPL)? "1":"0";
			shark = (isShark)? "1":"0";

			valueSet = BusinessRulesXxmDashboard.getXxmDashboardMetrics(jltXxm, jltPartner, 
					parPre, parDev, bb, clpl, shark, optionString);
		
			otherTableValueset = valueSet.getOtherTableValues();
			salesTableValueset = valueSet.getSalesTableValues();
			techTableValueset = valueSet.getTechTableValues();
			

	    	actualRS = rsActual = techTableValueset.getRevRS();
	    	thresholdRS = rsThreshold = techTableValueset.getThresholdRS();
	    	actualSec = secActual = techTableValueset.getRevSec();
	    	thresholdSec = secThreshold = techTableValueset.getThresholdSec();
	    	actualUCS = ucsActual = techTableValueset.getRevUCS();
	    	thresholdUCS = ucsThreshold = techTableValueset.getThresholdUCS();
	    	actualUC = ucActual = techTableValueset.getRevUC();
	    	thresholdUC = ucThreshold = techTableValueset.getThresholdUC();
	    	actualDCS = dcsActual = techTableValueset.getRevDCS();
	    	thresholdDCS = dcsThreshold = techTableValueset.getThresholdDCS();
	    	actualVideo = videoActual = techTableValueset.getRevVideo();
	    	thresholdVideo = videoThreshold = techTableValueset.getThresholdVideo();
	    	actualWLAN = wlanActual = techTableValueset.getRevWLAN();
	    	thresholdWLAN = wlanThreshold = techTableValueset.getThresholdWLAN();

	        /* Panel2 Data Assignment*/			                    	
	    	panel2ActualQ1 = q1Actual = salesTableValueset.getRevQ1();
	    	panel2ThresholdQ1 = q1Threshold = salesTableValueset.getThresholdQ1();
	    	panel2ActualQ2 = q2Actual = salesTableValueset.getRevQ2();
	    	panel2ThresholdQ2 = q2Threshold = salesTableValueset.getThresholdQ2();
	    	panel2ActualH1 = h1Actual = salesTableValueset.getRevH1();
	    	panel2ThresholdH1 = h1Threshold = salesTableValueset.getThresholdH1();
	    	panel2ActualQ3 = q3Actual = salesTableValueset.getRevQ3();
	    	panel2ThresholdQ3 = q3Threshold = salesTableValueset.getThresholdQ3();
	    	panel2ActualQ4 = q4Actual = salesTableValueset.getRevQ4();
	    	panel2ThresholdQ4 = q4Threshold = salesTableValueset.getThresholdQ4();
	    	panel2ActualH2 = h2Actual = salesTableValueset.getRevH2();
	    	panel2ThresholdH2 = h2Threshold = salesTableValueset.getThresholdH2();
	    	panel2ActualYTD = ytdActual = salesTableValueset.getRevYTD();
	    	panel2ThresholdYTD = ytdThreshold = salesTableValueset.getThresholdYTD();

			
			/* Panel1 Data Assignment*/
	        pointRS = rsPoint = CalcHelper.getTechPerformance(techTableValueset.getRevRS(), techTableValueset.getThresholdRS());
	        pointSec = secPoint = CalcHelper.getTechPerformance(techTableValueset.getRevSec(), techTableValueset.getThresholdSec());
	        pointUCS = ucsPoint = CalcHelper.getTechPerformance(techTableValueset.getRevUCS(), techTableValueset.getThresholdUCS());
	        pointUC = ucPoint = CalcHelper.getTechPerformance(techTableValueset.getRevUC(), techTableValueset.getThresholdUC());
	        pointDCS = dcsPoint = CalcHelper.getTechPerformance(techTableValueset.getRevDCS(), techTableValueset.getThresholdDCS());
	        pointVideo = videoPoint = CalcHelper.getTechPerformance(techTableValueset.getRevVideo(), techTableValueset.getThresholdVideo());
	        pointWLAN = wlanPoint = CalcHelper.getTechPerformance(techTableValueset.getRevWLAN(), techTableValueset.getThresholdWLAN());
	        techCoverage = coverage = CalcHelper.getCoverage(rsPoint, secPoint, ucsPoint,
	        		ucPoint, dcsPoint, videoPoint,  wlanPoint);
	        posibleTechPoints = CalcHelper.getTotalPosibleCoverage(rsPoint, secPoint, ucsPoint,
	        		ucPoint, dcsPoint, videoPoint,  wlanPoint);

	        /* Panel2 Data Assignment*/			                    	
	        q1Point = CalcHelper.getAchievement(salesTableValueset.getRevQ1(), salesTableValueset.getThresholdQ1());
	        q2Point = CalcHelper.getAchievement(salesTableValueset.getRevQ2(), salesTableValueset.getThresholdQ2());
	        h1Point = CalcHelper.getAchievement(salesTableValueset.getRevH1(), salesTableValueset.getThresholdH1());
	        q3Point = CalcHelper.getAchievement(salesTableValueset.getRevQ3(), salesTableValueset.getThresholdQ3());
	        q4Point = CalcHelper.getAchievement(salesTableValueset.getRevQ4(), salesTableValueset.getThresholdQ4());
	        h2Point = CalcHelper.getAchievement(salesTableValueset.getRevH2(), salesTableValueset.getThresholdH2());
	        ytdPoint = CalcHelper.getAchievement(salesTableValueset.getRevYTD(), salesTableValueset.getThresholdYTD());
	        double maxPoint = CalcHelper.getMaxOutofSeven(q1Point, q2Point, 
	        		h1Point, q3Point, q4Point, h2Point, ytdPoint);
	        double minPoint = CalcHelper.getMinOutofSeven(q1Point, q2Point, 
	        		h1Point, q3Point, q4Point, h2Point, ytdPoint);
	        
	        /*Panel3 Data Assignment*/
		    /*Finding Panel3 Metrics*/

	        q1TotCus[0][0] = otherTableValueset.getTotCustomers();
	        q1CusPen[0][0] = otherTableValueset.getCusPenQ1();
	        q1TechPen[0][0] = CalcHelper.getTechnologyPenetration(otherTableValueset.getTechCountQ1(), otherTableValueset.getCusPenQ1());
		    q1DiscountAll[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueAllQ1(), otherTableValueset.getBaseListAllQ1());
		    q1DiscountColl[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueCollabQ1(), otherTableValueset.getBaseListCollabQ1());
		    q1DiscountDCV[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueDCVQ1(), otherTableValueset.getBaseListDCVQ1());
		    q1DiscountSec[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueSecurityQ1(), otherTableValueset.getBaseListSecurityQ1());
		    q1DiscountBN[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueENTNWQ1(), otherTableValueset.getBaseListENTNWQ1());
		    q1YldPerCus[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllQ1(), otherTableValueset.getCusPenQ1());
		    q1YldPerOpp[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllQ1(), otherTableValueset.getOppCountQ1());
		    q1Achievement[0][0] = otherTableValueset.getRevenueAllQ1();
		    q1CWP[0][0] = otherTableValueset.getCWP();
		    q1AOC[0][0] = CalcHelper.getAchievement(otherTableValueset.getRevenueAllQ1(), otherTableValueset.getCWP());

	        q2TotCus[0][0] = otherTableValueset.getTotCustomers();
	        q2CusPen[0][0] = otherTableValueset.getCusPenQ2();
	        q2TechPen[0][0] = CalcHelper.getTechnologyPenetration(otherTableValueset.getTechCountQ2(), otherTableValueset.getCusPenQ2());
		    q2DiscountAll[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueAllQ2(), otherTableValueset.getBaseListAllQ2());
		    q2DiscountColl[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueCollabQ2(), otherTableValueset.getBaseListCollabQ2());
		    q2DiscountDCV[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueDCVQ2(), otherTableValueset.getBaseListDCVQ2());
		    q2DiscountSec[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueSecurityQ2(), otherTableValueset.getBaseListSecurityQ2());
		    q2DiscountBN[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueENTNWQ2(), otherTableValueset.getBaseListENTNWQ2());
		    q2YldPerCus[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllQ2(), otherTableValueset.getCusPenQ2());
		    q2YldPerOpp[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllQ2(), otherTableValueset.getOppCountQ2());
		    q2Achievement[0][0] = otherTableValueset.getRevenueAllQ2();
		    q2CWP[0][0] = otherTableValueset.getCWP();
		    q2AOC[0][0] = CalcHelper.getAchievement(otherTableValueset.getRevenueAllQ2(), otherTableValueset.getCWP());

	        h1TotCus[0][0] = otherTableValueset.getTotCustomers();
	        h1CusPen[0][0] = otherTableValueset.getCusPenH1();
	        h1TechPen[0][0] = CalcHelper.getTechnologyPenetration(otherTableValueset.getTechCountH1(), otherTableValueset.getCusPenH1());
		    h1DiscountAll[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueAllH1(), otherTableValueset.getBaseListAllH1());
		    h1DiscountColl[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueCollabH1(), otherTableValueset.getBaseListCollabH1());
		    h1DiscountDCV[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueDCVH1(), otherTableValueset.getBaseListDCVH1());
		    h1DiscountSec[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueSecurityH1(), otherTableValueset.getBaseListSecurityH1());
		    h1DiscountBN[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueENTNWH1(), otherTableValueset.getBaseListENTNWH1());
		    h1YldPerCus[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllH1(), otherTableValueset.getCusPenH1());
		    h1YldPerOpp[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllH1(), otherTableValueset.getOppCountH1());
		    h1Achievement[0][0] = otherTableValueset.getRevenueAllH1();
		    h1CWP[0][0] = otherTableValueset.getCWP();
		    h1AOC[0][0] = CalcHelper.getAchievement(otherTableValueset.getRevenueAllH1(), otherTableValueset.getCWP());

	        q3TotCus[0][0] = otherTableValueset.getTotCustomers();
	        q3CusPen[0][0] = otherTableValueset.getCusPenQ3();
	        q3TechPen[0][0] = CalcHelper.getTechnologyPenetration(otherTableValueset.getTechCountQ3(), otherTableValueset.getCusPenQ3());
		    q3DiscountAll[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueAllQ3(), otherTableValueset.getBaseListAllQ3());
		    q3DiscountColl[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueCollabQ3(), otherTableValueset.getBaseListCollabQ3());
		    q3DiscountDCV[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueDCVQ3(), otherTableValueset.getBaseListDCVQ3());
		    q3DiscountSec[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueSecurityQ3(), otherTableValueset.getBaseListSecurityQ3());
		    q3DiscountBN[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueENTNWQ3(), otherTableValueset.getBaseListENTNWQ3());
		    q3YldPerCus[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllQ3(), otherTableValueset.getCusPenQ3());
		    q3YldPerOpp[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllQ3(), otherTableValueset.getOppCountQ3());
		    q3Achievement[0][0] = otherTableValueset.getRevenueAllQ3();
		    q3CWP[0][0] = otherTableValueset.getCWP();
		    q3AOC[0][0] = CalcHelper.getAchievement(otherTableValueset.getRevenueAllQ3(), otherTableValueset.getCWP());

	        q4TotCus[0][0] = otherTableValueset.getTotCustomers();
	        q4CusPen[0][0] = otherTableValueset.getCusPenQ4();
	        q4TechPen[0][0] = CalcHelper.getTechnologyPenetration(otherTableValueset.getTechCountQ4(), otherTableValueset.getCusPenQ4());
		    q4DiscountAll[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueAllQ4(), otherTableValueset.getBaseListAllQ4());
		    q4DiscountColl[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueCollabQ4(), otherTableValueset.getBaseListCollabQ4());
		    q4DiscountDCV[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueDCVQ4(), otherTableValueset.getBaseListDCVQ4());
		    q4DiscountSec[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueSecurityQ4(), otherTableValueset.getBaseListSecurityQ4());
		    q4DiscountBN[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueENTNWQ4(), otherTableValueset.getBaseListENTNWQ4());
		    q4YldPerCus[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllQ4(), otherTableValueset.getCusPenQ4());
		    q4YldPerOpp[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllQ4(), otherTableValueset.getOppCountQ4());
		    q4Achievement[0][0] = otherTableValueset.getRevenueAllQ4();
		    q4CWP[0][0] = otherTableValueset.getCWP();
		    q4AOC[0][0] = CalcHelper.getAchievement(otherTableValueset.getRevenueAllQ4(), otherTableValueset.getCWP());

	        h2TotCus[0][0] = otherTableValueset.getTotCustomers();
	        h2CusPen[0][0] = otherTableValueset.getCusPenH2();
	        h2TechPen[0][0] = CalcHelper.getTechnologyPenetration(otherTableValueset.getTechCountH2(), otherTableValueset.getCusPenH2());
		    h2DiscountAll[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueAllH2(), otherTableValueset.getBaseListAllH2());
		    h2DiscountColl[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueCollabH2(), otherTableValueset.getBaseListCollabH2());
		    h2DiscountDCV[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueDCVH2(), otherTableValueset.getBaseListDCVH2());
		    h2DiscountSec[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueSecurityH2(), otherTableValueset.getBaseListSecurityH2());
		    h2DiscountBN[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueENTNWH2(), otherTableValueset.getBaseListENTNWH2());
		    h2YldPerCus[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllH2(), otherTableValueset.getCusPenH2());
		    h2YldPerOpp[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllH2(), otherTableValueset.getOppCountH2());
		    h2Achievement[0][0] = otherTableValueset.getRevenueAllH2();
		    h2CWP[0][0] = otherTableValueset.getCWP();
		    h2AOC[0][0] = CalcHelper.getAchievement(otherTableValueset.getRevenueAllH2(), otherTableValueset.getCWP());

	        ytdTotCus[0][0] = otherTableValueset.getTotCustomers();
	        ytdCusPen[0][0] = otherTableValueset.getCusPenYTD();
	        ytdTechPen[0][0] = CalcHelper.getTechnologyPenetration(otherTableValueset.getTechCountYTD(), otherTableValueset.getCusPenYTD());
		    ytdDiscountAll[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueAllYTD(), otherTableValueset.getBaseListAllYTD());
		    ytdDiscountColl[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueCollabYTD(), otherTableValueset.getBaseListCollabYTD());
		    ytdDiscountDCV[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueDCVYTD(), otherTableValueset.getBaseListDCVYTD());
		    ytdDiscountSec[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueSecurityYTD(), otherTableValueset.getBaseListSecurityYTD());
		    ytdDiscountBN[0][0] = CalcHelper.getDiscount(otherTableValueset.getRevenueENTNWYTD(), otherTableValueset.getBaseListENTNWYTD());
		    ytdYldPerCus[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllYTD(), otherTableValueset.getCusPenYTD());
		    ytdYldPerOpp[0][0] = CalcHelper.getYield(otherTableValueset.getRevenueAllYTD(), otherTableValueset.getOppCountYTD());
		    ytdAchievement[0][0] = otherTableValueset.getRevenueAllYTD();
		    ytdCWP[0][0] = otherTableValueset.getCWP();
		    ytdAOC[0][0] = CalcHelper.getAchievement(otherTableValueset.getRevenueAllYTD(), otherTableValueset.getCWP());
		    

	    	if (dataType.equals("All")) {
	    		q1TotCus[0][0] = (q1TotCus[0][0] + (q1CusPen[0][0] - q1TempCusPen));
	    		q2TotCus[0][0] = (q2TotCus[0][0] + (q2CusPen[0][0] - q2TempCusPen));
	    		h1TotCus[0][0] = (h1TotCus[0][0] + (h1CusPen[0][0] - h1TempCusPen));
	    		q3TotCus[0][0] = (q3TotCus[0][0] + (q3CusPen[0][0] - q3TempCusPen));
	    		q4TotCus[0][0] = (q4TotCus[0][0] + (q4CusPen[0][0] - q4TempCusPen));
	    		h2TotCus[0][0] = (h2TotCus[0][0] + (h2CusPen[0][0] - h2TempCusPen));
	    		ytdTotCus[0][0] = (ytdTotCus[0][0] + (ytdCusPen[0][0] - ytdTempCusPen));
	    	} else {
	    		q1TempCusPen = q1CusPen[0][0]; 
	    		q2TempCusPen = q2CusPen[0][0]; 
	    		h1TempCusPen = h1CusPen[0][0]; 
	    		q3TempCusPen = q3CusPen[0][0]; 
	    		q4TempCusPen = q4CusPen[0][0]; 
	    		h2TempCusPen = h2CusPen[0][0]; 
	    		ytdTempCusPen = ytdCusPen[0][0]; 
	    	}

		    /*Creating Category Dataset of the Panel3 Metrics*/
		    q1Pen[0][0] = q1TotCus[0][0];
		    q1Pen[0][1] = q1CusPen[0][0];
		    q1Discount[0][4] = q1DiscountAll[0][0];
		    q1Discount[0][2] = q1DiscountColl[0][0];
		    q1Discount[0][3] = q1DiscountDCV[0][0];
		    q1Discount[0][1] = q1DiscountSec[0][0];
		    q1Discount[0][0] = q1DiscountBN[0][0];
		    q1Yld[0][0] = q1YldPerCus[0][0]; 
		    q1Yld[0][1] = q1YldPerOpp[0][0];
		    q1CWPAchievement[0][0] =  q1Achievement[0][0];
		    q1CWPAchievement[0][1] =  q1CWP[0][0];
		    
		    q1TotCusDs = DatasetUtilities.createCategoryDataset("", "", q1Pen);
		    q1PenDs = DatasetUtilities.createCategoryDataset("", "", q1TechPen);
		    q1DiscountDs = DatasetUtilities.createCategoryDataset("", "", q1Discount);
		    q1YldDs = DatasetUtilities.createCategoryDataset("", "", q1Yld);
		    q1CWPAchievementDs = DatasetUtilities.createCategoryDataset("", "", q1CWPAchievement);
		    q1AOCDs = DatasetUtilities.createCategoryDataset("", "", ytdAOC);

		    q2Pen[0][0] = q2TotCus[0][0];
		    q2Pen[0][1] = q2CusPen[0][0];
		    q2Discount[0][4] = q2DiscountAll[0][0];
		    q2Discount[0][2] = q2DiscountColl[0][0];
		    q2Discount[0][3] = q2DiscountDCV[0][0];
		    q2Discount[0][1] = q2DiscountSec[0][0];
		    q2Discount[0][0] = q2DiscountBN[0][0];
		    q2Yld[0][0] = q2YldPerCus[0][0]; 
		    q2Yld[0][1] = q2YldPerOpp[0][0];
		    q2CWPAchievement[0][0] =  q2Achievement[0][0];
		    q2CWPAchievement[0][1] =  q2CWP[0][0];
		    
		    q2TotCusDs = DatasetUtilities.createCategoryDataset("", "", q2Pen);
		    q2PenDs = DatasetUtilities.createCategoryDataset("", "", q2TechPen);
		    q2DiscountDs = DatasetUtilities.createCategoryDataset("", "", q2Discount);
		    q2YldDs = DatasetUtilities.createCategoryDataset("", "", q2Yld);
		    q2CWPAchievementDs = DatasetUtilities.createCategoryDataset("", "", q2CWPAchievement);
		    q2AOCDs = DatasetUtilities.createCategoryDataset("", "", ytdAOC);

		    h1Pen[0][0] = h1TotCus[0][0];
		    h1Pen[0][1] = h1CusPen[0][0];
		    h1Discount[0][4] = h1DiscountAll[0][0];
		    h1Discount[0][2] = h1DiscountColl[0][0];
		    h1Discount[0][3] = h1DiscountDCV[0][0];
		    h1Discount[0][1] = h1DiscountSec[0][0];
		    h1Discount[0][0] = h1DiscountBN[0][0];
		    h1Yld[0][0] = h1YldPerCus[0][0]; 
		    h1Yld[0][1] = h1YldPerOpp[0][0];
		    h1CWPAchievement[0][0] =  h1Achievement[0][0];
		    h1CWPAchievement[0][1] =  h1CWP[0][0];
		    
		    h1TotCusDs = DatasetUtilities.createCategoryDataset("", "", h1Pen);
		    h1PenDs = DatasetUtilities.createCategoryDataset("", "", h1TechPen);
		    h1DiscountDs = DatasetUtilities.createCategoryDataset("", "", h1Discount);
		    h1YldDs = DatasetUtilities.createCategoryDataset("", "", h1Yld);
		    h1CWPAchievementDs = DatasetUtilities.createCategoryDataset("", "", h1CWPAchievement);
		    h1AOCDs = DatasetUtilities.createCategoryDataset("", "", ytdAOC);

		    q3Pen[0][0] = q3TotCus[0][0];
		    q3Pen[0][1] = q3CusPen[0][0];
		    q3Discount[0][4] = q3DiscountAll[0][0];
		    q3Discount[0][2] = q3DiscountColl[0][0];
		    q3Discount[0][3] = q3DiscountDCV[0][0];
		    q3Discount[0][1] = q3DiscountSec[0][0];
		    q3Discount[0][0] = q3DiscountBN[0][0];
		    q3Yld[0][0] = q3YldPerCus[0][0]; 
		    q3Yld[0][1] = q3YldPerOpp[0][0];
		    q3CWPAchievement[0][0] =  q3Achievement[0][0];
		    q3CWPAchievement[0][1] =  q3CWP[0][0];
		    
		    q3TotCusDs = DatasetUtilities.createCategoryDataset("", "", q3Pen);
		    q3PenDs = DatasetUtilities.createCategoryDataset("", "", q3TechPen);
		    q3DiscountDs = DatasetUtilities.createCategoryDataset("", "", q3Discount);
		    q3YldDs = DatasetUtilities.createCategoryDataset("", "", q3Yld);
		    q3CWPAchievementDs = DatasetUtilities.createCategoryDataset("", "", q3CWPAchievement);
		    q3AOCDs = DatasetUtilities.createCategoryDataset("", "", ytdAOC);

		    q4Pen[0][0] = q4TotCus[0][0];
		    q4Pen[0][1] = q4CusPen[0][0];
		    q4Discount[0][4] = q4DiscountAll[0][0];
		    q4Discount[0][2] = q4DiscountColl[0][0];
		    q4Discount[0][3] = q4DiscountDCV[0][0];
		    q4Discount[0][1] = q4DiscountSec[0][0];
		    q4Discount[0][0] = q4DiscountBN[0][0];
		    q4Yld[0][0] = q4YldPerCus[0][0]; 
		    q4Yld[0][1] = q4YldPerOpp[0][0];
		    q4CWPAchievement[0][0] =  q4Achievement[0][0];
		    q4CWPAchievement[0][1] =  q4CWP[0][0];
		    
		    q4TotCusDs = DatasetUtilities.createCategoryDataset("", "", q4Pen);
		    q4PenDs = DatasetUtilities.createCategoryDataset("", "", q4TechPen);
		    q4DiscountDs = DatasetUtilities.createCategoryDataset("", "", q4Discount);
		    q4YldDs = DatasetUtilities.createCategoryDataset("", "", q4Yld);
		    q4CWPAchievementDs = DatasetUtilities.createCategoryDataset("", "", q4CWPAchievement);
		    q4AOCDs = DatasetUtilities.createCategoryDataset("", "", ytdAOC);

		    h2Pen[0][0] = h2TotCus[0][0];
		    h2Pen[0][1] = h2CusPen[0][0];
		    h2Discount[0][4] = h2DiscountAll[0][0];
		    h2Discount[0][2] = h2DiscountColl[0][0];
		    h2Discount[0][3] = h2DiscountDCV[0][0];
		    h2Discount[0][1] = h2DiscountSec[0][0];
		    h2Discount[0][0] = h2DiscountBN[0][0];
		    h2Yld[0][0] = h2YldPerCus[0][0]; 
		    h2Yld[0][1] = h2YldPerOpp[0][0];
		    h2CWPAchievement[0][0] =  h2Achievement[0][0];
		    h2CWPAchievement[0][1] =  h2CWP[0][0];
		    
		    h2TotCusDs = DatasetUtilities.createCategoryDataset("", "", h2Pen);
		    h2PenDs = DatasetUtilities.createCategoryDataset("", "", h2TechPen);
		    h2DiscountDs = DatasetUtilities.createCategoryDataset("", "", h2Discount);
		    h2YldDs = DatasetUtilities.createCategoryDataset("", "", h2Yld);
		    h2CWPAchievementDs = DatasetUtilities.createCategoryDataset("", "", h2CWPAchievement);
		    h2AOCDs = DatasetUtilities.createCategoryDataset("", "", ytdAOC);


		    ytdPen[0][0] = ytdTotCus[0][0];
		    ytdPen[0][1] = ytdCusPen[0][0];
		    ytdDiscount[0][4] = ytdDiscountAll[0][0];
		    ytdDiscount[0][2] = ytdDiscountColl[0][0];
		    ytdDiscount[0][3] = ytdDiscountDCV[0][0];
		    ytdDiscount[0][1] = ytdDiscountSec[0][0];
		    ytdDiscount[0][0] = ytdDiscountBN[0][0];
		    ytdYld[0][0] = ytdYldPerCus[0][0]; 
		    ytdYld[0][1] = ytdYldPerOpp[0][0];
		    ytdCWPAchievement[0][0] =  ytdAchievement[0][0];
		    ytdCWPAchievement[0][1] =  ytdCWP[0][0];
		    
		    ytdTotCusDs = DatasetUtilities.createCategoryDataset("", "", ytdPen);
		    ytdPenDs = DatasetUtilities.createCategoryDataset("", "", ytdTechPen);
		    ytdDiscountDs = DatasetUtilities.createCategoryDataset("", "", ytdDiscount);
		    ytdYldDs = DatasetUtilities.createCategoryDataset("", "", ytdYld);
		    ytdCWPAchievementDs = DatasetUtilities.createCategoryDataset("", "", ytdCWPAchievement);
		    ytdAOCDs = DatasetUtilities.createCategoryDataset("", "", ytdAOC);

		    
		    if (!isJustPanel3Change) {
	        compoPanel.remove(jlDScreenImage1);
	        compoPanel.remove(jtaWhatIsIt);
			compoPanel.remove(jlWhatIsItTitle);
			compoPanel.remove(jbWhatIsItReadMore);
	        compoPanel.remove(jlDScreenImage2);
	        compoPanel.remove(jtaHowItWorks);
			compoPanel.remove(jlHowItWorksTitle);
	        compoPanel.remove(infoPanel);
		    compoPanel.setBackground(Color.BLUE);
		    compoPanel.add(panel1);
		    compoPanel.add(panel2);
		    panel3Parent.add(panel3);
		    compoPanel.add(panel3Parent);
		    compoPanel.add(sep2);
		    compoPanel.add(sep3);
		    compoPanel.add(viewToggle);
			compoPanel.add(jchbxPanel3PartnerPreferred);
			compoPanel.add(jchbxPanel3PartnerDevelop);
			compoPanel.add(jchbxPanel3BigBet);
			compoPanel.add(jchbxPanel3CLToPL);
			compoPanel.remove(jchbxPanel3Sharkpool);
	        panel1.removeAll();
	        panel11.removeAll();
	        panel1.add(panel11);
	        panel2.removeAll();
	        setPanel1ComponentsReady(coverage, rsPoint, secPoint, ucsPoint, ucPoint, dcsPoint, videoPoint, wlanPoint);
	        setPanel2ComponentsReady(q1Point, q2Point, h1Point, q3Point, q4Point, h2Point, ytdPoint, minPoint, maxPoint);
		    }
	        panel3.removeAll();
	        setPanel3ComponentsReady();
	        getContentPane().revalidate();
	        getContentPane().repaint();
		}

		
		
		private class Q1ChartMouseListener implements ChartMouseListener {
			private JLabel jlToolTip = new JLabel();
			public void chartMouseClicked(ChartMouseEvent event) {
				// No Actions
			}
			public void chartMouseMoved(ChartMouseEvent event) {
				try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				int x = event.getTrigger().getX();
				int y = event.getTrigger().getY();
				ChartEntity entity = event.getEntity();
				if (entity != null) {
				    if ((x >= 60 && x < 190) && (y >= 20 && y < 150)) {
				    	int xPos = 105;
				    	int posOffset = -50;
				    	String displayText = ComponentHelper.getMyToolTip(panel2ActualQ1, panel2ThresholdQ1); 
					if (!displayText.equals("No Data")) {
							xPos = xPos + posOffset;
					} 
					jlToolTip.setText(displayText);
					jlToolTip.setFont(new Font("Verdana", Font.ITALIC, 12));
					jlToolTip.setBounds(xPos, 180, 200, 50);
					panel2.add(jlToolTip);
					panel2.repaint();
					panel2.revalidate();
				    } else {
						panel2.remove(jlToolTip);
						panel2.repaint();
						panel2.revalidate();
				    }
				}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}
		
		private class Q2ChartMouseListener implements ChartMouseListener {
			private JLabel jlToolTip = new JLabel();
			public void chartMouseClicked(ChartMouseEvent event) {
				// No Actions
			}
			public void chartMouseMoved(ChartMouseEvent event) {
				try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				int x = event.getTrigger().getX();
				int y = event.getTrigger().getY();
				ChartEntity entity = event.getEntity();
				if (entity != null) {
				    if ((x >= 60 && x < 190) && (y >= 20 && y < 150)) {
				    	int xPos = 320;
				    	int posOffset = -50;
				    	String displayText = ComponentHelper.getMyToolTip(panel2ActualQ2, panel2ThresholdQ2); 
					if (!displayText.equals("No Data")) {
							xPos = xPos + posOffset;
					} 
					jlToolTip.setText(displayText);
					jlToolTip.setFont(new Font("Verdana", Font.ITALIC, 12));
					jlToolTip.setBounds(xPos, 180, 200, 50);
					panel2.add(jlToolTip);
					panel2.repaint();
					panel2.revalidate();
				    } else {
						panel2.remove(jlToolTip);
						panel2.repaint();
						panel2.revalidate();
				    }
				}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}

		private class Q3ChartMouseListener implements ChartMouseListener {
			private JLabel jlToolTip = new JLabel();
			public void chartMouseClicked(ChartMouseEvent event) {
				// No Actions
			}
			public void chartMouseMoved(ChartMouseEvent event) {
				try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				int x = event.getTrigger().getX();
				int y = event.getTrigger().getY();
				ChartEntity entity = event.getEntity();
				if (entity != null) {
				    if ((x >= 60 && x < 190) && (y >= 20 && y < 150)) {
				    	int xPos = 535;
				    	int posOffset = -50;
				    	String displayText = ComponentHelper.getMyToolTip(panel2ActualQ3, panel2ThresholdQ3); 
					if (!displayText.equals("No Data")) {
							xPos = xPos + posOffset;
					} 
					jlToolTip.setText(displayText);
					jlToolTip.setFont(new Font("Verdana", Font.ITALIC, 12));
					jlToolTip.setBounds(xPos, 180, 200, 50);
					panel2.add(jlToolTip);
					panel2.repaint();
					panel2.revalidate();
				    } else {
						panel2.remove(jlToolTip);
						panel2.repaint();
						panel2.revalidate();
				    }
				}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}

		private class Q4ChartMouseListener implements ChartMouseListener {
			private JLabel jlToolTip = new JLabel();
			public void chartMouseClicked(ChartMouseEvent event) {
				// No Actions
			}
			public void chartMouseMoved(ChartMouseEvent event) {
				try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				int x = event.getTrigger().getX();
				int y = event.getTrigger().getY();
				ChartEntity entity = event.getEntity();
				if (entity != null) {
				    if ((x >= 60 && x < 190) && (y >= 20 && y < 150)) {
				    	int xPos = 750;
				    	int posOffset = -50;
				    	String displayText = ComponentHelper.getMyToolTip(panel2ActualQ4, panel2ThresholdQ4); 
					if (!displayText.equals("No Data")) {
							xPos = xPos + posOffset;
					} 
					jlToolTip.setText(displayText);
					jlToolTip.setFont(new Font("Verdana", Font.ITALIC, 12));
					jlToolTip.setBounds(xPos, 180, 200, 50);
					panel2.add(jlToolTip);
					panel2.repaint();
					panel2.revalidate();
				    } else {
						panel2.remove(jlToolTip);
						panel2.repaint();
						panel2.revalidate();
				    }
				}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}

		private class Q5ChartMouseListener implements ChartMouseListener {
			private JLabel jlToolTip = new JLabel();
			public void chartMouseClicked(ChartMouseEvent event) {
				// No Actions
			}
			public void chartMouseMoved(ChartMouseEvent event) {
				try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				int x = event.getTrigger().getX();
				int y = event.getTrigger().getY();
				ChartEntity entity = event.getEntity();
				if (entity != null) {
				    if ((x >= 60 && x < 190) && (y >= 20 && y < 150)) {
			    	int xPos = 210;
			    	int posOffset = -50;
			    	String displayText = ComponentHelper.getMyToolTip(panel2ActualYTD, panel2ThresholdYTD); 
				if (!displayText.equals("No Data")) {
						xPos = xPos + posOffset;
				} 
				jlToolTip.setText(displayText);
				jlToolTip.setFont(new Font("Verdana", Font.ITALIC, 12));
				jlToolTip.setBounds(xPos, 320, 200, 50);
					panel2.add(jlToolTip);
					panel2.repaint();
					panel2.revalidate();
				    } else {
						panel2.remove(jlToolTip);
						panel2.repaint();
						panel2.revalidate();
				    }
				}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}

		private class Q6ChartMouseListener implements ChartMouseListener {
			private JLabel jlToolTip = new JLabel();
			public void chartMouseClicked(ChartMouseEvent event) {
				// No Actions
			}
			public void chartMouseMoved(ChartMouseEvent event) {
				try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				int x = event.getTrigger().getX();
				int y = event.getTrigger().getY();
				ChartEntity entity = event.getEntity();
				if (entity != null) {
				    if ((x >= 60 && x < 190) && (y >= 20 && y < 150)) {
				    	int xPos = 425;
				    	int posOffset = -50;
				    	String displayText = ComponentHelper.getMyToolTip(panel2ActualH1, panel2ThresholdH1); 
					if (!displayText.equals("No Data")) {
							xPos = xPos + posOffset;
					} 
					jlToolTip.setText(displayText);
					jlToolTip.setFont(new Font("Verdana", Font.ITALIC, 12));
					jlToolTip.setBounds(xPos, 320, 200, 50);
					panel2.add(jlToolTip);
					panel2.repaint();
					panel2.revalidate();
				    } else {
						panel2.remove(jlToolTip);
						panel2.repaint();
						panel2.revalidate();
				    }
				}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}
		
		private class Q7ChartMouseListener implements ChartMouseListener {
			private JLabel jlToolTip = new JLabel();
			public void chartMouseClicked(ChartMouseEvent event) {
				// No Actions
			}
			public void chartMouseMoved(ChartMouseEvent event) {
				try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				int x = event.getTrigger().getX();
				int y = event.getTrigger().getY();
				ChartEntity entity = event.getEntity();
				if (entity != null) {
				    if ((x >= 60 && x < 190) && (y >= 20 && y < 150)) {
				    	int xPos = 640;
				    	int posOffset = -50;
				    	String displayText = ComponentHelper.getMyToolTip(panel2ActualH2, panel2ThresholdH2); 
					if (!displayText.equals("No Data")) {
							xPos = xPos + posOffset;
					} 
					jlToolTip.setText(displayText);
					jlToolTip.setFont(new Font("Verdana", Font.ITALIC, 12));
					jlToolTip.setBounds(xPos, 320, 200, 50);
					panel2.add(jlToolTip);
					panel2.repaint();
					panel2.revalidate();
				    } else {
						panel2.remove(jlToolTip);
						panel2.repaint();
						panel2.revalidate();
				    }
				}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}
		

		protected void setPanel2ComponentsReady(double q1Data, double q2Data, double h1Data, double q3Data, 
				double q4Data, double h2Data, double ytdData, double minDialValue, double maxDialValue) {

			jlSalesAchievementTitle = new JLabel("Sales Achievement");
			jlSalesAchievementTitle.setBackground(null);
			jlSalesAchievementTitle.setForeground(Color.DARK_GRAY);
			jlSalesAchievementTitle.setFont(new Font("Arial",Font.BOLD,20));
			jlSalesAchievementTitle.setBounds(375,5,400,25);
			jlSalesAchievementTitle.setOpaque(false);
			panel2.add(jlSalesAchievementTitle);
			
			jlRedMark = new JLabel(redMark);
			jlRedMark.setBackground(null);
			jlRedMark.setBounds(680-20, 5, 20, 20);
			jlRedMark.setOpaque(false);
			panel2.add(jlRedMark);

			jlRedText = new JLabel(GeneralConstants.RED_TEXT);
			jlRedText.setBackground(null);
			jlRedText.setBounds(702-20, 5, 40, 20);
			jlRedText.setForeground(Color.DARK_GRAY);
			jlRedText.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
			jlRedText.setOpaque(false);
			panel2.add(jlRedText);

			jlYellowMark = new JLabel(yellowMark);
			jlYellowMark.setBackground(null);
			jlYellowMark.setBounds(770-20, 5, 20, 20);
			jlYellowMark.setOpaque(false);
			panel2.add(jlYellowMark);

			jlYellowText = new JLabel(GeneralConstants.YELLOW_TEXT);
			jlYellowText.setBackground(null);
			jlYellowText.setBounds(792-20, 5, 70, 20);
			jlYellowText.setForeground(Color.DARK_GRAY);
			jlYellowText.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
			jlYellowText.setOpaque(false);
			panel2.add(jlYellowText);
		    
			jlGreenMark = new JLabel(greenMark);
			jlGreenMark.setBackground(null);
			jlGreenMark.setBounds(860-20, 5, 20, 20);
			jlGreenMark.setOpaque(false);
			panel2.add(jlGreenMark);
		    
			jlGreenText = new JLabel(GeneralConstants.GREEN_TEXT);
			jlGreenText.setBackground(null);
			jlGreenText.setBounds(882-20, 5, 40, 20);
			jlGreenText.setForeground(Color.DARK_GRAY);
			jlGreenText.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
			jlGreenText.setOpaque(false);
			panel2.add(jlGreenText);
			
			
			
			SpeedoMeter meterChart = new SpeedoMeter("");
		    q1DataSet = new DefaultValueDataset((int)Math.round(q1Data));
		    JFreeChart q1Chart = meterChart.createChart(q1DataSet, minDialValue, GeneralConstants.RED_POINT_BOUNDARY,
		    		GeneralConstants.AMBER_POINT_START,GeneralConstants.AMBER_POINT_END,
		    		GeneralConstants.GREEN_POINT_BOUNDARY,maxDialValue);
		    q1Chart.removeLegend();
		    q1Chart.setTitle("Q1 Sales");
		    q1Panel = new ChartPanel(q1Chart);
		    q1Panel.setBounds(2, 40, 250, 160);
		    q1Panel.setBorder(null);
		    q1Panel.setBackground(null);
		    q1Panel.setOpaque(false);
		    q1Panel.addChartMouseListener(new Q1ChartMouseListener());
		    q1Panel.updateUI();
		    panel2.add(q1Panel);

		    q2DataSet = new DefaultValueDataset((int)Math.round(q2Data));
		    JFreeChart q2Chart = meterChart.createChart(q2DataSet, minDialValue, GeneralConstants.RED_POINT_BOUNDARY,
		    		GeneralConstants.AMBER_POINT_START,GeneralConstants.AMBER_POINT_END,
		    		GeneralConstants.GREEN_POINT_BOUNDARY,maxDialValue);
		    q2Chart.removeLegend();
		    q2Chart.setTitle("Q2 Sales");
		    q2Panel = new ChartPanel(q2Chart);
		    q2Panel.setBounds(215, 40, 250, 160);
		    q2Panel.setBorder(null);
		    q2Panel.setBackground(null);
		    q2Panel.setOpaque(false);
		    q2Panel.addChartMouseListener(new Q2ChartMouseListener());
		    q2Panel.updateUI();
		    panel2.add(q2Panel);
		    
		    q3DataSet = new DefaultValueDataset((int)Math.round(q3Data));
		    JFreeChart q3Chart = meterChart.createChart(q3DataSet, minDialValue, GeneralConstants.RED_POINT_BOUNDARY,
		    		GeneralConstants.AMBER_POINT_START,GeneralConstants.AMBER_POINT_END,
		    		GeneralConstants.GREEN_POINT_BOUNDARY,maxDialValue);
		    q3Chart.removeLegend();
		    q3Chart.setTitle("Q3 Sales");
		    q3Panel = new ChartPanel(q3Chart);
		    q3Panel.setBounds(430, 40, 250, 160);
		    q3Panel.setBorder(null);
		    q3Panel.setBackground(null);
		    q3Panel.setOpaque(false);
		    q3Panel.addChartMouseListener(new Q3ChartMouseListener());
		    q3Panel.updateUI();
		    panel2.add(q3Panel);

		    q4DataSet = new DefaultValueDataset((int)Math.round(q4Data));
		    JFreeChart q4Chart = meterChart.createChart(q4DataSet, minDialValue, GeneralConstants.RED_POINT_BOUNDARY,
		    		GeneralConstants.AMBER_POINT_START,GeneralConstants.AMBER_POINT_END,
		    		GeneralConstants.GREEN_POINT_BOUNDARY,maxDialValue);
		    q4Chart.removeLegend();
		    q4Chart.setTitle("Q4 Sales");
		    q4Panel = new ChartPanel(q4Chart);
		    q4Panel.setBounds(645, 40, 250, 160);
		    q4Panel.setBorder(null);
		    q4Panel.setBackground(null);
		    q4Panel.setOpaque(false);
		    q4Panel.addChartMouseListener(new Q4ChartMouseListener());
		    q4Panel.updateUI();
		    panel2.add(q4Panel);

		    q5DataSet = new DefaultValueDataset((int)Math.round(ytdData));
		    JFreeChart q5Chart = meterChart.createChart(q5DataSet, minDialValue, GeneralConstants.RED_POINT_BOUNDARY,
		    		GeneralConstants.AMBER_POINT_START,GeneralConstants.AMBER_POINT_END,
		    		GeneralConstants.GREEN_POINT_BOUNDARY,maxDialValue);
		    q5Chart.removeLegend();
		    q5Chart.setTitle("YTD Sales");
		    q5Panel = new ChartPanel(q5Chart);
		    q5Panel.setBounds(110, 180, 250, 160);
		    q5Panel.setBorder(null);
		    q5Panel.setBackground(null);
		    q5Panel.setOpaque(false);
		    q5Panel.updateUI();
		    q5Panel.addChartMouseListener(new Q5ChartMouseListener());
		    panel2.add(q5Panel);
		    
		    q6DataSet = new DefaultValueDataset((int)Math.round(h1Data));
		    JFreeChart q6Chart = meterChart.createChart(q6DataSet, minDialValue, GeneralConstants.RED_POINT_BOUNDARY,
		    		GeneralConstants.AMBER_POINT_START,GeneralConstants.AMBER_POINT_END,
		    		GeneralConstants.GREEN_POINT_BOUNDARY,maxDialValue);
		    q6Chart.removeLegend();
		    q6Chart.setTitle("H1 Sales");
		    q6Panel = new ChartPanel(q6Chart);
		    q6Panel.setBounds(325, 180, 250, 160);
		    q6Panel.setBorder(null);
		    q6Panel.setBackground(null);
		    q6Panel.setOpaque(false);
		    q6Panel.addChartMouseListener(new Q6ChartMouseListener());
		    q6Panel.updateUI();
		    panel2.add(q6Panel);

		    q7DataSet = new DefaultValueDataset((int)Math.round(h2Data));
		    JFreeChart q7Chart = meterChart.createChart(q7DataSet, minDialValue, GeneralConstants.RED_POINT_BOUNDARY,
		    		GeneralConstants.AMBER_POINT_START,GeneralConstants.AMBER_POINT_END,
		    		GeneralConstants.GREEN_POINT_BOUNDARY,maxDialValue);
		    q7Chart.removeLegend();
		    q7Chart.setTitle("H2 Sales");
		    q7Panel = new ChartPanel(q7Chart);
		    q7Panel.setBounds(540, 180, 250, 160);
		    q7Panel.setBorder(null);
		    q7Panel.setBackground(null);
		    q7Panel.setOpaque(false);
		    q7Panel.addChartMouseListener(new Q7ChartMouseListener());
		    q7Panel.updateUI();
		    panel2.add(q7Panel);
		    
		    
		    meterChart.pack();
		    RefineryUtilities.centerFrameOnScreen(meterChart);
		}

		protected void setPanel3ComponentsReady() {
			final int CHART_X_POSITION_Q1 = 325;
			final int CHART_WIDTH_Q1 = 70;
			final int CHART_Y_POSITION_Q1 = 50+30;
			final int CHART_Y_INCREMENT_Q1 = 35;
			final int CHART_HEIGHT_Q1 = 20;
			final int VALUE_X_POSITION_Q1 = 400;
			final int VALUE_WIDTH_Q1 = 50+10;
			final int VALUE_Y_POSITION_Q1 = 53+30;
			final int VALUE_HEIGHT_Q1 = 15;

			final int INCREMENT_QTOQ = 135;
			
			final int CHART_X_POSITION_Q2 = CHART_X_POSITION_Q1+INCREMENT_QTOQ;
			final int CHART_WIDTH_Q2 = CHART_WIDTH_Q1;
			final int CHART_Y_POSITION_Q2 = CHART_Y_POSITION_Q1;
			final int CHART_Y_INCREMENT_Q2 = CHART_Y_INCREMENT_Q1;
			final int CHART_HEIGHT_Q2 = CHART_HEIGHT_Q1;
			final int VALUE_X_POSITION_Q2 = VALUE_X_POSITION_Q1+INCREMENT_QTOQ;
			final int VALUE_WIDTH_Q2 = VALUE_WIDTH_Q1;
			final int VALUE_Y_POSITION_Q2 = VALUE_Y_POSITION_Q1;
			final int VALUE_HEIGHT_Q2 = VALUE_HEIGHT_Q1;
			
			final int CHART_X_POSITION_H1 = CHART_X_POSITION_Q1+(2*INCREMENT_QTOQ);
			final int CHART_WIDTH_H1 = CHART_WIDTH_Q1;
			final int CHART_Y_POSITION_H1 = CHART_Y_POSITION_Q1;
			final int CHART_Y_INCREMENT_H1 = CHART_Y_INCREMENT_Q1;
			final int CHART_HEIGHT_H1 = CHART_HEIGHT_Q1;
			final int VALUE_X_POSITION_H1 = VALUE_X_POSITION_Q1+(2*INCREMENT_QTOQ);
			final int VALUE_WIDTH_H1 = VALUE_WIDTH_Q1;
			final int VALUE_Y_POSITION_H1 = VALUE_Y_POSITION_Q1;
			final int VALUE_HEIGHT_H1 = VALUE_HEIGHT_Q1;

			final int CHART_X_POSITION_Q3 = CHART_X_POSITION_Q1+(3*INCREMENT_QTOQ);
			final int CHART_WIDTH_Q3 = CHART_WIDTH_Q1;
			final int CHART_Y_POSITION_Q3 = CHART_Y_POSITION_Q1;
			final int CHART_Y_INCREMENT_Q3 = CHART_Y_INCREMENT_Q1;
			final int CHART_HEIGHT_Q3 = CHART_HEIGHT_Q1;
			final int VALUE_X_POSITION_Q3 = VALUE_X_POSITION_Q1+(3*INCREMENT_QTOQ);
			final int VALUE_WIDTH_Q3 = VALUE_WIDTH_Q1;
			final int VALUE_Y_POSITION_Q3 = VALUE_Y_POSITION_Q1;
			final int VALUE_HEIGHT_Q3 = VALUE_HEIGHT_Q1;
			
			final int CHART_X_POSITION_Q4 = CHART_X_POSITION_Q1+(4*INCREMENT_QTOQ);
			final int CHART_WIDTH_Q4 = CHART_WIDTH_Q1;
			final int CHART_Y_POSITION_Q4 = CHART_Y_POSITION_Q1;
			final int CHART_Y_INCREMENT_Q4 = CHART_Y_INCREMENT_Q1;
			final int CHART_HEIGHT_Q4 = CHART_HEIGHT_Q1;
			final int VALUE_X_POSITION_Q4 = VALUE_X_POSITION_Q1+(4*INCREMENT_QTOQ);
			final int VALUE_WIDTH_Q4 = VALUE_WIDTH_Q1;
			final int VALUE_Y_POSITION_Q4 = VALUE_Y_POSITION_Q1;
			final int VALUE_HEIGHT_Q4 = VALUE_HEIGHT_Q1;

			final int CHART_X_POSITION_H2 = CHART_X_POSITION_Q1+(5*INCREMENT_QTOQ);
			final int CHART_WIDTH_H2 = CHART_WIDTH_Q1;
			final int CHART_Y_POSITION_H2 = CHART_Y_POSITION_Q1;
			final int CHART_Y_INCREMENT_H2 = CHART_Y_INCREMENT_Q1;
			final int CHART_HEIGHT_H2 = CHART_HEIGHT_Q1;
			final int VALUE_X_POSITION_H2 = VALUE_X_POSITION_Q1+(5*INCREMENT_QTOQ);
			final int VALUE_WIDTH_H2 = VALUE_WIDTH_Q1;
			final int VALUE_Y_POSITION_H2 = VALUE_Y_POSITION_Q1;
			final int VALUE_HEIGHT_H2 = VALUE_HEIGHT_Q1;
			
			final int CHART_X_POSITION_YTD = CHART_X_POSITION_Q1+(6*INCREMENT_QTOQ);
			final int CHART_WIDTH_YTD = CHART_WIDTH_Q1;
			final int CHART_Y_POSITION_YTD = CHART_Y_POSITION_Q1;
			final int CHART_Y_INCREMENT_YTD = CHART_Y_INCREMENT_Q1;
			final int CHART_HEIGHT_YTD = CHART_HEIGHT_Q1;
			final int VALUE_X_POSITION_YTD = VALUE_X_POSITION_Q1+(6*INCREMENT_QTOQ);
			final int VALUE_WIDTH_YTD = VALUE_WIDTH_Q1;
			final int VALUE_Y_POSITION_YTD = VALUE_Y_POSITION_Q1;
			final int VALUE_HEIGHT_YTD = VALUE_HEIGHT_Q1;

			setPanel3SideHeaders();
			setPanel3MetricQ1(CHART_X_POSITION_Q1,CHART_WIDTH_Q1,CHART_Y_POSITION_Q1,CHART_Y_INCREMENT_Q1, CHART_HEIGHT_Q1,
					VALUE_X_POSITION_Q1,VALUE_WIDTH_Q1,VALUE_Y_POSITION_Q1,VALUE_HEIGHT_Q1);	
			setPanel3MetricQ2(CHART_X_POSITION_Q2,CHART_WIDTH_Q2,CHART_Y_POSITION_Q2,CHART_Y_INCREMENT_Q2, CHART_HEIGHT_Q2,
					VALUE_X_POSITION_Q2,VALUE_WIDTH_Q2,VALUE_Y_POSITION_Q2,VALUE_HEIGHT_Q2);	
			setPanel3MetricH1(CHART_X_POSITION_H1,CHART_WIDTH_H1,CHART_Y_POSITION_H1,CHART_Y_INCREMENT_H1, CHART_HEIGHT_H1,
					VALUE_X_POSITION_H1,VALUE_WIDTH_H1,VALUE_Y_POSITION_H1,VALUE_HEIGHT_H1);	
			setPanel3MetricQ3(CHART_X_POSITION_Q3,CHART_WIDTH_Q3,CHART_Y_POSITION_Q3,CHART_Y_INCREMENT_Q3, CHART_HEIGHT_Q3,
					VALUE_X_POSITION_Q3,VALUE_WIDTH_Q3,VALUE_Y_POSITION_Q3,VALUE_HEIGHT_Q3);	
	/*		setPanel3MetricQ4(CHART_X_POSITION_Q4,CHART_WIDTH_Q4,CHART_Y_POSITION_Q4,CHART_Y_INCREMENT_Q4, CHART_HEIGHT_Q4,
					VALUE_X_POSITION_Q4,VALUE_WIDTH_Q4,VALUE_Y_POSITION_Q4,VALUE_HEIGHT_Q4);	
	*/		setPanel3MetricH2(CHART_X_POSITION_H2,CHART_WIDTH_H2,CHART_Y_POSITION_H2,CHART_Y_INCREMENT_H2, CHART_HEIGHT_H2,
					VALUE_X_POSITION_H2,VALUE_WIDTH_H2,VALUE_Y_POSITION_H2,VALUE_HEIGHT_H2);	
			setPanel3MetricYTD(CHART_X_POSITION_YTD,CHART_WIDTH_YTD,CHART_Y_POSITION_YTD,CHART_Y_INCREMENT_YTD, CHART_HEIGHT_YTD,
					VALUE_X_POSITION_YTD,VALUE_WIDTH_YTD,VALUE_Y_POSITION_YTD,VALUE_HEIGHT_YTD);	
	}
		
		private void setPanel3MetricQ1(final int chartXPosition,final int chartWidth,final int chartYPosition,final int chartYIncrement,
				final int chartHeight,final int valueXPosition,final int valueWidth,final int valueYPosition,final int valueHeight) {
			// Q1 Total Accounts Bar Chart
		    HorizontalBarChartMetric hBarChartTotalAccountsQ1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricTotalAccountsQ1 = hBarChartTotalAccountsQ1.drawChart(q1TotCusDs);
		    ValueAxis rend1Q1 = hbcMetricTotalAccountsQ1.getCategoryPlot().getRangeAxis();
		    rend1Q1.setUpperMargin(0);
		    ChartPanel hbcpMetricTotalAccountsQ1 = new ChartPanel(hbcMetricTotalAccountsQ1);
		    hbcpMetricTotalAccountsQ1.setLayout(null);
		    hbcpMetricTotalAccountsQ1.setBackground(null);
		    hbcpMetricTotalAccountsQ1.setOpaque(false);
		    hbcpMetricTotalAccountsQ1.setBounds(chartXPosition,chartYPosition,chartWidth,(chartHeight+40));
		    hbcpMetricTotalAccountsQ1.setDomainZoomable(true);
		    
		    panel3.add(hbcpMetricTotalAccountsQ1);
		    
		    jlTotalAccountsValueQ1 = new JLabel(new DecimalFormat("####").format(q1TotCus[0][0]));
		    jlTotalAccountsValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTotalAccountsValueQ1.setForeground(Color.blue);
		    jlTotalAccountsValueQ1.setBounds(valueXPosition, valueYPosition, valueWidth, valueHeight);
		    panel3.add(jlTotalAccountsValueQ1);

		    jlAccountPenetrationValueQ1 = new JLabel(new DecimalFormat("####").format(q1CusPen[0][0]));
		    jlAccountPenetrationValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAccountPenetrationValueQ1.setForeground(Color.blue);
		    jlAccountPenetrationValueQ1.setBounds(valueXPosition, valueYPosition+chartYIncrement, valueWidth, valueHeight);
		    panel3.add(jlAccountPenetrationValueQ1);
		    
			// Q1  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartPenetrationQ1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricPenetrationQ1 = hBarChartPenetrationQ1.drawChart(q1PenDs);
		    ValueAxis rend2Q1 = hbcMetricPenetrationQ1.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer2Q1 = (BarRenderer) hbcMetricPenetrationQ1.getCategoryPlot().getRenderer();
		    GradientPaint gp2Q1 = new GradientPaint(0.0f,0.0f,Color.CYAN,0.0f,0.0f,new Color(0,0,125));
		    bRenderer2Q1.setSeriesPaint(0, gp2Q1);
		    rend2Q1.setUpperMargin(0);
		    ChartPanel hbcpMetricPenetrationQ1 = new ChartPanel(hbcMetricPenetrationQ1);
		    hbcpMetricPenetrationQ1.setLayout(null);
		    hbcpMetricPenetrationQ1.setBackground(null);
		    hbcpMetricPenetrationQ1.setOpaque(false);;
		    hbcpMetricPenetrationQ1.setBounds(chartXPosition,(chartYPosition+chartYIncrement)+35,chartWidth,(chartHeight+5));
		    hbcpMetricPenetrationQ1.setDomainZoomable(true);
		    panel3.add(hbcpMetricPenetrationQ1);
		    hBarChartPenetrationQ1.pack();
		    
		    jlTechPenetrationValueQ1 = new JLabel(new DecimalFormat("##.##").format(q1TechPen[0][0]));
		    jlTechPenetrationValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTechPenetrationValueQ1.setForeground(Color.blue);
		    jlTechPenetrationValueQ1.setBounds(valueXPosition, valueYPosition+(2*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlTechPenetrationValueQ1);
		    
		    // Q1  Discount Bar Chart
		    HorizontalBarChartMetric hBarChartDiscountQ1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricDiscountQ1 = hBarChartDiscountQ1.drawChart(q1DiscountDs);
		    ValueAxis rend3Q1 = hbcMetricDiscountQ1.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer3Q1 = (BarRenderer) hbcMetricDiscountQ1.getCategoryPlot().getRenderer();
		    GradientPaint gp3Q1 = new GradientPaint(0.0f,0.0f,Color.magenta,0.0f,0.0f,new Color(0,0,64));
		    bRenderer3Q1.setSeriesPaint(0, gp3Q1);
		    rend3Q1.setUpperMargin(0);
		    ChartPanel hbcpMetricDiscountQ1 = new ChartPanel(hbcMetricDiscountQ1);
		    hbcpMetricDiscountQ1.setLayout(null);
		    hbcpMetricDiscountQ1.setBackground(null);
		    hbcpMetricDiscountQ1.setOpaque(false);
//		    hbcpMetricDiscountQ1.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+25,chartWidth,chartHeight+130);
		    hbcpMetricDiscountQ1.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+15,chartWidth,chartHeight+175);
		    hbcpMetricDiscountQ1.setDomainZoomable(true);
		    panel3.add(hbcpMetricDiscountQ1);
		    
		    jlBNDiscountValueQ1 = new JLabel(new DecimalFormat("##.#%").format(q1DiscountBN[0][0]));
		    jlBNDiscountValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlBNDiscountValueQ1.setForeground(Color.blue);
		    jlBNDiscountValueQ1.setBounds(valueXPosition, valueYPosition+(3*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlBNDiscountValueQ1);

		    jlSecDiscountValueQ1 = new JLabel(new DecimalFormat("##.#%").format(q1DiscountSec[0][0]));
		    jlSecDiscountValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlSecDiscountValueQ1.setForeground(Color.blue);
		    jlSecDiscountValueQ1.setBounds(valueXPosition, valueYPosition+(4*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlSecDiscountValueQ1);
		    
		    jlCollDiscountValueQ1 = new JLabel(new DecimalFormat("##.#%").format(q1DiscountColl[0][0]));
		    jlCollDiscountValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCollDiscountValueQ1.setForeground(Color.blue);
		    jlCollDiscountValueQ1.setBounds(valueXPosition, valueYPosition+(5*chartYIncrement)+3, valueWidth, valueHeight);
		    panel3.add(jlCollDiscountValueQ1);
		    
		    jlDCVDiscountValueQ1 = new JLabel(new DecimalFormat("##.#%").format(q1DiscountDCV[0][0]));
		    jlDCVDiscountValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlDCVDiscountValueQ1.setForeground(Color.blue);
		    jlDCVDiscountValueQ1.setBounds(valueXPosition, valueYPosition+(6*chartYIncrement)+2, valueWidth, valueHeight);
		    panel3.add(jlDCVDiscountValueQ1);

		    jlOverallDiscountValueQ1 = new JLabel(new DecimalFormat("##.#%").format(q1DiscountAll[0][0]));
		    jlOverallDiscountValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlOverallDiscountValueQ1.setForeground(Color.blue);
		    jlOverallDiscountValueQ1.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlOverallDiscountValueQ1);

			// Q1  Avg Yield Bar Chart
		    HorizontalBarChartMetric hBarChartYldQ1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricYldQ1 = hBarChartYldQ1.drawChart(q1YldDs);
		    ValueAxis rend4Q1 = hbcMetricYldQ1.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer4Q1 = (BarRenderer) hbcMetricYldQ1.getCategoryPlot().getRenderer();
		    GradientPaint gp4Q1 = new GradientPaint(0.0f,0.0f,Color.green,0.0f,0.0f,new Color(0,0,64));
		    bRenderer4Q1.setSeriesPaint(0, gp4Q1);
		    rend4Q1.setUpperMargin(0);
		    ChartPanel hbcpMetricYldQ1 = new ChartPanel(hbcMetricYldQ1);
		    hbcpMetricYldQ1.setLayout(null);
		    hbcpMetricYldQ1.setBackground(null);
		    hbcpMetricYldQ1.setOpaque(false);;
//		    hbcpMetricYldQ1.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement),chartWidth,chartHeight+40);
		    hbcpMetricYldQ1.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricYldQ1.setDomainZoomable(true);
		    panel3.add(hbcpMetricYldQ1);

		    jlAvgYldPerAccountsValueQ1 = new JLabel(new DecimalFormat("$#####.##").format(q1YldPerCus[0][0]));
		    jlAvgYldPerAccountsValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerAccountsValueQ1.setForeground(Color.blue);
		    jlAvgYldPerAccountsValueQ1.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerAccountsValueQ1);

		    jlAvgYldPerOppValueQ1 = new JLabel(new DecimalFormat("$#####.##").format(q1YldPerOpp[0][0]));
		    jlAvgYldPerOppValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerOppValueQ1.setForeground(Color.blue);
		    jlAvgYldPerOppValueQ1.setBounds(valueXPosition, valueYPosition+(8*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerOppValueQ1);

			// Q1  CWP and Achievement Bar Chart
		    HorizontalBarChartMetric hBarChartCWPAchievementQ1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricCWPAchievementQ1 = hBarChartCWPAchievementQ1.drawChart(q1CWPAchievementDs);
		    ValueAxis rend5Q1 = hbcMetricCWPAchievementQ1.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer5Q1 = (BarRenderer) hbcMetricCWPAchievementQ1.getCategoryPlot().getRenderer();
		    GradientPaint gp5Q1 = new GradientPaint(0.0f,0.0f,Color.BLUE,0.0f,0.0f,new Color(0,0,64));
		    bRenderer5Q1.setSeriesPaint(0, gp5Q1);
		    rend5Q1.setUpperMargin(0);
		    ChartPanel hbcpMetricCWPAchievementQ1 = new ChartPanel(hbcMetricCWPAchievementQ1);
		    hbcpMetricCWPAchievementQ1.setLayout(null);
		    hbcpMetricCWPAchievementQ1.setBackground(null);
		    hbcpMetricCWPAchievementQ1.setOpaque(false);;
		    hbcpMetricCWPAchievementQ1.setBounds(chartXPosition,chartYPosition+(9*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricCWPAchievementQ1.setDomainZoomable(true);
		    panel3.add(hbcpMetricCWPAchievementQ1);

		    jlAchievementValueQ1 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(q1Achievement[0][0])));
		    jlAchievementValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAchievementValueQ1.setForeground(Color.blue);
		    jlAchievementValueQ1.setBounds(valueXPosition, valueYPosition+(9*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAchievementValueQ1);

		    jlCWPValueQ1 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(q1CWP[0][0])));
		    jlCWPValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCWPValueQ1.setForeground(Color.blue);
		    jlCWPValueQ1.setBounds(valueXPosition, valueYPosition+(10*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlCWPValueQ1);
		
			// Q1  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartAOCQ1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricAOCQ1 = hBarChartAOCQ1.drawChart(q1AOCDs);
		    ValueAxis rend6Q1 = hbcMetricAOCQ1.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer6Q1 = (BarRenderer) hbcMetricAOCQ1.getCategoryPlot().getRenderer();
		    GradientPaint gp6Q1 = new GradientPaint(0.0f,0.0f,Color.RED,0.0f,0.0f,new Color(125,0,0));
		    bRenderer6Q1.setSeriesPaint(0, gp6Q1);
		    rend6Q1.setUpperMargin(0);
		    ChartPanel hbcpMetricAOCQ1 = new ChartPanel(hbcMetricAOCQ1);
		    hbcpMetricAOCQ1.setLayout(null);
		    hbcpMetricAOCQ1.setBackground(null);
		    hbcpMetricAOCQ1.setOpaque(false);;
		    hbcpMetricAOCQ1.setBounds(chartXPosition,chartYPosition+(11*chartYIncrement)+35,chartWidth,chartHeight);
		    hbcpMetricAOCQ1.setDomainZoomable(true);
		    panel3.add(hbcpMetricAOCQ1);
		    
		    jlAOCValueQ1 = new JLabel(new DecimalFormat("###.#%").format(q1AOC[0][0]/100));
		    jlAOCValueQ1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAOCValueQ1.setForeground(Color.blue);
		    jlAOCValueQ1.setBounds(valueXPosition, valueYPosition+(11*chartYIncrement)+30, valueWidth, chartHeight);
		    panel3.add(jlAOCValueQ1);
		
		}

		private void setPanel3MetricQ2(final int chartXPosition,final int chartWidth,final int chartYPosition,final int chartYIncrement,
				final int chartHeight,final int valueXPosition,final int valueWidth,final int valueYPosition,final int valueHeight) {
			// Q2 Total Accounts Bar Chart
		    HorizontalBarChartMetric hBarChartTotalAccountsQ2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricTotalAccountsQ2 = hBarChartTotalAccountsQ2.drawChart(q2TotCusDs);
		    ValueAxis rend1Q2 = hbcMetricTotalAccountsQ2.getCategoryPlot().getRangeAxis();
		    rend1Q2.setUpperMargin(0);
		    ChartPanel hbcpMetricTotalAccountsQ2 = new ChartPanel(hbcMetricTotalAccountsQ2);
		    hbcpMetricTotalAccountsQ2.setLayout(null);
		    hbcpMetricTotalAccountsQ2.setBackground(null);
		    hbcpMetricTotalAccountsQ2.setOpaque(false);
		    hbcpMetricTotalAccountsQ2.setBounds(chartXPosition,chartYPosition,chartWidth,(chartHeight+40));
		    hbcpMetricTotalAccountsQ2.setDomainZoomable(true);
		    
		    panel3.add(hbcpMetricTotalAccountsQ2);
		    
		    jlTotalAccountsValueQ2 = new JLabel(new DecimalFormat("####").format(q2TotCus[0][0]));
		    jlTotalAccountsValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTotalAccountsValueQ2.setForeground(Color.blue);
		    jlTotalAccountsValueQ2.setBounds(valueXPosition, valueYPosition, valueWidth, valueHeight);
		    panel3.add(jlTotalAccountsValueQ2);

		    jlAccountPenetrationValueQ2 = new JLabel(new DecimalFormat("####").format(q2CusPen[0][0]));
		    jlAccountPenetrationValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAccountPenetrationValueQ2.setForeground(Color.blue);
		    jlAccountPenetrationValueQ2.setBounds(valueXPosition, valueYPosition+chartYIncrement, valueWidth, valueHeight);
		    panel3.add(jlAccountPenetrationValueQ2);
		    
			// Q2  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartPenetrationQ2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricPenetrationQ2 = hBarChartPenetrationQ2.drawChart(q2PenDs);
		    ValueAxis rend2Q2 = hbcMetricPenetrationQ2.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer2Q2 = (BarRenderer) hbcMetricPenetrationQ2.getCategoryPlot().getRenderer();
		    GradientPaint gp2Q2 = new GradientPaint(0.0f,0.0f,Color.CYAN,0.0f,0.0f,new Color(0,0,125));
		    bRenderer2Q2.setSeriesPaint(0, gp2Q2);
		    rend2Q2.setUpperMargin(0);
		    ChartPanel hbcpMetricPenetrationQ2 = new ChartPanel(hbcMetricPenetrationQ2);
		    hbcpMetricPenetrationQ2.setLayout(null);
		    hbcpMetricPenetrationQ2.setBackground(null);
		    hbcpMetricPenetrationQ2.setOpaque(false);;
		    hbcpMetricPenetrationQ2.setBounds(chartXPosition,(chartYPosition+chartYIncrement)+35,chartWidth,(chartHeight+5));
		    hbcpMetricPenetrationQ2.setDomainZoomable(true);
		    panel3.add(hbcpMetricPenetrationQ2);
		    hBarChartPenetrationQ2.pack();
		    
		    jlTechPenetrationValueQ2 = new JLabel(new DecimalFormat("##.##").format(q2TechPen[0][0]));
		    jlTechPenetrationValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTechPenetrationValueQ2.setForeground(Color.blue);
		    jlTechPenetrationValueQ2.setBounds(valueXPosition, valueYPosition+(2*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlTechPenetrationValueQ2);
		    
		    // Q2  Discount Bar Chart
		    HorizontalBarChartMetric hBarChartDiscountQ2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricDiscountQ2 = hBarChartDiscountQ2.drawChart(q2DiscountDs);
		    ValueAxis rend3Q2 = hbcMetricDiscountQ2.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer3Q2 = (BarRenderer) hbcMetricDiscountQ2.getCategoryPlot().getRenderer();
		    GradientPaint gp3Q2 = new GradientPaint(0.0f,0.0f,Color.magenta,0.0f,0.0f,new Color(0,0,64));
		    bRenderer3Q2.setSeriesPaint(0, gp3Q2);
		    rend3Q2.setUpperMargin(0);
		    ChartPanel hbcpMetricDiscountQ2 = new ChartPanel(hbcMetricDiscountQ2);
		    hbcpMetricDiscountQ2.setLayout(null);
		    hbcpMetricDiscountQ2.setBackground(null);
		    hbcpMetricDiscountQ2.setOpaque(false);
//		    hbcpMetricDiscountQ2.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+25,chartWidth,chartHeight+130);
		    hbcpMetricDiscountQ2.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+15,chartWidth,chartHeight+175);
		    hbcpMetricDiscountQ2.setDomainZoomable(true);
		    panel3.add(hbcpMetricDiscountQ2);
		    
		    jlBNDiscountValueQ2 = new JLabel(new DecimalFormat("##.#%").format(q2DiscountBN[0][0]));
		    jlBNDiscountValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlBNDiscountValueQ2.setForeground(Color.blue);
		    jlBNDiscountValueQ2.setBounds(valueXPosition, valueYPosition+(3*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlBNDiscountValueQ2);

		    jlSecDiscountValueQ2 = new JLabel(new DecimalFormat("##.#%").format(q2DiscountSec[0][0]));
		    jlSecDiscountValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlSecDiscountValueQ2.setForeground(Color.blue);
		    jlSecDiscountValueQ2.setBounds(valueXPosition, valueYPosition+(4*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlSecDiscountValueQ2);
		    
		    jlCollDiscountValueQ2 = new JLabel(new DecimalFormat("##.#%").format(q2DiscountColl[0][0]));
		    jlCollDiscountValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCollDiscountValueQ2.setForeground(Color.blue);
		    jlCollDiscountValueQ2.setBounds(valueXPosition, valueYPosition+(5*chartYIncrement)+3, valueWidth, valueHeight);
		    panel3.add(jlCollDiscountValueQ2);
		    
		    jlDCVDiscountValueQ2 = new JLabel(new DecimalFormat("##.#%").format(q2DiscountDCV[0][0]));
		    jlDCVDiscountValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlDCVDiscountValueQ2.setForeground(Color.blue);
		    jlDCVDiscountValueQ2.setBounds(valueXPosition, valueYPosition+(6*chartYIncrement)+2, valueWidth, valueHeight);
		    panel3.add(jlDCVDiscountValueQ2);

		    jlOverallDiscountValueQ2 = new JLabel(new DecimalFormat("##.#%").format(q2DiscountAll[0][0]));
		    jlOverallDiscountValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlOverallDiscountValueQ2.setForeground(Color.blue);
		    jlOverallDiscountValueQ2.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlOverallDiscountValueQ2);

			// Q2  Avg Yield Bar Chart
		    HorizontalBarChartMetric hBarChartYldQ2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricYldQ2 = hBarChartYldQ2.drawChart(q2YldDs);
		    ValueAxis rend4Q2 = hbcMetricYldQ2.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer4Q2 = (BarRenderer) hbcMetricYldQ2.getCategoryPlot().getRenderer();
		    GradientPaint gp4Q2 = new GradientPaint(0.0f,0.0f,Color.green,0.0f,0.0f,new Color(0,0,64));
		    bRenderer4Q2.setSeriesPaint(0, gp4Q2);
		    rend4Q2.setUpperMargin(0);
		    ChartPanel hbcpMetricYldQ2 = new ChartPanel(hbcMetricYldQ2);
		    hbcpMetricYldQ2.setLayout(null);
		    hbcpMetricYldQ2.setBackground(null);
		    hbcpMetricYldQ2.setOpaque(false);;
//		    hbcpMetricYldQ2.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement),chartWidth,chartHeight+40);
		    hbcpMetricYldQ2.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricYldQ2.setDomainZoomable(true);
		    panel3.add(hbcpMetricYldQ2);

		    jlAvgYldPerAccountsValueQ2 = new JLabel(new DecimalFormat("$#####.##").format(q2YldPerCus[0][0]));
		    jlAvgYldPerAccountsValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerAccountsValueQ2.setForeground(Color.blue);
		    jlAvgYldPerAccountsValueQ2.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerAccountsValueQ2);

		    jlAvgYldPerOppValueQ2 = new JLabel(new DecimalFormat("$#####.##").format(q2YldPerOpp[0][0]));
		    jlAvgYldPerOppValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerOppValueQ2.setForeground(Color.blue);
		    jlAvgYldPerOppValueQ2.setBounds(valueXPosition, valueYPosition+(8*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerOppValueQ2);

			// Q2  CWP and Achievement Bar Chart
		    HorizontalBarChartMetric hBarChartCWPAchievementQ2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricCWPAchievementQ2 = hBarChartCWPAchievementQ2.drawChart(q2CWPAchievementDs);
		    ValueAxis rend5Q2 = hbcMetricCWPAchievementQ2.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer5Q2 = (BarRenderer) hbcMetricCWPAchievementQ2.getCategoryPlot().getRenderer();
		    GradientPaint gp5Q2 = new GradientPaint(0.0f,0.0f,Color.BLUE,0.0f,0.0f,new Color(0,0,64));
		    bRenderer5Q2.setSeriesPaint(0, gp5Q2);
		    rend5Q2.setUpperMargin(0);
		    ChartPanel hbcpMetricCWPAchievementQ2 = new ChartPanel(hbcMetricCWPAchievementQ2);
		    hbcpMetricCWPAchievementQ2.setLayout(null);
		    hbcpMetricCWPAchievementQ2.setBackground(null);
		    hbcpMetricCWPAchievementQ2.setOpaque(false);;
		    hbcpMetricCWPAchievementQ2.setBounds(chartXPosition,chartYPosition+(9*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricCWPAchievementQ2.setDomainZoomable(true);
		    panel3.add(hbcpMetricCWPAchievementQ2);

		    jlAchievementValueQ2 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(q2Achievement[0][0])));
		    jlAchievementValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAchievementValueQ2.setForeground(Color.blue);
		    jlAchievementValueQ2.setBounds(valueXPosition, valueYPosition+(9*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAchievementValueQ2);

		    jlCWPValueQ2 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(q2CWP[0][0])));
		    jlCWPValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCWPValueQ2.setForeground(Color.blue);
		    jlCWPValueQ2.setBounds(valueXPosition, valueYPosition+(10*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlCWPValueQ2);
		
			// Q2  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartAOCQ2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricAOCQ2 = hBarChartAOCQ2.drawChart(q2AOCDs);
		    ValueAxis rend6Q2 = hbcMetricAOCQ2.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer6Q2 = (BarRenderer) hbcMetricAOCQ2.getCategoryPlot().getRenderer();
		    GradientPaint gp6Q2 = new GradientPaint(0.0f,0.0f,Color.RED,0.0f,0.0f,new Color(125,0,0));
		    bRenderer6Q2.setSeriesPaint(0, gp6Q2);
		    rend6Q2.setUpperMargin(0);
		    ChartPanel hbcpMetricAOCQ2 = new ChartPanel(hbcMetricAOCQ2);
		    hbcpMetricAOCQ2.setLayout(null);
		    hbcpMetricAOCQ2.setBackground(null);
		    hbcpMetricAOCQ2.setOpaque(false);;
		    hbcpMetricAOCQ2.setBounds(chartXPosition,chartYPosition+(11*chartYIncrement)+35,chartWidth,chartHeight);
		    hbcpMetricAOCQ2.setDomainZoomable(true);
		    panel3.add(hbcpMetricAOCQ2);
		    
		    jlAOCValueQ2 = new JLabel(new DecimalFormat("###.#%").format(q2AOC[0][0]/100));
		    jlAOCValueQ2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAOCValueQ2.setForeground(Color.blue);
		    jlAOCValueQ2.setBounds(valueXPosition, valueYPosition+(11*chartYIncrement)+30, valueWidth, chartHeight);
		    panel3.add(jlAOCValueQ2);
		
		}

		private void setPanel3MetricH1(final int chartXPosition,final int chartWidth,final int chartYPosition,final int chartYIncrement,
				final int chartHeight,final int valueXPosition,final int valueWidth,final int valueYPosition,final int valueHeight) {
			// H1 Total Accounts Bar Chart
		    HorizontalBarChartMetric hBarChartTotalAccountsH1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricTotalAccountsH1 = hBarChartTotalAccountsH1.drawChart(h1TotCusDs);
		    ValueAxis rend1H1 = hbcMetricTotalAccountsH1.getCategoryPlot().getRangeAxis();
		    rend1H1.setUpperMargin(0);
		    ChartPanel hbcpMetricTotalAccountsH1 = new ChartPanel(hbcMetricTotalAccountsH1);
		    hbcpMetricTotalAccountsH1.setLayout(null);
		    hbcpMetricTotalAccountsH1.setBackground(null);
		    hbcpMetricTotalAccountsH1.setOpaque(false);
		    hbcpMetricTotalAccountsH1.setBounds(chartXPosition,chartYPosition,chartWidth,(chartHeight+40));
		    hbcpMetricTotalAccountsH1.setDomainZoomable(true);
		    
		    panel3.add(hbcpMetricTotalAccountsH1);
		    
		    jlTotalAccountsValueH1 = new JLabel(new DecimalFormat("####").format(h1TotCus[0][0]));
		    jlTotalAccountsValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTotalAccountsValueH1.setForeground(Color.blue);
		    jlTotalAccountsValueH1.setBounds(valueXPosition, valueYPosition, valueWidth, valueHeight);
		    panel3.add(jlTotalAccountsValueH1);

		    jlAccountPenetrationValueH1 = new JLabel(new DecimalFormat("####").format(h1CusPen[0][0]));
		    jlAccountPenetrationValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAccountPenetrationValueH1.setForeground(Color.blue);
		    jlAccountPenetrationValueH1.setBounds(valueXPosition, valueYPosition+chartYIncrement, valueWidth, valueHeight);
		    panel3.add(jlAccountPenetrationValueH1);
		    
			// H1  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartPenetrationH1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricPenetrationH1 = hBarChartPenetrationH1.drawChart(h1PenDs);
		    ValueAxis rend2H1 = hbcMetricPenetrationH1.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer2H1 = (BarRenderer) hbcMetricPenetrationH1.getCategoryPlot().getRenderer();
		    GradientPaint gp2H1 = new GradientPaint(0.0f,0.0f,Color.CYAN,0.0f,0.0f,new Color(0,0,125));
		    bRenderer2H1.setSeriesPaint(0, gp2H1);
		    rend2H1.setUpperMargin(0);
		    ChartPanel hbcpMetricPenetrationH1 = new ChartPanel(hbcMetricPenetrationH1);
		    hbcpMetricPenetrationH1.setLayout(null);
		    hbcpMetricPenetrationH1.setBackground(null);
		    hbcpMetricPenetrationH1.setOpaque(false);;
		    hbcpMetricPenetrationH1.setBounds(chartXPosition,(chartYPosition+chartYIncrement)+35,chartWidth,(chartHeight+5));
		    hbcpMetricPenetrationH1.setDomainZoomable(true);
		    panel3.add(hbcpMetricPenetrationH1);
		    hBarChartPenetrationH1.pack();
		    
		    jlTechPenetrationValueH1 = new JLabel(new DecimalFormat("##.##").format(h1TechPen[0][0]));
		    jlTechPenetrationValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTechPenetrationValueH1.setForeground(Color.blue);
		    jlTechPenetrationValueH1.setBounds(valueXPosition, valueYPosition+(2*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlTechPenetrationValueH1);
		    
		    // H1  Discount Bar Chart
		    HorizontalBarChartMetric hBarChartDiscountH1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricDiscountH1 = hBarChartDiscountH1.drawChart(h1DiscountDs);
		    ValueAxis rend3H1 = hbcMetricDiscountH1.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer3H1 = (BarRenderer) hbcMetricDiscountH1.getCategoryPlot().getRenderer();
		    GradientPaint gp3H1 = new GradientPaint(0.0f,0.0f,Color.magenta,0.0f,0.0f,new Color(0,0,64));
		    bRenderer3H1.setSeriesPaint(0, gp3H1);
		    rend3H1.setUpperMargin(0);
		    ChartPanel hbcpMetricDiscountH1 = new ChartPanel(hbcMetricDiscountH1);
		    hbcpMetricDiscountH1.setLayout(null);
		    hbcpMetricDiscountH1.setBackground(null);
		    hbcpMetricDiscountH1.setOpaque(false);
//		    hbcpMetricDiscountH1.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+25,chartWidth,chartHeight+130);
		    hbcpMetricDiscountH1.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+15,chartWidth,chartHeight+175);
		    hbcpMetricDiscountH1.setDomainZoomable(true);
		    panel3.add(hbcpMetricDiscountH1);
		    
		    jlBNDiscountValueH1 = new JLabel(new DecimalFormat("##.#%").format(h1DiscountBN[0][0]));
		    jlBNDiscountValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlBNDiscountValueH1.setForeground(Color.blue);
		    jlBNDiscountValueH1.setBounds(valueXPosition, valueYPosition+(3*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlBNDiscountValueH1);

		    jlSecDiscountValueH1 = new JLabel(new DecimalFormat("##.#%").format(h1DiscountSec[0][0]));
		    jlSecDiscountValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlSecDiscountValueH1.setForeground(Color.blue);
		    jlSecDiscountValueH1.setBounds(valueXPosition, valueYPosition+(4*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlSecDiscountValueH1);
		    
		    jlCollDiscountValueH1 = new JLabel(new DecimalFormat("##.#%").format(h1DiscountColl[0][0]));
		    jlCollDiscountValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCollDiscountValueH1.setForeground(Color.blue);
		    jlCollDiscountValueH1.setBounds(valueXPosition, valueYPosition+(5*chartYIncrement)+3, valueWidth, valueHeight);
		    panel3.add(jlCollDiscountValueH1);
		    
		    jlDCVDiscountValueH1 = new JLabel(new DecimalFormat("##.#%").format(h1DiscountDCV[0][0]));
		    jlDCVDiscountValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlDCVDiscountValueH1.setForeground(Color.blue);
		    jlDCVDiscountValueH1.setBounds(valueXPosition, valueYPosition+(6*chartYIncrement)+2, valueWidth, valueHeight);
		    panel3.add(jlDCVDiscountValueH1);

		    jlOverallDiscountValueH1 = new JLabel(new DecimalFormat("##.#%").format(h1DiscountAll[0][0]));
		    jlOverallDiscountValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlOverallDiscountValueH1.setForeground(Color.blue);
		    jlOverallDiscountValueH1.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlOverallDiscountValueH1);

			// H1  Avg Yield Bar Chart
		    HorizontalBarChartMetric hBarChartYldH1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricYldH1 = hBarChartYldH1.drawChart(h1YldDs);
		    ValueAxis rend4H1 = hbcMetricYldH1.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer4H1 = (BarRenderer) hbcMetricYldH1.getCategoryPlot().getRenderer();
		    GradientPaint gp4H1 = new GradientPaint(0.0f,0.0f,Color.green,0.0f,0.0f,new Color(0,0,64));
		    bRenderer4H1.setSeriesPaint(0, gp4H1);
		    rend4H1.setUpperMargin(0);
		    ChartPanel hbcpMetricYldH1 = new ChartPanel(hbcMetricYldH1);
		    hbcpMetricYldH1.setLayout(null);
		    hbcpMetricYldH1.setBackground(null);
		    hbcpMetricYldH1.setOpaque(false);;
//		    hbcpMetricYldH1.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement),chartWidth,chartHeight+40);
		    hbcpMetricYldH1.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricYldH1.setDomainZoomable(true);
		    panel3.add(hbcpMetricYldH1);

		    jlAvgYldPerAccountsValueH1 = new JLabel(new DecimalFormat("$#####.##").format(h1YldPerCus[0][0]));
		    jlAvgYldPerAccountsValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerAccountsValueH1.setForeground(Color.blue);
		    jlAvgYldPerAccountsValueH1.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerAccountsValueH1);

		    jlAvgYldPerOppValueH1 = new JLabel(new DecimalFormat("$#####.##").format(h1YldPerOpp[0][0]));
		    jlAvgYldPerOppValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerOppValueH1.setForeground(Color.blue);
		    jlAvgYldPerOppValueH1.setBounds(valueXPosition, valueYPosition+(8*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerOppValueH1);

			// H1  CWP and Achievement Bar Chart
		    HorizontalBarChartMetric hBarChartCWPAchievementH1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricCWPAchievementH1 = hBarChartCWPAchievementH1.drawChart(h1CWPAchievementDs);
		    ValueAxis rend5H1 = hbcMetricCWPAchievementH1.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer5H1 = (BarRenderer) hbcMetricCWPAchievementH1.getCategoryPlot().getRenderer();
		    GradientPaint gp5H1 = new GradientPaint(0.0f,0.0f,Color.BLUE,0.0f,0.0f,new Color(0,0,64));
		    bRenderer5H1.setSeriesPaint(0, gp5H1);
		    rend5H1.setUpperMargin(0);
		    ChartPanel hbcpMetricCWPAchievementH1 = new ChartPanel(hbcMetricCWPAchievementH1);
		    hbcpMetricCWPAchievementH1.setLayout(null);
		    hbcpMetricCWPAchievementH1.setBackground(null);
		    hbcpMetricCWPAchievementH1.setOpaque(false);;
		    hbcpMetricCWPAchievementH1.setBounds(chartXPosition,chartYPosition+(9*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricCWPAchievementH1.setDomainZoomable(true);
		    panel3.add(hbcpMetricCWPAchievementH1);

		    jlAchievementValueH1 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(h1Achievement[0][0])));
		    jlAchievementValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAchievementValueH1.setForeground(Color.blue);
		    jlAchievementValueH1.setBounds(valueXPosition, valueYPosition+(9*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAchievementValueH1);

		    jlCWPValueH1 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(h1CWP[0][0])));
		    jlCWPValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCWPValueH1.setForeground(Color.blue);
		    jlCWPValueH1.setBounds(valueXPosition, valueYPosition+(10*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlCWPValueH1);
		
			// H1  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartAOCH1 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricAOCH1 = hBarChartAOCH1.drawChart(h1AOCDs);
		    ValueAxis rend6H1 = hbcMetricAOCH1.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer6H1 = (BarRenderer) hbcMetricAOCH1.getCategoryPlot().getRenderer();
		    GradientPaint gp6H1 = new GradientPaint(0.0f,0.0f,Color.RED,0.0f,0.0f,new Color(125,0,0));
		    bRenderer6H1.setSeriesPaint(0, gp6H1);
		    rend6H1.setUpperMargin(0);
		    ChartPanel hbcpMetricAOCH1 = new ChartPanel(hbcMetricAOCH1);
		    hbcpMetricAOCH1.setLayout(null);
		    hbcpMetricAOCH1.setBackground(null);
		    hbcpMetricAOCH1.setOpaque(false);;
		    hbcpMetricAOCH1.setBounds(chartXPosition,chartYPosition+(11*chartYIncrement)+35,chartWidth,chartHeight);
		    hbcpMetricAOCH1.setDomainZoomable(true);
		    panel3.add(hbcpMetricAOCH1);
		    
		    jlAOCValueH1 = new JLabel(new DecimalFormat("###.#%").format(h1AOC[0][0]/100));
		    jlAOCValueH1.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAOCValueH1.setForeground(Color.blue);
		    jlAOCValueH1.setBounds(valueXPosition, valueYPosition+(11*chartYIncrement)+30, valueWidth, chartHeight);
		    panel3.add(jlAOCValueH1);
		
		}

		private void setPanel3MetricQ3(final int chartXPosition,final int chartWidth,final int chartYPosition,final int chartYIncrement,
				final int chartHeight,final int valueXPosition,final int valueWidth,final int valueYPosition,final int valueHeight) {
			// Q3 Total Accounts Bar Chart
		    HorizontalBarChartMetric hBarChartTotalAccountsQ3 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricTotalAccountsQ3 = hBarChartTotalAccountsQ3.drawChart(q3TotCusDs);
		    ValueAxis rend1Q3 = hbcMetricTotalAccountsQ3.getCategoryPlot().getRangeAxis();
		    rend1Q3.setUpperMargin(0);
		    ChartPanel hbcpMetricTotalAccountsQ3 = new ChartPanel(hbcMetricTotalAccountsQ3);
		    hbcpMetricTotalAccountsQ3.setLayout(null);
		    hbcpMetricTotalAccountsQ3.setBackground(null);
		    hbcpMetricTotalAccountsQ3.setOpaque(false);
		    hbcpMetricTotalAccountsQ3.setBounds(chartXPosition,chartYPosition,chartWidth,(chartHeight+40));
		    hbcpMetricTotalAccountsQ3.setDomainZoomable(true);
		    
		    panel3.add(hbcpMetricTotalAccountsQ3);
		    
		    jlTotalAccountsValueQ3 = new JLabel(new DecimalFormat("####").format(q3TotCus[0][0]));
		    jlTotalAccountsValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTotalAccountsValueQ3.setForeground(Color.blue);
		    jlTotalAccountsValueQ3.setBounds(valueXPosition, valueYPosition, valueWidth, valueHeight);
		    panel3.add(jlTotalAccountsValueQ3);

		    jlAccountPenetrationValueQ3 = new JLabel(new DecimalFormat("####").format(q3CusPen[0][0]));
		    jlAccountPenetrationValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAccountPenetrationValueQ3.setForeground(Color.blue);
		    jlAccountPenetrationValueQ3.setBounds(valueXPosition, valueYPosition+chartYIncrement, valueWidth, valueHeight);
		    panel3.add(jlAccountPenetrationValueQ3);
		    
			// Q3  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartPenetrationQ3 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricPenetrationQ3 = hBarChartPenetrationQ3.drawChart(q3PenDs);
		    ValueAxis rend2Q3 = hbcMetricPenetrationQ3.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer2Q3 = (BarRenderer) hbcMetricPenetrationQ3.getCategoryPlot().getRenderer();
		    GradientPaint gp2Q3 = new GradientPaint(0.0f,0.0f,Color.CYAN,0.0f,0.0f,new Color(0,0,125));
		    bRenderer2Q3.setSeriesPaint(0, gp2Q3);
		    rend2Q3.setUpperMargin(0);
		    ChartPanel hbcpMetricPenetrationQ3 = new ChartPanel(hbcMetricPenetrationQ3);
		    hbcpMetricPenetrationQ3.setLayout(null);
		    hbcpMetricPenetrationQ3.setBackground(null);
		    hbcpMetricPenetrationQ3.setOpaque(false);;
		    hbcpMetricPenetrationQ3.setBounds(chartXPosition,(chartYPosition+chartYIncrement)+35,chartWidth,(chartHeight+5));
		    hbcpMetricPenetrationQ3.setDomainZoomable(true);
		    panel3.add(hbcpMetricPenetrationQ3);
		    hBarChartPenetrationQ3.pack();
		    
		    jlTechPenetrationValueQ3 = new JLabel(new DecimalFormat("##.##").format(q3TechPen[0][0]));
		    jlTechPenetrationValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTechPenetrationValueQ3.setForeground(Color.blue);
		    jlTechPenetrationValueQ3.setBounds(valueXPosition, valueYPosition+(2*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlTechPenetrationValueQ3);
		    
		    // Q3  Discount Bar Chart
		    HorizontalBarChartMetric hBarChartDiscountQ3 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricDiscountQ3 = hBarChartDiscountQ3.drawChart(q3DiscountDs);
		    ValueAxis rend3Q3 = hbcMetricDiscountQ3.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer3Q3 = (BarRenderer) hbcMetricDiscountQ3.getCategoryPlot().getRenderer();
		    GradientPaint gp3Q3 = new GradientPaint(0.0f,0.0f,Color.magenta,0.0f,0.0f,new Color(0,0,64));
		    bRenderer3Q3.setSeriesPaint(0, gp3Q3);
		    rend3Q3.setUpperMargin(0);
		    ChartPanel hbcpMetricDiscountQ3 = new ChartPanel(hbcMetricDiscountQ3);
		    hbcpMetricDiscountQ3.setLayout(null);
		    hbcpMetricDiscountQ3.setBackground(null);
		    hbcpMetricDiscountQ3.setOpaque(false);
//		    hbcpMetricDiscountQ3.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+25,chartWidth,chartHeight+130);
		    hbcpMetricDiscountQ3.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+15,chartWidth,chartHeight+175);
		    hbcpMetricDiscountQ3.setDomainZoomable(true);
		    panel3.add(hbcpMetricDiscountQ3);
		    
		    jlBNDiscountValueQ3 = new JLabel(new DecimalFormat("##.#%").format(q3DiscountBN[0][0]));
		    jlBNDiscountValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlBNDiscountValueQ3.setForeground(Color.blue);
		    jlBNDiscountValueQ3.setBounds(valueXPosition, valueYPosition+(3*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlBNDiscountValueQ3);

		    jlSecDiscountValueQ3 = new JLabel(new DecimalFormat("##.#%").format(q3DiscountSec[0][0]));
		    jlSecDiscountValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlSecDiscountValueQ3.setForeground(Color.blue);
		    jlSecDiscountValueQ3.setBounds(valueXPosition, valueYPosition+(4*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlSecDiscountValueQ3);
		    
		    jlCollDiscountValueQ3 = new JLabel(new DecimalFormat("##.#%").format(q3DiscountColl[0][0]));
		    jlCollDiscountValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCollDiscountValueQ3.setForeground(Color.blue);
		    jlCollDiscountValueQ3.setBounds(valueXPosition, valueYPosition+(5*chartYIncrement)+3, valueWidth, valueHeight);
		    panel3.add(jlCollDiscountValueQ3);
		    
		    jlDCVDiscountValueQ3 = new JLabel(new DecimalFormat("##.#%").format(q3DiscountDCV[0][0]));
		    jlDCVDiscountValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlDCVDiscountValueQ3.setForeground(Color.blue);
		    jlDCVDiscountValueQ3.setBounds(valueXPosition, valueYPosition+(6*chartYIncrement)+2, valueWidth, valueHeight);
		    panel3.add(jlDCVDiscountValueQ3);

		    jlOverallDiscountValueQ3 = new JLabel(new DecimalFormat("##.#%").format(q3DiscountAll[0][0]));
		    jlOverallDiscountValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlOverallDiscountValueQ3.setForeground(Color.blue);
		    jlOverallDiscountValueQ3.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlOverallDiscountValueQ3);

			// Q3  Avg Yield Bar Chart
		    HorizontalBarChartMetric hBarChartYldQ3 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricYldQ3 = hBarChartYldQ3.drawChart(q3YldDs);
		    ValueAxis rend4Q3 = hbcMetricYldQ3.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer4Q3 = (BarRenderer) hbcMetricYldQ3.getCategoryPlot().getRenderer();
		    GradientPaint gp4Q3 = new GradientPaint(0.0f,0.0f,Color.green,0.0f,0.0f,new Color(0,0,64));
		    bRenderer4Q3.setSeriesPaint(0, gp4Q3);
		    rend4Q3.setUpperMargin(0);
		    ChartPanel hbcpMetricYldQ3 = new ChartPanel(hbcMetricYldQ3);
		    hbcpMetricYldQ3.setLayout(null);
		    hbcpMetricYldQ3.setBackground(null);
		    hbcpMetricYldQ3.setOpaque(false);;
//		    hbcpMetricYldQ3.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement),chartWidth,chartHeight+40);
		    hbcpMetricYldQ3.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricYldQ3.setDomainZoomable(true);
		    panel3.add(hbcpMetricYldQ3);

		    jlAvgYldPerAccountsValueQ3 = new JLabel(new DecimalFormat("$#####.##").format(q3YldPerCus[0][0]));
		    jlAvgYldPerAccountsValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerAccountsValueQ3.setForeground(Color.blue);
		    jlAvgYldPerAccountsValueQ3.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerAccountsValueQ3);

		    jlAvgYldPerOppValueQ3 = new JLabel(new DecimalFormat("$#####.##").format(q3YldPerOpp[0][0]));
		    jlAvgYldPerOppValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerOppValueQ3.setForeground(Color.blue);
		    jlAvgYldPerOppValueQ3.setBounds(valueXPosition, valueYPosition+(8*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerOppValueQ3);

			// Q3  CWP and Achievement Bar Chart
		    HorizontalBarChartMetric hBarChartCWPAchievementQ3 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricCWPAchievementQ3 = hBarChartCWPAchievementQ3.drawChart(q3CWPAchievementDs);
		    ValueAxis rend5Q3 = hbcMetricCWPAchievementQ3.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer5Q3 = (BarRenderer) hbcMetricCWPAchievementQ3.getCategoryPlot().getRenderer();
		    GradientPaint gp5Q3 = new GradientPaint(0.0f,0.0f,Color.BLUE,0.0f,0.0f,new Color(0,0,64));
		    bRenderer5Q3.setSeriesPaint(0, gp5Q3);
		    rend5Q3.setUpperMargin(0);
		    ChartPanel hbcpMetricCWPAchievementQ3 = new ChartPanel(hbcMetricCWPAchievementQ3);
		    hbcpMetricCWPAchievementQ3.setLayout(null);
		    hbcpMetricCWPAchievementQ3.setBackground(null);
		    hbcpMetricCWPAchievementQ3.setOpaque(false);;
		    hbcpMetricCWPAchievementQ3.setBounds(chartXPosition,chartYPosition+(9*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricCWPAchievementQ3.setDomainZoomable(true);
		    panel3.add(hbcpMetricCWPAchievementQ3);

		    jlAchievementValueQ3 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(q3Achievement[0][0])));
		    jlAchievementValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAchievementValueQ3.setForeground(Color.blue);
		    jlAchievementValueQ3.setBounds(valueXPosition, valueYPosition+(9*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAchievementValueQ3);

		    jlCWPValueQ3 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(q3CWP[0][0])));
		    jlCWPValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCWPValueQ3.setForeground(Color.blue);
		    jlCWPValueQ3.setBounds(valueXPosition, valueYPosition+(10*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlCWPValueQ3);
		
			// Q3  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartAOCQ3 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricAOCQ3 = hBarChartAOCQ3.drawChart(q3AOCDs);
		    ValueAxis rend6Q3 = hbcMetricAOCQ3.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer6Q3 = (BarRenderer) hbcMetricAOCQ3.getCategoryPlot().getRenderer();
		    GradientPaint gp6Q3 = new GradientPaint(0.0f,0.0f,Color.RED,0.0f,0.0f,new Color(125,0,0));
		    bRenderer6Q3.setSeriesPaint(0, gp6Q3);
		    rend6Q3.setUpperMargin(0);
		    ChartPanel hbcpMetricAOCQ3 = new ChartPanel(hbcMetricAOCQ3);
		    hbcpMetricAOCQ3.setLayout(null);
		    hbcpMetricAOCQ3.setBackground(null);
		    hbcpMetricAOCQ3.setOpaque(false);;
		    hbcpMetricAOCQ3.setBounds(chartXPosition,chartYPosition+(11*chartYIncrement)+35,chartWidth,chartHeight);
		    hbcpMetricAOCQ3.setDomainZoomable(true);
		    panel3.add(hbcpMetricAOCQ3);
		    
		    jlAOCValueQ3 = new JLabel(new DecimalFormat("###.#%").format(q3AOC[0][0]/100));
		    jlAOCValueQ3.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAOCValueQ3.setForeground(Color.blue);
		    jlAOCValueQ3.setBounds(valueXPosition, valueYPosition+(11*chartYIncrement)+30, valueWidth, chartHeight);
		    panel3.add(jlAOCValueQ3);
		
		}

		private void setPanel3MetricQ4(final int chartXPosition,final int chartWidth,final int chartYPosition,final int chartYIncrement,
				final int chartHeight,final int valueXPosition,final int valueWidth,final int valueYPosition,final int valueHeight) {
			// Q4 Total Accounts Bar Chart
		    HorizontalBarChartMetric hBarChartTotalAccountsQ4 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricTotalAccountsQ4 = hBarChartTotalAccountsQ4.drawChart(q4TotCusDs);
		    ValueAxis rend1Q4 = hbcMetricTotalAccountsQ4.getCategoryPlot().getRangeAxis();
		    rend1Q4.setUpperMargin(0);
		    ChartPanel hbcpMetricTotalAccountsQ4 = new ChartPanel(hbcMetricTotalAccountsQ4);
		    hbcpMetricTotalAccountsQ4.setLayout(null);
		    hbcpMetricTotalAccountsQ4.setBackground(null);
		    hbcpMetricTotalAccountsQ4.setOpaque(false);
		    hbcpMetricTotalAccountsQ4.setBounds(chartXPosition,chartYPosition,chartWidth,(chartHeight+40));
		    hbcpMetricTotalAccountsQ4.setDomainZoomable(true);
		    
		    panel3.add(hbcpMetricTotalAccountsQ4);
		    
		    jlTotalAccountsValueQ4 = new JLabel(new DecimalFormat("####").format(q4TotCus[0][0]));
		    jlTotalAccountsValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTotalAccountsValueQ4.setForeground(Color.blue);
		    jlTotalAccountsValueQ4.setBounds(valueXPosition, valueYPosition, valueWidth, valueHeight);
		    panel3.add(jlTotalAccountsValueQ4);

		    jlAccountPenetrationValueQ4 = new JLabel(new DecimalFormat("####").format(q4CusPen[0][0]));
		    jlAccountPenetrationValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAccountPenetrationValueQ4.setForeground(Color.blue);
		    jlAccountPenetrationValueQ4.setBounds(valueXPosition, valueYPosition+chartYIncrement, valueWidth, valueHeight);
		    panel3.add(jlAccountPenetrationValueQ4);
		    
			// Q4  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartPenetrationQ4 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricPenetrationQ4 = hBarChartPenetrationQ4.drawChart(q4PenDs);
		    ValueAxis rend2Q4 = hbcMetricPenetrationQ4.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer2Q4 = (BarRenderer) hbcMetricPenetrationQ4.getCategoryPlot().getRenderer();
		    GradientPaint gp2Q4 = new GradientPaint(0.0f,0.0f,Color.CYAN,0.0f,0.0f,new Color(0,0,125));
		    bRenderer2Q4.setSeriesPaint(0, gp2Q4);
		    rend2Q4.setUpperMargin(0);
		    ChartPanel hbcpMetricPenetrationQ4 = new ChartPanel(hbcMetricPenetrationQ4);
		    hbcpMetricPenetrationQ4.setLayout(null);
		    hbcpMetricPenetrationQ4.setBackground(null);
		    hbcpMetricPenetrationQ4.setOpaque(false);;
		    hbcpMetricPenetrationQ4.setBounds(chartXPosition,(chartYPosition+chartYIncrement)+35,chartWidth,(chartHeight+5));
		    hbcpMetricPenetrationQ4.setDomainZoomable(true);
		    panel3.add(hbcpMetricPenetrationQ4);
		    hBarChartPenetrationQ4.pack();
		    
		    jlTechPenetrationValueQ4 = new JLabel(new DecimalFormat("##.##").format(q4TechPen[0][0]));
		    jlTechPenetrationValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTechPenetrationValueQ4.setForeground(Color.blue);
		    jlTechPenetrationValueQ4.setBounds(valueXPosition, valueYPosition+(2*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlTechPenetrationValueQ4);
		    
		    // Q4  Discount Bar Chart
		    HorizontalBarChartMetric hBarChartDiscountQ4 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricDiscountQ4 = hBarChartDiscountQ4.drawChart(q4DiscountDs);
		    ValueAxis rend3Q4 = hbcMetricDiscountQ4.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer3Q4 = (BarRenderer) hbcMetricDiscountQ4.getCategoryPlot().getRenderer();
		    GradientPaint gp3Q4 = new GradientPaint(0.0f,0.0f,Color.magenta,0.0f,0.0f,new Color(0,0,64));
		    bRenderer3Q4.setSeriesPaint(0, gp3Q4);
		    rend3Q4.setUpperMargin(0);
		    ChartPanel hbcpMetricDiscountQ4 = new ChartPanel(hbcMetricDiscountQ4);
		    hbcpMetricDiscountQ4.setLayout(null);
		    hbcpMetricDiscountQ4.setBackground(null);
		    hbcpMetricDiscountQ4.setOpaque(false);
//		    hbcpMetricDiscountQ4.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+25,chartWidth,chartHeight+130);
		    hbcpMetricDiscountQ4.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+15,chartWidth,chartHeight+175);
		    hbcpMetricDiscountQ4.setDomainZoomable(true);
		    panel3.add(hbcpMetricDiscountQ4);
		    
		    jlBNDiscountValueQ4 = new JLabel(new DecimalFormat("##.#%").format(q4DiscountBN[0][0]));
		    jlBNDiscountValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlBNDiscountValueQ4.setForeground(Color.blue);
		    jlBNDiscountValueQ4.setBounds(valueXPosition, valueYPosition+(3*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlBNDiscountValueQ4);

		    jlSecDiscountValueQ4 = new JLabel(new DecimalFormat("##.#%").format(q4DiscountSec[0][0]));
		    jlSecDiscountValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlSecDiscountValueQ4.setForeground(Color.blue);
		    jlSecDiscountValueQ4.setBounds(valueXPosition, valueYPosition+(4*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlSecDiscountValueQ4);
		    
		    jlCollDiscountValueQ4 = new JLabel(new DecimalFormat("##.#%").format(q4DiscountColl[0][0]));
		    jlCollDiscountValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCollDiscountValueQ4.setForeground(Color.blue);
		    jlCollDiscountValueQ4.setBounds(valueXPosition, valueYPosition+(5*chartYIncrement)+3, valueWidth, valueHeight);
		    panel3.add(jlCollDiscountValueQ4);
		    
		    jlDCVDiscountValueQ4 = new JLabel(new DecimalFormat("##.#%").format(q4DiscountDCV[0][0]));
		    jlDCVDiscountValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlDCVDiscountValueQ4.setForeground(Color.blue);
		    jlDCVDiscountValueQ4.setBounds(valueXPosition, valueYPosition+(6*chartYIncrement)+2, valueWidth, valueHeight);
		    panel3.add(jlDCVDiscountValueQ4);

		    jlOverallDiscountValueQ4 = new JLabel(new DecimalFormat("##.#%").format(q4DiscountAll[0][0]));
		    jlOverallDiscountValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlOverallDiscountValueQ4.setForeground(Color.blue);
		    jlOverallDiscountValueQ4.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlOverallDiscountValueQ4);

			// Q4  Avg Yield Bar Chart
		    HorizontalBarChartMetric hBarChartYldQ4 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricYldQ4 = hBarChartYldQ4.drawChart(q4YldDs);
		    ValueAxis rend4Q4 = hbcMetricYldQ4.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer4Q4 = (BarRenderer) hbcMetricYldQ4.getCategoryPlot().getRenderer();
		    GradientPaint gp4Q4 = new GradientPaint(0.0f,0.0f,Color.green,0.0f,0.0f,new Color(0,0,64));
		    bRenderer4Q4.setSeriesPaint(0, gp4Q4);
		    rend4Q4.setUpperMargin(0);
		    ChartPanel hbcpMetricYldQ4 = new ChartPanel(hbcMetricYldQ4);
		    hbcpMetricYldQ4.setLayout(null);
		    hbcpMetricYldQ4.setBackground(null);
		    hbcpMetricYldQ4.setOpaque(false);;
//		    hbcpMetricYldQ4.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement),chartWidth,chartHeight+40);
		    hbcpMetricYldQ4.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricYldQ4.setDomainZoomable(true);
		    panel3.add(hbcpMetricYldQ4);

		    jlAvgYldPerAccountsValueQ4 = new JLabel(new DecimalFormat("$#####.##").format(q4YldPerCus[0][0]));
		    jlAvgYldPerAccountsValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerAccountsValueQ4.setForeground(Color.blue);
		    jlAvgYldPerAccountsValueQ4.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerAccountsValueQ4);

		    jlAvgYldPerOppValueQ4 = new JLabel(new DecimalFormat("$#####.##").format(q4YldPerOpp[0][0]));
		    jlAvgYldPerOppValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerOppValueQ4.setForeground(Color.blue);
		    jlAvgYldPerOppValueQ4.setBounds(valueXPosition, valueYPosition+(8*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerOppValueQ4);

			// Q4  CWP and Achievement Bar Chart
		    HorizontalBarChartMetric hBarChartCWPAchievementQ4 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricCWPAchievementQ4 = hBarChartCWPAchievementQ4.drawChart(q4CWPAchievementDs);
		    ValueAxis rend5Q4 = hbcMetricCWPAchievementQ4.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer5Q4 = (BarRenderer) hbcMetricCWPAchievementQ4.getCategoryPlot().getRenderer();
		    GradientPaint gp5Q4 = new GradientPaint(0.0f,0.0f,Color.BLUE,0.0f,0.0f,new Color(0,0,64));
		    bRenderer5Q4.setSeriesPaint(0, gp5Q4);
		    rend5Q4.setUpperMargin(0);
		    ChartPanel hbcpMetricCWPAchievementQ4 = new ChartPanel(hbcMetricCWPAchievementQ4);
		    hbcpMetricCWPAchievementQ4.setLayout(null);
		    hbcpMetricCWPAchievementQ4.setBackground(null);
		    hbcpMetricCWPAchievementQ4.setOpaque(false);;
		    hbcpMetricCWPAchievementQ4.setBounds(chartXPosition,chartYPosition+(9*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricCWPAchievementQ4.setDomainZoomable(true);
		    panel3.add(hbcpMetricCWPAchievementQ4);

		    jlAchievementValueQ4 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(q4Achievement[0][0])));
		    jlAchievementValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAchievementValueQ4.setForeground(Color.blue);
		    jlAchievementValueQ4.setBounds(valueXPosition, valueYPosition+(9*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAchievementValueQ4);

		    jlCWPValueQ4 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(q4CWP[0][0])));
		    jlCWPValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCWPValueQ4.setForeground(Color.blue);
		    jlCWPValueQ4.setBounds(valueXPosition, valueYPosition+(10*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlCWPValueQ4);
		
			// Q4  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartAOCQ4 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricAOCQ4 = hBarChartAOCQ4.drawChart(q4AOCDs);
		    ValueAxis rend6Q4 = hbcMetricAOCQ4.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer6Q4 = (BarRenderer) hbcMetricAOCQ4.getCategoryPlot().getRenderer();
		    GradientPaint gp6Q4 = new GradientPaint(0.0f,0.0f,Color.RED,0.0f,0.0f,new Color(125,0,0));
		    bRenderer6Q4.setSeriesPaint(0, gp6Q4);
		    rend6Q4.setUpperMargin(0);
		    ChartPanel hbcpMetricAOCQ4 = new ChartPanel(hbcMetricAOCQ4);
		    hbcpMetricAOCQ4.setLayout(null);
		    hbcpMetricAOCQ4.setBackground(null);
		    hbcpMetricAOCQ4.setOpaque(false);;
		    hbcpMetricAOCQ4.setBounds(chartXPosition,chartYPosition+(11*chartYIncrement)+35,chartWidth,chartHeight);
		    hbcpMetricAOCQ4.setDomainZoomable(true);
		    panel3.add(hbcpMetricAOCQ4);
		    
		    jlAOCValueQ4 = new JLabel(new DecimalFormat("###.#%").format(q4AOC[0][0]/100));
		    jlAOCValueQ4.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAOCValueQ4.setForeground(Color.blue);
		    jlAOCValueQ4.setBounds(valueXPosition, valueYPosition+(11*chartYIncrement)+30, valueWidth, chartHeight);
		    panel3.add(jlAOCValueQ4);
		
		}

		private void setPanel3MetricH2(final int chartXPosition,final int chartWidth,final int chartYPosition,final int chartYIncrement,
				final int chartHeight,final int valueXPosition,final int valueWidth,final int valueYPosition,final int valueHeight) {
			// H2 Total Accounts Bar Chart
		    HorizontalBarChartMetric hBarChartTotalAccountsH2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricTotalAccountsH2 = hBarChartTotalAccountsH2.drawChart(h2TotCusDs);
		    ValueAxis rend1H2 = hbcMetricTotalAccountsH2.getCategoryPlot().getRangeAxis();
		    rend1H2.setUpperMargin(0);
		    ChartPanel hbcpMetricTotalAccountsH2 = new ChartPanel(hbcMetricTotalAccountsH2);
		    hbcpMetricTotalAccountsH2.setLayout(null);
		    hbcpMetricTotalAccountsH2.setBackground(null);
		    hbcpMetricTotalAccountsH2.setOpaque(false);
		    hbcpMetricTotalAccountsH2.setBounds(chartXPosition,chartYPosition,chartWidth,(chartHeight+40));
		    hbcpMetricTotalAccountsH2.setDomainZoomable(true);
		    
		    panel3.add(hbcpMetricTotalAccountsH2);
		    
		    jlTotalAccountsValueH2 = new JLabel(new DecimalFormat("####").format(h2TotCus[0][0]));
		    jlTotalAccountsValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTotalAccountsValueH2.setForeground(Color.blue);
		    jlTotalAccountsValueH2.setBounds(valueXPosition, valueYPosition, valueWidth, valueHeight);
		    panel3.add(jlTotalAccountsValueH2);

		    jlAccountPenetrationValueH2 = new JLabel(new DecimalFormat("####").format(h2CusPen[0][0]));
		    jlAccountPenetrationValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAccountPenetrationValueH2.setForeground(Color.blue);
		    jlAccountPenetrationValueH2.setBounds(valueXPosition, valueYPosition+chartYIncrement, valueWidth, valueHeight);
		    panel3.add(jlAccountPenetrationValueH2);
		    
			// H2  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartPenetrationH2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricPenetrationH2 = hBarChartPenetrationH2.drawChart(h2PenDs);
		    ValueAxis rend2H2 = hbcMetricPenetrationH2.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer2H2 = (BarRenderer) hbcMetricPenetrationH2.getCategoryPlot().getRenderer();
		    GradientPaint gp2H2 = new GradientPaint(0.0f,0.0f,Color.CYAN,0.0f,0.0f,new Color(0,0,125));
		    bRenderer2H2.setSeriesPaint(0, gp2H2);
		    rend2H2.setUpperMargin(0);
		    ChartPanel hbcpMetricPenetrationH2 = new ChartPanel(hbcMetricPenetrationH2);
		    hbcpMetricPenetrationH2.setLayout(null);
		    hbcpMetricPenetrationH2.setBackground(null);
		    hbcpMetricPenetrationH2.setOpaque(false);;
		    hbcpMetricPenetrationH2.setBounds(chartXPosition,(chartYPosition+chartYIncrement)+35,chartWidth,(chartHeight+5));
		    hbcpMetricPenetrationH2.setDomainZoomable(true);
		    panel3.add(hbcpMetricPenetrationH2);
		    hBarChartPenetrationH2.pack();
		    
		    jlTechPenetrationValueH2 = new JLabel(new DecimalFormat("##.##").format(h2TechPen[0][0]));
		    jlTechPenetrationValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTechPenetrationValueH2.setForeground(Color.blue);
		    jlTechPenetrationValueH2.setBounds(valueXPosition, valueYPosition+(2*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlTechPenetrationValueH2);
		    
		    // H2  Discount Bar Chart
		    HorizontalBarChartMetric hBarChartDiscountH2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricDiscountH2 = hBarChartDiscountH2.drawChart(h2DiscountDs);
		    ValueAxis rend3H2 = hbcMetricDiscountH2.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer3H2 = (BarRenderer) hbcMetricDiscountH2.getCategoryPlot().getRenderer();
		    GradientPaint gp3H2 = new GradientPaint(0.0f,0.0f,Color.magenta,0.0f,0.0f,new Color(0,0,64));
		    bRenderer3H2.setSeriesPaint(0, gp3H2);
		    rend3H2.setUpperMargin(0);
		    ChartPanel hbcpMetricDiscountH2 = new ChartPanel(hbcMetricDiscountH2);
		    hbcpMetricDiscountH2.setLayout(null);
		    hbcpMetricDiscountH2.setBackground(null);
		    hbcpMetricDiscountH2.setOpaque(false);
//		    hbcpMetricDiscountH2.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+25,chartWidth,chartHeight+130);
		    hbcpMetricDiscountH2.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+15,chartWidth,chartHeight+175);
		    hbcpMetricDiscountH2.setDomainZoomable(true);
		    panel3.add(hbcpMetricDiscountH2);
		    
		    jlBNDiscountValueH2 = new JLabel(new DecimalFormat("##.#%").format(h2DiscountBN[0][0]));
		    jlBNDiscountValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlBNDiscountValueH2.setForeground(Color.blue);
		    jlBNDiscountValueH2.setBounds(valueXPosition, valueYPosition+(3*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlBNDiscountValueH2);

		    jlSecDiscountValueH2 = new JLabel(new DecimalFormat("##.#%").format(h2DiscountSec[0][0]));
		    jlSecDiscountValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlSecDiscountValueH2.setForeground(Color.blue);
		    jlSecDiscountValueH2.setBounds(valueXPosition, valueYPosition+(4*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlSecDiscountValueH2);
		    
		    jlCollDiscountValueH2 = new JLabel(new DecimalFormat("##.#%").format(h2DiscountColl[0][0]));
		    jlCollDiscountValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCollDiscountValueH2.setForeground(Color.blue);
		    jlCollDiscountValueH2.setBounds(valueXPosition, valueYPosition+(5*chartYIncrement)+3, valueWidth, valueHeight);
		    panel3.add(jlCollDiscountValueH2);
		    
		    jlDCVDiscountValueH2 = new JLabel(new DecimalFormat("##.#%").format(h2DiscountDCV[0][0]));
		    jlDCVDiscountValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlDCVDiscountValueH2.setForeground(Color.blue);
		    jlDCVDiscountValueH2.setBounds(valueXPosition, valueYPosition+(6*chartYIncrement)+2, valueWidth, valueHeight);
		    panel3.add(jlDCVDiscountValueH2);

		    jlOverallDiscountValueH2 = new JLabel(new DecimalFormat("##.#%").format(h2DiscountAll[0][0]));
		    jlOverallDiscountValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlOverallDiscountValueH2.setForeground(Color.blue);
		    jlOverallDiscountValueH2.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlOverallDiscountValueH2);

			// H2  Avg Yield Bar Chart
		    HorizontalBarChartMetric hBarChartYldH2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricYldH2 = hBarChartYldH2.drawChart(h2YldDs);
		    ValueAxis rend4H2 = hbcMetricYldH2.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer4H2 = (BarRenderer) hbcMetricYldH2.getCategoryPlot().getRenderer();
		    GradientPaint gp4H2 = new GradientPaint(0.0f,0.0f,Color.green,0.0f,0.0f,new Color(0,0,64));
		    bRenderer4H2.setSeriesPaint(0, gp4H2);
		    rend4H2.setUpperMargin(0);
		    ChartPanel hbcpMetricYldH2 = new ChartPanel(hbcMetricYldH2);
		    hbcpMetricYldH2.setLayout(null);
		    hbcpMetricYldH2.setBackground(null);
		    hbcpMetricYldH2.setOpaque(false);;
		    hbcpMetricYldH2.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricYldH2.setDomainZoomable(true);
		    panel3.add(hbcpMetricYldH2);

		    jlAvgYldPerAccountsValueH2 = new JLabel(new DecimalFormat("$#####.##").format(h2YldPerCus[0][0]));
		    jlAvgYldPerAccountsValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerAccountsValueH2.setForeground(Color.blue);
		    jlAvgYldPerAccountsValueH2.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerAccountsValueH2);

		    jlAvgYldPerOppValueH2 = new JLabel(new DecimalFormat("$#####.##").format(h2YldPerOpp[0][0]));
		    jlAvgYldPerOppValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerOppValueH2.setForeground(Color.blue);
		    jlAvgYldPerOppValueH2.setBounds(valueXPosition, valueYPosition+(8*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerOppValueH2);

			// H2  CWP and Achievement Bar Chart
		    HorizontalBarChartMetric hBarChartCWPAchievementH2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricCWPAchievementH2 = hBarChartCWPAchievementH2.drawChart(h2CWPAchievementDs);
		    ValueAxis rend5H2 = hbcMetricCWPAchievementH2.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer5H2 = (BarRenderer) hbcMetricCWPAchievementH2.getCategoryPlot().getRenderer();
		    GradientPaint gp5H2 = new GradientPaint(0.0f,0.0f,Color.BLUE,0.0f,0.0f,new Color(0,0,64));
		    bRenderer5H2.setSeriesPaint(0, gp5H2);
		    rend5H2.setUpperMargin(0);
		    ChartPanel hbcpMetricCWPAchievementH2 = new ChartPanel(hbcMetricCWPAchievementH2);
		    hbcpMetricCWPAchievementH2.setLayout(null);
		    hbcpMetricCWPAchievementH2.setBackground(null);
		    hbcpMetricCWPAchievementH2.setOpaque(false);;
		    hbcpMetricCWPAchievementH2.setBounds(chartXPosition,chartYPosition+(9*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricCWPAchievementH2.setDomainZoomable(true);
		    panel3.add(hbcpMetricCWPAchievementH2);

		    jlAchievementValueH2 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(h2Achievement[0][0])));
		    jlAchievementValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAchievementValueH2.setForeground(Color.blue);
		    jlAchievementValueH2.setBounds(valueXPosition, valueYPosition+(9*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAchievementValueH2);

		    jlCWPValueH2 = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(h2CWP[0][0])));
		    jlCWPValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCWPValueH2.setForeground(Color.blue);
		    jlCWPValueH2.setBounds(valueXPosition, valueYPosition+(10*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlCWPValueH2);
		
			// H2  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartAOCH2 = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricAOCH2 = hBarChartAOCH2.drawChart(h2AOCDs);
		    ValueAxis rend6H2 = hbcMetricAOCH2.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer6H2 = (BarRenderer) hbcMetricAOCH2.getCategoryPlot().getRenderer();
		    GradientPaint gp6H2 = new GradientPaint(0.0f,0.0f,Color.RED,0.0f,0.0f,new Color(125,0,0));
		    bRenderer6H2.setSeriesPaint(0, gp6H2);
		    rend6H2.setUpperMargin(0);
		    ChartPanel hbcpMetricAOCH2 = new ChartPanel(hbcMetricAOCH2);
		    hbcpMetricAOCH2.setLayout(null);
		    hbcpMetricAOCH2.setBackground(null);
		    hbcpMetricAOCH2.setOpaque(false);;
		    hbcpMetricAOCH2.setBounds(chartXPosition,chartYPosition+(11*chartYIncrement)+35,chartWidth,chartHeight);
		    hbcpMetricAOCH2.setDomainZoomable(true);
		    panel3.add(hbcpMetricAOCH2);
		    
		    jlAOCValueH2 = new JLabel(new DecimalFormat("###.#%").format(h2AOC[0][0]/100));
		    jlAOCValueH2.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAOCValueH2.setForeground(Color.blue);
		    jlAOCValueH2.setBounds(valueXPosition, valueYPosition+(11*chartYIncrement)+30, valueWidth, chartHeight);
		    panel3.add(jlAOCValueH2);
		
		}

		private void setPanel3MetricYTD(final int chartXPosition,final int chartWidth,final int chartYPosition,final int chartYIncrement,
				final int chartHeight,final int valueXPosition,final int valueWidth,final int valueYPosition,final int valueHeight) {
			// YTD Total Accounts Bar Chart
		    HorizontalBarChartMetric hBarChartTotalAccountsYTD = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricTotalAccountsYTD = hBarChartTotalAccountsYTD.drawChart(ytdTotCusDs);
		    ValueAxis rend1YTD = hbcMetricTotalAccountsYTD.getCategoryPlot().getRangeAxis();
		    rend1YTD.setUpperMargin(0);
		    ChartPanel hbcpMetricTotalAccountsYTD = new ChartPanel(hbcMetricTotalAccountsYTD);
		    hbcpMetricTotalAccountsYTD.setLayout(null);
		    hbcpMetricTotalAccountsYTD.setBackground(null);
		    hbcpMetricTotalAccountsYTD.setOpaque(false);
		    hbcpMetricTotalAccountsYTD.setBounds(chartXPosition,chartYPosition,chartWidth,(chartHeight+40));
		    hbcpMetricTotalAccountsYTD.setDomainZoomable(true);
		    
		    panel3.add(hbcpMetricTotalAccountsYTD);
		    
		    jlTotalAccountsValueYTD = new JLabel(new DecimalFormat("####").format(ytdTotCus[0][0]));
		    jlTotalAccountsValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTotalAccountsValueYTD.setForeground(Color.blue);
		    jlTotalAccountsValueYTD.setBounds(valueXPosition, valueYPosition, valueWidth, valueHeight);
		    panel3.add(jlTotalAccountsValueYTD);

		    jlAccountPenetrationValueYTD = new JLabel(new DecimalFormat("####").format(ytdCusPen[0][0]));
		    jlAccountPenetrationValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAccountPenetrationValueYTD.setForeground(Color.blue);
		    jlAccountPenetrationValueYTD.setBounds(valueXPosition, valueYPosition+chartYIncrement, valueWidth, valueHeight);
		    panel3.add(jlAccountPenetrationValueYTD);
		    
			// YTD  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartPenetrationYTD = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricPenetrationYTD = hBarChartPenetrationYTD.drawChart(ytdPenDs);
		    ValueAxis rend2YTD = hbcMetricPenetrationYTD.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer2YTD = (BarRenderer) hbcMetricPenetrationYTD.getCategoryPlot().getRenderer();
		    GradientPaint gp2YTD = new GradientPaint(0.0f,0.0f,Color.CYAN,0.0f,0.0f,new Color(0,0,125));
		    bRenderer2YTD.setSeriesPaint(0, gp2YTD);
		    rend2YTD.setUpperMargin(0);
		    ChartPanel hbcpMetricPenetrationYTD = new ChartPanel(hbcMetricPenetrationYTD);
		    hbcpMetricPenetrationYTD.setLayout(null);
		    hbcpMetricPenetrationYTD.setBackground(null);
		    hbcpMetricPenetrationYTD.setOpaque(false);;
		    hbcpMetricPenetrationYTD.setBounds(chartXPosition,(chartYPosition+chartYIncrement)+35,chartWidth,(chartHeight+5));
		    hbcpMetricPenetrationYTD.setDomainZoomable(true);
		    panel3.add(hbcpMetricPenetrationYTD);
		    hBarChartPenetrationYTD.pack();
		    
		    jlTechPenetrationValueYTD = new JLabel(new DecimalFormat("##.##").format(ytdTechPen[0][0]));
		    jlTechPenetrationValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlTechPenetrationValueYTD.setForeground(Color.blue);
		    jlTechPenetrationValueYTD.setBounds(valueXPosition, valueYPosition+(2*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlTechPenetrationValueYTD);
		    
		    // YTD  Discount Bar Chart
		    HorizontalBarChartMetric hBarChartDiscountYTD = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricDiscountYTD = hBarChartDiscountYTD.drawChart(ytdDiscountDs);
		    ValueAxis rend3YTD = hbcMetricDiscountYTD.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer3YTD = (BarRenderer) hbcMetricDiscountYTD.getCategoryPlot().getRenderer();
		    GradientPaint gp3YTD = new GradientPaint(0.0f,0.0f,Color.magenta,0.0f,0.0f,new Color(0,0,64));
		    bRenderer3YTD.setSeriesPaint(0, gp3YTD);
		    rend3YTD.setUpperMargin(0);
		    ChartPanel hbcpMetricDiscountYTD = new ChartPanel(hbcMetricDiscountYTD);
		    hbcpMetricDiscountYTD.setLayout(null);
		    hbcpMetricDiscountYTD.setBackground(null);
		    hbcpMetricDiscountYTD.setOpaque(false);
//		    hbcpMetricDiscountYTD.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+25,chartWidth,chartHeight+130);
		    hbcpMetricDiscountYTD.setBounds(chartXPosition,chartYPosition+(2*chartYIncrement)+15,chartWidth,chartHeight+175);
		    hbcpMetricDiscountYTD.setDomainZoomable(true);
		    panel3.add(hbcpMetricDiscountYTD);
		    
		    jlBNDiscountValueYTD = new JLabel(new DecimalFormat("##.#%").format(ytdDiscountBN[0][0]));
		    jlBNDiscountValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlBNDiscountValueYTD.setForeground(Color.blue);
		    jlBNDiscountValueYTD.setBounds(valueXPosition, valueYPosition+(3*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlBNDiscountValueYTD);

		    jlSecDiscountValueYTD = new JLabel(new DecimalFormat("##.#%").format(ytdDiscountSec[0][0]));
		    jlSecDiscountValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlSecDiscountValueYTD.setForeground(Color.blue);
		    jlSecDiscountValueYTD.setBounds(valueXPosition, valueYPosition+(4*chartYIncrement)+5, valueWidth, valueHeight);
		    panel3.add(jlSecDiscountValueYTD);
		    
		    jlCollDiscountValueYTD = new JLabel(new DecimalFormat("##.#%").format(ytdDiscountColl[0][0]));
		    jlCollDiscountValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCollDiscountValueYTD.setForeground(Color.blue);
		    jlCollDiscountValueYTD.setBounds(valueXPosition, valueYPosition+(5*chartYIncrement)+3, valueWidth, valueHeight);
		    panel3.add(jlCollDiscountValueYTD);
		    
		    jlDCVDiscountValueYTD = new JLabel(new DecimalFormat("##.#%").format(ytdDiscountDCV[0][0]));
		    jlDCVDiscountValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlDCVDiscountValueYTD.setForeground(Color.blue);
		    jlDCVDiscountValueYTD.setBounds(valueXPosition, valueYPosition+(6*chartYIncrement)+2, valueWidth, valueHeight);
		    panel3.add(jlDCVDiscountValueYTD);

		    jlOverallDiscountValueYTD = new JLabel(new DecimalFormat("##.#%").format(ytdDiscountAll[0][0]));
		    jlOverallDiscountValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlOverallDiscountValueYTD.setForeground(Color.blue);
		    jlOverallDiscountValueYTD.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement), valueWidth, valueHeight);
		    panel3.add(jlOverallDiscountValueYTD);

			// YTD  Avg Yield Bar Chart
		    HorizontalBarChartMetric hBarChartYldYTD = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricYldYTD = hBarChartYldYTD.drawChart(ytdYldDs);
		    ValueAxis rend4YTD = hbcMetricYldYTD.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer4YTD = (BarRenderer) hbcMetricYldYTD.getCategoryPlot().getRenderer();
		    GradientPaint gp4YTD = new GradientPaint(0.0f,0.0f,Color.green,0.0f,0.0f,new Color(0,0,64));
		    bRenderer4YTD.setSeriesPaint(0, gp4YTD);
		    rend4YTD.setUpperMargin(0);
		    ChartPanel hbcpMetricYldYTD = new ChartPanel(hbcMetricYldYTD);
		    hbcpMetricYldYTD.setLayout(null);
		    hbcpMetricYldYTD.setBackground(null);
		    hbcpMetricYldYTD.setOpaque(false);;
//		    hbcpMetricYldYTD.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement),chartWidth,chartHeight+40);
		    hbcpMetricYldYTD.setBounds(chartXPosition,chartYPosition+(7*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricYldYTD.setDomainZoomable(true);
		    panel3.add(hbcpMetricYldYTD);

		    jlAvgYldPerAccountsValueYTD = new JLabel(new DecimalFormat("$#####.##").format(ytdYldPerCus[0][0]));
		    jlAvgYldPerAccountsValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerAccountsValueYTD.setForeground(Color.blue);
		    jlAvgYldPerAccountsValueYTD.setBounds(valueXPosition, valueYPosition+(7*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerAccountsValueYTD);

		    jlAvgYldPerOppValueYTD = new JLabel(new DecimalFormat("$#####.##").format(ytdYldPerOpp[0][0]));
		    jlAvgYldPerOppValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAvgYldPerOppValueYTD.setForeground(Color.blue);
		    jlAvgYldPerOppValueYTD.setBounds(valueXPosition, valueYPosition+(8*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAvgYldPerOppValueYTD);

			// YTD  CWP and Achievement Bar Chart
		    HorizontalBarChartMetric hBarChartCWPAchievementYTD = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricCWPAchievementYTD = hBarChartCWPAchievementYTD.drawChart(ytdCWPAchievementDs);
		    ValueAxis rend5YTD = hbcMetricCWPAchievementYTD.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer5YTD = (BarRenderer) hbcMetricCWPAchievementYTD.getCategoryPlot().getRenderer();
		    GradientPaint gp5YTD = new GradientPaint(0.0f,0.0f,Color.BLUE,0.0f,0.0f,new Color(0,0,64));
		    bRenderer5YTD.setSeriesPaint(0, gp5YTD);
		    rend5YTD.setUpperMargin(0);
		    ChartPanel hbcpMetricCWPAchievementYTD = new ChartPanel(hbcMetricCWPAchievementYTD);
		    hbcpMetricCWPAchievementYTD.setLayout(null);
		    hbcpMetricCWPAchievementYTD.setBackground(null);
		    hbcpMetricCWPAchievementYTD.setOpaque(false);;
		    hbcpMetricCWPAchievementYTD.setBounds(chartXPosition,chartYPosition+(9*chartYIncrement)+35,chartWidth,chartHeight+40);
		    hbcpMetricCWPAchievementYTD.setDomainZoomable(true);
		    panel3.add(hbcpMetricCWPAchievementYTD);

		    jlAchievementValueYTD = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(ytdAchievement[0][0])));
		    jlAchievementValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAchievementValueYTD.setForeground(Color.blue);
		    jlAchievementValueYTD.setBounds(valueXPosition, valueYPosition+(9*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlAchievementValueYTD);

		    jlCWPValueYTD = new JLabel(new DecimalFormat("$#####.##").format(CalcHelper.getValueInThousandUSD(ytdCWP[0][0])));
		    jlCWPValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlCWPValueYTD.setForeground(Color.blue);
		    jlCWPValueYTD.setBounds(valueXPosition, valueYPosition+(10*chartYIncrement)+2+35, valueWidth, valueHeight);
		    panel3.add(jlCWPValueYTD);
		
			// YTD  Penetration Bar Chart
		    HorizontalBarChartMetric hBarChartAOCYTD = new HorizontalBarChartMetric("");
		    JFreeChart hbcMetricAOCYTD = hBarChartAOCYTD.drawChart(ytdAOCDs);
		    ValueAxis rend6YTD = hbcMetricAOCYTD.getCategoryPlot().getRangeAxis();
		    BarRenderer bRenderer6YTD = (BarRenderer) hbcMetricAOCYTD.getCategoryPlot().getRenderer();
		    GradientPaint gp6YTD = new GradientPaint(0.0f,0.0f,Color.RED,0.0f,0.0f,new Color(125,0,0));
		    bRenderer6YTD.setSeriesPaint(0, gp6YTD);
		    rend6YTD.setUpperMargin(0);
		    ChartPanel hbcpMetricAOCYTD = new ChartPanel(hbcMetricAOCYTD);
		    hbcpMetricAOCYTD.setLayout(null);
		    hbcpMetricAOCYTD.setBackground(null);
		    hbcpMetricAOCYTD.setOpaque(false);;
		    hbcpMetricAOCYTD.setBounds(chartXPosition,chartYPosition+(11*chartYIncrement)+35,chartWidth,chartHeight);
		    hbcpMetricAOCYTD.setDomainZoomable(true);
		    panel3.add(hbcpMetricAOCYTD);
		    
		    jlAOCValueYTD = new JLabel(new DecimalFormat("###.#%").format(ytdAOC[0][0]/100));
		    jlAOCValueYTD.setFont(new Font(fontFamily,Font.BOLD,fontSize-1));
		    jlAOCValueYTD.setForeground(Color.blue);
		    jlAOCValueYTD.setBounds(valueXPosition, valueYPosition+(11*chartYIncrement)+30, valueWidth, chartHeight);
		    panel3.add(jlAOCValueYTD);
		
		}
		
}
