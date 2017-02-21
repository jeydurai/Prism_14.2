package prism14;

import java.util.Comparator;

public class TopNames implements Comparator<TopNames>, Comparable<TopNames>{

	private String names;
	private double booking;
	
	public TopNames(String names, double booking) {
		this.names = names;
		this.booking = booking;
	}
	
	public TopNames() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return this.names;
	}
	
	public double getBooking() {
		return this.booking;
	}

	@Override
	public int compareTo(TopNames o) {
		return 0;
	}

	@Override
	public int compare(TopNames o1, TopNames o2) {
		return (int) (o2.booking-o1.booking);
	}
}
