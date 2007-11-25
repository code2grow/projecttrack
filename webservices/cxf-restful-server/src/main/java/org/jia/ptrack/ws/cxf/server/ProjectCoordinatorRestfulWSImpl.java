package org.jia.ptrack.ws.cxf.server;

import java.util.List;

import javax.jws.WebService;

import org.jia.ptrack.facade.ProjectCoordinatorFacade;
import org.jia.ptrack.facade.ProjectDetails;
import org.jia.ptrack.facade.ProjectSummary;

@WebService
public class ProjectCoordinatorRestfulWSImpl implements ProjectCoordinatorRestfulWS {

  private ProjectCoordinatorFacade projectCoordinatorFacade;

  
  public ProjectCoordinatorRestfulWSImpl(
      ProjectCoordinatorFacade projectCoordinatorFacade) {
    this.projectCoordinatorFacade = projectCoordinatorFacade;
  }


   public ProjectDetails getProjectDetails(GetProject getProject) {
    return projectCoordinatorFacade.getProjectDetails(getProject.getIdAsInt());
  }


  public List<ProjectSummary> getProjects() {
    List<ProjectSummary> projects = projectCoordinatorFacade.getProjectsWaitingForApproval(null);
    return projects;
  }


}
