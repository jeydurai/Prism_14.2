package prism14;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;


public class DesktopPrimaryScreen extends JFrame implements ActionListener{

	JDesktopPane desktop;
    protected GradientPanel mainPanel, compoPanel;
    private JMenuBar mainMenuBar;
    protected JPanel bannerPanel;
    private JMenu queryMenu, dashboardMenu, reportMenu, helpMenu;
    private JMenuItem dashboardXxmPerformance,dashboardGeneral,dashboardLeadingIndicator,
    dashboardAT,dashboardStacking;
    private JMenuItem reportTBM,reportPartner,reportCustomer;
    private JMenuItem querySelectInUserData, querySelectInSalesList;
    private JMenuItem helpVersion;
    private ImageIcon imgLogo, pScreenImage1, pScreenImage2, trueNorthImage, primaryScreenTitle;
    private DateFormat dateFormat;
    private Date currentDate;
    private JSeparator sep1;
    private JLabel jlLogo, jlPrismText, jlDateTime, jlPScreenImage1, 
    jlPScreenImage2, jlTrueNorthImage;
    protected JLabel JlPrimaryScreenTitle;
    protected JScrollPane mainScrollPane, rootScrollPane;
    private JButton jbSignOut,jbEditAccount,jbWelcomeTo;
    private BufferedImage image;
    protected int fontSize, tempCount;
    protected String fontFamily;
    protected String fontSpecialFamily;
	public static String userID, role, eUnit, region, reportingTo, firstName, lastName, emailID;
	public static double xProp, yProp;
    private DataBaseUserDataQueries userDataQueries;
    private DataBaseUserData currentUserDataEntry;
    private List<DataBaseUserData> resultsUserData;
    private boolean isAdmin, isTBMVBMDashboard, isTBMVBMScorecard, isGeneralDashboard, 
    isLeadingIndicator, isStackRanking, isATDashboard;

    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
    }

	public DesktopPrimaryScreen() {
		super(GeneralConstants.SOFTWARE_NAME);
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset,
				screenSize.width - inset*2,
				screenSize.height - inset*2);
		desktop = new JDesktopPane();
		setContentPane(desktop);
		pack();
		setJMenuBar(createMenuBar());
	}
	
	public DesktopPrimaryScreen(String u, double x, double y) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		super(GeneralConstants.SOFTWARE_NAME);
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		desktop = new JDesktopPane();
		setBounds(inset, inset,
				screenSize.width - inset*2,
				screenSize.height - inset*2);
        fontSize = 11;
        fontFamily = "Arial";
        fontSpecialFamily = "Verdana";
        userID = u;
        xProp = x;
        yProp = y;
		pScreenImage1 = new ImageIcon(this.getClass().getResource(GeneralConstants.PRIMARY_SCREEN_IMAGE1));
		pScreenImage2 = new ImageIcon(this.getClass().getResource(GeneralConstants.PRIMARY_SCREEN_IMAGE2));
		trueNorthImage = new ImageIcon(this.getClass().getResource(GeneralConstants.TRUE_NORTH_IMAGE2));
		primaryScreenTitle = new ImageIcon(this.getClass().getResource(GeneralConstants.PRIMARY_SCREEN_TITLE));
        
    	DataBaseUserDataQueries userDataQueries = new DataBaseUserDataQueries();

