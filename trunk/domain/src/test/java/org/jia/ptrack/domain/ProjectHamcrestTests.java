package org.jia.ptrack.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.jia.ptrack.domain.ProjectMatchers.withinInclusivePeriod;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

public class ProjectHamcrestTests extends TestCase {

  private Project project;
  private User projectManager;
  private User businessAnalyst;
  private Status state0;
  private Date startTime;
  private Status state1;
  private Status state2;
  private Operation expectedOperation0;

  protected void setUp() throws Exception {
    PTrackWorld world = new PTrackWorld();
    projectManager = world.getItProjectManager();
    businessAnalyst = world.getItBusinessAnalyst();
    project = world.getProjectInProposalState();
    startTime = new Date();

    state0 = world.getInitialState();
    state1 = state0.getApprovalStatus();
    state2 = state1.getApprovalStatus();
    expectedOperation0 = new Operation(null, projectManager, state0, state1,
        "Excellent");
  }

  public void testProjectInProposalState() {
    assertTrue(project.getHistory().isEmpty());
    assertThat(project.getStatus(), is(state0));

  }

  public void testChangeStatus() throws InterruptedException {
    boolean result = project.changeStatus(true, projectManager, "Excellent");
    Date endTime = new Date();

    assertThat(result, is(true));
    
    assertThat(project.getStatus(), is(state1));
    
    assertHistoryContains(project, startTime, endTime, expectedOperation0);
  }

  private void assertHistoryContains(Project project, Date startTime, Date endTime,
      Operation... expectedOperations) {
    int i = 0;
    List<Operation> history = project.getHistory();
    assertEquals(expectedOperations.length, history.size());
    for (Operation expectedOperation : expectedOperations) {
      Operation operation = history.get(i++);
      assertOperationEqual(expectedOperation, startTime, endTime, operation);
      startTime = operation.getTimestamp();
    }
  }

  private void assertOperationEqual(Operation expectedOperation, Date startTime, Date endTime, 
      Operation operation) {
    assertEquals(expectedOperation.getComments(), operation.getComments());
    assertEquals(expectedOperation.getUser(), operation.getUser());
    assertEquals(expectedOperation.getFromStatus(), operation.getFromStatus());
    assertEquals(expectedOperation.getToStatus(), operation.getToStatus());
    assertThat(operation.getTimestamp(), is(withinInclusivePeriod(startTime, endTime)));
  }

  public void testChangeStatusTwice() {
    project.changeStatus(true, projectManager, "Excellent");
    boolean result = project.changeStatus(true, projectManager, "Even better");
    Date endTime = new Date();

    assertTrue(result);
    assertEquals(state2, project.getStatus());

    Operation expectedOperation1 = new Operation(null, projectManager, state1,
        state2, "Even better");
    assertHistoryContains(project, startTime, endTime, expectedOperation0, expectedOperation1);

  }
}
