package prism14;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GradientLabel extends JLabel{
	private Point2D startPoint;
	private Point2D endPoint;
	protected boolean isDark;
	
	public GradientLabel(String text) {
		super(text);
		setOpaque(true);
		setDark(false);
	}
	public void setDark(boolean b) {
		isDark = b;
	}
	public void paint(Graphics oldG) {
		Graphics2D g = (Graphics2D)oldG;
//		super.paint(g);
		startPoint = new Point2D.Float(0, 0);
		endPoint = new Point2D.Float(0, getWidth());
		Color back = Color.white;
			if (isDark)
				back = Color.black;
		Paint gradientPaint = new GradientPaint(startPoint,back,endPoint,getBackground(),true);
		g.setPaint(gradientPaint);
//		g.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
		g.fillRect(0, 0, getWidth(), getHeight());
		
	}
}
