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

package org.jia.validators.taglib;

import org.jia.util.Util;
import org.jia.validators.RegularExpressionValidator;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.webapp.ValidatorTag;

import javax.servlet.jsp.JspException;

public class RegexpValidatorTag extends ValidatorTag
{
  private String expression;
  private String errorMessage;

  public RegexpValidatorTag()
  {
    super();
    setValidatorId(RegularExpressionValidator.VALIDATOR_ID);
  }

  // ValidatorTag methods

  protected Validator createValidator()
          throws JspException
  {
    RegularExpressionValidator validator =
            (RegularExpressionValidator)super.createValidator();

    FacesContext context = FacesContext.getCurrentInstance();
    Application app = context.getApplication();

    if (expression != null)
    {
      if (Util.isBindingExpression(expression))
      {
        validator.setExpression((String)app.createValueBinding(expression).
                                            getValue(context));
      }
      else
      {
        validator.setExpression(expression);
      }
    }
    if (errorMessage != null)
    {
      if (Util.isBindingExpression(errorMessage))
      {
        validator.setErrorMessage((String)app.createValueBinding(errorMessage).
                                            getValue(context));
      }
      else
      {
        validator.setErrorMessage(errorMessage);
      }
    }

    return validator;
  }

  public void release()
  {
    super.release();
    expression = null;
    errorMessage = null;
  }

  // Properties

  public String getExpression()
  {
    return expression;
  }

  public void setExpression(String expression)
  {
    this.expression = expression;
  }

  public String getErrorMessage()
  {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage)
  {
    this.errorMessage = errorMessage;
  }
}
