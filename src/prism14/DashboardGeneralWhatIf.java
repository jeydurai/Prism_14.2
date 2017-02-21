package prism14;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;

public class DashboardGeneralWhatIf {

	private DashboardGeneralValues values;
	private double techANSSplit, techDCSSplit, techOthersSplit, techLANSSplit,
	techRoutingSplit, techSecuritySplit, techStorageSplit, techUCSplit, techUCSSplit, 
	techVideoSplit, techWLANSplit;
	private double archCollabSplit, archDCVSplit, archENTNWSplit, archSecuritySplit;
	private double collabUCSplit, collabVideoSplit, dcvANSSplit, dcvUCSSplit, dcvStorageSplit,
	dcvDCSSplit; 
	private double securitySecuritySplit, entNWOthersSplit, entNWLANSSplit, entNWRoutingSplit, 
	entNWWLANSplit; 
	private double m1Split, m2Split, m3Split, m4Split, m5Split, m6Split, m7Split, m8Split, m9Split, 
	m10Split, m11Split, m12Split, h1Split, h2Split, q1Split, q2Split, q3Split, q4Split;
	private double m1w1Split, m1w2Split, m1w3Split, m1w4Split;
	private double m2w1Split, m2w2Split, m2w3Split, m2w4Split;
	private double m3w1Split, m3w2Split, m3w3Split, m3w4Split, m3w5Split;
	private double m4w1Split, m4w2Split, m4w3Split, m4w4Split;
	private double m5w1Split, m5w2Split, m5w3Split, m5w4Split;
	private double m6w1Split, m6w2Split, m6w3Split, m6w4Split, m6w5Split;
	private double m7w1Split, m7w2Split, m7w3Split, m7w4Split;
	private double m8w1Split, m8w2Split, m8w3Split, m8w4Split;
	private double m9w1Split, m9w2Split, m9w3Split, m9w4Split, m9w5Split;
	private double m10w1Split, m10w2Split, m10w3Split, m10w4Split;
	private double m11w1Split, m11w2Split, m11w3Split, m11w4Split;
	private double m12w1Split, m12w2Split, m12w3Split, m12w4Split, m12w5Split;
	
	private double totalBooking, cusPen, yldPerCus, entNWBooking,
	secBook, collBooking, dcvBooking, 
	totalList, entNWList, secList, collList, dcvList, overAllDis,
	entNWDis, secDis, collDis, dcvDis, techPen, atAttach, nonATAttach, m1, m2, m3, m4, m5, 
	m6, m7, m8, m9, m10, m11, m12, q1, q2, q3, q4, h1, h2, techANS, techDCS, techOth, techLAN, techRou,
	techSec, techSto, techUC, techUCS, techVid, techWLA;
	private double m1w1, m1w2, m1w3, m1w4,
	m2w1, m2w2, m2w3, m2w4, m3w1, m3w2, m3w3, m3w4, m3w5,
	m4w1, m4w2, m4w3, m4w4,
	m5w1, m5w2, m5w3, m5w4, m6w1, m6w2, m6w3, m6w4, m6w5,
	m7w1, m7w2, m7w3, m7w4,
	m8w1, m8w2, m8w3, m8w4, m9w1, m9w2, m9w3, m9w4, m9w5,
	m10w1, m10w2, m10w3, m10w4,
	m11w1, m11w2, m11w3, m11w4, m12w1, m12w2, m12w3, m12w4, m12w5;

