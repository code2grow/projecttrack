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

package org.jia.validators;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.*;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class RegularExpressionValidator implements Validator, StateHolder
{
  public static final String VALIDATOR_ID = "jia.RegularExpressionValidator";
  private String expression;
  private String errorMessage;

  private boolean transientFlag;
  protected Pattern pattern;

  public RegularExpressionValidator()
  {
    this(null, null);
  }

  public RegularExpressionValidator(String expression, String errorMessage)
  {
    super();
    transientFlag = false;
    setExpression(expression);
    setErrorMessage(errorMessage);
  }

  // Validator methods

  public void validate(FacesContext context, UIComponent component, Object inputValue)
  {
    EditableValueHolder input = (EditableValueHolder)component;
    String value = null;
    try
    {
      value = (String)inputValue;
    }
    catch (ClassCastException e)
    {
      throw new ValidatorException(new FacesMessage(
                    "Validation Error: Value can not be converted to String.",
                    null));
    }
    if (!isValid(value))
    {
      String messageText = errorMessage;
      if (messageText == null)
      {
        messageText = "Validation Error: This field is not formatted properly.";
      }
      throw new ValidatorException(new FacesMessage(messageText, null));
    }
  }

  // Protected methods

  protected boolean isValid(String value)
  {
    if (pattern == null)
    {
      throw new NullPointerException("The expression property hasn't been set.");
    }

    return pattern.matcher(value).matches();
  }

  // Properties

  public String getErrorMessage()
  {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage)
  {
    this.errorMessage = errorMessage;
  }

  public String getExpression()
  {
    return expression;
  }

  public void setExpression(String expression)
  {
    this.expression = expression;
    if (expression != null)
    {
      pattern = Pattern.compile(expression);
    }
  }

  // StateHolder methods

  public void setTransient(boolean transientValue)
  {
    this.transientFlag = transientValue;
  }

  public boolean isTransient()
  {
    return transientFlag;
  }

  public Object saveState(FacesContext context)
  {
    Object[] values = new Object[3];
    values[0] = pattern;
    values[1] = expression;
    values[2] = errorMessage;

    return values;
  }

  public void restoreState(FacesContext context, Object state)
  {
    Object[] values = (Object[])state;
    pattern = (Pattern)values[0];
    expression = (String)values[1];
    errorMessage = (String)values[2];
  }
}
