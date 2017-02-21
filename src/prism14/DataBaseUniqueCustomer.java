package prism14;

public class DataBaseUniqueCustomer {

	private String node, customer, technology;
	private double atAttach, nonATAttach, bookAll, listAll, 
	archBookColl, archListColl,archBookDCV, archListDCV,
	archBookENTNW, archListENTNW, archBookSec, archListSec,
	techANS, techDCS, techLAN, techOth, techRou, techSec, techSto,
	techUC, techUCS, techVid, techWLA, m1, m2, m3, m4, m5, m6, m7, 
	m8, m9, m10, m11, m12, q1, q2, q3, q4, h1, h2, 
	m1W1, m1W2, m1W3, m1W4,
	m2W1, m2W2, m2W3, m2W4, 
	m3W1, m3W2, m3W3, m3W4, m3W5, 
	m4W1, m4W2, m4W3, m4W4,
	m5W1, m5W2, m5W3, m5W4, 
	m6W1, m6W2, m6W3, m6W4, m6W5, 
	m7W1, m7W2, m7W3, m7W4,
	m8W1, m8W2, m8W3, m8W4, 
	m9W1, m9W2, m9W3, m9W4, m9W5, 
	m10W1, m10W2, m10W3, m10W4,
	m11W1, m11W2, m11W3, m11W4, 
	m12W1, m12W2, m12W3, m12W4, m12W5;
	
