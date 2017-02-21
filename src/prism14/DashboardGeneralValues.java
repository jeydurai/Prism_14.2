package prism14;

import java.util.List;

import javax.swing.JOptionPane;

public class DashboardGeneralValues {

	private double totalBooking, totalList, cusPen, yldPerCus, entNWBooking,
	entNWList, secBook, secList, collBooking, collList, dcvBooking, dcvList, overAllDis, 
	entNWDis, secDis, collDis, dcvDis, techPen, atAttach, nonATAttach, m1, m2, m3, m4, m5, 
	m6, m7, m8, m9, m10, m11, m12, q1, q2, q3, q4, h1, h2, techANS, techDCS, techOth, techLAN, techRou,
	techSec, techSto, techUC, techUCS, techVid, techWLA;
	private List<TopNames> data;
	private double m1w1, m1w2, m1w3, m1w4,
	m2w1, m2w2, m2w3, m2w4, m3w1, m3w2, m3w3, m3w4, m3w5,
	m4w1, m4w2, m4w3, m4w4,
	m5w1, m5w2, m5w3, m5w4, m6w1, m6w2, m6w3, m6w4, m6w5,
	m7w1, m7w2, m7w3, m7w4,
	m8w1, m8w2, m8w3, m8w4, m9w1, m9w2, m9w3, m9w4, m9w5,
	m10w1, m10w2, m10w3, m10w4,
	m11w1, m11w2, m11w3, m11w4, m12w1, m12w2, m12w3, m12w4, m12w5;
	public DashboardGeneralValues(double tBooking, double tList, double cPen, 
			double eBook, double eList, double sBook, double sList,  double cBook, double cList, 
			double dBook, double dList, double techs, double atAttach, 
			double nonATAttach, double m1, double m2, double m3, double m4, double m5, double m6, 
			double m7, double m8, double m9, double m10, double m11, double m12, double q1, 
			double q2, double q3, double q4, double techANS, double techDCS, double techOth, 
			double techLAN, double techRou, double techSec, double techSto, double techUC, 
			double techUCS, double techVid, double techWLA, List<TopNames> data,
			double m1w1, double m1w2, double m1w3, double m1w4,
			double m2w1, double m2w2, double m2w3, double m2w4, double m3w1, 
			double m3w2, double m3w3, double m3w4, double m3w5,
			double m4w1, double m4w2, double m4w3, double m4w4,
			double m5w1, double m5w2, double m5w3, double m5w4, 
			double m6w1, double m6w2, double m6w3, double m6w4, double m6w5,
			double m7w1, double m7w2, double m7w3, double m7w4,
			double m8w1, double m8w2, double m8w3, double m8w4, double m9w1, 
			double m9w2, double m9w3, double m9w4, double m9w5, double m10w1, 
			double m10w2, double m10w3, double m10w4, double m11w1, double m11w2, 
			double m11w3, double m11w4, double m12w1, double m12w2, double m12w3, 
			double m12w4, double m12w5) {
		setBookingAll(tBooking);
		setListAll(tList);
		setDiscountAll(tBooking, tList);
		setYldPerCus(tBooking, cPen);
		setCusPen(cPen);
		setBookingENTNW(eBook);
		setListENTNW(eList);
		setDiscountENTNW(eBook, eList);
		setBookingSecurity(sBook);
		setListSecurity(sList);
		setDiscountSecurity(sBook, sList);
		setBookingCollab(cBook);
		setListCollab(cList);
		setDiscountCollab(cBook, cList);
		setBookingDCV(dBook);
		setListDCV(dList);
		setDiscountDCV(dBook, dList);
		setTechPen(techs, cPen);
		setATAttach(atAttach);
		setNonATAttach(nonATAttach);
		setM1(m1);
		setM2(m2);
		setM3(m3);
		setM4(m4);
		setM5(m5);
		setM6(m6);
		setM7(m7);
		setM8(m8);
		setM9(m9);
		setM10(m10);
		setM11(m11);
		setM12(m12);
		setQ1(q1);
		setQ2(q2);
		setQ3(q3);
		setQ4(q4);
		setH1(q1, q2);
		setH2(q3, q4);
		setANS(techANS);
		setDCSwitching(techDCS);
		setOthers(techOth);
		setLANSwitching(techLAN);
		setRouting(techRou);
		setSecurity(techSec);
		setStorage(techSto);
		setUC(techUC);
		setUCS(techUCS);
		setVideo(techVid);
		setWirelessLAN(techWLA);
		setTopData(data);
		this.m1w1 = m1w1;
		this.m1w2 = m1w2;
		this.m1w3 = m1w3;
		this.m1w4 = m1w4;
		this.m2w1 = m2w1;
		this.m2w2 = m2w2;
		this.m2w3 = m2w3;
		this.m2w4 = m2w4;
		this.m3w1 = m3w1;
		this.m3w2 = m3w2;
		this.m3w3 = m3w3;
		this.m3w4 = m3w4;
		this.m3w5 = m3w5;
		this.m4w1 = m4w1;
		this.m4w2 = m4w2;
		this.m4w3 = m4w3;
		this.m4w4 = m4w4;
		this.m5w1 = m5w1;
		this.m5w2 = m5w2;
		this.m5w3 = m5w3;
		this.m5w4 = m5w4;
		this.m6w1 = m6w1;
		this.m6w2 = m6w2;
		this.m6w3 = m6w3;
		this.m6w4 = m6w4;
		this.m6w5 = m6w5;
		this.m7w1 = m7w1;
		this.m7w2 = m7w2;
		this.m7w3 = m7w3;
		this.m7w4 = m7w4;
		this.m8w1 = m8w1;
		this.m8w2 = m8w2;
		this.m8w3 = m8w3;
		this.m8w4 = m8w4;
		this.m9w1 = m9w1;
		this.m9w2 = m9w2;
		this.m9w3 = m9w3;
		this.m9w4 = m9w4;
		this.m9w5 = m9w5;
		this.m10w1 = m10w1;
		this.m10w2 = m10w2;
		this.m10w3 = m10w3;
		this.m10w4 = m10w4;
		this.m11w1 = m11w1;
		this.m11w2 = m11w2;
		this.m11w3 = m11w3;
		this.m11w4 = m11w4;
		this.m12w1 = m12w1;
		this.m12w2 = m12w2;
		this.m12w3 = m12w3;
		this.m12w4 = m12w4;
		this.m12w5 = m12w5;
}
	
