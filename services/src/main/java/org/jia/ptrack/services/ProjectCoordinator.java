package org.jia.ptrack.services;

import java.io.Serializable;
import java.util.List;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;

public interface ProjectCoordinator extends Serializable
{
  public Project add(Project project);

  public Project get(int id) ;
  public List<Project> getAllProjects(ProjectColumnType sortColumn);
  public List<Project> getProjectsWaitingForApproval(ProjectColumnType sortColumn);
  
  public boolean changeStatus(Project project, boolean approve, String comments);
}
