package org.jia.components.model;

import javax.faces.model.*;

import java.util.ArrayList;
import java.util.List;
import de.nava.informa.core.ChannelIF;

/**
 * NOTE: Channel data is cached by this object.
 * NOTE: Prime unit-testing candidate.
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ChannelDataModel extends DataModel
{
  private ChannelIF channel;
  private List items;
  private int rowIndex;

  public ChannelDataModel()
  {
    this(null);
  }

  public ChannelDataModel(ChannelIF channel)
  {
    super();
    rowIndex = -1;
    setWrappedData(channel);
  }

  // DataModel properties

  public void setRowIndex(int rowIndex)
  {
    if (rowIndex < -1)
    {
      throw new IllegalArgumentException("rowIndex must be -1 or greater.");
    }
    if (channel != null && this.rowIndex != rowIndex)
    {
      this.rowIndex = rowIndex;
      Object rowData = null;
      if (isRowAvailable())
      {
        rowData = getRowData();
      }
      DataModelListener[] listeners = getDataModelListeners();
      for (int i = 0; i < listeners.length; i++)
      {
        listeners[i].rowSelected(new DataModelEvent(this, rowIndex, rowData));
      }
    }
  }

  public int getRowIndex()
  {
    return rowIndex;
  }

  public boolean isRowAvailable()
  {
    return (channel != null && rowIndex > -1 && rowIndex < items.size());
  }

  public int getRowCount()
  {
    if (channel == null)
    {
      return -1;
    }
    return items.size();
  }

  public Object getRowData()
  {
    if (channel == null)
    {
      return null;
    }
    return items.get(rowIndex);
  }

  public void setWrappedData(Object data)
  {
    if (data != null)
    {
      if (!(data instanceof ChannelIF))
      {
        throw new IllegalArgumentException(
            "Only ChannelIF instances can be wrapped; " +
            " recieved a " + data.getClass().getName() +
            " instance instead.");
      }
      channel = (ChannelIF)data;
      items = new ArrayList(channel.getItems());
      setRowIndex(0);
    }
    else
    {
      channel = null;
      items = null;
    }
  }

  public Object getWrappedData()
  {
    return channel;
  }

}