	public DashboardGeneralWhatIf(DashboardGeneralValues val, double whatIfValue) {
		
		setTechSplits(val.getANS(), val.getDCSwitching(), val.getOthers(), val.getLANSwitching(),
				val.getRouting(), val.getSecurity(), val.getStorage(), val.getUC(), val.getUCS(),
				val.getVideo(), val.getWirelessLAN());
		setArchSplits(val.getBookingAll(), val.getBookingCollab(), val.getBookingDCV(), 
				val.getBookingENTNW(), val.getBookingSecurity());
		setCollabSplits(val.getBookingCollab(), val.getUC(), val.getVideo());
		setDCVSplits(val.getBookingDCV(), val.getANS(), val.getUCS(), val.getStorage(),
				val.getDCSwitching());
		securitySecuritySplit = (val.getBookingSecurity()!=0) ? 1D : 0D;
		
		setENTNWSplits(val.getBookingENTNW(), val.getOthers(), val.getLANSwitching(), val.getRouting(), 
				val.getWirelessLAN());
		setMonthSplits(val.getBookingAll(), val.getM1(), val.getM2(), val.getM3(), val.getM4(), val.getM5(), val.getM6(), 
				val.getM7(), val.getM8(), val.getM9(), val.getM10(), val.getM11(), val.getM12());
		setM1WeekSplits(val.getM1(), val.getM1W1(), val.getM1W2(), val.getM1W3(), val.getM1W4());
		setM2WeekSplits(val.getM2(), val.getM2W1(), val.getM2W2(), val.getM2W3(), val.getM2W4());
		setM3WeekSplits(val.getM3(), val.getM3W1(), val.getM3W2(), val.getM3W3(), val.getM3W4(), val.getM3W5());
		setM4WeekSplits(val.getM4(), val.getM4W1(), val.getM4W2(), val.getM4W3(), val.getM4W4());
		setM5WeekSplits(val.getM5(), val.getM5W1(), val.getM5W2(), val.getM5W3(), val.getM5W4());
		setM6WeekSplits(val.getM6(), val.getM6W1(), val.getM6W2(), val.getM6W3(), val.getM6W4(), val.getM6W5());
		setM7WeekSplits(val.getM7(), val.getM7W1(), val.getM7W2(), val.getM7W3(), val.getM7W4());
		setM8WeekSplits(val.getM8(), val.getM8W1(), val.getM8W2(), val.getM8W3(), val.getM8W4());
		setM9WeekSplits(val.getM9(), val.getM9W1(), val.getM9W2(), val.getM9W3(), val.getM9W4(), val.getM9W5());
		setM10WeekSplits(val.getM10(), val.getM10W1(), val.getM10W2(), val.getM10W3(), val.getM10W4());
		setM11WeekSplits(val.getM11(), val.getM11W1(), val.getM11W2(), val.getM11W3(), val.getM11W4());
		setM12WeekSplits(val.getM12(), val.getM12W1(), val.getM12W2(), val.getM12W3(), val.getM12W4(), val.getM12W5());
		
		setHalfYearSplits(val.getBookingAll(), val.getH1(), val.getH2());
		setQuarterSplits(val.getBookingAll(), val.getQ1(), val.getQ2(), val.getQ3(), val.getQ4());
		
		totalBooking = val.getBookingAll() + (val.getBookingAll() * whatIfValue);
		setTechnologies(totalBooking);
		setArchitectures(totalBooking);
		this.atAttach = val.getATAttach();
		this.nonATAttach = val.getNonATAttach();
		this.cusPen = val.getCusPen();
		this.techPen = val.getTechPen();
		setDiscounts(val.getListAll(), val.getListCollab(), val.getListDCV(), 
				val.getListENTNW(), val.getListSecurity());
		setYldPerCus(totalBooking, val.getCusPen());
		setQuarterValues(totalBooking);
		setHalfYearValues(totalBooking);
		setMonthValues(totalBooking);
		setWeekValues(this.m1, this.m2, this.m3, this.m4, this.m5, this.m6, this.m7, 
				this.m8, this.m9, this.m10, this.m11, this.m12);
	}

