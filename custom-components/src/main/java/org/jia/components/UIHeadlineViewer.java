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

import org.jia.components.model.ChannelDataModel;
import org.jia.util.Util;

import javax.faces.application.Application;
import javax.faces.component.*;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.el.ValueBinding;

import de.nava.informa.core.ChannelIF;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.parsers.RSSParser;
import de.nava.informa.utils.FeedManager;

public class UIHeadlineViewer extends UIData
{
  public final static String COMPONENT_TYPE = "jia.HeadlineViewer";

  private UIPanel innerPanel;
  private UIOutput itemDescription;
  private UIOutput itemPublishedDate;
  private UIOutput itemReceivedDate;
  private UIOutput itemTitle;
  private UIOutput channelTitle;
  private UIOutput itemCreator;

  private String url;
  private Boolean showChannelTitle;
  private Boolean showItemCreator;
  private Boolean showItemDescription;
  private Boolean showItemPublishedDate;
  private Boolean showItemReceivedDate;
  private Boolean showItemTitle;

  public UIHeadlineViewer()
  {
    super();
    this.setVar("item");
    addChildrenAndFacets();
  }

  // Protected methods

  protected void addChildrenAndFacets()
  {
    Application app = getFacesContext().getApplication();

    setChannelTitle(createChannelTitle(app));

    UIColumn column = (UIColumn)app.createComponent(UIColumn.COMPONENT_TYPE);
    column.setId("column1");
    getChildren().add(column);

    setInnerPanel(createInnerPanel(app));
    setItemTitle(createItemTitle(app));
    setItemCreator(createItemCreator(app));
    setItemPublishedDate(createItemPublishedDate(app));
    setItemReceivedDate(createItemReceivedDate(app));
    setItemDescription(createItemDescription(app));
  }

  protected UIOutput createChannelTitle(Application app)
  {
    UIOutput channelTitle = (UIOutput)app.createComponent(UIOutput.COMPONENT_TYPE);
    channelTitle.setId("channelTitle");
    return channelTitle;
  }

  protected UIPanel createInnerPanel(Application app)
  {
    UIPanel innerPanel = (UIPanel)app.createComponent(UIPanel.COMPONENT_TYPE);
    innerPanel.setId("innerPanel");
    innerPanel.setRendererType("javax.faces.Grid");
    return innerPanel;
  }

  protected UIOutput createItemTitle(Application app)
  {
    UIOutput itemTitle = (UIOutput)app.createComponent(UIOutput.COMPONENT_TYPE);
    itemTitle.setId("itemTitle");
    itemTitle.setRendererType("javax.faces.Link");
    itemTitle.setValueBinding("value",
                              app.createValueBinding("#{item.link}"));

    UIOutput itemTitleText = (UIOutput)app.createComponent(UIOutput.COMPONENT_TYPE);
    itemTitleText.setValueBinding("value",
                                  app.createValueBinding("#{item.title}"));
    itemTitle.getChildren().add(itemTitleText);

    return itemTitle;
  }

  protected UIOutput createItemCreator(Application app)
  {
    UIOutput itemCreator = (UIOutput)app.createComponent(UIOutput.COMPONENT_TYPE);
    itemCreator.setId("itemCreator");
    itemCreator.setValueBinding("value",
                                app.createValueBinding("#{item.creator}"));
    return itemCreator;
  }

  protected UIOutput createItemPublishedDate(Application app)
  {
    UIOutput itemPublishedDate = (UIOutput)app.createComponent(UIOutput.COMPONENT_TYPE);
    itemPublishedDate.setId("itemPublishedDate");
    itemPublishedDate.setValueBinding("value",
                                      app.createValueBinding("#{item.date}"));

    DateTimeConverter converter = new DateTimeConverter();
    converter.setPattern("'Published on ' EEE MMM dd yyyy 'at' hh:mm a.");
    itemPublishedDate.setConverter(converter);

    return itemPublishedDate;
  }

  protected UIOutput createItemReceivedDate(Application app)
  {
    UIOutput itemReceivedDate = (UIOutput)app.createComponent(UIOutput.COMPONENT_TYPE);
    itemReceivedDate.setId("itemReceivedDate");
    itemReceivedDate.setValueBinding("value",
                                     app.createValueBinding("#{item.found}"));
    DateTimeConverter converter = new DateTimeConverter();
    converter.setPattern("'Received on ' EEE MMM dd yyyy 'at' hh:mm a.");
    itemReceivedDate.setConverter(converter);
    return itemReceivedDate;
  }

  protected UIOutput createItemDescription(Application app)
  {
    UIOutput itemDescription = (UIOutput)app.createComponent(UIOutput.COMPONENT_TYPE);
    itemDescription.setId("itemDescription");
    itemDescription.setValueBinding("value",
                                    app.createValueBinding(
                                         "#{item.description}"));
    return itemDescription;
  }

  // Embedded component properties

  public UIOutput getChannelTitle()
  {
    return channelTitle;
  }

  protected void setChannelTitle(UIOutput channelTitle)
  {
    this.channelTitle = channelTitle;
    getFacets().put("header", channelTitle);
  }

  public UIPanel getInnerPanel()
  {
    return innerPanel;
  }

  protected void setInnerPanel(UIPanel innerPanel)
  {
    this.innerPanel = innerPanel;
    findComponent("column1").getChildren().add(innerPanel);
  }

  public UIOutput getItemTitle()
  {
    return itemTitle;
  }

