package prism14;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class UserAccountUpdateScreen extends JInternalFrame{

		static int openFrameCount = 0;
		static final int xOffset = 30, yOffset = 30;
		private JPanel mainPanel, bannerPanel, footPanel, rightPanel;
		private JLabel jlSecImage1, jlSecImage2, jlGenTerms, jlWelcomeTo, jlAccountInfoTitle, jlBannerImageLogo, 
		jlEU, jlPassTerms, jlConfirmEmailID, jlDepartment, jlDivision, jlReportingTo, jlOldPassword, 
		jlNewPassword, jlConfirmPassword, jlRole, jlFirstName, jlLastName, jlEmailID;
		private JTextField jtfFirstName, jtfLastName, jtfEmailID, jtfConfirmEmailID, jtfDepartment, 
		jtfDivision, jtfReportingTo, jtfRole;
		private JPasswordField jpfOldPassword, jpfConfirmPassword, jpfNewPassword;
		@SuppressWarnings("rawtypes")
		private JList jltEU, jltRegion, jltNode;
		private JScrollPane regionScrollPane, nodeScrollPane, euScrollPane;
		private ImageIcon bannerImage, secImage1, secImage2, imageIcon;
		private BufferedImage logoImage;
		private JButton jbSignOut;
		private RoundButton jbSave, jbReset;
		private JTextArea jtaPassTerms, jtaGenTerms;
		
	    private DataBaseUserDataQueries userDataQueries;
	    private DataBaseUserData currentUserDataEntry;
	    private DataBaseUserLoginData currentUserDataEntry1;
	    private List<DataBaseUserData> resultsUserData;
	    private List<DataBaseUserLoginData> resultsUserData1;
		private DataBaseUserDataUpdate currentUserDataUpdateEntry;

	    private DataBaseSalesListQueries salesListQueries;
	    private DataBaseSalesList currentSalesListEntry;
	    private List<DataBaseSalesList> resultsSalesList;

	    private ArrayList<String> tempArrayList;
	    private DefaultListModel euFillModel;
	    private DefaultListModel regionFillModel;
	    private DefaultListModel nodeFillModel;
	    private DefaultListModel userFillModel;
	    private DefaultListModel specialNodeFillModel;

	    private String oldPassword, newPassword, firstName, lastName, emailID, reportingTo, 
	    eUnit, department, division, region, roleString, role,confirmPassword, tempEU, tempRegion;
	    
	    
		@SuppressWarnings({ "serial", "unused", "unchecked", "rawtypes" })
		public UserAccountUpdateScreen() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			super("User Account Screen #" + (++openFrameCount),
					true, true, true, true);
	    	DataBaseUserDataQueries userDataQueries = new DataBaseUserDataQueries();
	    	DataBaseSalesListQueries salesListQueries = new DataBaseSalesListQueries();
	    	imageIcon = new ImageIcon(this.getClass().getResource(GeneralConstants.INTERNAL_FRAME_LOGO_IMAGE));
			setSize(700,500);
			setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
		    this.setFrameIcon(imageIcon);

	    	roleString = DesktopPrimaryScreen.role;
	    	reportingTo = DesktopPrimaryScreen.reportingTo;
	    	firstName = DesktopPrimaryScreen.firstName;
	    	lastName = DesktopPrimaryScreen.lastName;
	    	emailID = DesktopPrimaryScreen.emailID;
	    	tempEU = DesktopPrimaryScreen.eUnit;
	    	tempRegion = DesktopPrimaryScreen.region;
			    
			mainPanel = new JPanel();
			bannerPanel = new JPanel();
			footPanel = new JPanel();
			rightPanel = new JPanel();
			bannerImage = new ImageIcon(this.getClass().getResource(GeneralConstants.CISCO_BLUE_LOGO));
			secImage1 = new ImageIcon(this.getClass().getResource(GeneralConstants.SECURITY_IMAGE1));
			secImage2 = new ImageIcon(this.getClass().getResource(GeneralConstants.SECURITY_IMAGE2));
			
			jlBannerImageLogo = new JLabel(bannerImage);
			jlBannerImageLogo.setBounds(40, 10, 100, 60);
			


			bannerPanel.setBackground(new Color(245,245,245));
			bannerPanel.setLayout(null);
	        bannerPanel.setSize(1370,80);
	        bannerPanel.setLocation(0, 0);
	        bannerPanel.setBorder(BorderFactory.createLineBorder(new Color(220,220,220), 1));        
			bannerPanel.add(jlBannerImageLogo);

			jlAccountInfoTitle = new JLabel("User Account Information:");
			jlAccountInfoTitle.setBackground(null);
			jlAccountInfoTitle.setForeground(Color.BLACK);
			jlAccountInfoTitle.setFont(new Font("Arial Black",Font.BOLD,30));
			jlAccountInfoTitle.setBorder(null);
			jlAccountInfoTitle.setBounds(10, 60, 1000, 100);
			
			jlFirstName = new JLabel("First Name:");
			jlFirstName.setBackground(null);
			jlFirstName.setForeground(Color.DARK_GRAY);
			jlFirstName.setFont(new Font("Arial",Font.BOLD,14));
			jlFirstName.setBorder(null);
			jlFirstName.setBounds(10, 165, 100, 20);

			jtfFirstName = new JTextField(firstName);
			jtfFirstName.setBackground(null);
			jtfFirstName.setForeground(Color.DARK_GRAY);
			jtfFirstName.setFont(new Font("Arial",Font.PLAIN,14));
			jtfFirstName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			jtfFirstName.setBounds(10, 185, 250, 30);
			jtfFirstName.addFocusListener(new firstNameFocusListener());

			jlLastName = new JLabel("Last Name:");
			jlLastName.setBackground(null);
			jlLastName.setForeground(Color.DARK_GRAY);
			jlLastName.setFont(new Font("Arial",Font.BOLD,14));
			jlLastName.setBorder(null);
			jlLastName.setBounds(270, 165, 100, 20);

			jtfLastName = new JTextField(lastName);
			jtfLastName.setBackground(null);
			jtfLastName.setForeground(Color.DARK_GRAY);
			jtfLastName.setFont(new Font("Arial",Font.PLAIN,14));
			jtfLastName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			jtfLastName.setBounds(270, 185, 250, 30);
			jtfLastName.addFocusListener(new lastNameFocusListener());

			jlEmailID = new JLabel("Email ID:");
			jlEmailID.setBackground(null);
			jlEmailID.setForeground(Color.DARK_GRAY);
			jlEmailID.setFont(new Font("Arial",Font.BOLD,14));
			jlEmailID.setBorder(null);
			jlEmailID.setBounds(10, 225, 100, 20);

			jtfEmailID = new JTextField(emailID);
			jtfEmailID.setBackground(null);
			jtfEmailID.setForeground(Color.DARK_GRAY);
			jtfEmailID.setFont(new Font("Arial",Font.PLAIN,14));
			jtfEmailID.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			jtfEmailID.setBounds(10, 255, 350, 30);
			jtfEmailID.addFocusListener(new emailIDFocusListener());
			
			jlConfirmEmailID = new JLabel("Confirm Email ID:");
			jlConfirmEmailID.setBackground(null);
			jlConfirmEmailID.setForeground(Color.DARK_GRAY);
			jlConfirmEmailID.setFont(new Font("Arial",Font.BOLD,14));
			jlConfirmEmailID.setBorder(null);
			jlConfirmEmailID.setBounds(370, 225, 200, 20);

			jtfConfirmEmailID = new JTextField(){
				public void addNotify() {
				super.addNotify();
				requestFocus();
				}
			};
			jtfConfirmEmailID.setBackground(null);
			jtfConfirmEmailID.setForeground(Color.DARK_GRAY);
			jtfConfirmEmailID.setFont(new Font("Arial",Font.PLAIN,14));
			jtfConfirmEmailID.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			jtfConfirmEmailID.setBounds(370, 255, 350, 30);
			jtfConfirmEmailID.addFocusListener(new ConfirmEmailIDFocusListener());

			jlRole = new JLabel("Role:");
			jlRole.setBackground(null);
			jlRole.setForeground(Color.DARK_GRAY);
			jlRole.setFont(new Font("Arial",Font.BOLD,14));
			jlRole.setBorder(null);
			jlRole.setBounds(10, 295, 100, 20);

			jtfRole = new JTextField(roleString);
			jtfRole.setBackground(null);
			jtfRole.setForeground(Color.DARK_GRAY);
			jtfRole.setFont(new Font("Arial",Font.PLAIN,14));
			jtfRole.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			jtfRole.setBounds(10, 325, 150, 25);
			jtfRole.setEditable(false);
			jtfRole.addFocusListener(new roleFocusListener());

		    euFillModel = new DefaultListModel ();

		    if (roleString.equals("HEAD") || roleString.equals("ADMIN")) {
			    try {
					resultsUserData =  userDataQueries.getAllEU();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    } else {
			    try {
					resultsUserData =  userDataQueries.getEUbyName(DesktopPrimaryScreen.userID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
			    for (int i = 0; i < resultsUserData.size(); i++) {
			    	currentUserDataEntry = resultsUserData.get(i);
			    	if (!currentUserDataEntry.getEU().equals("ALL")) {
				    	euFillModel.addElement(currentUserDataEntry.getEU());
			    	} 
			    }
		    jltEU = new JList();
		    jltEU.setModel(euFillModel);
		    jltEU.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    jltEU.setEnabled(false);
		    jltEU.setFont(new Font("Arial",Font.BOLD,12));
		    euScrollPane = new JScrollPane(jltEU, 
				     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    euScrollPane.setBounds(170, 300, 70, 55);

		    regionFillModel = new DefaultListModel ();
		    if (tempRegion.equals("ALL") && tempEU.equals("ALL")) {
			    try {
					resultsUserData =  userDataQueries.getAllRegion();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    } else if (tempRegion.equals("ALL") && tempEU.equals("EU3")) {
			    try {
					resultsUserData =  userDataQueries.getRegionbyEU(tempEU);
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
			    for (int i = 0; i < resultsUserData.size(); i++) {
			    	currentUserDataEntry = resultsUserData.get(i);
			    		if (currentUserDataEntry.getRegion().equals("ALL")) {
			    			
			    		} else {
			    			regionFillModel.addElement(currentUserDataEntry.getRegion());
			    		}
			    }
		    jltRegion = new JList();
		    jltRegion.setModel(regionFillModel);
		    jltRegion.setEnabled(false);
		    jltRegion.setFont(new Font("Arial",Font.PLAIN,12));
		    regionScrollPane = new JScrollPane(jltRegion, 
		     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    regionScrollPane.setBounds(260,300,100,55);

/*Node Filler*/		    
		    nodeFillModel = new DefaultListModel ();
		    if (roleString.equals("HEAD") || roleString.equals("ADMIN")) {
			    try {
					resultsSalesList =  salesListQueries.getAllSL6();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				    for (int i = 0; i < resultsSalesList.size(); i++) {
				    	currentSalesListEntry = resultsSalesList.get(i);
				    		if (!nodeFillModel.contains(currentSalesListEntry.getSalesLevel6())) {
				    			nodeFillModel.addElement(currentSalesListEntry.getSalesLevel6());
				    		} 
				    }
		    } else if (roleString.equals("RM") || roleString.equals("TM")) {
		    	userFillModel = new DefaultListModel();
			    try {
					resultsUserData =  userDataQueries.getUserNamebyReportingTo(DesktopPrimaryScreen.userID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				    for (int i = 0; i < resultsUserData.size(); i++) {
				    	currentUserDataEntry = resultsUserData.get(i);
				    		if (!currentUserDataEntry.getUserName().equals("ALL")) {
				    			userFillModel.addElement(currentUserDataEntry.getUserName());
				    		} 
				    }
		    	for (int l = 0; l < userFillModel.size(); l++) {
			    try {
					resultsSalesList =  salesListQueries.getSL6byUserName((String)userFillModel.getElementAt(l));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			    
				    for (int k = 0; k < resultsSalesList.size(); k++) {
				    	currentSalesListEntry = resultsSalesList.get(k);
				    		if (!nodeFillModel.contains(currentSalesListEntry.getSalesLevel6())) {
				    			nodeFillModel.addElement(currentSalesListEntry.getSalesLevel6());
				    		}
				    }
		    	}
		    } else {
			    try {
					resultsSalesList =  salesListQueries.getSL6byUserName(DesktopPrimaryScreen.userID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				    for (int i = 0; i < resultsSalesList.size(); i++) {
				    	currentSalesListEntry = resultsSalesList.get(i);
				    		if (!nodeFillModel.contains(currentSalesListEntry.getSalesLevel6())) {
				    			nodeFillModel.addElement(currentSalesListEntry.getSalesLevel6());
				    		} 
				    }
		    }
		
		    jltNode = new JList();
	    	jltNode.setModel(nodeFillModel);
		    jltNode.setFont(new Font("Arial",Font.PLAIN,12));
		    jltNode.setEnabled(false);
		    nodeScrollPane = new JScrollPane(jltNode, 
		     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    nodeScrollPane.setBounds(370,300,350,55);
			
			jlDepartment = new JLabel("Department:");
			jlDepartment.setBackground(null);
			jlDepartment.setForeground(Color.DARK_GRAY);
			jlDepartment.setFont(new Font("Arial",Font.BOLD,14));
			jlDepartment.setBorder(null);
			jlDepartment.setBounds(10, 360, 100, 20);

			jtfDepartment = new JTextField("Sales");
			jtfDepartment.setEditable(false);
			jtfDepartment.setBackground(null);
			jtfDepartment.setForeground(Color.DARK_GRAY);
			jtfDepartment.setFont(new Font("Arial",Font.PLAIN,14));
			jtfDepartment.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			jtfDepartment.setBounds(10, 390, 250, 30);
			jtfDepartment.addFocusListener(new departmentFocusListener());

			jlDivision = new JLabel("Division:");
			jlDivision.setBackground(null);
			jlDivision.setForeground(Color.DARK_GRAY);
			jlDivision.setFont(new Font("Arial",Font.BOLD,14));
			jlDivision.setBorder(null);
			jlDivision.setBounds(270, 360, 100, 20);

			jtfDivision = new JTextField("PLB");
			jtfDivision.setEditable(false);
			jtfDivision.setBackground(null);
			jtfDivision.setForeground(Color.DARK_GRAY);
			jtfDivision.setFont(new Font("Arial",Font.PLAIN,14));
			jtfDivision.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			jtfDivision.setBounds(270, 390, 250, 30);
			jtfDivision.addFocusListener(new divisionFocusListener());

			jlReportingTo = new JLabel("Reporting To:");
			jlReportingTo.setBackground(null);
			jlReportingTo.setForeground(Color.DARK_GRAY);
			jlReportingTo.setFont(new Font("Arial",Font.BOLD,14));
			jlReportingTo.setBorder(null);
			jlReportingTo.setBounds(530, 360, 100, 20);

			jtfReportingTo = new JTextField(reportingTo);
			jtfReportingTo.setEditable(false);
			jtfReportingTo.setBackground(null);
			jtfReportingTo.setForeground(Color.DARK_GRAY);
			jtfReportingTo.setFont(new Font("Arial",Font.PLAIN,14));
			jtfReportingTo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			jtfReportingTo.setBounds(530, 390, 250, 30);
			jtfReportingTo.addFocusListener(new reportingToFocusListener());

			jlOldPassword = new JLabel("Old Password:");
			jlOldPassword.setBackground(null);
			jlOldPassword.setForeground(Color.DARK_GRAY);
			jlOldPassword.setFont(new Font("Arial",Font.BOLD,14));
			jlOldPassword.setBorder(null);
			jlOldPassword.setBounds(10, 430, 150, 20);

			jpfOldPassword = new JPasswordField();
			jpfOldPassword.setBackground(null);
			jpfOldPassword.setForeground(Color.DARK_GRAY);
			jpfOldPassword.setFont(new Font("Arial",Font.PLAIN,14));
			jpfOldPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			jpfOldPassword.setBounds(10, 460, 250, 30);
			jpfOldPassword.addFocusListener(new oldPasswordFocusListener());

			jlNewPassword = new JLabel("New Password:");
			jlNewPassword.setBackground(null);
			jlNewPassword.setForeground(Color.DARK_GRAY);
			jlNewPassword.setFont(new Font("Arial",Font.BOLD,14));
			jlNewPassword.setBorder(null);
			jlNewPassword.setBounds(10, 500, 150, 20);

			jpfNewPassword = new JPasswordField();
			jpfNewPassword.setBackground(null);
			jpfNewPassword.setForeground(Color.DARK_GRAY);
			jpfNewPassword.setFont(new Font("Arial",Font.PLAIN,14));
			jpfNewPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			jpfNewPassword.setBounds(10, 530, 250, 30);
			jpfNewPassword.addFocusListener(new newPasswordFocusListener());
			
			jlConfirmPassword = new JLabel("Confirm Password:");
			jlConfirmPassword.setBackground(null);
			jlConfirmPassword.setForeground(Color.DARK_GRAY);
			jlConfirmPassword.setFont(new Font("Arial",Font.BOLD,14));
			jlConfirmPassword.setBorder(null);
			jlConfirmPassword.setBounds(270, 500, 150, 20);

			jpfConfirmPassword = new JPasswordField();
			jpfConfirmPassword.setBackground(null);
			jpfConfirmPassword.setForeground(Color.DARK_GRAY);
			jpfConfirmPassword.setFont(new Font("Arial",Font.PLAIN,14));
			jpfConfirmPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			jpfConfirmPassword.setBounds(270, 530, 250, 30);
			jpfConfirmPassword.addFocusListener(new confirmPasswordFocusListener());

			jbSave = new RoundButton("Save");
			jbSave.setBackground(new Color(80,150,255));
			jbSave.setForeground(Color.WHITE);
			jbSave.setFont(new Font("Arial",Font.PLAIN,12));
			jbSave.setHorizontalTextPosition(SwingConstants.CENTER);
			jbSave.setBounds(300, 580, 80, 40);
			jbSave.setBorder(BorderFactory.createLineBorder(new Color(80,150,255), 1, true));
			jbSave.setFocusPainted(false);
			jbSave.addActionListener(new saveListener());
		

			jbReset = new RoundButton("No, Thanks!");
			jbReset.setBackground(new Color(80,150,255));
			jbReset.setForeground(Color.WHITE);
			jbReset.setFont(new Font("Arial",Font.PLAIN,12));
			jbReset.setHorizontalTextPosition(SwingConstants.CENTER);
			jbReset.setBounds(400, 580, 80, 40);
			jbReset.setBorder(BorderFactory.createLineBorder(new Color(80,150,255), 1, true));
			jbReset.setFocusPainted(false);
			jbReset.addActionListener(new noThanksListener());
			
			jlPassTerms = new JLabel("Warning on Sharing Password:");
			jlPassTerms.setBackground(null);
			jlPassTerms.setForeground(Color.DARK_GRAY);
			jlPassTerms.setFont(new Font("Arial",Font.BOLD,14));
			jlPassTerms.setBorder(null);
			jlPassTerms.setBounds(0, 0, 150, 50);
			
			jtaPassTerms = new JTextArea();
			jtaPassTerms.setBackground(null);
			jtaPassTerms.setForeground(Color.DARK_GRAY);
			jtaPassTerms.setFont(new Font("Arial",Font.PLAIN,11));
			jtaPassTerms.setBounds(0, 40, 1330, 50);
			jtaPassTerms.setText("Let’s just get this one out of the way up front: "
					+ "We do not explicitly prohibit you from sharing your password"
					+ " with “other members of the household,” which you can interpret"
					+ " however you like. It does, however, state that, as the account holder,"
					+ " you are entirely responsible for whatever the people who have access to your account do with it");
			jtaPassTerms.setLineWrap(true);
			jtaPassTerms.setWrapStyleWord(true);
			jtaPassTerms.setEditable(false);

			jlGenTerms = new JLabel("The Rights and Obligatio​ns of the User:");
			jlGenTerms.setBackground(null);
			jlGenTerms.setForeground(Color.DARK_GRAY);
			jlGenTerms.setFont(new Font("Arial",Font.BOLD,14));
			jlGenTerms.setBorder(null);
			jlGenTerms.setBounds(0, 0, 350, 50);
			
			jtaGenTerms = new JTextArea();
			jtaGenTerms.setBackground(null);
			jtaGenTerms.setForeground(Color.DARK_GRAY);
			jtaGenTerms.setFont(new Font("Arial",Font.PLAIN,11));
			jtaGenTerms.setBounds(0, 40, 500, 500);
			jtaGenTerms.setText("The user account is to be used primarily to support accessing business intelligence, "
					+ "data in graphical representaion and doing data analysis."
					+ "The user account may not be used to seek out data security breaches, "
					+ "or for any other kind of system intrusion. "
					+ "The user must immediately notify the adminstration of any data security breaches he/she has noticed. "
					+ "Trying to obtain information on a text, image, data transfer "
					+ "or other similar electronic message the user has no permission to is forbidden "
					+ "and can lead to charges for a communications crime. "
					+ "The user is not allowed to give or transfer business data to  "
					+ "third party without a specific agreement with the Regional Heads. The user is required to abide "
					+ "by all instructions and regulations issued by the Sales Strategy personnel (SSP) concerning the use of the "
					+ "user account and the business intelligence services provided by the SSP. These secured data is "
					+ "hosted through our Cisco Data Centre, which reserves the rights to take action in case of breach. "
					+ "The user of the service is liable for damages caused by action contrary to this terms & conditions, "
					+ "or otherwise intentionally or accidentally caused by the user.");
			jtaGenTerms.setLineWrap(true);
			jtaGenTerms.setWrapStyleWord(true);
			jtaGenTerms.setEditable(false);
			
			footPanel.setBackground(null);
			footPanel.setLayout(null);
	        footPanel.setSize(1340,70);
	        footPanel.setLocation(10, 630);
			footPanel.add(jlPassTerms);
			footPanel.add(jtaPassTerms);
			
			rightPanel.setBackground(null);
			rightPanel.setLayout(null);
	        rightPanel.setSize(500,250);
	        rightPanel.setLocation(850, 90);
	        rightPanel.add(jlGenTerms);
	        rightPanel.add(jtaGenTerms);

	        jlSecImage1 = new JLabel(secImage1);
	        jlSecImage1.setBounds(800, 350, 250, 200);

	        jlSecImage2 = new JLabel(secImage2);
	        jlSecImage2.setBounds(1060, 450, 250, 200);

			
			mainPanel.setBackground(Color.WHITE);
			mainPanel.setLayout(null);
			mainPanel.add(bannerPanel);
			mainPanel.add(jlAccountInfoTitle);
			mainPanel.add(jlFirstName);
			mainPanel.add(jtfFirstName);
			mainPanel.add(jlLastName);
			mainPanel.add(jtfLastName);
			mainPanel.add(jlEmailID);
			mainPanel.add(jtfEmailID);
			mainPanel.add(jlConfirmEmailID);
			mainPanel.add(jtfConfirmEmailID);
			mainPanel.add(jlRole);
			mainPanel.add(jtfRole);
			mainPanel.add(euScrollPane);
			mainPanel.add(regionScrollPane);
			mainPanel.add(nodeScrollPane);
			mainPanel.add(jlDepartment);
			mainPanel.add(jtfDepartment);
			mainPanel.add(jlDivision);
			mainPanel.add(jtfDivision);
			mainPanel.add(jlReportingTo);
			mainPanel.add(jtfReportingTo);
			mainPanel.add(jlOldPassword);
			mainPanel.add(jpfOldPassword);
			mainPanel.add(jlNewPassword);
			mainPanel.add(jpfNewPassword);
			mainPanel.add(jlConfirmPassword);
			mainPanel.add(jpfConfirmPassword);
			mainPanel.add(jbSave);
			mainPanel.add(jbReset);
			mainPanel.add(rightPanel);
			mainPanel.add(footPanel);
			mainPanel.add(jlSecImage1);
			mainPanel.add(jlSecImage2);
			this.getRootPane().setDefaultButton(jbSave);
			setContentPane(mainPanel);
		}

		public class firstNameFocusListener implements FocusListener {
			public void focusGained(FocusEvent e) {
				setFocusBorder(jtfFirstName);
			}
			public void focusLost(FocusEvent e) {
				setLostBorder(jtfFirstName);
			}
		}
		
		public class lastNameFocusListener implements FocusListener {
			public void focusGained(FocusEvent e) {
				setFocusBorder(jtfLastName);
			}
			public void focusLost(FocusEvent e) {
				setLostBorder(jtfLastName);
			}
		}

		public class emailIDFocusListener implements FocusListener {
			public void focusGained(FocusEvent e) {
				setFocusBorder(jtfEmailID);
			}
			public void focusLost(FocusEvent e) {
				setLostBorder(jtfEmailID);
			}
		}

		public class departmentFocusListener implements FocusListener {
			public void focusGained(FocusEvent e) {
				setFocusBorder(jtfDepartment);
			}
			public void focusLost(FocusEvent e) {
				setLostBorder(jtfDepartment);
			}
		}
		
		public class divisionFocusListener implements FocusListener {
			public void focusGained(FocusEvent e) {
				setFocusBorder(jtfDivision);
			}
			public void focusLost(FocusEvent e) {
				setLostBorder(jtfDivision);
			}
		}

		public class ConfirmEmailIDFocusListener implements FocusListener {
			public void focusGained(FocusEvent e) {
				setFocusBorder(jtfConfirmEmailID);
			}
			public void focusLost(FocusEvent e) {
				setLostBorder(jtfConfirmEmailID);
			}
		}

		public class oldPasswordFocusListener implements FocusListener {
			public void focusGained(FocusEvent e) {
				setFocusBorder(jpfOldPassword);
			}
			public void focusLost(FocusEvent e) {
				setLostBorder(jpfOldPassword);
			}
		}

		public class newPasswordFocusListener implements FocusListener {
			public void focusGained(FocusEvent e) {
				setFocusBorder(jpfNewPassword);
			}
			public void focusLost(FocusEvent e) {
				setLostBorder(jpfNewPassword);
			}
		}

		public class confirmPasswordFocusListener implements FocusListener {
			public void focusGained(FocusEvent e) {
				setFocusBorder(jpfConfirmPassword);
			}
			public void focusLost(FocusEvent e) {
				setLostBorder(jpfConfirmPassword);
			}
		}
		
		public class roleFocusListener implements FocusListener {
			public void focusGained(FocusEvent e) {
				setFocusBorder(jtfRole);
			}
			public void focusLost(FocusEvent e) {
				setLostBorder(jtfRole);
			}
		}

		public class reportingToFocusListener implements FocusListener {
			public void focusGained(FocusEvent e) {
				setFocusBorder(jtfReportingTo);
			}
			public void focusLost(FocusEvent e) {
				setLostBorder(jtfReportingTo);
			}
		}
		
		private void setFocusBorder(JComponent comp) {
			comp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(150,200,225), 1, true),
					BorderFactory.createLineBorder(new Color(235,235,235), 2, false)));
		}
		private void setLostBorder(JComponent comp) {
			comp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,225,225), 1, true),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		}

		private class noThanksListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		}
		private class saveListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jbSave) {
					char[] tempPassOld = jpfOldPassword.getPassword();
					char[] tempPassNew = jpfNewPassword.getPassword();
					char[] tempPassConfirm = jpfConfirmPassword.getPassword();
					oldPassword = String.copyValueOf(tempPassOld);
					newPassword = String.copyValueOf(tempPassNew);
					confirmPassword = String.copyValueOf(tempPassConfirm);
					emailID = jtfEmailID.getText();
					firstName = jtfFirstName.getText();
					lastName = jtfLastName.getText();
					reportingTo = jtfReportingTo.getText();
					eUnit = tempEU;
					department = jtfDepartment.getText();
					division = jtfDivision.getText();
					region = tempRegion;
					role = jtfRole.getText();
					
					if (emailID.equals(jtfConfirmEmailID.getText())) {
						if (newPassword.equals(confirmPassword)) {
								try {
									userDataQueries = new DataBaseUserDataQueries();
									int tempCount;
									resultsUserData1 = 
											userDataQueries.getPrimarybyNameandPwd(DesktopPrimaryScreen.userID, oldPassword);
									tempCount = resultsUserData1.size();
									currentUserDataEntry1 = resultsUserData1.get(0);
								} 
								catch (SQLException e1) {
									e1.printStackTrace();
								}
								catch (NullPointerException e1) {
									e1.printStackTrace();
									JOptionPane.showMessageDialog(null, "Null Pointer Exception");
								} catch (InstantiationException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IllegalAccessException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
										if (resultsUserData1.size() != 0) {
											// go to Account Update Screen
										boolean updateStat = false;
										try {
												currentUserDataUpdateEntry = new DataBaseUserDataUpdate();
											} catch (InstantiationException
													| IllegalAccessException
													| ClassNotFoundException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											try {
												updateStat = currentUserDataUpdateEntry.activateUpdateAllbyNameandPwd(newPassword,
														firstName, lastName, emailID,  reportingTo, eUnit, department, division, 
														region, role, DesktopPrimaryScreen.userID, oldPassword);
											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											JOptionPane.showMessageDialog(null, "User Account data updated Successfully");;
											setVisible(false);
									} else {
										showErrorMessage();
									}
						} else { //
							setLostBorder(jtfConfirmEmailID);
							showNewConfirmPasswordErrorMessage();
						}
					} else {
						showEmailIDErrorMessage();
					}
					
				}
			}
		}

		private void showEmailIDErrorMessage() {
			jtfConfirmEmailID.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(245,10,10), 1, true),
					BorderFactory.createLineBorder(new Color(235,235,235), 2, false)));
			jtfConfirmEmailID.setText("");
			jtfConfirmEmailID.requestFocus();
			JOptionPane.showMessageDialog(null, "Confirm Email ID does not match with Email Id");
		}
		private void showNewConfirmPasswordErrorMessage() {
			jpfConfirmPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(245,10,10), 1, true),
					BorderFactory.createLineBorder(new Color(235,235,235), 2, false)));
			jpfConfirmPassword.setText("");
			jpfConfirmPassword.requestFocus();
			JOptionPane.showMessageDialog(null, "Confirm Password does not match with New Password");
		}
		private void showErrorMessage() {
			jpfOldPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(245,10,10), 1, true),
					BorderFactory.createLineBorder(new Color(235,235,235), 2, false)));
			jpfOldPassword.requestFocus();
		}
		private class closeApplication implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jbSignOut) {
					System.exit(0);
				}
			}
		}
}

