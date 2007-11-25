package org.jia.ptrack.ws.cxf.server;

import java.util.List;

import javax.jws.WebService;

import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;
import org.jia.ptrack.facade.ProjectDetails;
import org.jia.ptrack.facade.ProjectSummary;

@WebService
public interface ProjectCoordinatorRestfulWS  {

  
  @Get 
  @HttpResource(location="/projects")
  public List<ProjectSummary> getProjects();

  @Get 
  @HttpResource(location="/projects/{id}") 
  public ProjectDetails getProjectDetails(GetProject getProject) ;
}
