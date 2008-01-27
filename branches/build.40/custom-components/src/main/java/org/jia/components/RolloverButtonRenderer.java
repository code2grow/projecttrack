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

import org.jia.util.Util;

import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.render.Renderer;

import java.util.Map;


public class RolloverButtonRenderer extends Renderer
{

  public RolloverButtonRenderer()
  {
  }

  public void encodeBegin(FacesContext context, UIComponent component)
              throws java.io.IOException
  {
    if (!component.isRendered())
    {
      return;
    }

    Map attributes = component.getAttributes();
    if ((attributes.get("image") == null) &&
        (attributes.get("value") == null))
    {
      throw new InvalidAttributesException(
          "Either the image or value attribute must be set.");
    }

    UICommand button = (UICommand)component;
    String clientId = button.getClientId(context);
    ResponseWriter writer = context.getResponseWriter();

    writer.startElement("input", button);
    writer.writeAttribute("name", clientId, "id");
    Util.writePassthroughAttributes(button.getAttributes(), writer);

    String imageSrc = getImageSrc(context,
                                 (String)attributes.get("image"));    /**/
    String rolloverImageSrc = getImageSrc(context,
                               (String)attributes.get("rolloverImage")); /**/
    if (imageSrc != null)
    {
      writer.writeAttribute("type", "image", "type");
      writer.writeAttribute("src", imageSrc, "image");
      if (rolloverImageSrc != null)
      {
        writer.writeAttribute("onmouseover",
                              "document.getElementsByName('" + clientId +
                              "')[0].set_image(this, '" + rolloverImageSrc +
                              "')",
                              null);
        writer.writeAttribute("onmouseout",
                              "document.getElementsByName('" + clientId +
                              "')[0].set_image(this, '" + imageSrc + "')",
                              null);
      }
    }
    else
    {
      String type = (String)attributes.get("type");
      if (type != null)
      {
        writer.writeAttribute("type", type, "type");
      }
      else
      {
        writer.writeAttribute("type", "submit", "type");
      }
      String value = (String)attributes.get("value");
      if (value != null)
      {
        writer.writeAttribute("value", value, "value");
      }
    }
    writer.endElement("input");

    if ((imageSrc != null) && (rolloverImageSrc != null))
    {
      // Script comes after the input element, since it references the element.
      writer.startElement("script", button);
      writer.writeAttribute("language", "JavaScript", null);
      writer.writeText("document.getElementsByName('" + clientId +
                       "')[0].set_image = function(button, img)",
                       null);
      writer.writeText("{", null);
      writer.writeText("button.src = img;", null);
      writer.writeText("}", null);
      writer.endElement("script");
    }
  }

  public void decode(FacesContext context, UIComponent component)
  {
    if (Util.canModifyValue(component))
    {
      String type = (String)component.getAttributes().get("type");
      String clientId = component.getClientId(context);
      Map requestParameterMap = context.getExternalContext().getRequestParameterMap();
      /* If the type is not "reset", and there's a request parameter equal
         to the client id, we queue an action event. The "x" and "y" are
         sent by the browser if an image was clicked for the button.
         We don't care what the values are, since no value is actually changed.
         We just want to make sure the request paramter is there,
         which means that the button was clicked. */
      if ((type == null || !type.equalsIgnoreCase("reset")) &&
          ((requestParameterMap.get(clientId) != null) ||
           (requestParameterMap.get(clientId + ".x") != null) ||
           (requestParameterMap.get(clientId + ".y") != null)))
      {
        component.queueEvent(new ActionEvent(component));
      }
    }
  }

  protected String getImageSrc(FacesContext context, String image)
  {
    if (image != null && image.startsWith("/"))
    {
      return context.getExternalContext().getRequestContextPath() +
                     image;
    }
    else
    {
      return image;
    }
  }
}
