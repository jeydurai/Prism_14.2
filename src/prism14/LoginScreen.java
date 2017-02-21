package prism14;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginScreen extends JFrame {

	private static String userID;
	private List<DataBaseUserLoginData> resultUserDataList;
	private DataBaseUserDataQueries userDataQueries;
	private DataBaseUserLoginData currentUserDataEntry;
	private DataBaseUserDataUpdate currentUserDataUpdateEntry;
	
	private GradientPanel beautyPanel;
	private JPanel mainPanel;
	private JPanel inputBoxPanel;
	private JPanel bannerPanel;
	
	private JLabel jlUserID;
	private JTextField jtfUserID;
	private JLabel jlPwd;
	private JPasswordField jtfPwd;
	private JLabel jlSignInTitle;
	private JLabel jlNewTo;
	private JLabel jlBannerImageLogo;
	private JLabel jlTrueNorthImageLogo;
	private JLabel jlInputBoxImageLogo;
	private JLabel jlMainTitle;
	private JLabel jlDashboardImage1;
	private JLabel jlDashboardImage2;
	private JLabel jlFootTitle1;
	private JLabel jlFootTitle2;
	private JTextArea jtaMainTitleInfo;
	private JTextArea jtaFootTitle1Info;
	private JTextArea jtaFootTitle2Info;
	private JTextArea jtaLoginErrorMessage;
	private ImageIcon bannerImage;
	private ImageIcon trueNorthImage;
	private ImageIcon inputBoxImage;
	private ImageIcon dashboardImage1;
	private ImageIcon dashboardImage2;
	private BufferedImage logoImage;
	private JButton jbSignin;
	private JButton jbCreateAccount;
	private JCheckBox jcStayedin;
	private JButton jbCantAccess;
	private JButton jbMainTitle1ReadMore;
	private JButton jbMainTitle2ReadMore;
	private static double xProp;
	private static double yProp;
	
	@SuppressWarnings({ "serial", "unused" })
	public LoginScreen(double x, double y) {

		xProp = x;
		yProp = y;
		try {
	        logoImage =  ImageIO.read(this.getClass().getResource(GeneralConstants.LOGO_IMAGE));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    this.setIconImage(logoImage);

		
		mainPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		inputBoxPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		bannerPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		beautyPanel = new GradientPanel();
		jlUserID = new JLabel("Username") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jlPwd = new JLabel("Password") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jlSignInTitle = new JLabel("Sign in");
		jtfUserID = new JTextField() {
			public void addNotify() {
				super.addNotify();
				requestFocus();
			}
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jtfPwd = new JPasswordField() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		bannerImage = new ImageIcon(this.getClass().getResource(GeneralConstants.LOGIN_BANNER_IMAGE));
		inputBoxImage = new ImageIcon(this.getClass().getResource(GeneralConstants.LOGIN_INPUT_IMAGE));
		trueNorthImage = new ImageIcon(this.getClass().getResource(GeneralConstants.TRUE_NORTH_IMAGE));
		
		jlBannerImageLogo = new JLabel(bannerImage) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jlBannerImageLogo.setBounds((int)(40*xProp), (int)(10*yProp), (int)(100*xProp), (int)(60*yProp));
		
		jlNewTo = new JLabel("New to" + GeneralConstants.SOFTWARE_NAME + "?") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jlNewTo.setFont(new Font("Arial",Font.PLAIN,(int)(12*((xProp+yProp)/2))));
		jlNewTo.setBackground(null);
		jlNewTo.setForeground(Color.GRAY);
//		jlNewTo.setBounds(1025, 25, 150, 35);
		jlNewTo.setBounds((int)(1025*xProp), (int)(25*yProp), (int)(150*xProp), (int)(35*yProp));

		jbCreateAccount = new JButton("CREATE AN ACCOUNT") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jbCreateAccount.setBackground(new Color(255,10,10));
		jbCreateAccount.setForeground(Color.WHITE);
		jbCreateAccount.setFont(new Font("Arial",Font.BOLD,(int)(12*((xProp+yProp)/2))));
		jbCreateAccount.setBorder(BorderFactory.createLineBorder(new Color(255,10,10), 1, true));
//		jbCreateAccount.setBounds(1150, 25, 150, 30);
		jbCreateAccount.setBounds((int)(1150*xProp), (int)(25*yProp), (int)(150*xProp), (int)(30*yProp));
		jbCreateAccount.setFocusPainted(false);
		
		jlTrueNorthImageLogo = new JLabel(trueNorthImage) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
//		jlTrueNorthImageLogo.setBounds(30, 95, 400, 100);
		jlTrueNorthImageLogo.setBounds((int)(30*xProp), (int)(95*yProp), (int)(400*xProp), (int)(100*yProp));
		jlTrueNorthImageLogo.setOpaque(false);
		
		jtaMainTitleInfo = new JTextArea() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jtaMainTitleInfo.setBackground(null);
		jtaMainTitleInfo.setForeground(Color.DARK_GRAY);
		jtaMainTitleInfo.setFont(new Font("Arial",Font.PLAIN,(int)(16*((xProp+yProp)/2))));
//		jtaMainTitleInfo.setBounds(80, 200, 300, 100);
		jtaMainTitleInfo.setBounds((int)(80*xProp), (int)(200*yProp), (int)(300*xProp), (int)(100*yProp));
		jtaMainTitleInfo.setText("Experience the robust measurement systems and dashboards, everywhere you work with Cisco.");
		jtaMainTitleInfo.setLineWrap(true);
		jtaMainTitleInfo.setWrapStyleWord(true);
		jtaMainTitleInfo.setEditable(false);
		jtaMainTitleInfo.setOpaque(false);
		
		
		dashboardImage1 = new ImageIcon(this.getClass().getResource(GeneralConstants.LOGIN_DASH_IMAGE1));
		jlDashboardImage1 = new JLabel(dashboardImage1);
		jlDashboardImage1.setBackground(null);
//		jlDashboardImage1.setBounds(480, 200, 350, 300);
		jlDashboardImage1.setBounds((int)(480*xProp), (int)(200*yProp), (int)(350*xProp), (int)(300*yProp));

		dashboardImage2 = new ImageIcon(this.getClass().getResource(GeneralConstants.LOGIN_DASH_IMAGE2));
		jlDashboardImage2 = new JLabel(dashboardImage2);
		jlDashboardImage2.setBackground(null);
//		jlDashboardImage2.setBounds(100, 250, 350, 300);
		jlDashboardImage2.setBounds((int)(100*xProp), (int)(250*yProp), (int)(350*xProp), (int)(300*yProp));
		
		beautyPanel.setBackground(Color.LIGHT_GRAY);
//		beautyPanel.setBounds(80, 450, 750, 100);
		beautyPanel.setBounds((int)(80*xProp), (int)(450*yProp), (int)(750*xProp), (int)(100*yProp));


		jlFootTitle1 = new JLabel("About " +  GeneralConstants.SOFTWARE_NAME + "- Business Intelligence Management from PLB") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jlFootTitle1.setBackground(null);
		jlFootTitle1.setForeground(Color.DARK_GRAY);
		jlFootTitle1.setFont(new Font("Arial",Font.BOLD,(int)(14*((xProp+yProp)/2))));
//		jlFootTitle1.setBounds(80,550,500,50);
		jlFootTitle1.setBounds((int)(80*xProp), (int)(550*yProp), (int)(500*xProp), (int)(50*yProp));
		
		jtaFootTitle1Info = new JTextArea() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jtaFootTitle1Info.setBackground(null);
		jtaFootTitle1Info.setForeground(Color.DARK_GRAY);
		jtaFootTitle1Info.setFont(new Font("Arial",Font.PLAIN,(int)(14*((xProp+yProp)/2))));
//		jtaFootTitle1Info.setBounds(80, 590, 300, 50);
		jtaFootTitle1Info.setBounds((int)(80*xProp), (int)(590*yProp), (int)(300*xProp), (int)(50*yProp));
		jtaFootTitle1Info.setText("Performance Reports and Dashboards for TBM/VBM, Partner and Customers. "
				+ "Leading Indicators, Stack Ranking and Many more... ");
		jtaFootTitle1Info.setLineWrap(true);
		jtaFootTitle1Info.setWrapStyleWord(true);
		jtaFootTitle1Info.setEditable(false);

		jbMainTitle1ReadMore = new JButton("Learn More") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jbMainTitle1ReadMore.setForeground(new Color(80,150,255));
		jbMainTitle1ReadMore.setBackground(null);
		jbMainTitle1ReadMore.setFont(new Font("Arial",Font.PLAIN,(int)(13*((xProp+yProp)/2))));
		jbMainTitle1ReadMore.setBorder(null);
		jbMainTitle1ReadMore.setHorizontalAlignment(SwingConstants.LEFT);
//		jbMainTitle1ReadMore.setBounds(80, 660, 200, 35);
		jbMainTitle1ReadMore.setBounds((int)(80*xProp), (int)(660*yProp), (int)(200*xProp), (int)(35*yProp));
		
		
		jlFootTitle2 = new JLabel("Bringing " + GeneralConstants.SOFTWARE_NAME + " to work User Specific") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jlFootTitle2.setBackground(null);
		jlFootTitle2.setForeground(Color.DARK_GRAY);
		jlFootTitle2.setFont(new Font("Arial",Font.BOLD,(int)(14*((xProp+yProp)/2))));
//		jlFootTitle2.setBounds(540,550,500,50);
		jlFootTitle2.setBounds((int)(540*xProp), (int)(550*yProp), (int)(500*xProp), (int)(50*yProp));
		
		jtaFootTitle2Info = new JTextArea() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jtaFootTitle2Info.setBackground(null);
		jtaFootTitle2Info.setForeground(Color.DARK_GRAY);
		jtaFootTitle2Info.setFont(new Font("Arial",Font.PLAIN,(int)(14*((xProp+yProp)/2))));
//		jtaFootTitle2Info.setBounds(540, 590, 300, 70);
		jtaFootTitle2Info.setBounds((int)(540*xProp), (int)(590*yProp), (int)(300*xProp), (int)(70*yProp));
		jtaFootTitle2Info.setText("Excel Dashboard which helped us measure last year "
				+ "performance now has become platform independent tool reaching out each individual in sales team...");
		jtaFootTitle2Info.setLineWrap(true);
		jtaFootTitle2Info.setWrapStyleWord(true);
		jtaFootTitle2Info.setEditable(false);

		jbMainTitle2ReadMore = new JButton("Learn More") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jbMainTitle2ReadMore.setForeground(new Color(80,150,255));
		jbMainTitle2ReadMore.setBackground(null);
		jbMainTitle2ReadMore.setFont(new Font("Arial",Font.PLAIN,(int)(13*((xProp+yProp)/2))));
		jbMainTitle2ReadMore.setBorder(null);
		jbMainTitle2ReadMore.setHorizontalAlignment(SwingConstants.LEFT);
//		jbMainTitle2ReadMore.setBounds(540, 660, 200, 35);
		jbMainTitle2ReadMore.setBounds((int)(540*xProp), (int)(660*yProp), (int)(200*xProp), (int)(35*yProp));
		
		
//		jlSignInTitle.setBounds(30, 15, 50, 20);
		jlSignInTitle.setBounds((int)(30*xProp), (int)(15*yProp), (int)(50*xProp), (int)(20*yProp));
		jlSignInTitle.setFont(new Font("Arial",Font.PLAIN,(int)(16*((xProp+yProp)/2))));

		jlInputBoxImageLogo = new JLabel(inputBoxImage) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
//		jlInputBoxImageLogo.setBounds(250, 0, 100, 60);
		jlInputBoxImageLogo.setBounds((int)(250*xProp), (int)(0*yProp), (int)(100*xProp), (int)(60*yProp));

//		jlUserID.setBounds(30, 50, 120, 30);
		jlUserID.setBounds((int)(30*xProp), (int)(50*yProp), (int)(120*xProp), (int)(30*yProp));
		jlUserID.setFont(new Font("Arial Narrow",Font.BOLD,(int)(18*((xProp+yProp)/2))));
//		jtfUserID.setBounds(30, 80, 300, 30);
		jtfUserID.setBounds((int)(30*xProp), (int)(80*yProp), (int)(300*xProp), (int)(30*yProp));
		jtfUserID.setFont(new Font("Arial",Font.PLAIN,(int)(16*((xProp+yProp)/2))));
		jtfUserID.addFocusListener(new userIDFocusListener());
		jtfUserID.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

//		jlPwd.setBounds(30, 120, 100, 30);
		jlPwd.setBounds((int)(30*xProp), (int)(120*yProp), (int)(100*xProp), (int)(30*yProp));
		jlPwd.setFont(new Font("Arial Narrow",Font.BOLD,(int)(18*((xProp+yProp)/2))));
//		jtfPwd.setBounds(30, 150, 300, 30);
		jtfPwd.setBounds((int)(30*xProp), (int)(150*yProp), (int)(300*xProp), (int)(30*yProp));
		jtfPwd.setFont(new Font("Arial",Font.BOLD,(int)(20*((xProp+yProp)/2))));
		jtfPwd.addFocusListener(new userPwdFocusListener());
		jtfPwd.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		jbSignin = new JButton("Sign in") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jbSignin.setBackground(new Color(80,150,255));
		jbSignin.setForeground(Color.WHITE);
		jbSignin.setFont(new Font("Arial",Font.BOLD,(int)(16*((xProp+yProp)/2))));
		jbSignin.setBorder(BorderFactory.createLineBorder(new Color(80,150,255), 1, true));
//		jbSignin.setBounds(30, 200, 85, 35);
		jbSignin.setBounds((int)(30*xProp), (int)(200*yProp), (int)(85*xProp), (int)(35*yProp));
		jbSignin.setFocusPainted(false);
		jbSignin.addActionListener(new signinListener());

		jbCantAccess = new JButton("Can't access your account?") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jbCantAccess.setForeground(new Color(80,100,255));
		jbCantAccess.setBackground(new Color(245,245,245));
		jbCantAccess.setFont(new Font("Arial",Font.PLAIN,(int)(13*((xProp+yProp)/2))));
		jbCantAccess.setBorder(null);
		jbCantAccess.setHorizontalAlignment(SwingConstants.LEFT);
//		jbCantAccess.setBounds(30, 245, 200, 35);
		jbCantAccess.setBounds((int)(30*xProp), (int)(245*yProp), (int)(200*xProp), (int)(35*yProp));
		
		jcStayedin = new JCheckBox("Stay Signed in") {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        			RenderingHints.VALUE_ANTIALIAS_ON);
            }
		};
		jcStayedin.setBackground(null);
//		jcStayedin.setBounds(130, 200, 150, 35);
		jcStayedin.setBounds((int)(130*xProp), (int)(200*yProp), (int)(150*xProp), (int)(35*yProp));
		jcStayedin.setFont(new Font("Aria",Font.PLAIN,(int)(13*((xProp+yProp)/2))));
		jcStayedin.setForeground(Color.GRAY);
		
		
		inputBoxPanel.setBackground(new Color(245,245,245));
		inputBoxPanel.setLayout(null);
//		inputBoxPanel.setBounds(850, 125, 350, 300);
		inputBoxPanel.setBounds((int)(850*xProp), (int)(125*yProp), (int)(350*xProp), (int)(300*yProp));
		inputBoxPanel.setBorder(BorderFactory.createLineBorder(new Color(220,220,220), 1));
		inputBoxPanel.add(jlSignInTitle);
		inputBoxPanel.add(jlUserID);
		inputBoxPanel.add(jtfUserID);
		inputBoxPanel.add(jlPwd);
		inputBoxPanel.add(jtfPwd);
		inputBoxPanel.add(jbSignin);
		inputBoxPanel.add(jcStayedin);
		inputBoxPanel.add(jbCantAccess);
		inputBoxPanel.add(jlInputBoxImageLogo);
		
		
		bannerPanel.setBackground(new Color(245,245,245));
		bannerPanel.setLayout(null);
        bannerPanel.setSize((int)(1370*xProp),(int)(80*yProp));
        bannerPanel.setLocation(0, 0);
        bannerPanel.setBorder(BorderFactory.createLineBorder(new Color(220,220,220), 1));        
		bannerPanel.add(jlBannerImageLogo);
		bannerPanel.add(jlNewTo);
		bannerPanel.add(jbCreateAccount);
        

		
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(null);
		mainPanel.add(inputBoxPanel);
		mainPanel.add(bannerPanel);
		mainPanel.add(jtaMainTitleInfo);
		mainPanel.add(jlTrueNorthImageLogo);
		mainPanel.add(jlDashboardImage1);
		mainPanel.add(jlDashboardImage2);
		mainPanel.add(beautyPanel);
		mainPanel.add(jlFootTitle1);
		mainPanel.add(jtaFootTitle1Info);
		mainPanel.add(jlFootTitle2);
		mainPanel.add(jtaFootTitle2Info);
		mainPanel.add(jbMainTitle1ReadMore);
		mainPanel.add(jbMainTitle2ReadMore);
		this.getRootPane().setDefaultButton(jbSignin);
		setContentPane(mainPanel);
	
	}
	
	private class signinListener implements ActionListener {
		private String userName;
		private String pWord;
		
		private int tempCount;
		
		public void actionPerformed(ActionEvent e) {
			boolean updateStat = false;
			if (e.getSource() == jbSignin) {
				char[] tempPass = jtfPwd.getPassword();
				userID = userName = jtfUserID.getText();
				pWord = String.copyValueOf(tempPass);
				try {
					userDataQueries = new DataBaseUserDataQueries();
					resultUserDataList = 
							userDataQueries.getPrimarybyNameandPwd(userName, pWord);
					tempCount = resultUserDataList.size();

					if (resultUserDataList.size() != 0) {
					currentUserDataEntry = resultUserDataList.get(0);
					}
					
				} 
				catch (SQLException | ClassNotFoundException | IllegalAccessException |
						InstantiationException | IndexOutOfBoundsException e1) {
					e1.printStackTrace();
				}
				
					
					if (resultUserDataList.size() != 0) {
							// go to Account Update Screen
							try {
								currentUserDataUpdateEntry = new DataBaseUserDataUpdate();
							} catch (InstantiationException | IllegalAccessException
									| ClassNotFoundException e1) {
								e1.printStackTrace();
							}
							try {
								updateStat = currentUserDataUpdateEntry.
										activateUpdateHitsbyNameandPwd(currentUserDataEntry.getHits()+1, userName, pWord);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							refreshInputBox();
					} else {
						showErrorMessage();
					}
			}
		}
	}

	public class userIDFocusListener implements FocusListener {
		public void focusGained(FocusEvent e) {
			jtfUserID.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(150,200,225), 1, true),
					BorderFactory.createLineBorder(new Color(235,235,235), 2, false)));
		}
		public void focusLost(FocusEvent e) {
			jtfUserID.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		}
	}
	
	public class userPwdFocusListener implements FocusListener {
		public void focusGained(FocusEvent e) {
			jtfPwd.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(150,200,225), 1, true),
					BorderFactory.createLineBorder(new Color(235,235,235), 2, false)));
		}
		public void focusLost(FocusEvent e) {
			jtfPwd.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		}
	}
	
	public void showErrorMessage() {
		JButton errorButton = new RoundButton("?");
//		inputBoxPanel.setBounds(850, 125, 350, 300+50);
		inputBoxPanel.setBounds((int)(850*xProp), (int)(125*yProp), (int)(350*xProp), (int)(350*yProp));
//		jcStayedin.setBounds(130, 200+50, 150, 35);
		jcStayedin.setBounds((int)(130*xProp), (int)(250*yProp), (int)(150*xProp), (int)(35*yProp));
//		jbCantAccess.setBounds(30, 245+50, 200, 35);
		jbCantAccess.setBounds((int)(30*xProp), (int)(295*yProp), (int)(200*xProp), (int)(35*yProp));
//		jbSignin.setBounds(30, 200+50, 85, 35);
		jbSignin.setBounds((int)(30*xProp), (int)(250*yProp), (int)(85*xProp), (int)(35*yProp));
		jtaLoginErrorMessage = new JTextArea();
		jtaLoginErrorMessage.setBackground(null);
		jtaLoginErrorMessage.setForeground(new Color(255,10,10));
		jtaLoginErrorMessage.setFont(new Font("Arial",Font.PLAIN,(int)(14*((xProp+yProp)/2))));
//		jtaLoginErrorMessage.setBounds(30, 200, 200, 30);
		jtaLoginErrorMessage.setBounds((int)(30*xProp), (int)(200*yProp), (int)(200*xProp), (int)(30*yProp));
		jtaLoginErrorMessage.setText("The username or password you entered is incorrect.");
		jtaLoginErrorMessage.setLineWrap(true);
		jtaLoginErrorMessage.setWrapStyleWord(true);
		jtaLoginErrorMessage.setEditable(false);
		jtfPwd.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(245,10,10), 1, true),
				BorderFactory.createLineBorder(new Color(235,235,235), 2, false)));
		jtfPwd.setText("");
		jtfPwd.requestFocus();
		errorButton.setBackground(new Color(255,10,10));
		errorButton.setForeground(Color.WHITE);
		errorButton.setFont(new Font("Arial",Font.BOLD,(int)(8*((xProp+yProp)/2))));
		errorButton.setHorizontalTextPosition(SwingConstants.CENTER);
		errorButton.setVerticalTextPosition(SwingConstants.CENTER);
