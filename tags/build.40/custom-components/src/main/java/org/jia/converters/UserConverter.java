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

package org.jia.converters;

import org.jia.ptrack.domain.*;
import org.jia.ptrack.domain.EnumeratedType.*;

import javax.faces.application.FacesMessage;
import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class UserConverter implements Converter, StateHolder
{
  public final static String CONVERTER_ID = "jia.User";
  private boolean transientFlag;
  private boolean showRole;
  private StyleType style;

  public UserConverter()
  {
    this(StyleType.FIRSTNAME_LASTNAME, false);
  }

  public UserConverter(StyleType style, boolean showRole)
  {
    transientFlag = false;
    style = style;
    showRole = showRole;
  }

  // Converter methods

  public Object getAsObject(FacesContext context, UIComponent component,
                            String displayString) throws ConverterException
  {
    User user = new User();
    FacesMessage message = getStringAsUser(user, displayString);
    if (message != null)
    {
      throw new ConverterException(message);
    }
    return user;
  }

  public String getAsString(FacesContext context, UIComponent component,
                            Object object) throws ConverterException
  {
    if (!(object instanceof User))
    {
      throw new ConverterException("Object is of type " +
                  object.getClass().getName() + " expected object of type " +
                  User.class.getName() + ".");
    }
    return getUserAsString((User)object);
  }

  // Protected methods

  protected FacesMessage getStringAsUser(User user, String displayString)
  {
    FacesMessage errorMessage = null;

    if (style == StyleType.FIRSTNAME)
    {
      errorMessage = getUserFromFirstName(user, displayString);
    }
    else
    if (style == StyleType.LASTNAME)
    {
      errorMessage = getUserFromLastName(user, displayString);
    }
    else
    if (style == StyleType.FIRSTNAME_LASTNAME)
    {
      errorMessage = getUserFromFN_LN(user, displayString);
    }
    else
    if (style == StyleType.LASTNAME_FIRSTNAME)
    {
      errorMessage = getUserFromLN_FN(user, displayString);
    }

    if (errorMessage == null && showRole)
    {
      errorMessage = setUserRole(user, displayString);
    }
    return errorMessage;
  }

  protected FacesMessage getUserFromFirstName(User user, String displayString)
  {
    String[] names = displayString.split("\\s", 2);
    if (showRole != true && names.length > 1)
    {
      return new FacesMessage("Conversion error: string must be in format 'First name' (one word)",
                              null);
    }
    else
    {
      user.setFirstName(displayString);
    }
    return null;
  }

  protected FacesMessage getUserFromLastName(User user, String displayString)
  {
    String[] names = displayString.split("\\s", 2);
    if (showRole != true && names.length > 1)
    {
      return new FacesMessage("Conversion error: string must be in format 'Last name' (one word)",
                              null);
    }
    else
    {
      user.setLastName(displayString);
    }
    return null;
  }

  protected FacesMessage getUserFromFN_LN(User user, String displayString)
  {
    String[] names = displayString.split("\\s", 3);
    if (names.length < 2 || (showRole == false && names.length > 2))
    {
      return new FacesMessage("Conversion error: string must be in format 'First name Last name'",
                              null);
    }
    user.setFirstName(names[0]);
    user.setLastName(names[1]);
    return null;
  }

  protected FacesMessage getUserFromLN_FN(User user, String displayString)
  {
    String[] names = displayString.split(",\\s", 2);
    String firstNames[] = null;
    if (names.length > 1)
    {
      firstNames = names[1].split("\\s", 2);
    }
    if (names.length != 2 ||
        (showRole == false && firstNames.length > 1))
   {
      return new FacesMessage("Conversion error: string must be in format 'Last name, First name'",
                              null);
    }
    user.setLastName(names[0]);
    user.setFirstName(firstNames[0]);
    return null;
  }

  protected FacesMessage setUserRole(User user, String displayString)
  {
    int startIndex = displayString.indexOf(" (");
    int endIndex = displayString.lastIndexOf(')');
    if (startIndex == -1 || endIndex == -1)
    {
      return new FacesMessage("Conversion error: no role found; string must end in format '(Role)'",
                              null);
    }
    String roleString = displayString.substring(startIndex + 2, endIndex);
    try
    {
      user.setRole((RoleType)RoleType.getEnumManager().getInstanceByDescription(roleString));
    }
    catch (IllegalArgumentException e)
    {
      return new FacesMessage("Conversion error: invalid role. Try capitalizing each word.",
                              null);
    }
    return null;
  }

  protected String getUserAsString(User user)
  {
    StringBuffer buffer = new StringBuffer();
    if (style == StyleType.FIRSTNAME && user.getFirstName() != null)
    {
      buffer.append(user.getFirstName());
    }
    else
    if (style == StyleType.LASTNAME && user.getLastName() != null)
    {
      buffer.append(user.getLastName());
    }
    else
    if (style == StyleType.FIRSTNAME_LASTNAME)
    {
      if (user.getFirstName() != null)
      {
        buffer.append(user.getFirstName());
        buffer.append(" ");
      }
      if (user.getLastName() != null)
      {
        buffer.append(user.getLastName());
      }
    }
    else
    if (style == StyleType.LASTNAME_FIRSTNAME)
    {
      if (user.getLastName() != null)
      {
        buffer.append(user.getLastName());
        buffer.append(", ");
      }
      if (user.getFirstName() != null)
      {
        buffer.append(user.getFirstName());
      }
    }

    if (showRole && user.getRole() != null)
    {
      buffer.append(" (");
      buffer.append(user.getRole().getDescription());
      buffer.append(")");
    }
    return buffer.toString();
  }

  // Properties

  public StyleType getStyle()
  {
    return style;
  }

  public void setStyle(StyleType style)
  {
    this.style = style;
  }

  public boolean isShowRole()
  {
    return showRole;
  }

  public void setShowRole(boolean showRole)
  {
    this.showRole = showRole;
  }

  // StateHolder

  public boolean isTransient()
  {
    return transientFlag;
  }

  public void setTransient(boolean transientFlag)
  {
    this.transientFlag = transientFlag;
  }

  public Object saveState(FacesContext context)
  {
    Object[] values = new Object[2];
    values[0] = style.getValue();
    values[1] = showRole == true ? Boolean.TRUE : Boolean.FALSE;
    return values;
  }

  public void restoreState(FacesContext context, Object state)
  {
    Object[] values = (Object[])state;
    String styleValue = (String) values[0];
    style = (StyleType)StyleType.getEnumManager().getInstanceByValue(styleValue);
    showRole = ((Boolean)values[1]).booleanValue();
  }

  // Nested top-level class

  public static class StyleType extends EnumeratedType
  {
    public final static StyleType FIRSTNAME =
            new StyleType(0, "firstname");
    public final static StyleType LASTNAME =
            new StyleType(10, "lastname");
    public final static StyleType FIRSTNAME_LASTNAME =
            new StyleType(20, "firstname_lastname");
    public final static StyleType LASTNAME_FIRSTNAME =
            new StyleType(30, "lastname_firstname");

    private static EnumManager enumManager;

    static
    {
      enumManager = new EnumManager();
      enumManager.addInstance(FIRSTNAME);
      enumManager.addInstance(LASTNAME);
      enumManager.addInstance(FIRSTNAME_LASTNAME);
      enumManager.addInstance(LASTNAME_FIRSTNAME);
    }

    public static EnumManager getEnumManager()
    {
      return enumManager;
    }

    private StyleType(int value, String description)
    {
      super(value, description);
    }
  }
}
