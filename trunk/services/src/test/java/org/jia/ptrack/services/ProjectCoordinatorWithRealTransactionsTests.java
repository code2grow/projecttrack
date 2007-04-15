package org.jia.ptrack.services;

import java.util.List;

import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.domain.UserFactory;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class ProjectCoordinatorWithRealTransactionsTests extends
		AbstractDependencyInjectionSpringContextTests {

	private ProjectCoordinator coordinator;

	protected void onSetUp() throws Exception {
		SecurityTestUtil.clearUser();
		super.onSetUp();
	}

	protected void onTearDown() throws Exception {
		SecurityTestUtil.clearUser();
		super.onTearDown();
	}

	protected String[] getConfigLocations() {
		return new String[] { "classpath*:appCtx/**/*.xml",
				"classpath:appCtx/security/testing-acegi-security.xml" };
	}

	public void setProjectCoordinator(ProjectCoordinator coordinator) {
		this.coordinator = coordinator;
	}

	public void test() throws Exception {
		SecurityTestUtil.setUser(UserFactory.makeProjectManager(null));
		List projects = coordinator.getAllProjects(ProjectColumnType.NAME);
		assertNotNull(projects);
	}

}
