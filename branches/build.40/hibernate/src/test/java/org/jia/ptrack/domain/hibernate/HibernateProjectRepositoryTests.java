package org.jia.ptrack.domain.hibernate;

import java.util.List;

import net.chrisrichardson.ormunit.hibernate.HibernatePersistenceTests;
import net.chrisrichardson.util.TxnCallback;

import org.jia.ptrack.domain.DataStoreException;
import org.jia.ptrack.domain.ObjectNotFoundException;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.domain.ProjectFactory;
import org.jia.ptrack.domain.ProjectRepository;
import org.jia.ptrack.domain.RoleType;
import org.jia.ptrack.domain.StateMachine;
import org.jia.ptrack.domain.Status;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserFactory;
import org.ptrack.domain.hibernate.HibernatePTrackTestConstants;
import org.springframework.dao.support.DataAccessUtils;

public class HibernateProjectRepositoryTests extends HibernatePersistenceTests {

	private ProjectRepository projectRepository;

	private Status initialStatus;

	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}

	public void setProjectRepository(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	protected void onSetUp() throws Exception {
		super.onSetUp();
		StateMachine sm = (StateMachine) DataAccessUtils
				.uniqueResult(getHibernateTemplate().find("from StateMachine where name = ?", "default"));
		initialStatus = sm.getInitialStatus();
	}

	public void testAddProject() throws DataStoreException {
		Project project = ProjectFactory.makeProject3(initialStatus, null);
		projectRepository.add(project);

		assertNotNull(getHibernateTemplate().get(Project.class,
				new Integer(project.getId())));

	}

	public void testGetProject() throws ObjectNotFoundException,
			DataStoreException {
		final Project project = ProjectFactory.makeProject3(initialStatus, null);
		getHibernateTemplate().save(project);

		Project p2 = projectRepository.get(project.getId());
		assertNotNull(p2);
		assertEquals(project.getId(), p2.getId());
	}

	public void testGetAllProjectsSortedByByName() throws ObjectNotFoundException,
			DataStoreException {
		assertNotEmpty(projectRepository.getAllProjects(ProjectColumnType.NAME));
		assertNotEmpty(projectRepository.getAllProjects(ProjectColumnType.NAME));
	}

	public void testGetAllProjectsSortedByByRole() throws ObjectNotFoundException,
			DataStoreException {
		assertNotEmpty(projectRepository.getAllProjects(ProjectColumnType.ROLE));
	}

	public void testGetAllProjectsSortedByByStatus()
			throws ObjectNotFoundException, DataStoreException {
		assertNotEmpty(projectRepository.getAllProjects(ProjectColumnType.STATUS));
	}

	public void testGetAllProjectsSortedByByType() throws ObjectNotFoundException,
			DataStoreException {
		assertNotEmpty(projectRepository.getAllProjects(ProjectColumnType.TYPE));
	}

	private void assertNotEmpty(List projects) {
		assertFalse(projects.isEmpty());
	}

	public void testGetProjectsWaitingApprovalByRoleSortedByByName()
			throws ObjectNotFoundException, DataStoreException {
		assertNotEmpty(projectRepository.getProjectsWaitingApprovalByRole(RoleType.PROJECT_MANAGER,
				ProjectColumnType.NAME));
	}

	public void testGetProjectsWaitingApprovalByRoleSortedByByRole()
			throws ObjectNotFoundException, DataStoreException {
		final List<Project> projects = projectRepository.getProjectsWaitingApprovalByRole(RoleType.PROJECT_MANAGER,
								ProjectColumnType.ROLE);
		assertNotEmpty(projects);
		System.out.println("<<<<Updating.....");
		doWithTransaction(new TxnCallback(){

			public void execute() throws Throwable {
				Project project = projectRepository.get(projects.get(0).getId());
				User projectManager = UserFactory.makeProjectManager(null);
				while (project.changeStatus(true, projectManager, "Cool!"));
				System.out.println("committing");
			}});
		System.out.println(">> DONE Updating");
		List projects2 = projectRepository.getProjectsWaitingApprovalByRole(RoleType.PROJECT_MANAGER,
				ProjectColumnType.ROLE);
		System.out.println(">> QUERIED ");
		assertEquals(projects.size() - 1, projects2.size());
		
	}

	public void testGetProjectsWaitingApprovalByRoleSortedByByStatus()
			throws ObjectNotFoundException, DataStoreException {
		assertNotEmpty(projectRepository.getProjectsWaitingApprovalByRole(RoleType.PROJECT_MANAGER,
				ProjectColumnType.STATUS));
	}

	public void testGetProjectsWaitingApprovalByRoleSortedByByType()
			throws ObjectNotFoundException, DataStoreException {
		assertNotEmpty(projectRepository.getProjectsWaitingApprovalByRole(RoleType.PROJECT_MANAGER,
				ProjectColumnType.TYPE));
	}
	
	public void testUpdate() throws DataStoreException {
		Project project = ProjectFactory.makeProject3(initialStatus, null);
		projectRepository.add(project);
		
		project = projectRepository.get(project.getId());
		
		String newDescription = "new description: " + Long.toString(System.currentTimeMillis());
		project.setDescription(newDescription);
		
		logger.debug("updating");
		projectRepository.update(project);
		logger.debug("updated");

		project = projectRepository.get(project.getId());
		assertEquals(newDescription, project.getDescription());
	}

}
