package org.ptrack.domain.hibernate;

import net.chrisrichardson.ormunit.hibernate.HibernateMappingTests;

import org.hibernate.engine.CascadeStyle;
import org.jia.ptrack.domain.Operation;
import org.jia.ptrack.domain.Project;

public class HibernateProjectOneToManyTests extends HibernateMappingTests {

	// FIXME Consider a base class??
	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}

	public void testMappingForOperationHistoryField() {
		assertClassMapping(Project.class, "Project");
		assertListField("operationHistory")
			.assertForeignKey("PROJECT_ID")
			.assertIndexColumn("OPERATION_INDEX")
			.assertElementClass(Operation.class)
			.cascade(CascadeStyle.ALL_DELETE_ORPHAN);
	}

}
