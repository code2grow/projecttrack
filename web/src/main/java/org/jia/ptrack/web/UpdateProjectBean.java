package org.jia.ptrack.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jia.ptrack.domain.Project;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 *
 */

public class UpdateProjectBean extends BaseBean
{
  private String comments;

  public UpdateProjectBean()
  {
  }

  public String getComments()
  {
    return comments;
  }

  public void setComments(String comments)
  {
    this.comments = comments;
  }

  public String approve()
  {
    return update(true);
  }

  public String reject()
  {
    return update(false);
  }

  protected String update(boolean approve)
  {
    FacesContext facesContext = getFacesContext();
    Utils.log(facesContext, "Executing UpdateProjectBean.update(), approve = " + approve);

    Visit visit = getVisit();
    boolean projectFound = true;
    Project project = visit.getCurrentProject();
    try {
			if (!getProjectCoordinator().changeStatus(project, approve, comments)) {
				Utils.addInvalidStateChangeMessage(facesContext, approve);
				return Constants.FAILURE_OUTCOME;
			}
		} catch (ObjectRetrievalFailureException e) {
			projectFound = false;
		} catch (DataAccessException d) {
			Utils.reportError(facesContext, "A database error has occurred.",
					"Error updating project.", d);
			return Constants.ERROR_OUTCOME;
		} 
    if (projectFound == false)
    {
      facesContext.addMessage(null,
                              new FacesMessage(FacesMessage.SEVERITY_WARN,
                                               "The project you selected cannot be found",
                                               "The project is no longer in the data store."));
      return Constants.FAILURE_OUTCOME;
    }

    if (visit.isReadOnly())
    {
      return Constants.SUCCESS_READONLY_OUTCOME;
    }
    else
    {
      return Constants.SUCCESS_READWRITE_OUTCOME;
    }
  }

}
