package prism14;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class TopNamesComponent {

	private JButton toggle;
	private JLabel label;
	
	public TopNamesComponent(JButton t, JLabel l) {
		this.toggle = t;
		this.label = l;
	}
	
	public JButton getNameToggle() {
		return this.toggle;
	}

	public JLabel getToggleLabel() {
		return this.label;
	}
}
