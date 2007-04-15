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

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class UIDebug extends UIComponentBase implements Serializable
{
  public final static String COMPONENT_TYPE = "jia.Debug";
  public final static String COMPONENT_FAMILY = "jia.Debug";
  private boolean showRequest = true;
  private boolean showAppAttributes = true;
  private boolean showSessionAttributes = true;
  private boolean showRequestAttributes = true;
  private boolean showStackTrace = true;

  public UIDebug()
  {
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

    ResponseWriter writer = context.getResponseWriter();
    writer.startElement("h2", this);
    writer.writeText("Debug Info", null);
    writer.endElement("h2");
    if (showRequest)
    {
      displayRequest(context);
    }
    if (showRequestAttributes)
    {
      displayRequestAttributes(context);
    }
    if (showSessionAttributes)
    {
      displaySessionAttributes(context);
    }
    if (showAppAttributes)
    {
      displayAppAttributes(context);
    }
    if (showStackTrace)
    {
      displayStackTrace(context);
    }
  }

  protected void displayRequest(FacesContext context) throws IOException
  {
    HttpServletRequest request = (HttpServletRequest)context.
                                 getExternalContext().getRequest();
    ResponseWriter writer = context.getResponseWriter();
    int count = 0;

    startSection("Request properties", writer);
    Cookie[] cookies = request.getCookies();
    if (cookies != null)
    {
      for (count = 0; count < cookies.length; count++)
      {
        String prefix = "Cookies[" + count + "]: ";
        displayProperty(prefix + "Name", cookies[count].getName(), writer);
        displayProperty(prefix + "Path", cookies[count].getPath(), writer);
        displayProperty(prefix + "Value", cookies[count].getValue(), writer);
        displayProperty(prefix + "Comment", cookies[count].getComment(), writer);
        displayProperty(prefix + "Secure", new Boolean(cookies[count].getSecure()),
                        writer);
        displayProperty(prefix + "Version",
                        new Integer(cookies[count].getVersion()), writer);
        displayProperty(prefix + "MaxAge", new Integer(cookies[count].getMaxAge()),
                        writer);
        displayProperty(prefix + "Domain", cookies[count].getDomain(), writer);
      }
    }
    else
    {
      displayNoneFound("cookies", writer);
    }

    count = 0;
    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements())
    {
      String headerName = (String) headerNames.nextElement();
      displayProperty("Headers[" + count + "]: " +
                      headerName + " : ",
                      request.getHeader(headerName),
                      writer);
      count++;
    }
    if (count == 0)
    {
      displayNoneFound("headers", writer);
    }

    count = 0;
    Enumeration parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements())
    {
      String parameterName = (String) parameterNames.nextElement();
      displayProperty("Parameters[" + count + "]: " +
                      parameterName + ": ",
                      request.getParameter(parameterName),
                      writer);
      count++;
    }
    if (count == 0)
    {
      displayNoneFound("parameters", writer);
    }
    displayProperty("Context path", request.getContextPath(), writer);
    displayProperty("Request URI", request.getRequestURI(), writer);
    displayProperty("Auth type", request.getAuthType(), writer);
    displayProperty("Character encoding", request.getCharacterEncoding(),
                    writer);
    displayProperties("Locale", request.getLocales(), writer);
    displayProperty("Content length", String.valueOf(request.getContentLength()),
                    writer);
    displayProperty("Content type", request.getContentType(), writer);
    displayProperty("Path info", request.getPathInfo(), writer);
    displayProperty("Protocol", request.getProtocol(), writer);
    displayProperty("Remote address", request.getRemoteAddr(), writer);
    displayProperty("Remote host", request.getRemoteHost(), writer);
    displayProperty("Remote user", request.getRemoteUser(), writer);
    displayProperty("Scheme", request.getScheme(), writer);
    displayProperty("Server name", request.getServerName(), writer);
    displayProperty("Query string", request.getQueryString(), writer);
    displayProperty("Servlet path", request.getServletPath(), writer);
    displayProperty("Server port", String.valueOf(request.getServerPort()),
                    writer);
    displayProperty("User principal", request.getUserPrincipal(), writer);
    displayProperty("Session ID from cookie",
                    String.valueOf(request.isRequestedSessionIdFromCookie()),
                    writer);
    displayProperty("Session ID from URL",
                    String.valueOf(request.isRequestedSessionIdFromURL()),
                    writer);
    displayProperty("Session valid",
                    String.valueOf(request.isRequestedSessionIdValid()),
                    writer);
    displayProperty("Session secure", String.valueOf(request.isSecure()),
                    writer);

    endSection(writer);
  }

  protected void displayRequestAttributes(FacesContext context) throws IOException
  {
    ResponseWriter writer = context.getResponseWriter();
    Map requestMap = context.getExternalContext().getRequestMap();

    startSection("Request attributes", writer);
    if (requestMap.size() > 0)
    {
      displayProperties("attributes", requestMap, writer);
    }
    else
    {
      displayNoneFound("request attributes", writer);
    }

    endSection(writer);
  }

  protected void displayAppAttributes(FacesContext context) throws IOException
  {
    ResponseWriter writer = context.getResponseWriter();
    Map appMap = context.getExternalContext().getApplicationMap();

    startSection("Application attributes", writer);
    if (appMap.size() > 0)
    {
      displayProperties("attributes", appMap, writer);
    }
    else
    {
      displayNoneFound("application attributes", writer);
    }

    endSection(writer);
  }

  protected void displaySessionAttributes(FacesContext context) throws IOException
  {
    ResponseWriter writer = context.getResponseWriter();
    Map sessionMap = context.getExternalContext().getSessionMap();

    startSection("Session attributes", writer);

    if (sessionMap.size() > 0)
    {
      displayProperties("attributes", sessionMap, writer);
    }
    else
    {
      displayNoneFound("session attributes", writer);
    }

    endSection(writer);
  }

  protected void displayStackTrace(FacesContext context) throws IOException
  {
    ResponseWriter writer = context.getResponseWriter();
    Map requestMap = context.getExternalContext().getRequestMap();
    Exception e = (Exception)requestMap.get("javax.servlet.error.exception");

    startSection("Exception", writer);

    if (e != null)
    {
      displayProperty("Exception description", e.getMessage(), writer);
      displayProperties("Stack trace", e.getStackTrace(), writer);
    }
    else
    {
      displayNoneFound("exceptions", writer);
    }
  }

  protected void startSection(String headerName, ResponseWriter writer)
                  throws IOException
  {
    writer.startElement("p", this);
    writer.startElement("table", this);
    writer.startElement("tr", this);
    writer.startElement("th", this);
    writer.writeAttribute("colspan", "2", null);
    writer.writeAttribute("align", "left", null);
    writer.startElement("h3", this);
    writer.writeText(headerName, null);
    writer.endElement("h3");
    writer.endElement("th");
    writer.endElement("tr");
  }

  protected void endSection(ResponseWriter writer) throws IOException
  {
    writer.endElement("table");
    writer.endElement("p");
  }

  protected void displayProperty(String propertyName, Object propertyValue,
                                 ResponseWriter writer) throws IOException
  {
    String displayValue;
    if (propertyValue == null)
    {
      displayValue = "null";
    }
    else
    {
      displayValue = propertyValue.toString();
    }

    int maxLen = 60;
    int valueLen = displayValue.length();
    for (int startIndex = 0; startIndex < valueLen;
         startIndex += maxLen)
    {
      int endIndex = startIndex + maxLen;
      if (endIndex > valueLen)
      {
        endIndex = valueLen;
      }
      if (startIndex > 0)
      {
        propertyName = "";
      }
      writer.startElement("tr", this);
      writer.startElement("td", this);
      writer.startElement("b", this);
      writer.writeText(propertyName, null);
      writer.endElement("b");
      writer.endElement("td");
      writer.startElement("td", this);
      writer.writeText(displayValue.substring(startIndex, endIndex), null);
      writer.endElement("td");
      writer.endElement("tr");
    }
  }

  protected void displayNoneFound(String elementName, ResponseWriter writer)
                 throws IOException
  {
    writer.startElement("td", this);
    writer.startElement("td", this);
    writer.writeAttribute("colspan", "2", null);
    writer.writeText("No " + elementName + " found.", null);
  }

  protected void displayProperties(String propertyName, Object[] array,
                         ResponseWriter writer) throws IOException
  {
    for (int i = 0; i < array.length; i++)
    {
      displayProperty(propertyName + "[" + i + "]", array[i], writer);
    }
  }


  protected void displayProperties(String propertyName, Enumeration enumeration,
                                   ResponseWriter writer) throws IOException
  {
    int i = 0;
    while (enumeration.hasMoreElements())
    {
      displayProperty(propertyName + "[" + i + "]", enumeration.nextElement(),
                    writer);
      i++;
    }
  }


  protected void displayProperties(String propertyName, Map map,
                         ResponseWriter writer) throws IOException
  {
    int i = 0;
    Iterator iterator = map.keySet().iterator();
    while (iterator.hasNext())
    {
      String key = (String)iterator.next();
      displayProperty(propertyName + "[" + i + "]:" + key,
                      map.get(key), writer);
      i++;
    }
  }

  // Properties

  public boolean getShowRequest()
  {
    return showRequest;
  }

  public void setShowRequest(boolean showRequest)
  {
    this.showRequest = showRequest;
  }

  public boolean getShowAppAttributes()
  {
    return showAppAttributes;
  }

  public void setShowAppAttributes(boolean showAppAttributes)
  {
    this.showAppAttributes = showAppAttributes;
  }

  public boolean getShowSessionAttributes()
  {
    return showSessionAttributes;
  }

  public void setShowSessionAttributes(boolean showSessionAttributes)
  {
    this.showSessionAttributes = showSessionAttributes;
  }

  public boolean getShowRequestAttributes()
  {
    return showRequestAttributes;
  }

  public void setShowRequestAttributes(boolean showRequestAttributes)
  {
    this.showRequestAttributes = showRequestAttributes;
  }

  public boolean getShowStackTrace()
  {
    return showStackTrace;
  }

  public void setShowStackTrace(boolean showStackTrace)
  {
    this.showStackTrace = showStackTrace;
  }

}
