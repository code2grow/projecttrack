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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.jia.ptrack.domain.ProjectType;

public class ProjectTypeConverter
    implements Converter
{
  public static final String CONVERTER_TYPE = "ProjectType";

  public ProjectTypeConverter()
  {
  }

  public Object getAsObject(FacesContext context, UIComponent component,
                            String value)
  {
    Utils.log(context, "Converting from " + value + " to Object");

    try
    {
      return getStringAsProjectType(value);
    }
    catch (NumberFormatException ne)
    {
      Utils.log(context,
                "Can't convert to an ProjectType; value (" + value + ") is not an integer.");
      throw new ConverterException("Can't convert to an ProjectType; value (" +
                                   value + ") is not an integer.", ne);
    }
    catch (IllegalArgumentException e)
    {
      Utils.log(context, "Can't convert ProjectType; unknown value: " + value);
      throw new ConverterException("Can't convert ProjectType; unknown value: " +
                                   value, e);
    }
  }

  protected ProjectType getStringAsProjectType(String value) throws
      NumberFormatException, IllegalArgumentException
  {
    if (value == null)
    {
      return null;
    }
    return (ProjectType) ProjectType.getEnumManager().getInstanceByValue(value);
  }

  public String getAsString(FacesContext context, UIComponent component,
                            Object value)
  {
    Utils.log(context, "Converting from " + value + " to string");
    if (value instanceof ProjectType)
    {
      return getProjectTypeAsString(value);
    }
    else
    {
      Utils.log(context, "Incorrect type (" + value.getClass() +
                "; value = " + value +
                "); value must be an ProjectType instance");
      throw new ConverterException("Incorrect type (" + value.getClass() +
                                   "; value = " + value +
                                   "); value must be an ProjectType instance");
    }
  }

  protected String getProjectTypeAsString(Object value)
  {
    if (value == null)
    {
      return null;
    }
    ProjectType Project = (ProjectType) value;
    return String.valueOf(Project.getValue());
  }

}
