package org.jia.ptrack.facade;

import java.util.List;

import org.jia.ptrack.domain.ProjectColumnType;

public interface ProjectCoordinatorFacade {


	  public ProjectDetails getProjectDetails(int id) ;
	  public List<ProjectSummary> getProjectsWaitingForApproval(ProjectColumnType sortColumn);
	  public List<ProjectSummary> approveProject(int projectId, int version, String comments);
	  
//	  public Project add(Project project);
//	  public List getAllProjects(ProjectColumnType sortColumn);
//	  public boolean changeStatus(Project project, boolean approve, String comments);

}
