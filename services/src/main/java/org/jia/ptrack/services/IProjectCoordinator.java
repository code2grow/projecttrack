package org.jia.ptrack.services;

import java.io.Serializable;
import java.util.List;

import org.jia.ptrack.domain.DataStoreException;
import org.jia.ptrack.domain.ObjectNotFoundException;
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

public interface IProjectCoordinator extends Serializable
{
  public Project add(Project project) throws DataStoreException;

  public Project get(String id) throws ObjectNotFoundException,
                                       DataStoreException;
  public List getAllProjects(ProjectColumnType sortColumn) throws ObjectNotFoundException,
                                         DataStoreException;
  public List getProjectsWaitingForApproval(ProjectColumnType sortColumn) throws ObjectNotFoundException,
      DataStoreException;
  
  public boolean changeStatus(Project project, boolean approve, String comments) throws ObjectNotFoundException, DataStoreException;
}