  protected void setItemTitle(UIOutput itemTitle)
  {
    this.itemTitle = itemTitle;
    innerPanel.getFacets().put("header", itemTitle);
  }

  public UIOutput getItemCreator()
  {
    return itemCreator;
  }

  protected void setItemCreator(UIOutput itemCreator)
  {
    this.itemCreator = itemCreator;
    innerPanel.getChildren().add(itemCreator);
  }

  public UIOutput getItemPublishedDate()
  {
    return itemPublishedDate;
  }

  protected void setItemPublishedDate(UIOutput itemPublishedDate)
  {
    this.itemPublishedDate = itemPublishedDate;
    innerPanel.getChildren().add(itemPublishedDate);
  }

  public UIOutput getItemReceivedDate()
  {
    return itemReceivedDate;
  }

  protected void setItemReceivedDate(UIOutput itemReceivedDate)
  {
    this.itemReceivedDate = itemReceivedDate;
    innerPanel.getChildren().add(itemReceivedDate);
  }

  public UIOutput getItemDescription()
  {
    return itemDescription;
  }

  protected void setItemDescription(UIOutput itemDescription)
  {
    this.itemDescription = itemDescription;
    innerPanel.getChildren().add(itemDescription);
  }

  // UIComponent methods

  public void encodeBegin(FacesContext context)
          throws java.io.IOException
  {
    ChannelDataModel data = (ChannelDataModel)getValue();
    if (data != null)
    {
      ChannelIF channel = (ChannelIF)data.getWrappedData();
      getChannelTitle().setValue(channel.getTitle());
    }
    channelTitle.setRendered(getShowChannelTitle());
    itemTitle.setRendered(getShowItemTitle());
    itemCreator.setRendered(getShowItemCreator());
    itemDescription.setRendered(getShowItemDescription());
    itemPublishedDate.setRendered(getShowItemPublishedDate());
    itemReceivedDate.setRendered(getShowItemReceivedDate());

    super.encodeBegin(context);
  }

  // Other properties

  public String getURL()
  {
    return url;
  }

  public void setURL(String url)
  {
    if (url != null)
    {
      ValueBinding binding = getFacesContext().getApplication().
              createValueBinding("#{UIHeadlineViewerFeedManager}");
      FeedManager manager = (FeedManager)binding.
              getValue(getFacesContext());

      ChannelIF channel = null;
      try
      {
        if (manager != null)
        {
          channel = manager.addFeed(url).getChannel(); // If feed already exists, it will be returned
        }
        else
        {
          channel = RSSParser.parse(new ChannelBuilder(), url);
        }
      }
      catch (Exception e)
      {
        getFacesContext().getExternalContext().
                log("Error creating channel from URL " + url, e);
        return;
      }
      this.url = url;
      setValue(new ChannelDataModel(channel));
    }
  }

  public boolean getShowChannelTitle()
  {
    return Util.getBooleanProperty(this, showChannelTitle, "showChannelTitle", true);
  }

  public void setShowChannelTitle(boolean showChannelTitle)
  {
    this.showChannelTitle = new Boolean(showChannelTitle);
  }

  public boolean getShowItemTitle()
  {
    return Util.getBooleanProperty(this, showItemTitle, "showItemTitle", true);
  }

  public void setShowItemTitle(boolean showItemTitle)
  {
    this.showItemTitle = new Boolean(showItemTitle);
  }

  public boolean getShowItemCreator()
  {
    return Util.getBooleanProperty(this, showItemCreator, "showItemCreator",
                                   false);
  }

  public void setShowItemCreator(boolean showItemCreator)
  {
    this.showItemCreator = new Boolean(showItemCreator);
  }

  public boolean getShowItemPublishedDate()
  {
    return Util.getBooleanProperty(this, showItemPublishedDate,
                                   "showItemPublishedDate", false);
  }

  public void setShowItemPublishedDate(boolean showItemPublishedDate)
  {
    this.showItemPublishedDate = new Boolean(showItemPublishedDate);
  }

  public boolean getShowItemReceivedDate()
  {
    return Util.getBooleanProperty(this, showItemReceivedDate,
                                   "showItemReceivedDate", false);
  }

  public void setShowItemReceivedDate(boolean showItemReceivedDate)
  {
    this.showItemReceivedDate = new Boolean(showItemReceivedDate);
  }

  public boolean getShowItemDescription()
  {
    return Util.getBooleanProperty(this, showItemDescription,
                                   "showItemDescription", false);
  }

  public void setShowItemDescription(boolean showItemDescription)
  {
    this.showItemDescription = new Boolean(showItemDescription);
  }

  // StateHolder methods

  public Object saveSate(FacesContext context)
  {
    Object[] values = new Object[8];
    values[0] = super.saveState(context);
    values[1] = url;
    values[2] = showChannelTitle;
    values[3] = showItemTitle;
    values[4] = showItemCreator;
    values[5] = showItemPublishedDate;
    values[6] = showItemReceivedDate;
    values[7] = showItemDescription;

    return values;
  }

  public void restoreSate(FacesContext context, Object state)
  {
    Object[] values = (Object[])state;
    super.restoreState(context, values[0]);
    url = (String)values[1];
    showChannelTitle = (Boolean)values[2];
    showItemTitle = (Boolean)values[3];
    showItemCreator = (Boolean)values[4];
    showItemPublishedDate = (Boolean)values[5];
    showItemReceivedDate = (Boolean)values[6];
    showItemDescription = (Boolean)values[7];
  }
}
