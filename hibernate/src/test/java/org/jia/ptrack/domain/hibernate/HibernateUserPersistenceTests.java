package org.jia.ptrack.domain.hibernate;

import net.chrisrichardson.ormunit.hibernate.HibernatePersistenceTests;

import org.jia.ptrack.domain.RoleType;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserMother;

public class HibernateUserPersistenceTests extends HibernatePersistenceTests {

	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}
	
	public void test() {
		System.out.println(RoleType.getEnumManager().getInstances());
		User user = UserMother.makeUpperManager();
		save(user);
		user = (User) hibernateTemplate.get(User.class, user.getId());
		assertNotNull(user);
	}
}