	public DashboardGeneralWhatIf(DashboardGeneralValues val, String option, 
			WhatIfDataTransfer whatIfValue) {
		
		setTechSplits(val.getANS(), val.getDCSwitching(), val.getOthers(), val.getLANSwitching(),
				val.getRouting(), val.getSecurity(), val.getStorage(), val.getUC(), val.getUCS(),
				val.getVideo(), val.getWirelessLAN());
		setArchSplits(val.getBookingAll(), val.getBookingCollab(), val.getBookingDCV(), 
				val.getBookingENTNW(), val.getBookingSecurity());
		setCollabSplits(val.getBookingCollab(), val.getUC(), val.getVideo());
		setDCVSplits(val.getBookingDCV(), val.getANS(), val.getUCS(), val.getStorage(),
				val.getDCSwitching());
		securitySecuritySplit = (val.getBookingSecurity()!=0) ? 1D : 0D;
		
		setENTNWSplits(val.getBookingENTNW(), val.getOthers(), val.getLANSwitching(), val.getRouting(), 
				val.getWirelessLAN());
		setMonthSplits(val.getBookingAll(), val.getM1(), val.getM2(), val.getM3(), val.getM4(), val.getM5(), val.getM6(), 
				val.getM7(), val.getM8(), val.getM9(), val.getM10(), val.getM11(), val.getM12());
		setM1WeekSplits(val.getM1(), val.getM1W1(), val.getM1W2(), val.getM1W3(), val.getM1W4());
		setM2WeekSplits(val.getM2(), val.getM2W1(), val.getM2W2(), val.getM2W3(), val.getM2W4());
		setM3WeekSplits(val.getM3(), val.getM3W1(), val.getM3W2(), val.getM3W3(), val.getM3W4(), val.getM3W5());
		setM4WeekSplits(val.getM4(), val.getM4W1(), val.getM4W2(), val.getM4W3(), val.getM4W4());
		setM5WeekSplits(val.getM5(), val.getM5W1(), val.getM5W2(), val.getM5W3(), val.getM5W4());
		setM6WeekSplits(val.getM6(), val.getM6W1(), val.getM6W2(), val.getM6W3(), val.getM6W4(), val.getM6W5());
		setM7WeekSplits(val.getM7(), val.getM7W1(), val.getM7W2(), val.getM7W3(), val.getM7W4());
		setM8WeekSplits(val.getM8(), val.getM8W1(), val.getM8W2(), val.getM8W3(), val.getM8W4());
		setM9WeekSplits(val.getM9(), val.getM9W1(), val.getM9W2(), val.getM9W3(), val.getM9W4(), val.getM9W5());
		setM10WeekSplits(val.getM10(), val.getM10W1(), val.getM10W2(), val.getM10W3(), val.getM10W4());
		setM11WeekSplits(val.getM11(), val.getM11W1(), val.getM11W2(), val.getM11W3(), val.getM11W4());
		setM12WeekSplits(val.getM12(), val.getM12W1(), val.getM12W2(), val.getM12W3(), val.getM12W4(), val.getM12W5());
		
		setHalfYearSplits(val.getBookingAll(), val.getH1(), val.getH2());
		setQuarterSplits(val.getBookingAll(), val.getQ1(), val.getQ2(), val.getQ3(), val.getQ4());
		
		switch (option)
		{
		case "Discount Overall":
			totalBooking = val.getListAll()-(val.getListAll()*(val.getDiscountAll()+whatIfValue.getDiscountAllValue()));
			setTechnologies(totalBooking);
			setArchitectures(totalBooking);
			this.atAttach = val.getATAttach();
			this.nonATAttach = val.getNonATAttach();
			if (whatIfValue.getTechPenValue() != 0.0D) {
				this.techPen = CalcHelper.round((val.getTechPen() + (val.getTechPen() * whatIfValue.getTechPenValue())), 2, BigDecimal.ROUND_HALF_UP);
				this.totalBooking = this.totalBooking + ((this.techPen - val.getTechPen())*(this.totalBooking/val.getTechPen()));
			} else {
				this.techPen = val.getTechPen();
			}
			if (whatIfValue.getCusPenValue() != 0.0D) {
				this.cusPen = CalcHelper.round((val.getCusPen() + (val.getCusPen() * whatIfValue.getCusPenValue())), 0, BigDecimal.ROUND_HALF_UP);
				this.totalBooking = this.totalBooking + ((this.cusPen - val.getCusPen())*CalcHelper.getValueInMillionUSDFromThousand(val.getYldPerCus()));
			} else {
				this.cusPen = val.getCusPen();
			}
			break;
		case "Others":
				this.entNWBooking = val.getListENTNW()-(val.getListENTNW()*(val.getDiscountENTNW()+whatIfValue.getDiscountENTNWValue()));
				this.secBook = val.getListSecurity()-(val.getListSecurity()*(val.getDiscountSecurity()+whatIfValue.getDiscountSecValue()));
				this.collBooking = val.getListCollab()-(val.getListCollab()*(val.getDiscountCollab()+whatIfValue.getDiscountCollValue()));
				this.dcvBooking = val.getListDCV()-(val.getListDCV()*(val.getDiscountDCV()+whatIfValue.getDiscountDCVValue()));
				this.totalBooking = this.dcvBooking + this.secBook + this.entNWBooking + this.collBooking;
				if (whatIfValue.getTechPenValue() != 0.0D) {
					this.techPen = CalcHelper.round((val.getTechPen() + (val.getTechPen() * whatIfValue.getTechPenValue())), 2, BigDecimal.ROUND_HALF_UP);
					this.totalBooking = this.totalBooking + ((this.techPen - val.getTechPen())*(this.totalBooking/val.getTechPen()));
				} else {
					this.techPen = val.getTechPen();
				}
				if (whatIfValue.getCusPenValue() != 0.0D) {
					this.cusPen = CalcHelper.round((val.getCusPen() + (val.getCusPen() * whatIfValue.getCusPenValue())), 0, BigDecimal.ROUND_HALF_UP);
					this.totalBooking = this.totalBooking + ((this.cusPen - val.getCusPen())*CalcHelper.getValueInMillionUSDFromThousand(val.getYldPerCus()));
				} else {
					this.cusPen = val.getCusPen();
				}
			setENTNWSplitValues();
			setCollabSplitValues();
			setDCVSplitValues();
			this.techSec = this.secBook;
			this.atAttach = val.getATAttach();
			this.nonATAttach = val.getNonATAttach();
			break;
		}
		setDiscounts(val.getListAll(), val.getListCollab(), val.getListDCV(), 
				val.getListENTNW(), val.getListSecurity());
		setYldPerCus(totalBooking, val.getCusPen());
		setQuarterValues(totalBooking);
		setHalfYearValues(totalBooking);
		setMonthValues(totalBooking);
		setWeekValues(this.m1, this.m2, this.m3, this.m4, this.m5, this.m6, this.m7, 
				this.m8, this.m9, this.m10, this.m11, this.m12);
	}

	
/*	public DashboardGeneralWhatIf(DashboardGeneralValues val, String option, 
			double whatIfValue) {
		
		setTechSplits(val.getANS(), val.getDCSwitching(), val.getOthers(), val.getLANSwitching(),
				val.getRouting(), val.getSecurity(), val.getStorage(), val.getUC(), val.getUCS(),
				val.getVideo(), val.getWirelessLAN());
		setArchSplits(val.getBookingAll(), val.getBookingCollab(), val.getBookingDCV(), 
				val.getBookingENTNW(), val.getBookingSecurity());
		setCollabSplits(val.getBookingCollab(), val.getUC(), val.getVideo());
		setDCVSplits(val.getBookingDCV(), val.getANS(), val.getUCS(), val.getStorage(),
				val.getDCSwitching());
		securitySecuritySplit = (val.getBookingSecurity()!=0) ? 1D : 0D;
		
		setENTNWSplits(val.getBookingENTNW(), val.getOthers(), val.getLANSwitching(), val.getRouting(), 
				val.getWirelessLAN());
		setMonthSplits(val.getBookingAll(), val.getM1(), val.getM2(), val.getM3(), val.getM4(), val.getM5(), val.getM6(), 
				val.getM7(), val.getM8(), val.getM9(), val.getM10(), val.getM11(), val.getM12());
		setM1WeekSplits(val.getM1(), val.getM1W1(), val.getM1W2(), val.getM1W3(), val.getM1W4());
		setM2WeekSplits(val.getM2(), val.getM2W1(), val.getM2W2(), val.getM2W3(), val.getM2W4());
		setM3WeekSplits(val.getM3(), val.getM3W1(), val.getM3W2(), val.getM3W3(), val.getM3W4(), val.getM3W5());
		setM4WeekSplits(val.getM4(), val.getM4W1(), val.getM4W2(), val.getM4W3(), val.getM4W4());
		setM5WeekSplits(val.getM5(), val.getM5W1(), val.getM5W2(), val.getM5W3(), val.getM5W4());
		setM6WeekSplits(val.getM6(), val.getM6W1(), val.getM6W2(), val.getM6W3(), val.getM6W4(), val.getM6W5());
		setM7WeekSplits(val.getM7(), val.getM7W1(), val.getM7W2(), val.getM7W3(), val.getM7W4());
		setM8WeekSplits(val.getM8(), val.getM8W1(), val.getM8W2(), val.getM8W3(), val.getM8W4());
		setM9WeekSplits(val.getM9(), val.getM9W1(), val.getM9W2(), val.getM9W3(), val.getM9W4(), val.getM9W5());
		setM10WeekSplits(val.getM10(), val.getM10W1(), val.getM10W2(), val.getM10W3(), val.getM10W4());
		setM11WeekSplits(val.getM11(), val.getM11W1(), val.getM11W2(), val.getM11W3(), val.getM11W4());
		setM12WeekSplits(val.getM12(), val.getM12W1(), val.getM12W2(), val.getM12W3(), val.getM12W4(), val.getM12W5());
		
		setHalfYearSplits(val.getBookingAll(), val.getH1(), val.getH2());
		setQuarterSplits(val.getBookingAll(), val.getQ1(), val.getQ2(), val.getQ3(), val.getQ4());
		
		switch (option)
		{
		case "Discount Overall":
			totalBooking = val.getListAll()-(val.getListAll()*(val.getDiscountAll()+whatIfValue));
			setTechnologies(totalBooking);
			setArchitectures(totalBooking);
			this.atAttach = val.getATAttach();
			this.nonATAttach = val.getNonATAttach();
			this.cusPen = val.getCusPen();
			this.techPen = val.getTechPen();
			break;
		case "Discount ENTNW" :
			this.entNWBooking = val.getListENTNW()-(val.getListENTNW()*(val.getDiscountENTNW()+whatIfValue));
			this.totalBooking = this.entNWBooking + val.getBookingSecurity() + 
					val.getBookingCollab() + val.getBookingDCV();
			setENTNWSplitValues();
			this.techANS = val.getANS();
			this.techDCS = val.getDCSwitching();
			this.techSec = val.getSecurity();
			this.techSto = val.getStorage();
			this.techUC = val.getUC();
			this.techUCS = val.getUCS();
			this.techVid = val.getVideo();
			this.collBooking = val.getBookingCollab();
			this.dcvBooking = val.getBookingDCV();
			this.secBook = val.getBookingSecurity();
			this.atAttach = val.getATAttach();
			this.nonATAttach = val.getNonATAttach();
			this.cusPen = val.getCusPen();
			this.techPen = val.getTechPen();
			break;
		case "Discount Security" :
			this.secBook = val.getListSecurity()-(val.getListSecurity()*(val.getDiscountSecurity()+whatIfValue));
			this.totalBooking = this.secBook + val.getBookingENTNW() + 
					val.getBookingCollab() + val.getBookingDCV();
			this.techSec = this.secBook;
			this.techANS = val.getANS();
			this.techDCS = val.getDCSwitching();
			this.techSto = val.getStorage();
			this.techUC = val.getUC();
			this.techUCS = val.getUCS();
			this.techVid = val.getVideo();
			this.techOth = val.getOthers();
			this.techLAN = val.getLANSwitching();
			this.techRou = val.getRouting();
			this.techWLA = val.getWirelessLAN();
			this.collBooking = val.getBookingCollab();
			this.dcvBooking = val.getBookingDCV();
			this.entNWBooking = val.getBookingENTNW();
			this.atAttach = val.getATAttach();
			this.nonATAttach = val.getNonATAttach();
			this.cusPen = val.getCusPen();
			this.techPen = val.getTechPen();
			break;
		case "Discount Collab" :
			this.collBooking = val.getListCollab()-(val.getListCollab()*(val.getDiscountCollab()+whatIfValue));
			this.totalBooking = this.collBooking + val.getBookingSecurity() + 
					val.getBookingENTNW() + val.getBookingDCV();
			setCollabSplitValues();
			this.techANS = val.getANS();
			this.techDCS = val.getDCSwitching();
			this.techSec = val.getSecurity();
			this.techSto = val.getStorage();
			this.techUCS = val.getUCS();
			this.techOth = val.getOthers();
			this.techLAN = val.getLANSwitching();
			this.techRou = val.getRouting();
			this.techWLA = val.getWirelessLAN();
			this.entNWBooking = val.getBookingENTNW();
			this.dcvBooking = val.getBookingDCV();
			this.secBook = val.getBookingSecurity();
			this.atAttach = val.getATAttach();
			this.nonATAttach = val.getNonATAttach();
			this.cusPen = val.getCusPen();
			this.techPen = val.getTechPen();
			break;
		case "Discount DCV" :
			this.dcvBooking = val.getListDCV()-(val.getListDCV()*(val.getDiscountDCV()+whatIfValue));
			this.totalBooking = this.dcvBooking + val.getBookingSecurity() + 
					val.getBookingENTNW() + val.getBookingCollab();
			setDCVSplitValues();
			this.techSec = val.getSecurity();
			this.techOth = val.getOthers();
			this.techLAN = val.getLANSwitching();
			this.techRou = val.getRouting();
			this.techUC = val.getUC();
			this.techVid = val.getVideo();
			this.techWLA = val.getWirelessLAN();
			this.entNWBooking = val.getBookingENTNW();
			this.dcvBooking = val.getBookingDCV();
			this.secBook = val.getBookingSecurity();
			this.atAttach = val.getATAttach();
			this.nonATAttach = val.getNonATAttach();
			this.cusPen = val.getCusPen();
			this.techPen = val.getTechPen();
			break;
		case "Technology Penetration" :
			this.techPen = CalcHelper.round((val.getTechPen() + (val.getTechPen() * whatIfValue)), 2, BigDecimal.ROUND_HALF_UP);
			totalBooking = val.getBookingAll() + ((this.techPen - val.getTechPen())*(val.getBookingAll()/val.getTechPen()));
			setTechnologies(totalBooking);
			setArchitectures(totalBooking);
			this.atAttach = val.getATAttach();
			this.nonATAttach = val.getNonATAttach();
			this.cusPen = val.getCusPen();
			break;
		case "Customer Penetration" :
			this.cusPen = CalcHelper.round((val.getCusPen() + (val.getCusPen() * whatIfValue)), 0, BigDecimal.ROUND_HALF_UP);
			totalBooking = val.getBookingAll() + ((this.cusPen - val.getCusPen())*CalcHelper.getValueInMillionUSDFromThousand(val.getYldPerCus()));
			setTechnologies(totalBooking);
			setArchitectures(totalBooking);
			this.atAttach = val.getATAttach();
			this.nonATAttach = val.getNonATAttach();
			this.techPen = val.getTechPen();
			break;
		}
		setDiscounts(val.getListAll(), val.getListCollab(), val.getListDCV(), 
				val.getListENTNW(), val.getListSecurity());
		setYldPerCus(totalBooking, val.getCusPen());
		setQuarterValues(totalBooking);
		setHalfYearValues(totalBooking);
		setMonthValues(totalBooking);
		setWeekValues(this.m1, this.m2, this.m3, this.m4, this.m5, this.m6, this.m7, 
				this.m8, this.m9, this.m10, this.m11, this.m12);
	}*/
	
	
	private void setTechSplits(double valANS, double valDCS, double valOthers, double valLANS, 
			double valRouting, double valSecurity, double valStorage, double valUC, double valUCS, 
			double valVideo, double valWLAN) {
		double total;
		
		total = valANS +valDCS +valOthers +valLANS +
				valRouting +valSecurity +valStorage +valUC +valUCS +
				valVideo +valWLAN;
		
		techANSSplit = (total != 0) ? valANS/total : 0D;
		techDCSSplit = (total != 0) ? valDCS/total : 0D;
		techOthersSplit = (total != 0) ? valOthers/total : 0D;
		techLANSSplit = (total != 0) ? valLANS/total : 0D;
		techRoutingSplit = (total != 0) ? valRouting/total : 0D;
		techSecuritySplit = (total != 0) ? valSecurity/total : 0D;
		techStorageSplit = (total != 0) ? valStorage/total : 0D;
		techUCSplit = (total != 0) ? valUC/total : 0D;
		techUCSSplit = (total != 0) ? valUCS/total : 0D;
		techVideoSplit = (total != 0) ? valVideo/total : 0D;
		techWLANSplit = (total != 0) ? valWLAN/total : 0D;
	}
	
