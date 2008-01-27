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

import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.render.Renderer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class ToolbarRenderer extends Renderer
{
  public ToolbarRenderer()
  {
    super();
  }

  public void encodeBegin(FacesContext context, UIComponent component)
              throws java.io.IOException
  {
    if (component.isRendered())
    {
      UINavigator navigator = (UINavigator)component;
      Map attributes = navigator.getAttributes();
      ResponseWriter writer = context.getResponseWriter();

      // Hidden input field for postback
      writer.startElement("input", navigator);
      writer.writeAttribute("type", "hidden", null);
      writer.writeAttribute("name", navigator.getClientId(context), "clientId");
      writer.endElement("input");

      writer.startElement("table", navigator);

      if (attributes.get("cellpadding") == null)
      {
        attributes.put("cellpadding", "4");
      }
      Util.writePassthroughAttributes(attributes, writer);
    }
  }

  public void encodeChildren(FacesContext context, UIComponent component)
              throws java.io.IOException
  {
    if (component.isRendered())
    {
      UINavigator navigator = (UINavigator)component;
      Map attributes = navigator.getAttributes();
      UIComponent header = navigator.getHeader();
      boolean vertical = isVertical(attributes);
      if (header != null)
      {
        ResponseWriter writer = context.getResponseWriter();
        if (vertical)
        {
          writer.startElement("thead", header);
          writer.startElement("tr", header);
          writer.startElement("th", header);
        }
        else
        {
          writer.startElement("tbody", navigator);
          writer.startElement("tr", header);
          writer.startElement("td", header);
        }
        if (attributes.get("headerClass") != null)
        {
          writer.writeAttribute("class", attributes.get("headerClass"),
                                "headerClass");
        }
        header.encodeBegin(context);
        header.encodeChildren(context);
        header.encodeEnd(context);
        if (vertical)
        {
          writer.endElement("th");
          writer.endElement("tr");
          writer.endElement("thead");
        }
        else
        {
          writer.endElement("td");
        }
      }
    }
  }

  public void encodeEnd(FacesContext context, UIComponent component)
              throws java.io.IOException
  {
    if (component.isRendered())
    {
      ResponseWriter writer = context.getResponseWriter();
      UINavigator navigator = (UINavigator)component;
      Map attributes = navigator.getAttributes();
      boolean vertical = isVertical(attributes);
      UIForm parentForm = getParentForm(navigator);
      if (parentForm == null)
      {
        throw new NullPointerException("No parent UIForm found.");
      }

      NavigatorItemList items = navigator.getItems();
      if (items != null && items.size() > 0)
      {
        if (vertical)
        {
          writer.startElement("tbody", navigator);
        }
        else
        if (navigator.getHeader() == null)
        {
          writer.startElement("tbody", navigator);
          writer.startElement("tr", navigator);
        }
        Iterator itemIterator = items.iterator();
        while (itemIterator.hasNext())
        {
          encodeItem((NavigatorItem)itemIterator.next(), items, context, writer,
                     navigator, attributes, vertical, parentForm);
        }
        if (!vertical)
        {
          writer.endElement("tr");
        }
        writer.endElement("tbody");
      }
      writer.endElement("table");
    }
  }

  protected void encodeItem(NavigatorItem item, NavigatorItemList items,
                          FacesContext context, ResponseWriter writer,
                          UINavigator navigator, Map attributes,
                          boolean vertical, UIForm parentForm) throws IOException
  {
    if (vertical)
    {
      writer.startElement("tr", navigator);
    }
    writer.startElement("td", navigator);
    writeItemClass(writer, item.equals(navigator.getItems().getSelectedItem()),
                   attributes);

    if (!item.isDisabled())
    {
      writer.startElement("a", navigator);
      if (item.isDirect() && item.getLink() != null)
      {
        writer.writeAttribute("href", context.getExternalContext().
                              encodeResourceURL(item.getLink()),
                              null);
      }
      else
      {
        writer.writeAttribute("href", "#", null);
        String clientId = navigator.getClientId(context);
        writer.writeAttribute("onclick",
                              "document.forms['" +
                              parentForm.getClientId(context) + "']['" +
                              clientId + "'].value='" + items.indexOf(item) +
                              "'; document.forms['" +
                              parentForm.getClientId(context) +
                              "'].submit();false",
                              null);

      }
    }
    if (item.getIcon() != null)
    {
      encodeIcon(context, writer, attributes, item.getIcon());
    }
    if (item.getLabel() != null)
    {
      writer.writeText(item.getLabel(), null);
    }
    if (!item.isDisabled())
    {
      writer.endElement("a");
    }

    writer.endElement("td");
    if (vertical)
    {
      writer.endElement("tr");
    }
  }

  protected void encodeIcon(FacesContext context, ResponseWriter writer,
                          Map attributes, String iconUrl) throws IOException
  {
    if (iconUrl.startsWith("/"))
    {
      iconUrl = context.getExternalContext().getRequestContextPath() +
                iconUrl;
    }
    writer.startElement("img", null);
    writer.writeAttribute("border", "0", null);
    writer.writeAttribute("src",
                          context.getExternalContext().encodeResourceURL(iconUrl),
                          null);

    if (attributes.get("iconClass") != null)
    {
      writer.writeAttribute("class", attributes.get("iconClass"), null);
    }
    else
    {
      writer.writeAttribute("style", "margin-left: 0px; margin-right: 10px; " +
                            "border: 0px; vertical-align: middle;", null);
    }
    writer.endElement("img");
  }

  protected void writeItemClass(ResponseWriter writer, boolean selected,
                              Map attributes)
                 throws java.io.IOException
  {
    if (selected)
    {
      writer.writeAttribute("class", attributes.get("selectedItemClass"), null);
    }
    else
    {
      writer.writeAttribute("class", attributes.get("itemClass"), null);
    }
  }

  protected boolean isVertical(Map attributes)
  {
    String layout = (String)attributes.get("layout");
    if (layout == null || layout.equalsIgnoreCase("VERTICAL"))
    {
      return true;
    }
    else
    if (layout.equalsIgnoreCase("HORIZONTAL"))
    {
      return false;
    }
    else
    {
      throw new IllegalArgumentException("Attribute \"layout\" must be either " +
                   "VERTICAL or HORIZONTAL (currently set to " + layout + ")");
    }
  }

  public void decode(FacesContext context, UIComponent component)
  {
    if (Util.canModifyValue(component))
    {
      UINavigator navigator = (UINavigator)component;
      Map parameterMap = context.getExternalContext().getRequestParameterMap();
      String selectedItem =
              (String)parameterMap.get((String)navigator.getClientId(context));
      if (selectedItem != null && selectedItem.length() > 0)
      {
        NavigatorItemList items = navigator.getItems();
        if (items != null)
        {
          navigator.setSubmittedItem((NavigatorItem)items.get(
                                     Integer.parseInt(selectedItem)));
          navigator.queueEvent(new ActionEvent(navigator));
        }
      }
    }
  }

  protected UIForm getParentForm(UIComponent component)
  {
    UIComponent parent = component.getParent();
    while (parent != null)
    {
      if (parent instanceof UIForm)
      {
         break;
      }
      parent = parent.getParent();
    }
    return (UIForm)parent;
  }
}
