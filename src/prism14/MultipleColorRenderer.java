package prism14;

import java.awt.Color;
import java.awt.Paint;

import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;

public class MultipleColorRenderer extends BarRenderer{

	public MultipleColorRenderer() {
		super();
	}
	
	public Paint getItemPaint(int xRow, int xCol ) {
		CategoryDataset dataset = getPlot().getDataset();
		String lRowKey = (String) dataset.getRowKey(xRow);
		String lColKey = (String) dataset.getColumnKey(xCol);
		double lValue = dataset.getValue(lRowKey, lColKey).doubleValue();
			if (lValue >= 75) {
				return Color.GREEN.darker();
			} if (lValue < 75 && lValue > 50) {
				return Color.YELLOW.darker();
			} else {
				return Color.RED.darker();
			}
	}
}
