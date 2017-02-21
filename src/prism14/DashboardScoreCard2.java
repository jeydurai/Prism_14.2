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
import java.awt.event.KeyEvent;
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
import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
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


public class DashboardScoreCard2 extends JInternalFrame{

	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
    private ImageIcon dScreenImage1, dScreenImage2, screenTitle, imageIcon;
    private JLabel jlDScreenImage1, jlDScreenImage2, jlWhatIsItTitle, jlHowItWorksTitle, jlScreenTitle;
    private GradientPanel infoPanel;
	private JTextArea jtaWhatIsIt, jtaHowItWorks, jtaInfo;
	private JButton jbWhatIsItReadMore;
	private JFrame jfWhatIsItReadMore;
    private JSeparator sep1, sep11,sep2, sep3;
    private JLabel jlXxmOption, jlEU, jlRegion, jlNode, jlXxm;
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

//    private DataBaseSalesListQueries salesListQueries;
    private DataBaseSalesAgentListQueries salesListQueries;
    private DataBaseSalesList currentSalesListEntry;
    private List<DataBaseSalesList> resultsSalesList;
    
    private ArrayList<String> tempArrayList;


    @SuppressWarnings("rawtypes")
	private DefaultListModel regionFillModel, userFillModel, dummyModel;
    

    protected GradientPanel2 bannerPanel, panel1;
    protected GradientPanel mainPanel, compoPanel, internalCompoPanel;
    protected JLabel JlPrimaryScreenTitle;
    private ImageIcon imgLogo, pScreenImage1, pScreenImage2, trueNorthImage, primaryScreenTitle;
    private DateFormat dateFormat;
    private Date currentDate;
    private JLabel jlLogo, jlDateTime, jlPScreenImage1, 
    jlPScreenImage2, jlTrueNorthImage, jlPerIndexScore;
    protected JScrollPane mainScrollPane, rootScrollPane, compoScrollPane;
    protected int fontSize, tempCount;
    protected String fontFamily, fontSpecialFamily;
	public static String role, eUnit, region, reportingTo, firstName, lastName, emailID;
    private DataBaseUserDataQueries userDataQueries;
    private DataBaseUserData currentUserDataEntry;
    private List<DataBaseUserData> resultsUserData;
    private JRadioButton jrbQ1, jrbQ2, jrbH1, jrbQ3, jrbQ4, jrbH2, jrbYTD; 
    private ButtonGroup bg;
	private JPanel finQuarterPanel;
	private boolean isItFirstTime;
    private ImageIcon redMark, yellowMark, greenMark;

