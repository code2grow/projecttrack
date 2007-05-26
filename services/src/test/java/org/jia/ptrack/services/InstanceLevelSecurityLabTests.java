package org.jia.ptrack.services;

import org.acegisecurity.AccessDeniedException;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.domain.ProjectFactory;
import org.jia.ptrack.domain.StateMachineRepository;
import org.jia.ptrack.domain.Status;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserFactory;
import org.jia.ptrack.domain.hibernate.PtrackDatabaseInitializer;

public class InstanceLevelSecurityLabTests extends AbstractPtrackServicesTest {

	private PtrackDatabaseInitializer initializer;

	private ProjectCoordinator coordinator;

	private StateMachineRepository stateMachineRepository;

	private Project project;

	protected String[] getConfigLocations() {
		// Specify acegiForTesting.xml to override production definition
		return new String[] {
				"classpath*:appCtx/**/*.xml",
				"classpath:appCtx/security/testing-acegi-security.xml",
				"classpath:appCtxForInstanceSecurity/security/instance-business-tier-acegi-security.xml" };
	}

	public void setProjectCoordinator(ProjectCoordinator coordinator) {
		this.coordinator = coordinator;
	}

	public void setStateMachineRepository(
			StateMachineRepository stateMachineRepository) {
		this.stateMachineRepository = stateMachineRepository;
	}

	public void setInitializer(PtrackDatabaseInitializer initializer) {
		this.initializer = initializer;
	}

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		User user = initializer
						.getMarketingDepartmentProjectManager();
		SecurityTestUtil.setUser(user);
		Status initialStatus = stateMachineRepository.findTheStateMachine()
				.getInitialStatus();
		project = ProjectFactory.makeProject3(initialStatus, user);
		coordinator.add(project);
		assertNotNull(project.getInitiatedBy());
		assertNotNull(project.getInitiatedBy().getDepartment());
	}

	public void testGetAccessSameDepartments(){

		System.out.println("Getting project 1");
		assertNotNull(coordinator.get(project.getId()));
	}

	public void testGetAccessUpperManager(){

		SecurityTestUtil.setUser(UserFactory.makeUpperManager());

		System.out.println("Getting project 2");
		assertNotNull(coordinator.get(project.getId()));
	}

	public void testGetAccessAcrossDepartments() {

		System.out.println("Getting project 1");
		assertNotNull(coordinator.get(project.getId()));

		SecurityTestUtil.setUser(UserFactory.makeUpperManager());

		System.out.println("Getting project 2");
		assertNotNull(coordinator.get(project.getId()));

		SecurityTestUtil.setUser(initializer.getItDepartmentProjectManager());

		System.out.println("Getting project 3");

		try {
			coordinator.get(project.getId());
			fail("access should have been denied");
		} catch (AccessDeniedException e) {
			// expected this
		}
	}

	public void testChangeStatusSameDepartments() {

		SecurityTestUtil.setUser(initializer
				.getMarketingDepartmentProjectManager());
		coordinator.changeStatus(project, true, "great project");

	}

	public void testChangeStatusAcrossDepartments() {

		try {
			SecurityTestUtil.setUser(initializer
					.getItDepartmentProjectManager());
			coordinator.changeStatus(project, true, "great project");
			fail("access should have been denied");
		} catch (AccessDeniedException e) {
			// expected this
		}
	}

	public void testGetAllProjectsInIT() {
		SecurityTestUtil.setUser(initializer.getItDepartmentProjectManager());
		System.out.println("calling method");
		assertEquals(3, coordinator.getAllProjects(ProjectColumnType.NAME).size());
		System.out.println("done calling method");
	}

	public void testGetAllProjectsInMarketing() {
		SecurityTestUtil.setUser(initializer.getMarketingDepartmentProjectManager());
		assertEquals(1, coordinator.getAllProjects(ProjectColumnType.NAME).size());
	}

	public void testGetAllProjectsForUpper() {
		SecurityTestUtil.setUser(UserFactory.makeUpperManager());
		assertEquals(4, coordinator.getAllProjects(ProjectColumnType.NAME).size());
	}
	
	public void testGetProjectsWaitingApprovalInIT() {
		SecurityTestUtil.setUser(initializer.getItDepartmentProjectManager());
		assertEquals(2, coordinator.getProjectsWaitingForApproval(ProjectColumnType.NAME).size());
	}
	
	public void testGetProjectsWaitingApprovalInMarketing() {
		SecurityTestUtil.setUser(initializer.getMarketingDepartmentProjectManager());
		assertEquals(1, coordinator.getProjectsWaitingForApproval(ProjectColumnType.NAME).size());
	}

}
