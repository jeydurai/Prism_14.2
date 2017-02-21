package prism14;

import java.util.Comparator;

public class StackRankers implements Comparator<StackRankers>, Comparable<StackRankers>{

	private String names, node, eu, saType;
	private double value;
	
	public StackRankers(double value, String names, String node, String eu, String saType) {
		this.names = names;
		this.node = node;
		this.eu = eu;
		this.saType = saType;
		this.value = value;
	}
	
	public StackRankers() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return this.names;
	}

	public String getNode() {
		return this.node;
	}

	public String getExecutionUnit() {
		return this.eu;
	}

	public String getSalesAgentType() {
		return this.saType;
	}

	public double getValue() {
		return this.value;
	}

	@Override
	public int compareTo(StackRankers o) {
		 if(this.value < o.value) return 1;
         if(this.value > o.value) return -1;
         return 0;
	}

/*	@Override
	public int compare(StackRankers o1, StackRankers o2) {
		return (int) (o2.value-o1.value);
	}*/
	@Override
	public int compare(StackRankers o1, StackRankers o2) {
		double v1 = o1.value;
		double v2 = o2.value;

		if (v1 == v2)
			return 0;
		else if (v1 > v2)
			return -1;
		else
			return 1;
	}
}
