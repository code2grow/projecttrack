package org.jia.ptrack.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project
    implements Serializable
{
  
 
  private String name;
  private User initiatedBy;
  private String description;
  private List<Operation> operationHistory;
  private List artifacts;
  private ProjectType type;
  private int id;
  
  private int version;
  
	public int getId() {
		return id;
	}
  
  private Status status;

  private String initialComments;
  private Contact requirementsContact;

  
  public class ProjectBuilder {
    private String name;
    private User initiatedBy;
    private String description;
    private List<Operation> operationHistory;
    private List artifacts;
    private ProjectType type;
    private int id;
    private Status status;
    
    public ProjectBuilder(Status initialStatus) {
      this.status = status;
    }

    ProjectBuilder name(String name) {
      this.name = name;
      return this;
    }
  
    ProjectBuilder description(String description) {
      this.description = description;
      return this;
    }

    ProjectBuilder initiatedBy(User initiatedBy) {
      this.initiatedBy = initiatedBy;
      return this;
    }
    
    public Project make() {
      // Verify that we have everything
      return new Project(this);
    }
  }
  
  public Project(ProjectBuilder builder) {
    this.name = builder.name;
    this.initiatedBy = builder.initiatedBy;
  }

  public Project()
  {
    operationHistory = new ArrayList();
    artifacts = new ArrayList();
  }

  public Project(Status initialStatus)
  {
    this();
    this.status = initialStatus;
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

  public List<Operation> getHistory()
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

  public boolean changeStatus(boolean approve, User user,
                                           String comments)
  {
    return changeStatus(new Date(), approve, user, comments);
  }

  public boolean changeStatus(Date date, boolean approve,
                                           User user,
                                           String comments)
  {
    if (status == null)
    {
      throw new NullPointerException("The initialStatus property must be " +
                                     "set before the status can be changed.");
    }

	if (status.isValidStateChange(approve, user.getRole()))
    {
      return false;
    }

    Status fromStatus = status;
    Status toStatus = status.getToStatus(approve);

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

  public void setInitialComments(String initialComments)
  {
    this.initialComments = initialComments;
  }

  public String getInitialComments()
  {
    return initialComments;
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

	
	public boolean isValidStateChange(boolean approve) {
		return status.isValidStateChange(approve);
	}

	public boolean isApprovable(User user) {
		return status.isValidStateChange(true, user);
	}

	public boolean isRejectable(User user) {
		return status.isValidStateChange(false, user);
	}

	public int getVersion() {
		return version;
	}


  public Project(String name, ProjectType type, String description,
      User initiatedBy, Status status, List artifacts,
      String initialComments, Contact requirementsContact) {
    super();
    this.name = name;
    this.initiatedBy = initiatedBy;
    this.description = description;
    this.artifacts = artifacts;
    this.type = type;
    this.status = status;
    this.initialComments = initialComments;
    this.requirementsContact = requirementsContact;
  }
}
