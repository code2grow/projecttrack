package org.jia.ptrack.services;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectRepository;
import org.jia.ptrack.domain.UserFactory;
import org.jia.ptrack.domain.hibernate.HibernateInitializer;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class ProjectCoordinatorImplTests extends
		AbstractDependencyInjectionSpringContextTests {

	private ProjectCoordinator coordinator;

	private HibernateInitializer initializer;

	private ProjectRepository projectRepository;

	private HibernateTemplate template;

	protected String[] getConfigLocations() {
		return new String[] { "classpath*:appCtx/**/*.xml", 
				"classpath:appCtx/security/testing-acegi-security.xml" };
	}

	public void setProjectCoordinator(ProjectCoordinator coordinator) {
		this.coordinator = coordinator;
	}

	public void setInitializer(HibernateInitializer initializer) {
		this.initializer = initializer;
	}

	public void setHibernateTemplate(HibernateTemplate template) {
		this.template = template;
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
		SecurityTestUtil.setUser(UserFactory.makeProjectManager(null));
		System.out.println("updating first time");
		Project project = initializer.getProject3();
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
}
