
package org.jia.ptrack.ws.springws.server;

import java.util.List;

import org.jia.ptrack.facade.ProjectCoordinatorFacade;
import org.jia.ptrack.facade.ProjectSummary;
import org.springframework.ws.server.endpoint.AbstractMarshallingPayloadEndpoint;

public class ProjectsWaitingForApprovalEndpoint extends
    AbstractMarshallingPayloadEndpoint {

  private ProjectCoordinatorFacade projectCoordinatorFacade;
  
  
  public ProjectsWaitingForApprovalEndpoint(
      ProjectCoordinatorFacade projectCoordinatorFacade) {
    this.projectCoordinatorFacade = projectCoordinatorFacade;
  }


  @Override
  protected Object invokeInternal(Object object) throws Exception {
//    ProjectsWaitingForApprovalRequest request = (ProjectsWaitingForApprovalRequest) object;
    List<ProjectSummary> summaries = projectCoordinatorFacade.getProjectsWaitingForApproval(null);
    return new ProjectsWaitingForApprovalResponse(summaries);
  }

}
