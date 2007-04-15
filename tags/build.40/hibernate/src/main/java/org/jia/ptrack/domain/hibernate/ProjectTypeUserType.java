package org.jia.ptrack.domain.hibernate;


import org.jia.ptrack.domain.ProjectType;
import org.jia.ptrack.domain.EnumeratedType.EnumManager;

public class ProjectTypeUserType extends EnumeratedUserType {

	public Class returnedClass() {
		return ProjectType.class;
	}

	protected EnumManager getEnumManager() {
		return ProjectType.getEnumManager();
	}

}
