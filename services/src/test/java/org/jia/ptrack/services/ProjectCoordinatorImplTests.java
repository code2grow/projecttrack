package org.jia.ptrack.services;

import net.chrisrichardson.ormunit.hibernate.HibernatePersistenceTests;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectMother;
import org.jia.ptrack.domain.ProjectRepository;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserMother;
import org.jia.ptrack.domain.hibernate.PtrackDatabaseInitializer;
import org.springframework.dao.OptimisticLockingFailureException;

public class ProjectCoordinatorImplTests extends
		HibernatePersistenceTests<ProjectCoordinatorImplTests> {

	private ProjectCoordinator coordinator;

	private PtrackDatabaseInitializer initializer;

	private ProjectRepository projectRepository;

	protected String[] getConfigLocations() {
		return new String[] { "classpath*:appCtx/common/**/*.xml",
				"classpath*:appCtx/testing/**/*.xml" };
	}

	public void setProjectCoordinator(ProjectCoordinator coordinator) {
		this.coordinator = coordinator;
	}

	public void setInitializer(PtrackDatabaseInitializer initializer) {
		this.initializer = initializer;
	}

	protected void onSetUp() throws Exception {
		super.onSetUp();
		SecurityTestUtil.clearUser();
	}

	protected void onTearDown() throws Exception {
		SecurityTestUtil.clearUser();
		super.onTearDown();
	}

	public void testChangeStatus() throws Exception {
		SecurityTestUtil.setUser(UserMother.makeProjectManager(null));
		System.out.println("updating first time");
		Project project = projectRepository.get(initializer.getProjectInProposalState().getId());
		assertTrue(coordinator.changeStatus(project, true, "great project"));

		System.out.println("updating again");

		project = projectRepository.get(project.getId());
		assertTrue(coordinator.changeStatus(project, true,
				"really great project"));
		System.out.println("updating again");

		project = projectRepository.get(project.getId());
		assertFalse(coordinator.changeStatus(project, true,
				"really great project"));
	}

	public void setProjectRepository(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public void testSimultaneousEdit() {
		User projectManager = initializer.getItDepartmentProjectManager();
		SecurityTestUtil.setUser(projectManager);
		Project project1 = ProjectMother.makeProjectInProposalState(initializer.getInitialState(), projectManager);
		projectRepository.add(project1);
		Project project2 = projectRepository.get(project1.getId());
		assertFalse(project1 == project2);
		assertTrue(coordinator.changeStatus(project1, true, "great project"));
		try {
			assertTrue(coordinator
					.changeStatus(project2, true, "great project"));
			fail("Expected exception");
		} catch (OptimisticLockingFailureException e) {

		}
	}
}
