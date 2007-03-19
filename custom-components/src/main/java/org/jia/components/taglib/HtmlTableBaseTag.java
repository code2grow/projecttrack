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

import org.jia.util.Util;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public abstract class HtmlTableBaseTag extends HtmlBaseTag
{
  private String cellpadding;
  private String cellspacing;
  private String border;
  private String width;
  private String summary;
  private String frame;
  private String align;
  private String bgcolor;

  public HtmlTableBaseTag()
  {
    super();
  }

  protected void setProperties(UIComponent component)
  {
    super.setProperties(component);

    Application app = FacesContext.getCurrentInstance().getApplication();
    Util.addAttribute(app, component, "cellpadding", cellpadding);
    Util.addAttribute(app, component, "cellspacing", cellspacing);
    Util.addAttribute(app, component, "border", border);
    Util.addAttribute(app, component, "width", width);
    Util.addAttribute(app, component, "summary", summary);
    Util.addAttribute(app, component, "frame", frame);
    Util.addAttribute(app, component, "align", align);
    Util.addAttribute(app, component, "bgcolor", bgcolor);
  }

  public String getCellpadding()
  {
    return cellpadding;
  }
  public void setCellpadding(String cellpadding)
  {
    this.cellpadding = cellpadding;
  }
  public String getCellspacing()
  {
    return cellspacing;
  }
  public void setCellspacing(String cellspacing)
  {
    this.cellspacing = cellspacing;
  }
  public String getBorder()
  {
    return border;
  }
  public void setBorder(String border)
  {
    this.border = border;
  }
  public String getWidth()
  {
    return width;
  }
  public void setWidth(String width)
  {
    this.width = width;
  }
  public String getSummary()
  {
    return summary;
  }
  public void setSummary(String summary)
  {
    this.summary = summary;
  }
  public String getFrame()
  {
    return frame;
  }
  public void setFrame(String frame)
  {
    this.frame = frame;
  }
  public String getAlign()
  {
    return align;
  }
  public void setAlign(String align)
  {
    this.align = align;
  }
  public String getBgcolor()
  {
    return bgcolor;
  }
  public void setBgcolor(String bgcolor)
  {
    this.bgcolor = bgcolor;
  }

}