	private void setArchSplits(double valBookingAll, double valBookingCollab, double valBookingDCV, 
			double valBookingENTNW, double valBookingSecurity) {
		archCollabSplit = (valBookingAll != 0) ? valBookingCollab/valBookingAll : 0D;
		archDCVSplit = (valBookingAll != 0) ? valBookingDCV/valBookingAll : 0D;
		archENTNWSplit = (valBookingAll != 0) ? valBookingENTNW/valBookingAll : 0D;
		archSecuritySplit = (valBookingAll != 0) ? valBookingSecurity/valBookingAll : 0D;
	}
	
	private void setCollabSplits(double valBookingCollab, double valUC, double valVideo) {
		collabUCSplit = (valBookingCollab != 0) ? valUC/valBookingCollab : 0D;
		collabVideoSplit = (valBookingCollab != 0) ? valVideo/valBookingCollab : 0D;
	}
	
	private void setDCVSplits(double valBookingDCV, double valANS, double valUCS, double valStorage, 
			double valDCSwitching) {
		
		dcvANSSplit = (valBookingDCV != 0) ? valANS/valBookingDCV : 0D;
		dcvUCSSplit = (valBookingDCV != 0) ? valUCS/valBookingDCV : 0D;
		dcvStorageSplit = (valBookingDCV != 0) ? valStorage/valBookingDCV : 0D;
		dcvDCSSplit = (valBookingDCV != 0) ? valDCSwitching/valBookingDCV : 0D;
	}
	
