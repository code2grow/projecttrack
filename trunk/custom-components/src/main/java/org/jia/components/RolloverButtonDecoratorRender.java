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

import javax.faces.FactoryFinder;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.*;

import java.util.Map;

public class RolloverButtonDecoratorRender extends Renderer
{
  protected RenderKit defaultRenderKit = null;

  public RolloverButtonDecoratorRender()
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
          "Either the image, or value attribute must be set.");
    }

    UIComponent button = (UIComponent)component;
    String clientId = component.getClientId(context);

    String imageSrc = getImageSrc(context,
                                 (String)attributes.get("image"));
    String rolloverImageSrc = getImageSrc(context,
                               (String)attributes.get("rolloverImage"));

    if (imageSrc != null && rolloverImageSrc != null)
    {
      attributes.put("onmouseover",
                     "document.getElementsByName('" + clientId +
                     "')[0].set_image(this, '" + rolloverImageSrc +
                     "')");
      attributes.put("onmouseout",
                     "document.getElementsByName('" + clientId +
                     "')[0].set_image(this, '" + imageSrc + "')");
    }

    // Delegate to the Button renderer for the bulk of the work
    getButtonRenderer(context).encodeBegin(context, button);
  }

  public void encodeChildren(FacesContext context, UIComponent component) throws java.io.IOException
  {
    getButtonRenderer(context).encodeChildren(context, component);
  }

  public void encodeEnd(FacesContext context, UIComponent component) throws java.io.IOException
  {
    getButtonRenderer(context).encodeEnd(context, component);

    if (component.getAttributes().get("image") != null &&
        component.getAttributes().get("rolloverImage") != null)
    {
      ResponseWriter writer = context.getResponseWriter();
      writer.startElement("script", component);
      writer.writeAttribute("language", "JavaScript", null);
      writer.writeText("document.getElementsByName('" +
                       component.getClientId(context) +
                       "')[0].set_image = function(component, img)",
                       null);
      writer.writeText("{", null);
      writer.writeText("component.src = img;", null);
      writer.writeText("}", null);
      writer.endElement("script");
    }
  }

  public void decode(FacesContext context, UIComponent component)
  {
    getButtonRenderer(context).decode(context, component);
  }

  protected Renderer getButtonRenderer(FacesContext context)
  {
    RenderKitFactory rkFactory =
      (RenderKitFactory)FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
    RenderKit defaultRenderKit =  // talk about other variation of this method
      rkFactory.getRenderKit(context, RenderKitFactory.HTML_BASIC_RENDER_KIT);  /** Changed from DEFAULT_RENDER_KIT, changed order of parameters **/
    return defaultRenderKit.getRenderer(UICommand.COMPONENT_FAMILY, "javax.faces.Button");
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
