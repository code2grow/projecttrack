package org.jia.ptrack.services;

import java.util.List;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.domain.UserFactory;

public class ProgrammaticSecurityLabTests extends AbstractPtrackServicesTest {

	private ProjectCoordinator coordinator;
	
	protected String[] getConfigLocations() {
		// Specify acegiForTesting.xml to override production definition
		return new String[] { "classpath*:appCtx/common/**/*.xml",
				"classpath*:appCtx/testing/**/*.xml" };
	}
	
	public void setProjectCoordinator(ProjectCoordinator coordinator) {
		this.coordinator = coordinator;
	}
	
	public void testGetProjectsWaitingForProjectManager() {
		SecurityTestUtil.setUser(UserFactory.makeProjectManager(null));
		List projects = coordinator.getProjectsWaitingForApproval(null);
		assertEquals(2, projects.size());
	}

	public void testGetProjectsWaitingForBusinessAnalyst() {
		SecurityTestUtil.setUser(UserFactory.makeBusinessAnalyst(null));
		List projects = coordinator.getProjectsWaitingForApproval(ProjectColumnType.NAME);
		assertEquals(1, projects.size());
		assertEquals("Test Project #1", ((Project)projects.get(0)).getName());
	}

	public void testGetProjectsWaitingForDevelopmentManager() {
		SecurityTestUtil.setUser(UserFactory.makeDevelopmentManager(null));
		List projects = coordinator.getProjectsWaitingForApproval(null);
		assertEquals(0, projects.size());
	}

	// FIXME - we need a better testing strategy
	
	public void testApproveProject() {
		SecurityTestUtil.setUser(UserFactory.makeProjectManager(null));
		List projects = coordinator.getProjectsWaitingForApproval(null);
		Project project = (Project) projects.get(0);
		assertTrue(coordinator.changeStatus(project, true, "excellent project"));
	}
}
