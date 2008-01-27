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

package org.jia.examples;

import java.io.*;
import javax.faces.event.ActionEvent;
import javax.faces.context.*;
import java.util.*;
import javax.faces.component.*;
import javax.faces.application.*;
import javax.faces.model.*;
import org.jia.converters.*;
import javax.faces.component.html.*;
import javax.faces.event.*;
import javax.faces.lifecycle.*;
import javax.faces.FactoryFinder;
import javax.faces.lifecycle.Lifecycle;
import org.jia.examples.UserBean;
import javax.faces.component.UICommand;
import javax.faces.validator.*;

public class TestForm implements Serializable
{
  private int counter;
  private String message = "What time is love?";
  private String loggingMessage;
  private boolean toggleFlag = false;
  private int number;
  private String string;
  private javax.faces.component.UIData testTable;
  private double doubleValue = 0.056;
  private javax.faces.model.SelectItem[] colors;
  private transient UserConverter userConverter = new UserConverter();
  private UIPanel converterPanel = null;
  private UserBean user;
  private javax.faces.component.UIViewRoot view;
  private javax.faces.component.html.HtmlPanelGrid changePanel;
  private javax.faces.component.html.HtmlOutputText outputText;
  private javax.faces.component.html.HtmlDataTable dataTable;
  private javax.faces.application.Application application;
  private boolean addMessages = true;

  // Actions

  public TestForm()
  {
    colors = new SelectItem[5];
    colors[0] = new SelectItem("0", "blue");
    colors[1] = new SelectItem("1", "red");
    colors[2] = new SelectItem("2", "green");
    colors[3] = new SelectItem("3", "black");
    colors[4] = new SelectItem("4", "purple");

UIOutput output = (UIOutput)getApplication().createComponent("javax.faces.Output");

    LifecycleFactory lifecycleFactory = (LifecycleFactory)FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
    Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
    lifecycle.addPhaseListener(
      new PhaseListener()
      {
        public void beforePhase(PhaseEvent event)
        {
//          refreshList();
        }

        public void afterPhase(PhaseEvent event)
        {
        }

        public PhaseId getPhaseId()
        {
          return PhaseId.RENDER_RESPONSE;
        }
      });

  }

  public UICommand getTestCommand()
  {
    UICommand command = (UICommand)getApplication().
                          createComponent(HtmlCommandButton.COMPONENT_TYPE);
    command.addActionListener(new ActionListener()
    {
      public void processAction(ActionEvent event) throws AbortProcessingException
      {
        FacesContext.getCurrentInstance().getExternalContext().
            log("User fired action for command " + event.getComponent());
      }
    });
    command.setValueBinding("value",
        getApplication().createValueBinding("#{myBean.userName}"));
    command.setValueBinding("title",
        getApplication().createValueBinding("#{myBundle.userNameTitle}"));

    LongRangeValidator myValidator = (LongRangeValidator)
        application.createValidator(LongRangeValidator.VALIDATOR_ID);
    myValidator.setMinimum(0);
    myValidator.setMaximum(555);
    UIInput input = new UIInput();
    input.addValidator(getApplication().createValidator(LongRangeValidator.VALIDATOR_ID));
    return null;
  }

  public String toggleNextOrPrevious()
  {
    toggleFlag = !toggleFlag;
    if (toggleFlag)
    {
      return "next";
    }
    else
    {
      return "previous";
    }
  }

  public String incrementCounter()
  {
    counter++;
    return "success";
  }

  public String loadInbox()
  {
    return "inbox";
  }

  public String logout()
  {
    return "logout";
  }

  public void incrementCounter(ActionEvent e)
  {
    counter++;
  }

  // Properties

  public int getCounter()
  {
    return counter;
  }

