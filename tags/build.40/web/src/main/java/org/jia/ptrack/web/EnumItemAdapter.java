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

import org.jia.ptrack.domain.ArtifactType;
import org.jia.ptrack.domain.EnumeratedType;
import org.jia.ptrack.domain.ProjectType;
import org.jia.ptrack.domain.RoleType;

import javax.faces.model.SelectItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class EnumItemAdapter
{
  private Map itemLists = new HashMap();

  public EnumItemAdapter()
  {
    addType(ProjectType.class, ProjectType.getEnumManager());
    addType(ArtifactType.class, ArtifactType.getEnumManager());
    addType(RoleType.class, RoleType.getEnumManager());
  }

  protected void addType(Class clazz, EnumeratedType.EnumManager enumManager)
  {
    Iterator types = enumManager.getInstances().iterator();
    List selectItems = new ArrayList();
    while (types.hasNext())
    {
      EnumeratedType type = (EnumeratedType)types.next();
      SelectItem item = new SelectItem(type,
                                       type.getDescription(),
                                       type.getDescription());
      selectItems.add(item);
    }
    itemLists.put(clazz, selectItems);
  }

  public List getArtifacts()
  {
    return (List)itemLists.get(ArtifactType.class);
  }

  public List getRoles()
  {
    return (List)itemLists.get(RoleType.class);
  }

  public List getProjectTypes()
  {
    return (List)itemLists.get(ProjectType.class);
  }

  /** For demonstration; not really used by app
   *
   */
  public List getArtifactsHardcoded()
  {
    List artifacts = new ArrayList();
    artifacts.add(new SelectItem(ArtifactType.DEPLOYMENT,
                                 ArtifactType.DEPLOYMENT.getDescription()));
    artifacts.add(new SelectItem(ArtifactType.ARCHITECTURE,
                                 ArtifactType.ARCHITECTURE.getDescription()));
    artifacts.add(new SelectItem(ArtifactType.MAINTENANCE,
                                 ArtifactType.MAINTENANCE.getDescription()));
    artifacts.add(new SelectItem(ArtifactType.PROPOSAL,
                                 ArtifactType.PROPOSAL.getDescription()));
    artifacts.add(new SelectItem(ArtifactType.USER,
                                 ArtifactType.USER.getDescription()));
    artifacts.add(new SelectItem(ArtifactType.TEST,
                                 ArtifactType.TEST.getDescription()));
    artifacts.add(new SelectItem(ArtifactType.PROJECT_PLAN,
                                 ArtifactType.PROJECT_PLAN.getDescription()));
    return artifacts;
  }
}