	private void setBookingAll(double val) {totalBooking = val;}
	private void setListAll(double val) {totalList = val;}
	private void setDiscountAll(double val1, double val2) {overAllDis = CalcHelper.getDiscount(val1, val2);}
	private void setYldPerCus(double val1, double val2) {yldPerCus = (val2 == 0) ? 0 : CalcHelper.getValueInThousandUSD(val1*1000000)/val2;}
	private void setCusPen(double val) {cusPen = val;}
	private void setBookingENTNW(double val) {entNWBooking = val;}
	private void setListENTNW(double val) {entNWList = val;}
	private void setDiscountENTNW(double val1, double val2) {entNWDis = CalcHelper.getDiscount(val1, val2);}
	private void setBookingSecurity(double val) {secBook = val;}
	private void setListSecurity(double val) {secList = val;}
	private void setDiscountSecurity(double val1, double val2) {secDis = CalcHelper.getDiscount(val1, val2);}
	private void setBookingCollab(double val) {collBooking = val;}
	private void setListCollab(double val) {collList = val;}
	private void setDiscountCollab(double val1, double val2) {collDis = CalcHelper.getDiscount(val1, val2);}
	private void setBookingDCV(double val) {dcvBooking = val;}
	private void setListDCV(double val) {dcvList = val;}
	private void setDiscountDCV(double val1, double val2) {dcvDis = CalcHelper.getDiscount(val1, val2);}
	private void setTechPen(double val1, double val2) {techPen = CalcHelper.getTechnologyPenetration(val1, val2);}
	private void setATAttach(double val) {atAttach = val;}
	private void setNonATAttach(double val) {nonATAttach = val;}
	private void setM1(double val) {m1 = val;}
	private void setM2(double val) {m2 = val;}
	private void setM3(double val) {m3 = val;}
	private void setM4(double val) {m4 = val;}
	private void setM5(double val) {m5 = val;}
	private void setM6(double val) {m6 = val;}
	private void setM7(double val) {m7 = val;}
	private void setM8(double val) {m8 = val;}
	private void setM9(double val) {m9 = val;}
	private void setM10(double val) {m10 = val;}
	private void setM11(double val) {m11 = val;}
	private void setM12(double val) {m12 = val;}
	private void setQ1(double val) {q1 = val;}
	private void setQ2(double val) {q2 = val;}
	private void setQ3(double val) {q3 = val;}
	private void setQ4(double val) {q4 = val;}
	private void setH1(double val1, double val2) {h1 = val1 + val2;}
	private void setH2(double val1, double val2) {h2 = val1 + val2;}
	private void setANS(double val) {this.techANS = val;}
	private void setDCSwitching(double val) {this.techDCS = val;}
	private void setOthers(double val) {this.techOth = val;}
	private void setLANSwitching(double val) {this.techLAN = val;}
	private void setRouting(double val) {this.techRou = val;}
	private void setSecurity(double val) {this.techSec = val;}
	private void setStorage(double val) {this.techSto = val;}
	private void setUC(double val) {this.techUC = val;}
	private void setUCS(double val) {this.techUCS = val;}
	private void setVideo(double val) {this.techVid = val;}
	private void setWirelessLAN(double val) {this.techWLA = val;}
	private void setTopData(List<TopNames> val) {this.data = val;}
	