	public DataBaseUniqueCustomer(String node,  
	String customer, String technology, double atAttach, double nonATAttach, double bookAll, 
	double listAll, double archBookColl, double archListColl, 
	double archBookDCV, double archListDCV,	double archBookENTNW, 
	double archListENTNW, double archBookSec, double archListSec, 
	double techANS, double techDCS, double techLAN, double techOth, 
	double techRou, double techSec, double techSto, double techUC, 
	double techUCS, double techVid, double techWLA, double m1, double m2, 
	double m3, double m4, double m5, double m6, double m7, double m8, 
	double m9, double m10, double m11, double m12, double q1, double q2, 
	double q3, double q4, double m1W1, double m1W2, double m1W3, double m1W4,
	double m2W1, double m2W2, double m2W3, double m2W4, 
	double m3W1, double m3W2, double m3W3, double m3W4, double m3W5, 
	double m4W1, double m4W2, double m4W3, double m4W4,
	double m5W1, double m5W2, double m5W3, double m5W4, 
	double m6W1, double m6W2, double m6W3, double m6W4, double m6W5, 
	double m7W1, double m7W2, double m7W3, double m7W4,
	double m8W1, double m8W2, double m8W3, double m8W4, 
	double m9W1, double m9W2, double m9W3, double m9W4, double m9W5, 
	double m10W1, double m10W2, double m10W3, double m10W4,
	double m11W1, double m11W2, double m11W3, double m11W4, 
	double m12W1, double m12W2, double m12W3, double m12W4, double m12W5) {

		setSalesLevel6(node);
		setCustomer(customer);
		setTechnology(technology);
		setATAttach(atAttach);
		setNonATAttach(nonATAttach);
		setBookingAll(bookAll);
		setListAll(listAll);
		setArchBookingCollab(archBookColl);
		setArchListCollab(archListColl);
		setArchBookingDCV(archBookDCV);
		setArchListDCV(archListDCV);
		setArchBookingENTNetWorking(archBookENTNW); 
		setArchListENTNetWorking(archListENTNW);
		setArchBookingSecurity(archBookSec);
		setArchListSecurity(archListSec);
		setANS(techANS);
		setDCSwitching(techDCS);
		setLANSwitching(techLAN);
		setOthersTechnology(techOth); 
		setRouting(techRou);
		setSecurity(techSec);
		setStorage(techSto);
		setUC(techUC); 
		setUCS(techUCS);
		setVideo(techVid);
		setWirelessLAN(techWLA);
		setBookingM1(m1);
		setBookingM2(m2); 
		setBookingM3(m3);
		setBookingM4(m4);
		setBookingM5(m5);
		setBookingM6(m6);
		setBookingM7(m7);
		setBookingM8(m8); 
		setBookingM9(m9);
		setBookingM10(m10);
		setBookingM11(m11);
		setBookingM12(m12);
		setBookingQ1(q1);
		setBookingQ2(q2); 
		setBookingQ3(q3);
		setBookingQ4(q4);
		setBookingH1(q1, q2);
		setBookingH2(q3, q4);
		setBookingM1W1(m1W1); 
		setBookingM1W2(m1W2);
		setBookingM1W3(m1W3);
		setBookingM1W4(m1W4);
		setBookingM2W1(m2W1);
		setBookingM2W2(m2W2);
		setBookingM2W3(m2W3);
		setBookingM2W4(m2W4); 
		setBookingM3W1(m3W1);
		setBookingM3W2(m3W2);
		setBookingM3W3(m3W3);
		setBookingM3W4(m3W4);
		setBookingM3W5(m3W5); 
		setBookingM4W1(m4W1); 
		setBookingM4W2(m4W2);
		setBookingM4W3(m4W3);
		setBookingM4W4(m4W4);
		setBookingM5W1(m5W1);
		setBookingM5W2(m5W2);
		setBookingM5W3(m5W3);
		setBookingM5W4(m5W4); 
		setBookingM6W1(m6W1);
		setBookingM6W2(m6W2);
		setBookingM6W3(m6W3);
		setBookingM6W4(m6W4);
		setBookingM6W5(m6W5); 
		setBookingM7W1(m7W1); 
		setBookingM7W2(m7W2);
		setBookingM7W3(m7W3);
		setBookingM7W4(m7W4);
		setBookingM8W1(m8W1);
		setBookingM8W2(m8W2);
		setBookingM8W3(m8W3);
		setBookingM8W4(m8W4); 
		setBookingM9W1(m9W1);
		setBookingM9W2(m9W2);
		setBookingM9W3(m9W3);
		setBookingM9W4(m9W4);
		setBookingM9W5(m9W5); 
		setBookingM10W1(m10W1); 
		setBookingM10W2(m10W2);
		setBookingM10W3(m10W3);
		setBookingM10W4(m10W4);
		setBookingM11W1(m11W1);
		setBookingM11W2(m11W2);
		setBookingM11W3(m11W3);
		setBookingM11W4(m11W4); 
		setBookingM12W1(m12W1);
		setBookingM12W2(m12W2);
		setBookingM12W3(m12W3);
		setBookingM12W4(m12W4);
		setBookingM12W5(m12W5); 
	}
	
