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

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;

public class InputDateTEI extends TagExtraInfo
{
  public InputDateTEI()
  {
  }

  public boolean isValid(TagData tagData)
  {
    return (isTrue(tagData.getAttribute("showDay")) ||
            isTrue(tagData.getAttribute("showMonth")) ||
            isTrue(tagData.getAttribute("showYear")) ||
            isTrue(tagData.getAttribute("showTime")));
  }

  /**
   * Returns true if the value wasn't set, is a request time value, or is true.
   * @param booleanAttribute
   * @return true if the value wasn't set, is a request time value, or is true.
   */
  protected boolean isTrue(Object booleanAttribute)
  {
    return (booleanAttribute == null ||
            booleanAttribute == TagData.REQUEST_TIME_VALUE ||
            booleanAttribute.toString().equalsIgnoreCase("true"));
  }
}
