package org.jia.ptrack.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Project
    implements Serializable
{
  private String name;
  private User initiatedBy;
  private String description;
  private java.util.List operationHistory;
  private List artifacts;
  private ProjectType type;
  private int id;
  
  // private int version;
  
  private Status status;

  private String initialComments;
  private Contact requirementsContact;

  public Project()
  {
    operationHistory = new ArrayList();
    artifacts = new ArrayList();
  }

  public Project(Status initialStatus)
  {
    this();
    setInitialStatus(initialStatus);
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public void setInitiatedBy(User initiatedBy)
  {
    this.initiatedBy = initiatedBy;
  }

  public User getInitiatedBy()
  {
    return initiatedBy;
  }

  public void setRequirementsContactName(String requirementsContactName)
  {
	if (requirementsContact == null) {
		requirementsContact = new Contact();
	}
    this.requirementsContact.setName(requirementsContactName);
  }

  public String getRequirementsContactName()
  {
    return requirementsContact == null ? null : requirementsContact.getName();
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getDescription()
  {
    return description;
  }

  public List getHistory()
  {
    return operationHistory;
  }

  public void setArtifacts(ArtifactType[] artifacts)
  {
	  
    this.artifacts = new ArrayList();
    for (int i = 0; i < artifacts.length; i++) {
		ArtifactType type = artifacts[i];
		this.artifacts.add(type);
	}
  }

  public ArtifactType[] getArtifacts()
  {
    return (ArtifactType[]) artifacts.toArray(new ArtifactType[artifacts.size()]);
  }

  public void setType(ProjectType type)
  {
    this.type = type;
  }

  public ProjectType getType()
  {
    return type;
  }

  public Status getStatus()
  {
    return status;
  }

  public synchronized boolean changeStatus(boolean approve, User user,
                                           String comments)
  {
    return changeStatus(new Date(), approve, user, comments);
  }

  public synchronized boolean changeStatus(Date date, boolean approve,
                                           User user,
                                           String comments)
  {
    if (status == null)
    {
      throw new NullPointerException("The initialStatus property must be " +
                                     "set before the status can be changed.");
    }

    if (!status.isValidStateChange(approve))
    {
      return false;
    }

    if (!user.getRole().equals(status.getRole()))
    	return false;
    	
    Status fromStatus = status;
    Status toStatus = null;

    if (approve)
    {
      toStatus = status.getApprovalStatus();
    }
    else
    {
      toStatus = status.getRejectionStatus();
    }

    Operation newAction = new Operation(date, user, fromStatus, toStatus,
                                        comments);
    operationHistory.add(newAction);
    status = toStatus;
    return true;
  }

  public void setInitialStatus(Status initialStatus)
  {
    if (status == null)
    {
      this.status = initialStatus;
    }
  }

//  public void setIdMap(java.util.Map idMap)
//  {
//    this.idMap = idMap;
//  }
//
//  public java.util.Map getIdMap()
//  {
//    Map idMap = new HashMap();
//    idMap.put(name, id);
//    return idMap;
//  }

  public void setInitialComments(String initialComments)
  {
    this.initialComments = initialComments;
  }

  public String getInitialComments()
  {
    return initialComments;
  }

  public String toString()
  {
    return name;
  }

  public String getRequirementsContactEmail()
  {
    return requirementsContact == null ? null : requirementsContact.getEmail();
  }

  public void setRequirementsContactEmail(String requirementsContactEmail)
  {
	if (requirementsContact == null) {
		requirementsContact = new Contact();
	}
    this.requirementsContact.setEmail(requirementsContactEmail);
  }

public String getId() {
	return Integer.toString(id);
}

public boolean isValidStateChange(boolean approve) {
	return getStatus().isValidStateChange(approve);
}
}
