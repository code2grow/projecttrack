/**
   JavaServer Faces in Action example code, Copyright (C) 2004 Kito D. Mann.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

**/

package org.jia.ptrack.web;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.jia.ptrack.domain.DataStoreException;
import org.jia.ptrack.domain.EnumeratedType;
import org.jia.ptrack.domain.ObjectNotFoundException;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.services.*;

class CommandType extends EnumeratedType
{
  public final static CommandType APPROVE = new CommandType(0, "Approve");
  public final static CommandType REJECT = new CommandType(10, "Reject");
  public final static CommandType DETAILS = new CommandType(20, "Details");

  private CommandType(int value, String description)
  {
    super(value, description);
  }
}

public class SelectProjectBean  extends BaseBean
{
  private javax.faces.component.UIData projectTable;
  private ProjectColumnType sortColumn;

  public SelectProjectBean()
  {
  }

  public UIData getProjectTable()
  {
    return projectTable;
  }

  public void setProjectTable(UIData projectTable)
  {
    this.projectTable = projectTable;
  }

  public List getInboxProjects() throws DataStoreException
  {
    try
    {
      return getProjectCoordinator().getProjectsWaitingForApproval(sortColumn);
    }
    catch (ObjectNotFoundException e)
    {
      return new ArrayList(0);
    }
  }

  public List getAllProjects() throws DataStoreException
  {
    try
    {
      return getProjectCoordinator().getAllProjects(sortColumn);
    }
    catch (ObjectNotFoundException e)
    {
      return new ArrayList(0);
    }
  }

  public void sort(ActionEvent actionEvent)
  {
    Utils.log(getFacesContext(), "Inside sort; sender id = " + actionEvent.getComponent().getId());
    sortColumn = (ProjectColumnType) ProjectColumnType.getEnumManager().
              getInstanceByDescription(actionEvent.getComponent().getId());
/*
    UIParameter-based way of perameterizing an action listener method.

    boolean paramFound = false;
    List children = e.getComponent().getChildren();
    for (int i = 0; i < children.size(); i++)
    {
      if (children.get(i) instanceof UIParameter)
      {
        UIParameter currentParam = (UIParameter) children.get(i);
        if (currentParam.getName().equals("column") &&
            currentParam.getValue() != null)
        {
          Utils.log(getFacesContext(),
                    "Sorting by column " + currentParam.getValue());
          String paramValue = currentParam.getValue().toString();
          sortColumn = (ProjectColumnType) ProjectColumnType.getEnumManager().
              getInstance(paramValue);
          paramFound = true;
          break;
        }
      }
    }
    if (!paramFound)
    {
      throw new FacesException("Expected child UIParameter with name 'column' and a value equal to the column name to sort.");
    }
 */
  }

  public String approve()
  {
    return getProject(CommandType.APPROVE);
  }

  public String reject()
  {
    return getProject(CommandType.REJECT);
  }

  public String details()
  {
    return getProject(CommandType.DETAILS);
  }

  protected String getProject(CommandType command)
  {
    FacesContext facesContext = getFacesContext();
    Utils.log(facesContext, "Executing GetProjectForm.getProject(), command = " +
              command);
    Project project = (Project) projectTable.getRowData(); //  make sure it still exists
    try
    {
      project = getProjectCoordinator().get(project.getId());
    }
    catch (ObjectNotFoundException e)
    {
      facesContext.addMessage(null,
                              new FacesMessage(FacesMessage.SEVERITY_WARN,
                                               "The project you selected cannot be found",
                                               "The project is no longer in the data store."));
      return Constants.FAILURE_OUTCOME;
    }
    catch (DataStoreException d)
    {
      Utils.reportError(facesContext, "A database error has occrred",
                       "Error loading project", d);
      return Constants.ERROR_OUTCOME;
    }

    if (command == CommandType.APPROVE || command == CommandType.REJECT)
    {
      if (!project.isValidStateChange(command == CommandType.APPROVE))
      {
        Utils.addInvalidStateChangeMessage(facesContext,
                                           command == CommandType.APPROVE);
        return Constants.FAILURE_OUTCOME;
      }
    }

    getVisit().setCurrentProject(project);

    return Constants.SUCCESS_OUTCOME;
  }
  
  // FIXME - Kito what should this do?
  public String getSort() {
	  return null;
  }
}