	//set Methods
	public void setSalesLevel6(String node) {this.node = node;}
	public void setCustomer(String customer) {this.customer = customer;}
	public void setTechnology(String technology) {this.technology = technology;}
	public void setATAttach(double atAttach) {this.atAttach = atAttach;}
	public void setNonATAttach(double nonATAttach) {this.nonATAttach = nonATAttach;}
	public void setBookingAll(double bookAll) {this.bookAll = bookAll;}
	public void setListAll(double listAll) {this.listAll = listAll;}
	public void setArchBookingCollab(double archBookColl) {this.archBookColl = archBookColl;}
	public void setArchListCollab(double archListColl) {this.archListColl = archListColl;}
	public void setArchBookingDCV(double archBookDCV) {this.archBookDCV = archBookDCV;}
	public void setArchListDCV(double archListDCV) {this.archListDCV = archListDCV;}
	public void setArchBookingENTNetWorking(double archBookENTNW) {this.archBookENTNW = archBookENTNW;} 
	public void setArchListENTNetWorking(double archListENTNW) {this.archListENTNW = archListENTNW;}
	public void setArchBookingSecurity(double archBookSec) {this.archBookSec = archBookSec;}
	public void setArchListSecurity(double archListSec) {this.archListSec = archListSec;}
	public void setANS(double ANS) {this.techANS = ANS;}
	public void setDCSwitching(double techDCS) {this.techDCS = techDCS;}
	public void setLANSwitching(double techLAN) {this.techLAN = techLAN;}
	public void setOthersTechnology(double techOth) {this.techOth = techOth;} 
	public void setRouting(double techRou) {this.techRou = techRou;}
	public void setSecurity(double techSec) {this.techSec = techSec;}
	public void setStorage(double techSto) {this.techSto = techSto;}
	public void setUC(double techUC) {this.techUC = techUC;} 
	public void setUCS(double techUCS) {this.techUCS = techUCS;}
	public void setVideo(double techVid) {this.techVid = techVid;}
	public void setWirelessLAN(double techWLA) {this.techWLA = techWLA;}
	public void setBookingM1(double m1) {this.m1 = m1;}
	public void setBookingM2(double m2) {this.m2 = m2;} 
	public void setBookingM3(double m3) {this.m3 = m3;}
	public void setBookingM4(double m4) {this.m4 = m4;}
	public void setBookingM5(double m5) {this.m5 = m5;}
	public void setBookingM6(double m6) {this.m6 = m6;}
	public void setBookingM7(double m7) {this.m7 = m7;}
	public void setBookingM8(double m8) {this.m8 = m8;} 
	public void setBookingM9(double m9) {this.m9 = m9;}
	public void setBookingM10(double m10) {this.m10 = m10;}
	public void setBookingM11(double m11) {this.m11 = m11;}
	public void setBookingM12(double m12) {this.m12 = m12;}
	public void setBookingQ1(double q1) {this.q1 = q1;}
	public void setBookingQ2(double q2) {this.q2 = q2;} 
	public void setBookingQ3(double q3) {this.q3 = q3;}
	public void setBookingQ4(double q4) {this.q4 = q4;}
	public void setBookingH1(double q1, double q2) {this.h1 = q1+q2;}
	public void setBookingH2(double q3, double q4) {this.h2 = q3+q4;}
	public void setBookingM1W1(double m1W1) {this.m1W1 = m1W1;} 
	public void setBookingM1W2(double m1W2) {this.m1W2 = m1W2;} 
	public void setBookingM1W3(double m1W3) {this.m1W3 = m1W3;} 
	public void setBookingM1W4(double m1W4) {this.m1W4 = m1W4;} 
	public void setBookingM2W1(double m2W1) {this.m2W1 = m2W1;} 
	public void setBookingM2W2(double m2W2) {this.m2W2 = m2W2;} 
	public void setBookingM2W3(double m2W3) {this.m2W3 = m2W3;} 
	public void setBookingM2W4(double m2W4) {this.m2W4 = m2W4;} 
	public void setBookingM3W1(double m3W1) {this.m3W1 = m3W1;} 
	public void setBookingM3W2(double m3W2) {this.m3W2 = m3W2;} 
	public void setBookingM3W3(double m3W3) {this.m3W3 = m3W3;} 
	public void setBookingM3W4(double m3W4) {this.m3W4 = m3W4;} 
	public void setBookingM3W5(double m3W5) {this.m3W5 = m3W5;} 
	public void setBookingM4W1(double m4W1) {this.m4W1 = m4W1;} 
	public void setBookingM4W2(double m4W2) {this.m4W2 = m4W2;} 
	public void setBookingM4W3(double m4W3) {this.m4W3 = m4W3;} 
	public void setBookingM4W4(double m4W4) {this.m4W4 = m4W4;} 
	public void setBookingM5W1(double m5W1) {this.m5W1 = m5W1;} 
	public void setBookingM5W2(double m5W2) {this.m5W2 = m5W2;} 
	public void setBookingM5W3(double m5W3) {this.m5W3 = m5W3;} 
	public void setBookingM5W4(double m5W4) {this.m5W4 = m5W4;} 
	public void setBookingM6W1(double m6W1) {this.m6W1 = m6W1;} 
	public void setBookingM6W2(double m6W2) {this.m6W2 = m6W2;} 
	public void setBookingM6W3(double m6W3) {this.m6W3 = m6W3;} 
	public void setBookingM6W4(double m6W4) {this.m6W4 = m6W4;} 
	public void setBookingM6W5(double m6W5) {this.m6W5 = m6W5;} 
	public void setBookingM7W1(double m7W1) {this.m7W1 = m7W1;} 
	public void setBookingM7W2(double m7W2) {this.m7W2 = m7W2;} 
	public void setBookingM7W3(double m7W3) {this.m7W3 = m7W3;} 
	public void setBookingM7W4(double m7W4) {this.m7W4 = m7W4;} 
	public void setBookingM8W1(double m8W1) {this.m8W1 = m8W1;} 
	public void setBookingM8W2(double m8W2) {this.m8W2 = m8W2;} 
	public void setBookingM8W3(double m8W3) {this.m8W3 = m8W3;} 
	public void setBookingM8W4(double m8W4) {this.m8W4 = m8W4;} 
	public void setBookingM9W1(double m9W1) {this.m9W1 = m9W1;} 
	public void setBookingM9W2(double m9W2) {this.m9W2 = m9W2;} 
	public void setBookingM9W3(double m9W3) {this.m9W3 = m9W3;} 
	public void setBookingM9W4(double m9W4) {this.m9W4 = m9W4;} 
	public void setBookingM9W5(double m9W5) {this.m9W5 = m9W5;} 
	public void setBookingM10W1(double m10W1) {this.m10W1 = m10W1;} 
	public void setBookingM10W2(double m10W2) {this.m10W2 = m10W2;} 
	public void setBookingM10W3(double m10W3) {this.m10W3 = m10W3;} 
	public void setBookingM10W4(double m10W4) {this.m10W4 = m10W4;} 
	public void setBookingM11W1(double m11W1) {this.m11W1 = m11W1;} 
	public void setBookingM11W2(double m11W2) {this.m11W2 = m11W2;} 
	public void setBookingM11W3(double m11W3) {this.m11W3 = m11W3;} 
	public void setBookingM11W4(double m11W4) {this.m11W4 = m11W4;} 
	public void setBookingM12W1(double m12W1) {this.m12W1 = m12W1;} 
	public void setBookingM12W2(double m12W2) {this.m12W2 = m12W2;} 
	public void setBookingM12W3(double m12W3) {this.m12W3 = m12W3;} 
	public void setBookingM12W4(double m12W4) {this.m12W4 = m12W4;} 
	public void setBookingM12W5(double m12W5) {this.m12W5 = m12W5;} 

