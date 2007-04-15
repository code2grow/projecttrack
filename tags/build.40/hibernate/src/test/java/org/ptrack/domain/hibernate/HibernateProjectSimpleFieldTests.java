package org.ptrack.domain.hibernate;

import net.chrisrichardson.ormunit.hibernate.HibernateMappingTests;

import org.jia.ptrack.domain.Project;

public class HibernateProjectSimpleFieldTests extends HibernateMappingTests {

	// FIXME Consider a base class??
	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}
	
	public void testMappingForSimpleFields() {
		assertClassMapping(Project.class, "Project");
		assertIdField("id", "ID");
		assertField("name", "NAME");
		assertField("description", "DESCRIPTION");
		assertField("initialComments", "INITIAL_COMMENTS");
	}

}
