package org.jia.ptrack.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.jia.ptrack.domain.ArtifactType;

/**
 *
 *  NOTE: This class differs slightly from the book. The core conversion logic
 *        was factored out into separate methods
 *        for easy testing.
 */

public class ArtifactTypeConverter
    implements Converter
{

  public final static String CONVERTER_ID = "jia.ArtifactType";

  public ArtifactTypeConverter()
  {
  }

  public Object getAsObject(FacesContext context, UIComponent component,
                            String value)
  {
    Utils.log(context, "Converting from " + value + " to Object");

    try
    {
      return getStringAsArtifactType(value);
    }
    catch (NumberFormatException ne)
    {
      Utils.log(context, "Can't convert to an ArtifactType; value (" +
                value + ") is not an integer.");
      throw new ConverterException("Can't convert to an ArtifactType; value (" +
                                   value + ") is not an integer.", ne);
    }
    catch (IllegalArgumentException e)
    {
      Utils.log(context, "Can't convert ArtifactType; unknown value: " + value);
      throw new ConverterException(
          "Can't convert ArtifactType; unknown value: " + value, e);
    }
  }

  protected ArtifactType getStringAsArtifactType(String value) throws
      NumberFormatException,
      IllegalArgumentException
  {
    if (value == null)
    {
      return null;
    }
    return (ArtifactType) ArtifactType.getEnumManager().getInstanceByValue(value);
  }

  public String getAsString(FacesContext context, UIComponent component,
                            Object value)
  {
    Utils.log(context, "Converting from " + value + " to string");

    if (value instanceof ArtifactType)
    {
      return getArtifactTypeAsString(value);
    }
    else
    {
      Utils.log(context, "Incorrect type (" + value.getClass() +
                "; value = " + value +
                "); value must be an ArtifactType instance");
      throw new ConverterException("Incorrect type (" + value.getClass() +
                                   "; value = " + value +
                                   "); value must be an ArtifactType instance");
    }
  }

  protected String getArtifactTypeAsString(Object value)
  {
    if (value == null)
    {
      return null;
    }
    ArtifactType artifact = (ArtifactType) value;
    return String.valueOf(artifact.getValue());
  }
}
