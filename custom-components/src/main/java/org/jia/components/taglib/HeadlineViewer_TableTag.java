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

import org.jia.components.UIHeadlineViewer;
import org.jia.util.Util;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class HeadlineViewer_TableTag extends HtmlTableBaseTag
{
  private String url;
  private String styleClass;
  private String channelTitleClass;
  private String itemTitleClass;
  private String itemHeaderClass;
  private String itemDescriptionClass;
  private String itemClasses;
  private String showChannelTitle;
  private String showItemCreator;
  private String showItemDescription;
  private String showItemPublishedDate;
  private String showItemReceivedDate;
  private String showItemTitle;
  private String rows;

  public HeadlineViewer_TableTag()
  {
    super();
  }

  // UIComponentTag methods

  public String getComponentType()
  {
    return UIHeadlineViewer.COMPONENT_TYPE;
  }

  public String getRendererType()
  {
    return "javax.faces.Table";
  }

  protected void setProperties(UIComponent component)
  {
    super.setProperties(component);

    UIHeadlineViewer viewer = (UIHeadlineViewer)component;
    Application app = getFacesContext().getApplication(); // changed from context.

    if (url != null)
    {
      if (isValueReference(url))
      {
        viewer.setURL((String)app.createValueBinding(url).getValue(
                              FacesContext.getCurrentInstance()));
      }
      else
      {
        viewer.setURL(url);
      }
    }
    if (showChannelTitle != null)
    {
      if (isValueReference(showChannelTitle))
      {
        viewer.setValueBinding("showChannelTitle",
                               app.createValueBinding(showChannelTitle));
      }
      else
      {
        viewer.setShowChannelTitle(Boolean.valueOf(showChannelTitle).booleanValue());
      }
    }
    if (showItemTitle != null)
    {
      if (isValueReference(showItemTitle))
      {
        viewer.setValueBinding("showItemTitle",
                               app.createValueBinding(showItemTitle));
      }
      else
      {
        viewer.setShowItemTitle(Boolean.valueOf(showItemTitle).booleanValue());
      }
    }
    if (showItemCreator != null)
    {
      if (isValueReference(showItemCreator))
      {
        viewer.setValueBinding("showItemCreator",
                               app.createValueBinding(showItemCreator));
      }
      else
      {
        viewer.setShowItemCreator(Boolean.valueOf(showItemCreator).booleanValue());
      }
    }
    if (showItemPublishedDate != null)
    {
      if (isValueReference(showItemPublishedDate))
      {
        viewer.setValueBinding("showItemPublishedDate",
                               app.createValueBinding(showItemPublishedDate));
      }
      else
      {
        viewer.setShowItemPublishedDate(
                Boolean.valueOf(showItemPublishedDate).booleanValue());
      }
    }
    if (showItemReceivedDate != null)
    {
      if (isValueReference(showItemReceivedDate))
      {
        viewer.setValueBinding("showItemReceivedDate",
                               app.createValueBinding(showItemReceivedDate));
      }
      else
      {
        viewer.setShowItemReceivedDate(
                Boolean.valueOf(showItemReceivedDate).booleanValue());
      }
    }
    if (showItemDescription  != null)
    {
      if (isValueReference(showItemDescription))
      {
        viewer.setValueBinding("showItemDescription",
                               app.createValueBinding(showItemDescription));
      }
      else
      {
        viewer.setShowItemDescription(
                Boolean.valueOf(showItemDescription).booleanValue());
      }
    }
    if (rows != null)
    {
      if (isValueReference(rows))
      {
        viewer.setValueBinding("rows",
                               app.createValueBinding(rows));
      }
      else
      {
        viewer.setRows(Integer.parseInt(rows));
      }
    }

    Util.addAttribute(app, viewer, "styleClass", styleClass);
    Util.addAttribute(app, viewer, "headerClass", channelTitleClass);
    Util.addAttribute(app, viewer, "rowClasses", itemClasses);

    UIPanel panel = viewer.getInnerPanel();
    panel.getAttributes().put("columns", new Integer(1));
    panel.getAttributes().put("cellpadding", new Integer(2));
    panel.getAttributes().put("cellspacing", new Integer(0));
    Util.addAttribute(app, panel, "headerClass", itemTitleClass);

    Util.addAttribute(app, viewer.getItemCreator(),  "styleClass",
                      itemHeaderClass);
    Util.addAttribute(app, viewer.getItemPublishedDate(), "styleClass",
                      itemHeaderClass);
    Util.addAttribute(app, viewer.getItemReceivedDate(), "styleClass",
                      itemHeaderClass);
    Util.addAttribute(app,  viewer.getItemDescription(), "styleClass",
                      itemDescriptionClass);
  }

  public void release()
  {
    url = null;
    styleClass = null;
    channelTitleClass = null;
    itemTitleClass = null;
    itemHeaderClass = null;
    itemDescriptionClass = null;
    showChannelTitle = null;
    showItemCreator = null;
    showItemReceivedDate = null;
    showItemTitle = null;
    showItemDescription = null;
    showItemPublishedDate = null;
    rows = null;
    itemClasses = null;
  }

  // Component properties

  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getShowChannelTitle()
  {
    return showChannelTitle;
  }

  public void setShowChannelTitle(String showChannelTitle)
  {
    this.showChannelTitle = showChannelTitle;
  }

  public String getShowItemCreator()
  {
    return showItemCreator;
  }

  public void setShowItemCreator(String showItemCreator)
  {
    this.showItemCreator = showItemCreator;
  }

  public String getShowItemReceivedDate()
  {
    return showItemReceivedDate;
  }

  public void setShowItemReceivedDate(String showItemReceivedDate)
  {
    this.showItemReceivedDate = showItemReceivedDate;
  }

  public String getShowItemPublishedDate()
  {
    return showItemPublishedDate;
  }

  public void setShowItemPublishedDate(String showItemPublishedDate)
  {
    this.showItemPublishedDate = showItemPublishedDate;
  }

  public String getShowItemTitle()
  {
    return showItemTitle;
  }

  public void setShowItemTitle(String showItemTitle)
  {
    this.showItemTitle = showItemTitle;
  }

  public String getShowItemDescription()
  {
    return showItemDescription;
  }

  public void setShowItemDescription(String showItemDescription)
  {
    this.showItemDescription = showItemDescription;
  }

  public String getRows()
  {
    return rows;
  }

  public void setRows(String rows)
  {
    this.rows = rows;
  }

  // Renderer attributes

  public String getStyleClass()
  {
    return styleClass;
  }

  public void setStyleClass(String styleClass)
  {
    this.styleClass = styleClass;
  }

  public String getChannelTitleClass()
  {
    return channelTitleClass;
  }

  public void setChannelTitleClass(String channelTitleClass)
  {
    this.channelTitleClass = channelTitleClass;
  }

  public String getItemTitleClass()
  {
    return itemTitleClass;
  }

  public void setItemTitleClass(String itemTitleClass)
  {
    this.itemTitleClass = itemTitleClass;
  }

  public String getItemHeaderClass()
  {
    return itemHeaderClass;
  }

  public void setItemHeaderClass(String itemHeaderClass)
  {
    this.itemHeaderClass = itemHeaderClass;
  }

  public String getItemDescriptionClass()
  {
    return itemDescriptionClass;
  }

  public void setItemDescriptionClass(String itemDescriptionClass)
  {
    this.itemDescriptionClass = itemDescriptionClass;
  }

  public String getItemClasses()
  {
    return itemClasses;
  }

  public void setItemClasses(String itemClasses)
  {
    this.itemClasses = itemClasses;
  }
}
