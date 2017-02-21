package prism14;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class StackRankersComponent {

	private JLabel name;
	private JLabel label;
	
	public StackRankersComponent(JLabel t, JLabel l) {
		this.name = t;
		this.label = l;
	}
	
	public JLabel getNameLabel() {
		return this.name;
	}

	public JLabel getValueLabel() {
		return this.label;
	}
}