    DashboardScoreCard2CompoGroup box1, box2, box3, box4, box5, box6, box7, box8, box9, box10;
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
	public DashboardScoreCard2() {
		super("TBM-VBM ScoreCard #" + (++openFrameCount),
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
		screenTitle = new ImageIcon(this.getClass().getResource(GeneralConstants.TBMVBM_TITLE));
		redMark = new ImageIcon(this.getClass().getResource(GeneralConstants.RED_BALL2));
		yellowMark = new ImageIcon(this.getClass().getResource(GeneralConstants.YELLOW_BALL2));
		greenMark = new ImageIcon(this.getClass().getResource(GeneralConstants.GREEN_BALL2));

		jlPerIndexScore = new JLabel();

		jlScreenTitle = new JLabel(screenTitle);
		jlScreenTitle.setBounds(400, 15, 600, 50);
		jlScreenTitle.setOpaque(false);

        try {
	    	userDataQueries = new DataBaseUserDataQueries();
	        eUMasterQueries = new DataBaseEUMasterQueries();
	        salesListQueries = new DataBaseSalesAgentListQueries();
	        }
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException iException){
			iException.printStackTrace();
		}

        
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
		bannerPanel.setBackground(new Color(255,255,255));
		bannerPanel.setLayout(null);
        bannerPanel.setSize(1355,95);
        bannerPanel.setLocation(0, 0);
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
        compoPanel.setPreferredSize(new Dimension(1200,1040));
        compoPanel.setFont(new Font(fontFamily,Font.BOLD,fontSize));

        internalCompoPanel = new GradientPanel();
        internalCompoPanel.setBackground(new Color(250,250,250,150));
        internalCompoPanel.setPreferredSize(new Dimension(1350,650));
        internalCompoPanel.setFont(new Font(fontFamily,Font.BOLD,fontSize));

        compoScrollPane = new JScrollPane();
        compoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        compoScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        compoScrollPane.setBounds(10, 50, 1325, 415);
        
        mainScrollPane = new JScrollPane();
        mainScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setBounds(0, 180, 1355, 485);
        mainScrollPane.setViewportView(compoPanel);
        
	    sep1 = new JSeparator();
	    sep1.setBounds(0, 95,1360,2);
	    mainPanel.add(sep1);

	    sep11 = new JSeparator();
	    sep11.setBounds(1, 180,1360,2);
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
		jtaWhatIsIt.setText("TBM/VBM Scorescard is a Performance Measuring Dashboard that "
				+ "has a set of Metrics comparison showing PL TBM/VBM's Goal vs. Achievement, Growth, "
				+ "Discount, AT penetration, SCP Performance, Leads, DeBooking, Linearity... "
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
				+ "will list as to which node/TBM/VBM Names \nyou have access to... Upon the TBM/VBM selection the Scorecard "
				+ "will be turned on.");
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
        jtaInfo.setText(GeneralConstants.TBMVBM_INFO2);
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

			
		    Border bor = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		    finQuarterPanel = new JPanel();
		    finQuarterPanel.setBorder(BorderFactory.createTitledBorder(bor, "Fin Quarter"));
		    finQuarterPanel.setBounds(10, FR_COMP_Y_POSITION, 320, 65);
		    finQuarterPanel.setBackground(null);
		    finQuarterPanel.setOpaque(false);
		    finQuarterPanel.setFont(new Font(fontFamily,Font.BOLD,fontSize-10));
		    mainPanel.add(finQuarterPanel);
			
			jrbQ1 = ComponentHelper.getRadioButton("Q1", new Font(fontFamily,Font.PLAIN,fontSize), 
					FR_COMP_WIDTH-20, FR_COMP_HEIGHT-10);
			jrbQ1.addActionListener(new QuarterListener());
			finQuarterPanel.add(jrbQ1);
			jrbQ2 = ComponentHelper.getRadioButton("Q2", new Font(fontFamily,Font.PLAIN,fontSize), 
					FR_COMP_WIDTH-20, FR_COMP_HEIGHT-10);
			jrbQ2.addActionListener(new QuarterListener());
			finQuarterPanel.add(jrbQ2);
			jrbQ3 = ComponentHelper.getRadioButton("Q3", new Font(fontFamily,Font.PLAIN,fontSize), 
					FR_COMP_WIDTH-20, FR_COMP_HEIGHT-10);
			jrbQ3.setEnabled(true);
			jrbQ3.addActionListener(new QuarterListener());
			finQuarterPanel.add(jrbQ3);
			jrbQ4 = ComponentHelper.getRadioButton("Q4", new Font(fontFamily,Font.PLAIN,fontSize), 
					FR_COMP_WIDTH-20, FR_COMP_HEIGHT-10);
			jrbQ4.addActionListener(new QuarterListener());
			jrbQ4.setEnabled(true);
			finQuarterPanel.add(jrbQ4);
			jrbH1 = ComponentHelper.getRadioButton("H1", new Font(fontFamily,Font.PLAIN,fontSize), 
					FR_COMP_WIDTH-20, FR_COMP_HEIGHT-10);
			jrbH1.addActionListener(new QuarterListener());
			jrbH1.setEnabled(true);
			finQuarterPanel.add(jrbH1);
			jrbH2 = ComponentHelper.getRadioButton("H2", new Font(fontFamily,Font.PLAIN,fontSize), 
					FR_COMP_WIDTH-20, FR_COMP_HEIGHT-10);
			jrbH2.addActionListener(new QuarterListener());
			jrbH2.setEnabled(true);
			finQuarterPanel.add(jrbH2);
			jrbYTD = ComponentHelper.getRadioButton("YTD", new Font(fontFamily,Font.PLAIN,fontSize), 
					FR_COMP_WIDTH-20, FR_COMP_HEIGHT-10);
			jrbYTD.addActionListener(new QuarterListener());
			jrbYTD.setEnabled(true);
			finQuarterPanel.add(jrbYTD);

			
		    bg = new ButtonGroup();
		    bg.add(jrbQ1);
		    bg.add(jrbQ2);
		    bg.add(jrbH1);
		    bg.add(jrbQ3);
		    bg.add(jrbQ4);
		    bg.add(jrbH2);
		    bg.add(jrbYTD);
			
			
			jlXxmOption = new JLabel();
		    jlXxmOption.setText("XXM Option: ");
		    jlXxmOption.setBounds(FR_COMP_X_POSITION+(6*FR_COMP_INCREMENT)+20,FR_COMP_Y_POSITION,FR_COMP_WIDTH,FR_COMP_HEIGHT);
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
		    jcbxXxmOption.setBounds(FR_COMP_X_POSITION+(7*FR_COMP_INCREMENT)+40, FR_COMP_Y_POSITION, FR_COMP_WIDTH-20, FR_COMP_HEIGHT);
		    jcbxXxmOption.addItemListener(new xxmOptionListener());
		    jcbxXxmOption.setSelectedIndex(-1);
		    mainPanel.add(jcbxXxmOption);

		    jlEU = new JLabel();
		    jlEU.setText("Execution Unit: ");
		    jlEU.setFont(new Font(fontFamily,Font.BOLD,fontSize));
		    jlEU.setBounds(FR_COMP_X_POSITION+(8*FR_COMP_INCREMENT)+60, FR_COMP_Y_POSITION, FR_COMP_WIDTH+20, FR_COMP_HEIGHT);
		    mainPanel.add(jlEU);
			jcbxEU = new JComboBox();
		    jcbxEU.setMaximumRowCount(4);
		    jcbxEU.setSelectedIndex(-1);
		    jcbxEU.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
		    jcbxEU.setBounds(FR_COMP_X_POSITION+(10*FR_COMP_INCREMENT)+45, FR_COMP_Y_POSITION, FR_COMP_WIDTH-20, FR_COMP_HEIGHT);
		    mainPanel.add(jcbxEU);
		    jcbxEU.addItemListener(new euComboListener());

		    jlRegion = new JLabel();
		    jlRegion.setText("Region: ");
		    jlRegion.setBounds(FR_COMP_X_POSITION+(11*FR_COMP_INCREMENT)+65, FR_COMP_Y_POSITION, FR_COMP_WIDTH+FR_COMP_INCREMENT+20, FR_COMP_HEIGHT);
		    jlRegion.setFont(new Font(fontFamily,Font.BOLD,fontSize));
		    mainPanel.add(jlRegion);
		    jltRegion = new JList();
		    jltRegion.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    jltRegion.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
		    regionScrollPane = new JScrollPane(jltRegion, 
		     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    regionScrollPane.setBounds(FR_COMP_X_POSITION+(12*FR_COMP_INCREMENT)+65, FR_COMP_Y_POSITION, FR_COMP_WIDTH-20, FR_COMP_HEIGHT+35);
		    mainPanel.add(regionScrollPane);
		    jltRegion.addListSelectionListener(new regionListListener());

		    jlNode = new JLabel();
		    jlNode.setText("Node: ");
		    jlNode.setFont(new Font(fontFamily,Font.BOLD,fontSize));
		    jlNode.setBounds(FR_COMP_X_POSITION+(14*FR_COMP_INCREMENT)+35, FR_COMP_Y_POSITION, FR_COMP_WIDTH+FR_COMP_INCREMENT+20, FR_COMP_HEIGHT);
		    mainPanel.add(jlNode);

		    jltNode = new JList();
		    jltNode.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    jltNode.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
		    nodeScrollPane = new JScrollPane(jltNode,
		            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    nodeScrollPane.setBounds(FR_COMP_X_POSITION+(14*FR_COMP_INCREMENT)+75, FR_COMP_Y_POSITION, FR_COMP_WIDTH+(2*FR_COMP_INCREMENT)+20, FR_COMP_HEIGHT+35);
		    mainPanel.add(nodeScrollPane);
		    jltNode.addListSelectionListener(new nodeListListener());

		    
		    jlXxm = new JLabel();
		    jlXxm.setText("xxM: ");
		    jlXxm.setFont(new Font(fontFamily,Font.BOLD,fontSize));
		    jlXxm.setBounds(FR_COMP_X_POSITION+(19*FR_COMP_INCREMENT)+40, FR_COMP_Y_POSITION, FR_COMP_WIDTH+FR_COMP_INCREMENT+20, FR_COMP_HEIGHT);
		    mainPanel.add(jlXxm);

		    jltXxm = new JList();
		    jltXxm.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    jltXxm.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
		    xxmScrollPane = new JScrollPane(jltXxm,
		            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    xxmScrollPane.setBounds(FR_COMP_X_POSITION+(20*FR_COMP_INCREMENT)+20, FR_COMP_Y_POSITION, FR_COMP_WIDTH+(4*FR_COMP_INCREMENT)+20, FR_COMP_HEIGHT+35);
		    mainPanel.add(xxmScrollPane);
		    jltXxm.addListSelectionListener(new xxmListListener());
		    
			}
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
	private class QuarterListener implements ActionListener {
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void actionPerformed(ActionEvent e) {
	    	try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				if (e.getSource() == jrbQ1 || e.getSource() == jrbQ2  || 
						e.getSource() == jrbQ3  || e.getSource() == jrbQ4 
								  || e.getSource() == jrbH1   || e.getSource() == jrbH2 || e.getSource() == jrbYTD) {
		               dummyModel = new DefaultListModel();
			    	   dummyModel.removeAllElements();
			    	   jltRegion.setModel(dummyModel);
			    	   jltNode.setModel(dummyModel);
			    	   jltXxm.setModel(dummyModel);
					   jcbxXxmOption.setSelectedIndex(-1);
					   jcbxEU.setSelectedIndex(-1);
				}		
	    	} catch(Exception ex) {
	    		ex.printStackTrace();
	    	} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    	}
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
//		    	   jltPartner.setModel(dummyModel);

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
//		    	   jltPartner.setModel(dummyModel);
		    	   
		   	    if (DesktopPrimaryScreen.role.equals("HEAD") || DesktopPrimaryScreen.role.equals("ADMIN")) {
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
//			    	   jltPartner.setModel(dummyModel);
			    	@SuppressWarnings("rawtypes")
					ListModel model = jltRegion.getModel();
			        node = new DefaultListModel();
			        for (int i = 0; i < model.getSize(); i++){
			            if (jltRegion.isSelectedIndex(i)) {
			            	tString1 = (String)jcbxXxmOption.getSelectedItem();
			            	tString2 = (String)jcbxEU.getSelectedItem();
			            	tString3 = (String)model.getElementAt(i);
			            	if (DesktopPrimaryScreen.role.equals("HEAD") || DesktopPrimaryScreen.role.equals("ADMIN") ) {
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
//			    	   jltPartner.setModel(dummyModel);
			        @SuppressWarnings("rawtypes")
					ListModel model = jltNode.getModel();
			        xxM = new DefaultListModel();
			    	userFillModel = new DefaultListModel();
			        for (int i = 0; i < model.getSize(); i++){
			            if (jltNode.isSelectedIndex(i)) {
			            	tString1 = (String)jcbxXxmOption.getSelectedItem();
			            	tString2 = (String)jcbxEU.getSelectedItem();
			            	tString3 = (String)model.getElementAt(i);
			            	if (DesktopPrimaryScreen.role.equals("HEAD") || DesktopPrimaryScreen.role.equals("ADMIN") ) {
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
			    		if (jrbQ1.isSelected() || jrbQ2.isSelected() || jrbQ3.isSelected() || jrbQ4.isSelected()
			    				|| jrbH1.isSelected() || jrbH2.isSelected() || jrbYTD.isSelected()) {
					    	internalCompoPanel.removeAll();
							compoPanel.remove(jlPerIndexScore);
					    	screenRefresh();
			    		} else {
			    			JOptionPane.showMessageDialog(null, "Finanical Year Quarter must be selected!, Try again...");
			    		}
			    	} catch(Exception ex) {
				    	ex.printStackTrace();
				    } finally {
				    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				    }
				}
		    }
		    
		}

		@SuppressWarnings("null")
		private void screenRefresh() {
			String nodeString, nameString, regionString, finQuarter = null;
			double indexScore = 0D;
			GeneralConstants.ScorecardWaits waits = null;
			ScorecardXxmValues valueSet;
			ScorecardGoalActualValues dataGoalActual;
			ScorecardGrowthValues dataGrowth;
			ScorecardDiscountValues dataDiscount;
			ScorecardATPenetrationValues dataATPenetration;
/*			DashboardXxmOtherTableValues dataOtherTable = null;*/
			ScorecardSCPValues scpTable = null;
			ScorecardLeadsValues dataLeads;
			ScorecardPartnerPenetrationValues dataPartnerPenetration;
			ScorecardLinearityValues dataLinearity;
			ScorecardPipelineValues dataPipeline;
			ScorecardDebookingValues dataDebooking;
			RatioBoundaries[] rBoundaryBox1 = null, rBoundaryBox2 = null, rBoundaryBox3 = null, rBoundaryBox4 = null,
			rBoundaryBox5 = null, rBoundaryBox6 = null, rBoundaryBox7 = null, rBoundaryBox8 = null, rBoundaryBox9 = null,
			rBoundaryBox10 = null;
			RatioBoundaries rBound1, rBound2, rBound3, rBound4,
			rBound5, rBound6, rBound7, rBound8, rBound9,
			rBound10;
			int heightIncre = 15;
			int widthInre = 30;
			int rowIncre = 40;
			int frameWidth = 300;
			double totalPoints = 0, earnedPoints = 0;
			GeneralConstants.ComparePoint compare = null;
			if (!isItFirstTime) {
		        compoPanel.remove(jlDScreenImage1);
		        compoPanel.remove(jtaWhatIsIt);
				compoPanel.remove(jlWhatIsItTitle);
				compoPanel.remove(jbWhatIsItReadMore);
		        compoPanel.remove(jlDScreenImage2);
		        compoPanel.remove(jtaHowItWorks);
				compoPanel.remove(jlHowItWorksTitle);
		        compoPanel.remove(infoPanel);
			    compoPanel.setBackground(Color.BLUE);
		        compoPanel.setPreferredSize(new Dimension(1200,500));
		        compoPanel.add(compoScrollPane);
		        compoScrollPane.setViewportView(internalCompoPanel);
			 }
			
			
			
			if (!ComponentHelper.doesListContainJustOne(jltNode)) {
				nodeString = "ALL"; 			    	
			} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
	
			if (!ComponentHelper.doesListContainJustOne(jltXxm)) {
				nameString = "ALL"; 			    	
			} else nameString=ComponentHelper.getSelectedListItem(jltXxm);

			if (!ComponentHelper.doesListContainJustOne(jltRegion)) {
				regionString = "ALL"; 			    	
			} else regionString=ComponentHelper.getSelectedListItem(jltRegion);

			String eu = (String)jcbxEU.getSelectedItem();
			String optionString = (String)jcbxXxmOption.getSelectedItem();

			if (jrbQ1.isSelected()) {
				finQuarter = "Q1";
			} else if (jrbQ2.isSelected()) {
				finQuarter = "Q2";
			} else if (jrbH1.isSelected()) {
				finQuarter = "H1";
			} else if (jrbQ3.isSelected()) {
				finQuarter = "Q3";
			} else if (jrbQ4.isSelected()) {
				finQuarter = "Q4";
			} else if (jrbH2.isSelected()) {
				finQuarter = "H2";
			} else if (jrbYTD.isSelected()) {
				finQuarter = "YT";
			}
			
			
			valueSet = BusinessRulesXxmScoreCard.
					getXxmScorecardMetrics(jltNode, jltXxm, optionString, 
							finQuarter, eu, regionString, nodeString, nameString);
			
			
			dataGoalActual = valueSet.getGoalActualValues();
			dataGrowth = valueSet.getGrowthValues();
			dataDiscount = valueSet.getDiscountValues();
			dataATPenetration = valueSet.getATPenValues();
/*			dataOtherTable = valueSet.getOtherTableValues();*/
			scpTable = valueSet.getSCPTableValues();
			dataLeads = valueSet.getLeadsValues();
			dataPartnerPenetration = valueSet.getPartnerPenetrationValues();
			dataLinearity = valueSet.getLinearityValues();
			dataPipeline = valueSet.getPipelineValues();
			dataDebooking = valueSet.getDebookingValues();

			

			

				// Booking Vs. Goal 
				String[] box1Metrics = new String[1];
				box1Metrics[0] = "Booking/Goal";
				double[] box1Values = new double[1];
				box1Values[0] = CalcHelper.getValueInMillionUSD(dataGoalActual.getActual());
				double[] box1Thresholds = new double[1];
				box1Thresholds[0] = CalcHelper.getValueInMillionUSD(dataGoalActual.getThreshold());
				String[] box1formatMethod = new String[1];
				box1formatMethod[0] = "$";
				GeneralConstants.CompareMethod[] box1calcMethod = new GeneralConstants.CompareMethod[1];
				box1calcMethod[0] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				rBoundaryBox1 = new RatioBoundaries[1];
				rBoundaryBox1[0] = new RatioBoundaries(0.7D, 0.9D, compare.RED_WHEN_LESS);
				rBound1 = new RatioBoundaries(0.7D, 0.9D, compare.RED_WHEN_LESS);
				box1 = BusinessRulesXxmScoreCard.getMetricPanel("Booking Vs. Goal", 
						5, 50, frameWidth, 75+(box1Metrics.length*heightIncre), 
						redMark, yellowMark, greenMark, "Metrics", 
						box1Metrics, box1Values, box1Thresholds, rBoundaryBox1, box1formatMethod, 
						box1calcMethod, rBound1);
				internalCompoPanel.add(box1.getTitle());
				internalCompoPanel.add(box1.getMark());
				box1.getScore().setText("Score: " +
						new DecimalFormat("##.##").format(box1.getMetricPoint()*waits.BOOKING_VS_GOAL_WAIT.getValue()));
				internalCompoPanel.add(box1.getScore());
				internalCompoPanel.add(box1.getFrame());
				internalCompoPanel.add(box1.getLegend());
				
				earnedPoints += box1.getMetricPoint();
				totalPoints += box1Metrics.length;
				indexScore += box1.getMetricPoint()*waits.BOOKING_VS_GOAL_WAIT.getValue();
					
				// Growth 
				String[] box2Metrics = new String[1];
				box2Metrics[0] = "FY14/FY13";
				double[] box2Values = new double[1];
				box2Values[0] = CalcHelper.getValueInMillionUSD(dataGrowth.getActual());
				double[] box2Thresholds = new double[1];
				box2Thresholds[0] = CalcHelper.getValueInMillionUSD(dataGrowth.getThreshold());
				String[] box2formatMethod = new String[1];
				box2formatMethod[0] = "$";
				GeneralConstants.CompareMethod[] box2calcMethod = new GeneralConstants.CompareMethod[1];
				box2calcMethod[0] = GeneralConstants.CompareMethod.GROWTH;
				rBoundaryBox2 = new RatioBoundaries[1];
				rBoundaryBox2[0] = new RatioBoundaries(0.0D, 0.099D, compare.RED_WHEN_LESS_THAN_EQUAL_AND_GREATER_THAN_EQUAL_TO);
				rBound2 = new RatioBoundaries(0.0D, 0.099D, compare.RED_WHEN_LESS_THAN_EQUAL_AND_GREATER_THAN_EQUAL_TO);
				box2 = BusinessRulesXxmScoreCard.getMetricPanel("Growth", 
						box1.getFrame().getX()+box1.getFrame().getWidth()+widthInre, 50, frameWidth, 75+(box2Metrics.length*heightIncre), 
						redMark, yellowMark, greenMark, "Metrics", 
						box2Metrics, box2Values, box2Thresholds, rBoundaryBox2, box2formatMethod, 
						box2calcMethod, rBound2);
				internalCompoPanel.add(box2.getTitle());
				internalCompoPanel.add(box2.getMark());
				box2.getScore().setText("Score: " +
						new DecimalFormat("##.##").format(box2.getMetricPoint()*waits.GROWTH_WAIT.getValue()));
				internalCompoPanel.add(box2.getScore());
				internalCompoPanel.add(box2.getFrame());
				internalCompoPanel.add(box2.getLegend());
				earnedPoints += box2.getMetricPoint();
				totalPoints += box2Metrics.length;
				indexScore += box2.getMetricPoint()*waits.GROWTH_WAIT.getValue();
				

				// Discount
				String[] box3Metrics = new String[5];
				box3Metrics[0] = "ENT. NW";
				box3Metrics[1] = "Security";
				box3Metrics[2] = "Collab";
				box3Metrics[3] = "DCV";
				box3Metrics[4] = "Overall";
				double[] box3Values = new double[5];
				box3Values[0] = CalcHelper.getDiscount(dataDiscount.getBookActualENTNW(), dataDiscount.getListActualENTNW());
				box3Values[1] = CalcHelper.getDiscount(dataDiscount.getBookActualSecurity(), dataDiscount.getListActualSecurity());
				box3Values[2] = CalcHelper.getDiscount(dataDiscount.getBookActualCollab(), dataDiscount.getListActualCollab());
				box3Values[3] = CalcHelper.getDiscount(dataDiscount.getBookActualDCV(), dataDiscount.getListActualDCV());
				box3Values[4] = CalcHelper.getDiscount(dataDiscount.getBookActualENTNW() 
						+ dataDiscount.getBookActualSecurity() 
						+ dataDiscount.getBookActualCollab() 
						+ dataDiscount.getBookActualDCV(), 
						dataDiscount.getListActualENTNW()
						+ dataDiscount.getListActualSecurity()
						+ dataDiscount.getListActualCollab()
						+ dataDiscount.getListActualDCV());
				double[] box3Thresholds = new double[5];
				box3Thresholds[0] = CalcHelper.getDiscount(dataDiscount.getBookThresholdENTNW(), dataDiscount.getListThresholdENTNW());
				box3Thresholds[1] = CalcHelper.getDiscount(dataDiscount.getBookThresholdSecurity(), dataDiscount.getListThresholdSecurity());
				box3Thresholds[2] = CalcHelper.getDiscount(dataDiscount.getBookThresholdCollab(), dataDiscount.getListThresholdCollab());
				box3Thresholds[3] = CalcHelper.getDiscount(dataDiscount.getBookThresholdDCV(), dataDiscount.getListThresholdDCV());
				box3Thresholds[4] = CalcHelper.getDiscount(dataDiscount.getBookThresholdENTNW() 
						+ dataDiscount.getBookThresholdSecurity() 
						+ dataDiscount.getBookThresholdCollab() 
						+ dataDiscount.getBookThresholdDCV(), 
						dataDiscount.getListThresholdENTNW()
						+ dataDiscount.getListThresholdSecurity()
						+ dataDiscount.getListThresholdCollab()
						+ dataDiscount.getListThresholdDCV());
				String[] box3formatMethod = new String[5];
				box3formatMethod[0] = "%";
				box3formatMethod[1] = "%";
				box3formatMethod[2] = "%";
				box3formatMethod[3] = "%";
				box3formatMethod[4] = "%";
				GeneralConstants.CompareMethod[] box3calcMethod = new GeneralConstants.CompareMethod[5];
				box3calcMethod[0] = GeneralConstants.CompareMethod.RANGE;
				box3calcMethod[1] = GeneralConstants.CompareMethod.RANGE;
				box3calcMethod[2] = GeneralConstants.CompareMethod.RANGE;
				box3calcMethod[3] = GeneralConstants.CompareMethod.RANGE;
				box3calcMethod[4] = GeneralConstants.CompareMethod.RANGE;
				rBoundaryBox3 = new RatioBoundaries[5];
				rBoundaryBox3[0] = new RatioBoundaries(0D, box3Thresholds[0]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
				rBoundaryBox3[1] = new RatioBoundaries(0D, box3Thresholds[1]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
				rBoundaryBox3[2] = new RatioBoundaries(0D, box3Thresholds[2]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
				rBoundaryBox3[3] = new RatioBoundaries(0D, box3Thresholds[3]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
				rBoundaryBox3[4] = new RatioBoundaries(0D, box3Thresholds[4]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
				rBound3 = new RatioBoundaries(0D, box3Thresholds[4]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
				
				box3 = BusinessRulesXxmScoreCard.getMetricPanel("Discount", 
						box2.getFrame().getX()+box2.getFrame().getWidth()+widthInre, 50, frameWidth, 75+(box3Metrics.length*heightIncre), 
						redMark, yellowMark, greenMark, "Archs.", 
						box3Metrics, box3Values, box3Thresholds, rBoundaryBox3, box3formatMethod, 
						box3calcMethod, rBound3);
				internalCompoPanel.add(box3.getTitle());
				internalCompoPanel.add(box3.getMark());
				box3.getScore().setText("Score: " +
						new DecimalFormat("##.##").format(box3.getMetricPoint()*waits.DISCOUNT_WAIT.getValue()));
				internalCompoPanel.add(box3.getScore());
				internalCompoPanel.add(box3.getFrame());
				internalCompoPanel.add(box3.getLegend());
				earnedPoints += box3.getMetricPoint();
				totalPoints += box3Metrics.length;
				indexScore += box3.getMetricPoint()*waits.DISCOUNT_WAIT.getValue();

				
				// AT Attach 
				String[] box4Metrics = new String[1];
				box4Metrics[0] = "AT Attach";
				double[] box4Values = new double[1];
				box4Values[0] = CalcHelper.getATPenetration(dataATPenetration.getActualSalesAT(), dataATPenetration.getActualSalesNonAT());
				double[] box4Thresholds = new double[1];
				box4Thresholds[0] = CalcHelper.getATPenetration(dataATPenetration.getThresholdSalesAT(), dataATPenetration.getThresholdSalesNonAT());
				String[] box4formatMethod = new String[1];
				box4formatMethod[0] = "%";
				GeneralConstants.CompareMethod[] box4calcMethod = new GeneralConstants.CompareMethod[1];
//				box4calcMethod[0] = GeneralConstants.CompareMethod.RANGE;
				box4calcMethod[0] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				rBoundaryBox4 = new RatioBoundaries[1];
				rBoundaryBox4[0] = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
//				rBoundaryBox4[0] = new RatioBoundaries(0D, box4Thresholds[0]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
				rBound4 = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
//				rBound4 = new RatioBoundaries(0D, box4Thresholds[0]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
				box4 = BusinessRulesXxmScoreCard.getMetricPanel("AT Attach", 
						box3.getFrame().getX()+box3.getFrame().getWidth()+widthInre, 50, frameWidth, 75+(box4Metrics.length*heightIncre), 
						redMark, yellowMark, greenMark, "Metrics", 
						box4Metrics, box4Values, box4Thresholds, rBoundaryBox4, box4formatMethod, 
						box4calcMethod, rBound4);
				internalCompoPanel.add(box4.getTitle());
				internalCompoPanel.add(box4.getMark());
				box4.getScore().setText("Score: " +
						new DecimalFormat("##.##").format(box4.getMetricPoint()*waits.AT_ATTACH_WAIT.getValue()));
				internalCompoPanel.add(box4.getScore());
				internalCompoPanel.add(box4.getFrame());
				internalCompoPanel.add(box4.getLegend());
				earnedPoints += box4.getMetricPoint();
				totalPoints += box4Metrics.length;
				indexScore += box4.getMetricPoint()*waits.AT_ATTACH_WAIT.getValue();
				
				// SCP Performance
				String[] box5Metrics = new String[4];
				box5Metrics[0] = "A/c. Penetration";
				box5Metrics[1] = "Yield/Acs";
				box5Metrics[2] = "Tech. Penetration";
				box5Metrics[3] = "Discount";
				double[] box5Values = new double[4];
				
				box5Values[0] = scpTable.getActualAccountPenetration();
				box5Values[1] = CalcHelper.getYield(scpTable.getActualRevenue(), scpTable.getActualAccountPenetration());
				box5Values[2] = CalcHelper.getTechnologyPenetration(scpTable.getActualTechnologyCount(), scpTable.getActualAccountPenetration());
				box5Values[3] = CalcHelper.getDiscount(scpTable.getActualBookingNet(), scpTable.getActualBookingList());

				double[] box5Thresholds = new double[4];
				box5Thresholds[0] = scpTable.getThresholdTotalAccounts()*0.25D;
				box5Thresholds[1] = 10226.06D;
				box5Thresholds[2] = 3.2D;
				box5Thresholds[3] = 0.70D;
				String[] box5formatMethod = new String[4];
				box5formatMethod[0] = "#";
				box5formatMethod[1] = "$";
				box5formatMethod[2] = "#.#";
				box5formatMethod[3] = "%";
				GeneralConstants.CompareMethod[] box5calcMethod = new GeneralConstants.CompareMethod[4];
				box5calcMethod[0] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				box5calcMethod[1] = GeneralConstants.CompareMethod.RANGE;
				box5calcMethod[2] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				box5calcMethod[3] = GeneralConstants.CompareMethod.RANGE;
				rBoundaryBox5 = new RatioBoundaries[4];
				rBoundaryBox5[0] = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
				rBoundaryBox5[1] = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
				rBoundaryBox5[2] = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
//				rBoundaryBox5[3] = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
				rBoundaryBox5[3] = new RatioBoundaries(0D, box5Thresholds[3]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
				rBound5 = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
				box5 = BusinessRulesXxmScoreCard.getMetricPanel("SCP Performance", 
						box1.getFrame().getX(), box3.getLegend().getY()+box3.getLegend().getHeight()+rowIncre, frameWidth, 75+(box5Metrics.length*heightIncre), 
						redMark, yellowMark, greenMark, "Metrics.", 
						box5Metrics, box5Values, box5Thresholds, rBoundaryBox5, box5formatMethod, 
						box5calcMethod, rBound5);
				internalCompoPanel.add(box5.getTitle());
				internalCompoPanel.add(box5.getMark());
				box5.getScore().setText("Score: " +
						new DecimalFormat("##.##").format(box5.getMetricPoint()*waits.SCP_WAIT.getValue()));
				internalCompoPanel.add(box5.getScore());
				internalCompoPanel.add(box5.getFrame());
				internalCompoPanel.add(box5.getLegend());
				earnedPoints += box5.getMetricPoint();
				totalPoints += box5Metrics.length;
				indexScore += box5.getMetricPoint()*waits.SCP_WAIT.getValue();
				

				// Leads
				String[] box6Metrics = new String[3];
				box6Metrics[0] = "Leads Waiting";
				box6Metrics[1] = "Accptd, Not Convrtd";
				box6Metrics[2] = "Converted";
				double[] box6Values = new double[3];
//				box6Values[0] = CalcHelper.getRatio(dataLeads.getLeadsWaitingActual(), dataLeads.getLeadsOverallActual());
//				box6Values[1] = CalcHelper.getRatio(dataLeads.getLeadsAcceptedActual(), dataLeads.getLeadsOverallActual());
//				box6Values[2] = CalcHelper.getRatio(dataLeads.getLeadsConvertedActual(), dataLeads.getLeadsOverallActual());
				box6Values[0] = dataLeads.getLeadsWaitingActual();
				box6Values[1] = dataLeads.getLeadsAcceptedActual();
				box6Values[2] = dataLeads.getLeadsConvertedActual();
				double[] box6Thresholds = new double[3];
//				box6Thresholds[0] = CalcHelper.getRatio(dataLeads.getLeadsWaitingThreshold(), dataLeads.getLeadsOverallThreshold());
//				box6Thresholds[1] = CalcHelper.getRatio(dataLeads.getLeadsAcceptedThreshold(), dataLeads.getLeadsOverallThreshold());
//				box6Thresholds[2] = CalcHelper.getRatio(dataLeads.getLeadsConvertedThreshold(), dataLeads.getLeadsOverallThreshold());
				box6Thresholds[0] = dataLeads.getLeadsWaitingThreshold();
				box6Thresholds[1] = dataLeads.getLeadsAcceptedThreshold();
				box6Thresholds[2] = dataLeads.getLeadsConvertedThreshold();
				String[] box6formatMethod = new String[3];
				box6formatMethod[0] = "$";
				box6formatMethod[1] = "$";
				box6formatMethod[2] = "$";
				GeneralConstants.CompareMethod[] box6calcMethod = new GeneralConstants.CompareMethod[3];
				box6calcMethod[0] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				box6calcMethod[1] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				box6calcMethod[2] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				rBoundaryBox6 = new RatioBoundaries[3];
//				rBoundaryBox6[0] = new RatioBoundaries(0.3D, 0.5D, compare.GREEN_WHEN_LESS);
				rBoundaryBox6[0] = new RatioBoundaries(0D, box6Thresholds[0]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
//				rBoundaryBox6[1] = new RatioBoundaries(0.3D, 0.5D, compare.GREEN_WHEN_LESS);
				rBoundaryBox6[1] = new RatioBoundaries(0D, box6Thresholds[1]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
				rBoundaryBox6[2] = new RatioBoundaries(0D, 0.5D, compare.RED_WHEN_LESS_THAN_EQUAL_AND_GREATER_THAN_EQUAL_TO);
				rBound6 = new RatioBoundaries(0.7D, 1.0D, compare.RED_WHEN_LESS);
				box6 = BusinessRulesXxmScoreCard.getMetricPanel("Leads", 
						box5.getFrame().getX()+box5.getFrame().getWidth()+widthInre, 
						box3.getLegend().getY()+box3.getLegend().getHeight()+rowIncre, 
						frameWidth, 75+(box6Metrics.length*heightIncre), 
						redMark, yellowMark, greenMark, "Metrics.", 
						box6Metrics, box6Values, box6Thresholds, rBoundaryBox6, box6formatMethod, 
						box6calcMethod, rBound6);
				internalCompoPanel.add(box6.getTitle());
				internalCompoPanel.add(box6.getMark());
				box6.getScore().setText("Score: " +
						new DecimalFormat("##.##").format(box6.getMetricPoint()*waits.LEADS_WAIT.getValue()));
				internalCompoPanel.add(box6.getScore());
				internalCompoPanel.add(box6.getFrame());
				internalCompoPanel.add(box6.getLegend());
				earnedPoints += box6.getMetricPoint();
				totalPoints += box6Metrics.length;
				indexScore += box6.getMetricPoint()*waits.LEADS_WAIT.getValue();
				
				
				// De-Booking 
				String[] box7Metrics = new String[1];
				box7Metrics[0] = "De-Booking";
				double[] box7Values = new double[1];
				box7Values[0] = CalcHelper.getValueInMillionUSD(dataDebooking.getActual());
				double[] box7Thresholds = new double[1];
				box7Thresholds[0] = CalcHelper.getValueInMillionUSD(dataDebooking.getThreshold());
				String[] box7formatMethod = new String[1];
				box7formatMethod[0] = "$";
				GeneralConstants.CompareMethod[] box7calcMethod = new GeneralConstants.CompareMethod[1];
				box7calcMethod[0] = GeneralConstants.CompareMethod.NEGATIVE_ACHIEVEMENT;
				rBoundaryBox7 = new RatioBoundaries[1];
				rBoundaryBox7[0] = new RatioBoundaries(0D, box7Thresholds[0]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
//				rBound7 = new RatioBoundaries(0.05D, 0.1D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
				rBound7 = new RatioBoundaries(0.9D, 1.0D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
//				rBound7 = new RatioBoundaries(0D, box7Thresholds[0]*0.05D, compare.GREEN_WHEN_LESS);
				box7 = BusinessRulesXxmScoreCard.getMetricPanel("De-Booking", 
						box6.getFrame().getX()+box6.getFrame().getWidth()+widthInre, 
						box3.getLegend().getY()+box3.getLegend().getHeight()+rowIncre, 
						frameWidth, 75+(box7Metrics.length*heightIncre), 
						redMark, yellowMark, greenMark, "Metrics", 
						box7Metrics, box7Values, box7Thresholds, rBoundaryBox7, box7formatMethod, 
						box7calcMethod, rBound7);
				internalCompoPanel.add(box7.getTitle());
				internalCompoPanel.add(box7.getMark());
				box7.getScore().setText("Score: " +
						new DecimalFormat("##.##").format(box7.getMetricPoint()*waits.DEBOOKING_WAIT.getValue()));
				internalCompoPanel.add(box7.getScore());
				internalCompoPanel.add(box7.getFrame());
				internalCompoPanel.add(box7.getLegend());
				earnedPoints += box7.getMetricPoint();
				totalPoints += box7Metrics.length;
				indexScore += box7.getMetricPoint()*waits.DEBOOKING_WAIT.getValue();

				
				// Linearity
				String[] box8Metrics = new String[3];
				box8Metrics[0] = "As of M1";
				box8Metrics[1] = "As of M2";
				box8Metrics[2] = "As of M3";
				double[] box8Values = new double[3];
				box8Values[0] = CalcHelper.getValueInMillionUSD(dataLinearity.getLeadsM1Actual());
				box8Values[1] = CalcHelper.getValueInMillionUSD(dataLinearity.getLeadsM1Actual()+dataLinearity.getLeadsM2Actual());
				box8Values[2] = CalcHelper.getValueInMillionUSD(dataLinearity.getLeadsM1Actual()+dataLinearity.getLeadsM2Actual()+
						+dataLinearity.getLeadsM3Actual());
				double[] box8Thresholds = new double[3];
				box8Thresholds[0] = CalcHelper.getValueInMillionUSD(dataLinearity.getLeadsM1Threshold());
				box8Thresholds[1] = CalcHelper.getValueInMillionUSD(dataLinearity.getLeadsM2Threshold());
				box8Thresholds[2] = CalcHelper.getValueInMillionUSD(dataLinearity.getLeadsM3Threshold());
				String[] box8formatMethod = new String[3];
				box8formatMethod[0] = "$";
				box8formatMethod[1] = "$";
				box8formatMethod[2] = "$";
				GeneralConstants.CompareMethod[] box8calcMethod = new GeneralConstants.CompareMethod[3];
				box8calcMethod[0] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				box8calcMethod[1] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				box8calcMethod[2] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				rBoundaryBox8 = new RatioBoundaries[3];
				rBoundaryBox8[0] = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
				rBoundaryBox8[1] = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
				rBoundaryBox8[2] = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
				rBound8 = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
				box8 = BusinessRulesXxmScoreCard.getMetricPanel("Linearity", 
						box7.getFrame().getX()+box7.getFrame().getWidth()+widthInre, 
						box3.getLegend().getY()+box3.getLegend().getHeight()+rowIncre, 
						frameWidth, 75+(box8Metrics.length*heightIncre), 
						redMark, yellowMark, greenMark, "Months.", 
						box8Metrics, box8Values, box8Thresholds, rBoundaryBox8, box8formatMethod, 
						box8calcMethod, rBound8);
				internalCompoPanel.add(box8.getTitle());
				internalCompoPanel.add(box8.getMark());
				box8.getScore().setText("Score: " +
						new DecimalFormat("##.##").format(box8.getMetricPoint()*waits.LINEARITY_WAIT.getValue()));
				internalCompoPanel.add(box8.getScore());
				internalCompoPanel.add(box8.getFrame());
				internalCompoPanel.add(box8.getLegend());
				earnedPoints += box8.getMetricPoint();
				totalPoints += box8Metrics.length;
				indexScore += box8.getMetricPoint()*waits.LINEARITY_WAIT.getValue();
				
				
				// Forecast Pipeline
				String[] box9Metrics = new String[5];
				box9Metrics[0] = "Quarter-1";
				box9Metrics[1] = "Quarter-2";
				box9Metrics[2] = "Quarter-3";
				box9Metrics[3] = "Quarter-4";
				box9Metrics[4] = "Overall";
				double[] box9Values = new double[5];
				box9Values[0] = CalcHelper.getValueInMillionUSD(dataPipeline.getActualQ1());
				box9Values[1] = CalcHelper.getValueInMillionUSD(dataPipeline.getActualQ2());
				box9Values[2] = CalcHelper.getValueInMillionUSD(dataPipeline.getActualQ3());
				box9Values[3] = CalcHelper.getValueInMillionUSD(dataPipeline.getActualQ4());
				box9Values[4] = box9Values[0] + box9Values[1] + box9Values[2] + box9Values[3];
				double[] box9Thresholds = new double[5];
				box9Thresholds[0] = CalcHelper.getValueInMillionUSD(dataPipeline.getThresholdQ1());
				box9Thresholds[1] = CalcHelper.getValueInMillionUSD(dataPipeline.getThresholdQ2());
				box9Thresholds[2] = CalcHelper.getValueInMillionUSD(dataPipeline.getThresholdQ3());
				box9Thresholds[3] = CalcHelper.getValueInMillionUSD(dataPipeline.getThresholdQ4());
				box9Thresholds[4] = box9Thresholds[0] + box9Thresholds[1] + box9Thresholds[2] + box9Thresholds[3];
				String[] box9formatMethod = new String[5];
				box9formatMethod[0] = "$";
				box9formatMethod[1] = "$";
				box9formatMethod[2] = "$";
				box9formatMethod[3] = "$";
				box9formatMethod[4] = "$";
				GeneralConstants.CompareMethod[] box9calcMethod = new GeneralConstants.CompareMethod[5];
				box9calcMethod[0] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				box9calcMethod[1] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				box9calcMethod[2] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				box9calcMethod[3] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				box9calcMethod[4] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				rBoundaryBox9 = new RatioBoundaries[5];
				rBoundaryBox9[0] = new RatioBoundaries(1.5D, 2.0D, compare.RED_WHEN_LESS);
				rBoundaryBox9[1] = new RatioBoundaries(1.5D, 2.0D, compare.RED_WHEN_LESS);
				rBoundaryBox9[2] = new RatioBoundaries(1.5D, 2.0D, compare.RED_WHEN_LESS);
				rBoundaryBox9[3] = new RatioBoundaries(1.5D, 2.0D, compare.RED_WHEN_LESS);
				rBoundaryBox9[4] = new RatioBoundaries(1.5D, 2.0D, compare.RED_WHEN_LESS);
				rBound9 = new RatioBoundaries(1.5D, 2.0D, compare.RED_WHEN_LESS);
				box9 = BusinessRulesXxmScoreCard.getMetricPanel("Forecast Pipeline", 
						box1.getFrame().getX(), box5.getLegend().getY()+box5.getLegend().getHeight()+rowIncre, frameWidth, 75+(box9Metrics.length*heightIncre), 
						redMark, yellowMark, greenMark, "Fin. Qrtrs", 
						box9Metrics, box9Values, box9Thresholds, rBoundaryBox9, box9formatMethod, 
						box9calcMethod, rBound9);
				internalCompoPanel.add(box9.getTitle());
				internalCompoPanel.add(box9.getMark());
				box9.getScore().setText("Score: " +
						new DecimalFormat("##.##").format(box9.getMetricPoint()*waits.PIPELINE_WAIT.getValue()));
				internalCompoPanel.add(box9.getScore());
				internalCompoPanel.add(box9.getFrame());
				internalCompoPanel.add(box9.getLegend());
				earnedPoints += box9.getMetricPoint();
				totalPoints += box9Metrics.length;
				indexScore += box9.getMetricPoint()*waits.PIPELINE_WAIT.getValue();

				
				// Partner Penetration 
				String[] box10Metrics = new String[1];
				box10Metrics[0] = "Part.Penetration";
				double[] box10Values = new double[1];
				box10Values[0] = dataPartnerPenetration.getActual();
				double[] box10Thresholds = new double[1];
				box10Thresholds[0] = dataPartnerPenetration.getThreshold();
				String[] box10formatMethod = new String[1];
				box10formatMethod[0] = "#";
				GeneralConstants.CompareMethod[] box10calcMethod = new GeneralConstants.CompareMethod[1];
				box10calcMethod[0] = GeneralConstants.CompareMethod.ACHIEVEMENT;
				rBoundaryBox10 = new RatioBoundaries[1];
				rBoundaryBox10[0] = new RatioBoundaries(0.8D, 0.95D, compare.RED_WHEN_LESS);
				rBound10 = new RatioBoundaries(0.8D, 0.95D, compare.RED_WHEN_LESS);
				box10 = BusinessRulesXxmScoreCard.getMetricPanel("Partner Penetration", 
						box9.getFrame().getX()+box9.getFrame().getWidth()+widthInre, 
						box5.getLegend().getY()+box5.getLegend().getHeight()+rowIncre, 
						frameWidth, 75+(box10Metrics.length*heightIncre), 
						redMark, yellowMark, greenMark, "Metrics", 
						box10Metrics, box10Values, box10Thresholds, rBoundaryBox10, box10formatMethod, 
						box10calcMethod, rBound10);
				internalCompoPanel.add(box10.getTitle());
				internalCompoPanel.add(box10.getMark());
				box10.getScore().setText("Score: " +
						new DecimalFormat("##.##").format(box10.getMetricPoint()*waits.PARTNER_PENETRATION_WAIT.getValue()));
				internalCompoPanel.add(box10.getScore());
				internalCompoPanel.add(box10.getFrame());
				internalCompoPanel.add(box10.getLegend());
				earnedPoints += box10.getMetricPoint();
				totalPoints += box10Metrics.length;
				indexScore += box10.getMetricPoint()*waits.PARTNER_PENETRATION_WAIT.getValue();

				
				
				
				jlPerIndexScore.setText("Performance Index Score: " + 
				new DecimalFormat("##.##").format(indexScore));
				jlPerIndexScore.setBounds(10, 10, 350, 25);
				jlPerIndexScore.setFont(new Font(fontFamily,Font.BOLD,fontSize+10));
				compoPanel.add(jlPerIndexScore);
				
		        getContentPane().revalidate();
		        getContentPane().repaint();

		}
		
		
		
		
}
