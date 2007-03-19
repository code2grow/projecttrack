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

package org.jia.util;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.component.StateHolder;

public class ConstantMethodBinding extends MethodBinding implements StateHolder
{
  private String value;
  private boolean transientFlag;

  public ConstantMethodBinding(String value)
  {
    this.value = value;
  }

  public Class getType(FacesContext context)
  {
    return String.class;
  }

  public Object invoke(FacesContext context, Object[] params)
  {
    return value;
  }

  public Object saveState(FacesContext context)
  {
    return value;
  }

  public void restoreState(FacesContext context, Object state)
  {
    this.value = (String)state;
  }

  public boolean isTransient()
  {
    return transientFlag;
  }

  public void setTransient(boolean transientFlag)
  {
    this.transientFlag = transientFlag;
  }
}
