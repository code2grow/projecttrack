package org.jia.ptrack.domain;

public class UserMother {

	// TODO merge UserFactory into UserMother
	
	public static User makeProjectManager() {
		return UserFactory.makeProjectManager(new Department());
	}

}
