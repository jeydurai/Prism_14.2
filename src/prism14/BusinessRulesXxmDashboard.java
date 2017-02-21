package prism14;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public final class BusinessRulesXxmDashboard {

	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	public static DashboardXxmValues getXxmDashboardMetrics(JList jltSalesAgent, JList jltPar, 
			String parPre, String parDev, String bb, 
			String clpl, String shark, String option) {
		DashboardXxmOtherTableValues otherTableValues;
		DashboardXxmSalesTableValues salesTableValues;
		DashboardXxmTechTableValues techTableValues;
		
		int tempCount=0, tempCount2=0, tempCount3=0;
		double totCustomers=0D, q1cusPen=0D, q1techCount=0D, 
		q1oppCount=0D, q1revAll=0D, q1bListAll=0D, q1revColl=0D, 
		q1bListColl=0D, q1revDCV=0D, q1bListDCV=0D, q1revBN=0D,
		q1bListBN=0D, q1revSec=0D, q1bListSec=0D, cwp=0D,
		q2cusPen=0D, q2techCount=0D, 
		q2oppCount=0D, q2revAll=0D, q2bListAll=0D, q2revColl=0D, 
		q2bListColl=0D, q2revDCV=0D, q2bListDCV=0D, q2revBN=0D,
		q2bListBN=0D, q2revSec=0D, q2bListSec=0D,
		h1cusPen=0D, h1techCount=0D, 
		h1oppCount=0D, h1revAll=0D, h1bListAll=0D, h1revColl=0D, 
		h1bListColl=0D, h1revDCV=0D, h1bListDCV=0D, h1revBN=0D,
		h1bListBN=0D, h1revSec=0D, h1bListSec=0D,
		q3cusPen=0D, q3techCount=0D, 
		q3oppCount=0D, q3revAll=0D, q3bListAll=0D, q3revColl=0D, 
		q3bListColl=0D, q3revDCV=0D, q3bListDCV=0D, q3revBN=0D,
		q3bListBN=0D, q3revSec=0D, q3bListSec=0D,
		q4cusPen=0D, q4techCount=0D, 
		q4oppCount=0D, q4revAll=0D, q4bListAll=0D, q4revColl=0D, 
		q4bListColl=0D, q4revDCV=0D, q4bListDCV=0D, q4revBN=0D,
		q4bListBN=0D, q4revSec=0D, q4bListSec=0D,
		h2cusPen=0D, h2techCount=0D, 
		h2oppCount=0D, h2revAll=0D, h2bListAll=0D, h2revColl=0D, 
		h2bListColl=0D, h2revDCV=0D, h2bListDCV=0D, h2revBN=0D,
		h2bListBN=0D, h2revSec=0D, h2bListSec=0D,
		ytdtotCustomers=0D, ytdcusPen=0D, ytdtechCount=0D, 
		ytdoppCount=0D, ytdrevAll=0D, ytdbListAll=0D, ytdrevColl=0D, 
		ytdbListColl=0D, ytdrevDCV=0D, ytdbListDCV=0D, ytdrevBN=0D,
		ytdbListBN=0D, ytdrevSec=0D, ytdbListSec=0D;
		
		double q1Rev=0D, q2Rev=0D, h1Rev=0D, q3Rev=0D, q4Rev=0D, h2Rev=0D,
		ytdRev=0D;
		double q1Threshold=0D, q2Threshold=0D, h1Threshold=0D, q3Threshold=0D, 
				q4Threshold=0D, h2Threshold=0D, ytdThreshold=0D;

		double rsRev=0D, secRev=0D, ucsRev=0D, ucRev=0D, 
				dcsRev=0D, videoRev=0D, wLanRev=0D;
		
		double rsThreshold=0D, secThreshold=0D, ucsThreshold=0D, ucThreshold=0D, 
				dcsThreshold=0D, videoThreshold=0D, wLanThreshold=0D;

		DashboardXxmValues valueSet = null;
		
		DashboardXxmDataset resultData;
		List<DataBaseXxmDashOtherTableModified> resultSubData1;
		List<DataBaseXxmDashSalesTable> resultSubData2;
		List<DataBaseXxmDashTechTable> resultSubData3;
		DataBaseXxmDashOtherTableModifedQueries query;
		DataBaseXxmDashOtherTableModified currentEntry;
		DataBaseXxmDashSalesTable currentEntry2;
		DataBaseXxmDashTechTable currentEntry3;
		query = new DataBaseXxmDashOtherTableModifedQueries();
		String[] xxmArray, partnerArray;
		int xxmArrayLength = ComponentHelper.getListInArray(jltSalesAgent).length;
		int partnerArrayLength = ComponentHelper.getListInArray(jltPar).length;
		xxmArray = new String[xxmArrayLength];
		partnerArray = new String[partnerArrayLength];
		xxmArray = ComponentHelper.getListInArray(jltSalesAgent);
		partnerArray = ComponentHelper.getListInArray(jltPar);
		try {
			resultData = query.getAll(parPre, parDev, bb, clpl, shark, option);
			
// this is the data for Other Table					
					resultSubData1 = resultData.getOtherTableData();
				    tempCount = resultSubData1.size();
				    for (int j = 0; j < tempCount; j++) {
				    	currentEntry = resultSubData1.get(j);
				    	double q1value1 = currentEntry.getTotCustomers();
				    	double q1value2 = currentEntry.getCusPenQ1();
				    	double q1value3 = currentEntry.getTechCountQ1();
				    	double q1value4 = currentEntry.getOppCountQ1();
				    	double q1value5 = currentEntry.getRevenueAllQ1();
				    	double q1value6 = currentEntry.getBaseListAllQ1();
				    	double q1value7 = currentEntry.getRevenueCollabQ1();
				    	double q1value8 = currentEntry.getBaseListCollabQ1();
				    	double q1value9 = currentEntry.getRevenueDCVQ1();
				    	double q1value10 = currentEntry.getBaseListDCVQ1();
				    	double q1value11 = currentEntry.getRevenueENTNWQ1();
				    	double q1value12 = currentEntry.getBaseListENTNWQ1();
				    	double q1value13 = currentEntry.getRevenueSecurityQ1();
				    	double q1value14 = currentEntry.getBaseListSecurityQ1();
				    	double q1value15 = currentEntry.getCWP();

				    	double q2value2 = currentEntry.getCusPenQ2();
				    	double q2value3 = currentEntry.getTechCountQ2();
				    	double q2value4 = currentEntry.getOppCountQ2();
				    	double q2value5 = currentEntry.getRevenueAllQ2();
				    	double q2value6 = currentEntry.getBaseListAllQ2();
				    	double q2value7 = currentEntry.getRevenueCollabQ2();
				    	double q2value8 = currentEntry.getBaseListCollabQ2();
				    	double q2value9 = currentEntry.getRevenueDCVQ2();
				    	double q2value10 = currentEntry.getBaseListDCVQ2();
				    	double q2value11 = currentEntry.getRevenueENTNWQ2();
				    	double q2value12 = currentEntry.getBaseListENTNWQ2();
				    	double q2value13 = currentEntry.getRevenueSecurityQ2();
				    	double q2value14 = currentEntry.getBaseListSecurityQ2();
				    	
				    	double h1value2 = currentEntry.getCusPenH1();
				    	double h1value3 = currentEntry.getTechCountH1();
				    	double h1value4 = currentEntry.getOppCountH1();
				    	double h1value5 = currentEntry.getRevenueAllH1();
				    	double h1value6 = currentEntry.getBaseListAllH1();
				    	double h1value7 = currentEntry.getRevenueCollabH1();
				    	double h1value8 = currentEntry.getBaseListCollabH1();
				    	double h1value9 = currentEntry.getRevenueDCVH1();
				    	double h1value10 = currentEntry.getBaseListDCVH1();
				    	double h1value11 = currentEntry.getRevenueENTNWH1();
				    	double h1value12 = currentEntry.getBaseListENTNWH1();
				    	double h1value13 = currentEntry.getRevenueSecurityH1();
				    	double h1value14 = currentEntry.getBaseListSecurityH1();

				    	double q3value2 = currentEntry.getCusPenQ3();
				    	double q3value3 = currentEntry.getTechCountQ3();
				    	double q3value4 = currentEntry.getOppCountQ3();
				    	double q3value5 = currentEntry.getRevenueAllQ3();
				    	double q3value6 = currentEntry.getBaseListAllQ3();
				    	double q3value7 = currentEntry.getRevenueCollabQ3();
				    	double q3value8 = currentEntry.getBaseListCollabQ3();
				    	double q3value9 = currentEntry.getRevenueDCVQ3();
				    	double q3value10 = currentEntry.getBaseListDCVQ3();
				    	double q3value11 = currentEntry.getRevenueENTNWQ3();
				    	double q3value12 = currentEntry.getBaseListENTNWQ3();
				    	double q3value13 = currentEntry.getRevenueSecurityQ3();
				    	double q3value14 = currentEntry.getBaseListSecurityQ3();

				    	double q4value2 = currentEntry.getCusPenQ4();
				    	double q4value3 = currentEntry.getTechCountQ4();
				    	double q4value4 = currentEntry.getOppCountQ4();
				    	double q4value5 = currentEntry.getRevenueAllQ4();
				    	double q4value6 = currentEntry.getBaseListAllQ4();
				    	double q4value7 = currentEntry.getRevenueCollabQ4();
				    	double q4value8 = currentEntry.getBaseListCollabQ4();
				    	double q4value9 = currentEntry.getRevenueDCVQ4();
				    	double q4value10 = currentEntry.getBaseListDCVQ4();
				    	double q4value11 = currentEntry.getRevenueENTNWQ4();
				    	double q4value12 = currentEntry.getBaseListENTNWQ4();
				    	double q4value13 = currentEntry.getRevenueSecurityQ4();
				    	double q4value14 = currentEntry.getBaseListSecurityQ4();
				    	
				    	double h2value2 = currentEntry.getCusPenH2();
				    	double h2value3 = currentEntry.getTechCountH2();
				    	double h2value4 = currentEntry.getOppCountH2();
				    	double h2value5 = currentEntry.getRevenueAllH2();
				    	double h2value6 = currentEntry.getBaseListAllH2();
				    	double h2value7 = currentEntry.getRevenueCollabH2();
				    	double h2value8 = currentEntry.getBaseListCollabH2();
				    	double h2value9 = currentEntry.getRevenueDCVH2();
				    	double h2value10 = currentEntry.getBaseListDCVH2();
				    	double h2value11 = currentEntry.getRevenueENTNWH2();
				    	double h2value12 = currentEntry.getBaseListENTNWH2();
				    	double h2value13 = currentEntry.getRevenueSecurityH2();
				    	double h2value14 = currentEntry.getBaseListSecurityH2();

				    	double ytdvalue2 = currentEntry.getCusPenYTD();
				    	double ytdvalue3 = currentEntry.getTechCountYTD();
				    	double ytdvalue4 = currentEntry.getOppCountYTD();
				    	double ytdvalue5 = currentEntry.getRevenueAllYTD();
				    	double ytdvalue6 = currentEntry.getBaseListAllYTD();
				    	double ytdvalue7 = currentEntry.getRevenueCollabYTD();
				    	double ytdvalue8 = currentEntry.getBaseListCollabYTD();
				    	double ytdvalue9 = currentEntry.getRevenueDCVYTD();
				    	double ytdvalue10 = currentEntry.getBaseListDCVYTD();
				    	double ytdvalue11 = currentEntry.getRevenueENTNWYTD();
				    	double ytdvalue12 = currentEntry.getBaseListENTNWYTD();
				    	double ytdvalue13 = currentEntry.getRevenueSecurityYTD();
				    	double ytdvalue14 = currentEntry.getBaseListSecurityYTD();
				    	
				    	String sAgent = currentEntry.getSalesAgentName();
				    	String pName = currentEntry.getPartnerName();
				    	for (int i=0; i<xxmArrayLength; i++) {
					    	for (int l=0; l<partnerArrayLength; l++) {
					    		if (xxmArray[i].equals(sAgent) && 
					    				partnerArray[l].equals(pName)) {
/*									JOptionPane.showMessageDialog(null, q1value1 + "Array XXm: " + xxmArray[i] + 
											" S Agent: " + sAgent + "Partner Array: " + partnerArray[l] + " PName: " + 
											pName);*/
					    			totCustomers += q1value1;
					    			q1cusPen += q1value2;
					    			q1techCount += q1value3;
					    			q1oppCount += q1value4;
					    			q1revAll += q1value5;
					    			q1bListAll += q1value6;
					    			q1revColl += q1value7;
					    			q1bListColl += q1value8;
					    			q1revDCV += q1value9;
					    			q1bListDCV += q1value10;
					    			q1revBN += q1value11;
					    			q1bListBN += q1value12; 
					    			q1revSec += q1value13;
					    			q1bListSec += q1value14;
//					    			JOptionPane.showMessageDialog(null, q1value5);
					    			cwp += q1value15;
					    			

					    			q2cusPen += q2value2;
					    			q2techCount += q2value3;
					    			q2oppCount += q2value4;
					    			q2revAll += q2value5;
					    			q2bListAll += q2value6;
					    			q2revColl += q2value7;
					    			q2bListColl += q2value8;
					    			q2revDCV += q2value9;
					    			q2bListDCV += q2value10;
					    			q2revBN += q2value11;
					    			q2bListBN += q2value12; 
					    			q2revSec += q2value13;
					    			q2bListSec += q2value14;

					    			h1cusPen += h1value2;
					    			h1techCount += h1value3;
					    			h1oppCount += h1value4;
					    			h1revAll += h1value5;
					    			h1bListAll += h1value6;
					    			h1revColl += h1value7;
					    			h1bListColl += h1value8;
					    			h1revDCV += h1value9;
					    			h1bListDCV += h1value10;
					    			h1revBN += h1value11;
					    			h1bListBN += h1value12; 
					    			h1revSec += h1value13;
					    			h1bListSec += h1value14;

					    			q3cusPen += q3value2;
					    			q3techCount += q3value3;
					    			q3oppCount += q3value4;
					    			q3revAll += q3value5;
					    			q3bListAll += q3value6;
					    			q3revColl += q3value7;
					    			q3bListColl += q3value8;
					    			q3revDCV += q3value9;
					    			q3bListDCV += q3value10;
					    			q3revBN += q3value11;
					    			q3bListBN += q3value12; 
					    			q3revSec += q3value13;
					    			q3bListSec += q3value14;

					    			q4cusPen += q4value2;
					    			q4techCount += q4value3;
					    			q4oppCount += q4value4;
					    			q4revAll += q4value5;
					    			q4bListAll += q4value6;
					    			q4revColl += q4value7;
					    			q4bListColl += q4value8;
					    			q4revDCV += q4value9;
					    			q4bListDCV += q4value10;
					    			q4revBN += q4value11;
					    			q4bListBN += q4value12; 
					    			q4revSec += q4value13;
					    			q4bListSec += q4value14;

					    			h2cusPen += h2value2;
					    			h2techCount += h2value3;
					    			h2oppCount += h2value4;
					    			h2revAll += h2value5;
					    			h2bListAll += h2value6;
					    			h2revColl += h2value7;
					    			h2bListColl += h2value8;
					    			h2revDCV += h2value9;
					    			h2bListDCV += h2value10;
					    			h2revBN += h2value11;
					    			h2bListBN += h2value12; 
					    			h2revSec += h2value13;
					    			h2bListSec += h2value14;

					    			ytdcusPen += ytdvalue2;
					    			ytdtechCount += ytdvalue3;
					    			ytdoppCount += ytdvalue4;
					    			ytdrevAll += ytdvalue5;
					    			ytdbListAll += ytdvalue6;
					    			ytdrevColl += ytdvalue7;
					    			ytdbListColl += ytdvalue8;
					    			ytdrevDCV += ytdvalue9;
					    			ytdbListDCV += ytdvalue10;
					    			ytdrevBN += ytdvalue11;
					    			ytdbListBN += ytdvalue12; 
					    			ytdrevSec += ytdvalue13;
					    			ytdbListSec += ytdvalue14;
					    		}
					    	}
				    	}
					}

				 // this is the data for Sales Table					
					resultSubData2 = resultData.getSalesTableData();
				    tempCount2 = resultSubData2.size();
				    for (int j = 0; j < tempCount2; j++) {
				    	currentEntry2 = resultSubData2.get(j);
				    	double q1 = currentEntry2.getQ1Rev();
				    	double q2 = currentEntry2.getQ2Rev();
				    	double h1 = currentEntry2.getH1Rev();
				    	double q3 = currentEntry2.getQ3Rev();
				    	double q4 = currentEntry2.getQ4Rev();
				    	double h2 = currentEntry2.getH2Rev();
				    	double ytd = currentEntry2.getYTDRev();
				    	
				    	String sAgent = currentEntry2.getSalesAgentName();
				    	String pName = currentEntry2.getUniquePartnerName();
				    	String dType = currentEntry2.getDataType();
				    	for (int i=0; i<xxmArrayLength; i++) {
					    	for (int l=0; l<partnerArrayLength; l++) {
					    		if (xxmArray[i].equals(sAgent) && 
					    				partnerArray[l].equals(pName) &&
					    				dType.equals("Actual")) {
					    			q1Rev += q1;
					    			q2Rev += q2;
					    			h1Rev += h1;
					    			q3Rev += q3;
					    			q4Rev += q4;
					    			h2Rev += h2;
					    			ytdRev += ytd;
					    		} else if ((xxmArray[i].equals(sAgent) && 
					    				partnerArray[l].equals(pName) &&
					    				dType.equals("Threshold"))) {
					    			q1Threshold += q1;
					    			q2Threshold += q2;
					    			h1Threshold += h1;
					    			q3Threshold += q3;
					    			q4Threshold += q4;
					    			h2Threshold += h2;
					    			ytdThreshold += ytd;
					    		}
					    	}
				    	}
					}
				
					 // this is the data for Tech Table					
						resultSubData3 = resultData.getTechTableData();
					    tempCount3 = resultSubData3.size();
					    for (int j = 0; j < tempCount3; j++) {
					    	currentEntry3 = resultSubData3.get(j);
					    	double rs = currentEntry3.getRS();
					    	double sec = currentEntry3.getSec();
					    	double ucs = currentEntry3.getUCS();
					    	double uc = currentEntry3.getUC();
					    	double dcs = currentEntry3.getDCS();
					    	double video = currentEntry3.getVideo();
					    	double wLan = currentEntry3.getWLAN();
					    	
					    	String sAgent = currentEntry3.getSalesAgentName();
					    	String pName = currentEntry3.getUniquePartnerName();
					    	String dType = currentEntry3.getDataType();
					    	for (int i=0; i<xxmArrayLength; i++) {
						    	for (int l=0; l<partnerArrayLength; l++) {
						    		if (xxmArray[i].equals(sAgent) && 
						    				partnerArray[l].equals(pName) &&
						    				dType.equals("Actual")) {
						    			rsRev += rs;
						    			secRev += sec;
						    			ucsRev += ucs;
						    			ucRev += uc;
						    			dcsRev += dcs;
						    			videoRev += video;
						    			wLanRev += wLan;
						    		} else if ((xxmArray[i].equals(sAgent) && 
						    				partnerArray[l].equals(pName) &&
						    				dType.equals("Threshold"))) {
						    			rsThreshold += rs;
						    			secThreshold += sec;
						    			ucsThreshold += ucs;
						    			ucThreshold += uc;
						    			dcsThreshold += dcs;
						    			videoThreshold += video;
						    			wLanThreshold += wLan;
						    		}
						    	}
					    	}
						}

				    
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		otherTableValues = new DashboardXxmOtherTableValues(
    			totCustomers,
    			q1cusPen,
    			q1techCount,
    			q1oppCount,
    			q1revAll,
    			q1bListAll,
    			q1revColl,
    			q1bListColl,
    			q1revDCV,
    			q1bListDCV,
    			q1revBN,
    			q1bListBN, 
    			q1revSec,
    			q1bListSec,
    			cwp,
    			q2cusPen,
    			q2techCount,
    			q2oppCount,
    			q2revAll,
    			q2bListAll,
    			q2revColl,
    			q2bListColl,
    			q2revDCV,
    			q2bListDCV,
    			q2revBN,
    			q2bListBN, 
    			q2revSec,
    			q2bListSec,
    			h1cusPen,
    			h1techCount,
    			h1oppCount,
    			h1revAll,
    			h1bListAll,
    			h1revColl,
    			h1bListColl,
    			h1revDCV,
    			h1bListDCV,
    			h1revBN,
    			h1bListBN, 
    			h1revSec,
    			h1bListSec,
    			q3cusPen,
    			q3techCount,
    			q3oppCount,
    			q3revAll,
    			q3bListAll,
    			q3revColl,
    			q3bListColl,
    			q3revDCV,
    			q3bListDCV,
    			q3revBN,
    			q3bListBN, 
    			q3revSec,
    			q3bListSec,
    			q4cusPen,
    			q4techCount,
    			q4oppCount,
    			q4revAll,
    			q4bListAll,
    			q4revColl,
    			q4bListColl,
    			q4revDCV,
    			q4bListDCV,
    			q4revBN,
    			q4bListBN, 
    			q4revSec,
    			q4bListSec,
    			h2cusPen,
    			h2techCount,
    			h2oppCount,
    			h2revAll,
    			h2bListAll,
    			h2revColl,
    			h2bListColl,
    			h2revDCV,
    			h2bListDCV,
    			h2revBN,
    			h2bListBN, 
    			h2revSec,
    			h2bListSec,
    			ytdcusPen,
    			ytdtechCount,
    			ytdoppCount,
    			ytdrevAll,
    			ytdbListAll,
    			ytdrevColl,
    			ytdbListColl,
    			ytdrevDCV,
    			ytdbListDCV,
    			ytdrevBN,
    			ytdbListBN, 
    			ytdrevSec,
    			ytdbListSec);
		salesTableValues = new DashboardXxmSalesTableValues(
    			q1Rev,
    			q2Rev,
    			h1Rev,
    			q3Rev,
    			q4Rev,
    			h2Rev,
    			ytdRev,
    			q1Threshold,
    			q2Threshold,
    			h1Threshold,
    			q3Threshold,
    			q4Threshold,
    			h2Threshold,
    			ytdThreshold);
		
		techTableValues = new DashboardXxmTechTableValues(
    			rsRev,
    			secRev,
    			ucsRev,
    			ucRev,
    			dcsRev,
    			videoRev,
    			wLanRev,
    			rsThreshold,
    			secThreshold,
    			ucsThreshold,
    			ucThreshold,
    			dcsThreshold,
    			videoThreshold,
    			wLanThreshold);
		valueSet = new DashboardXxmValues(otherTableValues, salesTableValues, techTableValues);
		
		return valueSet;
	}

	
}
