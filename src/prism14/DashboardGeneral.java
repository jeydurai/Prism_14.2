package prism14;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public class DashboardGeneral extends JInternalFrame{

	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
	private JScrollPane rootScrollPane, mainScrollPane;
	private GradientPanel mainPanel, compoPanel;
	private GradientPanel2 sidePanel, bannerPanel, techSplitHolder, archSplitHolder, 
	quarterSplitHolder, halfYearSplitHolder, month_weekSplitHolder, topPartnerHolder, topTBMHolder, topCustomerHolder;
	private PicturedPanel currentYearPanel, jpTechnologyImage, jpArchitectureImage, 
	jpCalendarImage, jpDiscountImage, compareYearPanel;
	private JPanel subSCMSPanel;
	private ImageIcon imageIcon, imgLogo, trueNorthImage, screenTitle, sidePanelImage1, 
	sidePanelImage2, sidePanelImage3, sidePanelImageName1, sidePanelImageName2, 
	sidePanelImageName3;
	private BufferedImage panelImage, technologyImage, architectureImage, 
	calendarImage, discountImage;
	private JLabel jlLogo, jlDateTime, jlTrueNorthImage, jlScreenTitle, jlPanelImage,
	jlSidePanelImage1, jlSidePanelImage2, jlSidePanelImage3, jlSidePanelImageName1, 
	jlSidePanelImageName2,	jlSidePanelImageName3;
	private JTextArea jtaSalesAgentInfo, jtaPartnerInfo, jtaCustomerInfo,
	jtaTechnologyInfo, jtaArchitectureInfo, jtaCalendarInfo, jtaDiscountInfo;
	private JSeparator sep1;
    private DateFormat dateFormat;
    private Date currentDate;
    protected int fontSize;
    protected String fontSpecialFamily, fontFamily, optionTrigger, finQuarter, finMonth, finYear;
    protected DefaultValueDataset dataset1, dataset2;
    private double[][] techSplitValues, archSplitValues, quarterSplitValues, halfYearSplitValues, 
    weekSplitValues, monthSplitValues, topTBMValues, topPartnerValues, topCustomerValues;
    private JPanel switchPanel;
    private boolean isYTD, isH1, isH2, isQ1, isQ2, isQ3, isQ4, isAnalysis, 
    isAbs, isFirstAttemptFinished, isTopTBMAttempted, isTBMToggle1, isTBMToggle2, isTBMToggle3,
    isTBMToggle4, isTBMToggle5, isTopPartnerAttempted, isTopCustomerAttempted, 
    isPartnerToggle1, isPartnerToggle2, isPartnerToggle3, isPartnerToggle4, isPartnerToggle5,
    isCustomerToggle1, isCustomerToggle2, isCustomerToggle3, isCustomerToggle4, isCustomerToggle5, 
    isCompareYearAttempted, isSubPeriodPlotOn, isYokePanelOn;
    private JButton jbYTD, jbH1, jbH2, jbQ1, jbQ2, jbQ3, jbQ4, 
    tbmToggle1, tbmToggle2, tbmToggle3, tbmToggle4, tbmToggle5,
    partnerToggle1, partnerToggle2, partnerToggle3, partnerToggle4, partnerToggle5, 
    customerToggle1, customerToggle2, customerToggle3, customerToggle4, 
    customerToggle5, jbSalesAgentReadMore, 
    jbPartnerReadMore,	jbCustomerReadMore;; 
    private RoundButton jbLeft, jbRight, jbShowSalesAgent, jbOverride;
    private JToggleButton jbAnalysis, jbAbs;
    private JLabel jlYTD, jlH1, jlH2, jlQ1, jlQ2, jlQ3, jlQ4, jlAnalysis;
    private JLabel jlTitleYTD, jlTitleH1, jlTitleH2, jlTitleQ1, jlTitleQ2, 
    jlTitleQ3, jlTitleQ4, jlTitleAnalysis;
    private JLabel periodPos1, periodPos2, periodPos3, periodPos4;
    private int leftPoint, rightPoint;

    private JLabel jlOption, jlEU, jlRegion, jlNode, jlYear, jlName, jlAnalysisInput;
    private JComboBox jcbxOption, jcbxEU, jcbxYear, jcbxAnalysis, jcbxYearCompare;
    private JList jltRegion, jltNode, jltName; 
    private JScrollPane regionScrollPane, nodeScrollPane, ScrollPane, NameScrollPane;
    private JRadioButton jrbPL, jrbPLV, jrbAll;
    private ButtonGroup bg;
    private DefaultListModel dummyModel;
    private ArrayList<String> tempArrayList;
    private DataBaseUserDataQueries userDataQueries;
    private DataBaseUserData currentUserDataEntry;
    private List<DataBaseUserData> resultsUserData;
    private DataBaseUniqueDataQueries uniqueDataQueries;
    private DataBaseUniqueData currentUniqueDataEntry;
    private List<DataBaseUniqueData> resultsUniqueData;
    private DefaultComboBoxModel eUNames, fYears;
    private DefaultListModel regionFillModel, node;
    private String[] option;
    private List<DashboardGeneralValues> valueSet = null;
    private DashboardGeneralValues valueEntry;
    private DashboardGeneralWhatIf whatIfEntry;
    private List<TopNames> valueTop = null;
    private TopNames topEntry;
	private ChartPanel centerChartPanel, disAllChartPanel, disCollChartPanel, 
	disDCVChartPanel, disENTNWChartPanel, disSecChartPanel, penCusChartPanel, 
	techPenChartPanel, atAttachChartPanel, yokePanel;
	private JPanel techPenHolder, atAttachHolder;
	private JSlider jsDisAll, jsDisENTNW, jsDisSec, jsDisColl, jsDisDCV, 
	jsCusPen, jsTechPen;
	private JLabel jlDisAll, jlDisENTNW, jlDisSec, jlDisColl, jlDisDCV, 
	jlCusPen, jlTechPen;
	private String topTBM, topPartner, topCustomer, nString;
	private DashboardGeneralFieldHelper field;
	private double needleWhereTo;
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
    }

	
	public DashboardGeneral() {
		super("General Dashboard #" + (++openFrameCount),
				true, true, true, true);
		setSize(700,500);
		setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
		trueNorthImage = new ImageIcon(this.getClass().getResource(GeneralConstants.TRUE_NORTH_IMAGE2));
    	imageIcon = new ImageIcon(this.getClass().getResource(GeneralConstants.INTERNAL_FRAME_LOGO_IMAGE));
		screenTitle = new ImageIcon(this.getClass().getResource(GeneralConstants.GENERAL_DASHBOARD_TITLE));
		sidePanelImage1 = new ImageIcon(this.getClass().getResource(GeneralConstants.SALES_PERSON_IMAGE));
		sidePanelImage2 = new ImageIcon(this.getClass().getResource(GeneralConstants.PARTNER_IMAGE));
		sidePanelImage3 = new ImageIcon(this.getClass().getResource(GeneralConstants.CUSTOMER_IMAGE));
		sidePanelImageName1 = new ImageIcon(this.getClass().getResource(GeneralConstants.SALES_PERSON_NAME_IMAGE));
		sidePanelImageName2 = new ImageIcon(this.getClass().getResource(GeneralConstants.PARTNER_NAME_IMAGE));
		sidePanelImageName3 = new ImageIcon(this.getClass().getResource(GeneralConstants.CUSTOMER_NAME_IMAGE));
		try {
	        panelImage =  ImageIO.read(this.getClass().getResource(GeneralConstants.SKY_VIEW_IMAGE));
			technologyImage =  ImageIO.read(this.getClass().getResource(GeneralConstants.TECHNOLOGY_IMAGE));
			architectureImage =   ImageIO.read(this.getClass().getResource(GeneralConstants.ARCHITECTURE_IMAGE));
			calendarImage =   ImageIO.read(this.getClass().getResource(GeneralConstants.CALENDAR_IMAGE));
			discountImage =   ImageIO.read(this.getClass().getResource(GeneralConstants.DISCOUNT_IMAGE));
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
        fontSize = 11;
        fontFamily = "Arial";
        fontSpecialFamily = "Verdana";
    	imageIcon = new ImageIcon(this.getClass().getResource(GeneralConstants.INTERNAL_FRAME_LOGO_IMAGE));
    	this.setFrameIcon(imageIcon);
    	option = new String[3];
    	option[0] = "Partner";
    	option[1] = "Customer";
    	option[2] = "TBM/VBM";
        try {
	    	userDataQueries = new DataBaseUserDataQueries();
	    	uniqueDataQueries = new DataBaseUniqueDataQueries();
	        }
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException iException){
			iException.printStackTrace();
		}
        
    	setPanelsReady();
    	setGraphsPlotted();

	}
	
	@SuppressWarnings("serial")
	protected void setPanelsReady() {
        imgLogo = new ImageIcon(this.getClass().getResource(GeneralConstants.CISCO_LOGO));

        jlLogo = new JLabel(imgLogo);
        jlLogo.setBounds(0, 0, 100, 60);

        Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
        rootScrollPane = new JScrollPane();
        rootScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        rootScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rootScrollPane.setPreferredSize(di);
        setContentPane(rootScrollPane);

		mainPanel = new GradientPanel();
        mainPanel.setBackground(Color.BLUE);
        mainPanel.setLayout(null);
        rootScrollPane.setViewportView(mainPanel);
        
        bannerPanel = new GradientPanel2();
		bannerPanel.setBackground(new Color(255,255,255));
        bannerPanel.setBounds(0, 0, 1355, 95);
        bannerPanel.setLayout(null);
		bannerPanel.add(jlLogo);
        mainPanel.add(bannerPanel);

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

		jlScreenTitle = new JLabel(screenTitle);
		jlScreenTitle.setBounds(400, 15, 600, 50);
		jlScreenTitle.setOpaque(false);
		bannerPanel.add(jlScreenTitle);

		sep1 = new JSeparator();
        sep1.setBounds(0, 95, 1360, 2);
        mainPanel.add(sep1);

        mainScrollPane = new JScrollPane();
        mainScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setBounds(0, 165, 1355, 485);
        mainPanel.add(mainScrollPane);

        compoPanel = new GradientPanel();
        compoPanel.setBackground(Color.BLUE);
//        compoPanel.setPreferredSize(new Dimension(1200,810));
        compoPanel.setPreferredSize(new Dimension(1200,1050));
        compoPanel.setFont(new Font(fontFamily,Font.BOLD,fontSize));
        mainScrollPane.setViewportView(compoPanel);

        sidePanel = new GradientPanel2();
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setLayout(null);
        sidePanel.setBounds(5, 5, 365, 520);
        sidePanel.setFont(new Font(fontFamily,Font.BOLD,fontSize));
        compoPanel.add(sidePanel);

        
        jlSidePanelImage1 = new JLabel(sidePanelImage1);
        jlSidePanelImage1.setBounds(0, 0, 150, 150);
        jlSidePanelImage1.setOpaque(false);
        
        jlSidePanelImageName1 = new JLabel(sidePanelImageName1);
        jlSidePanelImageName1.setBounds(0, 120, 150, 50);
        jlSidePanelImageName1.setOpaque(false);
        
		jtaSalesAgentInfo = new JTextArea() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jtaSalesAgentInfo.setBackground(null);
		jtaSalesAgentInfo.setForeground(Color.DARK_GRAY);
		jtaSalesAgentInfo.setFont(new Font("Arial",Font.PLAIN,14));
		jtaSalesAgentInfo.setBounds(155, 50, 160, 80);
		jtaSalesAgentInfo.setText("Basic Metrics of TBM/VBM; Top Partners; "
				+ "Top Customers, etc. and 'What if' Analysis...");
		jtaSalesAgentInfo.setLineWrap(true);
		jtaSalesAgentInfo.setWrapStyleWord(true);
		jtaSalesAgentInfo.setEditable(false);

        
		jbSalesAgentReadMore = new JButton("Learn More") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jbSalesAgentReadMore.setForeground(new Color(80,150,255));
		jbSalesAgentReadMore.setBackground(null);
		jbSalesAgentReadMore.setFont(new Font("Arial",Font.PLAIN,13));
		jbSalesAgentReadMore.setBorder(null);
		jbSalesAgentReadMore.setFocusable(false);
		jbSalesAgentReadMore.setHorizontalAlignment(SwingConstants.LEFT);
		jbSalesAgentReadMore.setBounds(155, 130, 100, 35);

        jlSidePanelImage2 = new JLabel(sidePanelImage2);
        jlSidePanelImage2.setBounds(205, 150, 150, 150);
        jlSidePanelImage2.setOpaque(false);

        jlSidePanelImageName2 = new JLabel(sidePanelImageName2);
        jlSidePanelImageName2.setBounds(205, 280, 150, 50);
        jlSidePanelImageName2.setOpaque(false);

        
		jtaPartnerInfo = new JTextArea() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jtaPartnerInfo.setBackground(null);
		jtaPartnerInfo.setForeground(Color.DARK_GRAY);
		jtaPartnerInfo.setFont(new Font("Arial",Font.PLAIN,14));
		jtaPartnerInfo.setBounds(50, 200, 160, 80);
		jtaPartnerInfo.setText("Basic Metrics of Partners; "
				+ "Top Customers, etc. and 'What if' Analysis...");
		jtaPartnerInfo.setLineWrap(true);
		jtaPartnerInfo.setWrapStyleWord(true);
		jtaPartnerInfo.setEditable(false);
        
        
		jbPartnerReadMore = new JButton("Learn More") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jbPartnerReadMore.setForeground(new Color(80,150,255));
		jbPartnerReadMore.setBackground(null);
		jbPartnerReadMore.setFont(new Font("Arial",Font.PLAIN,13));
		jbPartnerReadMore.setBorder(null);
		jbPartnerReadMore.setFocusable(false);
		jbPartnerReadMore.setHorizontalAlignment(SwingConstants.LEFT);
		jbPartnerReadMore.setBounds(50, 280, 100, 35);
        
        
        jlSidePanelImage3 = new JLabel(sidePanelImage3);
        jlSidePanelImage3.setBounds(0, 300, 150, 150);
        jlSidePanelImage3.setOpaque(false);
        

		jtaCustomerInfo = new JTextArea() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jtaCustomerInfo.setBackground(null);
		jtaCustomerInfo.setForeground(Color.DARK_GRAY);
		jtaCustomerInfo.setFont(new Font("Arial",Font.PLAIN,14));
		jtaCustomerInfo.setBounds(155, 350, 160, 80);
		jtaCustomerInfo.setText("Basic Metrics of Customers; "
				+ " and 'What if' Analysis...");
		jtaCustomerInfo.setLineWrap(true);
		jtaCustomerInfo.setWrapStyleWord(true);
		jtaCustomerInfo.setEditable(false);
        
        
		jbCustomerReadMore = new JButton("Learn More") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jbCustomerReadMore.setForeground(new Color(80,150,255));
		jbCustomerReadMore.setBackground(null);
		jbCustomerReadMore.setFont(new Font("Arial",Font.PLAIN,13));
		jbCustomerReadMore.setBorder(null);
		jbCustomerReadMore.setFocusable(false);
		jbCustomerReadMore.setHorizontalAlignment(SwingConstants.LEFT);
		jbCustomerReadMore.setBounds(155, 430, 100, 35);

		
		sidePanel.add(jlSidePanelImage1);
        sidePanel.add(jlSidePanelImageName1);
        sidePanel.add(jtaSalesAgentInfo);
        sidePanel.add(jbSalesAgentReadMore);
        sidePanel.add(jlSidePanelImage2);
        sidePanel.add(jlSidePanelImageName2);
        sidePanel.add(jtaPartnerInfo);
        sidePanel.add(jbPartnerReadMore);
        sidePanel.add(jlSidePanelImage3);
        sidePanel.add(jtaCustomerInfo);
        sidePanel.add(jbCustomerReadMore);
        
        
        currentYearPanel = new PicturedPanel(panelImage);
        currentYearPanel.setBackground(new Color(75, 75, 250));
        currentYearPanel.setBounds(375, 5, 955, 480);
        currentYearPanel.setBounds(375, 5, 955, 520);
        currentYearPanel.setFont(new Font(fontFamily,Font.BOLD,fontSize));
        currentYearPanel.setLayout(null);
        compoPanel.add(currentYearPanel);

        jpTechnologyImage = new PicturedPanel(technologyImage);
        jpTechnologyImage.setBounds(5, 5, 300, 180);
        jpTechnologyImage.setOpaque(false);
        
		jtaTechnologyInfo = new JTextArea() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jtaTechnologyInfo.setBackground(null);
		jtaTechnologyInfo.setOpaque(false);
		jtaTechnologyInfo.setForeground(Color.WHITE);
		jtaTechnologyInfo.setFont(new Font("Verdana",Font.BOLD,16));
		jtaTechnologyInfo.setBounds(310, 5, 300, 120);
		jtaTechnologyInfo.setText("Application Networking Services, DC Switching, "
				+ "Routing, LAN Switching, Security, Storage, UC, UCS, Video, "
				+ "Wireless LAN and Others ...");
		jtaTechnologyInfo.setLineWrap(true);
		jtaTechnologyInfo.setWrapStyleWord(true);
		jtaTechnologyInfo.setEditable(false);
        
		
        jpCalendarImage = new PicturedPanel(calendarImage);
        jpCalendarImage.setBounds(650, 5, 300, 180);
        jpCalendarImage.setOpaque(false);

		jtaCalendarInfo = new JTextArea() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jtaCalendarInfo.setBackground(null);
		jtaCalendarInfo.setOpaque(false);
		jtaCalendarInfo.setForeground(Color.WHITE);
		jtaCalendarInfo.setFont(new Font("Verdana",Font.BOLD,16));
		jtaCalendarInfo.setBounds(350, 140, 300, 120);
		jtaCalendarInfo.setText("For Each Metrics, information can be drilled down by "
				+ "by Year, Half Year, Quarter and Month ...");
		jtaCalendarInfo.setLineWrap(true);
		jtaCalendarInfo.setAlignmentX(RIGHT_ALIGNMENT);
		jtaCalendarInfo.setWrapStyleWord(true);
		jtaCalendarInfo.setEditable(false);
        
        
        jpArchitectureImage = new PicturedPanel(architectureImage);
        jpArchitectureImage.setBounds(5, 310, 170, 140);
        jpArchitectureImage.setOpaque(false);

		jtaArchitectureInfo = new JTextArea() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jtaArchitectureInfo.setBackground(null);
		jtaArchitectureInfo.setOpaque(false);
		jtaArchitectureInfo.setForeground(Color.WHITE);
		jtaArchitectureInfo.setFont(new Font("Verdana",Font.BOLD,16));
		jtaArchitectureInfo.setBounds(180, 280, 300, 120);
		jtaArchitectureInfo.setText("ENT. Net Workings, Security, "
				+ "Collaboration and Data Centre & Virtualization ...");
		jtaArchitectureInfo.setLineWrap(true);
		jtaArchitectureInfo.setAlignmentX(RIGHT_ALIGNMENT);
		jtaArchitectureInfo.setWrapStyleWord(true);
		jtaArchitectureInfo.setEditable(false);

        
        jpDiscountImage = new PicturedPanel(discountImage);
        jpDiscountImage.setBounds(780, 310, 170, 140);
        jpDiscountImage.setOpaque(false);


		jtaDiscountInfo = new JTextArea() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jtaDiscountInfo.setBackground(null);
		jtaDiscountInfo.setOpaque(false);
		jtaDiscountInfo.setForeground(Color.WHITE);
		jtaDiscountInfo.setFont(new Font("Verdana",Font.BOLD,16));
		jtaDiscountInfo.setBounds(540, 230, 300, 120);
		jtaDiscountInfo.setText("Architecture wise Discount Offered, "
				+ "AT Attach Rate, Customer Penetration, Technology Penetration ...");
		jtaDiscountInfo.setLineWrap(true);
		jtaDiscountInfo.setAlignmentX(RIGHT_ALIGNMENT);
		jtaDiscountInfo.setWrapStyleWord(true);
		jtaDiscountInfo.setEditable(false);
        
        currentYearPanel.add(jpTechnologyImage);
        currentYearPanel.add(jtaTechnologyInfo);
        currentYearPanel.add(jpCalendarImage);
        currentYearPanel.add(jtaCalendarInfo);
        currentYearPanel.add(jpArchitectureImage);
        currentYearPanel.add(jtaArchitectureInfo);
        currentYearPanel.add(jpDiscountImage);
        currentYearPanel.add(jtaDiscountInfo);

	
	}
	
	private void setGraphsPlotted() {
		setWhatIfController();
		setFlightYoke();
		setControlButtons();
		setOptionSelectors();
	}
	
	private void setCenterPlot(double d1, double d2) {
		dataset1 = new DefaultValueDataset(d1);
		dataset2 = new DefaultValueDataset(d2);
		centerChartPanel = ComponentHelper.getTotalBookingPlot(d1, d2);
		centerChartPanel.setBounds(370, 60, 200, 200);
		currentYearPanel.add(centerChartPanel);
	}
	
	private void setDiscountPlots(double disAll, double disColl, double disENTNW, double disSec,
			double disDCV, double cPen, double techPen, double atAttach, double nonATAttach) {
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
		ChartPanel  techSplitChartPanel, archSplitChartPanel, 
		quarterSplitChartPanel, month_weekSplitChartPanel, halfYearSplitChartPanel;
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
	    archSplitValues[0][0] = CalcHelper.getValueInThousandUSDFromMillion(eBook);
	    archSplitValues[0][1] = CalcHelper.getValueInThousandUSDFromMillion(sBook);
	    archSplitValues[0][2] = CalcHelper.getValueInThousandUSDFromMillion(cBook);
	    archSplitValues[0][3] = CalcHelper.getValueInThousandUSDFromMillion(dBook);
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
	
	private void setToppersSalesAgent(List<TopNames> top, int yPos) {
		ChartPanel  topTBMChartPanel;
		topTBMHolder = new GradientPanel2();
		int techSplitYPos = 34, techSplitYPosIncre = 23, xPos=10, width=170,		
		height=10, xPosOffset= 260; 
		String[] topTBMLabelArray = new String[5];
		CategoryDataset topTBMDataset;
		topTBMValues = new double[1][5];

		int size = (top.size()>5) ? 5 : top.size();
		
		topEntry = null;
		for (int i=0; i<topTBMLabelArray.length; i++) {
			if (i+1 <= size) {
		    	topEntry = top.get(i);
				topTBMLabelArray[i] = topEntry.getName(); 
				topTBMValues[0][i] = CalcHelper.getValueInThousandUSD(topEntry.getBooking());
			} else {
				topTBMLabelArray[i] = ""; 
				topTBMValues[0][i] = 0D;
			}
		}
		
	    // Top 5 topTBM Horizontal Bar Graph
	    topTBMDataset = DatasetUtilities.createCategoryDataset("", "", topTBMValues);
	    
	    topTBMChartPanel = ComponentHelper.getTopGraph(topTBMDataset);
	    topTBMChartPanel.setBounds(180, 15, 80, 140);
//	    topTBMChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    topTBMHolder.setBackground(new Color(170, 170, 220));
	    topTBMHolder.setOpaque(false);
	    topTBMHolder.setBounds(5, yPos, 355, 160);
	    topTBMHolder.setLayout(null);
	    topTBMHolder.add(topTBMChartPanel);
		ComponentHelper.placeTitle(topTBMHolder, "Top 5 TBM/VBM(s)", 125, 3, 150, 15);
		
		int i=0;
		TopNamesComponent obj = 
				ComponentHelper.getTopGraphToggle(
						topTBMLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topTBMValues[0][i]);
		tbmToggle1 = obj.getNameToggle();
		topTBMHolder.add(obj.getNameToggle());
		topTBMHolder.add(obj.getToggleLabel());

		i++;
		 obj = ComponentHelper.getTopGraphToggle(
						topTBMLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topTBMValues[0][i]);
		tbmToggle2 = obj.getNameToggle();
		topTBMHolder.add(obj.getNameToggle());
		topTBMHolder.add(obj.getToggleLabel());
		
		i++;
		 obj = ComponentHelper.getTopGraphToggle(
						topTBMLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topTBMValues[0][i]);
		tbmToggle3 = obj.getNameToggle();
		topTBMHolder.add(obj.getNameToggle());
		topTBMHolder.add(obj.getToggleLabel());
		
		i++;
		 obj = ComponentHelper.getTopGraphToggle(
						topTBMLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topTBMValues[0][i]);
		tbmToggle4 = obj.getNameToggle();
		topTBMHolder.add(obj.getNameToggle());
		topTBMHolder.add(obj.getToggleLabel());

		i++;
		 obj = ComponentHelper.getTopGraphToggle(
						topTBMLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topTBMValues[0][i]);
		tbmToggle5 = obj.getNameToggle();
		topTBMHolder.add(obj.getNameToggle());
		topTBMHolder.add(obj.getToggleLabel());
		tbmToggle1.addActionListener(new TopTBMActionListener());
		tbmToggle2.addActionListener(new TopTBMActionListener());
		tbmToggle3.addActionListener(new TopTBMActionListener());
		tbmToggle4.addActionListener(new TopTBMActionListener());
		tbmToggle5.addActionListener(new TopTBMActionListener());

		
		sidePanel.add(topTBMHolder);
	}
	
	private void setToppersPartner(List<TopNames> top, int yPos) {
		ChartPanel  topPartnerChartPanel;
		topPartnerHolder = new GradientPanel2();
		int techSplitYPos = 34, techSplitYPosIncre = 23, xPos=10, width=170,		
		height=10, xPosOffset= 260; 
		String[] topPartnerLabelArray = new String[5];
		CategoryDataset topPartnerDataset;

		topPartnerValues = new double[1][5];

		int size = (top.size()>5) ? 5 : top.size();
		topEntry = null;
		for (int i=0; i<topPartnerLabelArray.length; i++) {
			if (i+1 <= size) {
		    	topEntry = top.get(i);
		    	topPartnerLabelArray[i] = topEntry.getName(); 
		    	topPartnerValues[0][i] = CalcHelper.getValueInThousandUSD(topEntry.getBooking());
			} else {
				topPartnerLabelArray[i] = ""; 
				topPartnerValues[0][i] = 0D;
			}
		}
		

	    // Top 5 topPartner Horizontal Bar Graph
	    topPartnerDataset = DatasetUtilities.createCategoryDataset("", "", topPartnerValues);
	    
	    topPartnerChartPanel = ComponentHelper.getTopGraph(topPartnerDataset);
	    topPartnerChartPanel.setBounds(180, 15, 80, 140);
//	    topPartnerChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    topPartnerHolder.setBackground(new Color(170, 170, 220));
	    topPartnerHolder.setOpaque(false);
	    topPartnerHolder.setBounds(5, yPos, 355, 160);
	    topPartnerHolder.setLayout(null);
	    topPartnerHolder.add(topPartnerChartPanel);
		ComponentHelper.placeTitle(topPartnerHolder, "Top 5 Partners", 125, 3, 150, 15);

		int i=0;
		TopNamesComponent obj = 
				ComponentHelper.getTopGraphToggle(
						topPartnerLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topPartnerValues[0][i]);
		partnerToggle1 = obj.getNameToggle();
		topPartnerHolder.add(obj.getNameToggle());
		topPartnerHolder.add(obj.getToggleLabel());

		i++;
		 obj = ComponentHelper.getTopGraphToggle(
				 topPartnerLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topPartnerValues[0][i]);
		partnerToggle2 = obj.getNameToggle();
		topPartnerHolder.add(obj.getNameToggle());
		topPartnerHolder.add(obj.getToggleLabel());
		
		i++;
		 obj = ComponentHelper.getTopGraphToggle(
				 topPartnerLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topPartnerValues[0][i]);
		partnerToggle3 = obj.getNameToggle();
		topPartnerHolder.add(obj.getNameToggle());
		topPartnerHolder.add(obj.getToggleLabel());
		
		i++;
		 obj = ComponentHelper.getTopGraphToggle(
				 topPartnerLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topPartnerValues[0][i]);
		partnerToggle4 = obj.getNameToggle();
		topPartnerHolder.add(obj.getNameToggle());
		topPartnerHolder.add(obj.getToggleLabel());

		i++;
		 obj = ComponentHelper.getTopGraphToggle(
				 topPartnerLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topPartnerValues[0][i]);
		partnerToggle5 = obj.getNameToggle();
		topPartnerHolder.add(obj.getNameToggle());
		topPartnerHolder.add(obj.getToggleLabel());
		
		partnerToggle1.addActionListener(new TopPartnerActionListener());
		partnerToggle2.addActionListener(new TopPartnerActionListener());
		partnerToggle3.addActionListener(new TopPartnerActionListener());
		partnerToggle4.addActionListener(new TopPartnerActionListener());
		partnerToggle5.addActionListener(new TopPartnerActionListener());
	    
	    sidePanel.add(topPartnerHolder);

	}
	
	private void setToppersCustomer(List<TopNames> top, int yPos) {
		ChartPanel  topCustomerChartPanel;
		topCustomerHolder = new GradientPanel2();
		int techSplitYPos = 34, techSplitYPosIncre = 23, xPos=10, width=170,		
		height=10, xPosOffset= 260; 
		String[] topCustomerLabelArray = new String[5];
		CategoryDataset topCustomerDataset;

		topCustomerValues = new double[1][5];

		int size = (top.size()>5) ? 5 : top.size();
		topEntry = null;
		for (int i=0; i<topCustomerLabelArray.length; i++) {
			if (i+1 <= size) {
		    	topEntry = top.get(i);
		    	topCustomerLabelArray[i] = topEntry.getName(); 
		    	topCustomerValues[0][i] = CalcHelper.getValueInThousandUSD(topEntry.getBooking());
			} else {
				topCustomerLabelArray[i] = ""; 
				topCustomerValues[0][i] = 0D;
			}
		}

		topCustomerDataset = DatasetUtilities.createCategoryDataset("", "", topCustomerValues);
	    
	    topCustomerChartPanel = ComponentHelper.getTopGraph(topCustomerDataset);
	    topCustomerChartPanel.setBounds(180, 15, 80, 140);
//	    topCustomerChartPanel.addChartMouseListener(new Q1ChartMouseListener());
	    topCustomerHolder.setBackground(new Color(170, 170, 220));
	    topCustomerHolder.setOpaque(false);
	    topCustomerHolder.setBounds(5, yPos, 355, 160);
	    topCustomerHolder.setLayout(null);
	    topCustomerHolder.add(topCustomerChartPanel);
	    
		ComponentHelper.placeTitle(topCustomerHolder, "Top 5 Customers", 125, 3, 150, 15);

	    
		int i=0;
		TopNamesComponent obj = 
				ComponentHelper.getTopGraphToggle(
						topCustomerLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topCustomerValues[0][i]);
		customerToggle1 = obj.getNameToggle();
		topCustomerHolder.add(obj.getNameToggle());
		topCustomerHolder.add(obj.getToggleLabel());

		i++;
		 obj = ComponentHelper.getTopGraphToggle(
				 topCustomerLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topCustomerValues[0][i]);
		customerToggle2 = obj.getNameToggle();
		topCustomerHolder.add(obj.getNameToggle());
		topCustomerHolder.add(obj.getToggleLabel());
		
		i++;
		 obj = ComponentHelper.getTopGraphToggle(
				 topCustomerLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topCustomerValues[0][i]);
		customerToggle3 = obj.getNameToggle();
		topCustomerHolder.add(obj.getNameToggle());
		topCustomerHolder.add(obj.getToggleLabel());
		
		i++;
		 obj = ComponentHelper.getTopGraphToggle(
				 topCustomerLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topCustomerValues[0][i]);
		customerToggle4 = obj.getNameToggle();
		topCustomerHolder.add(obj.getNameToggle());
		topCustomerHolder.add(obj.getToggleLabel());

		i++;
		 obj = ComponentHelper.getTopGraphToggle(
				 topCustomerLabelArray[i], xPos, techSplitYPos+(i*techSplitYPosIncre), 
						width, height, xPosOffset, topCustomerValues[0][i]);
		customerToggle5 = obj.getNameToggle();
		topCustomerHolder.add(obj.getNameToggle());
		topCustomerHolder.add(obj.getToggleLabel());

		customerToggle1.addActionListener(new TopCustomerActionListener());
		customerToggle2.addActionListener(new TopCustomerActionListener());
		customerToggle3.addActionListener(new TopCustomerActionListener());
		customerToggle4.addActionListener(new TopCustomerActionListener());
		customerToggle5.addActionListener(new TopCustomerActionListener());
		sidePanel.add(topCustomerHolder);
	}
	
	private void setWhatIfController() {
        Font font = new Font("Arial", Font.BOLD, 9);

	    jsDisAll = ComponentHelper.placeWhatIfJSlider(-20, 20, 1, 2, 10, 
	    		225, 15, 90, 50, Color.WHITE.darker(), font);
	    jsDisAll.setValue(0);
	    jsDisAll.addChangeListener(new SliderListener());
		jsDisENTNW = ComponentHelper.placeWhatIfJSlider(-20, 20, 1, 2, 10, 
				320, 15, 90, 50, Color.WHITE.darker(), font);
		jsDisENTNW.setValue(0);
		jsDisENTNW.addChangeListener(new SliderListener());
		jsDisSec = ComponentHelper.placeWhatIfJSlider(-20, 20, 1, 2, 10, 
				415, 15, 90, 50, Color.WHITE.darker(), font);
		jsDisSec.setValue(0);
		jsDisSec.addChangeListener(new SliderListener());
		jsDisColl = ComponentHelper.placeWhatIfJSlider(-20, 20, 1, 2, 10, 
				510, 15, 90, 50, Color.WHITE.darker(), font);
		jsDisColl.setValue(0);
		jsDisColl.addChangeListener(new SliderListener());
		jsDisDCV = ComponentHelper.placeWhatIfJSlider(-20, 20, 1, 2, 10, 
				605, 15, 90, 50, Color.WHITE.darker(), font);
		jsDisDCV.setValue(0);
		jsDisDCV.addChangeListener(new SliderListener());
		jsCusPen = ComponentHelper.placeWhatIfJSlider(-20, 20, 1, 2, 10, 
				225, 330, 120, 50, Color.BLACK, font);
		jsCusPen.setValue(0);
		jsCusPen.addChangeListener(new SliderListener());
		jsTechPen = ComponentHelper.placeWhatIfJSlider(-20, 20, 1, 2, 10, 
				600, 330, 120, 50, Color.BLACK, font);
		jsTechPen.setValue(0);
		jsTechPen.addChangeListener(new SliderListener());

        
        
        jlDisAll = ComponentHelper.placeWhatIfLabel(205, 15, 120, 50, 
        		"Overall Discount", Color.WHITE.darker(), font);
        jlDisENTNW = ComponentHelper.placeWhatIfLabel(300, 15, 120, 50, 
        		"ENT NW Discount", Color.WHITE.darker(), font);
        jlDisSec = ComponentHelper.placeWhatIfLabel(395, 15, 120, 50, 
        		"Security Discount", Color.WHITE.darker(), font);
        jlDisColl = ComponentHelper.placeWhatIfLabel(490, 15, 120, 50, 
        		"Coll Discount", Color.WHITE.darker(), font);
        jlDisDCV = ComponentHelper.placeWhatIfLabel(585, 15, 120, 50, 
        		"DCV Discount", Color.WHITE.darker(), font);
        jlCusPen = ComponentHelper.placeWhatIfLabel(225, 330, 120, 50, 
        		"Cus. Penetration", Color.BLACK, font);
        jlTechPen = ComponentHelper.placeWhatIfLabel(600, 330, 120, 50, 
        		"Tech. Penetration", Color.BLACK, font);
	}
	
	private class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			if (!source.getValueIsAdjusting()) {
				double disAllValue = (double)jsDisAll.getValue();
				double disENTNWValue = (double)jsDisENTNW.getValue();
				double disSecValue = (double)jsDisSec.getValue();
				double disCollValue = (double)jsDisColl.getValue();
				double disDCVValue = (double)jsDisDCV.getValue();
				double disCusPenValue = (double)jsCusPen.getValue();
				double disTechPenValue = (double)jsTechPen.getValue();
				WhatIfDataTransfer whatIfData = new WhatIfDataTransfer(disAllValue/100D, disENTNWValue/100D,
						disSecValue/100D, disCollValue/100D, disDCVValue/100D, disCusPenValue/10D,
						disTechPenValue/10D);
				
				if (source.equals(jsDisAll)) {
			    	whatIfEntry = new DashboardGeneralWhatIf(valueEntry, "Discount Overall",
			    			whatIfData);
				} else {
			    	whatIfEntry = new DashboardGeneralWhatIf(valueEntry, "Others",
			    			whatIfData);
				}
				currentYearPanel.remove(centerChartPanel);
			    currentYearPanel.remove(disAllChartPanel);
			    currentYearPanel.remove(disENTNWChartPanel);
			    currentYearPanel.remove(disSecChartPanel);
			    currentYearPanel.remove(disCollChartPanel);
			    currentYearPanel.remove(disDCVChartPanel);
			    currentYearPanel.remove(penCusChartPanel);
			    currentYearPanel.remove(techPenHolder);
			    currentYearPanel.remove(atAttachHolder);
			    currentYearPanel.remove(techSplitHolder);
			    currentYearPanel.remove(archSplitHolder);
			    currentYearPanel.remove(month_weekSplitHolder);
			    currentYearPanel.remove(quarterSplitHolder);
			    currentYearPanel.remove(halfYearSplitHolder);
			    if (isCompareYearAttempted) {
			    	compoPanel.remove(compareYearPanel);
			    	isCompareYearAttempted = false;
			    }
		    	setCenterPlot(whatIfEntry.getBookingAll(), whatIfEntry.getYldPerCus());
		    	setDiscountPlots(whatIfEntry.getDiscountAll()*100D, whatIfEntry.getDiscountCollab()*100D, 
		    			whatIfEntry.getDiscountENTNW()*100D, whatIfEntry.getDiscountSecurity()*100D, whatIfEntry.getDiscountDCV()*100D, 
		    			whatIfEntry.getCusPen(), CalcHelper.round(
		    			whatIfEntry.getTechPen(), 2, BigDecimal.ROUND_HALF_UP), 
		    			whatIfEntry.getATAttach(), 
		    			whatIfEntry.getNonATAttach());		
				setOtherSplitPlots(whatIfEntry.getBookingENTNW(), whatIfEntry.getBookingSecurity(), 
						whatIfEntry.getBookingCollab(), whatIfEntry.getBookingDCV(),	whatIfEntry.getM1(), 
						whatIfEntry.getM2(), whatIfEntry.getM3(), whatIfEntry.getM4(), whatIfEntry.getM5(), 
						whatIfEntry.getM6(), whatIfEntry.getM7(), whatIfEntry.getM8(), whatIfEntry.getM9(), 
						whatIfEntry.getM10(), whatIfEntry.getM11(), whatIfEntry.getM12(), whatIfEntry.getQ1(), 
						whatIfEntry.getQ2(), whatIfEntry.getQ3(),	whatIfEntry.getQ4(), whatIfEntry.getANS(), 
						whatIfEntry.getDCSwitching(), whatIfEntry.getOthers(), whatIfEntry.getLANSwitching(), 
						whatIfEntry.getRouting(), whatIfEntry.getSecurity(), whatIfEntry.getStorage(), 
						whatIfEntry.getUC(), whatIfEntry.getUCS(), whatIfEntry.getVideo(), 
						whatIfEntry.getWirelessLAN(), whatIfEntry.getH1(), whatIfEntry.getH2(),
						whatIfEntry.getM1W1(), whatIfEntry.getM1W2(), whatIfEntry.getM1W3(), whatIfEntry.getM1W4(), 
						whatIfEntry.getM2W1(), whatIfEntry.getM2W2(), whatIfEntry.getM2W3(), whatIfEntry.getM2W4(),
						whatIfEntry.getM3W1(), whatIfEntry.getM3W2(), whatIfEntry.getM3W3(), whatIfEntry.getM3W4(), whatIfEntry.getM3W5(),
						whatIfEntry.getM4W1(), whatIfEntry.getM4W2(), whatIfEntry.getM4W3(), whatIfEntry.getM4W4(), 
						whatIfEntry.getM5W1(), whatIfEntry.getM5W2(), whatIfEntry.getM5W3(), whatIfEntry.getM5W4(),
						whatIfEntry.getM6W1(), whatIfEntry.getM6W2(), whatIfEntry.getM6W3(), whatIfEntry.getM6W4(), whatIfEntry.getM6W5(),
						whatIfEntry.getM7W1(), whatIfEntry.getM7W2(), whatIfEntry.getM7W3(), whatIfEntry.getM7W4(), 
						whatIfEntry.getM8W1(), whatIfEntry.getM8W2(), whatIfEntry.getM8W3(), whatIfEntry.getM8W4(),
						whatIfEntry.getM9W1(), whatIfEntry.getM9W2(), whatIfEntry.getM9W3(), whatIfEntry.getM9W4(), whatIfEntry.getM9W5(),
						whatIfEntry.getM10W1(), whatIfEntry.getM10W2(), whatIfEntry.getM10W3(), whatIfEntry.getM10W4(), 
						whatIfEntry.getM11W1(), whatIfEntry.getM11W2(), whatIfEntry.getM11W3(), whatIfEntry.getM11W4(),
						whatIfEntry.getM12W1(), whatIfEntry.getM12W2(), whatIfEntry.getM12W3(), whatIfEntry.getM12W4(), whatIfEntry.getM12W5());
				getContentPane().revalidate();
		    	getContentPane().repaint();
			}
		}
	}
	
	private void placeSubPeriodLabels(String p1, String p2, String p3, String p4) {
        if (isSubPeriodPlotOn) {
    	    currentYearPanel.remove(periodPos1);
    	    currentYearPanel.remove(periodPos2);
    	    currentYearPanel.remove(periodPos3);
    	    currentYearPanel.remove(periodPos4);
        }
        periodPos1 = ComponentHelper.getPeriodLabel(p1, 420, 370, 25, 20);
        periodPos2 = ComponentHelper.getPeriodLabel(p2, 440, 355, 25, 20);
        periodPos3 = ComponentHelper.getPeriodLabel(p3, 470, 350, 25, 20);
        periodPos4 = ComponentHelper.getPeriodLabel(p4, 500, 355, 25, 20);
	    currentYearPanel.add(periodPos1);
	    currentYearPanel.add(periodPos2);
	    currentYearPanel.add(periodPos3);
	    currentYearPanel.add(periodPos4);
		getContentPane().revalidate();
    	getContentPane().repaint();
	}
	
	private void setSubPeriodSteering(double needlePosition ) {
		// Yoke Plot
	    if (isYokePanelOn) {
		    currentYearPanel.remove(yokePanel);
	    }
        yokePanel = ComponentHelper.getYokePlot(needlePosition);
	    yokePanel.setBounds(385, 360, 180, 100);
	    currentYearPanel.add(yokePanel);
		getContentPane().revalidate();
    	getContentPane().repaint();
	}
	private void setFlightYoke() {
        ImageIcon imgFlightYoke = new ImageIcon(this.getClass().
        		getResource(GeneralConstants.FLIGHT_YOKE_IMAGE));
        JLabel jlFlightYoke = new JLabel(imgFlightYoke);
        
        jlFlightYoke.setBounds(365, 345, 220, 120);
        jlFlightYoke.setOpaque(false);

        try {
			Image leftArrow =  ImageIO.read(this.getClass().getResource(GeneralConstants.LEFT_ARROW_IMAGE));
		    jbLeft = ComponentHelper.getPeriodChangeButton(leftArrow, 390, 410, 50, 30);
		    jbLeft.setEnabled(false);
		    jbLeft.addActionListener(new periodButtonListener());
			Image rightArrow =  ImageIO.read(this.getClass().getResource(GeneralConstants.RIGHT_ARROW_IMAGE));
		    jbRight = ComponentHelper.getPeriodChangeButton(rightArrow, 512, 410, 50, 30);
		    jbRight.setEnabled(false);
		    jbRight.addActionListener(new periodButtonListener());
			Image override =  ImageIO.read(this.getClass().getResource(GeneralConstants.SWITCH_ON_IMAGE));
		    jbOverride = ComponentHelper.getPeriodChangeButton(override, 450, 420, 50, 30);
		    jbOverride.setEnabled(false);
		    jbOverride.addActionListener(new overrideButtonListener());
		} catch (IOException e) {
			e.printStackTrace();
		}
	    currentYearPanel.add(jbLeft);
	    currentYearPanel.add(jbRight);
	    currentYearPanel.add(jbOverride);
	    currentYearPanel.add(jlFlightYoke);
	}

	private void setControlButtons() {
		
	    switchPanel = new JPanel();
	    switchPanel.setBounds(220, 390, 510, 80);
	    switchPanel.setBackground(Color.BLACK);
	    switchPanel.setLayout(null);
	    
	    jbYTD = ComponentHelper.getControlButton("YTD", 5, 5, 30, 20);
	    jbYTD.addActionListener(new controlButtonListener());
	    jbH1 = ComponentHelper.getControlButton("H1", 40, 5, 30, 20);
	    jbH1.addActionListener(new controlButtonListener());
	    jbH2 = ComponentHelper.getControlButton("H2", 75, 5, 30, 20);
	    jbH2.addActionListener(new controlButtonListener());
	    jbQ1 = ComponentHelper.getControlButton("Q1", 5, 30, 30, 20);
	    jbQ1.addActionListener(new controlButtonListener());
	    jbQ2 = ComponentHelper.getControlButton("Q2", 40, 30, 30, 20);
	    jbQ2.addActionListener(new controlButtonListener());
	    jbQ3 = ComponentHelper.getControlButton("Q3", 75, 30, 30, 20);
	    jbQ3.addActionListener(new controlButtonListener());
	    jbQ4 = ComponentHelper.getControlButton("Q4", 110, 30, 30, 20);
	    jbQ4.addActionListener(new controlButtonListener());
	    jbAnalysis = ComponentHelper.getControlTButton("Analysis", 5, 55, 100, 20);
	    jbAnalysis.addItemListener(new assumptionButtonToggleListener());
	    jbAbs = ComponentHelper.getControlTButton("%", 110, 55, 30, 20);
	    jbAbs.addItemListener(new absToggleListener());
	    switchPanel.add(jbYTD);
	    switchPanel.add(jbH1);
	    switchPanel.add(jbH2);
	    switchPanel.add(jbQ1);
	    switchPanel.add(jbQ2);
	    switchPanel.add(jbQ3);
	    switchPanel.add(jbQ4);
	    switchPanel.add(jbAnalysis);
	    switchPanel.add(jbAbs);
	    currentYearPanel.add(switchPanel);
	    setControlLights();
	}

	
	private void setControlLights() {
		ImageIcon imgOff = new ImageIcon(this.getClass().
	     		getResource(GeneralConstants.GREEN_LIGHT_OFF_IMAGE));;
		ImageIcon imgOff2 = new ImageIcon(this.getClass().
	     		getResource(GeneralConstants.RED_LIGHT_OFF_IMAGE));;
	   jlYTD = ComponentHelper.getControlLight(imgOff, 370, 5, 35, 35);
	   jlTitleYTD = ComponentHelper.getControlLightLabel("YTD", 380, 0, 20, 10);
	   switchPanel.add(jlYTD);
	   switchPanel.add(jlTitleYTD);
	   jlH1 = ComponentHelper.getControlLight(imgOff, 410, 5, 35, 35);
	   jlTitleH1 = ComponentHelper.getControlLightLabel("H1", 420, 0, 20, 10);
	   switchPanel.add(jlH1);
	   switchPanel.add(jlTitleH1);
	   jlH2 = ComponentHelper.getControlLight(imgOff, 450, 5, 35, 35);
	   jlTitleH2 = ComponentHelper.getControlLightLabel("H2", 460, 0, 20, 10);
	   switchPanel.add(jlH2);
	   switchPanel.add(jlTitleH2);
	   jlQ1 = ComponentHelper.getControlLight(imgOff, 360, 45, 35, 35);
	   jlTitleQ1 = ComponentHelper.getControlLightLabel("Q1", 370, 40, 20, 10);
	   switchPanel.add(jlQ1);
	   switchPanel.add(jlTitleQ1);
	   jlQ2 = ComponentHelper.getControlLight(imgOff, 400, 45, 35, 35);
	   jlTitleQ2 = ComponentHelper.getControlLightLabel("Q2", 410, 40, 20, 10);
	   switchPanel.add(jlQ2);
	   switchPanel.add(jlTitleQ2);
	   jlQ3 = ComponentHelper.getControlLight(imgOff, 440, 45, 35, 35);
	   jlTitleQ3 = ComponentHelper.getControlLightLabel("Q3", 450, 40, 20, 10);
	   switchPanel.add(jlQ3);
	   switchPanel.add(jlTitleQ3);
	   jlQ4 = ComponentHelper.getControlLight(imgOff, 480, 45, 35, 35);
	   jlTitleQ4 = ComponentHelper.getControlLightLabel("Q4", 490, 40, 20, 10);
	   switchPanel.add(jlQ4);
	   switchPanel.add(jlTitleQ4);
	   jlAnalysis = ComponentHelper.getControlLight(imgOff2, 110, 0, 35, 35);
	   jlTitleAnalysis = ComponentHelper.getControlLightLabel("", 120, 0, 20, 10);
	   switchPanel.add(jlAnalysis);
	   switchPanel.add(jlTitleAnalysis);
	}
	
	
	private void setOptionSelectors() {
		try {
			
	    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		int FR_COMP_X_POSITION = 10;
		int FR_COMP_WIDTH = 80;
		int FR_COMP_Y_POSITION = 100;
		int FR_COMP_HEIGHT = 25;
		int FR_COMP_INCREMENT = 51;
		int tempCount;

		jlOption = new JLabel();
	    jlOption.setText("Option: ");
	    jlOption.setBounds(FR_COMP_X_POSITION,FR_COMP_Y_POSITION,FR_COMP_WIDTH,FR_COMP_HEIGHT);
	    jlOption.setFont(new Font(fontFamily,Font.BOLD,fontSize));
	    mainPanel.add(jlOption);

	    jcbxOption = new JComboBox(option);
	    jcbxOption.setSelectedIndex(-1);
	    jcbxOption.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
	    jcbxOption.setBounds(FR_COMP_X_POSITION+FR_COMP_INCREMENT-5, FR_COMP_Y_POSITION, FR_COMP_WIDTH+5, FR_COMP_HEIGHT);
	    jcbxOption.addItemListener(new OptionListener());
	    mainPanel.add(jcbxOption);

	    jlYear = new JLabel();
	    jlYear.setText("Fin Year: ");
	    jlYear.setFont(new Font(fontFamily,Font.BOLD,fontSize));
	    jlYear.setBounds(FR_COMP_X_POSITION+(2*FR_COMP_INCREMENT)+40, FR_COMP_Y_POSITION, FR_COMP_WIDTH+20, FR_COMP_HEIGHT);
	    mainPanel.add(jlYear);
	    jcbxYear= ComponentHelper.fillGDYearComboBox();
	    jcbxYear.setMaximumRowCount(4);
	    jcbxYear.setSelectedIndex(-1);
	    jcbxYear.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
	    jcbxYear.setBounds(FR_COMP_X_POSITION+(4*FR_COMP_INCREMENT)-10, FR_COMP_Y_POSITION, FR_COMP_WIDTH-20, FR_COMP_HEIGHT);
	    jcbxYear.addItemListener(new YearListener());
	    mainPanel.add(jcbxYear);

	    jlEU = new JLabel();
	    jlEU.setText("Execution Unit: ");
	    jlEU.setFont(new Font(fontFamily,Font.BOLD,fontSize));
	    jlEU.setBounds(FR_COMP_X_POSITION+(5*FR_COMP_INCREMENT)+10, FR_COMP_Y_POSITION, FR_COMP_WIDTH+20, FR_COMP_HEIGHT);
	    mainPanel.add(jlEU);
		jcbxEU = new JComboBox();
	    jcbxEU.setMaximumRowCount(4);
	    jcbxEU.setSelectedIndex(-1);
	    jcbxEU.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
	    jcbxEU.setBounds(FR_COMP_X_POSITION+(7*FR_COMP_INCREMENT), FR_COMP_Y_POSITION, FR_COMP_WIDTH-20, FR_COMP_HEIGHT);
	    jcbxEU.addItemListener(new EUComboListener());
	    mainPanel.add(jcbxEU);

	    Border bor = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
	    subSCMSPanel = new JPanel();
	    subSCMSPanel.setBorder(BorderFactory.createTitledBorder(bor, "Sub SCMS"));
	    subSCMSPanel.setBounds(FR_COMP_X_POSITION+(8*FR_COMP_INCREMENT)+20, FR_COMP_Y_POSITION+5, (2*FR_COMP_WIDTH), (2*FR_COMP_HEIGHT));
	    subSCMSPanel.setBackground(null);
	    subSCMSPanel.setOpaque(false);
	    subSCMSPanel.setFont(new Font(fontFamily,Font.BOLD,fontSize-10));
	    mainPanel.add(subSCMSPanel);
	    
		jrbPL = new JRadioButton("PL");
		jrbPL.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
		jrbPL.setBounds(FR_COMP_X_POSITION+(8*FR_COMP_INCREMENT)+20, FR_COMP_Y_POSITION, FR_COMP_WIDTH-40, FR_COMP_HEIGHT);
		jrbPL.setBackground(null);
		jrbPL.setOpaque(false);
		jrbPL.setMnemonic(KeyEvent.VK_L);
		jrbPL.setFocusable(false);
		jrbPL.setEnabled(false);
		jrbPL.addActionListener(new SubSCMSListener());
		subSCMSPanel.add(jrbPL);

		jrbPLV = new JRadioButton("PLV");
		jrbPLV.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
		jrbPLV.setBounds(FR_COMP_X_POSITION+(9*FR_COMP_INCREMENT)+35, FR_COMP_Y_POSITION, FR_COMP_WIDTH-35, FR_COMP_HEIGHT);
		jrbPLV.setBackground(null);
		jrbPLV.setOpaque(false);
		jrbPLV.setMnemonic(KeyEvent.VK_V);
		jrbPLV.setFocusable(false);
		jrbPLV.setEnabled(false);
		jrbPLV.addActionListener(new SubSCMSListener());
		subSCMSPanel.add(jrbPLV);
	    
		jrbAll = new JRadioButton("All");
		jrbAll.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
		jrbAll.setBounds(FR_COMP_X_POSITION+(9*FR_COMP_INCREMENT)+5, FR_COMP_Y_POSITION+FR_COMP_HEIGHT, FR_COMP_WIDTH-35, FR_COMP_HEIGHT);
		jrbAll.setBackground(null);
		jrbAll.setOpaque(false);
		jrbAll.setFocusable(false);
		jrbAll.setMnemonic(KeyEvent.VK_A);
		jrbAll.setEnabled(false);
		jrbAll.addActionListener(new SubSCMSListener());
		subSCMSPanel.add(jrbAll);

	    bg = new ButtonGroup();
	    bg.add(jrbPL);
	    bg.add(jrbPLV);
	    bg.add(jrbAll);

	    jlRegion = new JLabel();
	    jlRegion.setText("Region: ");
	    jlRegion.setBounds(FR_COMP_X_POSITION+(11*FR_COMP_INCREMENT)+35, FR_COMP_Y_POSITION, FR_COMP_WIDTH+FR_COMP_INCREMENT+20, FR_COMP_HEIGHT);
	    jlRegion.setFont(new Font(fontFamily,Font.BOLD,fontSize));
	    mainPanel.add(jlRegion);
	    jltRegion = new JList();
	    jltRegion.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    jltRegion.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
	    regionScrollPane = new JScrollPane(jltRegion, 
	     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    regionScrollPane.setBounds(FR_COMP_X_POSITION+(12*FR_COMP_INCREMENT)+35, FR_COMP_Y_POSITION, FR_COMP_WIDTH-20, FR_COMP_HEIGHT+35);
	    mainPanel.add(regionScrollPane);
	    jltRegion.addListSelectionListener(new RegionListListener());

	    jlNode = new JLabel();
	    jlNode.setText("Node: ");
	    jlNode.setFont(new Font(fontFamily,Font.BOLD,fontSize));
	    jlNode.setBounds(FR_COMP_X_POSITION+(13*FR_COMP_INCREMENT)+55, FR_COMP_Y_POSITION, FR_COMP_WIDTH+FR_COMP_INCREMENT+20, FR_COMP_HEIGHT);
	    mainPanel.add(jlNode);

	    
		Image quesMark =  ImageIO.read(this.getClass().getResource(GeneralConstants.LOCAL_LOGO_IMAGE2));
		jbShowSalesAgent = ComponentHelper.getPeriodChangeButton(quesMark, 965, FR_COMP_Y_POSITION+45, 15, 15);
		jbShowSalesAgent.setBackground(Color.LIGHT_GRAY.brighter());
		jbShowSalesAgent.setOpaque(false);
		jbShowSalesAgent.addActionListener(new ShowSalesAgentListener());
		jbShowSalesAgent.setEnabled(false);
	    mainPanel.add(jbShowSalesAgent);
	    
	    jltNode = new JList();
	    jltNode.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    jltNode.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
	    nodeScrollPane = new JScrollPane(jltNode,
	            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    nodeScrollPane.setBounds(FR_COMP_X_POSITION+(14*FR_COMP_INCREMENT)+45, FR_COMP_Y_POSITION, FR_COMP_WIDTH+(2*FR_COMP_INCREMENT)+10, FR_COMP_HEIGHT+35);
	    mainPanel.add(nodeScrollPane);
	    jltNode.addListSelectionListener(new NodeListListener());

	    jlName = new JLabel();
	    jlName.setText("Unique_Names: ");
	    jlName.setFont(new Font(fontFamily,Font.BOLD,fontSize));
	    jlName.setBounds(FR_COMP_X_POSITION+(18*FR_COMP_INCREMENT)+45, FR_COMP_Y_POSITION, FR_COMP_WIDTH+FR_COMP_INCREMENT+20, FR_COMP_HEIGHT);
	    mainPanel.add(jlName);
	    
	    jltName = new JList();
	    jltName.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    jltName.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
	    NameScrollPane = new JScrollPane(jltName,
	            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    NameScrollPane.setBounds(FR_COMP_X_POSITION+(20*FR_COMP_INCREMENT)+40, FR_COMP_Y_POSITION, FR_COMP_WIDTH+(4*FR_COMP_INCREMENT), FR_COMP_HEIGHT+35);
	    mainPanel.add(NameScrollPane);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
	    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

	
	private class SubSCMSListener implements ActionListener {
		
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
	    	try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if (e.getSource() == jrbPL || e.getSource() == jrbPLV || e.getSource() == jrbAll) {
	               dummyModel = new DefaultListModel();
		    	   dummyModel.removeAllElements();
		    	   jltRegion.setModel(dummyModel);
		    	   jltNode.setModel(dummyModel);
		    	   jltName.setModel(dummyModel);
				   jbShowSalesAgent.setEnabled(false);
		    	   String eu = (String)jcbxEU.getSelectedItem();
		    	   jltRegion.setModel(ComponentHelper.fillGDRegionListBox(DesktopPrimaryScreen.userID, eu));
			}		
	    	} catch(Exception ex) {
	    		ex.printStackTrace();
	    	} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    	}
		}
	}

	
	private class RegionListListener implements ListSelectionListener {
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		String subSCMS, region;
	    @SuppressWarnings("unchecked")
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting() && !jltRegion.isSelectionEmpty()) {
		    	try {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		               dummyModel = new DefaultListModel();
			    	   dummyModel.removeAllElements();
			    	   jltName.setModel(dummyModel);
			    	if (jrbPL.isSelected() || jrbPLV.isSelected() || 
			    			jrbAll.isSelected()) {
				    	if (jrbPL.isSelected()) {
				    		subSCMS="PL";
				    	} else if (jrbPLV.isSelected()) {
				    		subSCMS="PL_S";
				    	} else subSCMS="ALL";
				    	if (ComponentHelper.doesListContainAll(jltRegion)) {
				    		if (!ComponentHelper.doesListContainAtleastOne(jltRegion)) {
				    		region = "ALL"; 			    	
				    		} else region=ComponentHelper.getSelectedListItem(jltRegion);
				    	} else if (ComponentHelper.doesListContainMultiple(jltRegion)) {
				    		region="Multiple";
				    	} else region=ComponentHelper.getSelectedListItem(jltRegion);
				    	
				    	   String eu = (String)jcbxEU.getSelectedItem();
				    	   jltNode.setModel(ComponentHelper.fillGDNodeListBox(jltRegion, DesktopPrimaryScreen.userID, subSCMS, region, finYear, eu));
				    	   
			    	} else JOptionPane.showMessageDialog(null, "Sub SCMS must be selected!");
		    	} catch (Exception ex) {
		    		ex.printStackTrace();
		    	} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    	}
			}
	    }
	}
	
	private class ShowSalesAgentListener implements ActionListener {
		String subSCMS, region, nodeString, salesAgentString;
	    JScrollPane sPane;
	    JList nameList;
	    int resultDialog;
	    String[] tempArr;
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
	    	try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		    	
			    nameList = new JList();
			    nameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			    nameList.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
			    sPane = new JScrollPane(nameList,
			            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			    sPane.setPreferredSize(new Dimension(290,100));
		    	
			    	if (jrbPL.isSelected()) {
			    		subSCMS="PL";
			    	} else if (jrbPLV.isSelected()) {
			    		subSCMS="PL_S";
			    	} else subSCMS="ALL";
			    	
			    	if (ComponentHelper.doesListContainAll(jltRegion)) {
			    		if (!ComponentHelper.doesListContainAtleastOne(jltRegion)) {
			    			region = "ALL"; 			    	
			    		} else region=ComponentHelper.getSelectedListItem(jltRegion);
			    	} else if (ComponentHelper.doesListContainMultiple(jltRegion)) {
			    		region="Multiple";
			    	} else region=ComponentHelper.getSelectedListItem(jltRegion);

			    	if (ComponentHelper.doesListContainAll(jltNode)) {
			    		if (!ComponentHelper.doesListContainAtleastOne(jltNode)) {
			    			nodeString = "ALL"; 			    	
			    		} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
			    	} else if (ComponentHelper.doesListContainMultiple(jltNode)) {
			    		nodeString="Multiple";
			    	} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
			    	
	    	   String eu = (String)jcbxEU.getSelectedItem();
	    	   String optionString = (String)jcbxOption.getSelectedItem();
	    	   nameList.setModel(ComponentHelper.fillSalesAgentListBox(jltNode, jltRegion, nodeString, subSCMS, region, finYear, eu));
	    	   
	    	   //Getting input in Dialog for selecting sales_agents
			   do {
	    	   resultDialog = JOptionPane.showConfirmDialog(null, sPane,"Select Sales Agent Name", 
					   JOptionPane.OK_CANCEL_OPTION);
			   tempArr = ComponentHelper.getListInArray(nameList);

			   } while (tempArr.length<1 && resultDialog == JOptionPane.OK_OPTION) ;
			   

		    	if (ComponentHelper.doesListContainAll(nameList)) {
		    		if (!ComponentHelper.doesListContainAtleastOne(nameList)) {
		    			salesAgentString = "ALL"; 			    	
		    		} else nodeString=ComponentHelper.getSelectedListItem(nameList);
		    	} else if (ComponentHelper.doesListContainMultiple(nameList)) {
		    		salesAgentString="Multiple";
		    	} else salesAgentString=ComponentHelper.getSelectedListItem(nameList);

		    	
			   if (resultDialog == JOptionPane.OK_OPTION) {
				   if (optionString.equals("TBM/VBM")) {
					   jltName.setModel(ComponentHelper.getListModelFromArray(tempArr));	
				   } else if (optionString.equals("Partner")) {
				    	   jltName.setModel(ComponentHelper.
				    			   fillPartnerListBox(nameList, jltNode, nodeString, subSCMS, 
				    					   salesAgentString, finYear));
				   } else jltName.setModel(ComponentHelper.
						   fillCustomerListBox(nameList, jltNode, nodeString, subSCMS, 
								   salesAgentString, finYear));
			   } else JOptionPane.showMessageDialog(null, 
					   "If you don't select anything, you may be unable to proceed further");
			   
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	    	} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    	}
		}
	}
	
	private class NodeListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting() && !jltNode.isSelectionEmpty()) {
                dummyModel = new DefaultListModel();
	    	    dummyModel.removeAllElements();
		    	jltName.setModel(dummyModel);
				jbShowSalesAgent.setEnabled(true);
			}
	    }
	}

	
	private class EUComboListener implements ItemListener {
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
	    public void itemStateChanged(ItemEvent event)  {
	    	try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

	    	if (event.getStateChange() == ItemEvent.SELECTED) {
                dummyModel = new DefaultListModel();
	    	    dummyModel.removeAllElements();
	    	    jltRegion.setModel(dummyModel);
	    	    jltNode.setModel(dummyModel);
	    	    jltName.setModel(dummyModel);
				jbShowSalesAgent.setEnabled(false);
	    	    jrbPL.setSelected(false);
	    		jrbPL.setEnabled(true);
	    	    jrbPLV.setSelected(false);
	    		jrbPLV.setEnabled(true);
	    	    jrbAll.setSelected(false);
	    		jrbAll.setEnabled(true);
	        }
	    	} catch(Exception ex) {
	    		ex.printStackTrace();
	    	} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    	}
	    }
	}

	
	private class OptionListener implements ItemListener {
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
	    public void itemStateChanged(ItemEvent event) {
	    	try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		       if (event.getStateChange() == ItemEvent.SELECTED) {
		    	   optionTrigger = (String)jcbxOption.getSelectedItem();
		    	   jrbPL.setEnabled(false);
		    	   jrbPLV.setEnabled(false);
		    	   jrbAll.setEnabled(false);
	               dummyModel = new DefaultListModel();
		    	   dummyModel.removeAllElements();
		    	   jltNode.setModel(dummyModel);
				   jbShowSalesAgent.setEnabled(false);
		    	   jltRegion.setModel(dummyModel);
		    	   jltName.setModel(dummyModel);
		    	   jcbxEU.setModel(ComponentHelper.fillGDEUComboBox(DesktopPrimaryScreen.userID));
		    	   jcbxEU.setSelectedIndex(-1);
		       }
	    	} catch(Exception ex) {
	    		ex.printStackTrace();
	    	} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    	}
	    }
	}
	
	private class YearListener implements ItemListener {
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
	    public void itemStateChanged(ItemEvent event) {
	    	try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		       if (event.getStateChange() == ItemEvent.SELECTED) {
		    	   finYear = (String)jcbxYear.getSelectedItem();
		    	   finYear = finYear.substring(finYear.indexOf("FY")+2);
		       }
	    	} catch(Exception ex) {
	    		ex.printStackTrace();
	    	} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    	}
	    }
	}

	
	private class controlButtonListener implements ActionListener {
		
        ImageIcon imgOn, imgOff, imgOn2, imgOff2;
		public void actionPerformed(ActionEvent e) {
			try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//		    	currentYearPanel.remove(jbLeft);
//			    currentYearPanel.remove(jbRight);
		    	jbLeft.setEnabled(true);
		    	jbRight.setEnabled(true);
//			    currentYearPanel.add(jbLeft);
//			    currentYearPanel.add(jbRight);
				getContentPane().revalidate();
		    	getContentPane().repaint();

			    sidePanel.removeAll();
		        currentYearPanel.remove(jpTechnologyImage);
		        currentYearPanel.remove(jtaTechnologyInfo);
		        currentYearPanel.remove(jpCalendarImage);
		        currentYearPanel.remove(jtaCalendarInfo);
		        currentYearPanel.remove(jpArchitectureImage);
		        currentYearPanel.remove(jtaArchitectureInfo);
		        currentYearPanel.remove(jpDiscountImage);
		        currentYearPanel.remove(jtaDiscountInfo);
	    	   imgOn = new ImageIcon(this.getClass().
	              		getResource(GeneralConstants.GREEN_LIGHT_IMAGE));
	    	   imgOff = new ImageIcon(this.getClass().
	              		getResource(GeneralConstants.GREEN_LIGHT_OFF_IMAGE));
	    	   imgOn2 = new ImageIcon(this.getClass().
	              		getResource(GeneralConstants.RED_LIGHT_IMAGE));
	    	   imgOff2 = new ImageIcon(this.getClass().
	              		getResource(GeneralConstants.RED_LIGHT_OFF_IMAGE));
	    	   JLabel label, titleLabel;
	    	   if (isAnalysis) {
	    		   jlAnalysis = ComponentHelper.getControlLight(imgOn2, 110, 0, 35, 35);
	    	   } else jlAnalysis = ComponentHelper.getControlLight(imgOff2, 110, 0, 35, 35);

			if (e.getSource() == jbYTD) {
				   needleWhereTo = 0D;
				   setSubPeriodSteering(needleWhereTo);
				   placeSubPeriodLabels("Q1", "Q2", "Q3", "Q4");
				   isSubPeriodPlotOn = true;
				   isYokePanelOn = true;
				   isYTD=true;
				   isH1=false;
				   isH2=false;
				   isQ1=false;
				   isQ2=false;
				   isQ3=false;
				   isQ4=false;
	 	    	   label = ComponentHelper.getControlLight(imgOn, 370, 5, 35, 35);
	 	    	   titleLabel = ComponentHelper.getControlLightLabel("YTD", 380, 0, 20, 10);
	 	    	   jlH1 = ComponentHelper.getControlLight(imgOff, 410, 5, 35, 35);
	 	    	   jlH2 = ComponentHelper.getControlLight(imgOff, 450, 5, 35, 35);
	 	    	   jlQ1 = ComponentHelper.getControlLight(imgOff, 360, 45, 35, 35);
	 	    	   jlQ2 = ComponentHelper.getControlLight(imgOff, 400, 45, 35, 35);
	 	    	   jlQ3 = ComponentHelper.getControlLight(imgOff, 440, 45, 35, 35);
	 	    	   jlQ4 = ComponentHelper.getControlLight(imgOff, 480, 45, 35, 35);
	 	    	   ComponentHelper.refreshSwitchPanel(currentYearPanel, switchPanel, jbYTD, jbH1, 
	 	    			   jbH2, jbQ1, jbQ2, jbQ3, jbQ4, jbAnalysis, jbAbs, titleLabel, label,
	 	    			   jlH1, jlTitleH1, jlH2, jlTitleH2, jlQ1, jlTitleQ1, jlQ2, jlTitleQ2, 
	 	    			   jlQ3, jlTitleQ3, jlQ4, jlTitleQ4, jlAnalysis, jlTitleAnalysis);
			    	finQuarter = "ALL";
			    	finMonth = "ALL";
	 	    	   	refreshScreenforYTD();
					getContentPane().revalidate();
			    	getContentPane().repaint();
			} else if (e.getSource() == jbH1) {
				   needleWhereTo = 0D;
				   setSubPeriodSteering(needleWhereTo);
				   placeSubPeriodLabels("Q1", "Q2", "", "");
				   isSubPeriodPlotOn = true;
				   isYokePanelOn = true;
				   isYTD=false;
				   isH1=true;
				   isH2=false;
				   isQ1=false;
				   isQ2=false;
				   isQ3=false;
				   isQ4=false;
 	    	   label = ComponentHelper.getControlLight(imgOn, 410, 5, 35, 35);
 	    	   titleLabel = ComponentHelper.getControlLightLabel("H1", 420, 0, 20, 10);
 	    	   jlYTD = ComponentHelper.getControlLight(imgOff, 370, 5, 35, 35);
 	    	   jlH2 = ComponentHelper.getControlLight(imgOff, 450, 5, 35, 35);
 	    	   jlQ1 = ComponentHelper.getControlLight(imgOff, 360, 45, 35, 35);
 	    	   jlQ2 = ComponentHelper.getControlLight(imgOff, 400, 45, 35, 35);
 	    	   jlQ3 = ComponentHelper.getControlLight(imgOff, 440, 45, 35, 35);
 	    	   jlQ4 = ComponentHelper.getControlLight(imgOff, 480, 45, 35, 35);
 	    	   ComponentHelper.refreshSwitchPanel(currentYearPanel, switchPanel, jbYTD, jbH1, 
 	    			   jbH2, jbQ1, jbQ2, jbQ3, jbQ4, jbAnalysis, jbAbs, titleLabel, label,
 	    			   jlYTD, jlTitleYTD, jlH2, jlTitleH2, jlQ1, jlTitleQ1, jlQ2, jlTitleQ2, 
 	    			   jlQ3, jlTitleQ3, jlQ4, jlTitleQ4, jlAnalysis, jlTitleAnalysis);
		    	finQuarter = "Q1";
		    	finMonth = "Q2";
	    	   	refreshScreenforYTD();
				getContentPane().revalidate();
		    	getContentPane().repaint();
			} else if (e.getSource() == jbH2) {
				   needleWhereTo = 0D;
				   setSubPeriodSteering(needleWhereTo);
				   placeSubPeriodLabels("Q3", "Q4", "", "");
				   isSubPeriodPlotOn = true;
				   isYokePanelOn = true;
				   isYTD=false;
				   isH1=false;
				   isH2=true;
				   isQ1=false;
				   isQ2=false;
				   isQ3=false;
				   isQ4=false;
 	    	   label = ComponentHelper.getControlLight(imgOn, 450, 5, 35, 35);
 	    	   titleLabel = ComponentHelper.getControlLightLabel("H2", 460, 0, 20, 10);
 	    	   jlYTD = ComponentHelper.getControlLight(imgOff, 370, 5, 35, 35);
 	    	   jlH1 = ComponentHelper.getControlLight(imgOff, 410, 5, 35, 35);
 	    	   jlQ1 = ComponentHelper.getControlLight(imgOff, 360, 45, 35, 35);
 	    	   jlQ2 = ComponentHelper.getControlLight(imgOff, 400, 45, 35, 35);
 	    	   jlQ3 = ComponentHelper.getControlLight(imgOff, 440, 45, 35, 35);
 	    	   jlQ4 = ComponentHelper.getControlLight(imgOff, 480, 45, 35, 35);
 	    	   ComponentHelper.refreshSwitchPanel(currentYearPanel, switchPanel, jbYTD, jbH1, 
 	    			   jbH2, jbQ1, jbQ2, jbQ3, jbQ4, jbAnalysis, jbAbs, titleLabel, label,
 	    			   jlYTD, jlTitleYTD, jlH1, jlTitleH1, jlQ1, jlTitleQ1, jlQ2, jlTitleQ2, 
 	    			   jlQ3, jlTitleQ3, jlQ4, jlTitleQ4, jlAnalysis, jlTitleAnalysis);
		    	finQuarter = "Q3";
		    	finMonth = "Q4";
	    	   	refreshScreenforYTD();
				getContentPane().revalidate();
		    	getContentPane().repaint();
			} else if (e.getSource() == jbQ1) {
				   needleWhereTo = 0D;
				   setSubPeriodSteering(needleWhereTo);
				   placeSubPeriodLabels("M1", "M2", "M3", "");
				   isSubPeriodPlotOn = true;
				   isYokePanelOn = true;
				   isYTD=false;
				   isH1=false;
				   isH2=false;
				   isQ1=true;
				   isQ2=false;
				   isQ3=false;
				   isQ4=false;
 	    	   label = ComponentHelper.getControlLight(imgOn, 360, 45, 35, 35);
 	    	   titleLabel = ComponentHelper.getControlLightLabel("Q1", 370, 40, 20, 10);
 	    	   jlYTD = ComponentHelper.getControlLight(imgOff, 370, 5, 35, 35);
 	    	   jlH1 = ComponentHelper.getControlLight(imgOff, 410, 5, 35, 35);
 	    	   jlH2 = ComponentHelper.getControlLight(imgOff, 450, 5, 35, 35);
 	    	   jlQ2 = ComponentHelper.getControlLight(imgOff, 400, 45, 35, 35);
 	    	   jlQ3 = ComponentHelper.getControlLight(imgOff, 440, 45, 35, 35);
 	    	   jlQ4 = ComponentHelper.getControlLight(imgOff, 480, 45, 35, 35);
 	    	   ComponentHelper.refreshSwitchPanel(currentYearPanel, switchPanel, jbYTD, jbH1, 
 	    			   jbH2, jbQ1, jbQ2, jbQ3, jbQ4, jbAnalysis, jbAbs, titleLabel, label,
 	    			   jlYTD, jlTitleYTD, jlH1, jlTitleH1, jlH2, jlTitleH2, jlQ2, jlTitleQ2, 
 	    			   jlQ3, jlTitleQ3, jlQ4, jlTitleQ4, jlAnalysis, jlTitleAnalysis);
		    	finQuarter = jbQ1.getText();
		    	finMonth = "ALL";
	    	   	refreshScreenforYTD();
				getContentPane().revalidate();
		    	getContentPane().repaint();
			} else if (e.getSource() == jbQ2) {
				   needleWhereTo = 0D;
				   setSubPeriodSteering(needleWhereTo);
				   placeSubPeriodLabels("M1", "M2", "M3", "");
				   isSubPeriodPlotOn = true;
				   isYokePanelOn = true;
				   isYTD=false;
				   isH1=false;
				   isH2=false;
				   isQ1=false;
				   isQ2=true;
				   isQ3=false;
				   isQ4=false;
 	    	   label = ComponentHelper.getControlLight(imgOn, 400, 45, 35, 35);
 	    	   titleLabel = ComponentHelper.getControlLightLabel("Q2", 410, 40, 20, 10);
 	    	   jlYTD = ComponentHelper.getControlLight(imgOff, 370, 5, 35, 35);
 	    	   jlH1 = ComponentHelper.getControlLight(imgOff, 410, 5, 35, 35);
 	    	   jlH2 = ComponentHelper.getControlLight(imgOff, 450, 5, 35, 35);
 	    	   jlQ1 = ComponentHelper.getControlLight(imgOff, 360, 45, 35, 35);
 	    	   jlQ3 = ComponentHelper.getControlLight(imgOff, 440, 45, 35, 35);
 	    	   jlQ4 = ComponentHelper.getControlLight(imgOff, 480, 45, 35, 35);
 	    	   ComponentHelper.refreshSwitchPanel(currentYearPanel, switchPanel, jbYTD, jbH1, 
 	    			   jbH2, jbQ1, jbQ2, jbQ3, jbQ4, jbAnalysis, jbAbs, titleLabel, label,
 	    			   jlYTD, jlTitleYTD, jlH1, jlTitleH1, jlH2, jlTitleH2, jlQ1, jlTitleQ1, 
 	    			   jlQ3, jlTitleQ3, jlQ4, jlTitleQ4, jlAnalysis, jlTitleAnalysis);
		    	finQuarter = jbQ2.getText();
		    	finMonth = "ALL";
	    	   	refreshScreenforYTD();
				getContentPane().revalidate();
		    	getContentPane().repaint();
			} else if (e.getSource() == jbQ3) {
				   needleWhereTo = 0D;
				   setSubPeriodSteering(needleWhereTo);
				   placeSubPeriodLabels("M1", "M2", "M3", "");
				   isSubPeriodPlotOn = true;
				   isYokePanelOn = true;
				   isYTD=false;
				   isH1=false;
				   isH2=false;
				   isQ1=false;
				   isQ2=false;
				   isQ3=true;
				   isQ4=false;
 	    	   label = ComponentHelper.getControlLight(imgOn, 440, 45, 35, 35);
 	    	   titleLabel = ComponentHelper.getControlLightLabel("Q3", 450, 40, 20, 10);
 	    	   jlYTD = ComponentHelper.getControlLight(imgOff, 370, 5, 35, 35);
 	    	   jlH1 = ComponentHelper.getControlLight(imgOff, 410, 5, 35, 35);
 	    	   jlH2 = ComponentHelper.getControlLight(imgOff, 450, 5, 35, 35);
 	    	   jlQ1 = ComponentHelper.getControlLight(imgOff, 360, 45, 35, 35);
 	    	   jlQ2 = ComponentHelper.getControlLight(imgOff, 400, 45, 35, 35);
 	    	   jlQ4 = ComponentHelper.getControlLight(imgOff, 480, 45, 35, 35);
 	    	   ComponentHelper.refreshSwitchPanel(currentYearPanel, switchPanel, jbYTD, jbH1, 
 	    			   jbH2, jbQ1, jbQ2, jbQ3, jbQ4, jbAnalysis, jbAbs, titleLabel, label,
 	    			   jlYTD, jlTitleYTD, jlH1, jlTitleH1, jlH2, jlTitleH2, jlQ1, jlTitleQ1, 
 	    			   jlQ2, jlTitleQ2, jlQ4, jlTitleQ4, jlAnalysis, jlTitleAnalysis);
		    	finQuarter = jbQ3.getText();
		    	finMonth = "ALL";
	    	   	refreshScreenforYTD();
				getContentPane().revalidate();
		    	getContentPane().repaint();
			} else if (e.getSource() == jbQ4) {
				   needleWhereTo = 0D;
				   setSubPeriodSteering(needleWhereTo);
				   placeSubPeriodLabels("M1", "M2", "M3", "");
				   isSubPeriodPlotOn = true;
				   isYokePanelOn = true;
				   isYTD=false;
				   isH1=false;
				   isH2=false;
				   isQ1=false;
				   isQ2=false;
				   isQ3=false;
				   isQ4=true;
 	    	   label = ComponentHelper.getControlLight(imgOn, 480, 45, 35, 35);
 	    	   titleLabel = ComponentHelper.getControlLightLabel("Q4", 490, 40, 20, 10);
 	    	   jlYTD = ComponentHelper.getControlLight(imgOff, 370, 5, 35, 35);
 	    	   jlH1 = ComponentHelper.getControlLight(imgOff, 410, 5, 35, 35);
 	    	   jlH2 = ComponentHelper.getControlLight(imgOff, 450, 5, 35, 35);
 	    	   jlQ1 = ComponentHelper.getControlLight(imgOff, 360, 45, 35, 35);
 	    	   jlQ2 = ComponentHelper.getControlLight(imgOff, 400, 45, 35, 35);
 	    	   jlQ3 = ComponentHelper.getControlLight(imgOff, 440, 45, 35, 35);
 	    	   ComponentHelper.refreshSwitchPanel(currentYearPanel, switchPanel, jbYTD, jbH1, 
 	    			   jbH2, jbQ1, jbQ2, jbQ3, jbQ4, jbAnalysis, jbAbs, titleLabel, label,
 	    			   jlYTD, jlTitleYTD, jlH1, jlTitleH1, jlH2, jlTitleH2, jlQ1, jlTitleQ1, 
 	    			   jlQ2, jlTitleQ2, jlQ3, jlTitleQ3, jlAnalysis, jlTitleAnalysis);
		    	finQuarter = jbQ4.getText();
		    	finMonth = "ALL";
	    	   	refreshScreenforYTD();
				getContentPane().revalidate();
		    	getContentPane().repaint();
			}
	    	   if (isYTD) {
    	    	   ComponentHelper.changeToSwitchOnState(jbYTD, Color.WHITE, Color.BLACK);
	    	   } else ComponentHelper.changeToSwitchOffState(jbYTD, Color.BLACK, Color.WHITE);
	    	   if (isH1) {
    	    	   ComponentHelper.changeToSwitchOnState(jbH1, Color.WHITE, Color.BLACK);
	    	   } else ComponentHelper.changeToSwitchOffState(jbH1, Color.BLACK, Color.WHITE);

	    	   if (isH2) {
    	    	   ComponentHelper.changeToSwitchOnState(jbH2, Color.WHITE, Color.BLACK);
	    	   } else ComponentHelper.changeToSwitchOffState(jbH2, Color.BLACK, Color.WHITE);
	    	   if (isQ1) {
    	    	   ComponentHelper.changeToSwitchOnState(jbQ1, Color.WHITE, Color.BLACK);
	    	   } else ComponentHelper.changeToSwitchOffState(jbQ1, Color.BLACK, Color.WHITE);
	    	   if (isQ2) {
    	    	   ComponentHelper.changeToSwitchOnState(jbQ2, Color.WHITE, Color.BLACK);
	    	   } else ComponentHelper.changeToSwitchOffState(jbQ2, Color.BLACK, Color.WHITE);
	    	
	    	   if (isQ3) {
    	    	   ComponentHelper.changeToSwitchOnState(jbQ3, Color.WHITE, Color.BLACK);
	    	   } else ComponentHelper.changeToSwitchOffState(jbQ3, Color.BLACK, Color.WHITE);
	    	   if (isQ4) {
    	    	   ComponentHelper.changeToSwitchOnState(jbQ4, Color.WHITE, Color.BLACK);
	    	   } else ComponentHelper.changeToSwitchOffState(jbQ4, Color.BLACK, Color.WHITE);
			   isFirstAttemptFinished = true;
			   isTopTBMAttempted = false;
		       isTopPartnerAttempted = false;
		       isTopCustomerAttempted = false;
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}


	private void refreshScreenforYTD() {
 	   String nodeString = null, nameString = null;
 	   topTBM = null;
 	   topPartner = null;
 	   topCustomer = null;
			if (!ComponentHelper.doesListContainJustOne(jltNode)) {
				nodeString = "Multiple"; 			    	
			} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
	
			if (!ComponentHelper.doesListContainJustOne(jltName)) {
				nameString = "Multiple"; 			    	
			} else nameString=ComponentHelper.getSelectedListItem(jltName);

			nString = nodeString;
			if (optionTrigger.equals("TBM/VBM")) {
			valueSet = ComponentHelper.getTBMMetricsYTD(jltName, jltNode, nodeString, nameString, 
					finYear, finQuarter, finMonth, false);
			} else if (optionTrigger.equals("Partner")) {
				valueSet = ComponentHelper.getPartnerMetricsYTD(jltName, jltNode, nodeString, nameString, 
						finYear, finQuarter, finMonth, false);
			} else if (optionTrigger.equals("Customer")) {
				valueSet = ComponentHelper.getCustomerMetricsYTD(jltName, jltNode, nodeString, nameString, 
						finYear, finQuarter, finMonth, false);
			}
			
			valueEntry = valueSet.get(0);
			if (isFirstAttemptFinished) {
				currentYearPanel.remove(centerChartPanel);
			    currentYearPanel.remove(disAllChartPanel);
			    currentYearPanel.remove(disENTNWChartPanel);
			    currentYearPanel.remove(disSecChartPanel);
			    currentYearPanel.remove(disCollChartPanel);
			    currentYearPanel.remove(disDCVChartPanel);
			    currentYearPanel.remove(penCusChartPanel);
			    currentYearPanel.remove(techPenHolder);
			    currentYearPanel.remove(atAttachHolder);
			    currentYearPanel.remove(techSplitHolder);
			    currentYearPanel.remove(archSplitHolder);
			    currentYearPanel.remove(month_weekSplitHolder);
			    currentYearPanel.remove(quarterSplitHolder);
			    currentYearPanel.remove(halfYearSplitHolder);
			    sidePanel.removeAll();
				setWhatIfController();
			}
		    if (isCompareYearAttempted) {
		    	compoPanel.remove(compareYearPanel);
		    	isCompareYearAttempted = false;
		    }

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
		if (optionTrigger.equals("TBM/VBM")) {
			setToppersSalesAgent(valueTop, 5);
		} else if (optionTrigger.equals("Partner")) {
			setToppersPartner(valueTop, 5);
		} else if (optionTrigger.equals("Customer")) {
			setToppersCustomer(valueTop, 5);
		}
	}
	
	
	private class TopTBMActionListener implements ActionListener {
		
		private String nodeString, nameString;
		private List<TopNames> top;
		public void actionPerformed(ActionEvent e) {
			try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				if (e.getSource() == tbmToggle1) {
					isTBMToggle1=true;
					isTBMToggle2=false;
					isTBMToggle3=false;
					isTBMToggle4=false;
					isTBMToggle5=false;
					nameString = tbmToggle1.getText();
				} else if (e.getSource() == tbmToggle2) {
					isTBMToggle1=false;
					isTBMToggle2=true;
					isTBMToggle3=false;
					isTBMToggle4=false;
					isTBMToggle5=false;
					nameString = tbmToggle2.getText();
				} else if (e.getSource() == tbmToggle3) {
					isTBMToggle1=false;
					isTBMToggle2=false;
					isTBMToggle3=true;
					isTBMToggle4=false;
					isTBMToggle5=false;
					nameString = tbmToggle3.getText();
				} else if (e.getSource() == tbmToggle4) {
					isTBMToggle1=false;
					isTBMToggle2=false;
					isTBMToggle3=false;
					isTBMToggle4=true;
					isTBMToggle5=false;
					nameString = tbmToggle4.getText();
				} else if (e.getSource() == tbmToggle5) {
					isTBMToggle1=false;
					isTBMToggle2=false;
					isTBMToggle3=false;
					isTBMToggle4=false;
					isTBMToggle5=true;
					nameString = tbmToggle5.getText();
				} 
		    	   if (isTBMToggle1) {
	    	    	   ComponentHelper.changeToSwitchOnState(tbmToggle1, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(tbmToggle1, null, Color.BLUE);
		    	   if (isTBMToggle2) {
	    	    	   ComponentHelper.changeToSwitchOnState(tbmToggle2, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(tbmToggle2, null, Color.BLUE);
		    	   if (isTBMToggle3) {
	    	    	   ComponentHelper.changeToSwitchOnState(tbmToggle3, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(tbmToggle3, null, Color.BLUE);
		    	   if (isTBMToggle4) {
	    	    	   ComponentHelper.changeToSwitchOnState(tbmToggle4, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(tbmToggle4, null, Color.BLUE);
		    	   if (isTBMToggle5) {
	    	    	   ComponentHelper.changeToSwitchOnState(tbmToggle5, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(tbmToggle5, null, Color.BLUE);
		    		if (!ComponentHelper.doesListContainJustOne(jltNode)) {
		    			nodeString = "Multiple"; 			    	
		    		} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
	
				nString = nodeString;
		        valueSet = null;
		    	valueSet = ComponentHelper.getTBMPartnerMetricsYTD(jltNode, nodeString, nameString, 
		    			finYear, finQuarter, finMonth, false);
		    	valueEntry=null;
		    	valueEntry = valueSet.get(0);
		    	topTBM = nameString;
		    	topPartner = null;
		    	topCustomer = null;
		    	
				currentYearPanel.remove(centerChartPanel);
			    currentYearPanel.remove(disAllChartPanel);
			    currentYearPanel.remove(disENTNWChartPanel);
			    currentYearPanel.remove(disSecChartPanel);
			    currentYearPanel.remove(disCollChartPanel);
			    currentYearPanel.remove(disDCVChartPanel);
			    currentYearPanel.remove(penCusChartPanel);
			    currentYearPanel.remove(techPenHolder);
			    currentYearPanel.remove(atAttachHolder);
			    currentYearPanel.remove(techSplitHolder);
			    currentYearPanel.remove(archSplitHolder);
			    currentYearPanel.remove(month_weekSplitHolder);
			    currentYearPanel.remove(quarterSplitHolder);
			    currentYearPanel.remove(halfYearSplitHolder);
			    if (isCompareYearAttempted) {
			    	compoPanel.remove(compareYearPanel);
			    	isCompareYearAttempted = false;
			    }
				if (isTopTBMAttempted) {
				    topPartnerHolder.removeAll();
				    sidePanel.remove(topPartnerHolder);
		    	}
				if (isTopPartnerAttempted) {
				    topCustomerHolder.removeAll();
				    sidePanel.remove(topCustomerHolder);
				}
				top = (List<TopNames>) valueEntry.getTopData();
		    	Collections.sort(top, new TopNames());
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
				setToppersPartner(top, 170);
				setWhatIfController();
				getContentPane().revalidate();
		    	getContentPane().repaint();
	            isTopTBMAttempted = true;
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}


	private class TopPartnerActionListener implements ActionListener {
		
		private String nodeString, tbmString, partnerString;
		private List<TopNames> top;
		public void actionPerformed(ActionEvent e) {
			try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		        if (optionTrigger.equals("TBM/VBM")) {
					tbmString = ComponentHelper.getSelectedTopNames(tbmToggle1, 
							tbmToggle2, tbmToggle3, tbmToggle4, tbmToggle5);
		        }
				if (e.getSource() == partnerToggle1) {
					isPartnerToggle1=true;
					isPartnerToggle2=false;
					isPartnerToggle3=false;
					isPartnerToggle4=false;
					isPartnerToggle5=false;
					partnerString = partnerToggle1.getText();
				} else if (e.getSource() == partnerToggle2) {
					isPartnerToggle1=false;
					isPartnerToggle2=true;
					isPartnerToggle3=false;
					isPartnerToggle4=false;
					isPartnerToggle5=false;
					partnerString = partnerToggle2.getText();
				} else if (e.getSource() == partnerToggle3) {
					isPartnerToggle1=false;
					isPartnerToggle2=false;
					isPartnerToggle3=true;
					isPartnerToggle4=false;
					isPartnerToggle5=false;
					partnerString = partnerToggle3.getText();
				} else if (e.getSource() == partnerToggle4) {
					isPartnerToggle1=false;
					isPartnerToggle2=false;
					isPartnerToggle3=false;
					isPartnerToggle4=true;
					isPartnerToggle5=false;
					partnerString = partnerToggle4.getText();
				} else if (e.getSource() == partnerToggle5) {
					isPartnerToggle1=false;
					isPartnerToggle2=false;
					isPartnerToggle3=false;
					isPartnerToggle4=false;
					isPartnerToggle5=true;
					partnerString = partnerToggle5.getText();
				} 
		    	   if (isPartnerToggle1) {
	    	    	   ComponentHelper.changeToSwitchOnState(partnerToggle1, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(partnerToggle1, null, Color.BLUE);
		    	   if (isPartnerToggle2) {
	    	    	   ComponentHelper.changeToSwitchOnState(partnerToggle2, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(partnerToggle2, null, Color.BLUE);
		    	   if (isPartnerToggle3) {
	    	    	   ComponentHelper.changeToSwitchOnState(partnerToggle3, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(partnerToggle3, null, Color.BLUE);
		    	   if (isPartnerToggle4) {
	    	    	   ComponentHelper.changeToSwitchOnState(partnerToggle4, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(partnerToggle4, null, Color.BLUE);
		    	   if (isPartnerToggle5) {
	    	    	   ComponentHelper.changeToSwitchOnState(partnerToggle5, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(partnerToggle5, null, Color.BLUE);
	
		    	   if (!ComponentHelper.doesListContainJustOne(jltNode)) {
		    			nodeString = "Multiple"; 			    	
		    		} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
	
	
		        valueSet = null;
				nString = nodeString;

		        if (optionTrigger.equals("TBM/VBM")) {
			    	valueSet = ComponentHelper.getTBMPartnerCustomerMetricsYTD(jltNode, nodeString, tbmString, 
			    			partnerString, finYear, finQuarter, finMonth, false);
			    	topTBM = tbmString;
			    	topPartner = partnerString;
			    	topCustomer = null;
		        } else if (optionTrigger.equals("Partner")) {
			    	valueSet = ComponentHelper.getPartnerCustomerMetricsYTD(jltNode, nodeString, 
			    			partnerString, finYear, finQuarter, finMonth, false);
			    	topTBM = null;
			    	topPartner = partnerString;
			    	topCustomer = null;
		        }
		    	valueEntry=null;
		    	valueEntry = valueSet.get(0);
	
				currentYearPanel.remove(centerChartPanel);
			    currentYearPanel.remove(disAllChartPanel);
			    currentYearPanel.remove(disENTNWChartPanel);
			    currentYearPanel.remove(disSecChartPanel);
			    currentYearPanel.remove(disCollChartPanel);
			    currentYearPanel.remove(disDCVChartPanel);
			    currentYearPanel.remove(penCusChartPanel);
			    currentYearPanel.remove(techPenHolder);
			    currentYearPanel.remove(atAttachHolder);
			    currentYearPanel.remove(techSplitHolder);
			    currentYearPanel.remove(archSplitHolder);
			    currentYearPanel.remove(month_weekSplitHolder);
			    currentYearPanel.remove(quarterSplitHolder);
			    currentYearPanel.remove(halfYearSplitHolder);
			    if (isCompareYearAttempted) {
			    	compoPanel.remove(compareYearPanel);
			    	isCompareYearAttempted = false;
			    }
				if (isTopPartnerAttempted) {
				    topCustomerHolder.removeAll();
				    sidePanel.remove(topCustomerHolder);
		    	}
				top = (List<TopNames>) valueEntry.getTopData();
		    	Collections.sort(top, new TopNames());
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
		        if (optionTrigger.equals("TBM/VBM")) {
					setToppersCustomer(top, 335);
		        } else if (optionTrigger.equals("Partner")) {
					setToppersCustomer(top, 170);
		        }
				setWhatIfController();
				getContentPane().revalidate();
		    	getContentPane().repaint();
		    	isTopPartnerAttempted = true;
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
			    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}


	private class TopCustomerActionListener implements ActionListener {
		
		private String nodeString, tbmString, partnerString, customerString;
		private List<TopNames> top;
		public void actionPerformed(ActionEvent e) {
			try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		        if (optionTrigger.equals("TBM/VBM")) {
					tbmString = ComponentHelper.getSelectedTopNames(tbmToggle1, 
							tbmToggle2, tbmToggle3, tbmToggle4, tbmToggle5);
		        }
		        if (optionTrigger.equals("TBM/VBM") || optionTrigger.equals("Partner")) {
					partnerString = ComponentHelper.getSelectedTopNames(partnerToggle1, 
							partnerToggle2, partnerToggle3, partnerToggle4, partnerToggle5);
		        }
				if (e.getSource() == customerToggle1) {
					isCustomerToggle1=true;
					isCustomerToggle2=false;
					isCustomerToggle3=false;
					isCustomerToggle4=false;
					isCustomerToggle5=false;
					customerString = customerToggle1.getText();
				} else if (e.getSource().equals(customerToggle2)) {
					isCustomerToggle1=false;
					isCustomerToggle2=true;
					isCustomerToggle3=false;
					isCustomerToggle4=false;
					isCustomerToggle5=false;
					customerString = customerToggle2.getText();
				} else if (e.getSource() == customerToggle3) {
					isCustomerToggle1=false;
					isCustomerToggle2=false;
					isCustomerToggle3=true;
					isCustomerToggle4=false;
					isCustomerToggle5=false;
					customerString = customerToggle3.getText();
				} else if (e.getSource() == customerToggle4) {
					isCustomerToggle1=false;
					isCustomerToggle2=false;
					isCustomerToggle3=false;
					isCustomerToggle4=true;
					isCustomerToggle5=false;
					customerString = customerToggle4.getText();
				} else if (e.getSource() == customerToggle5) {
					isCustomerToggle1=false;
					isCustomerToggle2=false;
					isCustomerToggle3=false;
					isCustomerToggle4=false;
					isCustomerToggle5=true;
					customerString = customerToggle5.getText();
				} 
		    	   if (isCustomerToggle1) {
	    	    	   ComponentHelper.changeToSwitchOnState(customerToggle1, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(customerToggle1, null, Color.BLUE);
		    	   if (isCustomerToggle2) {
	    	    	   ComponentHelper.changeToSwitchOnState(customerToggle2, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(customerToggle2, null, Color.BLUE);
		    	   if (isCustomerToggle3) {
	    	    	   ComponentHelper.changeToSwitchOnState(customerToggle3, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(customerToggle3, null, Color.BLUE);
		    	   if (isCustomerToggle4) {
	    	    	   ComponentHelper.changeToSwitchOnState(customerToggle4, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(customerToggle4, null, Color.BLUE);
		    	   if (isCustomerToggle5) {
	    	    	   ComponentHelper.changeToSwitchOnState(customerToggle5, Color.BLACK, Color.RED.darker());
		    	   } else ComponentHelper.changeToSwitchOffState(customerToggle5, null, Color.BLUE);
	
		    	   if (!ComponentHelper.doesListContainJustOne(jltNode)) {
		    			nodeString = "Multiple"; 			    	
		    		} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
	
	
		        valueSet = null;
				nString = nodeString;
		        if (optionTrigger.equals("TBM/VBM")) {
			    	valueSet = ComponentHelper.getTBMCustomerMetricsYTD(jltNode, nodeString, tbmString, 
			    			partnerString, customerString, finYear, finQuarter, finMonth, false);
			    	topTBM = tbmString;
			    	topPartner = partnerString;
			    	topCustomer = customerString;
		        } else if (optionTrigger.equals("Partner")) {
			    	valueSet = ComponentHelper.getTopPartnerCustomerMetricsYTD(jltNode, nodeString,  
			    			partnerString, customerString, finYear, finQuarter, finMonth, false);
			    	topTBM = null;
			    	topPartner = partnerString;
			    	topCustomer = customerString;
		        }  else if (optionTrigger.equals("Customer")) {
			    	valueSet = ComponentHelper.getTopCustomerMetricsYTD(jltNode, nodeString,  
			    			customerString, finYear, finQuarter, finMonth, false);
			    	topTBM = null;
			    	topPartner = null;
			    	topCustomer = customerString;
		        }
		        
		    	valueEntry=null;
		    	valueEntry = valueSet.get(0);
	
				currentYearPanel.remove(centerChartPanel);
			    currentYearPanel.remove(disAllChartPanel);
			    currentYearPanel.remove(disENTNWChartPanel);
			    currentYearPanel.remove(disSecChartPanel);
			    currentYearPanel.remove(disCollChartPanel);
			    currentYearPanel.remove(disDCVChartPanel);
			    currentYearPanel.remove(penCusChartPanel);
			    currentYearPanel.remove(techPenHolder);
			    currentYearPanel.remove(atAttachHolder);
			    currentYearPanel.remove(techSplitHolder);
			    currentYearPanel.remove(archSplitHolder);
			    currentYearPanel.remove(month_weekSplitHolder);
			    currentYearPanel.remove(quarterSplitHolder);
			    currentYearPanel.remove(halfYearSplitHolder);
			    if (isCompareYearAttempted) {
			    	compoPanel.remove(compareYearPanel);
			    	isCompareYearAttempted = false;
			    }
				top = (List<TopNames>) valueEntry.getTopData();
		    	Collections.sort(top, new TopNames());
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
				setWhatIfController();
				getContentPane().revalidate();
		    	getContentPane().repaint();
		    	isTopCustomerAttempted = true;
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

	
	private class assumptionButtonToggleListener implements ItemListener {
	    @SuppressWarnings({ "unchecked", "rawtypes" })
        ImageIcon imgOn, imgOff, imgOn2, imgOff2;
	    String nodeString, nameString;
		@Override
	    public void itemStateChanged(ItemEvent event) {
			try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

	    	   imgOn = new ImageIcon(this.getClass().
	              		getResource(GeneralConstants.RED_LIGHT_IMAGE));
	    	   imgOff = new ImageIcon(this.getClass().
	              		getResource(GeneralConstants.RED_LIGHT_OFF_IMAGE));
	    	   imgOn2 = new ImageIcon(this.getClass().
	              		getResource(GeneralConstants.GREEN_LIGHT_IMAGE));
	    	   imgOff2 = new ImageIcon(this.getClass().
	              		getResource(GeneralConstants.GREEN_LIGHT_OFF_IMAGE));
	    	   JLabel label, titleLabel;
	    	   if (event.getStateChange() == ItemEvent.SELECTED) {
	    		   ComponentHelper.refreshWhenAssumptionOn(switchPanel, 
	    				   jbYTD, jbQ1, jbQ2, jbQ3, jbQ4, jbH1, jbH2);
		    	   isAnalysis=true;
	    		   jbAnalysis.setForeground(Color.BLACK);
    	    	   label = ComponentHelper.getControlLight(imgOn, 110, 0, 35, 35);
    	    	   titleLabel = ComponentHelper.getControlLightLabel("?", 120, 0, 20, 10);
    	    	   if (isYTD) {
        	    	   jlYTD = ComponentHelper.getControlLight(imgOn2, 370, 5, 35, 35);
    	    	   } else {
        	    	   jlYTD = ComponentHelper.getControlLight(imgOff2, 370, 5, 35, 35);
    	    	   }
    	    	   if (isH1) {
        	    	   jlH1 = ComponentHelper.getControlLight(imgOn2, 410, 5, 35, 35);
    	    	   } else {
        	    	   jlH1 = ComponentHelper.getControlLight(imgOff2, 410, 5, 35, 35);
    	    	   }
    	    	   if (isH2) {
        	    	   jlH2 = ComponentHelper.getControlLight(imgOn2, 450, 5, 35, 35);
    	    	   } else {
        	    	   jlH2 = ComponentHelper.getControlLight(imgOff2, 450, 5, 35, 35);
    	    	   }
    	    	   if (isQ1) {
        	    	   jlQ1 = ComponentHelper.getControlLight(imgOn2, 360, 45, 35, 35);
    	    	   } else {
        	    	   jlQ1 = ComponentHelper.getControlLight(imgOff2, 360, 45, 35, 35);
    	    	   }
    	    	   if (isQ2) {
        	    	   jlQ2 = ComponentHelper.getControlLight(imgOn2, 400, 45, 35, 35);
    	    	   } else {
        	    	   jlQ2 = ComponentHelper.getControlLight(imgOff2, 400, 45, 35, 35);
    	    	   }
    	    	   if (isQ3) {
        	    	   jlQ3 = ComponentHelper.getControlLight(imgOn2, 440, 45, 35, 35);
    	    	   } else {
        	    	   jlQ3 = ComponentHelper.getControlLight(imgOff2, 440, 45, 35, 35);
    	    	   }
    	    	   if (isQ4) {
        	    	   jlQ4 = ComponentHelper.getControlLight(imgOn2, 480, 45, 35, 35);
    	    	   } else {
        	    	   jlQ4 = ComponentHelper.getControlLight(imgOff2, 480, 45, 35, 35);
    	    	   }
    	    	   ComponentHelper.refreshSwitchPanel(currentYearPanel, switchPanel, jbYTD, jbH1, 
    	    			   jbH2, jbQ1, jbQ2, jbQ3, jbQ4, jbAnalysis, jbAbs, titleLabel, label,
    	    			   jlH1, jlTitleH1, jlH2, jlTitleH2, jlQ1, jlTitleQ1, jlQ2, jlTitleQ2, 
    	    			   jlQ3, jlTitleQ3, jlQ4, jlTitleQ4, jlYTD, jlTitleYTD);
    	    	   
	 		    jcbxAnalysis = new JComboBox();
	 		    jcbxAnalysis.setMaximumRowCount(2);
	 		    jcbxAnalysis.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
	 		    jcbxAnalysis.setPreferredSize(new Dimension(100,25));
	 		    jcbxAnalysis.addItem("What If Analysis");
	 		    jcbxAnalysis.addItem("Compare with preferred Year");
	 		    jcbxAnalysis.setSelectedIndex(-1);

	 		    int resultDialog = JOptionPane.showConfirmDialog(null, jcbxAnalysis,"Select the Type of Analysis", 
 					   JOptionPane.OK_CANCEL_OPTION);
 			   
		    	String analysisType = (String)jcbxAnalysis.getSelectedItem();

 	    	   
	 	    	   if (resultDialog == JOptionPane.OK_OPTION) {
	 				   if (analysisType.equals("What If Analysis")) {
							setWhatIfController();
	 		   		        currentYearPanel.add(jsDisAll);
	 		   		        currentYearPanel.add(jsDisENTNW);
	 		   		        currentYearPanel.add(jsDisSec);
	 		   		        currentYearPanel.add(jsDisColl);
	 		   		        currentYearPanel.add(jsDisDCV);
	 		   		        currentYearPanel.add(jsCusPen);
	 		   		        currentYearPanel.add(jsTechPen);
	 		   		        currentYearPanel.add(jlDisAll);
	 		   		        currentYearPanel.add(jlDisENTNW);
	 		   		        currentYearPanel.add(jlDisSec);
	 		   		        currentYearPanel.add(jlDisColl);
	 		   		        currentYearPanel.add(jlDisDCV);
	 		   		        currentYearPanel.add(jlCusPen);
	 		   		        currentYearPanel.add(jlTechPen);
	 		    	   	    jsDisAll.setEnabled(true);
	 		    	        jsDisENTNW.setEnabled(true);
	 		    	        jsDisSec.setEnabled(true);
	 		    	        jsDisColl.setEnabled(true);
	 		    	        jsDisDCV.setEnabled(true);
	 		    	        jsCusPen.setEnabled(true);
	 		    	        jsTechPen.setEnabled(true);
			    		    jlOption.setEnabled(false);
			    		    jcbxOption.setEnabled(false);
			    		    jlYear.setEnabled(false);
			    		    jcbxYear.setEnabled(false);
			    		    jlEU.setEnabled(false);
			    		    jcbxEU.setEnabled(false);
			    		    subSCMSPanel.setEnabled(false);
			    		    jrbPL.setEnabled(false);
			    		    jrbPLV.setEnabled(false);
			    		    jrbAll.setEnabled(false);
			    		    jlRegion.setEnabled(false);
			    		    jltRegion.setEnabled(false);
			    		    regionScrollPane.setEnabled(false);
			    		    jlNode.setEnabled(false);
			    		    jltNode.setEnabled(false);
			    		    nodeScrollPane.setEnabled(false);
			    		    jbShowSalesAgent.setEnabled(false);
			    		    jlName.setEnabled(false);
			    		    jltName.setEnabled(false);
			    		    NameScrollPane.setEnabled(false);
	 		    	        
					    	whatIfEntry = new DashboardGeneralWhatIf(valueEntry, 0D);
							currentYearPanel.remove(centerChartPanel);
						    currentYearPanel.remove(disAllChartPanel);
						    currentYearPanel.remove(disENTNWChartPanel);
						    currentYearPanel.remove(disSecChartPanel);
						    currentYearPanel.remove(disCollChartPanel);
						    currentYearPanel.remove(disDCVChartPanel);
						    currentYearPanel.remove(penCusChartPanel);
						    currentYearPanel.remove(techPenHolder);
						    currentYearPanel.remove(atAttachHolder);
						    currentYearPanel.remove(techSplitHolder);
						    currentYearPanel.remove(archSplitHolder);
						    currentYearPanel.remove(month_weekSplitHolder);
						    currentYearPanel.remove(quarterSplitHolder);
						    currentYearPanel.remove(halfYearSplitHolder);
						    if (isCompareYearAttempted) {
						    	compoPanel.remove(compareYearPanel);
						    	isCompareYearAttempted = false;
						    }
					    	setCenterPlot(whatIfEntry.getBookingAll(), whatIfEntry.getYldPerCus());
					    	setDiscountPlots(whatIfEntry.getDiscountAll()*100D, whatIfEntry.getDiscountCollab()*100D, 
					    			whatIfEntry.getDiscountENTNW()*100D, whatIfEntry.getDiscountSecurity()*100D, whatIfEntry.getDiscountDCV()*100D, 
					    			whatIfEntry.getCusPen(), CalcHelper.round(
					    			whatIfEntry.getTechPen(), 2, BigDecimal.ROUND_HALF_UP), 
					    			whatIfEntry.getATAttach(), 
					    			whatIfEntry.getNonATAttach());		
							setOtherSplitPlots(whatIfEntry.getBookingENTNW(), whatIfEntry.getBookingSecurity(), 
									whatIfEntry.getBookingCollab(), whatIfEntry.getBookingDCV(),	whatIfEntry.getM1(), 
									whatIfEntry.getM2(), whatIfEntry.getM3(), whatIfEntry.getM4(), whatIfEntry.getM5(), 
									whatIfEntry.getM6(), whatIfEntry.getM7(), whatIfEntry.getM8(), whatIfEntry.getM9(), 
									whatIfEntry.getM10(), whatIfEntry.getM11(), whatIfEntry.getM12(), whatIfEntry.getQ1(), 
									whatIfEntry.getQ2(), whatIfEntry.getQ3(),	whatIfEntry.getQ4(), whatIfEntry.getANS(), 
									whatIfEntry.getDCSwitching(), whatIfEntry.getOthers(), whatIfEntry.getLANSwitching(), 
									whatIfEntry.getRouting(), whatIfEntry.getSecurity(), whatIfEntry.getStorage(), 
									whatIfEntry.getUC(), whatIfEntry.getUCS(), whatIfEntry.getVideo(), 
									whatIfEntry.getWirelessLAN(), whatIfEntry.getH1(), whatIfEntry.getH2(),
									whatIfEntry.getM1W1(), whatIfEntry.getM1W2(), whatIfEntry.getM1W3(), whatIfEntry.getM1W4(), 
									whatIfEntry.getM2W1(), whatIfEntry.getM2W2(), whatIfEntry.getM2W3(), whatIfEntry.getM2W4(),
									whatIfEntry.getM3W1(), whatIfEntry.getM3W2(), whatIfEntry.getM3W3(), whatIfEntry.getM3W4(), whatIfEntry.getM3W5(),
									whatIfEntry.getM4W1(), whatIfEntry.getM4W2(), whatIfEntry.getM4W3(), whatIfEntry.getM4W4(), 
									whatIfEntry.getM5W1(), whatIfEntry.getM5W2(), whatIfEntry.getM5W3(), whatIfEntry.getM5W4(),
									whatIfEntry.getM6W1(), whatIfEntry.getM6W2(), whatIfEntry.getM6W3(), whatIfEntry.getM6W4(), whatIfEntry.getM6W5(),
									whatIfEntry.getM7W1(), whatIfEntry.getM7W2(), whatIfEntry.getM7W3(), whatIfEntry.getM7W4(), 
									whatIfEntry.getM8W1(), whatIfEntry.getM8W2(), whatIfEntry.getM8W3(), whatIfEntry.getM8W4(),
									whatIfEntry.getM9W1(), whatIfEntry.getM9W2(), whatIfEntry.getM9W3(), whatIfEntry.getM9W4(), whatIfEntry.getM9W5(),
									whatIfEntry.getM10W1(), whatIfEntry.getM10W2(), whatIfEntry.getM10W3(), whatIfEntry.getM10W4(), 
									whatIfEntry.getM11W1(), whatIfEntry.getM11W2(), whatIfEntry.getM11W3(), whatIfEntry.getM11W4(),
									whatIfEntry.getM12W1(), whatIfEntry.getM12W2(), whatIfEntry.getM12W3(), whatIfEntry.getM12W4(), whatIfEntry.getM12W5());
							getContentPane().revalidate();
					    	getContentPane().repaint();
				
	 				   } else {
	 			    	    String tempYear = (String)jcbxYear.getSelectedItem();
	 					    jcbxYearCompare= ComponentHelper.fillCompareYearComboBox(tempYear);
	 					    jcbxYearCompare.setMaximumRowCount(4);
	 					    jcbxYearCompare.setSelectedIndex(-1);
	 					    jcbxYearCompare.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
	 					    jcbxYearCompare.setPreferredSize(new Dimension(50,25));
	 					    
	 			 		    int resultDialog2 = JOptionPane.showConfirmDialog(null, jcbxYearCompare,"Select Fin Year to compare with", 
	 		 					   JOptionPane.OK_CANCEL_OPTION);
		 		 	    	   if (resultDialog2 == JOptionPane.OK_OPTION) {
		 			    		    jlOption.setEnabled(false);
		 			    		    jcbxOption.setEnabled(false);
		 			    		    jlYear.setEnabled(false);
		 			    		    jcbxYear.setEnabled(false);
		 			    		    jlEU.setEnabled(false);
		 			    		    jcbxEU.setEnabled(false);
		 			    		    subSCMSPanel.setEnabled(false);
		 			    		    jrbPL.setEnabled(false);
		 			    		    jrbPLV.setEnabled(false);
		 			    		    jrbAll.setEnabled(false);
		 			    		    jlRegion.setEnabled(false);
		 			    		    jltRegion.setEnabled(false);
		 			    		    regionScrollPane.setEnabled(false);
		 			    		    jlNode.setEnabled(false);
		 			    		    jltNode.setEnabled(false);
		 			    		    nodeScrollPane.setEnabled(false);
		 			    		    jbShowSalesAgent.setEnabled(false);
		 			    		    jlName.setEnabled(false);
		 			    		    jltName.setEnabled(false);
		 			    		    NameScrollPane.setEnabled(false);
		 		 	    		   	DashboardGeneralSupport dgs = null;
			 			    	    String yearOverride = (String)jcbxYearCompare.getSelectedItem();
			 			    	    yearOverride = yearOverride.substring(yearOverride.indexOf("FY")+2);
		 		 	    		   if(topTBM == null && topPartner == null && topCustomer == null) {
//		 		 	    			   JOptionPane.showMessageDialog(null, "No Toppers");
		 		 	    				if (!ComponentHelper.doesListContainJustOne(jltNode)) {
		 		 	    					nodeString = "Multiple"; 			    	
		 		 	    				} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
		 		 	    		
		 		 	    				if (!ComponentHelper.doesListContainJustOne(jltName)) {
		 		 	    					nameString = "Multiple"; 			    	
		 		 	    				} else nameString=ComponentHelper.getSelectedListItem(jltName);

		 		 	    				if (optionTrigger.equals("TBM/VBM")) {
		 		 	    					valueSet = ComponentHelper.getTBMMetricsYTD(jltName, jltNode, nodeString, nameString, 
		 		 	    							yearOverride, finQuarter, finMonth, true);
		 		 	    					} else if (optionTrigger.equals("Partner")) {
		 		 	    						valueSet = ComponentHelper.getPartnerMetricsYTD(jltName, jltNode, nodeString, nameString, 
		 		 	    								yearOverride, finQuarter, finMonth, true);
		 		 	    					} else if (optionTrigger.equals("Customer")) {
		 		 	    						valueSet = ComponentHelper.getCustomerMetricsYTD(jltName, jltNode, nodeString, nameString, 
		 		 	    								yearOverride, finQuarter, finMonth, true);
		 		 	    					}
		 		 	    				dgs = new DashboardGeneralSupport(optionTrigger, valueSet, 
		 		 	    						yearOverride, finQuarter, finMonth, isAbs);
		 		 	    		   } else if(topTBM == null && topPartner != null && topCustomer != null) {
//		 		 	    			   JOptionPane.showMessageDialog(null, "Top Partner & Top Customer");
		 		 	    				if (!ComponentHelper.doesListContainJustOne(jltNode)) {
		 		 	    					nodeString = "Multiple"; 			    	
		 		 	    				} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
	 		 				    	valueSet = ComponentHelper.getTopPartnerCustomerMetricsYTD(jltNode, nodeString,  
	 		 				    			topPartner, topCustomer, yearOverride, finQuarter, finMonth, true);
	 		 				    		dgs = new DashboardGeneralSupport(optionTrigger, valueSet, 
	 		 	    						yearOverride, finQuarter, finMonth, isAbs);
		 		 	    		   } else if(topTBM != null && topPartner != null && topCustomer != null) {
//		 		 	    			   JOptionPane.showMessageDialog(null, "Top TBM, Top Partner & Top Customer");
		 		 	    				if (!ComponentHelper.doesListContainJustOne(jltNode)) {
		 		 	    					nodeString = "Multiple"; 			    	
		 		 	    				} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
		 		 				    	valueSet = ComponentHelper.getTBMCustomerMetricsYTD(jltNode, nodeString, topTBM, 
		 		 				    			topPartner, topCustomer, yearOverride, finQuarter, finMonth, true);
		 		 				    	dgs = new DashboardGeneralSupport(optionTrigger, valueSet, 
	 		 	    						yearOverride, finQuarter, finMonth, isAbs);
		 		 	    		   } else if(topTBM == null && topPartner == null && topCustomer != null) {
//		 		 	    			   JOptionPane.showMessageDialog(null, "only Top Customer");
		 		 	    				if (!ComponentHelper.doesListContainJustOne(jltNode)) {
		 		 	    					nodeString = "Multiple"; 			    	
		 		 	    				} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
		 		 				    	valueSet = ComponentHelper.getTopCustomerMetricsYTD(jltNode, nodeString,  
		 		 				    			topCustomer, yearOverride, finQuarter, finMonth, true);
		 		 				    	dgs = new DashboardGeneralSupport(optionTrigger, valueSet, 
	 		 	    						yearOverride, finQuarter, finMonth, isAbs);
		 		 	    		   } else if(topTBM == null && topPartner != null && topCustomer == null) {
//		 		 	    			   JOptionPane.showMessageDialog(null, "only Top Partner");
		 		 	    				if (!ComponentHelper.doesListContainJustOne(jltNode)) {
		 		 	    					nodeString = "Multiple"; 			    	
		 		 	    				} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
		 		 				    	valueSet = ComponentHelper.getPartnerCustomerMetricsYTD(jltNode, nodeString, 
		 		 				    			topPartner, yearOverride, finQuarter, finMonth, true);
		 		 				    	dgs = new DashboardGeneralSupport(optionTrigger, valueSet, 
	 		 	    						yearOverride, finQuarter, finMonth, isAbs);
		 		 	    		   } else if(topTBM != null && topPartner == null && topCustomer == null) {
//		 		 	    			   JOptionPane.showMessageDialog(null, "only Top TBMs");
		 		 	    				if (!ComponentHelper.doesListContainJustOne(jltNode)) {
		 		 	    					nodeString = "Multiple"; 			    	
		 		 	    				} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
		 		 	  		    	valueSet = ComponentHelper.getTBMPartnerMetricsYTD(jltNode, nodeString, topTBM, 
		 		 	  		    		yearOverride, finQuarter, finMonth, true);
		 		 				    	dgs = new DashboardGeneralSupport(optionTrigger, valueSet, 
	 		 	    						yearOverride, finQuarter, finMonth, isAbs);
		 		 	    		   }  else if(topTBM != null && topPartner != null && topCustomer == null) {
//		 		 	    			   JOptionPane.showMessageDialog(null, "Top TBMs & Top Partner");
		 		 	    				if (!ComponentHelper.doesListContainJustOne(jltNode)) {
		 		 	    					nodeString = "Multiple"; 			    	
		 		 	    				} else nodeString=ComponentHelper.getSelectedListItem(jltNode);
		 		 				    	valueSet = ComponentHelper.getTBMPartnerCustomerMetricsYTD(jltNode, nodeString, topTBM, 
		 		 				    			topPartner, yearOverride, finQuarter, finMonth, true);
		 		 				    	dgs = new DashboardGeneralSupport(optionTrigger, valueSet, 
	 		 	    						yearOverride, finQuarter, finMonth, isAbs);
		 		 	    		   }
		 		 	    		   
	 		 	    				field = dgs.getCompareYearPanel();
	 		 	    				compareYearPanel = field.getPanel();
	 		 	    				compareYearPanel.setBackground(new Color(75, 75, 250));
	 		 	    				compareYearPanel.setBounds(375, 530, 955, 520);
	 		 	    				compareYearPanel.setFont(new Font(fontFamily,Font.BOLD,fontSize));
	 		 	    				compareYearPanel.setLayout(null);
	 		 	    		        compoPanel.add(compareYearPanel);
	 		 	    				isCompareYearAttempted = field.isCompareYearAttempted();

		 		 	    	   }  else {
		 			    		   ComponentHelper.refreshWhenAssumptionOff(switchPanel, 
		 			    				   jbYTD, jbQ1, jbQ2, jbQ3, jbQ4, jbH1, jbH2);
		 		 	    		   JOptionPane.showMessageDialog(null, 
			 		 					   "Oops! You are not interested to explore YtoY analysis!");
		 		 	    	   }
	 				   }
	 			   } else {
		    		   ComponentHelper.refreshWhenAssumptionOff(switchPanel, 
		    				   jbYTD, jbQ1, jbQ2, jbQ3, jbQ4, jbH1, jbH2);
	 				   JOptionPane.showMessageDialog(null, 
		 					   "Oops! You are not interested to explore the analysis option!");
	 			   }
    	        
    	        
    	        
	    	   } else {
	    		   ComponentHelper.refreshWhenAssumptionOff(switchPanel, 
	    				   jbYTD, jbQ1, jbQ2, jbQ3, jbQ4, jbH1, jbH2);

			   		    if (isCompareYearAttempted) {
					    	compoPanel.remove(compareYearPanel);
					    	isCompareYearAttempted = false;
					    }
	    		    jlOption.setEnabled(true);
	    		    jcbxOption.setEnabled(true);
	    		    jlYear.setEnabled(true);
	    		    jcbxYear.setEnabled(true);
	    		    jlEU.setEnabled(true);
	    		    jcbxEU.setEnabled(true);
	    		    subSCMSPanel.setEnabled(true);
	    		    jrbPL.setEnabled(true);
	    		    jrbPLV.setEnabled(true);
	    		    jrbAll.setEnabled(true);
	    		    jlRegion.setEnabled(true);
	    		    jltRegion.setEnabled(true);
	    		    regionScrollPane.setEnabled(true);
	    		    jlNode.setEnabled(true);
	    		    jltNode.setEnabled(true);
	    		    jbShowSalesAgent.setEnabled(true);
	    		    nodeScrollPane.setEnabled(true);
	    		    jlName.setEnabled(true);
	    		    jltName.setEnabled(true);
	    		    NameScrollPane.setEnabled(true);
	    		   isAnalysis=false;
	    		   jbAnalysis.setForeground(Color.WHITE);
    	    	   label = ComponentHelper.getControlLight(imgOff, 110, 0, 35, 35);
    	    	   titleLabel = ComponentHelper.getControlLightLabel("?", 120, 0, 20, 10);
    	    	   if (isYTD) {
        	    	   jlYTD = ComponentHelper.getControlLight(imgOn2, 370, 5, 35, 35);
    	    	   } else {
        	    	   jlYTD = ComponentHelper.getControlLight(imgOff2, 370, 5, 35, 35);
    	    	   }
    	    	   if (isH1) {
        	    	   jlH1 = ComponentHelper.getControlLight(imgOn2, 410, 5, 35, 35);
    	    	   } else {
        	    	   jlH1 = ComponentHelper.getControlLight(imgOff2, 410, 5, 35, 35);
    	    	   }
    	    	   if (isH2) {
        	    	   jlH2 = ComponentHelper.getControlLight(imgOn2, 450, 5, 35, 35);
    	    	   } else {
        	    	   jlH2 = ComponentHelper.getControlLight(imgOff2, 450, 5, 35, 35);
    	    	   }
    	    	   if (isQ1) {
        	    	   jlQ1 = ComponentHelper.getControlLight(imgOn2, 360, 45, 35, 35);
    	    	   } else {
        	    	   jlQ1 = ComponentHelper.getControlLight(imgOff2, 360, 45, 35, 35);
    	    	   }
    	    	   if (isQ2) {
        	    	   jlQ2 = ComponentHelper.getControlLight(imgOn2, 400, 45, 35, 35);
    	    	   } else {
        	    	   jlQ2 = ComponentHelper.getControlLight(imgOff2, 400, 45, 35, 35);
    	    	   }
    	    	   if (isQ3) {
        	    	   jlQ3 = ComponentHelper.getControlLight(imgOn2, 440, 45, 35, 35);
    	    	   } else {
        	    	   jlQ3 = ComponentHelper.getControlLight(imgOff2, 440, 45, 35, 35);
    	    	   }
    	    	   if (isQ4) {
        	    	   jlQ4 = ComponentHelper.getControlLight(imgOn2, 480, 45, 35, 35);
    	    	   } else {
        	    	   jlQ4 = ComponentHelper.getControlLight(imgOff2, 480, 45, 35, 35);
    	    	   }
    	    	   ComponentHelper.refreshSwitchPanel(currentYearPanel, switchPanel, jbYTD, jbH1, 
    	    			   jbH2, jbQ1, jbQ2, jbQ3, jbQ4, jbAnalysis, jbAbs, titleLabel, label,
    	    			   jlH1, jlTitleH1, jlH2, jlTitleH2, jlQ1, jlTitleQ1, jlQ2, jlTitleQ2, 
    	    			   jlQ3, jlTitleQ3, jlQ4, jlTitleQ4, jlYTD, jlTitleYTD);


       	    	jsDisAll.setEnabled(false);
       	        jsDisENTNW.setEnabled(false);
       	        jsDisSec.setEnabled(false);
       	        jsDisColl.setEnabled(false);
       	        jsDisDCV.setEnabled(false);
       	        jsCusPen.setEnabled(false);
       	        jsTechPen.setEnabled(false);

    	    	currentYearPanel.remove(jsDisAll);
   		        currentYearPanel.remove(jsDisENTNW);
   		        currentYearPanel.remove(jsDisSec);
   		        currentYearPanel.remove(jsDisColl);
   		        currentYearPanel.remove(jsDisDCV);
   		        currentYearPanel.remove(jsCusPen);
   		        currentYearPanel.remove(jsTechPen);
   		        currentYearPanel.remove(jlDisAll);
   		        currentYearPanel.remove(jlDisENTNW);
   		        currentYearPanel.remove(jlDisSec);
   		        currentYearPanel.remove(jlDisColl);
   		        currentYearPanel.remove(jlDisDCV);
   		        currentYearPanel.remove(jlCusPen);
   		        currentYearPanel.remove(jlTechPen);
    	    	   

       	        
       	        
	    	   }
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
	    }

	 }


	
	
	
	private class absToggleListener implements ItemListener {
	    @SuppressWarnings({ "unchecked", "rawtypes" })
        ImageIcon imgOn, imgOff, imgOn2, imgOff2;
		@Override
	    public void itemStateChanged(ItemEvent event) {
			try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    	   if (event.getStateChange() == ItemEvent.SELECTED) {
	    		   jbAbs.setText("Abs");
	    		   jbAbs.setForeground(Color.BLACK);
	    		   isAbs = true;
	    	   } else {
	    		   jbAbs.setText("%");
	    		   jbAbs.setForeground(Color.WHITE);
	    		   isAbs = false;
	    	   }
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
	    }

	 }

	
	private class overrideButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					if (e.getSource() == jbOverride) {
			    	   	refreshScreenforYTD();
						getContentPane().revalidate();
				    	getContentPane().repaint();
					}

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

		}
	}

	private class periodButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			try {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//		    	currentYearPanel.remove(jbOverride);
		    	jbOverride.setEnabled(true);
//		    	currentYearPanel.add(jbOverride);
				getContentPane().revalidate();
		    	getContentPane().repaint();
				if (e.getSource() == jbLeft) {
					   needleWhereTo--;
					if (isYTD && needleWhereTo <0) {
						needleWhereTo = 3D;
					} else if ((isH1 || isH2)&& needleWhereTo <0) {
						needleWhereTo = 1D;
					} else if ((isQ1 || isQ2 || isQ3 || isQ4)&& needleWhereTo <0) {
						needleWhereTo = 2D;
					}
				} else {
					   needleWhereTo++;
					if (isYTD && needleWhereTo >3) {
						needleWhereTo = 0D;
					} else if ((isH1 || isH2)&& needleWhereTo >1) {
						needleWhereTo = 0D;
					} else if ((isQ1 || isQ2 || isQ3 || isQ4)&& needleWhereTo >2) {
						needleWhereTo = 0D;
					}
				}
				
				if (isYTD && needleWhereTo == 0D) {
					finQuarter = "Q1";
				} else if (isYTD && needleWhereTo == 1D) {
					finQuarter = "Q2";
				} else if (isYTD && needleWhereTo == 2D) {
					finQuarter = "Q3";
				} else if (isYTD && needleWhereTo == 3D) {
					finQuarter = "Q4";
				} else if (isH1 && needleWhereTo == 0D) {
					finQuarter = "Q1";
				} else if (isH1 && needleWhereTo == 1D) {
					finQuarter = "Q2";
				} else if (isH2 && needleWhereTo == 0D) {
					finQuarter = "Q3";
				} else if (isH2 && needleWhereTo == 1D) {
					finQuarter = "Q4";
				} else if (isQ1 && needleWhereTo == 0D) {
					finMonth = "01";
				} else if (isQ1 && needleWhereTo == 1D) {
					finMonth = "02";
				} else if (isQ1 && needleWhereTo == 2D) {
					finMonth = "03";
				} else if (isQ2 && needleWhereTo == 0D) {
					finMonth = "04";
				} else if (isQ2 && needleWhereTo == 1D) {
					finMonth = "05";
				} else if (isQ2 && needleWhereTo == 2D) {
					finMonth = "06";
				} else if (isQ3 && needleWhereTo == 0D) {
					finMonth = "07";
				} else if (isQ3 && needleWhereTo == 1D) {
					finMonth = "08";
				} else if (isQ3 && needleWhereTo == 2D) {
					finMonth = "09";
				} else if (isQ4 && needleWhereTo == 0D) {
					finMonth = "10";
				} else if (isQ4 && needleWhereTo == 1D) {
					finMonth = "11";
				} else if (isQ4 && needleWhereTo == 2D) {
					finMonth = "12";
				}
				   isYokePanelOn = true;
				   setSubPeriodSteering(needleWhereTo);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
		    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

	
}
