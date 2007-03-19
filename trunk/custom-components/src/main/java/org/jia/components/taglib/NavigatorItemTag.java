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

package org.jia.components.taglib;

import org.jia.components.UINavigator;
import org.jia.components.model.NavigatorItem;
import org.jia.components.model.NavigatorItemList;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentTag;

import javax.servlet.jsp.JspException;

public class NavigatorItemTag extends UIComponentTag
{
  private String name;
  private String label;
  private String icon;
  private String action;
  private String link;
  private String direct;
  private String disabled;

  public NavigatorItemTag()
  {
    super();
  }

  public String getComponentType()
  {
    return null;
  }
  public String getRendererType()
  {
    return null;
  }

  //
  // Methods from UIComponentTag
  //

  public int doStartTag() throws JspException
  {
    FacesContext context = FacesContext.getCurrentInstance();
    Application app = context.getApplication();
    UIComponentTag parentTag = getParentUIComponentTag(pageContext);
    // This check should be handled by a TagLibraryValidator, but...
    if (parentTag == null || !(parentTag instanceof Navigator_ToolbarTag))
    {
      throw new JspException(
             "This tag must be nested inside a parent Navigator_ToolbarTag.");
    }

    Navigator_ToolbarTag parentToolbarTag = (Navigator_ToolbarTag)parentTag;
    UINavigator navigator = (UINavigator)parentToolbarTag.getComponentInstance();
    NavigatorItemList items = navigator.getItems();
    if (items == null)
    {
      items = new NavigatorItemList();
      navigator.setValue(items);
    }
    if (name != null && isValueReference(name))
    {
      name = (String)app.createValueBinding(name).getValue(context);
    }
    if (!items.containsName(name))
    {
      if (label != null && isValueReference(label))
      {
        label = (String)app.createValueBinding(label).getValue(context);
      }
      if (icon != null && isValueReference(icon))
      {
        icon = (String)app.createValueBinding(icon).getValue(context);
      }
      if (link != null && isValueReference(link))
      {
        link = (String)app.createValueBinding(link).getValue(context);
      }
      boolean bDirect = false;
      if (direct != null)
      {
        if (isValueReference(direct))
        {
          bDirect = ((Boolean)app.createValueBinding(direct).getValue(
                  context)).booleanValue();
        }
        else
        {
          bDirect = Boolean.valueOf(direct).booleanValue();
        }
      }
      boolean bDisabled = false;
      if (disabled != null)
      {
        if (isValueReference(disabled))
        {
          bDisabled = ((Boolean)app.createValueBinding(disabled).getValue(
                  context)).booleanValue();
        }
        else
        {
          bDisabled = Boolean.valueOf(disabled).booleanValue();
        }
      }

      items.add(new NavigatorItem(name, label, icon, action, link,
                bDirect, bDisabled));
    }
    return getDoStartValue();
  }

  public int doEndTag() throws JspException
  {
      return getDoEndValue();
  }

  public void release()
  {
    super.release();

    name = null;
    label = null;
    icon = null;
    action = null;
    link = null;
    direct = null;
    disabled = null;
  }

  // Properties

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getIcon()
  {
    return icon;
  }

  public void setIcon(String icon)
  {
    this.icon = icon;
  }

  public String getAction()
  {
    return action;
  }

  public void setAction(String action)
  {
    this.action = action;
  }

  public String getLink()
  {
    return link;
  }

  public void setLink(String link)
  {
    this.link = link;
  }

  public String getDirect()
  {
    return direct;
  }

  public void setDirect(String direct)
  {
    this.direct = direct;
  }

  public String getDisabled()
  {
    return disabled;
  }

  public void setDisabled(String disabled)
  {
    this.disabled = disabled;
  }
}
