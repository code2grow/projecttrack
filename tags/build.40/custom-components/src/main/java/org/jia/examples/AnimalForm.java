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

package org.jia.examples;

import javax.faces.context.*;
import javax.faces.event.*;
import javax.faces.model.*;

public class AnimalForm
{
  private javax.faces.model.SelectItemGroup lizardGroup;
  private String[] favoriteAnimals =
      {
      "1"};
  public AnimalForm()
  {
    SelectItem[] items = new SelectItem[2];
    items[0] = new SelectItem("30", "chameleons");
    items[1] = new SelectItem("40", "geckos");
    lizardGroup = new SelectItemGroup("lizards", null, false, items);
  }

  public javax.faces.model.SelectItemGroup getLizardGroup()
  {
    return lizardGroup;
  }

  public void setLizardGroup(javax.faces.model.SelectItemGroup lizardGroup)
  {
    this.lizardGroup = lizardGroup;
  }

  public String[] getFavoriteAnimals()
  {
    return favoriteAnimals;
  }

  public void setFavoriteAnimals(String[] favoriteAnimal)
  {
    this.favoriteAnimals = favoriteAnimal;
  }

  public String getFavoriteAnimal()
  {
    return favoriteAnimals[0];
  }

  public void setFavoriteAnimal(String favoriteAnimal)
  {
    this.favoriteAnimals = new String[] { favoriteAnimal };
  }

  public void favoriteAnimalChange(ValueChangeEvent event)
  {
    FacesContext.getCurrentInstance().getExternalContext().
        log("favoriteAnimalChenge listener fired");
  }

  protected void processAnimalRequest(int value)
  {
  }
}