	private void setENTNWSplits(double valBookingENTNW, double valOthers, double valLANSwitching, 
			double valRouting, double valWirelessLAN) {

		entNWOthersSplit = (valBookingENTNW != 0) ? valOthers/valBookingENTNW : 0D;
		entNWLANSSplit = (valBookingENTNW != 0) ? valLANSwitching/valBookingENTNW : 0D;
		entNWRoutingSplit = (valBookingENTNW != 0) ? valRouting/valBookingENTNW : 0D;
		entNWWLANSplit = (valBookingENTNW != 0) ? valWirelessLAN/valBookingENTNW : 0D;
	}
	
	private void setMonthSplits(double valBookingAll, double valM1, double valM2, double valM3, 
			double valM4, double valM5, double valM6, double valM7, double valM8, double valM9, 
			double valM10, double valM11, double valM12) {
		m1Split = (valBookingAll != 0) ? valM1/valBookingAll : 0D;
		m2Split = (valBookingAll != 0) ? valM2/valBookingAll : 0D;
		m3Split = (valBookingAll != 0) ? valM3/valBookingAll : 0D;
		m4Split = (valBookingAll != 0) ? valM4/valBookingAll : 0D;
		m5Split = (valBookingAll != 0) ? valM5/valBookingAll : 0D;
		m6Split = (valBookingAll != 0) ? valM6/valBookingAll : 0D;
		m7Split = (valBookingAll != 0) ? valM7/valBookingAll : 0D;
		m8Split = (valBookingAll != 0) ? valM8/valBookingAll : 0D;
		m9Split = (valBookingAll != 0) ? valM9/valBookingAll : 0D;
		m10Split = (valBookingAll != 0) ? valM10/valBookingAll : 0D;
		m11Split = (valBookingAll != 0) ? valM11/valBookingAll : 0D;
		m12Split = (valBookingAll != 0) ? valM12/valBookingAll : 0D;
	}
	
