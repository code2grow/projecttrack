package org.jia.ptrack.domain;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PTrackWorld {

  private final Department itDepartment;
  private final User itProjectManager;
  private final User itBusinessAnalyst;
  private final StateMachine stateMachine;

  private final Department marketingDepartment;
  private final Collection<User> marketingDepartmentEmployees;

  private final Project projectInCompleteState;
  private final Project projectInRequirementsState;
  private final Project projectInProposalState;
  private final List<User> itDepartmentEmployees;
  private final Status initialStatus;
  private final User marketingDepartmentProjectManager;

  public PTrackWorld() {
    itDepartment = new Department("IT");

    itProjectManager = UserMother.makeProjectManager(itDepartment);
    itBusinessAnalyst = UserMother.makeBusinessAnalyst(itDepartment);

    User systemsManager = UserMother.makeSystemsManager(itDepartment);
    User qaManager = UserMother.makeQAManager(itDepartment);
    User developmentManager = UserMother.makeDevelopmentManager(itDepartment);
    User upperManager = UserMother.makeUpperManager();

    itDepartmentEmployees = new LinkedList<User>();
    itDepartmentEmployees.add(itProjectManager);
    itDepartmentEmployees.add(systemsManager);
    itDepartmentEmployees.add(qaManager);
    itDepartmentEmployees.add(developmentManager);
    itDepartmentEmployees.add(itBusinessAnalyst);
    itDepartmentEmployees.add(upperManager);

    stateMachine = new DefaultStateMachineFactory().makeStateMachine("default");
    initialStatus = stateMachine.getInitialStatus();
    projectInCompleteState = ProjectMother.makeProjectInCompleteState(initialStatus, itProjectManager, getAllITDepartmentEmployees());
    projectInRequirementsState = ProjectMother.makeProjectInRequirementsState(initialStatus, itProjectManager);
    projectInProposalState = ProjectMother.makeProjectInProposalState(initialStatus, itProjectManager);
    
    marketingDepartment = new Department("Marketing");
    marketingDepartmentProjectManager = UserMother.makeMarketingDepartmentProjectManager(marketingDepartment);
    marketingDepartmentEmployees = new LinkedList<User>();
    marketingDepartmentEmployees.add(marketingDepartmentProjectManager);
  }

  public User getItBusinessAnalyst() {
    return itBusinessAnalyst;
  }

  public Department getITDepartment() {
    return itDepartment;
  }

  public User getItProjectManager() {
    return itProjectManager;
  }

  public StateMachine getStateMachine() {
    return stateMachine;
  }

  public Collection<User> getAllITDepartmentEmployees() {
    return itDepartmentEmployees;
  }

  public Department getMarketingDepartment() {
    return marketingDepartment;
  }

  public Collection<User> getMarketingDepartmentEmployees() {
    return marketingDepartmentEmployees;
  }

  public Project getProjectInCompleteState() {
    return projectInCompleteState;
  }

  public Project getProjectInRequirementsState() {
    return projectInRequirementsState;
  }

  public Project getProjectInProposalState() {
    return projectInProposalState;
  }

  public Status getInitialState() {
    return initialStatus;
  }

  public User getMarketingDepartmentProjectManager() {
    return marketingDepartmentProjectManager;
  }

  

}
