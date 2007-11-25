package org.jia.ptrack.ws.cxf.client;

import java.util.List;

import org.jia.ptrack.domain.ProjectType;
import org.jia.ptrack.facade.ProjectSummary;
import org.jia.ptrack.ws.cxf.server.ProjectCoordinatorWS;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class CXFClientTests extends
    AbstractDependencyInjectionSpringContextTests {

  private ProjectCoordinatorWS service;
  private ServerLauncher server;

  @Override
  protected String[] getConfigLocations() {
    return new String[] { "/appCtxClient/client.xml" };
  }

  public void setService(ProjectCoordinatorWS service) {
    this.service = service;
  }

  @Override
  protected void onSetUp() throws Exception {
    super.onSetUp();
    server = new ServerLauncher();
    server.start();
  }

  @Override
  protected void onTearDown() throws Exception {
    server.stop();
    super.onTearDown();
  }

  public void testGetProjectsWaitingForApproval() {
    List<ProjectSummary> projects = service.getProjectsWaitingForApproval(null);
    System.out.println(projects);

    ProjectSummary project = projects.get(0);
    System.out.println(project.getName());
    System.out.println(project.getStatusName());
    ProjectType type = project.getType();
    System.out.println(type.toString());

    System.out.println("getting details");
    project = service.getProjectDetails(project.getId());
    System.out.println(project.getName());
    System.out.println(project.getStatusName());
    System.out.println(type.toString());

    projects = service.approveProject(project.getId(), 0, "Awesome");
    System.out.println(projects);
  }

}
