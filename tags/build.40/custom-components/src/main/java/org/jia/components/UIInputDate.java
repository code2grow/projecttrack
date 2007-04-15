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

import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UIInputDate extends UIInput
{
  public final static String COMPONENT_TYPE = "jia.InputDate";
  public final static String COMPONEN_FAMILY = "jia.InputDate";
  protected final static String DAY_KEY = "day";
  protected final static String MONTH_KEY = "month";
  protected final static String YEAR_KEY = "year";
  protected final static String TIME_KEY = "time";

  private boolean showDay = true;
  private boolean showMonth = true;
  private boolean showYear = true;
  private boolean showTime = true;
  private int startYear = -1;
  private int endYear = -1;

  public UIInputDate()
  {
    super();
    setRendererType(null);
  }

  public String getFamily()
  {
    return COMPONENT_FAMILY;
  }

  // Encoding methods

  public void encodeBegin(FacesContext context) throws java.io.IOException
  {
    if (!isRendered())
    {
        return;
    }

    if (!showDay && !showMonth && !showYear && !showTime)
    {
      throw new InvalidPropertiesException(
                "All display properties set to false. " +
                "One of the showDay, showMonth, showYear, or showTime " +
                "properties must be set to true.");
     }

    ResponseWriter writer = context.getResponseWriter();
    Calendar calendar = getCalendar(context, getDate());

    if (showDay)
    {
      displaySelectFromCalendar(DAY_KEY, Calendar.DAY_OF_MONTH, "dd", calendar,
                                calendar.getMinimum(Calendar.DAY_OF_MONTH),
                                calendar.getMaximum(Calendar.DAY_OF_MONTH),
                                context, writer);
    }
    if (showMonth)
    {
      displaySelectFromCalendar(MONTH_KEY, Calendar.MONTH, "MMMM", calendar,
                                calendar.getMinimum(Calendar.MONTH),
                                calendar.getMaximum(Calendar.MONTH),
                                context, writer);
    }
    if (showYear)
    {
      int startValue = startYear, endValue = endYear;
      if (startYear == -1)
      {
        startValue = calendar.get(Calendar.YEAR);
      }
      if (endYear == -1)
      {
        endValue = calendar.get(Calendar.YEAR);
      }
      displaySelectFromCalendar(YEAR_KEY, Calendar.YEAR, "yyyy", calendar,
                                startValue,
                                endValue,
                                context, writer);
    }
    if (showTime)
    {
      displaySelectFromCalendar(TIME_KEY, Calendar.HOUR_OF_DAY, "hh:00 a", calendar,
                                calendar.getMinimum(Calendar.HOUR_OF_DAY),
                                calendar.getMaximum(Calendar.HOUR_OF_DAY),
                                context, writer);
    }
  }

  protected void displayOption(String text, int value, boolean selected,
                               ResponseWriter writer)
                 throws IOException
  {
    writer.startElement("option", this);
    writer.writeAttribute("value", new Integer(value), null);
    if (selected)
    {
      writer.writeAttribute("selected", "true", null);
    }
    writer.writeText(text, null);
    writer.endElement("option");
  }

  protected void displaySelectFromCalendar(String key, int calendarField,
                                         String dateFormatPattern,
                                         Calendar calendar,
                                         int minFieldValue,
                                         int maxFieldValue,
                                         FacesContext context,
                                         ResponseWriter writer)
                 throws IOException
  {
    Locale locale = context.getViewRoot().getLocale();
    writer.startElement("select", this);
    writer.writeAttribute("name", getFieldKey(context, key), null);

    SimpleDateFormat formatter = new SimpleDateFormat(dateFormatPattern, locale);
    Calendar tempCalendar = Calendar.getInstance(locale);
    tempCalendar.clear();
    tempCalendar.set(calendarField, minFieldValue);
    boolean done = false;
    while (!done)
    {
      int currentFieldValue = tempCalendar.get(calendarField);
      displayOption(formatter.format(tempCalendar.getTime()),
                    currentFieldValue,
                    calendar.get(calendarField) == currentFieldValue,
                    writer);
      tempCalendar.roll(calendarField, 1);
      if (calendarField == Calendar.YEAR)
      {
        done = tempCalendar.get(calendarField) > maxFieldValue; // years go on forever, so doesn't reset
      }
      else
      {
        done = tempCalendar.get(calendarField) == minFieldValue; // value resets
      }
    }
    writer.endElement("select");
  }

  // Decoding methods

  public void decode(FacesContext context)
  {
    if (!Util.canModifyValue(this))
    {
      return;
    }

    Date currentValue = getDate();
    Calendar calendar = getCalendar(context, currentValue);

    Map requestParameterMap = context.getExternalContext().getRequestParameterMap();

    String dayKey = getFieldKey(context, DAY_KEY);
    String monthKey = getFieldKey(context, MONTH_KEY);
    String yearKey = getFieldKey(context, YEAR_KEY);
    String timeKey = getFieldKey(context, TIME_KEY);
    if (requestParameterMap.containsKey(dayKey))
    {
      calendar.set(Calendar.DAY_OF_MONTH,
                   Integer.parseInt((String)requestParameterMap.get(dayKey)));
    }
    if (requestParameterMap.containsKey(monthKey))
    {
      calendar.set(Calendar.MONTH,
                   Integer.parseInt((String)requestParameterMap.get(monthKey)));
    }
    if (requestParameterMap.containsKey(yearKey))
    {
      calendar.set(Calendar.YEAR,
                   Integer.parseInt((String)requestParameterMap.get(yearKey)));
    }
    if (requestParameterMap.containsKey(timeKey))
    {
      calendar.set(Calendar.HOUR_OF_DAY,
                   Integer.parseInt((String)requestParameterMap.get(timeKey)));
    }

/*    String dayKey = getFieldKey(context, DAY_KEY);
    String monthKey = getFieldKey(context, MONTH_KEY);
    String yearKey = getFieldKey(context, YEAR_KEY);
    String timeKey = getFieldKey(context, TIME_KEY);
    setSubmittedValue(new Object[] { requestParameterMap.get(dayKey),
                                     requestParameterMap.get(monthKey),
                                     requestParameterMap.get(yearKey),
                                     requestParameterMap.get(timeKey)});*/
    setSubmittedValue(calendar.getTime());
//    this.setPrevious(currentValue);
//    setValue(calendar.getTime());
  }

  protected String getFieldKey(FacesContext context, String suffix)
  {
    return getClientId(context) +  NamingContainer.SEPARATOR_CHAR + suffix;
  }

  protected Calendar getCalendar(FacesContext context, Date currentValue)
  {
    Calendar calendar = Calendar.getInstance(context.getViewRoot().getLocale());
    if (currentValue != null)
    {
      calendar.setTime(currentValue);
    }
    else
    {
//      calendar.setTime();
//      setValue(currentTime);
    }
    return calendar;
  }

  // StateHolder methods

  public Object saveState(FacesContext context)
  {
    Object[] values = new Object[7];
    values[0] = super.saveState(context);
    values[1] = showDay ? Boolean.TRUE : Boolean.FALSE;
    values[2] = showMonth ? Boolean.TRUE : Boolean.FALSE;
    values[3] = showYear ? Boolean.TRUE : Boolean.FALSE;
    values[4] = showTime ? Boolean.TRUE : Boolean.FALSE;
    values[5] = new Integer(startYear);
    values[6] = new Integer(endYear);

    return values;
  }

  public void restoreState(FacesContext context, Object state)
  {
    Object[] values = (Object[])state;
    super.restoreState(context, values[0]);
    // Avoid setters to speed things up
    showDay = ((Boolean) values[1]).booleanValue();
    showMonth = ((Boolean)values[2]).booleanValue();
    showYear = ((Boolean)values[3]).booleanValue();
    showTime = ((Boolean)values[4]).booleanValue();
    startYear = ((Integer)values[5]).intValue();
    endYear = ((Integer)values[6]).intValue();
  }

 // Properties

  public Date getDate()
  {
    return (Date)getValue();
  }

  public void setDate(Date date)
  {
    setValue(date);
  }

  public boolean getShowDay()
  {
    return showDay;
  }

  public void setShowDay(boolean showDay)
  {
    this.showDay = showDay;
  }

  public boolean getShowMonth()
  {
    return showMonth;
  }

  public void setShowMonth(boolean showMonth)
  {
    this.showMonth = showMonth;
  }

  public boolean getShowYear()
  {
    return showYear;
  }

  public void setShowYear(boolean showYear)
  {
    this.showYear = showYear;
  }

  public int getStartYear()
  {
    return startYear;
  }

  public void setStartYear(int startYear)
  {
    this.startYear = startYear;
  }

  public int getEndYear()
  {
    return endYear;
  }

  public void setEndYear(int endYear)
  {
    this.endYear = endYear;
  }

  public boolean getShowTime()
  {
    return showTime;
  }

  public void setShowTime(boolean showTime)
  {
    this.showTime = showTime;
  }
}