	//get Methods
	public String getSalesLevel6() {return this.node;}
	public String getCustomer() {return this.customer;}
	public String getTechnology() {return this.technology;}
	public double getATAttach() {return this.atAttach;}
	public double getNonATAttach() {return this.nonATAttach;}
	public double getBookingAll() {return this.bookAll;}
	public double getListAll() {return this.listAll;}
	public double getArchBookingCollab() {return this.archBookColl;}
	public double getArchListCollab() {return this.archListColl;}
	public double getArchBookingDCV() {return this.archBookDCV;}
	public double getArchListDCV() {return this.archListDCV;}
	public double getArchBookingENTNetWorking() {return this.archBookENTNW;} 
	public double getArchListENTNetWorking() {return this.archListENTNW;}
	public double getArchBookingSecurity() {return this.archBookSec;}
	public double getArchListSecurity() {return this.archListSec;}
	public double getANS() {return this.techANS;}
	public double getDCSwitching() {return this.techDCS;}
	public double getLANSwitching() {return this.techLAN;}
	public double getOthersTechnology() {return this.techOth;} 
	public double getRouting() {return this.techRou;}
	public double getSecurity() {return this.techSec;}
	public double getStorage() {return this.techSto;}
	public double getUC() {return this.techUC;} 
	public double getUCS() {return this.techUCS;}
	public double getVideo() {return this.techVid;}
	public double getWirelessLAN() {return this.techWLA;}
	public double getBookingM1() {return this.m1;}
	public double getBookingM2() {return this.m2;} 
	public double getBookingM3() {return this.m3;}
	public double getBookingM4() {return this.m4;}
	public double getBookingM5() {return this.m5;}
	public double getBookingM6() {return this.m6;}
	public double getBookingM7() {return this.m7;}
	public double getBookingM8() {return this.m8;} 
	public double getBookingM9() {return this.m9;}
	public double getBookingM10() {return this.m10;}
	public double getBookingM11() {return this.m11;}
	public double getBookingM12() {return this.m12;}
	public double getBookingQ1() {return this.q1;}
	public double getBookingQ2() {return this.q2;} 
	public double getBookingQ3() {return this.q3;}
	public double getBookingQ4() {return this.q4;}
	public double getBookingH1() {return this.h1;}
	public double getBookingH2() {return this.h2;}
	public double getBookingM1W1() {return this.m1W1;} 
	public double getBookingM1W2() {return this.m1W2;} 
	public double getBookingM1W3() {return this.m1W3;} 
	public double getBookingM1W4() {return this.m1W4;} 
	public double getBookingM2W1() {return this.m2W1;} 
	public double getBookingM2W2() {return this.m2W2;} 
	public double getBookingM2W3() {return this.m2W3;} 
	public double getBookingM2W4() {return this.m2W4;} 
	public double getBookingM3W1() {return this.m3W1;} 
	public double getBookingM3W2() {return this.m3W2;} 
	public double getBookingM3W3() {return this.m3W3;} 
	public double getBookingM3W4() {return this.m3W4;} 
	public double getBookingM3W5() {return this.m3W5;} 
	public double getBookingM4W1() {return this.m4W1;} 
	public double getBookingM4W2() {return this.m4W2;} 
	public double getBookingM4W3() {return this.m4W3;} 
	public double getBookingM4W4() {return this.m4W4;} 
	public double getBookingM5W1() {return this.m5W1;} 
	public double getBookingM5W2() {return this.m5W2;} 
	public double getBookingM5W3() {return this.m5W3;} 
	public double getBookingM5W4() {return this.m5W4;} 
	public double getBookingM6W1() {return this.m6W1;} 
	public double getBookingM6W2() {return this.m6W2;} 
	public double getBookingM6W3() {return this.m6W3;} 
	public double getBookingM6W4() {return this.m6W4;} 
	public double getBookingM6W5() {return this.m6W5;} 
	public double getBookingM7W1() {return this.m7W1;} 
	public double getBookingM7W2() {return this.m7W2;} 
	public double getBookingM7W3() {return this.m7W3;} 
	public double getBookingM7W4() {return this.m7W4;} 
	public double getBookingM8W1() {return this.m8W1;} 
	public double getBookingM8W2() {return this.m8W2;} 
	public double getBookingM8W3() {return this.m8W3;} 
	public double getBookingM8W4() {return this.m8W4;} 
	public double getBookingM9W1() {return this.m9W1;} 
	public double getBookingM9W2() {return this.m9W2;} 
	public double getBookingM9W3() {return this.m9W3;} 
	public double getBookingM9W4() {return this.m9W4;} 
	public double getBookingM9W5() {return this.m9W5;} 
	public double getBookingM10W1() {return this.m10W1;} 
	public double getBookingM10W2() {return this.m10W2;} 
	public double getBookingM10W3() {return this.m10W3;} 
	public double getBookingM10W4() {return this.m10W4;} 
	public double getBookingM11W1() {return this.m11W1;} 
	public double getBookingM11W2() {return this.m11W2;} 
	public double getBookingM11W3() {return this.m11W3;} 
	public double getBookingM11W4() {return this.m11W4;} 
	public double getBookingM12W1() {return this.m12W1;} 
	public double getBookingM12W2() {return this.m12W2;} 
	public double getBookingM12W3() {return this.m12W3;} 
	public double getBookingM12W4() {return this.m12W4;} 
	public double getBookingM12W5() {return this.m12W5;} 

}
