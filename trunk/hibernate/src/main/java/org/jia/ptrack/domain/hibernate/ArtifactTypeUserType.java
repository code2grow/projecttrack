package org.jia.ptrack.domain.hibernate;


import org.jia.ptrack.domain.ArtifactType;
import org.jia.ptrack.domain.EnumeratedType.EnumManager;

public class ArtifactTypeUserType extends EnumeratedUserType {

	public Class returnedClass() {
		return ArtifactType.class;
	}

	protected EnumManager getEnumManager() {
		return ArtifactType.getEnumManager();
	}

}
