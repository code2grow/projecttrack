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

import org.jia.components.UIDebug;

import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentTag;

public class DebugTag extends UIComponentTag
{
  private Boolean showRequest;
  private Boolean showRequestAttributes;
  private Boolean showSessionAttributes;
  private Boolean showAppAttributes;
  private Boolean showStackTrace;

  public DebugTag()
  {
    super();
  }

  // UIComponentTag methods

  public String getComponentType()
  {
    return "jia.Debug";
  }

  public String getRendererType()
  {
    return null;
  }

  protected void setProperties(UIComponent component)
  {
    super.setProperties(component);

    UIDebug debug = (UIDebug)component;
    if (showRequest != null)
    {
      debug.setShowRequest(showRequest.booleanValue());
    }
    if (showRequestAttributes != null)
    {
      debug.setShowRequestAttributes(showRequestAttributes.booleanValue());
    }
    if (showSessionAttributes != null)
    {
      debug.setShowSessionAttributes(showSessionAttributes.booleanValue());
    }
    if (showStackTrace != null)
    {
      debug.setShowStackTrace(showStackTrace.booleanValue());
    }
    if (showAppAttributes != null)
    {
      debug.setShowAppAttributes(showAppAttributes.booleanValue());
    }
  }

  public void release()
  {
    super.release();
    showRequest = null;
    showRequestAttributes = null;
    showSessionAttributes = null;
    showAppAttributes = null;
    showStackTrace = null;
  }

  // Properties

  public Boolean getShowRequest()
  {
    return showRequest;
  }

  public void setShowRequest(Boolean showRequest)
  {
    this.showRequest = showRequest;
  }

  public Boolean getShowRequestAttributes()
  {
    return showRequestAttributes;
  }

  public void setShowRequestAttributes(Boolean showRequestAttributes)
  {
    this.showRequestAttributes = showRequestAttributes;
  }

  public Boolean getShowSessionAttributes()
  {
    return showSessionAttributes;
  }

  public void setShowSessionAttributes(Boolean showSessionAttributes)
  {
    this.showSessionAttributes = showSessionAttributes;
  }

  public Boolean getShowAppAttributes()
  {
    return showAppAttributes;
  }

  public void setShowAppAttributes(Boolean showAppAttributes)
  {
    this.showAppAttributes = showAppAttributes;
  }

  public Boolean getShowStackTrace()
  {
    return showStackTrace;
  }

  public void setShowStackTrace(Boolean showStackTrace)
  {
    this.showStackTrace = showStackTrace;
  }

}
