package prism14;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.lowagie.text.Font;
import com.thehowtotutorial.splashscreen.JSplash;

public class Prism14 {
    public static void main(String[] args) {
        // TODO code application logic here
        try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch(UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(InstantiationException e) {
            e.printStackTrace();
        }
        catch(IllegalAccessException e) {
            e.printStackTrace();
        }

       try {
        JSplash splash = new JSplash(Prism14.class.getResource(GeneralConstants.SPLASH_IMAGE), 
        		true, true, false, 
        		"FY14 Cisco-PLB. " + GeneralConstants.VERSION, 
        		null, Color.BLUE, new Color(10,255,10,255));
        splash.splashOn();
        // Call Method
        splash.setProgress(20, "Initializing...");
        Thread.sleep(1250);
        splash.setProgress(40, "Loading...");
        Thread.sleep(1250);
        splash.setProgress(60, "Applying...");
        Thread.sleep(1250);
        splash.setProgress(80, "Starting...");
        Thread.sleep(1250);
        splash.setProgress(100, "Opening...");
        Thread.sleep(1250);
        splash.splashOff();
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
/*       LoginScreen lScreen;
        
       lScreen = new LoginScreen();
       lScreen.setSize(1350, 725);
       lScreen.setLocation(0, 0);
       lScreen.setUndecorated(false);
       lScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
       lScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       lScreen.setVisible(true);*/
       
       new ScreenResolution();
       
    }
    
}
