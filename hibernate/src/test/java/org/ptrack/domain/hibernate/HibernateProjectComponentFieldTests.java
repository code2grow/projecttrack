package org.ptrack.domain.hibernate;

import net.chrisrichardson.ormunit.hibernate.ComponentFieldMapping;
import net.chrisrichardson.ormunit.hibernate.HibernateMappingTests;

import org.jia.ptrack.domain.Project;

public class HibernateProjectComponentFieldTests extends HibernateMappingTests {

	// FIXME Consider a base class??
	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}
	
	public void testMappingForComponentField() {
		assertClassMapping(Project.class, "Project");
		assertComponentField("requirementsContact");
		ComponentFieldMapping requirementsContactMapping = getComponentFieldMapping("requirementsContact");
		requirementsContactMapping.assertField("name", "requirements_contact_name");
		requirementsContactMapping.assertField("email", "requirements_contact_email");
	}
}
