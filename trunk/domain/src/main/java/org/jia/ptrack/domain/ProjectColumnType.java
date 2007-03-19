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

package org.jia.ptrack.domain;

public class ProjectColumnType extends EnumeratedType
{
  public final static ProjectColumnType NAME = new ProjectColumnType(0, "Name");
  public final static ProjectColumnType TYPE = new ProjectColumnType(10, "Type");
  public final static ProjectColumnType STATUS = new ProjectColumnType(20,
      "Status");
  public final static ProjectColumnType ROLE = new ProjectColumnType(30,
      "Role");

  private static EnumManager enumManager;

  static
  {
    enumManager = new EnumManager();
    enumManager.addInstance(NAME);
    enumManager.addInstance(TYPE);
    enumManager.addInstance(STATUS);
    enumManager.addInstance(ROLE);
  }

  public static EnumManager getEnumManager()
  {
    return enumManager;
  }

  private ProjectColumnType(int value, String description)
  {
    super(value, description);
  }
}
