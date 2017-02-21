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
import java.awt.Image;
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
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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



public class DashboardStackRanking extends JInternalFrame{

	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
    private ImageIcon screenTitle, imageIcon, imgLogo, trueNorthImage, primaryScreenTitle;
    private JLabel jlScreenTitle, JlPrimaryScreenTitle, jlLogo, jlDateTime, jlTrueNorthImage, 
    jlFinQuarter, jlStackBy, jlEU, jlHeader1, jlSliderInfo;
	private JButton jbShow;
    private JSeparator sep1, sep11,sep2, sep3;
    @SuppressWarnings("rawtypes")
	private JComboBox jcbxFinQuarter, jcbxStackBy, jcbxXxmOption, jcbxEU;
    @SuppressWarnings("rawtypes")
    protected GradientPanel2 bannerPanel, panel1, holderEU, holderEU2, holderEU3, holderEUAll;
    protected GradientPanel mainPanel, compoPanel, InternalCompoPanel1, 
    InternalCompoPanel2, InternalCompoPanel3, InternalCompoPanel4;
    private DateFormat dateFormat;
    private Date currentDate;
    protected JScrollPane mainScrollPane, rootScrollPane, InternalCompoScrollPanel1, 
    InternalCompoScrollPanel2, InternalCompoScrollPanel3, InternalCompoScrollPanel4;
    protected int fontSize, tempCount;
    protected String fontFamily, fontSpecialFamily, dynamicHeader;
    private JRadioButton jrbQ1, jrbQ2, jrbH1, jrbQ3, jrbQ4, jrbH2, jrbYTD; 
    private ArrayList<String> quarterArray, stackByArray, xxmOptionArray, euArray;
    private DefaultComboBoxModel quarterModel, stackByModel, xxmOptionModel, euModel;
    private DataBaseSalesAgentListQueries salesListQueries;
    private DataBaseSalesAgentList currentSalesListEntry;
    private List<DataBaseSalesAgentList> resultsSalesList;
    private String[] salesAgentName, salesLevel6, salesAgentType, executionUnit, region, userName;
    private double[] points;
    DashboardScoreCard2CompoGroup box1, box2, box3, box4, box5, box6, box7, box8, box9, box10;
    private ImageIcon redMark, yellowMark, greenMark;
    private boolean isFirstTimeOver;
    private JSlider rankingSlider;
    private int dStart, dEnd;
    private JPanel legend;
    
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
	public DashboardStackRanking() {
		super("Stack Ranking #" + (++openFrameCount),
				true, true, true, true);
		trueNorthImage = new ImageIcon(this.getClass().getResource(GeneralConstants.TRUE_NORTH_IMAGE2));
    	imageIcon = new ImageIcon(this.getClass().getResource(GeneralConstants.INTERNAL_FRAME_LOGO_IMAGE));
        fontSize = 11;
        fontFamily = "Arial";
        fontSpecialFamily = "Verdana";
        dStart = 0;
        dEnd = 19;
		setSize(700,500);
		setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
		
		screenTitle = new ImageIcon(this.getClass().getResource(GeneralConstants.STACK_RANKING_TITLE));


		jlScreenTitle = new JLabel(screenTitle);
		jlScreenTitle.setBounds(400, 15, 600, 50);
		jlScreenTitle.setOpaque(false);
		quarterArray = new ArrayList<String>();
		stackByArray = new ArrayList<String>();
		xxmOptionArray = new ArrayList<String>();
		euArray = new ArrayList<String>();
        try {
	        salesListQueries = new DataBaseSalesAgentListQueries();
	        }
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException iException){
			iException.printStackTrace();
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
        initiateStackRanking();
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

	protected void setPanelReady() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	    ImageIcon redMark, yellowMark, greenMark;
		GeneralConstants.ComparePoint compare = null;
		RatioBoundaries rBound;
		rBound = new RatioBoundaries(50D, 75D, compare.RED_WHEN_LESS);
		redMark = new ImageIcon(this.getClass().getResource(GeneralConstants.RED_BALL2));
		yellowMark = new ImageIcon(this.getClass().getResource(GeneralConstants.YELLOW_BALL2));
		greenMark = new ImageIcon(this.getClass().getResource(GeneralConstants.GREEN_BALL2));
        Font font = new Font("Arial", Font.BOLD, 9);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		Dimension di = new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
		int scrollPaneOffset = 5;
		int headerYOffset = 20;
        rootScrollPane = new JScrollPane();
        rootScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        rootScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rootScrollPane.setPreferredSize(di);

		mainPanel = new GradientPanel();
        mainPanel.setBackground(Color.BLUE);
        mainPanel.setLayout(null);
        rootScrollPane.setViewportView(mainPanel);
        setContentPane(rootScrollPane);
        
		compoPanel = new GradientPanel();
        compoPanel.setBackground(Color.BLUE);
        compoPanel.setPreferredSize(new Dimension(1200,600));
        compoPanel.setFont(new Font(fontFamily,Font.BOLD,fontSize));

        mainScrollPane = new JScrollPane();
        mainScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setBounds(0, 130, 1355, 510);
        mainScrollPane.setViewportView(compoPanel);

        InternalCompoPanel1 = new GradientPanel();
        InternalCompoPanel1.setBackground(new Color(250,250,250,150));
        InternalCompoPanel1.setPreferredSize(new Dimension(375,475));
        InternalCompoPanel1.setFont(new Font(fontFamily,Font.BOLD,fontSize));

        InternalCompoScrollPanel1 = new JScrollPane();
        InternalCompoScrollPanel1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        InternalCompoScrollPanel1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        InternalCompoScrollPanel1.setBounds(10, 50, 1300, 450);
        InternalCompoScrollPanel1.setViewportView(InternalCompoPanel1);
        
	    jlHeader1 = new JLabel("Stack Ranking Based on Index Score");
	    jlHeader1.setBounds(InternalCompoScrollPanel1.getX(), 
	    		InternalCompoScrollPanel1.getY()-headerYOffset, 350, 20);
	    compoPanel.add(jlHeader1);

	    mainPanel.add(mainScrollPane);
        compoPanel.add(InternalCompoScrollPanel1);
	    
        rankingSlider = ComponentHelper.placeWhatIfJSlider(1, 3, 1, 1, 1, 
	    		250, 5, 100, 40, Color.DARK_GRAY.darker(), font);
        rankingSlider.setEnabled(true);
        rankingSlider.addChangeListener(new SliderListener());
        compoPanel.add(rankingSlider);

	    jlSliderInfo = new JLabel();
	    jlSliderInfo.setBounds(rankingSlider.getX()+rankingSlider.getWidth()+5, 5, 100, 15);
	    jlSliderInfo.setFont(new Font("Arial", Font.PLAIN, 9));
	    compoPanel.add(jlSliderInfo);

	    legend = ComponentHelper.getColorLegend(redMark, yellowMark, greenMark, rBound, 
	    		jlSliderInfo.getX()+350, jlSliderInfo.getY()+jlSliderInfo.getHeight()+5, 300, 25);
	    compoPanel.add(legend);
	    
	}

