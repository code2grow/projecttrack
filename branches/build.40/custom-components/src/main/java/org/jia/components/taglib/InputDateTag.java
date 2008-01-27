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

import org.jia.components.UIInputDate;

import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentTag;

public class InputDateTag extends UIComponentTag
{
  private String value;
  private Boolean showDay;
  private Boolean showMonth;
  private Boolean showTime;
  private Boolean showYear;
  private Integer startYear;
  private Integer endYear;
  private Boolean immediate;

  public InputDateTag()
  {
    super();
  }

  // UIComponentTag methods

  public String getComponentType()
  {
    return UIInputDate.COMPONENT_TYPE;
  }

  public String getRendererType()
  {
    return null;
  }

  protected void setProperties(UIComponent component)
  {
    super.setProperties(component);
    UIInputDate uiDate = (UIInputDate)component;
    if (showDay != null)
    {
      uiDate.setShowDay(showDay.booleanValue());
    }
    if (showMonth != null)
    {
      uiDate.setShowMonth(showMonth.booleanValue());
    }
    if (showYear != null)
    {
      uiDate.setShowYear(showYear.booleanValue());
    }
    if (startYear != null)
    {
      uiDate.setStartYear(startYear.intValue());
    }
    if (endYear != null)
    {
      uiDate.setEndYear(endYear.intValue());
    }
    if (showTime != null)
    {
      uiDate.setShowTime(showTime.booleanValue());
    }
    if (value != null)
    {
      if (isValueReference(value))
      {
        uiDate.setValueBinding("value",
                               getFacesContext().getApplication().
                               createValueBinding(value));  /** Changed from context. */
      }
      else
      {
        uiDate.setValue(value);
      }
    }
    if (immediate != null)
    {
      uiDate.setImmediate(immediate.booleanValue());
    }
  }

  public void release()
  {
    super.release();
    showDay = null;
    showMonth = null;
    showYear = null;
    showTime = null;
    startYear = null;
    endYear = null;
    value = null;
    immediate = null;
  }

  // Properties

  public Boolean getShowDay()
  {
    return showDay;
  }

  public void setShowDay(Boolean showDay)
  {
    this.showDay = showDay;
  }

  public Boolean getShowMonth()
  {
    return showMonth;
  }

  public void setShowMonth(Boolean showMonth)
  {
    this.showMonth = showMonth;
  }

  public Boolean getShowYear()
  {
    return showYear;
  }

  public void setShowYear(Boolean showYear)
  {
    this.showYear = showYear;
  }

  public Integer getStartYear()
  {
    return startYear;
  }

  public void setStartYear(Integer startYear)
  {
    this.startYear = startYear;
  }

  public Integer getEndYear()
  {
    return endYear;
  }

  public void setEndYear(Integer endYear)
  {
    this.endYear = endYear;
  }

  public Boolean getShowTime()
  {
    return showTime;
  }

  public void setShowTime(Boolean showTime)
  {
    this.showTime = showTime;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public Boolean getImmediate()
  {
    return immediate;
  }

  public void setImmediate(Boolean immediate)
  {
    this.immediate = immediate;
  }
}