	public double getBookingAll() {return totalBooking;}
	public double getListAll() {return totalList;}
	public double getDiscountAll() {return overAllDis;}
	public double getYldPerCus() {return yldPerCus;}
	public double getCusPen() {return cusPen;}
	public double getBookingENTNW() {return entNWBooking;}
	public double getListENTNW() {return entNWList;}
	public double getDiscountENTNW() {return entNWDis;}
	public double getBookingSecurity() {return secBook;}
	public double getListSecurity() {return secList;}
	public double getDiscountSecurity() {return secDis;}
	public double getBookingCollab() {return collBooking;}
	public double getListCollab() {return collList;}
	public double getDiscountCollab() {return collDis;}
	public double getBookingDCV() {return dcvBooking;}
	public double getListDCV() {return dcvList;}
	public double getDiscountDCV() {return dcvDis;}
	public double getTechPen() {return techPen;}
	public double getATAttach() {return atAttach;}
	public double getNonATAttach() {return nonATAttach;}
	public double getM1() {return this.m1;}
	public double getM2() {return this.m2;}
	public double getM3() {return this.m3;}
	public double getM4() {return this.m4;}
	public double getM5() {return this.m5;}
	public double getM6() {return this.m6;}
	public double getM7() {return this.m7;}
	public double getM8() {return this.m8;}
	public double getM9() {return this.m9;}
	public double getM10() {return this.m10;}
	public double getM11() {return this.m11;}
	public double getM12() {return this.m12;}
	public double getQ1() {return this.q1;}
	public double getQ2() {return this.q2;}
	public double getQ3() {return this.q3;}
	public double getQ4() {return this.q4;}
	public double getH1() {return h1;}
	public double getH2() {return h2;}
	public double getANS() {return this.techANS;}
	public double getDCSwitching() {return this.techDCS;}
	public double getOthers() {return this.techOth;}
	public double getLANSwitching() {return this.techLAN;}
	public double getRouting() {return this.techRou;}
	public double getSecurity() {return this.techSec;}
	public double getStorage() {return this.techSto;}
	public double getUC() {return this.techUC;}
	public double getUCS() {return this.techUCS;}
	public double getVideo() {return this.techVid;}
	public double getWirelessLAN() {return this.techWLA;}
	public List<TopNames> getTopData() {return this.data;}
	public double getM1W1() {return this.m1w1;}
	public double getM1W2() {return this.m1w2;}
	public double getM1W3() {return this.m1w3;}
	public double getM1W4() {return this.m1w4;}
	public double getM2W1() {return this.m2w1;}
	public double getM2W2() {return this.m2w2;}
	public double getM2W3() {return this.m2w3;}
	public double getM2W4() {return this.m2w4;}
	public double getM3W1() {return this.m3w1;}
	public double getM3W2() {return this.m3w2;}
	public double getM3W3() {return this.m3w3;}
	public double getM3W4() {return this.m3w4;}
	public double getM3W5() {return this.m3w5;}
	public double getM4W1() {return this.m4w1;}
	public double getM4W2() {return this.m4w2;}
	public double getM4W3() {return this.m4w3;}
	public double getM4W4() {return this.m4w4;}
	public double getM5W1() {return this.m5w1;}
	public double getM5W2() {return this.m5w2;}
	public double getM5W3() {return this.m5w3;}
	public double getM5W4() {return this.m5w4;}
	public double getM6W1() {return this.m6w1;}
	public double getM6W2() {return this.m6w2;}
	public double getM6W3() {return this.m6w3;}
	public double getM6W4() {return this.m6w4;}
	public double getM6W5() {return this.m6w5;}
	public double getM7W1() {return this.m7w1;}
	public double getM7W2() {return this.m7w2;}
	public double getM7W3() {return this.m7w3;}
	public double getM7W4() {return this.m7w4;}
	public double getM8W1() {return this.m8w1;}
	public double getM8W2() {return this.m8w2;}
	public double getM8W3() {return this.m8w3;}
	public double getM8W4() {return this.m8w4;}
	public double getM9W1() {return this.m9w1;}
	public double getM9W2() {return this.m9w2;}
	public double getM9W3() {return this.m9w3;}
	public double getM9W4() {return this.m9w4;}
	public double getM9W5() {return this.m9w5;}
	public double getM10W1() {return this.m10w1;}
	public double getM10W2() {return this.m10w2;}
	public double getM10W3() {return this.m10w3;}
	public double getM10W4() {return this.m10w4;}
	public double getM11W1() {return this.m11w1;}
	public double getM11W2() {return this.m11w2;}
	public double getM11W3() {return this.m11w3;}
	public double getM11W4() {return this.m11w4;}
	public double getM12W1() {return this.m12w1;}
	public double getM12W2() {return this.m12w2;}
	public double getM12W3() {return this.m12w3;}
	public double getM12W4() {return this.m12w4;}
	public double getM12W5() {return this.m12w5;}
}
