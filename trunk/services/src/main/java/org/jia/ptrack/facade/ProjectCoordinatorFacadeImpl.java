package org.jia.ptrack.facade;

import java.util.List;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.domain.ProjectRepository;
import org.jia.ptrack.services.ProjectCoordinator;
import org.jia.ptrack.services.SecurityInfoProvider;
import org.springframework.util.Assert;

public class ProjectCoordinatorFacadeImpl implements ProjectCoordinatorFacade {

	private ProjectCoordinator projectCoordinator;
	private final ProjectDTOAssembler assembler;
	private SecurityInfoProvider securityInfoProvider;
	private final ProjectRepository projectRepository;
	
	public ProjectCoordinatorFacadeImpl(ProjectCoordinator projectCoordinator, 
			ProjectDTOAssembler assembler, 
			SecurityInfoProvider securityInfoProvider,
			ProjectRepository projectRepository) {
		this.projectCoordinator = projectCoordinator;
		this.assembler = assembler;
		this.securityInfoProvider = securityInfoProvider;
		this.projectRepository = projectRepository;
	}

	public ProjectDetails getProjectDetails(int id) {
		Project project = projectCoordinator.get(id);
		return assembler.makeProjectDetails(project, securityInfoProvider.getCurrentUser());
	}

	public List<ProjectSummary> getProjectsWaitingForApproval(
			ProjectColumnType sortColumn) {
		List<Project> projects = projectCoordinator.getProjectsWaitingForApproval(sortColumn);
		return assembler.makeProjectSummaries(projects);
	}

	public List<ProjectSummary> approveProject(int projectId, int version, String comments) {
		Project project = projectRepository.get(projectId);
		Assert.isTrue(version == project.getVersion());
		Assert.isTrue(projectCoordinator.changeStatus(project, true, comments));
		return getProjectsWaitingForApproval(null);
	}

}
