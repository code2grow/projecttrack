package org.jia.ptrack.domain.hibernate;

import java.sql.SQLException;

import net.chrisrichardson.ormunit.hibernate.HibernatePersistenceTests;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jia.ptrack.domain.DefaultStateMachineFactory;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectMother;
import org.jia.ptrack.domain.StateMachine;
import org.jia.ptrack.domain.Status;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserRepository;
import org.springframework.orm.hibernate3.HibernateCallback;

public class HibernateProjectPersistenceTests extends
		HibernatePersistenceTests<HibernateProjectPersistenceTests> {
	private Project project;
	private UserRepository userRepository;
	private Integer pid;

	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}

	public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
	
	public void testSaveProject() {

		createAndSaveProject();
	}

	private void createAndSaveProject() {
		makeProject();
		saveProject();
	}

	public void testUpdateProject() {
		createAndSaveProject();

		txnThis.changeStatusFirstTime();

		txnThis.verifyProjectState();

		txnThis.changeStatusAgain();

	}

	public void changeStatusFirstTime() {
		logger.debug("changing status 1");

    User projectManager = userRepository.findUser("proj_mgr");
		Project project = load(Project.class, pid);
		project.changeStatus(true, projectManager, "I like it");
		logger.debug("updated project 1");
	}

	public void verifyProjectState() {
		logger.debug("checking project 2");

		project = load(Project.class, pid);
		project.getHistory().size();
		project.isValidStateChange(true);
		logger.debug("checked project");
	}

	public void changeStatusAgain() {
		logger.debug("changing status 2");

    User projectManager = userRepository.findUser("proj_mgr");
		project = load(Project.class, pid);
		project.changeStatus(true, projectManager, "I like it again");
		logger.debug("updated project 2");
	}

	public void testDeleteProject() {
		testUpdateProject();
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Project p = (Project) session.get(Project.class, pid);
				logger.debug("deleting project");
				session.delete(p);
				logger.debug("flushing");
				session.flush();
				logger.debug("done flushing");
				return null;
			}
		});
	}

	private void saveProject() {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				logger.debug("Saving project");
				session.save(project);
				logger.debug("flushing");
				session.flush();
				logger.debug("flushed");
				return null;
			}
		});
		pid = new Integer(project.getId());
	}

	private void makeProject() {
		StateMachine stateManager = DefaultStateMachineFactory
				.makeStateMachine("default");
		Status initialStatus = stateManager.getInitialStatus();
		save(initialStatus);
		getHibernateTemplate().flush();
		project = ProjectMother.makeProjectInProposalState(initialStatus, null);
	}

}
