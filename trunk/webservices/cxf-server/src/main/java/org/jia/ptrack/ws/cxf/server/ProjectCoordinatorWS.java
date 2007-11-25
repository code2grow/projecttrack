package org.jia.ptrack.ws.cxf.server;

import java.util.List;

import javax.jws.WebService;

import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.facade.ProjectDetails;
import org.jia.ptrack.facade.ProjectSummary;

@WebService
public interface ProjectCoordinatorWS  {

  public ProjectDetails getProjectDetails(int id) ;
  public List<ProjectSummary> getProjectsWaitingForApproval(ProjectColumnType sortColumn);
  public List<ProjectSummary> approveProject(int projectId, int version, String comments);
  public String ping();
}
