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

import org.jia.components.model.NavigatorItem;
import org.jia.components.model.NavigatorItemList;
import org.jia.util.Util;

import com.sun.faces.util.ConstantMethodBinding;

import javax.faces.application.FacesMessage;
import javax.faces.component.*;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.el.MethodBinding;
import javax.faces.event.*;

import java.io.IOException;

public class UINavigator extends UIComponentBase implements ActionSource,
        ValueHolder
{
  public final static String COMPONENT_FAMILY = "jia.Navigator";
  public final static String COMPONENT_TYPE = "jia.Navigator";
  private Boolean immediate;
  private MethodBinding actionListenerBinding;
  private Object value;
  private boolean valid;
  protected NavigatorActionListener navigatorListener;
  private javax.faces.el.MethodBinding actionListener;
  private Converter converter;
  private NavigatorItem submittedItem;

  public UINavigator()
  {
    super();
    setRendererType("jia.Toolbar");
    navigatorListener = new NavigatorActionListener();
    addActionListener(navigatorListener);
  }

  public String getFamily()
  {
    return COMPONENT_FAMILY;
  }

  public NavigatorItemList getItems()
  {
    return (NavigatorItemList)getValue();
  }

  public void setItems(NavigatorItemList itemList)
  {
    setValue(itemList);
  }

  public NavigatorItem getSubmittedItem()
  {
    return submittedItem;
  }

  public void setSubmittedItem(NavigatorItem submittedItem)
  {
    this.submittedItem = submittedItem;
  }

  public UIComponent getHeader()
  {
    return (UIComponent)getFacets().get("header");
  }

  public void setHeader(UIComponent header)
  {
    getFacets().put("header", header);
  }

  public void addStandardHeader(String headerLabel)
  {
    UIComponent header = getHeader();
    if (header == null || !(header instanceof UIOutput))
    {
      UIOutput titleOutput = (UIOutput)FacesContext.getCurrentInstance().
              getApplication().createComponent(UIOutput.COMPONENT_TYPE);
      titleOutput.setValue(headerLabel);
      setHeader(titleOutput);
    }
    else
    {
      ((UIOutput)header).setValue(headerLabel);
    }
  }

  // UIComponent properties

  public boolean getRendersChildren()
  {
    return true;
  }

  // UIComponent methods (similar to UICommand)

  public void broadcast(FacesEvent event)
          throws AbortProcessingException
  {
    super.broadcast(event);

    MethodBinding binding = getActionListener();
    if (binding != null)
    {
      FacesContext context = getFacesContext();
      binding.invoke(context, new Object[]
                     {event});
    }
  }

  public void queueEvent(FacesEvent event)
  {
    if (event instanceof ActionEvent)
    {
      if (isImmediate())
      {
        event.setPhaseId(PhaseId.APPLY_REQUEST_VALUES);
      }
      else
      {
        event.setPhaseId(PhaseId.INVOKE_APPLICATION);
      }
    }
    super.queueEvent(event);
  }

  // ActionSource methods

  public MethodBinding getAction()
  {
    NavigatorItem selectedItem = getItems().getSelectedItem();
    String actionString = null;
    if (selectedItem == null ||
        (actionString = selectedItem.getAction()) == null)
    {
      return null;
    }
    if (Util.isBindingExpression(actionString))
    {
      return FacesContext.getCurrentInstance().getApplication().
              createMethodBinding(actionString, null);
    }
    else
    {
      return new ConstantMethodBinding(actionString);
    }
  }

  public void setAction(MethodBinding actionBinding)
  {
    NavigatorItem selectedItem = getItems().getSelectedItem();
    if (selectedItem == null)
    {
      throw new IllegalStateException("No item is currently selected.");
    }
    if (actionBinding == null)
    {
      selectedItem.setAction(null);
    }
    else
    {
      selectedItem.setAction(actionBinding.getExpressionString());
    }
  }

  public void setActionListener(MethodBinding actionListener)
  {
    this.actionListenerBinding = actionListenerBinding;
  }

  public MethodBinding getActionListener()
  {
    return actionListenerBinding;
  }

  public boolean isImmediate()
  {
    return Util.getBooleanProperty(this, immediate, "immediate", false);
  }

  public void setImmediate(boolean immediate)
  {
    this.immediate = new Boolean(immediate);
  }

  public void addActionListener(ActionListener listener)
  {
    addFacesListener(listener);
  }

  public void removeActionListener(ActionListener listener)
  {
    removeFacesListener(listener);
  }

  public ActionListener[] getActionListeners()
  {
    return (ActionListener[])getFacesListeners(ActionListener.class);
  }

  // ValueHolder methods

  public Object getLocalValue()
  {
    return this.value;
  }

  public Object getValue()
  {
    return Util.getObjectProperty(this, value, "value", value);
  }

  public void setValue(Object value)
  {
    this.value = value;
  }

  public boolean isValid()
  {
    return valid;
  }

  public void setValid(boolean valid)
  {
    this.valid = valid;
  }

  public void setConverter(Converter converter)
  {
    this.converter = converter;
  }

  public Converter getConverter()
  {
    return converter;
  }

  // StateHolder methods

  public Object saveState(FacesContext context)
  {
    removeActionListener(navigatorListener);

    Object[] values = new Object[5];
    values[0] = super.saveState(context);
    values[1] = value;
    values[2] = valid ? Boolean.TRUE : Boolean.FALSE;
    values[3] = immediate;
    values[4] = saveAttachedState(context, actionListener);

    return values;
  }

  public void restoreState(FacesContext context, Object state)
  {
    Object[] values = (Object[])state;
    super.restoreState(context, values[0]);
    value = values[1];
    valid = ((Boolean)values[2]).booleanValue();
    Boolean immediateState = (Boolean)values[3];
    if (immediateState != null)
    {
      setImmediate(immediateState.booleanValue());
    }
    actionListener = (MethodBinding)restoreAttachedState(context, values[4]);
  }

  private class NavigatorActionListener implements ActionListener
  {
    public void processAction(ActionEvent event)
    {
      UINavigator navigator = (UINavigator)event.getComponent();

      NavigatorItem selectedItem = navigator.getSubmittedItem();
      navigator.getItems().setSelectedItem(selectedItem);
      navigator.setSubmittedItem(null);

      if (navigator.getAction() != null)
      {
        getFacesContext().getApplication().getActionListener().
                processAction(event);
      }
      else
      {
        String link = selectedItem.getLink();
        if (link != null)
        {
          FacesContext context = getFacesContext();
          try
          {
            context.getExternalContext().redirect(link);
          }
          catch (IOException e)
          {
            String message = "Error redirecting to link '" + link + "'";
            context.addMessage(navigator.getClientId(context),
                               new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                message, null));
            context.getExternalContext().log(message, e);
          }
        }
      }
    }
  }
}