//Get Role		    
	    try {
			resultsUserData =  userDataQueries.getModuleAccessbyName(userID);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
	    int tempCount = resultsUserData.size();
		    for (int i = 0; i < tempCount; i++) {
		    	currentUserDataEntry = resultsUserData.get(i);
		    	role = currentUserDataEntry.getRole();
		    	isAdmin = currentUserDataEntry.isAdminModuleAccessible();
		    	isTBMVBMDashboard = currentUserDataEntry.isTBMVBMDashModuleAccessible();
		    	isTBMVBMScorecard = currentUserDataEntry.isTBMVBMScoreModuleAccessible();
		    	isGeneralDashboard = currentUserDataEntry.isGeneralDashboardAccessible();
		    	isLeadingIndicator = currentUserDataEntry.isLeadingIndicatorAccessible();
		    	isStackRanking = currentUserDataEntry.isStackRankingAccessible();
		    	isATDashboard = currentUserDataEntry.isATDashboardAccessible();
		    }

//Get Reporting to		    
	    try {
			resultsUserData =  userDataQueries.getReportingTobyName(userID);
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
			resultsUserData =  userDataQueries.getFirstNamebyName(userID);
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
			resultsUserData =  userDataQueries.getLastNamebyName(userID);
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
			resultsUserData =  userDataQueries.getEmailIDbyName(userID);
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
			resultsUserData =  userDataQueries.getEUbyName(userID);
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
			resultsUserData =  userDataQueries.getRegionbyName(userID);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
	     tempCount = resultsUserData.size();
		    for (int i = 0; i < tempCount; i++) {
		    	currentUserDataEntry = resultsUserData.get(i);
		    	region = currentUserDataEntry.getRegion();
		    }

        setPanelReady();
        setHeadersReady();
		setContentPane(desktop);
		setJMenuBar(createMenuBar());
	}

	protected JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
        JMenu dashboardMenu = new JMenu("Dashboard");
        dashboardMenu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(dashboardMenu);
        JMenu queryMenu = new JMenu("Query");
        queryMenu.setMnemonic(KeyEvent.VK_Q);
        menuBar.add(queryMenu);
        JMenu reportMenu = new JMenu("Report");
        reportMenu.setMnemonic(KeyEvent.VK_R);
        menuBar.add(reportMenu);
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(helpMenu);
        JMenuItem dashboardXxmPerformance = new JMenuItem("TBM/VBM Scorecard");
        dashboardXxmPerformance.setMnemonic(KeyEvent.VK_T);
        dashboardXxmPerformance.setActionCommand("TBM/VBM Scorecard");
        dashboardXxmPerformance.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        dashboardXxmPerformance.addActionListener(this);
        JMenuItem dashboardXxmDashboard = new JMenuItem("TBM/VBM Dashboard");
        dashboardXxmDashboard.setMnemonic(KeyEvent.VK_B);
        dashboardXxmDashboard.setActionCommand("TBM/VBM Dashboard");
        dashboardXxmDashboard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        dashboardXxmDashboard.addActionListener(this);
        JMenuItem dashboardGeneral = new JMenuItem("General Dashboard");
        dashboardGeneral.setMnemonic(KeyEvent.VK_G);
        dashboardGeneral.setActionCommand("General Dashboard");
        dashboardGeneral.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        dashboardGeneral.addActionListener(this);
        JMenuItem dashboardLeadingIndicator = new JMenuItem("Leading Indicator");
        dashboardLeadingIndicator.setMnemonic(KeyEvent.VK_L);
        dashboardLeadingIndicator.setActionCommand("Leading Indicator");
        dashboardLeadingIndicator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        dashboardLeadingIndicator.addActionListener(this);
        JMenuItem dashboardAT = new JMenuItem("AT Dashboard");
        dashboardAT.setMnemonic(KeyEvent.VK_A);
        dashboardAT.setActionCommand("AT Dashboard");
        dashboardAT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        dashboardAT.addActionListener(this);
        JMenuItem dashboardStacking = new JMenuItem("Stack Ranking");
        dashboardStacking.setMnemonic(KeyEvent.VK_S);
        dashboardStacking.setActionCommand("Stack Ranking");
        dashboardStacking.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        dashboardStacking.addActionListener(this);
        JMenuItem reportTBM = new JMenuItem("TBM Report");
        reportTBM.setMnemonic(KeyEvent.VK_T);
        reportTBM.setActionCommand("TBM Report");
        reportTBM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        reportTBM.addActionListener(this);
        JMenuItem reportPartner = new JMenuItem("Partner Report");
        reportPartner.setMnemonic(KeyEvent.VK_P);
        reportPartner.setActionCommand("Partner Report");
        reportPartner.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        reportPartner.addActionListener(this);
        JMenuItem reportCustomer = new JMenuItem("Customer Report");
        reportCustomer.setMnemonic(KeyEvent.VK_C);
        reportCustomer.setActionCommand("Customer Report");
        reportCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        reportCustomer.addActionListener(this);
        reportMenu.add(reportCustomer);
        JMenuItem querySelectInUserData = new JMenuItem("User Data");
        querySelectInUserData.setMnemonic(KeyEvent.VK_U);
        querySelectInUserData.setActionCommand("User Data");
        querySelectInUserData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        querySelectInUserData.addActionListener(this);
        JMenuItem querySelectInSalesList = new JMenuItem("Sales List");
        querySelectInSalesList.setMnemonic(KeyEvent.VK_S);
        querySelectInSalesList.setActionCommand("Sales List");
        querySelectInSalesList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        querySelectInSalesList.addActionListener(this);
        JMenuItem helpVersion = new JMenuItem("About True North");
        helpVersion.setMnemonic(KeyEvent.VK_H);
        helpVersion.setActionCommand("About True North");
        helpVersion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        helpVersion.addActionListener(this);
        JMenuItem helpSignOut = new JMenuItem("Sign Out");
        helpSignOut.setMnemonic(KeyEvent.VK_O);
        helpSignOut.setActionCommand("Sign Out");
        helpSignOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        helpSignOut.addActionListener(this);
        JMenuItem helpExit = new JMenuItem("Quit Application");
        helpExit.setMnemonic(KeyEvent.VK_Q);
        helpExit.setActionCommand("Exit Application");
        helpExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        helpExit.addActionListener(this);

        
    	if (isAdmin) {
    		
    	}
    	if (isTBMVBMDashboard) {
            dashboardMenu.add(dashboardXxmDashboard);
    	}
    	if (isTBMVBMScorecard) {
            dashboardMenu.add(dashboardXxmPerformance);
    	}
    	if (isGeneralDashboard) {
            dashboardMenu.add(dashboardGeneral);
    	}
    	if (isLeadingIndicator) {
            dashboardMenu.add(dashboardLeadingIndicator);
    	}
    	if (isStackRanking) {
            dashboardMenu.add(dashboardStacking);
    	}
    	if (isATDashboard) {
            dashboardMenu.add(dashboardAT);
    	}
        
        queryMenu.add(querySelectInUserData);
        reportMenu.add(reportTBM);
        reportMenu.add(reportPartner);
        queryMenu.add(querySelectInSalesList);
        helpMenu.add(helpVersion);
        helpMenu.add(helpSignOut);
        helpMenu.add(helpExit);
        return menuBar;
	}
	
	
	protected void setPanelReady() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	jbWelcomeTo = new JButton("Welcome to " + GeneralConstants.SOFTWARE_NAME + ", Congratulations!");
		jbWelcomeTo.setBackground(this.getBackground());
		jbWelcomeTo.setForeground(Color.GRAY);
		jbWelcomeTo.setFont(new Font("Arial",Font.PLAIN,12));
		jbWelcomeTo.setBorder(null);
		jbWelcomeTo.setBounds(860, 75, 230, 20);
		jbWelcomeTo.setFocusPainted(false);
		jbWelcomeTo.setHorizontalAlignment(SwingConstants.RIGHT);

		jbEditAccount = new JButton(firstName + "," + lastName);
		jbEditAccount.setBackground(this.getBackground());
		jbEditAccount.setForeground(Color.GRAY);
		jbEditAccount.setFont(new Font("Arial",Font.PLAIN,12));
		jbEditAccount.setHorizontalTextPosition(SwingConstants.LEFT);
		jbEditAccount.setBorder(null);
		jbEditAccount.setBounds(1090, 75, 200, 20);
		jbEditAccount.setFocusPainted(false);
		jbEditAccount.addActionListener(new editAccountListener());

		jbSignOut = new JButton();
		jbSignOut.setText("<HTML><FONT color=\"#000099\"><U>SignOut</U></FONT></HTML>");
		jbSignOut.setBackground(this.getBackground());
		jbSignOut.setForeground(Color.GRAY);
		jbSignOut.setFont(new Font("Arial",Font.PLAIN,12));
		jbSignOut.setHorizontalTextPosition(SwingConstants.LEFT);
		jbSignOut.setBorder(null);
		jbSignOut.setBounds(1290, 75, 80, 20);
		jbSignOut.setFocusPainted(false);
		jbSignOut.addActionListener(new closeApplication());
		
        
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
        rootScrollPane = new JScrollPane();
        rootScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        rootScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rootScrollPane.setPreferredSize(di);

		mainPanel = new GradientPanel();
        mainPanel.setBackground(Color.BLUE);
        mainPanel.setLayout(null);
        mainPanel.add(jbWelcomeTo);
        mainPanel.add(jbSignOut);
        mainPanel.add(jbEditAccount);
        rootScrollPane.setViewportView(mainPanel);
        rootScrollPane.setBounds(0, 0, this.getWidth()+100, this.getHeight()-25);
        desktop.add(rootScrollPane);
        
        
        sep1 = new JSeparator();
        sep1.setBounds(0, 95,1360,2);
        mainPanel.add(sep1);
        
        jlPScreenImage1 = new JLabel(pScreenImage1);
        jlPScreenImage1.setBounds(1, 97, 700, 700);
        jlPScreenImage1.setOpaque(false);
        mainPanel.add(jlPScreenImage1);

        jlPScreenImage2 = new JLabel(pScreenImage2);
        jlPScreenImage2.setBounds(700, 97, 700, 700);
        jlPScreenImage2.setOpaque(false);
        mainPanel.add(jlPScreenImage2);
    }

    protected void setHeadersReady() {
    	
        try {
            image =  ImageIO.read(this.getClass().getResource(GeneralConstants.LOGO_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setIconImage(image);
        
        
        imgLogo = new ImageIcon(this.getClass().getResource(GeneralConstants.CISCO_LOGO));

        jlLogo = new JLabel(imgLogo);
        jlLogo.setBounds(0, 0, 100, 60);

        bannerPanel = new JPanel() {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
    			RenderingHints.VALUE_ANTIALIAS_ON);
        }
        };
//		bannerPanel.setBackground(new Color(245,245,245));
		bannerPanel.setBackground(new Color(255,255,255));
		bannerPanel.setLayout(null);
        bannerPanel.setSize(1370,95);
        bannerPanel.setLocation(0, 0);
        bannerPanel.setBorder(BorderFactory.createLineBorder(new Color(220,220,220), 1));        
		bannerPanel.add(jlLogo);
    	
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        currentDate = new Date();
        jlDateTime = new JLabel();
        jlDateTime.setText(dateFormat.format(currentDate));
        jlDateTime.setFont(new Font(fontSpecialFamily,Font.ITALIC+Font.BOLD,fontSize+3));
        jlDateTime.setBounds(1180, 0, 200, 20);
		bannerPanel.add(jlDateTime);
        
        jlTrueNorthImage = new JLabel(trueNorthImage);
        jlTrueNorthImage.setBounds(1265-35, 12, 125, 80);
        jlTrueNorthImage.setOpaque(false);
		bannerPanel.add(jlTrueNorthImage);

		JlPrimaryScreenTitle = new JLabel(primaryScreenTitle);
		JlPrimaryScreenTitle.setBounds(350, 15, 700, 50);
		JlPrimaryScreenTitle.setOpaque(false);
        bannerPanel.add(JlPrimaryScreenTitle);

		mainPanel.add(bannerPanel);

    }
	
	
	public void actionPerformed(ActionEvent e) {
		if ("TBM/VBM Dashboard".equals(e.getActionCommand())) {
		   try {
	   		    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			createXxmDashboard();
		   } catch (Exception ex) {
			   ex.printStackTrace();
		   } finally {
	   		    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		   }
		} else if ("TBM/VBM Scorecard".equals(e.getActionCommand())) {
			   try {
		   		    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					createXxmScorecard();
			   } catch (Exception ex) {
				   ex.printStackTrace();
			   } finally {
		   		    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			   }
		} else if ("About True North".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(desktop, GeneralConstants.VERSION);
		} else if ("General Dashboard".equals(e.getActionCommand())) {
		   try {
	   		    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				createGeneralDashboard();
		   } catch (Exception ex) {
			   ex.printStackTrace();
		   } finally {
	  		    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		   }
		} else if ("Leading Indicator".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(desktop, "Under Construction...");
		} else if ("AT Dashboard".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(desktop, "Under Construction...");
		} else if ("Stack Ranking".equals(e.getActionCommand())) {
			   try {
		   		    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					createStackRanking();
			   } catch (Exception ex) {
				   ex.printStackTrace();
			   } finally {
		   		    getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			   }
		} else if ("TBM Report".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(desktop, "Under Construction...");
		} else if ("Partner Report".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(desktop, "Under Construction...");
		} else if ("Customer Report".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(desktop, "Under Construction...");
		} else if ("User Data".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(desktop, "Under Construction...");
		} else if ("Sales List".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(desktop, "Under Construction...");
		} else if ("Sign Out".equals(e.getActionCommand())) {
			signOut();
		} else if ("Exit Application".equals(e.getActionCommand())) {
			exitApplication();
		}
	}
	
    private class editAccountListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jbEditAccount) {
				UserAccountEditScreen eScreen;
					try {
				    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				    	createUserUpdateScreen();
				    } catch (Exception e1) {
						e1.printStackTrace();
					} finally {
				    	getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
			}
		}
	}
	
    private class closeApplication implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		if (e.getSource() == jbSignOut) {
    			signOut();
    		}
    	}
    }

    
    private void exitApplication() {
    	System.exit(0);
    }
    
    private void signOut() {
    	dispose();
		LoginScreen lScreen = new LoginScreen(xProp, yProp);
	    lScreen.setSize(1350, 725);
	    lScreen.setLocation(0, 0);
	    lScreen.setUndecorated(false);
	    lScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    lScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    lScreen.setVisible(true);
    }

	protected void createXxmDashboard() {
		DashboardScoreCard frame = new DashboardScoreCard();
		frame.setVisible(true);
		desktop.add(frame);
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	protected void createXxmScorecard() {
		DashboardScoreCard2 frame = new DashboardScoreCard2();
		frame.setVisible(true);
		desktop.add(frame);
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}


	protected void createStackRanking() {
		DashboardStackRanking frame = new DashboardStackRanking();
		frame.setVisible(true);
		desktop.add(frame);
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	
	protected void createUserUpdateScreen() {
		UserAccountUpdateScreen frame;
		try {
			frame = new UserAccountUpdateScreen();
			frame.setVisible(true);
			desktop.add(frame);
			try {
				frame.setSelected(true);
			} catch (java.beans.PropertyVetoException e) {
				e.printStackTrace();
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	protected void createGeneralDashboard() {
		DashboardGeneral frame;
		frame = new DashboardGeneral();
		frame.setVisible(true);
		desktop.add(frame);
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
}
