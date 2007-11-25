package org.jia.ptrack.ws.springws.server;

import java.util.List;

import org.jia.ptrack.facade.ProjectSummary;

public class ProjectsWaitingForApprovalResponse {
  private List<ProjectSummary> summaries;

  public ProjectsWaitingForApprovalResponse(List<ProjectSummary> summaries) {
    this.summaries = summaries;
  }

  public List<ProjectSummary> getSummaries() {
    return summaries;
  }

}
