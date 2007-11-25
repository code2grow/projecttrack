package org.jia.ptrack.ws.cxf.server;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.facade.ProjectCoordinatorFacade;
import org.jia.ptrack.facade.ProjectDetails;
import org.jia.ptrack.facade.ProjectSummary;

@WebService
public class ProjectCoordinatorWSImpl implements ProjectCoordinatorWS {

  private ProjectCoordinatorFacade projectCoordinatorFacade;

  
  public ProjectCoordinatorWSImpl(
      ProjectCoordinatorFacade projectCoordinatorFacade) {
    this.projectCoordinatorFacade = projectCoordinatorFacade;
  }


  public List<ProjectSummary> approveProject(int projectId, int version,
      String comments) {
    return projectCoordinatorFacade
        .approveProject(projectId, version, comments);
  }


  public ProjectDetails getProjectDetails(int id) {
    return projectCoordinatorFacade.getProjectDetails(id);
  }


  public List<ProjectSummary> getProjectsWaitingForApproval(
      ProjectColumnType sortColumn) {
    List<ProjectSummary> projects = projectCoordinatorFacade.getProjectsWaitingForApproval(sortColumn);
    System.out.println(projects);
    return projects;
  }


  public String ping() {
    return "Hello " + new Date();
  }

}
