package prism14;

import java.util.List;

public class ScorecardXxmDataset {

	private List<ScorecardGoalActualTable> goalActualTable;
	private List<ScorecardGrowthTable> growthTable;
	private List<ScorecardDiscountTable> discountTable;
	private List<ScorecardATPenetrationTable> atPenTable;
//	private List<DataBaseXxmDashOtherTableModified> otherTableTable;
	private List<ScorecardSCPTable> scpTable;
	private List<ScorecardLeadsTable> leadsTable;
	private List<ScorecardPartnerPenetrationTable> partnerPenTable;
	private List<ScorecardLinearityTable> linearityTable;
	private List<ScorecardPipelineTable> pipelineTable;
	private List<ScorecardDebookingTable> debookingTable;
	
	
	public ScorecardXxmDataset(List<ScorecardGoalActualTable> goalActualTable, 
			List<ScorecardGrowthTable> growthTable, 
			List<ScorecardDiscountTable> discountTable, 
			List<ScorecardATPenetrationTable> atPenTable,
/*			List<DataBaseXxmDashOtherTableModified> otherTableTable,*/
			List<ScorecardSCPTable> scpTable,
			List<ScorecardLeadsTable> leadsTable, 
			List<ScorecardPartnerPenetrationTable> partnerPenTable, 
			List<ScorecardLinearityTable> linearityTable, 
			List<ScorecardPipelineTable> pipelineTable, 
			List<ScorecardDebookingTable> debookingTable) {
		this.goalActualTable = goalActualTable;
		this.growthTable = growthTable;
		this.discountTable = discountTable;
		this.atPenTable = atPenTable;
		this.leadsTable = leadsTable;
		this.partnerPenTable = partnerPenTable;
		this.linearityTable = linearityTable;
		this.pipelineTable = pipelineTable;
		this.debookingTable = debookingTable;
		this.scpTable = scpTable;
	}

	public List<ScorecardGoalActualTable> getGoalActualData() {
		return this.goalActualTable;
		}
	
	public List<ScorecardGrowthTable> getGrowthTableData() {
		return this.growthTable;
		}

	public List<ScorecardDiscountTable> getDiscountData() {
		return this.discountTable;
		}

	public List<ScorecardATPenetrationTable> getATPenData() {
		return this.atPenTable;
		}

	public List<ScorecardLeadsTable> getLeadsData() {
		return this.leadsTable;
		}

	public List<ScorecardPartnerPenetrationTable> getPartnerPenetrationData() {
		return this.partnerPenTable;
		}

	public List<ScorecardLinearityTable> getLinearityData() {
		return this.linearityTable;
		}

	public List<ScorecardPipelineTable> getPipelineData() {
		return this.pipelineTable;
		}

	public List<ScorecardDebookingTable> getDebookingData() {
		return this.debookingTable;
		}

/*	public List<DataBaseXxmDashOtherTableModified> getOtherTableData() {
		return this.otherTableTable;
		}
*/
	public List<ScorecardSCPTable> getSCPTableData() {
		return this.scpTable;
		}

}
