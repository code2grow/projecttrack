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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.RoleType;
import org.jia.ptrack.domain.User;

public class Visit implements Serializable
{
	// TODO - TC
  private transient Log logger = LogFactory.getLog(getClass());
  
  private User user;
  private Project currentProject;
  private List localeItems;
  private AuthenticationBean authenticationBean;

  public Visit()
  {
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public Project getCurrentProject()
  {
    return currentProject;
  }

  public void setCurrentProject(Project currentProject)
  {
    this.currentProject = currentProject;
  }

  public AuthenticationBean getAuthenticationBean()
  {
    return authenticationBean;
  }

  public void setAuthenticationBean(AuthenticationBean authenticationBean)
  {
    this.authenticationBean = authenticationBean;
  }

  public List getSupportedtLocaleItems()
  {
    if (localeItems == null)
    {
      localeItems = new ArrayList();
      Application app = FacesContext.getCurrentInstance().getApplication();
      for (Iterator i = app.getSupportedLocales(); i.hasNext(); )
      {
        Locale locale = (Locale)i.next();
        SelectItem item = new SelectItem(locale.toString(), locale.getDisplayName());
        localeItems.add(item);
      }
      if (localeItems.size() == 0)
      {
        Locale defaultLocale = app.getDefaultLocale();
        localeItems.add(new SelectItem(defaultLocale.toString(), defaultLocale.getDisplayName()));
      }
    }
    return localeItems;
  }

  // These methods are for demonstration, and not used by the real application

  public SelectItem getExtraLocaleItem()
  {
    return new SelectItem("es", "Spanish");
  }

  public SelectItem[] getSupportedLocaleItemArray()
  {
    SelectItem[] localeItems = new SelectItem[2];
    SelectItem item = new SelectItem();
    localeItems[0] = new SelectItem("en", "English");
    localeItems[1] = new SelectItem("ru", "Russian");
    return localeItems;
  }

  // End demonstration methods

  public String getLocale()
  {
    return FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
  }

  public void setLocale(String locale)
  {
    FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(locale));
  }

	private HttpServletRequest getRequest() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		return request;
	}

	public boolean isReadOnly() {
		return !isInboxAuthorized();
	}

   public boolean isUserInRole(RoleType roleType) {
		// pia-lab-returning-false-stub(acegi-web)
	    HttpServletRequest request = getRequest();
		boolean result = request.isUserInRole(roleType.getValue());
		return result;
	}

   public boolean isInboxAuthorized()
   {
		// pia-lab-returning-false-stub(acegi-web)
    return isUserInRole(RoleType.PROJECT_APPROVER);
   }

  public boolean isCreateNewAuthorized()
  {
		// pia-lab-returning-false-stub(acegi-web)
    return isUserInRole(RoleType.PROJECT_CREATOR);
  }

}
