package prism14;

public class DataBaseXxmDashOtherTableModified {

	private String partnerName, salesAgentName;
	
	private double totCustomers, q1cusPen, q1techCount, q1oppCount, q1revAll, 
	q1bListAll, q1revColl, q1bListColl, q1revDCV, q1bListDCV, q1revBN,
	q1bListBN, q1revSec, q1bListSec, cwp;
	
	private double q2cusPen, q2techCount, q2oppCount, q2revAll, 
	q2bListAll, q2revColl, q2bListColl, q2revDCV, q2bListDCV, q2revBN,
	q2bListBN, q2revSec, q2bListSec;

	private double h1cusPen, h1techCount, h1oppCount, h1revAll, 
	h1bListAll, h1revColl, h1bListColl, h1revDCV, h1bListDCV, h1revBN,
	h1bListBN, h1revSec, h1bListSec;

	private double q3cusPen, q3techCount, q3oppCount, q3revAll, 
	q3bListAll, q3revColl, q3bListColl, q3revDCV, q3bListDCV, q3revBN,
	q3bListBN, q3revSec, q3bListSec;

	private double q4cusPen, q4techCount, q4oppCount, q4revAll, 
	q4bListAll, q4revColl, q4bListColl, q4revDCV, q4bListDCV, q4revBN,
	q4bListBN, q4revSec, q4bListSec;
	
	private double h2cusPen, h2techCount, h2oppCount, h2revAll, 
	h2bListAll, h2revColl, h2bListColl, h2revDCV, h2bListDCV, h2revBN,
	h2bListBN, h2revSec, h2bListSec;

	private double ytdcusPen, ytdtechCount, ytdoppCount, ytdrevAll, 
	ytdbListAll, ytdrevColl, ytdbListColl, ytdrevDCV, ytdbListDCV, ytdrevBN,
	ytdbListBN, ytdrevSec, ytdbListSec;
	
