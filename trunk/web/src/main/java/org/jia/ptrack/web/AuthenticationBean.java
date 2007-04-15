package org.jia.ptrack.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jia.ptrack.domain.RoleType;
import org.jia.ptrack.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class AuthenticationBean extends BaseBean
{
  private String loginName;
  private String password;

  public AuthenticationBean()
  {
  }

  public void setLoginName(String loginName)
  {
    this.loginName = loginName;
  }

  public String getLoginName()
  {
    return loginName;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getPassword()
  {
    return password;
  }

  public String login()
  {
    FacesContext facesContext = getFacesContext();
    Utils.log(facesContext, "Executing AuthenticationBean.getUser()");

    User newUser = null;
    try
    {
      newUser = getUserCoordinator().getUser(loginName, password);
    }
    catch (ObjectRetrievalFailureException e)
    {
      facesContext.addMessage(null,
                              Utils.getMessage("BadLogin", null,
                                               FacesMessage.SEVERITY_INFO));

  /*      facesContext.addMessage(null,
                              new FacesMessage(FacesMessage.SEVERITY_WARN,
                              "Incorrect name or password.", "")); */
      return Constants.FAILURE_OUTCOME;
    }
    catch (DataAccessException d)
    {
       Utils.reportError(facesContext, "ErrorLoadingUser", d);

  /*      Utils.reportError(facesContext, "A database error has occurred.",
                                       "Error loading User object", d);*/
      return Constants.ERROR_OUTCOME;
    }

    Visit visit = getVisit();
    visit.setUser(newUser);
    visit.setAuthenticationBean(this);
//    setVisit(visit);
//
//    getApplication().createValueBinding("#{" + Constants.VISIT_KEY_SCOPE +
//         Constants.VISIT_KEY + "}").setValue(facesContext, visit);

    if (newUser.getRole().equals(RoleType.UPPER_MANAGER))
    {
      return Constants.SUCCESS_READONLY_OUTCOME;
    }

    return Constants.SUCCESS_READWRITE_OUTCOME;
  }

  public boolean isInboxAuthorized()
  {
    return getVisit().isUserInRole(RoleType.PROJECT_APPROVER);
  }

  public boolean isCreateNewAuthorized()
  {
    return getVisit().isUserInRole(RoleType.PROJECT_CREATOR);
  }


}
