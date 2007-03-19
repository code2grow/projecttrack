package org.ptrack.domain.hibernate;

import net.chrisrichardson.ormunit.hibernate.HibernateMappingTests;
import net.chrisrichardson.ormunit.hibernate.ListFieldMapping;

import org.hibernate.engine.CascadeStyle;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.hibernate.ArtifactTypeUserType;
import org.jia.ptrack.domain.hibernate.ProjectTypeUserType;

public class HibernateProjectUserTypeFieldTests extends HibernateMappingTests {

	// FIXME Consider a base class??
	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}
	
	public void testMappingUserTypeFields() {
		assertClassMapping(Project.class, "Project");
		
		assertField("type", "TYPE", ProjectTypeUserType.class);

		assertListField("artifacts");
		ListFieldMapping artifactsMapping = getListFieldMapping("artifacts");
		artifactsMapping.assertForeignKey("PROJECT_ID");
		artifactsMapping.assertIndexColumn("ARTIFACT_INDEX");
		artifactsMapping.assertTable("PROJECT_ARTIFACTS");
		artifactsMapping.assertElementClass(ArtifactTypeUserType.class);
		artifactsMapping.cascade(CascadeStyle.ALL);
	}


}