	private void setM1WeekSplits(double month, double w1, double w2, double w3, double w4) {
		m1w1Split = (month != 0) ? w1/month : 0D;
		m1w2Split = (month != 0) ? w2/month : 0D;
		m1w3Split = (month != 0) ? w3/month : 0D;
		m1w4Split = (month != 0) ? w4/month : 0D;
	}

	private void setM2WeekSplits(double month, double w1, double w2, double w3, double w4) {
		m2w1Split = (month != 0) ? w1/month : 0D;
		m2w2Split = (month != 0) ? w2/month : 0D;
		m2w3Split = (month != 0) ? w3/month : 0D;
		m2w4Split = (month != 0) ? w4/month : 0D;
	}
	
	private void setM3WeekSplits(double month, double w1, double w2, double w3, double w4, double w5) {
		m3w1Split = (month != 0) ? w1/month : 0D;
		m3w2Split = (month != 0) ? w2/month : 0D;
		m3w3Split = (month != 0) ? w3/month : 0D;
		m3w4Split = (month != 0) ? w4/month : 0D;
		m3w5Split = (month != 0) ? w5/month : 0D;
	}

	private void setM4WeekSplits(double month, double w1, double w2, double w3, double w4) {
		m4w1Split = (month != 0) ? w1/month : 0D;
		m4w2Split = (month != 0) ? w2/month : 0D;
		m4w3Split = (month != 0) ? w3/month : 0D;
		m4w4Split = (month != 0) ? w4/month : 0D;
	}