  public void setCounter(int counter)
  {
    this.counter = counter;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public String getLoggingMessage()
  {
    return loggingMessage;
  }

  public void setLoggingMessage(String loggingMessage)
  {
    this.loggingMessage = loggingMessage;
  }

  public boolean getTrueProperty()
  {
    return true;
  }

  public boolean getFalseProperty()
  {
    return false;
  }

  public int getNumber()
  {
    return number;
  }

  public void setNumber(int number)
  {
    this.number = number;
  }

  public String getString()
  {
    return string;
  }

  public void setString(String string)
  {
    this.string = string;
  }

  public void setConverterPanel(UIPanel panel)
  {
    this.converterPanel = panel;
  }

  public UIPanel getConverterPanel()
  {
    if (converterPanel == null)
    {
      converterPanel = (UIPanel)FacesContext.getCurrentInstance().getApplication().createComponent(HtmlPanelGrid.COMPONENT_TYPE);
      UIOutput output = (UIOutput)FacesContext.getCurrentInstance().getApplication().createComponent(HtmlOutputText.COMPONENT_TYPE);
      output.setValue("foo2");
      converterPanel.getChildren().add(output);
    }
    return converterPanel;
  }

  // Event handlers

  public void toggleLocale(ActionEvent e)
  {
    FacesContext context = FacesContext.getCurrentInstance();
    UIViewRoot view = context.getViewRoot();
    if (view.getLocale().equals(Locale.ENGLISH))
    {
      view.setLocale(new Locale("es", "ES"));
    }
    else
    {
      view.setLocale(Locale.ENGLISH);
    }
  }

  public void validateEmail(FacesContext context, UIComponent component,
                            Object value)
  {
    EditableValueHolder input = (EditableValueHolder)component;
    context.getExternalContext().log("inside validateEmail");
    if (((String)value).indexOf("@") > -1)
    {
      input.setValid(true);
    }
    else
    {
      context.addMessage(component.getClientId(context),
                         new FacesMessage("Invalid e-mail address", null));
      input.setValid(false);
    }
  }

  public void testCommand(ActionEvent e)
  {
    Map project = (Map)testTable.getRowData();
    message = (String)project.get("name");
  }

  public javax.faces.component.UIData getTestTable()
  {
    return testTable;
  }
  public void setTestTable(javax.faces.component.UIData testTable)
  {
    this.testTable = testTable;
  }
  public double getDoubleValue()
  {
    return doubleValue;
  }
  public void setDoubleValue(double doubleValue)
  {
    this.doubleValue = doubleValue;
  }
  public javax.faces.model.SelectItem[] getColors()
  {
    return colors;
  }
  public void setColors(javax.faces.model.SelectItem[] colors)
  {
    this.colors = colors;
  }
  public org.jia.converters.UserConverter getUserConverter() {
    return userConverter;
  }
  public void setUserConverter(org.jia.converters.UserConverter userConverter) {
    this.userConverter = userConverter;
  }

  protected void createComponent(Application app, List children,
                                     String componentType, String converterType)
  {
/*
    HtmlOutputText outputText = application.createComponent(
                              HtmlOutputText.COMPONENT_TYPE);

    UIComponent component = app.createComponent(componentType);
    if ((component instanceof UIOutput) && !(component instanceof UISelectBoolean))
    {
      if (componentType.indexOf("Select") > -1)
      {
        return;
/*        for (int i = 0; i < 50; i++)
        {
          UISelectItem item = new UISelectItem();
          item.setItemLabel(String.valueOf(i));
          item.setItemValue(String.valueOf(i));
          component.getChildren().add(item);
        }*/
      }
  /*    UIOutput label = new UIOutput();
      label.setValue(componentType);
      children.add(label);


      ((UIOutput)component).setValue(new Date());
          //new User("foo", "foo", RoleType.BUSINESS_ANALYST));
      ((UIOutput)component).setConverter(app.createConverter(converterType));
      children.add(component);
    }
  }

  public void testConverters(ActionEvent e)
  {
    List children = converterPanel.getChildren();
    children.clear();
    Application app = FacesContext.getCurrentInstance().getApplication();
    Iterator componentTypes = app.getComponentTypes();
    while (componentTypes.hasNext())
    {
      createComponent(app, children, (String)componentTypes.next(), "DateTime");
    }
  }
*/
  public void addMessages(ActionEvent e)
  {
    FacesContext context = FacesContext.getCurrentInstance();
    if (addMessages)
    {
      context.addMessage(null, new FacesMessage("Your order has been processed successfully.", ""));
      context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                "Free shipping limit exceeded.",
                                                "We had to charge your card an extra $25 for shipping."));
    }
  }
  public UserBean getUser()
  {
    return user;
  }
  public void setUser(UserBean user)
  {
    this.user = user;
  }
  public javax.faces.component.UIViewRoot getView()
  {
    return view;
  }
  public void setView(javax.faces.component.UIViewRoot view)
  {
    this.view = view;
  }

  public void sendMessage(ActionEvent e)
  {
    FacesContext context  = FacesContext.getCurrentInstance();
    UIViewRoot view = context.getViewRoot();
    HtmlOutputText output =
        (HtmlOutputText)view.findComponent("messageForm:outputMessage");
    output.setStyle("color:blue");
    output.setValue("Who's the Mann?");
  }

  public void processValueChanged(ValueChangeEvent event)
//                                         throws AbortProcessingException
  {
/*    UIInput sender = (UIInput)event.getComponent();
    sender.setReadonly(true);
    changePanel.setRendered(true);*/
  }

  public javax.faces.component.html.HtmlPanelGrid getChangePanel()
  {
    return changePanel;
  }
  public void setChangePanel(javax.faces.component.html.HtmlPanelGrid changePanel)
  {
    this.changePanel = changePanel;
  }
  public javax.faces.component.html.HtmlOutputText getOutputText()
  {
    return outputText;
  }
  public void setOutputText(javax.faces.component.html.HtmlOutputText outputText)
  {
    this.outputText = outputText;
  }
  public javax.faces.component.html.HtmlDataTable getDataTable()
  {
    if (dataTable == null)
    {
      String numberStrings[] = new String[] {"one", "two", "three"};
      FacesContext facesContext = FacesContext.getCurrentInstance();
      dataTable = (HtmlDataTable)facesContext.getApplication().createComponent(
                                 HtmlDataTable.COMPONENT_TYPE);
      DataModel myDataModel = new ArrayDataModel(numberStrings);
      myDataModel.addDataModelListener(new DataModelListener()
      {

        public void rowSelected(DataModelEvent e)
        {
          FacesContext.getCurrentInstance().getExternalContext().
                       log("phase: " + e.getRowIndex() + "; row seleted:" + e.getRowIndex());
        }
      });
      dataTable.setValue(myDataModel);
    }
    return dataTable;
  }

  public void setDataTable(javax.faces.component.html.HtmlDataTable dataTable)
  {
    this.dataTable = dataTable;
  }
  public javax.faces.application.Application getApplication()
  {
    return FacesContext.getCurrentInstance().getApplication();
  }
  public void setApplication(javax.faces.application.Application application)
  {
    this.application = application;
  }
  public boolean isAddMessages()
  {
    return addMessages;
  }
  public void setAddMessages(boolean addMessages)
  {
    this.addMessages = addMessages;
  }

  public void deleteUser(ActionEvent e)
  {
    UserBean user = (UserBean)testTable.getRowData();
    ArrayList userList = (ArrayList)testTable.getValue();
    userList.remove(user);
  }

  public String testImmediate()
  {
    FacesContext.getCurrentInstance().getExternalContext().log("Input value = " + message);
    return null;
  }

}
