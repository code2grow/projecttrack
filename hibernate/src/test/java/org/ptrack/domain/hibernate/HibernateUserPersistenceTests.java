package org.ptrack.domain.hibernate;

import net.chrisrichardson.ormunit.hibernate.HibernatePersistenceTests;

import org.jia.ptrack.domain.RoleType;
import org.jia.ptrack.domain.User;

public class HibernateUserPersistenceTests extends HibernatePersistenceTests {

	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}
	
	public void test() {
		System.out.println(RoleType.getEnumManager().getInstances());
		User user = (User) hibernateTemplate.get(User.class, "upper_mgr");
		assertNotNull(user);
	}
}
