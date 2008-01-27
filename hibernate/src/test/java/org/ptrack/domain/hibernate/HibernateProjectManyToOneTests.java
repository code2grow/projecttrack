package org.ptrack.domain.hibernate;

import net.chrisrichardson.ormunit.hibernate.HibernateMappingTests;

import org.hibernate.engine.CascadeStyle;
import org.jia.ptrack.domain.Project;

public class HibernateProjectManyToOneTests extends HibernateMappingTests {

	// FIXME Consider a base class??
	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}

	public void testStatusFieldMapping() {
		assertClassMapping(Project.class, "Project");
		assertManyToOneField("status", "status_id").cascade(CascadeStyle.NONE);
	}

	public void testInitiatedByMapping() {
		assertClassMapping(Project.class, "Project");
		assertManyToOneField("initiatedBy", "initiated_by").cascade(CascadeStyle.NONE);
	}
}
