package org.jia.ptrack.domain;

public class PTrackWorld {

	private Department department;
	private User projectManager;
	private User businessAnalyst;
	private Project project;
	private Project project2;
	private StateMachine stateMachine;

	public PTrackWorld() {
		department = new Department("IT");
		projectManager = UserFactory.makeProjectManager(department);
		businessAnalyst = UserFactory.makeBusinessAnalyst(department);
		stateMachine = new DefaultStateMachineFactory().makeStateMachine("test");
		Status initialStatus = stateMachine.getInitialStatus();
		project = new Project(initialStatus);
		project.setInitiatedBy(projectManager);

		project2 = new Project(initialStatus);
		project2.setInitiatedBy(projectManager);
	}

	public User getBusinessAnalyst() {
		return businessAnalyst;
	}

	public Department getDepartment() {
		return department;
	}

	public Project getProject() {
		return project;
	}

	public User getProjectManager() {
		return projectManager;
	}

	public StateMachine getStateMachine() {
		return stateMachine;
	}

	public Project getProject2() {
		return project2;
	}
	
	
}
