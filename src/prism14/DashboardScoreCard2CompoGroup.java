package prism14;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardScoreCard2CompoGroup {

	private JPanel panel, legendPanel;
	private JLabel title, mark, score;
	private double point;
	
	public DashboardScoreCard2CompoGroup(JPanel panel, JLabel title, JLabel mark, 
			JLabel score, JPanel lPanel, double point) {
		this.panel = panel;
		this.title = title;
		this.point = point;
		this.legendPanel = lPanel;
		this.mark = mark;
		this.score = score;
	}
	
	public JPanel getFrame() {
		return this.panel;
	}
	public JPanel getLegend() {
		return this.legendPanel;
	}
	public JLabel getTitle() {
		return this.title;
	}
	public double getMetricPoint() {
		return this.point;
	}
	public JLabel getMark() {
		return this.mark;
	}
	public JLabel getScore() {
		return this.score;
	}

}
