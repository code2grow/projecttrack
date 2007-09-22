package org.jia.ptrack.domain;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class ProjectMother {

  public static Project makeProjectInProposalState(Status initialStatus, User user) {
    Project project;
    project = new Project(initialStatus);
    project.setName("Test Project #2");
    // project.setDescription("This is a description.");
    project.setType(ProjectType.INTERNAL_WEB);
    project.setArtifacts(new ArtifactType[] { ArtifactType.PROPOSAL,
        ArtifactType.PROJECT_PLAN });
    project.setInitiatedBy(user);
    return project;
  }

  public static Project makeProjectInRequirementsState(Status initialStatus, User user) {
    Project project;
    project = new Project(initialStatus);
    project.setName("Test Project #1");
    project.setDescription("description");
    project.setRequirementsContactName("Rick Jones");
    project.setRequirementsContactEmail("jones@nowhere.com");
    project.setType(ProjectType.EXTERNAL_DB);
    project.setArtifacts(new ArtifactType[] { ArtifactType.ARCHITECTURE,
        ArtifactType.DEPLOYMENT, ArtifactType.MAINTENANCE });
    project.setInitiatedBy(user);
    assertTrue("change status", project.changeStatus(true, user,
        "blah, blah, blah"));
    assertTrue("change status", project.changeStatus(true, user,
        "blah, blah, blah"));
    return project;
  }

  public static Project makeProjectInCompleteState(Status initialStatus, User user,
      Collection users) {
    Date opDate;
    Project project = new Project(initialStatus);
    project.setName("Inventory Manager 2.0");
    // project.setDescription("description");
    project.setInitiatedBy(user);
    project.setRequirementsContactName("Joan TooBusy");
    project.setRequirementsContactEmail("joan@toobusy.com");
    project
        .setInitialComments("The first version is horrible and completely unusable. It's time to rewrite it.");
    project.setType(ProjectType.INTERNAL_WEB);
    project.setArtifacts(new ArtifactType[] { ArtifactType.PROPOSAL,
        ArtifactType.PROJECT_PLAN });

    project.setInitiatedBy(user);
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mma");
    try {
      opDate = formatter.parse("04/12/2002 04:30pm");
    } catch (Exception e) {
      e.printStackTrace();
      opDate = new Date();
    }
    assertTrue(
        "change status",
        project
            .changeStatus(
                opDate,
                true,
                user,
                "Funding has been approved. The users are excited about the prospect of having something they can use."));

    try {
      opDate = formatter.parse("08/12/2002 08:30pm");
    } catch (Exception e) {
      e.printStackTrace();
      opDate = new Date();
    }
    
    // FIXME - what is this mean to do!
    
    for (int i = 0; i < 10; i++) {
      Status status = project.getStatus();
      if (status.isFinalState())
        break;
      User u = findUserForRole(users, status.getRole());
      assertTrue("Change status failed", project.changeStatus(opDate, true, u,
          i + " They all lied!"));
    }
    return project;
  }

  private static User findUserForRole(Collection users, RoleType role) {
    for (Iterator it = users.iterator(); it.hasNext();) {
      User user = (User) it.next();
      if (user.getRole().equals(role))
        return user;
    }
    throw new RuntimeException("User not found for role: " + role);
  }

  private static void assertTrue(String message, boolean result) {
    if (!result)
      throw new RuntimeException("Assertion failed: " + message);
  }

  public static Project makeProjectInRequirementsState() {
    StateMachine stateMachine = new DefaultStateMachineFactory()
        .makeStateMachine("default");

    Department itDepartment = new Department("IT");

    User projectManager = UserMother.makeProjectManager(itDepartment);
    Status initialState = stateMachine.getInitialStatus();
    Project project2 = makeProjectInRequirementsState(initialState, projectManager);
    return project2;
  }

}
