package org.jia.ptrack.facade;

import java.util.List;

import org.jia.ptrack.domain.UserMother;
import org.jia.ptrack.services.SecurityTestUtil;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class ProjectCoordinatorFacadeTests extends
		AbstractDependencyInjectionSpringContextTests {

	private ProjectCoordinatorFacade facade;
	
	protected String[] getConfigLocations() {
		return new String[] {
				"classpath*:appCtx/common/**/*.xml",
				"classpath*:appCtx/testing/**/*.xml",
				"classpath*:appCtx/facade/**/*.xml",
		};
	}

	
	public void setFacade(ProjectCoordinatorFacade facade) {
		this.facade = facade;
	}


	public void test() {
		SecurityTestUtil.setUser(UserMother.makeProjectManager(null));
		List<ProjectSummary> summaries = facade.getProjectsWaitingForApproval(null);
		assertFalse(summaries.isEmpty());
		ProjectDetails details = facade.getProjectDetails(summaries.get(0).getId());
		assertNotNull(details);
		List<ProjectSummary> summaries2 = facade.approveProject(details.getId(), details.getVersion(), "Great");
	}

}
