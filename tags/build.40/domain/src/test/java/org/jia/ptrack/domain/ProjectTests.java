package org.jia.ptrack.domain;

import junit.framework.TestCase;

public class ProjectTests extends TestCase {
	
	
	private Project p;
	private User projectManager;
	private User businessAnalyst;

	protected void setUp() throws Exception {
		Department department = new Department("IT");
		projectManager = UserFactory.makeProjectManager(department);
		businessAnalyst = UserFactory.makeBusinessAnalyst(department);
		StateMachine stateMachine = new DefaultStateMachineFactory().makeStateMachine("test");
		Status initialStatus = stateMachine.getInitialStatus();
		p = new Project(initialStatus);
		p.setInitiatedBy(projectManager);
	}

	public void testProjectApproval() {
		assertEquals(0, p.getHistory().size());
		assertTrue(p.changeStatus(true, projectManager, "Excellent"));
		assertEquals(1, p.getHistory().size());
		assertTrue(p.changeStatus(true, projectManager, "Even better"));
		assertEquals(2, p.getHistory().size());
		assertTrue(p.changeStatus(true, businessAnalyst, "Fantastic"));
		assertEquals(3, p.getHistory().size());
		
		assertEquals(((Operation)p.getHistory().get(0)).getComments(), "Excellent");
		assertEquals(((Operation)p.getHistory().get(1)).getComments(), "Even better");
		assertEquals(((Operation)p.getHistory().get(2)).getComments(), "Fantastic");
	}

	public void testProjectApproval_invalidRole() {
		assertEquals(0, p.getHistory().size());
		assertFalse(p.changeStatus(true, businessAnalyst, "Excellent"));
		assertEquals(0, p.getHistory().size());
	}

}
