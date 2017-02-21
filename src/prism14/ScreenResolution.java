package prism14;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ScreenResolution {
	
	private String resText;
    private JScrollPane rPane;
    private JList resolutionList;
    private int resultDialog, fontSize;
    private String[] tempArr;
    private String fontFamily;
    private GeneralConstants.MonitorResolution mResolution;
    private double xProp, yProp;

	@SuppressWarnings("unchecked")
	public ScreenResolution() {
        fontSize = 11;
        fontFamily = "Arial";
        resolutionList = new JList();
        resolutionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resolutionList.setFont(new Font(fontFamily,Font.PLAIN,fontSize-1));
	    rPane = new JScrollPane(resolutionList,
	            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    rPane.setPreferredSize(new Dimension(290,100));

	    resolutionList.setModel(ComponentHelper.fillResolutionListBox());
	    resolutionList.setSelectedIndex(0);

	   resultDialog = JOptionPane.showConfirmDialog(null, rPane,"Select your screen resolution", 
			   JOptionPane.OK_CANCEL_OPTION);
	   tempArr = ComponentHelper.getListInArray(resolutionList);

		   
		   if (resultDialog == JOptionPane.OK_OPTION) {
			   resText=ComponentHelper.getSelectedListItem(resolutionList);
				   switch (resText) {
				   case "STANDARD_LAPTOP (1366 x 768)":
					   xProp = mResolution.STANDARD_LAPTOP_X.getValue();
					   yProp = mResolution.STANDARD_LAPTOP_Y.getValue();
					   break;
				   case "MAC (1920 x 1080)":
					   xProp = mResolution.MAC_X.getValue();
					   yProp = mResolution.MAC_Y.getValue();
					   break;
				   case "WIDER_DESKTOP (1440 x 900)":
					   xProp = mResolution.WIDER_DESKTOP_X.getValue();
					   yProp = mResolution.WIDER_DESKTOP_Y.getValue();
					   break;
				   case "WIDEST_DESKTOP (1600 x 900)":
					   xProp = mResolution.WIDEST_DESKTOP_X.getValue();
					   yProp = mResolution.WIDEST_DESKTOP_Y.getValue();
					   break;
				   case "STANDARD_DESKTOP (1280 x 1024)":
					   xProp = mResolution.STANDARD_DESKTOP_X.getValue();
					   yProp = mResolution.STANDARD_DESKTOP_Y.getValue();
					   break;
				   case "OLD_STANDARD_LAPTOP (1280 x 800)":
					   xProp = mResolution.OLD_STD_LAPTOP_X.getValue();
					   yProp = mResolution.OLD_STD_LAPTOP_Y.getValue();
					   break;
				   }
				   
				   login();
		   } else {
			   System.exit(0);
		   }
	}

	private void login() {
	    LoginScreen lScreen;
	    
	    lScreen = new LoginScreen(xProp, yProp);
	    lScreen.setSize(1350, 725);
	    lScreen.setLocation(0, 0);
	    lScreen.setUndecorated(false);
	    lScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    lScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    lScreen.setVisible(true);

	}

}
