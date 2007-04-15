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
import org.jia.util.Util;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;

public class Navigator_ToolbarTag extends HtmlTableBaseTag
{
  private String headerClass;
  private String itemClass;
  private String selectedItemClass;
  private String headerLabel;
  private String layout;
  private String value;
  private String iconClass;
  private String immediate;
  private String actionListener;

  public Navigator_ToolbarTag()
  {
    super();
  }

  public String getComponentType()
  {
    return UINavigator.COMPONENT_TYPE;
  }

  public String getRendererType()
  {
    return "jia.Toolbar";
  }

  protected void setProperties(UIComponent component)
  {
    super.setProperties(component);
    UINavigator navigator = (UINavigator)component;
    FacesContext context = FacesContext.getCurrentInstance();
    Application app = context.getApplication();

    if (immediate != null)
    {
      if (isValueReference(immediate))
      {
        navigator.setValueBinding("immediate",app.
                                  createValueBinding(immediate));
      }
      else
      {
        navigator.setImmediate(new Boolean(immediate).booleanValue());
      }
    }
    if (headerLabel != null)
    {
      if (isValueReference(headerLabel))
      {
        ValueBinding binding = app.createValueBinding(headerLabel);
        navigator.addStandardHeader((String)binding.getValue(context));
      }
      else
      {
        navigator.addStandardHeader(headerLabel);
      }
    }
    if (actionListener != null)
    {
      if (isValueReference(actionListener))
      {
        MethodBinding mBinding = app.createMethodBinding(actionListener,
                                           new Class[] { ActionEvent.class });
        navigator.setActionListener(mBinding);
      }
      else
      {
        throw new IllegalArgumentException("The actionListener property " +
          "must be a method binding expression");  /**/
      }
    }

    if (value != null)
    {
      if (isValueReference(value))
      {
        navigator.setValueBinding("value", app.createValueBinding(value));
      }
      else
      {
        throw new IllegalArgumentException("The value property must be a value " +
              "binding expression that points to a NavigatorItemList object.");
      }
    }

    Util.addAttribute(app, navigator, "headerClass", headerClass);
    Util.addAttribute(app, navigator, "itemClass", itemClass);
    Util.addAttribute(app, navigator, "selectedItemClass", selectedItemClass);
    Util.addAttribute(app, navigator, "iconClass", iconClass);
    Util.addAttribute(app, navigator, "layout", layout);
  }

  public ValueBinding createDefaultValueBinding(FacesContext context)
  {
    return context.getApplication().createValueBinding(
            "#{sessionScope.NavigatorItemList" + getId() + "}");
  }

  public void release()
  {
    super.release();

    headerClass = null;
    itemClass = null;
    selectedItemClass = null;
    headerLabel = null;
    layout = null;
    value = null;
    immediate = null;
    iconClass = null;
    actionListener = null;
  }

  // Properties


  // setId is implemented in superclass, but getId isn't.
  public String getId()
  {
    return super.getId();
  }

  public String getHeaderClass()
  {
    return headerClass;
  }

  public void setHeaderClass(String headerClass)
  {
    this.headerClass = headerClass;
  }

  public String getImmediate()
  {
    return immediate;
  }

  public void setImmediate(String immediate)
  {
    this.immediate = immediate;
  }

  public String getItemClass()
  {
    return itemClass;
  }

  public void setItemClass(String itemClass)
  {
    this.itemClass = itemClass;
  }

  public String getSelectedItemClass()
  {
    return selectedItemClass;
  }

  public void setSelectedItemClass(String selectedItemClass)
  {
    this.selectedItemClass = selectedItemClass;
  }

  public String getHeaderLabel()
  {
    return headerLabel;
  }

  public void setHeaderLabel(String headerLabel)
  {
    this.headerLabel = headerLabel;
  }

  public String getLayout()
  {
    return layout;
  }

  public void setLayout(String layout)
  {
    this.layout = layout;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public String getIconClass()
  {
    return iconClass;
  }

  public void setIconClass(String iconClass)
  {
    this.iconClass = iconClass;
  }

  public String getActionListener()
  {
    return actionListener;
  }

  public void setActionListener(String actionListener)
  {
    this.actionListener = actionListener;
  }
}
