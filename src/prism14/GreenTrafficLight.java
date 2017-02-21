package prism14;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GreenTrafficLight extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D rLight = (Graphics2D) g;
		rLight.setColor(new Color(50,50,50));
//		rLight.setColor(new Color(150,150,150));
		rLight.fillArc(100, 2, 15, 15, 0, 360);
		rLight.setColor(new Color(50,50,50));
//		rLight.setColor(new Color(150,150,150));
		rLight.fillArc(150, 2, 15, 15, 0, 360);
		rLight.setColor(new Color(0,255,0));
		rLight.fillArc(200, 2, 15, 15, 0, 360);
	}
}
