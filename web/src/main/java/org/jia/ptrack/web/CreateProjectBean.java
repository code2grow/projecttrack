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

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.jia.ptrack.domain.DataStoreException;
import org.jia.ptrack.domain.Project;

public class CreateProjectBean extends BaseBean
{
//  private javax.faces.component.UISelectOne projectSelectOne;
  private HtmlSelectOneMenu projectSelectOne;
  private UIInput reqContactEmailInput;

  public CreateProjectBean()
  {
  }

  public HtmlSelectOneMenu getProjectSelectOne()
  {
    if (projectSelectOne == null)
    {
      projectSelectOne = (HtmlSelectOneMenu)getApplication().
                             createComponent(HtmlSelectOneMenu.COMPONENT_TYPE);
      projectSelectOne.setId("typeSelectOne");
  //    projectSelectOne.setTitle("Select the project type");
      projectSelectOne.setTitle(
        Utils.getDisplayString(getApplication().getMessageBundle(),
                               "ProjectTypeTitle",
                               null,
                               getFacesContext().getViewRoot().getLocale()));
      projectSelectOne.setRequired(true);
      projectSelectOne.setValueBinding("value",
           getApplication().createValueBinding("#{visit.currentProject.type}"));
      projectSelectOne.setConverter(getApplication().createConverter(
                                        ProjectTypeConverter.CONVERTER_TYPE));
    }

    return projectSelectOne;
  }

  /*
    The above method, using UISelectOne and attributes instead of
    HtmlSelectOneMenu and properties.

    public javax.faces.component.UISelectOne getProjectSelectOne()
    {
      if (projectSelectOne == null)
      {
        projectSelectOne = (UISelectOne)getApplication().
                            createComponent(UISelectOne.COMPONENT_TYPE);
        projectSelectOne.setId("typeSelectOne");
        projectSelectOne.getAttributes().put("title", "Select the project type");
        projectSelectOne.setRequired(true);
        projectSelectOne.setValueBinding("value",
             getApplication().createValueBinding("#{visit.currentProject.type}"));
        projectSelectOne.setConverter(getApplication().createConverter(
                                      ProjectTypeConverter.CONVERTER_TYPE));
      }

      return projectSelectOne;
    }
  */

  public void setProjectSelectOne(HtmlSelectOneMenu projectSelectOne)
  {
    this.projectSelectOne = projectSelectOne;
  }

  public UIInput getReqContactEmailInput()
  {
    return reqContactEmailInput;
  }

  public void setReqContactEmailInput(UIInput reqContactEmailInput)
  {
    this.reqContactEmailInput = reqContactEmailInput;
  }

  public void validateReqContact(FacesContext facesContext,
                                 UIComponent component,
                                 Object newValue) throws ValidatorException
  {
    if (reqContactEmailInput.getSubmittedValue().toString().equals(""))
    {
      facesContext.addMessage(reqContactEmailInput.getClientId(facesContext),
             new FacesMessage("Please fill in this field."));
      throw new ValidatorException(new FacesMessage(
                  "E-mail address is required for the contact."));
    }
  }

  public String create()
  {
    getVisit().setCurrentProject(new Project());
    return Constants.SUCCESS_OUTCOME;
  }

  public String add()
  {
    FacesContext facesContext = getFacesContext();
    Utils.log(facesContext, "Executing NewProjectBean.invoke()");

    Project project = getVisit().getCurrentProject();
    try { getProjectCoordinator().add(project); }
    catch (DataStoreException e)
    {
      Utils.reportError(facesContext, "A database error has occrred",
                       "Error adding project", e);
      return Constants.ERROR_OUTCOME;
    }
    if (getVisit().isReadOnly())
    {
      return Constants.SUCCESS_READONLY_OUTCOME;
    }
    else
    {
      return Constants.SUCCESS_READWRITE_OUTCOME;
    }
  }
}