//		errorButton.setBounds(165, 215, 20, 20);
		errorButton.setBounds((int)(165*xProp), (int)(215*yProp), (int)(20*xProp), (int)(20*yProp));
		
		inputBoxPanel.add(errorButton);
		inputBoxPanel.add(jtaLoginErrorMessage);
	}
	public void refreshInputBox() {
//		inputBoxPanel.setBounds(850, 125, 350, 300);
		inputBoxPanel.setBounds((int)(850*xProp), (int)(125*yProp), (int)(350*xProp), (int)(300*yProp));
//		jcStayedin.setBounds(130, 200, 150, 35);
		jcStayedin.setBounds((int)(130*xProp), (int)(200*yProp), (int)(150*xProp), (int)(35*yProp));
//		jbCantAccess.setBounds(30, 245, 200, 35);
		jbCantAccess.setBounds((int)(30*xProp), (int)(245*yProp), (int)(200*xProp), (int)(35*yProp));
//		jbSignin.setBounds(30, 200, 85, 35);
		jbSignin.setBounds((int)(30*xProp), (int)(200*yProp), (int)(85*xProp), (int)(35*yProp));
			if (jtaLoginErrorMessage != null) {
				inputBoxPanel.remove(jtaLoginErrorMessage);
				jtaLoginErrorMessage = null;
			}
			jtfPwd.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			this.dispose();
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        	public void run() {
	        		createAndShowGUI();
	        	}
	        });

	        
	}
	private static void createAndShowGUI() {
		DesktopPrimaryScreen dpScreen;
		try {
			dpScreen = new DesktopPrimaryScreen(userID, xProp, yProp);
			dpScreen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    dpScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
			dpScreen.setVisible(true);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
