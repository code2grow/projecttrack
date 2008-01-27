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

package org.jia.components.model;

import java.util.*;

public class NavigatorItemList extends ArrayList
{
  private NavigatorItem selectedItem;

  public NavigatorItemList()
  {
    super();
  }

  public boolean containsName(String name)
  {
    Iterator iterator = iterator();
    while (iterator.hasNext())
    {
      if ( ((NavigatorItem)iterator.next()).getName().equals(name) )
      {
        return true;
      }
    }
    return false;
  }

  // List methods

  public void add(int index, Object element)
  {
    if (!(element instanceof NavigatorItem))
    {
      throw new ClassCastException(
          "This list only accepts NavigatorItem instances.");
    }
    super.add(index, element);
  }

  public boolean add(Object element)
  {
    if (!(element instanceof NavigatorItem))
    {
      throw new ClassCastException(
          "This list only accepts NavigatorItem instances.");
    }
    return super.add(element);
  }

  public boolean addAll(Collection c)
  {
    Iterator iterator = c.iterator();
    while (iterator.hasNext())
    {
      if (!(iterator.next() instanceof NavigatorItem))
      {
        throw new ClassCastException("This list only accepts NavigatorItem instances.");
      }
    }
    return super.addAll(c);
  }

  public boolean addAll(int index, Collection c)
  {
    Iterator iterator = c.iterator();
    while (iterator.hasNext())
    {
      if (!(iterator.next() instanceof NavigatorItem))
      {
        throw new ClassCastException("This list only accepts NavigatorItem instances.");
      }
    }
    return super.addAll(index, c);
  }

  public Object set(int index, Object element)
  {
    if (!(element instanceof NavigatorItem))
    {
      throw new ClassCastException(
          "This list only accepts NavigatorItem instances.");
    }
    return super.set(index, element);
  }

  // Properties

  public NavigatorItem getSelectedItem()
  {
    return selectedItem;
  }

  public void setSelectedItem(NavigatorItem selectedItem)
  {
    this.selectedItem = selectedItem;
  }
}
