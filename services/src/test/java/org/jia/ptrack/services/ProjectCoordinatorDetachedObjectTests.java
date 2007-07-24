package org.jia.ptrack.services;

import java.util.Iterator;
import java.util.List;

import org.jia.ptrack.domain.Operation;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.domain.UserFactory;
import org.jia.ptrack.domain.hibernate.PtrackDatabaseInitializer;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class ProjectCoordinatorDetachedObjectTests extends
		AbstractDependencyInjectionSpringContextTests {

	private ProjectCoordinator coordinator;

	private PtrackDatabaseInitializer initializer;

	protected String[] getConfigLocations() {
		// Specify acegiForTesting.xml to override production definition
		return new String[] { "classpath*:appCtx/common/**/*.xml"
				// $pia-lab-begin(spring-transactions)$
				,"classpath*:appCtx/detachedObjects/**/*.xml" 
				,"classpath*:appCtx/testing/**/*.xml" 
				// $pia-lab-end$
				};
	}

	public void setProjectCoordinator(ProjectCoordinator coordinator) {
		this.coordinator = coordinator;
	}

	public void setInitializer(PtrackDatabaseInitializer initializer) {
		this.initializer = initializer;
	}

	protected void onTearDown() throws Exception {
		SecurityTestUtil.clearUser();
		super.onTearDown();
	}

	protected void onSetUp() throws Exception {
		super.onSetUp();
		SecurityTestUtil.clearUser();
		SecurityTestUtil.setUser(initializer.getItDepartmentProjectManager());
	}

	public void testGetProject() {
		Project project = coordinator.get(initializer.getProject1().getId());
		assertFalse(project.getHistory().isEmpty());
		assertHistory(project.getHistory());
		assertFalse(project.getArtifacts().length == 0);
		assertNotNull(project.getStatus().getName());
		project.getStatus().getRole();
		assertNotNull(project.getInitiatedBy().toString());

		project = coordinator.get(initializer.getProject2().getId());
		assertFalse(project.getHistory().isEmpty());
		assertHistory(project.getHistory());
		assertFalse(project.getArtifacts().length == 0);
		assertNotNull(project.getStatus().getName());
		assertNotNull(project.getStatus().getRole());
		assertNotNull(project.getInitiatedBy().toString());

		project = coordinator.get(initializer.getProject3().getId());
		assertTrue(project.getHistory().isEmpty());
		assertHistory(project.getHistory());
		assertFalse(project.getArtifacts().length == 0);
		assertNotNull(project.getStatus().getName());
		assertNotNull(project.getStatus().getRole());
		assertNotNull(project.getInitiatedBy().toString());
	}

	private void assertHistory(List history) {
		for (Iterator it = history.iterator(); it.hasNext();) {
			Operation op = (Operation) it.next();
			assertNotNull(op.getFromStatus().getName());
			assertNotNull(op.getToStatus().getName());
			assertNotNull(op.getUser().getRole());
		}

	}

	public void testGetProjectsWaitingForApproval() {
		SecurityTestUtil.setUser(UserFactory.makeProjectManager(null));
		List projects = coordinator
				.getProjectsWaitingForApproval(ProjectColumnType.NAME);
		for (Iterator it = projects.iterator(); it.hasNext();) {
			Project project = (Project) it.next();
			assertNotNull(project.getStatus().toString());
			assertNotNull(project.getStatus().getRole());
		}
		assertEquals(1, projects.size());
	}


}
