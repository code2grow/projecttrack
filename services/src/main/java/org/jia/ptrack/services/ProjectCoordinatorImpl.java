package org.jia.ptrack.services;

import java.util.List;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.domain.ProjectRepository;
import org.jia.ptrack.domain.RoleType;
import org.jia.ptrack.domain.StateMachine;
import org.jia.ptrack.domain.StateMachineRepository;
import org.jia.ptrack.domain.User;

public class ProjectCoordinatorImpl implements ProjectCoordinator {

	private ProjectRepository projectRepository;
	private StateMachineRepository stateMachineRepository;
	private SecurityInfoProvider securityInfo;
	
	public ProjectCoordinatorImpl(ProjectRepository projectRepository,
			StateMachineRepository stateMachineRepository,
			SecurityInfoProvider securityInfo) {
		this.projectRepository = projectRepository;
		this.stateMachineRepository = stateMachineRepository;
		this.securityInfo = securityInfo;
	}

	public Project add(Project project){
		StateMachine sm = stateMachineRepository.findTheStateMachine();
	    project.setInitialStatus(sm.getInitialStatus());
	    project.setInitiatedBy(securityInfo.getCurrentUser());
		projectRepository.add(project);
		return project;
	}

	public Project get(int id) {
		return projectRepository.get(id);
	}

	public List getAllProjects(ProjectColumnType sortColumn) {
		return projectRepository.getAllProjects(sortColumn);
	}

	public List getProjectsWaitingForApproval(ProjectColumnType sortColumn) {
		RoleType role = getRoleForLoggedInUser();
		return projectRepository.getProjectsWaitingApprovalByRole(role, sortColumn);
	}

	private RoleType getRoleForLoggedInUser() {
		User user = securityInfo.getCurrentUser();
		return user.getRole();
	}

	public boolean changeStatus(Project project, boolean approve, String comments) {
		project = projectRepository.merge(project);
		User user = securityInfo.getCurrentUser();
		return project.changeStatus(approve, user, comments);
	}

}
