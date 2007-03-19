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

import java.util.List;
import javax.faces.component.UIData;
import javax.faces.event.ActionEvent;

import org.jia.ptrack.domain.DataStoreException;
import org.jia.ptrack.domain.ObjectNotFoundException;

public class ShowHistoryBean extends BaseBean
{
  private int rowsPerPage = 5;
  private UIData historyDataTable;

  public ShowHistoryBean()
  {
  }

  public UIData getHistoryDataTable()
  {
    return historyDataTable;
  }

  public void setHistoryDataTable(UIData historyDataTable)
  {
    this.historyDataTable = historyDataTable;
  }

  public int getRowsPerPage()
  {
    return rowsPerPage;
  }

  public void setRowsPerPage(int rowsPerPage)
  {
    this.rowsPerPage = rowsPerPage;
  }

  public List getCurrentProjectHistory()
  {
//	  return getVisit().getCurrentProject().getHistory();
	  // Keep refreshing the project as we page through to avoid any lazy initialization problems when navigating from
	  // objects that are on later pages.
    try {
		return getProjectCoordinator().get(getVisit().getCurrentProject().getId()).getHistory();
	} catch (ObjectNotFoundException e) {
		throw new UnsupportedOperationException();
	} catch (DataStoreException e) {
		throw new UnsupportedOperationException();
	}
  }

  public boolean getShowNext()
  {
    return (historyDataTable.getFirst() + rowsPerPage) <
             getCurrentProjectHistory().size();
  }

  public boolean getShowPrevious()
  {
    return (historyDataTable.getFirst() - rowsPerPage) >= 0;
  }

  public void next(ActionEvent actionEvent)
  {
    int newFirst = historyDataTable.getFirst() + rowsPerPage;
    if (newFirst < getCurrentProjectHistory().size())
    {
      historyDataTable.setFirst(newFirst);
    }
  }

  public void previous(ActionEvent actionEvent)
  {
    int newFirst = historyDataTable.getFirst() - rowsPerPage;
    if (newFirst >= 0)
    {
      historyDataTable.setFirst(newFirst);
    }
  }

}