	public DataBaseXxmDashOtherTableModified(String partnerName, String salesAgentName,  
			double totCustomers, double cwp, double q1cusPen, double q1techCount, 
			double q1oppCount, double q1revAll, double q1bListAll, double q1revColl, 
			double q1bListColl, double q1revDCV, double q1bListDCV, double q1revBN,
			double q1bListBN, double q1revSec, double q1bListSec, 
			double q2cusPen, double q2techCount, 
			double q2oppCount, double q2revAll, double q2bListAll, double q2revColl, 
			double q2bListColl, double q2revDCV, double q2bListDCV, double q2revBN,
			double q2bListBN, double q2revSec, double q2bListSec,
			double h1cusPen, double h1techCount, 
			double h1oppCount, double h1revAll, double h1bListAll, double h1revColl, 
			double h1bListColl, double h1revDCV, double h1bListDCV, double h1revBN,
			double h1bListBN, double h1revSec, double h1bListSec,
			double q3cusPen, double q3techCount, 
			double q3oppCount, double q3revAll, double q3bListAll, double q3revColl, 
			double q3bListColl, double q3revDCV, double q3bListDCV, double q3revBN,
			double q3bListBN, double q3revSec, double q3bListSec,
			double q4cusPen, double q4techCount, 
			double q4oppCount, double q4revAll, double q4bListAll, double q4revColl, 
			double q4bListColl, double q4revDCV, double q4bListDCV, double q4revBN,
			double q4bListBN, double q4revSec, double q4bListSec,
			double h2cusPen, double h2techCount, 
			double h2oppCount, double h2revAll, double h2bListAll, double h2revColl, 
			double h2bListColl, double h2revDCV, double h2bListDCV, double h2revBN,
			double h2bListBN, double h2revSec, double h2bListSec,
			double ytdcusPen, double ytdtechCount, 
			double ytdoppCount, double ytdrevAll, double ytdbListAll, double ytdrevColl, 
			double ytdbListColl, double ytdrevDCV, double ytdbListDCV, double ytdrevBN,
			double ytdbListBN, double ytdrevSec, double ytdbListSec) {
		
		this.partnerName =  partnerName;
		this.salesAgentName =  salesAgentName;
		
		this.totCustomers = totCustomers;
		this.q1cusPen = q1cusPen;
		this.q1techCount = q1techCount;
		this.q1oppCount = q1oppCount;
		this.q1revAll =  q1revAll;
		this.q1bListAll = q1bListAll;
		this.q1revColl = q1revColl;
		this.q1bListColl = q1bListColl;
		this.q1revDCV = q1revDCV;
		this.q1bListDCV = q1bListDCV;
		this.q1revBN = q1revBN;
		this.q1bListBN = q1bListBN;
		this.q1revSec = q1revSec;
		this.q1bListSec = q1bListSec;
		this.cwp = cwp;

		this.q2cusPen = q2cusPen;
		this.q2techCount = q2techCount;
		this.q2oppCount = q2oppCount;
		this.q2revAll =  q2revAll;
		this.q2bListAll = q2bListAll;
		this.q2revColl = q2revColl;
		this.q2bListColl = q2bListColl;
		this.q2revDCV = q2revDCV;
		this.q2bListDCV = q2bListDCV;
		this.q2revBN = q2revBN;
		this.q2bListBN = q2bListBN;
		this.q2revSec = q2revSec;
		this.q2bListSec = q2bListSec;

		this.h1cusPen = h1cusPen;
		this.h1techCount = h1techCount;
		this.h1oppCount = h1oppCount;
		this.h1revAll =  h1revAll;
		this.h1bListAll = h1bListAll;
		this.h1revColl = h1revColl;
		this.h1bListColl = h1bListColl;
		this.h1revDCV = h1revDCV;
		this.h1bListDCV = h1bListDCV;
		this.h1revBN = h1revBN;
		this.h1bListBN = h1bListBN;
		this.h1revSec = h1revSec;
		this.h1bListSec = h1bListSec;

		this.q3cusPen = q3cusPen;
		this.q3techCount = q3techCount;
		this.q3oppCount = q3oppCount;
		this.q3revAll =  q3revAll;
		this.q3bListAll = q3bListAll;
		this.q3revColl = q3revColl;
		this.q3bListColl = q3bListColl;
		this.q3revDCV = q3revDCV;
		this.q3bListDCV = q3bListDCV;
		this.q3revBN = q3revBN;
		this.q3bListBN = q3bListBN;
		this.q3revSec = q3revSec;
		this.q3bListSec = q3bListSec;

		this.q4cusPen = q4cusPen;
		this.q4techCount = q4techCount;
		this.q4oppCount = q4oppCount;
		this.q4revAll =  q4revAll;
		this.q4bListAll = q4bListAll;
		this.q4revColl = q4revColl;
		this.q4bListColl = q4bListColl;
		this.q4revDCV = q4revDCV;
		this.q4bListDCV = q4bListDCV;
		this.q4revBN = q4revBN;
		this.q4bListBN = q4bListBN;
		this.q4revSec = q4revSec;
		this.q4bListSec = q4bListSec;

		this.h2cusPen = h2cusPen;
		this.h2techCount = h2techCount;
		this.h2oppCount = h2oppCount;
		this.h2revAll =  h2revAll;
		this.h2bListAll = h2bListAll;
		this.h2revColl = h2revColl;
		this.h2bListColl = h2bListColl;
		this.h2revDCV = h2revDCV;
		this.h2bListDCV = h2bListDCV;
		this.h2revBN = h2revBN;
		this.h2bListBN = h2bListBN;
		this.h2revSec = h2revSec;
		this.h2bListSec = h2bListSec;
		
		this.ytdcusPen = ytdcusPen;
		this.ytdtechCount = ytdtechCount;
		this.ytdoppCount = ytdoppCount;
		this.ytdrevAll =  ytdrevAll;
		this.ytdbListAll = ytdbListAll;
		this.ytdrevColl = ytdrevColl;
		this.ytdbListColl = ytdbListColl;
		this.ytdrevDCV = ytdrevDCV;
		this.ytdbListDCV = ytdbListDCV;
		this.ytdrevBN = ytdrevBN;
		this.ytdbListBN = ytdbListBN;
		this.ytdrevSec = ytdrevSec;
		this.ytdbListSec = ytdbListSec;

	}

	public String getPartnerName() {return this.partnerName;}
	public String getSalesAgentName() {return this.salesAgentName;}
	
	public double getTotCustomers() {return this.totCustomers;}
	public double getCusPenQ1() {return this.q1cusPen;}
	public double getTechCountQ1() {return this.q1techCount;}
	public double getOppCountQ1() {return this.q1oppCount;}
	public double getRevenueAllQ1() {return this.q1revAll;}
	public double getBaseListAllQ1() {return this.q1bListAll;}
	public double getRevenueCollabQ1() {return this.q1revColl;}
	public double getBaseListCollabQ1() {return this.q1bListColl;}
	public double getRevenueDCVQ1() {return this.q1revDCV;}
	public double getBaseListDCVQ1() {return this.q1bListDCV;}
	public double getRevenueENTNWQ1() {return this.q1revBN;}
	public double getBaseListENTNWQ1() {return this.q1bListBN;}
	public double getRevenueSecurityQ1() {return this.q1revSec;}
	public double getBaseListSecurityQ1() {return this.q1bListSec;}
	public double getCWP() {return this.cwp;}

	
	public double getCusPenQ2() {return this.q2cusPen;}
	public double getTechCountQ2() {return this.q2techCount;}
	public double getOppCountQ2() {return this.q2oppCount;}
	public double getRevenueAllQ2() {return this.q2revAll;}
	public double getBaseListAllQ2() {return this.q2bListAll;}
	public double getRevenueCollabQ2() {return this.q2revColl;}
	public double getBaseListCollabQ2() {return this.q2bListColl;}
	public double getRevenueDCVQ2() {return this.q2revDCV;}
	public double getBaseListDCVQ2() {return this.q2bListDCV;}
	public double getRevenueENTNWQ2() {return this.q2revBN;}
	public double getBaseListENTNWQ2() {return this.q2bListBN;}
	public double getRevenueSecurityQ2() {return this.q2revSec;}
	public double getBaseListSecurityQ2() {return this.q2bListSec;}

