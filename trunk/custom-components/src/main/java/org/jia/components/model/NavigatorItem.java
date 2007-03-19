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

package org.jia.components.model;

import java.io.Serializable;

public class NavigatorItem implements Serializable
{
  private String label;
  private String icon;
  private String link;
  private boolean direct;
  private boolean disabled;
  private String name;
  private String action;

  public NavigatorItem(String name, String label, String icon,
                       String action, String link, boolean direct,
                       boolean disabled)
  {
    if (name == null)
    {
      throw new NullPointerException("Name cannot be null");
    }
    this.name = name;
    this.label = label;
    this.icon = icon;
    this.action = action;
    this.link = link;
    this.direct = direct;
    this.disabled = disabled;
  }

  public NavigatorItem()
  {
  }

  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getIcon()
  {
    return icon;
  }

  public void setIcon(String icon)
  {
    this.icon = icon;
  }

  public String getAction()
  {
    return action;
  }

  public void setAction(String action)
  {
    this.action = action;
  }

  public String getLink()
  {
    return link;
  }

  public void setLink(String link)
  {
    this.link = link;
  }

  public boolean isDirect()
  {
    return direct;
  }

  public void setDirect(boolean direct)
  {
    this.direct = direct;
  }

  public boolean isDisabled()
  {
    return disabled;
  }

  public void setDisabled(boolean disabled)
  {
    this.disabled = disabled;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    if (name == null)
    {
      throw new NullPointerException("Name cannot be null");
    }
    this.name = name;
  }
}
