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

package org.jia.ptrack.web;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Utils
{
  public static void log(FacesContext facesContext, String message)
  {
    facesContext.getExternalContext().log(message);

  }

  public static void log(FacesContext facesContext, String message,
                         Exception exception)
  {
    facesContext.getExternalContext().log(message, exception);
  }

  public static void reportError(FacesContext facesContext, String message,
                                String detail, Exception exception)
  {
    facesContext.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            message, detail));
    if (exception != null)
    {
      facesContext.getExternalContext().log(message, exception);
    }
  }

  public static void reportError(FacesContext facesContext, String messageId,
                                   Exception exception)
  {
    FacesMessage message = getMessage(messageId, null,
                             FacesMessage.SEVERITY_ERROR);
    facesContext.addMessage(null, message);
    if (exception != null)
    {
      facesContext.getExternalContext().log(message.getSummary(), exception);
    }
  }


  public static void log(ServletContext servletContext, String message)
  {
    servletContext.log(message);
  }

  protected static void addInvalidStateChangeMessage(FacesContext context,
                                                 boolean approve)
  {

    String message;
    if (approve)
    {
      message = "You cannot approve a project with this status.";
    }
    else
    {
      message = "You cannot reject a project with this status.";
    }
    context.addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        message, ""));
  }

  protected static ClassLoader getCurrentClassLoader(Object defaultObject)
  {
    ClassLoader loader =
          Thread.currentThread().getContextClassLoader();
    if (loader == null)
    {
      loader = defaultObject.getClass().getClassLoader();
    }
    return loader;
  }

  public static String getDisplayString(String bundleName, String id,
                                        Object params[],
                                        Locale locale)
  {
    String text = null;
    ResourceBundle bundle =
        ResourceBundle.getBundle(bundleName, locale,
                                 getCurrentClassLoader(params));
    try
    {
      text = bundle.getString(id);
    }
    catch (MissingResourceException e)
    {
      text = "!! key " + id + " not found !!";
    }
    if (params != null)
    {
      MessageFormat mf = new MessageFormat(text, locale);
      text = mf.format(params, new StringBuffer(), null).toString();
    }
    return text;
  }

  public static FacesMessage getMessage(String messageId, Object params[],
                                        FacesMessage.Severity severity)
  {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    String bundleName = facesContext.getApplication().getMessageBundle();
    if (bundleName != null)
    {
      String summary = null;
      String detail = null;
      Locale locale = facesContext.getViewRoot().getLocale();
      ResourceBundle bundle =
          ResourceBundle.getBundle(bundleName, locale,
                                   getCurrentClassLoader(params));
      try
      {
        summary = bundle.getString(messageId);
        detail = bundle.getString(messageId + ".detail");
      }
      catch (MissingResourceException e)
      {}
      if (summary != null)
      {
        MessageFormat mf = null;
        if (params != null)
        {
          mf = new MessageFormat(summary, locale);
          summary = mf.format(params, new StringBuffer(), null).toString();
        }
        if (detail != null && params != null)
        {
          mf.applyPattern(detail);
          detail = mf.format(params, new StringBuffer(), null).toString();
        }
        return (new FacesMessage(severity, summary, detail));
      }
    }
    return new FacesMessage(severity, "!! key " + messageId + " not found !!",
                            null);
  }
}
