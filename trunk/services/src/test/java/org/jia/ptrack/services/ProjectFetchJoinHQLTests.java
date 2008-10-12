package org.jia.ptrack.services;

import java.util.Iterator;
import java.util.List;

import org.jia.ptrack.domain.Password;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.domain.RoleType;
import org.jia.ptrack.domain.UserId;
import org.jia.ptrack.domain.UserMother;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class ProjectFetchJoinHQLTests extends AbstractDependencyInjectionSpringContextTests {

	private ProjectCoordinator coordinator;

	protected String[] getConfigLocations() {
		return new String[] { "classpath*:appCtx/common/**/*.xml",
				// $pia-lab-begin(spring-transactions)$
				"classpath*:appCtx/detachedObjects/**/*.xml", 
				// $pia-lab-end$
				"classpath*:appCtx/testing/**/*.xml"  };
	}

	public void setProjectCoordinator(ProjectCoordinator coordinator) {
		this.coordinator = coordinator;
	}

	protected void onTearDown() throws Exception {
		SecurityTestUtil.clearUser();
		super.onTearDown();
	}

	protected void onSetUp() throws Exception {
		super.onSetUp();
		SecurityTestUtil.clearUser();
		SecurityTestUtil.setUser(new UserId("proj_mgr"), new Password("faces"), new RoleType[] {});
	}

	public void testGetAllProjects()  {
		SecurityTestUtil.setUser(UserMother.makeProjectManager(null));
		List projects = coordinator.getAllProjects(ProjectColumnType.NAME);
		for (Iterator it = projects.iterator(); it.hasNext();) {
			Project project = (Project) it.next();
			assertNotNull(project.getStatus().toString());
			project.getStatus().getRole();
		}
		assertEquals(3, projects.size());
	}

}
