package prism14;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class TrafficLightPanel extends JPanel{
	private void doDrawing(Graphics g) {
		super.paintComponent(g);
		Graphics2D rLight = (Graphics2D) g;
		rLight.setColor(new Color(255,0,0));
		rLight.fillArc(125, 2, 15, 15, 0, 360);
		rLight.setColor(new Color(255,255,128));
		rLight.fillArc(175, 2, 15, 15, 0, 360);
		rLight.setColor(new Color(0,255,0));
		rLight.fillArc(225, 2, 15, 15, 0, 360);
	}
}
