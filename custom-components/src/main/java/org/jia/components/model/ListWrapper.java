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

import java.io.Serializable;
import java.util.*;

public abstract class ListWrapper implements List, Serializable
{
  private java.util.List wrappedList;

  public ListWrapper(List wrappedList)
  {
    this.wrappedList = wrappedList;
  }

  public java.util.List getWrappedList()
  {
    return wrappedList;
  }

  public void setWrappedList(java.util.List wrappedList)
  {
    this.wrappedList = wrappedList;
  }

  // wrapped methods

  public void add(int index, Object element)
  {
    wrappedList.add(index, element);
  }

  public boolean add(Object element)
  {
   return wrappedList.add(element);
  }

  public int size()
  {
    return wrappedList.size();
  }

  public boolean isEmpty()
  {
    return wrappedList.isEmpty();
  }

  public boolean contains(Object o)
  {
    return wrappedList.contains(o);
  }

  public Iterator iterator()
  {
    return wrappedList.iterator();
  }

  public Object[] toArray()
  {
    return wrappedList.toArray();
  }

  public Object[] toArray(Object[] a)
  {
    return wrappedList.toArray(a);
  }

  public boolean remove(Object o)
  {
    return wrappedList.remove(o);
  }

  public boolean containsAll(Collection c)
  {
    return wrappedList.containsAll(c);
  }

  public boolean addAll(Collection c)
  {
    return wrappedList.addAll(c);
  }

  public boolean addAll(int index, Collection c)
  {
    return wrappedList.addAll(index, c);
  }

  public boolean removeAll(Collection c)
  {
    return wrappedList.removeAll(c);
  }

  public boolean retainAll(Collection c)
  {
    return wrappedList.retainAll(c);
  }

  public void clear()
  {
    wrappedList.clear();
  }

  public boolean equals(Object o)
  {
    return wrappedList.equals(o);
  }

  public Object get(int index)
  {
    return wrappedList.get(index);
  }

  public Object set(int index, Object element)
  {
    return wrappedList.set(index, element);
  }

  public Object remove(int index)
  {
    return wrappedList.remove(index);
  }

  public int indexOf(Object o)
  {
    return wrappedList.indexOf(o);
  }

  public int lastIndexOf(Object o)
  {
    return wrappedList.lastIndexOf(o);
  }

  public ListIterator listIterator()
  {
    return wrappedList.listIterator();
  }

  public ListIterator listIterator(int index)
  {
    return wrappedList.listIterator(index);
  }

  public List subList(int fromIndex, int toIndex)
  {
    return wrappedList.subList(fromIndex, toIndex);
  }


}
