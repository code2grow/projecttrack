package org.jia.ptrack.domain;

public class DefaultStateMachineFactory {

	public static StateMachine makeStateMachine(String name) {
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

		// TODO - consider using a factory for Status objects
		
		proposal.setRejectionStatus(proposal);
		proposal.setApprovalStatus(planning);
		proposal.setInitialState(true);

		planning.setRejectionStatus(proposal);
		planning.setApprovalStatus(analysis);

		analysis.setRejectionStatus(planning);
		analysis.setApprovalStatus(architecture);

		architecture.setRejectionStatus(analysis);
		architecture.setApprovalStatus(initialDevelopment);

		initialDevelopment.setRejectionStatus(architecture);
		initialDevelopment.setApprovalStatus(betaDeployment);

		betaDeployment.setRejectionStatus(initialDevelopment);
		betaDeployment.setApprovalStatus(betaTesting);

		betaTesting.setRejectionStatus(initialDevelopment);
		betaTesting.setApprovalStatus(finalDevelopment);

		finalDevelopment.setRejectionStatus(betaTesting);
		finalDevelopment.setApprovalStatus(uatDeployment);

		uatDeployment.setRejectionStatus(finalDevelopment);
		uatDeployment.setApprovalStatus(uaTesting);

		uaTesting.setRejectionStatus(uatDeployment);
		uaTesting.setApprovalStatus(productionDeployment);

		productionDeployment.setRejectionStatus(finalDevelopment);
		productionDeployment.setApprovalStatus(complete);

		complete.setRejectionStatus(productionDeployment);
		complete.setApprovalStatus(closed);

		closed.setRejectionStatus(closed);
		closed.setApprovalStatus(closed);
		closed.setFinalState(true);

		return new StateMachine(name, proposal);
	}
}
