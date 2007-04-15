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

package org.jia.converters.taglib;

import org.jia.converters.UserConverter;
import org.jia.util.Util;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.webapp.ConverterTag;

public class UserConverterTag extends ConverterTag
{
  private String style;
  private String showRole;

  public UserConverterTag()
  {
    super();
    setConverterId(UserConverter.CONVERTER_ID);
  }

  protected Converter createConverter() throws javax.servlet.jsp.JspException
  {
    UserConverter converter = (UserConverter)super.createConverter();
    FacesContext context = FacesContext.getCurrentInstance();
    Application app = context.getApplication();

    if (style != null)
    {
      if (Util.isBindingExpression(style))
      {
        converter.setStyle((UserConverter.StyleType)
                            app.createValueBinding(style).getValue(context));
      }
      else
      {
        UserConverter.StyleType styleType =
          (UserConverter.StyleType)UserConverter.StyleType.
                                         getEnumManager().getInstanceByDescription(style.toLowerCase());
        converter.setStyle(styleType);
      }
    }

    if (showRole != null)
    {
      if (Util.isBindingExpression(showRole))
      {
        converter.setShowRole(((Boolean)app.createValueBinding(showRole).
                                            getValue(context)).booleanValue());
      }
      else
      {
        converter.setShowRole(Boolean.valueOf(showRole).booleanValue());
      }
    }

    return converter;
  }

  public void release()
  {
    super.release();
    style = null;
    showRole = null;
  }

  public String getStyle()
  {
    return style;
  }

  public void setStyle(String style)
  {
    this.style = style;
  }

  public String getShowRole()
  {
    return showRole;
  }

  public void setShowRole(String showRole)
  {
    this.showRole = showRole;
  }
}
