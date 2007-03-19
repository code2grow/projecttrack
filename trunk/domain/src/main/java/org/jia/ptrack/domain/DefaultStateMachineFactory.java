package org.jia.ptrack.domain;

public class DefaultStateMachineFactory {

	public StateMachine makeStateMachine(String name) {
		RoleType systemsManager = RoleType.SYSTEMS_MANAGER;
		RoleType developmentManager = RoleType.DEVELOPMENT_MANAGER;
		RoleType projectManager = RoleType.PROJECT_MANAGER;
		RoleType businessAnalyst = RoleType.BUSINESS_ANALYST;
		RoleType qaManager = RoleType.QA_MANAGER;

		Status proposal = new Status("Proposal", projectManager);
		Status planning = new Status("Planning", projectManager);
		Status analysis = new Status("Requirements/Analysis", businessAnalyst);
		Status architecture = new Status("Architecture", developmentManager);
		Status initialDevelopment = new Status("Core Development",
				developmentManager);
		Status betaDeployment = new Status("Beta Deployment", systemsManager);
		Status betaTesting = new Status("Beta Testing", qaManager);
		Status finalDevelopment = new Status("Final Development",
				developmentManager);
		Status uatDeployment = new Status("Acceptance Testing Deployment",
				systemsManager);
		Status uaTesting = new Status("Acceptance Testing", qaManager);
		Status productionDeployment = new Status("Production Deployment",
				systemsManager);
		Status complete = new Status("Complete", projectManager);
		Status closed = new Status("Closed", null);

		proposal.rejectionStatus = proposal;
		proposal.approvalStatus = planning;
		proposal.initialState = true;

		planning.rejectionStatus = proposal;
		planning.approvalStatus = analysis;

		analysis.rejectionStatus = planning;
		analysis.approvalStatus = architecture;

		architecture.rejectionStatus = analysis;
		architecture.approvalStatus = initialDevelopment;

		initialDevelopment.rejectionStatus = architecture;
		initialDevelopment.approvalStatus = betaDeployment;

		betaDeployment.rejectionStatus = initialDevelopment;
		betaDeployment.approvalStatus = betaTesting;

		betaTesting.rejectionStatus = initialDevelopment;
		betaTesting.approvalStatus = finalDevelopment;

		finalDevelopment.rejectionStatus = betaTesting;
		finalDevelopment.approvalStatus = uatDeployment;

		uatDeployment.rejectionStatus = finalDevelopment;
		uatDeployment.approvalStatus = uaTesting;

		uaTesting.rejectionStatus = uatDeployment;
		uaTesting.approvalStatus = productionDeployment;

		productionDeployment.rejectionStatus = finalDevelopment;
		productionDeployment.approvalStatus = complete;

		complete.rejectionStatus = productionDeployment;
		complete.approvalStatus = closed;

		closed.rejectionStatus = closed;
		closed.approvalStatus = closed;
		closed.finalState = true;

		return new StateMachine(name, proposal);
	}
}
