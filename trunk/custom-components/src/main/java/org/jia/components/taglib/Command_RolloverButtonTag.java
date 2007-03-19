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

import org.jia.util.ConstantMethodBinding;
import org.jia.util.Util;

import javax.faces.application.Application;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;

import java.util.Map;

public class Command_RolloverButtonTag extends HtmlBaseTag
{
  private String image;
  private String rolloverImage;
  private String action;
  private String value;
  private String immediate;
  private String actionListener;
  private String type;

  public Command_RolloverButtonTag()
  {
    super();
  }

  // HtmlBaseTag methods

  public String getComponentType()
  {
    return HtmlCommandButton.COMPONENT_TYPE;
  }

  public String getRendererType()
  {
    return "jia.RolloverButton";
  }

protected void setProperties(UIComponent component)
{
  super.setProperties(component);

  UICommand command = (UICommand)component;
  Application app = getFacesContext().getApplication();
  Map attributes = command.getAttributes();
  if (value != null)
  {
    if (isValueReference(value))
    {
      command.setValueBinding("value",
                              app.createValueBinding(value));
    }
    else
    {
      command.setValue(value);
    }
  }
  if (action != null)  /**/
  {
    MethodBinding actionBinding = null;
    if (isValueReference(action))
    {
      actionBinding = app.createMethodBinding(action, null);
    }
    else
    {
      actionBinding = new ConstantMethodBinding(action);
    }
    command.setAction(actionBinding);
  }
  if (actionListener != null)
  {
    MethodBinding actionListenerBinding =
            app.createMethodBinding(actionListener, new Class[] { ActionEvent.class });
    command.setActionListener(actionListenerBinding);
  }
  if (immediate != null)
  {
    if (isValueReference(immediate))
    {
      command.setValueBinding("immediate",
                              app.createValueBinding(immediate));
    }
    else
    {
      command.setImmediate(Boolean.valueOf(immediate).booleanValue());
    }
  }
  Util.addAttribute(app, component, "image", image);
  Util.addAttribute(app, component, "rolloverImage", rolloverImage);
  Util.addAttribute(app, component, "type", type);
}

public void release()
{
  super.release();

  image = null;
  rolloverImage = null;
  action = null;
  value = null;
  immediate = null;
  actionListener = null;
  action = null;
  type = null;
}

  // Properties

  public String getImage()
  {
    return image;
  }

  public void setImage(String image)
  {
    this.image = image;
  }
  public String getRolloverImage()
  {
    return rolloverImage;
  }

  public void setRolloverImage(String rolloverImage)
  {
    this.rolloverImage = rolloverImage;
  }

  public String getImmediate()
  {
    return immediate;
  }

  public void setImmediate(String immediate)
  {
    this.immediate = immediate;
  }

  public String getAction()
  {
    return action;
  }

  public void setAction(String action)
  {
    this.action = action;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public String getActionListener()
  {
    return actionListener;
  }

  public void setActionListener(String actionListenerRef)
  {
    this.actionListener = actionListenerRef;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }
}
