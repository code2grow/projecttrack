package org.jia.ptrack.services;

import java.util.List;

import org.acegisecurity.AccessDeniedException;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectFactory;
import org.jia.ptrack.domain.StateMachineRepository;
import org.jia.ptrack.domain.Status;
import org.jia.ptrack.domain.UserFactory;

public class MethodLevelSecurityLabTests extends AbstractPtrackServicesTest {

	private ProjectCoordinator coordinator;

	private StateMachineRepository stateMachineRepository;

	protected String[] getConfigLocations() {
		// Specify acegiForTesting.xml to override production definition
		return new String[] { "classpath*:appCtx/common/**/*.xml",
				"classpath*:appCtx/testing/**/*.xml" };
	}

	public void setProjectCoordinator(ProjectCoordinator coordinator) {
		this.coordinator = coordinator;
	}

	public void setStateMachineRepository(
			StateMachineRepository stateMachineRepository) {
		this.stateMachineRepository = stateMachineRepository;
	}

	public void testGetProjectsWaitingForProjectManager()
			{
		SecurityTestUtil.setUser(UserFactory.makeProjectManager(null));
		List projects = coordinator.getProjectsWaitingForApproval(null);
		assertNotNull(projects);
	}

	public void testGetProjectsWaitingForUpperManagement()
			{
		SecurityTestUtil.setUser(UserFactory.makeUpperManager());
		try {
			coordinator.getProjectsWaitingForApproval(null);
			fail("expected access denied");
		} catch (AccessDeniedException e) {
		}
	}

	public void testAddByProjectManager()  {
		SecurityTestUtil.setUser(UserFactory.makeProjectManager(null));
		Status initialStatus = stateMachineRepository.findTheStateMachine()
				.getInitialStatus();
		coordinator.add(ProjectFactory.makeProject3(initialStatus, null));
	}

	public void testAddByBusinessAnalyst() {
		SecurityTestUtil.setUser(UserFactory.makeBusinessAnalyst(null));
		try {
			Status initialStatus = stateMachineRepository.findTheStateMachine()
					.getInitialStatus();
			coordinator.add(ProjectFactory.makeProject3(initialStatus, null));
			fail("expected access denied");
		} catch (AccessDeniedException e) {
		}
	}

	public void testChangeStatusProjectManager()
			{
		SecurityTestUtil.setUser(UserFactory.makeProjectManager(null));
		List projects = coordinator.getAllProjects(null);
		Project project = (Project) projects.get(0);
		assertTrue(coordinator.changeStatus(project, true, "great"));
	}

	public void testChangeStatusUpperManager()
	{
		SecurityTestUtil.setUser(UserFactory.makeUpperManager());
		List projects = coordinator.getAllProjects(null);
		Project project = (Project) projects.get(0);
		try {
			coordinator.changeStatus(project, true, "great");
			fail("expected access denied");
		} catch (AccessDeniedException e) {
		}
	}

}