	private void setM5WeekSplits(double month, double w1, double w2, double w3, double w4) {
		m5w1Split = (month != 0) ? w1/month : 0D;
		m5w2Split = (month != 0) ? w2/month : 0D;
		m5w3Split = (month != 0) ? w3/month : 0D;
		m5w4Split = (month != 0) ? w4/month : 0D;
	}
	
	private void setM6WeekSplits(double month, double w1, double w2, double w3, double w4, double w5) {
		m6w1Split = (month != 0) ? w1/month : 0D;
		m6w2Split = (month != 0) ? w2/month : 0D;
		m6w3Split = (month != 0) ? w3/month : 0D;
		m6w4Split = (month != 0) ? w4/month : 0D;
		m6w5Split = (month != 0) ? w5/month : 0D;
	}
	
	private void setM7WeekSplits(double month, double w1, double w2, double w3, double w4) {
		m7w1Split = (month != 0) ? w1/month : 0D;
		m7w2Split = (month != 0) ? w2/month : 0D;
		m7w3Split = (month != 0) ? w3/month : 0D;
		m7w4Split = (month != 0) ? w4/month : 0D;
	}

	private void setM8WeekSplits(double month, double w1, double w2, double w3, double w4) {
		m8w1Split = (month != 0) ? w1/month : 0D;
		m8w2Split = (month != 0) ? w2/month : 0D;
		m8w3Split = (month != 0) ? w3/month : 0D;
		m8w4Split = (month != 0) ? w4/month : 0D;
	}
	
	private void setM9WeekSplits(double month, double w1, double w2, double w3, double w4, double w5) {
		m9w1Split = (month != 0) ? w1/month : 0D;
		m9w2Split = (month != 0) ? w2/month : 0D;
		m9w3Split = (month != 0) ? w3/month : 0D;
		m9w4Split = (month != 0) ? w4/month : 0D;
		m9w5Split = (month != 0) ? w5/month : 0D;
	}
	
	private void setM10WeekSplits(double month, double w1, double w2, double w3, double w4) {
		m10w1Split = (month != 0) ? w1/month : 0D;
		m10w2Split = (month != 0) ? w2/month : 0D;
		m10w3Split = (month != 0) ? w3/month : 0D;
		m10w4Split = (month != 0) ? w4/month : 0D;
	}

	private void setM11WeekSplits(double month, double w1, double w2, double w3, double w4) {
		m11w1Split = (month != 0) ? w1/month : 0D;
		m11w2Split = (month != 0) ? w2/month : 0D;
		m11w3Split = (month != 0) ? w3/month : 0D;
		m11w4Split = (month != 0) ? w4/month : 0D;
	}
	
	private void setM12WeekSplits(double month, double w1, double w2, double w3, double w4, double w5) {
		m12w1Split = (month != 0) ? w1/month : 0D;
		m12w2Split = (month != 0) ? w2/month : 0D;
		m12w3Split = (month != 0) ? w3/month : 0D;
		m12w4Split = (month != 0) ? w4/month : 0D;
		m12w5Split = (month != 0) ? w5/month : 0D;
	}

	private void setHalfYearSplits(double valBookingAll, double valH1, double valH2) {
		h1Split = (valBookingAll != 0) ? valH1/valBookingAll : 0D;
		h2Split = (valBookingAll != 0) ? valH2/valBookingAll : 0D;
	}
	
	private void setQuarterSplits(double valBookingAll, double valQ1, double valQ2, 
			double valQ3, double valQ4) {
		q1Split = (valBookingAll != 0) ? valQ1/valBookingAll : 0D;
		q2Split = (valBookingAll != 0) ? valQ2/valBookingAll : 0D;
		q3Split = (valBookingAll != 0) ? valQ3/valBookingAll : 0D;
		q4Split = (valBookingAll != 0) ? valQ4/valBookingAll : 0D;
	}
	
	private void setTechnologies(double value) {
		value = CalcHelper.getValueInThousandUSD(value*1000000);
		techANS = value * techANSSplit;
		techDCS = value * techDCSSplit;
		techOth = value * techOthersSplit;
		techLAN = value * techLANSSplit;
		techRou = value * techRoutingSplit;
		techSec = value * techSecuritySplit;
		techSto = value * techStorageSplit;
		techUC = value * techUCSplit;
		techUCS = value * techUCSSplit;
		techVid = value * techVideoSplit;
		techWLA = value * techWLANSplit;
	}

	private void setArchitectures(double value) {
//		value = CalcHelper.getValueInThousandUSDFromMillion(value);
		entNWBooking = value * archENTNWSplit;
		secBook = value * archSecuritySplit;
		collBooking = value * archCollabSplit;
		dcvBooking = value * archDCVSplit;
	}

