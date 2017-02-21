package prism14;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public final class CalcHelper {

	public static double getDiscount(double rev, double bList) {
		double discount = (bList <= 0D) ? 0D : (1-(rev/bList));
		return discount;
	}

	public static double getYield(double rev, double count) {
		double yield = (count == 0D) ? 0D : (rev/count);
		return yield;
	}

	public static double getRatio(double numerator, double denominator) {
		double ratio = (denominator == 0D) ? 0D : (numerator/denominator);
		return ratio;
	}

	public static double getTechnologyPenetration(double tCount, double cPen) {
		double tPen = (cPen == 0D) ? 0D : (tCount/cPen);
		return tPen;
	}

	public static double getATPenetration(double salesAT, double salesNonAT) {
		double atPen = (salesNonAT+salesAT == 0D) ? 0D : (salesAT/(salesNonAT+salesAT));
		return atPen;
	}

	public static double getMaxOutofSeven(double doub1, double doub2, 
			double doub3, double doub4, double doub5, double doub6, double doub7) {
		double maxDouble;
		maxDouble = (doub1 > doub2) ? doub1 : doub2;
		maxDouble = (maxDouble > doub3) ? maxDouble : doub3;
		maxDouble = (maxDouble > doub4) ? maxDouble : doub4;
		maxDouble = (maxDouble > doub5) ? maxDouble : doub5;
		maxDouble = (maxDouble > doub6) ? maxDouble : doub6;
		maxDouble = (maxDouble > doub7) ? maxDouble : doub7;
		maxDouble = (maxDouble > 120D) ? 120D : 100D;
		return maxDouble;
	}

	public static double getMinOutofSeven(double doub1, double doub2, 
			double doub3, double doub4, double doub5, double doub6, double doub7) {
		double minDouble;
		minDouble = (doub1 < doub2) ? doub1 : doub2;
		minDouble = (minDouble < doub3) ? minDouble : doub3;
		minDouble = (minDouble < doub4) ? minDouble : doub4;
		minDouble = (minDouble < doub5) ? minDouble : doub5;
		minDouble = (minDouble < doub6) ? minDouble : doub6;
		minDouble = (minDouble < doub7) ? minDouble : doub7;
		minDouble = (minDouble < 0) ? 0D : 0D;
		return minDouble;
	}
	
	public static double getAchievement(double actual, double threshold) {
		double achievement;
		if (actual ==0D && threshold <= 0D) {
			achievement = 100D;
		} else if (threshold <= 0D) {
			achievement = 100D;
		} else achievement = (actual/threshold)*100; 
		return achievement;
	}

	public static double getAchievementSpecial(double actual, double threshold, double uLimit) {
		double achievement;
		if (actual ==0D && threshold <= 0D) {
			achievement = uLimit*100;
		} else if (threshold <= 0D) {
			achievement = uLimit*100;
		} else achievement = (actual/threshold)*100; 
		return achievement;
	}

	
	public static double getGrowth(double actual, double threshold) {
		double growth;
		if (actual ==0D && threshold == 0D) {
			growth = 0D;
		} else if (threshold == 0D) {
			growth = 100D;
		} else if (actual <0D || threshold < 0D) {
			growth = 0D;
		} else growth = ((actual-threshold)/threshold)*100; 
		return growth;
	}

	
	private static double getTechAchievement(double actual, double threshold) {
		double achievement;
		if (actual ==0D && threshold == 0D) {
			achievement = 0D;
		} else if (actual < 0D) {
			achievement = 0D;
		} else if (threshold == 0D) {
			achievement = 100D;
		} else achievement = (actual/threshold)*100; 
		return achievement;
	}

	public static double getTechPerformance(double actual, double threshold) {
		double point;
		if (getTechAchievement(actual, threshold) > GeneralConstants.GREEN_POINT_BOUNDARY) {
			point = GeneralConstants.GREEN_POINT;
		} else if (getTechAchievement(actual, threshold) >= GeneralConstants.AMBER_POINT_START && 
				getTechAchievement(actual, threshold) < GeneralConstants.AMBER_POINT_END) {
			point = GeneralConstants.AMBER_POINT;
		} else if (getTechAchievement(actual, threshold) > 0D && 
				getTechAchievement(actual, threshold) < GeneralConstants.RED_POINT_BOUNDARY) {
			point = GeneralConstants.RED_POINT;
		} else if (getTechAchievement(actual, threshold) == -1D) {
			point = -1D;
		} else point = 0D; 
		return point;
	}

	public static double getCoverage(double rs, double sec, double ucs, 
			double uc, double dcs, double video, double wlan) {
//		double techCounter = 0;
		double techCounter = 7;
		double coverage = 0D;
/*		if (!(rs < 0D)) {
			techCounter++;  
		} else {
			rs = 0;
		}
		if (!(sec < 0D)) {
			techCounter++;  
		} else {
			sec = 0;
		}
		if (!(ucs < 0D)) {
			techCounter++;  
		} else {
			ucs = 0;
		}
		if (!(uc < 0D)) {
			techCounter++;  
		} else {
			uc = 0;
		}
		if (!(dcs < 0D)) {
			techCounter++;  
		} else {
			dcs = 0;
		}
		if (!(video < 0D)) {
			techCounter++;  
		} else {
			video = 0;
		}
		if (!(wlan < 0D)) {
			techCounter++;  
		} else {
			wlan = 0;
		}*/
		coverage = (techCounter == 0) ? 0 : (rs + sec + ucs + uc + dcs + video + wlan)/techCounter; 
		return coverage;
	}
	
	public static double getTotalPosibleCoverage(double rs, double sec, double ucs, 
			double uc, double dcs, double video, double wlan) {
		double techCounter = 0;
		double coverage = 0D;
		if (!(rs < 0D)) {
			techCounter++;  
		} else {
			rs = 0;
		}
		if (!(sec < 0D)) {
			techCounter++;  
		} else {
			sec = 0;
		}
		if (!(ucs < 0D)) {
			techCounter++;  
		} else {
			ucs = 0;
		}
		if (!(uc < 0D)) {
			techCounter++;  
		} else {
			uc = 0;
		}
		if (!(dcs < 0D)) {
			techCounter++;  
		} else {
			dcs = 0;
		}
		if (!(video < 0D)) {
			techCounter++;  
		} else {
			video = 0;
		}
		if (!(wlan < 0D)) {
			techCounter++;  
		} else {
			wlan = 0;
		}
		return techCounter;
	}

	public static double getValueInThousandUSD(double val) {
		return val/GeneralConstants.THOUSAND;
	}

	public static double getValueInThousandUSDFromMillion(double val) {
		return (val * GeneralConstants.MILLION)/GeneralConstants.THOUSAND;
	}
	
	public static double getValueInMillionUSDFromThousand(double val) {
		return (val * GeneralConstants.THOUSAND)/GeneralConstants.MILLION;
	}

	public static double getValueInMillionUSD(double val) {
		return val/GeneralConstants.MILLION;
	}
	
	public static double round(double unrounded, int precision, int roundingMode) {
		BigDecimal bd = new BigDecimal(unrounded);
		BigDecimal rounded = bd.setScale(precision, roundingMode);
		return rounded.doubleValue();	
	}
}

