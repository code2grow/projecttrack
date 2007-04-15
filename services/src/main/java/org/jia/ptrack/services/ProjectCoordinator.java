package org.jia.ptrack.services;

import java.io.Serializable;
import java.util.List;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public interface ProjectCoordinator extends Serializable
{
  public Project add(Project project);

  public Project get(int id) ;
  public List getAllProjects(ProjectColumnType sortColumn);
  public List getProjectsWaitingForApproval(ProjectColumnType sortColumn);
  
  public boolean changeStatus(Project project, boolean approve, String comments);
}
