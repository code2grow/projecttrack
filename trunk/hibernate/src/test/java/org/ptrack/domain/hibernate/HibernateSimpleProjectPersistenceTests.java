package org.ptrack.domain.hibernate;

import java.io.Serializable;
import java.util.List;

import net.chrisrichardson.ormunit.hibernate.HibernatePersistenceTests;
import net.chrisrichardson.util.TxnCallback;

import org.jia.ptrack.domain.Project;

public class HibernateSimpleProjectPersistenceTests extends
		HibernatePersistenceTests {

	private static final String PROJECT_NAME = "My Project";

	private static final String NEW_PROJECT_NAME = "My Really Project";

	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS_WITH_EMPTY_DB;
	}

	public void testSave() {
		// pia-lab-method-stub(hibernate-repository)
		Project p = new Project();
		p.setName(PROJECT_NAME);
		Serializable savedId = getHibernateTemplate().save(p);
		assertEquals("Id field not mapped?", savedId, new Integer(p.getId()));
	}

	public void testGet() {
		// pia-lab-method-stub(hibernate-repository)
		Project p = new Project();
		p.setName(PROJECT_NAME);

		getHibernateTemplate().save(p);

		Project p2 = (Project) getHibernateTemplate().get(Project.class,
				new Integer(p.getId()));
		assertEquals(PROJECT_NAME, p2.getName());
	}

	public void testUpdate() {
		// pia-lab-method-stub(hibernate-repository)
		final Project p = new Project();
		p.setName(PROJECT_NAME);

		getHibernateTemplate().save(p);

		doWithTransaction(new TxnCallback() {

			public void execute() throws Throwable {
				Project p2 = (Project) getHibernateTemplate().get(
						Project.class, new Integer(p.getId()));
				p2.setName(NEW_PROJECT_NAME);
			}
		});

		Project p3 = (Project) getHibernateTemplate().get(Project.class,
				new Integer(p.getId()));
		assertEquals(NEW_PROJECT_NAME, p3.getName());
	}

	public void testFind() {
		// pia-lab-method-stub(hibernate-repository)
		Project p = new Project();
		p.setName(PROJECT_NAME);

		getHibernateTemplate().save(p);

		List projects = getHibernateTemplate().find("from Project");
		assertFalse(projects.isEmpty());
	}

	public void testDelete() {
		// pia-lab-method-stub(hibernate-repository)
		Project p = new Project();
		p.setName(PROJECT_NAME);

		getHibernateTemplate().save(p);

		Project p2 = (Project) getHibernateTemplate().get(Project.class,
				new Integer(p.getId()));
		assertEquals(PROJECT_NAME, p2.getName());

		getHibernateTemplate().delete(p2);

		assertNull(getHibernateTemplate().get(Project.class,
				new Integer(p.getId())));
	}

}