	public double getCusPenH1() {return this.h1cusPen;}
	public double getTechCountH1() {return this.h1techCount;}
	public double getOppCountH1() {return this.h1oppCount;}
	public double getRevenueAllH1() {return this.h1revAll;}
	public double getBaseListAllH1() {return this.h1bListAll;}
	public double getRevenueCollabH1() {return this.h1revColl;}
	public double getBaseListCollabH1() {return this.h1bListColl;}
	public double getRevenueDCVH1() {return this.h1revDCV;}
	public double getBaseListDCVH1() {return this.h1bListDCV;}
	public double getRevenueENTNWH1() {return this.h1revBN;}
	public double getBaseListENTNWH1() {return this.h1bListBN;}
	public double getRevenueSecurityH1() {return this.h1revSec;}
	public double getBaseListSecurityH1() {return this.h1bListSec;}
	
	public double getCusPenQ3() {return this.q3cusPen;}
	public double getTechCountQ3() {return this.q3techCount;}
	public double getOppCountQ3() {return this.q3oppCount;}
	public double getRevenueAllQ3() {return this.q3revAll;}
	public double getBaseListAllQ3() {return this.q3bListAll;}
	public double getRevenueCollabQ3() {return this.q3revColl;}
	public double getBaseListCollabQ3() {return this.q3bListColl;}
	public double getRevenueDCVQ3() {return this.q3revDCV;}
	public double getBaseListDCVQ3() {return this.q3bListDCV;}
	public double getRevenueENTNWQ3() {return this.q3revBN;}
	public double getBaseListENTNWQ3() {return this.q3bListBN;}
	public double getRevenueSecurityQ3() {return this.q3revSec;}
	public double getBaseListSecurityQ3() {return this.q3bListSec;}
	
	public double getCusPenQ4() {return this.q4cusPen;}
	public double getTechCountQ4() {return this.q4techCount;}
	public double getOppCountQ4() {return this.q4oppCount;}
	public double getRevenueAllQ4() {return this.q4revAll;}
	public double getBaseListAllQ4() {return this.q4bListAll;}
	public double getRevenueCollabQ4() {return this.q4revColl;}
	public double getBaseListCollabQ4() {return this.q4bListColl;}
	public double getRevenueDCVQ4() {return this.q4revDCV;}
	public double getBaseListDCVQ4() {return this.q4bListDCV;}
	public double getRevenueENTNWQ4() {return this.q4revBN;}
	public double getBaseListENTNWQ4() {return this.q4bListBN;}
	public double getRevenueSecurityQ4() {return this.q4revSec;}
	public double getBaseListSecurityQ4() {return this.q4bListSec;}

	public double getCusPenH2() {return this.h2cusPen;}
	public double getTechCountH2() {return this.h2techCount;}
	public double getOppCountH2() {return this.h2oppCount;}
	public double getRevenueAllH2() {return this.h2revAll;}
	public double getBaseListAllH2() {return this.h2bListAll;}
	public double getRevenueCollabH2() {return this.h2revColl;}
	public double getBaseListCollabH2() {return this.h2bListColl;}
	public double getRevenueDCVH2() {return this.h2revDCV;}
	public double getBaseListDCVH2() {return this.h2bListDCV;}
	public double getRevenueENTNWH2() {return this.h2revBN;}
	public double getBaseListENTNWH2() {return this.h2bListBN;}
	public double getRevenueSecurityH2() {return this.h2revSec;}
	public double getBaseListSecurityH2() {return this.h2bListSec;}

	public double getCusPenYTD() {return this.ytdcusPen;}
	public double getTechCountYTD() {return this.ytdtechCount;}
	public double getOppCountYTD() {return this.ytdoppCount;}
	public double getRevenueAllYTD() {return this.ytdrevAll;}
	public double getBaseListAllYTD() {return this.ytdbListAll;}
	public double getRevenueCollabYTD() {return this.ytdrevColl;}
	public double getBaseListCollabYTD() {return this.ytdbListColl;}
	public double getRevenueDCVYTD() {return this.ytdrevDCV;}
	public double getBaseListDCVYTD() {return this.ytdbListDCV;}
	public double getRevenueENTNWYTD() {return this.ytdrevBN;}
	public double getBaseListENTNWYTD() {return this.ytdbListBN;}
	public double getRevenueSecurityYTD() {return this.ytdrevSec;}
	public double getBaseListSecurityYTD() {return this.ytdbListSec;}

}
