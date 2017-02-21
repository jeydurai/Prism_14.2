package prism14;

public class ScorecardXxmValues {

	private ScorecardGoalActualValues goalActualValues;
	private ScorecardGrowthValues growthValues;
	private ScorecardDiscountValues discountValues;
	private ScorecardATPenetrationValues atPenValues;
	private ScorecardLeadsValues leadsValues;
	private ScorecardPartnerPenetrationValues partnerPenValues;
	private ScorecardLinearityValues linearityValues;
	private ScorecardPipelineValues pipelineValues;
	private ScorecardDebookingValues debookingValues;
	private ScorecardSCPValues scpTableValues;
	
	
	public ScorecardXxmValues(ScorecardGoalActualValues goalActualValues, 
	ScorecardGrowthValues growthValues, 
	ScorecardDiscountValues discountValues, 
	ScorecardATPenetrationValues atPenValues,
	ScorecardSCPValues scpTableValues,
	ScorecardLeadsValues leadsValues, 
	ScorecardPartnerPenetrationValues partnerPenValues, 
	ScorecardLinearityValues linearityValues, 
	ScorecardPipelineValues pipelineValues, 
	ScorecardDebookingValues debookingValues) {
		this.goalActualValues = goalActualValues;
		this.growthValues = growthValues;
		this.discountValues = discountValues;
		this.atPenValues = atPenValues;
		this.leadsValues = leadsValues;
		this.partnerPenValues = partnerPenValues;
		this.linearityValues = linearityValues;
		this.pipelineValues = pipelineValues;
		this.debookingValues = debookingValues;
		this.scpTableValues = scpTableValues;
	}

	public ScorecardGoalActualValues getGoalActualValues() {
		return this.goalActualValues;
		}
	
	public ScorecardGrowthValues getGrowthValues() {
		return this.growthValues;
		}

	public ScorecardDiscountValues getDiscountValues() {
		return this.discountValues;
		}

	public ScorecardATPenetrationValues getATPenValues() {
		return this.atPenValues;
		}

	public ScorecardLeadsValues getLeadsValues() {
		return this.leadsValues;
		}

	public ScorecardPartnerPenetrationValues getPartnerPenetrationValues() {
		return this.partnerPenValues;
		}

	public ScorecardLinearityValues getLinearityValues() {
		return this.linearityValues;
		}

	public ScorecardPipelineValues getPipelineValues() {
		return this.pipelineValues;
		}

	public ScorecardDebookingValues getDebookingValues() {
		return this.debookingValues;
		}

	public ScorecardSCPValues getSCPTableValues() {
		return this.scpTableValues;
		}

}
