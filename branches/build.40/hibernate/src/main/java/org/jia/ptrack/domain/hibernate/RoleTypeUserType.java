package org.jia.ptrack.domain.hibernate;


import org.jia.ptrack.domain.RoleType;
import org.jia.ptrack.domain.EnumeratedType.EnumManager;

public class RoleTypeUserType extends EnumeratedUserType {

	public Class returnedClass() {
		return RoleType.class;
	}

	protected EnumManager getEnumManager() {
		return RoleType.getEnumManager();
	}

}
