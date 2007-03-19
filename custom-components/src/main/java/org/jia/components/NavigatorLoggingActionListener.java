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

package org.jia.components;

import org.jia.examples.TestForm;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.*;

public class NavigatorLoggingActionListener implements ActionListener
{
  public NavigatorLoggingActionListener()
  {
  }

  public void processAction(ActionEvent event) throws AbortProcessingException
  {
    UINavigator navigator = (UINavigator)event.getSource();
    FacesContext context = FacesContext.getCurrentInstance();
    ValueBinding binding = context.getApplication().createValueBinding("#{testForm}");
    TestForm testForm = (TestForm)binding.getValue(context);
    String message = "NavigatorItem '" +
                     navigator.getItems().getSelectedItem().getName() +
                     "' selected for UINavigator '" + navigator.getId() + "'";
    if (testForm != null)
    {
      testForm.setLoggingMessage(message);
    }
    context.getExternalContext().log(message);
  }

  public PhaseId getPhaseId()
  {
   return PhaseId.APPLY_REQUEST_VALUES;
  }

}
