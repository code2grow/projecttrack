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

package org.jia.util;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.ValueBinding;

import java.io.IOException;
import java.util.Map;

public class Util
{
  /** An array of all the HTML pass-through attributes. The "class" attribute
   *  is left out, since it's a special case. */
  protected static String[] passthroughAttributes =
      {"onclick", "onkeydown", "onkeyup", "onmousedown", "onmousemove", "onblur",
       "onchange", "ondblclick", "onfocus", "onkeypress", "onreset", "onselect",
       "onmouseout", "onmouseover", "onmouseup", "onsubmit", "onunload",
       "disabled", "readonly", "alt", "lang", "dir", "tabindex", "accesskey",
       "title", "style", "rows", "cols", "height", "size", "maxlength", "width",
       "dir", "longdesc", "rules", "frame", "border", "cellspacing", "cellpadding",
       "bgcolor", "usemap", "rel", "rev", "shape", "coords", "hreflang",
       "enctype", "acceptcharset", "accept", "summary", "target", "ismap"};

  public static void addAttribute(Application app, UIComponent component,
                                  String key, Object value)
  {
    if (key != null && value != null)
    {
      if (isBindingExpression(value))
      {
        component.setValueBinding(key, app.createValueBinding((String)value));
      }
      else
      {
        component.getAttributes().put(key, value);
      }
    }
  }

  public static boolean isBindingExpression(Object value)
  {
    return (value != null && value instanceof String &&
            ((String)value).startsWith("#{") &&
            ((String)value).endsWith("}"));
  }

  public static void writePassthroughAttributes(Map componentAttributes,
                                                ResponseWriter writer)
                     throws IOException
  {
    for (int i = 0; i < passthroughAttributes.length; i++)
    {
      Object value = componentAttributes.get(passthroughAttributes[i]);
      if (value != null)
      {
        writer.writeAttribute(passthroughAttributes[i], value,
                              passthroughAttributes[i]);
      }
    }
    String styleClass = (String)componentAttributes.get("styleClass");
    if (styleClass != null)
    {
      writer.writeAttribute("class", styleClass, null);
    }
  }

  public static boolean canModifyValue(UIComponent component)
  {
    return (component.isRendered() &&
            !getBooleanValue(component.getAttributes().get("readonly"), false) &&
            !getBooleanValue(component.getAttributes().get("disabled"), false));
  }

  public static boolean getBooleanValue(Object attributeValue,
                                        boolean valueIfNull)
  {
    if (attributeValue == null)
    {
      return valueIfNull;
    }
    if (attributeValue instanceof String)
    {
     return ((String)attributeValue).equalsIgnoreCase("true");
    }
    else
    {
      return ((Boolean)attributeValue).booleanValue();
    }
  }

  public static boolean getBooleanProperty(UIComponent component,
                                           Boolean property, String key,
                                           boolean defaultValue)
  {
    if (property != null)
    {
      return property.booleanValue();
    }
    else
    {
      ValueBinding binding = (ValueBinding)component.getValueBinding(key);
      if (binding != null)
      {
        Boolean value = (Boolean)binding.getValue(
                FacesContext.getCurrentInstance());
        if (value != null)
        {
          return value.booleanValue();
        }
      }
    }
    return defaultValue;
  }


  public static String getStringProperty(UIComponent component,
                                         String property, String key,
                                         String defaultValue)
  {
    if (property != null)
    {
      return property;
    }
    else
    {
      ValueBinding binding = (ValueBinding)component.getValueBinding(key);
      if (binding != null)
      {
        return (String)binding.getValue(FacesContext.getCurrentInstance());
      }
    }
    return defaultValue;
  }

  public static Object getObjectProperty(UIComponent component,
                                         Object property, String key,
                                         Object defaultValue)
  {
    if (property != null)
    {
      return property;
    }
    else
    {
      ValueBinding binding = (ValueBinding)component.getValueBinding(key);
      if (binding != null)
      {
        return binding.getValue(FacesContext.getCurrentInstance());
      }
    }
    return defaultValue;
  }
}