	private class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			if (!source.getValueIsAdjusting()) {
				int rankingSliderValue = rankingSlider.getValue();
				jlSliderInfo.setText(new DecimalFormat("###").format((rankingSliderValue*20)-19) +
						" To " + new DecimalFormat("###").format(rankingSliderValue*20) + " Records");
				getContentPane().revalidate();
		    	getContentPane().repaint();
		        dStart = (rankingSlider.getValue()*20)-20;
		        dEnd = dStart+19;
		    	initiateStackRanking();
			}
		}
	}

	
    protected void setComponentsReady() {
		try {
	    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
	    sep1 = new JSeparator();
	    sep1.setBounds(0, 95,1360,2);
	    mainPanel.add(sep1);

	    sep11 = new JSeparator();
	    sep11.setBounds(1, 130,1360,2);
	    mainPanel.add(sep11);
	    
	    jlFinQuarter = new JLabel("Fin Quarter:");
	    jlFinQuarter.setBounds(10, 100, 70, 15);
	    mainPanel.add(jlFinQuarter);
	    
	    quarterArray.add("Q1");
	    quarterArray.add("Q2");
	    quarterArray.add("H1");
	    quarterArray.add("Q3");
	    quarterArray.add("Q4");
	    quarterArray.add("H2");
	    quarterArray.add("YTD");
	    quarterModel = new DefaultComboBoxModel (quarterArray.toArray());
	    jcbxFinQuarter = new JComboBox();
	    jcbxFinQuarter.setModel(quarterModel);;
	    jcbxFinQuarter.setMaximumRowCount(quarterArray.size());
	    jcbxFinQuarter.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
	    jcbxFinQuarter.setBounds(85, 100, 50, 15);
	    jcbxFinQuarter.setSelectedIndex(0);
	    mainPanel.add(jcbxFinQuarter);

	    jlStackBy = new JLabel("Stack Ranking By:");
	    jlStackBy.setBounds(145, 100, 110, 15);
	    mainPanel.add(jlStackBy);

	    stackByArray.add("Score Index");
	    stackByArray.add("Goal Vs. Booking");
	    stackByArray.add("Growth");
	    stackByArray.add("Discount");
	    stackByArray.add("AT Attach");
	    stackByArray.add("SCP Performance");
	    stackByArray.add("Leads");
	    stackByArray.add("De-Booking");
	    stackByArray.add("Linearity");
	    stackByArray.add("Forecast Pipeline");
	    stackByArray.add("Partner Penetration");
	    stackByModel = new DefaultComboBoxModel (stackByArray.toArray());
	    jcbxStackBy = new JComboBox();
	    jcbxStackBy.setModel(stackByModel);
	    jcbxStackBy.setMaximumRowCount(stackByArray.size());
	    jcbxStackBy.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
	    jcbxStackBy.setBounds(260, 100, 120, 15);
	    jcbxStackBy.setSelectedIndex(0);
	    mainPanel.add(jcbxStackBy);

	    
	    jlStackBy = new JLabel("Xxm Option:");
	    jlStackBy.setBounds(390, 100, 70, 15);
	    mainPanel.add(jlStackBy);

	    xxmOptionArray.add("TBM");
	    xxmOptionArray.add("VBM");
	    xxmOptionArray.add("ALL");
	    xxmOptionModel = new DefaultComboBoxModel (xxmOptionArray.toArray());
	    jcbxXxmOption = new JComboBox();
	    jcbxXxmOption.setModel(xxmOptionModel);
	    jcbxXxmOption.setMaximumRowCount(xxmOptionArray.size());
	    jcbxXxmOption.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
	    jcbxXxmOption.setBounds(465, 100, 50, 15);
	    jcbxXxmOption.setSelectedIndex(0);
	    mainPanel.add(jcbxXxmOption);

	    jlEU = new JLabel("Execution Unit:");
	    jlEU.setBounds(jcbxXxmOption.getX()+jcbxXxmOption.getWidth()+10, jcbxXxmOption.getY(), 100, 15);
	    mainPanel.add(jlEU);

	    euArray.add("EU1");
	    euArray.add("EU2");
	    euArray.add("EU3");
	    euArray.add("ALL");
	    euModel = new DefaultComboBoxModel (euArray.toArray());
	    jcbxEU = new JComboBox();
	    jcbxEU.setModel(euModel);
	    jcbxEU.setMaximumRowCount(euArray.size());
	    jcbxEU.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
	    jcbxEU.setBounds(jlEU.getX()+jlEU.getWidth()+5, jlEU.getY(), 50, 15);
	    jcbxEU.setSelectedIndex(3);
	    mainPanel.add(jcbxEU);

	    
	    Image override =  ImageIO.read(this.getClass().getResource(GeneralConstants.SWITCH_ON_IMAGE));
	    jbShow = ComponentHelper.getSwitchButton(override, jcbxEU.getX()+jcbxEU.getWidth()+10, jcbxEU.getY(), 50, 25);
	    jbShow.addActionListener(new ShowButtonListener());
	    mainPanel.add(jbShow);

		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
	    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

    
	private void setEUView(List<StackRankers> top) {
		ChartPanel  cPanelEU;
	    StackRankers topEntry;
	    String labelText;
	    holderEU = new GradientPanel2();
		int splitYPos = 35, splitYPosIncre = 19, xPos=15, width=350,		
		height=10, xPosOffset= 530; 
		String[] salesAgentsEU = new String[20];
		String[] salesLevel6 = new String[20];
		String[] executionUnit = new String[20];
		String[] salesAgentType = new String[20];
		CategoryDataset dataSetEU;
		double[][] valuesEU = new double[1][20];

		if (isFirstTimeOver) {
			InternalCompoPanel1.removeAll();
	    }

		int j = 0;
		topEntry = null;
		for (int i=dStart; i<dEnd+1; i++) {
			if (i < top.size()) {
		    	topEntry = top.get(i);
		    	salesAgentsEU[j] = topEntry.getName();
		    	salesLevel6[j] = topEntry.getNode();
		    	executionUnit[j] = topEntry.getExecutionUnit();
		    	salesAgentType[j] = topEntry.getSalesAgentType();
		    	valuesEU[0][j] = topEntry.getValue();
			} else {
		    	salesAgentsEU[j] = ""; 
		    	salesLevel6[j] = "";
		    	executionUnit[j] = "";
		    	salesAgentType[j] = "";
				valuesEU[0][j] = 0D;
			}
			j++;

		}
		
	    // Top TBM Horizontal Bar Graph
		dataSetEU = DatasetUtilities.createCategoryDataset("", "", valuesEU);
	    
		cPanelEU = ComponentHelper.getStackRankingGraph(dataSetEU);
		cPanelEU.setBounds(370, 5, 170, 430);
	    holderEU.setBackground(new Color(170, 170, 220));
	    holderEU.setOpaque(false);
	    holderEU.setBounds(200, 5, 800, 430);
	    holderEU.setLayout(null);
	    holderEU.add(cPanelEU);
		ComponentHelper.placeTitle(holderEU, dynamicHeader + " Top TBM/VBM(s)", 330, 5, 200, 15);
		
		for (int i=0; i<salesAgentsEU.length; i++) {
			if (valuesEU[0][i] == 0) {
				labelText = "";
			} else {
				labelText = (dStart+i+1) + ") " + salesAgentsEU[i] + " (" +
						salesAgentType[i] + " / " + salesLevel6[i] + " / " + executionUnit[i] + ")";
			}
			StackRankersComponent obj = 
					ComponentHelper.getTopGraphLabels(
							labelText, xPos, splitYPos+(i*splitYPosIncre), 
							width, height, xPosOffset, valuesEU[0][i]);
			holderEU.add(obj.getNameLabel());
			holderEU.add(obj.getValueLabel());
		}
		InternalCompoPanel1.add(holderEU);
		isFirstTimeOver = true;
		getContentPane().revalidate();
    	getContentPane().repaint();
    	salesAgentsEU = null;
    	valuesEU = null;
	}

	private void initiateStackRanking() {
    	List<StackRankers> top = new ArrayList<StackRankers>();
    	String optionString, finQuarter, euOption;
		optionString = (String)jcbxXxmOption.getSelectedItem();
		finQuarter = (String)jcbxFinQuarter.getSelectedItem();
			if (finQuarter.equals("YTD")) {
				finQuarter = "YT";
			}
		System.out.println(finQuarter);
		euOption = (String)jcbxEU.getSelectedItem();

	    try {
			resultsSalesList =  salesListQueries.getAll(optionString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    int tempCount = resultsSalesList.size();
		    if (tempCount > 1) {
		    	salesAgentName = new String[tempCount];
		    	salesLevel6 = new String[tempCount];
		    	salesAgentType = new String[tempCount];
		    	executionUnit = new String[tempCount];
		    	region = new String[tempCount];
		    	userName = new String[tempCount];
		    	points = new double[tempCount];
		    
			    for (int i = 0; i < tempCount; i++) {
			    	currentSalesListEntry = resultsSalesList.get(i);
			    	salesAgentName[i] = currentSalesListEntry.getSalesAgentName();
			    	salesLevel6[i] = currentSalesListEntry.getSalesLevel6();
			    	salesAgentType[i] = currentSalesListEntry.getSalesAgentType();
			    	executionUnit[i] = currentSalesListEntry.getExecutionUnit();
			    	region[i] = currentSalesListEntry.getRegion();
			    	userName[i] = currentSalesListEntry.getUserName();
			    }
			}	
		    
			top.clear();
		    for (int i = 0; i < tempCount; i++) {
				String nodeString, nameString, eu, regionString;
				double indexScore = 0D;
				GeneralConstants.ScorecardWaits waits = null;
				ScorecardXxmValues valueSet;
				ScorecardGoalActualValues dataGoalActual;
				ScorecardGrowthValues dataGrowth;
				ScorecardDiscountValues dataDiscount;
				ScorecardATPenetrationValues dataATPenetration;
				DashboardXxmOtherTableValues dataOtherTable = null;
				ScorecardSCPValues scpTable;
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

				
				nodeString = salesLevel6[i]; 			    	
				nameString = salesAgentName[i]; 			    	
				regionString = region[i]; 			    	
				eu = executionUnit[i];

				valueSet = BusinessRulesXxmScoreCard.
						getXxmScorecardMetricsRanking(optionString, 
								finQuarter, eu, regionString, nodeString, nameString);
				
				
				
				dataGoalActual = valueSet.getGoalActualValues();
				dataGrowth = valueSet.getGrowthValues();
				dataDiscount = valueSet.getDiscountValues();
				dataATPenetration = valueSet.getATPenValues();
				scpTable = valueSet.getSCPTableValues();
/*				dataOtherTable = valueSet.getOtherTableValues();*/
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
					box4calcMethod[0] = GeneralConstants.CompareMethod.ACHIEVEMENT;
					rBoundaryBox4 = new RatioBoundaries[1];
					rBoundaryBox4[0] = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
//					rBoundaryBox4[0] = new RatioBoundaries(0D, box4Thresholds[0]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
					rBound4 = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
//					rBound4 = new RatioBoundaries(0D, box4Thresholds[0]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
					box4 = BusinessRulesXxmScoreCard.getMetricPanel("AT Attach", 
							box3.getFrame().getX()+box3.getFrame().getWidth()+widthInre, 50, frameWidth, 75+(box4Metrics.length*heightIncre), 
							redMark, yellowMark, greenMark, "Metrics", 
							box4Metrics, box4Values, box4Thresholds, rBoundaryBox4, box4formatMethod, 
							box4calcMethod, rBound4);
					earnedPoints += box4.getMetricPoint();
					totalPoints += box4Metrics.length;
					indexScore += box4.getMetricPoint()*waits.AT_ATTACH_WAIT.getValue();

					
/*					// SCP Performance
					String[] box5Metrics = new String[4];
					box5Metrics[0] = "A/c. Penetration";
					box5Metrics[1] = "Yield/Acs";
					box5Metrics[2] = "Tech. Penetration";
					box5Metrics[3] = "Discount";
					double[] box5Values = new double[4];
					if (finQuarter.equals("Q1")) {
						box5Values[0] = dataOtherTable.getCusPenQ1();
						box5Values[1] = CalcHelper.getYield(dataOtherTable.getRevenueAllQ1(), dataOtherTable.getCusPenQ1());
						box5Values[2] = CalcHelper.getTechnologyPenetration(dataOtherTable.getTechCountQ1(), dataOtherTable.getCusPenQ1());
						box5Values[3] = CalcHelper.getDiscount(dataOtherTable.getRevenueAllQ1(), dataOtherTable.getBaseListAllQ1());
					} else if (finQuarter.equals("Q2")) {
						box5Values[0] = dataOtherTable.getCusPenQ2();
						box5Values[1] = CalcHelper.getYield(dataOtherTable.getRevenueAllQ2(), dataOtherTable.getCusPenQ2());
						box5Values[2] = CalcHelper.getTechnologyPenetration(dataOtherTable.getTechCountQ2(), dataOtherTable.getCusPenQ2());
						box5Values[3] = CalcHelper.getDiscount(dataOtherTable.getRevenueAllQ2(), dataOtherTable.getBaseListAllQ2());
					}
					 else if (finQuarter.equals("H1")) {
						box5Values[0] = dataOtherTable.getCusPenH1();
						box5Values[1] = CalcHelper.getYield(dataOtherTable.getRevenueAllH1(), dataOtherTable.getCusPenH1());
						box5Values[2] = CalcHelper.getTechnologyPenetration(dataOtherTable.getTechCountH1(), dataOtherTable.getCusPenH1());
						box5Values[3] = CalcHelper.getDiscount(dataOtherTable.getRevenueAllH1(), dataOtherTable.getBaseListAllH1());
					}
					 else if (finQuarter.equals("Q3")) {
						box5Values[0] = dataOtherTable.getCusPenQ3();
						box5Values[1] = CalcHelper.getYield(dataOtherTable.getRevenueAllQ3(), dataOtherTable.getCusPenQ3());
						box5Values[2] = CalcHelper.getTechnologyPenetration(dataOtherTable.getTechCountQ3(), dataOtherTable.getCusPenQ3());
						box5Values[3] = CalcHelper.getDiscount(dataOtherTable.getRevenueAllQ3(), dataOtherTable.getBaseListAllQ3());
					}
					 else if (finQuarter.equals("Q4")) {
						box5Values[0] = dataOtherTable.getCusPenQ4();
						box5Values[1] = CalcHelper.getYield(dataOtherTable.getRevenueAllQ4(), dataOtherTable.getCusPenQ4());
						box5Values[2] = CalcHelper.getTechnologyPenetration(dataOtherTable.getTechCountQ4(), dataOtherTable.getCusPenQ4());
						box5Values[3] = CalcHelper.getDiscount(dataOtherTable.getRevenueAllQ4(), dataOtherTable.getBaseListAllQ4());
					}
					 else if (finQuarter.equals("H2")) {
						box5Values[0] = dataOtherTable.getCusPenH2();
						box5Values[1] = CalcHelper.getYield(dataOtherTable.getRevenueAllH2(), dataOtherTable.getCusPenH2());
						box5Values[2] = CalcHelper.getTechnologyPenetration(dataOtherTable.getTechCountH2(), dataOtherTable.getCusPenH2());
						box5Values[3] = CalcHelper.getDiscount(dataOtherTable.getRevenueAllH2(), dataOtherTable.getBaseListAllH2());
					}
					 else if (finQuarter.equals("YTD")) {
						box5Values[0] = dataOtherTable.getCusPenYTD();
						box5Values[1] = CalcHelper.getYield(dataOtherTable.getRevenueAllYTD(), dataOtherTable.getCusPenYTD());
						box5Values[2] = CalcHelper.getTechnologyPenetration(dataOtherTable.getTechCountYTD(), dataOtherTable.getCusPenYTD());
						box5Values[3] = CalcHelper.getDiscount(dataOtherTable.getRevenueAllYTD(), dataOtherTable.getBaseListAllYTD());
					}
					double[] box5Thresholds = new double[4];
					box5Thresholds[0] = dataOtherTable.getTotCustomers()*0.25D;
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
//					rBoundaryBox5[3] = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
					rBoundaryBox5[3] = new RatioBoundaries(0D, box5Thresholds[3]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
					rBound5 = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
					box5 = BusinessRulesXxmScoreCard.getMetricPanel("SCP Performance", 
							box1.getFrame().getX(), box3.getLegend().getY()+box3.getLegend().getHeight()+rowIncre, frameWidth, 75+(box5Metrics.length*heightIncre), 
							redMark, yellowMark, greenMark, "Metrics.", 
							box5Metrics, box5Values, box5Thresholds, rBoundaryBox5, box5formatMethod, 
							box5calcMethod, rBound5);
					earnedPoints += box5.getMetricPoint();
					totalPoints += box5Metrics.length;
					indexScore += box5.getMetricPoint()*waits.SCP_WAIT.getValue();
*/
					
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
//					rBoundaryBox5[3] = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
					rBoundaryBox5[3] = new RatioBoundaries(0D, box5Thresholds[3]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
					rBound5 = new RatioBoundaries(0.9D, 1.0D, compare.RED_WHEN_LESS);
					box5 = BusinessRulesXxmScoreCard.getMetricPanel("SCP Performance", 
							box1.getFrame().getX(), box3.getLegend().getY()+box3.getLegend().getHeight()+rowIncre, frameWidth, 75+(box5Metrics.length*heightIncre), 
							redMark, yellowMark, greenMark, "Metrics.", 
							box5Metrics, box5Values, box5Thresholds, rBoundaryBox5, box5formatMethod, 
							box5calcMethod, rBound5);
					earnedPoints += box5.getMetricPoint();
					totalPoints += box5Metrics.length;
					indexScore += box5.getMetricPoint()*waits.SCP_WAIT.getValue();


					// Leads
					String[] box6Metrics = new String[3];
					box6Metrics[0] = "Leads Waiting";
					box6Metrics[1] = "Accptd, Not Convrtd";
					box6Metrics[2] = "Converted";
					double[] box6Values = new double[3];
//					box6Values[0] = CalcHelper.getRatio(dataLeads.getLeadsWaitingActual(), dataLeads.getLeadsOverallActual());
//					box6Values[1] = CalcHelper.getRatio(dataLeads.getLeadsAcceptedActual(), dataLeads.getLeadsOverallActual());
//					box6Values[2] = CalcHelper.getRatio(dataLeads.getLeadsConvertedActual(), dataLeads.getLeadsOverallActual());
					box6Values[0] = dataLeads.getLeadsWaitingActual();
					box6Values[1] = dataLeads.getLeadsAcceptedActual();
					box6Values[2] = dataLeads.getLeadsConvertedActual();
					double[] box6Thresholds = new double[3];
//					box6Thresholds[0] = CalcHelper.getRatio(dataLeads.getLeadsWaitingThreshold(), dataLeads.getLeadsOverallThreshold());
//					box6Thresholds[1] = CalcHelper.getRatio(dataLeads.getLeadsAcceptedThreshold(), dataLeads.getLeadsOverallThreshold());
//					box6Thresholds[2] = CalcHelper.getRatio(dataLeads.getLeadsConvertedThreshold(), dataLeads.getLeadsOverallThreshold());
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
//					rBoundaryBox6[0] = new RatioBoundaries(0.3D, 0.5D, compare.GREEN_WHEN_LESS);
					rBoundaryBox6[0] = new RatioBoundaries(0D, box6Thresholds[0]*0.05D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
//					rBoundaryBox6[1] = new RatioBoundaries(0.3D, 0.5D, compare.GREEN_WHEN_LESS);
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
//					rBound7 = new RatioBoundaries(0.05D, 0.1D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
					rBound7 = new RatioBoundaries(0.9D, 1.0D, compare.GREEN_WHEN_LESS_THAN_EQUAL);
//					rBound7 = new RatioBoundaries(0D, box7Thresholds[0]*0.05D, compare.GREEN_WHEN_LESS);
					box7 = BusinessRulesXxmScoreCard.getMetricPanel("De-Booking", 
							box6.getFrame().getX()+box6.getFrame().getWidth()+widthInre, 
							box3.getLegend().getY()+box3.getLegend().getHeight()+rowIncre, 
							frameWidth, 75+(box7Metrics.length*heightIncre), 
							redMark, yellowMark, greenMark, "Metrics", 
							box7Metrics, box7Values, box7Thresholds, rBoundaryBox7, box7formatMethod, 
							box7calcMethod, rBound7);
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
					earnedPoints += box10.getMetricPoint();
					totalPoints += box10Metrics.length;
					indexScore += box10.getMetricPoint()*waits.PARTNER_PENETRATION_WAIT.getValue();
					points[i] = indexScore;
					if (!euOption.equals("ALL")) {
						if (euOption.equals(executionUnit[i])) {
			    			top.add(new StackRankers(points[i], salesAgentName[i], salesLevel6[i], 
			    					executionUnit[i], salesAgentType[i]));
						}
					} else {
		    			top.add(new StackRankers(points[i], salesAgentName[i], salesLevel6[i], 
		    					executionUnit[i], salesAgentType[i]));
					}
		    }

		Collections.sort(top, new StackRankers());

		dynamicHeader= (euOption.equals("ALL")) ? "Country" : euOption;
		setEUView(top);
	}
	
    private class ShowButtonListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
			try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    		if (e.getSource().equals(jbShow)) {
	    	        dStart = (rankingSlider.getValue()*20)-20;
	    	        dEnd = dStart+19;
    				initiateStackRanking();
	    			}
			}
			catch (Exception exception) {
				exception.printStackTrace();
			} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
    	}
    }
    
	
}