	private void setDiscounts(double valListAll, double valListCollab, double valListDCV, 
			double valListENTNW, double valListSecurity) {
		overAllDis = CalcHelper.getDiscount(totalBooking, valListAll);
		entNWDis = CalcHelper.getDiscount(entNWBooking, valListENTNW);
		secDis = CalcHelper.getDiscount(secBook, valListSecurity);
		collDis = CalcHelper.getDiscount(collBooking, valListCollab);
		dcvDis = CalcHelper.getDiscount(dcvBooking, valListDCV);
	}

	private void setQuarterValues(double value) {
		q1 = value * q1Split;
		q2 = value * q2Split;
		q3 = value * q3Split;
		q4 = value * q4Split;
	}
	private void setHalfYearValues(double value) {
		h1 = value * h1Split;
		h2 = value * h2Split;
	}

	private void setMonthValues(double value) {
		m1 = value * m1Split;
		m2 = value * m2Split;
		m3 = value * m3Split;
		m4 = value * m4Split;
		m5 = value * m5Split;
		m6 = value * m6Split;
		m7 = value * m7Split;
		m8 = value * m8Split;
		m9 = value * m9Split;
		m10 = value * m10Split;
		m11 = value * m11Split;
		m12 = value * m12Split;
	}
	
	private void setWeekValues(double m1, double m2, double m3, double m4, double m5, 
			double m6, double m7, double m8, double m9, double m10, 
			double m11, double m12) {
		m1w1 = m1 * m1w1Split;
		m1w2 = m1 * m1w2Split;
		m1w3 = m1 * m1w3Split;
		m1w4 = m1 * m1w4Split;

		m2w1 = m2 * m2w1Split;
		m2w2 = m2 * m2w2Split;
		m2w3 = m2 * m2w3Split;
		m2w4 = m2 * m2w4Split;
	
		m3w1 = m3 * m3w1Split;
		m3w2 = m3 * m3w2Split;
		m3w3 = m3 * m3w3Split;
		m3w4 = m3 * m3w4Split;
		m3w5 = m3 * m3w5Split;

		m4w1 = m4 * m4w1Split;
		m4w2 = m4 * m4w2Split;
		m4w3 = m4 * m4w3Split;
		m4w4 = m4 * m4w4Split;

		m5w1 = m5 * m5w1Split;
		m5w2 = m5 * m5w2Split;
		m5w3 = m5 * m5w3Split;
		m5w4 = m5 * m5w4Split;

		m6w1 = m6 * m6w1Split;
		m6w2 = m6 * m6w2Split;
		m6w3 = m6 * m6w3Split;
		m6w4 = m6 * m6w4Split;
		m6w5 = m6 * m6w5Split;

		m7w1 = m7 * m7w1Split;
		m7w2 = m7 * m7w2Split;
		m7w3 = m7 * m7w3Split;
		m7w4 = m7 * m7w4Split;

		m8w1 = m8 * m8w1Split;
		m8w2 = m8 * m8w2Split;
		m8w3 = m8 * m8w3Split;
		m8w4 = m8 * m8w4Split;
	
		m9w1 = m9 * m9w1Split;
		m9w2 = m9 * m9w2Split;
		m9w3 = m9 * m9w3Split;
		m9w4 = m9 * m9w4Split;
		m9w5 = m9 * m9w5Split;

		m10w1 = m10 * m10w1Split;
		m10w2 = m10 * m10w2Split;
		m10w3 = m10 * m10w3Split;
		m10w4 = m10 * m10w4Split;

		m11w1 = m11 * m11w1Split;
		m11w2 = m11 * m11w2Split;
		m11w3 = m11 * m11w3Split;
		m11w4 = m11 * m11w4Split;
	
		m12w1 = m12 * m12w1Split;
		m12w2 = m12 * m12w2Split;
		m12w3 = m12 * m12w3Split;
		m12w4 = m12 * m12w4Split;
		m12w5 = m12 * m12w5Split;
	}
	private void setYldPerCus(double val1, double val2) {
		yldPerCus = (val2 == 0) ? 0 : CalcHelper.getValueInThousandUSD(val1*1000000)/val2;
	}
	
	private void setENTNWSplitValues() {
		techOth = entNWBooking * entNWOthersSplit;
		techLAN = entNWBooking * entNWLANSSplit;
		techRou = entNWBooking * entNWRoutingSplit;
		techWLA = entNWBooking * entNWWLANSplit;
	}
	
	private void setCollabSplitValues() {
		techUC = this.collBooking * this.collabUCSplit;
		techVid = this.collBooking * this.collabVideoSplit;
	}
	
	private void setDCVSplitValues() {
		techANS = this.dcvBooking * this.dcvANSSplit;
		techUCS = this.dcvBooking * this.dcvUCSSplit;
		techSto = this.dcvBooking * this.dcvStorageSplit;
		techDCS = this.dcvBooking * this.dcvDCSSplit;
	}
	
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
