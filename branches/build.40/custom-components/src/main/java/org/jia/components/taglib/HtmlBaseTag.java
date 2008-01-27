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
import javax.faces.webapp.UIComponentTag;

public abstract class HtmlBaseTag extends UIComponentTag
{
  private String styleClass;
  private String onclick;
  private String ondblclick;
  private String onkeydown;
  private String onkeypress;
  private String onkeyup;
  private String onmousedown;
  private String onmousemove;
  private String onmouseout;
  private String onmouseover;
  private String onmouseup;
  private String disabled;
  private String alt;
  private String lang;
  private String dir;
  private String tabindex;
  private String accesskey;
  private String title;
  private String style;

  public HtmlBaseTag()
  {
    super();
  }

  // HtmlBaseTag methods

  protected void setProperties(UIComponent component)
  {
    super.setProperties(component);

    Application app = getFacesContext().getApplication();
    Util.addAttribute(app, component, "onclick", onclick);
    Util.addAttribute(app, component, "ondblclick", ondblclick);
    Util.addAttribute(app, component, "onkeydown", onkeydown);
    Util.addAttribute(app, component, "onkeypress", onkeypress);
    Util.addAttribute(app, component, "onkeyup", onkeyup);
    Util.addAttribute(app, component, "onmousedown", onmousedown);
    Util.addAttribute(app, component, "onmousemove", onmousemove);
    Util.addAttribute(app, component, "onmouseout", onmouseout);
    Util.addAttribute(app, component, "onmouseover", onmouseover);
    Util.addAttribute(app, component, "onmouseup", onmouseup);
    Util.addAttribute(app, component, "disabled", disabled);
    Util.addAttribute(app, component, "alt", alt);
    Util.addAttribute(app, component, "lang", lang);
    Util.addAttribute(app, component, "dir", dir);
    Util.addAttribute(app, component, "tabindex", tabindex);
    Util.addAttribute(app, component, "accesskey", accesskey);
    Util.addAttribute(app, component, "title", title);
    Util.addAttribute(app, component, "style", style);
    Util.addAttribute(app, component, "styleClass", styleClass);
  }

  public void release()
  {
    super.release();

    styleClass = null;
    onclick = null;
    ondblclick = null;
    onkeydown = null;
    onkeypress = null;
    onkeyup = null;
    onmousedown = null;
    onmousemove = null;
    onmouseout = null;
    onmouseover = null;
    onmouseup = null;
    disabled = null;
    alt = null;
    lang = null;
    dir = null;
    tabindex = null;
    accesskey = null;
    title = null;
    style = null;
  }

  // Properties

  public String getStyleClass()
  {
    return styleClass;
  }

  public void setStyleClass(String styleClass)
  {
    this.styleClass = styleClass;
  }

  public String getOnclick()
  {
    return onclick;
  }

  public void setOnclick(String onclick)
  {
    this.onclick = onclick;
  }

  public String getOndblclick()
  {
    return ondblclick;
  }

  public void setOndblclick(String ondblclick)
  {
    this.ondblclick = ondblclick;
  }

  public String getOnkeydown()
  {
    return onkeydown;
  }

  public void setOnkeydown(String onkeydown)
  {
    this.onkeydown = onkeydown;
  }

  public String getOnkeypress()
  {
    return onkeypress;
  }

  public void setOnkeypress(String onkeypress)
  {
    this.onkeypress = onkeypress;
  }

  public String getOnkeyup()
  {
    return onkeyup;
  }

  public void setOnkeyup(String onkeyup)
  {
    this.onkeyup = onkeyup;
  }

  public String getOnmousedown()
  {
    return onmousedown;
  }

  public void setOnmousedown(String onmousedown)
  {
    this.onmousedown = onmousedown;
  }

  public String getOnmousemove()
  {
    return onmousemove;
  }

  public void setOnmousemove(String onmousemove)
  {
    this.onmousemove = onmousemove;
  }

  public String getOnmouseout()
  {
    return onmouseout;
  }

  public void setOnmouseout(String onmouseout)
  {
    this.onmouseout = onmouseout;
  }

  public String getOnmouseover()
  {
    return onmouseover;
  }

  public void setOnmouseover(String onmouseover)
  {
    this.onmouseover = onmouseover;
  }

  public String getOnmouseup()
  {
    return onmouseup;
  }

  public void setOnmouseup(String onmouseup)
  {
    this.onmouseup = onmouseup;
  }

  public String getDisabled()
  {
    return disabled;
  }

  public void setDisabled(String disabled)
  {
    this.disabled = disabled;
  }

  public String getAlt()
  {
    return alt;
  }

  public void setAlt(String alt)
  {
    this.alt = alt;
  }

  public String getLang()
  {
    return lang;
  }

  public void setLang(String lang)
  {
    this.lang = lang;
  }

  public String getDir()
  {
    return dir;
  }

  public void setDir(String dir)
  {
    this.dir = dir;
  }

  public String getTabindex()
  {
    return tabindex;
  }

  public void setTabindex(String tabindex)
  {
    this.tabindex = tabindex;
  }

  public String getAccesskey()
  {
    return accesskey;
  }

  public void setAccesskey(String accesskey)
  {
    this.accesskey = accesskey;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getStyle()
  {
    return style;
  }

  public void setStyle(String style)
  {
    this.style = style;
  }
}
